 $(document).ready(function()
{
	 var createproject=0;
	 var creProject=0;
	 var newleadid="";
	 var editleadid="";
	 $('#editLeads').addClass('hideall');
	 $('#viewLeads').addClass('hideall');
	 if($.trim($('#createLead').val())==='createLead')
		{
			$('#allLead').addClass('hideall');
		}
	 else
		 {
		 $('#allLead').removeClass('hideall');
		 }
	var optionsAll="<option value=%>All</option>";
	var options="<option value=0>Select</option>";
	
	 $.extend($.ui.dialog.prototype.options, {
			modal : true,
			bgiframe : true,
			resizable : false,
			autoOpen : false
		});
	 
	   function validateEmail(email) {
		   
	           var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	           return re.test(email);
	       }
	
		   function isValidURL(url){
			    //var RegExp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
			   if(/(^|\s)((https?:\/\/)?[\w-]+(\.[\w-]+)+\.?(:\d+)?(\/\S*)?)/gi.test(url)) {
		            return true;
		        } else {
		            return false;
		        }
			}
		  /* $('#dlgleadsuccessMsg').dialog({ 
			  open: function(event, ui) {
			   setTimeout(function(){
			   $('#dlgleadsuccessMsg').dialog('close');
			   }, 5000);
			   }
			   });*/
		   
		   $('#dlgleadsuccessMsg').dialog({
				buttons : {
					"Ok" : function() {
						$('#dlgleadsuccessMsg').dialog('close');
						 $('#createOpp').val('createOpps');
						 $('#leadId').val(newleadid);
						 $('#cmdOPPleadId').trigger('click');
					}
				},
				
				close: function() { 
					$('#dlgleadsuccessMsg').dialog('close');
					 $('#createOpp').val('createOpps');
					 $('#leadId').val(newleadid);
					 $('#cmdOPPleadId').trigger('click');
				}
			});
		   
		   $('#dlgleadEditsuccessMsg').dialog({
				buttons : {
					"Ok" : function() {
						$('#dlgleadEditsuccessMsg').dialog('close');
						 $('#createOpp').val('createOpps');
						 $('#leadId').val(editleadid);
						 $('#cmdOPPleadId').trigger('click');
					}
				},
				
				close: function() { 
					$('#dlgleadEditsuccessMsg').dialog('close');
					 $('#createOpp').val('createOpps');
					 $('#leadId').val(editleadid);
					 $('#cmdOPPleadId').trigger('click');
				}
			});
		   
		   
		   $('#dlgResidentsMsg').dialog({
				buttons : {
					"Ok" : function() {
						$('#dlgResidentsMsg').dialog('close');
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
		   
		   function editerrordisplay(errorval,id){
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
	$('#newLeadfrm1').find(":input").not("[type=button],[type=hidden]").blur(function()
	{
			 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
				   $('#'+$(this).attr('id')+'Error').removeClass('hideall');
              } else {
            	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
            	  if($(this).attr('id')==='editWebsite')
	        		 {
	        		   
	        		   if(isValidURL($(this).val()))
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
	
	$('#newStep1PrimaryDetails').find(":input").not("[type=button],[type=hidden]").blur(function()
			{
	           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
	               $('#'+$(this).attr('id')+'Error').addClass('hideall');
	               $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	           } else {
	        	   
	        	   if($(this).attr('id')==='newLeadPrimaryEmail')
	        	   {
		                 	if(validateEmail($(this).val()))
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
	
	$('#newStep1SecondaryDetails').find(":input").not("[type=button],[type=hidden]").blur(function()
			{
		 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
             $('#'+$(this).attr('id')+'Error').addClass('hideall');
             $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
         } else {
      	   
      	   if($(this).attr('id')==='newLeadSecondaryEmail')
             	{
                 	if(validateEmail($(this).val()))
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
	
	
	$('#editStep1PrimaryDetails').find(":input").not("[type=button],[type=hidden]").blur(function()
			{
			if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
        /*    $('#'+$(this).attr('id')+'Error').addClass('hideall');
            $('#'+$(this).attr('id')+'ValidError').addClass('hideall');*/
				 editerrordisplay("",$(this).attr('id'));
	        } else {
	     	   
	     	   if($(this).attr('id')==='editLeadPrimaryEmail')
	            	{
	                	if(validateEmail($(this).val()))
	            		{
	                		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	                		 editerrordisplay("",$(this).attr('id'));
	            		}
	                	else
	            		{
	                		//$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
	                		 editerrordisplay("Email is not valid format",$(this).attr('id'));
	            		}
	            	}
	        }
	});
	
	
	$('#editStep1SecondaryDetails').find(":input").not("[type=button],[type=hidden]").blur(function()
			{
		 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
            /* $('#'+$(this).attr('id')+'Error').addClass('hideall');
             $('#'+$(this).attr('id')+'ValidError').addClass('hideall');*/
			 editerrordisplay("",$(this).attr('id'));
         } else {
      	   
      	   if($(this).attr('id')==='editLeadSecondaryEmail')
             	{
                 	if(validateEmail($(this).val()))
             		{
                 		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
             		}
                 	else
             		{
                 		//$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
                 		editerrordisplay("Email is not valid format",$(this).attr('id'));
                 		
             		}
             	}
         }
	});
	
	
	$('#editLeadfrm1').find(":input").not("[type=button],[type=hidden]").blur(function()
			{ 
					 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
						  // $('#'+$(this).attr('id')+'Error').removeClass('hideall');
						 editerrordisplay("Required Field",$(this).attr('id'));
		              } else {
		            	 // $('#'+$(this).attr('id')+'Error').addClass('hideall');
		            	  editerrordisplay("",$(this).attr('id'));
		            	  
		              }
			});
			
	
	$('#newLeadfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
	{
		 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
          //   $('#'+$(this).attr('id')+'Error').removeClass('hideall');
         //    $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
             errordisplay("Required Field",$(this).attr('id'));
             if($(this).attr('id')==='newAddress2')
      	   {
            	 errordisplay("",$(this).attr('id'));
      	   }
             else if($.trim($(this).attr('id'))==='customFlagYes')
      	   {
            	 errordisplay("",$(this).attr('id'));
      	   }
             else if($.trim($(this).attr('id'))==='privateFlagYes')
      	   {
            	 errordisplay("",$(this).attr('id'));
      	   }
             else
             {
          	   flag = false;
             }
         } else {
      	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
        	   errordisplay("",$(this).attr('id'));
      	 if($.trim($(this).attr('id'))==='newWebsite')
		 {
		   if(isValidURL($(this).val()))
     		{
         		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			   errordisplay("",$(this).attr('id'));
     		}
         	else
     		{
         		 errordisplay("Website is not valid format",$(this).attr('id'));
         		//$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
     		}
		   
		 }
         }
     });
 
	$('#editLeadfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
	{
		   if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
              /* $('#'+$(this).attr('id')+'Error').removeClass('hideall');
               $('#'+$(this).attr('id')+'ValidError').addClass('hideall');*/
			   editerrordisplay("Required Field",$(this).attr('id'));
               if($(this).attr('id')==='editAddress2')
        	   {
            	   editerrordisplay("",$(this).attr('id'));
        	   }
               else if($(this).attr('id')==='editFax')
        	   {
            	   editerrordisplay("",$(this).attr('id'));
        	   }
               else if($.trim($(this).attr('id'))==='editcustomFlagYes')
        	   {
            	   editerrordisplay("",$(this).attr('id'));
        	   }
               else if($.trim($(this).attr('id'))==='editPrivateFlagYes')
        	   {
            	   editerrordisplay("",$(this).attr('id'));
        	   }
               else
               {
            	   flag = false;
               }
           } else {
        	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
        	   editerrordisplay("",$(this).attr('id'));
        	   if($.trim($(this).attr('id'))==='editWebsite')
      		 {
      		   if(isValidURL($(this).val()))
           		{
               		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
      			 editerrordisplay("",$(this).attr('id'));
           		}
               	else
           		{
               		//$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
               	 editerrordisplay("Website is not valid format",$(this).attr('id'));
           		}
      		   
      		 }
           }
       });

	$('#newcreateProject').click(function()
			{
		creProject=1;
	    $('#step3NextLead').trigger('click');
			});
	 $('#createProject').click(function()
				{
		   createproject=1;
		    $('#editStep3NextLead').trigger('click');
				});
	
	function createFirstTab()
	{
		 $('#newStep1Lead').removeClass('hideall');
		 $('#newStep1PrimaryDetails').removeClass('hideall');
		 $('#newStep1SecondaryDetails').removeClass('hideall');
		 
		 $('.step3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px -44px');
		   }).trigger('mouseover');
		 
		 $('.step2')
		    // On mouse over, move the background on hover
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px -44px');
		   }).trigger('mouseover');
		 
		 $('.step1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px 0px');
		   }).trigger('mouseover');
	}
	
	
	function viewFirstTab()
	{
		 $('#viewStep1SecondaryDetails').addClass('hideall');
		 $('#viewStep1PrimaryDetails').addClass('hideall');
		 $('#viewStep1Lead').removeClass('hideall');
		 
		 $('#viewStep3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px -44px');
		   }).trigger('mouseover');
		 
		 $('#viewStep2')
		    // On mouse over, move the background on hover
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px -44px');
		   }).trigger('mouseover');
		 
		 $('#viewStep1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px 0px');
		   }).trigger('mouseover');
	}
	
	function editFirstTab()
	{
		 
		 $('#editStep1Lead').removeClass('hideall');
		 $('#editStep1PrimaryDetails').removeClass('hideall');
		 $('#editStep1SecondaryDetails').removeClass('hideall');
		
		 
		 $('#editStep3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px -44px');
		   }).trigger('mouseover');
		 
		 $('#editStep2')
		    // On mouse over, move the background on hover
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px -44px');
		   }).trigger('mouseover');
		 
		 $('#editStep1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px 0px');
		   }).trigger('mouseover');
	}
	
	function createSecondTab()
	{
		 $('#newStep1SecondaryDetails').addClass('hideall');
		 $('#newStep1Lead').addClass('hideall');
		 $('#newStep1PrimaryDetails').removeClass('hideall');
		 
		 $('.step1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px -44px');
		   }).trigger('mouseover');
		 
		 $('.step3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px -44px');
		   }).trigger('mouseover');
		 
		 $('.step2')
		    // On mouse over, move the background on hover
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px 0px');
		   }).trigger('mouseover');
		 
	}
	
	function viewSecondTab()
	{
		 $('#viewStep1SecondaryDetails').addClass('hideall');
		 $('#viewStep1Lead').addClass('hideall');
		 $('#viewStep1PrimaryDetails').removeClass('hideall');
		 
		 $('#viewStep1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px -44px');
		   }).trigger('mouseover');
		 
		 $('#viewStep3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px -44px');
		   }).trigger('mouseover');
		 
		 $('#viewStep2')
		    // On mouse over, move the background on hover
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px 0px');
		   }).trigger('mouseover');
		 
	}
	
	function editSecondTab()
	{
		 $('#editStep1SecondaryDetails').addClass('hideall');
		 $('#editStep1Lead').addClass('hideall');
		 $('#editStep1PrimaryDetails').removeClass('hideall');
		 
		 $('#editStep1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px -44px');
		   }).trigger('mouseover');
		 
		 $('#editStep3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px -44px');
		   }).trigger('mouseover');
		 
		 $('#editStep2')
		    // On mouse over, move the background on hover
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px 0px');
		   }).trigger('mouseover');
		 
	}

	$('.viewCancelLead').click(function()
	{
		$('#viewLeads').addClass('hideall');
		$('#allLead').removeClass('hideall');
		
		$('#viewLeadfrm').find('input, textarea, select').attr('disabled',false);
		$('#viewLeadfrm1').find('input, textarea, select').attr('disabled',false);
	 	$('#viewStep1PrimaryDetails').find('input,textarea,select').attr('disabled',false);
	 	$('#viewStep1SecondaryDetails').find('input,textarea,select').attr('disabled',false);
		 viewFirstTab();
	});
	
	function createThirdTab()
	{
		 $('#newStep1Lead').addClass('hideall');
		 $('#newStep1PrimaryDetails').addClass('hideall');
		 $('#newStep1SecondaryDetails').removeClass('hideall');
		 $('.step1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px -44px');
		   }).trigger('mouseover');
		 
		 $('.step2')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px -44px');
		   }).trigger('mouseover');
		 $('.step3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px 0px');
		   }).trigger('mouseover');
	}	  
	

	function viewThirdTab()
	{
		 $('#viewStep1Lead').addClass('hideall');
		 $('#viewStep1PrimaryDetails').addClass('hideall');
		 $('#viewStep1SecondaryDetails').removeClass('hideall');
		 $('#viewStep1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px -44px');
		   }).trigger('mouseover');
		 
		 $('#viewStep2')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px -44px');
		   }).trigger('mouseover');
		 $('#viewStep3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px 0px');
		   }).trigger('mouseover');
	}	  
	
	
	function editThirdTab()
	{
		 $('#editStep1Lead').addClass('hideall');
		 $('#editStep1PrimaryDetails').addClass('hideall');
		 $('#editStep1SecondaryDetails').removeClass('hideall');
		 $('#editStep1')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '0px -44px');
		   }).trigger('mouseover');
		 
		 $('#editStep2')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-198px -44px');
		   }).trigger('mouseover');
		 $('#editStep3')
		   .mouseover(function() {
		     $(this).css('backgroundPosition', '-396px 0px');
		   }).trigger('mouseover');
	}	  
	function newLeadClearValues()
	{
		  $("#newLeadSource option:first-child").attr("selected","selected");
		   $("#newLeadSource option:first-child").attr("selected","selected");
		   
		   $("#newLeadCountry option:first-child").attr("selected","selected");
		   $("#newLeadState option:first-child").attr("selected","selected");
		   $("#newLeadCity option:first-child").attr("selected","selected");
		   
		   $('#newLeadPrimaryEmailValidError').addClass('hideall');
		   $('#newLeadSecondaryEmailValidError').addClass('hideall');
		   $('#newLeadNameValidError').addClass('hideall');
		   $('#customFlagYes').attr('checked',false);
		   $('#privateFlagYes').attr('checked',true);
		   $('#newLeadfrm1').find(":input").not("[type=button],[type=hidden]").each(
		              function() {
		            	  $(this).val('');
		            	  editerrordisplay("",$(this).attr('id'));
		              });
		 $('#newLeadfrm').find(":input").not("[type=button],[type=hidden]").each(
              function() {
            	  $(this).val('');
            	  editerrordisplay("",$(this).attr('id'));
              });
		 
		 $('#newStep1PrimaryDetails').find(":input").not("[type=button],[type=hidden]").each(
	              function() {
	            	  $(this).val('');
	            	  editerrordisplay("",$(this).attr('id'));
	              });
		 
		 $('#newStep1SecondaryDetails').find(":input").not("[type=button],[type=hidden]").each(
	              function() {
	            	  $(this).val('');
	            	  editerrordisplay("",$(this).attr('id'));
	              });
		 $('#newStep1TertiaryContactDetails').find(":input").not("[type=button],[type=hidden]").each(
	              function() {
	            	  $(this).val('');
	            	  editerrordisplay("",$(this).attr('id'));
	              });
		 $('#newAdditionalInfo').find(":input").not("[type=button],[type=hidden]").each(
	              function() {
	            	  $(this).val('');
	            	  editerrordisplay("",$(this).attr('id'));
	              });
		 
		 createFirstTab();
	}
	function getCreateLead()
	{
		$('#allLead').addClass('hideall');
		$('#createLeads').removeClass('hideall');
		newLeadClearValues();
	}
	
	function getCreateLeads()
	{
		$('#allLead').addClass('hideall');
		$('#createLeads').removeClass('hideall');
	}
