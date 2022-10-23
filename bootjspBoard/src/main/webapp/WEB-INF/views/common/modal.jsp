<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- modal : alert,confirm 함수 사용하지 않고 추가적인 정보를 받을 때 사용자가 만드는 입력 상자...-->
	<div id="myModal" class="modal">
		<!-- Modal content : 모달 입력창-->
		<div class="modal-content">
			<span class="close" id="close">&times;</span><br>   <!-- 특수기호 코드 &times;  는  x 기호  -->
			<div style="padding: 0px 20px;">
				<b>비밀번호 변경</b><br>
				<br>
				<form action="${contextPath}/member/passw.do" method="post" name="frmPass">
					<input type="hidden" name="email" value="${member.email}">  <!-- session 애트리뷰트에 저장된 이메일 파라미터로 전달 -->
					<div>
					<input type="password" name="oldpass" placeholder="현재 비밀번호">
					<small id="oldpass"></small>  <!-- 유효성 검사 오류시 출력할 내용 표시 -->
					</div>
					<div>
					<input type="password" name="newpass" placeholder="새로운 비밀번호">
					<small id="newpass">비밀번호는 8글자 이상입니다.</small>  <!-- 유효성 검사 오류시 출력할 내용 표시 -->
					</div>
					
					<div>
					<input type="password" name="newpass2" placeholder="새로운 비밀번호 확인">
					<small id="newpass2">비밀번호 확인 입력입니다.</small>  <!-- 유효성 검사 오류시 출력할 내용 표시 -->
					</div>
					<div>
					<input type="button" value="패스워드 변경" onclick="validPassw()"><input type="button" value="취소" onclick="history.back();">
					</div>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
//모달 입력 상자 닫기
//let close = document.getElementsByClassName('close')[0];	//getElementsXXX 이면 배열 리턴. 그 요소중에 0번 인덱스
const vclose = document.getElementById('close');
vclose.onclick = function(){
	document.getElementById('myModal').style.display='none';
}
function modalSet() {
	document.getElementById('myModal').style.display='block';
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/validPassword.js"></script>
	<!-- 모달 끝 -->