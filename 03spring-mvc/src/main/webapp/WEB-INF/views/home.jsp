<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<hr>
<h5> 프로젝트의 home(또는 index) URL 테스트</h5>
<a href="./">http://localhost:8080/app/</a><br>
<hr>
<h5>다른 URL 테스트</h5>
<ul>
	<li><a href="test">http://localhost:8080/app/test</a><br></li>
	<li><a href="hello">http://localhost:8080/app/hello</a><br></li>
	<li><a href="spring">http://localhost:8080/app/spring</a><br></li>
</ul>
<hr>
<h5>파라미터 전달 테스트</h5>
<ul>
	<li><a href="search?name=트와이스&age=25">파라미터는 ?name=트와이스&age=25</a></li>
	<li><a href="search2?name=트와이스&age=25">파라미터는 ?name=트와이스&age=25</a></li>
	<li><a href="search3?name=트와이스&age=25&city=서울">파라미터는 ?name=트와이스&age=25&city=서울</a></li>
	<li><a href="search4?name=트와이스&age=25&city=서울">파라미터는 ?name=트와이스&age=25&city=서울</a></li>
</ul>
<hr>
<h5>form 파라미터 전달 연습</h5>
	<p><a href="order">GO!! 주문 order</a></p>
<hr>
<h5>애트리뷰트 전달 테스트</h5>
<ul>
	<li><a href="model">핸들러 메소드에서 Model 객체에 데이터 저장 -> view로 전달</a></li>
	<li><a href="modelAttr?name=트와이스&age=25">modelAttr?name=트와이스&age=25 의 파라미터를 컨트롤러에서 view로 전달</a></li>
	<li><a href="modelAttr2?name=트와이스&age=25&city=서울">modelAttr2?name=트와이스&age=25&city=서울 의 파라미터를 컨트롤러에서 view로 전달</a></li>
	<li><a href="modelAttr3?name=트와이스&age=25&city=서울">modelAttr3?name=트와이스&age=25&city=서울 의 파라미터를 컨트롤러에서 view로 전달</a></li>
	<li><a href="order2">order 애트리뷰트 문제</a></li>
</ul>
<hr>
<h5>리다이렉트 url 로 파라티터 전달</h5>
<ul>
	<li><a href="redirectParam">[테스트]redirect:search?name=트와이스&amp;age=25</a></li>
	<li><a href="redirectAttr">핸들러 메소드에서 Model 객체에 데이터 저장 -> 리다이렉트 요청url의  파라미터</a></li>
</ul>
</body>
</html>
