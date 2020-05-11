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
					<form action="${(user?has_content)?string('afterUserUpdate','registerUser')}" method="post" class="bg-light"  id="Form" enctype="multipart/form-data" modelAttribute="AddressDto">
						
						<div class="form-group">
							<br>
							<div class="circle">
								<img class="profile-pic" name="defaultImage" src="${(profilePicture?has_content)?string('data:image/gif;base64,${profilePicture!""}','image/user-purple.png')}" />
								<input type="hidden" name="profile" value="${profilePicture!""}" />
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
							<#if (user?has_content)>
								<#list user.address as address>
									<div class="list-item">
					                    <div class="form-group">
					                        <span style="float: right;color: red" class="list-remove"><i class="fa fa-times"></i></span>
					                        <input type="text" name="address[${address?index}].home" class="form-control" id="${address?index}.house" autocomplete="off" placeholder="Home,Flate Name" onblur="HomeValidate(${address?index}.)" value="${address.home!""}">
					                        <span id="${address?index}.HouseError" class="text-danger font-weight-bold"></span>
					                    </div>
					
					                   <div class="form-group">
					                        <input type="text" name="address[${address?index}].street" class="form-control" id="${address?index}.landmark" autocomplete="off" placeholder="Street,Locality,LandMark" onblur="StreetValidate(${address?index}.)" value="${address.street!""}">
					                        <span id="${address?index}.Landmark2Error" class="text-danger font-weight-bold"></span>
					                    </div>
					
					                    <div class="row">
					                        <div class="col-md-6">
					                            <input type="text" name="address[${address?index}].city" class="form-control" id="${address?index}.City" autocomplete="off" placeholder="City" onblur="CityValidate(${address?index}.)" value="${address.city!""}">
					                            <span id="${address?index}.CityError" class="text-danger font-weight-bold"></span>
					                        </div>
					                        <div class="col-md-6">
					                            <input type="text" name="address[${address?index}].state" class="form-control" id="${address?index}.State" autocomplete="off" placeholder="State" onblur="StateValidate(${address?index}.)" value="${address.state!""}" >
					                            <span id="${address?index}.StateError" class="text-danger font-weight-bold"></span>
					                        </div>
					                    </div>
					                    <br>
					                    <div class="row">
					                        <div class="col-md-6">
					                            <input type="text" name="address[${address?index}].country" class="form-control" id="${address?index}.Country" autocomplete="off" placeholder="Country" onblur="CountryValidate(${address?index}.)" value="${address.country!""}">
					                            <span id="${address?index}.CountryError" class="text-danger font-weight-bold"></span>
					                        </div>
					                        <div class="col-md-6">
					                            <input type="text" name="address[${address?index}].zipcode" class="form-control" id="${address?index}.ZipCode" autocomplete="off" placeholder="ZipCode" onblur="ZipCodeValidate(${address?index}.)" value="${address.zipcode!""}">
					                            <span id="${address?index}.ZipCodeError" class="text-danger font-weight-bold"></span>
					                        </div>
					                    </div>
					                    <input type="hidden" name="address[${address?index}].id" value="${address.id}" />
					                </div>
								</#list>
				            <#else>
								<div class="list-item">
				                    <div class="form-group">
				                        <span style="float: right;color: red" class="list-remove"><i class="fa fa-times"></i></span>
				                        <input type="text" name="address[0].home" class="form-control" id="0.house" autocomplete="off" placeholder="Home,Flate Name" onblur="HomeValidate(0.)" >
				                        <span id="0.HouseError" class="text-danger font-weight-bold"></span>
				                    </div>
				
				                    <div class="form-group">
				                        <input type="text" name="address[0].street" class="form-control" id="0.landmark" autocomplete="off" placeholder="Street,Locality,LandMark" onblur="StreetValidate(0.)" >
				                        <span id="0.Landmark2Error" class="text-danger font-weight-bold"></span>
				                    </div>
				
				                    <div class="row">
				                        <div class="col-md-6">
				                            <input type="text" name="address[0].city" class="form-control" id="0.City" autocomplete="off" placeholder="City" onblur="CityValidate(0.)" >
				                            <span id="0.CityError" class="text-danger font-weight-bold"></span>
				                        </div>
				                        <div class="col-md-6">
				                            <input type="text" name="address[0].state" class="form-control" id="0.State" autocomplete="off" placeholder="State" onblur="StateValidate(0.)"  >
				                            <span id="0.StateError" class="text-danger font-weight-bold"></span>
				                        </div>
				                    </div>
				                    <br>
				                    <div class="row">
				                        <div class="col-md-6">
				                            <input type="text" name="address[0].country" class="form-control" id="0.Country" autocomplete="off" placeholder="Country" onblur="CountryValidate(0.)" >
				                            <span id="0.CountryError" class="text-danger font-weight-bold"></span>
				                        </div>
				                        <div class="col-md-6">
				                            <input type="text" name="address[0].zipcode" class="form-control" id="0.ZipCode" autocomplete="off" placeholder="ZipCode" onblur="ZipCodeValidate(0.)" >
				                            <span id="0.ZipCodeError" class="text-danger font-weight-bold"></span>
				                        </div>
				                    </div>
				                </div>
							</#if>
				            <br>
				            <button class="btn btn-info list-add" value="addAddress">
			                   Add Address
			                </button>
						</div>       
						
						<#-- user data to manage updatation -->
						<input type="hidden" name="userId" value="${(user.id)!""}" />
						<input type="hidden" name="userRole" value="${(user.role.role)!""}" />
						
						<span style="color:red" id="errMassage" class="text-danger font-weight-bold">${errMassage ! ""}</span><br>
						<button type="submit" name="operation" value="Register" class="btn btn-success" form="Form" id="btn-submit" onclick="return validation(1);">
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
	<script type="text/javascript" src="js/valid.js"></script> 
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/jquery.dynamiclist.js"></script>
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