function chksok() {
         console.log(webSocket);
      }



function sendMessage(){//클라이언트의 메시지를 전송하는 메서드
   // 대화창에 표시  
   chatWindow.innerHTML += "<div class='myMsg'>" + chatMessage.value + "</div>"
   webSocket.send(chatroomnum + ':' + chatid + ':' + chatMessage.value); // 서버로 전송// 채팅방번호 + 메시
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
      webSocket.send(chatroomnum +":"+chatid);
}; 

//웹소켓이 닫혔을 때(서버와의 연결이 끊겼을 때 실행) 실행
webSocket.onclose = function(event){
};

//에러 발생 시 실행
webSocket.onerror = function(event){
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
            var temp = content.replace(("/" + chatid), "[귓속말] : ");
            chatWindow.innerHTML += "<div>" + sender + "" + temp + "</div>";
      }
      
      }else{ // 일반 대화, /가 없다면 일반 메시지므로 대화창에 그대로 표시
      chatWindow.innerHTML += "<div class='yourMsg'>" + sender + " : " + content + "</div>";
      // 현재 전체 채팅이 되지 않아 다시 봐야 할 듯 -> 중괄호의 위치가 틀려 제대로 진행되지 않아
      // 문제점 발견후 수정완료
   }
   }
   chatWindow.scrollTop = chatWindow.scrollHeight;
};

/*function chatjoin(i){
   const xhr = new XMLHttpRequest();
   const chatroomnum = document.getElementById("chatroom" + i);
   if(chatroomnum.value == ""){
      alert("검색된 결과가 없습니다.");
      return false;
   }
   
   xhr.onreadystatechange = function(){
      if(xhr.readyState == 4){
         if(xhr.status == 200){
            let txt = xhr.responseText;
            txt = txt.trim();
            console.log(txt);
            if(txt == "O"){
               alert("채팅방에 참여를 시작합니다..!");
               $("#contents_box").load(location.href + "#contents_box");
            }
            else{
               alert("채팅방 참여에 실패하셨습니다..!");
               $("#contents_box").load(location.href + "#contents_box");
            }
         }
      }
   }
   
   xhr.open("GET",cp+"/chat/chatjoin.tc?chatroomnum="+chatroomnum.value,true);
   xhr.send();
}*/