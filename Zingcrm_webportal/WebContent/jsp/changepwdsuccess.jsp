<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=9" /> -->
<meta http-equiv="X-UA-Compatible" content="Edge" />
<title>Zingcrm</title>
<link type="text/css" href=<c:url value="/resources/css/style.css"/> rel="stylesheet" media="all" />
<!-- <link type="text/css" href=<c:url value="/resources/css/reg.css"/> rel="stylesheet" media="all" /> -->
<link type="text/css" href=<c:url value="/resources/css/headerfooter.css"/> rel="stylesheet" media="all" />
<link type="text/css" href=<c:url value="/resources/css/jquery-ui-1.9.2.custom.min.css"/> rel="stylesheet" />
<link rel="shortcut icon" type="images/x-icon" href="resources/images/favicon.ico"/>
<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="ie8Style.css" />
<![endif]-->

</head>

<body>
	<div class="page-body-wrapper">
	<div class="page-body">
	<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/tik.jpg">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Password Success</li>
									<!-- <li>Lorem ipsum dolor sit amet.</li> -->
								</ul>
							</div>
						</div>
	<table width="100%" border="0">
		<tr>
			<td><a href="/"> <span class="logo"></span>
			</a>
				<%-- <div id="titleheadbg">
					<p id="titlehead">
						<spring:message code="label.changePwdSuccess" />
					</p>
				</div> --%>
				<div class="container">
					<form id="successfrm" name='f' action="">
						<table id="successtable">
							<tr>
								<td>
									<p class="successP">
										<spring:message code="label.changePwdSuccess" />.<spring:message code="label.changePwdSuccessContent" />
									</p>
									<p class="successbtnnew"><!-- style="width:365px;float:right;"> -->
										<a href="/" class="clickbutton"> <spring:message
												code="label.homePage" />
										</a>
									</p>
								</td>
							</tr>
						</table>
					</form>
				</div></td>
		</tr>
	</table>
	</div></div>
			   
			<p class="foot">
				<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version 1.0</a>
				<span class="sep">|</span>
				<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
				<span>&copy; 2013 Zingcrm. All rights reserved.</span>			
			</p>	
	
	
</body>
</html>