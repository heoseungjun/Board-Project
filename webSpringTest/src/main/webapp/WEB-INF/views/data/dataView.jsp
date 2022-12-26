<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function dataDel(){
		if(confirm("이 글을 정말 삭제하시겠습니까?")){
			location.href="/myapp/data/dataDel/${vo.no}";
		}
	}
</script>
<div id="container">
	<h1>글 내용 보기</h1>
	<ul>
		<li>번호 : ${vo.no}</li>
		<li>글쓴이 : ${vo.userid}</li>
		<li>등록일 : ${vo.writedate}</li>
		<li>조회수 : ${vo.hit}</li>
		<li>제목 : ${vo.subject}</li>
		<li>내용 : ${vo.content}</li>
		<li>첨부파일 : 
			<a href="/myapp/upload/${vo.filename1}" download>${vo.filename1}</a>
			<c:if test="${vo.filename2!=null && vo.filename2!=''}">
				<a href="/myapp/upload/${vo.filename2}" download>${vo.filename2}</a>
			</c:if>
		</li>
	</ul>
	<div>
		<a href="/myapp/data/dataEdit?no=${vo.no}">수정</a>
		<a href="javascript:dataDel()">삭제</a>
	</div>
</div>