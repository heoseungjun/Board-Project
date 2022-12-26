<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	a{color:black;}
</style>
<script>
	function delCheck(){
		if(confirm("이 글을 정말 삭제하시겠습니까?")){
			location.href="/myapp/board/boardDel?no=${vo.no}";
		}
	}
	
	// ajax 댓글 처리
	$(function(){
		// 현재 글의 댓글 목록 가져오기
		function replyList(){
			var params = {no:${vo.no}};
			var url="/myapp/replyList";
			$.ajax({
				url:url,
				data:params,
				type:"GET",
				success:function(result){
					$result=$(result);
					var tag="";
					
					$result.each(function(idx,vo){
						tag += "<li>";
						tag += "<div><b>"+vo.userid+"</b>("+vo.writedate+")";
						if(vo.userid=='${logid}'){
							tag += "<input type='button' value='수정'/>";
							tag += "<input type='button' value='삭제'/>";
						}
						tag += "</div>";
						tag += "<div>"+vo.coment+"</div>";
						if(vo.userid=='${logid}'){    // 본인이 쓴 글일 때 수정폼 띄우기
							tag += "<div style='display:none'>";
							tag += "<form method='post'>"
							tag += "<textarea name='coment' rows='3' cols='50'>"+vo.coment+"</textarea>"
							tag += "<input type='hidden' name='replyno' value='"+vo.replyno+"'/>";
							tag += "<input type='submit' value='수정'/>";
							tag += "</form>"
							tag += "</div>";
						}
						tag += "</li>";
					});
					console.log(result);
					// html() : 기존 내용을 지우고 새로(html 태그) 추가한다
					// text() : 기존 내용을 지우고 문자로 추가
					$("#replyList").html(tag);
				},error:function(error){
					console.log(error);
				}
			});
		}
		// 댓글 쓰기 폼 submit 발생
		$("#replyBtn").click(function(){
			// form 태그의 action 제거(현재 페이지 리로드)
			if($("#coment").val()==""){
				alert("댓글 내용을 입력하세요");
				return false;
			}
			var params = $("#replyForm").serialize();    // 폼의 데이터를 쿼리로 만들어주는 함수 -> no=99&coment=dnjs
			var url = "/myapp/replyBoardWrite";
			
			$.ajax({
				url:url,
				data:params,
				type:"POST",
				success:function(result){
					console.log(result);
					// 댓글 등록 후 쓴 댓글 화면에서 지우기
					$("#coment").val("");
					// 해당 원글 댓글리스트 다시 가져오기
					replyList();
				},error:function(e){
					console.log(e.responseText);
				}
			});
		});
		// 댓글 수정 폼 보여주기
		//			  이벤트 종류   선택자
		$(document).on('click','#replyList input[value=수정]',function(){
			// 댓글은 숨기고 댓글 수정폼은 보여주기
			$(this).parent().next().css("display","none");    // 댓글 숨김
			$(this).parent().next().next().css("display","block");
		});
		// 댓글 수정하기 (DB)
		$(document).on('submit','#replyList form',function(e){
			e.preventDefault();
			var params = $(this).serialize();
			var url = "/myapp/replyBoardEditOk";
			console.log(params);
			$.ajax({
				url : url,
				data : params,
				type : "POST",
				success:function(result){
					console.log(result);
					replyList();
				},error:function(e){
					console.log(e.responseText);
				}
			});
			return false;
		});
		// 처음 글 내용보기로 왔을 때 댓글을 비동기식으로 가져오는 함수 호출
		replyList();
	});
</script>
<div class="container">
	<h1>글 내용 보기</h1>
	<ul>
		<li>글 번호</li>
		<li>${vo.no}</li>
		<li>글쓴이</li>
		<li>${vo.userid}</li>
		<li>등록일</li>
		<li>${vo.writedate}</li>
		<li>조회수</li>
		<li>${vo.hit}</li>
		<li>제목</li>
		<li>${vo.subject}</li>
		<li>내용</li>
		<li>${vo.content}</li>
	</ul>
	<div>
		<c:if test="${vo.userid}">
		<a href="/myapp/board/boardEdit?no=${vo.no}">수정 </a>
		<a href="javascript:delCheck()">삭제</a>
		</c:if>
	</div>
	<hr/>
	<style>
		#coment{
			width:400px;
			height:60px;
		}
		#replyList>li{
			border-bottom:1px solid #ddd;
			padding:5px 0px;
		}
	</style>
	<!-- 댓글 등록 폼 / ajax(비동기식)으로 처리하므로 action은 없음 -->
	<form method="post" id="replyForm">
		<!-- 원글번호 -->
		<input type="hidden" name="no" value="${vo.no}"/>  
		<textarea name="coment" id="coment"></textarea>
		<input type="button" value="댓글 등록" id="replyBtn"/>
	</form>
	<!-- 댓글 목록 -->
	<ul id="replyList">
	</ul>
</div>