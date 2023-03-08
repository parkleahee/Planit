
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
        left: 'title',
        right: 'prev,next today'
      },
      initialView: 'dayGridMonth',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
  					var end_test = arg.end;
  					var dt_end = end_test.setDate(end_test.getDate()-1);
  					var dt_start = moment(arg.start).format('YYYY-MM-DD');
  					dt_end = moment(arg.end).format('YYYY-MM-DD');
  					
  					location.href = "${cp}/schedule/writesetview?start="
						+ dt_start+"&end="+dt_end;
  					// titleform에 각각 담아주어서 전송하기 위함 ~ 
        calendar.unselect()
      },
      
      /*게시물 불러오는 페이지 여기에 작성!!!!!!!!  */
      eventClick: function(arg) {
        location.href = "${cp}/schedule/getwriteview?scnum="
				+ arg.event.id;
      },
      editable: true,
      dayMaxEvents: true, 
      events: [
    	  <c:forEach items="${scBarList}" var="scBar">
          {
            id: '${scBar.scnum}',
             title :'${scBar.title}',
              start :'${scBar.startdate}',
              <c:if test ="${scBar.startdate==scBar.enddate}">
              end :'${scBar.enddate}',
              </c:if>
              <c:if test ="${scBar.startdate!=scBar.enddate}">
              end :'${scBar.enddate} 00:00:01',
              </c:if>
              color :'${scBar.color}',
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
.fc-event-time {
	font-size: 0px;
	display: none;
	visibility: hidden;
}
</style>
<body>
	<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
	<c:if test="${t != null}">
		<script type="text/javascript">
	alert('${t}');
</script>
	</c:if>
	<c:if test="${f != null}">
		<script type="text/javascript">
	alert('${f}');
</script>
	</c:if>
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
				<div id="cal_menu_btn">
					<div id="cal_menu">
						<button id="cal_btn"
							onclick="location.href='${cp}/schedule/mainview'">month</button>
						<button id="tt_btn"
							onclick="location.href='${cp}/schedule/timelist'">timetable</button>
					</div>
					<div id="calendar"></div>
				</div>
				<div id="to">
					<h1>TO DO LIST</h1>
					<div class="todoForm">
						<input type="text" id="todo" name="todo">
						<input type="button" id="add-button" value="➕">
					</div>
					<div id="todo-list">
					</div>
					<div class="todoimg"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var cp = '${cp}';
</script>
<script src='${cp}/resources/js/main_nav.js' type="text/javascript"></script>
<script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
<script>
const todolist = $("#todo-list")
// addtodo위한 div태그 찾기  
const loginUser = "${loginUser.userid}"

// 문서가 시작 할 때 
$(document).ready(function(){ 
	showList();
	// 로그인을 했을때 투두리스트를 불러올수 있도록 하는 함수 ....
})
// 등록 버튼 눌렀을때 함수 실행 
$("#add-button").on("click",function(e){
	e.preventDefault();
	// 원래 이벤트 막아두기 
	var chkbox = document.getElementsByClassName('check_');
	if(chkbox.length>9){
		alert('등록은 10개만 가능합니다 ! 삭제 후 추가해주세요 ~')
		$("[name='todo']").val("");
		return false;
	}
	// 투두리스트 내가 입력하는 내용
	let todocontents = $("[name='todo']").val();
	// 유효성 검사
	if(todocontents == null || todocontents == undefined || todocontents == ""){ alert('내용을 입력해 주세요!'); $("[name='todo']").focus(); return;} 
	//return.. 종료..  
	
	todoService.add(
		{"userid":loginUser,"todocontents":todocontents},
		/* JSON화 시켜서 ajax로 넘겨주기 */
		function(result){
				let str = "";
				// result = todonum
				str += '<div><input type="checkbox" name="'+result+'" class="check_ todocheck">'
				str += '<span id="todo_cont'+result+'">'+todocontents+'</span>';
				str += '<a href="'+result+'"id="delete_btn" class="delete_">❌</a>';
				str += '<a href="'+result+'"id="important_btn" class="important_">⭐️</a>';
				str += '</div>'
				
					todolist.append(str);
				 
				$(".check_").on("click",checkTodo); 
				$(".important_").on("click",importTodo);
				$(".delete_").on("click",deleteTodo);
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
			function(data){
				//콜백함수 받아오기 
				let str = "";
				for(let i=0;i<data.length;i++){
					if(data[i].todocheck==0){
					str += '<div><input type="checkbox" name="'+data[i].todonum+'" class="check_ todocheck">'						
					}else if(data[i].todocheck==1){
					str += '<div><input checked type="checkbox" name="'+data[i].todonum+'" class="check_ todocheck">'	
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
				$(".delete_").on("click",deleteTodo);
				
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
				showList();
			}
		},
		// 에러 발생 
		function(err){
			alert("에러발생");
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

//엔터로 todoform submit 방지하기 
$('input[type="text"]').keydown(function() {
	  if (event.keyCode === 13) {
	    event.preventDefault();
	  };
	});


</script>
</html>
