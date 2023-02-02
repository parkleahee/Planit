<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="com.planit.dto.TimeDTO"%>

<!DOCTYPE html>
<html>
<head>
<title>메인페이지</title>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link href='${cp}/css/main_nav.css' rel='stylesheet' />
<link href='${cp}/css/main.css' rel='stylesheet' />
<link href='${cp}/css/fullcalendar.css' rel='stylesheet' />
<link href='${cp}/css/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='${cp}/js/index.global.js'></script>
<script src='${cp}/js/jquery-1.10.2.js' type="text/javascript"></script>
<script src='${cp}/js/jquery-ui.custom.min.js' type="text/javascript"></script>
<script src='${cp}/js/fullcalendar.js' type="text/javascript"></script>
<script src='${cp}/js/calendar.js' type="text/javascript"></script>
<script class="cssdesk"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js"
	type="text/javascript"></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	headerToolbar: {
        right: 'prev,next today'
      },
      initialView: 'timeGridWeek',
      timeFormat: {
          agenda: 'H(:mm)' //h:mm{ - h:mm}'
      },
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('일정을 입력해주세요 ! :');
        if (title) {
            // calendar.addEvent({
            //   title: title,
            //   start: arg.start,
            //   end: arg.end,
            //   allDay: arg.allDay
            // })
            console.log(title);
  					console.log(arg.start);
  					console.log(arg.end);
  					var dt_start = moment(arg.start).format('YYYY-MM-DDTHH:mm:ss');
  					var dt_end = moment(arg.end).format('YYYY-MM-DDTHH:mm:ss');
  					// titleform에 각각 담아주어서 전송하기 위함 ~ 
  					 $("#timetitle").val(title);
  					 $("#timestart").val(dt_start);
  					 $("#timeend").val(dt_end);
  					 
  					 const timeForm = document.timeForm;
  					 timeForm.submit();
          }
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('삭제할까요?')) {
	console.log(arg.event.id);
        	//보낼 데이터를 Json문자열로 변환
	const xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				let txt = xhr.responseText;
				txt = txt.trim();
				if (txt == "O") {
					alert("삭제 성공!");
					arg.event.remove()
				} else {
					alert("삭제 실패!");
					todo.value = "";
					todo.focus();
				}
			}
		}
	}

	xhr.open("GET", cp + "/schedule/timedelete.tc?timenum=" +arg.event.id, true);
	xhr.send();
} 
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
    	<c:forEach items="${timeList}" var="time">
          {
        	  /* id로 번호받아와서 삭제할수 있도록 수정해야 함 !!!!!! -> 달력도 */
        	  id :'${time.timenum}',
        	  title :'${time.timetitle}',
              start :'${time.timestart}',
              end :'${time.timeend}',
              textColor:"#000000",
              color:"#ffa94d"
           },
		</c:forEach>
           {
          	 title:'default',
          	 start : "2019-01-01",
          	 end : "2019-01-01"
           } 
      ]
    });

    calendar.render();
  });

</script>
<style>
#todo-list>div {
	width: 80%;
	margin: 0 auto;
	text-align: left;
	padding: 10px 0;
	border-bottom: 1px solid grey;
}

#todo-list>div>button {
	width: 20px;
	height: 20px;
	margin-left: 10px;
	border: 1px dotted gray;
	background-color: rgba(0, 0, 0, 0);
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

.goalset1 {
	display: none;
}

.goalset2 {
	display: none;
}

#cal_menu_btn {
	float: left;
}

#cal_btn {
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	border-right: none;
	width: 100px;
	height: 40px;
}

#tt_btn {
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	margin-left: -5px;
	width: 100px;
	height: 40px;
}

#cal_menu button {
	border: 1px solid #ff3b30;
	background-color: rgba(0, 0, 0, 0);
	color: #ff3b30;
	padding: 5px;
}

