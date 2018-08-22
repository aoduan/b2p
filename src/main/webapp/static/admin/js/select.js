$.ajax({
                type:"POST",
                dataType: "json",
                url:"/b2p/sys/admin/selectAllUser",
                data: {},
                success:function(data){
                   if(data!=null){
                       //进行数据展示
                       $.each(data, function(index, item) {
                           $('#tbody').append('<tr>'+
                           '<td>'+index+'</td>'+   
                           '<td>'+item.userName+'</td>'+
                           '<td>'+item.userNickname+'</td>'+
                           '<td>'+item.userEmail+'</td>'+
                           '<td>'+item.userRealName+'</td>'+
                           '<td>'+item.userIdcard+'</td>'+
                           '<td>'+item.userAddress+'</td>'+
                           '<td>'+item.userTelphone+'</td>'+
                           '<td>'+item.createDate+'</td>'+
                           '<td>'+item.userStatus+'</td>'+
                           '<td>'+
                           '<button class="btn btn-primary" onclick="update()">修改</button>'+
                           '<button class="btn btn-primary" style="background:red" onclick="del()">删除</button>'+
                           '</td>'+
                       '</tr>');
                       });
                  }
                   else{
                	   alert("没有数据");
                	   $("#thread").hide();
                   }
                   

                },
                error:function(data){
                    //发生错误，跳转到404页面
                    alert("发生错误");
                }
            });