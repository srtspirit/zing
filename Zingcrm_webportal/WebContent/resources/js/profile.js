$(document).ready(function()
{
	var optionsAll="<option value=%>All</option>";
	var options="<option value=0>Select</option>";
	var hei = "100%";
	
$(function()
{
	
	 $.extend($.ui.dialog.prototype.options, {
			modal : true,
			bgiframe : true,
			resizable : false,
			autoOpen : false
		});
	 
	 
	 $("#editUserPhone").mask("(999) 999?-9999");
	 


	 
	 $("#editUserPhone").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });
	 
	   $('#dlgsProfileMsg').dialog({
			buttons : {
				"Ok" : function() {
					$('#dlgsProfileMsg').dialog('close');
				}
			}
		});
	   
	   $('#editUserEmail').blur(function() // user name validation
		{
		   if ($.trim($('#editUserEmail').val().length) != 0) {
				$("#editUserEmailError").addClass("hideall");
			   $.ajax({
		           url: '/user/getEditEmailNameValidation',
		           data: 'email='+$.trim($('#editUserEmail').val())+'&userId='+$.trim($('#editUserId').val()),
		           success: function(data) {
		          	 if(data==='exists')
		          	{
		          		//$('#editUserEmailValidError').removeClass('hideall');
		          		 errordisplay("Email already exists",$('#editUserEmail').attr('id'));
		          	}
		          	 else
		          	{
		          		//$('#editUserEmailValidError').addClass('hideall');
		          		errordisplay("",$('#editUserEmail').attr('id'));
		          	}
		           }
				});
			} else {
				errordisplay("Required field",$('#editUserEmail').attr('id'));
				//$("#editUserEmailError").removeClass("hideall");
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
				
	    });  
	   
	   function getUserDetails()
		{
									
			$.getJSON('/user/getSessionUserDetails', function(data) {
			 if(data.status==='success')
			 {
				 $.each(data.result, function(key, val) 
		         {
					 $('#editActivityOrg').val(data.result[key].Org);
					 getEditAllDetails(data.result[key].RoleId,data.result[key].PhoneCode,data.result[key].TimezoneId)
					 $('#editUserId').val(data.result[key].UserId);
					 $('#editFirstName').val(data.result[key].FirstName);
					 $('#editLastName').val(data.result[key].LastName);	
					 $('#editUserEmail').val(data.result[key].Email);
					 $('#editUserPhone').val(data.result[key].Phone);
					 $('#editContUserId').val(data.result[key].ContId);
		         });
				 	
			 }
			 else
			 {
				 $('#dlgsMemberMsg').html('Error showing edit. Please try agian later.').dialog('open');
			 }
			});
		}
	   
	   getUserDetails();
	   function getEditAllDetails(roleId,phoneId,timeZoneId)
	   {
		   $.getJSON('/user/getEditRole', function(data) 
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
		
								
	   $('#editCancelUser').click(function()
	{
		   $('#editUserfrm').find(":input").not("[type=button],[type=hidden]").each(
		          function() {
		        	//  $('#'+$(this).attr('id')+'Error').addClass('hideall');
		        	  errordisplay("",$(this).attr('id'));
		          });
				// $('#editUserEmailValidError').addClass('hideall');
				// getUserDetails();
				 window.location.href="/index";
	   });
	   
		
		$('#editUserfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
		{
				 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
					  // $('#'+$(this).attr('id')+'Error').removeClass('hideall');
					  errordisplay("Required field",$(this).attr('id'));
                  } else {
                	  //$('#'+$(this).attr('id')+'Error').addClass('hideall');
                	  errordisplay("",$(this).attr('id'));
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
						           }
						           //editUserPhoneCode
						           else {
						        	   errordisplay("",$(this).attr('id'));
						        	   //$('#'+$(this).attr('id')+'Error').addClass('hideall');
						           }
						       });
					/* if(!$('#editUserEmailValidError').hasClass('hideall'))
					 {
						 flag=false;
					 }*/
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
						        		  $('#dlgsProfileMsg').html('Profile updated successfully').dialog('open');
						        		  //$('#editCancelUser').trigger('click');
					        		  }
						        	  else
					        		  {
						        		  $('#dlgsProfileMsg').html('Error updating profile. Please try again later.').dialog('open');
					        		  }
						           }
							});
					 }
					 
		   
			   });
});
});