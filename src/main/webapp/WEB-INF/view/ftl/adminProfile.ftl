		<!-- css -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
		<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap4.min.css" >

		<div class="container">
			<table id="example" class="table table-striped " style="width:100%;">
				<thead>
					<tr>
						<th>User ID</th>
						<th>First Name</th>
						<th>Email</th>
						<th>Contact</th>
						<th>Profile</th>
						<th>Gender</th>
						<th>Manage User</th>
					</tr>
				</thead>
				<tbody>
					<#list userDetail as list >
						<tr>
							<td>${list.id!""}</td>
							<td>${list.firstName!""}</td>
							<td>${list.email!""}</td>
							<td>${list.contact!""}</td>
							<td><img src="data:image/gif;base64,${list.base64image!""}" alt="profilePhoto" width="70px" height="70px" /></td>
							<td>${list.gender!""}</td>
							<td>
								<a href="doUpdateUser?id=${list.id!""}" style="color:white">
									<button class = "btn btn-sm btn-success btn">
										Edit
									</button>
								</a> 
								| 
								<button id = "delbtn" value = '${list.id!""}' class = "btn btn-sm btn-danger btn"> Delete</button>
							</td>
						</tr>
					</#list>
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