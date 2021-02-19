<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fithe.common.vo.ScheduleVO" %>
<%
	String num = (String)session.getAttribute("mnum");
	String id = (String)session.getAttribute("mid");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MY PAGE</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- 		<link href="/assets/demo-to-codepen.css" rel="stylesheet"> -->
<!-- 		<script src="/assets/demo-to-codepen.js"></script> -->
		<link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.5.1/main.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.5.1/main.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#myinfo").click(function(){
					console.log("내정보 가기");
					$("#mypage").attr('action',"myinfo.fit");
					$("#mypage").attr('method',"POST");
					$("#mypage").submit();
				});
			});
		</script>
		<style> 
			body {
					margin: 40px 10px; 
					padding: 0; 
					font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif; 
					font-size: 14px; } 
			#calendar { 
				max-width: 500px; 
				margin: 0 auto; 
			}	
		</style>

		<script>	
		document.addEventListener('DOMContentLoaded', function() {
			    var calendarEl = document.getElementById('calendar');
			    
			    var calendar = new FullCalendar.Calendar(calendarEl, {
			    	headerToolbar : {  //상단탭위치에 놓일 것들
			    		left: '', 
			    		center: 'title', 
			    		right: 'today prev,next' },
			    	footerToolbar :{
			    		left: '',
			    		center: '',
			    		right: 'coustom2 coustom1'
			    	},
			    	timeZone : 'local', //우리나라 시간기준
			    	selectable: true,	//날짜 선택시 색변경
			    	locale : "ko", //한글화
			    	navLinks: true,
			    	customButtons:{
			    		coustom1:{
			    			text : '입력',
			    			click: function(){
			    				var url = "schedulePopup.fit";
			    				var name = "schedulePopup";
			    				var option = "width=600, height = 600, left=100, top=50, location=no ";
			    				window.open(url,name,option)
			    			}
			    		},
			    		coustom2:{
			    			text: '조회',
			    			click: function(){
			    				var mid = "<%=id%>";
			    				var surl = "scheduleSelect.fit";
			    				var method = "POST";
			    				var datas = {"mid" : mid};
			    				
			    				$.ajax({
			    					url : surl,
			    					type : method,
			    					data : datas,
			    					success : function(data){
			    						if(data == "lists"){
			    							alert("조회");
			    						}else{
			    							alert("실패");
			    						}
			    						
			    					},
			    					error : function(error){
			    						alert("error : ", error);
			    					}
			    				});
			    			}
			    		}
			    	},
			    	events: [
			    			
<%-- 			    	   <% --%>
// 			    	   	for(int i =0; i<list.size(); i++){
// 			    	   		ScheduleVO svo = (ScheduleVO)list.get(i);
<%-- 			    	   %> --%>
// 			    	  		{
<%-- 				    		    title  : '<%=svo.getSmemo()%>', --%>
<%-- 				    		    start  : '<%=svo.getSdate()%>' --%>
// 			    		    },
// 			    		    {
<%-- 					    		title  : '<%=svo.getSmemo1()%>', --%>
<%-- 					    		start  : '<%=svo.getSdate()%>' --%>
// 				    		},
// 				    		{
<%-- 					    		title  : '<%=svo.getSmemo2()%>', --%>
<%-- 					    		start  : '<%=svo.getSdate()%>' --%>
// 				    		},
// 				    		{
<%-- 					    		title  : '<%=svo.getSmemo3()%>', --%>
<%-- 					    		start  : '<%=svo.getSdate()%>' --%>
// 				    		},
// 				    		{
<%-- 				    			title  : '<%=svo.getSmemo4()%>', --%>
<%-- 				    			start  : '<%=svo.getSdate()%>' --%>
// 				    		}	    
			    	
<%-- 			    	   <% --%>
// 			    	   	}
<%-- 			    	   %> --%>
			    	  ]
			    }
		    calendar.render();
	    });

		 

		</script>

	</head>
	<body>
		<%
// 			Object obj = session.getAttribute("list");
			Object obj = request.getAttribute("list");
			List<ScheduleVO> lists = (List)obj;
			System.out.println(lists.size());
		%>
		세션 ID : <%=session.getId() %> <br>
		세션 생성 시간 : <%=session.getMaxInactiveInterval()%>ms<br>
		<form id="mypage">
			<input type="button" id="myinfo" value="내정보">
		</form>
		<div id='calendar'></div>
	</body>
</html>