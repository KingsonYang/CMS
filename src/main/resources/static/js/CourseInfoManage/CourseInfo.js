/*课程信息*/
$(function(){

    //初始化table
    var oTable = new TableInit();
    oTable.init();

    var character = $('input[type=radio][name=course_character]:checked').val();

    /*单选按钮改变*/
    $('input[type=radio][name=course_character]').change(function() {
        character = this.value;
    });

    //注册新增按钮的事件
    $("#btn_add").click(function () {
        $("#myModalLabel").text("新增");
        $('#myModal').modal();
        $("#btn_submit").removeClass("display");
        $("#btn_update").addClass("display");
    });

    //注册新增事件
    $("#btn_submit").click(function () {
        var name = $("#course_name").val();
        var dec = $("#course_dec").val();
        var credit = $("#course_credit").val();
        $.ajax({
            url:"/courseInfo/add",
            data:{
                "name" : name,
                "dec" : dec,
                "credit" : credit,
                "character" : character
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

    //注册修改事件
    $("#btn_update").click(function () {
        var id = $("#course_id").val();
        var name = $("#course_name").val();
        var dec = $("#course_dec").val();
        var credit = $("#course_credit").val();
        $.ajax({
            url:"/courseInfo/updateById",
            data:{
                "id" : id,
                "name" : name,
                "dec" : dec,
                "credit" : credit,
                "character" : character
            },
            type:"post",
            success:function(msg){
                if(msg.code==200){
                    alert("修改成功");
                    $("#table").bootstrapTable("refresh");
                }else if(msg.code==404){
                    alert("修改失败");
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
            // strictSearch: true,
            showColumns: true,                  //是否显示所有的列（选择显示的列）
            showRefresh: true,                  //是否显示刷新按钮
            showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
            clickToSelect: true,                //是否启用点击选中行
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
                        return '<button class="btn btn-info btn-sm" onclick="selectOne(\'' + row.id + '\')">修改</button>' +
                            ' <button class="btn btn-primary btn-sm" onclick="del(\'' + row.id + '\')">删除</button>';
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

function upd(id) {

    var oneRow = selectOne(id);
    console.log(oneRow);

}

function selectOne(id) {
    $("#myModalLabel").text("修改");
    $('#myModal').modal();
    $("#btn_submit").addClass("display");
    $("#btn_update").removeClass("display");
    $.ajax({
        url:"/courseInfo/selectByID",
        data:{
            "id":id
        },
        type:"get",
        success:function(msg){
            $("#course_id").val(id);
            $("#course_name").val(msg.name);
            $("#course_dec").val(msg.dec);
            $("#course_credit").val(msg.credit);

            $('input[type=radio][name=course_character]').each(function(index) {
                if (msg.character == $('input[type=radio][name=course_character]').get(index).value) {
                    $('input[type=radio][name=course_character]').get(index).checked = true;
                }
            });
        }
    })
}

