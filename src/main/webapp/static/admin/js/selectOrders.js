$.ajax({
                type:"POST",
                dataType: "json",
                url:"/b2p/sys/admin/order/all",
                data: {},
                success:function(data){
                   if(data.model.OrderList.length>0){
                	 //展示数据
                     $.each(data.model.OrderList, function(index, item) {
                           $('#tbody').append('<tr>'+
                           '<td>'+index+'</td>'+
           				   '<td>'+item.orderGoodsId+'</td>'+
           				   '<td>'+item.orderBuyerId+'</td>'+
           				   '<td>'+item.orderPrice+'</td>'+
                           '<td>'+item.orderAddress+'</td>'+
                           '<td>'+item.createDate+'</td>'+
                           '<td>'+item.orderStatus+'</td>'+
           				   '<td>'+
                           '<button class="btn btn-primary" onclick="update()">修改</button>'+
                           '<button class="btn btn-primary" style="background:red" onclick="del()">删除</button>'+
                           '</td>'+
                       '</tr>');
                       });
                   }
                   else{
                	 alert("没有数据");
                     $('#table').hide();
                   }
                },
                error:function(data){
                    //发生错误，跳转到404页面
                    alert("发生错误");
                }
            });