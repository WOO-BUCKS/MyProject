<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%/*
	UserVO vo = (UserVO)session.getAttribute("vo");
	String auth = (String)session.getAttribute("auth");
*/%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 세션값을 el태그로 참조하려면 sessionScope.이름  -->
	<h3>세션값을 el태그로 참조하려면 sessionScope.이름</h3>
	auth: ${sessionScope.auth}<br/>
	아이디: ${sessionScope.vo.id}<br/>
	이름: ${sessionScope.vo.name}<br/>
	<!-- sessionScope생략할 수 있지만, 생략도 가능하다. -->
	<h3>sessionScope생략할 수 있지만, 생략도 가능하다.</h3>
	auth: ${auth}<br/>
	아이디: ${vo.id}<br/>
	이름: ${vo.name}<br/>
	<!-- 어플리케이션 가져오기 -->
	<h3>어플리케이션 가져오기</h3>
	app에 admin:${applicationScope.admin}<br/>

</body>
</html>