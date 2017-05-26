<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>试卷生成系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
    <style type="text/css">
        body {
            margin-left: 0px;
            background-image: url();
        }

        .style2 {
            color: #990000
        }

        .input2 {
            font-size: 12px;
            border: 3px double #A8D0EE;
            color: #344898;
        }

        .submit1 {
            border: 3px double #416C9C;
            height: 22px;
            width: 45px;
            background-color: #F2F2F2;
            font-size: 12px;
            padding-top: 1px;
            background-image: url(bt.gif);
            cursor: hand;
        }

        .STYLE12 {
            font-family: Georgia, "Times New Roman", Times, serif
        }

        .STYLE13 {
            color: #316BD6;
        }

        .STYLE15 {
            color: #fdsere;
            font-size: 9pt;
        }
    </style>


    <script type='text/javascript' src='<%=path %>/dwr/interface/loginService.js'></script>
    <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
    <script type='text/javascript' src='<%=path %>/dwr/util.js'></script>

    <script language="javascript">
        function check1() {
            if (document.ThisForm.username.value == "") {
                alert("请输入用户名");
                document.ThisForm.username.focus();
                return false;
            }
            document.ThisForm.submit();
        }

    </script>
</head>
<body>
<body>
<br>
<br>
<br>
<br>
<table width="559" height="403" border="0" align="center" cellpadding="0" cellspacing="0"
       background="<%=path %>/images/login1.jpg">
    <tr>
        <td>
            <div align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 40pt;"></div>
        </td>
    </tr>
    <tr>
        <td width="559">
            <form name="ThisForm" method="POST" action="<%=path %>/adminLogin.action">
                <table width="410" height="198" border="0" align="right" cellpadding="0" cellspacing="0">
                    <tr>
                        <td height="10" colspan="2"></td>
                    </tr>
                    <tr>
                        <td width="54" height="22" valign="bottom">
                            <span class="STYLE15">用户名：</span>
                        </td>
                        <td width="356" valign="bottom">
                            <input name="username" id="username" type="text" class="input2"
                                   onMouseOver="this.style.background='#F0DAF3';"
                                   onMouseOut="this.style.background='#FFFFFF'">
                        </td>
                    </tr>
                    <tr>
                        <td height="10" colspan="2" valign="bottom"></td>
                    </tr>
                    <tr>
                        <td height="31" colspan="2" valign="top" class="STYLE15">
                            密&nbsp;&nbsp;&nbsp;码：
                            <input name="password" id="password" type="password" size="21" class="input2" align="bottom"
                                   onMouseOver="this.style.background='#F0DAF3';"
                                   onMouseOut="this.style.background='#FFFFFF'">
                        </td>
                    </tr>
                    <tr>
                        <td height="10" colspan="2" valign="bottom"></td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="top">&nbsp; &nbsp; &nbsp; &nbsp;
                            <input name="button" type="button" class="submit1" value="登录" onClick="check1()"> &nbsp;
                            <input name="Submit2" type="reset" class="submit1" value="重置">
                            <img id="indicator" src="<%=path %>/images/loading.gif" style="display:none"/>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
</body>
