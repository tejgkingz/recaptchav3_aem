$('#form_aeminmyway').on('submit',function(){
//reCaptcha v3
if ($("#g-recaptcha-response").length) {
	var grecap = $("#g-recaptcha-response").val();
	var nop = false;
	$.ajax({
	 	url: '/bin/aeminmyway/getRecaptchaResponseServlet?token=' + grecap,
	 	data: $(this).serialize(), 
	 	async: false, 
	 	dataType: 'json', 
	 	cache: false, 
	 	success: function(data) {
	 		if (data.success == true && data.score > 0.5) {
	 		   nop = true;  
	 		} else {   
	 		   nop = false;  
	 		} 
	 	}, 
	 	fail: function(textStatus, errorThrown) {  
	 		console.log(textStatus, errorThrown); 
	 	}});
		if (nop == false) { 
			return false;
		}
	}});
});
}	});