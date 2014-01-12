<%@ page language="java" import="java.util.*,com.niit.model.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'request.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <%
  	int id = Integer.parseInt(request.getParameter("taskId"));
  	int id2 = Integer.parseInt(request.getParameter("acceptId"));
  	request.setAttribute("id",id);
  	request.setAttribute("id2",id2);
   %>
  <body>
	   	<form action="../request/${id2}.htm" method="post">
	   		所请求得任务ID:<input type="text" name="taskId" value="${id}"/><br/>
	   		报价：<input type="text" name="offer" /><br/>
	   		说明：<input type="text" name="explain" /><br/>
	   		<input type="submit" value="提交">
	   	</form>
  </body>
</html>
