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
<link rel="shortcut icon" type="images/x-icon"
	href="/resources/images/favicon.ico" />
</head>

<body onpageshow="if (event.persisted) noBack();" onbeforeunload="" onLoad="noBack();">
	<div class="page-body-container">
		<div class="page-body-wrapper">
			<div class="page-body">
				<div class="filter">
					<section class="main">
					<div id="allLead">
						<div class="infodashboard2">

							<div class="pageimg">
								<img class="boximg" src="/resources/images/setting.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Settings</li>
									<li></li>
								</ul>
							</div>

						</div>
					</div>
					<div class="clrdiv"></div>

					<div class="ex12">
						<form:form name="newSettingfrm" id="newSettingfrm"
							style="display: inline;" method="post">
							<div>
								<div class="headleft hideall">
									<p class="contlbl">
										Organization<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="org" id="newSettingOrg"
											name="newSettingOrg" maxlength="50" class="fieldinputac"></form:select>
									</p>
								</div>

								<div class="headleft">
									<p class="contlbl txtspace">User Field</p>
									<p class="contchk">
										<input class="contPrivateCheck" type="checkbox" value="1"
											id="requiredFlagYes" />Required
									</p>
								</div>
								<div class="clrdiv"></div>

								<div class="headleft">
									<p class="contlbl">
										Parent Field<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="parentField" type="text" id="newParentField"
											name="newParentField" maxlength="50" class="fieldinputac"></form:input>
									<p id="newParentFieldError" class="hideall errorMsg">This
										information is required
									<p></p>

								</div>
								<div class="headrightChid">
									<p class="contlbl">
										Child Field<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="childField" type="text" id="newChildField"
											name="newChildField" maxlength="50" class="fieldinputac"></form:input>
									<p id="newChildFieldError" class="hideall errorMsg">This
										information is required
									<p></p>
								</div>
								<div class="creCanBtn2 creCanBtn2child">
									<p>
										<input type="button" value="Update" class="signupbutton"
											id="updateSetting" />
									</p>
									<p>
										<input type="button" value="Cancel" class="cancelbutton"
											id="cancelSetting" />
									</p>
								</div>
							</div>
						</form:form>
					</div>
					<div class="parChild">
						<div class="parLft">
							<form:form name="newSettingDropfrm" id="newSettingDropfrm"
								style="display: inline;" method="post">
								<div class="headleft hideall">
									<p class="contlbl">
										Organization<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="org" id="newSettingDropOrg"
											name="newSettingDropOrg" maxlength="50" class="fieldinputac">
											<option value=0>Select</option>
										</form:select>
									</p>
								</div>


								<div class="headleft">
									<p class="contlbl">
										Parent Field<font color="red">*</font>
									</p>
									<p class="continput">
										<select id="newParentDropField" name="newParentDropField"
											maxlength="50" class="fieldinputac"><option value=0>Select</option></select>
										<form:input path="parentField" type="text"
											id="newParentDropFieldText" name="newParentDropFieldText"
											maxlength="50" class="fieldinputac AddClass hideall"></form:input>
									</p>
									<p id="newParentDropFieldError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="newParentDropFieldTextError" class="hideall errorMsg">This
										information is required
									<p>
									<div class="creCanBtn2 defaultClass">
										<p>
											<input type="button" value="Add" class="signupbutton"
												id="data1AddSetting" />
										</p>
										<p>
											<input type="button" value="Edit" class="signupbutton"
												id="data1EditSetting" />
										</p>
									</div>
									<div class="creCanBtn2 AddClass hideall">
										<p>
											<input type="button" value="Save" class="signupbutton"
												id="data1SaveSetting" />
										</p>
										<p>
											<input type="button" value="Cancel" class="cancelbutton"
												id="data1CancelSetting" />
										</p>
									</div>

								</div>
								<form:hidden path="parentId" id="ParentID" />
							</form:form>

						</div>
						<div class="parRgt">
							<form:form name="newSettingChildDropfrm"
								id="newSettingChildDropfrm" style="display: inline;"
								method="post">
								<div class="headleft">
									<p class="contlbl">
										Child Field<font color="red">*</font>
									</p>
									<p class="continput">
										<select id="newChildDropField" name="newChildDropField"
											maxlength="50" class="fieldinputac"><option value=0>Select</option></select>
										<form:input path="childField" type="text"
											id="newChildDropFieldText" name="newChildDropFieldText"
											maxlength="50" class="fieldinputac AddChildClass hideall"></form:input>
									</p>
									<p id="newChildDropFieldError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="newChildDropFieldTextError" class="hideall errorMsg">This
										information is required
									<p>
									<div class="creCanBtn2 defaultChildClass">
										<p>
											<input type="button" value="Add" class="signupbutton"
												id="data1ChildAddSetting" />
										</p>
										<p>
											<input type="button" value="Edit" class="signupbutton"
												id="data1ChildEditSetting" />
										</p>
									</div>
									<div class="creCanBtn2 AddChildClass hideall">
										<p>
											<input type="button" value="Save" class="signupbutton"
												id="data1ChildSaveSetting" />
										</p>
										<p>
											<input type="button" value="Cancel" class="cancelbutton"
												id="data1ChildCancelSetting" />
										</p>
									</div>
								</div>
								<form:hidden path="childId" id="ChildID" />
								<form:hidden path="parentId" id="ParentChildID" />
							</form:form>

						</div>
					</div>

					<div class="changeSalesRepDetails">
						<p>Change Sales Rep</p>

						<div class="headLselect">
							<p class="contlbl">
								From<font color="red">*</font>
							</p>
							<p class="continput">
								<select id="newFromSalesField" name="newFromSalesField"
									maxlength="50" class="fieldinputac" multiple="multiple"></select>
							</p>
							<p id="newFromSalesFieldError" class="hideall errorMsg">This
								information is required
							<p>
						</div>
						<div class="headRselect">
							<p class="contlbl">
								To<font color="red">*</font>
							</p>
							<p class="continput">
								<select id="newToSalesField" name="newToSalesField"
									maxlength="50" class="fieldinputac" multiple="multiple"></select>
							</p>
							<p id="newToSalesFieldError" class="hideall errorMsg">This
								information is required
							<p>
							<p id="newFromToSalesFieldError" class="hideall errorMsg">Same
								user
							<p>
						</div>
						<div class="clrdiv"></div>

						<div class="creCanBtn2">
							<p>
								<input type="button" value="Change" class="signupbutton"
									id="changeRepSetting" />
							</p>
							<p>
								<input type="button" value="Cancel" class="cancelbutton"
									id="cancelRepSetting" />
							</p>
						</div>
					</div>
				</div>
				</section>
			</div>
		</div>
	</div>
	</div>


	<div id="dlgDelSettingMsg" class="hideall"></div>
	<!-- 	<input type="hidden" id="roleId" name="roleId"/> -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-min.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/setting.js?t=1.1"/>></script>
	<!-- <script type="text/javascript" src=<c:url value="/resources/js/PIE_IE678.js"/>></script> -->

	<p class="foot">
		<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version
			1.0</a> <span class="sep">|</span>
		<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
		<span class="sep">|</span> -->
		<span>&copy; 2013 Zingcrm. All rights reserved.</span>
	</p>

</body>
<script type="text/javascript">
	$('#FEA_SETTING').addClass("active");
	
</script>
</html>