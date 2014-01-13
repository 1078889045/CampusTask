<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'single.jsp' starting page</title>
    
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
  <h2>任务详情</h2>
    <table border="1px;">
    	<tr>
    		<th>任务ID</th>
    		<td>任务内容</td>
    		<td>酬劳</td>
    		<td>发布时间</td>
    		<td>截止时间</td>
    	</tr>
    	<tr>
    		<th>${task.taskId}</th>
    		<td>${task.taskContent}</td>
    		<td>${task.reward}</td>
    		<td>${task.releaseTime}</td>
    		<td>${task.stopTime}</td>
    	</tr>
    	
    </table>
    <h2>候选人详情</h2>
    <c:if test="${not empty acceptList}">
	    
	    		<c:forEach items="${acceptList}" var="accept">
			   					<c:choose>
			   					<c:when test="${accept.tasks.taskId == task.taskId}">
				   					<table border="1px;">
								    	<tr>
								    		<th>候选人ID</th>
								    		<td>候选人姓名</td>
								    		<td>报价</td>
								    		<td>说明</td>
								    		<td>操作</td>
								    	</tr>
								    	<tr>
				   							<th>${accept.users.userId}</th>
				   							<td>${accept.users.userName}</td>
				   							<td>${accept.offer}</td>
				   							<td>${accept.explain}</td>
				   							<td><a href="../choose/${accept.aid}.htm">Choose</a></td>
				   						</tr>
		   							 </table>
			   					</c:when>
			   					
			   					
			   				</c:choose>	
			   				</c:forEach>
	    	
	</c:if>
  </body>
</html>
