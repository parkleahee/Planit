/**
 * 
 */
const ul = document.getElementById("categories");
let cnt = 1; 
let accRowCnt=1;
//객체 선언
let totalAmount = {};

function sendit(){
   const categoryForm = document.categoryForm;
   const boardtitle = categoryForm.title;
   if(boardtitle.value == ""){
      alert("제목을 입력하세요");
      boardtitle.focus();
      return false;
   }
   
   categoryForm.submit();
}

function makeaccount(){
   accRowCnt=1;
    const cate_account = document.getElementById("cate_account");
    const li = document.createElement("li");
    li.setAttribute("class","cate");

    const categorywrap_account = document.createElement("div");
    categorywrap_account.setAttribute("class","categorywrap_account");
    ul.appendChild(li);
    li.appendChild(categorywrap_account);

    const cloneAccount = cate_account.cloneNode(true);
    cloneAccount.setAttribute('name','account-'+cnt);
    cloneAccount.setAttribute('id','cate_account'+'-'+cnt);
    cloneAccount.style.display='block';
    categorywrap_account.appendChild(cloneAccount);
// 바디랑 푸터도 클래스명을 바꾸자
    const thead = cloneAccount.children[0].children[0];
//    cnt에 value넣기
    const hiddencnt = thead.children[0];
    hiddencnt.setAttribute("value","account-"+cnt);
    const startdate = thead.children[2].children[2].children[0];
    startdate.setAttribute("name","startdate-"+cnt );
    const enddate = thead.children[2].children[4].children[0];
    enddate.setAttribute("name","enddate-"+cnt );
//    바디
    const tbody = cloneAccount.children[0].children[1];
    tbody.setAttribute("id","body-"+cnt);
//    hiddenaccRowCnt value 넣기
    const hiddenaccRowCnt = tbody.children[1].children[0];
    hiddenaccRowCnt.setAttribute("value",cnt+"-"+accRowCnt);
    const accrowNum = tbody.children[1];
    accrowNum.setAttribute("id","accrow-"+cnt+"-"+accRowCnt);
    
    //쓴 금액 합산을 구하기위함
    totalAmount["amount-"+cnt]=0
    
    const makearrowbtn = accrowNum.children[1].children[0].children[0];
    makearrowbtn.addEventListener("click",makeaccrows);
    const accdate = accrowNum.children[2].children[0];
    accdate.setAttribute("id","accdate-"+cnt+"-"+accRowCnt);
    accdate.setAttribute("name","accdate-"+cnt+"-"+accRowCnt);
    
    const history = accrowNum.children[3].children[0];
    history.setAttribute("id","history-"+cnt+"-"+accRowCnt);
    history.setAttribute("name","history-"+cnt+"-"+accRowCnt);
    const amount = accrowNum.children[4].children[0];
    amount.setAttribute("id","amount-"+cnt+"-"+accRowCnt);
    amount.setAttribute("name","amount-"+cnt+"-"+accRowCnt);
    

    //금액의 인풋값을 저장하기 위함 
    amount.addEventListener("focus",function(e){totalAmount[e.target.id]= e.target.value; console.log(totalAmount)});
    
    
    const acctype = accrowNum.children[5].children[0];
    acctype.setAttribute("id","acctype-"+cnt+"-"+accRowCnt);
    acctype.setAttribute("name","acctype-"+cnt+"-"+accRowCnt);
    
    
    const tfooter = cloneAccount.children[0].children[2];
    const accmemo = tfooter.children[0].children[0].children[0];
    accmemo.setAttribute("id","accmemo-"+cnt );
    accmemo.setAttribute("name","accmemo-"+cnt );
    const budget = tfooter.children[1].children[1].children[0];
    budget.setAttribute("id","budget-"+cnt );
    budget.setAttribute("name","budget-"+cnt );
    //잔액계산할때 이용하기 위해 함수 달아줌
    budget.addEventListener("change",getNewBudget);


    cloneAccount.children[1].addEventListener("click",deleteCate);
    
    cnt++;
    
}
function makediary(){
   
        const cate_diary = document.getElementsByClassName("cate_diary");
        const li = document.createElement("li");
        li.setAttribute("class","cate");

        const categorywrap_diary = document.createElement("div");
        categorywrap_diary.setAttribute("class","categorywrap_diary");
        // const diary = document.createElement("div");
        // diary.setAttribute("id","diary");
        
        ul.appendChild(li);
        li.appendChild(categorywrap_diary);

        const cloneDiary = cate_diary[0].cloneNode(true);
        cloneDiary.setAttribute('name', 'diary-' + cnt);
        console.dir(cloneDiary)
        // class->id로 바꾸자
        cloneDiary.setAttribute('id', 'cate_diary '+'-'+cnt);

        cloneDiary.style.display = 'block';
        categorywrap_diary.appendChild(cloneDiary);
        console.dir(cloneDiary)
        // head
        const diahead = (cloneDiary.children[0].children[0]);
        // cnt , diadate, diaweather
        const cntdia = diahead.children[0];
        const diadate = diahead.children[2];
        const diaweather = diahead.children[3];
//        cntdia.setAttribute('name','cnt_diary');
        cntdia.setAttribute('value',"diary-"+cnt);
        diadate.setAttribute('name','diadate-'+cnt);
        diadate.setAttribute('id','diadate-'+cnt);
        diadate.setAttribute('class','diadate');
        diaweather.setAttribute('name','diaweather-'+cnt);
        diaweather.setAttribute('id','diaweather-'+cnt);
        diaweather.setAttribute('class','diaweather');
        
        // body
        const diabody = (cloneDiary.children[0].children[2]);
        //이미지,제목,메모
        const diaimg = diabody.children[0];
        const diatitle = diabody.children[1];
        const diacont = diabody.children[2];
        diaimg.setAttribute('name','diaimg-'+cnt);
        diaimg.setAttribute('id','diaimg-'+cnt);
        diaimg.setAttribute('class','diaimg');
        diatitle.setAttribute('name','diatitle-'+cnt);
        diatitle.setAttribute('id','diatitle-'+cnt);
        diatitle.setAttribute('class','diatitle');
        diacont.setAttribute('name','diacont-'+cnt);
        diacont.setAttribute('id','diacont-'+cnt);
        diacont.setAttribute('class','diacont');

        //btndiv
        console.dir(cloneDiary);
        cloneDiary.children[1].addEventListener("click",deleteCate);
        cnt++;
        
}

