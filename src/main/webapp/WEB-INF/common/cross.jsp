<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            background-color: #FFFFFF;
        }

        -->
    </style>
    <script language="JavaScript" type="text/JavaScript">
        function thecc(abc) {
            if (parent.document.all.counn.cols != '0,*') {
                parent.document.all.counn.cols = '0,*';
                document.all.demoo.src = "${base}/common/images/arrowsb1.gif";
            }
            else {
                parent.document.all.counn.cols = '160,*';
                document.all.demoo.src = "${base}/common/images/arrowsa1.gif";
            }
        }
    </script>
</head>

<body style="background-color:#CCCCEE">

<table height="100%" border="0" align="center" cellpadding="0" cellspacing="0"
       onclick="thecc(this)" style="cursor:hand; ">
    <tr>
        <td align="center" height=80">
            <table width="8" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <img src="${base}/common/images/arrowsa1.gif" width="7" height="80" id="demoo"
                             onmouseover="if(parent.document.all.counn.cols!='0,*'){
                                     this.src='${base}/common/images/arrowsa2.gif';
                                     }else{
                                     this.src='${base}/common/images/arrowsb2.gif';
                                     }"
                             onmouseout="if(parent.document.all.counn.cols!='0,*'){
                                     this.src='${base}/common/images/arrowsa1.gif';
                                     }else{
                                     this.src='${base}/common/images/arrowsb1.gif';
                                     }"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

</table>

</body>
</html>

