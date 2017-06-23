$(document).ready(function()
{
	var options="<option value=0>Select</option>";
	$(function()
	{
		 $.extend($.ui.dialog.prototype.options, {
				modal : true,
				bgiframe : true,
				resizable : false,
				autoOpen : false
			});
		 
		   $('#dlgDelSettingMsg').dialog({
				buttons : {
					"Ok" : function() {
						$('#dlgDelSettingMsg').dialog('close');
					}
				}
			});
		   
		   $.getJSON('/setting/getAllActiveRep', function(data) 
					{
							var repList ="";
							$.each(data.result, function(key, val) {
								 if(data.status==='success'){
									 repList += '<option value="' + data.result[key].key + '">'
										+ data.result[key].value + '</option>';
								 }
							});
						
							$('#newToSalesField').html(options+repList);
				    });  
		   
		   $.getJSON('/setting/getAllRep', function(data) 
					{
							var repList ="";
							$.each(data.result, function(key, val) {
								 if(data.status==='success'){
									 repList += '<option value="' + data.result[key].key + '">'
										+ data.result[key].value + '</option>';
								 }
							});
						
							$('#newFromSalesField').html(options+repList);
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
		   
		  $('#cancelRepSetting').click(function()
		  {
			  errordisplay("",$('#newFromSalesField').attr('id'));
			  errordisplay("",$('#newToSalesField').attr('id'));
			 // errordisplay("",$('#newFromToSalesField').attr('id'));
			//  $('#newFromSalesFieldError').addClass('hideall');
			 // $('#newToSalesFieldError').addClass('hideall');
			  $('#newFromToSalesFieldError').addClass('hideall');
	       	   $('#changeRepSetting').attr('disabled',false);
		  });
		   $('#newFromSalesField').change(function()
		  {
			   
			   if($('#newFromSalesField option:selected').val()==='0' || $('#newFromSalesField option:selected').val()===undefined)
				{
				   errordisplay("Required field",$('#newFromSalesField').attr('id'));
					//$('#newFromSalesFieldError').removeClass('hideall');
				}
			   else
				   {
				   errordisplay("",$('#newFromSalesField').attr('id'));
				 //  $('#newFromSalesFieldError').addClass('hideall');
				   }
			   
			   if($('#newFromSalesField option:selected').val()===$('#newToSalesField option:selected').val())
				{
					$('#newFromToSalesFieldError').removeClass('hideall');
				  
				}
			   else
				   {
				   $('#newFromToSalesFieldError').addClass('hideall');
				   }
			   if($('#newFromSalesField option:selected').val()=='0' && $('#newToSalesField option:selected').val()=='0')
				{
				   errordisplay("Required field",$('#newFromSalesField').attr('id'));
				//	$('#newFromSalesFieldError').removeClass('hideall');
				//	 $('#newFromToSalesFieldError').addClass('hideall');
				  
				}
			
		  });
		   
		   $('#newToSalesField').change(function()
		  {
			
			   if($('#newToSalesField option:selected').val()==='0' || $('#newToSalesField option:selected').val()===undefined)
				{
				   errordisplay("Required field",$('#newToSalesField').attr('id'));
					//$('#newToSalesFieldError').removeClass('hideall');
				}
			   else
			   {
				   errordisplay("",$('#newToSalesField').attr('id'));
				  // $('#newToSalesFieldError').addClass('hideall');
			   }
			   
			   if($('#newFromSalesField option:selected').val()==='0' || $('#newFromSalesField option:selected').val()===undefined)
				{
				   errordisplay("Required field",$('#newFromSalesField').attr('id'));
					//$('#newFromSalesFieldError').removeClass('hideall');
				}
			   
			   if($('#newFromSalesField option:selected').val()===$('#newToSalesField option:selected').val())
				{
					$('#newFromToSalesFieldError').removeClass('hideall');
				}
			   else
			   {
				   $('#newFromToSalesFieldError').addClass('hideall');
			   }
			   if($('#newFromSalesField option:selected').val()=='0' && $('#newToSalesField option:selected').val()=='0')
				{
					$('#newFromToSalesFieldError').removeClass('hideall');
					$('#newFromToSalesFieldError').addClass('hideall');
				}
			  
	      });
		  $('#changeRepSetting').click(function()
		  {
			  if($('#newFromSalesField option:selected').val()==='0' || $('#newFromSalesField option:selected').val()===undefined)
				{
				  errordisplay("Required field",$('#newFromSalesField').attr('id'));
					//$('#newFromSalesFieldError').removeClass('hideall');
				    return false;
				}
			  if($('#newToSalesField option:selected').val()==='0' || $('#newToSalesField option:selected').val()===undefined)
				{
				  errordisplay("Required field",$('#newToSalesField').attr('id'));
					//$('#newToSalesFieldError').removeClass('hideall');
				    return false;
				}
			  
			  
			  if($('#newFromSalesField option:selected').val()===$('#newToSalesField option:selected').val())
				{
					$('#newFromToSalesFieldError').removeClass('hideall');
				    return false;
				}
			  $('#changeRepSetting').attr('disabled',true);
				
				$.ajax({
			           url: '/setting/changeUser',
			           type:'post',
			           data:'fromUserId='+$('#newFromSalesField option:selected').val()+'&toUserId='+$('#newToSalesField option:selected').val(),
			           success: function(data)
			           {
			        	   $('#changeRepSetting').attr('disabled',false);
			        	  if($.trim(data)==='success')
		        		  {
			        		  $('#dlgDelSettingMsg').html('Sales rep has been changed successfully').dialog('open');
			        		 
			        		
		        		  }
			        	  else
		        		  {
			        		  $('#dlgDelSettingMsg').html('Error updating user. Please try again later.').dialog('open');
		        		  }
			           }
				});
		  });
		   
		function getUserField()
		{
			 $.getJSON('/setting/getUserField?orgId='+$.trim($('#newSettingOrg option:selected').val()), function(data) 
			{
					$.each(data.result, function(key, val) {
						 if(data.status==='success')
						 {
							 $('#newParentField').val( data.result[key].userFiled1);
							 $('#newChildField').val( data.result[key].userFiled2);
							 $('#requiredFlagYes').attr('checked',  data.result[key].required);
						 }
					});
		    });  
		}
		
		function getUserData()
		{
			$.getJSON('/setting/definedData?orgId='+$.trim($('#newSettingDropOrg option:selected').val()), function(data) 
					{
				var dataList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 dataList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#newParentDropField').html(options+dataList).trigger('click');
				if($('#newParentDropField').val()==='0'){
					$('#newChildDropField').html(options);
				}
					});
		 
		}
		 $.getJSON('/setting/orglist', function(data) 
		{
				var orgList ="";
				$.each(data.result, function(key, val) {
					 if(data.status==='success'){
						 orgList += '<option value="' + data.result[key].key + '">'
							+ data.result[key].value + '</option>';
					 }
				});
				$('#newSettingOrg').html(orgList);
				$('#newSettingDropOrg').html(orgList);
				
				getUserField();
				getUserData();
	    });
		 
		 
		$('#data1AddSetting').click(function()
		{	
			 errordisplay("",$('#newParentDropField').attr('id'));
			$('#newParentDropField').addClass('hideall');
			//$('#newParentDropTextField').addClass('hideall');
			$('.defaultClass').addClass('hideall');
			$('#ParentID').val(0);
			$('.AddClass').removeClass('hideall');
		});
		
		$('#data1ChildAddSetting').click(function()
		{	
			 errordisplay("",$('#newChildDropField').attr('id'));
			//$('#newChildDropFieldError').addClass('hideall');
			//$('#newChildDropFieldTextError').addClass('hideall');
			
			if($('#newParentDropField option:selected').val()==='0')
			{
				 errordisplay("Required field",$('#newParentDropField').attr('id'));
				//$('#newParentDropFieldTextError').addClass('hideall');
				//$('#newParentDropFieldError').removeClass('hideall');
			  return false;
			}
			/*else if($('#newParentDropField option:selected').val()!=='0' && $('#newChildDropField option:selected').val()==='0'){
				$('#newParentDropFieldError').addClass('hideall');
				$('#newParentDropFieldTextError').removeClass('hideall');
				return false;
			}*/
			$('.defaultChildClass').addClass('hideall');
			$('#ChildID').val(0);
			$('#ParentChildID').val($('#newParentDropField option:selected').val());
			$('.AddChildClass').removeClass('hideall');
			$('#newChildDropField').addClass('hideall');
		});
				
		
		
		 
		 
		$('#data1CancelSetting').click(function()
		{
			$('.AddClass').addClass('hideall');
			$('#ParentID').val(0);
			errordisplay("",$('#newParentDropField').attr('id'));
			 //$('#newParentDropFieldTextError').addClass('hideall');
			 //$('#newParentDropFieldError').addClass('hideall');
			$('#newParentDropFieldText').val('');
			$('#newChildDropFieldText').val('');
			 $('#data1SaveSetting').attr('disabled',false);
			$('.defaultClass').removeClass('hideall');
			$('#newParentDropField').removeClass('hideall');
		});
		
		$('#data1ChildCancelSetting').click(function()
		{
			$('.AddChildClass').addClass('hideall');
			$('#ChildID').val(0);
			$('#ParentChildID').val(0);
			errordisplay("",$('#newChildDropFieldText').attr('id'));
			//$('#newChildDropFieldTextError').addClass('hideall');
			$('#newChildDropFieldText').val('');
			$('#data1ChildSaveSetting').attr('disabled',false);
			$('.defaultChildClass').removeClass('hideall');
			$('#newChildDropField').removeClass('hideall');
			$('#newChildDropField').html(options);
		});
		
		
		 
		$('#newParentDropField').change(function()
		{
			if($(this).val()!=='0')
			{
				$('#newParentDropFieldError').addClass('hideall');
				$.getJSON('/activity/definedData2?data='+$.trim($('#newParentDropField option:selected').val()), function(data) 
				{
					 var dataList2 ="";
					$.each(data.result, function(key, val) {
						 if(data.status==='success'){
							 dataList2 += '<option value="' + data.result[key].key + '">'
								+ data.result[key].value + '</option>';
						 }
					});
					$('#newChildDropField').html(options+dataList2);
				});
			}
			else{
				$('#newChildDropField').html(options);
			}
				
		});
		
		$('#newChildDropField').change(function()
				{
					if($(this).val()!=='0')
					{
						errordisplay("",$('#newChildDropField').attr('id'));
						//$('#newChildDropFieldError').addClass('hideall');
					}
					else
						{
						errordisplay("",$('#newChildDropFieldText').attr('id'));
					//	$('#newChildDropFieldTextError').addClass('hideall');
						
						}
						
				});
				
		
		$('#data1EditSetting').click(function()
		{
			if($('#newParentDropField option:selected').val()==='0')
			{
				errordisplay("Required field",$('#newParentDropField').attr('id'));
				//$('#newParentDropFieldError').removeClass('hideall');
			  return false;
			}
			else{
				errordisplay("",$('#newParentDropField').attr('id'));
				//$('#newParentDropFieldError').addClass('hideall');
				$('.defaultClass').addClass('hideall');
				$('#newParentDropFieldText').val($('#newParentDropField option:selected').text());
				$('#ParentID').val($('#newParentDropField option:selected').val());
				$('.AddClass').removeClass('hideall');
				$('#newParentDropField').addClass('hideall');
			}
		});
		
		
		$('#data1ChildEditSetting').click(function()
				{
			if($('#newParentDropField option:selected').val()==='0')
			{
				errordisplay("Required field",$('#newParentDropField').attr('id'));
				//$('#newParentDropFieldError').removeClass('hideall');
			  return false;
			}
			else if($('#newChildDropField option:selected').val()==='0')
			{
				errordisplay("Required field",$('#newChildDropField').attr('id'));
				//$('#newChildDropFieldError').removeClass('hideall');
			  return false;
			}
			else{
				errordisplay("",$('#newParentDropField').attr('id'));
				errordisplay("",$('#newChildDropField').attr('id'));
				//$('#newChildDropFieldError').addClass('hideall');
				//$('#newParentDropFieldError').addClass('hideall');
				$('.defaultChildClass').addClass('hideall');
				$('#newChildDropFieldText').val($('#newChildDropField option:selected').text());
				$('#ParentChildID').val($('#newParentDropField option:selected').val());
				$('#ChildID').val($('#newChildDropField option:selected').val());
				$('.AddChildClass').removeClass('hideall');
				$('#newChildDropField').addClass('hideall');
			}
		});
		 
		 $('#newSettingfrm').find(":input").not("[type=button],[type=hidden]").blur(function()
		 {
			 if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
				   //$('#'+$(this).attr('id')+'Error').removeClass('hideall');
				   errordisplay("Required field",$(this).attr('id'));
              } else {
            	//  $('#'+$(this).attr('id')+'Error').addClass('hideall');
            	  errordisplay("",$(this).attr('id'));
              }
			});
		 
		 $('#cancelSetting').click(function()
	    {
			 
			 $('#newSettingfrm').find(":input").not("[type=button],[type=hidden]").each(
	          function() {
	        	  $(this).val('');
	        	  errordisplay("",$(this).attr('id'));
	        	 // $('#'+$(this).attr('id')+'Error').addClass('hideall');
	          });
			 $('#updateSetting').attr('disabled',false);
			 getUserField();
	    });
		 
		 $('#data1SaveSetting').click(function()
		 {
			 if ($.trim($('#newParentDropFieldText').val()).length == 0 || $.trim($('#newParentDropFieldText').val()) == 0) 
			 {
				 
				// $('#newParentDropFieldError').addClass('hideall');
				 errordisplay("Required field",$('#newParentDropFieldText').attr('id'));
				//	 $('#newParentDropFieldTextError').removeClass('hideall');
				 return false;
			 }
			 else{
				 errordisplay("",$('#newParentDropFieldText').attr('id'));
				 errordisplay("",$('#newParentDropField').attr('id'));
				// $('#newParentDropFieldError').addClass('hideall');
				// $('#newParentDropFieldTextError').addClass('hideall');
				 $('#data1SaveSetting').attr('disabled',true);
				 $.ajax({
			           url: '/setting/saveUserField1',
			           type:'post',
			           data:$('#newSettingDropfrm').serialize(),
			           success: function(data)
			           {
			        	   $('#data1SaveSetting').attr('disabled',true);
			        	  if($.trim(data)==='success')
		        		  {
			        		  $('#dlgDelSettingMsg').html('Parent Field saved successfully').dialog('open');
			        		  $('#data1CancelSetting').trigger('click');
			        		  getUserData();
		        		  }
			        	  else
		        		  {
			        		  $('#dlgDelSettingMsg').html('Error updating parent field. Please try again later.').dialog('open');
		        		  }
			           }
				});
			 }
			 
		 });
		 
		 
		 $('#data1ChildSaveSetting').click(function()
				 {
			 errordisplay("",$('#newParentDropField').attr('id'));
			// $('#newParentDropFieldTextError').addClass('hideall');
					 if ($.trim($('#newParentDropField').val()).length == 0 || $.trim($('#newParentDropField').val()) == 0) 
					 {
						// $('#newParentDropFieldError').addClass('hideall');
						// $('#newParentDropFieldTextError').removeClass('hideall');
						 errordisplay("Required field",$('#newParentDropField').attr('id'));
						 return false;
					 }
					 else if ($.trim($('#newChildDropFieldText').val()).length == 0 || $.trim($('#newChildDropFieldText').val()) == 0) 
					 {
						 errordisplay("Required field",$('#newChildDropFieldText').attr('id'));
						// $('#newChildDropFieldError').addClass('hideall');
						 //$('#newChildDropFieldTextError').removeClass('hideall');
						 return false;
					 }
					 else{
						 errordisplay("",$('#newParentDropField').attr('id'));
						 errordisplay("",$('#newChildDropFieldText').attr('id'));
						// $('#newParentDropFieldTextError').addClass('hideall');
						// $('#newChildDropFieldTextError').addClass('hideall');
						 $('#data1ChildSaveSetting').attr('disabled',true);
						 $.ajax({
					           url: '/setting/saveUserField2',
					           type:'post',
					           data:$('#newSettingChildDropfrm').serialize(),
					           success: function(data)
					           {
					        	   $('#data1ChildSaveSetting').attr('disabled',true);
					        	  if($.trim(data)==='success')
				        		  {
					        		  $('#dlgDelSettingMsg').html('Child Field saved successfully').dialog('open');
					        		  $('#data1ChildCancelSetting').trigger('click');
					        		  getUserData();
				        		  }
					        	  else
				        		  {
					        		  $('#dlgDelSettingMsg').html('Error updating child field. Please try again later.').dialog('open');
				        		  }
					           }
						});
					 }
					 
				 });
				 
		 
		 $('#updateSetting').click(function()
	    {
			 
			 var flag=true;
			 $('#newSettingfrm').find(":input").not("[type=button],[type=hidden]").removeClass('error').each(
		    		   
				       function() {
				           if ($.trim($(this).val()).length == 0 || $.trim($(this).val()) == 0) {
				        	   if($(this).attr('id')==='requiredFlagYes')
				        		   {  //dont do anything	
				        		   }
				        	   else
				        		   {
				        		   errordisplay("Reauired field",$(this).attr('id'));
				            	  // $('#'+$(this).attr('id')+'Error').removeClass('hideall');
				            	   	flag = false;
				        		   }
				        	   
				           } else {
				        	   errordisplay("",$(this).attr('id'));
				        	  // $('#'+$(this).attr('id')+'Error').addClass('hideall');
				        	 
				           }
				       });
			 if(flag)
			 {
				 $('#updateSetting').attr('disabled',true);
				
					$.ajax({
				           url: '/setting/editUserField',
				           type:'post',
				           data:$('#newSettingfrm').serialize()+'&required='+$('#requiredFlagYes').is(':checked'),
				           success: function(data)
				           {
				        	   $('#updateSetting').attr('disabled',false);
				        	  if($.trim(data)==='success')
			        		  {
				        		  $('#dlgDelSettingMsg').html('User Field updated successfully').dialog('open');
				        		 
				        		
			        		  }
				        	  else
			        		  {
				        		  $('#dlgDelSettingMsg').html('Error updating user field. Please try again later.').dialog('open');
			        		  }
				           }
					});
			 }
			 
	    });
					 
	});
});