function makemap(){
    const li = document.createElement("li");
    li.setAttribute("class","cate");
    const categorywrap_map = document.createElement("div");
    categorywrap_map.setAttribute("class","categorywrap_map");
    const map2 = document.createElement("div");
    map2.setAttribute("id","map"+cnt);
    map2.setAttribute("style","width:100%;height:300px;");
    const cate_map = document.createElement("div");
    cate_map.setAttribute("id","cate_map -"+cnt);
    cate_map.setAttribute("name","map-"+cnt)

    const inner = document.createElement("div");
    inner.setAttribute("class","inner");
    const latNLng = document.createElement("input");
    const mapCnt = document.createElement("input");
    latNLng.setAttribute("type","hidden");
    mapCnt.setAttribute("type","hidden");
    mapCnt.setAttribute("name","cnt");
    mapCnt.setAttribute("value","map-"+cnt);
    latNLng.setAttribute("name","latNlng-"+cnt);
    
    const btndiv = document.createElement("div");
    btndiv.setAttribute("class","btndiv");
    const xbutton = document.createElement("button");
    xbutton.setAttribute("class","xBtn");

    ul.appendChild(li);
    li.appendChild(categorywrap_map);
    categorywrap_map.appendChild(cate_map);
    cate_map.appendChild(inner);
    inner.appendChild(map2);
    inner.appendChild(latNLng);
    inner.appendChild(mapCnt);

    cate_map.appendChild(btndiv);
    btndiv.appendChild(xbutton);

    map2.style.display = 'block';

    //btndiv
    btndiv.addEventListener("click",deleteCate);

   var mapContainer = document.getElementById('map'+cnt), // 지도를 표시할 div 
       mapOption = { 
       center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
       level: 3 // 지도의 확대 레벨
   };
   
   var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
   
   // 지도를 클릭한 위치에 표출할 마커입니다
   var marker = new kakao.maps.Marker({ 
       // 지도 중심좌표에 마커를 생성합니다 
       position: map.getCenter() 
   }); 
   // 지도에 마커를 표시합니다
   marker.setMap(map);
   
   // 지도에 클릭 이벤트를 등록합니다
   // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
   kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
       
   // 클릭한 위도, 경도 정보를 가져옵니다 
   var latlng = mouseEvent.latLng; 
       // var latlng = 
   // 마커 위치를 클릭한 위치로 옮깁니다
   marker.setPosition(latlng);
   let longitude = latlng.getLng();
   let latitude = latlng.getLat();
   latNLng.setAttribute("value",latitude+"/"+longitude);
     
   });
   cnt++;
}
//
//function resize(obj) {
//    obj.style.height = "1px";
//    obj.style.height = (12+obj.scrollHeight)+"px";
//}
//
//const drawStar = (target) => {
//    $(`.star span`).css({ width: `${target.value * 10}%` });
//}
//

