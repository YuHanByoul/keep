$(document).ready(function(){
	
	$('#roleMngMainTab a').click(function (e) {
		  e.preventDefault();
		  var href = $(this).attr('href');
		  //console.log(href);
		  switch(href){
		  case '#defineRole':
			  loadTabContent('#defineRoleContent', '/mng/roleauth/defineRole.html?seCd='+_ROLE_LEVEL);
			  $('#mappingUserContent').html('');
			  $('#configMenuContent').html('');
			  $('#configRgnContent').html('');
              $('#configInstContent').html('');
			  
			  break;
		  case '#mappingUser':
			  loadTabContent('#mappingUserContent', '/mng/roleauth/mappingUserForm.html?seCd='+_ROLE_LEVEL);
			  $('#defineRoleContent').html('');
			  $('#configMenuContent').html('');
			  $('#configRgnContent').html('');
              $('#configInstContent').html('');

			  break;
		  case '#configMenu':
			  loadTabContent('#configMenuContent', '/mng/roleauth/configRoleMenuForm.html?seCd='+_ROLE_LEVEL);
			  $('#defineRoleContent').html('');
			  $('#mappingUserContent').html('');
			  $('#configRgnContent').html('');
			  $('#configInstContent').html('');

			  break;
		  case '#configInst':
              loadTabContent('#configInstContent', '/mng/roleauth/configRoleInstForm.html?seCd='+_ROLE_LEVEL);
              $('#defineRoleContent').html('');
              $('#mappingUserContent').html('');
              $('#configMenuContent').html('');
              $('#configRgnContent').html('');

              break;
          case '#configRgn':
              loadTabContent('#configRgnContent', '/mng/roleauth/configRoleRgnForm.html?seCd='+_ROLE_LEVEL);
              $('#defineRoleContent').html('');
              $('#mappingUserContent').html('');
              $('#configMenuContent').html('');
              $('#configInstContent').html('');

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