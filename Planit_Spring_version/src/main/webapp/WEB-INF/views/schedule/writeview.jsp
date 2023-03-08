<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%-- <link rel="stylesheet" href="${cp}/css/style.css"> --%>
    <link rel="stylesheet" href="${cp}/resources/css/writeViewTotal.css">
    <link href='${cp}/resources/css/main_nav.css' rel='stylesheet'/>
<style>
#main_navnav{
	box-sizing: border-box;
	float: left;
	/*background: url(./왼쪽\ 메뉴바\ 배경.png);*/
	/* border: 1px solid red; */
	height: 100vh;
	text-align: center;
	align-items: center;
	justify-content: center;
	justify-items: center;
	position: fixed;
    top: 5%;
    left: 0;
    right: 0;
    width: 250px;
}
</style>
</head>
<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
    <div id="container">
        <header>
        	<jsp:include page="header_write.jsp" />
      	</header>
        <div id="container_cate">
            <form action="${cp}/schedule/categorySave" method="post" name="categoryForm" onsubmit="return cate_sendit();">
                <div id="group">
                    <div class="group-title">
                        <label for="term_cont" id="term" onclick="changeterm()">
                            기간
                        </label>
                        <div id="term_cont" onclick="changeterm()">
                            <input type="text" name="term_content1" id="term_content1" value="${param.cont1_date1}" readonly>
                            <span id="term_content">~</span>
                            <input type="text" name="term_content2" id="term_content2" value="${param.cont1_date2}" readonly>
                        </div>
                    </div>
                    <div class="group-title">
                        <div class="dropdown">
                            <div id="authority" class="dropbtn">
                                    권한    
                            </div>
                             <input type="text" name="authority_content" id="authority_content" value="${param.authority}" readonly>
                            <div class="dropdown-content">
                                <span class="hover" onclick="alone()">혼자하기</span>
                                <span class="hover" onclick="withsee()">함께보기</span>
                                <span class="hover" onclick="withchange()">함께수정</span>
                            </div>
                        </div>
                    </div>
                    <div class="group-title">
                        <div id="friend">
                            함께할 친구
                        </div>
                        <div class="group_content" id="friend_detail">
                           <c:choose>
                              <c:when test="${result != null}">
                              <%-- <c:set var="result" value=""/>
                                 <c:forEach var="i" begin="0" end="${fn:length(paramValues.friends) -1}">
                                    <c:set var="ff" value="${paramValues.friends[i]}" />
                                    <c:set var="fri" value="${result += ff}"/> 
                                     <input type="text" name="friend_content" id="friend_content" value="${result}">
                                 </c:forEach> --%>
                                  <input type="text" name="friend_content" id="friend_content" value="${result}" readonly>
                                  <!-- serwriteview에서 먼저 체크된 친구들 id  -->
                                  <%-- <c:forEach items="${checkedfriendList}" var="i">
                                  <input type="hidden" name= "setview_friend_content" value="${i.userid}" readonly>
                                  </c:forEach> --%>
                              </c:when>
                              <c:otherwise>
                                  <input type="text" name="friend_content" id="friend_content" value="없음" readonly>                                 
                              </c:otherwise>
                           </c:choose>
                            <div class="hidden" id="friend_addlist">
                               <p class="hidden" id="hiddenmessage">더 이상 등록할 친구가 없습니다.</p>
                               <c:choose>
                                  <c:when test="${result != null}">
                                     	<c:if test="${checkedfriendList != null and checkedfriendList.size() > 0}">
                                     		<c:forEach var="i" begin="0" end="${checkedfriendList.size()-1}" >
                                     		<div id="friendDiv${i}">
                                     		 	<label for="friend${i}" id=friendlabel${i}>${checkedfriendList[i].username}</label>
			                                  	<input type="checkbox" class="modi_friend" id="friend${i}" name= "friends" value="${checkedfriendList[i].userid}">
		                                    </div>
		                                    </c:forEach>
                                     	</c:if>
                                        <c:if test="${fn:length(nocheckFriendList) > 0}">
                                           <c:forEach var="i" begin="${checkedfriendList.size()}" end="${(fn:length(nocheckFriendList)-1)+checkedfriendList.size()}">
                                           		<c:set var="j">${i-checkedfriendList.size()}</c:set>
                                              <div id="friendDiv${i}">
                                              <label for="friend${i}" id="friendlabel${i}">${nocheckFriendList.get(j).username}</label>
                                              <input type="checkbox" name="friends" id="friend${i}" value="${nocheckFriendList.get(j).userid}">
                                              </div>
                                           </c:forEach>
                                        </c:if>
                                  </c:when>
                                  <c:otherwise>
                                     <c:out value="친구가 없습니다."/>
                                  </c:otherwise>
                               </c:choose>
                               <br>
                             <input type="button" value="추가하기" id="addfriBtn" onclick="addfri()">
                            </div>
                        </div>
                    </div>
                </div>
                <ul id="categories">
                    <li id="titleco">
                        <div style="margin-left: 30px; width: 100%; text-align: center; height: 100px; padding-top:20px">
                            제목 : <input type="text" name="title" id="title" placeholder="스케쥴 제목을 입력해주세요"> 
                            <div id="color_div">
                                스케줄 바 색깔 : <input type="color" name="color" id="color">
                            </div>
                        </div>
                    </li>
                </ul>
                <div class="Btns">
                   <input type="submit" value="작성하기" class="w-btn w-btn-pink">
                   <%-- <a href="${cp}/schedule/temporarysave.tc"><button class="w-btn w-btn-pink">임시저장</button></a> --%>
                </div>
            </form>
            <!--1. 가계부  -->
         <div id= "cate_account" class="cate_account cate_hidden">
            <table class="accinner">
               <thead class="tablehead" style="text-align: center;">
                  <input type="hidden" name="cnt">
                  <input type="hidden" name="">
                  <tr>
                     <th style="border-top-left-radius: 20px;"></th>
                     <th>기간</th>
                     <!-- <th></th> -->
                     <th><input type="date" name="startdate" class="accterm"></th>
                     <th>~</th>
                     <th><input type="date" name="enddate" class="accterm"></th>
                     <th style="border-top-right-radius: 20px;"></th>
                  </tr>
                  <tr>
                     <td colspan="6">&nbsp;</td>
                  </tr>
               </thead>
               <tbody class="body" style="text-align: center;">
               <!--       <input type="hidden" name=""> -->
                  <tr>
                     <th></th>
                     <th>날짜</th>
                     <th>내역</th>
                     <th>금액</th>
                     <th>유형</th>
                     <th></th>
                  </tr>
                  
                  <tr id="accrow0">
                     <input type="hidden" name="accRowCnt">
                     <td class="plusbtndiv"><div class="plusbtndiv" style="margin: 0 auto;">
                        <button type="button"></button>
                     </div></td>
                     <td><input type="date" name="accdate" id="accdate" class="accdate"></td>
                     <td><input type="text" name="history" id="history" style="width: 100%;"></td>
                     <td><input type="text" name="amount" id="amount" onkeyup="inputNumberFormat(this)" 
                     onchange="showBalance(this)"></td>
                     <td><input type="text" name="acctype" id="acctype"></td>
                     <td><div>
                        <button type="button"></button>
                     </div></td>
                  </tr>
               </tbody>
               <tfoot class="footer">
                  <tr>
                     <td colspan="6">
                        <textarea name="accmemo" class="accmemo" id="accmemo" cols="20" rows="10" style="border: none;" placeholder="메모"></textarea>
                     </td>
                  </tr>
                  <tr>
                     <th style="border-bottom-left-radius: 20px;">예산</th>
                     <td colspan="2"  style="text-align:right;" ><input type="text" name="budget" id="budget" onkeyup="inputNumberFormat(this)" style="text-align:right; width: 90%;">원</td>
                     <!-- 사용자가 입력하는 칸이아니라 계산해서 보여주는 곳 -->
                     <th>잔액</th>
                     <td><input type="text" name="" id="" readonly></td>
                     <td style="border-bottom-right-radius: 20px;"></td>
                  </tr>
               </tfoot>
            </table>
            <div class="btndiv">
               <button class="xBtn"></button>
            </div>
         </div>
         
         <!--2. 음식 -->
         <div class="cate_food cate_hidden" id="cate_food">
            <div class="inner">
            <input type="hidden" name="cnt">
               <div class="fdinner, fdinner1, fd_header" id="fd_header">
                  <div class="fd_header_lt" id="fd_header_lt">
                     <input type="text" class="fd_time" name="fd_time" id="fd_time" placeholder="시간" >
                  </div>
                  <div class="fd_header_rt" id="fd_header_rt">
                     <span id="star" class = "star">★★★★★
                        <span id="starSpan" class="star" >★★★★★</span>
                        <input type="range" value="1" step="1" min="0" max="10" name="fdstar" oninput="drawStar(this)">
                     </span>
                  </div>
               </div>
               <div class="fdinner, fdinner2, fd_cont" id="fd_cont">
                  <!-- <div class="cate_food_img" id="cate_food_img" name="cate_food_img">
                     <span id="file_imgname">이미지탭 클릭 후 이미지를 추가할 수 있어요!</span>
                     이미지 div 추가될 곳 
                  </div> -->
                  <textarea class="fd_text" name="fd_text" id="fd_text" onkeydown="resize(this)"
                     onkeyup="resize(this)" placeholder="내용" ></textarea> 
               </div>
            <!-- </div> -->
            </div>
            <div class="btndiv">
               <button class="xBtn"></button>
            </div>
               
         </div>
               
         <!--3. 문화 -->
         <div id="cate_cul" class="cate_cul cate_hidden">
            <div class="inner">
               <div id="cul_top" style="position:relative;">
                  <!-- 클론할때마다 value에 "culture-"+cnt를 넘겨야합니다 -->
                  <input type="hidden" name="cnt" value=""> <!-- 카테고리 + CNT -->
                  <input type="text" name="cultitle" id="cultitle" placeholder="제목">
                  <input type="text" name="cultype" id="cultype" placeholder="유형">
                  <input type="text" name="culgenre" id="culgenre" placeholder="장르">
                  <span id="star" class = "star">
                     ★★★★★
                     <span id="starSpan" >★★★★★</span>
                     <input type="range" value="1" step="1" min="0" max="10" name="culstar" oninput="drawStar(this)">
                  </span>
               </div>
               <div id="cul_contents">
                  <div id="culimg"></div>
                  <textarea onkeydown="resize(this)" onkeyup="resize(this)" id="culcontents" placeholder="내용"></textarea>
               </div>
            </div>
            <div class="btndiv">
               <button class="xBtn"></button>
            </div>
            <br>
         </div>
         
         <!-- 4. 다이어리 -->
         <div id="cate_diary" class="cate_diary cate_hidden">
            <div class="inner">
               <div class="head">
                  <input type="hidden" name="cnt">
                  <input type="hidden">
                  <input type="text" name="diadate" id="diadate" placeholder="   2022년  12월  28일  화요일">         
                  <input type="text" name="diaweather" id="diaweather" placeholder="날씨 ">
               </div>
               <hr>
               <div id="body">
                  <div name="diaimg" id="diaimg">
                  </div>
                  <input type="text" name="diatitle" id="diatitle" placeholder="제목 : ">
                  <textarea name="diacont" id="diacont" onkeydown="resize(this)" onkeyup="resize(this)" ></textarea>
               </div>
            </div>
            <div class="btndiv">
               <button class="xBtn"></button>
            </div>
         </div>
         
         <!-- 5. 지도 -->
         <!-- 7. 메모 -->
         <div id="cate_memo" class="cate_memo cate_hidden">
            <div class="inner">
               <div id="memo_contents">
                  <input type="hidden" name="cnt" value=""> <!-- 카테고리 + CNT -->
                  <div id="memo_img"></div>
                  <textarea onkeydown="resize(this)" onkeyup="resize(this)" id="memocontents" placeholder="내용"></textarea>
               </div>
               <br>
            </div>
            <div class="btndiv">
               <button class="xBtn"></button>
            </div>
         </div>     
                    
        </div>
    </div>
