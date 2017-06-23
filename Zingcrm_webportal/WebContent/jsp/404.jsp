<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zingcrm - 404 Error Page</title>
<link type="text/css" href=<c:url value="/resources/css/login.css"/>
	rel="stylesheet" media="all" />
<!--[if IE 8]>
                            <style type="text/css">
                                #login {
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
                                #login.button {
                                    behavior:url(../resources/css/PIE.htc);
                                    border:0;
                                    padding:0 30px;
                                    height:30px;
                                    line-height:30px;
                                    text-align:center;
                                    font-size:12px;
                                    color:#fff;
                                    text-shadow:#007dab 0 1px 0;
                                    background:#0cbaff;
                                    border-radius:50px;
                                    cursor:pointer;
                                    position:relative;
                                    z-index: 0;
                                }
                                #login.button:hover {
                                    behavior:url(../resources/css/PIE.htc);
                                    opacity:0.8;
                                }
                            </style>
                        <![endif]-->
<link rel="shortcut icon" type="images/x-icon"
	href="resources/images/favicon.ico" />
</head>

<body>
	<table width="100%" border="0">
		<tr>
			<td><a href="/"> <span class="logo"></span>
			</a>
				 <div id="titleheadbg">
					<p id="titlehead">
						<spring:message code="label.pageNotFound" />
					</p>
				</div> 
				<%-- <div class="page-body-container">
					<div class="page-body-wrapper">
						<div class="page-body">
							<div class="search">
								<section class="main">
								<form class="contentsearch" name='search'>
									<p class="head">
										<spring:message code="label.pageNotFound" />
									</p>
									<table width="850" cellspacing="0" cellpadding="10">
										<tbody>
											<tr>
												<td>
													<p>
														<a href="/" class="clickbutton"> <spring:message
																code="label.homePage" />
														</a>
													</p>
												</td>
											</tr>
										</tbody>
									</table>
								</form>
								</section>
							</div>
						</div>
					</div>
				</div> --%>
				<div class="page-body-wrapper">
	<div class="page-body">
	<div class="infodashboard">
							<div class="pageimg">
								<img src="/resources/images/404.jpg" class="boximg">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">404 Error Page</li>
									<li>Lorem ipsum dolor sit amet.</li>
								</ul>
							</div>
						</div>
	<table width="100%" border="0">
		<tbody><tr>
			<td><!-- <a href="/"> <span class="logo"></span>
			</a> -->
				
				<div class="container1">
					<form action="" name="f" id="successfrm">
						<div style="width:880px;float:left; margin-bottom: 80px;">
						<div id="successtable">
							
									
									<p class="successP">
										404 Error Sorry, this page was not found. 
									</p>
									<p class="successbtnnew"><!-- style="width:365px;float:right;"> -->
										<a class="clickbutton" href="/"> Click here to login
										</a>
									</p>
									
								
						</div>
						</div>
					</form>
				</div></td>
		</tr>
	</tbody></table>
	</div></div>
				</td>
		</tr>
	</table>
	<%@ include file="footer.jsp"%>
	<!-- <script type="text/javascript"
		src=<c:url value="/resources/js/PIE_IE678.js"/>></script> -->
</body>

</html>