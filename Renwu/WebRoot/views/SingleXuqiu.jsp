<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html;charset=gb2312">
	<style type="text/css">
		.ttt{margin-top:20px;}
		.ttt td{width:100px;}
		#faqbg{background-color:#666666;position:absolute;z-index:99;left:0;top:0;display:none;width:100%;height:1000px;opacity:0.5;filter:alpha(opacity=50);-moz-opacity:0.5;}
		#faqdiv{position:absolute;width:400px;left:50%;top:50%;margin-left:-200px;height:auto;z-index:100;background-color:#fff;border:1px #8FA4F5 solid;padding:1px;}
		#faqdiv h2{height:25px;font-size:14px;background-color:#8FA4F5;position:relative;padding-left:10px;line-height:25px;}
		#faqdiv h2 a{position:absolute;right:5px;font-size:12px;color:#FF0000;}
		#faqdiv .form{padding:10px;}
	</style>
<script type="text/javascript" src="../views/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(function(){
	$(".but").click(function(){
		var myId = $(this).attr("id");
		document.getElementById("fff").action = '../pingjia.htm?xuqiuId='+myId;
		$("#faqbg").css({display:"block",height:$(document).height()});
		$("#faqdiv").css("auto","0px");
		$("#faqdiv").css("display","block");
		document.documentElement.scrollTop=0;
	});
	$(".close").click(function(){
		$("#faqbg").css("display","none");
		$("#faqdiv").css("display","none");
	});
});
	</script>
  </head>
  
  <body>
    <div style="margin:0 auto;border:1px solid green; width:80%;height:1200px;">
    	<div style="width:100%;height:320px;">
	  		<div style="float:left; width:50%;">
	   	 	用户：${yonghu.yonghuName}
	   	 	</div>
	   	 	<div id="faqbg"></div>
	   	 	<div id="faqdiv" style="float:left;display:none;">
	   	 	<h2>信息窗口<a href="#" class="close">关闭</a></h2>
			<div class="form">
		   		<form action="" method="post" id="fff">
		   			<table width="360px;">
		   			<tr>
		   				<td>评价分数</td>
		   				<td><input type="text" name="pinjiaZhurenValue"></td>
		   			</tr>
		   			<tr>
		   				<td>评价内容</td>
		   				<td><textarea name="pinjiaZhurenContent" cols="20" rows="6" ></textarea></td>
		   			</tr>
		   			
		   			<tr><td></td><td><input type="submit" class="btn" id="j-gotoService" value="评价"></td></tr>
		   			</table>
		   		</form>
	   		</div>
	   	</div>
   	 	</div>	
   	 	<div style="width:92%; height:850px;border:1px solid blue;margin:0px auto;">
   	 	<h4>需求信息</h4>
    	<c:if test="${not empty xuqiu}">	
    			<table border="1px;" cellspacing="0" cellpadding="0" align="center" class="ttt">
    				<tr align="center">
    					<td>需求编号</td>
    					<td>标题</td>
    					<td>内容</td>
    					<td>酬劳(元)</td>
    					<td>发布时间</td>
    					<td>截止时间</td>
    					<td>所属栏目</td>
    				</tr>
    				<tr align="center">
    					<td>${xuqiu.xuqiuId}</td>
    					<td>${xuqiu.xuqiuTitle}</td>
    					<td><textarea cols="15" rows="6" >${xuqiu.xuqiuContent}</textarea></td>
    					<td>${xuqiu.xuqiuMoney}</td>
    					<td>${xuqiu.xuqiuAddTime}</td>
    					<td>${xuqiu.xuqiuDeadLine}</td>
    					<td>${fenlei.xuqiuFenleiName}</td>
    				</tr>
    			</table>
    	</c:if>
    	<h4>认领信息</h4>
    	<c:if test="${not empty relingList}">
    		
    			<table border="1px;" cellspacing="0" cellpadding="0" align="center" class="ttt">
    				<tr align="center">
    					<td>认领编号</td>
    					<td>认领说明</td>
    					<td>认领时间</td>
    					<td>认领人</td>
    					<td>认领状态</td>
    					<td>选择</td>
    				</tr>
    				<c:forEach items="${relingList}" var="relinglist">
    				<c:choose>
	    			<c:when test="${relinglist.renlingIsDeal == true}">
    				<tr align="center">
    					<td>${relinglist.renlingId}</td>
    					<td><textarea cols="15" rows="6" >${relinglist.renlingContent}</textarea></td>
    					<td>${relinglist.renlingAddTime}</td>
	    					<c:forEach items="${yonghuList}" var="yonghulist">
	    						<c:choose>
	    							<c:when test="${yonghulist.yonghuId==relinglist.yonghuId}">
	    								<td>${yonghulist.yonghuName}</td>
	    							</c:when>
	    						</c:choose>
	    					</c:forEach>
	    				<td>已完成</td>	
	    				<td><input value="评价" class="but" id="${relinglist.xuqiuId}" type="button"></td>	   					
    				</tr>
    				</c:when>
    				<c:otherwise>
    					<c:choose>
		    			<c:when test="${relinglist.renlingIsDeal!=true}">
	    				<tr align="center">
	    					<td>${relinglist.renlingId}</td>
	    					<td><textarea cols="15" rows="6" >${relinglist.renlingContent}</textarea></td>
	    					<td>${relinglist.renlingAddTime}</td>
		    					<c:forEach items="${yonghuList}" var="yonghulist">
		    						<c:choose>
		    							<c:when test="${yonghulist.yonghuId==relinglist.yonghuId}">
		    								<td>${yonghulist.yonghuName}</td>
		    							</c:when>
		    						</c:choose>
		    					</c:forEach>
		    				<td>未完成</td>	
		    				<td><a href="../choose/${relinglist.renlingId}.htm">选择</a></td>	   					
	    				</tr>
    				</c:when>
    				</c:choose>
    				</c:otherwise>
    				</c:choose>
    				
    				
    				</c:forEach>
    			</table>
    		
    	</c:if>
    	</div>
    </div>
  </body>
</html>
