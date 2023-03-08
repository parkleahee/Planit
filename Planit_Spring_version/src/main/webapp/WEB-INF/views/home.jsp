<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="cp" scope="session"/>
<html>
<html lang="ko">
  <head>
  <meta charset="utf-8">
    <title>Plan.It</title>
    <style>
        *{
          font-family: '123RF';
          font-size: 50px;
          color: white;
          }
       #index_ment{ 
             width: 30%;
             height: 200px;
             background: url("${cp}/resources/images/index_ment.png") no-repeat;
             background-size: contain;
             margin-top: 5%;
             margin-left: 8%;
            }
       #index_logo{ 
               background: url("${cp}/resources/images/logo.png") no-repeat;
               background-size: contain;
               margin-left : 26%;
               width: 50%;
               height: 300px; 
               /* float: left; */
        }
    </style>
   <link rel="stylesheet" href="${cp}/resources/css/index_join_login.css" />
  </head>
  <body>
         <video muted autoplay loop>
              <source src="${cp}/resources/images/v.mp4" type="video/mp4" >
               <strong>Your browser does not support the video tag.</strong>
         </video>
   <div id="black_box"></div>
    <div class="jb-box">
    <div id = "index_ment"></div>
   <div id = "index_logo"></div>
   <div id="index_btn">
         <div id="index_login"><a href="${cp}/user/loginuser">로그인</a></div>
         <br>
         <div id="index_join"><a href="${cp}/user/joinuser">회원가입</a></div>
   </div>
    </div>
  </body>
</html>
