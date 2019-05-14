$(function(){

/*回车键绑定登陆按钮*/
$(document).keydown(function(event){
    if(event.keyCode==13){
        $("#user_login_btn").click();
    }
});

var radio = $('input[type=radio][name=user]:checked').val();

/*单选按钮改变*/
$('input[type=radio][name=user]').change(function() {
        radio = this.value;
        console.log(radio);
});

/*点击登陆*/
$("#user_login_btn").click(function(){

    console.log(radio);

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
		var id = $("#login_name").val();
		var password = $("#login_password").val();

		//发起ajax进行添加操作
		$.ajax({
			url:"/Login",
			data:{"id":id,"password":password,"roleId":radio},
			type:"post",
			success:function(msg){
				if(msg.code==200){
					//这里再次经过请求才进登录页面
					//如果放在静态static下静态页面就可以直接这样子跳页面:window.location.href = "signin.html";
					//但是在templates下必须经过请求否则就会暴露页面不安全
					//跳转到登录页面
					window.location.href = "http://localhost:8080/index";
				}else{
					alert(msg.msg.msg);
					//显示账号错误信息
//					show_validate_msg("#error_msg","error",msg.msg.msg);
				}

			},
			error:function(){
			}
		});
	}
});

    function changeLoginbtn() {
        var username = document.getElementById('login_name').value;
        var password = document.getElementById('login_password').value;
        if (username != "" && password != "") {
            document.getElementById('user_login_btn').removeAttribute("class");
            document.getElementById('user_login_btn').setAttribute("class","btn-current-big btn-default-main");
            document.getElementById('user_login_btn').removeAttribute("disabled");
        }else{
            document.getElementById('user_login_btn').removeAttribute("class");
            document.getElementById('user_login_btn').setAttribute("disabled","disabled");
            document.getElementById('user_login_btn').setAttribute("class","btn-current-big btn-disabled");
        }
    }
});
