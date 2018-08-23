$.ajax({
	type : "POST",
	headers : {
		cdutb2p_shop_token : GLOBAL_TOKEN
	},
	dataType : "json",
	url : "/b2p/shop/customerCenter/selectMyMessage",
	data : {},
	success : function(data) {
		$.each(data.model.MessageList, function(index, item) {	
			$("#tbody").append('<tr>' +
					'<td>'+item.workorderReason+'</td>'+
					'<td>'+item.createDate+'</td>'+ 
					'<td>'+item.createBy+'</td>'+
					'<td><button onclick="delCMessage('+index+')">已读</button></td>'+
					'<td><input type="hidden" id="id'+index+'" value='+item.id+'>'+
				    '</tr>');
		});
	},
	error : function(data) {
		alert("请登录");
	}
});
/*删除某些消息*/
function delCMessage(index){
	var id=$("#id"+index).val();
	if(id!=null||id!=""){
		var param={};
		param.w_id=id;
		$.ajax({
			type : "POST",
			headers : {
				cdutb2p_shop_token : GLOBAL_TOKEN
			},
			dataType : "json",
			url : "/b2p/shop/customerCenter/delMyMessage",
			data : param,
			success : function(data) {
				window.location.reload();
			},
			error : function(data) {
				alert("请登录");
			}
		});
	}
}