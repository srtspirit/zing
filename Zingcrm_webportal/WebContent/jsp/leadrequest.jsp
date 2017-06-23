
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
<link type="text/css" href=<c:url value="/resources/css/style.css"/> rel="stylesheet" media="all" />
<link type="text/css" href=<c:url value="/resources/css/headerfooter.css?t=1.1"/> rel="stylesheet" media="all" />
<link type="text/css" href=<c:url value="/resources/css/jquery-ui-1.9.2.custom.min.css?t=1.1"/> rel="stylesheet" />
<link type="text/css" href=<c:url value="/resources/css/jqgrid.css?t=1.1"/> rel="stylesheet" media="all" />
<link type="text/css" href=<c:url value="/resources/css/fileuploader1.css?t=1.1"/> rel="stylesheet" media="all" />
<link rel="shortcut icon" type="images/x-icon" href="resources/images/favicon.ico"/>
</head>
<body>
	
	<div class="page-body-container">
		<div class="page-body-wrapper">
			<div class="page-body">
				<div class="filter">
				<section class="main">		
				 <div id="allLeadRequest" class="hideall">
					<form class="contentsearch" name='search' action="" method='POST'>
					  
					    <div class="infodashboard">
				        <div class="pageimg"><img class="boximg" src="/resources/images/activity.png">   </div>
				        <div class="pageimg">
					        <ul>
					        	<li class="infotitlehead">Lead Request</li>
					        	<!-- <li>Activities/Tasks created by you or assigned to you.</li> -->
					        </ul>
				         </div>
				        </div>
					     <c:if test="${not empty error}">
							<div class="errorblock">			
								${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
							</div>
						 </c:if>
					  
					     <div class="clrdiv"></div>
						
					   			<div id="showLeadRequestDataButtons" class="topbar"></div>		    
							    <div id="showGridLeadRequest" class="shwworkordergrid">
									<div id="leadrequestgrid" class="workordergrid">
										<table id="leadrequestList"></table>
										<div id="leadrequestPager"></div>	
									</div>		
								</div>
							</div>								
					   	</form>	
					</div>	
					   	
				<div id="createLeadRequest" class="hideall">	
				
			     <div class="infodashLeadReqboard">
				        <div class="pageimg"><img class="boximg" src="/resources/images/activity.png">   </div>
				        <div class="pageimg">
					        <ul>
					        	<li class="infotitlehead">Create Lead Request</li>
					        	<!-- <li>Activities/Tasks created by you or assigned to you.</li> -->
					        </ul>
				         </div>
				        </div>
				       
			   <form:form  name="newLeadRequestfrm" id="newLeadRequestfrm" style="display: inline;" method="post">
			   	  <div class="backlink" align="right"><img id="newBackLeadRequest" src="/resources/images/backbtn.png"/>&nbsp;<!-- <a id="backMember">Back</a> --></div>
			   	  <div class="clrdiv"></div>
			   	  <div class="mainDiv"> 
			   	 	 <div class="headleft ">
				        <p class="contlbl">
				        Date<font color="red"></font>
				        </p>
				        <p class="continput">
				    	  <form:input  path="createddate" type="text" id="newLeadRequestDate" name="newLeadRequestDate"  class="fieldinputac"></form:input>
				        </p>
				    </div> 
				    <div class="headright ">
				     <p class="continput ">
				    	  <input type="text" id="newLeadRequestError" name="newLeadRequestError"  class="fieldinputac reqFld" disabled="true"/>
				        </p>
				    </div>
			   		<div class="headleft">
				        <p class="contlbl">
				        Source<font color="red"></font>
				        </p>
				        <p class="continput">
				    	  <form:select path="source" type="text"  id="newLeadRequestSource" name="newLeadRequestSource" maxlength="50" class="fieldinputac" >
				    	  
				    	  </form:select>
				    	   <!-- <p id="newActivityLeadError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
					<div class="headleft">
				        <p class="contlbl">
				       Business Name<font color="red">*</font>
				        </p>
				        <p class="continput">
				     <form:input path="businessname" type="text"  id="newLeadBusinessname" name="newLeadBusinessname" maxlength="50" class="fieldinputac"></form:input>
				        </p>
				         <p id="newLeadBusinessnameError" class="hideall errorMsg">There is already a BP with same name.<p>
				         <p id="newLeadExistsError" class="hideall errorMsg">There is already a Lead with same name.<p>
				    </div> 
				    
				    <div class="headleft">
				        <p class="contlbl">
				        Contact Name<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:input path="contactname" type="text"  id="newLeadRequestContactname" name="newLeadRequestContactname" maxlength="50" class="fieldinputac"></form:input>
				    	   <!-- <p id="newLeadRequestContactnameError" class="hideall errorMsg">This information is required<p>-->
				    	    
				        </p>
				    </div> 
				    
				    	<div class="headleft">
							<div class="phonediv">
							<p class="contlbl">
								Phone Number <font color="red">*</font>
							</p>
							<p id="editphone">
								<form:input path="phonenumber" type="text"
									id="newLeadPrimaryPhone" name="newLeadPrimaryPhone"
									maxlength="50" class="fieldinputacphone"></form:input>
							<!-- <p id="newLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							<div class="exDiv"><p class="exp">Ext:</p>
							<p  id="editex">
								<form:input path="extension" type="text"
									id="newLeadPrimaryPhoneEx" name="newLeadPrimaryPhoneEx"
									maxlength="5" ></form:input>
							<!-- <p id="newLeadPrimaryPhoneExError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							
							
						</div>
				    	<div class="headleft">
									<p class="contlbl">
										E -mail <font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="email" type="text"
											id="newLeadReuestEmail" name="newLeadReuestEmail"
											maxlength="50" class="fieldinputac"></form:input>
										<p id="newleadreqValidError" class="hideall errorMsg">Email already exists<p>	
										</p>
									<!-- <p id="newLeadReuestEmailError" class="hideall errorMsg">This
										information is required
									<p> -->
								
									<!-- <p id="newLeadPrimaryEmailValidError" class="hideall errorMsg">
										Email is not valid format
									<p></p> -->
								</div>
								<div class="headleft">
									<p class="contlbl">
								   Website
									</p>
									<p class="continput">
								 <form:input path="website" type="text"  id="newleadrequestwesite" name="newleadrequestwesite" maxlength="50" class="fieldinputac"></form:input>
									</p>
								<!-- 	 <p id="newleadrequestwesiteError" class="hideall errorMsg">This information is required<p> -->
								</div> 
						
						<div class="headleft">
							<p class="contlbl">
							Sales Rep <font color="red"></font>
							</p>
							<p class="continput">
							<form:input  type="hidden" path="hdn_salesleadid" id="newLeadRequestAss" name="newLeadRequestAss"></form:input>
							  <form:input path="salesleadid" type="text"  id="newLeadRequestAssigned" name="newLeadRequestAssigned" maxlength="50" class="fieldinputac"></form:input>
							   <p id="newLeadRequestAssignedError" class="hideall errorMsg">This information is required<p>
							</p>
						</div> 
							<div class="headleft">
							<p class="contlbl">
							Notes
							</p>
							<p class="continputact conttextarea">
							  <form:textarea path="notes" type="text"  id="newleadrequestnotes" name="newleadrequestnotes" maxlength="5000" class="fieldinputac"></form:textarea>
							  <!--  <p id="newDescError" class="hideall errorMsg">This information is required<p> -->
							</p>
						</div> 
					
				    <div class="creLRBtn creLRBtnlead">
						<!--  <p><input type="button" value="Create BP" class="updatebutton" id="newCreatBP" /></p> -->
						
				   	   <p><input type="button" value="Save" class="signupbutton" id="newSubmitLeadRequest" /></p>
						<p><input type="button" value="Cancel" class="cancelbutton" id="newCancelLeadRequest" /></p>
				    </div> 
			   	  </div> 
			   	  </form:form>
			   	  </div>
	
			   	<div id="editLeadRequest" class="hideall">	
				
			     <div class="infodashboard">
				        <div class="pageimg"><img class="boximg" src="/resources/images/activity.png">   </div>
				        <div class="pageimg">
					        <ul>
					        	<li class="infotitlehead">Edit Lead Request</li>
					        	
					        </ul>
				         </div>
				        </div>
			   <form:form  name="editLeadRequestfrm" id="editLeadRequestfrm" style="display: inline;" method="post" >
			   	  <div class="backlink" align="right"><img id="editBackLeadRequest" src="/resources/images/backbtn.png"/>&nbsp;<!-- <a id="backMember">Back</a> --></div>
			   	   <div class="clrdiv"></div>
			   	  <div class="mainDiv"> 
			   	   <div class="headleft ">
				        <p class="contlbl">
				        Date
				        </p>
				        <p class="continput">
				    	  <form:input  path="createddate" type="text" id="editLeadRequestDate" name="editLeadRequestDate"  class="fieldinputac"></form:input>
				        </p>
				    </div> 
				    <div class="headright ">
				     <p class="continput ">
				    	  <input type="text" id="editLeadRequestError" name="editLeadRequestError"  class="fieldinputac reqFld" disabled="true"/>
				        </p>
				    </div>
					<div class="headleft">
				        <p class="contlbl">
				        Source<font color="red"></font>
				        </p>
				        <p class="continput">
				    	  <form:select path="source" type="text"  id="editLeadRequestSource" name="editLeadRequestSource" maxlength="50" class="fieldinputac" >
				    	 
				    	  </form:select>
				    	  <!--  <p id="editLeadReqError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
				    <div class="headleft">
				        <p class="contlbl">
				       Business Name<font color="red">*</font>
				        </p>
				        <p class="continput">
				     <form:input path="businessname" type="text"  id="editLeadBusinessname" name="editLeadBusinessname" maxlength="50" class="fieldinputac"></form:input>
				       <p id="editLeadBusinessnameError" class="hideall errorMsg">There is already a BP with same name.<p>
				         <p id="editLeadExistsError" class="hideall errorMsg">There is already a Lead with same name.<p>
				        </p>
				       <!--   <p id="editLeadBusinessnameError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				     <div class="headleft">
				        <p class="contlbl">
				        Contact Name<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:input path="contactname" type="text"  id="editLeadRequestContactname" name="editLeadRequestContactname" maxlength="50" class="fieldinputac"></form:input>
				        </p>
				    </div> 
				    
				    	<div class="headleft">
							<div class="phonediv">
							<p class="contlbl">
								Phone Number <font color="red">*</font>
							</p>
							<p id="editphone">
								<form:input path="phonenumber" type="text"
									id="editLeadPrimaryPhone" name="editLeadPrimaryPhone"
									maxlength="50" class="fieldinputacphone"></form:input>
							<!-- <p id="editLeadPrimaryPhoneError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							<div class="exDiv"><p class="exp">Ext:</p>
							<p  id="editex">
								<form:input path="extension" type="text"
									id="editLeadPrimaryPhoneEx" name="editLeadPrimaryPhoneEx"
									maxlength="5" ></form:input>
							<!-- <p id="editLeadPrimaryPhoneExError" class="hideall errorMsg">This
								information is required
							<p></p> -->
							</div>
							</div>
								<div class="headleft">
									<p class="contlbl">
										E -mail <font color="red">*</font>
									</p>
									<p class="continput">
										<form:input path="email" type="text"
											id="editLeadReuestEmail" name="editLeadReuestEmail"
											maxlength="50" class="fieldinputac"></form:input>
											<input type="hidden" id="hidLeadreqEmail" name="hidLeadreqEmail"/>
									<!-- <p id="editLeadReuestEmailError" class="hideall errorMsg">This
										information is required
									<p>-->
									 <p id="editleadreqValidError" class="hideall errorMsg">Email already exists<p>
								<!-- 	<p id="editLeadPrimaryEmailValidError" class="hideall errorMsg">
										Email is not valid format
									<p></p> -->
								</div>
							<div class="headleft">
									<p class="contlbl">
								   Website
									</p>
									<p class="continput">
								 <form:input path="website" type="text"  id="editleadrequestwesite" name="editleadrequestwesite" maxlength="50" class="fieldinputac"></form:input>
									</p>
								
								</div> 
							<div class="headleft">
							<p class="contlbl">
							Sales Rep <font color="red">*</font>
							</p>
							<p class="continput">
							  <form:select path="salesleadid" type="text"  id="editLeadRequestAssigned" name="editLeadRequestAssigned" maxlength="50" class="fieldinputac">
							   <option value="0">Select</option>
				    	  </form:select>
							
							</p>
						</div>
						<div class="headleft">
									<p class="contlbl txtspace">
										Not Qualified
									</p>
									<p class="contchk">
									
									<input class="contCheck" type="checkbox"  value="1" id="editLeadReqFlagYes"/>Yes
									</p>
								</div>
						<div class="headleft">
							<p class="contlbl">
							Notes
							</p>
							<p class="continputact conttextarea">
							  <form:textarea path="notes" type="text"  id="editleadrequestnotes" name="editleadrequestnotes" maxlength="5000" class="fieldinputac"></form:textarea>
							  
							</p>
						</div> 
						</div>
			   	
				     
				     <div class="creLRBtn">
				   	   
				   	    <p><input type="button" value="Create BP" class="updatebutton" id="editCreateBP" /></p>
				   	    <p><input type="button" value="Save" class="signupbutton" id="editSubmitLeadRequest" /></p>
						<p><input type="button" value="Cancel" class="cancelbutton" id="editCancelLeadRequest" /></p>
				    </div>
				  
				
			   	  </div> 
			   	      <form:hidden id="leadrequestId" path="Id"/>
				     <form:hidden id="createdby" path="createdby"/>
				     <form:hidden id="editisBPCreated" path="isBPCreated"/>
			   	 
			   	   
			  </form:form>
			   </section>
			   </div>
			   </div>
			   </div>
			   </div>
			   
			
	
	<script type="text/javascript" src=<c:url value="/resources/js/jquery-min.js?t=1.1"/>></script>
	<script type="text/javascript" src=<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js?t=1.1"/>></script>
	<!-- <script type="text/javascript" src=<c:url value="/resources/js/PIE_IE678.js"/>></script> -->
	<script type="text/javascript" src=<c:url value="/resources/js/i18n/grid.locale-en.js?t=1.1"/>></script>
	<script type="text/javascript" src=<c:url value="/resources/js/jqGrid.js?t=1.1"/>></script>
	<script type="text/javascript" src=<c:url value="/resources/js/leadrequest.js?t=1.7"/>></script>  
	<script type="text/javascript" src=<c:url value="/resources/js/jquery.maskedinput-1.3.min.js?t=1.1"/>></script>
	<script type="text/javascript" src=<c:url value="/resources/js/fileuploader.js?t=1.1"/>></script>  
	<script type="text/javascript" src=<c:url value="/resources/js/grid.inlinedit.js?t=1.1"/>></script> 
   
	
	<p class="foot">
				<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version 1.0</a>
				<span class="sep">|</span>
				<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
				<span>&copy; 2013 Zingcrm. All rights reserved.</span>			
			</p>
			<form id="frmCreateLead" action="/business" hidden="true" method="post">
				<input type="hidden" id="createLead" name="createLead" /> 
				<input type="hidden" id="leadreqid" name="leadreqid" /> <input
							type="submit" id="cmdCreateLead" />
			</form>
			
			
			<div id="dlgLeadRequestMsg" class="hideall"></div>
	<div id="dlgDelActivityMsg" class="hideall">Are you sure?</div>
	<div id="dlgRemoveActivityMsg" class="hideall">Are you sure?</div>
	<input type="hidden" id="actOppLead" value="${leadId}"/>
	<input type="hidden" id="actOppOpp" value="${oppId}"/>
     <input type="hidden" id="actId" value="${actId}"/>	
      <input type="hidden" id="createAct" value="${createAct}"/>
      <input type="hidden" id="createLeadReq" value="${createLeadReq}"/>		
       <input type="hidden" id="leadId"/>	
        <input type="hidden" id="oppId"/>	
        <input type="hidden" id="hdn_time" value=""/>	
</body>

<script type="text/javascript">
	$('#FEA_LEADREQUEST').addClass("active");
	
</script>

</html>