<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.List" %>
<%@ page import="com.github.walker.basewf.auth.vo.Menu" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style type="text/css">
    .menu_link6    {  font-family:"Microsoft YaHei", "宋体", "Arial", "Haettenschweiler"; font-size:14px; font-weight:bold; color:#006666; background-image:url(../common/images/menu/leftmenu_bg.gif); letter-spacing:1pt; text-align:left; text-indent:-1pt}
    .submenu_link6 {  font-family:"Microsoft YaHei", "宋体", "Arial", "Haettenschweiler"; font-size:14px; color:#006666; background-image:url(../common/images/menu/submenu_link_bg.gif); letter-spacing:0pt; text-align:left; text-indent:28pt}
    </style>
</head>

<body bgcolor="#ffffff" leftmargin="0" topmargin="0">


<sf:form action="${base}/portal/listMenus" method="post" modelAttribute="loginForm">
    <table width="151" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <tbody>
                    这里放一张图片
                    </tbody>
                </table>
            </td>
        </tr>
    </table>

    <br>

    <table width="151" border="0" cellpadding="0" cellspacing="1">

        <%
            //父菜单控制
            List menus = (List) request.getAttribute("menuList");

            for (int i = 0; i < menus.size(); i++) {
                Menu menu = (Menu) menus.get(i);
                String menuId = menu.getId().toString();
                String url = menu.getUrl();
                if (url == null || "".equals(menuId.trim())) {
                    url = "#";
                } else if (url.startsWith("/")) {
                    url = request.getContextPath() + url;
                }

                //子菜单
                List subMenus = menu.getSubMenus();
        %>


        <%
            if (subMenus.size() > 0) {
        %>
        <tr style="cursor:hand;font-size:14px;height:28px"
            onclick="expand(img<%= menuId %>, submenu<%= menuId %>,'<%= url %>')">
        <%
		    }else{
        %>

        <tr style="font-size:14px;height:28px">
            <%
                }
            %>

            <td class="menu_link6">
                <img id="img<%= menuId %>" src="${base}/common/images/tree/plus.gif" width="20" height="20" align="absmiddle">
                <%= menu.getName() %>
            </td>
        </tr>

        <%
            //子菜单控制
            if (subMenus.size() > 0) {
        %>
        <tr id="submenu<%= menuId %>" style="display:none;">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <%
                        for (int j = 0; j < subMenus.size(); j++) {
                            Menu subMenu = (Menu) subMenus.get(j);
                            String subUrl = subMenu.getUrl().toString();
                            if (subUrl == null || subUrl.equals("")) {
                                subUrl = "#";
                            } else if (subUrl.startsWith("/")) {
                                subUrl = request.getContextPath() + subUrl;
                            }
                    %>
                    <tr height="28">
                        <td class="submenu_link6">
                            <a href="<%= subUrl %>" target="bodyFrame" style="text-decoration:none">
                                <%= subMenu.getName() %>
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>

</sf:form>
</body>
</html>

<script language="javascript">
    <!--
    var preImg = null;
    var preMenu = null;

    function exp(image, submenu) {
        if (submenu.style.display == 'none') {
            submenu.style.display = '';
            image.src = '../common/images/tree/minus.gif';
        } else {
            submenu.style.display = 'none';
            image.src = '../common/images/tree/plus.gif';
        }
    }

    function expHalf(image, submenu) {
        if (submenu.style.display == '') {
            submenu.style.display = 'none';
            image.src = '../common/images/tree/plus.gif';
        }
    }

    function expand(image, submenu, url) {
        exp(image, submenu);
        if (preImg != null && preImg != image) {
            expHalf(preImg, preMenu);
        }
        preImg = image;
        preMenu = submenu;
        if (url != "" && url != "#") parent.frames["bodyFrame"].location.href = url;
    }

    //-->
</script>
