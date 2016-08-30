<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type="text/javascript" src="${base}/common/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/common/js/library.js"></script>
</head>


<body style="background-color:#FFFFFF;">

<!-- 如果没有这行代码，modelAttribute="form"将无法识别；也可在进入本页面前，通过controller中设置modelAttribute.-->
<jsp:useBean id="loginForm" class="com.github.walker.basewf.portal.control.LoginForm" scope="request"></jsp:useBean>

<sf:form action="${base}/portal/login" method="post" modelAttribute="loginForm">

    <div align="center">

        <table width="70%" height="430" border="0" cellpadding="0" cellspacing="0">
            <tr valign="middle">
                <td height="420">
                    <img src="${base}/common/images/welcome.gif"/>
                </td>
            </tr>
        </table>

        <br>

        <table cellSpacing="0" cellPadding="0" width="350" border="1">
            <tr>
                <td width="25%" align="center">
                    用户名&nbsp;
                </td>
                <td>
                    <sf:input path="acctNo" size="15" maxlength="15"/>
                </td>
            </tr>

            <tr>
                <td align="center">
                    密&nbsp;码&nbsp;
                </td>
                <td>
                    <sf:password path="password" size="15" maxlength="15"/>
                </td>
            </tr>
        </table>

        <br>
        <button id="loginBtn" type="button">登 录</button>

    </div>

</sf:form>


<script type="text/javascript">
    $("#loginBtn").click(function () {
        $("#loginForm").submit();
    });
</script>


</body>

</html>
