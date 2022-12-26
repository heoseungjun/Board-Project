<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.dataFormDiv{
		overflow:auto;
	}
	.dataFormDiv>li{
		width:20%;
		padding:10px 0px;
	}
	.dataFormDiv>li:nth:child(2n){
		width:80%;
	}
	#subject{
		width:90%;
	}
	#content{
		width:90%;
		height:150px;
	}
</style>
<script>
	function formCheck(){
		if(document.getElementById("subject").value==""){
			alert("제목을 입력해주세요");
			return false;
		}
		var fileCount=0;
		if(document.getElementById("filename1").value!=""){
			fileCount++;
		}
		if(document.getElementById("filename2").value!=""){
			fileCount++;
		}
		if(fileCount==0){
			alert("첨부파일은 반드시 하나 이상 등록해주세요");
			return false;
		}
		return true;
	}
</script>
<div id="container">
	<h1>자료 등록</h1>
	<!-- 첨부파일을 서버로 전송 : form 태그에 반드시 enctype을 정의해야 함 -->
	<form method="post" action="/myapp/data/dataFormOk" enctype="multipart/form-data" onsubmit="return formCheck()">
		<ul class="dataFormDiv">
			<li>제목</li>
			<li><input type="text" name="subject" id="subject"/></li>
			<li>내용</li>
			<li><textarea name="content" id="content"></textarea></li>
			<li>첨부파일</li>
			<li>
				<input type="file" name="filename" id="filename1"/><br/>
				<input type="file" name="filename" id="filename2"/>
			</li>
			<li><input type="submit" value="등록"/></li>
		</ul>
	</form>
</div>