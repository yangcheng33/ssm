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
	<!-- #section:basics/content.breadcrumbs -->
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="#">Home</a>
			</li>

			<li>用户管理</li>
			<li class="active">修改</li>
		</ul>
		<!-- /.breadcrumb -->
	</div>

	<div class="page-content">
		<div id="successDiv" style="color:#0eb839; font-size: 18px; font-weight: bolder; height: 20px; text-align: center; position:relative; left:-16%;">
		</div>
		<div id="errorDiv" style="color:#ff5a29;font-size: 18px;  font-weight: bolder; height: 20px; text-align: center; position:relative; left:-16%;">
		</div>

		<div class="row">
			<div class="col-md-offset-1 col-md-9">
			<sf:form id="userForm" class="form-horizontal" action="${base}/auth/user/update" method="post"
					 modelAttribute="form">
				<sf:input path="criaAcctNo" cssStyle="visibility: hidden"/>
				<sf:input path="criaName" cssStyle="visibility: hidden"/>
				<sf:input path="id" cssStyle="visibility: hidden"/>

				<!-- #section:elements.form -->
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right">用户账号</label>
					<div class="col-sm-9">
						<sf:input path="acctNo"  maxlength="30" size="40" readonly="true"/>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right">用户姓名</label>

					<div class="col-sm-9">
						<sf:input path="name"  maxlength="30" size="40" />
					</div>
				</div>
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

<div class="footer">
	<div class="footer-inner">
	</div>
</div>



<script src="${base}/common/js/jquery.js"></script>
<script src="${base}/common/js/library.js"></script>

<script type="text/javascript">

	$("#commitBtn").click(function () {
		$.ajax({
			type: 'POST',
			url: "${base}/auth/user/update",
			data:$('#userForm').serializeObject(),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			beforeSend:function() {
			},
			success:function(data) {
				if(data && data.error != null){
					$("#successDiv").hide();
					$("#errorDiv").show();
					$('#errorDiv').html("提交失败！"+data.error);
				}else{
					$("#errorDiv").hide();
					$("#successDiv").show();
					$('#successDiv').html("提交成功！");
					setTimeout(function(){$("#successDiv").hide();},3*1000);
				}
			},
			error : function(data, status, e) {
				alert("status:" + status.toString() + " " + "error:" + e);
			}
		});
	});

	$("#backBtn").click(function () {
		var path = "${base}/auth/user/query";
		$('#userForm').attr("action", path).submit();
	});
</script>

</body>
</html>