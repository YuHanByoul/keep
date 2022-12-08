$.getScript( "/js/mng/role/ROLE.js" );

var orgnGrid = null;
var gridUrl = "/mng/roleauth/roleMgntUserMapped.do";
var treeUrl = "/mng/roleauth/roleMgntTreeList.do";
var popupUrl = "/mng/roleauth/roleUserMappPopup.html";
var detailUrl = "/mng/roleauth/roleDetailInfo.do";
var saveUrl = "/mng/roleauth/roleUserMappSave.do";
var batchUrl = "/mng/roleauth/roleTotalTermSave.do";

jQuery(document).ready(function(){
	init();
});

var userAddModal;
function init(){
	initJsGridCustom();
	
	fn_roleTreeList();
	
	makeGrid();

	jQuery("#goPopup").bind("click",function(){		
		if(jQuery("#roleid").val()  == '0' || jQuery("#roleid").val()  == ''){
			alert("트리에서 역할을 선택해 주십시오.");
			return;
		}
		
		$("#userAddModal").load(popupUrl, function(response, status, xhr) {
            if (status == "success") {
                userAddModal = new bootstrap.Modal($('#userAddModal'));
                userAddModal.show();
            }
        });
	});
	
	//검색 form submit
	$('#searchForm').submit(function(){
		$("#gridList").jsGrid("reset");
		return false;
	});
	
}

function initSearchForm() {
    jQuery("#searchType", "#searchForm").val("");
    jQuery("#searchKeyword", "#searchForm").val("");
}

function initJsGridCustom(){

	var MyDateField = function(config) {
	    jsGrid.Field.call(this, config);
	};
	 
	MyDateField.prototype = new jsGrid.Field({

	    sorter: function(date1, date2) {
	        return new Date(date1) - new Date(date2);
	    },
	 
	    itemTemplate: function(value) {
            try{
    	    	var date = new Date(value);
    	    	return date.toISOString().substring(0, 10);
    	    }catch(e){
                $("#gridList").jsGrid("reset");
            }
	    },
	 
	    insertTemplate: function(value) {
	        return this._insertPicker = $("<input type='date' value='" + new Date().toISOString().substring(0, 10) + "'>");
	    },
	 
	    editTemplate: function(value) {
	        return this._editPicker = $("<input type='date' value='" + value + "'>");
	    },
	 
	    insertValue: function() {
	        return this._insertPicker.val();
	    },
	 
	    editValue: function() {
	    	return this._editPicker.val();
	    }
	});
	 
	jsGrid.fields.date = MyDateField;	
		
}

function fn_roleTreeList(){
	//console.log("fn_roleTreeList");
	jQuery("#mappingUserTree").dynatree({
    	autoFocus: true,
    	cache: false,
    	minExpandLevel: 2,
    	generateIds : true,
    	initAjax: {
			url: treeUrl,
			dataType:'json',
			data: {	
				seCd : jQuery("#seCd").val(),
				//mkey:'${mkey}'
            }
		},
    	onActivate: function(node) {
    		if(node.data.key !="0" && node.data.key != undefined){    		
    			makeGrid(node.data.key);
    			jQuery("#roleid").val(node.data.key);
    			fn_getRoleInfo(node.data.key);
    		}else{
    			makeGrid();
    			jQuery("#roleid").val("");
    		}
  	  		
    	},
    	onClick: function(node, event) {
    	},
    	onCreate: function(node, span){
    		// 선택하기
    		if(jQuery("#roleid").val() != ""){
    			jQuery("#tree").dynatree("getTree").activateKey(jQuery("#roleid").val());
    		}
    	}
  	});  
}

function fn_getRoleInfo(roleId){
	jQuery.ajax({
		url : detailUrl,
		cache : false,
		dataType: 'json',
		data : {
			roleid : roleId,
			//mkey:'${mkey}'
		},
		success : function(data){
			fn_setRegistForm(data);
		
		}
	});
}

function fn_setRegistForm(data){
	if(data != undefined){		
		jQuery("#roleNm").html("역할 : "+data.NM);		// 역할
	}else{		
		jQuery("#roleNm").html("");
	}
}