//budget을 작성(수정)했을때 일어나는일
function getNewBudget(e){

  let containerNum=e.target.id.split("-")[1];
  let spending ="amount-"+containerNum;

  
  //budget-1='30,000'
  totalAmount[e.target.id]=e.target.value;
  // 그냥 그때그때 총액에서 빼서 잔액보여주면 됨
  let result = e.target.value.replace(/\,/g,'')-totalAmount[spending];

  e.target.parentElement.parentNode.children[3].children[0].value=
  result.toLocaleString();
  
  
}
function deleteCate(e){
    console.dir(e)
    const li = e.composedPath()[4]
    li.remove();
}
// 가계부 전용 함수

// 가계부 행 클릭횟수
function makeaccrows(e){
accRowCnt++;
//복제할 행(클릭한 버튼이 해당하는 행)
const accTr = e.composedPath()[3];
//복제할 행의 컨테이너 넘버구하기
// (컨테이너가 여러개가 있을 때 클릭한 컨테이너 내부에서 행이 복제되도록)
const accrowId= accTr.id;
const containerNum = accrowId.split("-")[1];
// 행을 복제하기
const cloneAccrow = accTr.cloneNode(true);
// date, history, amount, type 모두 백지로 비워주기
cloneAccrow.cells[1].children[0].value='';
cloneAccrow.cells[2].children[0].value='';
cloneAccrow.cells[3].children[0].value='';
cloneAccrow.cells[4].children[0].value='';
//복제한 행이 몇번째 컨테이너의 몇번째 행인지 히든에  적어주기
cloneAccrow.children[0].setAttribute("value",containerNum+"-"+accRowCnt);
//복제한 행에 id랑 name 새로 부여하기 (인풋네임-컨테이너 번호-클릭횟수)
cloneAccrow.setAttribute("id","accrow-"+containerNum+"-"+accRowCnt);
cloneAccrow.children[2].children[0].setAttribute("id","accdate-"+containerNum+"-"+accRowCnt);
cloneAccrow.children[2].children[0].setAttribute("name","accdate-"+containerNum+"-"+accRowCnt);
cloneAccrow.children[3].children[0].setAttribute("id","history-"+containerNum+"-"+accRowCnt);
cloneAccrow.children[3].children[0].setAttribute("name","history-"+containerNum+"-"+accRowCnt);
cloneAccrow.children[4].children[0].setAttribute("id","amount-"+containerNum+"-"+accRowCnt);
cloneAccrow.children[4].children[0].setAttribute("name","amount-"+containerNum+"-"+accRowCnt);

//인풋값을 저장하기 위해 이벤트를 달아줌
cloneAccrow.children[4].children[0].addEventListener("focus",function(e){totalAmount[e.target.id]= e.target.value;console.log(totalAmount)})


cloneAccrow.children[5].children[0].setAttribute("id","acctype-"+containerNum+"-"+accRowCnt);
cloneAccrow.children[5].children[0].setAttribute("name","acctype-"+containerNum+"-"+accRowCnt);
const body = e.composedPath()[4];
body.appendChild(cloneAccrow);

// 새로 만들어진 row에도 +row 클릭이벤트가 달려야함
const plusBtn = cloneAccrow.children[1].children[0].children[0];
plusBtn.addEventListener("click",makeaccrows);

// -버튼 달기위해서 class 달고 -row클릭 이벤트 달기
const minusBtntd = cloneAccrow.children[6];    
const minusBtnDiv = minusBtntd.children[0];
const minusBtn= minusBtnDiv.children[0];
minusBtn.addEventListener("click",removeaccrows);

minusBtntd.setAttribute("class","minusbtndiv");
minusBtnDiv.setAttribute("class","minusbtndiv");

}

