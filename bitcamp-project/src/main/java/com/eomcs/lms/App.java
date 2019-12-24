package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {
  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    LessonHandler.keyboard = keyboard;
    MemberHandler.keyboard = keyboard;
    BoardHandler.keyboard = keyboard;
    
    // BoardHandler의 메서드가 사용할 메모리만 게시판마다 따로 생성한다.
    // 데이터는 개별 관리하고 메서드는 공유한다
    LessonHandler 정규수업 = new LessonHandler();
    MemberHandler 일반회원 = new MemberHandler();
    BoardHandler 게시판1 = new BoardHandler();
    BoardHandler 게시판2 = new BoardHandler();
    BoardHandler 게시판3 = new BoardHandler();
    BoardHandler 게시판4 = new BoardHandler();
    BoardHandler 게시판5 = new BoardHandler();
    BoardHandler 게시판6 = new BoardHandler();
    
    
    String command;
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      switch (command) {
        case "/lesson/add": LessonHandler.addLesson(정규수업);
        break;
        case "/lesson/list": LessonHandler.listLesson(정규수업);
        break;
        case "/member/add": MemberHandler.addMember(일반회원);
        break;
        case "/member/list": MemberHandler.listMember(일반회원);
        break;
        case "/board/add": BoardHandler.addBoard(게시판1);
        break;
        case "/board/list": BoardHandler.listBoard(게시판1);
        break;
        case "/board/detail": BoardHandler.detailBoard(게시판1);
        break;
        case "/board2/add": BoardHandler.addBoard(게시판2);
        break;
        case "/board2/list": BoardHandler.listBoard(게시판2);
        break;
        case "/board2/detail": BoardHandler.detailBoard(게시판2);
        break;
        case "/board3/add": BoardHandler.addBoard(게시판3);
        break;
        case "/board3/list": BoardHandler.listBoard(게시판3);
        break;
        case "/board3/detail": BoardHandler.detailBoard(게시판3);
        break;
        case "/board4/add": BoardHandler.addBoard(게시판4);
        break;
        case "/board4/list": BoardHandler.listBoard(게시판4);
        break;
        case "/board4/detail": BoardHandler.detailBoard(게시판4);
        break;
        case "/board5/add": BoardHandler.addBoard(게시판5);
        break;
        case "/board5/list": BoardHandler.listBoard(게시판5);
        break;
        case "/board5/detail": BoardHandler.detailBoard(게시판5);
        break;
        case "/board6/add": BoardHandler.addBoard(게시판6);
        break;
        case "/board6/list": BoardHandler.listBoard(게시판6);
        break;
        case "/board6/detail": BoardHandler.detailBoard(게시판6);
        break;
        default:
          if(!command.equalsIgnoreCase("quit")) {
            System.out.println("잘못된 명령입니다.");
          }
      }
    } while(!command.equalsIgnoreCase("quit"));

    System.out.println("안녕!");
    keyboard.close();

  }
}
