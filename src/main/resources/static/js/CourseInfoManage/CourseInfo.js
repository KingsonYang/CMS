/*课程信息*/
$(function(){

    //初始化table
    var oTable = new TableInit();
    oTable.init();

    //注册新增按钮的事件
    $("#btn_add").click(function () {
        $("#myModalLabel").text("新增");
        $('#myModal').modal();
    });

    //注册新增事件
    $("#btn_submit").click(function () {
        $.ajax({
            url:"/courseInfo/add",
            data:{
                "" : ""
            },
            type:"post",
            success:function(msg){
                if(msg.code==200){
                    alert("新增成功");
                    $("#table").bootstrapTable("refresh");
                }else if(msg.code==404){
                    alert("新增失败");
                }
            }
        })
    });
});

var TableInit = function(){
    var oTableInit = new Object();
    oTableInit.init = function(){
        $("#table").bootstrapTable({ // 对应table标签的id
            url: '/courseInfo/queryList1',
            method: "post",
            cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
            striped: true,  //表格显示条纹，默认为false
            pagination: true, // 在表格底部显示分页组件，默认false
            pageList: [10, 20], // 设置页面可以显示的数据条数
            pageSize: 10, // 页面数据条数
            pageNumber: 1, // 首页页码
            sidePagination: 'client', // 设置为客户端分页
            sortName: 'id', // 要排序的字段
            sortOrder: 'asc', // 排序规则
            queryParams: oTableInit.queryParams,//传递参数（*）
            search: true,
            columns: [
                {checkbox: true, align: 'center' },
                {field: 'id', title: '编号', align: 'center', valign: 'middle' },
                {field: 'name',title: '课程名',align: 'center',valign: 'middle'},
                {field: 'dec',title: '简介',align: 'center',valign: 'middle'},
                {field: 'credit',title: '学分',align: 'center',valign: 'middle'},
                {field: 'character',title: '必/选修课',align: 'center',valign: 'middle',
                    formatter: function (value, row, index){ // 单元格格式化函数
                        var text = '-';
                        if (value == 1) {
                            text = "必修课";
                        } else if (value == 2) {
                            text = "选修课";
                        }
                        return text;
                    }},
                {title: "操作",align: 'center',valign: 'middle',width: 160, // 定义列的宽度，单位为像素px
                    formatter: function (value, row, index) {
                        return '<button class="btn btn-primary btn-sm" onclick="del(\'' + row.id + '\')">删除</button>';
                    }
                }
            ],
        })
    };
    oTableInit.queryParams = function(params){
        var param = {
            /*pageIndex: Math.ceil(params.offset / params.limit) + 1,
             pageSize: params.limit,
             order: params.order,
             ordername: params.sort,
             startDateTime: $("#dateSearch .startDate").val(),
             endDateTime: $("#dateSearch .endDate").val(),
             search: $("#dateSearch .imuserid").val()*/
            search: "数学"
        };
        return param;
    }

    return oTableInit;
}

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
                $("#table").bootstrapTable("refresh");
            }else if(msg.code==404){
                alert("删除失败");
            }
        }
    })
}

