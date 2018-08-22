
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

$.validator.setDefaults({
    submitHandler: function() {
	  $.ajax({
             type: "POST",
             url: getPath() + "/shop/user/find_password/set_password",
             data:{password:$("#passwordUser").val(),
				email:GetQueryString("email"),
				code:GetQueryString("code"),
			 },
             dataType: "json",
             success: function(data){  
                         if(data['success']){
							layer.alert(data['msg']);
							localStorage.cdutb2p_shop_token = ""
							sessionStorage.cdutb2p_shop_token = ""
							var t=setTimeout(function(){
								window.location.href = "./index.html";
							},2000);
						 }else{
						    window.location.href = "./index.html";
						 }
                      },
					  
             error: function(data){  
                         window.location.href = "./index.html";
                      }
         });

    },
	errorPlacement: function(error, element) { 
	    for(var i= 0;i<element.length;i++){
			if(element[i].id == "passwordUser"){
				$('#passwordTip').html(error);
			}else if(element[i].id == "repasswordUser"){
				$('#repasswordTip').html(error);
			}
		
		}
    }
});
$().ready(function() {
	$.ajax({
             type: "POST",
             url: getPath() + "/shop/user/find_password/validate_code",
             data:{
				email:GetQueryString("email"),
				code:GetQueryString("code"),
			 },
             dataType: "json",
             success: function(data){  
                         if(data['success']){
						 }else{
						   window.location.href = "./index.html";
						 }
                      },
					  
             error: function(data){  
                        window.location.href = "./index.html";
                      }
         });
    $("#submitForm_new").validate();
});