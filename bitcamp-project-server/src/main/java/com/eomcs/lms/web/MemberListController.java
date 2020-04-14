package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.service.MemberService;
import com.eomcs.util.RequestMapping;

@Component
public class MemberListController  {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/list")
  public String list(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.setAttribute("list", memberService.list());
    return "/member/list.jsp";

  }
}