</body>

<script type="text/javascript">
window.kakao=window.kakao||{},window.kakao.maps=window.kakao.maps||{},window.daum&&window.daum.maps?window.kakao.maps=window.daum.maps:(window.daum=window.daum||{},window.daum.maps=window.kakao.maps),function(){function a(){if(E.length){t(I[E.shift()],a).start()}else e()}function t(a,t){var e=document.createElement("script");return e.charset="utf-8",e.onload=t,e.onreadystatechange=function(){/loaded|complete/.test(this.readyState)&&t()},{start:function(){e.src=a||"",
document.getElementsByTagName("head")[0].appendChild(e),e=null}}}function e(){for(;c[0];)c.shift()();o.readyState=2}var o=kakao.maps=kakao.maps||{};if(void 0===o.readyState)o.onloadcallbacks=[],o.readyState=0;else if(2===o.readyState)return;o.VERSION={ROADMAP:"2212ejo",ROADMAP_SUFFIX:"",HYBRID:"2212ejo",SR:"3.00",ROADVIEW:"7.00",ROADVIEW_FLASH:"200402",BICYCLE:"6.00",USE_DISTRICT:"2212ejo",
SKYVIEW_VERSION:"160114",SKYVIEW_HD_VERSION:"160107"},o.RESOURCE_PATH={ROADVIEW_AJAX:"//t1.daumcdn.net/roadviewjscore/core/css3d/200204/standard/1580795088957/roadview.js",ROADVIEW_CSS:"//t1.daumcdn.net/roadviewjscore/core/openapi/standard/211122/roadview.js"};for(var n,r="https:"==location.protocol?"https:":"http:",s="",i=document.getElementsByTagName("script"),d=i.length;n=i[--d];)if(/\/(beta-)?dapi\.kakao\.com\/v2\/maps\/sdk\.js\b/.test(n.src)){s=n.src;break}i=null;var c=o.onloadcallbacks,E=["v3"],S="",I={v3:r+"//t1.daumcdn.net/mapjsapi/js/main/4.4.8/kakao.js",services:r+"//t1.daumcdn.net/mapjsapi/js/libs/services/1.0.2/services.js",
drawing:r+"//t1.daumcdn.net/mapjsapi/js/libs/drawing/1.2.6/drawing.js",clusterer:r+"//t1.daumcdn.net/mapjsapi/js/libs/clusterer/1.0.9/clusterer.js"},_=function(a){var t={};return a.replace(/[?&]+([^=&]+)=([^&]*)/gi,function(a,e,o){t[e]=o}),t}(s);S=_.appkey,S&&(o.apikey=S),o.version="4.4.8";var R=_.libraries;if(R&&(E=E.concat(R.split(","))),"false"!==_.autoload){for(var d=0,l=E.length;d<l;d++)!function(a){a&&document.write('<script charset="UTF-8" src="'+a+'"><\/script>')}(I[E[d]]);o.readyState=2}o.load=function(t){switch(c.push(t),o.readyState){case 0:o.readyState=1,a();break
;case 2:e()}}}();

</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script src="${cp}/resources/js/writeview.js"></script>
<script>

</script>


</html>