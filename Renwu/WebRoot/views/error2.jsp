<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<script type="text/javascript">
		function go(){
		alert("${error}");
		window.location.href="views/register.jsp";
		}
	</script>

  </head>
  
  <body onload="go()">
    
  </body>
</html>
