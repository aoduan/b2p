$.ajax({
			type:"POST",
			headers: {
		        cdutb2p_shop_token: GLOBAL_TOKEN
		    },
		    dataType: "json",
		    url:"/b2p/shop/customerCenter/myOrder",
		    data:{},
		    success:function(data){
		    	  $.each(data.model.OrderList, function(index, item) {
		    		  $("#tbody").append('<tr>'+
	                    		 '<td >'+item.orderGoodsId+'</td>'+
	                             '<td >'+item.createDate+'</td>'+
	                             '<td >'+item.orderPrice+'</td>'+
	                             '<td >'+item.orderStatus+'</td>'+
	                             '<td ><input type="hidden" value='+item.id+'></td>'+
	                             '<td ><button>催单</button><button>确认到达</button></td>'+
	                             '<td ><button>评价</button><button>投诉</button><button>删除</button></td>'+
	                     '</tr>'
	                     ); 
                  });
		    },
		    error:function(data){
		    	alert("请登录");
		    }
});
function del(){
	
}