const m_n = document.getElementById('main_nav');
const m_ar = ['마이페이지', '친구관리', '채팅', '문의하기', '로그아웃'];
const m_are = ['m_mypage', 'm_friend', 'm_chat', 'm_cont', 'm_logout'];
const m_userinfodiv = document.createElement('div');

m_userinfodiv.setAttribute('class','m_userinfo');
const userinfo_img = document.createElement('div');
m_userinfodiv.appendChild(userinfo_img);
// m_userinfodiv.innerHTML = '<img src="./gom_re.gif" alt="" class =
// "userinfo_img"></img>'+'<br>김사과님 환영합니다';
m_n.appendChild(m_userinfodiv);

for (let i = 0; i < m_ar.length; i++) {
    const m_div = document.createElement('div');
    m_div.setAttribute('id',m_are[i]);
    m_div.setAttribute('class','m_detail');
    m_div.innerHTML = '<a href="">'+m_ar[i]+'</a>';
    m_n.appendChild(m_div);
}


// 목표 설정, 모달 팝업
const m_goal = document.createElement('div');
m_goal.setAttribute('class','m_goal');

const m_goalbtn = document.createElement('button');
m_goalbtn.setAttribute('id','show');
m_goalbtn.innerHTML = '목표를 설정해 주세요.';
m_goal.appendChild(m_goalbtn);

const m_goalbg = document.createElement('div');
m_goalbg.setAttribute('class','background');
m_goal.appendChild(m_goalbg);
m_n.appendChild(m_goal);
m_goalbg.innerHTML = '<div class="window">'+
'<div class="popup">'+
        '<table>'+
           ' <tr>'+
                '<th>'+'<label for="usergoal">'+'목표 설정'+'</label>'+'</th>'+
                '<td>'+'<input id="usergoal1">'+'</td>'+
            '</tr>'+
            '<th>'+'기간 설정'+'</th>'+
        '<td>'+
            '<label>'+'7일'+ '<input checked type="radio" name="goalday" value="7">'
            +'</label>'+
            '<label>'+'30일' +'<input type="radio" name="goalday" value="30">'+'</label>'
        +'</td>'+'</table>'+
        '<button id="add_goal">'+'목표 설정'+'</button>'+
        '<button id="close">돌아가기</button>'+
        '</div>'+
        '</div>'+
        '</div>';

// 목표 설정 화면
 const m_goalset1 =document.createElement('div');
 m_goalset1.setAttribute('class','goalset1');
 m_goal.appendChild(m_goalset1);
 m_goalset1.innerHTML= 
 '<div id="goal_tit1">'+
 '</div>'+
 '<div>'+
 '<progress value="20" max="100">'+'</progress>'+
 '</div>'+
 '<input type="checkbox" name="" id="" value="목표 달성">'+'목표 달성'+'</div>'+
 '</div>';
 
 const m_goalset2 = document.createElement('div');
 m_goalset2.setAttribute('class','goalset2');
 m_goal.appendChild(m_goalset2);
 m_goalset2.innerHTML= 
	 '<div id="goal_tit2">'+
	 '</div>'+
	 '<div>'+
	 '<progress value="20" max="100">'+'</progress>'+
	 '</div>'+
	 '<input type="checkbox" name="" id="" value="목표 달성">'+'목표 달성'+'</div>'+
	 '</div>';

// const m_goalset =document.createElement('div');
// m_goalset.setAttribute('class','goalset1');
// m_goal.appendChild(m_goalset);

 // 제목 목표창에 넣기
 document.addEventListener('DOMContentLoaded', () => {
	const input = document.querySelector('#usergoal1');
	const add_goal = document.querySelector('#add_goal');
	const goal_tit1 = document.querySelector('#goal_tit1');
	
	const addGoal = () => {
		if(input.value ==''){
			alert('목표 입력하세요 !');
			console.log('1');
		}else{
			if(goal_tit1.innerHTML ==''){
				const text = document.createElement('span');
				text.textContent = input.value;
				input.value = '';
				goal_tit1.appendChild(text);

				document.getElementsByClassName("goalset1")[0].style.display = 'block';
				console.log('2');
			}else{
				if(goal_tit2.innerHTML ==''){
					const text = document.createElement('span')
					text.textContent = input.value;
					input.value = '';
					goal_tit2.appendChild(text);
					
					document.getElementsByClassName("goalset2")[0].style.display = 'block';
					console.log('3');					
				}else{
						alert('제발,, 돌아가,, ');
				}
			}
		}
//		else if(goal_tit1.innerHTML ==''){
//			if(input.value!== '') {
//
//				const text = document.createElement('span');
//				text.textContent = input.value;
//				input.value = '';
//				goal_tit1.appendChild(text);
//
//				document.getElementsByClassName("goalset1")[0].style.display = 'block';
//				console.log('2');
//			}
//		}else if(goal_tit1.innerHTML !==''){
//			if(input.value!== '') {
//
//				const text = document.createElement('span')
//				text.textContent = input.value;
//				input.value = '';
//				goal_tit2.appendChild(text);
//
//				document.getElementsByClassName("goalset2")[0].style.display = 'block';
//				console.log('3');
//			}
//		}else{
//			console.log('4');
//			if(input.value !== ''){				
//				alert ('돌아가세오');
//			}
//		}
		}
	
	add_goal.addEventListener('click', addGoal);
 })

function show() {
	document.querySelector(".background").className = "background show";
}

function close() {
	document.querySelector(".background").className = "background";
}

document.querySelector("#show").addEventListener("click", show);
document.querySelector("#close").addEventListener("click", close);
document.querySelector("#add_goal").addEventListener("click", close);



