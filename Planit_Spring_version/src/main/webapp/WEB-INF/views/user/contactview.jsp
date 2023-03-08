<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>문의하기</title>
    <link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
    <link href='${cp}/resources/css/contact.css' rel='stylesheet' />
</head>
<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
    <div id="wrap">
       <header>
			<jsp:include page="header.jsp"/>
		</header>
        <div id="sub">
            <div class="container">
                <div id="contact_info">
                    <h3>1:1문의쓰기</h3>
                    <p>산업안전보건법 제 41조 시행령에 근거하여 2018년 10월 18일 부터 산업안전보건법에 따라 고객응대근로자 보호조치를 시행하고 있습니다.
                        고객응대근로자에게 폭언, 폭행 등을 하지 말아주세요.</p>
                    </div>
       
                    <form name="contact" method="post" action="${cp}/user/usercontactokk" onsubmit="return contactChk();">
                        <ul class="tb_lst">
                            <li class="user_id">
                                <div class="in_td">
                                    <strong class="type">아이디</strong>
                                </div>
                                <div class="in_td">
                                    <p class="txt">${loginUser.userid}</p>
                                </div>
                            </li>
                            <li class="tit_row">
                                <div class="in_td">
                                    <label for="u_title" class="type">제목</label>
                                </div>
                                <div class="in_td">
                                    <div class="inpbx">
                                        <input type="text" name="contacttitle" id="u_title" placeholder="제목을 입력하세요.">
                                    </div>
                                </div>
                            </li>
                            <li class="cnt_row">
                                <div class="in_td">
                                    <label for="u_content" class="type">내용</label>
                                </div>
                                <div class="in_td">
                                    <div class="inptxtbx">
                                        <textarea name="contactcontents" id="u_content" rows="5"></textarea>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <div id="contact_notice">
                            <p class="txt_notice">
                                * 운영시간 : 평일 10:00 ~ 17:00 (점심시간 12:30 ~ 13:30 제외 / 주말 및 공휴일 제외)
                            </p>
                        </div>
                        <div class="btn_bx">
                            <input type="submit" class="btn_black" value="등록하기">
                        </div>
                    </form>
                </div>
                </div>
            </div>
    </body>
    <script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
    <script>
	var cp = '${cp}';
	
	function contactChk(){
		const contactForm = document.contact;
		const contactitle = contactForm.contacttitle;
		const contactcontents = contactForm.contactcontents;
		  
		    if(contactitle.value == ""){
		        alert("제목을 작성해 주세요!");
		        contacttitle.focus();
		        return false;
		    }
		    if(contactitle.value == "제목을 입력하세요."){
		        alert("제목을 작성해 주세요!");
		        contacttitle.value="";
		        contacttitle.focus();
		        return false;
		    }
		   	if(contactcontents.value==""){
		   		alert("내용을 작성해 주세요!");
		   		contactcontents.focus();
		   		return false;
		   	}
		    return true;
	}
	
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

//엔터로 todoform submit 금지하기 
$('input[type="text"]').keydown(function() {
	  if (event.keyCode === 13) {
	    event.preventDefault();
	  };
	});


	
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
	
	

</script>	
    </html>