function removeaccrows(e){

    const tr = e.composedPath()[3];
    containerNum=tr.id.split("-")[1];
    accRowCnt=tr.id.split("-")[2];
    let budget = "budget-"+containerNum;
    let spending ="amount-"+containerNum;

    //행을 삭제할 경우 totalAccount에서 삭제해야함.
    
    totalAmount[spending]-=tr.children[4].children[0].value.replace(/\,/g,'');

    delete totalAmount["amount-"+containerNum+"-"+accRowCnt];
    
    if(totalAmount[budget]){
            
        //나타나는 잔액도 바뀌어야함
        let result = totalAmount[budget].replace(/\,/g,'') -totalAmount[spending];   
        e.composedPath()[5].children[2].children[1].children[3].children[0].value= result.toLocaleString();

    }else{
        e.composedPath()[5].children[2].children[1].children[3].children[0].value ="-"+(totalAmount[spending]).toLocaleString(); 
    }


    tr.remove();
}
// 천의 단위로 콤마찍기 정규식 적용함
function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function comma(str) {
    str = String(str);
    return str.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}


//잔액 나타내기

//기존에 이 함수에 들어왔었는지 검사하고 
//만약 들어왔었다면(저장된 accRowCnt가 있다면) focus했을때의 input을 삭제한 후(그니까 focus했을 때의 값을 저장해야 한다는 뜻...음으으음) 새로운걸 더해야함.ㅋ

