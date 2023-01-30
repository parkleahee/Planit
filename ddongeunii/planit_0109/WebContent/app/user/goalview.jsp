<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
<link rel="stylesheet" href="${cp}/css/modify.css">
<link href='${cp}/css/main.css' rel='stylesheet' />
<link href='${cp}/css/main_nav.css' rel='stylesheet' />
<style>
.goal_container {
	width: 60%;
	height: 1000px;
	margin-left: 200px;
}

#mypage {
	width: 75%;
	margin: 0 auto;
	margin-bottom: 30px;
}

#sub {
	margin: 0 auto;
	margin-top: 100px;
}

.mypage_menu {
	width: 100%;
	height: 100px;
	font-size: 20px;
}

#mypage {
	width: 20%;
	float: left;
}

.goalset1 {
	display: none;
}

.goalset2 {
	display: none;
}

#goal_wrap {
	padding: 100px;
}

#goal_list {
	margin: 0 auto;
	margin-top: 15px;
	padding-left: 10px;
	border-radius: 1px;
	border: 1px solid black;
	width: 95%;
	height: 150px;
}

#goal_progress {
	width: 85%;
	padding: 10px 0;
}

#goal_tit {
	padding: 10px 0;
}

#goal_pernum {
	float: right;
	padding: 3px 15px 0 0;
}

.background {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100vh;
	background-color: rgba(0, 0, 0, 0.3);
	z-index: 1000;
	/* 숨기기 */
	z-index: -1;
	opacity: 0;
}

.show {
	opacity: 1;
	z-index: 1000;
	transition: all 0.5s;
}

.window {
	position: relative;
	width: 100%;
	height: 100%;
}

.popup {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #ffffff;
	box-shadow: 0 2px 7px rgba(0, 0, 0, 0.3);
	/* 임시 지정 */
	width: 300px;
	height: 400px;
	/* 초기에 약간 아래에 배치 */
	transform: translate(-50%, -40%);
}

.show .popup {
	transform: translate(-50%, -50%);
	transition: all 0.5s;
}

#deletegoal_btn
{
	margin-top: 20px;
	width:80px;
	height:40px;
	background-color: black;
	color: white;
	border:none;
	font-weight: 300;
	letter-spacing: 10px;
	text-align: center;
}

</style>
</head>

<body>
	<header>
			<div id="main_nav">
				<div id="m_mypage" class="m_detail">
					<a id="my_mypage" href="${cp}/user/usergoal.tc">마이페이지</a>
				</div>
				<div id="m_friend" class="m_detail">
					<a id="my_friend" href="">친구관리</a>
				</div>
				<div id="m_chat" class="m_detail">
					<a id="my_chat" href="">채팅</a>
				</div>
				<div id="m_cont" class="m_detail">
					<a id="my_cont" href="${cp}/user/usercontact.tc">문의하기</a>
				</div>
				<div id="m_logout" class="m_detail">
					<a id="my_logout" href="${cp}/user/userlogout.tc">로그아웃</a>
				</div>
				
				<div class="m_goal">
					<button id="show">목표를 설정해 주세요.</button>
					<div class="background">
						<div class="window">
							<div class="popup">
								<form id="goalForm" name="goalForm" method="post"
									action="${cp}/user/addgoalokaction.tc"
									onsubmit="return add_goal1()">
									<table id="goal_tb">
										<tbody>
											<tr>
												<td><h3>목표설정</h3></td>
											</tr>
											<tr>
												<td><input name="usergoal1" id="usergoal1"></td>
											</tr>
											<tr>
												<td>기간은 당일부터 30일 입니다 !</td>
											</tr>
										</tbody>
									</table>
									<input type="submit" value="목표 설정">
									<input type="button" id="close"
										onclick="location.href='${cp}/app/schedule/mainview.jsp';">돌아가기</input>
								</form>
							</div>
						</div>
					</div>
					<c:choose>
					<c:when test="${gdto.goal != null}">
					<div class="goalset1">
						<div id="goal_tit1"><span>${gdto.goal}</span></div>
						<div>
							<progress id="progress1" value="0" max="100"></progress>
						</div>
						<input type="button" name="getgoal_btn1" id="getgoal_btn1"
							value="오늘 목표 달성">
					</div>
					<!-- <script>
					alert('목표 설정 성공 ! 목표 설정은 2개까지 가능합니다 !');
					document.getElementsByClassName("goalset1")[0].style.display = 'block';
					</script> -->
					</c:when>
					</c:choose>
					<div class="goalset2">
						<div id="goal_tit2"><span></span></div>
						<div>
							<input id="goalday_1" type="hidden">
							<progress id="progress2" value="0" max="100"></progress>
						</div>
						<input type="button" name="getgoal_btn2" id="getgoal_btn2"
							value="오늘 목표 달성">
					</div>
				</div>
			</div>

		</header>
	<div id="sub">
		<div id="mypage">
			<div class="mypage_menu" id="mypage_menu1">
				<a href="${cp}/user/usergoal.tc">지난 목표보기</a>
			</div>
			<div class="mypage_menu" id="mypage_menu2">
				<a href="${cp}/user/usermyinfo.tc">정보 수정하기</a>
			</div>
			<div class="mypage_menu" id="mypage_menu3">
				<a href="${cp}/user/userwithdraw.tc">회원 탈퇴</a>
			</div>
		</div>

		<div class="goal_container">
		<c:forEach items="${goalList}" var="goal">		
			<div class="goal_wrap">
				<div id="goal_list">
					<div id="goal_tit">
						<span id="goal_title" style="font-size: 20px;">${goal.goal}</span>
					</div>
					<div id="goal_per">
						<progress id="goal_progress" value="10" max="100"></progress>
						<div id="goal_pernum">75%</div>
					</div>
					<div id="goal_date">${goal.term}부터 30일 동안 달성한 목표입니다 !</div>
					<div id="goal_delete">
						<input type="hidden" name="goalnum" value="${goal.goalnum}"> 
						<input type="button" id="deletegoal_btn" value="삭제" onclick="location.href='${cp}/user/deletegoal.tc?goalnum=${goal.goalnum}'">
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>

</body>
<script>
	var cp = '${cp}';
</script>
<script src='${cp}/js/main_nav.js' type="text/javascript"></script>
</html>