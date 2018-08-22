 $.ajax({
                type:"POST",
                headers: {
                    cdutb2p_sys_token: GLOBAL_TOKEN
                },
                dataType: "json",
                url:"/b2p/sys/admin/index",
                data: {},
                success:function(data){
                	 $("#userNickname").html(data.model.User.userNickname);
                     $("#userImage").attr("src",data.model.User.userImage);
                     $("#userImage2").attr("src",data.model.User.userImage);
                     $("#userImage3").attr("src",data.model.User.userImage);
                    //进行信息展示
                     $("#newUsers").html(data.model.IncreUsers);
                     $("#newOrders").html(data.model.IncreOrders);
                     $("#newGoods").html(data.model.IncreGoods);
                     $("#newVistior").html(data.model.IncreVisitors);

                },
                error:function(data){
                    //发生错误，跳转到404页面
                    alert("发生错误");
                }
            });