<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/myapp/js_css/board.css" type="text/css"/>
<script>
	function searchCheck(){
		// 검색어 입력 여부
		if(document.getElementById("searchWord").value==""){
			alert("검색할 내용을 입력해주세요");
			return false;
		}
		return true;
	}
</script>
<div class="container">
	<h1>게시판 목록</h1>
	<a href="/myapp/board/boardWrite">글쓰기</a>
	<ul class="boardList">
		<li>번호</li>
		<li>제목</li>
		<li>작성자</li>
		<li>등록일</li>
		<li>조회수</li>
		
		<c:forEach var="vo" items="${list}">
			<li>${vo.no}</li>
			<li class="word-cut"><a href="/myapp/board/boardView?no=${vo.no}">${vo.subject}</a></li>
			<li>${vo.userid}</li>
			<li>${vo.writedate}</li>
			<li>${vo.hit}</li>
		</c:forEach>
	</ul>
</div>
	<!-- 페이징 -->
	<div class="pageDiv">
		<ul>
			<!-- 이전 페이지 -->
			<c:if test="${pvo.nowPage==1}">
				<li>이전</li>
			</c:if>
			<c:if test="${pvo.nowPage>1}">
				<li><a href='/myapp/board/boardList?nowPage=${pvo.nowPage-1}<c:if test="${pvo.searchWord!=null}">&searchKey=${pvo.searchKey}&searchWord=${pvo.searchWord}</c:if>'>이전</a></li>
			</c:if>
			<!-- 페이지 번호 -->
			<c:forEach var="i" begin="${pvo.startPage}" end="${pvo.startPage+pvo.onePageCount-1}">
				<c:if test="${i<=pvo.totalPage}">
					<!-- 현재 페이지 -->
					<c:if test="${i==pvo.nowPage}">
						<li style="background-color:#ddd"><a href='/myapp/board/boardList?nowPage=${i}<c:if test="${pvo.searchWord!=null}">&searchKey=${pvo.searchKey}&searchWord=${pvo.searchWord}</c:if>'>${i}</a></li>
					</c:if>
					<!-- 현재 페이지가 아닐 때 -->
					<c:if test="${i!=pvo.nowPage}">
						<li><a href='/myapp/board/boardList?nowPage=${i}<c:if test="${pvo.searchWord!=null}">&searchKey=${pvo.searchKey}&searchWord=${pvo.searchWord}</c:if>'>${i}</a></li>
					</c:if>
				</c:if>
			</c:forEach>
			<!-- 다음 페이지 -->
			<c:if test="${pvo.nowPage<pvo.totalPage}">
				<li><a href='/myapp/board/boardList?nowPage=${pvo.nowPage+1}<c:if test="${pvo.searchWord!=null}">&searchKey=${pvo.searchKey}&searchWord=${pvo.searchWord}</c:if>'>다음</a></li>
			</c:if>
			<c:if test="${pvo.nowPage>=pvo.totalPage}">
				<li>다음</li>
			</c:if>
		</ul>
	</div>
	<!-- 검색어 처리 -->
	<div class="searchDiv">
		<form action="/myapp/board/boardList" onsubmit="return searchCheck()">
			<select name="searchKey">
				<option value="subject">제목</option>
				<option value="content">내용</option>
				<option value="userid">글쓴이</option>
			</select>
			<input type="text" name="searchWord" id="searchWord"/>
			<input type="submit" value="검색"/>
		</form>
	</div>
</div>