<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<style type="text/css">
			.mem{text-align : center;}
		</style>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript" 
				src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(document).on("click","#insertbutton",function(){
					console.log("등록버튼 클릭");
					
					//유효성 검사
					if(insertForm.mid.value == ""){
						alert("아이디를 입력해주세요");
						document.getElementById("mid").focus();
						return false;
					}else{
						document.getElementById("mpw").focus();
					}
					
					if(insertForm.mpw.value == ""){
						alert("비밀번호를 입력해주세요");
						document.getElementById("mpw").focus();
						return false;
					}else{
						document.getElementById("mname").focus();
					}
					
					if(insertForm.mname.value == ""){
						alert("이름을 입력해주세요");
						document.getElementById("mname").focus();
						return false;
					}else{
						document.getElementById("mbiry").focus();
					}
					
					if(insertForm.mbiry.value == ""){
						alert("연도를 입력해주세요");
						document.getElementById("mbiry").focus();
						return false;
					}else{
						document.getElementById("mbirm").focus();
					}
					
					if(insertForm.mbirm.value == ""){
						alert("월을 입력해주세요");
						document.getElementById("mbirm").focus();
						return false;
					}else{
						document.getElementById("mbird").focus();
					}
					
					if(insertForm.mbird.value == ""){
						alert("일을 입력해주세요");
						document.getElementById("mbird").focus();
						return false;
					}else{
						document.getElementById("mzonecode").focus();
					}
					
					if(insertForm.mzonecode.value == ""){
						alert("주소 찾기를 해주세요");
						document.getElementById("mzonecode").focus();
						return false;
					}else{
						document.getElementById("maddress_detail").focus();
					}
					
					if(insertForm.maddress_detail.value == ""){
						alert("상세주소를 입력해주세요");
						document.getElementById("maddress_detail").focus();
						return false;
					}else{
						document.getElementById("mph2").focus();
					}
					
					if(insertForm.mph2.value == ""){
						alert("핸드폰 번호를 입력해주세요");
						document.getElementById("mph2").focus();
						return false;
					}else{
						document.getElementById("mph3").focus();
					}
					
					if(insertForm.mph3.value == ""){
						alert("핸드폰 번호를 입력해주세요");
						document.getElementById("mph3").focus();
						return false;
					}else{
						document.getElementById("memail1").focus();
					}
					
					if(insertForm.memail1.value == ""){
						alert("핸드폰 번호를 입력해주세요");
						document.getElementById("memail1").focus();
						return false;
					}else{
						document.getElementById("memail2").focus();
					}
					
					document.insertForm.action="/Fithe/insert.fit";
					document.insertForm.method = "POST";
// 					document.insertForm.enctype = "application/www-form-urlencoded";
					document.insertForm.submit();
				});
				
				//아이디 중복체크 ajax로 구현
				$(document).on("click","#idcheck",function(){
					alert("아이디 중복확인 클릭");
					let idURL = "idCheck.fit";
					let method = "POST";
					let dataPara = {mid : $("#mid").val()};
					
					$.ajax({
						url : idURL,
	    				type : method,
	    				data : dataPara,
	    				success : function(data){
	    					if(data == 1){
	    						alert("중복된 아이디");
	    						$("#mid").val("");
	    						$("#mid").focus();
	    					}else{
	    						alert("사용 가능한 아이디");
	    						$("#mid").attr("style", "color:#f00");
	    						$("#mpw").focus();
	    					}
	    				},
	    				error : function(error){
	    					alert("error : " + error);
	    				}
					});
					
				});
			});
		</script>
		<script type="text/javascript">
			function pwcheck(){
				var mpw = document.getElementById("mpw");
				var mpw1 = document.getElementById("mpw1");
				
				if(mpw.value == mpw1.value){
					alert("비밀번호 확인 완료");
					mpw1.value = "";
					document.getElementById("mname").focus();
					return true;
				}else{
					alert("비밀번호를 다시 확인해주세요");
					mpw.value= "";
					mpw1.value= "";
					mpw.focus();
					return false;
				}
			}
			
			function adressfind(){
				new daum.Postcode({
					//다음 지도 팝업창 실행
					oncomplete: function(data) {
		                var addr = ''; // 주소 변수
		                
		                if (data.userSelectedType === 'R') { 
		                	// 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { 
		                	// 사용자가 지번 주소를 선택했을 경우
		                    addr = data.jibunAddress;
		                }
		                
		                document.getElementById('mzonecode').value = data.zonecode;
		                document.getElementById("maddress_road").value = addr;
		                //상세주소로 커서 이동
		                document.getElementById("maddress_detail").focus();
					}
				}).open();
			}
		</script>
	</head>
	<body>
	<div>
		<form id="insertForm" name="insertForm">
			<table border="1">
				<tr>
					<td colspan="2" align="center">회원가입</td>
				</tr>
				<tr>
					<td class="mem">회원번호</td>
					<td><input type="text" id="mnum" name="mnum" readonly/></td>
				</tr>
				<tr>
					<td class="mem">아이디</td>
					<td>
					<input type="text" id="mid" name="mid" />
					<input type="button" id="idcheck" value="아이디 중복확인">
					</td>
				</tr>
				<tr>
					<td class="mem">비밀번호</td>
					<td>
					<input type="password" id="mpw" name="mpw"/><br>
					<input type="password" id="mpw1" name="mpw1">
					<input type="button" onclick="pwcheck()"value="비밀번호 중복확인">
					</td>
				</tr>
				<tr>
					<td class="mem">이름</td>
					<td>
					<input type="text" id="mname" name="mname"/>
					</td>
				</tr>
				<tr>
					<td class="mem">생년월일</td>
					<td>
					<input type="text" id="mbiry" name="mbiry" size="5" maxlength="4" placeholder="ex)1994"/> 년
					<input type="text" id="mbirm" name="mbirm" size="5" maxlength="2" placeholder="ex)10"/> 월
					<input type="text" id="mbird" name="mbird" size="5" maxlength="2" placeholder="ex)07"/> 일
					</td>
				</tr>
				<tr>
					<td class="mem">성별</td>
					<td>
					<input type="radio" id="mgender" name="mgender" value="M" checked>남
					<input type="radio" id="mgender" name="mgender" value="F"> 여
					</td>
				</tr>
				<tr>
					<td class="mem">주 소</td>
					<td>
					<input type="text" id="mzonecode" name="mzonecode" placeholder="우편번호">
					<input type="button" onclick="adressfind()" value="주소 찾기"><br>
					<input type="text" id="maddress_road" name="maddress_road" placeholder="도로명 주소" style="width:350px"><br>
					<input type="text" id="maddress_detail" name="maddress_detail" placeholder="상세 주소" style="width:350px"> 
					</td>
				</tr>
				<tr>
					<td class="mem">핸드폰 번호</td>
					<td>
					<select name="mph1" id="mph1">
			       		<option value="010">010</option>
			       	 	<option value="011">011</option>
			        	<option value="019">019</option>
			        	<option value="016">016</option>	        			       
			        </select> 
			        - <input type="text" id="mph2" name="mph2" style="width:70px" maxlength="4">
			        - <input type="text" id="mph3" name="mph3" style="width:70px" maxlength="4">
					</td>
				</tr>
				<tr>
					<td class="mem">이메일</td>
					<td>
					<input type="text" id="memail1" name="memail1" style="width:100px"> @
					<input type="text" id="memail2" name="memail2" style="width:100px" placeholder="직접 입력">
<!-- 					<select id="meamil2" name="memail2" > -->
<!-- 						<option value="1" selected>직접입력</option> -->
<!-- 			       		<option value="naver.com">naver.com</option>	       	    -->
<!-- 			      		<option value="gmail.com">gmail.com</option> -->
<!-- 			      		<option value="daum.net">daum.net</option>	  -->
<!-- 					</select> -->
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="button" id="insertbutton" value="등록"/>
					<input type="reset" value="다시 입력">
					</td>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>