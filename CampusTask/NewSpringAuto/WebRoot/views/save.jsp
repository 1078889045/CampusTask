<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'save.jsp' starting page</title>

  </head>
  
  <body>
    <form action="../save.htm" method="post">
   栏目名称：<input type="text" name="catName" value="${category.catName}" /><br/><br/>        
    <input type="submit" value="提交" />
    <input type="button" onclick="location.href='../index.jsp'" value="返回主页" />
    </form>
  </body>
</html>
