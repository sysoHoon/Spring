<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX 테스트</title>
<style>
        td,th{
            border-top: 1px solid gray;
            border-bottom: 1px solid gray;
            padding: 5px 10px;
            
        }
        th {
            background-color: teal;
            color: white;
            text-align: left;
        }
        tr:hover{
            background-color: darkseagreen;
            color: white;
            cursor: pointer;
        }
       
        tr > td:nth-child(1){ width : 10% }
        tr > td:nth-child(2){ width : 15% }
        tr > td:nth-child(3){ width : 10% }
        tr > td:nth-child(4){ width : 25% }
        tr > td:nth-child(5){ width : 10% }
        tr > td:nth-child(6){ width : 10% }
        tr > td:nth-child(7){ width : 25% }

    </style>
    <link rel="stylesheet" href="resources/css/modal.css">
</head>
<body>
	<h3>AJAX - 비동기통신</h3>
    <button id="getAll">GET</button>
    <button id="getOne">GET ONE</button>
    <button id="post">POST</button>
    <button id="put">PUT</button>
    <button id="patch">PATCH</button>
    <button id="delete">DELETE</button>
    <br>
    <input type="text" id="mno" placeholder=" mno를 입력하세요.">
    <input type="text" id="name" placeholder=" name를 입력하세요.">
    <input type="text" id="password" placeholder=" password를 입력하세요."><br>
    <input type="text" id="age" placeholder=" age를 입력하세요.">
    <input type="text" id="email" placeholder=" email를 입력하세요." oninput="checkEmail()">
    <small id="validEmail"></small>
    <button onclick="checkEmail()">중복체크</button><br>
    <small>지역을 입력하세요. :</small> 
    <select name="addr" id="addr">
          <!-- value 값이 서버로 전달됩니다. -->
          <option value="서울">서울</option>
          <option value="인천">인천</option>
          <option value="대전">대전</option>
          <option value="광주">광주</option>
          <option value="부산">부산</option>
          <option value="기타">기타</option>
    </select>
    <span id="optgender">
       <input type="hidden" id="gender">
       <input type="radio"  name="gender" value="male" id="lblmale"> <label for="lblmale" class="mpt">남성</label>
       <input type="radio" name="gender" value="female" id="lblfemale"><label for="lblfemale" class="mpt">여성</label>
       <input type="radio" name="gender" value="unknown" id="lblno"> <label for="lblno" class="mpt">비공개</label>
    </span>
    <div id='chkhobby'>
    <input type="text" id="hobby" placeholder=" 취미를 입력하세요.(,로 구분)">
        <input type="checkbox" class="hobby" value="축구" id="football"><label for="football">축구</label>
        <input type="checkbox" class="hobby" value="달리기" id="running"><label for="running">달리기</label>
        <input type="checkbox" class="hobby" value="농구" id="basketball"><label for="basketball">농구</label>
        <input type="checkbox" class="hobby" value="스키" id="ski"><label for="ski">스키</label>
        <input type="checkbox" class="hobby" value="수영" id="swim"><label for="swim">수영</label>
    </div>
    <button onclick="document.getElementById('myModal').style.display='block';">패스워드 수정</button>
    <hr>
   	<table id="list" style="width:80%; border-collapse:collapsel">
   		<tr>
   			<th>번호</th>
   			<th>이름</th>
   			<th>나이</th>
   			<th>이메일</th>
   			<th>성별</th>
   			<th>지역</th>
   			<th>취미</th>
   		</tr>
   		<tr>
   			<td colspan="7" id="test">테스트 용...</td>
   		</tr>
	<tbody id="tbody">
   		</tbody>
   	</table>
<%@ include file="../common/modal.jsp" %>
<script type="text/javascript">
function changePassw(){
	const email = document.querySelector('#email').value
	const oldpass = document.querySelector("input[name='oldpass']").value
	const newpass = document.querySelector("input[name='newpass']").value
	if(email=="") {		//패스워드를 변경할 이메일
		alert('이메일 입력하세요..')
		return
	}
	//패스워드 유효성검사 validPassw() 함수 결과가 false이면 아래 통신 안함.
	if(validPassw()==false){
		alert('패스워드 검증 실패!!')
		return
	}
	
	const xhr = new XMLHttpRequest()
	xhr.open('PUT','member/changepw')		//메소드,url 
	xhr.setRequestHeader('content-type','application/json;charset=utf-8')	//보낼데이터형식;인코딩
	//자바스크립트 객체
	var jsob = {"oldpass":oldpass,"email":email,"newpass":newpass}
	xhr.send(JSON.stringify(jsob))		
	xhr.onload= function(){	//201:created
		if(xhr.status === 200) {
			const data = JSON.parse(xhr.response)
			if(data.result==1){
				document.getElementById('myModal').style.display='none';
				setTimeout("alert('패스워드 변경되었습니다.')",200)
				isemail = false;
			}
			else{
				document.getElementById("oldpass").innerHTML="현재 비밀번호가 불일치 입니다.";
				document.getElementById("oldpass").style.color='red'
			}
				
		}else{
			console.error('error',xhr.status,xhr.statusText)
		}  
	}
}	

document.querySelector('#optgender').addEventListener('click', function () {
    let gender;
	document.querySelectorAll('input[name="gender"]').forEach(item => {
        if (item.checked) gender = item.value;
    });
    document.querySelector('#gender').value = gender;
});

