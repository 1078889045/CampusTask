<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>班级信息管理中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
body {
	padding:0px;
	font-size:14px;
	width:auto;
	margin:0 auto;
	font-family: "黑体";
}
form {border:0px; padding:0px; margin:0px;}
input{ padding:0px; margin:0px;}
.gs{font-family:宋体;}
.gs:link{color:#5151FF; text-decoration:none;}
.gs:visited{color:#5151FF;}
.gs:hover{ text-decoration:underline;}
.false1{font-size:12px;color:#F00; line-height:40px;}
</style>
	
  </head>
  
  
  
  <body>
    <div id="ss" style="height:600px; width:700px;border:5px #FF7B3C dashed;margin:140px auto;">
    	<table width="695" height="593">
  <tr>
    <td colspan="2"><marquee direction=left><font color="#008000" size="+3">欢迎使用校园任务系统！</font></marquee></td>
    </tr>
  <tr>
    <td width="373">
    	<div style="width:350px;height:40px;">
    		<div style="float:left;color:#F90;padding-left:30px;width:88px;height:40px; line-height:40px; font-size:24px; font-weight:bolder;">
            	校园
    		</div>	
       		<div style="float:left;color:#999;width:108px;height:40px; line-height:40px; font-size:24px; font-weight:bolder;">    
            	任务系统
    		</div>
        </div>
        <div style="width:350px; font-family:宋体;margin-top:-10px;">
        	<p>校园任务系统是一个采用JAVA和SQL等多种数据库构建的门户网站解决方案，祝您工作愉快！</p>
        </div>
    </td>
    <td width="310">
    	<form name="form1" method="post" action="../login.htm">
            <table width="308" height="180">
              <tr>
                <td width="80">用户名：</td>
                <td width="211"><input type="text" id="yhm" name="yonghuName"/></td>
              </tr>
              <tr>
                <td>密码：</td>
                <td><input type="password" id="mm" name="yonghuPwd"/></td>
              </tr>
              <tr>
                <td>身份</td>
                <td>
                	<select name="sf" id="sf">
                    	<option value="1" id="1">学生</option>
                    </select>
                </td>
              </tr>
             
              <tr>
                <td>&nbsp;</td>
                <td>
                	<input type="submit" value="提交"/>
                    <input type="reset" value="重置" style="margin-left:50px;"/>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                	<h3>如无账号，请先<a href="register.jsp">注册</a></h3>
                </td>
              </tr>
            </table>
      
      	</form>
      </td>
  </tr>
  <tr>
    <td colspan="2" align="center">&copy;2014, <a href="http://user.qzone.qq.com/1078889045/infocenter" class="gs">Zz___xx软件技术有限公司</a></td>
    </tr>
</table>

</div>
  </body>
  
</html>
