<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>ID FIND</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(document).on("click","#idfindbutton",function(){
					console.log("찾기 버튼 클릭");
					$("#idfindForm").attr('action',"idfindOK.fit");
					$("#idfindForm").attr('method',"POST");
					$("#idfindForm").submit();
				});
			});
		</script>
		</head>
	<body>
		<form id="idfindForm">
			<tr>
				<td>이름 : </td>
				<td><input type="text" id="mname" name="mname"/></td>
			</tr>
			<tr>
				<td>이메일 : </td>
				<td><input type="text" id="memail" name="memail"/></td>
			</tr>
			<tr>
				<td>
				<input type="button" id="idfindbutton" value="아이디 찾기"/>
				</td>
			</tr>
		</form>
	</body>
</html>