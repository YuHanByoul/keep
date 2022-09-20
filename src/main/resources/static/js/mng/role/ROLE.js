/**
 * ROLE
 */
var ROLE = {

	/**
	 * 
	 */
	update: function(item, callback){
			
		let data = {
				"roleid": item.roleid,
				"userid": item.userid,
				"role_strt_dt": item.role_strt_dt,
				"role_end_dt": item.role_end_dt
		};
			
		console.log(JSON.stringify(data));
			
		$.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mng/roleauth/roleUserMappSave.do",
                data: JSON.stringify(data),
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("저장에 실패하였습니다. 권한이 있는지 확인하십시오.");
            		  return false;
            	  }

            	  if(typeof callback === 'function') callback(response);
        });
			
	},
	
	/**
	 * 
	 */
	delete: function(item, callback){
			console.log(JSON.stringify(item));
			
			$.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mng/roleauth/deleteRoleUser.do",
                data: JSON.stringify(item),
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("삭제에 실패하였습니다. 권한이 있는지 확인하십시오.");
            	  }
            	  
            	  if(typeof callback === 'function') callback(response);
              });
		}
}