var listToggle = true;
var contentToggle = false;

function loadContent(uri){
	$('#contentPanel').load(uri); 
	showContent(); 
	hideList();
}

function toggleListContent(){
	$("#jsGrid").jsGrid("reset");
	toggleContent(); 
	toggleList();	
}

function resetListContent(){
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

function toggleList(){
	listToggle ? $('#collapseList').collapse('hide') : $('#collapseList').collapse('show');	
	listToggle = !listToggle;
}

function toggleContent(){
	contentToggle ? $('#contentPanel').hide() : $('#contentPanel').show();
	if(contentToggle && !listToggle) toggleList();
	contentToggle = !contentToggle;
}

function escapeGridData(data){
	return JSON.parse($('<div/>').text(JSON.stringify(data)).html());
}