<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.InputStream"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="robots" content="noindex" />
	<title>Test Rest Service</title>

</head>
<body>

	<textarea rows="10" cols="100" id="jsondata"></textarea><br/>
	<input type="button" id="cmdLogin" value="Device Login" />
	 <input type="button" id="cmdLead" value="lead Details" />
	  <input type="button" id="cmdOpp" value="Opp Details" />
	   <input type="button" id="cmdPwd" value="Change Pwd Details" />
	   	   <input type="button" id="cmdForget" value="Forget Pwd Details" />
	   	    <input type="button" id="cmdBusinessPartner" value="BusinessPartner" />
	   	   <input type="button" id="cmdOpportunity" value="Create Opp" /><br/>\
	   	   
	   	    <input type="button" id="updateAct" value="Update Act" /><br/>	   	    
	   	   
	   	  <input type="button" id="cmdGetActivity" value="ActivityDetails" /> 
	   	   <input type="button" id="cmdDefinedData" value="DefinedData2" /> 
	   	      <input type="button" id="saveAct" value="saveActivity" /> 
	   	       <input type="button" id="getAllTasks" value="All Task" /><br/>
	   	         <input type="button" id="getCurrency" value="CurrencyList" /><br/>
	   	       <input type="file" name="files"/>
	   	        <input type="button" id="getStatus" value="getStatus" /><br/>
	<script type="text/javascript" src=<c:url value="/resources/js/jquery-min.js"/>></script>
	<script type="text/javascript" src=<c:url value="/resources/js/jquery-ui-1.9.2.custom.min.js"/>></script>
	<script type="text/javascript">
			

			$('#cmdLogin').click(function()
			{
				
				var json = {};
				
			/* 	json.userid =  "demosuperadmin@demo.com";
				json.pwd =  "demo1234"; */
				http://192.168.1.56:8080/zing/service/chkValidation
				$.ajax(
				{
					url: "/zing/service/chkValidation",
					contentType: "application/json",
					dataType: 'json',
					type: "POST",
					data: JSON.stringify({'userId':'demosuperadmin@demo.com','password':'demo1234'}),
					cache: false,
					success: function (data)
					{
						
						$('#jsondata').val(JSON.stringify(data));
					}
				});
			});	
			
			
			
			$('#cmdLead').click(function()
					{
						
						var json = {};
						
					/* 	json.userid =  "demosuperadmin@demo.com";
						json.pwd =  "demo1234"; */
						
						$.ajax(
						{
							url: "/zing/service/getLeadDetials",
							contentType: "application/json",
							dataType: 'json',
							type: "POST",
							data: JSON.stringify({'userId':'ca173eca-37e7-4122-9784-385e505ad560','org':'1','roleId':'1'}),
							cache: false,
							success: function (data)
							{
								
								$('#jsondata').val(JSON.stringify(data));
							}
						});
					});	
			
			
			$('#cmdOpp').click(function()
					{
						
						var json = {};
						
					/* 	json.userid =  "demosuperadmin@demo.com";
						json.pwd =  "demo1234"; */
						
						$.ajax(
						{
							url: "/zing/service/getOpportunity",
							contentType: "application/json",
							dataType: 'json',
							type: "POST",
							data: JSON.stringify({'id':'1','roleId':'4','org':'1','userId':'ca173eca-37e7-4122-9784-385e505ad560'}),
							cache: false,
							success: function (data)
							{
								$('#jsondata').val(JSON.stringify(data));
								
								/* $.each(data.result, function(key, val) {
									 if(data.status==='success'){
										alert(data.result[key].OpportunityName);
									 }
								}); */
								
							}
						});
					});	
			
					$('#cmdPwd').click(function()
					{
						
						var json = {};
					/* 	json.userid =  "demosuperadmin@demo.com";
						json.pwd =  "demo1234"; */
						
						$.ajax(
						{
							url: "/zing/service/changePassword",
							contentType: "application/json",
							dataType: 'json',
							type: "POST",
							data: JSON.stringify({'userId':'f15a0100-4040-49e5-8570-b9c3aad13123','currentpassword':'demo12345','password':'demo1234'}),
							cache: false,
							success: function (data)
							{
								
								$('#jsondata').val(JSON.stringify(data));
							}
						});
					});	
			
					
					$('#cmdForget').click(function()
							{
								
								var json = {};
							/* 	json.userid =  "demosuperadmin@demo.com";
								json.pwd =  "demo1234"; */
								
								$.ajax(
								{
									url: "/zing/service/forgetPassword",
									contentType: "application/json",
									dataType: 'json',
									type: "POST",
									data: JSON.stringify({'username':'sara@neshinc.com'}),
									cache: false,
									success: function (data)
									{
										
										$('#jsondata').val(JSON.stringify(data));
									}
								});
							});	
					
					
					$('#cmdBusinessPartner').click(function()
							{
								
								var json = {};
							/* 	json.userid =  "demosuperadmin@demo.com";
								json.pwd =  "demo1234"; */
								
								$.ajax(
								{
									url: "/zing/service/getBusinessPartner",
									contentType: "application/json",
									dataType: 'json',
									type: "POST",
									data: JSON.stringify({'userId':'f15a0100-4040-49e5-8570-b9c3aad13123','org':'1','roleId':'1'}),
									cache: false,
									success: function (data)
									{
										
										$('#jsondata').val(JSON.stringify(data));
									}
								});
							});	
					
					
					
					
					$('#cmdOpportunity').click(function()
							{
								
								var json = {};
							/* 	json.userid =  "demosuperadmin@demo.com";
								json.pwd =  "demo1234"; */
								
								$.ajax(
								{
									url: "/zing/service/createOpportunity",
									contentType: "application/json",
									dataType: 'json',
									type: "POST",
									data: JSON.stringify({'company':'1','opportunity':'aaa','description':'aaa','createdBy':'f15a0100-4040-49e5-8570-b9c3aad13123','org':'1','expectedVal':'236','expectedValue':'22','probability':'1','closeDate':''}),
									cache: false,
									success: function (data)
									{
										
										$('#jsondata').val(JSON.stringify(data));
									}
								});
							});	
					
					
					
					$('#cmdGetActivity').click(function()
							{
								
								var json = {};
							/* 	json.userid =  "demosuperadmin@demo.com";
								json.pwd =  "demo1234"; */
								
								$.ajax(
								{
									url: "/zing/service/getActivity",
									contentType: "application/json",
									dataType: 'json',
									type: "POST",
									data: JSON.stringify({'roleId':'1','userId':'f15a0100-4040-49e5-8570-b9c3aad13da7','org':'1','lead':'21'}),
									cache: false,
									success: function (data)
									{
										
										$('#jsondata').val(JSON.stringify(data));
									}
								});
							});	
					//
					
						$('#cmdDefinedData').click(function()
							{
								
								var json = {};
							/* 	json.userid =  "demosuperadmin@demo.com";
								json.pwd =  "demo1234"; */
								
								$.ajax(
								{
									url: "/zing/service/getDefinedData2",
									contentType: "application/json",
									dataType: 'json',
									type: "POST",
									data: JSON.stringify({'userField1':'1'}),
									cache: false,
									success: function (data)
									{
										
										$('#jsondata').val(JSON.stringify(data));
									}
								});
							});
					
					

						$('#getAllTasks').click(function()
								{
									
									var json = {};
								/* 	json.userid =  "demosuperadmin@demo.com";
									json.pwd =  "demo1234"; */
									
									$.ajax(
									{
										url: "/zing/service/getTasks",
										contentType: "application/json",
										dataType: 'json',
										type: "POST",
										data: JSON.stringify({'roleId':'1','userId':'1','org':'1','activityStatus':'Open'}),
										cache: false,
										success: function (data)
										{
											
											$('#jsondata').val(JSON.stringify(data));
										}
									});
								});	
						
						
						$('#saveAct').click(function()
								{
									
									var json = {};
								/* 	json.userid =  "demosuperadmin@demo.com";
									json.pwd =  "demo1234"; */
									
									$.ajax(
									{
										url: "/zing/service/getActivityImage",
										contentType: "application/json",
										dataType: 'json',
										type: "GET",
										data: JSON.stringify( {"userField1":"1","userField2":"1","assignedTo":"f15a0100-4040-49e5-8570-b9c3aad13da7","createdBy":"f15a0100-4040-49e5-8570-b9c3aad13da7","name":"gh","opportunity":"24","type":"1","desc":"Qwerty","dueDate":"2014-04-04"}
										),
										cache: false,
										success: function (data)
										{
											
											$('#jsondata').val(JSON.stringify(data));
										}
									});
								});	
						
						$('#updateAct').click(function()
								{
									
									var json = {};
								/* 	json.userid =  "demosuperadmin@demo.com";
									json.pwd =  "demo1234"; */
									
									$.ajax(
									{
										url: "/zing/service/getActivityImage",
										type: "GET",
										data: JSON.stringify({"id":"2"}),
										cache: false,
										success: function (data)
										{
											
											$('#jsondata').val(JSON.stringify(data));
										}
									});
								});	
						
						
				/* 	
				 	$('#getAllTasks').click(function()
								{
									
									var json = {};
								/* 	json.userid =  "demosuperadmin@demo.com";
									json.pwd =  "demo1234"; 
									
									$.ajax(
									{
										url: "/zing/service/getDefinedData2",
										contentType: "application/json",
										dataType: 'json',
										type: "POST",
										data: JSON.stringify({'userField1'*/

									/*		userField2

											assignedTo
											createdBy

											name
											opportunity
											type
											desc
											files[0]}),
										cache: false,
										success: function (data)
										{
											
											$('#jsondata').val(JSON.stringify(data));
										}
									});
								}); */
						 
					
								
								$('#getCurrency').click(function()
										{
											
											var json = {};
										/* 	json.userid =  "demosuperadmin@demo.com";
											json.pwd =  "demo1234"; */
											
											$.ajax(
											{
												url: "/zing/service/currencyList",
												contentType: "application/json",
												dataType: 'json',
												data: JSON.stringify({"id":"2"}),
												type: "POST",
												cache: false,
												success: function (data)
												{
													
													$('#jsondata').val(JSON.stringify(data));
												}
											});
										});	
								
								

								$('#getStatus').click(function()
										{
											
											var json = {};
										/* 	json.userid =  "demosuperadmin@demo.com";
											json.pwd =  "demo1234"; */
											
											$.ajax(
											{
												url: "/zing/service/getStatus",
												contentType: "application/json",
												dataType: 'json',
												data:JSON.stringify({"createdBy":"f15a0100-4040-49e5-8570-b9c3aad13da7","userId":"f15a0100-4040-49e5-8570-b9c3aad13da7"}),
												cache: false,
												type: "POST",
												success: function (data)
												{
													
													$('#jsondata').val(JSON.stringify(data));
												}
											});
										});	
								
							
								
								
			    
			
			
	</script>
</body>
</html>
