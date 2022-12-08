$(document).ready(function(){
	//console.log("Start : " + _ROLE_LEVEL);
	loadTabContent('#defineRoleContent', '/mng/roleauth/defineRole.html?seCd='+_ROLE_LEVEL);
	
	$('#roleMngMainTab a').click(function (e) {
		  e.preventDefault();
		  var href = $(this).attr('href');
		  //console.log(href);
		  switch(href){
		  case '#defineRole':
			  loadTabContent('#defineRoleContent', '/mng/roleauth/defineRole.html?seCd='+_ROLE_LEVEL);
			  $('#mappingUserContent').html('');
			  $('#configMenuContent').html('');
			  
			  break;
		  case '#mappingUser':
			  loadTabContent('#mappingUserContent', '/mng/roleauth/mappingUserForm.html?seCd='+_ROLE_LEVEL);
			  $('#defineRoleContent').html('');
			  $('#configMenuContent').html('');

			  break;
		  case '#configMenu':
			  loadTabContent('#configMenuContent', '/mng/roleauth/configRoleMenuForm.html?seCd='+_ROLE_LEVEL);
			  $('#defineRoleContent').html('');
			  $('#mappingUserContent').html('');

			  break;
		  }
		  
		  $(this).tab('show');
		})
});

function loadTabContent(id, url){
	
	$(id).load(url, function( response, status, xhr ) {
		if ( status == "error" ) {
			console.log("Loading Error : " + url);
		}
	});
}