<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	li{list-style-type:none};
</style>
<script>
	function formCheck(){
		if(document.getElementById("subject").value==""){
			alert("제목을 입력하세요");
			return false;
		}
		if(document.getElementById("content").value==""){
			alert("글 내용을 입력하세요");
			return false;
		}
		return true;
	}
</script>
<div class="container">
	<h1>글쓰기 폼</h1>
	<form method="post" action="/myapp/board/boardWriteOk" onsubmit="return formCheck()">
	<ul>
		<li>제목</li>
		<li><input type="text" name="subject" id="subject" size="50"/></li>
		<li>글내용</li>
		<li>
			<textarea name="content" id="content" rows="20" cols="100"></textarea>
		</li>
		<li><input type="submit" value="등록"/></li>
	</ul>
	</form>
</div>