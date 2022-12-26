<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	li{
		list-style-type:none;
	}
</style>
<script>
	$(function(){
		$("#searchZipcode").click(function(){
			window.open("/myapp/member/zipcodeSearch","zip","width=600px height=500px");
		});
	});
</script>
<div>
	<h1>회원정보 수정</h1>
	<form method="post" action="/myapp/member/updateForm">
		<ul>
			<li>아이디</li>
			<li><input type="text" name="userid" id="userid" value="${vo.userid}" readonly/>
			</li>
			<li>비밀번호</li>
			<li><input type="password" name="userpwd" id="userpwd" value="${vo.userpwd}" readonly/></li>
			<li>이름</li>
			<li><input type="text" name="username" id="username" value="${vo.username}"/></li>
			<li>연락처</li>
			<li>
				<input type="text" name="tel1" id="tel1" value="${vo.tel1}"/>
				<input type="text" name="tel2" id="tel2" value="${vo.tel2}"/>
				<input type="text" name="tel3" id="tel3" value="${vo.tel3}"/>
			</li>
			<li>이메일</li>
			<li><input type="text" name="email" id="email" value="${vo.email}"/></li>
			<li>우편번호</li>
			<li><input type="text" name="zipcode" id="zipcode" value="${vo.zipcode}"/>
				<input type="button" value="우편번호 검색" id="searchZipcode"/></li>
			<li>주소</li>
			<li><input type="text" name="addr" id="addr" value="${vo.addr}" style="width:80%;"/></li>
			<li>상세주소</li>
			<li><input type="text" name="detailaddr" id="detailaddr" value="${vo.detailaddr}"/></li>
			<li><input type="submit" value="수정"/></li>
		</ul>
	</form>
</div>