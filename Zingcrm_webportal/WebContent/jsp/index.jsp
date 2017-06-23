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
<meta http-equiv="X-UA-Compatible" content="IE=9" />
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


				<div class="infodashboard">
					<div class="pageimg">
						<img src="/resources/images/dashboard.png" class="boximg">
					</div>
					<div class="pageimg">
						<ul>
							<li class="infotitlehead">Dashboard</li>
							<li>View current quarter task(s) status and all tasks</li>
						</ul>
					</div>
				</div>

			<!-- 	<div class="createBoa">
				 <label></label>
				<select id="createIndex">
					<option value="0">Add new</option>
				</select>
				</div> -->
				<div class="clrdiv"></div>
				<div class="head2">
					<p>Chart View</p>
					<p>All Tasks</p>
					<div class="fieldtitlevalue">
							<p> View by </p>&nbsp;<select id="filterIndexStatusId"
								name="filterIndexStatusId" class="fieldinput">
								<option value="Open">All Open</option>
								<option value="Closed">All Closed</option>
								<option value="Today">Due Today</option>
								<option value="Overdue">Overdue</option>
							</select>
						</div>
				</div>

				<div class="maptable" id="est_dashboard"></div>
				<div class="gridtable">
					<!-- style="width:1100px;height:165px;background:#fff;margin-top: 5px;padding: 8px;border:1px solid #ccc;"> -->
					<!-- <div class="textemailtasks">

						
					</div> -->

					<!-- <div class="divTable">
						<div class="divRow">
							<div class="divtitleCell">Business Partner</div>
							<div class="divtitleCell">Opportunity</div>
							<div class="divtitleCell">Activity</div>
							<div class="divtitleCell">Type</div>
							<div class="divtitleCell">Due Date</div>
							<div class="divtitleCell">Assign To</div>
							<div class="divtitleCell">Status</div>
						</div>
						<div id="divAllTasks"></div>
					</div> 
				    <div id="noChart" class="hideall">No data</div>-->
				    <div id="showGridIndex" class="shwworkordergrid">
						<div id="indexgrid" class="workordergrid2">
							<table id="indexList"></table>
							<div id="indexPager"></div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<form id="frmAct" action="/activity" hidden="true"	method="post">
						<input type="hidden" id="actId" name="actId" /> 
						<input type="hidden" id="actStatusid" name="actStatusid" /> <input
							type="submit" id="cmdActID" />
		</form>				
		<form id="frmCreateLead" action="/business" hidden="true"
			method="post">
			<input type="hidden" id="createLead" name="createLead" /> <input
				type="submit" id="cmdCreateLead" />
		</form>
		<form id="frmCreateOpp" action="/project" hidden="true"
			method="post">
			<input type="hidden" id="createOpp" name="createOpp" /> <input
				type="submit" id="cmdCreateOpp" />
		</form>
		<form id="frmCreateAct" action="/activity" hidden="true"
			method="post">
			<input type="hidden" id="createActivity" name="createAct" /> <input
				type="submit" id="cmdCreateOppAct" />
		</form>
		<form id="frmCreateLeadReq" action="/leadrequest" hidden="true"
			method="post">
			<input type="hidden" id="createLeadRequest" name="createLeadReq" /> <input
				type="submit" id="cmdCreateLeadReq" />
		</form>
		<p class="foot">
		<a id="ftrPrivacy" href="javascript:poptastic('/jsp/privacy.jsp')">Version
			1.0</a> <span class="sep">|</span>
		<!-- <a id="ftrTerms" href="javascript:poptastic('/jsp/terms.jsp')">Terms of Service</a>
				<span class="sep">|</span> -->
		<span>&copy; 2013 Zingcrm Management. All rights reserved.</span>
	</p>
	</td>
	</tr>
	</table>
		
	<!-- 	<div id="dlgsMemberSessionMsg" title="Message"></div> -->

	<script type="text/javascript"
		src=/resources/js/jquery-min.js></script>
	<script type="text/javascript"
		src=/resources/js/jquery-ui-1.9.2.custom.min.js></script>
	<script type="text/javascript"
		src=/resources/js/i18n/grid.locale-en.js?t=1.1></script>
	<script type="text/javascript"
		src=/resources/js/jqGrid.js?t=1.1></script>
	<script type="text/javascript"
		src=/resources/js/Estchart.js></script>
	
	<script type="text/javascript">
	var option="<option value=0>Select</option>";
	$('#FEA_INDEX').addClass("active");
	 google.load("visualization", "1", {packages:["corechart"]});
     google.setOnLoadCallback(drawChart);
     function drawChart() {
	var jsonData ;
	$.ajax({
	  	url: '/index/getChartData',
	  	dataType:"json",
	   async: false,
	   success: function (response)    
	           {
	   jsonData=response;   
	       }
	   });
	
	
	 $.getJSON('/index/getAllTaskCreate', function(data) 
				{
						var createList ="";
						$.each(data.result, function(key, val) {
							 if(data.status==='success'){
								 createList += '<option value="' + data.result[key].key + '">'
									+ data.result[key].value + '</option>';
							 }
						});
						$('#createIndex').html("<option value=0>Add New</option>"+createList);
			    });  
				 

	
	var data = new google.visualization.DataTable(jsonData);
	
	       var options = {
	         is3D: true,
	         chartArea:{left:20,top:10,width:"100%",height:"100%"},
	         width:250,
	         backgroundColor: '#E4E4E4',
	         height:250
	         
	        };
	
	       var chart = new google.visualization.PieChart(document.getElementById('est_dashboard'));
	       chart.draw(data, options);
	     }
     
     function getIndexData()
     {
    	 $("#indexList").jqGrid({
 			url:'/index/getAllTasks?status='+escape($('#filterIndexStatusId option:selected').val()),
 			datatype:"xml",
 			colNames:['Business Partners','Project','Activity','Type','Due Date','Assigned To','ID','statusid'],
 			colModel:[
 					
 					{name:'bp',index:'7',width:50,resizable:false},
 					{name:'opp',index:'6',width:50,resizable:false},
 					{name:'activity',index:'1',width:50,resizable:false},
 					{name:'type',index:'2',width:50,resizable:false},
 			        {name:'dueDate',index:'4',width:50,resizable:false},
 			        {name:'assignedTo',index:'3',width:50,resizable:false},
 			       	{name:'id',index:'8',width:50,resizable:false,hidden:true},
 			       	{name:'statusid',index:'9',width:50,resizable:false,hidden:true}
 					],
 		  	
 					
 		    rowNum:20,
 		    width:798,
 		    height:210,
 		   // autowidth: true,
 		    //shrinkToFit : false,
 		    
 		    rowList:[20,40,60,80,100],
 			pager:$('#indexPager'),
 			sortname:'8',
 			viewrecords:true,
 			sortorder:"desc",
 			toppager:false,
 			gridview:true,
 			gridComplete:function(id){
 			
              $("tr.jqgrow:odd").addClass('allAltRowHoverClass');
              $("tr.jqgrow:even").addClass('allAltRowHoverClass');
              $("#indexList").setSelection(0,false);	  
              $('#indexPager_right').addClass('hideall');				
              jQuery("#indexPager .ui-pg-selbox").closest("td").after("<td dir='ltr'>No of rows </td>");
 			},
    	 	ondblClickRow: function(rowid){
    	 		
    	 		var ret = jQuery("#indexList").jqGrid('getRowData',rowid);
    	 		$('#actStatusid').val(ret.statusid);
    	 		$('#actId').val(ret.id);
    	 		$('#cmdActID').trigger('click');
			}
 		}); 
      
     }
     getIndexData();
    
    	 
    	 $('#filterIndexStatusId').change(function()
    	{
    		$("#indexList").setGridParam({url:'/index/getAllTasks?status='+escape($('#filterIndexStatusId option:selected').val()),page:1,rowNum:$("#indexList_toppager_center .ui-pg-selbox").val(),sortname:'8',sortorder:'desc'}).trigger("reloadGrid");
    		 
    	 });
    	 
     
     $('#createIndex').change(function()
     {
    	 if($(this).val()!==0)
   		 {
			if($(this).val()==='createLead')
			{
   			 $('#createLead').val($(this).val());
   			 $('#cmdCreateLead').trigger('click');
			}
			else if($(this).val()==='createOpps')
			{
				 $('#createOpp').val($(this).val());
	   			 $('#cmdCreateOpp').trigger('click');
			}
			else if($(this).val()==='createAct')
			{
				 $('#createActivity').val($(this).val());
	   			 $('#cmdCreateOppAct').trigger('click');
			}
			else if($(this).val()==='createLeadReq')
			{
				 $('#createLeadRequest').val($(this).val());
	   			 $('#cmdCreateLeadReq').trigger('click');
			}
   		 }
     });
     
	
	</script>


</body>
</html>