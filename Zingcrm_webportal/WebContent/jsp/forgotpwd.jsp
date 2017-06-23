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
<!--[if IE 9 ]>

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
	
#titlehead {
    behavior:url(../resources/css/PIE.htc);
    color: #FFFFFF;
    float: left;
    font-family: roboto;
    font-size: 30px;
    letter-spacing: -1px;
    margin-left: 406px;
    padding-top: 20px;
    position: relative;
}	

#titleheadbg {
    behavior:url(../resources/css/PIE.htc);
    margin-top: 80px;
    min-height: 84px;
    width: 100%;
}
	</style>
<![endif]-->
</head>
<body>
	<div class="headblck"></div>
<table width="100%" border="0">

	<tr><td>
	<a href="/">
					<span class="logo"></span>
					<!-- <span class="logohead">WMS</span> -->
				</a>
	<div id="titleheadbg">
		<p id="titlehead">
			Forgot Password
		</p>
	</div>	
	
	<div class="container" id="loginfrgt">
			<h2><p class="forgotPwd">Forgot Password</p></h2>
			 <h1><strong>To reset your password, enter your email. Email will be sent to your registered email id</strong></h1>					
  			
            <form:form name="forgotpwdfrm" id="forgotpwdfrm" style="display: inline;" method="post" action="/forgotpwd"> 
            <p id="Username_NotExistError" class="hideall" style="   color: #C85305;       margin-top: 12px;    overflow: hidden;    word-wrap: break-word;	float: left;" element="div" />Enter valid email</p>
            <form:errors path="*" cssClass="errorblock" class="errorblock hideall" element="div" id="errorblock" />	
            
            			<div>
							
							<label>Your Email</label>
							<form:input type="text" path="username" id="username"
								name="username" maxlength="100" style="color: #000;"
								class="field" placeholder="Email" />
								
						</div>
						<table class="buttable">
							<tr>
								<td><input type="button" name="btncancel" id="btncancel"
									value="Cancel"
									class="cancelbutton" /> </td>
									
								<td><input type="button" name="submitfrm" id="submitfrm"
									value="Send Email"
									class=" signupbutton signupbtnalign" /></td>
							</tr>
						</table>
						<br/><br/><br/>
			 </form:form>
			 <p class="back">&copy; 2013 Zingcrm. All rights reserved.</p> 
		</div>
	</td></tr>	
	</table>   
		    
		 
		<script type="text/javascript" src=<c:url value="/resources/js/jquery-min.js"/>></script>
		<script type="text/javascript" src=<c:url value="/resources/js/jquery-ui-min.js"/>></script> 
		<script type="text/javascript" src=<c:url value="/resources/js/mm.js"/>></script>
		 <script type="text/javascript">
		 function popupdialog(url) {
				var newwindow;
				newwindow = window
						.open(
								url,
								'name',
								'height=500,width=400,left=800,top=100,resizable=yes,scrollbars=yes,toolbar=yes,status=yes');
				if (window.focus) {
					newwindow.focus();
				}
			}
		
		function newSignClearValues()
		{
			$('#username').val('');
			$('#Username_NotExistError').addClass('hideall');			
		}
		$(function()
				{
				
					newSignClearValues();
					
					$('#btncancel').click(function() {
						window.location.href = "/";
					});

					 $('#submitfrm').click(function()
					{
					 	var flag=true;	
						 $('.errorblock').html('');
							if($.trim($('#username').val())==='')
							{
								$('#Username_NotExistError').removeClass('hideall');
								flag=false;
							}
							
							if(flag)
							{
								$('#forgotpwdfrm').submit();
								
							}
								
					 });
					 
					 $('#username').focusout(function()
						{
							if($.trim($('#username').val())==='')
							{
							   $('#Username_NotExistError').removeClass('hideall');
							   $('#errorblock').addClass('hideall');
							}
							else
							{
								$('#Username_NotExistError').addClass('hideall');						
							}
						}); 
					 $('#username').focus(function()
					{
						 newSignClearValues();
					}); 
								
				});
		</script>
		</body>		
		</html>