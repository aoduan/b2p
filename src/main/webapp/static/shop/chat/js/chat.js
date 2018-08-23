var path = getRealPath();
var token = GLOBAL_TOKEN;
var from_id;
var from_name;
var from_img;
var to_id;
var to_name;
var to_img;	
var msg_map = {};
var img_map = {};
var name_map = {};
var unread_map = {};
var user_id;
var user_img;
var total_unread = 0;
var websocket;
if(token != ""){
	
	if ('WebSocket' in window) {
		websocket= new WebSocket("ws://" + getRealPath_NoHttp() + "/websocket/chat?token="+token); 
	}else {
		websocket = new SockJS(path + "/sockjs/chat?token="+token);
	}
	
	websocket.onopen = function(event) {
		
		getMsgList();
	};
	websocket.onmessage = function(event) {
		var data=JSON.parse(event.data);
		if(data != null){
			//total_unread = total_unread +1;
			//if(unread_map[data['to_id']] == null){
			//	unread_map[data['to_id']] = 1;
			//}else{
			//	unread_map[data['to_id']] = unread_map[data['to_id']] + 1;
			//}
			
			openOChat(data['from_id'],data['from_name'],data['from_img'],data['msg'],data['type'],data['date'],"0");
			
			
		}	
	};
	websocket.onerror = function(event) {
		//layer.alert("系统错误");
	};
	websocket.onclose = function(event) {
		//layer.alert("系统错误");	
	}
}else{
	$('#chat').hide()
}




function send_msg_to_server(to_id,msg,type){
	rs = {};
	rs['to_id'] = to_id;
	rs['msg'] = msg;
	rs['type'] = type;
	websocket.send(JSON.stringify(rs));
}


