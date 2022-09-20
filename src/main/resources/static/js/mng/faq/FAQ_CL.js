/**
 * FAQ_CL
 */
var FAQ_CL = {
	/**
	 * 
	 */
	insert: function(item, callback){

			let data = {
					"user_se_cd": item.user_se_cd,
					"cl_nm": item.cl_nm,
					"ord": item.ord,
					"use_yn": item.use_yn
			};
			
			$.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mng/faq/addCl.do",
                data: JSON.stringify(data),
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("추가에 실패하였습니다. 권한이 있는지 확인 하십시오.");
            	  }
            	  
            	  if(typeof callback === 'function') callback();
              });
			
	},
		
	/**
	 * 
	 */
	update: function(item, callback){
			
		let data = {
				"clid": item.clid,
				"user_se_cd": item.user_se_cd,
				"cl_nm": item.cl_nm,
				"ord": item.ord,
				"use_yn": item.use_yn
		};
			
		$.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mng/faq/updateCl.do",
                data: JSON.stringify(data),
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("수정에 실패하였습니다. 권한이 있는지 확인 하십시오.");
            	  }

            	  if(typeof callback === 'function') callback();
        });
			
	},
	
	/**
	 * 
	 */
	delete: function(item, callback){
			
			$.ajax({
                type: "DELETE",
                contentType: "application/json; charset=utf-8",
                url: "/mng/faq/deleteCl.do?clid="+item.clid,
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("삭제에 실패하였습니다. 권한이 있는지 확인 하십시오.");
            	  }
            	  
            	  if(typeof callback === 'function') callback();
              });
		}
}