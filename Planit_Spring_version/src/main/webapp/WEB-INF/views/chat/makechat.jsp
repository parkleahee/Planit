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
/*     history.replaceState({},null, location.pathname);
   const webSocket=new WebSocket("ws://localhost:9090${cp}/ChatingServer");    
   
   var chatWindow, chatMessage, chatId, chatroomnum;
 */   
   
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
         <div id="friend_list">
         <div id="chatlist" style="overflow: scroll;">
<!--           <ul id="chat_ma">
         <li id="chat_md">  -->
            <div id="chatchat">
               <table class="list">
               <tr>
                  <td id="chat_tit">채팅방 이름</td>
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
                            onclick="location.href='${cp}/chat/chatroomentranceok?chatroomnum=${i.chatroomnum}'<%-- '&&chatname='+${i.chatname} --%>"></td>
                        </tr>
                     </c:forEach>
                  </c:when>
               </c:choose>
            </table>
            </div>
         </div>
         <!-- <div id="made_box"> -->
            <%-- <div id="form_contents" style="position: absolute; bottom: 0; left: 275px;">
               <form id="made_chat" action="${cp}/chat/makeNewChatRoom"
                  method="post" name="makechat">
                  <input type="text" name="chatRoomName" class="made_chat1">
                  <input type="submit" class="custom-btn btn-6" value="만들기">
               </form>
            </div> --%>
             <!-- </li> 
            <li id=fr_list>  -->
            <!-- <div id="friendls">
            </div> -->
<!--              </li>
            </ul>  -->
         <!-- </div> -->
         </div>
         <div id="contents_box" style="overflow: scroll; position: relative;">
         <form id="made_chat" action="${cp}/chat/makeNewChatRoom" method="post" name="makechat" onsubmit="return submission();">
            <div>
            <table class="list">
               <tr align="center" valign="middle" id="list_title">
                  <th width="40%">아이디</th>
                  <th width="30%">이름</th>
                  <th width="30%">
                  </th>
               </tr>
               <c:choose>
                  <c:when test="${friendlsList != null and friendlsList.size()>0 }">
                     <%--  <c:set var="i" value="1"/>
                            <c:forEach items="${friendlsList}" var="friend" step="1"> --%>
                     <c:forEach begin="0" end="${friendlsList.size() - 1}" var="i" step="1">
                        <tr>
                           <td><input type="text" id="friendid${i}"
                              value="${friendlsList[i].userid}" readonly="readonly"
                              style="text-align: center; font-size: 15px;"></td>
                           <td><input type="text" id="username"
                              value="${friendlsList[i].username}" readonly="readonly"
                              style="text-align: center; font-size: 15px;"></td>
                           <td><input type="checkbox" class="chatMember" name="chatMember" id="chatMember${i}"
                              value="${friendlsList[i].userid}"
                              style="text-align: center; font-size: 15px;"><label for="chatMember${i}"></label></td>
                        </tr>
                     </c:forEach>
                  </c:when>
               </c:choose>
                 
            </table>
            </div>
            <div style="position: absolute; bottom: 20px; text-align: center; width: 100%;" >
                  <input type="text" name="chatRoomName" class="made_chat1" placeholder="채팅방 이름을 입력하세요">
                  <input type="submit" class="custom-btn btn-6" value="만들기">
            </div>         
         </form>
            
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
	if(goalchk_1 == 'f'){
		document.getElementById("goalchk_1").innerHTML='오늘의 목표 달성🙂';
	}
	
	let goalchk_2="${goal2.goalcheck}";
	if(goalchk_2 == 'f'){
		document.getElementById("goalchk_2").innerHTML='오늘의 목표 달성🙂';
	}
}
   
   function submission(){
       const check = document.getElementsByClassName("chatMember");
       // classname : id가 올 경우 하나만을 가져오게 될 수 있으므로 객체가 여러 개가 될 수 있는 경우 classname을 가져 오는 것이 좋다.
        let flag = false; 
       // flag를 사용하는 이유 : 수정 중에 다른 것도 수정하게 되는 것을 방지하기 위해 flag를 만듬 즉, 하나씩 수정이 가능하게 만들어 주는 것이다.
       for (var i = 0; i < check.length; i++) {
          // 체크를 하나만 하는 것이 아닌 여러 개를 체크할 수 있으므로 배열의 형태를 구성한다.
         if(check[i].checked){
            flag = true;
         }
      }
       
          if(!flag){
          alert("함께 채팅할 친구를 체크해주세요");
          return false;
          }else{
          alert("채팅방을 만듭니다.");
          return true;
       }
   }
</script>
<script>
   var friendid = document.getElementById("friendid${i}");
/*    var chatroomnum = document.getElementById("chatroomnum${i}"); */
</script>
      <script src="${cp}/resources/js/jquery-3.6.1.min.js"></script>
   <script src="${cp}/resources/js/chat.js"></script>
   <script src="${cp}/resources/js/friend.js"></script>
   <script src="${cp}/resources/js/todo.js" ></script>
    <link rel="stylesheet" href="${cp}/resources/css/chat.css"/>   
    <link rel="stylesheet" href="${cp}/resources/css/main_nav.css"/> 
</html>