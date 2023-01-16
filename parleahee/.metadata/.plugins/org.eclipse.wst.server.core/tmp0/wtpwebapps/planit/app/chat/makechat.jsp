<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${cp}/chat/makeChatRoomOk.tc" method="post" name="makechat">
		<input type="text" name="chatRoomName">
		<input type="text" name="chatMember">
		<input type="submit" value = "만들기">
	</form>
		<form action="${cp}/chat/makeChatRoomOk.tc" method="post" name="makechat">
		<input type="text" name="chatRoomNum">
		<input type="submit" value = "채팅방 참가하기">
	</form>
</body>
</html>