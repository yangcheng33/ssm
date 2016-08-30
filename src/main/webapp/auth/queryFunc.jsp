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


<body>

<div>
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="#">Home</a>
            </li>

            <li>功能项管理</li>
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

        <sf:form id="queryForm" action="${base}/auth/func/query" modelAttribute="form" >
            <sf:input path="menuId" cssStyle="visibility: hidden"/>
            <sf:input path="CURR_PAGENUM" cssStyle="visibility: hidden"/>

            <sf:input path="id" cssStyle="visibility: hidden"/>
        </sf:form>

        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <table id="simple-table" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <!--
                    <th class="center">
                        <label class="pos-rel">
                            <input type="checkbox" class="ace"/>
                            <span class="lbl"></span>
                        </label>
                    </th>
                    -->
                    <th class="center">功能项名称</th>
                    <th class="center">功能项编号</th>
                    <th class="center" >
                        <button id="toAddBtn" type="button">
                            新 增
                        </button>
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="item" items="${rsList}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.code}</td>
                        <td align="center" nowrap>
                            <a href="#" onclick="toUpdate('${item.id}')">修改</a>
                            &nbsp
                            <a href="#" onclick="del('${item.id}')">删除</a>
                            &nbsp
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 分页控件栏 -->
            <div style="text-align: center">
                <jsp:include page="/common/pagerPanel.jsp" flush="true"/>
            </div>

        </div>
    </div>

</div>
<!-- /.main-content -->

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
        $("#queryForm").submit();
    });

    $("#toAddBtn").click(function () {
        var path = "${base}/auth/func/toAdd";
        $('#queryForm').attr("action", path).submit();
    });

    function toUpdate(id) {
        var path = "${base}/auth/func/toUpdate";
        $("#id").attr("value", id);
        $('#queryForm').attr("action", path).submit();
    }

    function del(id) {
        if(confirm("您确定要删除此功能项吗？")){
            var path = "${base}/auth/func/del";
            $("#id").attr("value", id);
            $('#queryForm').attr("action", path).submit();
        }
    }

</script>

</body>
</html>