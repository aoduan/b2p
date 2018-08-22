$.validator.setDefaults({
    submitHandler: function() {
         $.ajax({
             type: "POST",
             url: getPath() + "/shop/user/login",
             data: {username:$("#usernameUser").val(), password:$("#passwordUser").val()},
             dataType: "json",
             success: function(data){  
                         if(data['success']){
							var isChecked = $('#isremember').is(':checked');
							if(isChecked){
								//$.cookie("cdutb2p_shop_token",data['token'],{expires:7});
								localStorage.cdutb2p_shop_token = data['token'];
							}else{
								//$.cookie("cdutb2p_shop_token",data['token']);
								sessionStorage.cdutb2p_shop_token = data['token'];
							}
							layer.alert(data['msg']);
							var t=setTimeout(function(){
								window.location.href = "./index.html";
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
			if(element[i].id == "usernameUser"){
				$('#usernameTip').html(error);
			}else if(element[i].id == "passwordUser"){
				$('#passwordTip').html(error);
			}
		}
        
    }
});
$().ready(function() {
	$.ajax({
             type: "POST",
             headers: {
                 cdutb2p_shop_token: GLOBAL_TOKEN
             },
             url: getPath() + "/shop/user/islogin",
             data: {},
             dataType: "json",
             success: function(data){  
                         if(data['success']){
							 window.location.href = "./index.html";
						 }  
                      }

         });
    $("#submitForm_new").validate();
});

// layer.open({
  // type: 1,
  // title: false,
  // closeBtn: 0,
  // shadeClose: true,
  // area: ['666px', '333px'], //宽高
  // content: $('#regValidcodeBox').html()
// });