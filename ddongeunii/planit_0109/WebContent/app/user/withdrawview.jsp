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
<link rel="stylesheet" href="${cp}/css/modify.css" />
<link href='${cp}/css/main.css' rel='stylesheet' />
<link href='${cp}/css/main_nav.css' rel='stylesheet' />
<style>
.withdraw_container {
	width: 80%;
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

#goal_wrap {
	padding: 100px;
}

#withdraw_list {
	margin: 0 auto;
	padding-left: 10px;
	width: 95%;
	height: 300px;
}

#withdraw_title {
	width: 100px;
	text-align: center;
	margin-bottom: 30px;
}

#goal_pernum {
	float: right;
	padding: 3px 15px 0 0;
}

#goal_date {
	text-align: center;
}

h3 {
	font-weight: 400;
	font-size: 28px;
	line-height: 40px;
	text-align: center;
}

p {
	font-size: 18px;
	line-height: 40px;
	color: #303033;
	text-align: center;
}

#withdraw_btn1 {
	margin-right: 8px;
	background: #fff;
	color: #000;
	border: 1px solid #d4d4d4;
	width: 246px;
	height: 50px;
	box-sizing: border-box;
	line-height: 50px;
	font-weight: 300;
	font-size: 20px;
	text-align: center;
}

#withdraw_btn2 {
	text-align: center;
	width: 246px;
	height: 50px;
	box-sizing: border-box;
	line-height: 50px;
	font-weight: 300;
	font-size: 20px;
	color: #fff;
	background: #000;
	outline: none;
}

a {
	text-decoration: none;
}

#withdraw_btn {
	display: flex;
	justify-content: center;
}

.goalset1 {
	display: none;
}

.goalset2 {
	display: none;
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

.input_box{
    width:360px;
    margin: 0 auto;
    }
    
    #tit_pw {
    	margin-top: 20px;
    }
   #wd_info{
   text-align:left;
   	width: 360px;
    margin:0 auto;
    padding:20px 0; 
    
   }
   
   #wd_info2{
   	color:red;
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
					<c:if test="${goal1 != null and goal2 != null}">
						<script>
							document.getElementById('show').style.display = 'none';
						</script>
					</c:if>
					<c:if test="${goal1 != null}">
						<div class="goalset1">
							<div id="goal_tit1">
								<span>${goal1.goal}</span> <span>${goal1.goalnum}</span>
							</div>
							<div>
								<progress id="progress1" value="${100/30*goal1.goalcnt}"
									max="100"></progress>
									<br> <br>
								<span id="goalchk_1"></span>
							</div>
							<%-- <c:choose> --%>
								<c:if test="${goal1.goalcheck == 't'}">
									<input type="button" name="getgoal_btn1" id="getgoal_btn1"
										value="오늘 목표 달성" onclick="location.href='${cp}/schedule/cntgoalview.tc?goal=goal1&goalnum=${goal1.goalnum}'">
								</c:if>
								
								<%-- <c:otherwise>
								</c:otherwise>
							</c:choose> --%>
						</div>
						<script>
							/* alert('목표 설정 성공 ! 목표 설정은 2개까지 가능합니다 !'); */
							document.getElementsByClassName("goalset1")[0].style.display = 'block';
						</script>
					</c:if>
					<c:if test="${goal2 != null}">
						<div class="goalset2">
							<div id="goal_tit2">
								<span>${goal2.goal}</span> <span>${goal2.goalnum}</span>
							</div>
							<div>
								<progress id="progress2" value="${100/30*goal2.goalcnt}" max="100"></progress>
								<br> <br>
								<span id="goalchk_2"></span>
							</div>
								<c:if test="${goal2.goalcheck == 't'}">
									<input type="button" name="getgoal_btn2" id="getgoal_btn2"
										value="오늘 목표 달성"
										onclick="location.href='${cp}/schedule/cntgoalview.tc?goal=goal2&goalnum=${goal2.goalnum}'">
								</c:if>
								
						</div>
						<script>
							/* alert('목표 설정 성공 ! 목표 설정은 2개까지 가능합니다 !'); */
							document.getElementsByClassName("goalset2")[0].style.display = 'block';
						</script>
					</c:if>
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
		<div class="withdraw_container">
			<div class="withdraw_wrap">
				<div id="withdraw_list">
					<div id="withdraw_tit" style="margin-bottom: 50px;">
						<h3>${LoginUser.username}님 정말 탈퇴하시나요...진짜..?</h3>
					</div>
					<div id="withdraw_info" style="margin-bottom: 50px;">
						<div id="withdraw_info_1">
						<div id="form_wrap">
							<form name="withdrawForm" method="post" action="${cp}/user/userwithdrawok.tc" onsubmit="return wdchk()">
								<div class="input_box">
									<div class="tit">아이디</div>
									<div class="input">
										<input class="input_css" type="text" id="userid"
											name="userid" value="">
									</div>
								</div>
								<div class="input_box">
									<div class="tit" id="tit_pw">비밀번호</div>
									<div class="input">
										<input class="input_css" type="password" id="userpw"
											name="userpw" value="">
									</div>
								</div>
								<div id="wd_info">
 								<span id="wd_info1">탈퇴를 원하시면 아이디와 비밀번호를 한 번 더 입력해 주세요.</span> 
 								<br>
								<span id="wd_info2">기록과 개인정보는 탈퇴와 동시에 삭제됩니다.</span>
								</div>
								<div id="withdraw_btn">
									<input type="submit" id="withdraw_btn1" value="탈퇴 하기">
									<input type="button" id="withdraw_btn2" value="계속 이용하기"
										onclick="location.href=''">
								</div>
							</form>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var cp = '${cp}';
</script>
<script src='${cp}/js/main_nav.js' type="text/javascript"></script>
<script>
	function wdchk(){
		const wdForm = document.withdrawForm;
		
		if(wdForm.userid.value == ""){
			alert('아이디를 입력하세요!');
			userid.focus();
			return false;
		}
		
		if(wdForm.userpw.value == ""){
			alert('비밀번호를 입력하세요!');
			userpw.focus();
			return false;
		}
		
			if(wdForm.userid.value != '${loginUser.userid}'){
			alert('아이디가 일치하지 않습니다 !');
			userid.focus();
			userpw.value="";
			return false;
		}
		
		if(wdForm.userpw.value != '${loginUser.userid}'){
			alert('비밀번호가 일치하지 않습니다 !');
			userpw.focus();
			return false;
		} 
		alert('탈퇴가 정상적으로 처리되었습니다. 다음에 또 이용해 주세요 !');
		return true;
	}
	
	window.onload = function(){
		let goalchk_1="${goal1.goalcheck}";
		if(goalchk_1 == 'f'){
			document.getElementById("goalchk_1").innerHTML='오늘의 목표 달성🙂!';
		}
		
		let goalchk_2="${goal2.goalcheck}";
		if(goalchk_2 == 'f'){
			document.getElementById("goalchk_2").innerHTML='오늘의 목표 달성🙂!';
		}
	}
</script>
</html>