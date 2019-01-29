$(function(){

/*回车键绑定登陆按钮*/
/*$(document).keydown(function(event){
    if(event.keyCode==13){
        $("#user_login_btn").click();
    }
});*/

$("#user_login_btn").click(function(){

	//前端正则表达式验证
	if(!validate_add_form()){
		return false;
	}
	//验证用户名是否已经被占用
	if($(this).attr("ajax-va")=="error"){
		return false;
	}

	var kaptcha = $("#kaptcha").val();
	if (kaptcha.length == 0) {
		alert("您没有输入验证码！");
	} else {
		var name = $("#login_name").val();
		var password = $("#login_password").val();

		//发起ajax进行添加操作
		$.ajax({
			url:"/Login",
			data:{"name":name,"password":password},
			type:"post",
			success:function(msg){
				if(msg.code==200){
					//这里再次经过请求才进登录页面
					//如果放在静态static下静态页面就可以直接这样子跳页面:window.location.href = "signin.html";
					//但是在templates下必须经过请求否则就会暴露页面不安全
					//跳转到登录页面
					window.location.href = "http://localhost:8080/welcome";
				}else{
					alert("---登陆失败---");
					//显示失败信息
					//有哪个字段错误,就显示哪个字段
					if(undefined!=msg.msg.map.username){
						//显示账号错误信息
						show_validate_msg("#insert_name","error",msg.msg.map.username);
					}
					if(undefined!=msg.msg.map.password){
						show_validate_msg("#insert_password","error",msg.msg.map.password);
					}
				}

			},
			error:function(){
			}
		});
	}




});
});
