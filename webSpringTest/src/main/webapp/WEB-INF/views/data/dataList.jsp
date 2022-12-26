<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	#write>a,#dataDiv>li>a{
		color:black;
	}
	#dataDiv{
		overflow:auto;
	}
	#dataDiv>li{
		width:10%;
		float:left;
		height:40px;
		line-height:40px;
		border-bottom:1px solid #ddd;
	}
	#dataDiv>li:nth-child(6n+2){
		width:50%;
	}
</style>
<div class="container">
	<h1>자료실 목록</h1>
	<div id="write">
		<a href="/myapp/data/dataForm">자료 등록</a>
	</div>
	<ul id="dataDiv">
		<li>번호</li>
		<li>제목</li>
		<li>작성자</li>
		<li>조회수</li>
		<li>첨부파일</li>
		<li>등록일</li>
		<c:forEach var="vo" items="${list}">
			<li>${vo.no}</li>
			<li><a href="/myapp/data/dataView/?no=${vo.no}">${vo.subject}</a></li>
			<li>${vo.userid}</li>
			<li>${vo.hit}</li>
			<li>
				<a href="/myapp/upload/${vo.filename1}" download><img src="/myapp/img/disk.png" title="${vo.filename1}"/></a>  <!-- title은 마우스 오버 시 뜨는 이름 -->
				<c:if test="${vo.filename2!=null && vo.filename2!=''}">
					<a href="/myapp/upload/${vo.filename2}" download><img src="/myapp/img/disk.png" title="${vo.filename2}/"></a>
				</c:if>
			</li>
			<li>${vo.writedate}</li>
		</c:forEach>
	</ul>
</div>