<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.fithe.member.vo.MemberVO" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MY PAGE</title>
		<style type="text/css">
			.mem{text-align : center;}
		</style>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript" 
				src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(document).on("click","#updatebutton",function(){
					$("#mypageForm").attr('action',"update.do");
					$("#mypageForm").attr('method',"POST");
					$("#mypageForm").submit();
// 					document.mypageForm.action="/Fithe/update.fit";
// 					document.mypageForm.method="POST";
// 					document.mypageForm.submit();
					
				});
				
				$(document).on("click","#deletebutton",function(){
					alert("탈퇴");
					$("#mypageForm").attr('action',"delete.do");
					$("#mypageForm").attr('method',"POST");
					$("#mypageForm").submit();
// 					document.mypageForm.action="/Fithe/delete.fit";
// 					document.mypageForm.method="POST";
// 					document.mypageForm.submit();
				});
				
				$(document).on("click","#backbutton",function(){
					history.go(-1);
				});
				
// 				$("#mpwchange").click(function(event){
// 					console.log("비밀번호 변경");
// 					$("#mypageForm").attr('action',"pwchange.fit");
// 					$("#mypageForm").attr('metdho',"POST");
// 					$("#mypageForm").submit();
					
// 				});
			});
		</script>
		<script type="text/javascript">
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
		                document.getElementById("maddress_detail").value= "";
		                document.getElementById("maddress_detail").focus();
					}
				}).open();
			}
		</script>
	</head>
	<body onload="check()">
	MY page
	<%
		Object obj = request.getAttribute("listM");
		List<MemberVO> alist = (List)obj;
		int nCnt = alist.size();
		String mnum = "";
		String mid = "";
		String mpw = "";
		String mname = "";
		String mbir = "";
		String mgender = "";
		String mzonecode = "";
		String maddress_road = "";
		String maddress_detail = "";
		String mph = "";
		String memail = "";
		
		if(nCnt ==1 ){
			MemberVO mvo = alist.get(0);
			mnum = mvo.getMnum();
			mid = mvo.getMid();
			mpw = mvo.getMpw();
			mname = mvo.getMname();
			mbir = mvo.getMbir();
			mgender = mvo.getMgender();
			mzonecode = mvo.getMzonecode();
			maddress_road = mvo.getMaddress_road();
			maddress_detail = mvo.getMaddress_detail();
			mph = mvo.getMph();
			memail = mvo.getMemail();
		}
		
		System.out.println(mpw);
		//이메일
		String memail1 = memail.split("@")[0];
		String memail2 = memail.split("@")[1];
	%>
	
	<script>
		function check(){
			
			//생년월일
			var mbir = '<%=mbir%>';
			var myear = mbir.substring(0,4);
			var mmonth = mbir.substring(4,6);
			var mday = mbir.substring(6);
			
			document.getElementById("mbiry").value= myear;
			document.getElementById("mbirm").value= mmonth;
			document.getElementById("mbird").value= mday;
			
			//성별
			var mgender1 = '<%=mgender %>';
			if('M' == mgender1){
				document.getElementsByName("mgender")[0].checked = true;
			}if('F' == mgender1){
				document.getElementsByName("mgender")[1].checked = true;
			}
			
			//핸드폰
			var mph = '<%=mph%>';
			var mph1 = mph.split("-")[0];
			var mph2 = mph.split("-")[1];
			var mph3 = mph.split("-")[2];
			
			if('010' == mph1){
				document.getElementById("mph1").options[0].selected = true;
			}
			if('011' == mph1){
				document.getElementById("mph1").options[1].selected = true;
			}
			if('019' == mph1){
				document.getElementById("mph1").options[2].selected = true;
			}
			if('016' == mph1){
				document.getElementById("mph1").options[3].selected = true;
			}
			
			document.getElementById("mph2").value = mph2;
			document.getElementById("mph3").value = mph3;
			
			//이메일
			var memail = '<%=memail%>';
			var memail1 = memail.split("@")[0];
			var memail2 = memail.split("@")[1];
			
			document.getElementById("memail1").value = memail1;
			document.getElementById("memail2").value = memail2;
			
		}	
	</script>
	<div>
		<form id="mypageForm">
			<table border="1">
				<tr>
					<td colspan="2" align="center">MY PAGE</td>
				</tr>
				<tr>
					<td class="mem">회원번호</td>
					<td><input type="text" id="mnum" name="mnum" value="<%=mnum %>" readonly/></td>
				</tr>
				<tr>
					<td class="mem">아이디</td>
					<td>
					<input type="text" id="mid" name="mid" value="<%=mid%>" readonly/>
					</td>
				</tr>
				<tr>
					<td class="mem">비밀번호</td>
					<td>
						<input type="password" id="mpw" name="mpw" value="<%=mpw%>"/>
						<input type="button" id="mpwchange" value="비밀번호 변경"/>
					</td>
				</tr>
				<tr>
					<td class="mem">이름</td>
					<td>
					<input type="text" id="mname" name="mname" value="<%=mname %>"/>
					</td>
				</tr>
				<tr>
					<td class="mem">생년월일</td>
					<td>
					<input type="text" id="mbiry" name="mbiry" size="5" maxlength="4" readonly/> 년
					<input type="text" id="mbirm" name="mbirm" size="5" maxlength="2" readonly/> 월
					<input type="text" id="mbird" name="mbird" size="5" maxlength="2" readonly/> 일
					</td>
				</tr>
				<tr>
					<td class="mem">성별</td>
					<td>
					<input type="radio" id="mgender" name="mgender" value="M" >남
					<input type="radio" id="mgender" name="mgender" value="F" > 여
					</td>
				</tr>
				<tr>
					<td class="mem">주 소</td>
					<td>
					<input type="text" id="mzonecode" name="mzonecode" value="<%=mzonecode %>" placeholder="우편번호">
					<input type="button" onclick="adressfind()" value="주소 찾기"><br>
					<input type="text" id="maddress_road" name="maddress_road" value="<%=maddress_road %>" placeholder="도로명 주소" style="width:350px"><br>
					<input type="text" id="maddress_detail" name="maddress_detail" value="<%=maddress_detail%>" placeholder="상세 주소" style="width:350px"> 
					</td>
				</tr>
				<tr>
					<td class="mem">핸드폰 번호</td>
					<td>
					<select name="mph1" id="mph1" >
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
					<input type="button" id="updatebutton" value="회원 수정" />
					<input type="button" id="deletebutton" value="회원 탈퇴" />
					<input type="button" id="backbutton" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
	</body>
</html>