<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result==0}">
	<script>
		alert("글이 삭제되지 않았습니다");
		history.back();
	</script>
</c:if>
<c:if test="${result>0}">
	<script>
		location.href="/myapp/board/boardList";
	</script>
</c:if>