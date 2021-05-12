<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("addrss");
	
	
	
	//1. 회원아이디 중복검사
	UserDAO dao = UserDAO.getInstance();
	int result = dao.checkId(id);
	
	if(result == 0){ //중복
		//중복이기 때문에 가입페이지로 넘긴다
%>
	<script>
		alert("아이디가 중복되었습니다.");
		history.go(-1); //뒤로가기
	</script>
<% }else{
		//2. 회원가입 
		UserVO vo= new UserVO(id,pw,name,email,address,null);
		int result2 =  dao.join(vo);
	if(result2 == 1){ 
%>
	<script>
		alert("회원가입을 축하합니다!."); //이벤트 알림
		location.href = "login.jsp"; //자바 스크립트의 페이지 이동
	
	</script>	

<%	}else{ 

%>
	<script >
		alert("네트워크 오류입니다.");
		location.href="join.jsp";
	</script>

<%		
	}
}

%>