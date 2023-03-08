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
<link rel="stylesheet" href="${cp}/resources/css/modify.css" />
<link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
<link href='${cp}/resources/css/withdraw.css' rel='stylesheet' />
</head>

<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
	<header>
			<jsp:include page="header.jsp"/>
		</header>
	<div id="sub">
		<div id="mypage">
			<div class="mypage_menu" id="mypage_menu1">
				<a href="${cp}/user/usergoal">목표보기</a>
			</div>
			<div class="mypage_menu" id="mypage_menu2">
				<a href="${cp}/user/usermyinfo">정보 수정하기</a>
			</div>
			<div class="mypage_menu" id="mypage_menu3">
				<a href="${cp}/user/userwithdraw">회원 탈퇴</a>
			</div>
		</div>
		<div class="withdraw_container" id="withdraw_top">
			<div class="withdraw_wrap">
				<div id="withdraw_list">
					<div id="withdraw_tit" style="margin-bottom: 50px;">
						<h3>회원 탈퇴</h3>
					</div>
					<div id="withdraw_info" style="margin-bottom: 50px;">
						<div id="withdraw_info_1">
						<div id="form_wrap">
							<form name="withdrawForm" method="post" action="${cp}/user/userwithdrawok" onsubmit="return wdchk()">
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
										onclick="location.href='${cp}/schedule/mainview'">
								</div>
							</form>
						</div>
						<div>
			<a id= "background_img" href="#sub" ></a>
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
<script src='${cp}/resources/js/main_nav.js' type="text/javascript"></script>
<script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
<script>
	function cntGoal_1(e){
		/* e.preventDefault();  */
		let goal="goal1";
		let goalnum=$('#getgoal_btn1').attr("class");
		//번호 추출 
		
		goalService.cntGoal(
				// 자원 전체를 전달해야 한다. (JSON)
			{"goal":goal,"goalnum":goalnum},
			function(result){
				if(result == "success"){
					alert('목표 달성 성공 !');
					(location || window.location || document.location).reload();
				}
			}
		)
	}
	
	function cntGoal_2(e){
		let goal="goal2";
		let goalnum=$('#getgoal_btn2').attr("class");
		//번호 추출 
		
		goalService.cntGoal(
				// 자원 전체를 전달해야 한다. (JSON)
			{"goal":goal,"goalnum":goalnum},
			function(result){
				if(result == "success"){
					alert('목표 달성 성공 !');
					(location || window.location || document.location).reload();
				}
			}
		)
	}
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
		
		if(wdForm.userpw.value != '${loginUser.userpw}'){
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
			document.getElementById("goalchk_1").innerHTML='오늘의 목표 달성🙂';
		}
		
		let goalchk_2="${goal2.goalcheck}";
		if(goalchk_2 == 'f'){
			document.getElementById("goalchk_2").innerHTML='오늘의 목표 달성🙂';
		}
	}
</script>
</html>