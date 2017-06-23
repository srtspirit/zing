<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<script type="text/javascript">
window.history.forward();
function noBack() { window.history.forward(); }
</script>
<title>Zingcrm</title>
<link type="text/css" href=<c:url value="/resources/css/style.css"/>
	rel="stylesheet" media="all" />
<!-- <link type="text/css" href=<c:url value="/resources/css/reg.css"/> rel="stylesheet" media="all" /> -->
<link type="text/css"
	href=<c:url value="/resources/css/headerfooter.css?t=1.1"/> rel="stylesheet"
	media="all" />
<link type="text/css"
	href=<c:url value="/resources/css/jquery-ui-1.9.2.custom.min.css?t=1.1"/>
	rel="stylesheet" />
<link type="text/css" href=<c:url value="/resources/css/jqgrid.css?t=1.1"/>
	rel="stylesheet" media="all" />
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
						<form class="contentsearch" name='search' action="" method='POST'>

							<div class="infodashboard">
								<div class="pageimg">
									<img class="boximg" src="/resources/images/user.png">
								</div>
								<div class="pageimg">
									<ul>
										<li class="infotitlehead">User</li>
										<li>Build your Zing CRM team</li>
									</ul>
								</div>
							</div>
							<c:if test="${not empty error}">
								<div class="errorblock">
									${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
							</c:if>
							<div class="searchright">
								<div class="subhead">Search by Name</div>
								<p class="fieldtitle">
								<!-- 	<label id="name">Name</label>  --><select
										class="fieldinput hideall" name='filterUserOrgId'
										id="filterUserOrgId"></select> <input class="fieldinput"
										name='filterUserName' id="filterUserName"></input> <input
										type="button" name="SearchUserbutton" id="SearchUserbutton"
										value="Search" class="button searchBtn" />
									<!-- <span id="search_Error" class="filtererror hideall">Enter the company to search.</span> -->
								</p>
							</div>
							<div class="clrdiv"></div>
							<!-- <div id="tabs" class="maptab">
								<ul>
									<li><a href="#tabs-1">User</a></li>
								</ul>
								<div id="tabs-1"> -->
									<div id="showUserDataButtons" class="topbar"></div>
									<div id="showGridUser" class="shwworkordergrid">
										<div id="usersgrid" class="workordergrid">
											<table id="userList"></table>
											<div id="userPager"></div>
										</div>
									</div>
								<!-- </div>
							</div> -->
						</form>
					</div>
					<div id="createUser" class="hideall">

						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/user.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Create User</li>
									<li>Build your Zing CRM team</li>
								</ul>
							</div>
						</div>

						<form:form name="newUserfrm" id="newUserfrm"
							style="display: inline;" method="post">
							<div class="backlink" align="right">
								<img id="newBackUser" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>
							<div class="clrdiv"></div>
							<div class="mainDiv">
								<div class="headleft hideall">
									<p class="contlbl">
										Organization<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="org" id="newActivityOrg"
											name="newActivityOrg" maxlength="50" class="fieldinputac"></form:select>
									</p>
								</div>
								<div class="headleft">
									<p class="contlbl">
										First Name<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="firstName" type="text" id="newFirstName"
											name="newFirstName" maxlength="50" class="fieldinputac"></form:input>
									<p id="newFirstNameError" class="hideall errorMsg">This
										information is required
									<p></p>
								</div>
								<div class="headright">
									<p class="contlbl">
										Last Name<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="lastName" class="fieldinputac"
											name="newLastName" id="newLastName"></form:input>
									</p>
									<p id="newLastNameError" class="hideall errorMsg">This
										information is required
									<p>
								</div>

								<div class="headleft">
									<p class="contlbl">
										Email<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="email" type="text" id="newUserEmail"
											name="newUserEmail" maxlength="50" class="fieldinputac"></form:input>
									<p id="newUserEmailError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="newUserEmailValError" class="hideall errorMsg">Email
										is invalid format
									<p>
									<p id="newUserEmailValidError" class="hideall errorMsg">Email
										already exists
									<p></p>
								</div>

								<div class="headright">
									<p class="contlbl">
										Role<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="role" class="profileSignUp fieldinputapp"
											name="newUserRole" id="newUserRole"></form:select>
									</p>
									<p id="newUserRoleError" class="hideall errorMsg">This
										information is required
									<p>
								</div>

								<div class="headleft">
									<p class="contlbl">
										Create a password<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="password" type="password"
											id="newUserPassword" name="newUserPassword" maxlength="50"
											class="fieldinputac"></form:input>
									<p id="newUserPasswordError" class="hideall errorMsg">This
										information is required
									<p></p>
								</div>

								<div class="headright">
									<p class="contlbl">
										Re-enter password<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="confirmPassword" type="password"
											class="fieldinputac" name="newUserconfirmPassword"
											id="newUserconfirmPassword"></form:input>
									</p>
									<p id="newUserconfirmPasswordError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="newUserconfirmPasswordValidError"
										class="hideall errorMsg">Re-enter password does not match
									<p>
								</div>

								<div class="headleft">
									<p class="contlbl">
										Phone<font color="red">*</font>
									</p>
									<p class="contField">
										<form:select path="phoneCode" type="text"
											id="newUserPhoneCode" name="newUserPhoneCode" maxlength="50"></form:select>
										<form:input path="phone" type="text" id="newUserPhone"
											name="newUserPhone" maxlength="50" class="fieldinputac"></form:input>
									<p id="newUserPhoneError" class="hideall errorMsg">This
										information is required
									<p></p>
								</div>

								<div class="headright">
									<p class="contlbl">
										Timezone<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="timezone"
											class="profileSignUp fieldinputapp" name="newUserTimezone"
											id="newUserTimezone"></form:select>
									</p>
									<p id="newUserTimezoneError" class="hideall errorMsg">This
										information is required
									<p>
								</div>

								<div class="creCanBtn btnAlign">
									<p>
										<input type="button" value="Create" class="signupbutton"
											id="newSubmitUser" />
									</p>
									<p>
										<input type="button" value="Cancel" class="cancelbutton"
											id="newCancelUser" />
									</p>
								</div>
							</div>
						</form:form>
					</div>


					<div id="viewUser" class="hideall">

						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/user.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">View User</li>
									<li>Build your Zing CRM team</li>
								</ul>
							</div>
						</div>

						<div class="backlink" align="right">
							<img id="viewBackMember" src="/resources/images/backbtn.png" />&nbsp;<a
								id="viewBackMember"></a>
						</div>
						<div class="clrdiv"></div>
						<div class="subhead">User Information</div>
						<div class="clrdiv"></div>

					
						<div class="divTableuser">

							<div class="divRowuser">
								<div class="divCelluserlbl">Name:</div>
								<div id="viewFName" class="userinput divCelluser">uuu</div>

							</div>
							<div class="divRowuser">
								<div class="divCelluserlbl">Role:</div>
								<div id="viewRole" class="userinput divCelluser"></div>

							</div>
							<div class="divRowuser">
								<div class="divCelluserlbl">Email:</div>
								<div id="viewEmail" class="userinput divCelluser"></div>

							</div>
							<div class="divRowuser">
								<div class="divCelluserlbl">Phone No:</div>
								<div id="viewPhone" class="userinput divCelluser"></div>

							</div>
							<div class="divRowuser">
								<div class="divCelluserlbl">Status:</div>
								<div id="viewStatus" class="userinput divCelluser"></div>

							</div>
							<div class="divRowuser">
								<div class="divCelluserlbl">Time Zone:</div>
								<div id="viewTimezone" class="userinput divCelluser"></div>

							</div>

						</div>
						

						<div class="creCanBtn">
							<p>
								<input type="button" value="Close" class="cancelbutton"
									id="viewCloseUser" />
							</p>
						</div>
					</div>

					<div id="editUser" class="hideall">

						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/user.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Edit User</li>
									<li>Build your Zing CRM team</li>
								</ul>
							</div>
						</div>

						<form:form name="editUserfrm" id="editUserfrm"
							style="display: inline;" method="post">
							<div class="backlink" align="right">
								<img id="editBackUser" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>

							<div class="clrdiv"></div>

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
									<p id="editFirstNameError" class="hideall errorMsg">This
										information is required
									<p></p>
								</div>
								<div class="headright">
									<p class="contlbl">
										Last Name<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="lastName" class="fieldinputac"
											name="editLastName" id="editLastName"></form:input>
									</p>
									<p id="editLastNameError" class="hideall errorMsg">This
										information is required
									<p>
								</div>

								<div class="headleft">
									<p class="contlbl">
										Email<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="email" type="text" id="editUserEmail"
											name="editUserEmail" maxlength="50" class="fieldinputac"></form:input>
									<p id="editUserEmailError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="editUserEmailValError" class="hideall errorMsg">Email
										is invalid format
									<p>
									<p id="editUserEmailValidError" class="hideall errorMsg">Email
										already exists
									<p></p>
								</div>

								<div class="headright">
									<p class="contlbl">
										Role<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="role" class="profileSignUp fieldinputapp"
											name="editUserRole" id="editUserRole"></form:select>
									</p>
									<p id="editUserRoleError" class="hideall errorMsg">This
										information is required
									<p>
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
									<p id="editUserPhoneError" class="hideall errorMsg">This
										information is required
									<p></p>
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
									<p id="editUserTimezoneError" class="hideall errorMsg">This
										information is required
									<p>
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

					<!-- <div class="page-body-footer"></div> --> </section>
				</div>
			</div>
		</div>
	</div>

	<div id="dlgsMemberMsg" title="Message"></div>
	<div id="dlgDelMemberMsg" class="hideall" title="Message">Do you
		want to delete this user?</div>
	<!-- 	<input type="hidden" id="roleId" name="roleId"/> -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-min.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js?t=1.1"/>></script>
	<!-- <script type="text/javascript" src=<c:url value="/resources/js/PIE_IE678.js"/>></script> -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/i18n/grid.locale-en.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jqGrid.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/user.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery.maskedinput-1.3.min.js?t=1.1"/>></script>
	<p class="foot">
		<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version
			1.0</a> <span class="sep">|</span>
		<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
		<span>&copy; 2013 Zingcrm. All rights reserved.</span>
	</p>
</body>
<script type="text/javascript">
	$('#FEA_USER').addClass("active");
</script>
</html>