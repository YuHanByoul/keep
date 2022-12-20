/**
 * FAQ_CL
 */
var FAQ_CL = {
	/**
	 * 
	 */
	insert: function(item, callback){

			let data = {
					"userSeCd": item.userSeCd,
					"clNm": item.clNm,
					"ord": item.ord,
					"useYn": item.useYn
			};
			
			$.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mng/faq/insertCl.do",
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
				"userSeCd": item.userSeCd,
				"clNm": item.clNm,
				"ord": item.ord,
				"useYn": item.useYn
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