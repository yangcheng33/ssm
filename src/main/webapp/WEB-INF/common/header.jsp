<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div align="center" valign="bottom" id="header">
    <table width="100%" style='position: relative'>
        <tr>
            <td colspan="3" height="43px"></td>
        </tr>
        <tr>
            <td height="18" width="50%" align="left" nowrap>
                <%--<marquee  scrollAmount="4" direction="left" width="100%" height="100%" onmouseout="this.start()" onmouseover="this.stop()">--%>
                <span style="color: #ff7162">
                    ${USER.name}
                </span>
            </td>
            <td align="right" nowrap>
                <span style="color: #ff7162" id="timeColumn"> </span>
            </td>

            <td align="right" width="20%" nowrap>
                <%--&nbsp;&nbsp;--%>
                <%--<button id="chgPwdBtn" style="cursor:hand;height:17px;width:62px;font-size:12px;"--%>
                <%--onclick="modifyPwd()">--%>
                <%--修改密码--%>
                <%--</button>--%>
                &nbsp;&nbsp;
                <button id="logoutBtn" onclick="logout()">
                    退 出
                </button>
                &nbsp;&nbsp;
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    function modifyPwd() {
        // window.parent.bodyFrame.location = "sysmgr/userMgr.do?method=toModifyPwd";
    }

    function logout() {
        if (confirm("您确定要退出系统吗?")) {
            window.parent.location = "${base}/portal/logout";
        }
    }

    setInterval("document.getElementById('timeColumn').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);

</script>