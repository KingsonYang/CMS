/*课程信息*/
$(function(){



})

    /*根据id删除*/
    function del(id){
        $.ajax({
            url:"/courseInfo/delById",
            data:{
                "id":id
            },
            type:"post",
            success:function(msg){
                if(msg.code==200){
                    alert("删除成功");
                    initHtml();
                }else if(msg.code==404){
                    alert("删除失败");
                }
            }
        })
    }

