<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>My JSP 'edit.jsp' starting page</title>

  </head>
  
  <body>
     <c:if test="${not empty category}">
		<div>
				<c:forEach items="category" var="cate">
					<form action="../update.htm" method="post">
    Ҫ���µ���ĿID��<input type="text" name="id" value="${category.id}"/><br/><br/>
   ��Ŀ���ƣ�<input type="text" name="catName" value="${category.catName}" /><br/><br/>        
    <input type="submit" value="�ύ" />
    <input type="button" onclick="location.href='../index.jsp'" value="������ҳ" />
    </form>
					
				</c:forEach>
			</div>
</c:if>
  </body>
</html>
