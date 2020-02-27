package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.sql.DataSource;

public class MemberDaoImpl implements MemberDao {

  DataSource dataSource;

  public MemberDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(//
            "insert into lms_member(name, email, pwd, tel, photo) values(?, ?, ?, ?, ?)")) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getTel());
      stmt.setString(5, member.getPhoto());
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select member_id, name, email, pwd, cdt, tel, photo from lms_member");

        ResultSet rs = stmt.executeQuery()) {

      List<Member> list = new ArrayList<>();
      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
        Member m = new Member();
        m.setNo(rs.getInt("member_id"));
        m.setName(rs.getString("name"));
        m.setPassword(rs.getString("pwd"));
        m.setEmail(rs.getString("email"));
        m.setRegisteredDate(rs.getDate("cdt"));
        m.setTel(rs.getString("tel"));
        m.setPhoto(rs.getString("photo"));
        list.add(m);
      }
      return list;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, pwd, cdt, tel, photo"
                + " from lms_member where member_id=?")) {

      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
          Member m = new Member();
          m.setNo(rs.getInt("member_id"));
          m.setName(rs.getString("name"));
          m.setPassword(rs.getString("pwd"));
          m.setEmail(rs.getString("email"));
          m.setRegisteredDate(rs.getDate("cdt"));
          m.setTel(rs.getString("tel"));
          m.setPhoto(rs.getString("photo"));
          return m;
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("update lms_member set "//
            + "name=?, "//
            + "email=?, "//
            + "pwd=password(?), "//
            + "tel=?, "//
            + "photo=?"//
            + "where member_id=?")) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getTel());
      stmt.setString(5, member.getPhoto());
      stmt.setInt(6, member.getNo());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from lms_member where member_id=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> search(String keyword) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, pwd, cdt, tel, photo"//
                + " from lms_member"//
                + " where name like ?"//
                + " or email like ?"//
                + " or tel like ?")) {
      String value = "%" + keyword + "%";
      stmt.setString(1, value);
      stmt.setString(2, value);
      stmt.setString(3, value);

      try (ResultSet rs = stmt.executeQuery()) {
        List<Member> list = new ArrayList<>();
        // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
        while (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
          Member m = new Member();
          m.setNo(rs.getInt("member_id"));
          m.setName(rs.getString("name"));
          m.setPassword(rs.getString("pwd"));
          m.setEmail(rs.getString("email"));
          m.setRegisteredDate(rs.getDate("cdt"));
          m.setTel(rs.getString("tel"));
          m.setPhoto(rs.getString("photo"));
          list.add(m);
        }
        return list;
      }
    }
  }// search

  @Override
  public Member findByEamilAndPassword(String email, String password) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, pwd, cdt, tel, photo"//
                + " from lms_member"//
                + " where email = ?"//
                + " and pwd = password(?)")) {
      stmt.setString(1, email);
      stmt.setString(2, password);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setPassword(rs.getString("pwd"));
          member.setEmail(rs.getString("email"));
          member.setRegisteredDate(rs.getDate("cdt"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));
          return member;
        } else {
          return null;
        }
      }
    }
  }// findByEamilAndPassword

}
