<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="main_nav">
	<div id="m_intro">
		<a id="my_myintro" href="${cp}/schedule/mainview"></a>
	</div>
	<div id="m_mypage" class="m_detail">
		<a id="my_mypage" href="${cp}/user/usergoal">마이페이지</a>
	</div>
	<div id="m_friend" class="m_detail">
		<a id="my_friend" href="${cp}/friend/friendlslist">친구관리</a>
	</div>
	<div id="m_chat" class="m_detail">
		<a id="my_chat" href="${cp}/chat/makeChatRoom">채팅</a>
	</div>
	<div id="m_cont" class="m_detail">
		<a id="my_cont" href="${cp}/user/usercontact">문의하기</a>
	</div>
	<div id="m_logout" class="m_detail">
		<a id="my_logout" href="${cp}/user/userlogout">로그아웃</a>
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
					<span>${goal1.goal}</span>
				</div>
				<div>
					<progress id="progress1" value="${100/30*goal1.goalcnt}" max="100"></progress>
					<br> <br> <span id="goalchk_1"></span>
				</div>
				<%-- <c:choose> --%>
				<c:if test="${goal1.goalcheck == 't'}">
					<input type="button" name="getgoal_btn1" id="getgoal_btn1"
						class="${goal1.goalnum}" value="오늘 목표 달성" onclick="cntGoal_1();">
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
					<span>${goal2.goal}</span>
				</div>
				<div>
					<progress id="progress2" value="${100/30*goal2.goalcnt}" max="100"></progress>
					<br> <br> <span id="goalchk_2"></span>
				</div>
				<c:if test="${goal2.goalcheck == 't'}">
					<input type="button" name="getgoal_btn2" id="getgoal_btn2"
						class="${goal2.goalnum}" value="오늘 목표 달성"
						onclick="cntGoal_2();">
				</c:if>

			</div>
			<script>
				/* alert('목표 설정 성공 ! 목표 설정은 2개까지 가능합니다 !'); */
				document.getElementsByClassName("goalset2")[0].style.display = 'block';
			</script>
		</c:if>
	</div>
</div>


