<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	$(function(){
		$("#idChk").click(function(){
			if($("#userid").val()!=""){
			//                파일명                                              창이름      옵션
				window.open("/myapp/member/idCheck?userid="+$("#userid").val(),"idCheck","width=500px,height:400px");
			}else{
				alert("아이디를 입력해주세요");
			}
		});
		$("#userid").keyup(function(){
			$("#idCheckStatus").val("N");
		});
		
		// 우편번호 검색
		$("#searchZipcode").click(function(){
			window.open("/myapp/member/zipcodeSearch","zip","width=600px, height=500px");
		});
		
		// 회원가입 유효성검사
		$("#memForm").submit(function(){
			// 아이디 첫번째 문자는 영어
			// 영대소문자, 숫자, _만 가능
			// 길이는 8~15자리까지 허용
			
			// 정규표현식
			// ^ : 처음부터 문자를 검색     $ : 끝까지 검색
			// var reg=/^[A-Za-z]{1}[A-Za-z0-9]{7,14}$/
			var reg=/^[A-Za-z]{1}\w{7,14}$/    // \w : 영대소문자, 숫자, _를 포함한다는 뜻
			if(!reg.test($("#userid").val())){
				alert("아이디의 첫번째 문자는 영어, 나머지 글자는 영대소문자,숫자,_로만 구성해야 하며 7글자 이상이어야 합니다");
				return false;
			}
			// 비밀번호 확인
			if($("#userpwd").val() != $("#userpwd2").val()){
				alert("비밀번호가 서로 다릅니다")
				return false;
			}
			
			// 이름은 한글로 2~7글자까지 허용
			reg=/^[가-힣]{2,7}$/;
			if(!reg.test($("#username").val())){
				alert("이름은 한글이어야 하며, 2~7글자까지 입력 가능합니다");
				return false;
			}
			
			var tel=$("#tel1").val()+"-"+$("#tel2").val()+"-"+$("#tel3").val();
			console.log("tel->",tel);
			
			// 지역번호(통신사번호) : 010,02,031,032,041,042
			reg=/^(010|02|031|032|041|042)-[0-9]{3,4}-[0-9]{4}$/
			if(!reg.test(tel)){
				alert("전화번호를 다시 확인해주세요");
				return false;
			}
			
			// 이메일(gjtmdwns0815@naver.com)
			if($("#email").val()!=""){
				// 이메일 유효성 검사
				// ()? : 있어도 되고 없어도 됨
				reg=/^\w{6,15}[@][a-z0-9]{2,8}[.][a-z]{2,3}([.][a-z]{2,3})?$/
				if(!reg.test($("#email").val())){
					alert("잘못된 이메일 주소입니다");
					return false;
				}
			}
		
		});
	});
</script>
<style>
	li{list-style-type:none;}
</style>
<div class="container">
	<h1>회원가입</h1>
	<form method="post" action="/myapp/member/memberFormOk" id="memForm">
	<ul>
		<li>아이디</li>
		<li><input type="text" name="userid" id="userid"/>
			<input type="button" value="중복확인" id="idChk"/>
			<input type="hidden" value="N" id="idCheckStatus"/>
		</li>
		<li>비밀번호</li>
		<li><input type="password" name="userpwd" id="userpwd"/></li>
		<li>비밀번호 확인</li>
		<li><input type="password" name="userpwd2" id="userpwd2"/></li>
		<li>이름</li>
		<li><input type="text" name="username" id="username"/></li>
		<li>연락처</li>
		<li>
			<input type="text" name="tel1" id="tel1"/>
			<input type="text" name="tel2" id="tel2"/>
			<input type="text" name="tel3" id="tel3"/>
		</li>
		<li>이메일</li>
		<li><input type="text" name="email" id="email"/></li>
		<li>우편번호</li>
		<li><input type="text" name="zipcode" id="zipcode"/>
			<input type="button" value="우편번호 검색" id="searchZipcode"/></li>
		<li>주소</li>
		<li><input type="text" name="addr" id="addr" style="width:80%;"/></li>
		<li>상세주소</li>
		<li><input type="text" name="detailaddr" id="detailaddr"/></li>
		<li><input type="submit" value="회원가입"/></li>
	</ul>
	</form>
</div>