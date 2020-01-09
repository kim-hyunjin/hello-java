// 인덱스가 아닌 번호로 객체를 찾는 코드를 관리하기 쉽게 별도의 메서드로 분리한다.
// => indexOfBoard(int) 메서드 추가
//
package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Prompt;

public class BoardHandler {
  /// 필드 ///
  ArrayList<Board> boardList;
  Prompt input;

  /// 생성자 ///
  public BoardHandler(Prompt input) {
    this.input = input;
    this.boardList = new ArrayList<>(); ///
  }

  public BoardHandler(Prompt input, int capacity) {
    this.input = input;
    this.boardList = new ArrayList<>(capacity);
  }

  /// 메서드 ///
  public void addBoard() {
    Board board = new Board();
    board.setNo(input.inputInt("번호? "));
    board.setTitle(input.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);
    this.boardList.add(board); 
    System.out.println("저장되었습니다.");
  }

  public void listBoard() {
    Board[] arr = new Board[this.boardList.getSize()];
    this.boardList.toArray(arr);
    for (Board b : arr) {
      System.out.printf("%d, %s, %s, %d\n", 
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    int index = indexOfBoard(input.inputInt("번호? "));
    if (index == -1) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }
    Board b = this.boardList.get(index);
    System.out.printf("번호: %d\n", b.getNo());
    System.out.printf("제목: %s\n", b.getTitle());
    System.out.printf("등록일: %s\n", b.getDate());
    System.out.printf("조회수: %d\n", b.getViewCount());
  }

  public void updateBoard() {
    int index = indexOfBoard(input.inputInt("번호? "));
    if (index == -1) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }
    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();
    newBoard.setNo(oldBoard.getNo());
    newBoard.setTitle(input.inputString("내용? ", oldBoard.getTitle()));
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setDate(new Date(System.currentTimeMillis()));
    this.boardList.set(index, newBoard);
    if (newBoard.equals(oldBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
    } else {
      System.out.println("게시글을 변경했습니다.");
    }
  }

  public void deleteBoard() {
    int index = indexOfBoard(input.inputInt("번호? "));
    if (index == -1) {
      System.out.println("게시글 번호가 유효하지 않습니다.");
      return;
    }
    this.boardList.remove(index);
    System.out.println("게시글을 삭제했습니다.");
  }

  private int indexOfBoard(int no) {
    for (int i = 0; i < this.boardList.getSize(); i++) {
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
