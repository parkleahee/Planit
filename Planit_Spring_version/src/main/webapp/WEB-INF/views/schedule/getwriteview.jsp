<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="${cp}/resources/css/writeViewTotal.css">
<link href='${cp}/resources/css/main_nav.css' rel='stylesheet' />
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
<script type="text/javascript">
window.kakao=window.kakao||{},window.kakao.maps=window.kakao.maps||{},window.daum&&window.daum.maps?window.kakao.maps=window.daum.maps:(window.daum=window.daum||{},window.daum.maps=window.kakao.maps),function(){function a(){if(E.length){t(I[E.shift()],a).start()}else e()}function t(a,t){var e=document.createElement("script");return e.charset="utf-8",e.onload=t,e.onreadystatechange=function(){/loaded|complete/.test(this.readyState)&&t()},{start:function(){e.src=a||"",
document.getElementsByTagName("head")[0].appendChild(e),e=null}}}function e(){for(;c[0];)c.shift()();o.readyState=2}var o=kakao.maps=kakao.maps||{};if(void 0===o.readyState)o.onloadcallbacks=[],o.readyState=0;else if(2===o.readyState)return;o.VERSION={ROADMAP:"2212ejo",ROADMAP_SUFFIX:"",HYBRID:"2212ejo",SR:"3.00",ROADVIEW:"7.00",ROADVIEW_FLASH:"200402",BICYCLE:"6.00",USE_DISTRICT:"2212ejo",
SKYVIEW_VERSION:"160114",SKYVIEW_HD_VERSION:"160107"},o.RESOURCE_PATH={ROADVIEW_AJAX:"//t1.daumcdn.net/roadviewjscore/core/css3d/200204/standard/1580795088957/roadview.js",ROADVIEW_CSS:"//t1.daumcdn.net/roadviewjscore/core/openapi/standard/211122/roadview.js"};for(var n,r="https:"==location.protocol?"https:":"http:",s="",i=document.getElementsByTagName("script"),d=i.length;n=i[--d];)if(/\/(beta-)?dapi\.kakao\.com\/v2\/maps\/sdk\.js\b/.test(n.src)){s=n.src;break}i=null;var c=o.onloadcallbacks,E=["v3"],S="",I={v3:r+"//t1.daumcdn.net/mapjsapi/js/main/4.4.8/kakao.js",services:r+"//t1.daumcdn.net/mapjsapi/js/libs/services/1.0.2/services.js",
drawing:r+"//t1.daumcdn.net/mapjsapi/js/libs/drawing/1.2.6/drawing.js",clusterer:r+"//t1.daumcdn.net/mapjsapi/js/libs/clusterer/1.0.9/clusterer.js"},_=function(a){var t={};return a.replace(/[?&]+([^=&]+)=([^&]*)/gi,function(a,e,o){t[e]=o}),t}(s);S=_.appkey,S&&(o.apikey=S),o.version="4.4.8";var R=_.libraries;if(R&&(E=E.concat(R.split(","))),"false"!==_.autoload){for(var d=0,l=E.length;d<l;d++)!function(a){a&&document.write('<script charset="UTF-8" src="'+a+'"><\/script>')}(I[E[d]]);o.readyState=2}o.load=function(t){switch(c.push(t),o.readyState){case 0:o.readyState=1,a();break
;case 2:e()}}}();
</script>
<body>
<c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/');
		</script>
	</c:if>
	<!--  -->
	<div id="container">
		<header>
			<jsp:include page="header_write.jsp" />
		</header>
		<div id="container_cate">
			<div id="group">
				<div class="group-title">
					<label for="term_cont" id="term"> 기간 </label>
					<div id="term_cont">
						<input type="text" name="term_content1" id="term_content1"
							value="${scbar.startdate}" readonly> <span
							id="term_content">~</span> <input type="text"
							name="term_content2" id="term_content2" value="${scbar.enddate}"
							readonly>
					</div>
				</div>
				<div class="group-title">
					<div class="dropdown">
						<div id="authority" class="dropbtn">권한</div>
						<input type="text" name="authority_content" id="authority_content"
							value="${authority}" readonly>
					</div>
				</div>
				<div class="group-title">
					<div id="friend">함께할 친구</div>
					<div class="group_content" id="friend_detail">
						<c:choose>
							<c:when test="${friends != null}">
								<input type="text" name="friend_content" id="friend_content"
									readonly value="${friends}">
							</c:when>
							<c:otherwise>
								<input type="text" name="friend_content" id="friend_content"
									readonly value="X">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<ul id="categories">
				<li id="titleco">
					<div
						style="margin-left: 30px; width: 100%; text-align: center; height: 100px; padding-top: 20px">
						제목 : <input type="text" name="title" id="title"
							placeholder="스케줄 제목을 입력해주세요" value="${scbar.title}" readonly>
						<div id="color_div">
							스케줄 바 색깔 : <input type="color" name="color" id="color"
								value="${scbar.color}" readonly>
						</div>
					</div>
				</li>
				<!-- 불러온 카테고리들이 들어가야 할 곳 -->
				<c:choose>
					<c:when
						test="${monga.monga != null and fn:length(monga.monga) > 0}">
						<c:forEach var="i" begin="0" end="${fn:length(monga.catnum) - 1}"
							step="1">
							<c:set var="result" value="${monga.monga[i]}" />
							<c:choose>
								<c:when test="${monga.catnum[i] == 1}">

									<!-- 가계부 카테고리 -->

									<li class="cate">
										<div class="categorywrap_account">
											<div id="cate_account${i}" class="cate_account">
												<table class="accinner">
													<thead class="tablehead" style="text-align: center;">
														<tr>
															<th style="border-top-left-radius: 20px;"></th>
															<th>기간</th>
															<th><input type="date" name="startdate"
																class="accterm"
																value="${result.getAccount().getStartdate()}" readonly></th>
															<th>~</th>
															<th><input type="date" name="enddate"
																class="accterm"
																value="${result.getAccount().getEnddate()}" readonly></th>
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

														<c:forEach begin="0" var="j"
															end="${result.getAccountRow().size()-1}">
															<tr id="accrow${j}">
																<td class="plusbtndiv">
																	<!-- <div class="plusbtndiv" style="margin: 0 auto;">
                                             <button type="button"></button>
                                          </div> -->
																</td>
																<td><input type="date" name="accdate" id="accdate"
																	value="${result.getAccountRow().get(j).getAccdate()}"
																	readonly></td>
																<td><input type="text" name="history" id="history"
																	style="width: 100%;"
																	value="${result.getAccountRow().get(j).getHistory()}"
																	readonly></td>
																<td><input type="text" name="amount" id="amount"
																	onkeyup="inputNumberFormat(this)"
																	onchange="showBalance(this)"
																	value="${result.getAccountRow().get(j).getAmount()}"
																	readonly></td>
																<td><input type="text" name="acctype" id="acctype"
																	value="${result.getAccountRow().get(j).getAcctype()}"
																	readonly></td>
																<td><div>
																		<button type="button"></button>
																	</div></td>
															</tr>

														</c:forEach>
													</tbody>
													<tfoot class="footer">
														<tr>
															<td colspan="6"><textarea name="accmemo"
																	class="accmemo" id="accmemo" cols="20" rows="10"
																	style="border: none;" placeholder="메모" readonly>${result.getAccount().getAccmemo()}</textarea>
															</td>
														</tr>
														<tr>
															<th style="border-bottom-left-radius: 20px;">예산</th>
															<td colspan="2" style="text-align: right;"><input
																type="text" name="budget" id="budget"
																onkeyup="inputNumberFormat(this)"
																style="text-align: right; width: 90%;"
																value="${result.getAccount().getBudget()}" readonly>원</td>
															<!-- 사용자가 입력하는 칸이아니라 계산해서 보여주는 곳 -->
															<th>잔액</th>
															<td><input type="text" name="change" id="change"
																value="${result.getAccBalance()}" readonly></td>
															<td style="border-bottom-right-radius: 20px;"></td>
														</tr>
													</tfoot>

												</table>
											</div>
										</div>
									</li>
								</c:when>

								<c:when test="${monga.catnum[i] == 2}">
									<!-- 음식 카테고리 -->
									<li class="cate" style="text-align: center;">
										<div class="categorywrap_food">
											<div id="cate_food${i}" class="cate_food">
												<div class="inner">
													<div class="fdinner, fdinner1, fd_header" id="fd_header">
														<div class="fd_header_lt" id="fd_header_lt">
															<input type="hidden" name="cnt" value=""> <input
																type="text" class="fd_time" name="fd_time" id="fd_time"
																placeholder="아침"
																value="${result.getFood().getFoodTime()}" readonly>
														</div>
														<div class="fd_header_rt" id="fd_header_rt">
															<span id="star" class="star">★★★★★ <span
																id="starSpan" class="star"
																style="width:${result.getFood().getFoodScore()*10}%">★★★★★</span>
																<input type="range" readonly>
															</span>
														</div>
													</div>
													<div class="fdinner, fdinner2, fd_cont" id="fd_cont">
														<!-- <div class="cate_food_img" id="cate_food_img" name="cate_food_img">
                                    <span id="file_imgname">이미지탭 클릭 후 이미지를 추가할 수 있어요!</span>
                                    이미지 div 추가될 곳 
                                 </div> -->
														<textarea class="fd_text" name="fd_text" id="fd_text"
															onkeydown="resize(this)" onkeyup="resize(this)"
															placeholder="내용" readonly>${result.getFood().getFoodContents()}</textarea>
													</div>
													<!-- </div> -->
													<div class="btndiv">
														<button class="xBtn"></button>
													</div>
													<br>
												</div>
											</div>
										</div>
									</li>
								</c:when>

								<c:when test="${monga.catnum[i] == 3}">
									<!-- 문화카테고리 -->
									<li class="cate" style="text-align: center;">
										<div class="categorywrap_culture">
											<div id="cate_cul${i}" class="cate_cul">
												<div class="inner">
													<div id="cul_top" style="position: relative;">
														<!-- 클론할때마다 value에 "culture-"+cnt를 넘겨야합니다 -->
														<input type="hidden" name="cnt" value="">
														<!-- 카테고리 + CNT -->
														<input type="text" name="cultitle" id="cultitle"
															placeholder="제목"
															value="${result.getCulture().getCultitle()}" readonly>
														<input type="text" name="cultype" id="cultype"
															placeholder="유형"
															value="${result.getCulture().getCultype()}" readonly>
														<input type="text" name="culgenre" id="culgenre"
															placeholder="장르"
															value="${result.getCulture().getCulgenre()}" readonly>
														<span id="star" class="star"> ★★★★★ <span
															id="starSpan" class="star"
															style="width:${result.getCulture().getCulscore()*10}%">★★★★★</span>
															<input type="range" readonly>
														</span>
													</div>
													<div id="cul_contents">
														<div id="culimg"></div>
														<textarea onkeydown="resize(this)" onkeyup="resize(this)"
															id="culcontents" placeholder="내용" readonly>${result.getCulture().getCulcontents()}</textarea>
													</div>
												</div>
												<br>
											</div>
										</div>
									</li>
								</c:when>

								<c:when test="${monga.catnum[i] == 4}">
									<!-- 일기 카테고리 -->

									<li class="cate">
										<div class="categorywrap_diary">
											<div class="cate_diary">
												<div class="inner">
													<div class="head">
														<input type="text" name="diadate" id="diadate"
															class="diadate" placeholder="   2022년  12월  28일  화요일"
															value="${result.getDiary().getDiarydate()}" readonly>
														<input type="text" name="diaweather" id="diaweather"
															class="diaweather" placeholder="날씨 "
															value="${result.getDiary().getWeather()}" readonly>
													</div>
													<hr>
													<div id="body">
														<div name="diaimg" id="diaimg">
															
														</div>
														<input type="text" name="diatitle" class="diatitle"
															id="diatitle" placeholder="제목 : "
															value="${result.getDiary().getDiarytitle()}" readonly>
														<textarea name="diacont" id="diacont" class="diacont"
															onkeydown="resize(this)" onkeyup="resize(this)" readonly>${result.getDiary().getDiarycontents()}</textarea>
													</div>
												</div>
											</div>
										</div>
									</li>
								</c:when>
								<c:when test="${monga.catnum[i] == 5}">
									<script>
                           num = ${i};
                           latitude = ${result.getMap().getLatitude()};
                           longitude = ${result.getMap().getLongitude()};
                        </script>
									<!-- 맵 카테고리 -->
									<li class="cate">
										<div class="categorywrap_map">
											<div id="cate_map${i}">
												<div class="inner mapSelecter${i}">
													<script>
                                    
                                    var mapInner = document.querySelector("#cate_map${i}>.mapSelecter${i}");
                                    var mapDiv = document.createElement("div");
                                    mapDiv.setAttribute("id","map"+num);
                                    mapDiv.setAttribute("style","width:100%;height:300px;");
                                    mapInner.appendChild(mapDiv);
                                    
                                    var mapContainer = document.getElementById('map'+num), // 지도를 표시할 div 
                                    mapOption = { 
                                       center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
                                       level: 3 // 지도의 확대 레벨
                                    };
                                    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
                                    // 마커가 표시될 위치입니다 
                                    var markerPosition  = new kakao.maps.LatLng(latitude, longitude); 
                                    // 마커를 생성합니다
                                    var marker = new kakao.maps.Marker({
                                       position: markerPosition
                                    });
                                    // 마커가 지도 위에 표시되도록 설정합니다
                                    marker.setMap(map);
                                    </script>
												</div>
											</div>
										</div>
									</li>
								</c:when>
								<c:when test="${monga.catnum[i] == 7}">

									<!-- 메모 카테고리 -->

									<li class="cate" style="text-align: center;">
										<div class="categorywrap_memo">
											<div id="cate_memo${i}" class="cate_memo">
												<div class="inner">
													<div id="memo_contents">
														<input type="hidden" name="cnt" value="">
														<!-- 카테고리 + CNT -->
														<div id="memo_img"></div>
														<textarea onkeydown="resize(this)" onkeyup="resize(this)"
															id="memocontents" placeholder="내용" readonly>${result.getMemo().getMemocontents()}</textarea>
													</div>
												</div>
												<br>
											</div>
										</div>
									</li>
								</c:when>
							</c:choose>
						</c:forEach>
					</c:when>
				</c:choose>
				<div class="getViewBtns">
					<c:choose>
						<c:when
							test="${authority == '함께보기' and loginUser.userid != writer}">
							<a href="javascript:window.history.back();"><button
									class="w-btn w-btn-pink">뒤로가기</button></a>
						</c:when>
						<c:otherwise>
							<a href="${cp}/schedule/schedulemodify?scnum=${scnum}&writer=${writer}"><button
									class="w-btn w-btn-pink" type="button">수정</button></a>
							<a href="${cp}/schedule/scheduledelete?scnum=${scnum}"><button
									class="w-btn w-btn-pink" type="button">삭제</button></a>
							<a href="javascript:window.history.back();"><button
									class="w-btn w-btn-pink" type="button">뒤로가기</button></a>
						</c:otherwise>
					</c:choose>
				</div>
			</ul>
			<!-- 이 밑에서 부터는 복제할 카테고리들 목록 -->
		</div>
	</div>
</body>

<script>
let num = 0;
let latitude = 0;
let longitude = 0;
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script src="${cp}/resources/js/getwriteview.js"></script>
<script src='${cp}/resources/js/todo.js' type="text/javascript"></script>

</html>