<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<script type="text/javascript" src="${path }/static/jquery/jquery-3.3.1.min.js"></script>
<link type="text/css" rel="stylesheet" href="${path }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<style type="text/css">

	.table th, .table td { 
		text-align: center; 
		vertical-align: middle!important;
	}
	
	.info_left {
		margin: 20px;
		text-align: center;
	}

</style>

<script type="text/javascript">
	
	$(function(){
		
		to_page(1);
		
	});
	
	//跳转页码的 ajax 请求
	function to_page(pageNum){
		$.ajax({
			
			url: "${path}/emp",
			data: "page=" + pageNum,
			type: "GET",
			success: function(data){
				//1.解析并显示员工数据
				emps_table(data);
				
				//2.解析并显示分页信息
				page_inf(data);
				
				//3.显示分页条数据
				page_nav(data);
			}
			
		});
	}
	
	function emps_table(result){
		//
		$("tbody").empty();
		
		var emps = result.extend.pageInfo.list;
		
		for(var i=0; i<emps.length; i++){
			var empId = $("<td></td>").append(emps[i].empId);
			var name = $("<td></td>").append(emps[i].name);
	
			var gender = emps[i].gender == 'm' ? '男' : '女';
			var gender = $("<td></td>").append(emps[i].gender);
	
			var email = $("<td></td>").append(emps[i].email);
			var departmentName = $("<td></td>").append(emps[i].department.name);
		
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm")
							.addClass("glyphicon glyphicon-pencil").attr("id", emps[i].empId)
							.append("<span></span>").append("编辑");
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
							.addClass("glyphicon glyphicon-remove").attr("id", emps[i].empId)
							.append("<span></span>").append("删除");
			
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			
			$("<tr></tr>").append(empId)
				.append(name)
				.append(gender)
				.append(email)
				.append(departmentName)
				.append(btnTd)
				.appendTo("tbody");
		}
	}
	
	function page_inf(result){
		//
		$("#page_info_id").empty();
		
		var page = result.extend.pageInfo;
		$("#page_info_id").append("当前第" + page.pageNum + "页，一共有" + page.pages + "页，总共有" + page.total + "条记录");
	}
	
	function page_nav(result){
		
		$("#page_nav_id").empty();
		
		var pageinfo = result.extend.pageInfo;
		var pageNums = pageinfo.navigatepageNums;
		
		var ulNode = $("<ul></ul>").addClass("pagination");
		
		var aFirstNode = $("<a></a>").attr("href", "#").text("首页");
		var firstLiNode = $("<li></li>").append(aFirstNode);
		
		var aLastNode = $("<a></a>").attr("href", "#").text("尾页");
		var lastLiNode = $("<li></li>").append(aLastNode);
		
		var aPreNode = $("<a></a>").attr("href", "#").text("«");
		var preLiNode = $("<li></li>").append(aPreNode);
		
		var aLastNode = $("<a></a>").attr("href", "#").text("»");
		var nextLiNode = $("<li></li>").append(aLastNode);
		
		//判断是否有前一页
		if(pageinfo.hasPreviousPage == false){
			firstLiNode.addClass("disabled");
			preLiNode.addClass("disabled");
		} else {
			//跳转到首页
			firstLiNode.click(function(){
				to_page(1);
			});
			
			//跳转到前一页
			preLiNode.click(function(){
				to_page(pageinfo.prePage);
			});
		}
		
		//判断是否有后一页
		if(pageinfo.hasNextPage == false){
			lastLiNode.addClass("disabled");
			nextLiNode.addClass("disabled");
		} else {
			//跳转到下一页
			nextLiNode.click(function(){
				to_page(pageinfo.pageNum + 1);
			});
			
			//跳转到尾页
			lastLiNode.click(function(){
				to_page(pageinfo.pages);
			})
		}
		
		ulNode.append(firstLiNode).append(preLiNode);
		
		$.each(pageNums, function(index, item){
			var aNumNode = $("<a></a>").attr("href", "#").text(item);
			var numLiNode = $("<li></li>").append(aNumNode);
			
			//
			if(pageinfo.pageNum == item){
				numLiNode.addClass("active");
			}
			
			numLiNode.click(function(){
				to_page(item);
			});
			
			ulNode.append(numLiNode);
		})
		
		ulNode.append(nextLiNode).append(lastLiNode);
		
		$("<nav></nav>").append(ulNode)
			.appendTo("#page_nav_id");
	}
	
</script>
</head>
<body>
	<div class="container">
		<!-- 标签 -->
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				<h1>信息管理</h1>
			</div>
		</div>

		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-success">增加</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>

		<br>

		<!-- 表格 -->
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>邮箱</th>
							<th>部门</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				<div class="info_left">
					<div class="info_left" id="page_info_id"></div>
				</div>
			</div>
			
			<!-- 分页信息 -->
			<div class="col-md-6" id="page_nav_id">
				
			</div>
		</div>
	</div>
</body>
</html>