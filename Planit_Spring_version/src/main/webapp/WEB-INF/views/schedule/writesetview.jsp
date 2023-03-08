<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${cp}/resources/css/writeset.css" rel="stylesheet"/> <!-- 테마선택창 css -->
<link href='${cp}/resources/css/main_nav.css' rel='stylesheet'/>
<!-- <link href='/resources/css/main.css' rel='stylesheet' /> -->

</head>
<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
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
        <form action="${cp}/schedule/writeview" method="post" name="WriteSetForm">
            <div id="center_content">
                <div id="top_center_content">
                   <div class="dropdown">
                       <span class="dropbtn hover">스케줄 보기</span>
                       <div class="dropdown-content" id="dropdown_left">
                          <c:choose>
                             <c:when test="${titlelist != null and titlelist.size() > 0}">
                                <c:forEach var="i" begin="0" end="${titlelist.size()-1}">
                                    <div class="lastSche" id="scheYes"><a href="${cp}/schedule/getwriteview?scnum=${titlelist[i].scnum}">제목 : ${titlelist[i].scheduletitle}</a></div>                                
                                </c:forEach>                          
                             </c:when>
                             <c:otherwise>
                               <div class="lastSche" id="scheNope">작성된 스케줄이 없습니다.</div>                                
                             </c:otherwise>
                          </c:choose>
                       </div>
                   </div> 
                   <div class="dropdown">
                       <span class="dropbtn">스케줄 작성</span>
                        <div class="dropdown-content">
                            <div class="hover" id="createSchedule">새로 만들기</div>
                        </div>
                    </div>
                </div>
                <div id="bottom_center_content">
                    <div id="bottom_title">
                        <div>
                            <div>날짜</div>
                            <div>권한</div>
                            <div>친구</div>
                        </div>
                    </div>               
                    <div id="bottom_container">
                        <div id="bottom_cont1">
                            <c:choose>
                               <c:when test="${param.end == null}">
                                   <input type="text" name="cont1_date1" id="cont1_date1" required='' value="${startt}">
                                   <label alt='Placeholder' placeholder='Start'></label>
                                  <br><br><br>
                                   <br>
                                   <input type="text" name="cont1_date2" id="cont1_date2" required='' value="${startt}">
                                   <label alt='Placeholder' placeholder='End'></label>
                               </c:when>
                               <c:otherwise>
                                   <input type="text" name="cont1_date1" id="cont1_date1" value="${startt}">
                                   <label alt='Placeholder' placeholder='Start'></label>
                                   <br><br><br>
                                   <br>
                                   <input type="text" name="cont1_date2" id="cont1_date2" value="${endd}">                               
                                   <label alt='Placeholder' placeholder='End'></label>
                               </c:otherwise>
                            </c:choose>
                        </div>
                        <div id="bottom_cont2">
                            <label for="alone">혼자하기</label>
                            <input type="radio" name="authority" id="alone" value="혼자하기" onclick="div_OnOff(this.value,'friendList')">
                            <br>
                            <label for="read">함께보기</label>
                            <input type="radio" name="authority" id="read" value="함께보기" onclick="div_OnOff(this.value,'friendList')" checked>
                            <br>
                            <label for="write">함께수정</label>
                            <input type="radio" name="authority" id="write" value="함께수정" onclick="div_OnOff(this.value,'friendList')">
                        </div>
                        <div id="bottom_cont3">
                     <div id="friendList">
                        <c:choose>
                           <c:when test="${flist.size() > 0 }">
                              <c:forEach var="i" begin="0" end="${flist.size()-1}">
                                 <label for="friend${i}">${flist[i].username}</label>
                                 <input type="checkbox" name="friends" id="friend${i}" value="${flist[i].userid}"><br>                        
                              </c:forEach>
                           </c:when>
                           <c:otherwise>
                              <div>친구가 없습니다.</div>
                           </c:otherwise>
                        </c:choose>
                     </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
               <c:when test="${flist.size() > 0 }">
                  <c:forEach var="i" begin="0" end="${flist.size()-1}">
                     <input type="hidden" name="friendList" value="${flist[i].username}">
                     <input type="hidden" name="friendIdList" value="${flist[i].userid}">
                  </c:forEach>
               </c:when>
               <c:otherwise>
               </c:otherwise>
            </c:choose>
            <div id="submit">
               <input type="submit" value="작성하기" id="submit" class="w-btn w-btn-pink" onclick="return sendit();">
            </div>
        </form>
     </div>

</body>
<script type="text/javascript">
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
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${cp}/resources/js/writeset.js"></script>
<script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
</html>