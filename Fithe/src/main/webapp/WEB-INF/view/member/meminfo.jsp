<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fithe.schedule.vo.ScheduleVO" %>
<%
	String num = (String)session.getAttribute("mnum");
	String id = (String)session.getAttribute("mid");
	Object obj = request.getAttribute("list");
	List<ScheduleVO> lists = (List)obj;
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MY PAGE</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.5.1/main.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.5.1/main.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#myinfo").click(function(){
					console.log("내정보 가기");
					$("#mypage").attr('action',"myinfo.do");
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
			    		right: 'today prevYear,prev,next,nextYear' },
			    	footerToolbar :{
			    		left: '',
			    		center: '',
			    		right: 'coustom1'
			    	},
			    	timeZone : 'local', //우리나라 시간기준
			    	locale : "ko", //한글화
			    	fixedWeekCount : false, // flase 일경우 4~5주만 보이도록, true이면 6주가보이게 됨
// 			    	showNonCurrentDates : false, //다음달 표시 안되게하는것 
			    	navLinks: true,
// 			    	navLinkDayClick: function() { //필요할시
// 			    		console.log("할것입력");
// 			    	},
			    	dateClick : function(){
			    		alert("일정");  //수정 필요없고 삭제는 있어도무방
			    	},
			    	customButtons:{
			    		coustom1:{
			    			text : '입력',
			    			click: function(){
			    				var url = "schedulePopup.do";
			    				var name = "schedulePopup";
			    				var option = "width=600, height = 600, left=100, top=50, location=no ";
			    				window.open(url,name,option)
			    			}
			    		}
			    	},
			    	events: [
			    		   <%
			    		   	for(int i=0; i<lists.size(); i++){
			    		   		ScheduleVO svo = (ScheduleVO)lists.get(i);
			    		   %>
			    		   		{
			    		   			title : '1 : '+'<%=svo.getSmemo() %>',
			    		   			start : '<%=svo.getSdate()%>'
			    		   		},
			    		   		
			    		   		{
			    		   			title : '2 : ' + '<%=svo.getSmemo1() %>',
			    		   			start : '<%=svo.getSdate()%>'
			    		   		},
			    		   		
			    		   		{
			    		   			title : '3 : ' + '<%=svo.getSmemo2() %>',
			    		   			start : '<%=svo.getSdate()%>'
			    		   		},
			    		   		
			    		   		{
			    		   			title : '4 : ' + '<%=svo.getSmemo3() %>',
			    		   			start : '<%=svo.getSdate()%>'
			    		   		},
			    		   		
			    		   		{
			    		   			title : '5 : ' + '<%=svo.getSmemo4() %>',
			    		   			start : '<%=svo.getSdate()%>'
			    		   		},

			    		   <%
			    		   	}
			    		   %>
			    	]
			    });
		    calendar.render();
	    });

		 

		</script>

	</head>
	<body>

		세션 ID : <%=session.getId() %> <br>
		세션 생성 시간 : <%=session.getMaxInactiveInterval()%>ms<br>
		<form id="mypage">
			<input type="button" id="myinfo" value="내정보">
		</form>
		<div id='calendar'></div>
	</body>
</html>