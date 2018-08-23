var img_list = new Array();
var area_stage = 0;
one = [];
two = [];
three = [];
four = [];
var area_select_id;
var brand_select_id;
var oldlevel_select;
function genUserInfo() {
	$.ajax({
	type:"POST",
	headers: {
        cdutb2p_shop_token: GLOBAL_TOKEN
    },
    dataType: "json",
    url:"/b2p/shop/customerCenter/myInfo",
    data: {},
    success:function(data){
		if(GLOBAL_TOKEN == ""){
			window.location.href="./login.html";
		}
    	if(data.model.Customer.userNickname!=null||data.model.Customer.userNickname!=""){
    		$("#userNameOne").html(data.model.Customer.userNickname);
    	}
    },
    error:function(data){
    	alert("发生错误");
    	
    }
});
}

function genArea() {
	
	$.ajax({
		type : "POST",
		headers : {
			cdutb2p_shop_token : GLOBAL_TOKEN
		},
		url : getPath() + "/shop/goods/area/list",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data['success']) {
				renderArea(data['data']);
			}
		},
		error : function(data) {
			layer.alert('系统错误');
		}

	});
	$('#select_area_province').change(function(){
		area_stage = 1;
		renderArea();
	});
	$('#select_area_city').change(function(){
		area_stage = 2;
		renderArea();
	});
	$('#select_area_area').change(function(){
		area_select_id = $('#select_area_area').find("option:selected").attr("value");
	});
}

function renderArea(data) {
	if(area_stage == 0){
		one.push(data[0]);
		for(var i in data[0]['children']){
			two.push(data[0]['children'][i]);
			for(var j in data[0]['children'][i]['children']){
				three.push(data[0]['children'][i]['children'][j]);
				for(var k in data[0]['children'][i]['children'][j]['children']){
					four.push(data[0]['children'][i]['children'][j]['children'][k]);
				}
			}
		}
		html = "";
		for(var i in two){
			if(i == 0){
				html += "<option selected value=\""+two[i]['id']+"\">"+two[i]['areaName']+"</option>";
			}else{
				html += "<option value=\""+two[i]['id']+"\">"+two[i]['areaName']+"</option>";
			}
			
		}
		$('#select_area_province').html(html);
		html = "";
		for(var i in three){
			if(three[i]['areaParentId'] == $('#select_area_province').find("option:selected").attr("value")){
				if(i == 0){
					html += "<option selected value=\""+three[i]['id']+"\">"+three[i]['areaName']+"</option>";
				}else{
					html += "<option value=\""+three[i]['id']+"\">"+three[i]['areaName']+"</option>";
				}
				
			}
		}
		$('#select_area_city').html(html);
		html = "";
		for(var i in four){
			if(four[i]['areaParentId'] == $('#select_area_city').find("option:selected").attr("value")){
				if(i == 0){
					html += "<option selected value=\""+four[i]['id']+"\">"+four[i]['areaName']+"</option>";
					area_select_id = four[i]['id'];
				}else{
					html += "<option value=\""+four[i]['id']+"\">"+four[i]['areaName']+"</option>";
				}
				
			}
		}
		$('#select_area_area').html(html);
		
		
	}
	if(area_stage == 1){
		html = "";
		for(var i in three){
			if(three[i]['areaParentId'] == $('#select_area_province').find("option:selected").attr("value")){
				html += "<option value=\""+three[i]['id']+"\">"+three[i]['areaName']+"</option>";
			}
		}
		$('#select_area_city').html(html);
		html = "";
		for(var i in four){
			if(four[i]['areaParentId'] == $('#select_area_city').find("option:selected").attr("value")){
				html += "<option value=\""+four[i]['id']+"\">"+four[i]['areaName']+"</option>";
			}
		}
		$('#select_area_area').html(html);
	}
	if(area_stage == 2){
		html = "";
		for(var i in four){
			if(four[i]['areaParentId'] == $('#select_area_city').find("option:selected").attr("value")){
				html += "<option value=\""+four[i]['id']+"\">"+four[i]['areaName']+"</option>";
			}
		}
		$('#select_area_area').html(html);
	}
}	
	
	
	
	
function genBrand() {
	$.ajax({
		type : "POST",
		headers : {
			cdutb2p_shop_token : GLOBAL_TOKEN
		},
		url : getPath() + "/shop/goods/brand",
		data : {
			type : "phone_brand"
		},
		dataType : "json",
		success : function(data) {
			if (data['success']) {
				renderBrand(data['data']);
			}
		},
		error : function(data) {
			layer.alert('系统错误');
		}

	});
}

