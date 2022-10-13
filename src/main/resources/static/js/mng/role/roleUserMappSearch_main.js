/**
 * /js/mng/role/roleUserMappSearch.js
 * 
 * 권한 사용자 추가를 위한 스크립트.
 * 
 */

/** 체크박스로 선택된 항목 저장 */
var selectedItems = [];

/** jsGrid의 회원분류 값. */
var userSe = [
    { Name: "개인회원", Id: "P" },
    { Name: "기업회원", Id: "C" }
];

/**
 * 로딩 후 처리.
 * 
 * @returns
 */
$(function(){
	init();
    
    //검색 form submit
    $('#searchForm').submit(function(){
        $("#jsGrid").jsGrid("reset");        
        return false;
    });
    
    //사용자 추가 버튼 처리
    $('#addUserForm').submit(addUser);

});

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
		selectedItems =	$("#jsGrid").jsGrid('option', 'data');
		$($('.jsgrid-grid-body .jsgrid-table tbody tr td:nth-child(1) input')).prop('checked', true);
	}
}

var init = function(){
	var curDate = new Date().toISOString();
	$('#roleStartDate').val(curDate.substring(0,10));	//오늘 날짜를 default로 세팅한다.
	
    //jsGrid Init
    $("#jsGrid").jsGrid({
            height: "auto",
            width: "100%",
            autoload: true,            
            sorting: false,
            paging: true,
            selecting: true,
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
                            "roleid": jQuery("#roleid").val(),
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
                    width: "5%"
                },
/*                 { name: 'no', title:"No.", type: "text", width: 30 }, */
                { name: 'nm', title:"이름", type: "text", width: "20%" },
                { name: 'acnt', title:"아이디", type: "text", width: "20%", align: "center" },
                { name: 'userSeCd', title:"회원구분", type: "select", items: userSe, valueField: "Id", textField: "Name", width: "10%" }
            ],
            
    });	
}

var addUser = function(){
	var startDate = $('#roleStartDate').val();
	var endDate = $('#roleEndDate').val();
	
	if (startDate == '') {
		alert("사용기간을 선택해 주십시오.");
		$('#roleStartDate').focus();
		return false;
	} else if (endDate == '') {
        alert("사용기간을 선택해 주십시오.");
        $('#roleEndDate').focus();
        return false;
    }

	//if (parseInt(startDate.replace(/-/g, "")) > parseInt(endDate.replace(/-/g, ""))) {
	if (startDate > endDate) {
		alert("권한 사용기간 종료일자는 시작일보다 같거나 커야 합니다.");
		$('#roleEndDate').focus();
		return false;
	}
	
	if(selectedItems.length == 0){
		alert("사용자를 선택해 주십시오.");
		return false;    		
	}
	
	if(confirm("사용자를 추가 하시겠습니까?")){
		opener.addUserList(selectedItems, startDate, endDate);
		self.close();
		
		return true;
	}else{
		return false;
	}
	
}


function fn_addUsers(){
	var f =document.form1;
	jQuery("#refMenuid", opener.document).html(val);
	jQuery("input[name='refMenuid']", opener.document).val(val);
	window.close();
	
}