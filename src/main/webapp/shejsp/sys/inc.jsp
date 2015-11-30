<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="icon" href="cssstyle/ico/myico.ico" type="image/x-icon" />
<link rel="shortcut icon" href="cssstyle/ico/myico.ico" type="image/x-icon" />

<!--  引入jquery-->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/jquery.min.js"></script>

<!--  引入easyui-->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/themes/icon.css" type="text/css"></link>

<!--  引入bootstrap-->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/jslib/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"> 

<!-- 通用js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jqueryUtil.js"></script>

<!-- 通用样式-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cssstyle/common.css" type="text/css"></link>

<!-- 扩展EasyUI Icon(sshe项目扩展过来) -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cssstyle/extEasyUIIcon.css" type="text/css"></link>
<!-- 扩展Icon（erp2项目扩展过来，主要用这个）-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cssstyle/icon.css" type="text/css"></link>

<style type="text/css">
.panel-header, .panel-body {
border-width: 0px;
}
.datagrid,.combo-p{
border:solid 1px #D4D4D4;
}
.datagrid *{
-webkit-box-sizing: content-box;
-moz-box-sizing: content-box;
box-sizing: content-box;
}
</style>