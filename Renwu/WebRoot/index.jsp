<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="views/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
getLeibie();
$("#xuqiu").hide();
$("#leibie").change(function(){
    			$("#xuqiu").show();
				getXuqiu($("#leibie").val());
				if($("#leibie").val()==0){
					$("#xuqiu").hide();
				}
			});
});

function getLeibie(){
    		$.getJSON("findLeibie.htm",function(data){
    			$.each(data,function(i,rs){
    				$("#leibie").append("<option value="+rs.xuqiuFenleiId+">"+rs.xuqiuFenleiName+"</option>"); 
    			});
    		});
    	}

function getXuqiu(leibieId){
			$("#xuqiu").empty();		
    		$.getJSON("findXuqiuByleibieId.htm?leibieId="+leibieId,function(data){
    			$.each(data,function(i,rs){
    				var year = rs.xuqiuAddTime.year+1900;
    				var month =rs.xuqiuAddTime.month+1;
    				var day = rs.xuqiuAddTime.date;
    				var hours = rs.xuqiuAddTime.hours;
    				var minutes =rs.xuqiuAddTime.minutes;
    				var seconds = rs.xuqiuAddTime.seconds;
    				var year2 = rs.xuqiuDeadLine.year+1900;
    				var month2 =rs.xuqiuDeadLine.month+1;
    				var day2 = rs.xuqiuDeadLine.date;
    				var hours2 = rs.xuqiuDeadLine.hours;
    				var minutes2 =rs.xuqiuDeadLine.minutes;
    				var seconds2 = rs.xuqiuDeadLine.seconds;
    				var time = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    				var time2 = year2+"-"+month2+"-"+day2+" "+hours2+":"+minutes2+":"+seconds2;
    				$("#xuqiu").append("<tr align='center'>"+"<td>"+rs.xuqiuTitle+"</td>"+"<td>"+rs.xuqiuContent+"</td>"+"<td>"+rs.xuqiuMoney+"</td>"+"<td>"+time+"</td>"+"<td>"+time2+"</td>"+"<td>"+rs.yonghuId+"</td>"+"<td>"+rs.yonghuId+"</td>"+"<td>"+rs.xuqiuFenleiId+"</td>"+"<td class='aaa'>"+"<input value='抢' class='but' id='rs.xuqiuId' name='rs.yonghuId' type=button>"+"</td>"+"</tr>"); 
    			});
    		});
    	}
</script>
<style type="text/css">
.ttt td{width:100px;}
</style>

</head>
<body>
						<select id="leibie">
					   		<option value="0">请选择类别</option>
					   	</select>
					   	
					   	<div >
					   		<table  border="1px;" cellspacing="0" cellpadding="0" align="center" class="ttt">
								<tr align="center">
					   				<td>标题</td>
					   				<td>内容</td>
					   				<td>酬劳（元）</td>
					   				<td>发布时间</td>
					   				<td>截止时间</td>
					   				<td>发布人</td>
					   				<td>所在校区</td>
					   				<td>所属类别</td>
					   				<td>认领</td>
				   				</tr>
				   				</table>
				   				<table id="xuqiu" name="xuqiuId"  border="1px;" cellspacing="0" cellpadding="0" align="center" class="ttt">
					   				
				   				</table>
				   				
					   		
					   	</div>
</body>
</html> 
