<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>PW FIND</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(document).on("click","#pwfindbutton",function(){
					console.log("찾기 버튼 클릭");
					$("#pwfindForm").attr('action',"pwfindOK.fit");
					$("#pwfindForm").attr('method',"POST");
					$("#pwfindForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<form id="pwfindForm">
			<tr>
				<td>아이디 : </td>
				<td><input type="text" id="mid" name="mid"/></td>
			</tr>
			<tr>
				<td>이메일 : </td>
				<td><input type="text" id="memail" name="memail"/></td>
			</tr>
			<tr>
				<td>
				<input type="button" id="pwfindbutton" value="비밀번호 찾기"/>
				</td>
			</tr>
		</form>
	</body>
</html>