function makeGrid(roleId) {

	$("#gridList").jsGrid({
        height: "auto",
        width: "100%",
        autoload: true,
//        filtering: true,
        inserting: false,
        editing: true,
        sorting: true,
        paging: true,
        pagerContainer: "#listPager",
        pagerFormat: "{first} {prev} {pages} {next} {last}",
        pagePrevText: "이전",
        pageNextText: "다음",
        pageFirstText: " < 처음 ㅣ",
        pageLastText: " | 마지막 >",
        noDataContent: "데이터가 없습니다.",
        
        pageSize: 10,
        pageButtonCount: 10,
        pageLoading: true,
        pageIndex: 1,
        
        controller: {
            loadData: function (filter) {
            	var data = $.Deferred();
            	
            	var params = {
            			"roleid": roleId,
            			"useYn": $('#useYn').val(),
            			"searchType": $('#searchType').val(),
            			"searchKeyword": $('#searchKeyword').val(),
            			"pageNumber": filter.pageIndex,
            			"rowPerPage": filter.pageSize,
            			"orderField": filter.sortField,
            			"orderDirection": filter.sortOrder
            	}
            	//console.log(filter);
                $.ajax({
                  type: "GET",
                  contentType: "application/json; charset=utf-8",
                  url: gridUrl,
                  data: params,
                  dataType: "json"
                }).done(function(response){
                	var da = {
                            data : escapeGridData(response.list),
                            itemsCount : response.totalCount
                        };
                   //console.log(da);
                  data.resolve(da);
                });
                
                return data.promise();
            },
	        updateItem: function(item) {
	            //console.log(item);
	            if (item.roleStrtDd == '') {
                    alert("권한 시작일을 선택해 주십시오.");
                    return false;
                } else if (item.roleEndDd == '') {
                    alert("권한 종료일을 선택해 주십시오.");
                    return false;
                }
            
                if (item.roleStrtDd > item.roleEndDd) {
                    alert("권한 종료일은 시작일보다 같거나 커야 합니다.");
                    return false;
                }
                    
	            ROLE.update(item, function(data){
	    			if(data.result == 'success'){
	    				alert("저장하였습니다.");		// 저장하였습니다.
	    			}else{
	    				alert("저장에 실패하였습니다.");		// 저장에 실패하였습니다.
	    			}
	            	
	            });
	        },

	        deleteItem: function(item) {
	            //console.log(item);
	            ROLE.delete(item, function(data){
	            	if(data.result == 'success'){
	    				alert("삭제하였습니다.");		// 저장하였습니다.
	    			}else{
	    				alert("삭제에 실패하였습니다.");		// 저장에 실패하였습니다.
	    			}
	            	
	            	$("#gridList").jsGrid("reset"); 
	            });

	        }
            
		
        },
        deleteConfirm: "정말 삭제하시겠습니까?",
        fields: [ 
            { name: 'nm', title:"이름(기관)", type: "text", width: "200", 
                itemTemplate: function (value, item) {
                    if(item.instNm == null){
                        return item.nm;
                    }else{
                        return item.nm + "(" + item.instNm + ")";
                    }
                }, 
                editTemplate: function (value, item) {
                    if(item.instNm == null){
                        return item.nm;
                    }else{
                        return item.nm + "(" + item.instNm + ")";
                    }
                },
                editValue: function (value, item) {
                    return value;
                }
            },
            { name: 'acnt', title:"아이디", type: "text", width: "150", 
                editTemplate: function (value, item) {
                    return value;
                },
                editValue: function (value, item) {
                    return value;
                }
            },
            { name: 'roleStrtDd', title:"권한 시작일", type: "date", width: "150", align: "center" },
            { name: 'roleEndDd', title:"권한 종료일", type: "date", width: "150", align: "center" },
            { type: "control", width: "70" }
        ],
        rowClick: function() {  },
        
});

}


function fn_reloadGrid(){
	jQuery("#gridList").jqGrid('setGridParam', {
		page: 1
	}).trigger("reloadGrid");
}

var addUserList = function(list, startDate, endDate){
	
	var roleid = $('#roleid').val();
	list.map(function(item){
		item.roleid = roleid;
		item.roleStrtDd = startDate;
		item.roleEndDd = endDate;
		
		return item;
	});
	
	//console.log(list);
	
	if(displayWorkProgress()){
    	jQuery.ajax({
    		url : "/mng/roleauth/insertRoleUserList.do",
    		contentType: 'application/json',
    		dataType:'json',
    		method: 'post',
    		data : JSON.stringify(list),
    		success : function(data){
    			//console.log(data);
    			
    			//리로드
    			$("#gridList").jsGrid("reset");
    			
    			userAddModal.hide();
    			closeWorkProgress();
    		}
    	});
    }
}


