

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="header.jsp"%>
<html>
<head>
<script type="text/javascript">
window.history.forward();
function noBack() { window.history.forward(); }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<title>Zingcrm</title>
<link type="text/css" href=<c:url value="/resources/css/style.css?t=1.3"/> rel="stylesheet" media="all" />
<!-- <link type="text/css" href=<c:url value="/resources/css/reg.css"/> rel="stylesheet" media="all" /> -->
<link type="text/css" href=<c:url value="/resources/css/headerfooter.css?t=1.1"/> rel="stylesheet" media="all" />
<link type="text/css" href=<c:url value="/resources/css/jquery-ui-1.9.2.custom.min.css?t=1.1"/> rel="stylesheet" />
<link type="text/css" href=<c:url value="/resources/css/jqgrid.css?t=1.1"/> rel="stylesheet" media="all" />
<link type="text/css" href=<c:url value="/resources/css/fileuploader1.css?t=1.1"/> rel="stylesheet" media="all" />
<link rel="shortcut icon" type="images/x-icon" href="resources/images/favicon.ico"/>
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
				 <div id="allActivity" class="hideall">
<form class="contentsearch" name='search' action="" method='POST'>					  
					    <div class="infodashboard">
				        <div class="pageimg"><img class="boximg" src="/resources/images/activity.png">   </div>
				        <div class="pageimg">
					        <ul>
					        	<li class="infotitlehead">Activity</li>
					        	<li>Activities/Tasks created by you or assigned to you.</li>
					        </ul>
				         </div> 
				        </div>
					     <c:if test="${not empty error}">
							<div class="errorblock">			
								${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
							</div>
						 </c:if>
					  <div class="searchleadhright">
					  	<div class="subhead">Search by Business Partner or project</div>
					  	<p class="fieldtitle">
					       <!-- <label id="name">Business Partner or opportunity</label>	 -->				     
					       
					       <select class="fieldinput hideall" name='filterActivityOrgId' id="filterActivityOrgId"></select>
					       <select class="fieldinput" name='filterActivityLeadId' id="filterActivityLeadId"></select>
					       <select class="fieldinput" name='filterActivityOppId' id="filterActivityOppId"></select>
					       <input type="button" name="SearchOppbutton" id="SearchActivitybutton" value="Search" class="button searchBtn"/>    
					       <!-- <span id="search_Error" class="filtererror hideall">Enter the company to search.</span> -->
					    </p>
					    </div>
					     <div class="clrdiv"></div>
					   <!--  <div id="tabs" class="maptab">
							<ul>
								<li><a href="#tabs-1">Activity</a></li>								
							</ul>						
							<div id="tabs-1">			 -->					
					   			<div id="showActivityDataButtons" class="topbar"></div>		    
							    <div id="showGridActivity" class="shwworkordergrid">
									<div id="activitygrid" class="workordergrid">
										<table id="activityList"></table>
										<div id="activityPager"></div>	
									</div>		
								</div>
							</div>								
						<!--  </div> -->
					  	</form> 	
					</div>	
					   	
				<div id="createActivity" class="hideall">	
				
			     <div class="infodashboard">
				        <div class="pageimg"><img class="boximg" src="/resources/images/activity.png">   </div>
				        <div class="pageimg">
					        <ul>
					        	<li class="infotitlehead">Create Activity</li>
					        	<li>Activities/Tasks created by you or assigned to you.</li>
					        </ul>
				         </div>
				        </div>
				       
			   <form:form  name="newActivityfrm" id="newActivityfrm" style="display: inline;" action="/activity/createActivity" method="post" enctype="multipart/form-data">
			   	  <div class="backlink" align="right"><img id="newBackActivity" src="/resources/images/backbtn.png"/>&nbsp;<!-- <a id="backMember">Back</a> --></div>
			   	  <div class="clrdiv"></div>
			   	  <div class="mainDiv"> 
			   	 	 <div class="headleft hideall">
				        <p class="contlbl">
				        Organization<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:select path="org" id="newActivityOrg" name="newActivityOrg" maxlength="50" class="fieldinputac"></form:select>
				        </p>
				    </div> 
			   		<div class="headleft">
				        <p class="contlbl">
				        Business Partner<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <select type="text"  id="newActivityLead" name="newActivityLead" maxlength="50" class="fieldinputac" ></select>
				    	   <!-- <p id="newActivityLeadError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
					<div class="headright">
				        <p class="contlbl">
				       Project<font color="red">*</font>
				        </p>
				        <p class="continput">
				        <select  class="fieldinputac" name="newActivityOpp" id="newActivityOpp"></select>
				        </p>
				    <!--      <p id="newActivityOppError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				    
				    <div class="headleft">
				              
				        <p class="contlbl">
				     	Type<font color="red">*</font>
				        </p>
				        <p class="continput">
				        <form:select path="type" class="fieldinputac" name="newActivityType" id="newActivityType"></form:select>
				        </p>
				    </div> 
				    
				    <div class="headright">
				    <p class="contlbl">
				        Activity (Purpose)<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:input path="name" type="text"  id="newActivity" name="newActivity" maxlength="50" class="fieldinputac"></form:input>
				    	   <!-- <p id="newActivityError" class="hideall errorMsg">This information is required<p> -->
				    	    <p id="newActivityValidError" class="hideall errorMsg">Activity Name already exists<p>
				        </p>
				  
				         <!-- <p id="newActivityTypeError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				    
				    <div class="headleft">
				        <p class="contlbl">
				        Assigned To<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:select path="assignedTo" type="text"  id="newActivityAssigned" name="newActivityAssigned" maxlength="50" class="fieldinputac">
				    	  <option value="0">Select</option></form:select>
				    <!-- 	   <p id="newActivityAssignedError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
				    
				    <div class="headright">
				        <p class="contlbl">
				     	Due Date<font color="red">*</font>
				        </p>
				        <p class="continput">
				        <form:input readonly="true" path="dueDate" class="fieldinputac" name="newActivityDate" id="newActivityDate"></form:input>
				        </p>
				       <!--   <p id="newActivityDateError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				    <div id="createUserActivity"></div>
				     <div class="headleft">
				        <p class="contlbl">
				        Description<font color="red">*</font>
				        </p>
				        <p class="continputact conttextarea">
				    	  <form:textarea path="desc" type="text"  id="newDesc" name="newDesc" maxlength="5000" class="fieldinputac"></form:textarea>
				    	 <!--   <p id="newDescError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
				    <div class="clrdiv2"></div>
				    
				  <div class="fileupCom">
				     <p>Upload a File</p>
				    <div class="fileNote"> Note:Total file size uploaded should not exceed 30MB</div>
				     <div id="fileuploader" class="filebrowse" ></div>
				    
				    <!-- <p id="newfileuploadlimit" class="hideall errorMsg">File limit exceeded<p>
				      <input id="addFile" type="button" value="Add File" />
					     <table id="fileTable">
					     <tr><td><input name="filesList[0]" type="file" multiple="true"></input></td></tr>
					     </table>-->
				    <!--  <div class="hideall Removebutton1" id="cancelLink"><p>Remove</p><p><img src="/resources/images/trash.jpg"></p></div> -->
				     </div>	 
				   
								     				      
				    <div class="creCanBtn">
				   	   <p><input type="button" value="Save" class="signupbutton" id="newSubmitActivity" /></p>
						<p><input type="button" value="Go Back" class="cancelbutton" id="newCancelActivity" /></p>
				    </div> 
			   	  </div> 
			   	  <form:hidden id="newActBusinessHidden" path="lead"/>
			   	  <form:hidden id="newActOppHidden" path="opportunity"/>
			   	 <!-- <form:hidden id="fileName" path="fileName"/>  -->
			   	  	<input id="filesName" name="filesList" type="hidden">
			  </form:form>
			   </div>	 
			   
			   <div id="viewActivity" class="hideall">	
				
			     <div class="infodashboard">
				        <div class="pageimg"><img class="boximg" src="/resources/images/activity.png">   </div>
				        <div class="pageimg">
					        <ul>
					        	<li class="infotitlehead">View Activity</li>
					        	<li >Activities/Tasks created by you or assigned to you.</li>
					        </ul>
				         </div>
				        </div>
				        
			   <form:form  name="viewActivityfrm" id="viewActivityfrm" style="display: inline;" method="post" >
			   	  <div class="backlink" align="right"><img id="viewBackActivity" src="/resources/images/backbtn.png"/>&nbsp;<!-- <a id="backMember">Back</a> --></div>
			   	  <div class="clrdiv"></div>
			   	  <div class="mainDiv"> 
			   	 	 <div class="headleft hideall">
				        <p class="contlbl">
				        Organization
				        </p>
				        <p class="continput">
				    	  <form:select path="org" id="viewActivityOrg" name="viewActivityOrg" maxlength="50" class="fieldinputac"></form:select>
				        </p>
				    </div> 
			   		<div class="headleft">
				        <p class="contlbl">
				        Business Partner
				        </p>
				        <p class="continput">
				    	  <form:input path="lead" type="text"  id="viewActivityLead" name="viewActivityLead" maxlength="50" class="fieldinputac" ></form:input>
				        </p>
				    </div> 
					<div class="headright">
				        <p class="contlbl">
				       Project
				        </p>
				        <p class="continput">
				        <form:input  path="opportunity" class="fieldinputac" name="viewActivityOpp" id="viewActivityOpp"></form:input>
				        </p>
				    </div> 
				    
				    <div class="headleft">
				     <p class="contlbl">
				     	Type
				        </p>
				        <p class="continput">
				        <form:input path="type" class="fieldinputac" name="viewActivityType" id="viewActivityType"></form:input>
				        </p>
				       
				    </div> 
				    
				    <div class="headright">
				        <p class="contlbl">
				        Activity (Purpose)
				        </p>
				        <p class="continput">
				    	  <form:input path="name" type="text"  id="viewActivityName" name="viewActivityName" maxlength="50" class="fieldinputac"></form:input>
				        </p>
				    </div> 
				    
				    <div class="headleft">
				        <p class="contlbl">
				        Assigned To
				        </p>
				        <p class="continput">
				    	  <form:input path="assignedTo" type="text"  id="viewActivityAssigned" name="viewActivityAssigned" maxlength="50" class="fieldinputac"></form:input>
				        </p>
				    </div> 
				    
				    <div class="headright">
				        <p class="contlbl">
				     	Due Date
				        </p>
				        <p class="continput">
				        <form:input readonly="true" path="dueDate" class="fieldinputac" name="viewActivityDate" id="viewActivityDate"></form:input>
				        </p>
				    </div> 
				    <div id="viewUserActivity"></div>
				     <div class="headleft">
				        <p class="contlbl">
				     Status<font color="red"></font>
				        </p>
				        <p class="continput">
				        <form:input path="Status" class="fieldinputac" name="viewActivityStatus" id="viewActivityStatus"></form:input>
				        </p>
				        <!--  <p id="editActivityStatusError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				       <div id="viewFileUpload" class="headright">
				     <p class="contlbl">
				     	Attached file
				        </p> 
				     <p class="viewfileup"><a id="viewFileUploadImage" href="#"></a></p> 
				     <!-- <p class="contlbl">
				     	Uploaded file
				        </p>< --></div>
				    <div class="headleft">
				        <p class="contlbl">
				        Description<font color="red"></font>
				        </p>
				        <p class="continputact conttextarea">
				    	  <form:textarea path="desc" type="text"  id="viewDesc" name="viewDesc" maxlength="5000" class="fieldinputac"></form:textarea>
				    	  <!--  <p id="newDescError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
				  
				       
				    
				    <div class="creCanBtn" style="padding-top: 206px;">
						<p><input type="button" value="Close" class="viewcancelbutton" id="viewCloseActivity" /></p>
				    </div>
			   	  </div> 
			  </form:form>
			   </div>	 
			   
			   
			   	<div id="editActivity" class="hideall">	
				
			     <div class="infodashboard">
				        <div class="pageimg"><img class="boximg" src="/resources/images/activity.png">   </div>
				        <div class="pageimg">
					        <ul>
					        	<li class="infotitlehead">Edit Activity</li>
					        	<li >Activities/Tasks created by you or assigned to you.</li>
					        </ul>
				         </div>
				        </div>
				       
			   <form:form  name="editActivityfrm" id="editActivityfrm" style="display: inline;" action="/activity/editActivity" method="post" enctype="multipart/form-data" >
			   	  <div class="backlink" align="right"><img id="editBackActivity" src="/resources/images/backbtn.png"/>&nbsp;<!-- <a id="backMember">Back</a> --></div>
			   	   <div class="clrdiv"></div>
			   	  <div class="mainDiv"> 
			   	 	 <div class="headleft hideall">
				        <p class="contlbl">
				        Organization<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:select path="org" id="editActivityOrg" name="editActivityOrg" maxlength="50" class="fieldinputac"></form:select>
				        </p>
				    </div> 
			   		<div class="headleft">
				        <p class="contlbl">
				        Business Partner<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:select path="lead" type="text"  id="editActivityLead" name="editActivityLead" maxlength="50" class="fieldinputac" ></form:select>
				    	  <!--  <p id="editActivityLeadError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
					<div class="headright">
				        <p class="contlbl">
				       Project<font color="red">*</font>
				        </p>
				        <p class="continput">
				        <form:select  path="opportunity" class="fieldinputac" name="editActivityOpp" id="editActivityOpp"></form:select>
				        </p>
				      <!--    <p id="editActivityOppError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				    
				    <div class="headleft">
				       <p class="contlbl">
				     	Type<font color="red">*</font>
				        </p>
				        <p class="continput">
				        <form:select path="type" class="fieldinputac" name="editActivityType" id="editActivityType"></form:select>
				        </p>
				    </div> 
				    
				    <div class="headright">
				    	 <p class="contlbl">
				        Activity (Purpose)<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:input path="name" type="text"  id="editActivityName" name="editActivityName" maxlength="50" class="fieldinputac"></form:input>
				    	  				    	   
				    	<!--    <p id="editActivityNameError" class="hideall errorMsg">This information is required<p> -->
				    	    <p id="editActivityNameValidError" class="hideall errorMsg">Activity Name already exists<p>
				        </p>
				        
				        
				         <!-- <p id="editActivityTypeError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				    
				    <div class="headleft">
				        <p class="contlbl">
				        Assigned To<font color="red">*</font>
				        </p>
				        <p class="continput">
				    	  <form:select path="assignedTo" type="text"  id="editActivityAssigned" name="editActivityAssigned" maxlength="50" class="fieldinputac"></form:select>
				    	  <input type="text"  id="editActivityAssignedText"  maxlength="50" class="fieldinputac hideall"></input>
				    	   <!-- <p id="editActivityAssignedError" class="hideall errorMsg">This information is required<p> -->
				        </p>
				    </div> 
				    
				    <div class="headright">
				        <p class="contlbl">
				     	Due Date<font color="red">*</font>
				        </p>
				        <p class="continput">
				        <form:input readonly="true" path="dueDate" class="fieldinputac" name="editActivityDate" id="editActivityDate"></form:input>
				        </p>
				       <!--   <p id="editActivityDateError" class="hideall errorMsg">This information is required<p> -->
				    </div> 
				    <div id="editUserActivity"></div>
				    <div class="headleft">
				    <p class="contlbl">
				     Status<font color="red">*</font>
				        </p>
				        <p class="continput">
				        <select class="fieldinputac" id="editActivityStatus"></select>
				        </p>
				         <p id="editActivityStatusError" class="hideall errorMsg">This information is required<p>
				    </div> 
				      <div class="headleft">
				        <p class="contlbl">
				        Description<font color="red">*</font>
				        </p>
				        <p class="continputact conttextarea">
				    	  <form:textarea path="desc" type="text"  id="editDesc" name="editDesc" maxlength="5000" class="fieldinputac"></form:textarea>
				    	<!--    <p id="editDescError" class="hideall errorMsg">This information is required<p>  -->
				        </p>
				    </div> 
				  
				    <!-- <div id="editUserActivity"></div> -->
				     <div class="clrdiv2"></div>
				     <div class="fileupCom">
				     <div id="editFileUploaderPart">
				       <p class="">
				     	 Upload file
				        </p> 
				            <div class="fileNote"> Note:Total file size uploaded should not exceed 30MB</div>
				         <input id="documentsize" type="hidden" />
				        <div id="editfileuploader"></div>
				        </div>
				          <div id="editfileTable" class="imgViewBrd">
					  		<!--  <p class="viewfileupnew"><a id="editFileImage" href="#"></a></p>  -->
					     </div> 
				     </div>
				    <!--  <div id="editFileUpload" class="fileupCom hideall">
				     <p class="">
				     	Upload file
				        </p> 
				         <p id="uploadfilelimitError" class="hideall errorMsg">File limit exceeded<p>
					     <input id="editaddFile" type="button" value="Add File" />
					  <div id="editfileuploader"> </div> 
					     <input id="documentsize" type="hidden" />
					     <div id="editfileTable">
					     </div>
					      <table id="AddfileTable">
					     </table>
					     
				      <p class="viewfileupnew"><a id="editFileUploadImage" href="#"></a></p> 
				 	  <div class="Removebutton1 editcancelLink"> <p id="upladedfileedit"><a id="editUploadImage" href="#"></a></p><p>Remove</p><p><img src="/resources/images/trash.jpg"></p></div>
				 	   <input id="EditaddFile" type="button" value="Add File" />
					     <div id="editfileTable" class="viewfileupnew">
					   <p class="viewfileupnew"><a id="editFileImage" href="#"></a></p> 
					     </div> 
				     </div> -->
				     
				 <!--     <div class="fileupCom hideall" id="editNewUploading">
				     <p>Upload a File</p>
				    <p id="EdituploadfilelimitError" class="hideall errorMsg">File limit exceeded<p> 
				     <div id="editfileuploader"> </div> 
				     <p> <input id="editnewaddFile" type="button" value="Browse" /></p>
				      <table id="AddnewfileTable">
				     <tr><td><input name="filesCover[0]" type="file" ></input></td></tr> 
				     </table>
				    
				     <div class="Removebutton1 hideall" id="editNewcancelLink">   <p id="upladednewfileedit"><a  href="#"></a></p><p>Remove</p><p><img src="/resources/images/trash.jpg"></p></div>
				     </div>	 -->
				      
				      
				     <div class="creCanBtn">
				   	   <p><input type="button" value="Save" class="signupbutton" id="editSubmitActivity" /></p>
						<p><input type="button" value="Go Back" class="cancelbutton" id="editCancelActivity" /></p>
				    </div>
				    
				     <!--  <div id="editFileUpload" class="headleft">
				     <p class="contlbl">Attached File </p>
				     <p class="viewfileup"><a id="editFileUploadImage" href="#"></a></p>
				     <div class="attchfile"><div id="editRemoveUpload" class="Removebutton1">Remove  Attachment</div></div>
				     </div>  --> 
				 
			   	  </div> 
			   	  <input id="editfileName" name="filesList" type="hidden">
			   	
			   	  <form:hidden id="activityId" path="id"/>
			   	  <form:hidden id="createdBy" path="createdBy"/>
			   	  <form:hidden id="createdDate" path="createdDate"/>
			   	  <form:hidden id="status" path="status"/>
			   	  <form:hidden id="statusName" path="statusName"/>
			   	  <form:hidden id="editActStatus" path="activityStatus"/>
			   	   
			  </form:form>
			   </div>	 
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
	<script type="text/javascript" src=<c:url value="/resources/js/activity.js?t=1.10"/>></script>  
	<script type="text/javascript" src=<c:url value="/resources/js/fileuploader.js?t=1.3"/>></script>
	<script type="text/javascript" src=<c:url value="/resources/js/grid.inlinedit.js?t=1.1"/>></script> 
	
	<p class="foot">
				<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version 1.0</a>
				<span class="sep">|</span>
				<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
				<span>&copy; 2013 Zingcrm. All rights reserved.</span>			
			</p>
			<div id="dlgActivityMsg" class="hideall"></div>
			<div id="dlgActivitySuccessMsg" class="hideall"></div>
	<div id="dlgDelActivityMsg" class="hideall">Are you sure?</div>
	<div id="dlgRemoveActivityMsg" class="hideall">Are you sure?</div>
	<input type="hidden" id="actOppLead" value="${leadId}"/>
	<input type="hidden" id="actOppOpp" value="${oppId}"/>
     <input type="hidden" id="actId" value="${actId}"/>	
      <input type="hidden" id="createAct" value="${createAct}"/>	
      <input type="hidden" id="actStatus" value="${actStatus}"/>
      <input type="hidden" id="roleId" value="${roleid}"/>
      <input type="hidden" id="actStatusid" value="${actStatusid}"/>
       <input type="hidden" id="Editstatus" value="<%=request.getParameter("actEditstatus") %>"/>
       <input type="hidden" id="Createstatus" value="<%=request.getParameter("actCreatestatus") %>"/>
       <input type="hidden" id="editActId" value="<%=request.getParameter("editActId") %>"/>
       <input type="hidden" id="leadId"/>	
        <input type="hidden" id="oppId"/>	
</body>

<script type="text/javascript">
	$('#FEA_ACTIVITY').addClass("active");
	$(function()
			{
			$('#addFile').click(function() {
				$('#newfileuploadlimit').addClass('hideall');
				if($('#fileTable tr').children().length<10){
			    var fileIndex = $('#fileTable tr').children().length ;
			        $('#fileTable').append(
			               
			    '<tr><td><input type="file" name="filesList['+ fileIndex +']" multiple="true"/></td></tr>');
				}
				else{
					$('#newfileuploadlimit').removeClass('hideall');
				}
					});
			$('#editaddFile').click(function() {
				$('#uploadfilelimitError').addClass('hideall');
				var documentsize =parseInt($('#documentsize').val())+parseInt($('#AddfileTable tr').children().length+1);
				if(documentsize<11){
			    var editfileIndex = $('#AddfileTable tr').children().length+1 ;
			        $('#AddfileTable').append(
			               
			    ' <tr><td><input type="file" name="filesCover['+ editfileIndex +']" multiple="true"/></td></tr>');
				}
				else{
					$('#uploadfilelimitError').removeClass('hideall');
				}
					});
					
			/* 
			$('#editnewaddFile').click(function(){
				if($('#AddnewfileTable tr').children().length<10){
					$('#uploadfilelimitError').addClass('hideall');
				  var editnewfileIndex = $('#AddnewfileTable tr').children().length+1 ;
			        $('#AddnewfileTable').append(
			               
			    ' <tr><td><input type="file" name="filesCover['+ editnewfileIndex +']" class="filesCover['+ editnewfileIndex +'] " /></td></tr>');
				}
				else{
					$('#EdituploadfilelimitError').removeClass('hideall');
				}	
			}); */
			
			});
</script>
</html>