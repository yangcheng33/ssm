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
            <li class="active">绑定菜单及功能项</li>
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
            <div class="col-md-offset-0">
                <span style="font-size: larger; text-align: left">为角色"${role.name}" 绑定菜单和功能项：</span><br/>

                <sf:form id="roleForm" action="" method="post" modelAttribute="form"
                         class="form-horizontal">

                    <sf:input path="id" cssStyle="visibility: hidden"/>
                    <sf:input path="criaName" cssStyle="visibility: hidden"/>

                    <table id="simple-table" class="table table-striped table-bordered table-hover" style="width: 98%">
                        <thead>
                        <tr>
                            <th class="center"></th>
                            <th class="center">菜单编码</th>
                            <th class="center">菜单名称</th>
                            <th class="center">功能项</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${allMenuList}">
                            <tr>
                                <td style="text-align: center">
                                    <sf:checkbox path="menuIdArray" value="${item.id}"/>
                                </td>
                                <td>${item.code}</td>
                                <td>${item.name}</td>
                                <td>
                                    <c:if test="${item.funcIdNameMap!=null}">
                                        <c:forEach var="map" items="${item.funcIdNameMap}">
                                           <sf:checkbox path="funcIdArray" value="${map.key}"/>${map.value}&nbsp;
                                        </c:forEach>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </sf:form>
            </div>

            <div class="col-md-offset-4 col-md-9">
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
    <%----%>
    <%--function setMenu(){--%>
    <%--document.roleMenuFrame.document.forms[0].submit();--%>
    <%--}--%>
    <%----%>
    <%--function returnList(){     --%>

    <%--var criaName = document.forms[0].criaName.value;--%>
    <%--window.location="roleMgr.do?method=queryRole&criaName="+criaName--%>
    <%--+"&CURR_PAGENUM="+<bean:write name="CURR_PAGENUM"/>;--%>
    <%--}--%>
    <%----%>
    <%----%>
    <%--//全部勾选或全部取消--%>
    <%--function selectAll(){ --%>
    <%--var allChecked = document.getElementsByName("menuIdArray")[0].checked;--%>

    <%--var obj1 = document.roleMenuFrame.document.getElementsByName("menuIdArray");--%>
    <%--var obj2 = document.roleMenuFrame.document.getElementsByName("creatable");--%>
    <%--var obj3 = document.roleMenuFrame.document.getElementsByName("updatable");--%>
    <%--var obj4 = document.roleMenuFrame.document.getElementsByName("deletable");--%>
    <%----%>
    <%--for(var i = 0;i<obj1.length;i++){--%>
    <%--obj1[i].checked = allChecked;--%>
    <%--obj2[i].checked = allChecked;--%>
    <%--obj3[i].checked = allChecked;--%>
    <%--obj4[i].checked = allChecked;--%>
    <%--}--%>
    <%--}--%>

    $("#commitBtn").click(function () {
        var path = "${base}/auth/role/bindMenus";
        $('#roleForm').attr("action", path).submit();
    });

    $("#backBtn").click(function () {;
        var path = "${base}/auth/role/query";
        $('#roleForm').attr("action", path).submit();
    });
</script>

</body>
</html>
