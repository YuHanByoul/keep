/**
 * FAQ
 */
var FAQ = {
	/**
	 * 
	 */
	insert: function(item, callback){

			let data = {
					"userSeCd": item.userSeCd,
					"clid": item.clid,
					"title": item.title,
					"cntnts": item.cntnts,
					"ord": item.ord,
					"useYn": item.useYn
			};
			
			$.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mng/faq/add.do",
                data: JSON.stringify(data),
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("추가에 실패하였습니다. 권한이 있는지 확인하십시오.");
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
					"faqid": item.faqid,
					"userSeCd": item.userSeCd,
					"title": item.title,
					"cntnts": item.cntnts,
					"ord": item.ord,
					"useYn": item.useYn
			};
			
			$.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/mng/faq/update.do",
                data: JSON.stringify(data),
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("수정에 실패하였습니다. 권한이 있는지 확인하십시오.");
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
                url: "/mng/faq/delete.do?faqid="+item.faqid,
                dataType: "json"
              }).done(function(response){
            	  console.log(response);
            	  if(!response){
            		  alert("삭제에 실패하였습니다. 권한이 있는지 확인하십시오.");
            	  }
            	  
            	  if(typeof callback === 'function') callback();
              });
		}
}