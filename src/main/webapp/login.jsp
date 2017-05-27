<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>试卷生成系统</title>
    <style type="text/css">
        body {
            margin-left: 0px;
            background-color: #cccccc;
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

        .STYLE15 {
            color: #fdeded;
            font-size: 9pt;
        }
    </style>

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
<table width="559" height="403" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=path%>/images/login1.jpg">
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
                            <span class="STYLE15">username：</span>
                        </td>
                        <td width="356" valign="bottom">
                            <input name="username" id="username" type="text" size="21" class="input2"
                                   onMouseOver="this.style.background='#F0DAF3';"
                                   onMouseOut="this.style.background='#FFFFFF'">
                        </td>
                    </tr>
                    <tr>
                        <td height="10" colspan="2" valign="bottom"></td>
                    </tr>
                    <tr>
                        <td height="31" colspan="2" valign="top" class="STYLE15">
                            password：
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
                            <input name="button" type="button" class="submit1" value="Login" onClick="check1()"> &nbsp;
                            <input name="Submit2" type="reset" class="submit1" value="Reset">
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
</body>
