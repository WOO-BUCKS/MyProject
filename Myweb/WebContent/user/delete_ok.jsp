<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	1. 사용자가 직전화면에서 입력한 pw값과 id를 기반으로 login메서드를 재활용해서
		사용자를 검증합니다.
	2. login메서드가 null을 반환하면 "현재 비밀번호를 확인하세요" 출력하고 뒤로가기
		login메서드가 값을 가진다면 delete메서드를 작성하고 삭제를 진행함녀 됩니다.
	3. 삭제 성공시에는 세션을  전부 지워버리고 index.jsp페이지로 리다이렉트
		삭제 실패시에는 경고창을 띄우고 마이페이지로 리다이렉트
	*/
	String id = (String)session.getAttribute("user_id");
	String pw = request.getParameter("pw");
	
	UserDAO dao = UserDAO.getInstance();
	UserVO vo = dao.login(id, pw);
	int result = dao.delete(id);
	
	if(vo == null){ 
%>
<script>
	alert("현재 비밀번호를 확인하세요");
	history.go(-1);
</script>
<% 	}else{
		
	 if(result == 1){

		 session.invalidate();
%>
<script>
	alert("회원 삭제 성공");
	location.href="/Myweb";
</script>
<% 		
	 }else{
%>
	<script>
		alert("삭제 실패 다시확인해주세요!");
		location.href="mypage.jsp";	
	</script>	 
<%} %>	 

		
<%}%>	
	
