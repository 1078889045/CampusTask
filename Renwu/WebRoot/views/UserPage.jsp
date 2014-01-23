<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'UserPage.jsp' starting page</title>
	
	<script type="text/javascript" src="../views/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		function showTab(_this){
		//获取所有的选项卡
		var lis = document.getElementsByClassName("tabName")[0].getElementsByTagName("li");
		for(var i=0; i<lis.length; i++){
			if(_this.id == lis[i].id){//若当前点击的选项卡的id与循环到的li的id相同
				lis[i].style.backgroundColor = "white";	//则突出显示
				lis[i].style.borderBottom = "3px solid white";
				lis[i].style.borderTop = "3px solid #666";
			}else{
				lis[i].style.borderBottom = "3px solid #666";
				lis[i].style.borderTop = "3px solid white";
			}
		}
		var contents = document.getElementsByClassName("content");
		for(var i=0; i<contents.length;i++){
			if(contents[i].id != _this.id+"_content"){
				contents[i].style.display="none";
			}else{
				contents[i].style.display="block";
			}
		}
	}
	
	function select_all(){
	var c=document.all;
	for(var i=0;i<=c.length;i++)
	if(c[i].type=="checkbox")
	c[i].checked=true;
	}
	function deselect_all(){
	var c=document.all;
	for(var i=0;i<=c.length;i++)
	if(c[i].type=="checkbox")
	c[i].checked=false;
	}	

	function selectDelete() {
		
		var xuqiu="";
		var c=document.all;

		for(var i=0;i<c.length;i++){

		                 if(c[i].checked){
			                xuqiu+=c[i].value+";";
			                				
				flag=true;     //表示复选框有被选中的
			}
		}
		if(flag==true){
			if(!confirm('您确定要把“xuqiu”删除吗？'))
			return false;
		    window.location.href="../deleteXuqiu/${user.yonghuId}.htm?xuqiu="+xuqiu;  //提交后台 别忘了传参
		    window.location.reload(true);
		}
	}
	
	


$(document).ready(function(){
getLeibie();
$("#xuqiu").hide();
$("#leibie").change(function(){
    			$("#xuqiu").show();
				getXuqiu($("#leibie").val());
				if($("#leibie").val()==0){
					$("#xuqiu").hide();
					$("#all").css("display","block");
				}
			});
});

function getLeibie(){
    		$.getJSON("../findLeibie.htm",function(data){
    			$.each(data,function(i,rs){
    				$("#leibie").append("<option value="+rs.xuqiuFenleiId+">"+rs.xuqiuFenleiName+"</option>"); 
    			});
    		});
    	}

