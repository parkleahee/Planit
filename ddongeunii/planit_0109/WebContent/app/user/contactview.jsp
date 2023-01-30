<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>문의하기</title>
    <link href='${cp}/css/main_nav.css' rel='stylesheet' />
</head>
<style>

	.goalset1 {
	display: none;
}

.goalset2 {
	display: none;
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
    h3{
    text-align: left;
    position: relative;
    padding-left: 10px;
    padding-bottom: 10px;
    font-size: 22px;
    color: #000;
    display: block;
    font-weight: bold;
    }

    h3+p{
    text-align: left;
    padding-left: 10px;
    }

    li,ul {
        list-style: none;
    }

    ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}

.tb_lst > li::after {
    display: block;
    clear: both;
    content: "";
}

    .tb_lst{
        margin-top: 20px;
        border-top: 4px solid #000;
    }

    .tb_lst>li{
        display: table;
    position: relative;
    width: 100%;
    border-bottom: 1px solid #e4e4e4;
    }

.tb_lst .in_td:first-child {
    width: 120px;
    padding: 0;
    border-right: 1px solid #e4e4e4;
    vertical-align: top;
}

    .tb_lst .in_td {
    display: table-cell;
    height: 62px;
    padding: 10px 0 10px 20px;
    box-sizing: border-box;
    font-size: 14px;
    vertical-align: middle;
}

.inpbx {
    position: relative;
    height: 40px;
    border: 1px solid #d4d4d4;
    background: #fff;
    vertical-align: top;
    box-sizing: border-box;
}

.tb_lst .inpbx input, .tb_lst .inptxtbx textarea{
    padding: 0 18px;
    font-size: 14px;
    font-weight: 600;
    color: #303033;
    line-height: 20px;
}

.inpbx input {
    display: block;
    width: 100%;
    height: 100%;
    padding: 0 14px;
    font-size: 15px;
    color: #1a1a1a;
    border: 0;
    background: transparent;
    outline: none;
    box-sizing: border-box;
}

.tb_lst .type{
    display: inline-block;
    font-size: 14px;
    font-weight: 600;
    color: #303033;
    line-height: 60px;
}

input, textarea {
    border: 0;
    border-radius: 0;
    background: transparent;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
}

.tb_lst .cnt_row{
    position: relative;
}

.tb_lst > li{
    display: table;
    position: relative;
    width: 100%;
    border-bottom: 1px solid #e4e4e4;
}

.tb_lst .inptxtbx {
    padding-right: 12px;
}

.inptxtbx textarea {
    display: block;
    overflow: auto;
    width: 100%;
    padding: 14px;
    border: 1px solid #ccc;
    background: transparent;
    font-size: 15px;
    line-height: 20px;
    color: #1a1a1a;
    outline: none;
    box-sizing: border-box;
}

.btn_bx .btn_black{
    width: 100px;
    height: 40px;
}

.btn_black {
    display: inline-block;
    min-width: 82px;
    padding: 0 17px;
    border: 1px solid #303033;
    background: #303033;
    font-size: 13px;
    color: #fff;
    line-height: 38px;
    box-sizing: border-box;
}

a, button {
    outline: none;
}

button {
    border: 0;
    background: transparent;
    cursor: pointer;
}

.txt_notice {
    margin-left: 40px;
    text-align: left;
    font-size: 13px;
    margin-top: 16px;
    font-weight: 500;
    color: #000;
}
.container{
    width: 60%;
    margin: 0 auto;
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
									action="${cp}/user/addgoalokaction.tc"
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
									<input type="submit" value="목표 설정">
									<input type="button" id="close"
										onclick="location.href='${cp}/app/schedule/mainview.jsp';">돌아가기</input>
								</form>
							</div>
						</div>
					</div>
					<c:choose>
					<c:when test="${gdto.goal != null}">
					<div class="goalset1">
						<div id="goal_tit1"><span>${gdto.goal}</span></div>
						<div>
							<progress id="progress1" value="0" max="100"></progress>
						</div>
						<input type="button" name="getgoal_btn1" id="getgoal_btn1"
							value="오늘 목표 달성">
					</div>
					<script>
					alert('목표 설정 성공 ! 목표 설정은 2개까지 가능합니다 !');
					document.getElementsByClassName("goalset1")[0].style.display = 'block';
					</script>
					</c:when>
					<c:otherwise>
						<c:if test="" >
						</c:if>
					</c:otherwise>
					</c:choose>
					<div class="goalset2">
						<div id="goal_tit2"><span></span></div>
						<div>
							<input id="goalday_1" type="hidden">
							<progress id="progress2" value="0" max="100"></progress>
						</div>
						<input type="button" name="getgoal_btn2" id="getgoal_btn2"
							value="오늘 목표 달성">
					</div>
				</div>
			</div>

		</header>
        <div id="sub">
            <div class="container">

                <div id="contact_info">
                    <h3>1:1문의쓰기</h3>
                    <p>산업안전보건법 제 41조 시행령에 근거하여 2018년 10월 18일 부터 산업안전보건법에 따라 고객응대근로자 보호조치를 시행하고 있습니다.
                        고객응대근로자에게 폭언, 폭행 등을 하지 말아주세요.</p>
                    </div>
                    <form id="contact_info" name="contact" method="post" action="${cp}/user/usercontactok.us.tc">
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
                                        <input type="text" name="u_title" id="u_title" placeholder="제목을 입력하세요.">
                                    </div>
                                </div>
                            </li>
                            <li class="cnt_row">
                                <div class="in_td">
                                    <label for="u_content" class="type">내용</label>
                                </div>
                                <div class="in_td">
                                    <div class="inptxtbx">
                                        <textarea name="u_content" id="u_content" cols="1" rows="5" placeholder="내용을 입력하세요">
                                        </textarea>
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
    <script>
	var cp = '${cp}';
</script>
	<script src='${cp}/js/main_nav.js' type="text/javascript"></script>	
    </html>