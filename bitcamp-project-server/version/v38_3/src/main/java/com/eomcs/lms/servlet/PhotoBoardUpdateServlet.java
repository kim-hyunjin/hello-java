package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호?");
    PhotoBoard old = photoBoardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(
        Prompt.getString(in, out, String.format("제목(%s)?\n", old.getTitle()), old.getTitle()));
    photoBoard.setNo(no);

    if (photoBoardDao.update(photoBoard) > 0) {

      printPhotoFiles(out, no);

      out.println("\n사진은 일부만 변경할 수 없습니다.");
      String response = Prompt.getString(in, out, "사진을 변경하시겠습니다? (Y/N)");

      if (response.equalsIgnoreCase("y")) {

        // 이 사진 게시글에 첨부된 기존 파일을 모두 삭제한다.
        photoFileDao.deleteAll(no);

        List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
        // ArryaList에 들어있는 PhotoFile 데이터를 lms_photo_file 테이블에 저장한다.
        for (PhotoFile photoFile : photoFiles) {
          photoFile.setBoardNo(no);
          photoFileDao.insert(photoFile);
        }
      }
      out.println("사진 게시글을 변경했습니다.");
    } else {
      out.println("사진 게시글 변경에 실패했습니다.");
    }
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

  private void printPhotoFiles(PrintStream out, int boardNo) throws Exception {
    out.println("사진파일: ");
    List<PhotoFile> photoFiles = photoFileDao.findAll(boardNo);
    for (PhotoFile file : photoFiles) {
      out.printf("> %s\n", file.getFilePath());
    }
  }

}
