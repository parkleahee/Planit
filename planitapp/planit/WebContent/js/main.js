/**
 * 
 */

	document.addEventListener('DOMContentLoaded', () => {
		const input = document.querySelector('#todo')
		const addButton = document.querySelector('#add-button')
		const todoList = document.querySelector('#todo-list')
		// const alert = document.querySelector('span')

		  // '+' 버튼 익명 화살표 함수 
		const addTodo = () => {
			if (input.value !== '') {
				const item = document.createElement('div')
			  // 체크박스
				const checkbox = document.createElement('input')
				checkbox.type='checkbox'
			 // text
				const text = document.createElement('span');
			  // 제거하기 버튼
				const deleteButton = document.createElement('button')
				deleteButton.textContent="x"
			// 중요표시 버튼 
				const importantButton = document.createElement('button')
				importantButton.textContent="x"

				text.textContent = input.value
				input.value=''
			
				item.appendChild(checkbox)
				item.appendChild(text)
				item.appendChild(importantButton)
				item.appendChild(deleteButton)
				todoList.appendChild(item)

		// 체크박스 이벤트 리스너
				// checkbox.addEventListener('change', (event) =>{ 
				// 	if (event.currentTarget.checked)
				// 	{
				// 		text.style.textDecoration='line-through'
				// 	}
				// 	else {
				// 		text.style.textDecoration='none'
				// 	}
				// })

				importantButton.addEventListener('click', (event) =>{
					text.style.backgroundColor='rosybrown'
				})

			  // 제거하기 버튼 클릭 이벤트 리스너
				deleteButton.addEventListener('click', (event) => {
					todoList.removeChild(event.currentTarget.parentNode)
				})
				input.value =''
				alert.textContent = ''
			}
			else
				alert.textContent = ''
		}
		//중요 버튼눌렀을때 글짜 배경색 바뀌게 설정하기 

		addButton.addEventListener('click', addTodo)
  
		  // 할 일 입력창에서 enter key가 눌렸을 때
		input.addEventListener('keypress', (event) => {
			const ENTER = 13
			if (event.keyCode === ENTER)
				addTodo();
		})
	})
