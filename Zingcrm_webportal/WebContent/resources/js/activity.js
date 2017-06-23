$(document).ready(function()
{
	
	var arr=new Array();
	var newCount=0;
	var optionsAll="<option value=%>All</option>";
	var options="<option value=0>Select</option>";
	var editstatusval='', createstatusval='';
	if($.trim($('#Editstatus').val())!=='')
	{
		editstatusval=$('#Editstatus').val();
	}
	if($.trim($('#Createstatus').val())!=='')
	{
		createstatusval=$('#Createstatus').val();
	}
	
$(function()
{
	var generalRole="6";
	var supportRole="5";
	var currentuser="true";
	var activityImageid="";
	
	 $.extend($.ui.dialog.prototype.options, {
			modal : true,
			bgiframe : true,
			resizable : false,
			autoOpen : false
		});
	 
	$('#dlgActivityMsg').dialog({
		buttons : {
			"Ok" : function() {
				$('#dlgActivityMsg').dialog('close');
			}
		}
	});
	
	$('#dlgActivitySuccessMsg').dialog({
		buttons : {
			"Ok" : function() {
				window.location.href='/activity';
				$('#dlgActivitySuccessMsg').dialog('close');
			}
		}
	});
	function activitystatus(createstatusval){
		 if(createstatusval==='success')
		  {
   		  $('#dlgActivitySuccessMsg').html('Activity created successfully').dialog('open');
   		  $('#newBackActivity').trigger('click');
		  }
   	  else if($.trim(createstatusval)==='exists')
		  {
   		  errordisplay("Activity name already exists",$(this).attr('id'));
   		  $('#dlgActivitySuccessMsg').html('Activity name already exists.').dialog('open');
		  }

   	  else
		  {
   		  $('#dlgActivitySuccessMsg').html('Error creating activity. Please try again later.').dialog('open');
		  }
	}
	
	function editactivitystatus(editstatusval){
		   $('#editSubmitActivity').attr('disabled',false);
     	  if(editstatusval==='success')
 		  {
     		  $('#dlgActivityMsg').html('Activity updated successfully').dialog('open');
     		 $('#allActivity').addClass('hideall');
     		 getEditActivity($('#editActId').val());
 		  }
     	  else if($.trim(editstatusval)==='exists')
 		  {
     		  errordisplay("Activity name already exists",$(this).attr('id'));
     		  $('#dlgActivitySuccessMsg').html('Activity name already exists.').dialog('open');
 		  }
     	  else if($.trim(editstatusval)==='accessdenied')
 		  {
     		  $('#dlgActivitySuccessMsg').html("Only creator can close the task.").dialog('open');
 		  }
     	  else 
 		  {
     		  $('#dlgActivitySuccessMsg').html('Error updating activity. Please try again later.').dialog('open');
 		  }
	}
	
	if(createstatusval!='null' && createstatusval!=='' ){
		activitystatus(createstatusval);
		}
	
	if(editstatusval!='null' && editstatusval!==''){
		editactivitystatus(editstatusval);
		}
	
	
	if($.trim($('#actId').val())!=='')
	{
		$('#allActivity').addClass('hideall');
	}
	else if($.trim($('#createAct').val())==='createAct')
	{
		$('#allActivity').addClass('hideall');
		
	}
	else if($('#editActId').val()!=='')
		{
		$('#allActivity').addClass('hideall');
		}
	else 
	{
		$('#allActivity').removeClass('hideall');
	}
	
	
	 
	   
	   
	   
	   $('#dlgDelActivityMsg').dialog({
			buttons : {
				"Yes" : function() {
					$('#cmdDeleteActivity').attr('disabled',true);
					$
					.ajax({
						url : '/activity/deleteActivity',
						type : 'POST',
						data : 'activityId='+$.trim(cell.aid),
						success : function(data) {
							$('#cmdDeleteActivity').attr('disabled',false);
							if($.trim(data)=='success')
							{
								$("#activityList").trigger(
												'reloadGrid');
								 $('#dlgActivityMsg').html('Activity deleted successfully.').dialog('open');
								
							}	
							else
							{
								 $('#dlgActivityMsg').html('Error deleting Activity. Please try again later.').dialog('open');
							}
						 $('#dlgDelActivityMsg').dialog('close');
						}
					});
				},
				"No" : function() {
					$('#dlgDelActivityMsg').dialog('close');
				}
			}
		}); 
	   
/*	   
	   $('#dlgRemoveActivityMsg').dialog({
			buttons : {
				"Yes" : function() {*/
					/*$
					.ajax({
						url : '/activity/removeAttachment',
						type : 'POST',
						data : 'activityId='+$.trim($('#activityId').val()),
						success : function(data) {
							if($.trim(data)=='success')
							{
								$('#editFileUpload').addClass('hideall');
								$('#editNewUploading').removeClass('hideall');
								$('#editNewcancelLink').addClass('hideall');
								 $('#editfileName').val('');
								$('#dlgActivityMsg').html('Attached removed successfully.').dialog('open');
								 $('div#editNewcancelLink').css('display','none');
	 			 				 $('div.qq-upload-button').css('display','block');
							}	
							else
							{
								 $('#dlgActivityMsg').html('Error removing attachment. Please try again later.').dialog('open');
	 			 				 $('div.qq-upload-button').css('display','none');
	 			 				 $('div#editNewcancelLink').css('display','block');
							}
						 $('#dlgRemoveActivityMsg').dialog('close');
						}
					});*/
		/*			$('#editFileUpload').addClass('hideall');
					$('#editNewUploading').removeClass('hideall');
					$('#editNewcancelLink').addClass('hideall');
					$('#editfileName').val('');
					$('div#editNewcancelLink').css('display','none');
					$('div.qq-upload-button').css('display','block');
					$(this).dialog('close');
				},
				"No" : function() {
					$('#dlgRemoveActivityMsg').dialog('close');
				}
			}
		}); */
	
		
		$('#dlgRemoveActivityMsg').dialog({
			buttons : {
				"Yes" : function() {
					$
					.ajax({
						url : '/activity/removeAttachment',
						type : 'POST',
						data : 'activityId='+activityImageid,
						success : function(data) {
							if($.trim(data)=='success')
							{
								$('#documentsize').val(parseInt($('#documentsize').val())-1);
								if($('#documentsize').val()<10)
								{
									$('#editFileUploaderPart').removeClass('hideall');
								}	
								else
									{
									$('#editFileUploaderPart').addClass('hideall');
									}
								/*$('#editFileUpload').addClass('hideall');
								$('#editNewUploading').removeClass('hideall');
								$('#editNewcancelLink').addClass('hideall');
								 $('#editfileName').val('');*/
								$('#editRemove'+$.trim(activityImageid)).addClass('hideall');
								$('#editfile'+activityImageid).addClass('hideall');
							//	$('#editFileImage').addClass('hideall');
								/*if($('div#editfileuploader ul.qq-upload-list').find('p:not(.hideall)').length>0)
		 			 			{ 
		 			 				$('#dlgActivityMsg').html('Can upload only ten file.').dialog("open");
		 			 				$('#filebrowse').addClass('hideall');
		 			 				$('div#editfileuploader ul.qq-upload-list').append('<li class=hideall>a</li>');
		 			 				$('div.qq-upload-button').css('display','none');
		 			 				$('div#editNewcancelLink').css('display','block');
		 			 				return false;
		 			 				
		 			 			} */
								$('#dlgActivityMsg').html('Attachment removed successfully.').dialog('open');
								//getEditFileUploader();
							}	
							else
							{
								 $('#dlgActivityMsg').html('Error removing attachment. Please try again later.').dialog('open');
							}
						 $('#dlgRemoveActivityMsg').dialog('close');
						}
					});
				},
				"No" : function() {
					$('#dlgRemoveActivityMsg').dialog('close');
				}
			}
		}); 
	
	$('#tabs').fadeIn(10);
	$( "#tabs" ).tabs();	
	
	function errordisplay(errorval,id){
		 if(errorval!=''){
			 $('#'+id).addClass("ErrorField");
			/* $("#newLeadRequestError").css('display','block');
			 $("#newLeadRequestError").val(errorval);*/
		 }
		 else if(errorval=='Email already exists'){
			 $('#'+id).addClass("ErrorField");
			 /*$("#newLeadRequestError").css('display','block');
			 $("#newLeadRequestError").val(errorval);*/
		 }
		 else if(errorval=='Email is not valid format'){
			 $('#'+id).addClass("ErrorField");
			 /*$("#newLeadRequestError").css('display','block');
			 $("#newLeadRequestError").val(errorval);*/
		 }
		 else{
			 $('#'+id).removeClass("ErrorField");
			/* $("#newLeadRequestError").css('display','none');
			 $("#newLeadRequestError").val(errorval);*/
		 }
	 }
	
	 $.getJSON('/activity/orglist', function(data) 
	{
			var orgList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 orgList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			$('#filterActivityOrgId').html(orgList).trigger('change');
			$('#newActivityOrg').html(orgList);
			$('#viewActivityOrg').html(orgList);
			$('#editActivityOrg').html(orgList);
			
    });  
	 
	 

	 
	 
	 
	 
	 function getLeadOpp(orgId,LeadId,oppId,assignedTo,statusId,userId,typeId)
	 {

		 
		 $.getJSON('/activity/activityType?orgId='+escape(orgId), function(data) 
		{
				var typeList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 typeList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#editActivityType').html(options+typeList);
				$('#editActivityType').val(typeId);

				
		});
					 
		 
		 
		 $.getJSON('/activity/companylist?orgId='+escape(orgId)+'&flag=edit', function(data) 
		{
				var orgList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 orgList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#editActivityLead').html(options+orgList);
				$('#editActivityLead').val(LeadId);
				
				$.getJSON('/activity/opportunitylist?leadId='+escape(LeadId), function(data) 
				{
						var companyList ="";
						$.each(data.result, function(key, val) {
							 if(data.status==='success'){
								 companyList += '<option value="' + data.result[key].key + '">'
									+ data.result[key].value + '</option>';
							 }
						});
						$('#editActivityOpp').html(optionsAll+companyList);
						$('#editActivityOpp').val(oppId);
				});
				
				 $.getJSON('/activity/assignedTo?orgId='+escape(orgId)+'&companyId='+escape(LeadId), function(datas) 
							{
									var assignedList ="";
									$.each(datas.result, function(key, val) {
										 if(datas.status==='success'){
											 assignedList += '<option value="' + datas.result[key].key + '">'
												+ datas.result[key].value + '</option>';
										 }
									});
									$('#editActivityAssigned').html(options+assignedList).trigger('change');
									$('#editActivityAssigned').val(assignedTo);
							}); 
				 
		});
		 
		
		 
		 	$.getJSON('/activity/status?userId='+escape(userId), function(data) 
			{
				var statusList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 statusList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#editActivityStatus').html(statusList);
				$('#editActivityStatus').val(statusId);
				if(parseInt($.trim(statusId))===parseInt(3))
				{
					setTimeout(function(){
						$('#editActivityfrm').find('input,textarea,select').attr('disabled',true);
						$('#editSubmitActivity').css('display','none');
					  $('#editCancelActivity').attr('disabled',false);
					  $('#editSubmitActivity').attr('disabled',false);	
					 $('.Removebutton1').addClass('hideall');
					},200);
					//clearTimeout(myVar);
					
				}
				else
					{
					$('#editActivityfrm').find('input,textarea,select').attr('disabled',false);
					$('#editSubmitActivity').css('display','block');
					$('#editCancelActivity').attr('disabled',false);
					$('#editSubmitActivity').attr('disabled',false);
					 $('.Removebutton1').removeClass('hideall');
				}
			
				
			});  
	 }
	 
	 
	 $('#editActivityLead').change(function()
				{
					 if($(this).val()!=='%')
					{
							 $.getJSON('/activity/opportunitylist?leadId='+escape($('#editActivityLead option:selected').val()), function(data) 
							{
									var orgList ="";
									$.each(data.result, function(key, val) {
										 if(data.status==='success'){
											 orgList += '<option value="' + data.result[key].key + '">'
												+ data.result[key].value + '</option>';
										 }
									});
									$('#editActivityOpp').html(options+orgList);
							});
					}
					 else
					 {
						$('#editActivityOpp').html(options);
					 }
					 
				});
	 $('#newActivityOrg').change(function()
		{
			 if($(this).val()!=='0')
			{
					 $.getJSON('/activity/companylist?orgId='+escape($('#newActivityOrg option:selected').val())+'&flag=new', function(data) 
					{
							var orgList ="";
							$.each(data.result, function(key, val) {
								 if(data.status==='success'){
									 orgList += '<option value="' + data.result[key].key + '">'
										+ data.result[key].value + '</option>';
								 }
							});
							$('#newActivityLead').html(options+orgList).trigger('change');
							
							$('#newActivityLead').val($('#leadId').val());
							if($.trim($('#actOppLead').val())!=='')
							{
								
								$("#newActivityLead option[value="+$.trim($('#actOppLead').val())+"]").attr("selected","selected");
							}
							$('#newActivityLead').trigger('change');
					});
					 
					 $.getJSON('/activity/activityType?orgId='+escape($('#newActivityOrg option:selected').val()), function(data) 
					{
							var typeList ="";
							$.each(data.result, function(key, val) {
								 if(data.status==='success'){
									 typeList += '<option value="' + data.result[key].key + '">'
										+ data.result[key].value + '</option>';
								 }
							});
							$('#newActivityType').html(options+typeList);
					});
					 
					
			}
			 else
			 {
				$('#newActivityLead').html(options);
				$('#newActivityAssigned').html(options);
				 }
			});
 
	 $('#filterActivityOrgId').change(function()
	{
		 if($(this).val()!=='%')
		{
				 $.getJSON('/activity/companylist?orgId='+escape($('#filterActivityOrgId option:selected').val()), function(data) 
				{
						var orgList ="";
						$.each(data.result, function(key, val) {
							 if(data.status==='success'){
								 orgList += '<option value="' + data.result[key].key + '">'
									+ data.result[key].value + '</option>';
							 }
						});
						$('#filterActivityLeadId').html(optionsAll+orgList);
						
						if($.trim($('#actOppLead').val())!=='')
						{
							
							$("#filterActivityLeadId option[value="+$.trim($('#actOppLead').val())+"]").attr("selected","selected");
						}
						$('#filterActivityLeadId').trigger('change');
				});
		}
		 else
		 {
			$('#filterActivityLeadId').html(optionsAll);
		 }
	});
	 
	 
	 $('#newActivityLead').change(function()
				{
					 if($(this).val()!=='0')
					{
						 if($.trim($('#actOppLead').val())!=='0' && $.trim($('#actOppLead').val())!==''){
							 $('#newActivityLead').val($.trim($('#actOppLead').val()));
						 }
							 $.getJSON('/activity/createopportunitylist?leadId='+escape($('#newActivityLead option:selected').val()), function(data) 
							{
									var orgList ="";
									$.each(data.result, function(key, val) {
										 if(data.status==='success'){
											 orgList += '<option value="' + data.result[key].key + '">'
												+ data.result[key].value + '</option>';
										 }
									});
									$('#newActivityOpp').html(options+orgList);
									$('#newActivityOpp').val($.trim($('#oppId').val()));
									if($.trim($('#actOppOpp').val())!=='')
									{
										$("#newActivityOpp option[value="+$.trim($('#actOppOpp').val())+"]").attr("selected","selected").attr('disabled',true);
									}
									
							});
							 
							 $.getJSON('/activity/newActassignedTo?orgId='+escape($('#newActivityOrg option:selected').val())+'&companyId='+escape($('#newActivityLead option:selected').val()+'&newact=newact'), function(data) 
										{
												var orgList ="";
												$.each(data.result, function(key, val) {
													 if(data.status==='success'){
														 orgList += '<option value="' + data.result[key].key + '">'
															+ data.result[key].value + '</option>';
													 }
												});
												$('#newActivityAssigned').html(options+orgList).trigger('change');
										}); 
					}
					 else
					 {
						$('#newActivityOpp').html(options);
					 }
					 
					 
						 
				});
	 
	 
	 
	 $('#filterActivityLeadId').change(function()
	{
	
			 if($(this).val()!=='%')
				{
						 $.getJSON('/activity/opportunitylist?leadId='+escape($('#filterActivityLeadId option:selected').val()), function(data) 
						{
								var orgList ="";
								$.each(data.result, function(key, val) {
									 if(data.status==='success'){
										 orgList += '<option value="' + data.result[key].key + '">'
											+ data.result[key].value + '</option>';
									 }
								});
								$('#filterActivityOppId').html(optionsAll+orgList);
								
								if($.trim($('#actOppOpp').val())!=='')
								{
									$("#filterActivityOppId option[value="+$.trim($('#actOppOpp').val())+"]").attr("selected","selected");
								}
								$('#activityList').jqGrid('GridUnload');
								ActivityGrid();
						});
				}
				 else
				 {
					$('#filterActivityOppId').html(optionsAll);
					$('#activityList').jqGrid('GridUnload');
					ActivityGrid();
				 }
		
		
		 
	});
	 
	   function errordisplay(errorval,id){
			 if(errorval!=''){
				 $('#'+id).addClass("ErrorField");
				/* $("#newLeadRequestError").css('display','block');
				 $("#newLeadRequestError").val(errorval);*/
			 }
			 else if(errorval=='Email already exists'){
				 $('#'+id).addClass("ErrorField");
				 /*$("#newLeadRequestError").css('display','block');
				 $("#newLeadRequestError").val(errorval);*/
			 }
			 else if(errorval=='Email is not valid format'){
				 $('#'+id).addClass("ErrorField");
				 /*$("#newLeadRequestError").css('display','block');
				 $("#newLeadRequestError").val(errorval);*/
			 }
			 else{
				 $('#'+id).removeClass("ErrorField");
				/* $("#newLeadRequestError").css('display','none');
				 $("#newLeadRequestError").val(errorval);*/
			 }
		 }
	 
	 $("#newActivityDate").datepicker({
		 
		 
		   changeMonth: true,
			changeYear: true,
			 yearRange: "-100:+0",
			showAnim:'',dateFormat:'yy-mm-dd',
			//limitToToday:true,
			onChangeMonthYear: function (year,month,inst)
			{
				$("#newActivityDate").trigger('inst');
				$("#newActivityDate").datepicker("setDate",year+"-"+(month)+"-01");
			},
			onClose:function(dateText,inst)
			{
				$("#newActivityDate").trigger('blur');
				if($("#newActivityDate").val()==='')
				{
					$("#newActivityDate"+'Error').removeClass('hideall');  
				}
				else
					{
					$("#newActivityDate"+'Error').addClass('hideall');  
					}
			}	
	 });
	 
	 $("#editActivityDate").datepicker({
		 
		 
		   changeMonth: true,
			changeYear: true,
			 yearRange: "-100:+0",
			showAnim:'',dateFormat:'yy-mm-dd',
			//limitToToday:true,
			onChangeMonthYear: function (year,month,inst)
			{
				$("#editActivityDate").trigger('inst');
				$("#editActivityDate").datepicker("setDate",year+"-"+(month)+"-01");
			},
			onClose:function(dateText,inst)
			{
				$("#editActivityDate").trigger('blur');
				if($("#editActivityDate").val()==='')
				{
					$("#editActivityDate"+'Error').removeClass('hideall');  
				}
				else
					{
					$("#editActivityDate"+'Error').addClass('hideall');  
					}
			}	
	 });
				 
	 /*	$('#newActivityOpp').change(function()
	 	{
	 		$('#newActivity').trigger('blur');
	 	});*/
	 	
		$('#editActivityOpp').change(function()
			 	{
			 		$('#editActivityName').trigger('blur');
			 	});
	
	
 			$('#newActivity').blur(function() // Section name validation
			{
			   if ($.trim($('#newActivity').val().length) != 0) {
					$("#newActivityError").addClass("hideall");
				   $.ajax({
			           url: '/activity/getActivityNameValidation',
			           data: 'oppId='+$.trim($('#newActivityOpp').val())+'&activityName='+$.trim($('#newActivity').val()),
			           success: function(data) {
			          	 if(data==='exists')
			          	{
			          		$('#newActivityValidError').removeClass('hideall');
			          	}
			          	 else
			          	{
			          		$('#newActivityValidError').addClass('hideall');
			          	}
			           }
					});
				} else {
					$("#newActivityError").removeClass("hideall");
				}
			});
 			
 			
 			$('#editActivityName').blur(function() // Section name validation
 					{
 					   if ($.trim($('#editActivityName').val().length) != 0) {
 							$("#newActivityNameError").addClass("hideall");
 						   $.ajax({
 					           url: '/activity/getEditActivityNameValidation',
 					           data: 'oppId='+$.trim($('#editActivityOpp').val())+'&activityName='+$.trim($('#editActivityName').val())+'&activityId='+$('#activityId').val(),
 					           success: function(data) {
 					          	 if(data==='exists')
 					          	{
 					          		$('#editActivityNameValidError').removeClass('hideall');
 					          	}
 					          	 else
 					          	{
 					          		$('#editActivityNameValidError').addClass('hideall');
 					          	}
 					           }
 							});
 						} else {
 							$("#newActivityNameError").removeClass("hideall");
 						}
 					});
	
 			function getFileUploader()
 			{
 				uploader = new qq.FileUploader({
 		        element: document.getElementById('fileuploader'),
 		        action: '/upload/saveExcel',
 		        //Files with following extensions are only allowed
 		      //  allowedExtensions: ['txt','doc','docx','pdf'],
 		        sizeLimit: 31457280, // Maximum filesize limit which works without any problems is 30MB. Current limit is set to 10MB = 10 * 1024 * 1024
 			    multiple:false,
 				onSubmit:function(id,fileName)
 				{
 					
 						if($('div#fileuploader ul.qq-upload-list').find('li:not(.hideall)').length>9)
 			 			{ 
 			 				$('#dlgActivityMsg').html('Can upload only ten file.').dialog("open");
 			 				$('#filebrowse').addClass('hideall');
 			 				$('div#fileuploader ul.qq-upload-list').append('<li class=hideall>a</li>');
 			 				$('div.qq-upload-button').css('display','none');
 			 				$('div#cancelLink').css('display','block');
 			 				return false;
 			 			} 
 					  else{
 						  	$('div.qq-upload-button').css('display','none');
 			 				$('div#cancelLink').css('display','block');
  					  }
 				},
 		         onComplete: function(id, fileName, responseJSON)
 		         {  
 		        	fileName=id+fileName.replace(",","||||");
 		        	arr[newCount]=fileName;
 		        	newCount++;
 		        	/* $('#filesList'
 		        			 +newCount).val(fileName);*/
 	        		 $('#cancelLink').removeClass('hideall');
 		         }
 		    });   
 			}
 			
 			$('#cancelLink').click(function()
				{
					$("div#fileuploader ul.qq-upload-list li").addClass('hideall');	
					$('#filesList').val('');
					$('#cancelLink').addClass('hideall');
				
					 if($('div#fileuploader ul.qq-upload-list ').find('li:not(.hideall)').length>0)
			 			{ 		
							$('div.qq-upload-button').css('display','none');
							 $('div#cancelLink').css('display','block');
			 			}
						 else{
							 $('div#cancelLink').css('display','none');
							 $('div.qq-upload-button').css('display','block');
						 }
				});
 			
 			$('#editNewcancelLink').click(function()
 					{
 						$("div#editfileuploader ul.qq-upload-list li").addClass('hideall');	
 						$('#editfileName').val('');
 						$('#editNewcancelLink').addClass('hideall');
 						 if($('div#editfileuploader ul.qq-upload-list').find('li:not(.hideall)').length>0)
  			 			{ 
 							$('div.qq-upload-button').css('display','none');
 							 $('div#editNewcancelLink').css('display','block');
  			 			}
 						 else{
 							 $('div#editNewcancelLink').css('display','none');
 							 $('div.qq-upload-button').css('display','block');
 	 						
 						 }
 					});
 	 			
 			
 			function getEditFileUploader()
 			{
 				uploader = new qq.FileUploader({
 		        element: document.getElementById('editfileuploader'),
 		        action: '/upload/saveExcel',
 		        //Files with following extensions are only allowed
 		      // allowedExtensions: ['txt','doc','docx','pdf','ppt','pptx','xls','xlsx','rtf','jpg','png','gif','jpeg'],
 		       sizeLimit: 31457280, // Maximum filesize limit which works without any problems is 30MB. Current limit is set to 10MB = 10 * 1024 * 1024
 		        params: {
 				},
 			    multiple:false,
 				onSubmit:function(id,fileName)
 				{
 					 $('#documentsize').val(parseInt($('#documentsize').val())+1);
 					  if($('#documentsize').val()>10)
 			 			{ 
 						  $('#documentsize').val(parseInt($('#documentsize').val())-1);
 			 				$('#dlgActivityMsg').html('Can upload only ten file.').dialog("open");
 			 				$('#filebrowse').addClass('hideall');
 			 				$('div#editfileuploader ul.qq-upload-list').append('<li class=hideall>a</li>');
 			 				$('div.qq-upload-button').css('display','none');
 			 				$('div#editNewcancelLink').css('display','block');
 			 				return false;
 			 				
 			 			} 
 					 /* else{
 						    $('div.qq-upload-button').css('display','none');
			 				$('div#editNewcancelLink').css('display','block');
 					  }*/
 					
 					
 				},
 		         onComplete: function(id, fileName, responseJSON)
 		         {  
 		        	fileName=id+fileName.replace(",","||||");
 		        	
 		        	arr[newCount]=fileName;
 		        	newCount++;
 	        		$('#editNewcancelLink').removeClass('hideall');
 		         }
 		    });   
 			}
		$('#SearchActivitybutton').click(function()	//Filter Click Function
		{
			
			$("#activityList")
			.setGridParam(
					{
							url:'/activity/gridview?orgId='+escape($("#filterActivityOrgId").val())+'&leadId='+escape($('#filterActivityLeadId option:selected').val())+'&oppId='+escape($('#filterActivityOppId option:selected').val()),
						page : 1,
						rowNum : $(
								"#activityPager_center .ui-pg-selbox")
								.val(),
						sortname : '1',
						sortorder : 'desc'
					}).trigger("reloadGrid");
		}); 
		
		 function reload(result)
			{
				if($.trim(result.responseText)==='success')
				{
					 $('#dlgActivityMsg').html('Status has been updated Successfully.').dialog('open');
					 $("#activityList").trigger("reloadGrid");
				}
				else if($.trim(result.responseText)==='accessdenied')
				{
					$('#dlgActivityMsg').html('Only creator can close the task.').dialog('open');
					$("#activityList").trigger("reloadGrid");
				}
				else
				{
					$('#dlgActivityMsg').html('Error updating status. Please try again later.').dialog('open');
				}
				 
			} 
			 

	function ActivityGrid()
	{	
		$('#allActivity').addClass('hideall');
		$("#activityList").jqGrid({
  			url:'/activity/gridview?orgId='+escape($("#filterActivityOrgId").val())+'&leadId='+escape($('#filterActivityLeadId option:selected').val())+'&oppId='+escape($('#filterActivityOppId option:selected').val()),
  			datatype:"xml",
  			colNames:['Id','Business Partner','Project','Activity','Activity Type','Assigned To','Due Date','Attachment','Status','Action','Assign To','Opp Id','Lead','CreatedBy','CurUser'],
 			colModel:[
					
					{name:'aid',index:'1',width:90,resizable:true,hidden:true,editable:true},
					{name:'bp',index:'12',width:200,resizable:false},
					{name:'opp',index:'2',width:200,resizable:false},
					{name:'activity',index:'3',width:200,resizable:false},
					{name:'type',index:'4',width:120,resizable:false},
					{name:'assignedto',index:'5',width:160,resizable:false},
					{name:'duedate',index:'6',width:140,resizable:false},
					{name:'attachment',index:'7',width:100,resizable:false},
					{name:'status',index:'7',width:100,resizable:true,editable:true,edittype:"select", editoptions: {value:{"0":"Select"}}},
					{"name":"actions","formatter":"actions","editable":false,"bSubmit":"Accept","sortable":false,"resizable":false,"fixed":true,"bSubmit":"Accept","width":120, "formatoptions":{"keys":true, delbutton:false,onSuccess:false,extraparam: {oper:'edit'}, afterSave: function(rowid,c){reload(c);}}},
					{name:'userid',index:'9',width:170,resizable:false,hidden:true,editable:true},
					{name:'oppId',index:'10',width:170,resizable:false,hidden:true},
					{name:'leadId',index:'11',width:170,resizable:false,hidden:true},
					{name:'createdBy',index:'12',width:170,resizable:false,hidden:true,editable:true},
					{name:'curUser',index:'13',width:170,resizable:false,hidden:true,editable:true},
					
			],
			
  		    rowNum:20,
  		    rowList:[25,50,100],
  		    width:1114,
  		    height:300,
  		    pager:$('#activityPager'),
			sortname:'1',
			viewrecords:true,
			"editurl":"/activity/statusUpdate",
			sortorder:"desc",
  			toppager:false,
			gridview:true,
			gridComplete:function(id){
  				
  				$('.imgAttach').click(function()
  				{
  					$(this).attr('disabled',true);
  				});
  				 $.getJSON('/activity/status', function(data) {
					if(data.status==='success')
						{
	                        var roleList='';
	                        $.each(data.result, function(key, val) {
	                        	roleList +=data.result[key].key + ":"+ data.result[key].value+";";
	                        });
	                        if(roleList!="")
                     	{
	                        	roleList=roleList.substring(0,roleList.length-1);
                     	}
	                        $("#activityList").setColProp('status',{editoptions:{value:roleList}}); 
							//$("#activityList").setSelection(1,true);
						}
                 });
  				 var actstatus;
  				for(var i=0;i<parseInt($('#activityList').getGridParam('records'));++i)
				{
					var ret = jQuery("#activityList").jqGrid('getRowData',i+1);
			    	 if (ret.status==='Closed')
			         {
			    		 $("#"+parseInt(i+1)).find('span').css('display','none');
			    		 
			         }
			        else
			         { 
			        	 $("#"+parseInt(i+1)).find('span').css('display','block');
			         }
			    	 if(ret.aid==$.trim($('#actId').val())){
			    		 actstatus= ret.status;
			    	 }
			    	 
				}
                $("tr.jqgrow:odd").addClass('allAltRowHoverClass');
                $("tr.jqgrow:even").addClass('allAltRowHoverClass');
                $("#activityList").setSelection(0,false);	  
                $("#showActivityDataButtons").html($("#activityList").getGridParam('userData').operation);
                $('#activityPager_right').addClass('hideall');				
                jQuery("#activityPager .ui-pg-selbox").closest("td").after("<td dir='ltr'>No of rows </td>");
                	renderActivityApprovalHTML();
                
                	if($.trim($('#editActId').val())===null || $.trim($('#editActId').val())==='null')
        			{
                		$('#allActivity').removeClass('hideall');
        			}
                	else if($.trim($('#editActId').val())==='')
        			{
                		$('#allActivity').removeClass('hideall');
        			}
                	 
                	else if($.trim($('#editActId').val())!=='')
                	{
                		$('#allActivity').addClass('hideall');
                	}
                	if($.trim($('#actId').val())!=='' && $.trim($('#actStatusid').val())!='')
                	{
                		
                		if($.trim($('#actStatusid').val())==='3'){
                			$('#allActivity').addClass('hideall');
                			getViewDetail($.trim($('#actId').val()));
                    		$('#actId').val('');
                		}
                		 
                		else{
                			$('#allActivity').addClass('hideall');
                    		getEditActivity($('#actId').val());
                    		$('#actId').val('');
                		}
                		
                	}
                	
                	else if($.trim($('#createAct').val())==='createAct')
                	{
                		$('#allActivity').addClass('hideall');
                		$('#cmdCreateActivity').trigger('click');
                		$('#createAct').val('');
                	}
  			}, 
  			ondblClickRow: function(rowid){
  				$('#cmdEditActivity').trigger('click');
  			
  			}
  		}); 
	}
	function getAllSelectOptions(){
		 var states = { '1': 'Alabama', '2': 'California', '3': 'Florida', 
		               '4': 'Hawaii', '5': 'London', '6': 'Oxford' };
		 return states;

	}
	function generateUserField(userField1,userField2,required)
	{
		   if(required)
	        {
			   userField1=userField1+"<font color=red>*</font>";
			   userField2=userField2+"<font color=red>*</font>";
	       }
		   
		var strData="";
			 strData='<div class="headleft">'+
	       '<p class="contlbl">'+
	       userField1
	        +'</p>'+
	        '<p class="continput" id="data1">'+
	    	 '<select path="userField1" type="text"  id="userField1"  name="userField1" maxlength="50" class="fieldinputac" ><option value="0">Select</option></select><br/></p>'+
	    	 '<p id="userField1Error" class="hideall errorMsg">This information is required<p>'+
	    	 '</p>'+
	        '</div>';
			
			strData+='<div class="headright">'+
		      '  <p class="contlbl">'+
		      userField2
		        +'</p>'+
		        '<p class="continput"  id="data2">'+
		        '<select path="userField2" class="fieldinputac"  id="userField2"  name="userField2"><option value="0">Select</option></select><br/></p>'+
		        '<p id="userField2Error" class="hideall errorMsg">This information is required<p>'+
		        '</p>'+
		      '</div>';
			strData+='<input class="hideall" type="checkbox"  id="contCreateRequiredCheck"/>';
		$('#createUserActivity').html(strData); 
		$('#contCreateRequiredCheck').attr('checked',required);
		/*$('#datadefined1').click(function()
		{
			$('#data1').addClass('hideall');
			$('#dataText1').removeClass('hideall');
			$('#userFieldText1').focus();
			 $("#userField1 option:first-child").attr("selected","selected");
		});
		
		$('#datadefinedCancel1').click(function()
				{
			$('#dataText1').addClass('hideall');
					$('#data1').removeClass('hideall');
					
				});*/
	/*	$('#datadefined2').click(function()
		{
			$('#data2').addClass('hideall');
			$('#dataText2').removeClass('hideall');
			$('#userFieldText2').focus();
			$("#userField2 option:first-child").attr("selected","selected");
		});
				
				$('#datadefinedCancel2').click(function()
						{
						$('#dataText2').addClass('hideall');
							$('#data2').removeClass('hideall');
							
						});*/
		
		$.getJSON('/activity/definedData', function(data) 
				{
			var dataList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 dataList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			$('#userField1').html(options+dataList);
			$('#userField1').trigger('change');
				});  	
	 
	 $('#userField1').change(function()
	  {
		 if($(this).val()!=='0')
			 {
				errordisplay("",$('#userField1').attr('id'));
				errordisplay("",$('#userField2').attr('id'));
			// $('#userField1Error').addClass('hideall');
			// $('#userField2Error').addClass('hideall');
				 $.getJSON('/activity/definedData2?data='+$.trim($('#userField1 option:selected').val()), function(data) 
				{
					 var dataList2 ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 dataList2 += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#userField2').html(options+dataList2);
				});
			 }
		 else{
			 $('#userField2').html(options);
		 }
	   });
	 
	 $('#userField2').change(function()
			  {
				 if($(this).val()!=='0')
					 {
					 errordisplay("",$('#userField2').attr('id'));
					// $('#userField2Error').addClass('hideall');
					 }
			   });
	}
	
	
	function generateViewUserField(data1,data2,required,column1,column2)
	{
		var strData="";
			 strData='<div class="headleft">'+
	       '<p class="contlbl">'+
	       data1
	        +'</p>'+
	        '<p class="continput">'+
	    	 '<select path="userField1" type="text"  id="viewUserField1"  maxlength="50" class="fieldinputac"  disabled="true"><option value="0">Select</option></select>'+
	        '</p>'+
	        '</div>';
			strData+='<div class="headright">'+
		      '  <p class="contlbl">'+
		      data2
		        +'</p>'+
		        '<p class="continput">'+
		        '<select path="userField2" class="fieldinputac"  id="viewUserField2"  name="viewUserField2"  disabled="true"><option value="0">Select</option></select>'+
		        '</p>'+
		      '</div>'; 
	   
		$('#viewUserActivity').html(strData);
		$.getJSON('/activity/definedData', function(data) 
				{
			var dataList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 dataList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			$('#viewUserField1').html(options+dataList);
			$('#viewUserField1').val($.trim(column1));
			$('#viewUserField1').trigger('change');
				});  	
	 
		$('#viewUserField1').change(function()
			 {
			if($(this).val()!=='0')
				{
					 $.getJSON('/activity/definedData2?data='+$.trim(column1), function(data) 
					{
						 var dataList2 ="";
						$.each(data.result, function(key, val) {
							 if(data.status==='success'){
								 dataList2 += '<option value="' + data.result[key].key + '">'
									+ data.result[key].value + '</option>';
							 }
						});
						$('#viewUserField2').html(dataList2+options);
						$('#viewUserField2').val(column2);
					});
				}
			else
				{
				$('#viewUserField2').html(options);
				}
			 });
	}
	
	function generateEditUserField(userField1,userField2,required,column1,column2,RoleId,defaultOpp)
	{
		
		if(required)
        {
		   userField1=userField1+"<font color=red>*</font>";
		   userField2=userField2+"<font color=red>*</font>";
       }
		
			 strData='<div class="headleft">'+
	       '<p class="contlbl">'+
	       userField1
	        +'</p>'+
	        '<p class="continput" id="editdata1">'+
	    	 '<select path="userField1" type="text"  id="editUserField1" name="userField1"  maxlength="50" class="fieldinputac">'+
	        '<option value=0>Select</option>'+
	        '</select><br/>'+
	        '<p id="editUserField1Error" class="hideall errorMsg">This information is required<p>'+
	        '</p>'+
	    	// '<p class="continput hideall" id="editdataText1"><input path="userFieldText1" type="text"  id="edituserFieldText1"  name="userFieldText1" maxlength="50" class="fieldinputac" ></input><br/><a id="editdatadefinedCancel1" class="addData">Cancel</a>'+
	    	 '</p>'+
	        '</div>';
			strData+='<div class="headright">'+
		      '  <p class="contlbl">'+
		      userField2
		        +'</p>'+
		        '<p class="continput" id="editdata2">'+
		        '<select path="userField2" class="fieldinputac"  id="editUserField2"  name="userField2">'+
		        '<option value=0>Select</option>'+
		        '</select><br/>'+
		        '<p id="editUserField2Error" class="hideall errorMsg">This information is required<p>'+
		        '</p>'+
		      '</div>'; 
			strData+='<input class="hideall" type="checkbox"  id="contEditRequiredCheck"/>';
		$('#editUserActivity').html(strData);
		$('#contEditRequiredCheck').attr('checked',required);
						
		$.getJSON('/activity/definedData', function(data) 
				{
			var dataList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 dataList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			$('#editUserField1').html(options+dataList);
			$('#editUserField1').val(column1);
			$('#editUserField1').trigger('change');
				});  	
	 
		$('#editUserField1').change(function()
			 {
			
			if($(this).val()!=='0')
				{
				errordisplay("",$('#editUserField1').attr('id'));
				errordisplay("",$('#editUserField2').attr('id'));
				//$('#editUserField1Error').addClass('hideall');
				//$('#editUserField2Error').addClass('hideall');
					 $.getJSON('/activity/definedData2?data='+$.trim($('#editUserField1 option:selected').val()), function(data) 
					{
						 var dataList2 ="";
						$.each(data.result, function(key, val) {
							 if(data.status==='success')
							 {
								 dataList2 += '<option value="' + data.result[key].key + '">'
									+ data.result[key].value + '</option>';
							 }
						});
						$('#editUserField2').html(options+dataList2);
						$('#editUserField2').val(column2);
					});
				}
			else
				{
				$('#editUserField2').html(options);
				}
			 });
		
		$('#editUserField2').change(function()
		{
				
				if($(this).val()!=='0')
					{
					errordisplay("",$('#editUserField2').attr('id'));
					//$('#editUserField2Error').addClass('hideall');
					}
		});
		
		
		
		 if(RoleId==='4')
		 {
			 $('#editActivityLead').attr('disabled',true);
			 $('#editActivityOpp').attr('disabled',true);
			 $('#editActivityAssigned').attr('disabled',false);
			 $('#editActivityDate').attr('disabled',false);
			 $('#editUserField1').attr('disabled',false);
			 $('#editUserField2').attr('disabled',false);
			 $('#editDesc').attr('disabled',false);
			 $('#editActivityName').attr('disabled',false);
			  $('#editActivityType').attr('disabled',false);
			  $('#editActivityAssignedText').attr('disabled',false);
			  
		 }
		/* else if(RoleId==='5')
		 {
			 $('#editActivityLead').attr('disabled',true);
			  $('#editActivityOpp').attr('disabled',true);
			  $('#editActivityAssigned').attr('disabled',true);
			  $('#editActivityDate').attr('disabled',true);
			  $('#editUserField1').attr('disabled',true);
			  $('#editUserField2').attr('disabled',true);
			  $('#editDesc').attr('disabled',true);
			  $('#editActivityName').attr('disabled',true);
			  $('#editActivityType').attr('disabled',true);
			  $('#editActivityAssignedText').attr('disabled',true);
			  
			  
		 }*/
		 else
		{
			 $('#editActivityLead').attr('disabled',false);
			 $('#editActivityOpp').attr('disabled',false);
			 $('#editActivityAssigned').attr('disabled',false);
			 $('#editActivityAssignedText').attr('disabled',false);
			 $('#editActivityDate').attr('disabled',false);
			 $('#editUserField1').attr('disabled',false);
			 $('#editUserField2').attr('disabled',false);
			 $('#editDesc').attr('disabled',false);
			 $('#editActivityName').attr('disabled',false);
			  $('#editActivityType').attr('disabled',false);
		}
		 
		 if(RoleId==='5' && defaultOpp==='0')
		 {
			 $('#editActivityAssigned').addClass('hideall');
			 $('#editActivityAssignedText').removeClass('hideall');
		 }
		 else if(RoleId==='4' && defaultOpp==='1')
		 {
			 $('#editActivityAssigned').addClass('hideall');
			 $('#editActivityAssignedText').removeClass('hideall');
		 }
		 else
			 { 
			 
			 $('#editActivityAssigned').removeClass('hideall');
			 $('#editActivityAssignedText').addClass('hideall');
			 
			 }
		 
		 
	}
	function removeFunction(id){
		activityImageid=id;
		$('#dlgRemoveActivityMsg').dialog('open');
	}
	function getViewDetail(id)
	{
			$.getJSON('/activity/getActivityDetails?activityId='+(id), function(data) {
				 $('#cmdViewActivity').attr('disabled',true);
				
			 if(data.status==='success')
			 { 
				 $.each(data.result, function(key, val) 
		         {
					
					 $('#viewActivityOrg').val(data.result[key].Org);
					 $('#viewActivityLead').val(data.result[key].LeadName);	
					 $('#viewActivityOpp').val(data.result[key].OppName);
					 $('#viewActivityName').val(data.result[key].Name);
					 $('#viewActivityType').val(data.result[key].TypeName);
					 $('#viewActivityAssigned').val(data.result[key].AssignedName);
					 $('#viewActivityDate').val(data.result[key].DueDate);
					 $('#viewDesc').val(data.result[key].Desc);
					 $('#viewActivityStatus').val(function(){if (data.result[key].ActivityStatus==1){return "Open";} else if(data.result[key].ActivityStatus==2){return "Review";}else {return "Closed";}});
					 if(data.result[key].Document!=='')
					 {
						 $('#viewFileUploadImage').attr('href',data.result[key].Document);
					   	$('#viewFileUploadImage').html(data.result[key].FileName);
					   	$('#viewFileUpload').removeClass('hideall');
						 
					 }
					 else
						{
						 $('#viewFileUpload').addClass('hideall');
						}
					 
					  $.getJSON('/activity/getOrgUserDetails?orgId='+data.result[key].Org, function(datas) 
								{
										$.each(datas.result, function(keys, val) {
											 if(datas.status==='success')
											 {
												 generateViewUserField(datas.result[keys].userFiled1,datas.result[keys].userFiled2,datas.result[keys].required,data.result[key].Column1,data.result[key].Column2);
											 }
										});
								});
												 
							
					 
		         });
				 	$('#viewActivityfrm').find('input,textarea,select').attr('disabled',true);
          			$('#viewActivity').removeClass('hideall');
         			$('#viewCloseActivity').attr('disabled',false);
			 }
			 else
			 {
				
				 $('#dlgActivityMsg').html('Error showing view. Please try agian later.').dialog('open');
			 }
			 $('#cmdViewActivity').attr('disabled',false);
		 });			$('#allActivity').addClass('hideall');
        
	}
	
	function getEditActivity(id){
		
		$.getJSON('/activity/getActivityDetails?activityId='+(id), function(data) {
			 $('#cmdEditActivity').attr('disabled',true);
			if(data.status==='success')
		   {
			 getEditFileUploader();
			 $.each(data.result, function(key, val) 
	         {				 
				 if(data.result[key].ActivityStatus==3)
					 {
					 $('#cmdViewActivity').trigger('click');
					 }
				 else{
				 $('#editActivityOrg').val(data.result[key].Org);
				 getLeadOpp(data.result[key].Org,data.result[key].LeadId,data.result[key].OppId,data.result[key].AssignedId,data.result[key].ActivityStatus,data.result[key].CreatedBy,data.result[key].Type);
				 $('#editActivityName').val(data.result[key].Name);
				 $('#editActivityDate').val(data.result[key].DueDate);
				 $('#activityId').val(data.result[key].Id);
				 $('#createdBy').val(data.result[key].CreatedBy);
				 $('#createdDate').val(data.result[key].CreatedDate);
				 $('#editDesc').val(data.result[key].Desc);
				 $('#editActivityAssignedText').val(data.result[key].AssignedName);
				 $('#status').val(data.result[key].Status);
				 $('#documentsize').val(data.result[key].Documentsize);
				 
					
				 if(data.result[key].Documentsize!=="0")
					 {
					
					 
					// $('#editFileUpload').removeClass('hideall');
					
					// $('#uploadfilelimitError').addClass('hideall');
					 $('#documentsize').val(data.result[key].Documentsize);
					 $('#editfileTable').html('');
					 for(var i=0; i<data.result[key].Documentsize;i++){
						// $('#editNewUploading').addClass('hideall');
						// $('#editfileuploader').removeClass('hideall');
						 $('#editfileTable').append("" +
						 		"" +
						 		"<div class='imgName' id='editfile"+data.result[key].Documents[i].ImageId+"'>" +
						 				"" +
						 				"<a id='editFileImage' href='"+data.result[key].Documents[i].Document+"'>"+data.result[key].Documents[i].FileName+"</a>" +
						 						"</div>" +
						 						"<div id='editRemove"+data.result[key].Documents[i].ImageId+"'> " +
						 						"<div class=trash><img src='/resources/images/trash.jpg'></div>"+
						 								"<div class=imgRmv>" +
						 								"<a id='"+data.result[key].Documents[i].ImageId+"' class=remAnchor><p>Remove</p></a></div>");
						 
						 $('#editfile'+activityImageid+'').removeClass('hideall');
						 $('#editRemove'+$.trim(data.result[key].Documents[i].ImageId)).removeClass('hideall'); 
						 $('#Removebutton1').addClass('hideall');
							$('.remAnchor').click(function()
							{
							removeFunction($(this).attr('id'));
							});
					
					 }
					 
					 if(parseInt(data.result[key].Documentsize)<10 || parseInt(data.result[key].Documentsize)<'10')
					 {
						 $('#editFileUploaderPart').removeClass('hideall');
					 }
					 else
					 {
						 $('#editFileUploaderPart').addClass('hideall');
					 }
					 
					
					         
					 }
			
					 else
						{
						
							 $('#documentsize').val(data.result[key].Documentsize);
							// $('#uploadfilelimitError').addClass('hideall');
							 //$('#editFileUpload').addClass('hideall');
							// $('#editNewUploading').removeClass('hideall');
			 				 $('div#editNewcancelLink').css('display','none');
						}
				 
				
				 $.getJSON('/activity/getOrgUserDetails?orgId='+data.result[key].Org, function(datas) 
							{
									$.each(datas.result, function(keys, val) {
										 if(datas.status==='success')
										 {
											 generateEditUserField(datas.result[keys].userFiled1,datas.result[keys].userFiled2,datas.result[keys].required,data.result[key].Column1,data.result[key].Column2,data.result[key].RoleId,data.result[key].DefaultOpp);
										 }
									});
							});
				 		$('#allActivity').addClass('hideall');
	         			$('#editActivity').removeClass('hideall');
				 }
	         });
	         
    		
		 }
		 else
		 {
			 $('#dlgActivityMsg').html('Error showing edit. Please try agian later.').dialog('open');
		 }
			 $('#cmdEditActivity').attr('disabled',false);	
	 });
	}

	function renderActivityApprovalHTML()
	{
		  $('#cmdCreateActivity').click(function()
		  {
			 $('#allActivity').addClass('hideall');
			 $('#createActivity').removeClass('hideall');
			 $('#newActivityOrg').trigger('change');
			 $('#cancelLink').addClass('hideall');
			 $('div#cancelLink').css('display','none');
			 newClearValues();
			  $.getJSON('/activity/getOrgUserDetails?orgId='+$('#newActivityOrg').val(), function(data) 
				{
						$.each(data.result, function(key, val) {
							 if(data.status==='success')
							 {
								 generateUserField(data.result[key].userFiled1,data.result[key].userFiled2,data.result[key].required);
								 selRow = $("#activityList").jqGrid('getGridParam', 'selrow');
									if (selRow !== null) 
									{
										cell = $("#activityList").jqGrid(
												'getRowData', selRow);
										$('#leadId').val(cell.leadId);
										$('#oppId').val(cell.oppId);
										$('#newActivityLead').attr('disabled',true);
										$('#newActivityOpp').attr('disabled',true);
									}
									else if($('#filterActivityLeadId option:selected').val()!=='%' && $('#filterActivityOppId option:selected').val()!=='%')
									{
										$('#leadId').val($('#filterActivityLeadId option:selected').val()) ;
										$('#oppId').val($('#filterActivityOppId option:selected').val()) ;
										$('#newActivityLead').attr('disabled',true);
										$('#newActivityOpp').attr('disabled',true);
									}
									else if($('#filterActivityLeadId option:selected').val()!=='%')
									{
										$('#leadId').val($('#filterActivityLeadId option:selected').val()) ;
										$('#newActivityLead').attr('disabled',true);
										$('#newActivityOpp').attr('disabled',false);
									}
									else if($('#filterActivityOppId option:selected').val()!=='%')
									{
										$('#oppId').val($('#filterActivityOppId option:selected').val()) ;
										$('#newActivityOpp').attr('disabled',true);
										$('#newActivityLead').attr('disabled',false);
									}
									else
									{
										$('#newActivityLead').attr('disabled',false);
										$('#newActivityOpp').attr('disabled',false);
									}
									
									if($('#leadId').val()==='' || $('#leadId').val()==='0' || $('#newActivityLead option:selected').val()=='0'){
										 $('#newActivityLead').attr('disabled',false);
									 }
									if($('#oppId').val()==='' || $('#oppId').val()==='0' || $('#newActivityOpp option:selected').val()==='0'){
										 $('#newActivityOpp').attr('disabled',false);
									 }
									newCount=0;
									arr = [];
								 getFileUploader();
								 
									 
							 }
						});
			    }); 
		  });
						 
			  
		  
		  $('#cmdViewActivity').click(function()
					{
						selRow = $("#activityList").jqGrid('getGridParam', 'selrow');
						if (selRow !== null) {
							cell = $("#activityList").jqGrid(
									'getRowData', selRow);
							getViewDetail(cell.aid);
							
							}
							else
							{
								 $('#dlgActivityMsg').html('Select a row to view.').dialog('open');
							}
					});
		  
		  			$('#cmdEditActivity').click(function()
					{
		  				newCount=0;
		  				arr=[];
						selRow = $("#activityList").jqGrid('getGridParam', 'selrow');
						if (selRow !== null) {
							cell = $("#activityList").jqGrid(
									'getRowData', selRow);
							if($('#roleId').val()===generalRole || $('#roleId').val()===supportRole){
								if(cell.curUser===currentuser){
									getEditActivity(cell.aid);
									}
									else
									{
										getViewDetail(cell.aid);
									}
							}
							else{
								getEditActivity(cell.aid);
							}
							}
							else
							{
								 $('#dlgActivityMsg').html('Select a row to edit.').dialog('open');
							}
					});
					
		  
					
		  
		  
		   $('#cmdDeleteActivity').click(function()		//Delete click function
	      {
	          selRow = $("#activityList").jqGrid(
					'getGridParam', 'selrow');
				if (selRow !== null) {
					cell = $("#activityList").jqGrid(
							'getRowData', selRow);
						
						$('#dlgDelActivityMsg').dialog('open');
				} else {
					 $('#dlgActivityMsg').html('Select a row to delete.').dialog('open');
							
				}
	      });
	}
	
	$('#editCancelActivity').click(function()
    {
		$('#editBackActivity').trigger('click');
    });
	
	
	$('#editBackActivity').click(function(){
		 $('#editActivity').addClass('hideall');
		 $('#allActivity').removeClass('hideall');
		 $('#editActivityfrm').find(":input").not("[type=button],[type=hidden]").each(
         function() {
       	  $(this).val('');
       	errordisplay("",$(this).attr('id'));
         });
		 $('#AddnewfileTable').html('');
		 errordisplay("",$('#editActivity').attr('id'));
		 $('#editfileName').val('');
		 $('#editActId').val('');
		 $("div#editfileuploader ul.qq-upload-list li").addClass('hideall');	
		 $('#editNewcancelLink').addClass('hideall');
		 
		 $('#editFileUploadImage').attr('href','#');
		 $('#editFileUploadImage').html('');
		 $('#editFileUpload').addClass('hideall');
		 $('#editNewUploading').addClass('hideall');
		 $('#editfileName').val('');
		 $('#EdituploadfilelimitError').addClass('hideall');
		 $("#activityList").trigger("reloadGrid");
		
	});
	$('#newBackActivity').click(function(){
		 $('#createActivity').addClass('hideall');
		 $('#allActivity').removeClass('hideall');
		 newClearValues();
	});
	function newClearValues()
	{
		i=0;
		$('#newActivityLead').attr('disabled',false);
		$('#leadId').val('');
		$('#oppId').val('');
		$('#newActivityOpp').attr('disabled',false);
		 $('#newActivityfrm').find(":input").not("[type=button],[type=hidden]").each(
		          function() {
		        	  $(this).val('');
		        	  errordisplay("",$(this).attr('id'));
		        	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
		          });
		  		 errordisplay("",$('#newActivity').attr('id'));
				
				 $("#newActivityLead option:first-child").attr("selected","selected");
				 $("#newActivityOpp option:first-child").attr("selected","selected");
				 $("#newActivityAssigned option:first-child").attr("selected","selected");
				 $("#userField1 option:first-child").attr("selected","selected");
				 $("#userField2 option:first-child").attr("selected","selected");
				 $('#fileName').val('');
				 $("div#fileuploader ul.qq-upload-list li").addClass('hideall');	
				 $('#cancelLink').addClass('hideall');
				 $('#newActBusinessHidden').val('');
				 $('#newActOppHidden').val('');
				 $('#fileTable').html('');
				 $('#newfileuploadlimit').addClass('hideall');
				
	}
	
	
	$('#viewBackActivity').click(function()
	{
			$('#viewActivityfrm').find('input,textarea,select').attr('disabled',false);
			$('#allActivity').removeClass('hideall');
			$('#viewActivity').addClass('hideall');
			$('#viewCloseActivity').attr('disabled',false);
	});
	
		
	$('#viewCloseActivity').click(function()
	{
		$('#viewBackActivity').trigger('click');
	});
	
	$('#newCancelActivity').click(function(){
		$('#newBackActivity').trigger('click');
	});
	
	
	$('#newActivityfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
	 {
			 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) 
			 {
	        	   
	        	   if($.trim($(this).attr('id'))==='userField1' || $.trim($(this).attr('id'))==='userField2')
	        	   {
	        		   if($('#contCreateRequiredCheck').is(':checked'))
	        		   {
	        			   errordisplay("Required field",$(this).attr('id'));
	        		   }
	        	   }
	               else if($(this).attr('id')===undefined)
            	   {
            	   
            	   }
	               else
	               {
	            	   errordisplay("Required field",$(this).attr('id'));
	              }
	              
	           } else {
	        	   errordisplay("",$(this).attr('id'));
	           }
	 });
	
	$('#editActivityfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
			{
					 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
						   
						   
			        	   if($(this).attr('id')==='editUserField1' || $(this).attr('id')==='editUserField2')
			        	   {
			        		   if($('#contEditRequiredCheck').is(':checked'))
			        		   {
			        			   errordisplay("Required field",$(this).attr('id'));
			        			  // $('#'+$.trim($(this).attr('id'))+'Error').removeClass('hideall');
			        		   }
			        	   }
			               else if($(this).attr('id')===undefined)
		            	   {
		            	   
		            	   }
			               else
			               {
			            	   errordisplay("Required field",$(this).attr('id'));
			            	  // $('#'+$(this).attr('id')+'Error').removeClass('hideall');
			              }
						   
	                  } else {
	                	  errordisplay("",$(this).attr('id'));
	                	 // $('#'+$(this).attr('id')+'Error').addClass('hideall');
	                  }
			});
	
	
	$('#newSubmitActivity').click(function()
			{
				var flag=true;
				 $('#newActivityfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
			    		   
					       function() {
					           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
					        	   
					        	   if($(this).attr('id')==='userField1' || $(this).attr('id')==='userField2')
					        	   {
					        		   if($('#contCreateRequiredCheck').is(':checked'))
					        		   {
					        			   errordisplay("Required field",$(this).attr('id'));
					        			  // $('#'+$.trim($(this).attr('id'))+'Error').removeClass('hideall');
					            	   		flag = false;
					        		   }
					        	   }
					               else if($(this).attr('id')===undefined)
				            	   {
				            	   
				            	   }
					               else
					               {
					            	   errordisplay("Required field",$(this).attr('id'));
					            	 //  $('#'+$(this).attr('id')+'Error').removeClass('hideall');
					            	   	flag = false;
					              }
					              
					           } else {
					        	   errordisplay("",$(this).attr('id'));
					        	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
					           }
					       });
				/* if(!$('#newActivityValidError').hasClass('hideall'))
				 {
					 flag=false;
				 }*/
				 if(flag)
				 {
					 $('#newSubmitActivity').attr('disabled',true);
					 $('#newActBusinessHidden').val($('#newActivityLead').val());
					 $('#newActOppHidden').val($('#newActivityOpp').val());
					 $('#filesName').val(arr);
					 $('#newActivityfrm').submit();
						/*$.ajax({
					           url: '/activity/createActivity',
					           type:'post',
					           data:$('#newActivityfrm').serialize(),
					           success: function(data)
					           {
					        	   $('#newSubmitActivity').attr('disabled',false);
					        	  if(data==='success')
				        		  {
					        		  $('#dlgActivityMsg').html('Activity created successfully').dialog('open');
					        		  $("#activityList").trigger("reloadGrid");
					        		  $('#newBackActivity').trigger('click');
				        		  }
					        	  else if($.trim(data)==='exists')
				        		  {
					        		  errordisplay("Activity name already exists",$(this).attr('id'));
					        		 // $('#newActivityValidError').removeClass('hideall');
					        		  $('#dlgActivityMsg').html('Activity name already exists.').dialog('open');
				        		  }

					        	  else
				        		  {
					        		  $('#dlgActivityMsg').html('Error creating activity. Please try again later.').dialog('open');
				        		  }
					           }
						});*/
				 }
				 
			
			});
	
	
	$('#editSubmitActivity').click(function()
			{
				var flag=true;
				 $('#editActivityfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
			    		   
					       function() {
					           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
					        	   
					        	   
					        	   if($(this).attr('id')==='editUserField1' || $(this).attr('id')==='editUserField2')
					        	   {
					        		   if($('#contEditRequiredCheck').is(':checked'))
					        		   {
					        			   errordisplay("Required field",$(this).attr('id'));
					        			   //$('#'+$.trim($(this).attr('id'))+'Error').removeClass('hideall');
					            	   		flag = false;
					        		   }
					        	   }
					               else if($(this).attr('id')===undefined)
				            	   {
				            	   
				            	   }
					               else
					               {
					            	   errordisplay("Required field",$(this).attr('id'));
					            	 //  $('#'+$(this).attr('id')+'Error').removeClass('hideall');
					            	   	flag = false;
					              }
					           } else {
					        	   errordisplay("",$(this).attr('id'));
					        	   //$('#'+$(this).attr('id')+'Error').addClass('hideall');
					           }
					       });
				/* if(!$('#editActivityNameValidError').hasClass('hideall'))
				 {
					 flag=false;
				 }*/
				 if(flag)
				 {
					 $('#editSubmitActivity').attr('disabled',true);
					 $('#editActStatus').val($('#editActivityStatus option:selected').val());
					 $('#statusName').val($('#editActivityStatus option:selected').text());
					 $('#editfileName').val(arr);
					 $('#editActivityfrm').submit();
					/*	$.ajax({
					           url: '/activity/editActivity',
					           type:'post',
					           data:$('#editActivityfrm').serialize(),
					           success: function(data)
					           {
					        	   $('#editSubmitActivity').attr('disabled',false);
					        	  if(data==='success')
				        		  {
					        		  $('#dlgActivityMsg').html('Activity updated successfully').dialog('open');
					        		  $("#activityList").trigger("reloadGrid");
					        		  $('#editBackActivity').trigger('click');
				        		  }
					        	  else if($.trim(data)==='exists')
				        		  {
					        		 // $('#editActivityNameValidError').removeClass('hideall');
					        		  errordisplay("Activity name already exists",$(this).attr('id'));
					        		  $('#dlgActivityMsg').html('Activity name already exists.').dialog('open');
				        		  }
					        	  else if($.trim(data)==='accessdenied')
				        		  {
					        		  $('#dlgActivityMsg').html("Only creator can close the task.").dialog('open');
				        		  }
					        	  else
				        		  {
					        		  $('#dlgActivityMsg').html('Error updating activity. Please try again later.').dialog('open');
				        		  }
					           }
						});*/
				 }
				 
			
			});
	
	
});
});