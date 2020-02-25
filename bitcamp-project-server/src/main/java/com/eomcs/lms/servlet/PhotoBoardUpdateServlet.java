package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    int no = Integer.parseInt(in.nextLine());
    PhotoBoard old = photoBoardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }
    out.printf("제목(%s)? \n!{}!\n", old.getTitle());
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(in.nextLine());
    photoBoard.setNo(no);

    if (photoBoardDao.update(photoBoard) > 0) {
      out.println("사진파일: ");
      List<PhotoFile> photoFiles = photoFileDao.findAll(no);
      for (PhotoFile file : photoFiles) {
        out.printf("> %s\n", file.getFilePath());
      }
      out.println("\n사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      out.println("사진을 변경하시겠습니다? (Y/N)");
      out.println("!{}!");
      String response = in.nextLine();

      if (response.equalsIgnoreCase("y")) {

        // 이 사진 게시글에 첨부된 기존 파일을 모두 삭제한다.
        photoFileDao.deleteAll(no);

        // 새로 등록할 첨부파일을 입력받는다.
        out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
        out.println("파일명 입력 없이 엔터를 치면 파일 추가를 마칩니다.");
        ArrayList<PhotoFile> files = new ArrayList<>();
        while (true) {
          out.println("사진 파일? \n!{}!");
          String filepath = in.nextLine();
          if (filepath.length() == 0) {
            if (files.size() > 0) {
              break;
            } else {
              out.println("최소 한 개의 사진 파일을 등록해야합니다.");
              continue;
            }
          }
          files.add(new PhotoFile().setFilePath(filepath).setBoardNo(photoBoard.getNo()));
        }
        // ArryaList에 들어있는 PhotoFile 데이터를 lms_photo_file 테이블에 저장한다.
        for (PhotoFile photoFile : files) {
          photoFileDao.insert(photoFile);
        }
      }


      out.println("사진 게시글을 변경했습니다.");
    } else {
      out.println("사진 게시글 변경에 실패했습니다.");
    }
  }
}
