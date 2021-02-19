<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 사용 위한 임포트 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="styles/main.css"/> 
<head>
<meta charset="UTF-8">
<title>My Member List</title>
</head>

<header>
<h5>${name }님 안녕하세요 :-)</h5>
</header>
<body>
<p><a href="LogoutServlet" >Log out</a> / <a href="MemberModifyServlet">My page</a></p>
<div>
<h1>Member List</h1>

<form action="SearchServlet" method="post"  >
<select name="category">
	<option value="name">이름</option>
	<option value="phonenum">전화번호</option>
	<option value="group_name">그룹</option>
</select>
<input type="text" name="search" size="10"/>
<input type="submit" value="검색"/> 
</form>

<br/>
<br/>
<br/>

<!-- 연락처 목록  -->
<table class="table">
	<tr>
		<th>이름</th><th>연락처</th><th>주소</th><th>그룹</th><th>수정</th><th>삭제</th>
	</tr>
	
	<!-- 여러 행을 반복해서 출력  -->
	<c:forEach items="${members }" var="member">
	<!-- ArrayList에서 request를 꺼내는 items. member 변수에 주겠다  -->
		<tr>
			<!--   -->
			<td>${member.name }</td>
			<td>${fn:substring(member.phonenum,0,3) }-${fn:substring(member.phonenum,3,7) }-${fn:substring(member.phonenum,7,11)  }</td>
			<td>${member.address}</td>
			<td>${member.groupname }</td>
			<!-- 각 행의 멤버 수정, 삭제하기 -->
			<td><button type="button" onclick="location.href='PhoneBookModifyServlet?membernum=${member.membernum }'">수정</button></td>
			<td><button type="button" onclick="location.href='PhoneBookDeleteServlet?membernum=${member.membernum }'">삭제</button></td>
		</tr>
	</c:forEach>
</table>	
<br>

<button type="submit" onclick="location.href='PhoneBookInsertServlet'">연락처 추가</button>
<button type="submit" onclick="location.href='MainServlet'">전체 목록</button>

</div>

</body>
</html>