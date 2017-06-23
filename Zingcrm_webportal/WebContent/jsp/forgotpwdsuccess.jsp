<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Zingcrm - Forgot Password</title>
<link type="text/css" href=<c:url value="/resources/css/login.css"/> rel="stylesheet" media="all" />
<link rel="shortcut icon" href="/resources/images/favicon.ico"/> 
<!--[if IE 8]>
<style type="text/css">
#login{
	behavior:url(../resources/css/PIE.htc);
	margin:5em auto;
	background:#fff;
	border:8px solid #eee;
	width:500px;
	border-radius:5px;
	box-shadow:0 0 10px #4e707c;
	text-align:left;
	position:relative;
	}
#login .button{
	behavior:url(../resources/css/PIE.htc);
	border:0;
	padding:0 30px;
	height:30px;
	line-height:30px;
	text-align:center;
	font-size:12px;
	color:#fff;
	text-shadow:#007dab 0 1px 0;
	background:#32A6DC;
	border-radius:50px;
	cursor:pointer;
	position:relative;
	z-index: 0;
	}

#login .button:hover{
	behavior:url(../resources/css/PIE.htc);
	opacity:0.8;	
	}
	</style>
<![endif]-->
</head>
<body>
<table width="100%" border="0">
	<tr><td>
	<!-- <a href="http://www.neshwms.com">
					<span class="logo"></span>
					<span class="logohead">WMS</span>
				</a> -->
	<div id="titleheadbg">
		<p id="titlehead">
			Success!
		</p>
	</div>	
	<div class="container" id="login">
			 <h1><strong>Your password has been reset successfully!</strong></h1>					
  			<p>As per your request, your password has been reset and the new password is sent to your email account, please make sure to also check your spam filter if your email is not there in the next hour.</p> 
<p>If you have problems signing in, contact <a href="mailto:support@zingcrmqa.neshinc.com"  id="forgotpass" style="color:#0072C6;">support@zingcrmqa.neshinc.com
</a></p><br/>           
		</div>
		
	</td></tr>	
	</table>   
		    <p class="back">&copy; 2013 Zingcrm. All rights reserved.</p>
</body>
</html>