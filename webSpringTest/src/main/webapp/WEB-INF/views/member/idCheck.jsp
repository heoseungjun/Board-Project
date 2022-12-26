<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.topMenu, .bottom{
		display:none;
	}
	b{color:blue;}
</style>
<script>
	$(function(){
		$("#idCheckForm").submit(function(){
			if($("#userid").val()==""){
				alert("아이디를 입력하세요");
				return false;
			}
			return true;
		});
	});
	function setUserid(successId){
		// DB 검색하여 사용가능한 아이디를 회원 등록폼에 표시
		opener.document.getElementById("userid").value=successId;
		opener.document.getElementById("idCheckStatus").value="Y";
		window.close();
	}
</script>
<div>
	<div>
		<c:if test="${result==0}">
			<b>${userid}</b>는 사용 가능한 아이디입니다.
			<input type="button" value="사용하기" onclick="setUserid('${userid}')"/>
		</c:if>
		<c:if test="${result==1}">
			<b>${userid}</b>는 이미 사용 중인 아이디입니다.
		</c:if>
	</div>
	<hr>
	<div>
	<form action="/myapp/member/idCheck">
		아이디 입력 : <input type="text" name="userid" id="userid"/>
				   <input type="submit" value="중복확인" onsubmit="return idCheck()" id="idCheckForm"/>	
	</form>
	</div>
</div>