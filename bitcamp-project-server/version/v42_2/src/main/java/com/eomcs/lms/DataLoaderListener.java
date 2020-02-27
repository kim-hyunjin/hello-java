package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.sql.DataSource;
import com.eomcs.sql.PlatformTransactionManager;

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
    DataSource dataSource = new DataSource(jdbcUrl, username, password);
    PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
    context.put("transactionManager", txManager);
    context.put("dataSource", dataSource);
    context.put("boardDao", new BoardDaoImpl(dataSource));
    context.put("memberDao", new MemberDaoImpl(dataSource));
    context.put("lessonDao", new LessonDaoImpl(dataSource));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
    context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));
  }// contextInitialized

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
    // 애플리케이션이 종료될 때,
    // 모든 DB 커넥션을 명시적으로 끊어준다.
    // 그러면 DBMS는 timeout 될 때까지 기다릴 필요 없이
    // 클라이언트와 연결된 스레드를 즉시 해제시킬 수 있다.
    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }// contextDestroyed

}
