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
<link rel="stylesheet" href="${cp}/resources/css/modify.css">
<link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
</head>

<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
	<header>
		<jsp:include page="header.jsp" />
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

		<div class="goal_container">
			<c:if test="${goalList.size() == 0}">
				<div id="goalzero_img"></div>
				<div id="goalzero_txt">
					<span id="goalzero_txt1">⭐️달성한 목표가 없습니다 ! 목표를 달성하거나 새로운 목표를
						설정해 주세요⭐️ </span>
				</div>
			</c:if>
			<div class="goal_wrap">
				<c:forEach items="${goalList}" var="goal">
					<c:choose>
						<c:when test="${goal.goalnum == goal1.goalnum}">
							<div id="goal_list">
								<div id="goal_tit">
									<span id="goal_title" style="font-size: 20px;">${goal.goal}</span>
								</div>
								<div id="goal_per">
									<progress id="goal_progress" value="${100/30*goal.goalcnt}"
										max="100"></progress>
									<div id="goal_pernum">${goal.goalcnt}/30</div>
									<div id="goal_date">${goal.term}부터 실천중인 목표입니다 ^___^</div>
								</div>
							</div>
						</c:when>
						<c:when test="${goal.goalnum == goal2.goalnum}">
							<div id="goal_list">
								<div id="goal_tit">
									<span id="goal_title" style="font-size: 20px;">${goal.goal}</span>
								</div>
								<div id="goal_per">
									<progress id="goal_progress" value="${100/30*goal.goalcnt}"
										max="100"></progress>
									<div id="goal_pernum">${goal.goalcnt}/30</div>
								</div>
								<div id="goal_date">${goal.term}부터 실천중인 목표입니다 ^___^</div>
							</div>
						</c:when>
						<c:otherwise>
							<div id="goal_list">
								<div id="goal_tit">
									<span id="goal_title" style="font-size: 20px;">${goal.goal}</span>
								</div>
								<div id="goal_delete">
									<form name="deletegoalForm" action="${cp}/user/deletegoal"
										method="post">
										<input type="hidden" name="_method" value="delete" /> <input
											type="hidden" name="goalnum" value="${goal.goalnum}" /> <input
											type="submit" id="deletegoal_btn" value="X">
									</form>
								</div>
								<div id="goal_per">
									<progress id="goal_progress" value="${100/30*goal.goalcnt}"
										max="100"></progress>
									<div id="goal_pernum">${goal.goalcnt}/30</div>
								</div>
								<div id="goal_date">${goal.term}부터30일동안달성한 목표입니다 !</div>
							</div>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</div>
		</div>
		<div>
			<a id="background_img" href="#sub"></a>
		</div>
	</div>

</body>
<script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
<script>
	var cp = '${cp}';
	/* 목표 달성부분 ajax  */
	function cntGoal_1(e) {
		/* e.preventDefault();  */
		let goal = "goal1";
		let goalnum = $('#getgoal_btn1').attr("class");
		//번호 추출 

		goalService.cntGoal(
		// 자원 전체를 전달해야 한다. (JSON)
		{
			"goal" : goal,
			"goalnum" : goalnum
		}, function(result) {
			if (result == "success") {
				alert('목표 달성 성공 !');
				(location || window.location || document.location).reload();
			}
		})
	}

	function cntGoal_2(e) {
		let goal = "goal2";
		let goalnum = $('#getgoal_btn2').attr("class");
		//번호 추출 

		goalService.cntGoal(
		// 자원 전체를 전달해야 한다. (JSON)
		{
			"goal" : goal,
			"goalnum" : goalnum
		}, function(result) {
			if (result == "success") {
				alert('목표 달성 성공 !');
				(location || window.location || document.location).reload();
			}
		})
	}

	window.onload = function() {
		let goalchk_1 = "${goal1.goalcheck}";
		if (goalchk_1 == 'f') {
			document.getElementById("goalchk_1").innerHTML = '오늘의 목표 달성🙂';
		}

		let goalchk_2 = "${goal2.goalcheck}";
		if (goalchk_2 == 'f') {
			document.getElementById("goalchk_2").innerHTML = '오늘의 목표 달성🙂';
		}
	}
</script>
</html>