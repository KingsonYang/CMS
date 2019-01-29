/*修改密码*/
$(function(){

    /*确认旧密码*/
    $("#old_password").blur(function(){
            var password = $("#old_password").val();
            $.ajax({
                url:"/users/checkOldPwd",
            	data:{"password":password},
            	type:"post",
            	success:function(msg){
            	    if(msg.code==200){
                        $("#error_msg_1").text("").show();
            	    }else if(msg.code==404){
                        $("#error_msg_1").text("旧密码错误！").show();
                        $("#old_password").focus();
            	    }
            	}
            })
    })

    /*确认新密码*/
    function checkPwd(){
        var new_pwd = $("#new_password").val();
        var IS_new_pwd = $("#IS_new_password").val();
//        console.log(new_pwd + "+" + IS_new_pwd);
        if(!IS_new_pwd){
            $("#error_msg_2").text("请输入确认密码！").show();
            $("#IS_new_password").focus();
        }else if(new_pwd != IS_new_pwd){
            $("#error_msg_2").text("两次输入密码不一致！").show();
            $("#IS_new_password").focus();
        }else{
            $("#error_msg_2").text("").show();
            $.ajax({
                url:"/users/changePwd",
                data:{"password":new_pwd},
                type:"post",
                success:function(msg){
                    if(msg.code==200){
                       alert("密码修改成功");
                       window.location.href = "http://localhost:8080/welcome";
                    }else if(msg.code==404){
                        alert("密码修改失败");
                    }
                }
            });
        }
    }

   /*提交按钮*/
    $("#change_password_btn").click(function(){
        var old_pwd = $("#old_password").val();
        if(!old_pwd){
            $("#error_msg_1").text("请输入旧密码！").show();
        }
        checkPwd();

    })

   /*重置按钮*/
});