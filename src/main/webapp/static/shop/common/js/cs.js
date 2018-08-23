
function sawComment(){
	selectComment();
    $('html, body').animate({
			scrollTop : $("#tohere").offset().top
		}, 2000);
}
/**
 * 查询商品下的评论
 * 
 * @returns
 */
function selectComment(){
	$.ajax({
		type : "POST",
		headers : {
			cdutb2p_shop_token : GLOBAL_TOKEN
		},
		url : getPath() + "/shop/comment/getAll",
		data : {
			goods_id : getQueryString("goods_id"),
		},
		dataType : "json",
		success : function(data) {
			$.each(data.model.ShopCommentList, function(index, item) {	
				$("#tbody").append(	'<div class="info_baby">'+
				'<div class="baby_talk">'+
					'<p class="baby_face  clearfix">'+
						'<span class="baby_face_img"> <img src=../..'+item.userImage+' id="seller_img" alt="">'+
						'</span> <span><i id="seller_nickname"></i>'+item.userNickname+'</span>'+
					'</p>'+
					'<div class="baby_kuang clearfix">'+
						'<span class="icon_png sanjiao"></span>'+
						'<p id="goods_desc">'+item.commentMessage+'</p>'+
					'</div>'+
				'</div>'+
			'</div>');
			});
		},
		error : function(data) {
			//slayer.alert('系统错误');
		}

	});
}
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