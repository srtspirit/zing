<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<html>
<head>
<script type="text/javascript">
window.history.forward();
function noBack() { window.history.forward(); }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=9" /> -->
<meta http-equiv="X-UA-Compatible" content="Edge" />
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
					<div id="allOpp" class="hideall">
						<form class="contentsearch" name='search' action="" method='POST'>

							<div class="infodashboard">
								<div class="pageimg">
									<img class="boximg" src="/resources/images/opportunity.png">
								</div>
								<div class="pageimg">
									<ul>
										<li class="infotitlehead">Opportunity</li>
										<li>Opportunities from your Prospects and Customers</li>
									</ul>
								</div>
							</div>
							<c:if test="${not empty error}">
								<div class="errorblock">
									${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
							</c:if>
							<div class="searchright">
								<div class="subhead">Search by Business Partner</div>
								<p class="fieldtitle">
									<!-- <label id="name">Business Partner</label> --> <select
										class="fieldinput hideall" name='filterOppOrg'
										id="filterOppOrg"></select> <select class="fieldinput"
										name='filterOppcompany' id="filterOppcompany"></select>&nbsp;
									<input type="button" name="SearchOppbutton"
										id="SearchOppbutton" value="Go" class="button searchBtn" />
									<!--   <span id="search_Error" class="filtererror hideall">Enter the company to search.</span> -->
								</p>
							</div>
							<div class="clrdiv"></div>
							<!-- <div id="tabs" class="maptab">
								<ul>
									<li><a href="#tabs-1">Opportunity</a></li>
								</ul>
								<div id="tabs-1"> -->
									<div id="showOppDataButtons" class="topbar"></div>
									<div id="showGridOpp" class="shwworkordergrid">
										<div id="oppgrid" class="workordergrid">
											<table id="oppList"></table>
											<div id="oppPager"></div>
										</div>
									</div>
								</div>
							<!-- </div> -->
						</form>
					</div>

					<div id="createOpp" class="hideall">

						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/opportunity.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Create Project</li>
									<li>Opportunities from your Prospects and Customers</li>
								</ul>
							</div>
						</div>
						<form:form name="leadfrm" id="newOppfrm" style="display: inline;"
							method="post">
							<div class="backlink" align="right">
								<img id="newbackOpp" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>
							<div class="clrdiv"></div>
							<div class="mainDiv">
								<div class="headleft hideall">
									<p class="contlbl">
										Organization<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="org" id="oppOrg" name="oppOrg"
											maxlength="50" class="fieldinputac"></form:select>
									</p>
								</div>
								<div class="headleft">
									<p class="contlbl">
										Business Partner<font color="red">*</font>
									</p>
									<p class="continput">
										<select type="text" id="newOppCompany"
											name="newOppCompany" maxlength="50" class="fieldinputac"></select>
									<p id="newOppCompanyError" class="errorMsg hideall">This
										information is required
									<p></p>
								</div>


								<div class="headright">
									<p class="contlbl">Document Id</p>
									<p class="continput">
									New
										<%-- <form:input readonly="true" path="document" type="text"
											id="newOppDocument" name="newOppDocument" maxlength="50"
											class="fieldinputac"></form:input>
										<!-- <p id="newOppDocumentError" class="hideall">This information is required<p> --> --%>
									</p>
								</div>
								<div class="oppWid">
									<div class="headleft">
										<p class="contlbl">
											Project<font color="red">*</font>
										</p>
										<p class="continput">
											<form:input path="opportunity" type="text" id="newOpp"
												name="newOpp" maxlength="50" class="fieldinputac"></form:input>
										<p id="newOppError" class="errorMsg hideall">This
											information is required
										<p>
										<p id="newOppValidError" class=" errorMsg hideall">Project
											Already exists
										<p></p>
									</div>

									<div class="headright">
										<p class="contlbl">
											Description<font color="red">*</font>
										</p>
										<p class="continput">
											<form:textarea path="description" type="text" resize="none"
												id="newOppDesc" name="newOppDesc" maxlength="250"
												class="fieldinputac"></form:textarea>
										<p id="newOppDescError" class=" errorMsg hideall">This
											information is required
										<p></p>
									</div>
									
									<div class="headleft">
										<p class="contlbl">
											Expected Value<font color="red">*</font>
										</p>
										<p class="continput">
											<form:select path="expectedVal" type="text" id="newExpectedVal"
												name="newExpectedVal" maxlength="50" class="fieldinputac"
												>
												<option value=0>Select</option>
											</form:select>
											</p>
										<p id="newExpectedValError" class="errorMsg hideall">This
											information is required
										</p>
										
										<p class="continput mtop">
											<form:input path="expectedValue" type="text" resize="none"
												id="newExpectedValue" name="newExpectedValue" maxlength="8"
												class="fieldinputac"></form:input>
										</p>
										<p id="newExpectedValueError" class=" errorMsg hideall">This
											information is required
										</p>
										<p id="newExpectedValueValidError" class="errorMsg hideall">This
											information is numeric
										</p>
									</div>
									
									<div class="headright">
										<p class="contlbl">
										</p>
									
										
									</div>
									<div class="spacebtn"></div>
									<div class="headleft">
										<p class="contlbl">
											Probability<font color="red">*</font>
										</p>
										<p class="continput">
										<form:input path="probability" type="text" resize="none"
												id="newProbability" name="newProbability" maxlength="2"
												class="fieldinputac"></form:input>
										<p id="newProbabilityError" class="errorMsg hideall">This
											information is required
										<p>
										<p id="newProbabilityValidError" class="errorMsg hideall">This
											information is numeric
										<p>
										</p>
									</div>
									
									<div class="headright">
										<p class="contlbl">
										Expected Close Date
										</p>
										<p class="continput">
											<form:input path="closeDate" type="text" resize="none"
												id="newClosedDate" name="newClosedDate" maxlength="8"
												class="fieldinputac" readonly="true"></form:input>
									</div>
									
									
								</div>
								<div class="clrdiv"></div>

								<div class="creOppCanBtn">
									<p>
										<input type="button" value="Create" class="signupbutton"
											id="submitOpp" />
									</p>
									<p>
										<input type="button" value="Cancel" class="cancelbutton"
											id="cancelOpp" />
									</p>
								</div>


							</div>
							 <form:hidden path="company" id="newCompanyHidden" />
						</form:form>
					</div>


					<div id="viewOpp" class="hideall">

						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/opportunity.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">View Project</li>
									<li>Opportunities from your Prospects and Customers</li>
								</ul>
							</div>
						</div>

						<form:form name="viewOppfrm" id="viewOppfrm"
							style="display: inline;" method="post">
							<div class="backlink" align="right">
								<img id="viewbackOpp" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>
							<div class="clrdiv"></div>
							<div class="mainDiv">
								<div class="headleft hideall">
									<p class="contlbl">Organization</p>
									<p class="continput">
										<form:select path="org" id="viewOrg" name="viewOrg"
											maxlength="50" class="fieldinputac"></form:select>
									</p>
								</div>

								<div class="headleft">
									<p class="contlbl">Business Partner</p>
									<p class="continput">
										<form:input path="company" type="text" id="viewOppCompany"
											name="viewOppCompany" maxlength="50" class="fieldinputac"></form:input>
									</p>
								</div>
								<div class="headright">
									<p class="contlbl">Document Id</p>
									<p class="continput">
										<form:input readonly="true" path="document" type="text"
											id="viewOppDocument" name="viewOppDocument" maxlength="50"
											class="fieldinputac"></form:input>
										<!-- <p id="newOppDocumentError" class="hideall">This information is required<p> -->
									</p>
								</div>

								<div class="headleft">
									<p class="contlbl">Project</p>
									<p class="continput">
										<form:input path="opportunity" type="text" id="viewOppName"
											name="viewOppName" maxlength="50" class="fieldinputac"></form:input>
									</p>
								</div>

								<div class="headright">
									<p class="contlbl">Description</p>
									<p class="continput">
										<form:textarea path="description" type="text" resize="none"
											id="viewOppDesc" name="viewOppDesc" maxlength="250"
											class="fieldinputac"></form:textarea>
									</p>
								</div>
								
								<div class="headleft">
									<p class="contlbl">Expected Value</p>
									<p class="continput">
										<input  type="text" resize="none" id="viewOppExpectedVal" name="viewOppExpectedVal"
											class="fieldinputac"/>
											
									
									</p>
									<p class="continput mtop">
										<form:input path="expectedValue" type="text" resize="none"
											id="viewOppExpectedValue" name="viewOppExpectedValue" maxlength="8"
											class="fieldinputac"></form:input>
									</p>
								</div>
								
								<div class="headright">
									<p class="contlbl"></p>
									
								</div>
								<div class="spacebtn"></div>
								<div class="headleft">
									<p class="contlbl">Probability</p>
									<p class="continput">
										<form:input path="probability" type="text" resize="none"
											id="viewOppProbablity" name="viewOppProbablity" maxlength="2"
											class="fieldinputac"></form:input>
									</p>
								</div>
								<div class="headright">
									<p class="contlbl">Expected Close Date</p>
									<p class="continput">
										<form:input path="closeDate" type="text" resize="none"
											id="viewClosedDate" name="viewClosedDate" maxlength="2"
											class="fieldinputac" readonly="true"></form:input>
									</p>
								</div>
								<div class="headleft">
									<p class="contlbl">Status</p>
									<p class="continput">
										<form:input path="oppStatus" type="text" resize="none"
											id="viewOppStatus" name="viewOppStatus" maxlength="2"
											class="fieldinputac"></form:input>
									</p>
								</div>
								
								<div class="clrdiv"></div>

								<div class="creCanBtn">
									<p>
										<input type="button" value="Cancel" class="cancelbutton"
											id="closeOpp" />
									</p>
								</div>


							</div>
						</form:form>
					</div>

					<div id="editOpp" class="hideall">

						<div class="infodashboard">
							<div class="pageimg">
								<img class="boximg" src="/resources/images/opportunity.png">
							</div>
							<div class="pageimg">
								<ul>
									<li class="infotitlehead">Edit Project</li>
									<li>Opportunities from your Prospects and Customers</li>
								</ul>
							</div>
						</div>

						<form:form name="editOppfrm" id="editOppfrm"
							style="display: inline;" method="post">

							<div class="backlink" align="right">
								<img id="editbackOpp" src="/resources/images/backbtn.png" />&nbsp;
								<!-- <a id="backMember">Back</a> -->
							</div>
							<div class="clrdiv"></div>
							<div class="mainDiv">
								<div class="headleft hideall">
									<p class="contlbl">
										Organization<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="org" id="editOppOrg" name="editOppOrg"
											maxlength="50" class="fieldinputac"></form:select>
									</p>
								</div>

								<div class="headleft">
									<p class="contlbl">
										Business Partner<font color="red">*</font>
									</p>
									<p class="continput">
										<form:select path="company" type="text" id="editOppCompany"
											name="editOppCompany" maxlength="50" class="fieldinputac"
											readonly="true;"></form:select>
									<p id="editOppCompanyError" class="hideall">This
										information is required
									<p></p>
								</div>


								<div class="headright">
									<p class="contlbl">Document Id</p>
									<p class="continput">
										<form:input readonly="true" path="document" type="text"
											id="editOppDocument" name="editOppDocument" maxlength="50"
											class="fieldinputac"></form:input>
										<!-- <p id="editOppDocumentError" class="hideall">This information is required<p> -->
									</p>
								</div>

								<div class="headleft">
									<p class="contlbl">
										Project<font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="opportunity" type="text" id="editOppName"
											name="editOppName" maxlength="50" class="fieldinputac"></form:input>
									<p id="editOppNameError" class="hideall errorMsg">This information
										is required
									<p>
									<p id="editOppNameValidError" class="hideall errorMsg">Project
										Already exists
									<p></p>
								</div>

								<div class="headright">
									<p class="contlbl">
										Description<font color="red">*</font>
									</p>
									<p class="continput">
										<form:textarea path="description" type="text" resize="none"
											id="editOppDesc" name="editOppDesc" maxlength="250"
											class="fieldinputac"></form:textarea>
									<p id="editOppDescError" class="hideall errorMsg">This information
										is required
									<p></p>
								</div>
								
									<div class="headleft">
										<p class="contlbl">
											Expected Value<font color="red">*</font>
										</p>
										<p class="continput">
											<form:select path="expectedVal" type="text" id="editExpectedVal"
												name="editExpectedVal" maxlength="50" class="fieldinputac"
												>
												<option value=0>Select</option>
											</form:select>
										<p id="editExpectedValError" class="errorMsg hideall">This
											information is required
										<p>
										</p>
										<p class="continput mtop">
											<form:input path="expectedValue" type="text" resize="none"
												id="editExpectedValue" name="editExpectedValue" maxlength="8"
												class="fieldinputac"></form:input>
										</p>
										<p id="editExpectedValueError" class=" errorMsg hideall">This
											information is required
										</p>
										<p>
											<p id="editExpectedValueValidError" class="errorMsg hideall">This
											information is numeric
										</p>
									</div>
									
									<div class="headright">
										<p class="contlbl">
										</p>
										
										
									</div>
									<div class="spacebtn"></div>
									<div class="headleft">
										<p class="contlbl">
											Probability<font color="red">*</font>
										</p>
										<p class="continput">
										<form:input path="probability" type="text" resize="none"
												id="editProbability" name="editProbability" maxlength="2"
												class="fieldinputac"></form:input>
										<p id="editProbabilityError" class="errorMsg hideall">This
											information is required
										<p>
										<p id="editProbabilityValidError" class="errorMsg hideall">This
											information is numeric
										<p>
										</p>
									</div>
									
									<div class="headright">
										<p class="contlbl">
										Expected Close Date
										</p>
										<p class="continput">
											<form:input path="closeDate" type="text" resize="none"
												id="editClosedDate" name="editClosedDate" maxlength="8"
												class="fieldinputac" readonly="true"></form:input>
									</div>
									<div class="headleft">
									<p class="contlbl">Status</p>
									<p class="continput">
										<select  type="text" resize="none"											id="editOppStatus" 
											class="fieldinputac"></select>
									</p>
								</div>
								
								<div class="clrdiv"></div>
								<div class="creOppEditCanBtn">
									<p>
										<input type="button" value="Update" class="signupbutton"
											id="editOppUpdate" />
									</p>
									<p>
										<input type="button" value="Cancel" class="cancelbutton"
											id="editCancelOpp" />
									</p>
								</div>
							</div>
							<form:hidden path="CreatedBy" id="editCreatedBy" />
							<form:hidden path="createdDate" id="editCreatedDate" />
							<form:hidden path="id" id="editId" />
							<form:hidden path="status" id="editStatus" />
								<form:hidden path="oppStatus" id="editOPPORStatus" />
							<form:hidden path="defaultstate" id="editDefault" />
						</form:form>
					</div>
					<form id="frmOppAct" action="/activity" hidden="true" method="post">
						<input type="hidden" id="oppoppleadId" name="oppId" /> <input
							type="hidden" id="oppleadId" name="leadId" /> <input
							type="submit" id="cmdOPPActId" />
					</form>

					</section>
				</div>
			</div>
		</div>
	</div>


	<input type="hidden" value="${leadId}" id="oppLeadId" />
	<input type="hidden" value="${createOpp}" id="createOpportunity" />
	  <input type="hidden" id="leadId"/>
	<div id="dlgOppMsg" class="hideall"></div>
	<div id="dlgDelOppMsg" class="hideall">Are you sure?</div>
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
		src=<c:url value="/resources/js/project.js?t=1.4"/>></script>
	<p class="foot">
		<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version
			1.0</a> <span class="sep">|</span>
		<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
		<span>&copy; 2013 Zingcrm. All rights reserved.</span>
	</p>

</body>

<script type="text/javascript">
	$('#FEA_OPPORTUNITY').addClass("active");
</script>
</html>