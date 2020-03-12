package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.Prompt;

public class PhotoBoardListServlet implements Servlet {
  PhotoBoardService photoBoardService;
  LessonService lessonService;

  public PhotoBoardListServlet(PhotoBoardService photoBoardService, LessonService lessonService) {
    this.photoBoardService = photoBoardService;
    this.lessonService = lessonService;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int lessonNo = Prompt.getInt(in, out, "수업번호?");

    // 1) 수업 번호로 수업 정보를 가져온다.
    Lesson lesson = lessonService.get(lessonNo);

    // 2) 수업 번호에 해당하는 수업을 못찾았다면 에러메시지를 출력한다.
    if (lesson == null) {
      out.println("수업 번호가 유효하지 않습니다.");
      return;
    }

    // 2) 수업을 찾았다면, 수업명을 출력한다.
    out.printf("수업명: %s\n", lesson.getTitle());
    out.println("--------------------------------------------------------");

    // 4) 해당 수업의 사진 게시글을 가져온다.
    List<PhotoBoard> photoBoards = photoBoardService.listLessonPhoto(lessonNo);

    // 5) 클라이언트에게 게시글을 출력한다.
    for (PhotoBoard photoboard : photoBoards) {
      out.printf("%d, %s, %s, %d\n", photoboard.getNo(), photoboard.getTitle(),
          photoboard.getCreatedDate(), photoboard.getViewCount());
    }
  }
}
