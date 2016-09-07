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

            <li>角色管理</li>
            <li class="active">绑定产品</li>
        </ul>
        <!-- /.breadcrumb -->
    </div>

    <div class="page-content">
        <c:if test='${error != null}'>
            <div id="errorDiv" style="visibility:visible; color:#ff5a29; font-size: 18px; font-weight: bolder; height: 20px; text-align: center; position:relative; left:-16%;">
                    ${error}
            </div>
        </c:if>
        <c:if test='${success != null}'>
            <div  id="successDiv" style="visibility:visible; color:#0eb839;font-size: 18px;  font-weight: bolder; height: 20px; text-align: center; position:relative; left:-16%;">
                    ${success}
            </div>
        </c:if>
        <br/>

        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="col-md-offset-3 col-md-9">
                <span style="font-size: larger; text-align: left">为角色"${role.name}" 绑定产品：</span><br/>

                <sf:form id="roleForm" action="" method="post" modelAttribute="form"
                         class="form-horizontal">

                    <sf:input path="id" cssStyle="visibility: hidden"/>
                    <sf:input path="criaName" cssStyle="visibility: hidden"/>

                    <table id="simple-table" class="table table-striped table-bordered table-hover center"
                           style="width:500px;">
                        <thead>
                        <tr>
                            <th class="center"></th>
                            <th class="center">产品编码</th>
                            <th class="center">产品名称</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${allProdMap}">
                            <tr>
                                <td>
                                    <sf:checkbox path="prodCodeArray" value="${item.key}"/>
                                </td>
                                <td class="center">
                                    ${item.key}
                                </td>
                                <td>${item.value}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table><br/>

                    <div style="width: 100%; text-align: left">
                        <label style="text-align: left; font-size: 16px">
                            可查看其它用户创建的业务数据
                        </label>
                        <sf:checkbox path="canViewAll" value="1"/>
                    </div>

                </sf:form>
            </div>

            <div class="col-md-offset-4 col-md-9">
                <br/>
                <button id="commitBtn"  type="button">
                    提 交
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button id="backBtn"  type="button">
                    返 回
                </button>
            </div>

        </div>
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


    $("#commitBtn").click(function () {
        var path = "${base}/auth/role/bindProds";
        $('#roleForm').attr("action", path).submit();
    });

    $("#backBtn").click(function () {;
        var path = "${base}/auth/role/query";
        $('#roleForm').attr("action", path).submit();
    });



</script>

</body>
</html>
