<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/styles.css">
<meta charset="UTF-8">
<title>Insert Page</title>
</head>
<body>
	<header class="welcome-header">
	<h2 class="welcome-header_title">Add Member</h2>
		<p class="welcome-header_text">
		추가할 회원의 정보를 입력해 주세요. :-) 
		</p>
	
	</header>
	<form id="login-form" action="PhonebookServlet" method="post">
		이름 <input type="text" name="names" size="10"/><br>
		전화번호 <input type="text" name="phonenum" placeholder="010-xxxx-xxxx" size="15"/><br>
		주소 <input type="text" name="address" size="30"/><br>
		그룹 선택 <select name="category_id">
			<option value="01">가족</option>
			<option value="02">친구</option>
			<option value="03">기타</option>
		</select>
			<br>
		<input type="submit" value="OK!"/>
	</form>
</body>
</html>