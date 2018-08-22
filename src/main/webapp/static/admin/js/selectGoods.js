$.ajax({
                type:"POST",
                dataType: "json",
                url:"/b2p/sys/admin/goods/all",
                data: {},
                success:function(data){
                	if(data.model.GoodsList.length>0){
                		 //展示商品
                        $.each(data.model.GoodsList, function(index, item) {
                            $('#tbody').append('<tr>'+
                            '<td>'+index+'</td>'+
            				   '<td><img style="width:60px;height:40px;" src=/b2p'+item.goodsPics+'></td>'+
            				   '<td>手机</td>'+
            				   '<td>四川省-成都市</td>'+
                            '<td>苹果</td>'+
                            '<td>'+item.goodsOriginalPrice+'</td>'+
                            '<td>'+item.goodsPresentPrice+'</td>'+
                            '<td>'+item.goodsUpTime+'</td>'+
                            '<td>'+item.goodsEndTime+'</td>'+
                            '<td>'+item.goodsClickTimes+'</td>'+
                            '<td>'+item.status+'</td>'+
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