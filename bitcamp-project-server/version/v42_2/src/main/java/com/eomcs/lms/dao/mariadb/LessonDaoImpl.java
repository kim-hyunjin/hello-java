package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.sql.DataSource;

public class LessonDaoImpl implements LessonDao {

  DataSource dataSource;

  public LessonDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("insert into lms_lesson(titl, conts, sdt, edt, tot_hr, day_hr)"
                + " values(?, ?, ?, ?, ?, ?)")) {
      stmt.setString(1, lesson.getTitle());
      stmt.setString(2, lesson.getDescription());
      stmt.setDate(3, lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHours());
      stmt.setInt(6, lesson.getDayHours());

      return stmt.executeUpdate();
    }

  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select lesson_id, titl, conts, sdt, edt, tot_hr, day_hr from lms_lesson");
        ResultSet rs = stmt.executeQuery()) {

      List<Lesson> list = new ArrayList<>();
      while (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
        Lesson le = new Lesson();
        le.setNo(rs.getInt("lesson_id"));
        le.setTitle(rs.getString("titl"));
        le.setDescription(rs.getString("conts"));
        le.setStartDate(rs.getDate("sdt"));
        le.setEndDate(rs.getDate("edt"));
        le.setTotalHours(rs.getInt("tot_hr"));
        le.setDayHours(rs.getInt("day_hr"));
        list.add(le);
      }
      return list;
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select lesson_id, titl, conts, sdt, edt, tot_hr, day_hr from lms_lesson"
                + " where lesson_id=?");) {
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
          Lesson le = new Lesson();
          le.setNo(rs.getInt("lesson_id"));
          le.setTitle(rs.getString("titl"));
          le.setDescription(rs.getString("conts"));
          le.setStartDate(rs.getDate("sdt"));
          le.setEndDate(rs.getDate("edt"));
          le.setTotalHours(rs.getInt("tot_hr"));
          le.setDayHours(rs.getInt("day_hr"));
          return le;
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("update lms_lesson set "//
            + "titl=?, "//
            + "conts=?, "//
            + "sdt=?, "//
            + "edt=?, "//
            + "tot_hr=?, "//
            + "day_hr=? "//
            + "where lesson_id=?")) {
      stmt.setString(1, lesson.getTitle());
      stmt.setString(2, lesson.getDescription());
      stmt.setDate(3, lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHours());
      stmt.setInt(6, lesson.getDayHours());
      stmt.setInt(7, lesson.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from lms_lesson where lesson_id=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

}
