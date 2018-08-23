var l = localStorage.cdutb2p_shop_token;
var s = sessionStorage.cdutb2p_shop_token;
if(s != null){
	GLOBAL_TOKEN = s;
}else if(l != null){
	GLOBAL_TOKEN = l;
}else{
	GLOBAL_TOKEN = ''
}

function clearToken(){
	localStorage.cdutb2p_shop_token = "";
	sessionStorage.cdutb2p_shop_token = "";
}