<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
<link href='${cp}/resources/css/cont_detail.css' rel='stylesheet' />
<title>Board</title>
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
		</header>
		<div id="sub">
			<div class="admin_cont">
			<table class="title">
				<tr class="title_word">
					<td><h3>문의 게시판</h3></td>
				</tr>
			</table>
			<form class="contactForm">
				<table border="1" style="border-collapse: collapse;">
					<tr height="30px">
						<th width="150px">제 목</th>
						<td>${contactDetail.contacttitle}</td>
					</tr>
					<tr height="30px">
						<th width="150px">작성자</th>
						<td>${contactDetail.userid}</td>
					</tr>
					<tr height="300px">
						<th width="150px">내 용</th>
						<td>${contactDetail.contactcontents}</td>
					</tr>
					<tr height="30px">
						<th width="150px">작성시간</th>
						<td>${contactDetail.contactdate}</td>
					</tr>
				</table>
				<table class="btn_area">
					<tr valign="middle">
						<td><a id="list_btn" href="${cp}/admin/contactlist">목록</a>
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	</div>
</body>
</html>










