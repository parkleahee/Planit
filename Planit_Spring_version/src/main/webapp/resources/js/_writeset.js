/**
 * 
 */


     $("#createSchedule").click(function(event){
        const show = document.getElementById("bottom_center_content");
        if(show.style.display = 'none'){
            show.style.display = 'block';
        } 
    })


    function hide2(){
        const hide1 = document.getElementById("hide1");
        console.log("zz");
        if(hide1.style.display = 'none'){
            hide1.style.display = 'inline-block';
        }
    }

    function div_OnOff(v,id){
    	 // 라디오 버튼 value 값 조건 비교
    	if(v == "혼자보기"){
    		document.getElementById(id).style.display = "none"; // 숨김
    	}else{
    		document.getElementById(id).style.display = "block"; // 보여줌
    	}
    }
    
   
    
    
    