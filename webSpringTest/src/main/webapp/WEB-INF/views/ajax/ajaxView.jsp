<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	$(function(){
		// 비동기식으로 서버에 접속하여 문자열을 결과값으로 얻어오기
		$("#ajaxString").click(function(){
			var params="num=12&name=홍길동";
			$.ajax({
				url:"/myapp/ajaxString",    // 요청 매핑주소
				data:params,				// 서버로 보낼 값
				type:"GET",					// 전송 방식
				success:function(result){   // 서버에서 정상 응답할 경우
					// append() : 마지막에 추가
					// prepend() : 제일 앞에 추가
					$("#ajaxResult").append(result);
				},error:function(e){    // 서버에서 응답받지 못했을 때(변수는 마음대로)
					console.log(e.responseText);
				}
			});
		});
		// 비동기식으로 서버에서 객체 받아오기
		$("#ajaxObject").click(function(){
			var params={
				username:"허승준",
				tel:"010-1234-5678",
				age:23
			};
			
			var url="/myapp/ajaxObject";
			
			$.ajax({
				url:url,
				data:params,
				dataType:"json",
				type:"GET",
				success:function(result){
					var tag="<ol>";
					tag += "<li>번호=>"+result.no+"</li>";
					tag += "<li>제목=>"+result.subject+"</li>";
					tag += "<li>글쓴이=>"+result.userid+"</li>";
					tag += "</ol>";
					$("#ajaxResult").append(tag);
				},error:function(error){
					console.log(error.responseText);
				}
			});
		});
		// 비동기식으로 서버에 접속하여 List 받아오기
		$("#ajaxList").click(function(){
			$.ajax({
				url:"/myapp/ajaxList",
				data:{nowPage:4},    // "nowPage=2";
				type:"GET",    // 컨트롤러 접속
				success:function(result){
					// List의 객체를 순차적으로 접근하기 위해 $(reseult)
					var $result=$(result);
					var tag="<ul>";
					
					$result.each(function(idx,obj){
						tag+="<li>"+obj.no+"</li>";
						tag+="<li>"+obj.subject+"</li>";
						tag+="<li>"+obj.writedate+"</li>";
					});
					tag+="</ul>";
					
					$("#ajaxResult").append(tag);
				},error:function(e){
					console.log(e.responseText);
				}
			});
		});
		// 비동기식으로 Map 객체 얻어오기
		$("#ajaxMap").click(function(){
			$.ajax({
				url:"/myapp/ajaxMap",
				success:function(result){
					console.log(result);
					var tag="<ul>";
					tag += "<li>총 레코드 수 : "+result.totalRecord+"</li>";
					tag += "<li>메시지 : "+result.msg+"</li>";
					tag += "<li>글 번호 : "+result.boardVO.no+"<br>";
					tag += "제목 : "+result.boardVO.subject+"<br>";
					tag += "날짜 : "+result.boardVO.writedate+"</li></ul>";
					
					$("#ajaxResult").append(tag);
				},error:function(result){
					console.log(e.responseText);
				}
			});
		});
	});
</script>
<div class="container">
	<h1>Ajax : 동기식, 비동기식으로 요청 처리</h1>
	<pre>
		pom.xml에서 gson 프레임워크 추가
	</pre>
	<div>
		<input type="button" value="String" id="ajaxString"/>
		<input type="button" value="Object" id="ajaxObject"/>
		<input type="button" value="List" id="ajaxList"/>
		<input type="button" value="Map" id="ajaxMap"/>
	</div>
	<div id="ajaxResult"></div>
</div>