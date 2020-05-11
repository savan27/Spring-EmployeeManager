		<!-- css -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
		<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap4.min.css" >

		<div class="container">
			<table id="example" class="table table-striped " style="width:100%;">
				<thead>
					<tr>
						<th>User ID</th>
						<th>First Name</th>
						<th>Last name</th>
						<th>Email</th>
						<th>Contact</th>
						<th>Profile</th>
						<th>Gender</th>
						<th>Permission</th>
						<th>Hobbies</th>
						<th>Manage User</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.userList}" var="user">
						<tr>
							<td><c:out value="${user.userRId}" /></td>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.contact}" /></td>
							<td><img src="data:image/gif;base64,${user.displayImage}" alt="profilePhoto" width="70px" height="70px" /></td>
							<td><c:out value="${user.gender}" /></td>
							<td><c:out value="${user.permission}" /></td>
							<td><c:out value="${user.hobbies}" /></td>
							<td>
								<a href="RegisterController?role=admin&operaton=Update&id=<c:out value='${user.userRId}' />" style="color:white">
									<button class = "btn btn-sm btn-success btn">
										Edit
									</button>
								</a> 
								| 
								<button id = "delbtn" value = '${user.userRId}' class = "btn btn-sm btn-danger btn"> Delete</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Script -->
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="js/popper.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/dataTables.bootstrap4.min.js"></script>
		<script type="text/javascript" src="js/dataTable.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript" src="js/valid.js"></script>

	<!-- Admin profile section ends -->