//체크 박스 클릭하면 체크 된것 문자열 연결
document.querySelector('#chkhobby').addEventListener('click', function () {
    let hobbies = "";
    document.querySelectorAll('.hobby').forEach(item => {
        if (item.checked) hobbies = hobbies.concat(item.value, ",");
    });
    document.querySelector('#hobby').value = hobbies.substr(0, hobbies.length - 1);
});



// 모든 회원 가져오기
document.querySelector('#getAll').addEventListener('click',function(){
	const xhr = new XMLHttpRequest()
	xhr.open('GET','member/list')
	xhr.send()
	xhr.onload = function(){
		if(xhr.status==200){
			const data = JSON.parse(xhr.response)
			document.querySelector('#test').innerHTML = xhr.response
			const list = data.members
			document.querySelector('table > tbody:nth-child(2)').innerHTML=""
			list.forEach(function(ele){    //배열에서 하나 가져온 member
                const $tr = document.createElement("tr");	// tr 요소 생성, tr 태그안의 td를 문자열로
                const $temp=`<td>\${ele.mno}</td>			
                    <td>\${ele.name}</td>
                    <td>\${ele.age}</td>
                    <td>\${ele.email}</td>
                    <td>\${ele.gender}</td>
                    <td>\${ele.addr}</td>
                    <td>\${ele.hobby}</td>
                `;
                $tr.innerHTML = $temp
                document.querySelector('table > tbody:nth-child(2)').appendChild($tr)
			})
		}else{
			console.error('error', xhr.status, xhr.statusText)
		}
	}
});

// 멤버 삭제
document.querySelector('#delete').addEventListener('click',function(){
	const mno = document.querySelector('#mno').value
	if(mno==""){
		alert('삭제할 mno를 입력하세요.')
		return ;	// 종료
	}
	const xhr = new XMLHttpRequest();
//	xhr.open('DELETE','member/ajaxex?mno=' + mno)
	xhr.open('DELETE','member/ajaxex/' + mno)
	xhr.send()
	xhr.onload = function(){
		if(xhr.status == 200){
			// xhr.response는 문자열 이므로 -> object로 변환 필요
			const data = JSON.parse(xhr.response)
//			console.log(xhr.response.result)
//			console.log(xhr.response.message)
			console.log(data.result)
			console.log(data.message)
			if(data.result==1)
				alert("삭제 완료 했습니다.")
			else{
				alert("삭제할 데이터가 없습니다.")
			}
		}else{
			console.error('error', xhr.status, xhr.statusText)
		}
	}
});

let isemail = false		// 회원등록에서 유니크(유일성) 확인을 위한 변수
function checkEmail(){
	const email = document.querySelector('#email').value
	const regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	if(email=="" || regEmail.test(email)===false){
		document.querySelector('#validEmail').innerHTML = '이메일 형식이 아닙니다.'
		document.querySelector('#validEmail').style.color = 'red'
		document.querySelector('#email').focus()
		return ;
	}
	// 이메일 중복 검사
	const xhr = new XMLHttpRequest();
	xhr.open('GET','member/ajaxex?email=' + email)
	xhr.send()
	xhr.onload = function(){
		if(xhr.status == 200){
			const data = JSON.parse(xhr.response)
			console.log(data.result)
			if(data.result==1){
//				alert("중복된 이메일 입니다.")
				document.querySelector('#validEmail').innerHTML = '중복된 이메일 입니다.'
				document.querySelector('#validEmail').style.color = 'red'
				isemail = false;
			}else{
//				alert("사용 가능한 이메일입니다.")
				isemail = true;
				document.querySelector('#validEmail').innerHTML = '사용할 수 이메일 입니다.'
				document.querySelector('#validEmail').style.color = 'green'
			}
		}else{
			console.error('error', xhr.status, xhr.statusText)
		}
	}
}


document.querySelector('#post').addEventListener('click',function(){
	const name = document.querySelector('#name').value
	const password = document.querySelector('#password').value
	const age = document.querySelector('#age').value
	const email = document.querySelector('#email').value
	const addr = document.querySelector('#addr').value
	const gender = document.querySelector('#gender').value
	const hobby = document.querySelector('#hobby').value
	console.log(isemail)
	if(isemail==false){
		alert('이메일 중복 검사 실패')
		return;
	}
	const xhr = new XMLHttpRequest()
	xhr.open('POST','member/ajaxex')		// 메소드, url (POST 메소드는 데이터 insert)
	xhr.setRequestHeader('content-type','application/json;charset=utf-8')	// 보낼 데이터 형식; 인코딩
	// 자바스크립트 객체
	var jsob = {"name":name,"age":age,"password":password,"email":email,"addr":addr,"gender":gender,"hobby":hobby}
	xhr.send(JSON.stringify(jsob))	// 통신 요청 & 데이터 전송(데이터를 보낼 때는 문자열로 변환)
	xhr.onload = function(){	// 201 : created
		if(xhr.status === 200){
			console.log(xhr.response)	// 비동기 통신 응답 xhr.response
		}else{
			console.error('error', xhr.status, xhr.statusText)
			// status(상태코드), statusText, response(응답)는 xhr 객체의 프로퍼티
		}
	}
});

</script>
</body>
</html>