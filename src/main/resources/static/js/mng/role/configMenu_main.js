var menuLayout = null;
var isFlag = true;

var treeUrl = "/mng/roleauth/roleMgntTreeList.do";
var menuUrl = "/mng/roleauth/roleMgntMenuTreeList.do";
var saveUrl = "/mng/roleauth/saveMenuRole.do";
var siteUrl = "/mng/roleauth/selectRoleSiteList.do";
var copyUrl = "/mng/roleauth/roleMenuMappCopyPopup.do";

jQuery(document).ready(function(){
	
	jQuery("#menuTreeArea").hide();
	
	makeTree();
	
	// 저장
	jQuery("#save").bind("click",function(){
		fn_save();
	});
	
});

jQuery(function(){
	jQuery("#subTree").dynatree({	    	
    	autoFocus: true,
    	cache: false,
    	minExpandLevel : 4,	// 트리 4뎁스까지 펼치기
    	checkbox: true,
    	selectMode: 2,
    	onSelect: function(select, dtnode) {
   		    setTreeCheck(true,dtnode,select);
   		},
    	initAjax: {
			dataType:'json',
			data: {
				roleid : jQuery("#roleid").val()
            }
		},
    	onActivate: function(node) {
    	}
  	});
});

function makeTree(){
	
	jQuery("#baseTree").dynatree({	    	
    	autoFocus: true,
    	cache: false,
    	minExpandLevel : 2,	// 트리 2뎁스까지 펼치기
    	generateIds : true,
    	initAjax: {
    		url: treeUrl,
			dataType:'json',
			data: {	
				seCd : jQuery("#seCd").val(),
				//mkey:'${mkey}'
            },
            complete : function(data) {
            	//메뉴구성 창의 기본 높이를 역할구조와 맞추기 위해.
        		$('#menuBody').css("min-height", $('#roleBody').outerHeight() + "px");
            }
		},
    	onActivate: function(node) {
    		if(node.data.key !="0" && node.data.key != undefined){    		
	    		jQuery("#roleNm").text("역할 : " + node.data.title);	// 역할
                jQuery("#save").show();
	      		jQuery("#roleid").val(node.data.key);
	      		
                jQuery("#siteid").html("");
	   			jQuery("#subTree").hide();
	   			treeReload();
	   			
	   			jQuery("#layoutMenuCompose #txtDesc").hide();
	   			jQuery("#layoutMenuCompose #menuTreeArea").show();
                makeSitecd();
    		}else{
        		jQuery("#roleNm").text("");	// 역할
                jQuery("#save").hide();
        		
	   			jQuery("#layoutMenuCompose #txtDesc").show();
	   			jQuery("#layoutMenuCompose #menuTreeArea").hide();
				
      		}      		
    	},
    	onClick: function(node, event) {
    	},
    	onCreate: function(node, span){
    		// 선택하기
    		if(jQuery("#roleid").val() != ""){
    			jQuery("#baseTree").dynatree("getTree").activateKey(jQuery("#roleid").val());
    		}
    	}
  	});
}

function treeReload(){
	console.log("treeReload");
	if(jQuery("#roleid").val() == "" || jQuery("#roleid").val() == "0" || jQuery("#siteid").val() == null || jQuery("#siteid").val() == "" ){
        if(jQuery("#siteid").val() == ""){
            jQuery("#subTree").hide();
        }
		return;	//  역활과 사이트코드가 하나라도 빈값이면 실행안함
	}
	
	classrmNm = jQuery("#siteid option:checked").text();
	siteId = jQuery("#siteid").val();
	
	jQuery("#subTree").show();
	
	jQuery("#subTree").dynatree("option","initAjax",
			{url: menuUrl,
			dataType:'json',
			data: {
				siteid: siteId,
				classrm_nm: classrmNm,
				roleid : jQuery("#roleid").val(),
				//mkey:'${mkey}'
	        }
			});
	jQuery("#subTree").dynatree("getTree").reload();
}

function setTreeCheck(isfirst,node, select){
	if(node){
		var child = node.getChildren();
		if(child){
			for(var i= 0 ;i < child.length ; i++){				
				child[i].select(select);
				
				//alert(child[i].data.title);
				setTreeCheck(false,child[i], select);
			}
		}		
	}
}

