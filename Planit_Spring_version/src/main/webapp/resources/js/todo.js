/*
function f(){
	function insert(reply,callback){
		$.ajax({
			type:"POST",
			url:cp+"/reply/regist",
			data:JSON.stringify(reply),
			contentType:"application/json; charset=utf-8",
			success:function(result){
				callback();
			}
		})
	}
	return {add:insert,getList,remove,modify,displayTime}
}
const replyService = f();
*/


const todoService = (function(){
			// 외부에서 객체로 내용을 넘겨줌 자바스크립트 객체로 (ajax통신)
	function insert(reply,callback){
		$.ajax({
			type:"POST",
			url:cp+"/schedule/regist",
			// 요청보낼 url
			data:JSON.stringify(reply),
			//url을 통해 보낼 데이터 -> 매개변수로 들어온 reply에 담겨 있는 값들 
			//객체를 기반으로 JSON을 만들어준다. 
			//JSON이라는 형태를 POST로 보내면 RequestBody(내용)을 포함해서 보낸다. 
			// ->JSON이라는게 포함해서 Body로 날라간다. 
			contentType:"application/json; charset=utf-8",
			// 보내고있는 데이터의 형태 
			success:function(result,status,xhr){
									//응답코드
			//ajax통신이 성공한 결과 -> 함수 호출 	
				// 특정함수를 쓰면 매개변수로 날라옴 
				callback(result)
				// 내부에서 콜백 함수 호출 -> ajax통신의 결과를 넘겨주면서 
			}
		})
		
//		get.jsp에서 JSON형식으로 ajax통신이 성공했을때 받은 result를 콜백함수에서 다시 받아서 
// 		다시 get.jsp의 replyservice.add의 function에서 result를 받아 
// 		해당하는 함수를 실행하고 그 안에서 dom구현을 하게 된다. 
	}
	
	function selectAll(data,callback){
		// 날라온 데이터 받아서 셋팅 
		let userid = data;
		console.log(userid);
		// ajax통신을 JSON으로 받아옴 
		$.getJSON(
				//1. url (json화해서 받아옴 (.json))
			cp+"/schedule/todolist/"+userid+".json",
			function(data){
				//매개변수로 넣어줌 
				callback(data)
			}
		)
	}
	
	function drop(todonum,callback,error){
		console.log("들어옴1");
		$.ajax({
			// 내부에서 객체를 넘겨준다. 
			type:"DELETE",
			//REST방식의 개발 -> get(select의 역할)
			url:cp+"/schedule/"+todonum,
			success:function(result,status,xhr){
				if(callback){
					console.log("들어옴2");
					callback(result);
				}
			},
			error:function(xhr,status,err){
				if(error){
					error(err);
				}
			}
		})
	}
	
	function importTodo(todonum,callback){
		$.ajax({
			type:"PUT",
			url:cp+"/schedule/"+todonum,
			data:JSON.stringify(todonum),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(err){
				alert("에러발생 : "+err)
			}
		})
	}
	
	function checkTodo(todonum,callback){
		$.ajax({
			type:"PATCH",
			url:cp+"/schedule/"+todonum,
			data:JSON.stringify(todonum),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(err){
				alert("에러발생 : "+err)
			}
		})
	}
	
	
	//{프로퍼티:값,메소드명:함수(){}}
	// 선언과 동시에 호출할수 있도록 -> 마지막 리턴되는 것이 리플라이서비스에 담길 수 있도록
	return {add:insert,getList:selectAll,remove:drop,importTodo:importTodo,checkTodo:checkTodo}
})()


 
const timeService = (function(){
	
	function timeDelete(timenum,callback,error){
		console.log("들어옴1");
		$.ajax({
			// 내부에서 객체를 넘겨준다. 
			type:"DELETE",
			//REST방식의 개발 -> get(select의 역할)
			url:cp+"/schedule/"+timenum,
			success:function(result,status,xhr){
				if(callback){
					console.log("들어옴2");
					callback(result);
				}
			},
			error:function(xhr,status,err){
				if(error){
					error(err);
				}
			}
		})
	}
	
	//{프로퍼티:값,메소드명:함수(){}}
	// 선언과 동시에 호출할수 있도록 -> 마지막 리턴되는 것이 리플라이서비스에 담길 수 있도록
	return {timeDelete:timeDelete}
})()

const goalService = (function(){
	function addGoal(data,callback,error){
	    console.log("들어옴1");
	    console.log(data);
	    console.log(data.goal);
	    
	    let goal = data.goal;
	    console.log(goal);
	    // ajax통신을 JSON으로 받아옴 
	    $.ajax({
	        // 내부에서 객체를 넘겨준다. 
	        type:"POST",
	        //REST방식의 개발 -> get(select의 역할)
	        url:cp+"/schedule/"+goal,
	        success:function(result,status,xhr){
	            if(callback){
	                console.log(result);
	                callback(result);
	            }
	        },
	        error:function(xhr,status,err){
	            if(error){
	                error(err);
	            }
	        }
	    })
	}
	
	function cntGoal(data,callback,error){
		console.log("들어옴1");
		console.log(data);
		console.log(data.goal);
		console.log(data.goalnum);
		
		let goal = data.goal;
		let goalnum = data.goalnum;
		console.log(goal);
		console.log(goalnum);
		// ajax통신을 JSON으로 받아옴 
		$.ajax({
			// 내부에서 객체를 넘겨준다. 
			type:"POST",
			//REST방식의 개발 -> get(select의 역할)
			url:cp+"/schedule/cntgoalview/"+goal+"/"+goalnum,
			success:function(result,status,xhr){
				if(callback){
					console.log("들어옴2");
					console.log(result);
					callback(result);
				}
			},
			error:function(xhr,status,err){
				if(error){
					error(err);
				}
			}
		})
	}
	
	//{프로퍼티:값,메소드명:함수(){}}
	// 선언과 동시에 호출할수 있도록 -> 마지막 리턴되는 것이 리플라이서비스에 담길 수 있도록
	return {addGoal:addGoal,cntGoal:cntGoal}
})()










