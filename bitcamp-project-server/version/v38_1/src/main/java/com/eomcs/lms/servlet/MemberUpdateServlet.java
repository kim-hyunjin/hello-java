package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!\n");
    int no = Integer.parseInt(in.nextLine());
    Member old = memberDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 회원이 없습니다.");
      return;
    }
    Member member = new Member();
    member.setNo(no);
    out.println("변경을 원하지 않을 경우 엔터");

    out.printf("이름(%s)? \n!{}!\n", old.getName());
    String name = in.nextLine();
    if (name.length() == 0) {
      member.setName(old.getName());
    } else {
      member.setName(name);
    }

    out.printf("이메일(%s)? \n!{}!\n", old.getEmail());
    String email = in.nextLine();
    if (email.length() == 0) {
      member.setEmail(old.getEmail());
    } else {
      member.setEmail(email);
    }

    out.printf("비밀번호(%s)? \n!{}!\n", old.getPassword());
    String password = in.nextLine();
    if (password.length() == 0) {
      member.setPassword(old.getPassword());
    } else {
      member.setPassword(password);
    }

    out.printf("전화번호(%s)? \n!{}!\n", old.getTel());
    String tel = in.nextLine();
    if (tel.length() == 0) {
      member.setTel(old.getTel());
    } else {
      member.setTel(tel);
    }

    out.printf("사진(%s)? \n!{}!\n", old.getPhoto());
    String photo = in.nextLine();
    if (photo.length() == 0) {
      member.setPhoto(old.getPhoto());
    } else {
      member.setPhoto(photo);
    }


    if (memberDao.update(member) > 0) {
      out.println("회원정보를 수정했습니다.");
    } else {
      out.println("회원정보 수정을 실패했습니다.");
    }
  }
}
