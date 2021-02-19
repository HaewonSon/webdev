<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="styles/main.css"/> 
<head>
<meta charset="UTF-8">
<title>연락처 프로그램 - 회원 수정 </title>
</head>


<body>

<form action="PhoneBookModifyServlet" method="post">
	이름 : <input type="text" name="name" size="10" value="${member.name }"/><br/>
		<br/>
		${nameMsg }
		<br/>
		전화번호 : 
		<select name="phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="016">019</option>
		</select>
		-
		<input type="text" name="phone2" size="4" value="${fn:substring(member.phonenum,3,7)}"/>
		-
		<input type="text" name="phone3" size="4" value="${fn:substring(member.phonenum,7,11) }"/>
		<br/>
		${phoneMsg }
		<br/>
		주소 : <input type="text" name="address" size="10" value="${member.address }" />
		<br/>
		<br/>
		그룹 : <select name="groupnum">
			<option value="1">가족</option>
			<option value="2">친구</option>
			<option value="3">기타</option>
		</select>
		<br/>
		<br/>
		<input type="submit" value="회원수정"/><br/>
		<div style="color:orange">
		${msg }
		</div>
</form>

</body>
</html>
