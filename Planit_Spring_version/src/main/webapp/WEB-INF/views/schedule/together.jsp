<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>친구관리</title>
</head>
<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
    <div id="wrap">
        <div id="container">
            <header>
			<c:choose>
				<c:when test="${loginUser.userid == 'admin'}">
					<div id="main_nav">
						<div id="m_intro">
							<a id="my_myintro" href="${cp}/schedule/mainview"></a>
						</div>
						<div id="m_chat" class="m_detail">
							<a id="my_chat" href="${cp}/chat/makeChatRoom">채팅</a>
						</div>
						<div id="m_cont" class="m_detail">
							<a id="my_cont" href="${cp}/admin/contactlist">문의보기</a>
						</div>
						<div id="m_logout" class="m_detail">
							<a id="my_logout" href="${cp}/user/userlogout">로그아웃</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<jsp:include page="header_goalx.jsp" />
				</c:otherwise>
			</c:choose>
		</header>
            <div id="friend_list">
                <ul class="friend_sub">
                    <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/user/friendsearchlist.tc">친구 검색</a></li>
                    <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/friend/friendconfirmlist.tc">신청 확인</a></li>
                    <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/friend/friendlslist.tc">친구 리스트</a></li>
                    <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/friend/friendblocklist.tc">차단 리스트</a></li>
                    <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/schedule/togetherlist.tc">함께하는 일정보기</a></li>
                </ul>
            </div>
            <div id="contents_box" style="overflow: scroll;">
                <table class="title">
                    <tr align="center" valign="middle">
                        <td><h3>함께하는 일정 리스트</h3></td>
                    </tr>
                </table>
                
                <!-- 게시글 리스트 띄우는 테이블 -->
                <table class="list">
                    <tr align="center" valign="middle" id="list_title">
                        <th width="40%">스케줄 제목</th>   
                        <th width="30%">스케줄 작성자</th>   
                        <th width="30%"></th>
                    </tr>
                    <c:choose>
                        <c:when test="${togetherList != null and togetherList.size()>0 }">
                            <c:forEach begin="0" end="${togetherList.size() - 1}" var="i" step="1">
                                    <tr>
                                        <td><input type="text" id="scheduletitle${i}" value="${togetherList[i].scheduletitle}" readonly="readonly" style="text-align: center; font-size: 15px;"></td>
                                        <td><input type="text" id="scheduleuserid" value="${togetherList[i].userid}" readonly="readonly" style="text-align: center; font-size: 15px;"></td>                                       
                                        <td><input type="button" id="look" value="보러가기" style= "text-align: center; font-size: 15px;" onclick="lookschedule('${togetherList[i].scnum}')"></td>
                                    </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </table>
            </div>
        </div>
    </div>
</body>
<script>
   var cp = "${cp}";
   /* osole.log("friendid${i}") */
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
	
window.onload = function(){
	let goalchk_1="${goal1.goalcheck}";
	console.log('${goal1.goalcheck}');
	if(goalchk_1 == 'f'){
		document.getElementById("goalchk_1").innerHTML='오늘의 목표 달성🙂';
	}
	
	let goalchk_2="${goal2.goalcheck}";
	console.log('${goal1.goalcheck}');
	if(goalchk_2 == 'f'){
		document.getElementById("goalchk_2").innerHTML='오늘의 목표 달성🙂';
	}
}
   
</script>
   <script src="${cp}/resources/js/jquery-3.6.1.min.js"></script>
   <script src="${cp}/resources/js/friend.js" ></script>
   <script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
   <link rel="stylesheet" href="${cp}/resources/css/friend.css"/>
   <link rel="stylesheet" href="${cp}/resources/css/main_nav.css"/>
</html>