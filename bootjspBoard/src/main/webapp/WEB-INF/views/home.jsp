<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberV3</title>
<style type="text/css">
div{
	width:200px;height: 400px;
	border: 1px solid gray;
	padding:50px;
	margin: 100px auto;
}
</style>
</head>
<body>
	<script>
		var alertm='<c:out value="${alertm}" />'
		if(alertm!="")
			alert(alertm);
	</script>
<div>

<p>접속 시간 : ${serverTime }</p>
<ul>
<!-- 객체가 null 인지 비교 : 같다(==)는 eq , 같지않다(!=)  ne  -->
<!-- member 애트리뷰는 로그인 성공하면 session 에 저장했다. -->
<c:choose>    
	<c:when test="${member == null}">  
	<!-- 로그인 안했을 때 메뉴 -->
		<li><a href="login">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li><a href="${pageContext.request.contextPath}/member/join">회원가입</a></li>
		<li><a href="./member/list">회원목록</a>
	</c:when>
	<c:otherwise>  
	<!-- 로그인했을 때 메뉴 -->
		<h5 style="color:orange;">${member.email}&nbsp;님 환영합니다.</h5>
		<li><a href="./member/update">내 정보 수정</a></li>
		<li><a href="logout">로그아웃</a></li>
	</c:otherwise>
</c:choose>
	<h4>고객지원</h4>
	<li><a href="community/list">커뮤니티</a></li>
	<li><a href="gallery/gallery">갤러리</a></li>
</ul>
</div>


</body>
</html>








