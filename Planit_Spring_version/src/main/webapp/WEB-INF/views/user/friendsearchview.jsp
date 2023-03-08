<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>친구 검색</title>
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
         <jsp:include page="header.jsp"/>
      </header>
            <div id="friend_list">
                        <ul class="friend_sub">
                            <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/user/friendsearchlist">친구 검색</a></li>
                          <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/friend/friendconfirmlist">신청 확인</a></li>
                          <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/friend/friendlslist">친구 리스트</a></li>
                          <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/friend/friendblocklist">차단 리스트</a></li>
                           <li style="margin-top: 100px; margin-bottom: 100px;"><a class="friend_sub" href="${cp}/schedule/togetherlist">함께하는 일정보기</a></li>
                        </ul>
            </div>
            <div id="contents_box">
                <table class="title">
                    <tr align="center" valign="middle">
                        <td><h3>친구검색</h3></td>
                    </tr>
                    <tr align="right" valign="middle">
                        <td id="ok_fr"><%-- 검색된 친구 : ${totalCnt} --%></td>
                    </tr>
                </table>
        
                <table class="list">
                    <tr id="list_title" align="center" valign="middle">
                        <th width="40%">아이디</th>
                        <th width="30%">이름</th>
                        <th width="30%">친구신청</th>
                    </tr>
                    <c:choose>
                        <c:when test="${friendList != null and friendList.size()>0 }">
                                <c:forEach items="${friendList}" var="user">
                                <form name="addfriendForm" action="${cp}/friend/addfriendok" method="post">   
                                    <tr id="friend_search">
                                        <td><input type="text" name="friendid" value="${user.userid}" readonly="readonly" style="text-align: center; font-size: 15px;"></td>
                                        <td><input type="text" name="friendname" value="${user.username}" readonly="readonly" style="text-align: center; font-size: 15px;"></td>
                                        <td><input type="submit" value="신청" style="text-align: center; font-size: 15px;"></td>
                                    </tr>
                                </form>
                                </c:forEach>    
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td id="no_search" colspan="5" style="text-align: center; font-size: 20px; height: 30px;">검색하신 친구가 존재하지 않습니다.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table><br>
                <div class="search_area">
                    <input type="search" id="q" name="q" value="${keyword}" style="text-align: center; font-size: 15px;"> 
                    <input type="button" value="검색" style="text-align: center; font-size: 15px;" onclick="friendfind()">   
                </div>
            </div>
        </div>
    </div>
</body>
<script>
   var cp = "${cp}";
   
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
   <link rel="stylesheet" href="${cp}/resources/css/friend.css "/>
   <link rel="stylesheet" href="${cp}/resources/css/main_nav.css "/>
</html>