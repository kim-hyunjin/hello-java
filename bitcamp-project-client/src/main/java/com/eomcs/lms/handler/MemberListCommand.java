package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.eomcs.lms.dao.MemberDao;

public class MemberListCommand implements Command {

  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      //List<Member> members = memberDao.findAll();
   // JDBC Driver(MariaDB 프록시)를 로딩한다.
      Class.forName("org.mariadb.jdbc.Driver");

      // JDBC Driver를 이용하여 MariaDB 에 접속한다.
      Connection con = DriverManager.getConnection( //
          "jdbc:mariadb://localhost:3306/studydb", "study", "1111");

      // MariaDB에 명령을 전달할 객체 준비
      Statement stmt = con.createStatement();

      // MariaDB의 lms_member 테이블에 있는 데이터를 가져올 도구를 준비
      ResultSet rs = stmt.executeQuery( //
          "select member_id, name, email, cdt, tel, photo from lms_member");

      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
        System.out.printf("%d, %s, %s, %s, %s, %s\n", //
            rs.getInt("member_id"), //
            rs.getString("name"), //
            rs.getString("email"),
            rs.getDate("cdt"),
            rs.getString("tel"),
            rs.getString("photo")
        );
      }
      rs.close();
      stmt.close();
      con.close();
      
    }catch(Exception e) {
      System.out.println("목록조회 실패!");

    }
  }
}
