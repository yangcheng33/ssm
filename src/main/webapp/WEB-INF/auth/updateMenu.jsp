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

			<li>菜单管理</li>
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
				<sf:form id="menuForm" class="form-horizontal" action="${base}/auth/menu/update" method="post"
						 modelAttribute="form">
					<sf:input path="id" cssStyle="visibility: hidden"/>
					<sf:input path="pid" cssStyle="visibility: hidden"/>
					<sf:input path="criaName" cssStyle="visibility: hidden"/>
					<sf:input path="criaPname" cssStyle="visibility: hidden"/>
					<sf:input path="CURR_PAGENUM"  cssStyle="visibility: hidden"/>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">上级菜单名称</label>
						<div class="col-sm-9">
							<sf:input path="pname" size="50" maxlength="30" readonly="true"/>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">菜单名称</label>

						<div class="col-sm-9">
							<sf:input  path="name"  size="50" maxlength="30"/>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">菜单编号</label>

						<div class="col-sm-9">
							<sf:input path="code" size="50" maxlength="30" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right">菜单url</label>

						<div class="col-sm-9">
							<sf:input path="url" size="65" maxlength="120"/>
						</div>
					</div>

				</sf:form>
			</div>

			<div class="col-md-offset-4 col-md-9" >
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
		var name = $('#name').val().trim();
		var code = $('#code').val().trim();
		var url = $('#url').val().trim();

		if(name==""){
			alert("请输入菜单名称!");
			$('#name').focus();
			return false;
		}

		if(lengthB(name)>60){
			alert("菜单名称超长，请重新编辑!");
			$('#name').focus();
			return false;
		}

		if(code==""){
			alert("请输入菜单编号!");
			$('#code').focus();
			return false;
		}

		if(lengthB(code)>60){
			alert("菜单编号超长，请重新编辑!");
			$('#code').focus();
			return false;
		}

		if(lengthB(url)>100){
			alert("菜单编号超长，请重新编辑!");
			$('#url').focus();
			return false;
		}

		$.ajax({
			type: 'POST',
			url: "${base}/auth/menu/update",
			data:$('#menuForm').serializeObject(),
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
		var path = "${base}/auth/menu/query";
		$('#menuForm').attr("action", path).submit();
	});
</script>

</body>
</html>