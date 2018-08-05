<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<!-- 路径使用绝对路径, 即以服务器的路径(http://localhoat:8080)为基准 -->
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
				<table class="table table-striped">
					<tr>
						<th>编号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					
					<c:forEach items="${pageInfo.list }" var="page">
						<tr>
							<td>${page.empId }</td>
							<td>${page.name }</td>
							<td>${page.gender == "m" ? "男" : "女" }</td>
							<td>${page.email }</td>
							<td>${page.department.name }</td>
							<td>
								<button class="btn btn-primary btn-sm" id="${page.empId }">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 编辑
								</button>
							
								<button class="btn btn-danger btn-sm" id="${page.empId }">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				<div class="info_left">当前第 ${pageInfo.pageNum } 页，一共 ${pageInfo.pages } 页， 总共 ${pageInfo.total } 条记录</div>
			</div>
			
			<div class="col-md-6">
				<nav aria-label="Page navigation">
			    	<ul class="pagination">

			    		<li><a href="${path }/emps?page=1">首页</a></li>
			    		
			    		<c:if test="${pageInfo.hasPreviousPage }">
				   			<li>
				      			<a href="${path }/emps?page=${pageInfo.prePage }" aria-label="Previous">
				        			<span aria-hidden="true">&laquo;</span>
				      			</a>
				    		</li>
			    		</c:if>
			    		
			    		<c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
			    			<c:if test="${page_Num == pageInfo.pageNum}">
				    			<li class="active"><a href="#">${page_Num }</a></li>
			    			</c:if>
			    			<c:if test="${page_Num != pageInfo.pageNum }">
			    				<li><a href="${path }/emps?page=${page_Num }">${page_Num }</a></li>
			    			</c:if>
			    		</c:forEach>
			    		
			    		<c:if test="${pageInfo.hasNextPage }">
				    		<li>
				      			<a href="${path }/emps?page=${pageInfo.nextPage }" aria-label="Next">
				        			<span aria-hidden="true">&raquo;</span>
				      			</a>
				    		</li>
			    		</c:if>
			    		
			    		<li><a href="${path }/emps?page=${pageInfo.pages }">尾页</a></li>
			  		
			  		</ul>
				</nav>
			</div>
		</div>
	</div>

</body>
</html>