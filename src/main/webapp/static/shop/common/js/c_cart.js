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
			$("#tbody").append('<tr>' +
					'<td><img style="width:100px;height:100px" src=../..'+item.url+'></td>'+
					'<td>'+item.describe+'</td>' + 
					'<td>'+item.oldPrice+"  "+item.nowPrice+'</td>'+ 
					'<td>'+item.level+'</td>'+ 
					'<td><input type="hidden" id="id'+index+'" value='+item.id+'>'+
					'<td><button onclick="compare('+index+')">对比</button></td>'+
					'<td><button onclick="buyCart('+index+')">购买</button></td>'+
					'<td><button onclick="delCart('+index+')">删除</button></td>'+
				    '</tr>');
		});
		$("#tbody").append('<tr>' +
				'<td><input type="checkbox" name="answer" onclick="checkAll(this)"/>全选<td>'+
				'<td><input type="button" onclick="isCheck()" value="删除">'+
				'</tr>');
	},
	error : function(data) {
		alert("请登录");
	}
});
/*购买一件商品*/
function buyCart(index){

}
/*删除购物车中的某件商品*/
function delCart(index){
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
				alert("删除成功！");
				window.location.reload();
			},
			error : function(data) {
				alert("请登录");
			}
		});
	}
}
