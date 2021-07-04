<%--
  Created by IntelliJ IDEA.
  User: Chenzhimei
  Date: 2021/6/19
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<%=basePath%>/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="<%=basePath%>/static/plugins/bootstrap/css/bootstrap-table.css" rel="stylesheet" type="text/css" >
    <title>用戶列表</title>
</head>
<body>
<div>
测试一下web.xml里的<context-param>:${initParam.greeting}
</div>
<div>
    <form id="search-list">
        姓名<input name="name" value="amy"><br>
        年龄<input name="age" value="11"><br>
    </form>
    <table id="datagrid" style="table-layout:fixed;"></table>
</div>
<!--bootstraptable要在bootstrap前面……juqery要在這兩個前面-->
<script type="text/javascript" src="<%=basePath%>/static/plugins/jquery/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugins/jquery/jquery.form.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugins/bootstrap/js/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugins/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/static/plugins/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
    // bootbox.setDefaults("locale","zh_CN");
    $(function() {
        $('#datagrid').bootstrapTable({
            url : "<%=basePath%>/user/userData",
            method : "GET",
            dataType : "json",
            classes : 'table-no-bordered table-cell',
            sidePagination : "server",
            cache:false,
            contentType : "application/x-www-form-urlencoded",
            pagination : true,
            sortable: true,
            sortName: "hello david~",
            paginationLoop : true,//默认true，分页条无限循环
            onlyInfoPagination : false,
            showColumns : true,//默认为false隐藏某列下拉菜单，设为true显示
            showRefresh : false,//默认为false隐藏刷新按钮，设为true显示
            showToggle : false,//默认为false隐藏视图切换按钮，设为true显示
            showPaginationSwitch : false,
//			showFullscreen : true,
            pageNumber : 1,                       //初始化加载第一页，默认第一页
            pageSize : 20,                       //每页的记录行数（*）
            pageList :[20, 50],
            // height : getHeight(),
            queryParamsType : "",//默认是limit
            toolbar : "#toolbar",//操作栏
            clickToSelect : true,
            singleSelect : true,
            buttonsAlign : "right",
            //得到查询的参数
            queryParams : function (params) {//这个params是上面的属性
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                var temp = {
                    d:new Date().getTime(),
                    pageSize : params.pageSize, //页面大小
                    pageNum : params.pageNumber,
                    sortName : params.sortName,
                    sortOrder : params.sortOrder
                    //query:getQueryParams("search-list", true)
                };
                console.log(params)
                console.log($("#search-list").serialize())
                console.log($.param(temp))
                return $.param(temp) + "&" + $("#search-list").serialize();
                // return $.param(temp)+"&"+$('#search-list').serialize();
                //在 jQuery 中，我们可以使用$.param()方法将数组或对象转化为字符串序列，以便用于 URL 查询字符串或 Ajax 请求
                //$(selector).serialize():serialize() 方法通过序列化表单值创建 URL 编码文本字符串。您可以选择一个或多个表单元素（如输入和/或文本区），或表单元素本身。序列化的值可在生成 AJAX 请求时用于 URL 查询字符串中。
            },
            columns : [[
                {title : '姓名',
                field : 'name',
                width :50,
                align:"center",
                sortable: true
            },{title : '年齡',
                field : 'age',
                width : 120,
                align:'left',
                sortable: true
            },{
                title : '性別',
                field : 'gender',
                align : "center",
                width : 90,
                sortable: true
            }]],
            // icons:{
            //     paginationSwitchDown: 'icon-chevron-down',
            //     paginationSwitchUp: 'icon-chevron-up',
            //     refresh: 'icon-refresh',
            //     toggle: 'icon-list-alt',
            //     columns: 'icon-th',
            //     detailOpen: 'icon-plus',
            //     detailClose: 'icon-minus',
            //     fullscreen:'icon-fullscreen'
            // },
            onLoadSuccess: function(data){
                console.log("onLoadSuccess")
                console.log(data)
            },
            onLoadError: function () {
                console.log("数据加载失败！");
             },
        });
    })
    /************* 查询 start *************/
//var documentQuery, table = null, needRefresh = false, dlg = null, find = null;
    function query(){
        var url = "${contextPath}/gwbl/documentQuery/documentQueryData.do";
        $('#documentQuery').bootstrapTable('refresh',
            {url : url,
                pageNumber:1,
                query:getQueryParams("search-list", true)});
    }
</script>
</body>
</html>
