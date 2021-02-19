<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="styles/main.css"/> 
<head>
<meta charset="UTF-8">
<title> 회원가입 </title>
</head>

<header>
	<h2>Join Us</h2>
		<p>
		정보를 입력해 주세요. :-) 
		</p>
</header>
<body>
<form  action="JoinServlet" method="post" name="joinform">
	* 이름 : <input type="text" name="name" size="10" value="${member.name }"/><br/>
		${nameMsg }
		<br/>
	* 아이디 : <input type="text" name="id" size="10" value="${member.id }" /><br/>
		${idMsg }
		<br/>
	* 비밀번호 : <input type="password" name="password" size="10"/><br/>
		${pwMsg }
		<br/>
	* 전화번호 : 
		<select name="phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="016">019</option>
		</select>
		-
		<input type="text" name="phone2" size="4" value="${fn:substring(member.phonenum,3,7) }"/>
		-
		<input type="text" name="phone3" size="4" value="${fn:substring(member.phonenum,7,11)  }"/>
		<br/>
		${phoneMsg }
		<br/>
		주소 : <input type="text" name="address" size="10" value="${member.address }"/>
		<br/>
		<br/>
		<button type="submit" value="회원가입!">회원가입!</button>
</form>
</body>
</html>