<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
<link rel="stylesheet" href="${cp}/resources/css/modify.css">
<link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
</head>

<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
	<div id="clone_proof">
		<form id="email_proof_form">
			<!-- <label for="cloneusername">cloneusername</label>
        <input type="text" name="cloneusername" id="cloneusername"> -->
			<label for="message">message</label> <input type="text"
				name="message" id="message"> <label for="cloneemail">cloneemail</label>
			<input type="text" name="cloneemail" id="cloneemail"> <input
				type="submit" id="join_proof" value="Send Email">
		</form>
	</div>
	<header>
			<jsp:include page="header.jsp"/>
		</header>
	<div id="sub">
		<div id="mypage">
			<div class="mypage_menu" id="mypage_menu1">
				<a href="${cp}/user/usergoal">목표보기</a>
			</div>
			<div class="mypage_menu" id="mypage_menu2">
				<a href="${cp}/user/usermyinfo">정보 수정하기</a>
			</div>
			<div class="mypage_menu" id="mypage_menu3">
				<a href="${cp}/user/userwithdraw">회원 탈퇴</a>
			</div>
		</div>

		<div class="modify_container">
			<div class="modify_wrap">
				<div class="modify_info">
					<div class="modify_input">
						<input class="input_css" type="text" name="userid"
							value="아이디 : ${loginUser.userid}" readonly> <input
							class="input_css" type="text" name="userid"
							value="생년월일 : ${loginUser.userdob} " readonly> <input
							class="input_css" type="text" name="userid"
							value="이름 : ${loginUser.username}" readonly>
					</div>
				</div>
				<div class="form_wrap">
					<form name="modifyForm" class="modifyForm" action="${cp}/user/usermyinfook"
						method="post" onsubmit="return sendit()">
						<div class="input_box">
							<div class="tit">비밀번호</div>
							<div class="input">
								<input class="input_css" type="password" id="userpw"
									name="password" value="${loginUser.userpw}" onkeyup="pwcheck()">
							</div>
						</div>
						<div class="input_box">
							<div class="tit">비밀번호 확인</div>
							<div class="input">
								<input class="input_css" type="password" id="userpw_re"
									value="${loginUser.userpw}" onkeyup="pwcheck()">
							</div>
							<div id="txt_box">
								<span id="pwtxt">대문자+소문자+특수문자의 8글자 이상 문자열</span>
							</div>
						</div>
						<div class="input_box">
							<div class="tit">이메일</div>
							<div class="input">
								<input class="input_css" type="text" id="useremail"
									value="${loginUser.useremail}" name="useremail">
							</div>
							<div class="modify_btns">
								<input type="button" class="btn_bk btn_sm input_css"
									id="modify_proof_btn" onclick="proof()" value="인증번호 받기" /> <span
									id="proofok">&nbsp;</span>
							</div>
							<div id="email_box" class="input">
								<input class="input_css" type="text" id="emailchk" value=""
									name="proofcheck" placeholder="인증번호를 입력해주세요!">
							</div>
							<div class="modify_btns">
								<input type="button" id="modify_proofcheck_btn"
									class="btn_bk btn_sm input_css" onclick="proofcheckaction()"
									value="이메일 인증하기" />
							</div>
							<span id="proofok">&nbsp;</span>
						</div>
						<div class="input_box">
							<div class="tit">휴대전화</div>
							<div class="input">
								<input class="input_css" type="number" name="userphone"
									value="${loginUser.userphone}">
							</div>
						</div>
						<div class="input_box">
							<div class="tit">주소입력</div>
							<div class="input">
								<input class="input_css" type="text" id="sample6_postcode"
									value="${loginUser.zipcode}" name="zipcode" readonly
									onclick="sample6_execDaumPostcode()">
							</div>
							<div class="modify_btns">
								<input type="button" class="btn_bk btn_sm input_css"
									onclick="sample6_execDaumPostcode()" value="우편번호 찾기" />
							</div>
							<div class="input">
								<input class="input_css" type="text" id="sample6_address"
									value="${loginUser.addr}" name="addr" readonly>
							</div>
							<div class="input">
								<input class="input_css" type="text" id="sample6_detailAddress"
									value="${loginUser.addrdetail}" name="addrdetail">
							</div>
							<div class="input">
								<input class="input_css" type="text" id="sample6_extraAddress"
									value="${loginUser.addretc}" name="addretc" readonly>
							</div>
						</div>
						<div class="modify_btns bottom">
							<input type="submit" class="btn_sm input_css" value="수정 완료">
							<input type="button" class="btn_bk btn_sm" value="수정 취소">
						</div>
					</form>
				</div>
				<div>
			<a id= "background_img" href="#sub" ></a>
		</div>
			</div>
		</div>
	</div>
</body>
<script src='${cp}/resources/js/todo.js' type="text/javascript"></script>
<script>
	var cp = '${cp}';

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
	
	
	// 주소 찾기 
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
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
</script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</html>