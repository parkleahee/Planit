package com.planit.chatfrontcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.planit.dao.ChatDAO;

// 웹소켓은 http가 아닌 ws프로토콜을 사용!

@ServerEndpoint("/ChatingServer") 
// 해당 요청명으로 접속하는 클라이언트를 이 클래스가 처리
// 요청명이 /ChatingServer이므로 이 웹소켓에 접속하기 위한 url은 ws://호스트:포트번호/컨텍스트루트/ChatingServer
public class ChatServer{
	
	ChatDAO cdao = new ChatDAO();
	
	private static Set<Session> clients
		= Collections.synchronizedSet(new HashSet<Session>());
	private static HashMap<String, String> loginUserChat = new HashMap<String, String>(); //세션아이디, 로그인 유저
	private static HashMap<String, Session> loginUserSession = new HashMap<String, Session>();//세션아이디 유저 세션
	private static HashMap<String, Boolean> sessionFristChat = new HashMap<String, Boolean>(); //해당 세션의 첫번째 채팅인지 확인하기
	private static HashMap<Integer,ArrayList<String>> chatsessionid= new HashMap<Integer, ArrayList<String>>(); // 채팅방 번호, 세션 아이디
	private static HashMap<Integer,ArrayList<String>> chatmembers = new HashMap<Integer, ArrayList<String>>(); //채팅방 번호 , 유저 아이디
//	첫번째 채팅일 경우 ->  채팅방 번호와 유저아이디를 받아온다 - > 채팅방 번호와 세션 아이디를 저장 
	// 새로 접속한 클라이언트의 세션을 저장할 컬렉션 생성
	//synchronizedSet() 메서드는 멀티 스레드 환경에서도 안전한 Set컬렉션을 생성(여러 클라이언트가 동시에 접속해도 문제가 발생하지 않도록 동기화)

@OnOpen // 클라이언트 접속 시 실행 / 클라이언트가 접속했을 때 실행할 메서드 정의
public void onOpen(Session session) {
	System.out.println("웹소켓 연결 : " + session.getId());
	clients.add(session); // 세션 추가
	sessionFristChat.put(session.getId(), true);
}	

@OnMessage // 메시지를 받으면 실행 
// 클라이언트로부터 메시지를 받았을 때 실행할 메서드를 정의
public void onMessage(String message, Session session) throws IOException{ // 클라이언트가 보낸 메시지와 클라이언트와 연결된 세션이 매개변수로 넘어온다.
	System.out.println("메시지 전송 : " + session.getId() + ":" + message); // message 1:apple => 방번호 : 유저아이
	System.out.println(message.split(":")[0]);
	int chatroomnum = Integer.parseInt(message.split(":")[0]); //채팅방 번호 추출
	if (sessionFristChat.get(session.getId())) { // 해당 메시지가 open시 발생되는 메시지인지 확인
		String userid = message.split(":")[1]; // 유저아이디 추가
		loginUserChat.put(session.getId(),userid); //세션아이디,로그인 유저아이디 => 로그인 유저와 세션 아이디를 동기화
		loginUserSession.put(session.getId(),session); //세션아이디, 세션아이디
		sessionFristChat.put(session.getId(), false); // 첫번째 메시지가 아니라면 false
		if(chatmembers.get(chatroomnum)==null) {
			//서버 가동이후 해당 채딩방에 들어온 첫번째 맴버이므로 데이터베이스에서 가져온 채팅방 유저들을 세팅
			ArrayList<String> members = cdao.getchatmembers(chatroomnum);
			ArrayList<String> sessionid = new ArrayList<String>();
			sessionid.add(session.getId());
			chatmembers.put(chatroomnum, members);
			chatsessionid.put(chatroomnum, sessionid);
		}else {
			chatsessionid.get(chatroomnum).add(session.getId());
		}
	}
	else {
		
		message = message.substring(chatroomnum+"".length()+1);
		ArrayList<String> chatmemberList = chatsessionid.get(chatroomnum);
		//database에 등록하
		//채팅방 번호에 있는 사람들 목록 넘겨주기 /채팅방 번호 넘겨주기 / 유저 아이디 넘겨주기->
		//if 채팅방 번호에 있는 사람들 목록이면서 유저아이디가 아니면to에 등록 유저 아이디는 from에 등록
		String loginUser = loginUserChat.get(session.getId());
		System.out.println(chatmemberList.size()+"맴버리스트사이즈");
		if(cdao.sendChat(message,chatroomnum,chatmembers.get(chatroomnum),loginUser)) {
			synchronized(clients) { // 동기화 블록
				//for(Session client : clients) { // 모든 클라이언트에 메시지 전달 
			//		if(.equals(session)) {// 메시지를 보낸 클라이언트는 제외하고 전달
		//			if()client.equals 채팅방에 참여한 사람일 경우
						for (int j = 0; j<chatmemberList.size(); j++) {			
				//			System.out.println(client.getId() +"클라이언트 아이디");
							System.out.println(session.getId() +"세션 아이디");
							System.out.println(chatmemberList.get(j) +"맴버리스트아이 아이디");
							if (!session.getId().equals(chatmemberList.get(j))) {		
								loginUserSession.get(chatmemberList.get(j)).getBasicRemote().sendText(loginUser+":"+message); 
//								client.getBasicRemote().sendText(loginUser+":"+message); 
							}
			//           			}
				//	}
				}//세션 아이디 채팅방 넘버 유저아이디 -> 세션아이디에 해당하는 채팅방 넘버 -> 채팅방 맴버에게 보내기
			}
		}
	}
}

@OnClose // 클라이언트와의 연결이 끊기면 실행
public void onClose(Session session) {
	sessionFristChat.remove(session.getId());
	loginUserChat.remove(session.getId());
	clients.remove(session);
	System.out.println("웹소켓 종료 : " + session.getId());
}

@OnError // 에러가 발생했을 때 실행할 메서드 정의
public  void onError(Throwable e) {
	System.out.println("에러 발생");
	e.printStackTrace();
}

public static HashMap<Integer,ArrayList<String>> getChatmembers() {
	return chatmembers;
}

public static void setChatmembers(HashMap<Integer, ArrayList<String>> chatmembers) {
	ChatServer.chatmembers = chatmembers;
}

}

