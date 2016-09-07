<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title></title>

    <link rel="stylesheet" href="${base}/common/css/bootstrap.css"/>
    <link rel="stylesheet" href="${base}/common/css/ace.css"/>
</head>


<body class="no-skin">

<div>
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="#">Home</a>
            </li>

            <li>菜单管理</li>
            <li class="active">查询</li>
        </ul>
        <!-- /.breadcrumb -->
    </div>

    <div class="page-content">
        <c:if test='${success != null}'>
            <div  id="successDiv" style="visibility:visible; color:#0eb839;font-size: 18px;  font-weight: bolder; height: 20px; text-align: center; position:relative; left:-16%;">
                    ${success}
            </div>
        </c:if>
        <c:if test='${error != null}'>
            <div id="errorDiv" style="visibility:visible; color:#ff5a29; font-size: 18px; font-weight: bolder; height: 20px; text-align: center; position:relative; left:-16%;">
                    ${error}
            </div>
        </c:if>

        <sf:form id="queryForm" action="${base}/auth/menu/query" modelAttribute="form">
        <sf:input path="id" cssStyle="visibility: hidden"/>
        <sf:input path="CURR_PAGENUM" cssStyle="visibility: hidden"/>

        <div class="widget-box">
            <div class="widget-header widget-header-small">
                <h5 class="widget-title lighter">Search Form</h5>
            </div>

            <div class="widget-body">
                <div class="widget-main">
                    <div class="row" style="width: 100%;">
                        <div class="input-group" style="width: 100%;">

                            <label style="width: 12%; text-align: right">父菜单名称&nbsp;</label>

                            <span style="width: 20%; text-align: left">
                                <sf:input class="search-query" path="criaPname" size="15" maxlength="15"/>
                            </span>

                            <label style="width: 12%; text-align: right">菜单名称&nbsp;</label>

                            <span style="width: 20%; text-align: left">
                                <sf:input class="search-query" path="criaName" size="15" maxlength="15"/>
                            </span>

                            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button id="queryBtn" type="button">
                                    查 询
                                </button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </sf:form>

        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <table id="simple-table" class="table table-striped table-bordered table-hover" >
                <thead>
                <tr>
                    <th class="center">父菜单名称</th>
                    <th class="center">菜单名称</th>
                    <th class="center">菜单编码</th>
                    <th class="center">菜单url</th>
                    <th class="center">功能项</th>
                    <th class="center">
                        <button id="toAddBtn" type="button">
                            新增一级菜单
                        </button>
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="item" items="${rsList}">
                    <tr>
                        <td>${item.pname}</td>
                        <td>${item.name}</td>
                        <td>${item.code}</td>
                        <td>${item.url}</td>
                        <td>
                            <c:choose>
                            <c:when test="${item.funcItems!=null && item.funcItems!='' }">
                                <a href="#" onclick="toListFunc('${item.id}')">
                                    ${item.funcItems}
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" onclick="toListFunc('${item.id}')">
                                     <span style="font-style: italic; color: orange;">没有绑定功能项</span>
                                </a>
                            </c:otherwise>
                            </c:choose>
                        </td>
                        <td align="center" nowrap>
                            <a href="#" onclick="toUpdate('${item.id}')">修改</a>
                            &nbsp
                            <a href="#" onclick="del('${item.id}')">删除</a>
                            &nbsp
                            <c:if test='${item.pid == 0}'>
                                <a href="#" onclick="toAdd('${item.id}')">添加子菜单</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 分页控件栏 -->
            <div style="text-align: center">
                <jsp:include page="/WEB-INF/common/pagerPanel.jsp" flush="true"/>
            </div>

        </div>
    </div>

</div>
<!-- /.main-content -->

<div class="footer">
    <div class="footer-inner">
    </div>
</div>


<script src="${base}/common/js/jquery.js"></script>
<script src="${base}/common/js/library.js"></script>

<script type="text/javascript">
    var maxTime = 3;
    for (var i = 1; i <= maxTime; i++) {
        window.setTimeout("display(" + i + ")", i * 1000);
    }

    function display(time) {
        if (time == maxTime) {
            $('#successDiv').attr("style", "visibility: hidden");
        }
    }

    $("#queryBtn").click(function () {
        $('#CURR_PAGENUM').val("1");//重置页码
        //$("#queryForm").attr("method", "get");
        $("#queryForm").submit();
    });

    $("#toAddBtn").click(function () {
        var path = "${base}/auth/menu/toAdd";
        $("#id").attr("value", "0");
        $('#queryForm').attr("action", path).submit();
    });

    function toAdd(id) {
        var path = "${base}/auth/menu/toAdd";
        $("#id").attr("value", id);
        $('#queryForm').attr("action", path).submit();
    }


    function toUpdate(id) {
        var path = "${base}/auth/menu/toUpdate";
        $("#id").attr("value", id);
        $('#queryForm').attr("action", path).submit();
    }

    function del(id) {
        if (confirm("如您确定要删除此菜单吗? ")) {
            var path = "${base}/auth/menu/del";
            $("#id").attr("value", id);
            $('#queryForm').attr("action", path).submit();
        }
    }

    function toListFunc(id) {
        var path = "${base}/auth/func/query?menuId="+id;
        popWindow(path,800,520);
    }
</script>

</body>
</html>