<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${cp }/css/styles.css">

<style>
        .cate_diary{
            display: none;
        }
        #map{
            /* display none을 하면 페이지 로딩이 갑자기 느려져서 화면에 아예 뜨지를 않음.. */
            /* none 안하면 잘됨 */
            /* display: none; */
        }
        .cate_img{
            display: none;
        }
</style>
</head>
<body>
    <div id="container">
        <div id="side">
            <div id="mainside">
                <span>메인메뉴보기</span>
            </div>
            <div id="cateside">
                <span>&lt;&lt;</span>
                <span>가계부</span>
                <span id="sied_diary" onclick="makediary()">일기</span>
                <span id="side_culture" onclick="culture()">문화</span>
                <span>음식</span>
                <span id="side_memo" onclick="memo()">메모</span>
                <span id="side_img" onclick="makeimg(file1)">이미지</span>

                <!-- <input type="file" id="file1" name="file1" style="display:none;">
                <a href="javascript:upload('file1')">파일 선택</a> -->
                <span id="side_map" onclick="makemap()">지도</span>
            </div>
        </div>
        <div id="container_cate">
            <form action="" name="groupForm">
                <div id="group">
                    <div class="group-title" id="term">기간</div>
                    <div class="group-title" id="date">날짜</div>
                    <div class="group-title" id="authority">권한</div>
                    <div class="group-title" id="friend">함께할 친구</div>
                    <div class="group-content" id="term-content"></div>
                    <div class="group-content" id="date-content"></div>
                    <div class="group-content" id="authority-content"></div>
                    <div class="group-content" id="friend-content"></div>
                </div>
            </form>
            <form action="" name="categoryForm">
                <ul id="categories">
                    <li id="titleco">
                        <div style="margin: 0 auto; width: 70%;">
                            <input type="text" name="title" id="title" placeholder="스케쥴 제목을 입력해주세요"> 
                            <div id="color_div">
                                <input type="color" name="color" id="color">
                            </div>
                        </div>
                    </li>
                </ul>
            </form>
            <!-- 지도 div어떻게 감쌀지 고민쓰 삭제하기 버튼도 맞춰서 넣어야함-->
                    <!-- <div class="cate_map">
                        <div id="inner">
                        <input type="hidden">
                        <input type="hidden">
                        <div id="" style="width:100%;height:300px;"></div>
                        </div>
                        <div id="btndiv">
                        <button class="xBtn"></button>
                        </div>
                    </div> -->
            <!-- 다이어리 -->
                    
                    <div class="cate_diary">
                        <div class="inner">
                        <div class="head">
                            <input type="hidden">
                            <input type="hidden">
                            <input type="text" name="diadate" id="diadate" placeholder="   2022년  12월  28일  화요일">         
                            <input type="text" name="diaweather" id="diaweather" placeholder="날씨 ">
                        </div>
                        <hr>
                        <div id="body">
                            <div name="diaimg" id="diaimg"><img src="https://via.placeholder.com/" alt="이미지탭을 클릭하세요!">
                            </div>
                            <input type="text" name="diatitle" id="diatitle" placeholder="제목 : ">
                            <textarea name="diacont" id="diacont"onkeydown="resize(this)" onkeyup="resize(this)" ></textarea>
                        </div>
                        </div>
                        <div class="btndiv">
                        <button class="xBtn"></button>
                        </div>
                    </div>
                    
            <!-- 이미지 -->
            <div class="cate_img">
                <div class="inner">
                    <div id="body">
                        <div name="diaimg" id="diaimg"><img src="https://via.placeholder.com/" alt="이미지탭을 클릭하세요!">
                            <input type="file" id="file1" name="file1" style="display:none;">
                        </div>>
                    </div>
                </div>
                <div class="btndiv">
                <button class="xBtn"></button>
                </div>
            </div>
            <!-- 문화 -->
            <!-- 메모 -->
        </div>
    </div>
</body>
<script src="${cp }/js/cate.js"></script>

<script type="text/javascript">
window.kakao=window.kakao||{},window.kakao.maps=window.kakao.maps||{},window.daum&&window.daum.maps?window.kakao.maps=window.daum.maps:(window.daum=window.daum||{},window.daum.maps=window.kakao.maps),function(){function a(){if(E.length){t(I[E.shift()],a).start()}else e()}function t(a,t){var e=document.createElement("script");return e.charset="utf-8",e.onload=t,e.onreadystatechange=function(){/loaded|complete/.test(this.readyState)&&t()},{start:function(){e.src=a||"",
document.getElementsByTagName("head")[0].appendChild(e),e=null}}}function e(){for(;c[0];)c.shift()();o.readyState=2}var o=kakao.maps=kakao.maps||{};if(void 0===o.readyState)o.onloadcallbacks=[],o.readyState=0;else if(2===o.readyState)return;o.VERSION={ROADMAP:"2212ejo",ROADMAP_SUFFIX:"",HYBRID:"2212ejo",SR:"3.00",ROADVIEW:"7.00",ROADVIEW_FLASH:"200402",BICYCLE:"6.00",USE_DISTRICT:"2212ejo",
SKYVIEW_VERSION:"160114",SKYVIEW_HD_VERSION:"160107"},o.RESOURCE_PATH={ROADVIEW_AJAX:"//t1.daumcdn.net/roadviewjscore/core/css3d/200204/standard/1580795088957/roadview.js",ROADVIEW_CSS:"//t1.daumcdn.net/roadviewjscore/core/openapi/standard/211122/roadview.js"};for(var n,r="https:"==location.protocol?"https:":"http:",s="",i=document.getElementsByTagName("script"),d=i.length;n=i[--d];)if(/\/(beta-)?dapi\.kakao\.com\/v2\/maps\/sdk\.js\b/.test(n.src)){s=n.src;break}i=null;var c=o.onloadcallbacks,E=["v3"],S="",I={v3:r+"//t1.daumcdn.net/mapjsapi/js/main/4.4.8/kakao.js",services:r+"//t1.daumcdn.net/mapjsapi/js/libs/services/1.0.2/services.js",
drawing:r+"//t1.daumcdn.net/mapjsapi/js/libs/drawing/1.2.6/drawing.js",clusterer:r+"//t1.daumcdn.net/mapjsapi/js/libs/clusterer/1.0.9/clusterer.js"},_=function(a){var t={};return a.replace(/[?&]+([^=&]+)=([^&]*)/gi,function(a,e,o){t[e]=o}),t}(s);S=_.appkey,S&&(o.apikey=S),o.version="4.4.8";var R=_.libraries;if(R&&(E=E.concat(R.split(","))),"false"!==_.autoload){for(var d=0,l=E.length;d<l;d++)!function(a){a&&document.write('<script charset="UTF-8" src="'+a+'"><\/script>')}(I[E[d]]);o.readyState=2}o.load=function(t){switch(c.push(t),o.readyState){case 0:o.readyState=1,a();break
;case 2:e()}}}();

</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-migrate-1.2.1.js"></script>
</html>