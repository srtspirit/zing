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
<script type="text/javascript">
window.history.forward();
function noBack() { window.history.forward(); }
</script>
<title>Zingcrm</title>
<link type="text/css" href=<c:url value="/resources/css/style.css"/>
	rel="stylesheet" media="all" />
<!-- <link type="text/css" href=<c:url value="/resources/css/reg.css"/> rel="stylesheet" media="all" /> -->
<link type="text/css"
	href=<c:url value="/resources/css/headerfooter.css"/> rel="stylesheet"
	media="all" />
<link type="text/css"
	href=<c:url value="/resources/css/jquery-ui-1.9.2.custom.min.css"/>
	rel="stylesheet" />
<link rel="shortcut icon" type="images/x-icon"
	href="resources/images/favicon.ico" />
<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="ie8Style.css" />
<![endif]-->

</head>

<body onpageshow="if (event.persisted) noBack();" onbeforeunload="" onLoad="noBack();">

	<div class="page-body-container">
		<div class="page-body-wrapper">
			<div class="page-body">
				<div class="filter">
					<section class="main">
					<div id="allActivity" class="">

						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/change-password.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Change Password</li>
									<li>Keep your account secure by choosing a strong password</li>
								</ul>
							</div>
						</div>
						<div class="clrdiv"></div>
						<div class="clrdiv"></div>
						<c:if test="${not empty error}">
							<div class="errorblock">
								${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
						</c:if>

						<table class="filtercontent" id="filtertable">
							<tr>
								<td>
									<div class="changepwddiv">
										<form:form name="frmchangepwd" id="frmchangepwd" style="display: inline;" method="post">
											<p id="emptyUsername_Error" class="error hideall"
												style="color: #FF0000;">
												<spring:message code="label.errEmailReq" />
											</p>
<%-- 											<div class="changepwdlbl">
												<label id="CurrentPassword" class="chngpwdlbl"> <spring:message
														code="label.currentpwd" /><font color="red">*</font>
												</label>
												<form:input type="password" path="currentpassword"
													id="currentpassword" name="currentPassword" minlength="8"
													maxlength="16" class="fieldinputac" />
												<p id="currentpasswordReq_Error" class="error hideall">
													<spring:message code="label.errCurrentPwdReq" />
												</p>
												<p id="currentpasswordValid_Error" class="error hideall">
													<spring:message code="label.errCurrentPwdValid" />
												</p>
											</div>
											<div class="changepwdlbl">
												<label id="CreatePassword" class="chngpwdlbl"> <spring:message
														code="label.newpwd" /><font color="red">*</font>
												</label>
												<form:input type="password" path="password" id="password"
													name="Password" minlength="8" maxlength="16"
													class="fieldinputac" />
												<p id="passwordReq_Error" class="error hideall">
													<spring:message code="label.errPwdReq" />
												</p>
												<p id="passwordValid_Error" class="error hideall">
													<spring:message code="label.errPwdValid" />
												</p>
											</div>
											<div class="changepwdlbl">
												<label id="ConfirmPassword" class="chngpwdlbl"> <spring:message
														code="label.conformPwd" /><font color="red">*</font>
												</label>
												<form:password path="confirmPassword" id="conformpassword"
													minlength="8" maxlength="16" class="fieldinputac" />
												<p id="conformPwdReq_Error" class="error hideall">
													<spring:message code="label.errConformPwdReq" />
												</p>
												<p id="conformPwdValid_Error" class="error hideall">
													<spring:message code="label.errConformPwdValid" />
												</p>
											</div> --%>
												<div class="changepwdheadleft">
												<label id="CurrentPassword" class="changepwdcontlbl"> <spring:message
														code="label.currentpwd" /><font color="red">*</font>
												</label>
												<form:input type="password" path="currentpassword"
													id="currentpassword" name="currentPassword" minlength="8"
													maxlength="16" class="changepwdinputac" />
												<p id="currentpasswordReq_Error" class="errorMsg hideall">
													<spring:message code="label.errCurrentPwdReq" />
												</p>
												<p id="currentpasswordValid_Error" class="errorMsg hideall">
													<spring:message code="label.errCurrentPwdValid" />
												</p>
											    </div>
											<div class="changepwdheadleft">
												<label id="CreatePassword" class="changepwdcontlbl"> <spring:message
														code="label.newpwd" /><font color="red">*</font>
												</label>
												<form:input type="password" path="password" id="password"
													name="Password" minlength="8" maxlength="16"
													class="changepwdinputac" />
												<p id="passwordReq_Error" class="errorMsg hideall">
													<spring:message code="label.errPwdReq" />
												</p>
												<p id="passwordValid_Error" class="errorMsg hideall">
													<spring:message code="label.errPwdValid" />
												</p>
											</div>
											<div class="changepwdheadleft">
												<label id="ConfirmPassword" class="changepwdcontlbl"> <spring:message
														code="label.conformPwd" /><font color="red">*</font>
												</label>
												<form:password path="confirmPassword" id="conformpassword"
													minlength="8" maxlength="16" class="changepwdinputac" />
												<p id="conformPwdReq_Error" class="errorMsg hideall">
													<spring:message code="label.errConformPwdReq" />
												</p>
												<p id="conformPwdValid_Error" class="errorMsg hideall">
													<spring:message code="label.errConformPwdValid" />
												</p>
											</div>
											<br />
											<!-- </div> -->
											<div class="updtCan">
												<input type="button"
													value='<spring:message code="label.cancel" />'
													class="cancelbuttonpadding cancelbutton "
													id="changepasswordcancel" /> <input type="button"
													name="btnsubmit" id="btnsubmit"
													value='<spring:message code="label.update" />'
													class="updatebutton signupbutton" />


											</div>
										</form:form>
									</div>
								</td>
							</tr>
						</table>
					</div>
					</section>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-min.js"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/>></script>
	<!-- <script type="text/javascript" src=<c:url value="/resources/js/PIE_IE678.js"/>></script> -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/changepwd.js"/>></script>
	<p class="foot">
		<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version
			1.0</a> <span class="sep">|</span>
		<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
		<span>&copy; 2013 Zingcrm. All rights reserved.</span>
	</p>
</body>

<script type="text/javascript">
	$('#FEA_CHANGEPWD').addClass("active");
</script>
</html>