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
    
    <script>
    history.replaceState({},null, location.pathname);
   
   
   var chatWindow, chatMessage, chatId, chatroomnum;
   
   
    </script>

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
					<jsp:include page="header.jsp" />
				</c:otherwise>
			</c:choose>
		</header>
            <%-- <div id="friend_list">
             <table class="title">
                    <tr align="center" valign="middle">
                        <td><h3>채팅방 리스트</h3></td>
                    </tr>
                </table>
                <table class="chatroom_list">
                    <tr align="center" valign="middle" id="list_title">
                        <th width="70%">채팅방 번호</th>   
                    </tr>
                    <c:choose>
                  <c:when test="${chatroomList != null and chatroomList.size()>0 }">
                     <c:forEach begin="0" end="${chatroomList.size() - 1}" var="i" step="1">
                        <tr>
                           <td><input type="button" id="chatroomnum" value="${chatroomList[i].chatroomnum}" 
                           readonly="readonly" style="text-align: center; font-size: 15px;"></td>
                            <td><input type="button" id="join" value="참여하기" 
                            style= "text-align: center; font-size: 15px;" 
                            onclick="location.href='/chat/chatroomentranceok?chatroomnum='+${chatroomList[i].chatroomnum}"></td>
                        </tr>
                     </c:forEach>
                  </c:when>
               </c:choose>
                </table>
            </div> --%>
            <div id="friend_list">
         <div id="chatlist" style="overflow: scroll;">
            <div id="chatchat">
               <table class="list">
               <tr id="list_title">
                  <th>채팅방 이름</th>
               </tr>
               <c:choose>
                  <c:when test="${chatroomList != null and chatroomList.size()>0 }">
                     <%--  <c:set var="i" value="1"/>
                     
                            <c:forEach items="${friendlsList}" var="friend" step="1"> --%>
                     <c:forEach items="${chatroomList}" var="i">
                        <tr>
                           <td><input type="text" id="chatroomnum" value="${i.chatname}" 
                           readonly="readonly" style="text-align: center; font-size: 15px;"></td>
                            <td><input type="button" id="join" value="참여하기" 
                            style="text-align: center; font-size: 15px;" 
                            onclick="location.href='${cp}/chat/chatroomentranceok?chatroomnum='+${i.chatroomnum}"></td>
                        </tr>
                     </c:forEach>
                  </c:when>
               </c:choose>
            </table>
            </div>
         </div>
         </div>
            <div id="contents_box">
            <input type="text" id="chatId" value="${chatname}" readonly />
            <div id="chatWindow">
            <table>
               <c:forEach items="${chatcontent}" var="i" >
                  <c:choose>
                     <c:when test="${loginUser.userid == i.fromUser}">
                        <div class="myMsg">${i.contents}</div>
                     </c:when>
                     <c:otherwise>
                        <div class="yourMsg">${i.fromUser}:${i.contents}</div>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
            </table>
            </div>
            <div>
               <input type="text" id="chatMessage" onkeypress="enterKey();">
               <button id="sendBtn" class="w-btn w-btn-gra3 w-btn-gra-anim" onclick="sendMessage();">전송</button>
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
	
   var chatWindow,chatMessage,chatid,chatroomnum;
   window.onload = function() {
	   let goalchk_1="${goal1.goalcheck}";
		if(goalchk_1 == 'f'){
			document.getElementById("goalchk_1").innerHTML='오늘의 목표 달성🙂';
		}
		
		let goalchk_2="${goal2.goalcheck}";
		if(goalchk_2 == 'f'){
			document.getElementById("goalchk_2").innerHTML='오늘의 목표 달성🙂';
		}
   chatWindow = document.getElementById("chatWindow");
   chatMessage = document.getElementById("chatMessage");
   chatid = '${loginUser.userid}';
   chatroomnum =${chatroomnum};
}
   const webSocket=new WebSocket("ws://localhost:9090${cp}/ChatingServer");
   // 웹소켓 서버에 연결됐을 때 실행
   webSocket.onopen = function(event) {
/* chatWindow.innerHTML += "웹소켓 서버에 연결되었습니다.<br/>"; */
   webSocket.send("${chatroomnum}:${loginUser.userid}");
   console.log(chatroomnum +":"+chatId);
} 
   
   
</script>
   <script src="${cp}/resources/js/jquery-3.6.1.min.js"></script>
   <script src="${cp}/resources/js/chat.js" ></script>
   <script src="${cp}/resources/js/todo.js" ></script>
   <link rel="stylesheet" href="${cp}/resources/css/chat.css"/>
   <link rel="stylesheet" href="${cp}/resources/css/main_nav.css"/>
    
</html>