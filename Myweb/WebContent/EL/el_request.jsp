<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   UserVO vo= new UserVO();
   vo.setId("zzz123");
   vo.setName("드래곤볼");
   
   request.setAttribute("vo", vo);
   
   request.getRequestDispatcher("el_request_ok.jsp").forward(request, response);
   
%>
