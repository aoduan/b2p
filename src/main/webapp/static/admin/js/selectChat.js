$.ajax({
                type:"POST",
                dataType: "json",
                url:"/b2p/sys/admin/chat/all",
                data: {},
                success:function(data){
                   alert(data.model.ChatList.length);
                   if(data.model.ChatList.length>0){
                	 //进行数据展示
                       $.each(data, function(index, item) {
                           $('#tbody').append('<tr>'+
                           '<td>'+index+'</td>'+   
                           '<td>'+item.chatUserId+'</td>'+
                           '<td>'+item.chatGoods+'</td>'+
                           '<td>'+item.chatMessage+'</td>'+
                           '<td>'+item.createDate+'</td>'+
                           '<td>'+item.chatStatus+'</td>'+
                           '<td>'+
                           '<button class="btn btn-primary" onclick="update()">修改</button>'+
                           '<button class="btn btn-primary" style="background:red" onclick="del()">删除</button>'+
                           '</td>'+
                       '</tr>');
                       });
                   }
                   else{
                	alert("没有数据");
                	//进行隐藏
                	$('#table').hide();
                   }
                },
                error:function(data){
                    //发生错误，跳转到404页面
                    alert("发生错误");
                }
            });