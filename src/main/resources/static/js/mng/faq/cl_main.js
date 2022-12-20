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


/**
 * 
 */

$.getScript( "/js/mng/faq/FAQ_CL.js" );

var FAQ_CL_TARGET = [
	{ Name: "개인", Id: '101101' },
	{ Name: "기업", Id: '101102' }
];

$(function(){
	//jsGrid Init
	$("#jsGrid").jsGrid({
	        height: "auto",
	        width: "100%",
	        autoload: true,
	        sorting: false,
	        paging: true,
	        pagerContainer: "#listPager",
	        pagerFormat: "{first} {prev} {pages} {next} {last}",
	        pagePrevText: "이전",
	        pageNextText: "다음",
	        pageFirstText: "< 처음 ㅣ",
	        pageLastText: "ㅣ마지막 >",
	        
	        pageSize: 10,
	        pageButtonCount: 10,
	        pageLoading: true,
	        pageIndex: 1,
	        
	        controller: {
	            loadData: function (filter) {
	            	var data = $.Deferred();
	            	
	            	var params = {
            			"pageNumber": filter.pageIndex,
            			"rowPerPage": filter.pageSize,
            			"searchType" : $("#searchType").val()
	            	}
	            	
	                $.ajax({
	                  type: "GET",
	                  contentType: "application/json; charset=utf-8",
	                  url: "/mng/faq/selectFaqClList.do",
	                  data: params,
	                  dataType: "json"
	                }).done(function(response){
	                	var da = {
                                data :response.list,
                                itemsCount : response.totalCount
/*                                 pageSize: response.length,
                                pageIndex: response.page, */
                            };
	                  data.resolve(da);
	                });
	                
	                return data.promise();
	            },
		        insertItem: function(item) {
		            console.log(item);
		            FAQ_CL.insert(item);
		        },

		        updateItem: function(item) {
		            console.log(item);
		            FAQ_CL.update(item);
		        },

		        deleteItem: function(item) {
		            console.log(item);
		            FAQ_CL.delete(item, function(){ $("#jsGrid").jsGrid("refresh"); });

		        }
			
	        },
	        deleteConfirm: "정말 삭제하시겠습니까?",
	        fields: [ 
                { name: 'rowNumber', title:"번호", key: true, width: "3%", align:"center"},
                { name: 'userSeCd'     , title:"대상"    , type: "select", items: FAQ_CL_TARGET, valueField: "Id", textField: "Name", width: "5%" },
                { name: 'clNm'    , title:"분류명" , type: "text", width: "20%" },
                { name: 'clid'     , title:"분류코드", type: "text", width: "5%" },
                { name: 'useYn'   , title:"사용여부", width: "5%" },
                { name: 'ord'      , title:"순서", type: "number", width: "5%", align: "center" , sorting: false},
                { name: 'ord'      , title:"정렬", type: "number", width: "5%", align: "center"
                  	 ,itemTemplate: function(value, item) {
                  		 
	                   	    var firstObj = $("<a class='btn btn-xs btn-info' />").text("▲").on("click", function() { fn_changeOrd("OU",item);  return false;}).add("<span>&nbsp;</span>");
	                   	    var lastObj = $("<a class='btn btn-xs btn-info' />").text("▼").on("click", function() { fn_changeOrd("OD",item);  return false;});
		                   	    
			                   	if(item.totalCount==1 ){
		             	        	return "-" ;
		             	        }else if(item.rowNumber==1){
		                   	    	return firstObj;
		                   	    }else if(item.rowNumber==item.totalCount){
		                   	    	return lastObj
		                   	    }else{
		                   	    	return firstObj.add(lastObj);
		                   	    }
				         }
                }
	        ],
	        rowClick: function(args) {
	        	
	        	$("#regForm").show();
	        	$("#modifytFaqClBtn").val("수정");
	        	$("#clid").val(args.item.clid);
	        	$("#clNm").val(args.item.clNm);
	        	$('input:radio[name=userSeCd]:input[value=' +args.item.userSeCd+ ']').prop("checked", true);
	        	$("#ord").val(args.item.ord);
	        	$('input:radio[name=useYn]:input[value=' +args.item.useYn+ ']').prop("checked", true);
	        }
	        
	});
});

function openRegForm(){
	$("#regForm").show();
	fn_clearFaqClForm();
}
function hideRegForm(){
	$("#regForm").hide();
	fn_clearFaqClForm();
}



function fn_clearFaqClForm(){
	$("#modifytFaqClBtn").val("저장");
	$("#clid").val("");
	$("#clNm").val("");
	$('input:radio[name=userSeCd]:input[value="101102"]').prop("checked", true);
	$("#ord").val("");
	$('input:radio[name=useYn]:input[value="N"]').prop("checked", true);
}




function fn_modifyFaqCl(){
	
	var params = {
			 clid  : $("#clid").val()
			,clNm : $("#clNm").val()
        	,userSeCd  : $('input:radio[name=userSeCd]:checked').val()
        	,ord   : $("#ord").val()
        	,useYn: $('input:radio[name=useYn]:checked').val()
	}
    var clid = $("#clid").val();
	
	var validator= $("#faqClForm").validate({
	    rules   : { clNm : { required: true } },
	    messages: {clNm : 	{ required: "분류명을 입력하십시오" } }
	 })
	
	if(($("#faqClForm").valid() == false)) return;
	
	if(clid!=null && clid !='' && clid !='0' ){
		FAQ_CL.update(params, function(){alert("수정되었습니다."); return false; });
	}else{
		FAQ_CL.insert(params, function(){alert("저장되었습니다."); return false; });
	}
	fn_clearFaqClForm();
	resetListContent();
	$("#accordion").hide();
}

function fn_changeOrd(mode,item){
	var params = {
			"clid": item.clid,
			"ord": item.ord,
			"mode": mode	
			}
	var updUrl = "/mng/faq/modifyFaqClOrd.do";
	$.ajax({
		url : updUrl,
		type: 'POST',
		cache : false,
		dataType: 'json',
		data : params,
		success : function (data){
			if(data){
				resetListContent();
			}else{
				alert("정렬이 실패하였습니다. 다시한번 시도해주십시오.");
			}
		},
	 	error : function (error){
		} 
	});
}

