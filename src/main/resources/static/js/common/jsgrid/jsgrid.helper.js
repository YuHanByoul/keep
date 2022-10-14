var listToggle = true;
var contentToggle = false;

function loadContent(uri){ // 등록/수정 컨텐츠영역 로드시 사용(컨텐츠영역 노출, 그리드 숨김)
	$('#contentPanel').load(uri); 
	showContent(); 
	hideList();
}

function toggleListContent(){
	$("#jsGrid").jsGrid("reset");
	toggleContent(); 
	toggleList();	
}

function resetListContent(){ // 검색/저장/수정/삭제후 사용(컨텐츠영역 숨김, 그리드 노출)
	//리셋하고 현재 페이지로 돌아간다.
	var curPage = $("#jsGrid").jsGrid("option", "pageIndex");
	$("#jsGrid").jsGrid("reset").done(function(){
		$("#jsGrid").jsGrid("openPage", curPage);
	});
	hideContent(); 
	showList();	
}

function showList(){
	if(listToggle) return;
	listToggle = !listToggle;
	$('#collapseList').collapse('show');
}

function hideList(){
	if(!listToggle) return;
	listToggle = !listToggle;
	$('#collapseList').collapse('hide');
}

function showContent(){
	if(contentToggle) return;
	contentToggle = !contentToggle;
	$('#contentPanel').show();
}

function hideContent(){
	if(!contentToggle) return;
	contentToggle = !contentToggle;
	$('#contentPanel').hide();
}

function toggleList(){ // panel-heading 에서 클릭시 그리드를 접거나 펼때 사용
	listToggle ? $('#collapseList').collapse('hide') : $('#collapseList').collapse('show');	
	listToggle = !listToggle;
}

function toggleContent(){ // 컨텐츠영역에서 취소시 사용(컨텐츠 토글, 그리드 토글)
	contentToggle ? $('#contentPanel').hide() : $('#contentPanel').show();
	if(contentToggle && !listToggle) toggleList();
	contentToggle = !contentToggle;
}

function escapeGridData(data){ // 그리드에서 로드한 데이터를 escape 처리함(무조건 사용).
	return JSON.parse($('<div/>').text(JSON.stringify(data)).html());
}