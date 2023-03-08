<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="main_navnav">
   <div id="m_intro">
      <a id="my_myintro" href="${cp}/schedule/mainview"></a>
   </div>
   <div id="m_chat" class="m_detail">
      <a id="side_account"onclick="makeaccount()">가계부</a>
   </div>
   <div id="m_cont" class="m_detail">
      <a id="sied_diary" onclick="makediary()">일기</a>
   </div>
   <div id="m_logout" class="m_detail">
      <a id="side_culture" onclick="makeculture()">문화</a>
   </div>
   <div id="m_logout" class="m_detail">
      <a id="side_food" onclick="makefood()">음식</a>
   </div>
   <div id="m_logout" class="m_detail">
      <a id="side_memo" onclick="makememo()">메모</a>
   </div>
   <div id="m_logout" class="m_detail">
      <a id="side_map" onclick="makemap()">지도</a>
   </div>

</div>

