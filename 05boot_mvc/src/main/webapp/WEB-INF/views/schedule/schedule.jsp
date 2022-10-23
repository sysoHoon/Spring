<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 라이브러리 사용하기 위해 코딩. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberV3 My Schedule</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mytable.css">
</head>
<body>
<!-- 로그인을 해야하는 페이지에 추가 -->
<!-- 해당 파일의 코드를 복붙하는 결과 -> 컴파일 : 변수를 공유할 수 있다. -->
<%-- <%@ include file="../common/sessionCheck.jsp" %> --%>
<!-- 각각 파일을 컴파일 한 결과를 include : 변수 공유 안됨 -->
<jsp:include page="../common/sessionCheck.jsp"/>
<script type="text/javascript">
	var alertm = '<c:out value="${alertm}"/>'
	if(alertm != "")
		setTimeout("alert(alertm)",100)
</script>
<c:if test="${member != null }">  <!-- 세션 애트리뷰트가 null 아닐때만. -->
	<form action="./save.do" method="post">
	<input type="hidden" name="mno" value="${member.mno }"> 
		<input type="text" name="title" placeholder="내용 입력하세요."> <input
			type="date" name="sdate"> <input type="time" name="stime">
		<input type="submit" value="추가"> 
		<input type="button" value="홈" 
			 onclick="location.href='${pageContext.request.contextPath}/';">
			 				<!-- 상대경로로 하면 location.href='../'; -->
	</form>
	<hr>
	<h3>나의 스케쥴</h3>
	<%-- ${list} --%>
	<!-- session.getAttribute("list")  -->
	<br>
	<!-- <table> 태그로 출력 -->
	<!-- :반복필요.반복 할때 날짜와 시간을 각각 출력하는 fmt 태그로 코딩. 내용/날짜/시간 -->
	<table>
		<tr>
			<th width="50%">내용</th>
			<th width="25%">날짜</th>
			<th width="25%">시간</th>
			
		</tr>
	<c:forEach var="sch" items="${list}">
		<tr>
			<td>${sch.title}</td>
			<td colspan="2"> ${sch.sdate } 
				<input type="button" onclick="delete_sch('${sch.idx}')" value="삭제">
			</td>
			<%-- 날짜타입일때 사용<td>
				<fmt:formatDate value="${sch.sdate }" pattern="yyyy-MM-dd"/>
			</td>
			<td>
				<fmt:formatDate value="${sch.sdate }" pattern="a hh:mm"/>
			</td> --%>
		</tr>
	</c:forEach>		

	</table>
</c:if>

<script type="text/javascript">
	function delete_sch(idx) {
		const yn=confirm('스케쥴 삭제할까요?');
		console.log(yn);
		if(yn==true)   //if(confirm('스케쥴 삭제할까요?')) 로 테스트해보고 바꾸세요.
		location.href="./delete.do?idx="+idx;
	}
</script>
</body>
</html>