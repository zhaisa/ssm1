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
    <style>
        body { background: #ffffff; color: #444;font-size:12px; }
        a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
        body, div, q, iframe, form, h5 { margin: 0; padding: 0; }
        img, fieldset { border: none 0; }
        body, td, textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; }
        body, input, textarea, select, button { margin: 0; font-size: 12px; font-family: Tahoma, SimSun, sans-serif; }
        div, p, table, th, td { font-size:1em; font-family:inherit; line-height:inherit; }
        h5 { font-size:12px; }
        ol li,ul li{ margin-bottom:0.5em;}
        pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
        pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
        #content { padding-left:50px; padding-right:50px; }
        #content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
        #content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
        #content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
        #content div.item { margin-top:10px; margin-bottom:10px; border:#eee solid 4px; padding:10px; }
        hr { clear:both; margin:7px 0; +margin: 0;
            border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
        .infobar { background:#fff9e3; border:1px solid #fadc80; color:#743e04; }
        .buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(${basePath}plugins/zDialog/images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
        .buttonStyle:hover{background:url(${basePath}plugins/zDialog/images/buticon.gif) no-repeat left -23px;}

    </style>

</head>
<body >
<div class="content_wrap">
    <c:choose>
        <c:when test="${not empty roles}">
            <c:forEach items="${roles}" var="role" varStatus="vs">
                <c:choose>
                    <c:when test='${role.checked}'>
                        <input type="checkbox" name="checkbox" checked="checked"  id="${role.roleId}" value="${role.roleId}" />
                        ${role.roleName}
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="checkbox" id="${role.roleId}" value="${role.roleId}" />
                        ${role.roleName}
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:when>
        <c:otherwise>
            数据加载中...
        </c:otherwise>
    </c:choose>
</div>
&nbsp;&nbsp;
<input type="button" onClick="doSave()" value="保存" class="buttonStyle" />
<input onClick="dialogClose();" class="buttonStyle" type="button" value="关闭" />
<!-- 引入JQuery库 start -->
<script type="text/javascript" src="${basePath}static/js/jquery-1.8.3.js"></script>
<!-- 引入JQuery库 end -->
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zDrag.js"></script>
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zProgress.js"></script>
<script type="text/javascript">

    function dialogClose()
    {
        parentDialog.close();
    }

    function doSave() {
        /*var  nodes = $("input[name='checkbox'][checked]");
         Jquery1.3之后的版本已经不支持如上用法，应该改成 $("input[name='checkbox']:checked")
         不然会出现，你即使重新选择了一组checkbox，还是获取原来的已选checkbox的Value，不会获取新的已选checkbox的value
         */
        var tmpNode;
        var ids = "";
        var  nodes = $("input[name='checkbox']:checked");
        for(var i=0; i<nodes.length; i++){
            tmpNode = nodes[i];
            if(i!=nodes.length-1){
                ids += tmpNode.id+",";
            }else{
                ids += tmpNode.id;
            }
        }
        var userId = ${userId};
        var params = userId +";"+ids;
        //alert(ids);
        $.ajax({
            type: "POST",
            url: 'user/auth',
            data: {params:params,tm:new Date().getTime()},
            dataType:'json',
            cache: false,
            success: function(data){
                if("success" == data.result){
                    alert('分配角色成功!请重新登录!');
                    parent.location.reload();
                    doDialogClose();
                }else{
                    alert("分配角色失败!");
                }
            }
        });
    }

</script>
</body>
</html>