function showBalance(obj){
   
   let containerNum=obj.id.split("-")[1];
   let spending ="amount-"+containerNum;
   let budget = "budget-"+containerNum;
   const balanceInput = obj.parentElement.parentNode.parentNode.nextElementSibling.children[1].children[3].children[0];

    //이 함수에들어왔을 때 이미 들어온적이 있다면(focus된 적 있다면) 기존값을 먼저 지워주기
    for(let i=0;i<Object.keys(totalAmount).length; i++){
        if((Object.keys(totalAmount)[i])==obj.id){
            //바뀌기 전의 value
            const a=(totalAmount[(Object.keys(totalAmount)[i])]); 
            // console.log("a의값: "+a);
            if(isNaN(a)){
                totalAmount[spending]-=parseInt(a.replace(/\,/g,''));
            }
            else{
                // console.log("여기로 들어옴")
                totalAmount[spending]-=a;
            }
        }
    }

    //새로운 값이 들어오면 더해주기
        if(obj.value==""){     
            totalAmount[spending]+=0;             
        }
        else{
            // 숫자가 아니라 2,500 이런식으로 들어왔을때
            if(isNaN(obj.value)){
                totalAmount[spending]+=parseInt(obj.value.replace(/\,/g,''));
            }else{
                // 200 이런식으로 들어왔을때
                totalAmount[spending]+=parseInt(obj.value);
            }
        }     
    //잔액부분에 작성하기
    // 총액이 업데이트 되었는데 budget이 있으면
    // budget이 있으면 그냥 buget에서 빼서 보여주면 됨
    if(totalAmount[budget]!=undefined){
        
        let result = (totalAmount[budget].replace(/\,/g,'') -totalAmount[spending]);

        balanceInput.value= result.toLocaleString();

    //budget이 없으면    
    }else{
        balanceInput.value ="-"+(totalAmount[spending]).toLocaleString();
    }
}
 function makeculture(){
    const ul = document.getElementById("categories");
    const li = document.createElement("li");
    const categorywrap_culture = document.createElement("div");
    const cate_cul = document.getElementById("cate_cul");
    const clonecul = cate_cul.cloneNode(true);
    li.setAttribute("class","cate");
    categorywrap_culture.setAttribute("class","categorywrap_culture");
    li.style.textAlign = 'center';
    ul.appendChild(li);
    li.appendChild(categorywrap_culture);
    clonecul.setAttribute("id", "cate_cul"+cnt);
    clonecul.style.display = 'block';
    categorywrap_culture.appendChild(clonecul);
//Culture 각각 id 부여
    const cultitle = clonecul.children[0].children[0].children[1];
    const cultype = clonecul.children[0].children[0].children[2];
    const culgenre = clonecul.children[0].children[0].children[3];
    /*const culimg = clonecul.children[0].children[1].children[0];*/
    const culstar = clonecul.children[0].children[0].children[4].children[1];
    const culcontents = clonecul.children[0].children[1].children[1];
    const hidden = clonecul.children[0].children[0].children[0];
//    culimg.setAttribute("id", "culimg-" + cnt);
    cultitle.setAttribute("name", "cultitle-" + cnt);
    cultype.setAttribute("name", "cultype-" + cnt);
    culgenre.setAttribute("name", "culgenre-" + cnt);
    culstar.setAttribute("name", "culstar-" + cnt);
    culcontents.setAttribute("name", "culcontents-" + cnt);
    hidden.setAttribute("value", "culture-" + cnt);
//    culimg.setAttribute("name", "culimg-" + cnt);
//Culture 텍스트 박스 사이즈 고정
    culcontents.style.resize = 'none';
    culcontents.style.overflow = 'auto';
    culcontents.style.minHeight = '200px';
    culcontents.style.width = '100%';
    
    
//별에 id class부여하기
    const star = clonecul.children[0].children[0].children[4];
    console.dir(별);
    star.setAttribute("id","star");
    star.setAttribute("class","star");
    star.children[0].setAttribute("id","starSpan"+cnt);
    star.children[0].style.width=0;
    star.children[1].value=1;
    clonecul.children[1].addEventListener("click",deleteCate);
    cnt++;
}

function makememo(){
   const ul = document.getElementById("categories");
    const li = document.createElement("li");
    const categorywrap_memo = document.createElement("div");
    const cate_memo = document.getElementById("cate_memo");
    const clonememo = cate_memo.cloneNode(true);
    li.setAttribute("class","cate");
    categorywrap_memo.setAttribute("class","categorywrap_memo");
    li.style.textAlign = 'center';
    ul.appendChild(li);
    li.appendChild(categorywrap_memo);
    clonememo.setAttribute("id", "cate_memo"+cnt);
    clonememo.style.display = 'block';
    categorywrap_memo.appendChild(clonememo);
//memo 각각 id, name 부여
    /*const memo_img = clonememo.children[0].children[0].children[0];*/
    const hidden = clonememo.children[0].children[0].children[0];
    const memocontents = clonememo.children[0].children[0].children[2];
    hidden.setAttribute("value", "memo-" + cnt);
    /*memo_img.setAttribute("id", "memo_img-" + cnt);*/
    memocontents.setAttribute("id", "memocontents-" + cnt);
    /*memo_img.setAttribute("name", "memo_img-" + cnt);*/
    memocontents.setAttribute("name", "memocontents-" + cnt);
//textarea 사이즈 고정
    memocontents.style.resize = 'none';
    memocontents.style.overflow = 'auto';
    memocontents.style.minHeight = '200px';
    memocontents.style.width = '100%';
    clonememo.children[1].addEventListener("click",deleteCate);
    cnt++;
    
}

