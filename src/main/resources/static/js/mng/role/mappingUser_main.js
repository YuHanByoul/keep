$.getScript( "/js/mng/role/ROLE.js" );

/** jsGrid의 회원분류 값. */
var userSe = [
    { Name: "개인회원", Id: "P" },
    { Name: "기업회원", Id: "C" }
];

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

function init(){
	initJsGridCustom();
	
	fn_roleTreeList();
	
	makeGrid();
	
	jQuery("#goPopup").bind("click",function(){		
		if(jQuery("#roleid").val()  == '0' || jQuery("#roleid").val()  == ''){
			alert("역할을 선택해 주십시오.");
			return;
		}
		var f= document.mappingUserForm;
		Center_Fixed_Popup2("", "roleUserMappPopup", 700, 500, "yes");
		f.target = "roleUserMappPopup";
		f.action = popupUrl;
		f.submit();
		f.target = "_self";
	});
	
	//검색 form submit
	$('#searchForm').submit(function(){
		$("#gridList").jsGrid("reset");
		return false;
	});
	
}

function initJsGridCustom(){

	var MyDateField = function(config) {
	    jsGrid.Field.call(this, config);
	};
	 
	MyDateField.prototype = new jsGrid.Field({
	 
	    css: "date-field",            // redefine general property 'css'
	    align: "center",              // redefine general property 'align'

	    sorter: function(date1, date2) {
	        return new Date(date1) - new Date(date2);
	    },
	 
	    itemTemplate: function(value) {
	    	var date = new Date(value);
	    	return date.toISOString().substring(0, 10);
	    },
	 
	    insertTemplate: function(value) {
	        return this._insertPicker = $("<input>").datepicker({ defaultDate: new Date(), dateFormat:'yy-mm-dd',
	        	dayNamesMin: [  "일", "월", "화", "수", "목", "금", "토" ], 
				monthNamesShort : [ '1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월' ]});
	    },
	 
	    editTemplate: function(value) {
	        return this._editPicker = $("<input>").datepicker({dateFormat:'yy-mm-dd',
	        	dayNamesMin: [  "일", "월", "화", "수", "목", "금", "토" ], 
				monthNamesShort : [ '1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월' ]}).datepicker("setDate", new Date(value));
	    },
	 
	    insertValue: function() {
	        return this._insertPicker.datepicker("getDate").toISOString();
	    },
	 
	    editValue: function() {
	    	var date = new Date(this._editPicker.datepicker("getDate"));
	    	date.setMinutes(date.getMinutes() - date.getTimezoneOffset());
	    	return date.toISOString().substring(0, 10);
	    }
	});
	 
	jsGrid.fields.date = MyDateField;	
		
}

function fn_roleTreeList(){
	console.log("fn_roleTreeList");
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
        pageFirstText: "< 처음 ㅣ",
        pageLastText: "ㅣ마지막 >",
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
            	console.log(filter);
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
	            console.log(item);
	            ROLE.update(item, function(data){
	    			if(data.result == 'success'){
	    				alert("저장하였습니다.");		// 저장하였습니다.
	    			}else{
	    				alert("저장에 실패하였습니다.");		// 저장에 실패하였습니다.
	    			}
	            	
	            });
	        },

	        deleteItem: function(item) {
	            console.log(item);
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
            { name: 'nm', title:"이름(기업명)", type: "text", width: "15%", readOnly: true },
            { name: 'acnt', title:"아이디", type: "text", width: "10%", readOnly: true },
            { name: 'roleNm', title:"역할", type: "text", width: "10%", align: "center", readOnly: true },
            { name: 'userSeCd', title:"회원구분", type: "select", width: "10%", items: userSe, valueField: "Id", textField: "Name", readOnly: true },
            { name: 'roleStrtDt', title:"사용시작일", type: "date", width: "10%", align: "center" },
            { name: 'roleEndDt', title:"사용종료일", type: "date", width: "10%", align: "center" },
            { type: "control", width: "7%" }
        ],
        //rowClick: function(args) { readModify(args.item.faqid); },
        
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
		item.roleStrtDt = startDate;
		item.roleEndDt = endDate;
		
		return item;
	});
	
	//console.log(list);
	
	jQuery.ajax({
		url : "/mng/roleauth/insertRoleUserList.do",
		contentType: 'application/json',
		dataType:'json',
		method: 'post',
		data : JSON.stringify(list),
		success : function(data){
			console.log(data);
			
			//리로드
			$("#gridList").jsGrid("reset");
			
		}
	});
	
}