//저장
function fn_save(){
	
	if(jQuery("#roleid").val() == "" || jQuery("#roleid").val() == "0"){
		alert("선택된 역할이 없습니다.");	// 선택된 역할이 없습니다.
		return;
	}
    
    if(jQuery("#siteid").val() == ""){
        alert("사이트를 선택해주십시오."); // 선택된 역할이 없습니다.
        return;
    }
	
	if(!confirm("저장하시겠습니까?")){	// 저장하시겠습니까?
		return
	}
	
	var arr_menu_key = new Array();
	var arr_menu_obj = new Object();
	var arr_menu_add = new Array();
	var arr_menu_del = new Array();
	
	var nodeList = [];		// 체크된 노드  배열
	var node2List = [];		// 체크안된 노드 배열
	
	//초기값 저장
	jQuery("#subTree").dynatree("getRoot").visit(function(node){
		arr_menu_obj[node.data.key] = node.data.select;
		arr_menu_key.push(node.data.key);
    });
	
	var selectnode = jQuery("#subTree").dynatree("getSelectedNodes");
	jQuery(selectnode).each(function (i) {
		nodeList.push(selectnode[i].data.key);
	});	

	// 체크 안된 배열구하기
	jQuery(arr_menu_key).each(function(i) {
		
		var flag = 0;
		for(var j=0; j<nodeList.length; j++){
			if(arr_menu_key[i] == nodeList[j]){
				flag = 1;
				break;
			}
		}
		
		if(flag == 0)
			node2List.push(arr_menu_key[i]);
		
	});
	
	// 체크한것중 변경된 값 (홈 node추가안함)
	jQuery(nodeList).each(function(i) {
		if(!arr_menu_obj[nodeList[i]] && nodeList[i] != 0){
			arr_menu_add.push(nodeList[i]);
		}
	});
	
	// 체크안한것중 변경된 값 (홈 node추가안함)
	jQuery(node2List).each(function(i) {
		if(arr_menu_obj[node2List[i]] && node2List[i] != 0){
			arr_menu_del.push(node2List[i]);
		}
	});
	
	// 중복제거
	var addArray = [];
	jQuery.each(arr_menu_add, function(i, el){
		if(jQuery.inArray(el, addArray) === -1)   
			addArray.push(el);
	});
	
	// 중복제거
	var delArray = [];
	jQuery.each(arr_menu_del, function(i, el){
		if(jQuery.inArray(el, delArray) === -1)   
			delArray.push(el);
	});
	
	jQuery("#menuAdd").val(addArray);
	jQuery("#menuDel").val(delArray);
	
	if(jQuery("#menuAdd").val() == "" && jQuery("#menuDel").val() == ""){
    	alert("변경된 데이터가 없습니다.");	// 변경된 데이터가 없습니다.
    	return;
	}
		
	jQuery.ajax({
		url : saveUrl,
		cache : false,
		dataType: 'json',
		type : 'POST',
		data : $('#configMenuForm').serialize(),
		success : function(data){
			alert(data.msg);
			treeReload();
		}
	});

}

function makeSitecd(){
	jQuery("#siteid").html("");
	jQuery.ajax({
		url : siteUrl,
		cache : false,
		dataType: 'json',
		data : {
            seCd : jQuery("#seCd").val()
		},
		success : function(result){
			if(result.data != undefined && result.data != ""){								
				createSitecdTag(result.data);
			}else{
				jQuery("#subTree").hide();
			}
		}
	});
}

function createSitecdTag(data){
	var tag ="";	
	if(data.length > 1){
		tag += "<option value=''>- 선택 -</option>";		
		jQuery("#siteid").show();
	}else{		
		jQuery("#siteid").hide();
	}
	jQuery.each(data,function(key,obj){
		tag += "<option value='"+obj.siteid+"'>"+obj.siteNm+"</option>";
	});
	jQuery("#siteid").html(tag);
	
	if(data.length == 1){
		treeReload();
	}
}

// 메뉴권한 복사
function fn_mnuauthcpy(){

	if(jQuery("#roleid").val() == "" || jQuery("#roleid").val() == 0){
		alert("선택된 역할이 없습니다.");	// 선택된 역할이 없습니다.
		return;
	}
	
	if(jQuery("#siteid").val() == "" || jQuery("#siteid").val() == null){
		alert("선택된 사이트가 없습니다.");	// 선택된 사이트가 없습니다.
		return;
	}

	var f= document.configMenuForm;
	Center_Fixed_Popup2("", "roleMenuMappCopyPopup", 400, 300, "no");
	f.target = "roleMenuMappCopyPopup";
	f.action = copyUrl;
	f.submit();
	f.target = "";
}

//탭 이동
function goTab(url){
	var f= document.configMenuForm;
	
	f.action = url;
	f.submit();
}
