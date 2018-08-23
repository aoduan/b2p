$.ajax({
	type:"POST",
	headers: {
        cdutb2p_shop_token: GLOBAL_TOKEN
    },
    dataType: "json",
    url:"/b2p/shop/customerCenter/myInfo",
    data: {},
    success:function(data){
		if(GLOBAL_TOKEN == ""){
			window.location.href="./login.html";
		}
    	if(data.model.Customer.userNickname!=null||data.model.Customer.userNickname!=""){
    		$("#userNameOne").html(data.model.Customer.userNickname);
    	}
    },
    error:function(data){
    	alert("发生错误");
    	
    }
});
function setOrder(){
	$("#menu163").removeClass();
	$("#menu182").removeClass();
	$("#myGoods").hide();
	$("#myMessage").hide();
	$("#menu183").addClass("li-selected");
	$("#myOrder").css("display","block");
	
}
function setGoods(){
	$("#menu183").removeClass();
	$("#menu182").removeClass();
	$("#menu163").addClass("li-selected");
	$("#myOrder").hide();
	$("#myMessage").hide();
	$("#myGoods").css("display","block");
	
}
function setMessage(){
	$("#menu163").removeClass();
	$("#menu183").removeClass();
	$("#menu182").addClass("li-selected");
	$("#myOrder").hide();
	$("#myGoods").hide();
	$("#myMessage").css("display","block");
}