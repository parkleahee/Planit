<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<style>
	body{ background-color:#fff; }
	table{
		border:0px;
		width:900px;
	}
	.title h3{
		font-size: 1.5em;
		color:rgb(0, 0, 0);
	}
	.list{
		border-collapse: collapse;
		border-spacing: 0;
	}

	.list > tbody > tr > th{
		border-top: 1px solid #384d75;
		border-bottom: 1px solid #ccc;
		padding:5px;
	}
	.list > tbody > tr > td{
		border-bottom: 1px solid #ccc;
		padding: 5px;
		text-align: center;
	}
	a{
		display:inline-block;		
		color:black;
		font-weight: bold;
		text-decoration: none;
	}
	.list a{
		background-color: transparent;
		color:#424242;
	}
	.header_area a{
		width:100px;
		padding:10px;
		text-align: center;
	}
	.header_area span{ font-weight: bold; }
	.pagination a{
		padding: 5px;
		width:20px;
		height: 20px;
	}
	.nowPage{
		padding: 5px;
		width:20px;
		height:20px;
		display: inline-block;
		border-radius: 3px;
		color:black;
		font-weight: bold;
	}
	a.write{
		padding: 5px;
		width:70px;
		height:25px;
		text-align: center;
	}
	.search_area { text-align: center; vertical-align: middle; }
	.search_area #q{
		outline: none;
		/* border:2px solid rgb(0,200,80); */
		width:300px;
		padding:10px;
	}
	.search_area [type=button]{
		width:70px;
        background-color: black;
		padding:10px;
		height:38px;
		display:inline-block;
		border:1px solid rgb(0, 0, 0);
		position: relative;
		top:1.5px;
		color:#fff;
		font-weight: bold;
	}

    #logout{
        background-color: black;
        color: white;
    }
</style>
</head>
<body>
	<%-- <c:if test="${loginUser == null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace('${cp}/user/userlogin.us');
		</script>
	</c:if> --%>

	<div style="margin:0 auto; width:1000px;">
		<!— 로그아웃 버튼 배치할 테이블 —>
		<table class="header_area">
			<tr align="right" valign="middle">
				<td>
					<a id="logout" href="${cp}/user/userlogout.us">로그아웃</a>
				</td>			
			</tr>
		</table>

		<!— 타이틀과 글 개수 띄워주는 테이블 —>
		<table class="title">
			<tr align="center" valign="middle">
				<td><h3>문의 내역</h3></td>
			</tr>
			<tr align="right" valign="middle">
				<td>글 개수 : ${totalCnt}</td>
			</tr>
		</table>

		<!— 게시글 리스트 띄우는 테이블 —>
		<table class="list">
			<tr align="center" valign="middle">
				<th width="8%">번호</th>
				<th width="50%">제목</th>
				<th width="15%">작성자</th>
				<th width="17%">날짜</th>
			</tr>
			<c:choose>
				<c:when test="${boardList != null and boardList.size()>0 }">
					<c:forEach items="${boardList}" var="board">
						<tr>
							<td>${board.boardnum}</td>
							<td><a href="${cp}/board/boardview.bo?boardnum=${board.boardnum}&page=${page}&q=${keyword}">${board.boardtitle}</a></td>
							<td>${board.userid}</td>
							<td>${board.regdate}<c:if test="${board.regdate != board.updatedate}">(수정됨)</c:if></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5" style="text-align: center; font-size: 20px;">등록된 게시글이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table><br>

		<!— 페이징 처리하는 테이블 —>
		<table class="pagination">
			<tr align="center" valign="middle">
				<td>
					<c:if test="${startPage != 1}">
						<a href="">&lt;</a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage}">
						<c:if test="${i == page}">
							<span class="nowPage">${i}</span>
						</c:if>
						<c:if test="${i != page }">
							<a href="">${i}</a>
						</c:if>
					</c:forEach>
					<c:if test="${endPage != totalPage}">
						<a href="">&gt;</a>
					</c:if>
				</td>
			</tr>
		</table> 
		<!— 글쓰기 버튼 배치하는 테이블 —>
		<div class="search_area">
			<input type="search" id="q" name="q" value="${keyword}"> <input type="button" value="검색" onclick="search()">
		</div>
	</div>
</body>
<script>
	function search(){
		const q = document.getElementById("q");
		//유효성 검사
		if(q.value == ""){
			alert("검색어를 입력해주세요!");
			q.focus();
			return false;
		}
		location.href = "${cp}/board/boardlist.bo?q="+q.value;
	}

</script>
</html>