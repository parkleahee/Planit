function cntGoal_2(e){
		let goal="goal2";
		let goalnum=$('#getgoal_btn2').attr("class");
		//번호 추출 
		
		goalService.cntGoal(
				// 자원 전체를 전달해야 한다. (JSON)
			{"goal":goal,"goalnum":goalnum},
			function(result){
				if(result == "success"){
					alert('목표 달성 성공 !');
					(location || window.location || document.location).reload();
				}
			}
		)
	}
   
   window.onload = function(){
      let goalchk_1="${goal1.goalcheck}";
      if(goalchk_1 == 'f'){
         document.getElementById("goalchk_1").innerHTML='오늘의 목표 달성🙂';
      }
      
      let goalchk_2="${goal2.goalcheck}";
      if(goalchk_2 == 'f'){
         document.getElementById("goalchk_2").innerHTML='오늘의 목표 달성🙂';
      }
   }