function resize(obj) {
    obj.style.height = "1px";
    obj.style.height = (12+obj.scrollHeight)+"px";
}

function makestar(e){
   console.dir(e)
   
   const star = document.getElementById("star");
   const cloneStar = star.cloneNode(true);
   cloneStar.setAttribute("id","star"+cnt);
   cloneStar.children[1].value=1;
   cloneStar.children[0].style.width=0;
   
   document.body.appendChild(cloneStar);
   cloneStar.children[0].setAttribute("id","starSpan"+cnt);
   
   cnt++;
}

const drawStar = (target) => {
   
   const starSpanId=target.parentElement.children[0].id;
   document.getElementById(starSpanId).style.width = `${target.value * 10}%`;
}

function changeterm(){
    $("#term_content2").attr("readonly",false);
    $("#term_content2").focus();
}
function changedate(){
    $("#date_content2").attr("readonly",false);
    $("#date_content2").focus();
}

const WriteSetForm = document.getElementsByName("WriteSetForm")


function alone(){
    const authority_content = document.getElementById("authority_content");
    authority_content.setAttribute('value', '혼자하기')
}

function withsee(){
    const authority_content = document.getElementById("authority_content");
    authority_content.setAttribute('value', '함께보기')
}

function withchange(){
    const authority_content = document.getElementById("authority_content");
    authority_content.setAttribute('value', '함께수정')
}

function addfri() {
   // 체크한 데이터를 담을 배열 선언
   const arr = [];
   const friendslabel = [];
   const remain = document.getElementById("friend_content").value;
   // Name이 Color인 속성 취득
   const friends = document.getElementsByName("friends");
   const friendCheck = document.getElementById("friend_addlist").childNodes;
   const br = document.getElementById("friend_addlist");
   // 취득한 속성 만큼 루프
      for (let i = 0; i < friends.length; i++) {
     // 속성중에 체크 된 항목이 있을 경우
         if (friends[i].checked == true) {
               arr.push(friends[i].value);
               friends[i].style.display = 'none';
               friends[i].value = '';
               friends[i].previousElementSibling.style.display = 'none';
               friends[i].previousElementSibling.innerHTML = '';
         }
       }
       // 결과를 표시
       document.getElementById("friend_content").value = remain + arr;
}

