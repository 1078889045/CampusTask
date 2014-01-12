<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'UserPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div style="width:100%;; height:120px; border:1px solid green;">
  		<div style="float:left; width:40%;">
		  	<c:if test="${not empty user}">
		  	${user.userId}
		   		用户名：${user.userName}<br/>  
		   		联系方式：${user.contact}<br/>
		   		等级：${user.userLevel}<br/>
		   	</c:if>
	   	</div>
	   	<div style="float:left;">
	   		<form action="../addtask/${user.userId}.htm" method="post">
	   			任务内容：<input type="text" name="taskContent"><br/>
	   			酬劳：<input type="text" name="reward"><br/>
	   			发布时间：<input type="text" name="releaseTime"><br/>
	   			截至时间：<input type="text" name="stopTime"><br/>
	   			<input type="submit" class="btn" id="j-gotoService" value="发布任务">
	   		</form>
	   	</div>
   	</div>
   	
   	<div style="float:left;width:100%; height:600px;border:1px solid blue; ">
   	<c:if test="${not empty task}">
   	<h2>我已发布任务</h2>
   		<table border="1px;">
   			<tr>
   				<td>状态</td>
   				<td>任务ID</td>
   				<td>任务内容</td>
   				<td>酬劳</td>
   				<td>发布时间</td>
   				<td>截止时间</td>
   				<td>接单人</td>
   				<td>候选人</td>
   				<td>操作</td>
   			</tr>
   		<c:forEach items="${task}" var="task">
   			
   				
		   			<tr>
		   				<td>
		   				<c:choose>
			   				<c:when test="${task.ifComplete == false}">
			   					发布未完成
			   				</c:when>
			   				<c:when test="${task.ifComplete != false}">
			   					发布已完成
			   				</c:when> 
		   				</c:choose>
		   				</td>
		   				<td>${task.taskId}</td>
		   				<td>${task.taskContent}</td>
		   				<td>${task.reward}</td>
		   				<td>${task.releaseTime}</td>
		   				<td>${task.stopTime}</td>
		   				<td><a href="">${task.acceptId}</a></td> 
		   				<td>
		   					<c:forEach items="${acceptList}" var="accept">
		   					<c:choose>
		   					<c:when test="${accept.tasks.taskId == task.taskId}">
		   					<a href="">${accept.users.userName}</a>
		   					</c:when>
		   					
		   				</c:choose>	
		   				</c:forEach>
		   				</td>
		   				<td><a href="../delete/${task.taskId}.htm">任务取消</a> <a href="../finish/${task.taskId}.htm">任务完成</a></td>
		   			</tr>
		   		
		   		
   			
   		</c:forEach>
   		</table>
   	</c:if>

   		<c:if test="${not empty Tasks}">
   		<h2>我可接发布任务</h2>
   		<table border="1px;">
   			<tr>
   				<td>状态</td>
   				<td>任务ID</td>
   				<td>任务内容</td>
   				<td>酬劳</td>
   				<td>发布时间</td>
   				<td>截止时间</td>
   				<td>发布人</td>
   				<td>操作</td>
   			</tr>
   		<c:forEach items="${Tasks}" var="tasks">		
		   			<tr>
		   				<c:choose>
		   				<c:when test="${tasks.ifComplete != true && tasks.users.userId != user.userId}">
		   					<c:choose>
		   					<c:when test="${tasks.acceptId == NULL}">
		   					<td>可接</td>
		   					
		   					<td>${tasks.taskId}</td>
			   				<td>${tasks.taskContent}</td>
			   				<td>${tasks.reward}</td>
			   				<td>${tasks.releaseTime}</td>
			   				<td>${tasks.stopTime}</td>
			   				<td>${tasks.users.userName}</td>
		   					 <td><a href="../views/request.jsp?taskId=${tasks.taskId}&acceptId=${user.userId}">请求接单</a></td>  
		   					 </c:when>		   											
		   					</c:choose>			   					
		   				</c:when>		 
		   				</c:choose>
		   				
		   				<c:choose>
		   				<c:when test="${tasks.ifComplete != true && tasks.users.userId != user.userId}">
		   					<c:choose>
		   					<c:when test="${tasks.acceptId == user.userId}">
		   					<td>已接未完成</td>
		   					
		   					<td>${tasks.taskId}</td>
			   				<td>${tasks.taskContent}</td>
			   				<td>${tasks.reward}</td>
			   				<td>${tasks.releaseTime}</td>
			   				<td>${tasks.stopTime}</td>
			   				<td>${tasks.users.userName}</td>
		   					 <td><a href="">取消接单</a></td>  
		   					 </c:when>		   											
		   					</c:choose>			   					
		   				</c:when>		 
		   				</c:choose>	
		   				
		   				<c:choose>
		   				<c:when test="${tasks.ifComplete == true && tasks.users.userId != user.userId}">
		   					<c:choose>
		   					<c:when test="${tasks.acceptId == user.userId}">
		   					<td>已接已完成</td>
		   					
		   					<td>${tasks.taskId}</td>
			   				<td>${tasks.taskContent}</td>
			   				<td>${tasks.reward}</td>
			   				<td>${tasks.releaseTime}</td>
			   				<td>${tasks.stopTime}</td>
			   				<td>${tasks.users.userName}</td>
		   					 <td></td>  
		   					 </c:when>		   											
		   					</c:choose>			   					
		   				</c:when>		 
		   				</c:choose>			
		   			</tr>	
   		</c:forEach>
   		</table>
   		</c:if>
   </div>
   
  </body>
</html>
