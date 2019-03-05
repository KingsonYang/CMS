/*课程信息*/
$(function(){

    var pageSize = 5;
    var pageNum = 1;
    show(pageSize,pageNum);
    init(pageSize,pageNum);
})
function show(x,y){
    console.log(x + "---" + y);
}
function init(pageSize,pageNum){
        $.ajax({
            url:"/courseInfo/queryList",
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            data:{
                "pageSize":pageSize,
                "pageNum":pageNum
            },
            type:"post",
            success:function(msg){
                console.log(msg);
//                if(msg.code==200){
//                    console.log("ok");
//                }else if(msg.code==404){
//                    console.log("error");
//                }
            }
        })
    }