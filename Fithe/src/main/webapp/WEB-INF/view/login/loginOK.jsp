<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String num = (String)session.getAttribute("mnum");
	String id = (String)session.getAttribute("mid");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>loginOK</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(document).on("click","#mypagebutton",function(){
					console.log("info");
					$("#mypageForm").attr('action',"mypage.fit");
					$("#mypageForm").attr('method',"POST");
					$("#mypageForm").submit();
				});
				
			});
		</script>
	</head>
	<body>
		세션 ID : <%=session.getId() %> <br>
		세션 생성 시간 : <%=session.getMaxInactiveInterval() %> ms<br>
		회원 번호 : <%=num %> <br>
		회원 아이디 : <%=id %>
	<form id="mypageForm">
		<input type="button" id="mypagebutton" value="마이 페이지">
		<input type="button" id="logoutbutton" value="로그아웃" onclick="location.href='/Fithe/logout.fit'">
	</form>
	</body>
</html>