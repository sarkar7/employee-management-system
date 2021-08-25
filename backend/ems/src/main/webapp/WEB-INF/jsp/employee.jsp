<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.25/datatables.min.css" />

<style>
	#employeeList{
		width: 80%;
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		margin: auto;
	}
</style>


</head>
<body>
	<div id="employeeList">
		<table id="example" class="table table-striped table-bordered"
			style="width: 100%">
			<thead>
				<tr>
					<th>Name</th>
					<th>Position</th>
					<th>Office</th>
					<th>Age</th>
					<th>Start date</th>
					<th>Salary</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${empList}" var="emp">
					<tr>
						<td>${emp.name}</td>
						<td>${emp.position}</td>
						<td>${emp.office}</td>
						<td>${emp.age}</td>
						<td>${emp.startDate}</td>
						<td>${emp.salary}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>







	<script src="js/jquery-3.5.1.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/dt/dt-1.10.25/datatables.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</body>
</html>