$().ready(function(){
	$("#RegistrationExternal").validate({
		rules:{
			designation: "required",
			firstname: {
				required:true,
				alphabet:true},
			lastname:{
				required:true,
				alphabet:true},
			username:{
				minlength:2,
				required: true
			},
			password:{
				minlength:5,
				required: true
			},
			confirmpassword:{
				minlength:5,
				required: true,
				equalTo:"#password"
			},
			email:"required",
			address:"required",
			phone:"required",
			date_of_birth:"required",
			ssn:"required",
			secquestion1:"required",
			secquestion2:"required"
		},
		messages:{
			designation:"Please select a customer type",
			firstname:{
				required:"Please enter your first name",
				},
			lastname:{
				required:"Please enter your last name",
			},
			ssn:"Please enter your ssn",
			date_of_birth:"Please enter your DOB",
			address:"Please enter your Address",
			phone:"Please enter your phone number",
			secquestion1:"Please answer the security question",
			secquestion2:"Please answer the security question",
			email:"Please enter your email",
			username:{
				minlength:"Username should be atleast 2 characters long",
				required:"Please enter a username"
			},
			password:{
				minLength:"Password should be atleast 6 characters long",
				required:"Please enter a password"
			},
			confirmpassword:{
				minLength:"Password should be atleast 6 characters long",
				required:"Please enter a password",
				equalTo:"Passwords dont match"
			}
		}
	});
	
	$("#username").focus(function(){
		var firstname= $("#firstname").val();
		var lastname=$("#lastname").val();
		if(firstname && lastname && !this.value){
			this.value = firstname + "." + lastname;
		}
	});
	
	jQuery.validator.addMethod("alphabet", function(value, element) {
	    return this.optional(element) || /^[A-z]+$/i.test(value);
	}, "Letters only please");
})