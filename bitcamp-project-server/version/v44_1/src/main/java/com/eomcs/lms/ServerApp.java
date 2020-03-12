// LMS 서버
package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.MemberService;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.lms.servlet.BoardAddServlet;
import com.eomcs.lms.servlet.BoardDeleteServlet;
import com.eomcs.lms.servlet.BoardDetailServlet;
import com.eomcs.lms.servlet.BoardListServlet;
import com.eomcs.lms.servlet.BoardUpdateServlet;
import com.eomcs.lms.servlet.LessonAddServlet;
import com.eomcs.lms.servlet.LessonDeleteServlet;
import com.eomcs.lms.servlet.LessonDetailServlet;
import com.eomcs.lms.servlet.LessonListServlet;
import com.eomcs.lms.servlet.LessonSearchServlet;
import com.eomcs.lms.servlet.LessonUpdateServlet;
import com.eomcs.lms.servlet.LoginServlet;
import com.eomcs.lms.servlet.MemberAddServlet;
import com.eomcs.lms.servlet.MemberDeleteServlet;
import com.eomcs.lms.servlet.MemberDetailServlet;
import com.eomcs.lms.servlet.MemberListServlet;
import com.eomcs.lms.servlet.MemberSearchServlet;
import com.eomcs.lms.servlet.MemberUpdateServlet;
import com.eomcs.lms.servlet.PhotoBoardAddServlet;
import com.eomcs.lms.servlet.PhotoBoardDeleteServlet;
import com.eomcs.lms.servlet.PhotoBoardDetailServlet;
import com.eomcs.lms.servlet.PhotoBoardListServlet;
import com.eomcs.lms.servlet.PhotoBoardUpdateServlet;
import com.eomcs.lms.servlet.Servlet;
import com.eomcs.sql.SqlSessionFactoryProxy;

public class ServerApp {

  // 옵저버 관련 코드
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  // 커맨드(예: Servlet 구현체) 디자인 패턴과 관련된 코드
  Map<String, Servlet> servletMap = new HashMap<>();

  // 스레드 풀
  ExecutorService executorService = Executors.newCachedThreadPool();

  boolean serverStop = false;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }
  // 옵저버 관련코드 끝!

  public void service() {

    notifyApplicationInitialized();

    // SqlSessionFactory를 꺼낸다.
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.get("sqlSessionFactory");


    // DataLoaderListener가 준비한 DAO 객체를 꺼내 변수에 저장한다.
    MemberService memberService = (MemberService) context.get("memberService");
    BoardService boardService = (BoardService) context.get("boardService");
    LessonService lessonService = (LessonService) context.get("lessonService");
    PhotoBoardService photoBoardService = (PhotoBoardService) context.get("photoBoardService");


    // 커맨드 객체 역할을 수행하는 서블릿 객체를 맵에 보관한다.
    servletMap.put("/board/list", new BoardListServlet(boardService));
    servletMap.put("/board/add", new BoardAddServlet(boardService));
    servletMap.put("/board/detail", new BoardDetailServlet(boardService));
    servletMap.put("/board/update", new BoardUpdateServlet(boardService));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardService));
    //
    servletMap.put("/lesson/list", new LessonListServlet(lessonService));
    servletMap.put("/lesson/add", new LessonAddServlet(lessonService));
    servletMap.put("/lesson/detail", new LessonDetailServlet(lessonService));
    servletMap.put("/lesson/update", new LessonUpdateServlet(lessonService));
    servletMap.put("/lesson/delete", new LessonDeleteServlet(lessonService));
    servletMap.put("/lesson/search", new LessonSearchServlet(lessonService));
    //
    servletMap.put("/member/list", new MemberListServlet(memberService));
    servletMap.put("/member/add", new MemberAddServlet(memberService));
    servletMap.put("/member/detail", new MemberDetailServlet(memberService));
    servletMap.put("/member/update", new MemberUpdateServlet(memberService));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberService));
    servletMap.put("/member/search", new MemberSearchServlet(memberService));

    servletMap.put("/photoBoard/add", new PhotoBoardAddServlet(lessonService, photoBoardService));
    servletMap.put("/photoBoard/list", new PhotoBoardListServlet(photoBoardService, lessonService));
    servletMap.put("/photoBoard/detail", new PhotoBoardDetailServlet(photoBoardService));
    servletMap.put("/photoBoard/update", new PhotoBoardUpdateServlet(photoBoardService));
    servletMap.put("/photoBoard/delete", new PhotoBoardDeleteServlet(photoBoardService));

    servletMap.put("/auth/login", new LoginServlet(memberService));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      // System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        // System.out.println("클라이언트와 연결되었음!");
        executorService.submit(() -> {
          processRequest(socket);

          // 스레드에 보관된 SqlSession 객체를 제거한다.
          ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();

        });
        // 현재 서버 멈춤 상태라면,
        // 다음 클라이언트 요청을 받지 않고 종료한다.
        if (serverStop) {
          break;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    executorService.shutdown();
    // => 스레드풀에 소속된 스레드들의 작업이 모두 끝나면 종료한다.
    // => 따라서 shutdown()을 호출했다고 해서 모든 스레드가 즉시 작업을 멈추는 것이 아니다.
    // => 즉 스레드 종료를 예약한 다음에 바로 리턴한다.

    // 모든 스레드가 끝날 때까지 DB 커넥션을 종료하고 싶지 않다면,
    // 스레드가 모두 끝났는지 검사하며 기다려야 한다.
    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
      try {
        // 0.5초마다 깨어나서 스레드 종료 여부를 검사한다.
        Thread.sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }


    // 클라이언트 요청을 처리하는 스레드가 모두 종료된 후에
    // DB 커넥션을 닫도록 한다.
    notifyApplicationDestroyed();
    System.out.println("서버종료!");
  } // service()


  void processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      // 클라이언트가 보낸 명령을 읽는다.
      String request = in.nextLine();
      // System.out.println(request);

      if (request.equalsIgnoreCase("/server/stop")) {
        serverStop = true;
        out.println("서버를 종료합니다.");
        out.println("!end!");
        return;
      }
      Servlet servlet = servletMap.get(request);
      if (servlet != null) {
        try {
          servlet.service(in, out);
        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생!");
          out.println(e.getMessage());
        }
      } else { // 없다면? 간단한 안내 메시지를 응답한다.
        notFound(out);
      }
      out.println("!end!");
      out.flush();
      // System.out.println("클라이언트에게 응답하였음!");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }// processRequest

  private void notFound(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  public static void main(String[] args) {
    System.out.println("서버 수업 관리 시스템입니다.");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
