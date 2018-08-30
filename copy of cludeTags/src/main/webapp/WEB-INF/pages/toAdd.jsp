<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {

		$("#addButton").bind("click", function() {
			$.ajax({
				type : "post",
				url : "favorite_doAdd.action",
				data : $("#addForm").serialize(),
				dataType : "json",
				success : function(data) {
					//将结果json字符串传到  index.html页面
					window.returnValue = data; //将结果的json存到   window.returnValue
					window.close();
				}
			});
		});

	});
</script>

</head>

<body>
	<form id="addForm" method="post">
		链接名:<input type="text" name="flabel" id="flabel" /><br /> 
		地址:<input type="text" name="furl" id="furl" /> <br /> 
			类别:<input type="text" name="ftags" id="ftags" /><br /> 
			描述:<textarea rows="5" cols="40" name="fdesc" id="fdesc"></textarea>
		<br /> 
		<input id="addButton" type="button" value="添加" />
	</form>
</body>
</html>