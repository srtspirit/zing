$(document).ready(function()
{
	var optionsAll="<option value=%>All</option>";
	var options="<option value=0>Select</option>";
	var hei = "100%";
	
$(function()
{
	
	 if($.trim($('#createOpportunity').val())==='createOpps')
	  {
			$('#allOpp').addClass('hideall');
	  }
	 else
		 {
		 $('#allOpp').removeClass('hideall');
		 }
	 
	 $.extend($.ui.dialog.prototype.options, {
			modal : true,
			bgiframe : true,
			resizable : false,
			autoOpen : false
		});
	 
	 $("#newClosedDate").datepicker({
		 
		 
		   changeMonth: true,
			changeYear: true,
			// yearRange: "-100:+0",
			showAnim:'',dateFormat:'yy-mm-dd',
			minDate: new Date(),
			//limitToToday:true,
			onChangeMonthYear: function (year,month,inst)
			{
				$("#newClosedDate").trigger('inst');
				$("#newClosedDate").datepicker("setDate",year+"-"+(month)+"-01");
			},
			onClose:function(dateText,inst)
			{
				$("#newClosedDate").trigger('blur');
				if($("#newClosedDate").val()==='')
				{
					$("#newClosedDateError").removeClass('hideall');  
				}
				else
					{
					$("#newClosedDateError").addClass('hideall');  
					}
			}	
	 });
	 
	 
	 $("#editClosedDate").datepicker({
		 
		 
		   changeMonth: true,
			changeYear: true,
			// yearRange: "-100:+0",
			showAnim:'',dateFormat:'yy-mm-dd',
			minDate: new Date(),
			//limitToToday:true,
			onChangeMonthYear: function (year,month,inst)
			{
				$("#editClosedDate").trigger('inst');
				$("#editClosedDate").datepicker("setDate",year+"-"+(month)+"-01");
			},
			onClose:function(dateText,inst)
			{
				$("#editClosedDate").trigger('blur');
				if($("#editClosedDate").val()==='')
				{
					$("#editClosedDateError").removeClass('hideall');  
				}
				else
					{
					$("#editClosedDateError").addClass('hideall');  
					}
			}	
	 });
	 
	 
	   $('#dlgOppMsg').dialog({
			buttons : {
				"Ok" : function() {
					$('#dlgOppMsg').dialog('close');
				}
			}
		});
	   
	   
	   $('#dlgDelOppMsg').dialog({
			buttons : {
				"Yes" : function() {
					$('#cmdDeleteOpp').attr('disabled',true);
					$
					.ajax({
						url : '/opportunity/deleteOpp',
						type : 'POST',
						data : 'oppId='+$.trim(cell.id),
						success : function(data) {
							$('#cmdDeleteOpp').attr('disabled',false);
							if($.trim(data)=='success')
							{
								$("#oppList").trigger(
												'reloadGrid');
								 $('#dlgOppMsg').html('Opportunity deleted successfully.').dialog('open');
								
							}	
							else
							{
								 $('#dlgOppMsg').html('Error deleting Opportunity. Please try again later.').dialog('open');
							}
						 $('#dlgDelOppMsg').dialog('close');
						}
					});
				},
				"No" : function() {
					$('#dlgDelOppMsg').dialog('close');
				}
			}
		}); 
	   
	
	$('#tabs').fadeIn(10);
	$( "#tabs" ).tabs();	

	
	
	 $('#newOpp').blur(function() // Section name validation
				{
				   if ($.trim($('#newOpp').val().length) != 0) {
						$("#newOppError").addClass("hideall");
					   $.ajax({
				           url: '/opportunity/getOppNameValidation',
				           data: 'leadId='+$.trim($('#newOppCompany').val())+'&oppName='+$.trim($('#newOpp').val()),
				           success: function(data) {
				          	 if(data==='exists')
				          	{
				          		$('#newOppValidError').removeClass('hideall');
				          	}
				          	 else
				          	{
				          		$('#newOppValidError').addClass('hideall');
				          	}
				           }
						});
					} else {
						$("#newOppError").removeClass("hideall");
					}
				});
	 
	 
	 $('#editOppName').blur(function() // Section name validation
				{
				   if ($.trim($('#editOppName').val().length) != 0) {
						$("#editOppNameError").addClass("hideall");
					   $.ajax({
				           url: '/opportunity/getEditOppNameValidation',
				           data: 'leadId='+$.trim($('#editOppCompany').val())+'&oppName='+$.trim($('#editOppName').val())+'&oppId='+$('#editId').val(),
				           success: function(data) {
				          	 if(data==='exists')
				          	{
				          		$('#editOppNameValidError').removeClass('hideall');
				          	}
				          	 else
				          	{
				          		$('#editOppNameValidError').addClass('hideall');
				          	}
				           }
						});
					} else {
						$("#editOppNameError").removeClass("hideall");
					}
				});
	
	 $.getJSON('/opportunity/orglist', function(data) 
	{
			var orgList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 orgList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			$('#filterOppOrg').html(optionsAll+orgList).trigger('change');
			$('#oppOrg').html(orgList);
			$('#viewOrg').html(orgList);
			$('#editOppOrg').html(orgList);
			
    });  
	 
	 
	 $('#filterOppOrg').change(function()
	{
		 $.getJSON('/opportunity/companylist?orgId='+escape($('#filterOppOrg option:selected').val()), function(data) 
		{
				var orgList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 orgList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#filterOppcompany').html(optionsAll+orgList);
				if($.trim($('#oppLeadId').val())!=='')
				{
					$("#filterOppcompany option[value="+$.trim($('#oppLeadId').val())+"]").attr("selected","selected");
				}
				OppGrid();
		});
	});
	 
	 function editOrg(orgId,leadId,OppStatusId,countryId)
		{
		 
		 $.getJSON('/opportunity/currencylist', function(data) 
		{
				var currencyList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 currencyList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#editExpectedVal').html(options+currencyList);
				$('#editExpectedVal').val(countryId);
		});
		 $.getJSON('/opportunity/companylist?orgId='+escape(orgId), function(data) 
			{
					var compList ="";
					$('#editOppCompany').html('');
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 compList += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#editOppCompany').html(compList);
					$('#editOppCompany').val(leadId);
		    });  
		 
		 $.getJSON('/opportunity/getStatus?orgId='+escape(orgId), function(data) 
			{
					var statusList ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 statusList += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#editOppStatus').html(statusList);
					$('#editOppStatus').val(OppStatusId);
					
					if(parseInt($.trim($('#editOppStatus option:selected').val()))===parseInt(5))
					{
						setTimeout(function(){ 
						 $('#editOppfrm').find('input,textarea,select').attr('disabled',true);
						  $('#editOppUpdate').css('display','none');
						  $('#editCancelOpp').attr('disabled',false);
							$('#editOppUpdate').attr('disabled',false);
						},200);
						
						//$('#editOppStatus').attr('disabled',true);
					 	
						}
					else
						{
						//$('#editOppStatus').attr('disabled',false);
					 	$('#editOppfrm').find('input,textarea,select').attr('disabled',false);
						$('#editOppUpdate').css('display','block');
						$('#editCancelOpp').attr('disabled',false);
						$('#editOppUpdate').attr('disabled',false);
						}
					
		    });  
		}
	 
	function getCurrencyValue(){
	 $.getJSON('/opportunity/currencylist', function(data) 
				{
						var currencyList ="";
						$.each(data.result, function(key, val) {
							 if(data.status==='success'){
								 currencyList += '<option value="' + data.result[key].key + '">'
									+ data.result[key].value + '</option>';
							 }
						});
						$('#newExpectedVal').html(options+currencyList);
				});
	}
	 
	 
	 $('#oppOrg').change(function()
			{
				 $.getJSON('/opportunity/companylist?orgId='+escape($('#oppOrg option:selected').val()), function(data) 
				{
						var orgList ="";
						$.each(data.result, function(key, val) {
							 if(data.status==='success'){
								 orgList += '<option value="' + data.result[key].key + '">'
									+ data.result[key].value + '</option>';
							 }
						});
						$('#newOppCompany').html(options+orgList);
						$('#newOppCompany').val($.trim($('#leadId').val()));
				});
			});
	 
	 
		
		$('#SearchOppbutton').click(function()	//Filter Click Function
		{
			
			$("#oppList")
			.setGridParam(
					{
						//url : '/beds/bedsGridView?countryId='+escape($('#filterBedsCountry').val())+'&stateId='+escape($('#filterBedsState').val())+'&cityId='+escape("%"+$('#filterBedsCity').val()+"%")+'&homeId='+escape($('#filterBedsHome').val())+'&sectionId='+escape($('#filterBedsSection').val()),
						url : '/opportunity/gridview?companyName='+escape($("#filterOppcompany").val())+'&orgId='+escape($('#filterOppOrg option:selected').val()),
						page : 1,
						rowNum : $(
								"#oppPager_center .ui-pg-selbox")
								.val(),
						sortname : '1',
						sortorder : 'desc'
					}).trigger("reloadGrid");
		}); 
	
	function OppGrid()
	{	
		$("#oppList").jqGrid({
  			url:'/opportunity/gridview?companyName='+escape($("#filterOppcompany").val())+'&orgId='+escape($('#filterOppOrg option:selected').val()),
  			datatype:"xml",
  			colNames:['Id','Business Partner','Opportunity','Description','Lead Id','Document Id'],
 			colModel:[
					
					{name:'id',index:'1',width:200,resizable:true,hidden:true},
					{name:'lead',index:'2',width:240,resizable:false},
					{name:'opportunity',index:'3',width:240,resizable:false},
					{name:'desc',index:'4',width:510,resizable:false},
					{name:'leadid',index:'5',width:180,resizable:false,hidden:true},
					{name:'document',index:'6',width:140,resizable:false},
			],
  		  	
  		    rowNum:20,
  		  width:1132,
  		  height:300,
		    autowidth: true,
		    shrinkToFit : true,
	  		rowList:[20,40,60,80,100],
			pager:$('#oppPager'),
			sortname:'1',
			viewrecords:true,
			sortorder:"desc",
  			toppager:false,
			gridview:true,
			/*afterInsertRow: function (rowid, rowData, rowElm) 
  			{
                $("#leadList").setCell(rowid, 'date', '', '', { 'title': rowData.timezone }, false);
			},*/
  			gridComplete:function(id){
  			
                $("tr.jqgrow:odd").addClass('allAltRowHoverClass');
                $("tr.jqgrow:even").addClass('allAltRowHoverClass');
                $("#oppList").setSelection(0,false);	  
                $("#showOppDataButtons").html($("#oppList").getGridParam('userData').operation);
                $('#oppPager_right').addClass('hideall');				
                jQuery("#oppPager .ui-pg-selbox").closest("td").after("<td dir='ltr'>No of rows </td>");
              
  				renderOppApprovalHTML();
  				 $('#allOpp').removeClass('hideall');
  				 if($.trim($('#createOpportunity').val())==='createOpps')
  				{
  					$('#allOpp').addClass('hideall');
  					$('#cmdCreateOpp').trigger('click');
  					$('#createOpportunity').val('');
  				}
  				
  			}, 
  			ondblClickRow: function(rowid){
  				$('#cmdViewOpp').trigger('click');
  			}
  		}); 
		
		
	}
	
	
	
	
	function renderOppApprovalHTML()
	{
		$('#cmdCreateOpp').click(function()
		{
			newClearOppValues();
			getCurrencyValue();
			/* $.getJSON('/opportunity/getDocumentId?orgId='+($('#oppOrg option:selected').val()), function(data) {
				 if(data.status==='success')
				 {
					 $.each(data.result, function(key, val) 
			         {
						 $('#newOppDocument').val(data.result[key].DocumentId);
			         });
				 }
				 else
				 {
					 $('#newOppDocument').val(0);
				 }*/
				 
	        	   $('#oppOrg').trigger('change');
	        	   
	        	   selRow = $("#oppList").jqGrid('getGridParam', 'selrow');
					if (selRow !== null) 
					{
						
						cell = $("#oppList").jqGrid(
								'getRowData', selRow);
						$('#leadId').val(cell.leadid);
						$('#newOppCompany').attr('disabled',true);
					}
					else if($('#filterOppcompany option:selected').val()!=='%')
					{
						$('#leadId').val($('#filterOppcompany option:selected').val()) ;
						$('#newOppCompany').attr('disabled',true);
					}
					else
				    {
						$('#newOppCompany').attr('disabled',false);
					}
					
					if($('#leadId').val()==='')
						{
						$('#newOppCompany').attr('disabled',false);
						}
	   				$('#allOpp').addClass('hideall');
	   				$('#createOpp').removeClass('hideall');
		   				
			 });
			
		
		$('#cmdViewOpp').click(function()
				{
					
					selRow = $("#oppList").jqGrid('getGridParam', 'selrow');
					if (selRow !== null) {
						cell = $("#oppList").jqGrid(
								'getRowData', selRow);
							 $.getJSON('/opportunity/getOppDetails?oppId='+(cell.id), function(data) {
								 $('#cmdViewOpp').attr('disabled',true);
								 if(data.status==='success')
								 {
									 $.each(data.result, function(key, val) 
							         {
										 $('#viewOrg').val(data.result[key].Date);
										 $('#viewOppCompany').val(data.result[key].LeadName);
										 $('#viewOppDocument').val(data.result[key].Document);
										 $('#viewOppName').val(data.result[key].OppName);
										 $('#viewOppDesc').val(data.result[key].Desc);
										 $('#viewOppExpectedVal').val(data.result[key].Currency);
										 $('#viewOppExpectedValue').val(data.result[key].ExpectedValue);
										 $('#viewOppProbablity').val(data.result[key].Probability);
										 $('#viewClosedDate').val(data.result[key].CloseDate);
										 $('#viewOppStatus').val(data.result[key].OppStatus);
							         });
									 	$('#viewOppfrm').find('input,textarea,select').attr('disabled',true);
					         			$('#allOpp').addClass('hideall');
					         			$('#viewOpp').removeClass('hideall');
					         			$('#closeOpp').attr('disabled',false);
								 }
								 else
								 {
									 $('#dlgOppMsg').html('Error showing view. Please try agian later.').dialog('open');
								 }
								 $('#cmdViewOpp').attr('disabled',false);
							 });
						}
						else
						{
							 $('#dlgOppMsg').html('Select a row to view.').dialog('open');
						}
				});
		
		$('#cmdEditOpp').click(function()
				{
					
					selRow = $("#oppList").jqGrid('getGridParam', 'selrow');
					if (selRow !== null) {
						cell = $("#oppList").jqGrid(
								'getRowData', selRow);
							 $.getJSON('/opportunity/getOppDetails?oppId='+(cell.id), function(data) {
								 $('#cmdEditOpp').attr('disabled',true);
								 if(data.status==='success')
								 {
									 $.each(data.result, function(key, val) 
							         {
										 $('#editOppOrg').val(data.result[key].Date);
										 editOrg(data.result[key].AccountId,data.result[key].LeadId,data.result[key].OppStatusId,data.result[key].ExpectedSymbol);
										 $('#editOppDocument').val(data.result[key].Document);
										 $('#editOppName').val(data.result[key].OppName);
										 $('#editOppDesc').val(data.result[key].Desc);
										 $('#editCreatedBy').val(data.result[key].CreatedBy);
										 $('#editCreatedDate').val(data.result[key].CreatedDate);
										 $('#editId').val(cell.id);
										 $('#editStatus').val(data.result[key].Status);
										 $('#editDefault').val(data.result[key].Default);
										 $('#editExpectedValue').val(data.result[key].ExpectedValue);
										 $('#editProbability').val(data.result[key].Probability);
										 $('#editClosedDate').val(data.result[key].CloseDate);
										 
							         });
					         			$('#allOpp').addClass('hideall');
					         			$('#editOpp').removeClass('hideall');
								 }
								 else
								 {
									 $('#dlgOppMsg').html('Error showing edit. Please try agian later.').dialog('open');
								 }
								 $('#cmdEditOpp').attr('disabled',false);
							 });
						}
						else
						{
							 $('#dlgOppMsg').html('Select a row to edit.').dialog('open');
						}
				});
		

		   $('#cmdDeleteOpp').click(function()		//Delete click function
	      {
	          selRow = $("#oppList").jqGrid(
					'getGridParam', 'selrow');
				if (selRow !== null) {
					cell = $("#oppList").jqGrid(
							'getRowData', selRow);
						
						$('#dlgDelOppMsg').dialog('open');
				} else {
					 $('#dlgOppMsg').html('Select a row to delete.').dialog('open');
							
				}
	      });
		   
		   $('#cmdActOpp').click(function()		//Delete click function
	      {
	          selRow = $("#oppList").jqGrid(
					'getGridParam', 'selrow');
				if (selRow !== null) {
					cell = $("#oppList").jqGrid(
							'getRowData', selRow);
					
					$('#oppoppleadId').val(cell.id);
					$('#oppleadId').val(cell.leadid);
					$('#cmdOPPActId').trigger('click');
					
				} else {
					 $('#dlgOppMsg').html('Select a row.').dialog('open');
							
				}
	      });
				
	}
	
	
	
	$('#newOppfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
	{
			 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
				   $('#'+$(this).attr('id')+'Error').removeClass('hideall');
              } else {
            	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
            	  var nameRegex = /^[0-9]+(\.[0-9]+)+$/;
					var nameRegex1 =  /^[0-9]+$/;
					if($(this).attr('id')==='newExpectedValue' || $(this).attr('id')==='newProbability')
					{
						 if ($.trim($(this).val()).search(nameRegex) === 0 || $.trim($(this).val()).search(nameRegex1) === 0) 
				            {
							 $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
				            } 
		  		             else
		  		             {
		  		            	 $('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
		  		             }
					
					}
              }
	});
	
	$('#editOppfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
			{
					 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
						   $('#'+$(this).attr('id')+'Error').removeClass('hideall');
		              } else {
		            	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
		            	  var nameRegex = /^[0-9]+(\.[0-9]+)+$/;
							var nameRegex1 =  /^[0-9]+$/;
							if($(this).attr('id')==='editExpectedValue' || $(this).attr('id')==='editProbability')
							{
								 if ($.trim($(this).val()).search(nameRegex) === 0 || $.trim($(this).val()).search(nameRegex1) === 0) 
						            {
									 $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
						            } 
				  		             else
				  		             {
				  		            	 $('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
				  		             }
							
							}
		              }
			});
	
	function newClearOppValues()
	{
		 $("#newExpectedVal option:first-child").attr("selected","selected");
		 $("#newOppCompany option:first-child").attr("selected","selected");
		 $('#newOppfrm').find(":input").not("[type=button],[type=hidden]").each(
          function() {
        	  $(this).val('');
        	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
        	  $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
          });
		 $('#newOppValidError').addClass('hideall');
		 $('#newOppDocument').val(0);
		 $('#newOppCompany').attr('disabled',false);
		 $('#leadId').val('');
		 $('#newCompanyHidden').val('');
	}
	
	$('#newbackOpp').click(function()
	{
		$('#createOpp').addClass('hideall');
		$('#allOpp').removeClass('hideall');
		 newClearOppValues();
		

	});
	
	$('#editCancelOpp').click(function()
		{
		$('#editbackOpp').trigger('click');
		});
	
	$('#editbackOpp').click(function()
			{
				$('#editOpp').addClass('hideall');
				$('#allOpp').removeClass('hideall');
				 $('#editOppfrm').find(":input").not("[type=button],[type=hidden]").each(
		          function() {
		        	  $(this).val('');
		        	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
		        	  $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
		          });
				 $('#editOppNameValidError').addClass('hideall');

			});
	
	$('#viewbackOpp').click(function()
	{
		$('#viewOppfrm').find('input,textarea,select').attr('disabled',false);
			$('#viewOpp').addClass('hideall');
			$('#allOpp').removeClass('hideall');
	});
	
	
	$('#closeOpp').click(function()
	{
		$('#viewbackOpp').trigger('click');
	});
	
	$('#cancelOpp').click(function()
	{
		  $('#newbackOpp').trigger('click');
	});
	
	$('#submitOpp').click(function()
			{
				var flag=true;
				 $('#newOppfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
			    		   
					       function() {
					           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0)
					           {
					               $('#'+$(this).attr('id')+'Error').removeClass('hideall');
					               if($(this).attr('id')==='newClosedDate')
					            	{
					            	   
					            	   }
					               else
					            	   {
					               flag = false;
					            	   }
					           } else {
					        	   $('#'+$(this).attr('id')+'Error').addClass('hideall');
					        	   
					        	   var nameRegex = /^[0-9]+(\.[0-9]+)+$/;
			    					var nameRegex1 =  /^[0-9]+$/;
			    					if($(this).attr('id')==='newExpectedValue' || $(this).attr('id')==='newProbability')
									{
										 if ($.trim($(this).val()).search(nameRegex) === 0 || $.trim($(this).val()).search(nameRegex1) === 0) 
								            {
											 $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
								            } 
						  		             else
						  		             {
						  		            	 $('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
						  		            	 flag=false;
						  		             }
									
									}
						           
					           }
					       });
				 
				 if(!$('#newOppValidError').hasClass('hideall'))
				 {
					 flag=false;
				 }
				 if(flag)
				 {
					 $('#submitOpp').attr('disabled',true);
					 $('#newCompanyHidden').val($('#newOppCompany').val());
						$.ajax({
					           url: '/opportunity/createOpp',
					           type:'post',
					           data:$('#newOppfrm').serialize(),
					           success: function(data)
					           {
					        	   $('#submitOpp').attr('disabled',false);
					        	  if(data==='success')
				        		  {
					        		  $('#dlgOppMsg').html('Opportunity created successfully').dialog('open');
					        		  $("#oppList").trigger("reloadGrid");
					        		  $('#newbackOpp').trigger('click');
				        		  }
					        	  else if($.trim(data)==='exists')
					        	  {
					        		  $('#dlgOppMsg').html('Opportunity name already exists').dialog('open');
					        		  $('#newOppValidError').removeClass('hideall');
					        	  }
					        	  else
				        		  {
					        		  $('#dlgOppMsg').html('Error creating opportunity. Please try again later.').dialog('open');
				        		  }
					           }
						});
				 }
				 
			
			});
	

	$('#editOppUpdate').click(function()
			{
				var flag=true;
				 $('#editOppfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			               $('#'+$(this).attr('id')+'Error').removeClass('hideall');
			               if($(this).attr('id')==='editClosedDate')
		            	   {
			            	   
		            	   }
		               else
		            	   {
		               flag = false;
		            	   }
			           } else {
			        	   $('#'+$(this).attr('id')+'Error').addClass('hideall');
			        	   var nameRegex = /^[0-9]+(\.[0-9]+)+$/;
							var nameRegex1 =  /^[0-9]+$/;
							if($(this).attr('id')==='editExpectedValue' || $(this).attr('id')==='editProbability')
							{
								 if ($.trim($(this).val()).search(nameRegex) === 0 || $.trim($(this).val()).search(nameRegex1) === 0) 
						            {
									 $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
						            } 
				  		             else
				  		             {
				  		            	 $('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
				  		            	 flag=flase;
				  		             }
							
							}
			           }
			       });
				 if(!$('#editOppNameValidError').hasClass('hideall'))
				 {
					 flag=false;
				 }
				 if(flag)
				 {
					 $('#editOppUpdate').attr('disabled',true);
					 $('#editOPPORStatus').val($('#editOppStatus option:selected').val());
						$.ajax({
					           url: '/opportunity/updateOpp',
					           type:'post',
					           data:$('#editOppfrm').serialize(),
					           success: function(data)
					           {
					        	   $('#editOppUpdate').attr('disabled',false);
					        	  if(data==='success')
				        		  {
					        		  $('#dlgOppMsg').html('Opportunity updated successfully').dialog('open');
					        		  $("#oppList").trigger("reloadGrid");
					        		  $('#editbackOpp').trigger('click');
				        		  }
					        	  else if($.trim(data)==='exists')
					        	  {
					        		  $('#dlgOppMsg').html('Opportunity name already exists').dialog('open');
					        		  $('#editOppValidError').removeClass('hideall');
					        	  }
					        	  else
				        		  {
					        		  $('#dlgOppMsg').html('Error updating opportunity. Please try again later.').dialog('open');
				        		  }
					           }
						});
				 }
				 
			
			});
	
});
});