function getXuqiu(leibieId){
			$("#all").css("display","none");
			$("#xuqiu").empty();		
    		$.getJSON("../findXuqiuByleibieId.htm?leibieId="+leibieId,function(data){
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
    				$("#xuqiu").append("<tr align='center'>"+"<td>"+rs.xuqiuTitle+"</td>"+"<td>"+"<textarea name='xuqiuContent' cols='10' rows='6' >"+rs.xuqiuContent+"</textarea>"+"</td>"+"<td>"+rs.xuqiuMoney+"</td>"+"<td>"+time+"</td>"+"<td>"+time2+"</td>"+"<td>"+rs.yonghuId+"</td>"+"<td>"+rs.yonghuId+"</td>"+"<td>"+rs.xuqiuFenleiId+"</td>"+"<td>"+"<input type='button' value='抢'  class='bbb' id="+rs.xuqiuId+" name="+rs.yonghuId+" onclick='qiang("+rs.xuqiuId+","+rs.yonghuId+")' >"+"</td>"+"</tr>"); 
    				
    			});
    		});
    	}
    
    function qiang(myId,order){
		var user = ${user.yonghuId};
		if(order!= user){
		document.getElementById("fff").action = '../addRenling.htm?xuqiuId='+myId+'&yonghuId='+user;
		$("#faqbg").css({display:"block",height:$(document).height()});
		$("#faqdiv").css("auto","0px");
		$("#faqdiv").css("display","block");
		document.documentElement.scrollTop=0;
		}else{
			alert("此需求是您发布的，您无法抢单！");
		}

	$(".close").click(function(){
		$("#faqbg").css("display","none");
		$("#faqdiv").css("display","none");
	});
    }	
    	
    $(function(){
	$(".but").click(function(){
		var myId = $(this).attr("id");
		var order = $(this).attr("name");
		var user = ${user.yonghuId};
		if(order!= user){
		document.getElementById("fff").action = '../addRenling.htm?xuqiuId='+myId+'&yonghuId='+user;
		$("#faqbg").css({display:"block",height:$(document).height()});
		$("#faqdiv").css("auto","0px");
		$("#faqdiv").css("display","block");
		document.documentElement.scrollTop=0;
	}else{
		alert("此需求是您发布的，您无法抢单！");
	}
	});
	$(".close").click(function(){
		$("#faqbg").css("display","none");
		$("#faqdiv").css("display","none");
	});
	$(".pin").click(function(){
		var myId = $(this).attr("id");
		var yhId = $(this).attr("name");
		document.getElementById("fff2").action = '../rlpingjia.htm?xuqiuId='+myId+'&yonghuId='+yhId;
		$("#pj").css({display:"block",height:$(document).height()});
		$("#pjdiv").css("auto","0px");
		$("#pjdiv").css("display","block");
		document.documentElement.scrollTop=0;
	});
	$(".close2").click(function(){
		$("#pj").css("display","none");
		$("#pjdiv").css("display","none");
	});
});	
	</script>
	<style type="text/css">
		.tabName{
			margin:0px;
			padding:0px;
			width:300px;
			height:27px;
			list-style:none;
			text-align:center;
			}
		.tabName a{display:block;height:27px;width:100px;}
		.tabName a:link{color:#666;text-decoration:none;}
		.tabName a:visited{color:#666;}
		.tabName a:hover{color:#F00;background-color:#FFF;}
			.tabName li{
			margin:0px;
			padding:0px;
			height:27px;
			width:100px;
			display:inline-block;
		}
			.content{ width:99%; height:150px;display:none;}
			#zt:link{color:#000;text-decoration:none;}
			#zt:visited{color:#000;}
			#zt:hover{text-decoration:underline;}
		.hover .headlines a{font-size:14px;}
		.hover .headlines a:link{color:#666;text-decoration:none;}
		.hover .headlines a:visited{color:#666;}
		.hover .headlines a:hover{color:#F00;}
		.ttt td{width:100px;}
		.ttt2 td{width:100px;}
		
		.aaa a:link{color:#666;text-decoration:none;}
		.aaa a:visited{color:#666;}
		.aaa a:hover{color:#F00;}
		.bbb{font-size:24;}
		#faqbg{background-color:#666666;position:absolute;z-index:99;left:0;top:0;display:none;width:100%;height:1000px;opacity:0.5;filter:alpha(opacity=50);-moz-opacity:0.5;}
		#faqdiv{position:absolute;width:400px;left:50%;top:50%;margin-left:-200px;height:auto;z-index:100;background-color:#fff;border:1px #8FA4F5 solid;padding:1px;}
		#faqdiv h2{height:25px;font-size:14px;background-color:#8FA4F5;position:relative;padding-left:10px;line-height:25px;}
		#faqdiv h2 a{position:absolute;right:5px;font-size:12px;color:#FF0000;}
		#faqdiv .form{padding:10px;}
		
		#pj{background-color:#666666;position:absolute;z-index:99;left:0;top:0;display:none;width:100%;height:1000px;opacity:0.5;filter:alpha(opacity=50);-moz-opacity:0.5;}
		#pjdiv{position:absolute;width:400px;left:50%;top:50%;margin-left:-200px;height:auto;z-index:100;background-color:#fff;border:1px #8FA4F5 solid;padding:1px;}
		#pjdiv h2{height:25px;font-size:14px;background-color:#8FA4F5;position:relative;padding-left:10px;line-height:25px;}
		#pjdiv h2 a{position:absolute;right:5px;font-size:12px;color:#FF0000;}
		#pjdiv .form{padding:10px;}
	</style>
  </head>
  
  <body>
  	<div style="margin:0 auto;border:1px solid green; width:80%;height:1200px;">
  	<div style="width:100%;height:320px;">
  		<div style="float:left; width:50%;">
		  	<br><c:if test="${not empty user}">
		   		<h4>欢迎用户 ${user.yonghuName} 使用校园任务系统！</h4><br/>
		   		 <h4>您所在的校区是<br/>
							<c:forEach items="${xiaoquList}" var="xiaoqulist">	
								<c:choose>
								<c:when test="${xiaoqulist.xiaoquId==user.xiaoquId}">
									<c:forEach items="${xuexiaoList}" var="xuexiaolist">
										<c:choose>
										<c:when test="${xiaoqulist.xuexiaoId==xuexiaolist.xuexiaoId}">
												${xuexiaolist.xuexiaoName}
										</c:when>
										</c:choose>
									</c:forEach>
										${xiaoqulist.xiaoquName}
								</c:when>
								</c:choose>
							</c:forEach>
				</h4>			
		   	</c:if>
	   	</div>
	   	<div style="float:left;">
	   		<form action="../addxuqiu/${user.yonghuId}.htm" method="post">
	   			<table border="1px;">
	   			<tr>
	   				<td>任务标题</td>
	   				<td><input type="text" name="xuqiuTitle"></td>
	   			</tr>
	   			<tr>
	   				<td>任务内容</td>
	   				<td><textarea name="xuqiuContent" cols="20" rows="6" ></textarea></td>
	   			</tr>
	   			<tr>
	   				<td>酬劳</td>
	   				<td><input type="text" name="xuqiuMoney"></td>
	   			</tr>
	   			
	   			<tr>
	   				<td>截止时间</td>
	   				<td><input type="text" name="xuqiuDeadLine"></td>
	   			</tr>
	   			<tr>
	   			<td>所属类别</td>
	   					<td><select name="xuqiuFenleiId">
	   						<c:if test="${not empty fenleiList}">
   								<c:forEach items="${fenleiList}" var="fenleilist">
								<option value="${fenleilist.xuqiuFenleiId}">
									${fenleilist.xuqiuFenleiName}
								</option>
								</c:forEach>
   							</c:if>
	   						</select>
	   					</td> 
	   			</tr>
	   			<tr><td></td><td><input type="submit" class="btn" id="j-gotoService" value="发布任务"></td></tr>
	   			</table>
	   		</form>
	   	</div>
	   	
	   	<div id="faqbg"></div>
	   	 	<div id="faqdiv" style="float:left;display:none;">
	   	 	<h2>信息窗口<a href="#" class="close">关闭</a></h2>
			<div class="form">
		   		<form action="" method="post" id="fff">
		   			<table width="360px;">
		   			<tr>
		   				<td>认领说明</td>
		   				<td><textarea name="renlingContent" cols="20" rows="6" ></textarea></td>
		   			</tr>
		   			
		   			<tr><td></td><td><input type="submit" class="btn" id="j-gotoService" value="认领"></td></tr>
		   			</table>
		   		</form>
	   		</div>
	   	</div>
	   	
	   	<div id="pj"></div>
	   	 	<div id="pjdiv" style="float:left;display:none;">
	   	 	<h2>信息窗口<a href="#" class="close2">关闭</a></h2>
			<div class="form">
		   		<form action="" method="post" id="fff2">
		   			<table width="360px;">
		   			<tr>
		   				<td>评价分数</td>
		   				<td><input type="text" name="pinjiaRenlingValue"></td>
		   			</tr>
		   			<tr>
		   				<td>评价内容</td>
		   				<td><textarea name="pinjiaRenlingContent" cols="20" rows="6" ></textarea></td>
		   			</tr>
		   			
		   			<tr><td></td><td><input type="submit" class="btn" id="j-gotoService" value="评价"></td></tr>
		   			</table>
		   		</form>
	   		</div>
	   	</div>
   	</div>
   	
   	<div style="width:92%; height:850px;border:1px solid blue;margin:0px auto;">
   		<div style="width:100%; height:30px; border-bottom:3px solid #666;">
        <div style="height:27px; width:100px;float:left;line-height:27px; font-weight:bolder;">
        	<font id="zt">选择</font>
        </div>
        <div style="width:300px;height:27px;float:right;word-spacing: -8px;font-size:14px; line-height:27px;">
            <ul class="tabName">
                <li id="tab1" onmouseover="showTab(this)"><a>我的需求</a></li>
                <li id="tab3" onmouseover="showTab(this)"><a>我的认领</a></li>
                <li id="tab2" onmouseover="showTab(this)"><a>最新发布</a></li>
            </ul>
        </div>
    </div>    
		<div id="tab1_content" class="content" style="display:block;">
        	<div class="hover">
        	      <!-- test 1 -->
                  <div>
                      <h3>我的需求</h3>
                      	<c:if test="${empty xuqiu}">
				   			<h4>还未发布需求</h4>
				   		</c:if>
				   		<form action="" method="get">
				   			
				   		<c:if test="${not empty xuqiu}">
				   		<a href="javascript:;" onclick="select_all();">全选</a> 
				   			/ 
				   			<a href="javascript:;" onclick="deselect_all();">全部不选</a>
				   			<a href="javascript:;" onclick="selectDelete();">删除</a>
				   		<table border="1px;" cellspacing="0" cellpadding="0" align="center" class="ttt2">
				   			<tr align="center">
				   				<td></td>
				   				<td>需求编号</td>
				   				<td>标题</td>
				   				<td>内容</td>
				   				<td>酬劳（元）</td>
				   				<td>发布时间</td>
				   				<td>截止时间</td>
				   				<td>所属类别</td>
				   				<td>认领情况</td>
				   			</tr>
				   			
					   		
					   			<c:forEach items="${xuqiu}" var="xq">
					   				<tr align="center">
					   					<td><input type="checkbox" value="${xq.xuqiuId}" name="chkUserInfos" /></td>
					   					<td>${xq.xuqiuId}</td>
					   					<td>${xq.xuqiuTitle}</td>
						   				<td><textarea cols="15" rows="6" >${xq.xuqiuContent}</textarea></td>
						   				<td>${xq.xuqiuMoney}</td>
						   				<td>${xq.xuqiuAddTime}</td>
						   				<td>${xq.xuqiuDeadLine}</td>
						   				<td>
						   					<c:forEach items="${fenleiList}" var="fenlei">
												<c:choose>
													<c:when test="${fenlei.xuqiuFenleiId ==xq.xuqiuFenleiId}">
														${fenlei.xuqiuFenleiName}
													</c:when>
												
												</c:choose>
											</c:forEach>
										</td>
										<td class="aaa"><a href="../renling/${xq.xuqiuId}.htm">认领情况</a></td>
						   			</tr>
					   			</c:forEach>
					   		
				   		</table>
				   		</c:if>
				   		</form>
                  </div>  	
			</div>
        </div>
        
        <div id="tab3_content" class="content">
        	<div class="hover">
        	      <!-- test 1 -->
                  <div>
                      <h3>我的认领</h3>
                      	<c:if test="${empty relingList}">
				   			<h4>还未认领需求</h4>
				   		</c:if>
				   		<form action="" method="get">
				   		<c:if test="${not empty relingList}">
				   		<table border="1px;" cellspacing="0" cellpadding="0" align="center" class="ttt2">
				   			<tr align="center">
				   				<td>认领编号</td>
				   				<td>需求标题</td>
				   				<td>认领说明</td>
				   				<td>认领时间</td>
				   				<td>状态</td>
				   				<td>操作</td>
				   			</tr>
				   			
					   		
					   			<c:forEach items="${relingList}" var="relinglist">
					   				<tr align="center">
					   					
					   					<td>${relinglist.renlingId}</td>
					   					
					   					<c:forEach items="${xuqiuList}" var="xuqiulist">
											<c:choose>
												<c:when test="${xuqiulist.xuqiuId==relinglist.xuqiuId}">
														<td>${xuqiulist.xuqiuTitle}</td>
												</c:when>
											</c:choose>
										</c:forEach>
										
						   				<td><textarea cols="15" rows="6" >${relinglist.renlingContent}</textarea></td>
						   				<td>${relinglist.renlingAddTime}</td>
										
										<c:choose>
											<c:when test="${relinglist.renlingIsDeal == true}">
													<td>已被选中</td>
													<td class="aaa"><input value="评价" class="pin" id="${relinglist.xuqiuId}" name="${relinglist.yonghuId}" type="button"></td>
											</c:when>	
										</c:choose>
										<c:choose>
											<c:when test="${relinglist.renlingIsDeal != true}">
													<td>还未被选中</td>
													<td class="aaa"><a href="../deleteRenling/${relinglist.renlingId}.htm">取消认领</a></td>		
											</c:when>
										</c:choose>
						   			</tr>
					   			</c:forEach>
					   		
				   		</table>
				   		</c:if>
				   		</form>
                  </div>  	
			</div>
        </div>
        <div id="tab2_content" class="content">
        	<div class="hover">
        	      <!-- test 1 -->
                  <div>
                      <h3>最新发布</h3>
                      		<select id="leibie">
					   		<option value="0">请选择类别</option>
					   	</select>
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
				   			<div style="display:block;">	
							<table id="all" border="1px;" cellspacing="0" cellpadding="0" align="center" class="ttt" >
					   		<c:if test="${not empty xuqiuList}">
					   			<c:forEach items="${xuqiuList}" var="xuqiulist">
					   				<tr align="center">
					   					<td>${xuqiulist.xuqiuTitle}</td>
						   				<td><textarea name="xuqiuContent" cols="10" rows="6" >${xuqiulist.xuqiuContent}</textarea></td>
						   				<td>${xuqiulist.xuqiuMoney}</td>
						   				<td>${xuqiulist.xuqiuAddTime}</td>
						   				<td>${xuqiulist.xuqiuDeadLine}</td>
						   				
						   					<c:forEach items="${userList}" var="userlist">
												<c:choose>
													<c:when test="${userlist.yonghuId==xuqiulist.yonghuId}">
														<td>${userlist.yonghuName}</td>
														<td>
														<c:forEach items="${xiaoquList}" var="xiaoqulist">	
															<c:choose>
																<c:when test="${xiaoqulist.xiaoquId==userlist.xiaoquId}">
																	<c:forEach items="${xuexiaoList}" var="xuexiaolist">
																		<c:choose>
																			<c:when test="${xiaoqulist.xuexiaoId==xuexiaolist.xuexiaoId}">
																				${xuexiaolist.xuexiaoName}<br/>
																			</c:when>
																		</c:choose>
																	</c:forEach>
																	${xiaoqulist.xiaoquName}
																</c:when>
															</c:choose>
														</c:forEach>
														</td>
													</c:when>
												
												</c:choose>
											</c:forEach>
										
						   				<td>
						   					<c:forEach items="${fenleiList}" var="fenlei">
												<c:choose>
													<c:when test="${fenlei.xuqiuFenleiId==xuqiulist.xuqiuFenleiId}">
														${fenlei.xuqiuFenleiName}
													</c:when>
												
												</c:choose>
											</c:forEach>
										</td>
										<td class="aaa"><input value="抢" class="but" id="${xuqiulist.xuqiuId}" name="${xuqiulist.yonghuId}" type="button"></td>
						   			</tr>
					   			</c:forEach>
					   		</c:if>
				   		</table>
				   		</div>
                  </div>  	
			</div>
        </div>
       
    </div>
   	
   </div>

  </body>
</html>
