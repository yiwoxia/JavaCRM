<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="prc" value="${pageContext.request.contextPath }" ></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- easyui样式 -->
	<link rel="stylesheet" href="${prc }/resources/thrlib/jquery-easyui/themes/default/easyui.css" />
	<!-- 图标样式 -->
	<link rel="stylesheet" href="${prc }/resources/thrlib/jquery-easyui/themes/icon.css" />
	<!-- jquery库 -->
	<script type="text/javascript" src="${prc }/resources/thrlib/jquery-easyui/jquery.min.js" ></script>
	<!-- easyuijs -->
	<script type="text/javascript" src="${prc }/resources/thrlib/jquery-easyui/jquery.easyui.min.js" ></script>
	<!-- 国际化 -->
	<script type="text/javascript" src="${prc }/resources/thrlib/jquery-easyui/locale/easyui-lang-zh_CN.js" ></script>