$(function()
{
	
	 $.extend($.ui.dialog.prototype.options, {
			modal : true,
			bgiframe : true,
			resizable : false,
			autoOpen : false
		});
	 
	 $("#newLeadPrimaryPhone").mask("(999) 999?-9999");
	 $("#newLeadSecondaryPhone").mask("(999) 999?-9999");
	 $("#newLeadPrimaryMobile").mask("(999) 999?-9999");
	 $("#newLeadSecondaryMobile").mask("(999) 999?-9999");
	 $("#newFax").mask("(999) 999?-9999");
	 $("#newLeadPrimaryPhoneEx").mask("99?999");
	 $("#newLeadSecondaryPhoneEx").mask("99?999");
	 $("#newLeadThirdPhone").mask("(999) 999?-9999");
	 $("#newLeadThirdConPhoneEx").mask("99?999");
	 $("#newLeadThirdConMobile").mask("(999) 999?-9999");
	 $("#editLeadPrimaryPhone").mask("(999) 999?-9999");
	 $("#editLeadSecondaryPhone").mask("(999) 999?-9999");
	 $("#editLeadPrimaryMobile").mask("(999) 999?-9999");
	 $("#editLeadSecondaryMobile").mask("(999) 999?-9999");
	 $("#editFax").mask("(999) 999?-9999");
	 $("#editLeadPrimaryPhoneExtension").mask("99?999");
	 $("#editLeadSecPhoneExt").mask("99?999");
	 $("#editLeadThirdConPhone").mask("(999) 999?-9999");
	 $("#editLeadThirdConPhoneEx").mask("99?999");
	 $("#editLeadThirdConMobile").mask("(999) 999?-9999");
	 
	
		

	 $("#newLeadPrimaryPhone").on("blur", function() {
	 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

	 if( last.length == 3 ) {
	 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
	 var lastfour = move + last;

	 var first = $(this).val().substr( 0, 9 );

	 $(this).val( first + '-' + lastfour );
	 }
	 });
	 
	 
	 $("#newLeadSecondaryPhone").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });
	 
	 $("#newLeadThirdPhone").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });

	 $("#newLeadPrimaryMobile").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });
	 
	 

	 $("#newLeadSecondaryMobile").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
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
		 });
	 
	 

	 $("#editLeadSecondaryPhone").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });
	 
	 

	 $("#editLeadPrimaryMobile").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });
	 
	 

	 $("#editLeadSecondaryMobile").on("blur", function() {
		 var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

		 if( last.length == 3 ) {
		 var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
		 var lastfour = move + last;

		 var first = $(this).val().substr( 0, 9 );

		 $(this).val( first + '-' + lastfour );
		 }
		 });
	 
	
	 
	 
	 function getFileUploader()
		{
			uploader = new qq.FileUploader({
	        element: document.getElementById('fileuploader'),
	        action: '/upload/saveExcel',
	        //Files with following extensions are only allowed
	        allowedExtensions: ['xls'],
	       // sizeLimit: 20971520, // Maximum filesize limit which works without any problems is 30MB. Current limit is set to 10MB = 10 * 1024 * 1024
	        params: {
				/*'utcdate': $('#utcdate').val(),
				'rand':getRandom(),
				'eventId':$('#eventId').val()*/
			},
		    multiple:false,
			onSubmit:function(id,fileName)
			{
				  if($('div#fileuploader ul.qq-upload-list').find('li:not(.hideall)').length>0)
		 			{
		 				$('#dlgResidentsMsg').html('Upload only one file at a time').dialog("open");
		 				$('div#fileuploader ul.qq-upload-list').append('<li class=hideall>a</li>');
		 				return false;
		 			} 
				//alert(fileName);
			},
	         onComplete: function(id, fileName, responseJSON)
	         {  
	        	 
	        	 $('#fileName').val(fileName);
	        		$('#cancelLink').removeClass('hideall');
					$('#uploadFileName').val(fileName);
	         }
	    });   
		}
	 
	 
		$('#cancelLink').click(function()
		{
			$("div#fileuploader ul.qq-upload-list li").addClass('hideall');	
			$('#uploadFileName').val('');
			$('#fileName').val('');
			$('#cancelLink').addClass('hideall');
			$('#uploadingText').html('');
		});
		
	 $("#newLeadDate").datepicker({
		 
		 
		   changeMonth: true,
			changeYear: true,
			 yearRange: "-100:+0",
			showAnim:'',dateFormat:'yy-mm-dd',
			//limitToToday:true,
			onChangeMonthYear: function (year,month,inst)
			{
				$("#newLeadDate").trigger('inst');
				$("#newLeadDate").datepicker("setDate",year+"-"+(month)+"-01");
			},
			onClose:function(dateText,inst)
			{
				$("#newLeadDate").trigger('blur');
				if($("#newLeadDate").val()==='')
				{
					errordisplay("Required Field",$("#newLeadDate").attr('id'));
				//	$("#newLeadDate"+'Error').removeClass('hideall');  
					
				}
				else
					{
					//$("#newLeadDate"+'Error').addClass('hideall');  
					errordisplay("",$("#newLeadDate").attr('id'));
					}
			}	
	 });
	 
	 
	 $("#editLeadDate").datepicker({
		 
		 
		   changeMonth: true,
			changeYear: true,
			 yearRange: "-100:+0",
			showAnim:'',dateFormat:'yy-mm-dd',
			//limitToToday:true,
			onChangeMonthYear: function (year,month,inst)
			{
				$("#editLeadDate").trigger('inst');
				$("#editLeadDate").datepicker("setDate",year+"-"+(month)+"-01");
			},
			onClose:function(dateText,inst)
			{
				$("#editLeadDate").trigger('blur');
				if($("#editLeadDate").val()==='')
				{
					$("#editLeadDate"+'Error').removeClass('hideall');  
				}
				else
					{
					$("#editLeadDate"+'Error').addClass('hideall');  
					}
			}	
	 });
	   
	 
	   $('#dlgLeadMsg').dialog({
			buttons : {
				"Ok" : function() {
					$('#dlgLeadMsg').dialog('close');
				}
			}
		});
	   
	   
	   $('#dlgDelLeadMsg').dialog({
			buttons : {
				"Yes" : function() {
					$('#cmdDeleteLead').attr('disabled',true);
					$
					.ajax({
						url : '/business/deleteLead',
						type : 'POST',
						data : 'leadId='+$.trim(cell.id),
						success : function(data) {
							$('#cmdDeleteLead').attr('disabled',false);
							if($.trim(data)=='success')
							{
								$("#leadList").trigger(
												'reloadGrid');
								 $('#dlgLeadMsg').html('Business Partner deleted successfully.').dialog('open');
								
							}	
							else
							{
								 $('#dlgLeadMsg').html('Error deleting business partner. Please try again later.').dialog('open');
							}
						 $('#dlgDelLeadMsg').dialog('close');
						}
					});
				},
				"No" : function() {
					$('#dlgDelLeadMsg').dialog('close');
				}
			}
		}); 
	   
	   
	   
	
		 
	
	$('#tabs').fadeIn(10);
	$( "#tabs" ).tabs();	

	
	
	 $.getJSON('/business/orglist', function(data) 
	{
			var orgList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 orgList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			$('#leadOrg').html(orgList).trigger('change');
			$('#editleadOrg').html(orgList);
			$('#filterLeadId').html(orgList+optionsAll);
			$('#viewleadOrg').html(orgList);
			LeadGrid();	
    });  
	 
	 $.getJSON('/business/countrylist', function(data) 
	{
			var countryList ="";
			//var defaultcountry="";
			//var canadacountryid='40';
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
					 countryList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			 $('#newLeadCountry').html(options+countryList);
			 $('#newLeadCountry').val(40);
			 $('#newLeadCountry').trigger('change');
			 $('#editLeadCountry').html(options+countryList).trigger('change');
    });  
	 
	 $('#newLeadCountry').change(function()
	{
		 if($(this).val()!=='0')
			 {
			 var countryid=$(this).val();
			 var canadaCountryid='40';
			 $.getJSON('/business/statelist?countryId='+$(this).val(), function(data) 
			{
			 var stateList ="";
			 var defaultstate="";
			 var quebecstateid='7';
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 stateList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
						 if(data.result[key].key==quebecstateid){
							 defaultstate=data.result[key].value;
						 }
					 }
					
				});
				$('#newLeadState').html(options+stateList);
				if(countryid===canadaCountryid){
					 $('#newLeadState option:selected').text(defaultstate);
					 $('#newLeadState option:selected').val(7);
				}
				 $('#newLeadState').trigger('change');
				
			});
			 }
		 else
			 {
			 $('#newLeadState').html(options);
			 $('#newLeadCity').html(options);
			 }
	});
	 
	 
	 $('#editLeadCountry').change(function()
				{
					 if($(this).val()!=='0')
						 {
						 $.getJSON('/business/statelist?countryId='+$(this).val(), function(data) 
						{
						 var stateList ="";
							$.each(data.result, function(key, val) {
								 if(data.status==='success'){
									 stateList += '<option value="' + data.result[key].key + '">'
										+ data.result[key].value + '</option>';
								 }
							});
							 $('#editLeadState').html(options+stateList).trigger('change');
						});
						 }
					 
					 else
					 {
					 $('#editLeadState').html(options);
					 $('#editLeadCity').html(options);
					 }
				});
				 
	 
	 $('#newLeadState').change(function()
				{
					 if($.trim($(this).val())!=='0')
						 {
						 $.getJSON('/business/citylist?stateId='+$(this).val(), function(data) 
						{
						 var cityList ="";
							$.each(data.result, function(key, val) {
								 if(data.status==='success'){
									 cityList += '<option value="' + data.result[key].key + '">'
										+ data.result[key].value + '</option>';
								 }
							
							});
							 $('#newLeadCity').html(options+cityList);
						});
						 }
					 else
					 {
					 $('#newLeadCity').html(options);
					 }
				});
	 
	 
	 $('#editLeadState').change(function()
				{
					 if($.trim($(this).val())!=='0')
						 {
						 $.getJSON('/business/citylist?stateId='+$(this).val(), function(data) 
						{
						 var cityList ="";
							$.each(data.result, function(key, val) {
								 if(data.status==='success'){
									 cityList += '<option value="' + data.result[key].key + '">'
										+ data.result[key].value + '</option>';
								 }
							
							});
							 $('#editLeadCity').html(options+cityList);
						});
						 }
					 else
					 {
					 $('#editLeadCity').html(options);
					 }
				});
	 
	 
	
	 
	 $.getJSON('/business/sourceList', function(data) 
	{
			var sourceList ="";
			$.each(data.result, function(key, val) {
				 if(data.status==='success'){
				sourceList += '<option value="' + data.result[key].key + '">'
						+ data.result[key].value + '</option>';
				 }
			});
			
			$('#newLeadSource').html(sourceList);
			$('#newLeadSource').val($('#createLeadSource').val());
			$('#editLeadSource').html(sourceList);
    });  
		 
		$('#leadOrg').change(function()
		{
			 $.getJSON('/business/repList?orgId='+escape($('#leadOrg option:selected').val()), function(data) 
			{
					var sourceList ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
						sourceList += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#newLeadAssignedRep').html(options+sourceList);
					$('#newLeadAssignedRep').val($('#createLeadRep').val());
		    });  
	 
		});
		
		function editOrg(orgId,repId,stateId,cityId,countryId)
		{
			 $.getJSON('/business/repList?orgId='+escape(orgId), function(data) 
			{
					var sourceList ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
						sourceList += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#editLeadAssignedRep').html(sourceList);
					$('#editLeadAssignedRep').val(repId);
		    });  
			 
			 
			 $.getJSON('/business/statelist?countryId='+countryId, function(data) 
			{
			 var stateList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 stateList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				 $('#editLeadState').html(options+stateList);
				 $('#editLeadState').val(stateId);
			});
		
			 
			 $.getJSON('/business/citylist?stateId='+stateId, function(data) 
			{
			 var cityList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 cityList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				
				});
				 $('#editLeadCity').html(options+cityList);
				 $('#editLeadCity').val(cityId);
			});
			 
			 
		}
		
		/*$('#newLeadfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
				{
						 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
							   $('#'+$(this).attr('id')+'Error').removeClass('hideall');
		                  } else {
		                	  $('#'+$(this).attr('id')+'Error').addClass('hideall');
		                	  if($(this).attr('id')==='newLeadPrimaryEmail' || $(this).attr('id')==='newLeadSecondaryEmail')
		                       	{
		                	
		                           	if(validateEmail($(this).val()))
		                       		{
		                           		$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
		                       		}
		                           	else
		                       		{
		                           		$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
		                       		}
		                       	}
		                  }
				});*/
		
		
		 $('#newLeadName').blur(function() // Section name validation
					{
					   if ($.trim($('#newLeadName').val().length) != 0) {
						//	$("#newLeadNameError").addClass("hideall");
						   $.ajax({
					           url: '/business/getLeadNameValidation',
					           data: 'orgId='+$.trim($('#leadOrg').val())+'&leadName='+$.trim($('#newLeadName').val()),
					           success: function(data) {
					          	 if(data==='exists')
					          	{
					          		 errordisplay("Business Partner already exists",$('#newLeadName').attr('id'));
					          		 flag=false;
					          		
					          	}else{
					          	 errordisplay("",$('#newLeadName').attr('id'));
					          	}
					           }
							});
						} else {
							 errordisplay("Required Field",$('#newLeadName').attr('id'));
						}
					});
		 
		 
		 $('#editLeadName').blur(function() // Section name validation
					{
					   if ($.trim($('#editLeadName').val().length) != 0) {
							$("#editLeadNameError").addClass("hideall");
						   $.ajax({
					           url: '/business/getEditLeadNameValidation',
					           data: 'orgId='+$.trim($('#leadOrg').val())+'&leadName='+$.trim($('#editLeadName').val())+'&leadId='+$('#editId').val(),
					           success: function(data) {
					          	 if(data==='exists')
					          	{
					          	//	$('#editLeadNameValidError').removeClass('hideall');
					          		editerrordisplay("Business Partner already exists",$('#editLeadName').attr('id'));
					          	}
					          	 else
					          	{
					          		$('#editLeadNameValidError').addClass('hideall');
					          	}
					           }
							});
						} else {
							$("#editLeadNameError").removeClass("hideall");
						}
					});
		 
		 
		
		
		$('#Searchbutton').click(function()	//Filter Click Function
		{
			
			$("#leadList")
			.setGridParam(
					{
						//url : '/beds/bedsGridView?countryId='+escape($('#filterBedsCountry').val())+'&stateId='+escape($('#filterBedsState').val())+'&cityId='+escape("%"+$('#filterBedsCity').val()+"%")+'&homeId='+escape($('#filterBedsHome').val())+'&sectionId='+escape($('#filterBedsSection').val()),
						url : '/business/gridview?companyName='+escape("%"+$("#searchcompany").val()+"%")+'&orgId='+escape($('#filterLeadId option:selected').val()),
						page : 1,
						rowNum : $(
								"#leadPager_center .ui-pg-selbox")
								.val(),
						sortname : '1',
						sortorder : 'desc'
					}).trigger("reloadGrid");
		}); 
	
	function LeadGrid()
	{	
		$("#leadList").jqGrid({
  			url:'/business/gridview?companyName='+escape("%"+$("#searchcompany").val()+"%")+'&orgId='+escape($('#filterLeadId option:selected').val()),
  			datatype:"xml",
  			colNames:['Id','Business Partner','Contact Name','department','City','Email Id','Mobile','Phone','Extension'],
 			colModel:[
					
			        {name:'id',index:'0',width:80,resizable:true,hidden:true},
					{name:'companyname',index:'1',width:120,resizable:false},
					{name:'contactname',index:'2',width:120,resizable:false},
					{name:'department',index:'3',width:80,resizable:false,hidden:true},
					{name:'city',index:'4',width:80,resizable:false},
					{name:'email',index:'5',width:100,resizable:false},
			        {name:'mobile',index:'6',width:80,resizable:false},
			        {name:'phone',index:'7',width:60,resizable:false},
			        {name:'phoneexten',index:'8',width:60,resizable:false}
					],
  		  	
  		    rowNum:20,
  		    width:1119,
  		    height:300,
  		    /*autowidth: true,*/
  		    /*shrinkToFit : true,*/
  		    rowList:[20,40,60,80,100],
			pager:$('#leadPager'),
			sortname:'1',
			viewrecords:true,
			sortorder:"desc",
  			toppager:false,
			gridview:true,
			afterInsertRow: function (rowid, rowData, rowElm) 
  			{
                $("#leadList").setCell(rowid, 'date', '', '', { 'title': rowData.timezone }, false);
			},
  			gridComplete:function(id){
  			
                $("tr.jqgrow:odd").addClass('allAltRowHoverClass');
                $("tr.jqgrow:even").addClass('allAltRowHoverClass');
                $("#leadList").setSelection(0,false);	  
              /*  alert($("#leadList"));*/
               
                $("#showLeadDataButtons").html($("#leadList").getGridParam('userData').operation);
                $('#leadPager_right').addClass('hideall');				
                jQuery("#leadPager .ui-pg-selbox").closest("td").after("<td dir='ltr'>No of rows </td>");
              
  				renderLeadApprovalHTML();
  				$('#allLead').removeClass('hideall');
  				if($.trim($('#createLead').val())==='createLead')
  				{
  					$('#allLead').addClass('hideall');
  					getCreateLeads();
  					$('#createLead').val('');
  				}
  				
  			}, 
  			ondblClickRow: function(rowid){
  				$('#cmdEditLead').trigger('click');
  			}
  		}); 
	}
	
	function renderLeadApprovalHTML()
	{
		$('#cmdCreateLead').click(function()
		{
			
			getCreateLead();
			
		});
		
		$('#cmdViewLead').click(function()
		{
			
			selRow = $("#leadList").jqGrid('getGridParam', 'selrow');
			if (selRow !== null) {
				cell = $("#leadList").jqGrid(
						'getRowData', selRow);
				
					 $.getJSON('/business/getLeadDetails?leadId='+(cell.id), function(data) {
						 $('#cmdViewLead').attr('disabled',true);
						 if(data.status==='success')
						 {
							 $.each(data.result, function(key, val) 
					         {
								 $('#viewleadOrg').val(data.result[key].Org);
								 $('#viewLeadDate').val(data.result[key].Date);
								 $('#viewLeadSource').val(data.result[key].Source);
								 $('#viewLeadName').val(data.result[key].Name);
								 $('#viewLeadAssignedRep').val(data.result[key].RepName);
								 $('#viewAddress').val(data.result[key].Address);
								 $('#viewAddress2').val(data.result[key].Address2);
								 $('#viewLeadCity').val(data.result[key].City);
								 $('#viewLeadState').val(data.result[key].State);
								 $('#viewLeadCountry').val(data.result[key].Country);
								 $('#viewFax').val(data.result[key].Fax);
								 $('#viewWebsite').val(data.result[key].Website);
								 
								 $('#viewZipCode').val(data.result[key].ZipCode);
								 if(data.result[key].Flag==1)
									 {
									 $('#viewLeadFlag').attr('checked', true);
									 }
								 else
									 {
									 $('#viewLeadFlag').attr('checked', false);
									 }
								 
								 if(data.result[key].ActiveFlag==1)
								 {
								 $('#viewActiveLeadFlag').attr('checked', true);
								 }
							 else
								 {
								 $('#viewActiveLeadFlag').attr('checked', false);
								 }
								 
								 $('#viewLeadPrimaryName').val(data.result[key].PrimaryName);
								 $('#viewLeadPrimaryPhone').val(data.result[key].PrimaryPhone);
								 $('#viewLeadPrimaryPhoneExtension').val(data.result[key].PrimaryPhoneExtension);
								 $('#viewLeadPrimaryMobile').val(data.result[key].PrimaryMobile);
								 $('#viewLeadPrimaryEmail').val(data.result[key].PrimaryEmail);
								 $('#viewLeadPrimaryDept').val(data.result[key].PrimaryDept);
								 $('#viewLeadSecondaryName').val(data.result[key].SecondaryName);
								 $('#viewLeadSecondaryPhone').val(data.result[key].SecondaryPhone);
								 $('#viewLeadSecondaryMobile').val(data.result[key].SecondaryMobile);
								 $('#viewLeadSecondaryEmail').val(data.result[key].SecondaryEmail);
								 $('#viewLeadSecondaryDept').val(data.result[key].SecondaryDept);
								 $('#viewPrimaryLastName').val(data.result[key].PrimaryLastName);
								 $('#viewSecondaryLastName').val(data.result[key].SecondaryLastName);
								 
								 
					         });
							 	$('#viewLeadfrm').find('input,textarea,select').attr('disabled',true);
							 	$('#viewLeadfrm1').find('input,textarea,select').attr('disabled',true);
							 	$('#viewStep1PrimaryDetails').find('input,textarea,select').attr('disabled',true);
							 	$('#viewStep1SecondaryDetails').find('input,textarea,select').attr('disabled',true);
							 	
							 	$('.viewCancelLead').attr('disabled',false);
							 	$('#viewStep2NextLead').attr('disabled',false);
								$('#viewStep1BackLead').attr('disabled',false);
								$('#viewStep2BackLead').attr('disabled',false);
							 	$('#allLead').addClass('hideall');
								$('#viewLeads').removeClass('hideall');
						 }
						 else
						 {
							 $('#dlgLeadMsg').html('Error showing view. Please try agian later.').dialog('open');
						 }
						 $('#cmdViewLead').attr('disabled',false);
					 });
				}
				else
				{
					 $('#dlgLeadMsg').html('Select a row to view.').dialog('open');
				}
		});
		
		$('#cmdEditLead').click(function()
				{
					
					selRow = $("#leadList").jqGrid('getGridParam', 'selrow');
					if (selRow !== null) {
						cell = $("#leadList").jqGrid(
								'getRowData', selRow);
							 $.getJSON('/business/getLeadDetails?leadId='+(cell.id), function(data) {
								 $('#cmdEditLead').attr('disabled',true);
								 if(data.status==='success')
								 {
									 $.each(data.result, function(key, val) 
							         {
										 $('#editLeadDate').val(data.result[key].Date);
										 editOrg(data.result[key].Org,data.result[key].RepId,data.result[key].StateId,data.result[key].CityId,data.result[key].CountryId);
										 $('#contId').val(data.result[key].ContactId);
										 $('#editleadOrg').val(data.result[key].Org);
										 $('#editLeadCountry').val(data.result[key].CountryId);
										 $('#editFax').val(data.result[key].Fax);
										 $('#editWebsite').val(data.result[key].Website);
										 
										// $('#editLeadState').val(data.result[key].State);
										 $('#editCreatedBy').val(data.result[key].CreatedBy);
										 $('#editCreatedDate').val(data.result[key].CreatedDate);
										 $('#editLeadSource').val(data.result[key].SouceId);
										 $('#editLeadName').val(data.result[key].Name);
										 $('#editLeadAssignedRep').val(data.result[key].RepName);
										 $('#editAddress').val(data.result[key].Address);
										 $('#editAddress2').val(data.result[key].Address2);
										// $('#editLeadCity').val(data.result[key].City);
										 $('#editLeadZipcode').val(data.result[key].ZipCode);
										 $('#editLeadPrimaryName').val(data.result[key].PrimaryName);
										 $('#editLeadPrimaryPhone').val(data.result[key].PrimaryPhone);
										 $('#editLeadPrimaryPhoneExtension').val(data.result[key].PrimaryPhoneExtension);
										 $('#editLeadPrimaryMobile').val(data.result[key].PrimaryMobile);
										 $('#editLeadPrimaryEmail').val(data.result[key].PrimaryEmail);
										 $('#editLeadPrimaryDept').val(data.result[key].PrimaryDept);
										 $('#editLeadSecondaryName').val(data.result[key].SecondaryName);
										 $('#editLeadSecondaryPhone').val(data.result[key].SecondaryPhone);
										 $('#editLeadSecondaryMobile').val(data.result[key].SecondaryMobile);
										 $('#editLeadSecondaryEmail').val(data.result[key].SecondaryEmail);
										 $('#editLeadSecondaryDept').val(data.result[key].SecondaryDept);
										 $('#editStatus').val(data.result[key].Status);
										 $('#editPrimaryLastName').val(data.result[key].PrimaryLastName);
										 $('#editSecondaryLastName').val(data.result[key].SecondaryLastName);
										 $('#editId').val(cell.id);
										 $('#editLeadthirdConName').val(data.result[key].ThirdContName);
										 $('#editThirdConLastName').val(data.result[key].ThirdContLastName);
										 $('#editLeadThirdConPhone').val(data.result[key].ThirdContPhone);
										 $('#editLeadThirdConPhoneEx').val(data.result[key].ThirdContPhoneExt);
										 $('#editLeadThirdConMobile').val(data.result[key].ThirdContMobile);
										 $('#editLeadThirdConEmail').val(data.result[key].ThirdContEmail);
										 $('#editLeadThirdConDept').val(data.result[key].ThirdContDepart);
										 $('#editLeadCurSupplier').val(data.result[key].CurrentSup);
										 $('#editleadnotes').val(data.result[key].LeadNotes);
										 if(data.result[key].RoleId==='4')
										 {
											 $('#editLeadDate').attr('disabled',true);
											 $('#editLeadSource').attr('disabled',true);
											 $('#editLeadName').attr('disabled',true);
											 $('#editLeadAssignedRep').attr('disabled',true);
											 $('#editDate').val(data.result[key].Date);
											 $('#editSource').val(data.result[key].SouceId);
											 $('#editLead').val(data.result[key].Name);
											 $('#editRep').val(data.result[key].RepId);
											 
										 }
										 else
										 {
											 $('#editLeadDate').attr('disabled',false);
											 $('#editLeadSource').attr('disabled',false);
											 $('#editLeadName').attr('disabled',false);
											 $('#editLeadAssignedRep').attr('disabled',false);
											 $('#editDate').val("");
											 $('#editSource').val("");
											 $('#editLead').val("");
											 $('#editRep').val("");
										 }
										 if(data.result[key].Flag==1)
										 {
										 $('#editcustomFlagYes').attr('checked', true);
										 }
									 else
										 {
										 $('#editcustomFlagYes').attr('checked', false);
										 }
										 
										 if(data.result[key].ActiveFlag==1)
										 {
										 $('#editPrivateFlagYes').attr('checked', true);
										 }
									 else
										 {
										 $('#editPrivateFlagYes').attr('checked', false);
										 }
										 
							         });
									 $('#allLead').addClass('hideall');
									 $('#editLeads').removeClass('hideall');
								 }
								 else
								 {
									 $('#dlgLeadMsg').html('Error showing edit. Please try agian later.').dialog('open');
								 }
								 $('#cmdEditLead').attr('disabled',false);
							 });
						}
						else
						{
							 $('#dlgLeadMsg').html('Select a row to edit.').dialog('open');
						}
				});
		
		   $('#cmdDeleteLead').click(function()		//Delete click function
	      {
	          selRow = $("#leadList").jqGrid(
					'getGridParam', 'selrow');
				if (selRow !== null) {
					cell = $("#leadList").jqGrid(
							'getRowData', selRow);
						
						$('#dlgDelLeadMsg').dialog('open');
				} else {
					 $('#dlgLeadMsg').html('Select a row to delete.').dialog('open');
							
				}
	      });
		   
		   

		   $('#cmdOppLead').click(function()		//Delete click function
	      {
	          selRow = $("#leadList").jqGrid(
					'getGridParam', 'selrow');
				if (selRow !== null) {
					cell = $("#leadList").jqGrid(
							'getRowData', selRow);
					$('#leadId').val(cell.id);
					$('#cmdOPPleadId').trigger('click');
				} else {
					 $('#dlgLeadMsg').html('Select a row.').dialog('open');
							
				}
	      });
		   
		   $('#cmdExportLead').click(function()
		  	{
		  		$('#orgId').val($('#filterLeadId').val());
		  		$('#companyName').val("%"+$('#searchcompany').val()+"%");
		  		 $('#frmExportLead').submit();
		  	});
		   
		   
	   $('#cmdImportLead').click(function()
	  	{
		   $('#allLead').addClass('hideall');
	  		 $('#importOrgId').val($('#filterLeadId option:selected').val());
	  		 $('#frmImportLead').removeClass('hideall');
	  		$('#uploadFileName').html('');
	  		$('#uploadingText').html('');
	  		
	  		getFileUploader();
	  	});
		   
		   
	}
	

	
	$('#importBackLead').click(function()
	{
		 $('#frmImportLead').addClass('hideall');
		 $('#allLead').removeClass('hideall');
		 $("div#fileuploader ul.qq-upload-list li").addClass('hideall');	
			$('#uploadFileName').val('');
			$('#fileName').val('');
			$("#importOrgId option:first-child").attr("selected","selected");
			$('#uploadingText').html('');
			$('#cancelLink').addClass('hideall');
	});
	
	$('#backLead').click(function()
	{
		$('#createLeads').addClass('hideall');
		$('#allLead').removeClass('hideall');
		newLeadClearValues();
	});
	
	
	$('.cancelLead').click(function()
	{
		$('#backLead').trigger('click');
	});
	
	
	$('.editCancelLead').click(function()
	{
		
		   $("#editLeadCountry option:first-child").attr("selected","selected");
		   $("#editLeadState option:first-child").attr("selected","selected");
		   $("#editLeadCity option:first-child").attr("selected","selected");
		   $('#editLeads').addClass('hideall');
		   $('#allLead').removeClass('hideall');
		   $('#editLeadPrimaryEmailValidError').addClass('hideall');
		   $('#editLeadSecondaryEmailValidError').addClass('hideall');
		   $('#editLeadNameValidError').addClass('hideall');
		   $('#editcustomFlagYes').attr('checked',false);
		   $('#editPrivateFlagYes').attr('checked',false);
		   $('#editLeadfrm').find(":input").not("[type=button],[type=hidden]").each(
		              function() {
		            	  $(this).val('');
		            	  errordisplay("",$(this).attr('id'));
		              });
		 $('#editLeadfrm1').find(":input").not("[type=button],[type=hidden]").each(
	       function() {
	     	  $(this).val('');
	     	 errordisplay("",$(this).attr('id'));
	       });
	 
	 $('#editStep1PrimaryDetails').find(":input").not("[type=button],[type=hidden]").each(
           function() {
         	  $(this).val('');
         	 errordisplay("",$(this).attr('id'));
           });
	 
	 $('#editStep1SecondaryDetails').find(":input").not("[type=button],[type=hidden]").each(
           function() {
         	  $(this).val('');
         	 errordisplay("",$(this).attr('id'));
           });
	 $('#editStep1TertiaryContactDetails').find(":input").not("[type=button],[type=hidden]").each(
	           function() {
	         	  $(this).val('');
	         	 errordisplay("",$(this).attr('id'));
	           });
	 $('#editAdditionalInfo').find(":input").not("[type=button],[type=hidden]").each(
	           function() {
	         	  $(this).val('');
	         	 errordisplay("",$(this).attr('id'));
	           });
	 editFirstTab();
	});
	
	$('#step1BackLead').click(function()
	{
		createFirstTab();
	});
	
	$('#step2BackLead').click(function()
	{
		createSecondTab();
	});
	
	$('#viewStep1BackLead').click(function()
	{
		viewFirstTab();
	});
	
	$('#editStep1BackLead').click(function()
			{
				editFirstTab();
			});
			
	
	$('#viewStep2BackLead').click(function()
	{
		viewSecondTab();
	});
	
	$('#editStep2BackLead').click(function()
	{
		editSecondTab();
	});
	
	
	
	$('#editbackLead').click(function()
	{
		$('.editCancelLead').trigger('click');
	});
	
	$('#viewbackLead').click(function()
	{
		$('.viewCancelLead').trigger('click');
	});
	

	
	function secondaryDetailsvalidation(){
		var flag=true;
		$('#newStep1SecondaryDetails').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			              /* $('#'+$(this).attr('id')+'Error').addClass('hideall');
			               $('#'+$(this).attr('id')+'ValidError').addClass('hideall');*/
			        	   errordisplay("",$(this).attr('id'));
			           } else {
			        	   
			        	   if($(this).attr('id')==='newLeadSecondaryEmail')
	                       	{
	                           	if(validateEmail($(this).val()))
	                       		{
	                           	//	$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	                           	  errordisplay("",$(this).attr('id'));
	                       		}
	                           	else
	                       		{
	                           	//	$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
	                           	  errordisplay("Email is not valid format",$(this).attr('id'));
	                           		 flag = false;
	                       		}
	                       	}
			           }
			  });
		return flag;
	}
	
	function thirdContDetailsvalidation(){
		var flag=true;
		$('#newStep1TertiaryContactDetails').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			              /* $('#'+$(this).attr('id')+'Error').addClass('hideall');
			               $('#'+$(this).attr('id')+'ValidError').addClass('hideall');*/
			        	   errordisplay("",$(this).attr('id'));
			           } else {
			        	   
			        	   if($(this).attr('id')==='newLeadThirdConEmail')
	                       	{
	                           	if(validateEmail($(this).val()))
	                       		{
	                           	//	$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	                           	  errordisplay("",$(this).attr('id'));
	                       		}
	                           	else
	                       		{
	                           	//	$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
	                           	  errordisplay("Email is not valid format",$(this).attr('id'));
	                           		 flag = false;
	                       		}
	                       	}
			           }
			  });
		return flag;
	}
	$('#step3NextLead').click(function()
			{
		
				 if(createValidator())
				 {
					
					 if(createEmailValidator())
					 {
					
					if(secondaryDetailsvalidation()){
						
						 if(thirdContDetailsvalidation())
						 {
						
							 $('#step3NextLead').attr('disabled',true);
							 $('#cityName').val($('#newLeadCity option:selected').text());
							 $('#stateName').val($('#newLeadState option:selected').text());
							 $('#countryName').val($('#newLeadCountry option:selected').text());
								$.ajax({
							           url: '/business/createLead',
							           type:'post',
							           data:$('#newLeadfrm1').serialize()+'&'+$('#newLeadfrm').serialize()+'&customFlag='+$('#customFlagYes').is(':checked')+'&'+$('#newLeadPrimaryfrm').serialize()+'&'+$('#newLeadSecondaryfrm').serialize()+'&activeFlag='+$('#privateFlagYes').is(':checked')+'&'+$('#newLeadThirdfrm').serialize()+'&'+$('#newLeadAdditionalInfofrm').serialize(),
							           dataType: 'json',
							           success: function(data)
							           {
							        	   $('#step3NextLead').attr('disabled',false);
							        	  if($.trim(data.result)==='success')
						        		  {
							        		
							        		 
							        		 newleadid=data.value;
							        		  if(creProject==1){
							        			  $('#dlgleadsuccessMsg').html('Business Partner created successfully').dialog('open');
							        			 
							        		  }else{
							        		  $('#dlgLeadMsg').html('Business partner created successfully').dialog('open');
							        		  $("#leadList").trigger("reloadGrid");
							        		  $('#backLead').trigger('click');
							        		  }
						        		  }
							        	  else if($.trim(data.result)==='exists')
							        	  {
							        		  $('#dlgLeadMsg').html('Business partner name already exists').dialog('open');
							        		 // $('#newLeadNameValidError').removeClass('hideall');
							        		  editerrordisplay("Business partner name already exists",$('#editLeadName').attr('id'));
							        	  }
							        	  else
						        		  {
							        		  $('#dlgLeadMsg').html('Error creating business partner. Please try again later.').dialog('open');
						        		  }
							           }
								});
						    }
					   }
				  }
				 }
			});
	
	$('#editStep3NextLead').click(function()
			{
		var flag=true;
		if(editValidator())
		 {
			 if(editEmailVaidator())
				{
					 
				 if(editsecondarydetailValidator()){
					 if(editThirdContdetailValidator())
						 {
					 
					if($.trim($('#editDate').val())==='')
					 {
						 $('#editDate').val($('#editLeadDate').val());
					 }
					 if($.trim( $('#editSource').val())==='')
					 {
						 $('#editSource').val($('#editLeadSource option:selected').val());
					 }
					 if($.trim($('#editLead').val())==='')
					 {
						 $('#editLead').val($('#editLeadName').val());
					 }
					 if($.trim($('#editRep').val())==='')
					 {
						 $('#editRep').val($('#editLeadAssignedRep option:selected').val());
					 }
					 $('#eidtStep3NextLead').attr('disabled',true);
					 $('#editcityName').val($('#editLeadCity option:selected').text());
					 $('#editstateName').val($('#editLeadState option:selected').text());
					 $('#editcountryName').val($('#editLeadCountry option:selected').text());
						$.ajax({
					           url: '/business/updateLead',
					           type:'post',
					           data:$('#editLeadfrm').serialize()+'&customFlag='+$('#editcustomFlagYes').is(':checked')+'&'+$('#editLeadfrm1').serialize()+'&activeFlag='+$('#editPrivateFlagYes').is(':checked')+'&'+$('#editLeadPrimaryfrm').serialize()+'&'+$('#editLeadSecondaryfrm').serialize()+'&'+$('#editLeadThirdfrm').serialize()+'&'+$('#editLeadAdditionalInfofrm').serialize(),
					           success: function(data)
					           {
					        	   $('#eidtStep3NextLead').attr('disabled',false);
					        	  if(data==='success')
				        		  {
					        		 editleadid=$('#editId').val();
					        		  if(createproject==1){
					        			  $('#dlgleadEditsuccessMsg').html('Business Partner updated successfully').dialog('open');
					        			
					        		  }else{
					        		  $('#dlgLeadMsg').html('Business Partner updated successfully').dialog('open');
					        		  $("#leadList").trigger("reloadGrid");
					        		  $('#editbackLead').trigger('click');
					        		  }
				        		  }
					        	  else if($.trim(data)==='exists')
					        	  {
					        		  $('#dlgLeadMsg').html('Business partner already exists').dialog('open');
					        	//	  $('#editLeadNameValidError').removeClass('hideall');
					        		  editerrordisplay("Business Partner already exists",$(this).attr('id'));
					        	  }
					        	  else
				        		  {
					        		  $('#dlgLeadMsg').html('Error updating business partner. Please try again later.').dialog('open');
				        		  }
					           }
						});
				 }
				 }
				 }
		 }
			});
	
	function editsecondarydetailValidator(){
		var flag=true;
		 $('#editStep1SecondaryDetails').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			             //  $('#'+$(this).attr('id')+'Error').addClass('hideall');
			            //   $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			        	   editerrordisplay("",$(this).attr('id'));
			           } else {
			        	   
			        	   if($(this).attr('id')==='editLeadSecondaryEmail')
	                       	{
	                           	if(validateEmail($(this).val()))
	                       		{
	                           		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	                           	 editerrordisplay("",$(this).attr('id'));
	                       		}
	                           	else
	                       		{
	                           	//	$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
	                           	 editerrordisplay("Email is not valid format",$(this).attr('id'));
	                           		 flag = false;
	                       		}
	                       	}
			           }
			  });
		 return flag;
	}
	
	function editThirdContdetailValidator(){
		var flag=true;
		 $('#editStep1TertiaryContactDetails').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			             //  $('#'+$(this).attr('id')+'Error').addClass('hideall');
			            //   $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			        	   editerrordisplay("",$(this).attr('id'));
			           } else {
			        	   
			        	   if($(this).attr('id')==='editLeadThirdConEmail')
	                       	{
	                           	if(validateEmail($(this).val()))
	                       		{
	                           		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	                           	 editerrordisplay("",$(this).attr('id'));
	                       		}
	                           	else
	                       		{
	                           	//	$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
	                           	 editerrordisplay("Email is not valid format",$(this).attr('id'));
	                           		 flag = false;
	                       		}
	                       	}
			           }
			  });
		 return flag;
	}
	function createValidator()
	{
		var flag=true;
		
		 $('#newLeadfrm1').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			              // $('#'+$(this).attr('id')+'Error').removeClass('hideall');
			        	   errordisplay("Required Field",$(this).attr('id'));
			            	   flag = false;
			           } else {
			        	 //  $('#'+$(this).attr('id')+'Error').addClass('hideall');
			        	   errordisplay("",$(this).attr('id'));
			           }
			       });
		 
		
		
		 $('#newLeadfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			            //   $('#'+$(this).attr('id')+'Error').removeClass('hideall');
			             //  $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			        	   errordisplay("Required Field",$(this).attr('id'));
			               if($(this).attr('id')==='newAddress2')
		            	   {
			            	   errordisplay("",$(this).attr('id'));
		            	   }
			               else if($(this).attr('id')==='newFax')
		            	   {
			            	   errordisplay("",$(this).attr('id'));
		            	   }
			               else if($.trim($(this).attr('id'))==='customFlagYes')
		            	   {
			            	   errordisplay("",$(this).attr('id'));
		            	   }
			               else if($.trim($(this).attr('id'))==='privateFlagYes')
		            	   {
			            	   errordisplay("",$(this).attr('id'));
		            	   }
			               else
			               {
			            	   flag = false;
			               }
			           } else {
			        	//   $('#'+$(this).attr('id')+'Error').addClass('hideall');
			        	   errordisplay("",$(this).attr('id'));
			        	   if($.trim($(this).attr('id'))==='newWebsite')
			      		 {
			      		   if(isValidURL($(this).val()))
			           		{
			               		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			      			 errordisplay("",$(this).attr('id'));
			           		}
			               	else
			           		{
			               	//	$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
			               	 errordisplay("Website is not valid format",$(this).attr('id'));
			               		flag=false;
			           		}
			      		   
			      		 }
			           }
			       });
		 
		 /*if(!$('#newLeadNameValidError').hasClass('hideall'))
		 {
			 flag=false;
		 }*/
		  if ($.trim($('#newLeadName').val().length) != 0) {
				//	$("#newLeadNameError").addClass("hideall");
				   $.ajax({
			           url: '/business/getLeadNameValidation',
			           data: 'orgId='+$.trim($('#leadOrg').val())+'&leadName='+$.trim($('#newLeadName').val()),
			           success: function(data) {
			          	 if(data==='exists')
			          	{
			          		//$('#newLeadNameValidError').removeClass('hideall');
			          		 errordisplay("Business Partner already exists",$('#newLeadName').attr('id'));
			          		 flag=false;
			          		
			          	}else{
			          	 errordisplay("",$('#newLeadName').attr('id'));
			          	}
			           }
					});
				}
		 return flag;
	}
	
	
	function editValidator()
	{
		var flag=true;
		   
		 $('#editLeadfrm1').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			             //  $('#'+$(this).attr('id')+'Error').removeClass('hideall');
			        	   editerrordisplay("Required Field",$(this).attr('id'));
			            	   flag = false;
			           } else {
			        	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
			        	   editerrordisplay("",$(this).attr('id'));
			        	  
			           }
			       });
		 
		
		
		 $('#editLeadfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			             //  $('#'+$(this).attr('id')+'Error').removeClass('hideall');
			              // $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			        	   editerrordisplay("Required Field",$(this).attr('id'));
			               if($(this).attr('id')==='editAddress2')
		            	   {
			            	   editerrordisplay("",$(this).attr('id'));
		            	   }
			               else if($(this).attr('id')==='editFax')
		            	   {
			            	   editerrordisplay("",$(this).attr('id'));
		            	   }
			               else if($.trim($(this).attr('id'))==='editcustomFlagYes')
		            	   {
			            	   editerrordisplay("",$(this).attr('id'));
		            	   }
			               else if($.trim($(this).attr('id'))==='editPrivateFlagYes')
		            	   {
			            	   editerrordisplay("",$(this).attr('id'));
		            	   }
			               else
			               {
			            	   flag = false;
			               }
			           } else {
			        	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
			        	   errordisplay("",$(this).attr('id'));
			        	   if($.trim($(this).attr('id'))==='editWebsite')
			        		 {
			        		   if(isValidURL($(this).val()))
			             		{
			                 		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			        			   editerrordisplay("",$(this).attr('id'));
			             		}
			                 	else
			             		{
			                 		//$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
			                 		 editerrordisplay("Website is not valid format",$(this).attr('id'));
			                 		flag = false;
			             		}
			        		   
			        		 }
			           }
			       });
		 
		/* if(!$('#editLeadNameValidError').hasClass('hideall'))
		 {
			 flag=false;
		 }*/
	/*	  if ($.trim($('#editLeadName').val().length) != 0) {
				   $.ajax({
			           url: '/business/getLeadNameValidation',
			           data: 'orgId='+$.trim($('#leadOrg').val())+'&leadName='+$.trim($('#editLeadName').val()),
			           success: function(data) {
			          	 if(data==='exists')
			          	{alert("A");
			          		editerrordisplay("Business Partner already exists",$('#editLeadName').attr('id'));
			          		 flag=false;
			          		
			          	}else{
			          	 editerrordisplay("",$('#editLeadName').attr('id'));
			          	}
			           }
					});
				}*/
		 return flag;
	}
	$('#step1NextLead').click(function()
	{
		 if(createValidator())
		 {
			 createSecondTab();
		 }
	
	});
	
	
	$('#viewStep1NextLead').click(function()
	{
			viewSecondTab();
	});
	
	$('#editStep1NextLead').click(function()
	{
		 if(editValidator())
		 {
			editSecondTab();
		 }
	});
			
	
	$('.step1').click(function()
	{
		createFirstTab();
		 
	});
	
	$('.step2').click(function()
	{
		 if(createValidator())
		 {
			 createSecondTab();
		 }
	});
	
	$('.step3').click(function()
	{
		 if(createValidator())
		 {
			 if(createEmailVaidator())
			{
				 createThirdTab();
			}
		 }
	});
	
	
	$('#viewStep1').click(function()
	{
		viewFirstTab();
		 
	});
	
	$('#viewStep2').click(function()
	{
			 viewSecondTab();
	});
	
	$('#viewStep3').click(function()
	{
			 viewThirdTab();
	});
	

	$('#editStep1').click(function()
	{
		editFirstTab();
		 
	});
	
	$('#editStep2').click(function()
	{
		if(editValidator())
		 {
			 editSecondTab();
		 }
	});
	
	$('#editStep3').click(function()
	{
		if(editValidator())
		 {
			 if(editEmailVaidator())
				{
				 	editThirdTab();
				}
		 }
	});
	

	
	$('#viewStep2NextLead').click(function()
			{
		viewThirdTab();
			});
			
	
	function createEmailValidator()
	{
		var flag=true;
		$('#newStep1PrimaryDetails').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			           //    $('#'+$(this).attr('id')+'Error').addClass('hideall');
			           //    $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			        	   errordisplay("",$(this).attr('id'));
			        	   
			           } else {
			        	   if($(this).attr('id')==='newLeadPrimaryEmail')
	                       	{
			        		  
	                           	if(validateEmail($(this).val()))
	                       		{
	                           		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	                           	 errordisplay("",$(this).attr('id'));
	                       		}
	                           	else
	                       		{
	                           		//$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
	                           	 errordisplay("Email is not valid format",$(this).attr('id'));
	                           		 flag = false;
	                           		 
	                       		}
	                       	}
			           }
			       });
		 return flag;
	}
	
	function editEmailVaidator()
	{
		var flag=true;
		$('#editStep1PrimaryDetails').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
	    		   
			       function() {
			           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
			           //    $('#'+$(this).attr('id')+'Error').addClass('hideall');
			           //    $('#'+$(this).attr('id')+'ValidError').addClass('hideall');
			        	   editerrordisplay("",$(this).attr('id'));
			           } else {
			        	   
			        	   if($(this).attr('id')==='editLeadPrimaryEmail')
	                       	{
	                           	if(validateEmail($(this).val()))
	                       		{
	                           		//$('#'+$(this).attr('id')+'ValidError').addClass('hideall');
	                           	  editerrordisplay("",$(this).attr('id'));
	                       		}
	                           	else
	                       		{
	                           	//	$('#'+$(this).attr('id')+'ValidError').removeClass('hideall');
	                           	 editerrordisplay("Email is not valid format",$(this).attr('id'));
	                           		 flag = false;
	                       		}
	                       	}
			           }
			       });
		 return flag;
	}
	
	$('#editStep2NextLead').click(function()
	{
			
			 if(editEmailVaidator())
			 {
					editThirdTab();
			 }
	});
	
	$('#step2NextLead').click(function()
			{
				 if(createEmailVaidator())
				 {
					 createThirdTab();
				 }
			});
	
	

	
	 $('#cmdImportLeads').click(function()		//Delete click function
		      {
			   if($.trim($('#fileName').val())==='')
				{
					$('#dlgResidentsMsg').html('Select a file to upload').dialog('open');
					return false;
				}
			   $('#cancelLink').addClass('hideall');
			   $('#uploadingText').html('Uploading Business partner in process. This may take some time. Please wait...');
				$.ajax(
			    {
					url : '/business/importLeads',
					data : 'orgId='+ $('#importOrgId').val()+'&fileName='+$.trim($('#fileName').val()),
					cache : false,
					success : function(data) {
						$('#uploadingText').html('');
						if ($.trim(data) === 'success') 
						{
							$('#dlgLeadMsg').html("Imported successfully.").dialog('open');
							  $("#leadList").trigger("reloadGrid");
							  $('#importBackLead').trigger('click');
						} else if ($.trim(data) === 'failure') {
							$('#dlgLeadMsg').html(
											"Upload not successful...Check error log to correct input file")
									.dialog(
											'open');
							$('#uploadFileName').val($('#fileName').val());
							$('#getErrorLogFile').submit();
							
						}
						else if($.trim(data) === 'nodata') 
							{
								$('#dlgLeadMsg').html('Enter atleast one business partner detail.').dialog('open');
								$('#cancelLink').trigger('click');
								
							}
						else
							{
								$('#dlgLeadMsg')
								.html(
										"Error importing business partner..Please try again later..")
								.dialog(
										'open');
								}
					}
				});
		      });
	
	
});
});
