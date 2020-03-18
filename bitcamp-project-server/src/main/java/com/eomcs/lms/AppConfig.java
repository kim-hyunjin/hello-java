package com.eomcs.lms;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.sql.MybatisDaoFactory;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.SqlSessionFactoryProxy;

// Spring IoC 컨테이너가 탐색할 패키지 설정
@ComponentScan(value = "com.eomcs.lms")
public class AppConfig {

  // Spring IoC 컨테이너에 수동으로 등록할 객체를 등록하고 싶다면
  // 그 객체를 만들어주는 팩토리 메서드를 정의해야 한다.
  // 단, 메서드 선언부에 @Bean 애노테이션을 붙여야 한다.
  // 그래야만 Spring IoC 컨테이너가 메서드를 호출하고 그 리턴 값을 보관한다.

  @Bean
  public MybatisDaoFactory daoFactory(SqlSessionFactory sqlSessionFactory) {
    // DAO 프록시 객체를 생성해 줄 Factory를 준비
    return new MybatisDaoFactory(sqlSessionFactory);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/lms/conf/mybatis-config.xml");

    // 트랜잭션 제어를 위해 오리지널 객체를 프록시 객체에 담아 사용한다.
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
  }

  @Bean
  public PlatformTransactionManager platformTransactionManager(
      // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라.
      // 단 IoC 컨테이너 안에 들어있는 값이어야 한다.
      SqlSessionFactory sqlSessionFactory) {
    return new PlatformTransactionManager(sqlSessionFactory);
  }

  @Bean
  public BoardDao boardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(BoardDao.class);
  }

  @Bean
  public LessonDao lessonDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(LessonDao.class);
  }

  @Bean
  public MemberDao memberDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(MemberDao.class);
  }

  @Bean
  public PhotoBoardDao photoBoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  @Bean
  public PhotoFileDao photoFileDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoFileDao.class);
  }
}
