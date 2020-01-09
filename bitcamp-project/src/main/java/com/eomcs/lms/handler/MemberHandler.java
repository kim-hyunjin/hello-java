package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Prompt;

public class MemberHandler {
  ArrayList<Member> memberList;
  public Prompt input;

  public MemberHandler(Prompt input) {
    this.input = input;
    this.memberList = new ArrayList<>(); 
  }

  public MemberHandler(Prompt input, int capacity) {
    this.input = input;
    this.memberList = new ArrayList<>(capacity); 
  }

  public void addMember() {
    Member member = new Member();
    member.setNo(input.inputInt("번호? "));
    member.setName(input.inputString("이름? "));
    member.setEmail(input.inputString("이메일? "));
    member.setPassword(input.inputString("암호? "));
    member.setPhoto(input.inputString("사진? "));
    member.setTel(input.inputString("전화번호? "));
    member.setDate(new Date(System.currentTimeMillis()));
    this.memberList.add(member);
    System.out.println("저장되었습니다.");
  }

  public void listMember() {
    Member[] arr = this.memberList.toArray(new Member[] {});
    for (Member m : arr) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getDate());
    }
  }

  public void detailMember() {
    int index = indexOfMember(input.inputInt("번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }
    Member member = this.memberList.get(index);
    System.out.printf("번호: %d\n", member.getNo());
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
  }
  
  public void updateMember() {
    int index = indexOfMember(input.inputInt("번호? "));
    Member oldMember = this.memberList.get(index);
    if (oldMember == null) {
      System.out.println("번호가 유효하지 않습니다.");
      return;
    }
    Member newMember = new Member();
    newMember.setNo(oldMember.getNo());
    System.out.println("--- 수정사항을 입력하세요 ---");
    newMember.setName(input.inputString(String.format("이름(%s)? ", oldMember.getName()),
        oldMember.getName()));
    newMember.setEmail(input.inputString(String.format("이메일(%s)? ", oldMember.getEmail()),
        oldMember.getEmail()));
    newMember.setPassword(input.inputString(String.format("암호(%s)? ", oldMember.getPassword()),
        oldMember.getPassword()));
    newMember.setPhoto(input.inputString(String.format("사진(%s)? ", oldMember.getPhoto()),
        oldMember.getPhoto()));
    newMember.setTel(input.inputString(String.format("전화번호(%s)? ", oldMember.getTel()),
        oldMember.getTel()));
    newMember.setDate(oldMember.getDate());
    this.memberList.set(index, newMember);
    if (newMember.equals(oldMember)) {
      System.out.println("회원 정보 변경을 취소했습니다.");
    } else {
      System.out.println("회원 정보를 변경했습니다.");
    }
  }

  public void deleteMember() {
    int index = indexOfMember(input.inputInt("번호? "));
    Member oldMember = this.memberList.get(index);
    if (oldMember == null) {
      System.out.println("번호가 유효하지 않습니다.");
      return;
    }
    this.memberList.remove(index);
    System.out.println("회원 정보를 삭제했습니다.");
  }

  private int indexOfMember(int no) {
    for (int i = 0; i < this.memberList.getSize(); i++) {
      if (this.memberList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}

