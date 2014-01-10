<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/extremecomponents" prefix="ec"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>栏目列表</title>
    
<style type="text/css">

.eXtremeTable {
	margin: 0;
	padding: 0;
}

.eXtremeTable select {
	font-family: Verdana;
	font-size: 9px;
	border: solid 1px #EEE;
	width: 75px;
}

.eXtremeTable .tableRegion {
	border: 1px solid silver;
	padding: 2px;
	font-family:Verdana;
	font-size: 10px;
	margin-top: 7px;
}

.eXtremeTable .filter {
	background-color: #efefef;
}

.eXtremeTable .filter input {
	font-family: Verdana;
	font-size: 10px;
	width: 100%;
}

.eXtremeTable .filter select {
	font-family: Verdana;
	font-size: 9px;
	border: solid 1px #EEE;
	width: 100%;
}

.eXtremeTable .tableHeader {
	background-color: #308dbb;
	color: white;
	font-family:Verdana;
	font-size: 11px;
	font-weight: bold;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4;
	padding-bottom: 4;
	margin: 0;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
}

.eXtremeTable .tableHeaderSort {
	background-color: #3a95c2;
	color: white;
	font-family:Verdana;
	font-size: 11px;
	font-weight: bold;
	text-align: left;
	padding-right: 3px;
	padding-left: 3px;
	padding-top: 4;
	padding-bottom: 4;
	border-right-style: solid;
	border-right-width: 1px;
	border-color: white;
}

.eXtremeTable .odd a, .even a {
	color: Black;
	font-size: 10px;
}

.eXtremeTable .odd td, .eXtremeTable .even td {
	padding-top: 2px;
	padding-right: 3px;
	padding-bottom: 2px;
	padding-left: 3px;
	vertical-align: middle;
	font-family:Verdana;
	font-size: 10px;
}

.eXtremeTable .odd {
	background-color: #FFFFFF;
}

.eXtremeTable .even {
  	background-color: #dfe4e8;
}

.eXtremeTable .highlight td {
	color: black;
	font-size: 10px;
	padding-top: 2px;
	padding-right: 3px;
	padding-bottom: 2px;
	padding-left: 3px;
	vertical-align: middle;
	background-color: #fdecae;
}

.eXtremeTable .highlight a, .highlight a {
	color: black;
	font-size: 10px;
}

.eXtremeTable .toolbar {
	background-color: #F4F4F4;
	font-family:Verdana;
	font-size: 9px;
	margin-right: 1px;
	border-right: 1px solid silver;
	border-left: 1px solid silver;
	border-top: 1px solid silver;
	border-bottom: 1px solid silver;
}

.eXtremeTable .toolbar td {
	color: #444444;
	padding: 0px 3px 0px 3px;
	text-align:center;
}

.eXtremeTable .separator {
	width: 7px;
}

.eXtremeTable .statusBar {
	background-color: #F4F4F4;
	font-family:Verdana;
	font-size: 10px;
}

.eXtremeTable .filterButtons {
	background-color: #efefef;
	text-align: right;
}

.eXtremeTable .title {
	color: #444444;
	font-weight: bold;
	font-family:Verdana;
	font-size: 15px;
	vertical-align: middle;
}

.eXtremeTable .title span {
	margin-left: 7px;
}

.eXtremeTable .formButtons {
	display: block;
	margin-top: 10px;
	margin-left: 5px;
}

.eXtremeTable .formButton {
	cursor: pointer;
	font-family:Verdana;
	font-size:10px;
	font-weight: bold;
	background-color: #308dbb;
	color: white;
	margin-top: 5px;
	border: outset 1px #333;
	vertical-align: middle;
}

.eXtremeTable .tableTotal {
	background-color: #FFFFFF;
	border-top: solid 1px Silver;	
}

.eXtremeTable .tableTotalEmpty {
	background-color: #FFFFFF;	
}

</style>
	</head>

	<body>
		     <ec:table items="categoryList" var="category" action="${pageContext.request.contextPath}/index.htm"
     imagePath="${pageContext.request.contextPath}/images/table/*.gif"
			width="100%" showPagination="true" showStatusBar="true"
			rowsDisplayed="5" style="text-align: left;">

			<ec:row highlightRow="true">
				<ec:column property="mycheckbox" sortable="false" title="选项"
					filterable="false" width="10%">
					<input type="checkbox" name="mycheckbox" value="${category.id}"
						id="mycheckbox" />
				</ec:column>
				<ec:column property="id"  title="主键" filterable="false">
				</ec:column>
				<ec:column property="catName"  title="栏目名称" />

				<ec:column property="do" title="操作" width="" filterable="false"
					sortable="false">
					<a href="<c:url value='/edit/${category.id}.htm' />" title="编辑">编辑</a>&nbsp;&nbsp;                
                    <a href="<c:url value='/del/${category.id}.htm' />"
						onclick="if(!confirm('您确定要把“${category.catName}”删除吗？'))return false;" title="删除">删除
					</a>
				</ec:column>
			</ec:row>
		</ec:table>
		<table>
			<tr>
				<td style="padding: 10px 0;">
					<a href="javascript:;" onclick="checkboxObj.select_all();">全选</a> /
					<a href="javascript:;" onclick="checkboxObj.deselect_all();">全部不选</a>
					/
					<a href="javascript:;" onclick="checkboxObj.reverse_select();">反向选择</a>
					&nbsp; &nbsp; &nbsp;
					<select onchange="checkboxObj.handle_selected(this, 'bagForm');">
						<option value="-1">
							选择项:
						</option>
						<option value="del">
							刪除
						</option>
					</select>
				</td>
			</tr>
		</table>
		<a href="views/save.jsp">添加</a>
	</body>
</html>
