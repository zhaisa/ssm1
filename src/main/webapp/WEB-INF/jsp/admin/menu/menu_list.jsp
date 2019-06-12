<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath %>">
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Insert title here</title>

	<!-- bootstrap样式-->
	<link type="text/css" rel="stylesheet"
		  href="<%=basePath%>plugins/page/css/bootstrap-3.3.5.min.css" />

	<!-- jquery.pagination所需CSS -->
	<link type="text/css" rel="stylesheet" href="<%=basePath%>plugins/page/css/pagination.css" />

</head>
<body>
<br>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-md-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-inline">
						<input type="button" class="btn btn-default" value="新增" onclick="toAddDialog();"/>
					</form>
					<!-- demo  -->
					<table class="table" id="mTable">
						<thead>
						<tr>
							<th>菜单ID</th>
							<th>菜单名称</th>
							<th>菜单地址</th>
							<th>菜单类型</th>
							<th>菜单排序</th>
							<th>菜单状态</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody id="content">

						</tbody>
					</table>
					<div id="Pagination" class="pagination"></div>
					<!-- demo  -->
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>plugins/page/js/jquery.min.js"></script>
<!-- jquery.pagination所需JS 注意必须放在jquery.js后面 -->
<script type="text/javascript" src="<%=basePath%>plugins/page/js/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zDrag.js"></script>
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zProgress.js"></script>
<script type="text/javascript">

    var pageIndex = Number(${pageIndex});

    var totalCount = Number(${totalCount});

    $(document).ready(function () {
        //加入分页的绑定
        $("#Pagination").pagination(totalCount, {
            callback : pageselectCallback,
            prev_text : '< 上一页',
            next_text: '下一页 >',
            items_per_page : 6,
            num_display_entries : 6,
            current_page : pageIndex,
            num_edge_entries : 1,
            link_to: "menu/getMenus?pageIndex=__id__"  //分页的js中会自动把"__id__"替换为当前的数。0
        });

        var html = "";
        var data = ${menus};
        $.each(data,function(idx,obj){
            console.log(obj.menuIcon);
            var menuId = obj.menuId;
            var menuName = obj.menuName;
            var menuUrl = obj.menuUrl;
            var menuType;
            if(obj.menuType==1){
                menuType = "系统菜单";
            }else if(obj.menuType ==2){
                menuType = "业务菜单";
            }
            var menuOrder = obj.menuOrder;
            var menuStatus;
            if(obj.menuStatus ==0){
                menuStatus ="隐藏";
            }else if(obj.menuStatus == 1){
                menuStatus = "显示";
            }
            html += "<tr><td>"+menuId+"</td>"+
                "<td>"+menuName+"</td>"+
                "<td>"+menuUrl+"</td>"+
                "<td>"+menuType+"</td>"+
                "<td>"+menuOrder+"</td>"+
                "<td>"+menuStatus+"</td>"+
                "<td><a href='javascript:openEditDialog("+menuId+");' class='bounceIn'>编辑</a>"+
                "</tr>";

        });
        $("#content").append(html);

        //getSrceenWH();

        //显示弹框
        $('.bounceIn').click(function(){
            className = $(this).attr('class');
            $('#dialogBg').fadeIn(300);
            $('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
            alert('测试');
        });

    });

    //这个事件是在翻页时候用的
    function pageselectCallback(index, jq) {

    }

    function closdlg()
    {
        Dialog.close();
    }

    function openEditDialog(menuId){
        var diag = new Dialog();
        diag.Title = "编辑菜单信息";
        diag.Width = 400;
        diag.Height = 300;
        diag.URL = "goEditM.do?menuId="+menuId;
        diag.show();
    }

    function toAddDialog(){
        var diag = new Dialog();
        diag.Title = "新增菜单信息";
        diag.Width = 400;
        diag.Height = 300;
        diag.URL = "goAddM.do";
        diag.show();
    }

</script>
</body>
</html>