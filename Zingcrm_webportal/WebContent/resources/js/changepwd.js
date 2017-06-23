$(document).ready(function() {
	$(function() {
	
	$('#changepasswordcancel').click(function()
			{

			   $('#currentpasswordReq_Error').addClass('hideall');
				$('#currentpasswordValid_Error').addClass('hideall');
				$('#samepassword_Error').addClass('hideall');
				$('#passwordReq_Error').addClass('hideall');
				$('#passwordValid_Error').addClass('hideall');
		       $("#conformPwdReq_Error").addClass("hideall");
			   $('#conformPwdValid_Error').addClass('hideall');
			   $("#currentpassword").val('');
			   $("#password").val('');
			   $("#conformpassword").val('');
			   window.location.href="/index";
			});
	 function errordisplay(errorval,id){
		 if(errorval!=''){
			 $('#'+id).addClass("ErrorField");
		 }
		 else if(errorval=='Email already exists'){
			 $('#'+id).addClass("ErrorField");
		 }
		 else if(errorval=='Email is not valid format'){
			 $('#'+id).addClass("ErrorField");
		 }
		 else{
			 $('#'+id).removeClass("ErrorField");
		 }
	 }
    	
	$('#currentpassword').blur(function() {
		if ($.trim($('#currentpassword').val().length) != 0) {
			checkCurrentPassword();							
		} else {
			 errordisplay("Required field",$('#currentpassword').attr('id'));
			//$("#currentpasswordReq_Error").removeClass("hideall");
			$("#currentpasswordValid_Error").addClass("hideall");
		}
	});
	
	function checkCurrentPassword() {
		 errordisplay("",$('#currentpassword').attr('id'));
		//$("#currentpasswordReq_Error").addClass("hideall");
		$('#loadingImg').removeClass('hideall');
		$.ajax({
				url : "/changepwd/chkCurrentPassword",
				cache : false,
				data : 'currentpassword=' + $.trim($('#currentpassword').val()),
				success : function(isAvailable) {
					$('#loadingImg').addClass('hideall');
					if ($.trim(isAvailable) == "oldpasswordexists") {
						$("#currentpasswordValid_Error").addClass("hideall");
					} else if ($.trim(isAvailable) == "oldpasswordnotexists") {
						$("#currentpasswordValid_Error").removeClass("hideall");						
					}
				}
		});
	}
	
	//Password 
	$('#password').focusout(function()
	{
		if($.trim($('#password').val())==='')
		{
			$('#passwordValid_Error').addClass('hideall');
			 errordisplay("Required field",$('#password').attr('id'));
			//$('#passwordReq_Error').removeClass('hideall');
		}
		else
		{
			 errordisplay("",$('#password').attr('id'));
			//$('#passwordReq_Error').addClass('hideall');
			var lu= /^.*(?=.{8,16})(?=.*[a-z])(?=.*[A-Z])/;
			var un=/^.*(?=.{8,16})(?=.*[A-Z])(?=.*\d).*$/;
			var ln=/^.*(?=.{8,16})(?=.*[a-z])(?=.*\d).*$/;
			var us=/^.*(?=.{8,16})(?=.*[A-Z])(?=.*[!@#$%^&+=,.?|\:;"'~`]).*$/;
			var sn=/^.*(?=.{8,16})(?=.*\d)(?=.*[!@#$%^&+=,.?|\:;"'~`]).*$/;
			var ls=/^.*(?=.{8,16})(?=.*[a-z])(?=.*[!@#$%^&+=,.?|\:;"'~`]).*$/;
			
            if ($.trim($('#password').val()).search(lu) === 0 || $.trim($('#password').val()).search(sn) === 0 || $.trim($('#password').val()).search(us) === 0 || $.trim($('#password').val()).search(un) === 0 || $.trim($('#password').val()).search(ls) === 0 || $.trim($('#password').val()).search(ln) === 0) 
            {
            	$('#passwordValid_Error').addClass('hideall');
            }
            else
            {
            	 errordisplay("",$('#password').attr('id'));
            	//$('#passwordReq_Error').addClass('hideall');
     		    $('#passwordValid_Error').removeClass('hideall');
            }
		}
	});
	
	//Conform password 
	$('#conformpassword').focusout(function()
	{
		if($.trim($('#conformpassword').val())==='')
		{
			$('#conformPwdValid_Error').addClass('hideall');
			errordisplay("Required field",$('#conformpassword').attr('id'));
			//$('#conformPwdReq_Error').removeClass('hideall');
		}
		else
		{
			errordisplay("",$('#conformpassword').attr('id'));
			//$('#conformPwdReq_Error').addClass('hideall');
			if ($("#password").val() === $("#conformpassword").val())
			{
				$('#conformPwdValid_Error').addClass('hideall');
			}
			else
			{
				$('#conformPwdValid_Error').removeClass('hideall');
			}
		}
	});
	
	$('#btnsubmit').click(function(e) {
		var flag=true;
		e.preventDefault();
		
		if ($.trim($("#currentpassword").val()).length == 0 || $.trim($("#currentpassword").val()) == 0) {
			errordisplay("Required field",$('#currentpassword').attr('id'));
			//$("#currentpasswordReq_Error").removeClass("hideall");
			flag = false;
		} else {
			errordisplay("",$('#currentpassword').attr('id'));
			//$("#currentpasswordReq_Error").addClass("hideall");
		}
		
		if(!$('#currentpasswordValid_Error').hasClass('hideall'))
		{
			flag=false;
		}
		
		if ($.trim($("#password").val()).length == 0 || $.trim($("#password").val()) == 0) {
			errordisplay("Required field",$('#password').attr('id'));
			//$("#passwordReq_Error").removeClass("hideall");
			flag = false;
		} else {
			errordisplay("",$('#password').attr('id'));
			//$("#passwordReq_Error").addClass("hideall");
		}
		
		if(!$('#passwordValid_Error').hasClass('hideall'))
		{
			flag=false;
		}
		
		if ($.trim($("#conformpassword").val()).length == 0 || $.trim($("#conformpassword").val()) == 0) {
			errordisplay("Required field",$('#conformpassword').attr('id'));
			//$("#conformPwdReq_Error").removeClass("hideall");
			flag = false;
		} else {
			errordisplay("",$('#conformpassword').attr('id'));
			//$("#conformPwdReq_Error").addClass("hideall");
		}
		
		if(!$('#conformPwdValid_Error').hasClass('hideall'))
		{
			flag=false;
		}
		
		if (flag) {
			
			$('#btnsubmit').attr('disabled',true);
			  $.ajax({
                 url: '/changepwd/changePassword',
                 type: 'POST',
                 data: $('#frmchangepwd').serialize(),
                 success: function(data) { 
                	 $('#btnsubmit').attr('disabled',false);
                	 window.location.href="/jsp/changepwdsuccess.jsp";
                 }
             }); 
			 
			}
	});
	});
	
});