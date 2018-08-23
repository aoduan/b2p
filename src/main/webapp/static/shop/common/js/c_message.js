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
					'<td>'+timestampToTime(item.createDate)+'</td>'+ 
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
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
}
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