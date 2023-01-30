<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
	<script>
		history.replaceState({},null, location.pathname);
		const webSocket=new WebSocket("ws://localhost:9090/planit/ChatingServer"); 	
		function chksok() {
			console.log(webSocket);
		}
		// 선언해 둔 웹소켓 접속 url 뒤에 요청명을 덧붙여 웹소켓 객체를 생성
		var chatWindow, chatMessage, chatId, chatroomnum;
		// 채팅창이 열리면 대화창, 메시지 입력창, 대화명 표시란으로 사용할 DOM객체 저장
		
		window.onload = function() {
			chatWindow = document.getElementById("chatWindow");
			chatMessage = document.getElementById("chatMessage");
			chatId = "${loginUser.userid}";
			chatroomnum =${chatroomnum};
		}

		// 메시지 전송과 대화 종료가 되지 않는 상태임
		// onmessage에서 if문에 )를 빠뜨려 제대로 진행이 되지 않았던 문제점 발견
		// 해결
		// 문제 해결이 어려울 시 console.log(변수명);을 사용함으로써 문제점을 찾아가는 방법이 있다.
		// 귓속말 구현은 성공했으나 일반 대화가 되지 않음.
		// 친구와의 대화를 구현해야 함
		// 메시지 전송
		function sendMessage(){//클라이언트의 메시지를 전송하는 메서드
			// 대화창에 표시  
			chatWindow.innerHTML += "<div class='myMsg'>" + chatMessage.value + "</div>"
			webSocket.send(chatroomnum + ':' + chatMessage.value); // 서버로 전송// 채팅방번호 + 메시
			chatMessage.value=""; // 메시지 입력창 내용 지우기, 편의를 위해 입력상자의 내용을 비움
			chatWindow.scrollTop = chatWindow.scrollHeight; // 대화창 스크롤, 스크롤바를 항상 아래로 내려줌
		}
		
		// 서버와의 연결 종료
		function disconnect(){
			webSocket.close();
		}
		
		// 엔터키 입력 처리, 엔터가 눌리면 sendMessage()를 호출
		function enterKey(){
			if(window.event.keyCode == 13){ // 13은 ENTER키의 코드값
				sendMessage();
			}
		}
		// 각 상황은 이벤트로 전달되므로 이벤트별 리스너가 감지하여 메서드를 호출, 호출 시 인수로 이벤트 객체가 전달 
		// 웹소켓 서버에 연결됐을 때 실행
 			webSocket.onopen = function(event) {
			/* chatWindow.innerHTML += "웹소켓 서버에 연결되었습니다.<br/>"; */
 			webSocket.send("${chatroomnum}:${loginUser.userid}");
		}; 
		
		//웹소켓이 닫혔을 때(서버와의 연결이 끊겼을 때 실행) 실행
		webSocket.onclose = function(event){
			chatWindow.innerHTML += "웹소켓 서버가 종료되었습니다<br/>";
		};
		
		//에러 발생 시 실행
		webSocket.onerror = function(event){
			alert(event.data);
			chatWindow.innerHTML += "채팅 중 에러가 발생하였습니다<br/>";
		};
		
		//메시지를 받았을 때 실행, 서버로부터 메시지를 받았을 때 처리
		webSocket.onmessage = function(event){
			var message = event.data.split(":"); // 대화명과 메시지 분리, sendMessage()에서 메시지를 '대화명|메시지'로 가공했으므로 split()메서드로 다시 분리
			var sender = message[0]; // 보낸사람의 대화명
			var content = message[1]; // 메시지 내용
			if(content != ""){ // 메시지가 빈 값인지 아닌지 확인
				if(content.match("/")){ // 귓속말, 메시지 내용에 /가 포함되어 있다면 귓속말로 별도로 처리
					if(content.match(("/" + chatId))){// 자신에게 보낸것인지 확인
						// 귓속말 명령어 부분을 '[귓속말] : ' 문자열로 대체 후 채팅창에 출력
						var temp = content.replace(("/" + chatId), "[귓속말] : ");
						chatWindow.innerHTML += "<div>" + sender + "" + temp + "</div>";
				}
				
				}else{ // 일반 대화, /가 없다면 일반 메시지므로 대화창에 그대로 표시
				chatWindow.innerHTML += "<div>" + sender + " : " + content + "</div>";
				// 현재 전체 채팅이 되지 않아 다시 봐야 할 듯 -> 중괄호의 위치가 틀려 제대로 진행되지 않아
				// 문제점 발견후 수정완료
			}
			}
			chatWindow.scrollTop = chatWindow.scrollHeight;
		};
	</script>
	
	<style>
		#chatWindow{
			border:1px;
			solid black;
			width:270px;
			height: 310px;
			overflow: scroll;
			padding: 5px;
		}
		
		#chatMessage{
			width:236px;
			height: 30px;
		}
		
		#sendBtn{
			height: 30px;
			position: relative;
			top: 2px;
			left: -2px;
		}
		
		#closeBtn{
			margin-bottom: 3px;
			position: relative;
			top: 2px;
			left: -2px;
		}
		
		#chatId{
			width: 158px;
			height: 24px;
			border: 1px;
			solid #AAAAAA;
			background-color: #EEEEEE;
		}
		
		.myMsg{
			text-align: right;
		}
	</style>
</head>
<body> <!-- 대화창 구조 -->
	대화명 : <input type="text" id="chatId" value="${param.chatId}" readonly />
	<button id="closeBtn" onclick="disconnect();">채팅 종료</button>
	<div id="chatWindow"></div>
	<div>
		<input type="text" id="chatMessage" onkeyup="enterKey();">
		<button id="sendBtn" onclick="sendMessage();">전송</button>
		<button id="sendBtn" onclick="chksok()">소켓 오픈</button>
	</div>
</body>
</html>