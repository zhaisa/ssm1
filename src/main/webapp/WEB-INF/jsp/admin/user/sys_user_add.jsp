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
<div id="forlogin">
    <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
        <tr>
            <td width="150" align="right"><font color="red">*</font> 用户名:</td>
            <td><input type="text" id="username" /></td>
        </tr>
        <tr>
            <td width="150" align="right"><font color="red">*</font> 密码:</td>
            <td><input type="text" id="password" /></td>
        </tr>
        <tr>
            <td width="150" align="right"><font color="red">*</font> 确认密码:</td>
            <td><input type="text" id="rpassword" /></td>
        </tr>
        <tr>
            <td width="150" align="right"><font color="red">*</font> 手机号码:</td>
            <td><input type="text" id="phone" /></td>
        </tr>
        <tr>
            <td width="150" align="right">性别:</td>
            <td><input type="text" id="sex" /></td>
        </tr>
        <tr>
            <td width="150" align="right"><font color="red">*</font> 邮箱:</td>
            <td><input type="text" id="email" /></td>
        </tr>
        <tr>
            <td width="150" align="right">备注:</td>
            <td><input type="text" id="mark" /></td>
        </tr>
        <tr>
            <td colspan="2" align="left" style="padding-left:160px;">
                <input type="button" onClick="doSave()" value="保存" class="buttonStyle" />
                <input onClick="dialogClose();" class="buttonStyle" type="button" value="关闭" />
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.8.3.js"></script>
<!-- 引入JQuery提示库 start-->
<script type="text/javascript" src="${basePath}static/js/jquery.tips.js"></script>
<!-- 引入JQuery提示库 end-->
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zDrag.js"></script>
<script type="text/javascript" src="<%=basePath%>plugins/zDialog/zProgress.js"></script>
<script type="text/javascript">

    function dialogClose()
    {
        parentDialog.close();
    }

    function doCheck(){
        var username = $("#username").val();
        var password = $("#password").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var rpassword = $("#rpassword").val();
        if(username == ""){
            $("#username").tips({
                side : 1,
                msg : "请填写用户名!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }
        if(password == ""){
            $("#password").tips({
                side : 1,
                msg : "密码不可以为空!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }
        if(password != rpassword){
            $("#password").tips({
                side : 1,
                msg : "两次密码不一致!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }
        if(password ==""){
            $("#password").tips({
                side : 1,
                msg : "请填写密码!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }
        if(phone == ""){
            $("#phone").tips({
                side : 1,
                msg : "请填写手机号!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }
        if(email == ""){
            $("#email").tips({
                side : 1,
                msg : "请填写邮箱!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }

        var phoneRe = /^1\d{10}$/;
        if(!phoneRe.test(phone)){
            $("#phone").tips({
                side : 1,
                msg : "手机号码格式不正确!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }

        var emailRe = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        if(!emailRe.test(email)){
            $("#email").tips({
                side : 1,
                msg : "邮箱格式不正确!",
                bg : '#FF5080',
                time : 15
            });
            return false;
        }

        return true;
    }

    function doSave() {
        var username = $("#username").val();
        var password = $("#password").val();
        var phone = $("#phone").val();
        var sex = $("#sex").val()!=""?$("#sex").val():"保密";
        var email = $("#email").val();
        var mark = $("#mark").val()!=""?$("#mark").val():"没有备注";
        var params = username+","+password +","+phone +","+sex+","+email
            +","+mark;
        if(doCheck()){
            $.ajax({
                type: "POST",
                url: 'user/addU',
                data: {params:params,tm:new Date().getTime()},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" == data.result){
                        alert('新增成功!');
                        parent.location.reload();
                        dialogClose();
                    }else{
                        $("#username").tips({
                            side : 1,
                            msg : "新增失败!",
                            bg : '#FF5080',
                            time : 15
                        });
                    }
                }
            });
        }
    }
</script>
</body>
</html>