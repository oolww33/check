<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fithe.member.vo.MemberVO" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>UPDATE OK</title>
	</head>
	<%
		Object obj = request.getAttribute("listM");
		List<MemberVO> alist = (List)obj;
		int nCnt = alist.size();
	%>
	<body>
		세션 ID : <%=session.getId() %> <br>
		세션 생성 시간 : <%=session.getMaxInactiveInterval() %> ms<br>
	<%
		if(nCnt == 1){
	%>
		<script>
			alert("수정 완료");
		</script>
	<%
		}
	%>
	</body>
</html>