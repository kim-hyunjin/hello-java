package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

  String jdbcUrl;
  String username;
  String password;

  // 스레드에 값을 보관하는 일을 할 도구 준비
  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();


  public ConnectionFactory(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {
    Connection con = connectionLocal.get();
    if (con != null) { // 보관된 connection객체가 있다면 그 객체를 리턴한다.
      System.out.println("보관된 커넥션 리턴");
      return con;
    }
    // 보관된 connection 객체가 없다면 새로 만들어 보관 후 리턴한다.
    con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, username, password));
    connectionLocal.set(con);
    System.out.println("새 커넥션 리턴");
    return con;
  }

  public Connection removeConnection() {
    // 스레드에 보관된 Connection 객체를 제거한다.
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
      System.out.println("보관된 커넥션 제거");
    }
    return con;
  }
}
