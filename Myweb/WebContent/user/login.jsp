<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp"%>

<section>
	<div align="center">
		<h2>MVC1 로그인연습</h2>
		<hr/>
		<form action="login_ok.jsp" method="post">
			<input type="text" name="id" placeholder="아이디"><br/><br/>
			<input type="password" name ="password" placeholder="비밀번호"><br/>
		
			<input type="submit" value="로그인" class="btn btn-default">
			<input type="button" value="회원가입" class ="btn btn-primary" onclick ="location.href='join.jsp' ">
						
		</form>
	</div>
</section>

<%@ include file = "../include/footer.jsp"%>