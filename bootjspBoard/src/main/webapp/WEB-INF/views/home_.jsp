<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<hr/>
<ul>
	<li><a href="community/list">커뮤니티</a></li>
	<li><a href="gallery/gallery">갤러리</a></li>
<c:if test="${sessionScope.customer !=null }">  <!-- customer는 세션애트리뷰트입니다. -->
	<li>${sessionScope.customer.name }님(${sessionScope.customer.email }) 환영합니다.<br></li>
	<li><a href="member/update">나의 정보 수정</a></li>
	<li><a href="logout">로그아웃</a></li>
</c:if>
<c:if test="${customer ==null }">
	<li><a href="login">로그인</a></li>
	<li><a href="member/join?start=1">회원가입</a></li>
</c:if>
</ul>




	
<!--  애트리뷰트 저장 영역 : page , request , session , application
	  동일한 애트리뷰트가 여러 곳에 존재한다면 
	  el로 표시했을때 찾는 순서 page -> request -> session -> application
	  동일한 이름의 애트리뷰트가 없다면 sessionScope 은 생략합니다.
 -->
</body>
</html>
