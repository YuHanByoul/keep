/**
 * /js/mng/role/roleUserMappSearch.js
 * 
 * 권한 사용자 추가를 위한 스크립트.
 * 
 */

/** 체크박스로 선택된 항목 저장 */
var selectedItems = [];

/**
 * 로딩 후 처리.
 * 
 * @returns
 */
$(function(){
    $("#userAddModal").off("show.bs.modal").on("show.bs.modal", initRoleUserMappSearch);
});

function initRoleUserMappSearch() {
	initSearch();
    
    //검색 form submit
    $('#searchPopupForm').submit(function(){
        $("#jsGridPopup").jsGrid("reset");        
        return false;
    });
    
    //저장 버튼 처리
    $('#addUserForm').submit(addUser);
    
    jQuery("#addUserForm").validate({
        onsubmit: false,
        rules: {
             roleStartDate : { required: true }  
            ,roleEndDate   : { required: true }
        },
        messages: {
             roleStartDate : { required: "권한 시작일을 선택해 주십시오." }  
            ,roleEndDate   : { required: "권한 종료일을 선택해 주십시오." }
        }
    });
}

var selectItem = function(item) {
    selectedItems.push(item);
};

var unselectItem = function(item) {
    selectedItems = $.grep(selectedItems, function(i) {
        return i !== item;
    });
};

var selectToggle = function(){
	if ($('.jsgrid-grid-header th:nth-child(1) input').is(":checked") === false) {
		selectedItems = [];
		$($('.jsgrid-grid-body .jsgrid-table tbody tr td:nth-child(1) input')).prop('checked', false);
	} else {
		selectedItems =	$("#jsGridPopup").jsGrid('option', 'data');
		$($('.jsgrid-grid-body .jsgrid-table tbody tr td:nth-child(1) input')).prop('checked', true);
	}
}

var initSearch = function(){
	var curDate = new Date().toISOString();
	$('#roleStartDate').val(curDate.substring(0,10));	//오늘 날짜를 default로 세팅한다.
	
    //jsGrid Init
    $("#jsGridPopup").jsGrid({
            height: "auto",
            width: "100%",
            autoload: true,            
            sorting: false,
            paging: true,
            selecting: true,
            pagerContainer: "#popupListPager",
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
                            "roleid": jQuery("#roleid").val(),
                            "searchType": $('#searchType', "#searchPopupForm").val(),
                            "searchKeyword": $('#searchKeyword', "#searchPopupForm").val(),
                            "pageNumber": filter.pageIndex,
                            "rowPerPage": filter.pageSize,
                            "orderField": filter.sortField,
                            "orderDirection": filter.sortOrder
                    }
                    //console.log(filter);
                    $.ajax({
                      type: "GET",
                      contentType: "application/json; charset=utf-8",
                      url: "/mng/roleauth/selectUserList.do",
                      data: params,
                      dataType: "json"
                    }).done(function(response){
                        var da = {
                                data : escapeGridData(response.list),
                                itemsCount : response.totalCount
                            };

                      data.resolve(da);
                    });
                    
                    return data.promise();
                },
            
            },
            
            fields: [ 
            	{
                    headerTemplate: function() {
                        return $("<input>").attr("type", "checkbox")
                                .on("click", function () {
                                    selectToggle();
                                });
                    },
                    itemTemplate: function(_, item) {
                        return $("<input>").attr("type", "checkbox").attr("value", item.userid)
                                .prop("checked", $.inArray(item, selectedItems) > -1)
                                .on("change", function () {
                                    $(this).is(":checked") ? selectItem(item) : unselectItem(item);
                                });
                    },
                    align: "center",
                    width: "50"
                },
                { name: 'nm', title:"이름(기관)", type: "text", width: "200", itemTemplate: function(value, item) {
                        if(item.instNm == null){
                            return item.nm;
                        }else{
                            return item.nm + "(" + item.instNm + ")";
                        }
                    }
                },
                { name: 'acnt', title:"아이디", type: "text", width: "200", align: "center" },
                { name: 'moblphon', title:"휴대폰", type: "text", width: "200", align: "center" },
                { name: 'eml', title:"이메일", type: "text", width: "200", align: "center" }
            ],
            
    });	
}

function initSearchPopup() {
    $("#searchType", "#searchPopupForm").val('');
    $("#searchKeyword", "#searchPopupForm").val('');   
}

var addUser = function(){
	var startDate = $('#roleStartDate').val();
	var endDate = $('#roleEndDate').val();
	
    if(!jQuery("#addUserForm").valid()){
      return false;
    }

	//if (parseInt(startDate.replace(/-/g, "")) > parseInt(endDate.replace(/-/g, ""))) {
	if (startDate > endDate) {
		alert("권한 종료일은 시작일보다 같거나 커야 합니다.");
		$('#roleEndDate').focus();
		return false;
	}
	
	if(selectedItems.length == 0){
		alert("사용자를 선택해 주십시오.");
		return false;    		
	}
	
	if(confirm("사용자를 추가 하시겠습니까?")){
		addUserList(selectedItems, startDate, endDate);
		self.close();
		
		return true;
	}else{
		return false;
	}
	
}
