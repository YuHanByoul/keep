/**
 * 
 */

$.getScript( "/js/mng/faq/FAQ.js" );

var listToggle = true;
var contentToggle = false;




$(function(){
	$('#contentPanel').hide();
	
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
	        pageFirstText: " < 처음 ㅣ",
	        pageLastText: " | 마지막 >",
	        
	        pageSize: 10,
	        pageButtonCount: 10,
	        pageLoading: true,
	        pageIndex: 1,
	        noDataContent: "데이터가 없습니다.",
	        
	        controller: {
	            loadData: function (filter) {
	            	var data = $.Deferred();
	            	
	            	var params = {
	            			"clid": $('#sclid').val(),
	            			"useYn": $('#useYn').val(),
	            			"searchType": $('#searchType').val(),
	            			"searchKeyword": $('#searchKeyword').val(),
	            			"pageNumber": filter.pageIndex,
	            			"rowPerPage": filter.pageSize,
	            			"orderField": filter.sortField,
	            			"orderDirection": filter.sortOrder,
	            			"userSeCd" :  $('input[name=userSeCd]:checked').val(),
	            			
	            	}
	            	
	                $.ajax({
	                  type: "GET",
	                  contentType: "application/json; charset=utf-8",
	                  url: "/mng/faq/faqList.do",
	                  data: params,
	                  dataType: "json"
	                }).done(function(response){
	                	var da = {
                                data :response.list,
                                itemsCount : response.totalCount
                            };
	                  data.resolve(da); 
	                });
	                return data.promise();
	            },
	        },
	        deleteConfirm: "정말 삭제하시겠습니까?",
	        fields: [ 
	        	{ name: 'rowNumber',title:'번호'   , type: "number" , width: "3%", align:"center", sorting: false },
                { name: 'clNm' , title:"FAQ 분류", type: "text"   , width: "8%"  },
                { name: 'title' , title:"제목"    , type: "text"   , width: "20%" },
                { name: 'regDt', title:"등록일"   , type: "text"   , width: "5%"  },
                { name: 'useYn', title:"사용여부",  width: "5%" },
                { name: 'ord', title:"순서", type: "number", width: "5%", align: "center" , sorting: false},
                { name: 'ord', title:"정렬", type: "number", width: "5%", align: "center"
                  	 ,itemTemplate: function(value, item) {
                  		 
	                   	    var firstObj = $("<a class='btn btn-xs btn-info' />").text("▲").on("click", function() { fn_changeOrd("OU",item);  return false;}).add("<span>&nbsp;</span>");
	                   	    var lastObj = $("<a class='btn btn-xs btn-info' />").text("▼").on("click", function() { fn_changeOrd("OD",item);  return false;});
		                   	    
		                   	    if(item.totalCount==1 ){
	                	        	return "-" ;
	                	        }else if(item.rowNumber==1){
		                   	    	return firstObj;
		                   	    }else if(item.rowNumber==item.totalCount){
		                   	    	return lastObj;
		                   	    }else{
		                   	    	return firstObj.add(lastObj);
		                   	    }
				         }
                }
	        ],
	        rowClick: function(args) { readModify(args.item.faqid); },
	        
	});
         
	//검색 form submit
	$('#searchFrm').submit(function(){
		$("#jsGrid").jsGrid("reset");
		showList();
		return false;
	});

});


function regFaq(){
	$('#contentPanel').load("/mng/faq/reg.html"); 
	showContent(); 
	hideList();
}

function readModify(faqid){
	$('#contentPanel').load("/mng/faq/read.html?faqid=" + faqid); 
	showContent(); 
	hideList();
}

function toggleListContent(){
	$("#jsGrid").jsGrid("reset");
	toggleContent(); 
	toggleList();	
}



function fn_changeOrd(mode,item){
	var params = {
			"faqid": item.faqid,
			"clid": item.clid,
			"ord": item.ord,
			"mode": mode
	}
	var updUrl = "/mng/faq/modifyFaqOrd.do";
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

