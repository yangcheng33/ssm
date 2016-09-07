<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style type="text/css">
        #leftmenu, #split {
            float: left;
        }

        #leftmenu {
            overflow: scroll;
            width: 180px;
        }

        #split {
            width: 7px;
            border: 1px #ccc solid;
            text-align: center;
        }

        #split img {
            vertical-align: middle;
            cursor: pointer;
        }

        #content {
            float: right;
        }
    </style>

    <script type="text/javascript">
        (function($){
            $.fn.bgiframe = ($.browser.msie && /msie 6\.0/i.test(navigator.userAgent) ? function(s) {
                s = $.extend({
                    top     : 'auto', // auto == .currentStyle.borderTopWidth
                    left    : 'auto', // auto == .currentStyle.borderLeftWidth
                    width   : 'auto', // auto == offsetWidth
                    height  : 'auto', // auto == offsetHeight
                    opacity : true,
                    src     : 'javascript:false;'
                }, s);
                var html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="'+s.src+'"'+
                        'style="display:block;position:absolute;z-index:-1;'+
                        (s.opacity !== false?'filter:Alpha(Opacity=\'0\');':'')+
                        'top:'+(s.top=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')':prop(s.top))+';'+
                        'left:'+(s.left=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')':prop(s.left))+';'+
                        'width:'+(s.width=='auto'?'expression(this.parentNode.offsetWidth+\'px\')':prop(s.width))+';'+
                        'height:'+(s.height=='auto'?'expression(this.parentNode.offsetHeight+\'px\')':prop(s.height))+';'+
                        '"/>';
                return this.each(function() {
                    if ( $(this).children('iframe.bgiframe').length === 0 )
                        this.insertBefore( document.createElement(html), this.firstChild );
                });
            } : function() { return this; });

            // old alias
            $.fn.bgIframe = $.fn.bgiframe;

            function prop(n) {
                return n && n.constructor === Number ? n + 'px' : n;
            }

        })(jQuery);

        $(function(){
            var header = $('#header'), leftmenu = $('#leftmenu'), split = $('#split'), content = $('#content');
            resize();
            $(window).resize(function(){
                resize();
            });

            split.click(function(){
                if(leftmenu.width() == 180){
                    leftmenu.width(0);
                    content.width(content.width() + 180);
                }else{
                    leftmenu.width(180);
                    content.width(content.width() - 180);
                }
            });

            $('#toolbar').bgiframe();

            //hide toolbar and make visible the 'show' button
            $("span.hide-btn a").click(function() {
                $("#toolbar").hide();
            });

            $("#help").click(function() {
                $("#toolbar").toggle();
                //searchHelp();
            });
        });

        function resize(){
            var header = $('#header'), leftmenu = $('#leftmenu'), split = $('#split'), content = $('#content');

            leftmenu.height($(window).height() - header.height());
            split.height($(window).height() - header.height());
            split.css({'line-height' : $(window).height() - header.height() + 'px'});
            content.width($(window).width() - (leftmenu.width() + split.width() + 4));
            content.height($(window).height() - header.height());
        }
    </script>

</head>

<body>

<jsp:include page="header.jsp" />

<div>
    <iframe id="leftmenu" name="leftFrame" src="${base}/portal/listMenus" width="12%" height="88%"></iframe>

    <div id="split" >
        <img src="${base}/common/images/arrowsa1.gif" width="7px" height="80px" />
    </div>

    <iframe id="content" name="bodyFrame" src="${base}/WEB-INF/common/body.jsp" width="85%" height="88%"></iframe>
</div>

</body>
</html>
