/*课程信息*/
$(function(){

    var pageSize = 20;
    var pageNum = 1;
    var datas  = [];
//    init(pageSize,pageNum);

    $.ajax({
                url:"/courseInfo/queryList",
                data:{
                    "pageSize":pageSize,
                    "pageNum":pageNum
                },
                type:"post",
                success:function(msg){
                    datas = msg.list;
                    console.log(datas);
                    initTable();
                }
            })

function initTable(){
    $("#table").bootstrapTable({ // 对应table标签的id
              cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
              striped: true,  //表格显示条纹，默认为false
              pagination: true, // 在表格底部显示分页组件，默认false
              pageList: [10, 20], // 设置页面可以显示的数据条数
              pageSize: 10, // 页面数据条数
              pageNumber: 1, // 首页页码
              sidePagination: 'client', // 设置为客户端分页
              sortName: 'id', // 要排序的字段
              sortOrder: 'asc', // 排序规则
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
                          return '<button class="btn btn-primary btn-sm" onclick="del(\'' + row.stdId + '\')">删除</button>';
                      }
                  }
              ],
              data: datas,
        })
}


})

function del(id){
        $.ajax({
            url:"/courseInfo/delById",
            data:{
                "id":id
            },
            type:"post",
            success:function(msg){

            }
        })
    }