<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>试卷生成系统</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<FRAMESET frameSpacing=0 rows=80,* frameBorder=0><FRAME name=top src="<%=path %>/admin/top.jsp" frameBorder=0 noResize scrolling=no>
<FRAMESET frameSpacing=0 frameBorder=0 cols=220,*><FRAME name=menu src="<%=path %>/admin/left.jsp" frameBorder=0 noResize>
<FRAME name=dmMain src="" frameBorder=0></FRAMESET>
<NOFRAMES>
        <p>
            This page requires frames, but your browser does not support them.</p>
    </NOFRAMES></FRAMESET></HTML>