$.ajax({
                type:"POST",
                dataType: "json",
                url:"/b2p/sys/admin/comment/all",
                data: {},
                success:function(data){
                   alert(data.model.CommentList.length);
                   if(data.model.CommentList.length>0){
                	   //进行数据展示
                       $.each(data, function(index, item) {
                           $('#tbody').append('<tr>'+
                           '<td>'+index+'</td>'+   
                           '<td>'+item.commentUserId+'</td>'+
                           '<td>'+item.commentGoodsId+'</td>'+
                           '<td>'+item.commentMessage+'</td>'+
                           '<td>'+item.createDate+'</td>'+
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