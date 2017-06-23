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
					<div id="allUser" class="">
						<c:if test="${not empty error}">
							<div class="errorblock">
								${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
						</c:if>


						<div id="editUser">

							<div class="infodashboard">
								<div class="pageimg">
									<img class="boximg" src="/resources/images/change-profile.png">
								</div>
								<div class="pageimg">
									<ul>
										<li class="infotitlehead">Change Profile</li>
										<li>Update your information</li>
									</ul>
								</div>
							</div>
							<div class="clrdiv"></div>
							<form:form name="editUserfrm" id="editUserfrm"
								style="display: inline;" method="post">
								<!-- <div class="backlink" align="right"><img id="editBackUser" src="/resources/images/backbtn.png"/>&nbsp;<a id="backMember">Back</a></div> -->
								<div class="mainDiv">
									<div class="headleft hideall">
										<p class="contlbl">
											Organization<font color="red">*</font>
										</p>
										<p class="continput">
											<form:select path="org" id="editActivityOrg"
												name="editActivityOrg" maxlength="50" class="fieldinputac"></form:select>
										</p>
									</div>
									<div class="headleft">
										<p class="contlbl">
											First Name<font color="red">*</font>
										</p>
										<p class="continput">
											<form:input path="firstName" type="text" id="editFirstName"
												name="editFirstName" maxlength="50" class="fieldinputac"></form:input>
										<!-- <p id="editFirstNameError" class="hideall errorMsg">This
											information is required
										<p></p> -->
									</div>
									<div class="headright">
										<p class="contlbl">
											Last Name<font color="red">*</font>
										</p>
										<p class="continput">
											<form:input path="lastName" class="fieldinputac"
												name="editLastName" id="editLastName"></form:input>
										</p>
										<!-- <p id="editLastNameError" class="hideall errorMsg">This
											information is required
										<p> -->
									</div>

									<div class="headleft">
										<p class="contlbl">
											Email<font color="red">*</font>
										</p>
										<p class="continput">
											<form:input path="email" type="text" id="editUserEmail"
												name="editUserEmail" maxlength="50" class="fieldinputac"></form:input>
										<!-- <p id="editUserEmailError" class="hideall errorMsg">This
											information is required
										<p> -->
										<p id="editUserEmailValidError" class="hideall errorMsg">Email
											already exists
										<p></p>
									</div>

									<div class="headright">
										<p class="contlbl">
											Role<font color="red">*</font>
										</p>
										<p class="continput">
											<form:select path="role" class="profileSignUp fieldinputapp "
												name="editUserRole" id="editUserRole"></form:select>
										</p>
										<!-- <p id="editUserRoleError" class="hideall errorMsg">This
											information is required
										<p> -->
									</div>
									<div class="headleft">
										<p class="contlbl">
											Phone<font color="red">*</font>
										</p>
										<p class="contField">
											<form:select path="phoneCode" type="text"
												id="editUserPhoneCode" name="editUserPhoneCode"
												maxlength="50"></form:select>
											<form:input path="phone" type="text" id="editUserPhone"
												name="editUserPhone" maxlength="50" class="fieldinputac"></form:input>
										<!-- <p id="editUserPhoneError" class="hideall errorMsg">This
											information is required
										<p></p> -->
									</div>

									<div class="headright">
										<p class="contlbl">
											Timezone<font color="red">*</font>
										</p>
										<p class="continput">
											<form:select path="timezone"
												class="profileSignUp fieldinputapp" name="editUserTimezone"
												id="editUserTimezone"></form:select>
										</p>
										<!-- <p id="editUserTimezoneError" class="hideall errorMsg">This
											information is required
										<p> -->
									</div>

									<div class="creCanBtn btnAlign">
										<p>
											<input type="button" value="Update" class="signupbutton"
												id="editSubmitUser" />
										</p>
										<p>
											<input type="button" value="Cancel" class="cancelbutton"
												id="editCancelUser" />
										</p>
									</div>
								</div>
								<form:hidden id="editUserId" path="userId" />
								<form:hidden id="editContUserId" path="contactId" />
							</form:form>
						</div>

						<!-- <div class="page-body-footer"></div> -->
					</section>
				</div>
			</div>
		</div>
	</div>

	<div id="dlgsProfileMsg" title="Message"></div>
	<!-- 	<input type="hidden" id="roleId" name="roleId"/> -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-min.js"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/>></script>
	<!-- <script type="text/javascript" src=<c:url value="/resources/js/PIE_IE678.js"/>></script> -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/profile.js"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery.maskedinput-1.3.min.js"/>></script>
	<p class="foot">
		<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version
			1.0</a> <span class="sep">|</span>
		<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
		<span>&copy; 2013 Zingcrm. All rights reserved.</span>
	</p>
</body>
<script type="text/javascript">
	$('#FEA_PROFILE').addClass("active");
</script>
</html>