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


<script type="text/javascript">
    $("#loginBtn").click(function () {
        $("#loginForm").submit();
    });
</script>


</body>

</html>
