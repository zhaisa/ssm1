<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/12
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath %>">
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Title</title>
    <style>
        body { background: #ffffff; color: #444;font-size:12px; }
    </style>
    <!-- 编辑框-->
    <script type="text/javascript" charset="UTF-8" src="<%=basePath %>plugins/ueditor1_4_3_2/ueditor.config.js"></script>
    <script type="text/javascript" charset="UTF-8" src="<%=basePath %>plugins/ueditor1_4_3_2/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="<%=basePath %>plugins/ueditor1_4_3_2/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var ue = UE.getEditor('editor');
        function getContent() {
            var arr = [];
            arr.push("使用editor.getContent()方法可以获得编辑器的内容");
            arr.push("内容为：");
            arr.push(UE.getEditor('editor').getContent());
            //alert(arr.join("\n"));
        }
    </script>
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

        function setType(value){
            $("#type").val(value);
        }

        function isAll(){
            if($("#allusers").attr("checked") == 'checked'){
                $("#isAll").val('yes');
                $("#email").attr("disabled",true);
            }else{
                $("#isAll").val('no');
                $("#email").attr("disabled",false);
            }
        }

        //发送
        function sendEm(){

            if($("#type").val()=="1"){
                $("#content").val(getContentTxt());
            }else{
                $("#content").val(getContent());
            }
            if($("#email").val()==""){
                $("#email").tips({
                    side:3,
                    msg:'请输入邮箱',
                    bg:'#AE81FF',
                    time:2
                });
                $("#email").focus();
                return false;
            }
            if($("#title").val()==""){
                $("#title").tips({
                    side:3,
                    msg:'请输入标题',
                    bg:'#AE81FF',
                    time:2
                });
                $("#title").focus();
                return false;
            }
            if($("#content").val()==""){

                $("#content").tips({
                    side:1,
                    msg:'请输入内容',
                    bg:'#AE81FF',
                    time:3
                });
                return false;
            }

            var EMAIL = $("#email").val();
            var TYPE  = $("#type").val();
            var TITLE = $("#title").val();
            var CONTENT = $("#content").val();

            $("#emailDiv").hide();
            $("#sendingDiv").show();

            $.ajax({
                type: "POST",
                url: 'user/sendEmail.do?tm='+new Date().getTime(),
                data: {toEmail:EMAIL,title:TITLE,content:CONTENT},
                dataType:'json',
                //beforeSend: validateData,
                cache: false,
                success: function(data){
                    if("ok" == data.msg){
                        $("#msg").tips({
                            side:3,
                            msg:'发送成功！',
                            bg:'#68B500',
                            time:5
                        });
                        setTimeout("showdiv()",1000);
                    }else{
                        $("#msg").tips({
                            side:3,
                            msg:'发送失败!',
                            bg:'#68B500',
                            time:5
                        });
                    }

                }
            });

        }

        function showdiv(){
            $("#emailDiv").show();
            $("#sendingDiv").hide();
        }

        //ueditor纯文本
        function getContentTxt() {
            var arr = [];
            arr.push(UE.getEditor('editor').getContentTxt());
            return arr.join("");
        }
        //ueditor有标签文本
        function getContent() {
            var arr = [];
            arr.push(UE.getEditor('editor').getContent());
            return arr.join("");
        }

        function ueditor(){
            var ue = UE.getEditor('editor');
        }
    </script>
</head>
<body>
<div id="emailDiv">
    <textarea name="content" id="content" style="display:none" ></textarea>
    <input type="hidden" name="type" id="type" value="1"/>
    <input type="hidden" name="isAll" id="isAll" value="no"/>
    <table style="width:98%;" >
        <tr>
            <td style="margin-top:0px;">
                <div style="float: left;" style="width:81%">
                    <textarea name="email" id="email" rows="1" cols="50" style="width:600px;height:20px;" placeholder="请选输入对方邮箱,多个请用(;)分号隔开" title="请选输入对方邮箱,多个请用(;)分号隔开">${toEmails}</textarea>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="title" id="title" value="" placeholder="请选输入邮件标题" style="width:98%"/>
            </td>
        </tr>
        <tr>
            <td id="nr">
                <script id="editor" type="text/plain" style="width:96%;height:259px;"></script>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">
                <a class="" onclick="sendEm();">发送</a>
                <a class="" onclick="top.Dialog.close();">取消</a>
                <label style="float:left;padding-left: 32px;"><input onclick="setType('1');" checked="checked" type="radio" value="icon-edit"><span class="lbl">纯文本</span></label>
                <label style="float:left;padding-left: 5px;"><input onclick="setType('2');" type="radio" value="icon-edit"><span class="lbl">带标签</span></label>
                <label style="float:left;padding-left: 15px;"><input type="checkbox" id="allusers" onclick="isAll();" /><span class="lbl">全体用户</span></label>
            </td>
        </tr>
    </table>
</div>
<div id="sendingDiv" class="center" style="display:none"><br/><img src="<%=basePath%>static/images/jzx.gif"  /><br/><h4 class="lighter block green" id='msg'>正在发送...</h4></div>
</body>
</html>
