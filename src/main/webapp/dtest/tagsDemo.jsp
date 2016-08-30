<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>BaseWF</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title></title>

    <link rel="stylesheet" href="${base}/common/css/bootstrap.css"/>
    <link rel="stylesheet" href="${base}/common/css/ace.css"/>
</head>


<body>

<sf:form modelAttribute="form" action="${base}/tagsDemo/submit">

    <div align="center">
        input 标签：<sf:input path="input"/><br/><br/>
        password 标签：<sf:password path="password"/><br/><br/>
        textarea 标签：
        <sf:textarea path="textArea"/><br/><br/>

        绑定boolean的checkbox 标签：
        <sf:checkbox path="checkBoxBoolean"/><br/><br/>

        绑定Array的checkbox 标签：<br/>
        <sf:checkbox path="checkBoxArray1" value="item1"/>arrayItem 路人甲
        <sf:checkbox path="checkBoxArray1" value="item2"/>arrayItem 路人乙
        <sf:checkbox path="checkBoxArray1" value="item3"/>arrayItem 路人丙
        <sf:checkbox path="checkBoxArray1" value="item4"/>arrayItem 路人丁<br/><br/>

        绑定Array的checkboxs 标签：<br/>
        <sf:checkboxes path="checkBoxArray2" items="${preparedArray}"/><br/><br/>


        绑定Map的checkboxs 标签：<br/>
        <sf:checkboxes path="checkBoxList" items="${preparedMap}"/><br/><br/>


        绑定Integer的radiobutton 标签：<br/>
        <sf:radiobutton path="radiobuttonId" value="0"/>0
        <sf:radiobutton path="radiobuttonId" value="1"/>1
        <sf:radiobutton path="radiobuttonId" value="2"/>2<br/><br/>


        不绑定items数据直接在form:option添加的select 标签：
        <sf:select path="selectId1">
            <option>请选择人员</option>
            <sf:option value="1">甲</sf:option>
            <sf:option value="2">乙</sf:option>
            <sf:option value="3">丙</sf:option>
        </sf:select><br/><br/>


        绑定Map的select 标签：<br/>
        <sf:select path="selectId2" items="${preparedMap}"/><br/><br/>


        用form:option绑定items的select 标签：<br/>
        <sf:select path="selectId3">
            <sf:options items="${preparedMap}"/><br/><br/>
        </sf:select><br/><br/>

            <%--<sf:select path="selectId4">--%>
            <%--<sf:option value="" label="--Please Select"/>--%>
            <%--<sf:options items="${preparedList}" itemValue="bookId" itemLabel="title"/>--%>
            <%--</sf:select><br/><br/><br/>--%>

        <input type="submit" value="Submit"/>

    </div>

</sf:form>

<script src="${base}/common/js/jquery.js"></script>
<script src="${base}/common/js/library.js"></script>

</body>
</html>