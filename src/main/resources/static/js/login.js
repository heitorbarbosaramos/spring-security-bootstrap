var url  = window.location.href;

$(document).ready(function(){
	//$("#loader-img").hide();
	
	
	var page = url.split('/');
    var page = page[page.length-1];
	
	console.log(page);
	
	if(page === 'login?error'){
		$("#error").show();
	}else{
		$("#error").hide();
	}
	
	

	 $("#tabela").DataTable();

});
