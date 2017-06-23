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
<link type="text/css" href=<c:url value="/resources/css/style.css?t=1.3"/>
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
<link type="text/css"
	href=<c:url value="/resources/css/fileuploader.css?t=1.1"/> rel="stylesheet"
	media="all" />
<link rel="shortcut icon" type="images/x-icon"
	href="/resources/images/favicon.ico" />
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
					<div id="allLead" class="hideall">
						<form class="contentsearch" name='search' action="" method='POST'>
							
							<div class="infodashboard">
								<div class="pageimg">
									<img class="boximg" src="/resources/images/business-partners.png">
								</div>
								<div class="pageimg">
									<ul>
										<li class="infotitlehead">Business Partner</li>
										<li>Details of your Prospects and Customers</li>
									</ul>
								</div>
							</div>

							<div class="searchright">

								<div class="subhead">Search by Business Partner</div>
								<p class="fieldtitle">
									<!-- <label id="name">Business Partners</label> --> <select
										class="fieldinput hideall" name='filterLeadId'
										id="filterLeadId"></select> <input type="text"
										class="fieldinput" name='searchcompany' id="searchcompany" />&nbsp;
									<input type="button" name="Searchbutton" id="Searchbutton"
										value="Search" class="button searchBtn" /> <span
										id="search_Error" class="filtererror hideall">Enter the
										company to search.</span>
								</p>
							</div>
							<c:if test="${not empty error}">
								<div class="errorblock">
									${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
							</c:if>


							<div class="clrdiv"></div>
							<!-- <div id="tabs" class="maptab">
								<ul>
									<li><a href="#tabs-1">Business Partners</a></li>
								</ul>
								<div id="tabs-1"> -->
									<div id="showLeadDataButtons" class="topbar"></div>
									<div id="showGridLeads" class="shwworkordergrid">
										<div id="leadsgrid" class="workordergrid">
											<table id="leadList"></table>
											<div id="leadPager"></div>
										</div>
									</div>
								</div>
							<!-- </div> -->
						</form>
					</div>

					<div id="createLeads" class="hideall">
						<div class="backlink" align="right">
								<img id="backLead" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>
						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/business-partners.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Create Business Partner</li>
									<li>Details of your Prospects and Customers</li>
								</ul>
							</div>
							
							
						</div>
						<div class="stepsNew" id="stepbg">
							<!-- <ul>
								<li class="step1 steps">
								</li>
								
								<li class="step2 steps">
								</li>
								
								<li class="step3 steps">
								</li>
							</ul> -->
						
						   </div>
						   <div>
								<fieldset>
						<form:form name="newLeadfrm1" id="newLeadfrm1" style="display: inline;"
							method="post">
							<div class="mainDiv1">
							<div class="headleft">
									<p class="contlbl">
										Business Partner<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="lead" type="text" id="newLeadName"
											name="newLeadName" maxlength="50" class="fieldinputac" value="${lead}"></form:input>
									<!-- <p id="newLeadNameError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="newLeadNameValidError" class="hideall errorMsg">Business Partner
										 already exists
									<p></p> -->
								</div>

								<div class="headright">
									<p class="contlbl">
										Assigned Rep<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="rep" class="profileSignUp fieldinputapp"
											name="newLeadAssignedRep" id="newLeadAssignedRep"></form:select>
									</p>
									<!-- <p id="newLeadAssignedRepError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								</div>
							</form:form>
							</fieldset>
							</div>
						<div id="newStep1Lead">
						
								<fieldset>
						<form:form name="newLeadfrm" id="newLeadfrm" style="display: inline;"
							method="post">
							
							<div class="clrdiv"></div>
							<div class="mainDiv">
								<div class="headleft hideall">
									<p class="contlbl">
										Organization<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="org" id="leadOrg" name="leadOrg"
											maxlength="50" class="fieldinputac"></form:select>
									</p>
								</div>
								<div class="headleft">
									<p class="contlbl">
										Date<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input readonly="true" path="date" type="text"
											id="newLeadDate" name="newLeadDate" maxlength="50"
											class="fieldinputac" value="${date}"></form:input>
									<!-- <p id="newLeadDateError" class="hideall errorMsg">This
										information is required
									<p></p> -->
								</div>
								<div class="headright">
									<p class="contlbl">
										Source<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="source" class="profileSignUp fieldinputapp"
											name="newLeadSource" id="newLeadSource"></form:select>
									</p>
									<!-- <p id="newLeadSourceError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								<div class="headleft">
									<p class="contlbl">
										Address 1<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="address" class="fieldinputac"
											name="newAddress" id="newAddress" maxlength="150"></form:input>
									</p>
								<!-- 	<p id="newAddressError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								<div class="headright">
									<!-- <p class="divd"></p> -->
									<p class="contlbl">Address 2</p>
									<p class="continput">
										<form:input path="address2"
											class="fieldinputac" name="newAddress2"
											id="newAddress2" maxlength="150"></form:input>
									</p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Country<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="country" class="fieldinputac"
											name="newLeadCountry" id="newLeadCountry" >
											<option value=0>Select</option></form:select>
									</p>
									<!-- <p id="newLeadCountryError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
							<div class="headright">
									<p class="contlbl">
										Province<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="State" class="fieldinputac"
											name="newLeadState" id="newLeadState">
											<option value=0>Select</option>
											</form:select>
									</p>
									<!-- <p id="newLeadStateError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								<div class="headleft">
									<p class="contlbl">
										City<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="city" class="fieldinputac"
											name="newLeadCity" id="newLeadCity">
											<option value=0>Select</option></form:select>
									</p>
									<p id="newLeadCityError" class="hideall errorMsg">This
										information is required
									<p>
								</div>

								
								<div class="headright">
									<p class="contlbl">
										ZipCode<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="zipcode" class="fieldinputac"
											name="newZipCode" id="newZipCode" maxlength="7"></form:input>
									</p>
									<p id="newZipCodeError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Fax<font color="red"></font>
									</p>
									<p class="continput">
										<form:input path="Fax" class="fieldinputac"
											name="newFax" id="newFax" maxlength="15"></form:input>
									</p>
									<p id="newFaxError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								
								<div class="headright">
									<p class="contlbl">
										Website<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="Website" class="fieldinputac"
											name="newWebsite" id="newWebsite" maxlength="50" value="${Website}"></form:input>
									</p>
									<p id="newWebsiteError" class="hideall errorMsg">This
										information is required
									<p id="newWebsiteValidError" class="hideall errorMsg">
										Website is not valid format
									<p></p>
								</div>
								<div class="headleft">
									<p class="contlbl txtspace">
										Customer
									</p>
									<p class="contchk">
									
									<input class="contCheck" type="checkbox"  value="1" id="customFlagYes"/>Yes
									</p>
								</div>
								<div class="headright">
									<p class="contlbl txtspace">
										Active
									</p>
									<p class="contchk">
									
									<input class="contPrivateCheck" type="checkbox"  value="1" id="privateFlagYes" checked/>Yes
									</p>
								</div>
							</div>
							<form:hidden path="leadreqid" id="leadreqid" name="leadreqid" value="${leadrequestId}"/>
							</form:form>
							</fieldset>
					
							<fieldset>
							<div class="main-container">
<h3 >Contacts</h3>
<div class="container-para">

<span class="table-head">First name</span>
<span class="table-head">Last name</span>
<span class="table-head">Phone</span>
<span class="table-txt">Ext</span>
<span class="table-head">Mobile</span>
<span class="table-email" >Email</span>
<span class="table-head" style="border-right:none;">Department</span>
</div>
<div class="clr"></div>


<div class="form" id="newStep1PrimaryDetails">
<form:form name="newLeadPrimaryfrm" id="newLeadPrimaryfrm" style="display: inline;"
							method="post">
<span class="table-element">&nbsp;<form:input path="primaryName" type="text"
											id="newLeadPrimaryName" name="newLeadPrimaryName"
											maxlength="50" class="fieldcontinput" value="${primaryName}"></form:input></span>
<span class="table-element">&nbsp;<form:input path="primaryLastName" type="text"
											id="newPrimaryLastName" name="newPrimaryLastName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="primaryPhone" type="text"
									id="newLeadPrimaryPhone" name="newLeadPrimaryPhone"
									maxlength="50" class="fieldcontinput" value="${primaryPhone}"></form:input></span>
<span class="table-txt-element">&nbsp;<form:input path="primaryphoneExtension" type="text"
									id="newLeadPrimaryPhoneEx" name="newLeadPrimaryPhoneEx"
									maxlength="5" class="fieldcontinput" value="${primaryphoneExtension}"></form:input></span>
<span class="table-element">&nbsp;<form:input path="primaryMobile" type="text"
											id="newLeadPrimaryMobile" name="newLeadPrimaryMobile"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-email-element">&nbsp;<form:input path="primaryEmail" type="text"
											id="newLeadPrimaryEmail" name="newLeadPrimaryEmail"
											maxlength="50" class="fieldcontinput" value="${primaryEmail}"></form:input></span>
<span class="table-element" style="border-right:none;">&nbsp;<form:input path="primaryDept" type="text"
											id="newLeadPrimaryDept" name="newLeadPrimaryDept"
											maxlength="50" class="fieldcontinput"></form:input></span>
</form:form>
</div>
<div class="form" id="newStep1SecondaryDetails">
<form:form name="newLeadSecondaryfrm" id="newLeadSecondaryfrm" style="display: inline;"
							method="post">
<span class="table-element">&nbsp;<form:input path="secondaryName" type="text"
											id="newLeadSecondaryName" name="newLeadSecondaryName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="secondaryLastName" type="text"
											id="newSecondaryLastName" name="newSecondaryLastName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="secondaryPhone" type="text"
									id="newLeadSecondaryPhone" name="newLeadSecondaryPhone"
									maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-txt-element">&nbsp;<form:input path="secondaryphoneExtension" type="text"
									id="newLeadSecondaryPhoneEx" name="newLeadSecondaryPhoneEx"
									maxlength="5" class="fieldcontinput" ></form:input></span>
<span class="table-element">&nbsp;<form:input path="secondaryMobile" type="text"
											id="newLeadSecondaryMobile" name="newLeadSecondaryMobile"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-email-element">&nbsp;<form:input path="secondaryEmail" type="text"
											id="newLeadSecondaryEmail" name="newLeadSecondaryEmail"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element" style="border-right:none;">&nbsp;<form:input path="secondaryDept" type="text"
											id="newLeadSecondaryDept" name="newLeadSecondaryDept"
											maxlength="50" class="fieldcontinput"></form:input></span>
											<form:hidden path="countryName" id="countryName"/>
							<form:hidden path="stateName" id="stateName"/>
							<form:hidden path="cityName" id="cityName"/>
							
							<form:hidden path="leadreqemail" id="LeadReqEmail" value="${LeadReqEmail}"/>
</form:form>											
</div>
<div class="form" id="newStep1TertiaryContactDetails">
					<form:form name="newLeadThirdfrm" id="newLeadThirdfrm" style="display: inline;"
							method="post">
<span class="table-element">&nbsp;<form:input path="thirdConName" type="text"
											id="newLeadthirdName" name="newLeadthirdConName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="ThirdConLastName" type="text"
											id="newThirdLastName" name="newThirdLastConName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="thirdConPhone" type="text"
									id="newLeadThirdPhone" name="newLeadThirdConPhone"
									maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-txt-element">&nbsp;<form:input path="thirdConphoneExt" type="text"
									id="newLeadThirdConPhoneEx" name="newLeadThirdConPhoneEx"
									maxlength="5" class="fieldcontinput" ></form:input></span>
<span class="table-element">&nbsp;<form:input path="thirdConMobile" type="text"
											id="newLeadThirdConMobile" name="newLeadThirdConMobile"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-email-element">&nbsp;<form:input path="thirdConEmail" type="text"
											id="newLeadThirdConEmail" name="newLeadThirdConEmail"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element" style="border-right:none;">&nbsp;<form:input path="thirdConDept" type="text"
											id="newLeadThirdConDept" name="newLeadThirdConDept"
											maxlength="50" class="fieldcontinput"></form:input></span>
</form:form>											
</div>
</div></fieldset>
							
							<fieldset>
							<div id="newAdditionalInfo" >
							<form:form name="newLeadAdditionalInfofrm" id="newLeadAdditionalInfofrm" style="display: inline;"
							method="post">
							
							<div class="leadmainDiv">
							<div class="headleft">
										<p class="contlbl">
											Current Supplier
										</p>
										<p class="continput">
											<form:input path="CurrentSupplier" type="text"
												id="newLeadCurSupplier" name="newLeadCurSupplier"
												maxlength="50" class="fieldinputac"></form:input>
							</div>
							
							<div class="headleft">
							<p class="contlbl">
							Notes
							</p>
							<p class="continput">
							  <form:textarea path="leadnotes" type="text"  id="newleadnotes" name="newleadnotes" maxlength="5000" class="fieldinputac"></form:textarea>
							</p>
							</div>
							</div>
							
							</form:form>
							</div>
							</fieldset>
									<div class="creCanBtn"> 
									 <p>
										<input type="button" value="Create Project" class="signupbutton"
											id="newcreateProject" />
									</p> 
									 <p>
										<input type="button" value="Save" class="signupbutton"
											id="step3NextLead" />
									</p>
									<!-- <p>
										<input type="button" value="Back" class="signupbutton"
											id="step2BackLead" />
									</p> -->
									<p>
										<input type="button" value="Go Back" class="cancelbutton cancelLead"
											/>
									</p> 
									 </div> 
								</div>
					</div>
					
					
					<div id="viewLeads" class="hideall">
				<div class="backlink" align="right">
								<img id="viewbackLead" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>
						<div class="infodashboard">
						
							<div class="pageimg">
								<img class="boximg" src="/resources/images/business-partners.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">View Business Partner</li>
									<li>Details of your Prospects and Customers</li>
								</ul>
							</div>
							
							
						</div>
						<div class="stepsNew" id="stepbg">
							<ul>
								<li class="step1" id="viewStep1">
								</li>
								
								<li class="step2" id="viewStep2">
								</li>
								
								<li class="step3" id="viewStep3">
								</li>
							</ul>
						
						   </div>
						   <div>
								<fieldset>
						<form:form name="viewLeadfrm1" id="viewLeadfrm1" style="display: inline;"
							method="post">
							<div class="mainDiv1">
							<div class="headleft">
									<p class="contlbl">
										Business Partner
									</p>
									<p class="continput">
										<form:input path="lead" type="text" id="viewLeadName"
											name="viewLeadName" maxlength="50" class="fieldinputac"></form:input>
								</div>

								<div class="headright">
									<p class="contlbl">
										Assigned Rep
									</p>
									<p class="continput">
										<form:input path="rep" class="profileSignUp fieldinputapp"
											name="viewLeadAssignedRep" id="viewLeadAssignedRep"></form:input>
									</p>
								</div>
								</div>
							</form:form>
							</fieldset>
							</div>
							
							
						<div id="viewStep1Lead">
						
								<fieldset>
						<form:form name="viewLeadfrm" id="viewLeadfrm" style="display: inline;"
							method="post">
							
							<div class="clrdiv"></div>
							<div class="mainDiv">
								<div class="headleft">
									<p class="contlbl">
										Date
									</p>
									<p class="continput">
										<form:input readonly="true" path="date" type="text"
											id="viewLeadDate" name="editLeadDate" maxlength="50"
											class="fieldinputac"></form:input>
											</p>
								</div>
								<div class="headright">
									<p class="contlbl">
										Source
									</p>
									<p class="continput">
										<form:input path="source" class="profileSignUp fieldinputapp"
											name="viewLeadSource" id="viewLeadSource"></form:input>
									</p>
									<p id="viewLeadSourceError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								<div class="headleft">
									<p class="contlbl">
										Address 1
									</p>
									<p class="continput">
										<form:input path="address" class="fieldinputac"
											name="viewAddress" id="viewAddress" maxlength="150"></form:input>
									</p>
									<p id="viewAddressError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								<div class="headright">
									<!-- <p class="divd"></p> -->
									<p class="contlbl">Address 2</p>
									<p class="continput">
										<form:input path="address2"
											class="fieldinputac" name="viewAddress2"
											id="viewAddress2" maxlength="150"></form:input>
									</p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Country
									</p>
									<p class="continput">
										<input  class="fieldinputac"
											name="viewLeadCountry" id="viewLeadCountry" />
									</p>
								</div>
							<div class="headright">
									<p class="contlbl">
										Province
									</p>
									<p class="continput">
										<input  class="fieldinputac"
											name="viewLeadState" id="viewLeadState"/>
									</p>
									<p id="viewLeadStateError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								<div class="headleft">
									<p class="contlbl">
										City
									</p>
									<p class="continput">
										<input  class="fieldinputac"
											name="viewLeadCity" id="viewLeadCity"/>
									</p>
									<p id="viewLeadCityError" class="hideall errorMsg">This
										information is required
									<p>
								</div>

								
								<div class="headright">
									<p class="contlbl">
										ZipCode
									</p>
									<p class="continput">
										<form:input path="zipcode" class="fieldinputac"
											name="viewZipCode" id="viewZipCode" ></form:input>
									</p>
									<p id="viewZipCodeError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Fax
									</p>
									<p class="continput">
										<form:input path="Fax" class="fieldinputac"
											name="viewFax" id="viewFax" maxlength="15"></form:input>
									</p>
									<p id="viewFaxError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								
								<div class="headright">
									<p class="contlbl">
										Website
									</p>
									<p class="continput">
										<form:input path="Website" class="fieldinputac"
											name="viewWebsite" id="viewWebsite" maxlength="50"></form:input>
									</p>
									<p id="viewWebsiteError" class="hideall errorMsg">This
										information is required
									<p>
								</div>
								<div class="headleft">
									<p class="contlbl txtspace">
										Customer
									</p>
									<p class="contchk">
									
									<input class="contCheck" type="checkbox"  value="1" id="viewLeadFlag"/>Yes
									</p>
								</div>
								<div class="headright">
									<p class="contlbl txtspace">
										Active
									</p>
									<p class="contchk">
									
									<input class="contPrivateCheck" type="checkbox"  value="1" id="viewActiveLeadFlag"/>Yes
									</p>
								</div>
							</div>
							 
							</form:form>
							</fieldset>
							<div class="creCanBtn">
									<p>
										<input type="button" value="Next" class="signupbutton" id="viewStep1NextLead" />
									</p>
									<p>
										<input type="button" value="Close" class="cancelbutton viewCancelLead"/>
									</p>
								</div>
					
					</div>
					<div id="viewStep1PrimaryDetails" class="hideall">
					<!-- <p> Primary Details</p> -->
					<form:form name="viewLeadPrimaryfrm" id="viewLeadPrimaryfrm" style="display: inline;"
							method="post">
						<div class="headleft">
									<p class="contlbl">
										First name
									</p>
									<p class="continput">
										<form:input path="primaryName" type="text"
											id="viewLeadPrimaryName" name="viewLeadPrimaryName"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadPrimaryNameError" class="hideall errorMsg">This
										information is required
									<p></p>
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Last Name
									</p>
									<p class="continput">
										<form:input path="primaryLastName" type="text"
											id="viewPrimaryLastName" name="viewPrimaryLastName"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewPrimaryLastNameError" class="hideall errorMsg">This
										information is required
									<p></p>
						</div>
								
						<div class="headleft">
							<div class="phonediv">
							<p class="contlbl">
								Phone
							</p>
							<p id="editphone">
								<form:input path="primaryPhone" type="text"
									id="viewLeadPrimaryPhone" name="viewLeadPrimaryPhone"
									maxlength="50" ></form:input>
			
							<p id="viewLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p>
							</div>
							<div class="exDiv"><p class="exp">Ext:</p>
							<p id="editex">
								<form:input path="primaryphoneExtension" type="text"
									id="viewLeadPrimaryPhoneExtension" name="viewLeadPrimaryPhoneExtension"
									maxlength="5" class="exinputac"></form:input>
							<p id="editLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p>
							</div>
							
							
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Mobile
									</p>
									<p class="continput">
										<form:input path="primaryMobile" type="text"
											id="viewLeadPrimaryMobile" name="viewLeadPrimaryMobile"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadPrimaryMobileError" class="hideall errorMsg">This
										information is required
									<p></p>
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Email
									</p>
									<p class="continput">
										<form:input path="primaryEmail" type="text"
											id="viewLeadPrimaryEmail" name="viewLeadPrimaryEmail"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadPrimaryEmailError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="viewLeadPrimaryEmailValidError" class="hideall errorMsg">
										Email is not valid format
									<p></p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Dept
									</p>
									<p class="continput">
										<form:input path="primaryDept" type="text"
											id="viewLeadPrimaryDept" name="viewLeadPrimaryDept"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadPrimaryDeptError" class="hideall errorMsg">This
										information is required
									<p></p>
							</div>
							</form:form>
							
							<div class="creCanBtn">
									
									<p>
										<input type="button" value="Next" class="signupbutton"
											id="viewStep2NextLead" />
									</p>
									<p>
										<input type="button" value="Back" class="signupbutton"
											id="viewStep1BackLead" />
									</p>
									<p>
										<input type="button" value="Close" class="cancelbutton viewCancelLead"
											/>
									</p>
								</div>
					</div>
					
						<div id="viewStep1SecondaryDetails" class="hideall">
							<!-- <p>Secondary Details</p> -->
					<form:form name="viewLeadSecondaryfrm" id="viewLeadSecondaryfrm" style="display: inline;"
							method="post">
						<div class="headleft">
									<p class="contlbl">
										First name
									</p>
									<p class="continput">
										<form:input path="secondaryName" type="text"
											id="viewLeadSecondaryName" name="viewLeadSecondaryName"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadSecondaryNameError" class="hideall errorMsg">This
										information is required
									<p></p>
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Last Name
									</p>
									<p class="continput">
										<form:input path="secondaryLastName" type="text"
											id="viewSecondaryLastName" name="viewSecondaryLastName"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewSecondaryLastNameError" class="hideall errorMsg">This
										information is required
									<p></p>
						</div>
								
						<div class="headleft">
							<p class="contlbl">
								Phone
							</p>
							<p class="continput">
								<form:input path="secondaryPhone" type="text"
									id="viewLeadSecondaryPhone" name="viewLeadSecondaryPhone"
									maxlength="50" class="fieldinputac"></form:input>
							<p id="viewLeadSecondaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p>
						</div>
				
						<div class="headleft">
									<p class="contlbl">
										Mobile
									</p>
									<p class="continput">
										<form:input path="secondaryMobile" type="text"
											id="viewLeadSecondaryMobile" name="viewLeadSecondaryMobile"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadSecondaryMobileError" class="hideall errorMsg">This
										information is required
									<p></p>
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Email
									</p>
									<p class="continput">
										<form:input path="secondaryEmail" type="text"
											id="viewLeadSecondaryEmail" name="viewLeadSecondaryEmail"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadSecondaryEmailError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="viewLeadSecondaryEmailValidError" class="hideall errorMsg">
										Email is not valid format
									<p></p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Dept
									</p>
									<p class="continput">
										<form:input path="secondaryDept" type="text"
											id="viewLeadSecondaryDept" name="viewLeadSecondaryDept"
											maxlength="50" class="fieldinputac"></form:input>
									<p id="viewLeadSecondaryDeptError" class="hideall errorMsg">This
										information is required
									<p></p>
							</div>
							</form:form>
							
							<div class="creCanBtn">
										
									<p>
										<input type="button" value="Back" class="signupbutton"
											id="viewStep2BackLead" />
									</p>
									<p>
										<input type="button" value="Close" class="cancelbutton viewCancelLead"
											/>
									</p>
								</div>
					</div>
					</div>
					
					
					<div id="editLeads" class="hideall">
						<div class="backlink" align="right">
								<img id="editbackLead" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>
						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/business-partners.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Edit Business Partner</li>
									<li>Details of your Prospects and Customers</li>
								</ul>
							</div>
							
						</div>
				<div class="stepsNew" id="">
							<!-- 	 <ul>
								<li class="step1" id="editStep1">
								</li>
								
								<li class="step2" id="editStep2">
								</li>
								
								<li class="step3" id="editStep3">
								</li>
							</ul> -->
						
						   </div> 
						   
						<fieldset>
						<div>
						<form:form name="editLeadfrm1" id="editLeadfrm1" style="display: inline;"
							method="post">
							<div class="mainDiv1">
							<div class="headleft">
									<p class="contlbl">
										Business Partner<font color="red">*</font>
									</p>
									<p class="continput">
										<input   type="text" id="editLeadName"
											name="editLeadName" maxlength="50" class="fieldinputac"></p></input>
									<!-- <p id="editLeadNameError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="editLeadNameValidError" class="hideall errorMsg">Business Partner
										 already exists
									<p></p> -->
								</div>

								<div class="headright">
									<p class="contlbl">
										Assigned Rep<font color="red">*</font>
									</p>
									<p class="continput">
										<select  class="profileSignUp fieldinputapp"
											name="editLeadAssignedRep" id="editLeadAssignedRep"></select>
									</p>
									<!-- <p id="editLeadAssignedRepError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								</div>
							</form:form>
							</div>
							</fieldset>
							
						
						
								<fieldset>
								<div id="editStep1Lead">
						<form:form name="editLeadfrm" id="editLeadfrm" style="display: inline;"
							method="post">
							
							<div class="clrdiv"></div>
							<div class="mainDiv">
								<div class="headleft hideall">
									<p class="contlbl">
										Organization<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="org" id="editleadOrg" name="editleadOrg"
											maxlength="50" class="fieldinputac"></form:select>
									</p>
								</div>
								<div class="headleft">
									<p class="contlbl">
										Date<font color="red">*</font>
									</p>
									<p class="continput">
										<input  path="date" type="text"
											id="editLeadDate" name="editLeadDate" maxlength="50"
											class="fieldinputac"/>
									<!-- <p id="editLeadDateError" class="hideall errorMsg">This
										information is required
									<p></p> -->
								</div>
								<div class="headright">
									<p class="contlbl">
										Source<font color="red">*</font>
									</p>
									<p class="continput">
										<select  class="profileSignUp fieldinputapp"
											name="editLeadSource" id="editLeadSource"></select>
									</p>
								<!-- 	<p id="editLeadSourceError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								<div class="headleft">
									<p class="contlbl">
										Address 1<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="address" class="fieldinputac"
											name="editAddress" id="editAddress" maxlength="150"></form:input>
									</p>
									<!-- <p id="editAddressError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								<div class="headright">
									<!-- <p class="divd"></p> -->
									<p class="contlbl">Address 2</p>
									<p class="continput">
										<form:input path="address2"
											class="fieldinputac" name="editAddress2"
											id="editAddress2" maxlength="150"></form:input>
									</p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Country<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="country" class="fieldinputac"
											name="editLeadCountry" id="editLeadCountry" >
											<option value=0>Select</option></form:select>
									</p>
									<!-- <p id="editLeadCountryError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
							<div class="headright">
									<p class="contlbl">
										Province<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="State" class="fieldinputac"
											name="editLeadState" id="editLeadState">
											<option value=0>Select</option>
											</form:select>
									</p>
									<!-- <p id="editLeadStateError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								<div class="headleft">
									<p class="contlbl">
										City<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="city" class="fieldinputac"
											name="editLeadCity" id="editLeadCity">
											<option value=0>Select</option></form:select>
									</p>
									<!-- <p id="editLeadCityError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>

								
								<div class="headright">
									<p class="contlbl">
										ZipCode<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="zipcode" class="fieldinputac"
											name="editLeadZipcode" id="editLeadZipcode" maxlength="7"></form:input>
									</p>
									<!-- <p id="editLeadZipcodeError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Fax<font color="red"></font>
									</p>
									<p class="continput">
										<form:input path="Fax" class="fieldinputac"
											name="editFax" id="editFax" maxlength="15"></form:input>
									</p>
								<!-- 	<p id="editFaxError" class="hideall errorMsg">This
										information is required
									<p> -->
								</div>
								
								<div class="headright">
									<p class="contlbl">
										Website<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="Website" class="fieldinputac"
											name="editWebsite" id="editWebsite" maxlength="50"></form:input>
									</p>
									
									<!-- <p id="editWebsiteError" class="hideall errorMsg">This
										information is required
									<p id="editWebsiteValidError" class="hideall errorMsg">
										Website is not valid format
									<p></p> -->
								</div>
								<div class="headleft">
									<p class="contlbl txtspace">
										Customer
									</p>
									<p class="contchk">
									
									<input class="contCheck" type="checkbox"  value="1" id="editcustomFlagYes"/>Yes
									</p>
								</div>
								<div class="headright">
									<p class="contlbl txtspace">
										Active
									</p>
									<p class="contchk">
									
									<input class="contPrivateCheck" type="checkbox"  value="1" id="editPrivateFlagYes"/>Yes
									</p>
								</div>
							
							</form:form>
							</div>
							</fieldset>
						<!-- 	<div class="creCanBtn">
									<p>
										<input type="button" value="Next" class="signupbutton" id="editStep1NextLead" />
									</p>
									<p>
										<input type="button" value="Cancel" class="cancelbutton editCancelLead" id="cancelLead" />
									</p>
								</div> -->
								<fieldset>
							<div class="main-container">
<h3>Contacts</h3>
<div class="container-para">
<span class="table-head">First name</span>
<span class="table-head">Last name</span>
<span class="table-head">Phone</span>
<span class="table-txt">Ext</span>
<span class="table-head">Mobile</span>
<span class="table-email">Email</span>
<span class="table-head" style="border-right:none;">Department</span>
</div>
<div class="clr"></div>

<div class="form" id="editStep1PrimaryDetails">
<form:form name="editLeadPrimaryfrm" id="editLeadPrimaryfrm" style="display: inline;"
							method="post">
<span class="table-element">&nbsp;<form:input path="primaryName" type="text"
											id="editLeadPrimaryName" name="editLeadPrimaryName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="primaryLastName" type="text"
											id="editPrimaryLastName" name="editPrimaryLastName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="primaryPhone" type="text"
									id="editLeadPrimaryPhone" name="editLeadPrimaryPhone"
									maxlength="50" class="fieldcontinput" ></form:input></span>
<span class="table-txt-element">&nbsp;<form:input path="primaryphoneExtension" type="text"
									id="editLeadPrimaryPhoneExtension" name="editLeadPrimaryPhoneExtension"
									maxlength="5" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="primaryMobile" type="text"
											id="editLeadPrimaryMobile" name="editLeadPrimaryMobile"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-email-element">&nbsp;<form:input path="primaryEmail" type="text"
											id="editLeadPrimaryEmail" name="editLeadPrimaryEmail"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element" style="border-right:none;">&nbsp;<form:input path="primaryDept" type="text"
											id="editLeadPrimaryDept" name="editLeadPrimaryDept"
											maxlength="50" class="fieldcontinput"></form:input></span>
													<form:hidden path="CreatedBy" id="editCreatedBy" />
								<form:hidden path="createdDate" id="editCreatedDate" />
								<form:hidden path="id" id="editId" />
								<form:hidden path="contId" id="contId" />
								<form:hidden path="status" id="editStatus" />
								<form:hidden path="date" id="editDate" />
								<form:hidden path="source" id="editSource" />
								<form:hidden path="lead" id="editLead" />
								<form:hidden path="rep" id="editRep" />
							<form:hidden path="countryName" id="editcountryName"/>
							<form:hidden path="stateName" id="editstateName"/>
							<form:hidden path="cityName" id="editcityName"/>
							</form:form>
</div>
<div class="form" id="editStep1SecondaryDetails">
<form:form name="editLeadSecondaryfrm" id="editLeadSecondaryfrm" style="display: inline;"
							method="post">
<span class="table-element">&nbsp;<form:input path="secondaryName" type="text"
											id="editLeadSecondaryName" name="editLeadSecondaryName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="secondaryLastName" type="text"
											id="editSecondaryLastName" name="editSecondaryLastName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="secondaryPhone" type="text"
									id="editLeadSecondaryPhone" name="editLeadSecondaryPhone"
									maxlength="50" class="fieldcontinput" ></form:input></span>
<span class="table-txt-element">&nbsp;<form:input path="secondaryphoneExtension" type="text"
									id="editLeadSecPhoneExt" name="editLeadSecPhoneExt"
									maxlength="5" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="secondaryMobile" type="text"
											id="editLeadSecondaryMobile" name="editLeadSecondaryMobile"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-email-element">&nbsp;<form:input path="secondaryEmail" type="text"
											id="editLeadSecondaryEmail" name="editLeadSecondaryEmail"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element" style="border-right:none;">&nbsp;<form:input path="secondaryDept" type="text"
											id="editLeadSecondaryDept" name="editLeadSecondaryDept"
											maxlength="50" class="fieldcontinput"></form:input></span>
											<form:hidden path="countryName" id="countryName"/>
							<form:hidden path="stateName" id="stateName"/>
							<form:hidden path="cityName" id="cityName"/>
							
							<form:hidden path="leadreqemail" id="LeadReqEmail" value="${LeadReqEmail}"/>
</form:form>											
</div>
<div id="editStep1TertiaryContactDetails" class="form">
					<form:form name="editLeadThirdfrm" id="editLeadThirdfrm" style="display: inline;"
							method="post">
<span class="table-element">&nbsp;<form:input path="thirdConName" type="text"
											id="editLeadthirdConName" name="editLeadthirdConName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="ThirdConLastName" type="text"
											id="editThirdConLastName" name="editThirdConLastName"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="thirdConPhone" type="text"
									id="editLeadThirdConPhone" name="editLeadThirdConPhone"
									maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-txt-element">&nbsp;<form:input path="thirdConphoneExt" type="text"
									id="editLeadThirdConPhoneEx" name="editLeadThirdConPhoneEx"
									maxlength="5" class="fieldcontinput"></form:input></span>
<span class="table-element">&nbsp;<form:input path="thirdConMobile" type="text"
											id="editLeadThirdConMobile" name="editLeadThirdConMobile"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-email-element">&nbsp;<form:input path="thirdConEmail" type="text"
											id="editLeadThirdConEmail" name="editLeadThirdConEmail"
											maxlength="50" class="fieldcontinput"></form:input></span>
<span class="table-element" style="border-right:none;">&nbsp;<form:input path="thirdConDept" type="text"
											id="editLeadThirdConDept" name="editLeadThirdConDept"
											maxlength="50" class="fieldcontinput"></form:input></span>
</form:form>											
</div>
</div>
</fieldset>
					
				<%-- 	<fieldset>
					<div id="editStep1PrimaryDetails" class="">
					<!-- <p> Primary Details</p> -->
					<form:form name="editLeadPrimaryfrm" id="editLeadPrimaryfrm" style="display: inline;"
							method="post">
						<div class="headleft">
									<p class="contlbl">
										First name
									</p>
									<p class="continput">
										<form:input path="primaryName" type="text"
											id="editLeadPrimaryName" name="editLeadPrimaryName"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editLeadPrimaryNameError" class="hideall errorMsg">This
										information is required
									<p></p> -->
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Last Name
									</p>
									<p class="continput">
										<form:input path="primaryLastName" type="text"
											id="editPrimaryLastName" name="editPrimaryLastName"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editPrimaryLastNameError" class="hideall errorMsg">This
										information is required
									<p></p> -->
						</div>
								
						<div class="headleft" style="width:415px !important;">
							<div class="phonediv">
							<p class="contlbl">
								Phone
							</p>
							<p  id="editphone">
								<form:input path="primaryPhone" type="text"
									id="editLeadPrimaryPhone" name="editLeadPrimaryPhone"
									maxlength="50" ></form:input>
							<!-- <p id="editLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							<div class="exDiv"><p class="exp">Ext:</p>
							<p  id="editex">
								<form:input path="primaryphoneExtension" type="text"
									id="editLeadPrimaryPhoneExtension" name="editLeadPrimaryPhoneExtension"
									maxlength="5" class="exinputac"></form:input>
							<!-- <p id="editLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							
							
						</div>
						
						<div class="headleftS" >
									<p class="contlbl">
										Mobile
									</p>
									<p class="continput">
										<form:input path="primaryMobile" type="text"
											id="editLeadPrimaryMobile" name="editLeadPrimaryMobile"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editLeadPrimaryMobileError" class="hideall errorMsg">This
										information is required
									<p></p> -->
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Email
									</p>
									<p class="continput">
										<form:input path="primaryEmail" type="text"
											id="editLeadPrimaryEmail" name="editLeadPrimaryEmail"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editLeadPrimaryEmailError" class="hideall errorMsg">This
										information is required
									<p>
									<p id="editLeadPrimaryEmailValidError" class="hideall errorMsg">
										Email is not valid format
									<p></p> -->
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Dept
									</p>
									<p class="continput">
										<form:input path="primaryDept" type="text"
											id="editLeadPrimaryDept" name="editLeadPrimaryDept"
											maxlength="50" class="fieldinputac"></form:input>
								<!-- 	<p id="editLeadPrimaryDeptError" class="hideall errorMsg">This
										information is required
									<p></p> -->
							
							</form:form>
							</div>
							</fieldset>
							<!-- <div class="creCanBtn">
									
									<p>
										<input type="button" value="Next" class="signupbutton"
											id="editStep2NextLead" />
									</p>
									<p>
										<input type="button" value="Back" class="signupbutton"
											id="editStep1BackLead" />
									</p>
									<p>
										<input type="button" value="Cancel" class="cancelbutton editCancelLead"
											/>
									</p>
								</div> -->
					<!--</div>  -->
					<fieldset>
						<div id="editStep1SecondaryDetails" class="">
							<!-- <p>Secondary Details</p> -->
					<form:form name="editLeadSecondaryfrm" id="editLeadSecondaryfrm" style="display: inline;"
							method="post">
						<div class="headleft">
									<p class="contlbl">
										First name
									</p>
									<p class="continput">
										<form:input path="secondaryName" type="text"
											id="editLeadSecondaryName" name="editLeadSecondaryName"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editLeadSecondaryNameError" class="hideall errorMsg">This
										information is required
									<p></p> -->
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Last Name
									</p>
									<p class="continput">
										<form:input path="secondaryLastName" type="text"
											id="editSecondaryLastName" name="editSecondaryLastName"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editSecondaryLastNameError" class="hideall errorMsg">This
										information is required
									<p></p> -->
						</div>
								
						<div class="headleft">
							<p class="contlbl">
								Phone
							</p>
							<p class="continput">
								<form:input path="secondaryPhone" type="text"
									id="editLeadSecondaryPhone" name="editLeadSecondaryPhone"
									maxlength="50" class="fieldinputac"></form:input>
							<!-- <p id="editLeadSecondaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p> -->
						</div>
						<div class="headleft" style="width:415px !important;">
							<div class="phonediv">
							<p class="contlbl">
								Phone
							</p>
							<p  id="editphone">
								<form:input path="secondaryPhone" type="text"
									id="editLeadSecondaryPhone" name="editLeadSecondaryPhone"
									maxlength="50" ></form:input>
							<!-- <p id="editLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							<div class="exDiv"><p class="exp">Ext:</p>
							<p  id="editex">
								<form:input path="secondaryphoneExtension" type="text"
									id="editLeadSecPhoneExt" name="editLeadSecPhoneExt"
									maxlength="5" class="exinputac"></form:input>
							<!-- <p id="editLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							
							
						</div>
						
						
						<div class="headleft">
									<p class="contlbl">
										Mobile
									</p>
									<p class="continput">
										<form:input path="secondaryMobile" type="text"
											id="editLeadSecondaryMobile" name="editLeadSecondaryMobile"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editLeadSecondaryMobileError" class="hideall errorMsg">This
										information is required
									<p></p> -->
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Email
									</p>
									<p class="continput">
										<form:input path="secondaryEmail" type="text"
											id="editLeadSecondaryEmail" name="editLeadSecondaryEmail"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editLeadSecondaryEmailError" class="hideall errorMsg">This
										information is required
									<p> -->
									<!-- <p id="editLeadSecondaryEmailValidError" class="hideall errorMsg">
										Email is not valid format
									<p></p> -->
								</div>
								
								<div class="headleft">
									<p class="contlbl">
										Dept
									</p>
									<p class="continput">
										<form:input path="secondaryDept" type="text"
											id="editLeadSecondaryDept" name="editLeadSecondaryDept"
											maxlength="50" class="fieldinputac"></form:input>
									<!-- <p id="editLeadSecondaryDeptError" class="hideall errorMsg">This
										information is required
									<p></p> -->
							</div>
								<form:hidden path="CreatedBy" id="editCreatedBy" />
								<form:hidden path="createdDate" id="editCreatedDate" />
								<form:hidden path="id" id="editId" />
								<form:hidden path="contId" id="contId" />
								<form:hidden path="status" id="editStatus" />
								<form:hidden path="date" id="editDate" />
								<form:hidden path="source" id="editSource" />
								<form:hidden path="lead" id="editLead" />
								<form:hidden path="rep" id="editRep" />
							<form:hidden path="countryName" id="editcountryName"/>
							<form:hidden path="stateName" id="editstateName"/>
							<form:hidden path="cityName" id="editcityName"/>
							</form:form>
							</div>
							</fieldset>
							<fieldset>
							<div id="editStep1TertiaryContactDetails" class="">
					<form:form name="editLeadThirdfrm" id="editLeadThirdfrm" style="display: inline;"
							method="post">
							<div class="leadmainDiv">
						<div class="headleft">
									<p class="contlbl">
										First name
									</p>
									<p class="continput">
										<form:input path="thirdConName" type="text"
											id="editLeadthirdConName" name="editLeadthirdConName"
											maxlength="50" class="fieldinputac"></form:input>
						</div>
						
						<div class="headright">
									<p class="contlbl">
										Last Name
									</p>
									<p class="continput">
										<form:input path="ThirdConLastName" type="text"
											id="editThirdConLastName" name="editThirdConLastName"
											maxlength="50" class="fieldinputac"></form:input>
						</div>
								
						<div class="headleft">
							<div class="phonediv">
							<p class="contlbl">
								Phone
							</p>
							<p id="editphone">
								<form:input path="thirdConPhone" type="text"
									id="editLeadThirdConPhone" name="editLeadThirdConPhone"
									maxlength="50" class="fieldinputacphone"></form:input>
							</div>
							<div class="exDiv"><p class="exp">Ext:</p>
							<p  id="editex">
								<form:input path="thirdConphoneExt" type="text"
									id="editLeadThirdConPhoneEx" name="editLeadThirdConPhoneEx"
									maxlength="5" ></form:input>
							</div>
						</div>
						
						<div class="headright">
									<p class="contlbl">
										Mobile
									</p>
									<p class="continput">
										<form:input path="thirdConMobile" type="text"
											id="editLeadThirdConMobile" name="editLeadThirdConMobile"
											maxlength="50" class="fieldinputac"></form:input>
						</div>
						
						<div class="headleft">
									<p class="contlbl">
										Email
									</p>
									<p class="continput">
										<form:input path="thirdConEmail" type="text"
											id="editLeadThirdConEmail" name="editLeadThirdConEmail"
											maxlength="50" class="fieldinputac"></form:input>
									
								</div>
								
								<div class="headright">
									<p class="contlbl">
										Dept
									</p>
									<p class="continput">
										<form:input path="thirdConDept" type="text"
											id="editLeadThirdConDept" name="editLeadThirdConDept"
											maxlength="50" class="fieldinputac"></form:input>
								
							</div>
							
							</div>
							</form:form>
								</div>
							</fieldset> --%>
							<fieldset>
							<div id="editAdditionalInfo" >
							<form:form name="editLeadAdditionalInfofrm" id="editLeadAdditionalInfofrm" style="display: inline;"
							method="post">
							<!-- <div class="creCanBtn"> -->
							<div class="leadmainDiv">
							<div class="headleft">
										<p class="contlbl">
											Current Supplier
										</p>
										<p class="continput">
											<form:input path="CurrentSupplier" type="text"
												id="editLeadCurSupplier" name="editLeadCurSupplier"
												maxlength="50" class="fieldinputac"></form:input>
							</div>
							
							<div class="headleft">
							<p class="contlbl">
							Notes
							</p>
							<p class="continput">
							  <form:textarea path="leadnotes" type="text"  id="editleadnotes" name="editleadnotes" maxlength="5000" class="fieldinputac"></form:textarea>
							</p>
							</div>
							</div>
							<!-- </div> -->
							</form:form>
							</div>
							</fieldset>
							<div class="creCanBtn">
										
									 <p>
										<input type="button" value="Create Project" class="signupbutton"
											id="createProject" />
									</p> 
									
									<p>
										<input type="button" value="Save" class="signupbutton"
											id="editStep3NextLead" />
									</p>
									
									<p>
										<input type="button" value="Go Back" class="cancelbutton editCancelLead"
											/>
									</p>
								</div>
							
					</div>
					
					
					
					</div>
					<!-- import start -->
					
					<form id="frmImportLead" class="hideall">
						<div class="backlink" align="right">
							<img id="importBackLead" src="/resources/images/backbtn.png" />&nbsp;</div>
							<div id="uploadDlg" class="uploadDlg">
								<div align="center" class="analysisbutton uplaodExists">
									Select a file to upload Business Partner data.
									<div id="fileuploader" style="width: 100%;"></div>
									
									<div class="greenbtn1">
										<input type="Button" value="GO" id="cmdImportLeads"
											class="filterGenerateButton5 analysisbutton uplaodExists">
									</div>
									<div class="uptxtHt"><div id="uploadingText"></div></div>
									<div class="Removebutton1 hideall" id="cancelLink">
									<p>Remove</p>
									<p><img src="/resources/images/trash.jpg"></p>
								</div>
								
								
								</div>
							</div>
							<input type="hidden" name="fileName" id="fileName" /> 
							<input
								type="hidden" id="importOrgId" name="orgId" />
					</form>
					
					
					<!-- import End -->
			</div>
			
			
			
				
		
						
				
				
							<%-- 	
							</div> --%>
					
					<form id="frmExportLead" name="frmExportLead"
						action="/business/exportBP">
						<input type="hidden" id="orgId" name="orgId" /> <input
							type="hidden" id="companyName" name="companyName" />
					</form>
					<form id="frmOppLead" action="/project" hidden="true"
						method="post">
						<input type="hidden" id="leadId" name="leadId" />
						<input type="hidden" id="createOpp" name="createOpp" /> <input
							type="submit" id="cmdOPPleadId" />
					</form>

					<div id="dlgLeadMsg" class="hideall"></div>
				 <div id="dlgleadsuccessMsg" class="hideall"></div> 
				  <div id="dlgResidentsMsg" class="hideall"></div> 
				  <div id="dlgleadEditsuccessMsg" class="hideall"></div> 
					<div id="dlgDelLeadMsg" class="hideall">Are you sure?</div>



					<%-- <form id="frmImportLead" class="hideall">
						<div class="backlink" align="right">
							<img id="importBackLead" src="/resources/images/backbtn.png" />&nbsp;</div>
							<div id="uploadDlg" class="uploadDlg">
								<div align="center" class="analysisbutton uplaodExists">
									Select a file to upload Business Partner data.
									<div id="fileuploader" style="width: 100%;"></div>
									
									<div class="greenbtn1">
										<input type="Button" value="GO" id="cmdImportLeads"
											class="filterGenerateButton5 analysisbutton uplaodExists">
									</div>
									<div class="uptxtHt"><div id="uploadingText"></div></div>
									<div class="Removebutton1 hideall" id="cancelLink">
									<p>Remove</p>
									<p><img src="/resources/images/trash.jpg"></p>
								</div>
								
								
								</div>
							</div>
							<input type="hidden" name="fileName" id="fileName" /> 
							<input
								type="hidden" id="importOrgId" name="orgId" />
					</form> --%>
					</div>
					<form id="getErrorLogFile" action="/business/getErrorLogFile"
						method="get">
						<input id="uploadFileName" type="hidden" name="fileName" />
					</form>
					</section>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%-- <form id="frmCreateOpp" action="/project" hidden="true"
			method="post">
			<input type="hidden" id="createOpp" name="createOpp" />
			 <input type="hidden" id="leadid" name="leadid" />  <input
				type="submit" id="cmdCreateOpp" />
		</form> --%>
  <input type="hidden" id="createLead" value="${createLead}"/>	
    <input type="hidden" id="createLeadSource" value="${source}"/>	
      <input type="hidden" id="createLeadRep" value="${rep}"/>	
  
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
		src=<c:url value="/resources/js/business.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery.maskedinput-1.3.min.js?t=1.1"/>></script>
	<script type="text/javascript"
		src=<c:url value="/resources/js/fileuploader.js"/>></script>
	<p class="foot">
		<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version
			1.0</a> <span class="sep">|</span>
		<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
		<span class="sep">|</span> -->
		<span>&copy; 2013 Zingcrm. All rights reserved.</span>
	</p>
<%-- <form hidden="true" id="geoNameValues"> 

</form> --%>
</body>

<script type="text/javascript">
	$('#FEA_LEAD').addClass("active");
</script>
</html>