const friend_content = document.getElementById("friend_content");
const friend_addlist = document.getElementById("friend_addlist");
const hidden = document.getElementsByClassName("hidden");
friend_content.addEventListener('click', function() {
    friend_addlist.classList.toggle('hidden');
});

 function makefood() {
      const ul = document.getElementById("categories");
      const li = document.createElement("li");
      const categorywrap_food = document.createElement("div");
        const cate_food = document.getElementById("cate_food");
        const clonefd = cate_food.cloneNode(true);
        
        li.setAttribute("class","cate");
       categorywrap_food.setAttribute("class","categorywrap_food");
       li.style.textAlign = 'center';
       ul.appendChild(li);
       li.appendChild(categorywrap_food);
       clonefd.setAttribute("id", "cate_food" +cnt);
       clonefd.setAttribute('name', 'cate_food' + cnt);
       clonefd.style.display = 'block';
//       clonefd.setAttribute('onclick', 'getCateId(this)');
       categorywrap_food.appendChild(clonefd);

       //각각 요소에 cnt 붙여서 id & name 부여
//       console.log(clonefd.children);    
        // #fd_header -> id + cnt, name생성 + cnt
        const cl_fd_header = clonefd.children[0].children[0];
        cl_fd_header.setAttribute('name', 'fd_header' + cnt);
        cl_fd_header.setAttribute('id', 'fd_header' + cnt);
//        console.log(cl_fd_header.children)

//        // #fd_header - #fd_header_lt -> id + cnt, name생성 + cnt
        const cl_fd_header_lt = clonefd.children[0].children[0].children[0];
        cl_fd_header_lt.setAttribute('name', 'fd_header_lt' + cnt);
        cl_fd_header_lt.setAttribute('id', 'fd_header_lt' + cnt);
//
//        // #fd_header - #fd_header_lt - input#fd_time -> id + cnt, name 생성 +cnt
        const cl_fd_time = clonefd.children[0].children[0].children[0].children[0];
        cl_fd_time.setAttribute('name', 'fd_time' + cnt);
        cl_fd_time.setAttribute('id', 'fd_time' + cnt);
       
//      // #fd_header - #fd_header_rt -> id + cnt, name생성 + cnt
      const cl_fd_header_rt = clonefd.children[0].children[0].children[1];
      cl_fd_header_rt.setAttribute('name', 'fd_header_rt' + cnt);
      cl_fd_header_rt.setAttribute('id', 'fd_header_rt' + cnt);
        
//        // #fd_header - #fd_header_rt - #star -> id + cnt, name 생성 +cnt
        const cl_fd_star = clonefd.children[0].children[0].children[1].children[0].children[1];
        cl_fd_star.setAttribute('name', 'fdstar' + cnt);
        cl_fd_star.setAttribute('id', 'fdstar' + cnt);
        
//        // #fd_header - #fd_header_rt - #star - #starSpan -> id + cnt, name 생성 +cnt
//        const cl_fd_starSpan = clonefd.children[0].children[1].children[0].children[0];
//        cl_fd_starSpan.setAttribute('name', 'starSpan' + cnt);
//        cl_fd_starSpan.setAttribute('id', 'starSpan' + cnt);
//
//        // #fd_header - #fd_header_rt - 버튼
//        // console.log(clonefd.childNodes[1].childNodes[3].childNodes[1])
//
//        // #fd_cont -> id + cnt, name생성 + cnt
        const cl_fd_cont = clonefd.children[0].children[1];
        cl_fd_cont.setAttribute('name', 'fd_cont' + cnt);
        cl_fd_cont.setAttribute('id', 'fd_cont' + cnt);
//
//        // #fd_cont - #cate_food_img -> id + cnt, name생성 + cnt
//        const cl_fd_img = clonefd.children[1].children[0];
//        cl_fd_img.setAttribute('name', 'cate_food' + cnt + '_img');
//        cl_fd_img.setAttribute('id', 'cate_food' + cnt + '_img');
//
//        // #fd_cont - #fd_text -> id + cnt, name + cnt
        const cl_fd_text = clonefd.children[0].children[1].children[0];
        cl_fd_text.setAttribute('name', 'fd_text' + cnt);
        cl_fd_text.setAttribute('id', 'fd_text' + cnt);
        
       cl_fd_text .style.resize = 'none';
       cl_fd_text .style.overflow = 'auto';
       cl_fd_text .style.minHeight = '200px';
       cl_fd_text .style.width = '100%';
        
       const star = clonefd.children[0].children[0].children[1].children[0];
       star.setAttribute("id","star");
       star.setAttribute("class","star");
       star.children[0].setAttribute("id","starSpan"+cnt);
       star.children[0].style.width=0;
       star.children[1].value=1;
       console.log(cnt);
       cnt++;
    }
    /*document.getElementsByTagName("textarea")[0].focus();*/

  function makestar(e){
      console.dir(e)
      
      const star = document.getElementById("star");
      const cloneStar = star.cloneNode(true);
      cloneStar.setAttribute("id","star"+cnt);
      cloneStar.children[1].value=1;
      cloneStar.children[0].style.width=0;
      
      document.body.appendChild(cloneStar);
      cloneStar.children[0].setAttribute("id","starSpan"+cnt);
      
      cnt++;
}






