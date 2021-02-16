<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css" />
<meta charset="UTF-8">
<title> 회원가입 </title>
</head>
<body>
	<header class="welcome-header">
	<h2 class="welcome-header_title">Join Us</h2>
		<p class="welcome-header_text">
		정보를 입력해 주세요. :-) 
		</p>
	
	<form  action="JoinServlet" method="post" name="joinform">
		이름 : <input type="text" name="name" size="10" value="${member.name }"/><br/>
		<br/>
		아이디 : <input type="text" name="id" size="10" /><br/>
		<span style="color:orange">${msg }</span><br/>
		<br/>
		비밀번호 : <input type="password" name="password" size="10"/><br/>
		<span style="color:teal">${msg }</span><br/>
		<br/>
		전화번호 : ddddddd!
		<select name="phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="016">019</option>
		</select>
		-
		<input type="text" name="phone2" size="4" value="${member.phone2 }"/>
		-
		<input type="text" name="phone3" size="4" value="${member.phone3 }"/>
		<br/>
		<br/>
		주소 : <input type="text" name="address" size="10"/>
		<br/>
		<br/>
		<input type="submit" value="회원가입"/><br/>
		<div style="color:orange">
		${msg }
		</div>
	</form>
	</header>
</body>
</html>