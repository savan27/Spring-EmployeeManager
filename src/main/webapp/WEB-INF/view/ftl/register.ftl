<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
		
</head>
<body>

	<!-- Form area starts-->
	<div class="form" >
		<div class="container">
			<br>
			<div class="row">
				<div class="col-md-8 m-auto d-block">
					<h1 class="text-success text-center">
					${(user?has_content)?string('Update User Profile','Registration Form')}
					</h1>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-8 m-auto d-block">
					<form action="registerUser" method="post" class="bg-light"  id="Form" enctype="multipart/form-data" modelAttribute="AddressDto">
						
						<div class="form-group">
							<br>
							<div class="circle">
								<img class="profile-pic" name="defaultImage" src="${(profilePicture?has_content)?string('data:image/gif;base64,${profilePicture!""}','image/user-purple.png')}" />
							</div>
							<label>Upload Profile Picture </label>
							<i class="fa fa-camera upload-button"></i>
							<div class="p-image">
								<input class="file-upload" name="File" type="file" accept="image/*" id="File" onchange="imageValidate(this)" />
								<span id="FileError" class="text-danger font-weight-bold"></span>
							</div>
						</div>
						
						<div class="form-group">
							<label>First Name</label>
							<input type="text" name="firstName" class="form-control" id="firstName1" autocomplete="off" onblur="FnameValidate(1)" value="${(user.firstName)!""}" >
							<span id="FnameError1" class="text-danger font-weight-bold"></span>
						</div>

						<div class="form-group">	
							<label>Last Name</label>
							<input type="text" name="lastName" class="form-control" id="lastName1" autocomplete="off" onblur="LnameValidate(1)" value="${(user.lastName)!""}">
							<span id="LnameError1" class="text-danger font-weight-bold"></span>
						</div>

						<div class="form-group">
							<label>Password</label>
							<input type="password" name="password" class="form-control" id="password1" autocomplete="off"  onpaste="return false;" onblur="pwdValidate(1)" value="${(user.password)!""}">
							<span id="PasswordError1" class="text-danger font-weight-bold"></span>
						</div>

						<div class="form-group">
							<label>Confirm Password</label>
							<input type="password" name="Cpassword" class="form-control" id="confirmPassword1" autocomplete="off"  onpaste="return false;" onblur="cpwdValidate(1)" value="${(user.password)!""}">
							<span id="CpasswordError1" class="text-danger font-weight-bold"></span>
						</div>

						<div class="form-group">
							<label>Email Address</label>
							<input type="text" name="email" class="form-control" id="Email1" autocomplete="off" onblur="emailValidate(1)" value="${(user.email)!""}">
							<span id="EmailError1" class="text-danger font-weight-bold"></span>
						</div>

						<div class="form-group">
							<label>Contact No.</label>
							<input type="text" name="contact" class="form-control" id="Contact1" autocomplete="off" onblur="conValidate(1)" value="${(user.contact)!""}">
							<span id="ContactError1" class="text-danger font-weight-bold"></span>
						</div>
						<#assign gender= (user.gender)!"null"/>
						<div class="form-group"> 
							<label>Gender</label>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="gender" id="male" value="male" <#if gender == "male">checked</#if> >
								<label class="form-check-label" >
									Male
								</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="gender" id="female" value="female" <#if gender == "female">checked</#if> >
								<label class="form-check-label" >
									Female
								</label>
							</div>
						</div>
						<#assign permission= (user.permission)!"null"/>
						<div class="form-group">
							<label>Select Permission</label><br>
							<input type="checkbox" name="permission" value="Read" <#if permission?contains("Read")>checked</#if> >	Read
							<input type="checkbox" name="permission" value="Write" <#if permission?contains("Write")>checked</#if>  > Write
							<input type="checkbox" name="permission" value="Execute" <#if permission?contains("Execute")>checked</#if>  >Execute
						</div>
						<#assign hobbies= (user.hobbies)!"null"/>
						<div class="form-group">
							<label>Select You Like</label><br>
							<select name="hobbies" id="t">
							    <option value="Read" ${(hobbies == 'Read')?string('selected','')} >Read</option>
							    <option value="Write" ${(hobbies == 'Write')?string('selected','')} >Write</option>
							    <option value="Executeid" ${(hobbies == 'Executeid')?string('selected','')} >Execute</option>
						    </select>
						</div>

					<!-- manage address for existing user or new one -->
					<div id="example1" class="controls">
						<div class="list-item">
		                    <div class="form-group">
		                        <span style="float: right;color: red" class="list-remove"><i class="fa fa-times"></i></span>
		                        <input type="text" name="address[0].home" class="form-control" id="0.house" autocomplete="off" placeholder="Home,Flate Name" onblur="HomeValidate(0.)" value="">
		                        <span id="0.HouseError" class="text-danger font-weight-bold"></span>
		                    </div>
		
		                    <div class="form-group">
		                        <input type="text" name="address[0].street" class="form-control" id="0.landmark" autocomplete="off" placeholder="Street,Locality,LandMark" onblur="StreetValidate(0.)" value="">
		                        <span id="0.Landmark2Error" class="text-danger font-weight-bold"></span>
		                    </div>
		
		                    <div class="row">
		                        <div class="col-md-6">
		                            <input type="text" name="address[0].city" class="form-control" id="0.City" autocomplete="off" placeholder="City" onblur="CityValidate(0.)" value="">
		                            <span id="0.CityError" class="text-danger font-weight-bold"></span>
		                        </div>
		                        <div class="col-md-6">
		                            <input type="text" name="address[0].state" class="form-control" id="0.State" autocomplete="off" placeholder="State" onblur="StateValidate(0.)" value="" >
		                            <span id="0.StateError" class="text-danger font-weight-bold"></span>
		                        </div>
		                    </div>
		                    <br>
		                    <div class="row">
		                        <div class="col-md-6">
		                            <input type="text" name="address[0].country" class="form-control" id="0.Country" autocomplete="off" placeholder="Country" onblur="CountryValidate(0.)" value="">
		                            <span id="0.CountryError" class="text-danger font-weight-bold"></span>
		                        </div>
		                        <div class="col-md-6">
		                            <input type="text" name="address[0].zipcode" class="form-control" id="0.ZipCode" autocomplete="off" placeholder="ZipCode" onblur="ZipCodeValidate(0.)" value="">
		                            <span id="0.ZipCodeError" class="text-danger font-weight-bold"></span>
		                        </div>
		                    </div>
		                </div>
		                <br>
		                <button class="btn btn-info list-add" value="addAddress">
	                        Add Address
	                    </button>
					</div>       
						 <#--    <c:otherwise>
						    	<c:set var="count" value="1" scope="page" />
								<c:forEach items="${address.addressList}" var="data">
									<div>
										<div class="form-group">
											<label>Address ${count}:</label>
											<span style="float: right;" id="addAddress${data.AddressId}"><i class="fa fa-plus"></i></span>
											<pre style="float: right;"> | </pre>
											<span style="float: right;" id="removeAddress${data.AddressId}"><i class="fa fa-minus"></i></span>
											<input type="text" name="home" class="form-control" id="house${count}" autocomplete="off" placeholder="Address Line1" onblur="HomeValidate(${count ! ""})" value="">
											<span id="HouseError${count}" class="text-danger font-weight-bold"></span>
											<span>${address1Err}</span>
										</div>						
				
										<div class="form-group">
											<input type="text" name="leandmark" class="form-control" id="landmark${count}" autocomplete="off" placeholder="Address Line2" onblur="StreetValidate(${count})" value="">
											<span id="Landmark2Error${count}" class="text-danger font-weight-bold"></span>
											<span >${address2Err}</span>
										</div>
										
										<div class="row">
											<div class="col-md-6">
												<input type="text" name="City" class="form-control" id="City${count}" autocomplete="off" placeholder="City" onblur="CityValidate(${count})" value="">
												<span id="CityError${count}" class="text-danger font-weight-bold"></span>
												<span >${cityErr}</span>
											</div>
											<div class="col-md-6">
												<input type="text" name="State" class="form-control" id="State${count}" autocomplete="off" placeholder="State" onblur="StateValidate(${count})" value="">
												<span id="StateError${count}" class="text-danger font-weight-bold"></span>
												<span >${stateErr}</span>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="col-md-6">
												<input type="text" name="Country" class="form-control" id="Country${count}" autocomplete="off" placeholder="Country" onblur="CountryValidate(${count})" value="">
												<span id="CountryError${count}" class="text-danger font-weight-bold"></span>
												<span>${countryErr}</span>
											</div>
											<div class="col-md-6">
												<input type="text" name="ZipCode" class="form-control" id="ZipCode${count}" autocomplete="off" placeholder="ZipCode" onblur="ZipCodeValidate(${count})" value="">
												<span id="ZipCodeError${count}" class="text-danger font-weight-bold"></span>
												<span >${zipCodeErr}</span>
											</div>
										</div>
										<input type="hidden" name="addressId" value="${data.AddressId}" />
										<br>
									</div>
									<!-- increment the count 
									<c:set var="count" value="${count + 1}" scope="page"/>
								</c:forEach>
								<!-- get the last count 
								<input type="hidden" id="addressCount" value="${count ! ""}">
						    </c:otherwise>
						</c:choose> -->
						
						
						<!-- New address Append Here -->
						<div class="new_address_wrap">
							
						</div>
						
						<span style="color:red" id="errMassage" class="text-danger font-weight-bold">${errMassage ! ""}</span><br>
						<button type="submit" name="operation" value="Register" class="btn btn-success" form="Form" id="btn-submit" <#-- onclick="return validation(1);" -->>
							${(user?has_content)?string('Update','Register')}
						</button>
						<button class="btn btn-danger" name="operation" value="cancel">
							Cancel
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Form area ends-->

	<!-- Script -->
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/popper.min.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
	<#-- <script type="text/javascript" src="js/valid.js"></script> -->
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/jquery.dynamiclist.js"></script>
	<#-- <script type="text/javascript" src="js/appendAddress.js"></script> --> 
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<#-- Dynamically address appending -->
	<script>
         (function($) {
              $(document).ready(function() {
                  $("#example1").dynamiclist();
               });
          })(jQuery);
    </script>
			
</body>
</html>