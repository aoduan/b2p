$.ajax({
	type : "POST",
	headers : {
		cdutb2p_shop_token : GLOBAL_TOKEN
	},
	dataType : "json",
	url : "/b2p/shop/customerCenter/selectMyCart",
	data : {},
	success : function(data) {
		$.each(data.model.CartList, function(index, item) {	
			$("#tbody").append('<tr style="height:120px; overflow: hidden;display:block;margin-top:15px;border-bottom:thick dotted #fff;">' +
					'<td><a href="goods.html?goods_id='+item.cartGoodsId+'"><img style="width:100px;height:100px" src=../..'+item.url+'></a></td>'+
					'<td><span style="padding-top:20px;font-size:12px;margin-left:10px;text-align:left;width:200px;height:100px;overflow:hidden;display:block">'+item.describe+'<span></td>' + 
					'<td style="width:120px;height:100px"><span style="width:120px;font-size:14px;display:block" >原价￥'+item.oldPrice+'</span>'+
					'<span style="width:120px;color:red;font-size:20px;display:block">现价￥'+item.nowPrice+'</span></td>'+ 
					'<td style="width:50px;height:100px;"><span style="width:50px;font-size:14px;color:orange">程度'+item.level+'</td>'+ 
					'<td><input type="hidden" id="id'+index+'" value='+item.id+'>'+
					'<td><input type="hidden" id="goods_'+index+'" value='+item.cartGoodsId+'>'+
					'<td style="padding-left:10px;width:100px;height:100px">'+
					'<span style="width:80px;display:block;line-height:30px;font-size:15px;"><button style="background:#ff552e;color:white;border-radius:8px;width:100%;height:100%;" onclick="compare('+index+')">对比</button></span>'+
					'<span style="width:80px;display:block;height:30px"><button style="background:#ff552e;color:white;cursor: pointer;border-radius: 8px;width:100%;height:100%;" onclick="buyCart('+index+')">购买</button></button></span>'+
					'<span style="width:80px;display:block;height:30px"><button style="background:#ff552e;color:white;border-radius: 8px;width:100%;height:100%;" onclick="delCart('+index+')">删除</button></span>'+
					'</td>'+
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
/*购买一件商品*/
function buyCart(index){
	if( GLOBAL_TOKEN==""){
		window.location.href="./login.html";
	}
	var goods_id=$("#goods_"+index).val();
	window.location.href="./order.html?goods_id="+goods_id;
}
/*删除购物车中的某件商品*/
function delCart(index){
	var cart_id=$("#id"+index).val();
	if(cart_id!=null||cart_id!=""){
		var param={};
		param.c_id=cart_id;
		$.ajax({
			type : "POST",
			headers : {
				cdutb2p_shop_token : GLOBAL_TOKEN
			},
			dataType : "json",
			url : "/b2p/shop/customerCenter/delMyCart",
			data : param,
			success : function(data) {
				alert("删除成功！");
				window.location.reload();
			},
			error : function(data) {
				alert("请登录");
			}
		});
	}
}
/*对比功能*/
function compare(index){
	var cart_id=$("#id"+index).val();
	if(cart_id!=null||cart_id!=""){
		var param={};
		param.c_id=cart_id;
		alert(cart_id);
		$.ajax({
			type : "POST",
			headers : {
				cdutb2p_shop_token : GLOBAL_TOKEN
			},
			dataType : "json",
			url : "/b2p/shop/customerCenter/compareMyCart",
			data : param,
			success : function(data) {
				$("#compareGoods").html("");
				$("#sectionB").css("display","block");
				$("#compareGoodsTable").css({"border":" 2px solid #F5F5DC","-moz-border-radius":"15px"," border-radius":"25px"});
				$("#compareGoods").append("<span>>>相似商品</span>");
				$.each(data.model.ShopGoodsList, function(index, item) {	
					$("#compareGoods").append('<tr style="height:100px; overflow:hidden;display:block;margin-top:15px;border-bottom:thick dotted #fff; ">' +
							'<td><img style="width:100px;height:100px" src=../..'+item.goodsPics+'></td>'+ 
							'<td style="width:120px;height:100px"><span style="width:120px;font-size:14px;display:block">原价￥'+item.goodsOriginalPrice+'</span>'+
							'<span style="width:120px;color:red;font-size:20px;display:block">现价￥'+item.goodsPresentPrice+'</span></td>'+ 
							'<td style="width:50px;height:100px;"><span style="width:50px;font-size:14px;color:orange">程度'+item.goodsOldLevel+'</td>'+ 
							'<td><input type="hidden" id="id'+index+'" value='+item.id+'>'+
						    '</tr>');
				});
				
			},
			error : function(data) {
				alert("请登录");
			}
		});
	}
}
/*鼠标离开对比商品列表*/
function leval(){
	$("#sectionB").hide();
}