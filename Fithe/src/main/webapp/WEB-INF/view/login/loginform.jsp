<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(document).on("click","#insertbutton",function(){
					console.log("회원가입");
					location.href="/Fithe/insertForm.do";
					
				});
				
				$(document).on("keypress","#mpw", function(e){
					if(e.keyCode == '13'){
						$('#loginbutton').click();
					}
				});

				$(document).on("click","#loginbutton",function(){
					alert("로그인 ");
					document.loginForm.action="/Fithe/login.do";
					document.loginForm.method = "POST";
// 					document.insertForm.enctype = "application/www-form-urlencoded";
					document.loginForm.submit();
				});
				
				$(document).on("click","#idfind", function(){
					alert("아이디 찾기");
					document.loginForm.action="/Fithe/idfind.do";
					document.loginForm.method="GET";
					document.loginForm.submit();
				});
				
				$(document).on("click","#pwfind", function(){
					alert("비밀번호 찾기");
					document.loginForm.action="/Fithe/pwfind.do";
					document.loginForm.method="GET";
					document.loginForm.submit();
				});
			});
		</script>
	</head>
	<body>
	<div>
		<form id="loginForm" name="loginForm">
			<table border="1" align="center">
				<tr>
					<td>아이디 : </td>
					<td><input type="text" id="mid" name="mid" /></td>
				</tr>
				<tr>
					<td>비밀번호 : </td>
					<td><input type="password" id="mpw" name="mpw" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="button" value="로그인" id="loginbutton"/>
					<input type="button" value="회원가입" id="insertbutton"/> 
					</td>
				</tr>
				<tr>
					<td>
					<input type="button" value="아이디 찾기" id="idfind"/>
					<input type="button" value="비밀번호 찾기" id="pwfind"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		
	</div>
	</body>
</html>