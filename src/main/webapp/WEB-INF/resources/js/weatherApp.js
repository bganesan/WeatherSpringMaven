/**
 * 
 */
$(function() {
	function validateZIP(field) {
		
		if(typeof field == 'undefined' || field.length == 0){
			$('#errorMessage').html('Invalid zip code format. Please enter your 5 digit zipcode.');
			$('#errorMessage').show();
			return false;
		}
		
		var valid = "0123456789";
		for (var i=0; i < field.length; i++) {
			temp = "" + field.substring(i, i+1);
			if (valid.indexOf(temp) == "-1") {
				$('#errorMessage').html('Invalid zip code format. Please try again.');
				$('#errorMessage').show();
				return false;
			}
		}
		
		if (field.length != 5) {
			$('#errorMessage').html('Invalid zip code format. Please enter your 5 digit zipcode.');
			$('#errorMessage').show();
			return false;
		}
		return true;
	}
	$('#zipCode').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			$(".zipCodeButton").trigger('click');
		}
	 
	});

	$("#zipCodeSection").on("click", ".zipCodeButton", function() {
		var zip = $("#zipCode").val().trim();
		$('#errorMessage, #zipCodeDataSection').hide();
		if(validateZIP(zip)){
			$.ajax({
				type: "GET",
				url: "/WeatherSpringMaven/getWeather",
				data: "zipCode="+zip,
				success: function (data) {
					var errorMessage = data.error;
					if(typeof errorMessage != 'undefined'){
						if(errorMessage == '_ERR_INVALID_REQ'){
							$('#errorMessage').html('Invalid ZIP Format.');
							$('#errorMessage').show();
						}
						else if(errorMessage == '_ERR_ZIP_NOTFOUND'){
							$('#errorMessage').html('ZIP Code not found.');
							$('#errorMessage').show();
						}
						else if(errorMessage != ''){
							$('#errorMessage').html('A temporary Error Occured. Please try again later.');
							$('#errorMessage').show();
						}
						$("#zipCode").focus();
					}
					else{
						var temp = data.temperature, city = data.city,
						state = data.state,zip = data.zip;
						$("#zipCode").val('');
						$("#temperature").html(temp);
						$("#city").html(city);
						$("#state").html(state);
						$("#zip").html(zip);
						$("#zipCodeDataSection").show();
					}

				},
				error: function (error) {
					$('#errorMessage').html('Temporary Error Occured');
					$('#errorMessage').show();
				}
			});
		}
	});
});