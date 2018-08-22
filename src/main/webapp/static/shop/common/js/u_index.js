$.ajax({
	type:"POST",
	headers: {
        cdutb2p_shop_token: GLOBAL_TOKEN
    },
    dataType: "json",
    url:"/b2p/shop/customerCenter/myInfo",
    data: {},
    success:function(data){
    	alert(data.model.Customer.userName);
    	if(data.model.Customer.userName!=null||data.model.Customer.userName!=""){
    		$("#userNameOne").html(data.model.Customer.userName);
    	}
    },
    error:function(data){
    	alert("发生错误");
    	
    }
});
function setInfo(){
	$("#menu183").removeClass();
	$("#menu182").removeClass();
	$("#myPWD").hide();
	$("#myMessage").hide();
	$("#menu163").addClass("li-selected");
	$("#myInfo").css("display","block");
	
}
function setPWD(){
	$("#menu163").removeClass();
	$("#menu182").removeClass();
	$("#menu183").addClass("li-selected");
	$("#myInfo").hide();
	$("#myMessage").hide();
	$("#myPWD").css("display","block");
	
}
function setMessage(){
	$("#menu163").removeClass();
	$("#menu183").removeClass();
	$("#menu182").addClass("li-selected");
	$("#myInfo").hide();
	$("#myPWD").hide();
	$("#myMessage").css("display","block");
}
