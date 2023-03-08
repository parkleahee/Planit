<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
<link href='${cp}/resources/css/admin.css' rel='stylesheet' />
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
						<td><h3>문의 내역</h3></td>
					</tr>
					<tr align="right" valign="middle">
						<td>글 개수 : ${contact.totalCnt}</td>
					</tr>
				</table>

				<table class="list">
					<tr align="center" valign="middle">
						<th width="8%">번호</th>
						<th width="50%">제목</th>
						<th width="15%">작성자</th>
						<th width="17%">날짜</th>
					</tr>
					<c:choose>
						<c:when test="${contact.contactList != null and contact.contactList.size()>0 }">
							<c:forEach items="${contact.contactList}" var="contactt">
								<tr>
									<td>${contactt.contactnum}</td>
									<td><a
										href="${cp}/admin/contactview?contactnum=${contactt.contactnum}&page=${contact.page}&q=${contact.keyword}">${contactt.contacttitle}</a></td>
									<td>${contactt.userid}</td>
									<td>${contactt.contactdate}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5" style="text-align: center; font-size: 20px;">등록된
									게시글이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				<br>

				<table class="pagination">
					<tr align="center" valign="middle">
						<td><c:if test="${contact.startPage != 1}">
								<a href="">&lt;</a>
							</c:if> <c:forEach var="i" begin="${contact.startPage }" end="${contact.endPage}">
								<c:if test="${i == contact.page}">
									<span class="nowPage">${i}</span>
								</c:if>
								<c:if test="${i != contact.page }">
									<a href="${cp}/admin/contactlist?page=${i}">${i}</a>
								</c:if>
							</c:forEach> <c:if test="${contact.endPage != contact.totalPage}">
								<a href="">&gt;</a>
							</c:if></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>