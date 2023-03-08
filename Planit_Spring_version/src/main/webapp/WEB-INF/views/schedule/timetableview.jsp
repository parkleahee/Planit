<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title>메인페이지</title>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
<script src='${cp}/resources/js/index.global.js'></script>
<script src='${cp}/resources/js/jquery-1.10.2.js' type="text/javascript"></script>
<script src='${cp}/resources/js/jquery-ui.custom.min.js'
	type="text/javascript"></script>
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
  					console.log(arg.start);
  					console.log(arg.end);
  					var dt_start = moment(arg.start).format('YYYY-MM-DDTHH:mm:ss');
  					var dt_end = moment(arg.end).format('YYYY-MM-DDTHH:mm:ss');
  					 $("#timestart").val(dt_start);
  					 $("#timeend").val(dt_end);
  					show_time();
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('삭제할까요?')) {
        		let timenum = arg.event.id;
        	$.ajax({
    			// 내부에서 객체를 넘겨준다. 
    			type:"GET",
    			//REST방식의 개발 -> get(select의 역할)
    			headers : { "Content-type" : "application/json", "X-HTTP-Method-Override" : "POST" },
    			url:cp+"/schedule/"+timenum,
    			 success : function (data) {
                     alert('삭제 완료!');
                     arg.event.remove()
                 }
    		})
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
              color:'${time.timecolor}'
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
.fc-event-time{
	font-size:8px;
	}
</style>
<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
	<%-- <form id="timeForm" name="timeForm" action="${cp}/schedule/timeadd.tc"
		method="post">
		<input type="text" name="timetitle" id="timetitle" value="">
		<input type="hidden" name="timestart" id="timestart" value="">
		<input type="hidden" name="timeend" id="timeend" value="">
		<input type="color" name="timecolor" id="timecolor" value="">
	</form> --%>
	<div class="background_time">
		<div class="window_time">
			<div class="popup_time">
				<form id="timeForm" name="timeForm" method="post"
					action="${cp}/schedule/timeadd" onsubmit="return timeChk()">
					<table id="time_tb">
						<tbody>
							<tr>
								<td><h3>일정 추가하기</h3></td>
							</tr>
							<tr>
								<td><input type="text" name="timetitle" id="timetitle"></td>
							</tr>
							<tr>
								<td><input type="hidden" name="timestart" id="timestart"></td>
							</tr>
							<tr>
								<td><input type="hidden" name="timeend" id="timeend"></td>
							</tr>
							<tr>
								<td>배경 설정</td>
							</tr>
							<tr>
								<td><input type="color" name="timecolor" id="timecolor"></td>
							</tr>
						</tbody>
					</table>
					<input class="submit_time" type="submit" value="완료"> <input
						type="button" id="timeclose" class="timeclose"
						onclick="location.href='${cp}/schedule/timelist';" value="돌아가기">
				</form>
			</div>
		</div>
	</div>

	<div id="wrap">
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
		<div id="sub">
			<div id="cal_todo">
				<div id="cal_btn_menu">
					<div id="cal_menu_btn">
						<div id="cal_menu">
							<button id="cal_btn"
								onclick="location.href='${cp}/schedule/mainview'">month</button>
							<button id="tt_btn"
								onclick="location.href='${cp}/schedule/timelist'">timetable</button>
						</div>
						<div id="calendar"></div>
					</div>
				</div>
				<div id="to">
					<h1>TO DO LIST</h1>
					<div class="todoForm">
						<%-- action="${cp}/schedule/todook.tc"> --%>
						<input type="text" id="todo" name="todo"> <input
							type="button" id="add-button" value="➕">
					</div>
					<div id="todo-list"></div>
					<div class="todoimg"></div>
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
<script src='${cp}/resources/js/main_nav.js' type="text/javascript"></script>
<script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
<script>
function show() {
	document.querySelector(".background").className = "background show";
}

function close() {
	document.querySelector(".background").className = "background";
}

document.querySelector("#show").addEventListener("click", show);
document.querySelector("#close").addEventListener("click", close);

const todolist = $("#todo-list")
// addtodo위한 div태그 찾기  
const loginUser = "${loginUser.userid}"
//현재 보고 있는 todolist의 일정이 있는지 없는지를 표기하는 flag
let flag = true;

// 문서가 시작 할 때 
$(document).ready(function(){ 
	showList();
})
// 등록 버튼 눌렀을때 함수 실행 
$("#add-button").on("click",function(e){
	e.preventDefault();
	// 원래 이벤트 막아두기 
	let todocontents = $("[name='todo']").val();
	if(todocontents == null || todocontents == undefined || todocontents == ""){ alert('내용을 입력해 주세요!'); $("[name='todo']").focus(); return;}
	// 댓글 등록하기 위해 작성한 내용 추출 한 다음 
	todoService.add(
		{"userid":loginUser,"todocontents":todocontents},
		/* JSON화 시켜서 ajax로 넘겨주기 */
		function(result){
				let str = "";
				
				str += '<div><input type="checkbox" name="'+result+'" class="check_">'
				str += '<span id="todo_cont'+result+'">'+todocontents+'</span>';
				str += '<a href="'+result+'"id="delete_btn" class="delete_">❌</a>';
				str += '<a href="'+result+'"id="important_btn" class="important_">⭐</a>';
				str += '</div>'
					todolist.append(str);
				 
				$(".check_").on("click",checkTodo); 
				$(".important_").on("click",importTodo);
				// 방금 추가한 댓글도 삭제할수 있도록 댓글등록 후에도 이벤트를 달아주어야 한다. 
				$(".delete_").on("click",deleteTodo);
			// 댓글 추가 후 페이지 추가가 필요한 경우 
		}
	)
	$("[name='todo']").val("");
	// value 비워주기 
})

function showList(){
		//ajax
		console.log(loginUser);
		todoService.getList(
			//데이터 넘겨주기 
			loginUser,
		// 페이지넘이 담겨져 있는 것이 없는 경우 뒤에값을 넘겨준다 (or) ->  1page 지정 
			function(data){
				console.log(data[0]);
				//콜백함수 받아오기 
				let str = "";
				
				for(let i=0;i<data.length;i++){
					if(data[i].todocheck==0){
					str += '<div><input type="checkbox" name="'+data[i].todonum+'" class="check_">'						
					}else if(data[i].todocheck==1){
					str += '<div><input checked type="checkbox" name="'+data[i].todonum+'" class="check_">'	
					}
					if(data[i].todoimport==0){
					str += '<span id="todo_cont'+data[i].todonum+'">'+data[i].todocontents+'</span>';						
					}else if(data[i].todoimport==1){
					str += '<span class="clicked" id="todo_cont'+data[i].todonum+'">'+data[i].todocontents+'</span>';
					}
					str += '<a href="'+data[i].todonum+'"id="delete_btn" class="delete_">❌</a>';
					str += '<a href="'+data[i].todonum+'"id="important_btn" class="important_">⭐</a>';
					str += '</div>'
				}
				todolist.html(str);
				// 추가가 아니라 변경 
				
				$(".check_").on("click",checkTodo);
				$(".important_").on("click",importTodo);
				// 방금 추가한 댓글도 삭제할수 있도록 댓글등록 후에도 이벤트를 달아주어야 한다. 
				$(".delete_").on("click",deleteTodo);
			// 댓글 추가 후 페이지 추가가 필요한 경우 
				
			}
		)
	}
	function deleteTodo(e){
	// 이벤트 객체 e선언 
	e.preventDefault();
	let todonum = $(this).attr("href");
	// href의 속성을 꺼냄 
	todoService.remove(
		todonum,
		function(result){
			if(result == "success"){
				addcnt--;
				console.log(addcnt);
				showList();
			}
		},
		// 에러 발생 
		function(err){
			showList();
		}
	);
}
	
	//수정 완료버튼을 눌렀을 때  
	function importTodo(e){
		e.preventDefault(); 
		let todonum = $(this).attr("href");
		//번호 추출 
		
		todoService.importTodo(
				// 자원 전체를 전달해야 한다. (JSON)
			todonum,
			function(result){
				if(result == "success"){
					showList();
				}
			}
		)
	}	
	
	function checkTodo(e){
		e.preventDefault(); 
		let todonum = $(this).attr("name");
		console.log(todonum);
		//번호 추출 
		
		todoService.checkTodo(
				// 자원 전체를 전달해야 한다. (JSON)
			todonum,
			function(result){
				if(result == "success"){
					alert('todolist 달성!');
					showList();
				}
			}
		)
	}	
	
	//addGoal ajax
	$(".submit_id").on("click",function(e){
		e.preventDefault();
		// 원래 이벤트 막아두기 
		let goal = $("[name='goal']").val();
		if(goal == null || goal == undefined || goal == ""){ alert('내용을 입력해 주세요!'); $("[name='goal']").focus(); return;}
		// 댓글 등록하기 위해 작성한 내용 추출 한 다음 
		goalService.addGoal(
			{"goal":goal},
			/* JSON화 시켜서 ajax로 넘겨주기 */
			function(result){
	            if(result == "success"){
	                alert('목표 등록 완료 ! ');
	                console.log('${goal1}');
	                console.log('${goal2}');
	                (location || window.location || document.location).reload();
	            }
			}
		)
	})
	
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

	function timeChk(){
		const timeForm = document.timeForm;
		const timetitle = timeForm.timetitle;
		    if(timetitle.value == ""){
		        alert("내용을 입력하세요 !");
		        timetitle.focus();
		        return false;
		    }
		    return true;
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

//시간표 설정 모달창 
function show_time() {
    document.querySelector(".background_time").className = "background_time show_time";
}

//엔터로 todoform submit하기 
$('input[type="text"]').keydown(function() {
	  if (event.keyCode === 13) {
	    event.preventDefault();
	  };
	});
	



</script>
</html>
