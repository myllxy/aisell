<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--easyui的样式--%>
<c:set var="baseurl" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${baseurl}/easyui/themes/default/easyui.css">
<%--easyui的图标 --%>
<link rel="stylesheet" type="text/css" href="${baseurl}/easyui/themes/icon.css">
<%--jQuery的支持--%>
<script type="text/javascript" src="${baseurl}/easyui/jquery.min.js"></script>
<%--jQuery的扩展包 --%>
<script type="text/javascript" src="${baseurl}/easyui/plugin/jquery.jdirk.js"></script>
<%--easyui的核心功能--%>
<script type="text/javascript" src="${baseurl}/easyui/jquery.easyui.min.js"></script>
<%--国际化--%>
<script type="text/javascript" src="${baseurl}/easyui/locale/easyui-lang-zh_CN.js"></script>