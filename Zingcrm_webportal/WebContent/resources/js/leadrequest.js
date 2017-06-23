$(document).ready(function()
{
	var options="<option value=0>Select</option>";
	
$(function()
{
	var createbp=0;
	 $("#newLeadRequestError").css('display','none');
	 $("#editLeadRequestError").css('display','none');
	if($.trim($('#actId').val())!=='')
	{
		$('#allLeadRequest').addClass('hideall');
	}
	else if($.trim($('#createAct').val())==='createAct')
	{
		$('#allLeadRequest').addClass('hideall');
	}
	else
	{
	$('#allLeadRequest').removeClass('hideall');
	}
	
	 $.extend($.ui.dialog.prototype.options, {
			modal : true,
			bgiframe : true,
			resizable : false,
			autoOpen : false
		});
	 
	   $('#dlgLeadRequestMsg').dialog({
			buttons : {
				"Ok" : function() {
					$('#dlgLeadRequestMsg').dialog('close');
				}
			}
		});
	   $.getJSON('/leadrequest/sourcedetail', function(data) {
			var typeList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 typeList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			$('#newLeadRequestSource').html(typeList);
			$('#editLeadRequestSource').html(typeList);
	   });
	   function isValidURL(url){
		    //var RegExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
		   if(/(^|\s)((https?:\/\/)?[\w-]+(\.[\w-]+)+\.?(:\d+)?(\/\S*)?)/gi.test(url)) {
	            return true;
	        } else {
	            return false;
	        }
		} 
	   function onlyNumber(inputtxt){
			var numbers = /^[0-9]+$/;
			if(inputtxt.match(numbers)){
				return true;
			}else{
				return false;
			}
		}
	   
	   $('#newSubmitLeadRequest').click(function(){
		var flag=true;
		$('#newLeadRequestfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
				    		
						       function() {
						           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
						        	   if($(this).attr('id')===undefined)
					            	   {
					            	   }else if(($(this).attr('id')==='newLeadBusinessname')||(($(this).attr('id')==='newLeadPrimaryPhone'))|| (($(this).attr('id')==='newLeadReuestEmail')) || ($(this).attr('id')==='newLeadRequestAssigned') || ($(this).attr('id')==='newLeadRequestContactname'))
						               {
						            	   errordisplay("Required field",$(this).attr('id'));
						            	   flag = false;
						               }
						           } else {
						        	   errordisplay("",$(this).attr('id'));
							        	   if($.trim($(this).attr('id'))==='newleadrequestwesite') {
								      		   if(isValidURL($(this).val()))
								           		{
								      			 errordisplay("",$(this).attr('id'));
								           		}else{
								               	 errordisplay("Website is not valid format",$(this).attr('id'));
								               		flag=false;
								           		}
								      		 }
							        	   if($.trim($(this).attr('id'))==='newLeadPrimaryPhoneEx'){
								        	   if(onlyNumber($(this).val())){
								        		   errordisplay("",$(this).attr('id'));
								        	   }else{
								        		   errordisplay("Required Numeric value",$(this).attr('id'));
								               		flag=false;
								        	   }
							        	   }
						           }
						       });
					
					if(!validateEmail($.trim($('#newLeadReuestEmail').val())))
             		{
						 errordisplay("Email is not valid format",$('#newLeadReuestEmail').attr('id'));
						flag=false;
             		}
					 if(flag)
					 {
						 $('#newSubmitLeadRequest').attr('disabled',true);
							$.ajax({
						           url: '/leadrequest/createLeadRequest',
						           type:'Post',
						           data:$('#newLeadRequestfrm').serialize(),
						           success: function(data)
						           {
						        	   $('#newSubmitLeadRequest').attr('disabled',false);
						        	  if(data==='success')
					        		  {
						        		  leadreqcreated=1;
						        		  $('#dlgLeadRequestMsg').html('LeadRequest created successfully').dialog('open');
						        		  $("#leadrequestList").trigger("reloadGrid");
						        			$('#newleadreqValidError').addClass('hideall');
						        		  $('#newBackLeadRequest').trigger('click');
					        		  }
						        	  else if($.trim(data)==='exists')
					        		  {
						        		  $('#newLeadReuestEmail').addClass('ErrorField');
						        		  $('#newLeadBusinessnameError').addClass('hideall');
						        			$('#newLeadExistsError').addClass('hideall');
						        		 	$('#newleadreqValidError').removeClass('hideall');
						        		  errordisplay("Email already exists",$('#newLeadReuestEmail').attr('id'));
						        		//  $('#newLeadRequestValidError').removeClass('hideall');
					        		  }
						        	  else if(data==='leadexists')
				       			          	{
				       			          	$('#newLeadBusinessname').addClass('ErrorField');
				       			         	 $('#newLeadBusinessnameError').addClass('hideall');
				       			          		 $('#newLeadExistsError').removeClass('hideall');
				       			           	$('#newleadreqValidError').addClass('hideall');
				       			          	}
				       			          	 else if(data==='bpexists')
				       			          	{
				       			          		 $('#newLeadBusinessname').addClass('ErrorField');
				       			          		 $('#newLeadExistsError').addClass('hideall');
				       			          		 $('#newLeadBusinessnameError').removeClass('hideall');
				       			           	$('#newleadreqValidError').addClass('hideall');
				       			          	}
						     else
					        		  {
						        		  $('#dlgLeadRequestMsg').html('Error creating leadrequest. Please try again later.').dialog('open');
					        		  }
						           }
							});
					 }
				});
	   
	   $('#editSubmitLeadRequest').click(function(){
			var flag=true;
			$('#editLeadRequestfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
			function() {
				if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
					if($(this).attr('id')===undefined){}
					 else if(($(this).attr('id')==='editLeadBusinessname')||(($(this).attr('id')==='editLeadPrimaryPhone'))|| (($(this).attr('id')==='editLeadReuestEmail')) || ($(this).attr('id')==='editLeadRequestAssigned')||($(this).attr('id')==='editLeadRequestContactname')){
		            	   $('#'+$(this).attr('id')).addClass("ErrorField");
		            	   	flag = false;
					}
				} else {
		         	   editerrordisplay("",$(this).attr('id'));
		        	   if($.trim($(this).attr('id'))==='editleadrequestwesite'){
			      		   if(isValidURL($(this).val())){
			      			  editerrordisplay("",$(this).attr('id'));
			      		   } else{
			               	  editerrordisplay("Website is not valid format",$(this).attr('id'));
			               	  flag=false;
			           	   }
		      		   }
		        	   if($.trim($(this).attr('id'))==='editLeadPrimaryPhoneEx'){
			        	   if(onlyNumber($(this).val())){
			        		   errordisplay("",$(this).attr('id'));
			        	   }else{
			        		   errordisplay("Required Numeric value",$(this).attr('id'));
			               	   flag=false;
			        	   }
		        	   }
		           }
	       });
		   if(!validateEmail($.trim($('#editLeadReuestEmail').val()))){
			 editerrordisplay("Email is not valid format",$('#editLeadReuestEmail').attr('id'));
				flag=false;
     	   }
		   if(flag) {
				 $('#editSubmitLeadRequest').attr('disabled',true);
					$.ajax({
				           url: '/leadrequest/editLeadRequest',
				           type:'post',
				           data:$('#editLeadRequestfrm').serialize()+'&leadreqQualFlag='+$('#editLeadReqFlagYes').is(':checked'),
				           success: function(data)
				           {
				        	   $('#editSubmitLeadRequest').attr('disabled',false);
				        	  if(data==='success'){
				        		  if(createbp==1 && $('#editCreateBP').val()==='Create BP'){
									  $('#createLead').val('createLead');
									  $('#leadreqid').val($('#leadrequestId').val());
									  $('#cmdCreateLead').trigger('click');
									}else{
					        		  leadrequpdated=1;
					        		  $('#dlgLeadRequestMsg').html('LeadRequest updated successfully').dialog('open');
					        		  $("#leadrequestList").trigger("reloadGrid");
					        		  $('#editBackLeadRequest').trigger('click');
				        		  }
			        		  }
				        	  else if($.trim(data)==='exists')
			        		  {
				        		  $('#editLeadReuestEmail').addClass('ErrorField');
				        		  $('#editLeadExistsError').addClass('hideall');
				        		  $('#editLeadBusinessnameError').addClass('hideall');
				        		  $('#editleadreqValidError').removeClass('hideall');
				        		  editerrordisplay("Email already exists",$('#editLeadReuestEmail').attr('id'));  
			        		  } else if(data==='leadexists'){
       			          		 $('#editLeadBusinessname').addClass('ErrorField');
       			         	     $('#editLeadBusinessnameError').addClass('hideall');
       			         	     $('#editleadreqValidError').addClass('hideall');
       			          		 $('#editLeadExistsError').removeClass('hideall');
		       			      } else if(data==='bpexists'){
	       			          	 $('#editLeadBusinessname').addClass('ErrorField');
	       			          	 $('#editLeadExistsError').addClass('hideall');
	       			             $('#editleadreqValidError').addClass('hideall');
	       			          	 $('#editLeadBusinessnameError').removeClass('hideall');
	       			          }else{
				        		  $('#dlgLeadRequestMsg').html('Error updating LeadRequest. Please try again later.').dialog('open');
			        		  }
				           }
					});
		}
	});
    $('#newCreatBP').click(function(){
	   if($(this).val()==='Create BP'){
			 $('#createLead').val('createLead');
			 $('#leadreqemail').val($('#newLeadReuestEmail').val());
			 $('#cmdCreateLead').trigger('click');
		}
	});
	   
    $('#editCreateBP').click(function(){
	    	createbp=1;
	    	$('#editSubmitLeadRequest').trigger('click');
	});
	$('#tabs').fadeIn(10);
	$( "#tabs" ).tabs();	
	// LeadrequestGrid Load
	function validateEmail(email) {
       var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
       return re.test(email);
    }
	function errordisplay(errorval,id){
		 if(errorval!=''){
			 $('#'+id).addClass("ErrorField");
		 } else if(errorval=='Email already exists'){
			 $('#'+id).addClass("ErrorField");
		 } else if(errorval=='Email is not valid format'){
			 $('#'+id).addClass("ErrorField");
		 }else{
			 $('#'+id).removeClass("ErrorField");
		 }
	 }
	 function editerrordisplay(errorval,id){
		 if(errorval!=''){
			 $('#'+id).addClass("ErrorField");
			/* $("#editLeadRequestError").css('display','block');
			 $("#editLeadRequestError").val(errorval);*/
		 } else if(errorval=='Email already exists'){
			 $('#'+id).addClass("ErrorField");
		 } else if(errorval=='Email is not valid format'){
			 $('#'+id).addClass("ErrorField");
		 } else{
			 $('#'+id).removeClass("ErrorField");
		 }
	 }
		
	 $('#newLeadRequestContactname').blur(function(){ // Section name validation
		 if ($.trim($('#newLeadRequestContactname').val().length) != 0) {
			   errordisplay('',$(this).attr('id'));
			} else {
				errordisplay("Required Field",$(this).attr('id'));
			}
	 });
	 $('#editLeadRequestContactname').blur(function() {// Section name validation
	   if ($.trim($('#editLeadRequestContactname').val().length) != 0) {
		   editerrordisplay('',$(this).attr('id'));
		} else {
			editerrordisplay("Required Field",$(this).attr('id'));
		}
	 });
	 $("#hdn_time").datepicker({showAnim:'',dateFormat:'yy-mm-dd'}).datepicker("setDate", new Date());
	 $('#newLeadRequestDate').val($("#hdn_time").val());
	 $("#newLeadPrimaryPhone").mask("(999) 999?-9999");
	 $("#editLeadPrimaryPhone").mask("(999) 999?-9999"); 
	 
	 $("#newLeadPrimaryPhone").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );
		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;
		 var first = $(this).val().substr( 0, 9 );
		 $(this).val( first + '-' + lastfour );
		 }
		 $("#newLeadPrimaryPhone").removeClass('ErrorField');
		 if($(this).val()==''){
			 errordisplay('Required Field',$(this).attr('id'));
		 }
	 });
	 

	 
	 $("#editLeadPrimaryPhone").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );
		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;
		 var first = $(this).val().substr( 0, 9 );
		 $(this).val( first + '-' + lastfour );
		 }
		 if($(this).val()==''){
			 editerrordisplay('Required Field',$(this).attr('id'));
		 }else{
			 editerrordisplay('',$(this).attr('id'));
		 }
		 });
	 
		 $("#editLeadRequestAssigned").on("blur", function() {
			 if($(this).val()==0) {
				 editerrordisplay('Required Field',$(this).attr('id'));
				 } else{
				 editerrordisplay('',$(this).attr('id'));
			 }
		 });
	 	
		 
		 $('#newLeadBusinessname').blur(function(){ // Section name validation
			if ($.trim($('#newLeadBusinessname').val().length) != 0) {
         		  $.ajax({
		           url: '/leadrequest/getBusinessNameValidation',
		           data: 'businessname='+$.trim($('#newLeadBusinessname').val())+'&Id=0',
		           success: function(data) {
		          	 if($.trim(data)==='leadexists'){
		          		 $('#newLeadBusinessname').addClass('ErrorField');
		          		 $('#newLeadBusinessnameError').addClass('hideall');
		          		 $('#newLeadExistsError').removeClass('hideall');
		          	}else if($.trim(data)==='bpexists'){
		          		 $('#newLeadBusinessname').addClass('ErrorField');
		          	     $('#newLeadExistsError').addClass('hideall');
		          		 $('#newLeadBusinessnameError').removeClass('hideall');
		          	}else{
		          		 $('#newLeadExistsError').addClass('hideall');
		          		 $('#newLeadBusinessnameError').addClass('hideall');
		          		 $('#newLeadBusinessname').removeClass('ErrorField');
		          	}
		           }
				});
				} else {
					$('#newLeadExistsError').addClass('hideall');
					$('#newLeadBusinessname').addClass('ErrorField');
					$('#newLeadBusinessnameError').addClass('hideall');
				}
		 });
	
		 $('#editLeadBusinessname').blur(function(){ // Section name validation
			   if ($.trim($('#editLeadBusinessname').val().length) != 0) {
             		  $.ajax({
   			           url: '/leadrequest/getBusinessNameValidation',
   			           data: 'businessname='+$.trim($('#editLeadBusinessname').val())+'&Id='+$.trim($('#leadrequestId').val()),
   			           success: function(data) {
   			          	 if($.trim(data)==='leadexists'){
   			          		 $('#editLeadBusinessname').addClass('ErrorField');
   			          		 $('#editLeadBusinessnameError').addClass('hideall');
   			          		 $('#editLeadExistsError').removeClass('hideall');
   			          	}else if($.trim(data)==='bpexists'){
   			          		 $('#editLeadBusinessname').addClass('ErrorField');
   			          	     $('#editLeadExistsError').addClass('hideall');
   			          		 $('#editLeadBusinessnameError').removeClass('hideall');
   			          	} else{
   			          		 $('#editLeadExistsError').addClass('hideall');
   			          		 $('#editLeadBusinessnameError').addClass('hideall');
   			          		 $('#editLeadBusinessname').removeClass('ErrorField');
   			          	}
   			           }
   					});
				} else {
					$('#editLeadExistsError').addClass('hideall');
					$('#editLeadBusinessname').addClass('ErrorField');
					$('#editLeadBusinessnameError').addClass('hideall');
				}
			});
		 
 			$('#newLeadReuestEmail').blur(function(){ // Section name validation
			   if ($.trim($('#newLeadReuestEmail').val().length) != 0) {
                 	if(validateEmail($.trim($('#newLeadReuestEmail').val()))){
                 		$('#newLeadPrimaryEmailValidError').addClass('hideall');
                 		  $.ajax({
       			           url: '/leadrequest/getLeadReqEmailValidation',
       			           data: 'email='+$.trim($('#newLeadReuestEmail').val())+'&Id=0',
       			           success: function(data) {
       			          	 if(data==='exists'){
       			          		 $('#newleadreqValidError').removeClass('hideall');
       			          		 editerrordisplay("Email already exists",$('#newLeadReuestEmail').attr('id'));
       			          	}else{
	       			          	 $('#newleadreqValidError').addClass('hideall');
	       			          	 editerrordisplay('',$('#newLeadReuestEmail').attr('id'));
       			          	}
       			           }
       					});
             		}else{
                 		$('#newleadreqValidError').addClass('hideall');
                 		editerrordisplay("Email is not valid format",$(this).attr('id'));
             		}
				}else {
					$('#newleadreqValidError').addClass('hideall');
					editerrordisplay("Required Field",$(this).attr('id'));
				}
			});
 			
 			$('#editLeadReuestEmail').blur(function() { // Section name validation
 					   if ($.trim($('#editLeadReuestEmail').val().length) != 0) {
 						                 	if(validateEmail($.trim($('#editLeadReuestEmail').val()))){
 						                 		$('#editLeadPrimaryEmailValidError').addClass('hideall');
 						                 		$.ajax({
 						       			           url: '/leadrequest/getLeadReqEmailValidation',
 						       			           data: 'email='+$.trim($('#editLeadReuestEmail').val())+'&Id='+$.trim($('#leadrequestId').val()),
 						       			           success: function(data) {
 						       			          	 if($.trim(data)==='exists'){
 						       			          		$('#editleadreqValidError').removeClass('hideall');
 						       			          		editerrordisplay("Email already exists",$('#editLeadReuestEmail').attr('id'));
 						       			          	 }else {
	 						       			          	$('#editleadreqValidError').addClass('hideall');
	 						       			          	editerrordisplay('',$('#editLeadReuestEmail').attr('id'));
 						       			          	 }
 						       			           }
 						       					});
 						             		}else{
 						                 		$('#editleadreqValidError').addClass('hideall');
 						                 		editerrordisplay("Email is not valid format",$(this).attr('id'));
 						             		}
 						} else {
 							$('#editleadreqValidError').addClass('hideall');
 							editerrordisplay("Required Field",$(this).attr('id'));
 						}
 		});	

		$("#leadrequestList").jqGrid({
  			url:'/leadrequest/gridview?',
  			datatype:"xml",
  			colNames:['Id','Business Name','Contact Name','Email Id','Phone','Extension','isBPCreated'],
 			colModel:[
 			     	{name:'LeadRequestId',index:'1',width:90,resizable:true,hidden:true,editable:true},
					{name:'businessname',index:'2',width:250,resizable:true,editable:true},
					{name:'contactname',index:'3',width:250,resizable:false},
					{name:'email',index:'4',width:300,resizable:false},
					{name:'phone',index:'5',width:90,resizable:false},
					{name:'PhoneExtension',index:'6',width:90,resizable:false},
					{name:'isBPCreated',index:'7',width:140,resizable:false,hidden:true},
			],
			
  		    rowNum:20,
  		    rowList:[25,50,100],
  		    width:1127,
  		    height:300,
  		    pager:$('#leadrequestPager'),
			sortname:'1',
			viewrecords:true,
			sortorder:"desc",
  			toppager:false,
			gridview:true,
			gridComplete:function(id){
  				
  				for(var i=0;i<parseInt($('#leadrequestList').getGridParam('records'));++i){
					var ret = jQuery("#leadrequestList").jqGrid('getRowData',i+1);
				}
                $("tr.jqgrow:odd").addClass('allAltRowHoverClass');
                $("tr.jqgrow:even").addClass('allAltRowHoverClass');
                $("#leadrequestList").setSelection(0,false);	  
                $("#showLeadRequestDataButtons").html($("#leadrequestList").getGridParam('userData').operation);
                $('#leadrequestPager_right').addClass('hideall');				
                jQuery("#leadrequestPager .ui-pg-selbox").closest("td").after("<td dir='ltr'>No of rows </td>");
              
                	renderLeadRequestHTML();
                	$('#allLeadRequest').removeClass('hideall');
                	if($.trim($('#actId').val())!==''){
                		$('#allLead').addClass('hideall');
                		getViewDetail($('#actId').val());
                		$('#actId').val('');
                	}else if($.trim($('#createAct').val())==='createAct'){
                		$('#allLeadRequest').addClass('hideall');
                		$('#cmdCreateLeadRequest').trigger('click');
                		$('#createAct').val('');
                	}else if($.trim($('#createLeadReq').val())==='createLeadReq'){
                		$('#allLeadRequest').addClass('hideall');
                		$('#cmdCreateLeadRequest').trigger('click');
                		$('#createLeadReq').val('');
                	}
  			}, 
  			ondblClickRow: function(rowid){
  				$('#cmdEditLeadRequest').trigger('click');
  			}
  		}); 
	function getAllSelectOptions(){
		 var states = { '1': 'Alabama', '2': 'California', '3': 'Florida', 
		               '4': 'Hawaii', '5': 'London', '6': 'Oxford' };
		 return states;
	}
	function renderLeadRequestHTML()
	{
		  $('#cmdCreateLeadRequest').click(function()
		  {
			 $('#allLeadRequest').addClass('hideall');
			 $('#createLeadRequest').removeClass('hideall');
			 $('#newLeadRequestOrg').trigger('change');
			 newClearValues();
			 $.getJSON('/leadrequest/defaultSalesRep', function(data) 
						{
								var salesrepList ="";
								$.each(data.result, function(key, val) {
									 if(data.status==='success'){
										 salesrepList += '<option value="' + data.result[key].key + '">'
											+ data.result[key].value + '</option>';
									 }
									 $('#newLeadRequestAss').val(data.result[key].key);
									 $('#newLeadRequestAssigned').val(data.result[key].value).attr('disabled',true);
								});
						}); 
		  });
		  $('#cmdEditLeadRequest').click(function(){
			$("#editCreatBP").addClass("hideall");
			$('#editleadreqValidError').addClass('hideall');
			selRow = $("#leadrequestList").jqGrid('getGridParam', 'selrow');
			if (selRow !== null) {
				cell = $("#leadrequestList").jqGrid(
						'getRowData', selRow);
						$.getJSON('/leadrequest/getLeadRequestDetails?LeadRequestId='+(cell.LeadRequestId), function(data) {
							 $('#cmdEditLeadRequest').attr('disabled',true);
							if(data.status==='success'){
							 $.each(data.result, function(key, val){
								 $('#editLeadRequestDate').val(data.result[key].Date).attr("disabled",true);
								 $('#editLeadBusinessname').val(data.result[key].BName);
								 $('#editLeadRequestContactname').val(data.result[key].CName);
								 $('#leadrequestId').val(data.result[key].LRId);
								 $('#editLeadReuestEmail').val(data.result[key].Email);
								 $('#editLeadPrimaryPhone').val(data.result[key].Phone);
								 $('#editLeadPrimaryPhoneEx').val(data.result[key].PhoneEx);
								 $('#editleadrequestnotes').val(data.result[key].Note);
								 $('#editLeadRequestSource').val(data.result[key].Source);
								 $("#editLeadRequestAssigned option:first-child").attr("selected","selected");
								 $('#editleadrequestwesite').val(data.result[key].Website);
								 $('#createdby').val(data.result[key].Createdby);
								 $('#editisBPCreated').val(data.result[key].isBPCreated);
								 if(data.result[key].leadreqQualFlag==1){
								 $('#editLeadReqFlagYes').attr('checked', true);
								 }else{
								 $('#editLeadReqFlagYes').attr('checked', false);
								 }
								 var saleslead=data.result[key].SLeadId;
								 $.getJSON('/leadrequest/getSalesRepDetails', function(data){
										var dataList ="";
										$.each(data.result, function(key, val) {
											 if(data.status==='success'){
												 dataList += '<option value="' + data.result[key].key + '">'
													+ data.result[key].value + '</option>';
											 }
										});
										$('#editLeadRequestAssigned').html(dataList+options);
										$('#editLeadRequestAssigned').val(saleslead);
										$('#editLeadRequestAssigned').trigger('change');
								 });  	
								 if($('#editisBPCreated').val()==1){											
									$("input#editCreateBP").css('display','none');
								 }else{																							 
									$("input#editCreateBP").css('display','block');
								 }
							 		$('#allLeadRequest').addClass('hideall');
				         			$('#editLeadRequest').removeClass('hideall');
					         });
						 }else {
							 $('#dlgLeadRequestMsg').html('Error showing edit. Please try agian later.').dialog('open');
						 }
							 $('#cmdEditLeadRequest').attr('disabled',false);	
					 });
				}else{
					 $('#dlgLeadRequestMsg').html('Select a row to edit.').dialog('open');
				}
		});
	}
	
	$('#editCancelLeadRequest').click(function()
    {
		$('#editBackLeadRequest').trigger('click');
    });
	
	$('#editBackLeadRequest').click(function(){
		 $('#editLeadRequest').addClass('hideall');
		 $('#allLeadRequest').removeClass('hideall');
		 $('#editLeadRequestValidError').addClass('hideall');
		 $('#editNewcancelLink').addClass('hideall');
		 
		 $('#editLeadBusinessnameError').addClass('hideall');
         $('#editLeadExistsError').addClass('hideall');
         $('#editleadreqValidError').addClass('hideall');
         $('#editLeadReuestEmail').removeClass('ErrorField');
         $('#editLeadBusinessname').removeClass('ErrorField');
		 $('#editLeadRequestSource').val('');
		 $('#editLeadBusinessname').val('');
		 $('#editLeadRequestContactname').val('');
		 $('#editLeadPrimaryPhone').val('');
		 $('#editLeadPrimaryPhoneEx').val('');
		 $('#editLeadPrimaryEmail').val('');
		 $('#editleadrequestwesite').val('');
		 $('#editleadrequestnotes').val('');
		 $('#editLeadReqFlagYes').attr('checked', false);
			
	});
	function newClearValues(){
		$('#newLeadRequestSource').val('');
		$('#newleadreqValidError').addClass('hideall');
		$('#newLeadBusinessname').removeClass('ErrorField');
       	$('#newLeadBusinessnameError').addClass('hideall');
        $('#newLeadExistsError').addClass('hideall');
        $('#newleadreqValidError').addClass('hideall');
         
		$('#newLeadBusinessname').val('');
		$('#newLeadRequestContactname').val('');
		$('#newLeadPrimaryPhone').val('');
		$('#newLeadPrimaryPhoneEx').val('');
		$('#newLeadPrimaryEmail').val('');
		$('#newleadrequestwesite').val('');
		$('#newleadrequestnotes').val('');
		
		$('#newLeadRequestfrm').find(":input").not("[type=button],[type=hidden]").each(
		          function() {
		        	  $(this).val('');
		        	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
		 });
		 $('#newActivityValidError').addClass('hideall');
		 $("#newLeadRequestAssigned option:first-child").attr("selected","selected");
		 $("#newLeadRequestSource option:first-child").attr("selected","selected");
		 $('#newLeadRequestDate').val($("#hdn_time").val()).attr("disabled",true);
		 $('#newLeadBusinessname').removeClass("ErrorField");
		 $('#newLeadRequestContactname').removeClass("ErrorField");
		 $('#newLeadPrimaryPhone').removeClass("ErrorField");
		 $('#newLeadReuestEmail').removeClass("ErrorField");
		 $("#newLeadRequestError").css('display','none');
		 $("#newLeadRequestError").val('');
	}
	$('#newBackLeadRequest').click(function(){
		 $('#createLeadRequest').addClass('hideall');
		 $('#allLeadRequest').removeClass('hideall');
		 newClearValues();
	});
	
	$('.editcancelLink').click(function(){
		$('#dlgRemoveActivityMsg').dialog('open');
	});
	$('#newCancelLeadRequest').click(function(){
		$('#newBackLeadRequest').trigger('click');
	});	
	});
});