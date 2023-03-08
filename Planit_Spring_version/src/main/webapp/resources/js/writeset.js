/**
 * 
 */
   function sendit(){
      const authority =  $("input[name=authority]:checked").val(); 
      const friends =  $("input[name=friends]:checked"); 
      console.log(friends.length);
      if(authority != "혼자하기"){
         if(friends.length == 0){
            alert("친구를 선택해주세요!");
            return false;
         } else{
            return true;
         }
      }
      return true;
   }

    $("#createSchedule").click(function(event){
        const show = document.getElementById("bottom_center_content");
        const submit = document.getElementById("submit");
        const subinput = submit.children[0];
        if(show.style.display = 'none'){
            show.style.display = 'block';
            submit.style.display = 'block';
            subinput.style.display = 'block';
        } 
    })


    function hide2(){
        const hide1 = document.getElementById("hide1");
        if(hide1.style.display = 'none'){
            hide1.style.display = 'inline-block';
        }
    }

    /*function addFriend(){
        const friend_window = document.createAttribute();
    }*/

    function div_OnOff(v,id){
        // 라디오 버튼 value 값 조건 비교
       if(v == "혼자하기"){
          document.getElementById(id).style.display = "none"; // 숨김
       }else{
          document.getElementById(id).style.display = "block"; // 보여줌
       }
    }
    
   
    
    
    