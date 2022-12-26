<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	#frm>ul{
		overflow:hidden;
	}
	#frm>ul>li{
		float:left;
		padding:10px 0px;
		width:20%;
	}
	#frm>ul>li:nth-child(2n){
		width:80%;
	}
	#subject,#content{
		width:80%;
	}
	#content{
		height:200px;
	}
	.fDel:hover{
		cursor:pointer;
	}
	#filename1{
		margin:0px 0px 10px;
	}
	
</style>
<script>
	var cnt=${cnt};
	$(function(){
		// x를 누르면 기존 첨부파일 삭제, 새로운 첨부파일 선택할 수 있는 input 태그가 나타남
		$("#frm b.fDel").click(function(){
			// $(this) : 방금 이벤트가 발생한 객체  /  b태그의 부모인 div태그가 사라지게 하기
			$(this).parent().css("display","none");
			// input 태그의 type을 hidden->file로 변경
			$(this).parent().next().children().attr("type","file");
			// 삭제한 파일의 정보가 있는 input 태그의 name 속성을 delFile로 설정하기
			$(this).parent().next().next().attr("name","delFile");
			// 첨부파일의 개수를 감소
			cnt--;
		});
		
		// 데이터 유효성 검사
		$("#frm").submit(function(){
			// 제목
			if($("#subject").val()==""){
				alert("제목을 입력해주세요");
				return false;
			}
			// 파일 수
			if($("#filename1").val()!="") cnt++;
			if($("#filename2").val()!="") cnt++;
			
			if(cnt<1){
				alert("첨부파일을 1개 이상 등록해야 합니다");
				return false;
			}
			return true;
		});
	});
</script>
<div class="container">
	<h1>자료실 글 수정 폼</h1>
	<form method="post" action="/myapp/data/dataEditOk" enctype="multipart/form-data" id="frm">
		<input type="hidden" name="no" value="${vo.no}"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="subject" id="subject" value="${vo.subject}"/></li>
			<li>내용</li>
			<li>
				<textarea name="content" id="content">${vo.content}</textarea>
			</li>
			<li>첨부파일</li>
			<li>
				<!-- 첫 번째 첨부파일 -->
				<div>${vo.filename1}&nbsp;&nbsp;&nbsp;&nbsp;<b class="fDel">X</b></div>
				<div><input type="hidden" name="filename" id="filename1"/></div>
				<input type="hidden" name="" value="${vo.filename1}"/>  <!-- 삭제한 파일명 정보, name이 없기 때문에 서버로 가지 않음 -->
				
				<!-- 두 번째 첨부파일이 있을 때 -->
				<c:if test="${vo.filename2!=null && vo.filename2!=''}">
					<div>${vo.filename2}&nbsp;&nbsp;&nbsp;&nbsp;<b class="fDel">X</b></div>
					<div><input type="hidden" name="filename" id="filename2"/></div>
					<input type="hidden" name="" value="${vo.filename2}"/>  <!-- 삭제한 파일명 정보, name이 없기 때문에 서버로 가지 않음 -->
				</c:if>
				
				<!-- 두 번째 첨부파일이 없을 때 -->
				<c:if test="${vo.filename2==null || vo.filename2==''}">
					<div><input type="file" name="filename" id="filename2"/></div>
				</c:if>
			</li>
			<li><input type="submit" value="수정"/></li>
		</ul>
	</form>
</div>