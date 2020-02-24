package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  Connection con;

  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("insert into lms_photo(titl, lesson_id) values('"//
          + photoBoard.getTitle() + "', " + photoBoard.getLesson().getNo() + ")");
    }
  }

  @Override
  public List<PhotoBoard> findAllByLessonNo(int lessonNo) throws Exception {
    try (Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery( //
            "select photo_id, titl, cdt, vw_cnt, lesson_id from lms_photo where lesson_id="
                + lessonNo + " order by photo_id desc")) {

      List<PhotoBoard> list = new ArrayList<>();
      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
        PhotoBoard p = new PhotoBoard();
        p.setNo(rs.getInt("photo_id"));
        p.setTitle(rs.getString("titl"));
        p.setCreatedDate(rs.getDate("cdt"));
        p.setViewCount(rs.getInt("vw_cnt"));

        list.add(p);
      }
      return list;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select p.photo_id, p.titl, p.cdt, p.vw_cnt, l.lesson_id, l.titl lesson_title"
                + " from lms_photo p"
                + " inner join lms_lesson l on p.lesson_id = l.lesson_id where p.photo_id=" + no)) {

      if (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
        PhotoBoard p = new PhotoBoard();
        p.setNo(rs.getInt("photo_id"));
        p.setTitle(rs.getString("titl"));
        p.setCreatedDate(rs.getDate("cdt"));
        p.setViewCount(rs.getInt("vw_cnt"));

        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
        lesson.setTitle(rs.getString("lesson_title"));

        p.setLesson(lesson);
        return p;
      } else {
        return null;
      }
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("update lms_photo set "//
          + "titl='" + photoBoard.getTitle() + "' "//
          + "where photo_id=" + photoBoard.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("delete from lms_photo where photo_id=" + no);
    }
  }

}
