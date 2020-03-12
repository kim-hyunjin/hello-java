package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardService photoBoardService;

  public PhotoBoardUpdateServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호?");
    PhotoBoard old = photoBoardService.get(no);

    if (old == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(
        Prompt.getString(in, out, String.format("제목(%s)?", old.getTitle()), old.getTitle()));
    photoBoard.setNo(no);

    printPhotoFiles(out, old);

    out.println("\n사진은 일부만 변경할 수 없습니다.");
    String response = Prompt.getString(in, out, "사진을 변경하시겠습니다? (Y/N)");

    if (response.equalsIgnoreCase("y")) {
      // 사용자가 입력한 파일 목록을 PhotoBoard 객체에 저장한다.
      photoBoard.setFiles(inputPhotoFiles(in, out));
    }
    photoBoardService.update(photoBoard);
    out.println("사진 게시글을 변경했습니다.");

  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    // 새로 등록할 첨부파일을 입력받는다.
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 엔터를 치면 파일 추가를 마칩니다.");
    ArrayList<PhotoFile> files = new ArrayList<>();
    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일?");
      if (filepath.length() == 0) {
        if (files.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 사진 파일을 등록해야합니다.");
          continue;
        }
      }
      files.add(new PhotoFile().setFilePath(filepath));
    }
    return files;
  }

  private void printPhotoFiles(PrintStream out, PhotoBoard photoBoard) throws Exception {
    out.println("사진파일: ");
    for (PhotoFile file : photoBoard.getFiles()) {
      out.printf("> %s\n", file.getFilePath());
    }
  }

}
