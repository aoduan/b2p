$.validator.setDefaults({
    submitHandler: function() {
	  
	  $.ajax({
             type: "POST",
             url: getPath() + "/shop/user/find_password/validate_user",
             data:{username:$("#usernameUser").val(),email:$("#emailUser").val()
			 },
             dataType: "json",
             success: function(data){  
                         if(data['success']){
							layer.alert(data['msg']);
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
			}else if(element[i].id == "usernameUser"){
				$('#usernameTip').html(error);
			}
		
		}
    }
});
$().ready(function() {
    $("#submitForm_new").validate();
});