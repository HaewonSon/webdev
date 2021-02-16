<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header class="welcome-header">
	<h2 class="welcome-header_title">Add Member</h2>
		<p class="welcome-header_text">
		추가할 연락처의 정보를 입력해 주세요. :-) 
		</p>
	
	<form  action="PhoneBookInsertServlet" method="post" name="insertForm">
		이름 : <input type="text" name="name" size="10" value="${member.name }"/><br/>
		<br/>
		전화번호 : 
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
		주소 : <input type="text" name="address" size="10" placeholder="ex)서울시"/>
		<br/>
		<br/>
		그룹 : <select name="groupnum">
			<option value="1">가족</option>
			<option value="2">친구</option>
			<option value="3">기타</option>
		</select>
		<br/>
		<br/>
		<input type="submit" value="OK"/><br/>
		<div style="color:orange">
		${msg }
		</div>
	</form>
	</header>

</body>
</html>