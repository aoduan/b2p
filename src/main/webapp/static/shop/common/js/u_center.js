$.ajax({
	type:"POST",
	headers: {
        cdutb2p_shop_token: GLOBAL_TOKEN
    },
    dataType: "json",
    url:"/b2p/shop/customerCenter/myInfo",
    data: {},
    success:function(data){
    	setMyInfo(data);
    },
    error:function(data){
    	alert("发生错误");
    }
});
/*设置我的信息*/
function setMyInfo(data){
	if(data.model.Customer.userImage!=null||data.model.Customer.userImage!=""){
		$("#roundnessSquareImg").attr('src','../..'+data.model.Customer.userImage);
	}
	$("#userName").val(data.model.Customer.userName);
	$("#userNickname").val(data.model.Customer.userNickname);
	$("#userTelphone").val(data.model.Customer.userTelphone);
	$("#userEmail").val(data.model.Customer.userEmail);
}
/*显示上传图像按钮*/
function change(){
	var image=$("#userface").val();
	if(image==null||image==""){
		return false;
	}
	$("#submitButton").css("display","block");
	return true;
	
}
/**/
function upload(){
	$("#subform").ajaxSubmit({
		url:"/b2p/shop/customerCenter/uploadImage",
		type:"post",
		headers: {
	        cdutb2p_shop_token: GLOBAL_TOKEN
	    },
		dataType:"json",
		async:false,
		success:function(data){
			alert("上传成功");
		},
	    error:function(data){
	    	alert("上传失败");
	    }
		
	});
}
