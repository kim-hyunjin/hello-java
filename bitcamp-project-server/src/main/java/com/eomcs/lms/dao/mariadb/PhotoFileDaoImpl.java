package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  Connection con;

  public PhotoFileDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("insert into lms_photo_file(photo_id, file_path) values("//
          + photoFile.getBoardNo() + ", '" + photoFile.getFilePath() + "')");
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery( //
            "select photo_file_id, photo_id, file_path from lms_photo_file where photo_id="
                + boardNo + " order by photo_file_id asc")) {

      List<PhotoFile> list = new ArrayList<>();
      // ResultSet 도구를 사용하여 데이터를 하나씩 가져온다.
      while (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
        list.add(new PhotoFile(rs.getInt("photo_file_id"), rs.getString("file_path"),
            rs.getInt("photo_id")));
      }
      return list;
    }
  }

  @Override
  public int deleteAll(int boardNo) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("delete from lms_photo_file where photo_id=" + boardNo);
    }
  }

}
