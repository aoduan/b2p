$.ajax({
	type : "POST",
	headers : {
		cdutb2p_shop_token : GLOBAL_TOKEN
	},
	dataType : "json",
	url : "/b2p/shop/customerCenter/selectMyCollection",
	data : {},
	success : function(data) {
		
		$("#tbody").append('<form method="post">');
		$.each(data.model.CollectionList, function(index, item) {
			
			$("#tbody").append('<tr style="height:120px; overflow: hidden;display:block;margin-top:15px;border-bottom:thick dotted #fff;">' +
					'<td style="width:20px;margin-right:10px"><input name="checkbox" type="checkbox" /></td>' +
					'<td><a href="goods.html?goods_id='+item.collectionGoodsId+'"><img style="width:100px;height:100px" src=../..'+item.url+'></a></td>'+
					'<td><span style="padding-top:20px;font-size:12px;margin-left:10px;text-align:left;width:200px;height:100px;overflow:hidden;display:block">'+item.describe+'<span></td>' + 
					'</td>'+
				    '</tr>');	
		});
		$("#tbody").append('<tr style="height:120px; overflow: hidden;display:block;margin-top:15px;border-bottom:thick dotted #fff;">' +
				'<td style="width:60px;margin-right:10px"><input type="checkbox" name="answer" onclick="checkAll(this)"/>全选<td>'+
				'<td style="width:60px"><input type="button" onclick="isCheck()" value="删除" style="background:#ff552e;color:#fff;border:1px solid #ff552e;">'+
				'</tr>');
		$("#tbody").append("</form>");
	},
	error : function(data) {
		alert("请登录");
	}
});
/*全部选中选择框按钮*/
function checkAll(obj){
	 var answer= document.getElementsByName("checkbox");
	 if(obj.checked==true){
		  for(var i=0;i<answer.length;i++){
		   answer[i].checked = true;
		  }
	 }else{
		  for(var i=0;i<answer.length;i++){
		   answer[i].checked = false;
		  }
	 }
}
/*将选中的选择框进行提交*/
function isCheck(){
	var array=new Array();
	var answer= document.getElementsByName("checkbox");
	for(var i=0;i<answer.length;i++){
		if(answer[i].checked==true){
			var temp=$("#id"+i).val();
			array[i]=temp;
		}
	}
	for(var i=0;i<array.length;i++){
		console.log(array[i]);
	}

	//进行请求传递
	$.ajax({
		type : "POST",
		headers : {
			cdutb2p_shop_token : GLOBAL_TOKEN
		},
		dataType : "json",
		url : "/b2p/shop/customerCenter/delMyCollection",
		data:{array:array},
		success:function(data){
			alert("删除成功");
		},
		error:function(data){
			
		}
		
	});
	
	
}

