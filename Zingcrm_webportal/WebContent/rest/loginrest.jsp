<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WMS - Login</title>
<link type="text/css" href=<c:url value="/resources/css/login.css"/> rel="stylesheet" media="all" />
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
<link rel="shortcut icon" type="images/x-icon" href="resources/images/favicon.ico"/>
</head>
<!-- <body style="background: #e8ebff url(../resources/images/bg.png) repeat top left;"> -->
<body>
<table width="100%" border="0">
	<tr><td>
	<a href="http://www.neshwms.com">
					<span class="logo"></span>
					<!-- <span class="logohead">WMS</span> -->
				</a>
	<div id="titleheadbg">
		<p id="titlehead">
			Sign in to your WMS account
		</p>
	</div>	
	<div class="container">
			
		<form id="loginrest" name='f' action="/loginrest/chkValidation" method='POST'>		 

		    <h1><strong>Work Order Management</strong><img src="../resources/images/login.png" class="imglogin"/></h1>
		    
		    <p class="register">Not a member? <a href="registration">Register here!</a></p>
		    <p id="chkAvailablity" class="hideall error">Invalid credentials</p>
		  	<div>		    	
		    	<label for="login_username">Username</label>
		    	<p id="email_Error" class="error hideall">Enter an email address.</p> 
		    	<input type="text" name="email" id="email" class="field" value="someone@example.com" onfocus="javascript: if(this.value == 'someone@example.com'){ this.value = ''; }" onblur="javascript: if(this.value==''){this.value='someone@example.com';}" />
		    </div>			
			
			<div>		    	
		    	<label for="login_password">Password</label>
		    	<p id="password_Error" class="error hideall">Enter the password address.</p>
		    	<input type="password" name="password" id="password" class="field" value="password" onfocus="javascript: if(this.value == 'password'){ this.value = ''; }" onblur="javascript: if(this.value==''){this.value='password';}" />
		    </div>	
		    		
		    
		    <p class="forgot"><a href="#">Forgot password?</a></p>
		    			
		    <div class="submit">
		        <!-- <button type="submit">Log in</button> -->		 
		        <input type="button" name="loginbutton" id="loginbutton" value="Log in" class="button"/>          
		        
		        <label>
		        	<input type="checkbox" name="remember" id="login_remember" value="yes" />
		            Remember me on this computer
		        </label>   
		    </div>
		    
		    <p class="back">&copy; 2013 WMS. All rights reserved.</p> 
		  
		</form>	
	</div>
	</td></tr>	
	</table>
		<script type="text/javascript" src=<c:url value="/resources/js/PIE_IE678.js"/>></script>
		<script type="text/javascript">
			$('#loginbutton').click(function() 
			{
				var flag=true;
				
				if($.trim($('#email').val())=='')
				{
					$('#email_Error').removeClass('hideall');
					flag=false;
				}
				
				if($.trim($('#password').val())=='')
				{
					$('#password_Error').removeClass('hideall');
					flag=false;
				}
				
				if(flag)
				{
					$.ajax(
					{ 
						url: "/loginrest/chkValidation/email="+'/'+$.trim($('#email').val())+'/'+'password='+$.trim($('#password').val()),
		      			cache:false,  
		      			success:function(isExist)
		      			{      
							if($.trim(isExist)==='success')
							{	
								$('#chkAvailablity').addClass('hideall');
							}
							else if($.trim(isExist)==='failed')
							{
								$('#chkAvailablity').removeClass('hideall');								
				 			}
		      			}
	  				});
				}
			
				
			});
	
			
		</script>
</body>
</html>