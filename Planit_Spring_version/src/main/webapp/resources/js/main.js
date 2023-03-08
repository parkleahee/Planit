/**
 * 
 */

	/**
 * 
 */

//	document.addEventListener('DOMContentLoaded', () => {
//		const input = document.querySelector('#todo')
//		const addButton = document.querySelector('#add-button')
//		const todoList = document.querySelector('#todo-list')
//		// const alert = document.querySelector('span')
//
//		  // '+' 버튼 익명 화살표 함수 
//		const addTodo = () => {
//			
//			if (input.value !== '') {
//				const item = document.createElement('div')
//			  // 체크박스
//				const checkbox = document.createElement('input')
//				checkbox.type='checkbox'
//			 // text
//				const text = document.createElement('span');
//			  // 제거하기 버튼
//				const deleteButton = document.createElement('button')
//				deleteButton.textContent="x"
//			// 중요표시 버튼 
//				const importantButton = document.createElement('button')
//				importantButton.textContent="x"
//
//				text.textContent = input.value
//				input.value=''
//			
//				item.appendChild(checkbox)
//				item.appendChild(text)
//				item.appendChild(importantButton)
//				item.appendChild(deleteButton)
//				todoList.appendChild(item)
//
//		// 체크박스 이벤트 리스너
//				 checkbox.addEventListener('change', (event) =>{ 
//				 	if (event.currentTarget.checked)
//				 	{
//				 		text.style.textDecoration='line-through'
//				 	}
//				 	else {
//				 		text.style.textDecoration='none'
//				 	}
//				 })
//				
//				importantButton.addEventListener('click', (event) =>{
//					text.classList.toggle('clicked');
//				})
//
//			  // 제거하기 버튼 클릭 이벤트 리스너
//				deleteButton.addEventListener('click', (event) => {
//					todoList.removeChild(event.currentTarget.parentNode)
//				})
//				input.value =''
//				alert.textContent = ''
//			}
//			else
//				alert.textContent = ''
//		}
//		//중요 버튼눌렀을때 글짜 배경색 바뀌게 설정하기 
//
//		addButton.addEventListener('click', addTodo)
//  
		  // 할 일 입력창에서 enter key가 눌렸을 때
		input.addEventListener('keypress', (event) => {
			const ENTER = 13
			if (event.keyCode === ENTER)
				addTodo();
		})
//	})

	
	//나의 정보 수정하기 
const modifyForm = document.modifyForm;

function sendit(){
    
    const userpw = modifyForm.userpw;
    const userpw_re = modifyForm.userpw_re;
    if(userpw.value == ""){
        alert("비밀번호를 입력하세요!");
        userpw.focus();
        return false;
    }
    const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;
    if(!reg.test(userpw.value)){
    	alert("비밀번호는 8자 이상, 숫자, 대문자, 소문자, 특수문자를 모두 하나 이상 포함해야 합니다!");
    	userpw.focus();
    	return false;
    }
    if(/(\w)\1\1\1/.test(userpw.value)){
    	alert("같은 문자를 4번 연속해서 사용할 수 없습니다!")
    	userpw.focus();
    	return false;
    }
    if(userpw.value.search(/\s/) != -1){
    	alert("비밀번호는 공백을 포함할 수 없습니다!");
    	userpw.focus();
    	return false;
    }
    if(userpw_re.value == ""){
    	alert("비밀번호 확인을 해주세요!");
    	userpw_re.focus();
    	return false;
    }
    if(userpw.value != userpw_re.value){
    	alert("비밀번호 확인을 다시 해주세요!");
    	userpw.focus();
    	return false;
    }
    
    const username = modifyForm.username;
    if(username.value == ""){
        alert("이름을 입력하세요!");
        username.focus();
        return false;
    }
    const useremail = modifyForm.useremail;
    if(useremail.value==""){
    	alert("이메일을 입력해 주세요.");
    	useremail.focus()
    	return false;
    }else{
    	if(document.getElementById('proofok').innerHTML != "인증이 완료되었습니다."){
    		alert("이메일 인증을 완료해주세요");
    	return false;
    	}
    }
	
    const zipcode = modifyForm.zipcode;
    if(zipcode.value == ""){
        alert("주소찾기를 진행해주세요!");
        sample6_execDaumPostcode();
        return false;
    }

    const addrdetail = modifyForm.addrdetail;
    if(addrdetail.value == ""){
        alert("나머지 주소를 입력해주세요.")
        addrdetail.focus();
        return false;
    }
    alert('정보 수정 성공!');
    return true;
}

//이메일인증~
    const btn = document.getElementById('modify_proof');

    const epf = document.getElementById('email_proof_form');

     epf.addEventListener('submit', function(event) {
       event.preventDefault();

       const serviceID = 'default_service';
       const templateID = 'template_x5kekce';

       emailjs.sendForm(serviceID, templateID, this)
        .then(() => {
        }, (err) => {
          alert(JSON.stringify(err));
        });
    });
    const cloneusername= epf.cloneusername;
    const cloneemail = epf.cloneemail;
    const message = epf.message;
    const username = modifyForm.username;
    const useremail = modifyForm.useremail;

	 let proofnum = 0;
    function proof(){
    	//username.value!=""&&
    	if(useremail.value!=""){
    	proofnum = Math.floor(Math.random() * (9999 - 1000 + 1)) + 1000 ;
      message.value = proofnum;
      //cloneusername.value = username.value;
      cloneemail.value = useremail.value;
     // document.getElementById('proofchecktr').style.display ="table-row";
     	console.log(proofnum);
     	alert('입력하신 메일주소에서 인증번호를 확인해 주세요');
     	//이메일 인증번호 밑에 주석 해제해야 이메일 날라감
//      btn.click();
      }else {
		alert('이메일을 입력해 주세요');
	}
    }
    function proofcheckaction() {
		if (modifyForm.proofcheck.value==proofnum&&proofnum!=0) {
			//modifyForm.proofok.style.display = "inline";
			document.getElementById('proofok').innerHTML = "인증이 완료되었습니다.";
			document.getElementById('proofok').style.color = "red";
			modifyForm.modify_proofcheck_btn.style.display = "none";
			modifyForm.modify_proof_btn.style.display = "none";
			document.getElementById("email_box").style.display = "none";
			modifyForm.proofcheck.style.display = "none";
			useremail.readOnly = true;
		}else {
			alert("인증 실패! 다시시도해 주세요")
		}
	}
    
function pwcheck(){
    const userpw = modifyForm.userpw;
    const userpw_re = modifyForm.userpw_re;
    if(userpw.value != userpw_re.value){
        if(userpw_re.value ==""){}else{
        document.getElementById("pwtxt").innerHTML = "비밀번호와 비빌번호 확인이 다릅니다";
        document.getElementById("pwtxt").style.color = "red";
    }}else{
        if(userpw.value ==""){}else{
        document.getElementById("pwtxt").innerHTML = "비밀번호 확인 완료";
        document.getElementById("pwtxt").style.color = "red";
        }
    }
   
}
