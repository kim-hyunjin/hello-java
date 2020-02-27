package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardAddServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  LessonDao lessonDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, LessonDao lessonDao,
      PhotoFileDao photoFileDao) {

    this.photoBoardDao = photoBoardDao;
    this.lessonDao = lessonDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();
    out.println("제목? \n!{}!");
    photoBoard.setTitle(in.nextLine());

    out.println("수업 번호? \n!{}!");
    int lessonNo = Integer.parseInt(in.nextLine());
    Lesson lesson = lessonDao.findByNo(lessonNo);
    if (lesson == null) {
      out.println("수업 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setLesson(lesson);

    if (photoBoardDao.insert(photoBoard) > 0) {
      // 첨부파일을 입력받는다.
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

        // 1) 기본 생성자를 사용할 때,
        // PhotoFile file = new PhotoFile();
        // file.setFilePath(filepath);
        // file.setBoardNo(photoBoard.getNo());

        // 2) 초기 값을 설정하는 생성자를 사용할 때,
        // PhotoFile photoFile = new PhotoFile(filepath, photoBoard.getNo());

        // 3) 셋터를 통해 체인 방식으로 인스턴스 필드 값 설정하기
        files.add(new PhotoFile().setFilePath(filepath).setBoardNo(photoBoard.getNo()));
      }
      // ArryaList에 들어있는 PhotoFile 데이터를 lms_photo_file 테이블에 저장한다.
      for (PhotoFile photoFile : files) {
        photoFileDao.insert(photoFile);
      }
      out.println("새 사진 게시물을 등록했습니다.");
    } else {
      out.println("사진 게시물 등록에 실패했습니다.");
    }
  }
}