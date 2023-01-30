function show() {
	document.querySelector(".background").className = "background show";
}

function close() {
	document.querySelector(".background").className = "background";
}

document.querySelector("#show").addEventListener("click", show);
document.querySelector("#close").addEventListener("click", close);
//document.querySelector("#add_goal").addEventListener("click", close);

//카운트++에 따른 프로그레스바 길이 조절 
const getgoal_btn1 = document.getElementById('getgoal_btn1');
const getgoal_btn2 = document.getElementById('getgoal_btn2');
const goalday = document.getElementsByClassName('goalday');
let progress1 = document.getElementById('progress1');
let cnt1 = 0; 
let cnt2 = 0;
// 하루에 한번만 클릭할 수 있도록 설정 
var submitFlag = false;

function submitCheck(){
    if(submitFlag){
        return submitFlag;
    }else{
        submitFlag = true;
        return false;
    }
}

//getgoal_btn1.addEventListener('click',function(e){
////	location.href="cp+'/user/usergoal.tc?goalnum='${goalList.goalnum}";
//	 if(submitCheck()){
//	        alert("하루에 한 번 체크 가능합니다 !");
//	        return;
//	 }else{		 
//		 if(cnt1<=29){
//			 console.log('들어옴1')
//			 cnt1++;
//			 console.log(100/30*cnt1);
//			 progress1.value=100/30*cnt1;
//			 console.log(cnt1);
//			 alert(cnt1+'일 달성!');				
//		 }
//		 else if(cnt1==30){
//			 alert('목표 달성 성공 새로운 목표를 설정해 주세요 !');
//			 console.log('추가 안 된다.');
//			 console.log(cnt1);
//		 }
//	 }
//})

//getgoal_btn2.addEventListener('click',function(e){
//	if('${goal.goalcnt}'<=29){
//		console.log('들어옴1')
//		console.log(100/30*'${goal.goalcnt}');
//		progress2.value=100/30*'${goal.goalcnt}';
//		console.log('${goal.goalcnt}');
//		alert('${goal.goalcnt}'+'일 달성!');				
//	}
//	else if('${goal.goalcnt}'==30){
//		alert('목표 달성 성공 새로운 목표를 설정해 주세요 !');
//		console.log('추가 안 된다.');
//		console.log('${goal.goalcnt}');
//	}
//})

// 목표 설정
//function add_goal1(){
//	if(goal_input.value ==''){
//		alert('목표 입력하세요 !');
//		console.log('1');
//		goal_input.focus();
//		return false;
//	}
//	return true;
//}
			
