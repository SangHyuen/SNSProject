$(document).ready(function(){
	$('#login_btn').click(function(){
		console.log("login clicked!!!");
		
		var username = $('#login_username').val();
		var password = $('#login_password').val();
		
		if(!username || !password) {
			alert("error,No Blink!");
			return;
		}
		
		var param = {
				username: username,
				password: password
		}
		
		$.ajax({
	        url: "/auth",
	        method: "POST",
	        dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param)
	    }).then(function(data) {	    	
	    	document.cookie = "accesstoken=" + data.data.token;
	    	alert(data.data.token);
	    	window.location.href = '/index';
	    }, function(err) {
	    	alert("No User");
	    	window.location.reload();
	    });
		return false;
	});
});