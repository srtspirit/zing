<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link type="text/css" href=<c:url value="/resources/css/style.css"/>
	rel="stylesheet" media="all" />
<link type="text/css"
	href=<c:url value="/resources/css/headerfooter.css"/> rel="stylesheet"
	media="all" />
<link type="text/css"
	href=<c:url value="/resources/css/jquery-ui-1.9.2.custom.min.css"/>
	rel="stylesheet" />
<link type="text/css" href=<c:url value="/resources/css/jqgrid.css"/>
	rel="stylesheet" media="all" />
<!-- <link rel="shortcut icon" href="/resources/images/favicon.ico" /> -->
<link rel="shortcut icon" type="images/x-icon" href="resources/images/favicon.ico"/>
<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="ie8Style.css" />
<![endif]-->

</head>
<body>

	<div class="topHeaderBg">
		<div style="width: 60%; padding-left: 20px; float: left;">
			Hello,&nbsp;<%=request.getSession().getAttribute("name")%>
		</div>
		<div style="width: 84px; paddding-right: 20px; float: right;">
			<a href="<c:url value="j_spring_security_logout" />"><img
				src="/resources/images/logout-image.png"></a>
		</div>
	</div>
	<table width="100%" border="0" cellspacing=0 cellpadding=0>
		<tr>
			<td></td>
			<td>
				<!-- <table id="header" class="logoheader">
    		<tr>
    		  <td class="tdlogoheader">	
				<a href="http://www.neshwms.com">
					<span class="logo"></span>
				</a>
			  </td>
			  <td>
			  
				<header id="header-topbar-wrapper">
				<div id="header-topbar">
					<div id="header-account-controls" class="clr">
						
					</div>
				</div>
				</header>
			  </td>
			</tr>
		</table> -->

				<div id=menus>
					 <%=request.getSession().getAttribute("menu")%> 
					
					<div class="zinglogo">
						<a href="/index"> <img src="/resources/images/zingLogo.png" height="65px;"></a>
					</div>
				</div>
				<%String var =(String)request.getSession().getAttribute("menu");
					if(var==null){
						out.println("session=null");
						request.getSession().invalidate();
						response.sendRedirect("/login");
					}
					
					%>
</body>
</html>