<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
<style type="text/css">

.logo{
	width: 120px;
	height: 120px;
	float: left;
}

ul {
	list-style: none;
}

a {
	text-decoration: none;
	outline: none;
}

header {
    width: 95%;
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 0px;
}

header > h2 {
	margin-left:20px;
}

header > nav {
	width:800px;
	height:100%;
}

.ddicon{
	width: 20px;
	height: 20px;
	margin-top: 50px;
}


.container {
	width:70%;
	margin:0 auto;
	justify-content: space-between;
	align-items: center;
}

.searchButton{
	background-color: white;
	border: 1px solid white;
	bottom: 10px;
}

.search{
	margin-right: 0px;
}

.searchBox{
	height: 33px;
	width: 300px;
	border-radius: 10px;
}

.searchicon{
	width: 30px;
	height: 33px;
	float: right;
}

.searchicon:hover {
	cursor: pointer;
}

.navbar{
	padding: 20px 20px;
	margin-right: 0px;
	display: flex;
	width: 130%;
}

.upload{
	width: 40px;
	height: 35px;
}

.chat{
	width: 35px;
	height: 35px;
}

.cart{
	width: 35px;
	height: 35px;
}

.login{
	width: 35px;
	height: 35px;
}

.smenu{
	float: right;
	padding-left: 27%;
}

a > img {
	padding-left: 10px;
}

a > img:hover {
	cursor: pointer;
}

a {
    color : #222;
    text-decoration: none;
}

.dropbtn {
	background-color:white;
    font-size: 16px;
    border: none;
    cursor: pointer;
}
.dropdown {
    position: relative;
    display: inline-block;
}
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 80px;
    box-shadow: 0 15px 20px rgba(0, 0, 0, 0.2);
    z-index: 1;
    border-radius: 10px;
}
.dropdown-content a {
    color: black;
    padding: 12px 16px;
    font-size: 15px;
    color: gray;
    text-align: center;
    text-decoration: none;
    display: block;
    border-radius: 10px;
}
.dropdown-content a:hover {
    background-color: lightgray;
    color: black;
    font-size: 17px;
}
.dropdown:hover .dropdown-content {
    display: block;
}

@media screen and (max-width: 600px) {
	.container{
		flex-direction: column;
		align-items: flex-start;
	}
	
	.navbar{
		flex-direction: column;
		align-items: center;
		width: 100%;
	}
}

</style>
</head>
<body>
	<div class="container">
		<header>
		<div>
		<a href="#"><img class="logo" alt="로고" src="./images/logo.png"></a>
		<div class="dropdown">
		<button class="dropbtn"><img class="ddicon" alt="드롭다운" src="./images/down.png"></button>
			<div class="dropdown-content">
				<a href="#">피드</a>
				<a href="#">중고</a>
			</div>
		</div>
		</div>
			<nav>
		<div class="navbar">
        <form class="search" action="" method="post">
          <input style="border-radius: 5px;" name="searchVal" type="text" placeholder="    검색..." 
				class="searchBox" required>
        </form>		
				<button class="searchButton">
				<img class="searchicon" alt="검색" src="./images/searchicon.png"></button>
        <div class="smenu">
        <a><img class="upload" alt="업로드" src="./images/upload.png"></a>
        <a><img class="chat" alt="채팅" src="./images/chat.png"></a>
        <a><img class="cart" alt="카트" src="./images/cart.png"></a>
        <a><img class="login" alt="로그인" src="./images/login.png"></a>
        </div>
        </div>
			</nav>
		</header>
	<hr>
	</div>
	
</body>
</html>