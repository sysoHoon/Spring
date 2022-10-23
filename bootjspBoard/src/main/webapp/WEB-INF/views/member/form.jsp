<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberV3 회원가입 </title>
<script type="text/javascript">
	function validForm(){
		var frm = document.frmUser;
		var i;
		
		//a.
		if(frm.name.value=="") {
			alert("이름은 필수 입력입니다.");
			frm.name.focus();
			return false;
		}
		
		//b.
		if(frm.pwd.value.length < 8) {
			alert("패스워드는 8글자 이상입니다.");
			frm.pwd.focus();
			return false;
		}
		
		//c.
		if(frm.age.value < 20 || frm.age.value > 70) {
			alert("나이는 20세 이상 70세 미만으로만 가능합니다.");
			frm.age.focus();
			return false;
		}
		
		//d.
		for(i=0;i<frm.hobby.length;i++) {  /* frm.hobby 는 배열. 배열의 크기만큼 반복  */
			//alert(frm.hobby.length);
			if(frm.hobby[i].checked) {  //체크선택된 취미이면 바로 submit 되도록 return true;
				//alert(frm.hobby[i].value);
				return true;
			}	
		}	
		if(i==frm.hobby.length) {
			alert("취미 선택을 안하셨군요!취미 하나 선택해주세요.!");
			//document.getElementById('hobby1').focus();
			frm.hobby[0].focus();
			return false;
		}
		
		
	}
	
	function validEmail() {
		var email = document.frmUser.email.value;
		if(email==""){
			alert("이메일(아이디)를 입력해 주세요.!");
			document.frmUser.email.focus();
			return;   /* 함수 종료 */
		}
		//이동할 url , 윈도제목, 옵션 순서
		var url="./idCheck.do?email="+email;
		//새로운 브라우저 창을 열어줍니다. 브라우저 크기 : 300x150 ,
		//브라우저의 url은 url변수로 값으로 합니다.
		window.open(url,"아이디 중복체크","width=300,height=150,left=900");
	}
	
</script>
<!-- http://localhost:8085/idev/ 에 해당하는 부분은 contextPath 프로퍼티로 가져옵니다. -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mytable.css">
</head>
<body>
<p>접속 시간 : ${serverTime }</p>
<div style="width: 70%; margin: auto;">
		<h3>회원 등록</h3>
		<!-- 다른 방법 : onsubmit 은 입력값 유효성 검증을 위한 것이다.submit 전에 실행된다. -->
		<!-- 이 파일의 현재 경로(./)는 http://localhost:8085/idev/member -->
		<form name="frmUser" method="post" action="join.do">
			<table style="width: 100%">
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" ></td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email"><!--  unique 제약조건 email 컬럼 중복값 체크 -->
					<button type="button" onclick="validEmail()">중복체크</button>
					</td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" min="10" max="99"
						value="30"></td>
				</tr>
				<tr>
					<th>거주지</th>
					<td><select name="addr">
							<!-- value 속성은 서버에 전달시킬 값 -->
							<option value="서울">서울</option>
							<option value="인천" selected>인천</option>
							<option value="대전">대전</option>
							<option value="광주">광주</option>
							<option value="부산">부산</option>
					</select></td>
				</tr>
				<tr>
					<th>성별</th> 
					<td><input type="radio" value="male" name="gender" id="male">
						<label for="male">남자</label> 
						<!-- 텍스트를 클릭해도 선택이된다. label 태그의 for는 어떤 input과 연결인지 표시
						     태그의 구별은 id속성 값으로 한다. -->
						<input type="radio" value="female" name="gender" checked id="female">
						<label for="female">여자</label>
					</td>
				</tr>
				<tr>
					<th>취미</th> <!--*** name속성은 값을 위한 변수명, id속성은 태그구별을 위한 변수명 -->
					<td>
						<!-- 복수 선택이 가능 --> 
						<input type="checkbox" name="hobby" value="축구" id="hobby1">
						<label for="hobby1">축구</label>
						<input type="checkbox" name="hobby" value="농구" id="hobby2">
						<label for="hobby2">농구</label> 
						<input type="checkbox" name="hobby" value="스키" id="hobby3">
						<label for="hobby3">스키</label>
						<input type="checkbox" name="hobby" value="달리기" id="hobby4">
						<label for="hobby4">달리기</label>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center">
					<button type="submit">가입하기</button>
					<button type="reset">다시쓰기</button> 
				</tr>

			</table>
		</form>
	</div>
</body>
</html>


















