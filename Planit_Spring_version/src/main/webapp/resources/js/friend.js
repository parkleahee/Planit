/**
 * 
 */



/*$(document).ready(function(){
   $(".menu").mouseover(function(){
      $(this).children(".submenu").show(200);
   });
   $(".menu").mouseleave(function(){
      $(this).children(".submenu").hide(200);
   });
   
});*/


function enterKey(){
      if(window.event.keyCode == 13){
         friendfind();
      }
   }

function friendfind(){
   const q = document.getElementById("q");
   if(q.value == ""){
      alert("찾는 친구 이름를 입력해주세요!");
      q.focus();
      return false;
   }
   location.href = cp+"/user/friendsearchlist?q="+q.value;
}


   function friendok(i) {
      const xhr = new XMLHttpRequest();
      /* const result = document.getElementById("good"); */
      const userid = document.getElementById("friendid"+i);
      if(userid.value == ""){
         alert("검색된 결과가 없습니다.");
         return false;
      }
      
      xhr.onreadystatechange = function(){
         if(xhr.readyState == 4){
            if(xhr.status == 200){
               let txt = xhr.responseText;
               txt = txt.trim();
               if(txt == "O"){
                  /* result.innerHTML = "친구등록이 완료되었습니다.!"; */
                  (location || window.location || document.location).reload();
                  /*$("#contents_box").load(location.href + "#contents_box");*/
               }
               else{
                  /* result.innerHTML = "친구등록 된 친구입니다!"; */
                  (location || window.location || document.location).reload();
               }
            }
         }
      }      
      xhr.open("GET",cp+"/friend/friendconfirmviewok?userid="+userid.value,true);
      xhr.send();
   }

   function friendno(i){
      const xhr = new XMLHttpRequest();
      const userid = document.getElementById("friendid"+i);
      if(userid.value == ""){
      alert("검색된 결과가 없습니다.");
      return false;
      }
      xhr.onreadystatechange = function(){
      if(xhr.readyState == 4){
         if(xhr.status == 200){
               let txt = xhr.responseText;
               txt = txt.trim();
            if(txt == "O"){
               (location || window.location || document.location).reload();
            }
            else{
               (location || window.location || document.location).reload();
            }
         }
      }
   }

   xhr.open("GET",cp+"/friend/friendconfirmviewno?userid="+userid.value,true);
   xhr.send(); 
   }

   function chatopen(i){ // 채팅창을 팝업으로 
   var id = document.getElementById("friendid"+i);
   var loginUser = document.getElementById("loginUser");
   window.open(cp+"/chat/ChatWindow.jsp?chatId="+id.value+"&loginUser="+loginUser.value+",width=320, height=400"); 
   }

//   function show() {
//      document.querySelector(".background").className = "background show";
//   }
//   
//   function close() {
//      document.querySelector(".background").className = "background";
//   }
   
//   document.querySelector("#show").addEventListener("click", show);
//   document.querySelector("#close").addEventListener("click", close);

   function friendblock(i){
      const xhr = new XMLHttpRequest();
      /* const result = document.getElementById("good"); */
      const userid = document.getElementById('friendid'+ i);
      if(userid.value == ""){
         alert("검색된 결과가 없습니다.");
         return false;
      }
      
      xhr.onreadystatechange = function(){
         if(xhr.readyState == 4){
            if(xhr.status == 200){
               let txt = xhr.responseText;
               txt = txt.trim();
               if(txt == "O"){
                  (location || window.location || document.location).reload();
               }
               else{
                  (location || window.location || document.location).reload();
               }
            }
         }
      }
      
      xhr.open("GET",cp+"/friend/friendblockok?userid="+userid.value,true);
      xhr.send();
      
   }

   function blockend(i){
      const xhr = new XMLHttpRequest();
      /* const result = document.getElementById("good"); */
      const userid = document.getElementById("friendid" + i);
      if(userid.value == ""){
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
                  (location || window.location || document.location).reload();
               }
               else{
                  (location || window.location || document.location).reload();
               }
            }
         }
      }
      
      xhr.open("GET",cp+"/friend/friendblockend?userid="+userid.value,true);
      xhr.send();
   }
   
      function lookschedule(i){
            const scheduletitle = document.getElementById('scheduletitle');
            
            location.href = cp+"/schedule/getwriteview?scnum="+i;   
         }