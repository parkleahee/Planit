<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹소켓 채팅</title>
</head>
<body>
	<script>
		function chatWinOpen(){ // 채팅창을 팝업으로 
			var id = document.getElementById("chatId"); // 대화명 입력상자의 DOM 객체를 얻어옴
			if(id.value == ""){ // 대화명이 입력되지 않았으면 경고
				alert("대화명을 입력 후 채팅창을 열어주세요!");
				id.focus();
				return;
			}
			window.open("ChatWindow.jsp?chatId="+id.value,"","width=320, height=400");
			// 문제가 없다면 대화명을 매개변수로 전달해 채팅창을 띄움
			id.value=""; // 새로운 대화명을 입력할 수 있도록 기존의 내용을 지워 줌
		}
	</script>
	<h2>채팅창</h2>
	대화명 : <input type="text" id="chatId"/> <!-- 대화명 입력상자 -->
	<button onclick="chatWinOpen();">채팅 참여</button> <!-- 채팅참여 버튼을 누르면 chatWinOpen() 함수를 호출 -->
</body>
</html>