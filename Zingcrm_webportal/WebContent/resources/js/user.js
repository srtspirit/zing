$(document).ready(function()
{
	
$(function()
{
	
	 $.extend($.ui.dialog.prototype.options, {
			modal : true,
			bgiframe : true,
			resizable : false,
			autoOpen : false
		});
	 
	 function validateEmail(email) {
         //Email pattern
         var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
         return re.test(email);
     }
	 
	 $("#newUserPhone").mask("(999) 999?-9999");
	 $("#editUserPhone").mask("(999) 999?-9999");
	 


	 $("#newUserPhone").on("blur", function() {
	 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

	 if( last.length == 3 ) {
	 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
	 var lastfour = move + last;

	 var first = $(this).val().substr( 0, 9 );

	 $(this).val( first + '-' + lastfour );
	 }
	 });
	 
	 $("#editUserPhone").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });
	 
	 
	 
	 $('#tabs').fadeIn(10);
		$( "#tabs" ).tabs();	
	   $('#dlgsMemberMsg').dialog({
			buttons : {
				"Ok" : function() {
					$('#dlgsMemberMsg').dialog('close');
				}
			}
		});
	   
	   $('#dlgDelMemberMsg').dialog({
			buttons : {
				"Yes" : function() {
					$('#userList').attr('disabled',true);
					$
					.ajax({
						url : '/user/deleteUser',
						type : 'POST',
						data : 'userId='+$.trim(cell.id),
						success : function(data) {
							$('#cmdDeleteUser').attr('disabled',false);
							if($.trim(data)=='success')
							{
								$("#userList").trigger(
												'reloadGrid');
								 $('#dlgsMemberMsg').html('User deleted successfully.').dialog('open');
								
							}	
							else
							{
								 $('#dlgsMemberMsg').html('Error deleting user. Please try again later.').dialog('open');
							}
						 $('#dlgDelMemberMsg').dialog('close');
						}
					});
				},
				"No" : function() {
					$('#dlgDelMemberMsg').dialog('close');
				}
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
	   
	   $('#newUserEmail').blur(function() // user name validation
		{
		   if ($.trim($('#newUserEmail').val().length) != 0) {
				//$("#newUserEmailError").addClass("hideall");
			   errordisplay("",$('#newUserEmail').attr('id'));
			   $.ajax({
		           url: '/user/getEmailNameValidation',
		           data: 'email='+$.trim($('#newUserEmail').val()),
		           success: function(data) {
		          	 if(data==='exists')
		          	{
		          		$('#newUserEmailValidError').removeClass('hideall');
		          	}
		          	 else
		          	{
		          		$('#newUserEmailValidError').addClass('hideall');
		          	}
		           }
				});
			} else {
				 errordisplay("Required field",$('#newUserEmail').attr('id'));
				//$("#newUserEmailError").removeClass("hideall");
			}
		});
	   
	   $('#editUserEmail').blur(function() // user name validation
				{
				   if ($.trim($('#editUserEmail').val().length) != 0) {
						//$("#editUserEmailError").addClass("hideall");
						 errordisplay("",$('#editUserEmail').attr('id'));
					   $.ajax({
				           url: '/user/getEditEmailNameValidation',
				           data: 'email='+$.trim($('#editUserEmail').val())+'&userId='+$.trim($('#editUserId').val()),
				           success: function(data) {
				          	 if(data==='exists')
				          	{
				          		$('#editUserEmailValidError').removeClass('hideall');
				          	}
				          	 else
				          	{
				          		$('#editUserEmailValidError').addClass('hideall');
				          	}
				           }
						});
					} else {
						errordisplay("Required field",$('#editUserEmail').attr('id'));
						//$("#editUserEmailError").removeClass("hideall");
					}
				});
			   
	   
	 			
	   
	   
	   $.getJSON('/user/orglist', function(data) 
		{
				var orgList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 orgList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#filterUserOrgId').html(orgList);
				$('#newActivityOrg').html(orgList);
				$('#editActivityOrg').html(orgList);
				UserGrid();
				
	    });  
	   
	   function getAllDetails()
	   {
		   $.getJSON('/user/getRole', function(data) 
			{
					var roleList ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 roleList += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#newUserRole').html(roleList);
					
		    });  
		   
		   $.getJSON('/user/getPhoneCode', function(data) 
			{
					var phoneCode ="";
					var defaultphonecode="";
					var canadaphonecode='40';
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 phoneCode += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
							 if(data.result[key].key==canadaphonecode){
								 defaultphonecode=data.result[key].value;
							 }
						 }
					});
					$('#newUserPhoneCode').html(phoneCode);
					$('#newUserPhoneCode option:selected').text(defaultphonecode);
					$('#newUserPhoneCode option:selected').val(canadaphonecode);
					
		    });  
		   
		   $.getJSON('/user/getTimeZone', function(data) 
			{
					var timezone ="";
					var defaulttimezone="";
					var esterntimezoneid='16';
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 timezone += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
							 if(data.result[key].key==esterntimezoneid){
								 defaulttimezone=data.result[key].value;
							 }
						 }
					});
					$('#newUserTimezone').html(timezone);
				    $('#newUserTimezone option:selected').text(defaulttimezone);
				    $('#newUserTimezone option:selected').val(esterntimezoneid);
					
		    });  
	   }
	   
	   
	   function getEditAllDetails(roleId,phoneId,timeZoneId)
	   {
		   $.getJSON('/user/getRole', function(data) 
			{
					var roleList ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 roleList += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#editUserRole').html(roleList);
					$('#editUserRole').val(roleId);
					
		    });  
		   
		   $.getJSON('/user/getPhoneCode', function(data) 
			{
					var phoneCode ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 phoneCode += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#editUserPhoneCode').html(phoneCode);
					$('#editUserPhoneCode').val(phoneId);
					
		    });  
		   
		   $.getJSON('/user/getTimeZone', function(data) 
			{
					var timezone ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 timezone += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#editUserTimezone').html(timezone);
					$('#editUserTimezone').val(timeZoneId);
					
		    });  
	   }
	   
	   $('#SearchUserbutton').click(function()	//Filter Click Function
				{
					
					$("#userList")
					.setGridParam(
							{
								url:'/user/gridview?orgId='+escape($("#filterUserOrgId").val())+'&userName='+escape("%"+$('#filterUserName').val()+"%"),
								page : 1,
								rowNum : $(
										"#userPager_center .ui-pg-selbox")
										.val(),
								sortname : '1',
								sortorder : 'desc'
							}).trigger("reloadGrid");
				}); 
	   
	   function UserGrid()
	   {
		   
			
			$("#userList").jqGrid({
	  			url:'/user/gridview?orgId='+escape($("#filterUserOrgId").val())+'&userName='+escape("%"+$('#filterUserName').val()+"%"),
	  			datatype:"xml",
	  			colNames:['Id','First Name','Last Name','Email','Role','Phone','Status'],
	 			colModel:[
						
						{name:'id',index:'1',width:100,resizable:true,hidden:true},
						{name:'first',index:'2',width:200,resizable:false},
						{name:'last',index:'3',width:180,resizable:false},
						{name:'email',index:'4',width:280,resizable:false},
						{name:'role',index:'5',width:150,resizable:false},
						{name:'phone',index:'6',width:150,resizable:false},
						{name:'status',index:'7',width:140,resizable:false,hidden:true},
				],
	  		  	
	  		    rowNum:20,
	  		    width:1127,
	  		    
		  		height:300,
		  		rowList:[20,40,60,80,100],
				pager:$('#userPager'),
				sortname:'2',
				viewrecords:true,
				sortorder:"desc",
	  			toppager:false,
				gridview:true,
	  			gridComplete:function(id){
	  			
	                $("tr.jqgrow:odd").addClass('allAltRowHoverClass');
	                $("tr.jqgrow:even").addClass('allAltRowHoverClass');
	                $("#userList").setSelection(0,false);	  
	                $("#showUserDataButtons").html($("#userList").getGridParam('userData').operation);
	                $('#userPager_right').addClass('hideall');				
	                jQuery("#userPager .ui-pg-selbox").closest("td").after("<td dir='ltr'>No of rows </td>");
	              
	                	renderUserApprovalHTML();
	  			}, 
	  			ondblClickRow: function(rowid){
	  				$('#cmdEditUser').trigger('click');
	  			}
	  		}); 
	   }
	   
	   function renderUserApprovalHTML()
	   {
		   
		   $('#cmdCreateUser').click(function()
		   {
			   $('#newBackUser').trigger('click');
			   getAllDetails();
			   $('#allUser').addClass('hideall');
    			$('#createUser').removeClass('hideall');
		   });
		   
		   $('#cmdViewUser').click(function()
		   {

				selRow = $("#userList").jqGrid('getGridParam', 'selrow');
				if (selRow !== null) {
					cell = $("#userList").jqGrid(
							'getRowData', selRow);
					
					
							
							$.getJSON('/user/getUserDetails?userId='+(cell.id), function(data) {
								 $('#cmdViewUser').attr('disabled',true);
							 if(data.status==='success')
							 {
								 $.each(data.result, function(key, val) 
						         {
									 $('#viewFName').text(data.result[key].FirstName);
									 $('#viewLName').text(data.result[key].LastName);	
									 $('#viewRole').text(data.result[key].RoleName);
									 $('#viewEmail').text(data.result[key].Email);
									 $('#viewPhone').text(data.result[key].Phone);
									 if(data.result[key].Status)
										 {
										 $('#viewStatus').text('Active');
										 }
									 else
										 {
										 $('#viewStatus').text('InActive');
										 }
									 $('#viewTimezone').text(data.result[key].Timezone);
				         			$('#allUser').addClass('hideall');
				         			$('#viewUser').removeClass('hideall');
						         });
								 	
							 }
							 else
							 {
								 $('#dlgsMemberMsg').html('Error showing view. Please try agian later.').dialog('open');
							 }
							 $('#cmdViewUser').attr('disabled',false);
						 });
					 
					}
					else
					{
						 $('#dlgsMemberMsg').html('Select a row to view.').dialog('open');
					}
		   });
		   
		   $('#cmdEditUser').click(function()
				   {

						selRow = $("#userList").jqGrid('getGridParam', 'selrow');
						if (selRow !== null) {
							cell = $("#userList").jqGrid(
									'getRowData', selRow);
							if($.trim(cell.role)!='SuperAdmin')
							{
								$.getJSON('/user/getUserDetails?userId='+(cell.id), function(data) {
									 $('#cmdEditUser').attr('disabled',true);
								 if(data.status==='success')
								 {
									 $.each(data.result, function(key, val) 
							         {
										 $('#editActivityOrg').val(data.result[key].Org);
										 getEditAllDetails(data.result[key].RoleId,data.result[key].PhoneCode,data.result[key].TimezoneId)
										 $('#editUserId').val(cell.id);
										 $('#editFirstName').val(data.result[key].FirstName);
										 $('#editLastName').val(data.result[key].LastName);	
										 $('#editUserEmail').val(data.result[key].Email);
										 $('#editUserPhone').val(data.result[key].Phone);
										 $('#editContUserId').val(data.result[key].ContId);
					         			$('#allUser').addClass('hideall');
					         			$('#editUser').removeClass('hideall');
							         });
									 	
								 }
								 else
								 {
									 $('#dlgsMemberMsg').html('Error showing edit. Please try agian later.').dialog('open');
								 }
								 $('#cmdEditUser').attr('disabled',false);
								});
							}
							else
							{
								$('#dlgsMemberMsg').html("You can't edit higher role.").dialog('open');
							}
									
								 
							 
							}
							else
							{
								 $('#dlgsMemberMsg').html('Select a row to edit.').dialog('open');
							}
				   });
		   
		   $('#cmdDeleteUser').click(function()		//Delete click function
				      {
				          selRow = $("#userList").jqGrid(
								'getGridParam', 'selrow');
							if (selRow !== null) {
								cell = $("#userList").jqGrid(
										'getRowData', selRow);
									
									$('#dlgDelMemberMsg').dialog('open');
							} else {
								 $('#dlgsMemberMsg').html('Select a row to delete.').dialog('open');
										
							}
				      });
	   }
	   
	   $('#viewBackMember').click(function()
	   {
			$('#viewUser').addClass('hideall');
		   $('#allUser').removeClass('hideall');
	   });
	   
	   $('#newCancelUser').click(function()
			     {
					   $('#newBackUser').trigger('click');
			     });
	   
	   $('#newBackUser').click(function()
	   {
			$('#createUser').addClass('hideall');
			   $('#allUser').removeClass('hideall');
			   $("#newUserRole option:first-child").attr("selected","selected");
			   $("#newUserPhoneCode option:first-child").attr("selected","selected");
			   $("#newUserTimezone option:first-child").attr("selected","selected");
			   $('#newUserfrm').find(":input").not("[type=button],[type=hidden]").each(
			          function() {
			        	  $(this).val('');
			        	  errordisplay("",$(this).attr('id'));
			        	 // $('#'+$(this).attr('id')+'Error').addClass('hideall');
			          });
					 $('#newUserEmailValidError').addClass('hideall');
						$('#newUserconfirmPasswordValidError').addClass('hideall');
						$('#newUserEmailValError').addClass('hideall');
   });
	   
	   $('#editBackUser').click(function()
			   {
				$('#editUser').addClass('hideall');
				   $('#allUser').removeClass('hideall');
				   $('#editUserfrm').find(":input").not("[type=button],[type=hidden]").each(
				          function() {
				        	  $(this).val('');
				        	  errordisplay("",$(this).attr('id'));
				        	 // $('#'+$(this).attr('id')+'Error').addClass('hideall');
				          });
						 $('#editUserEmailValidError').addClass('hideall');
		   });
	   
	   $('#editCancelUser').click(function()
	   {
		   $('#editBackUser').trigger('click');
	   });
	   
	   $('#viewCloseUser').click(function()
     {
		   $('#viewBackMember').trigger('click');
     });
	   
		$('#newUserfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
				{
						 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
							  // $('#'+$(this).attr('id')+'Error').removeClass('hideall');
							 errordisplay("Required field",$(this).attr('id'));
		                  } else {
		                	  //$('#'+$(this).attr('id')+'Error').addClass('hideall');
		                	  errordisplay("",$(this).attr('id'));
		                	  if($(this).attr('id')==='newUserPassword' || $(this).attr('id')==='newUserconfirmPassword')
                              {
	                               	if($.trim($('#newUserPassword').val())!=''&&  $.trim($('#newUserconfirmPassword').val())!='')
	                               	{
	                         	           if($.trim($('#newUserPassword').val())!==$.trim($('#newUserconfirmPassword').val()))
	                         	           {
	                         	           	$('#newUserconfirmPasswordValidError').removeClass('hideall');
	                         	           }
	                         	           else
	                         	           {
	                         	           	$('#newUserconfirmPasswordValidError').addClass('hideall');
	                         	           }
	                               	}
                              }
		                	  if($(this).attr('id')==='newUserEmail')
		                       	{
		                	
		                           	if(validateEmail($(this).val()))
		                       		{
		                           		$('#'+$(this).attr('id')+'ValError').addClass('hideall');
		                       		}
		                           	else
		                       		{
		                           		$('#'+$(this).attr('id')+'ValError').removeClass('hideall');
		                       		}
		                       	}
		                  }
				});
		
		$('#editUserfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
		{
				 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
					 //  $('#'+$(this).attr('id')+'Error').removeClass('hideall');
					 errordisplay("Required field",$(this).attr('id'));
                  } else {
                	  errordisplay("",$(this).attr('id'));
                	 //$('#'+$(this).attr('id')+'Error').addClass('hideall');
                	  if($(this).attr('id')==='editUserEmail')
                     	{
              	
                         	if(validateEmail($(this).val()))
                     		{
                         		$('#'+$(this).attr('id')+'ValError').addClass('hideall');
                     		}
                         	else
                     		{
                         		$('#'+$(this).attr('id')+'ValError').removeClass('hideall');
                     		}
                     	}
                  }
		});
		
	   
	   $('#newSubmitUser').click(function()
	   {
			var flag=true;
			 $('#newUserfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
		    		   
				       function() {
				           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
				            	   //$('#'+$(this).attr('id')+'Error').removeClass('hideall');
				        	   errordisplay("Required field",$(this).attr('id'));
				            	   	flag = false;
				           } else {
				        	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
				        	   errordisplay("",$(this).attr('id'));
				        	   if($(this).attr('id')==='newUserPassword' || $(this).attr('id')==='newUserconfirmPassword')
                               {
	                               	if($.trim($('#newUserPassword').val())!=''&&  $.trim($('#newUserconfirmPassword').val())!='')
	                               	{
	                         	           if($.trim($('#newUserPassword').val())!==$.trim($('#newUserconfirmPassword').val()))
	                         	           {
	                         	           	$('#newUserconfirmPasswordValidError').removeClass('hideall');
	                         	           	flag = false;
	                         	           }
	                         	           else
	                         	           {
	                         	           	$('#newUserconfirmPasswordValidError').addClass('hideall');
	                         	           }
	                               	}
                               }
				        	   
				        	   if($(this).attr('id')==='newUserEmail')
		                       	{
		                	
		                           	if(validateEmail($(this).val()))
		                       		{
		                           		
		                           		$('#'+$(this).attr('id')+'ValError').addClass('hideall');
		                       		}
		                           	else
		                       		{
		                           		$('#'+$(this).attr('id')+'ValError').removeClass('hideall');
		                       		}
		                       	}
				           }
				       });
			 if(!$('#newUserEmailValidError').hasClass('hideall'))
			 {
				 flag=false;
			 }
			 if(!$('#newUserconfirmPasswordValidError').hasClass('hideall'))
			 {
				 flag=false;
			 }
			 if(!$('#newUserEmailValError').hasClass('hideall'))
			 {
				 flag=false;
			 }
			 if(flag)
			 {
				 $('#newSubmitUser').attr('disabled',true);
					$.ajax({
				           url: '/user/createUser',
				           type:'post',
				           data:$('#newUserfrm').serialize(),
				           success: function(data)
				           {
				        	   $('#newSubmitUser').attr('disabled',false);
				        	  if(data==='success')
			        		  {
				        		  $('#dlgsMemberMsg').html('User created successfully').dialog('open');
				        		  $("#userList").trigger("reloadGrid");
				        		  $('#newBackUser').trigger('click');
			        		  }
				        	  else
			        		  {
				        		  $('#dlgsMemberMsg').html('Error creating user. Please try again later.').dialog('open');
			        		  }
				           }
					});
			 }
			 
   
	   });
	   
	   
	   $('#editSubmitUser').click(function()
			   {
					var flag=true;
					 $('#editUserfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
				    		   
						       function() {
						           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
						            	 //  $('#'+$(this).attr('id')+'Error').removeClass('hideall');
						        	   errordisplay("Required field",$(this).attr('id'));
						            	   	flag = false;
						           } else {
						        	   errordisplay("",$(this).attr('id'));
						        	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
						        	   if($(this).attr('id')==='editUserEmail')
				                       	{
				                	
				                           	if(validateEmail($(this).val()))
				                       		{
				                           		$('#'+$(this).attr('id')+'ValError').addClass('hideall');
				                       		}
				                           	else
				                       		{
				                           		$('#'+$(this).attr('id')+'ValError').removeClass('hideall');
				                       		}
				                       	}
						           }
						       });
					 if(!$('#editUserEmailValidError').hasClass('hideall'))
					 {
						 flag=false;
					 }
					 if(!$('#editUserEmailValError').hasClass('hideall'))
					 {
						 flag=false;
					 }
					 if(flag)
					 {
						 $('#editSubmitUser').attr('disabled',true);
							$.ajax({
						           url: '/user/editUser',
						           type:'post',
						           data:$('#editUserfrm').serialize(),
						           success: function(data)
						           {
						        	   $('#editSubmitUser').attr('disabled',false);
						        	  if(data==='success')
					        		  {
						        		  $('#dlgsMemberMsg').html('User updated successfully').dialog('open');
						        		  $("#userList").trigger("reloadGrid");
						        		  $('#editBackUser').trigger('click');
					        		  }
						        	  else
					        		  {
						        		  $('#dlgsMemberMsg').html('Error updating user. Please try again later.').dialog('open');
					        		  }
						           }
							});
					 }
					 
		   
			   });
});
});