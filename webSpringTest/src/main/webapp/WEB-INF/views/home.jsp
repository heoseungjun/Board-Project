<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.container>a{color:black;}
</style>
<div class="container">
	<h1 style="text-align:center">홈페이지 
		<a href="/myapp/ajax/ajaxView">Ajax로 이동</a>
	</h1>
	<a href="/myapp/img/최성은.jpg"><img src="<%=request.getContextPath() %>/img/최성은.jpg"/ width="100%"></a>
	<a href="/myapp/img/001.png" download>gif,png,jpg,pdf,xls,ppt,zip,exe...는 donwnload 속성을 써주세요</a>
</div>