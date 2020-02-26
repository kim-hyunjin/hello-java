package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;

// 애플리케이션이 시작되거나 종료될 때
// 데이터를 로딩하고 저장하는 일을 한다.
//
public class DataLoaderListener implements ApplicationContextListener {

  String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
  String username = "study";
  String password = "1111";

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    context.put("boardDao", new BoardDaoImpl(jdbcUrl, username, password));
    context.put("memberDao", new MemberDaoImpl(jdbcUrl, username, password));
    context.put("lessonDao", new LessonDaoImpl(jdbcUrl, username, password));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(jdbcUrl, username, password));
    context.put("photoFileDao", new PhotoFileDaoImpl(jdbcUrl, username, password));
  }// contextInitialized

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
  }// contextDestroyed

}