function renderBrand(data) {
	one = []
	for ( var a in data) {
		one.push(data[a])
	}
	html = "";
	for(var i in one){
		if(i == 0){
			html += "<option selected value=\""+one[i]['id']+"\">"+one[i]['dictLabel']+"</option>";
			brand_select_id = one[i]['id'];
		}else{
			html += "<option value=\""+one[i]['id']+"\">"+one[i]['dictLabel']+"</option>";
		}
	}
	$('#brand_select').html(html);
	$('#brand_select').change(function(){
		brand_select_id = $('#brand_select').find("option:selected").attr("value");
	});
	

}


function goods_edit(){
	if($('#brand_model').val().length < 1 || $('#brand_model').val().length > 20){
		layer.alert("商品型号长度最大20");
		return;
	}
	if($('#pre_price').val().length < 1 || $('#pre_price').val().length > 6){
		layer.alert("商品原价为1-999999");
		return;
	}
	if($('#now_price').val().length < 1 || $('#now_price').val().length > 6){
		layer.alert("商品现价为1-999999");
		return;
	}
	if($('#title').val().length < 10 || $('#title').val().length > 100){
		layer.alert("商品标题长度10-100");
		return;
	}
	if($('#describe').val().length < 10 || $('#describe').val().length > 200){
		layer.alert("商品描述长度10-200");
		return;
	}
		
	var imgs = "";
	if(img_list.length == 0){
		layer.alert("请上传图片");
	}else{
		for(var i in img_list){
			imgs += img_list[i]['_raw'] + ",";
		}
	}
	$.ajax({
             type: "POST",
			 headers : {
				cdutb2p_shop_token : GLOBAL_TOKEN
			},
             url: getPath() + "/shop/sellerCenter/goods/edit",
             data: {
				goods_id:getQueryString("goods_id"),
				brand_id:brand_select_id,  
				brand_model:$('#brand_model').val(),
				oldLevel:oldlevel_select, 
				pre_price:$('#pre_price').val(), 
				now_price:$('#now_price').val(), 
				area_id:area_select_id,  
				title:$('#title').val(),  
				descibe:$('#describe').val(),
				pics:imgs,
			 },
             dataType: "json",
             success: function(data){  
                         if(data['success']){
							layer.alert(data['msg']);
							goods_id = data['data'];
							var t=setTimeout(function(){
								window.location.href = "./goods.html?goods_id="+getQueryString("goods_id");
							},2000);

						 }else{
						    layer.alert(data['msg']);
						 }
                      },
					  
             error: function(data){  
                        layer.alert('系统错误');
                   }
         });
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function initGoods() {
	if (getQueryString("goods_id") == null
			|| getQueryString("goods_id").length != 32) {
		window.location.href = "./index.html";
	}
	$.ajax({
		type : "POST",
		headers : {
			cdutb2p_shop_token : GLOBAL_TOKEN
		},
		url : getPath() + "/shop/goods/info",
		data : {
			goods_id : getQueryString("goods_id"),
		},
		dataType : "json",
		success : function(data) {
			if (data['success']) {
				renderInitGoods(data['data']);
			}
		},
		error : function(data) {
			layer.alert('系统错误');
		}

	});
}

function renderInitGoods(data) {
	$('#title').val(data['goodsTitle']);
	$('#pre_price').val(data['goodsOriginalPrice']);
	$('#now_price').val(data['goodsPresentPrice']);
	$('#brand_model').val(data['goodsBrandModel']);
	$('#describe').val(data['goodsDesc']);
	
	$('#brand_select').val(data['goodsBrandId']);
	
	area_select_id = data['goodsAreaId'];
	brand_select_id = data['goodsBrandId'];
	oldlevel_select = data['goodsOldLevel'];
	genUserInfo();
	genArea();
	genBrand();
	$('#select_oldlevel').val(data['goodsOldLevel']);
	$('#select_oldlevel').change(function(){
		oldlevel_select = $('#select_oldlevel').find("option:selected").attr("value");
	});
	
	
}

$(document).ready(function() {
	initGoods();
	$('#submit').click(function(){
		goods_edit();
	});
	

});