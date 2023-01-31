
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>메인페이지</title>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />


<link href='${cp}/css/main_nav.css' rel='stylesheet' />
<link href='${cp}/css/main.css' rel='stylesheet' />
<script src='${cp}/js/index.global.js'></script>
<script src='${cp}/js/jquery-1.10.2.js' type="text/javascript"></script>
<script src='${cp}/js/jquery-ui.custom.min.js' type="text/javascript"></script>


<script class="cssdesk"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js"
	type="text/javascript"></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'title',
        right: 'prev,next today'
      },
      initialView: 'dayGridMonth',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
    	  /* var title = prompt('제목을 입력해 주세요 :'); */
       /*  if (title) { */
            // calendar.addEvent({
            //   title: title,
            //   start: arg.start,
            //   end: arg.end,
            //   allDay: arg.allDay
            // })
            /* var dt_start = moment(start).format('YYYY MM DD');
								var dt_end = moment(end).format('YYYY MM DD');
								location.href = "${cp}/main/calDate.us?start="
										+ dt_start+"&end="+dt_end; */
           /*  console.log(title); */
  					console.log(arg.start);
  					console.log(arg.end);
  					var dt_start = moment(arg.start).format('YYYY-MM-DDTHH:mm:ss');
  					var dt_end = moment(arg.end).format('YYYY-MM-DDTHH:mm:ss');
  					location.href = "${cp}/schedule/calDate.tc?start="
						+ dt_start+"&end="+dt_end;
  					// titleform에 각각 담아주어서 전송하기 위함 ~ 
  					 /* $("#timetitle").val(title);
  					 $("#timestart").val(dt_start);
  					 $("#timeend").val(dt_end);
  					 
  					 const timeForm = document.timeForm;
  					 timeForm.submit(); */
          /* } */
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('삭제할까요?')) {
        	console.log(arg);
          /* arg.event.remove() */
        	/* location.href = "${cp}/schedule/timeDelete.tc?start="
				+ dt_start+"&end="+dt_end; */
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
    	<c:forEach items="${timeList}" var="time">
          {
          	title :'${time.timetitle}',
              start :'${time.timestart}',
              end :'${time.timeend}',
           },
		</c:forEach>
           {
          	 title:'default',
          	 start : "2019-01-01",
          	 end : "2019-01-01"
           } 
          ,
        {
          title: 'All Day Event',
          start: '2020-09-01'
        },
        {
          title: 'Long Event',
          start: '2020-09-07',
          end: '2020-09-10'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-09-09T16:00:00'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-09-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2020-09-11',
          end: '2020-09-13'
        },
        {
          title: 'Meeting',
          start: '2020-09-12T10:30:00',
          end: '2020-09-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2020-09-12T12:00:00'
        },
        {
          title: '테스트',
          start: '2020-09-13T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2020-09-13'
        },
        {
          title: 'Dinner',
          start: '2020-09-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2020-09-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2020-09-28'
        }
      ]
    });

    calendar.render();
  });

</script>
<style>
/* body{
	background-image:url('https://images.unsplash.com/photo-1564944062924-57874442ddea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1548&q=80');
	background-size:cover;
} */
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
							</div>
							<c:choose>
								<c:when test="${goal1.goalcheck == 't'}">
									<input type="button" name="getgoal_btn1" id="getgoal_btn1"
										value="오늘 목표 달성"
										onclick="location.href='${cp}/schedule/cntgoalview.tc?goal=goal1&goalnum=${goal1.goalnum}'">
								</c:when>
								<c:when test="${goal1.goalcheck == 'f'}">
										document.getElementById("getgoal_btn1").style.display = 'none';
									</c:when>
							</c:choose>
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
								<progress id="progress2" value="0" max="100"></progress>
							</div>
							<input type="button" name="getgoal_btn2" id="getgoal_btn2"
								value="오늘 목표 달성"
								onclick="location.href='${cp}/user/cntgoalview.tc?goal=goal2&goalnum=${goal2.goalnum}'">
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
				<div id="cal_menu_btn">
					<div id="cal_menu">
						<button id="cal_btn"
							onclick="location.href='${cp}/app/schedule/mainview.jsp'">month</button>
						<button id="tt_btn"
							onclick="location.href='${cp}/schedule/timelist.tc'">timetable</button>
					</div>
					<div id="calendar"></div>
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
	
	
</script>
</html>