#cal_menu button:hover {
	color: white;
	background-color: #ff3b30;
}
</style>
<body>
	<form id="timeForm" name="timeForm" action="${cp}/schedule/timetest.tc"
		method="post">
		<input type="hidden" name="timetitle" id="timetitle" value="">
		<input type="hidden" name="timestart" id="timestart" value="">
		<input type="hidden" name="timeend" id="timeend" value="">
	</form>
	<div id="wrap">
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
									action="${cp}/schedule/addgoalokaction.tc"
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
									<input type="submit" value="목표 설정"> <input
										type="button" id="close"
										onclick="location.href='${cp}/app/schedule/mainview.jsp';">돌아가기</input>
								</form>
							</div>
						</div>
					</div>
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
			<div id="cal_todo">
				<div id="cal_btn_menu">
					<div id="cal_menu_btn">
						<div id="cal_menu">
							<button id="cal_btn"
							onclick="location.href='${cp}/schedule/todoview.tc'">month</button>
						<button id="tt_btn"
							onclick="location.href='${cp}/schedule/timelist.tc'">timetable</button>
						</div>
						<div id="calendar"></div>
					</div>
				</div>
				<div id="to">
					<h1>TO DO LIST</h1>
					<form id="todoForm" name="todoForm" method="post">
						<%-- action="${cp}/schedule/todook.tc"> --%>
						<input type="text" id="todo" name="todo"> <input type="button"
							id="add-button" name="add-button" value="+" onclick="addTodo()">
					</form>
					<div id="todo-list">
						<c:choose>
							<c:when test="${todoList != null and todoList.size() < 11}">
								<c:forEach var="i" begin="0" end="${todoList.size()-1}" step="1">
									<%-- <c:set var="todo" value="todoList[i]"/> --%>
									<c:if test="${todoList[i].todoimport == 1}">
										<div>

											<input
												<c:if test="${todoList[i].todocheck == 1}">checked</c:if>
												type="checkbox" onclick="checkTodo('${i+1}')"> <span
												class="clicked" id="todo_cont">${todoList[i].todocontents}</span>
											<input type="hidden" id="todonum${i+1}" name="todonum"
												value="${todoList[i].todonum}"> <input type="button"
												id="important_btn" value="x" onclick="importTodo('${i+1}')">
											<input type="button" id="delete_btn" value="x"
												onclick="deleteTodo('${i+1}')">
										</div>
									</c:if>
									<c:if test="${todoList[i].todoimport == 0 }">
										<div>
											<input
												<c:if test="${todoList[i].todocheck == 1}">checked</c:if>
												type="checkbox" onclick="checkTodo('${i+1}')"> <span
												id="todo_cont">${todoList[i].todocontents}</span> <input
												type="hidden" id="todonum${i+1}" name="todonum"
												value="${todoList[i].todonum}"> <input type="button"
												id="important_btn" value="x" onclick="importTodo('${i+1}')">
											<input type="button" id="delete_btn" value="x"
												onclick="deleteTodo('${i+1}')">
											<%-- <input type="button" id="delete_btn" value="x" onclick="location.href='${cp}/schedule/tododeleteok.tc?todonum=${todo.todonum}'"> --%>
										</div>
									</c:if>
								</c:forEach>
							</c:when>
						</c:choose>
						<!-- <script>
							const todo = document.todoForm.todo;
							alert('10개까지 등록 가능합니다 ! 다른 일정 삭제후 등록해 주세요 !'); 
							todo.value = "";
						</script> -->
						<%-- </c:otherwise>
					</c:choose> --%>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var cp = '${cp}';
	var bdto = '${bdto.goal}';
	var i = '${i+1}';
</script>
<script src='${cp}/js/main_nav.js' type="text/javascript"></script>
<script>

	function addTodo() {
		const xhr = new XMLHttpRequest();
		const todo = document.todoForm.todo;
		if (todo.value == "") {
			alert("내용을 입력하세요 !");
			todo.focus();
			return false;
		}

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let txt = xhr.responseText;
					txt = txt.trim();
					if (txt == "O") {
						alert("등록 성공!");
						(location || window.location || document.location).reload();
						
					} else {
						alert("등록 실패!");
						todo.value = "";
						todo.focus();
					}
				}
			}
		}

		xhr.open("GET", cp + "/schedule/todook.tc?todo=" + todo.value, true);
		xhr.send();
	} 
	
	/* function addTodo(){
		//폼 가져오기
		var form = $('#todoForm')[0];
		
		// Create an FormData object 
		var data = new FormData(form);
		
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
			url: cp+'/schedule/todook.tc',	// form을 전송할 실제 파일경로
			data: data,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			beforeSend : function() {
				// 전송 전 실행 코드
			},
			success: function (data) {
				// 전송 후 성공 시 실행 코드
				console.log(data);
			},
			error: function (e) {
				// 전송 후 에러 발생 시 실행 코드
				console.log("ERROR : ", e);
			}
		});
	} */
	
	function deleteTodo(i) {
	
	var todonum = document.getElementById("todonum"+i); 
        	//보낼 데이터를 Json문자열로 변환
	const xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				let txt = xhr.responseText;
				txt = txt.trim();
				if (txt == "O") {
					alert("삭제 성공!");
					(location || window.location || document.location).reload();
				} else {
					alert("삭제 실패!");
					todo.value = "";
					todo.focus();
				}
			}
		}
	}

	xhr.open("GET", cp + "/schedule/tododeleteok.tc?todonum="+todonum.value, true);
	xhr.send();
}
	
	function importTodo(i) {
		
		var todonum = document.getElementById("todonum"+i); 
	        	//보낼 데이터를 Json문자열로 변환
		const xhr = new XMLHttpRequest();

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let txt = xhr.responseText;
					txt = txt.trim();
					if (txt == "O") {
						alert('성공 !');
						(location || window.location || document.location).reload();
					} else {
						todo.value = "";
						todo.focus();
					}
				}
			}
		}

		xhr.open("GET", cp + "/schedule/todoimport.tc?todonum="+todonum.value, true);
		xhr.send();
	}
	
function checkTodo(i) {
		
		var todonum = document.getElementById("todonum"+i); 
	        	//보낼 데이터를 Json문자열로 변환
		const xhr = new XMLHttpRequest();

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					let txt = xhr.responseText;
					txt = txt.trim();
					if (txt == "O") {
						alert('성공 !');
						(location || window.location || document.location).reload();
					} else {
						todo.value = "";
						todo.focus();
					}
				}
			}
		}

		xhr.open("GET", cp + "/schedule/todocheck.tc?todonum="+todonum.value, true);
		xhr.send();
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
<script src='${cp}/js/main_nav.js' type="text/javascript"></script>
</html>