function renderChat(id){
	$(".chat-list-people").each(function () {
		if(this['id'] == "chat-list-" + id){
			//$(".chatBox").toggle(10);
			$(this).click(function () {
				var n = $(this).index();
				if(id == null){
					return;
				}
			    data = msg_map[id];
				to_id = id;
				$('.chatBox-content-demo').html(data);
				
				$(".chatBox-head-one").toggle();
				$(".chatBox-head-two").toggle();
				$(".chatBox-list").fadeToggle();
				$(".chatBox-kuang").fadeToggle();
				total_unread = total_unread - unread_map[to_id];
				unread_map[to_id] = 0;
				reRenderUnread(to_id);
				$(".ChatInfoName").text($(this).children(".chat-name").children("p").eq(0).html());
				$(".ChatInfoHead>img").attr("src", $(this).children().eq(0).children("img").attr("src"));
				$(document).ready(function () {
					$("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
				});
			});
	}});
}

	
function openChat(seller_id,seller_nickname,seller_img){
	if(seller_id == user_id){
		return;
	}
	to_id = seller_id;
	to_name = seller_nickname;
	to_img = seller_img;
	if(msg_map[seller_id] != null){
		$(".chatBox").toggle(10);
		return;
	}
	msg_map[seller_id] = "";
	img_map[seller_id] = seller_img;
	name_map[seller_id] = seller_nickname;
	//unread_map[seller_id] = unread_map[seller_id] + 1;
	$(".chatBox").toggle(10);
	$('#chat_user_img').attr("src",getRealPath() + from_img);
	$('#chat_user_name').html(from_name);
	html = "";
	html += "<div class=\"chat-list-people\" id=\"chat-list-"+seller_id+"\">";
    html += "<div><img src=\""+getRealPath()+seller_img+"\" alt=\"头像\"/></div>";
    html += "<div class=\"chat-name\">";
    html += "<p>"+seller_nickname+"</p>";
    html += "</div>";
    html += " <div id=\"to_id_unread_num_"+ to_id +"\" class=\"message-num\">0</div>";
    html += "</div>";
	$('.chatBox-list').prepend(html);
	
	html = "";
	html += "<div class=\"chatBox-content-demo\" id=\"chatBox-content-demo\"></div>";
	$('.chatBox-content').append(html);
	renderChat(seller_id);

}
function openOChat(from_id,from_name,from_img,msg,type,date,is_read){
	if(from_id == user_id){
		return;
	}
	to_id = from_id;
	to_name = from_name;
	to_img = from_img;
	
	if(msg_map[from_id] != null){
		if($(".chatBox").is(':hidden')){
			//$(".chatBox").toggle(10);
		}
	}else{
		msg_map[from_id] = "";
		img_map[from_id] = from_img;
		name_map[from_id] = from_name;
		unread_map[from_id] = 0;
		$('#chat_user_img').attr("src",getRealPath() + from_img);
		$('#chat_user_name').html(from_name);
		html = "";
		html += "<div class=\"chat-list-people\" id=\"chat-list-"+from_id+"\">";
		html += "<div><img src=\""+getRealPath()+from_img+"\" alt=\"头像\"/></div>";
		html += "<div class=\"chat-name\">";
		html += "<p>"+from_name+"</p>";
		html += "</div>";
		html += " <div id=\"to_id_unread_num_"+ to_id +"\" class=\"message-num\">0</div>";
		html += "</div>";
		$('.chatBox-list').prepend(html);
	
		html = "";
		html += "<div class=\"chatBox-content-demo\" id=\"chatBox-content-demo\"></div>";
		$('.chatBox-content').append(html);
		if($(".chatBox").is(':hidden')){
			//$(".chatBox").toggle(10);
		}
		
		renderChat(from_id);
		
	}
	if (msg != "") {
		if(is_read == "0"){
			unread_map[from_id] = unread_map[from_id] + 1;
			total_unread = total_unread + 1;
			reRenderUnreadI(from_id);
		}
		html = "";
		if(type == "1"){
			html += "<div class=\"clearfloat\"><div class=\"author-name\"><small class=\"chat-date\">"+formatDateTime(new Date(date))+"</small>";
            html += "</div><div class=\"left\"><div class=\"chat-avatars\"><img src=\""+getRealPath()+img_map[to_id]+"\" alt=\"头像\"></div>";
            html += "<div class=\"chat-message\">"+msg+"</div></div></div>";
		}
		if(type == "2"){
			html += "<div class=\"clearfloat\"><div class=\"author-name\"><small class=\"chat-date\">"+formatDateTime(new Date(date))+"</small>";
            html += "</div><div class=\"left\"><div class=\"chat-avatars\"><img src=\""+getRealPath()+img_map[to_id]+"\" alt=\"头像\"></div>";
            html += "<div class=\"chat-message\">"+msg+"</div></div></div>";
		}
		if(type == "3"){
			html += "<div class=\"clearfloat\"><div class=\"author-name\"><small class=\"chat-date\">"+formatDateTime(new Date(date))+"</small>";
            html += "</div><div class=\"left\"><div class=\"chat-avatars\"><img src=\""+getRealPath()+img_map[to_id]+"\" alt=\"头像\"></div>";
            html += "<div class=\"chat-message\"><img src=\""+msg+"\" alt=\"\"></div></div></div>";
		}
		msg_map[to_id] = msg_map[to_id] + html;
		if($('#chat_user_name').html() == to_name){
			$('.chatBox-content-demo').html(msg_map[to_id]);
			$("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
		}
		
    }

	

}
function openTChat(from_id,from_name,from_img,msg,type,date){
	if(from_id == user_id){
		return;
	}
	to_id = from_id;
	to_name = from_name;
	to_img = from_img;
	
	if(msg_map[from_id] != null){
		if($(".chatBox").is(':hidden')){
			//$(".chatBox").toggle(10);
		}
	}else{
		msg_map[from_id] = "";
		img_map[from_id] = from_img;
		name_map[from_id] = from_name;
		unread_map[from_id] = 0;
		//$('#chat_user_img').attr("src",getRealPath() + from_img);
		//$('#chat_user_name').html(from_name);
		html = "";
		html += "<div class=\"chat-list-people\" id=\"chat-list-"+from_id+"\">";
		html += "<div><img src=\""+getRealPath()+from_img+"\" alt=\"头像\"/></div>";
		html += "<div class=\"chat-name\">";
		html += "<p>"+from_name+"</p>";
		html += "</div>";
		html += " <div id=\"to_id_unread_num_"+ to_id +"\" class=\"message-num\">0</div>";
		html += "</div>";
		$('.chatBox-list').prepend(html);
	
		html = "";
		html += "<div class=\"chatBox-content-demo\" id=\"chatBox-content-demo\"></div>";
		$('.chatBox-content').append(html);
		if($(".chatBox").is(':hidden')){
			//$(".chatBox").toggle(10);
		}
		renderChat(from_id);
	}
	
	if (msg != "") {
		html = "";
		if(type == "1"){
			html += "<div class=\"clearfloat\">" +
			"<div class=\"author-name\"><small class=\"chat-date\">"+formatDateTime(new Date(date))+"</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " +msg + " </div> " +
            "<div class=\"chat-avatars\"><img src=\""+getRealPath()+user_img+"\" alt=\"头像\" /></div> </div> </div>";
		}
		if(type == "2"){
			html += "<div class=\"clearfloat\">" +
                "<div class=\"author-name\"><small class=\"chat-date\">"+formatDateTime(new Date(date))+"</small> </div> " +
                "<div class=\"right\"> <div class=\"chat-message\"> " + msg + " </div> " +
                "<div class=\"chat-avatars\"><img src=\""+getRealPath()+user_img+"\" alt=\"头像\" /></div> </div> </div>";
		}
		if(type == "3"){
			html += "<div class=\"clearfloat\">" +
                "<div class=\"author-name\"><small class=\"chat-date\">"+formatDateTime(new Date(date))+"</small> </div> " +
                "<div class=\"right\"> <div class=\"chat-message\"><img src=" + msg + "></div> " +
                "<div class=\"chat-avatars\"><img src=\""+getRealPath()+user_img+"\" alt=\"头像\" /></div> </div> </div>";
		}
		msg_map[to_id] = msg_map[to_id] + html;
    }
	

}


function openLChat(data){
	var rows = data;
	rows.sort(function(a,b){
		return  a['date'] - b['date'];//时间正序
	});
	
	if(data != null){
		for(var i in data){
			if(user_id == data[i]['from_id']){
				openTChat(data[i]['to_id'],data[i]['to_name']
					,data[i]['to_img'],data[i]['msg'],data[i]['type'],data[i]['date']);
			}
			else{
				openOChat(data[i]['from_id'],data[i]['from_name']
					,data[i]['from_img'],data[i]['msg'],data[i]['type'],data[i]['date'],data[i]['status']);
				
			}
			
		}
	}

}

function renderChatList(data){
	for(var i in data){
		
		to_id = data[i]['to_id'];
		to_img = data[i]['to_img'];
		to_name = data[i]['to_name'];
		
		// if(to_id == user_id){
			// to_id = data[i]['from_id'];
			// to_img = data[i]['from_img'];
			// to_name = data[i]['from_name'];
		// }
		if(msg_map[to_id] != null){
			if($(".chatBox").is(':hidden')){
				//$(".chatBox").toggle(10);
			}
			break;
		}
		
		
		msg_map[to_id] = "";
		img_map[to_id] = to_img;
		name_map[to_id] = to_name;
		unread_map[to_id] = 0;
		//$('#chat_user_img').attr("src",getRealPath() + to_img);
		//$('#chat_user_name').html(to_name);
		html = "";
		html += "<div class=\"chat-list-people\" id=\"chat-list-"+to_id+"\">";
		html += "<div><img src=\""+getRealPath()+to_img+"\" alt=\"头像\"/></div>";
		html += "<div class=\"chat-name\">";
		html += "<p>"+to_name+"</p>";
		html += "</div>";
		html += " <div id=\"to_id_unread_num_"+ to_id +"\" class=\"message-num\">0</div>";
		html += "</div>";
		$('.chatBox-list').prepend(html);
	
		html = "";
		html += "<div class=\"chatBox-content-demo\" id=\"chatBox-content-demo\"></div>";
		$('.chatBox-content').append(html);
		if($(".chatBox").is(':hidden')){
			//$(".chatBox").toggle(10);
		}
		renderChat(to_id);
		getMsg(to_id);
	}
	
}

function reRenderUnread(id){
    $(".chat-message-num").html(total_unread);
	
	$('#to_id_unread_num_' + id).html(0);
}

function reRenderUnreadI(id){
    $(".chat-message-num").html( total_unread);

	$('#to_id_unread_num_' + id).html(unread_map[id]);
}

function getMsgList(){
	$.ajax({
		type : "POST",
		headers : {
			cdutb2p_shop_token : GLOBAL_TOKEN
		},
		url : getPath() + "/shop/chat/message/list",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data['success']) {
				user_id = data['user']['id'];
				user_img = data['user']['userImage'];
				renderChatList(data['data']);
			}else{
				
			}
		},
		error : function(data) {
			//layer.alert('系统错误');
		}

	});
	
}

function getMsg(to_id){
	$.ajax({
		type : "POST",
		headers : {
			cdutb2p_shop_token : GLOBAL_TOKEN
		},
		url : getPath() + "/shop/chat/message/get",
		data : {
			from_id:to_id,
		},
		dataType : "json",
		success : function(data) {
			if (data['success']) {
				openLChat(data['data']);
			}
		},
		error : function(data) {
			//layer.alert('系统错误');
		}

	});
	
}

var formatDateTime = function (date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h=h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var second=date.getSeconds();
    second=second < 10 ? ('0' + second) : second;
	return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}