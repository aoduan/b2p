$.validator.setDefaults({
    submitHandler: function() {
	  if($('#regGetcodeBtn').attr('disabled') == null || $('#regGetcodeBtn').attr('disabled') == "false"){
		  return;
	  }
	  $.ajax({
             type: "POST",
             url: getPath() + "/shop/user/reg",
             data:{emailcode:$("#emailcodeUser").val(),username:$("#usernameUser").val(),
 			   password:$("#passwordUser").val(),nickname:$("#nicknameUser").val(),
			   email:$("#emailUser").val()
			 },
             dataType: "json",
             success: function(data){  
                         if(data['success']){
							layer.alert(data['msg']);
							var t=setTimeout(function(){
								window.location.href = "./login.html";
							},2000);
							
						 }else{
						    layer.alert(data['msg']);
						 }
                      },
					  
             error: function(data){  
                         layer.alert('系统错误');
                      }
         });
	  
	  
	  
	  
    },
	errorPlacement: function(error, element) { 
	    for(var i= 0;i<element.length;i++){
			if(element[i].id == "emailUser"){
				$('#emailTip').html(error);
			}else if(element[i].id == "emailcodeUser"){
				$('#emailcodeTip').html(error);
			}else if(element[i].id == "mobileUser"){
				$('#mobileTip').html(error);
			}else if(element[i].id == "usernameUser"){
				$('#usernameTip').html(error);
			}else if(element[i].id == "nicknameUser"){
				$('#nicknameTip').html(error);
			}else if(element[i].id == "passwordUser"){
				$('#passwordTip').html(error);
			}else if(element[i].id == "repasswordUser"){
				$('#repasswordTip').html(error);
			}
		}
    }
});
$().ready(function() {
    $("#submitForm_new").validate();
});