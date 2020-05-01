<html>
<head>
<meta charset="UTF-8">
<title>Profile Page</title>
</head>
<body>


	<h2>User Information</h2>
	
	<table id="table-1">
		<thead>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
			</tr>
		</thead>
		<tbody>
			<#list emplist as emp>
				<tr>
					<td>${emp.id}</td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	
	
	
	

</body>
</html>