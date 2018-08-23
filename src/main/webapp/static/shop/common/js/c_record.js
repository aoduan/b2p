$.ajax({
			type:"POST",
			headers: {
		        cdutb2p_shop_token: GLOBAL_TOKEN
		    },
		    dataType: "json",
		    url:"/b2p/shop/customerCenter/myOrder",
		    data:{},
		    success:function(data){
		    	  $.each(data.model.OrderVoList, function(index, item) {
		    		  $("#tbody").append('<tr style="width:100%;height:120px;border-bottom:thick dotted #fff;">'+
	              		'<td style="width:110px;height:110px;"><img style="width:100px;height:100px;" src="../..'+item.url+'"/></td>'+
	              		'<td style="width:300px;padding-left:20px;padding-top:60px"><span style="display:block;width:300px;height:100px;overflow:hidden;">'+'二手手机购买'+'</span></td>'+
	              		'<td style="width:200px;"><span>'+timestampToTime(item.createDate)+'</span></td>'+
	              		'<td style="width:200px;"><span>￥'+item.orderPrice+'</span></td>'+
	              		'<td style="width:200px;"><span>'+item.orderPrice+'</span></td>'+
	              		'<td style="width:300px;"><a href="#"><span>评论一波</span></a></td>'+
	              	'</tr>');
                  });
		    },
		    error:function(data){
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
function del(){
	
}