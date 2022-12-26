<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.topMenu,.bottom{
		display:none;
	}
	li{
		list-style-type:none;
	}
	li:hover{
		text-decoration:underline;
		cursor:pointer;
	}
</style>
<script>
	$(function(){
		//도로명을 입력 후 우편번호 주소 검색
		$("#zipForm").submit(function(){
			if($("#doroName").val()==""){
				alert("도로명을 입력해주세요");
				return false;
			}
			return true;
		});
		
		// 우편번호와 주소를 회원가입폼에 표시
		$("#zipList>li").click(function(){
			// 이벤트가 발생한 li태그의 내용을 얻어오기
			var zipAddr=$(this).text();
			var zipcode=zipAddr.substring(0,5);
			var addr=zipAddr.substring(6);
			
			// 회원가입 폼에 표시
			opener.document.getElementById("zipcode").value=zipcode;
			opener.document.getElementById("addr").value=addr;
			
			// 현재 윈도우 닫기
			self.close();
		});
	});
</script>
<div>
	<form id="zipForm" action="/myapp/member/zipcodeSearch">
		도로명을 입력 후 우편번호를 검색하세요<br>
		(ex.백범로 22길)<br>
		도로명 : <input type="text" name="doroName" id="doroName"/>
		<input type="submit" value="주소 검색">
	</form>
	<hr>
	<ul id="zipList">
		<c:if test="${zipList!=null}">
			<c:forEach var="zipVO" items="${zipList}">
				<li>${zipVO.zipcode} ${zipVO.sido} ${zipVO.doroName}, (${zipVO.dong} ${zipVO.num1}<c:if test="${zipVO.num2>0}">-${zipVO.num2}</c:if>-${zipVO.num2} ${zipVO.buildName})</li>
			</c:forEach>
		</c:if>
	</ul>
</div>