<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MemberModifyServlet" method="post">
	이름 : <input type="text" name="name" size="10" value="${member.name }"/><br/>
		<br/>
		${nameMsg }
		Id : <input type="text" name="id" size="10" readonly="readonly" value="${id }"/><br/>
		<br/>
		<br/>
		Password : <input type="password" name="password" size="13" placeholder="input password" />
		${pwMsg }<br/>
		<br/> 
		전화번호 : 
		<select name="phone1">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="016">019</option>
		</select>
		-
		<input type="text" name="phone2" size="4" value="${fn:substring(member.phonenum,3,7)   }"/>
		-
		<input type="text" name="phone3" size="4" value="${fn:substring(member.phonenum,7,11) }"/>
		<br/>
		${phoneMsg }
		<br/>
		주소 : <input type="text" name="address" size="10" value="${member.address }" />
		<br/>
		<br/>
		<br/>
		<br/>
		<input type="submit" value="내 정보 수정"/><br/>
		<div style="color:orange">
		${msg }
		</div>
</form>
</body>
</html>