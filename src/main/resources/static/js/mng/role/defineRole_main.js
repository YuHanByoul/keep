var treeUrl = "/mng/roleauth/roleMgntTreeList.do";
//var orderUrl = "/mng/roleauth/roleReorder.do";
var detailUrl = "/mng/roleauth/roleDetailInfo.do";
var saveUrl = "/mng/roleauth/roleRegistInfo.do";
var tab1Url = "/mng/roleauth/roleMgntUserMappingForm.do";

// --- Contextmenu helper --------------------------------------------------
function bindContextMenu(span) {
  // Add context menu to this node:
  jQuery(span).contextMenu({menu: "roleContext"}, function(action, el, pos) {
    // The event was bound to the <span> tag, but the node object
    // is stored in the parent <li> tag
    var node = jQuery.ui.dynatree.getNode(el);
    switch( action ) {
    case "regist":
    	jQuery("#upprRoleid").val(node.data.key);
    	jQuery("#roleid").val("");
    	jQuery("#mode").val("W2");
    	fn_setRegistForm();
    	jQuery("#registForm").show();
    	break;    
    default:
    	jQuery("#upprRoleid").val("0");
    	jQuery("#roleid").val("");
    	jQuery("#mode").val("W");
    	fn_setRegistForm();
    }
  });
};
jQuery(document).ready(function(){
	
	jQuery("#defineRoleform").submit(function(){
		if(!confirm("저장하시겠습니까?")){		// 저장하시겠습니까?
			return
		}
		
		fn_roleRegist();
		
		return false;
	});
	
	//수정
	jQuery("#doEdit").bind("click",function(){
		if(!confirm("수정하시겠습니까?")){	// 수정하시겠습니까?
			return
		}
		fn_roleRegist();
	});
	
	jQuery("#doCancle").bind("click",function(){
		jQuery("#upprRoleid").val("0");
    	jQuery("#roleid").val("");
    	jQuery("#mode").val("W");
    	fn_setRegistForm();
	});
	
	jQuery("#doWrite").bind("click",function(){
		if(!confirm("저장하시겠습니까?")){		// 저장하시겠습니까?
			return
		}
		fn_roleRegist();
	});
	jQuery("#roleWirteForm").bind("click",function(){
		jQuery("#upprRoleid").val("0");
    	jQuery("#roleid").val("");
    	jQuery("#mode").val("W");
    	fn_setRegistForm(); 
		jQuery("#registForm").show();
	});
	

	fn_roleTreeList();
	
    jQuery("#defineRoleform").validate({
        rules: {
             nm         :   { required: true ,isBlank : true, maxlength: 60 }  
            ,dc         :   { maxlength: 400 }
        },
        messages: {
             nm         :   { required: "역할명을 입력해주십시오." , isBlank : "역할명에 공백이 있습니다.", maxlength: "역할명은 60자 이하여야 합니다." }  
            ,dc         :   { maxlength: "역할설명은 400자 이하여야 합니다."   }
        }
    });
});

function fn_roleTreeList(){
	
	jQuery("#tree").dynatree({
    	autoFocus: true,
    	cache: false,
    	minExpandLevel: 2,
    	generateIds : true,
    	initAjax: {
			url: treeUrl,
			dataType:'json',
			data: {	
				seCd : $("input[name='seCd']:checked").val(),
				//mkey:'${mkey}'
            }
		},
    	onActivate: function(node) {   
    		if(node.data.key !="0" && node.data.key != undefined){
    			jQuery("#mode").val("E");
    			fn_getRoleInfo(node.data.key);
    			jQuery("#registForm").show();
    		}else{
    			jQuery("#upprRoleid").val("0");
    	    	jQuery("#roleid").val("");
    			jQuery("#mode").val("W");
    			fn_setRegistForm();    			
    		}
  	  		
    	},
    	onClick: function(node, event) {
      		// Close menu on click
      		if( jQuery(".contextMenu:visible").length > 0 ){
      			jQuery(".contextMenu").hide();
			// return false;
      		}
    	},
    	
    	onCreate: function(node, span){
    		if(node.data.key!="0" ){
      			bindContextMenu(span);
    		}
    		
    		// 선택하기
    		if(jQuery("#roleid").val() != ""){
    			jQuery("#tree").dynatree("getTree").activateKey(jQuery("#roleid").val());
    		}else if(jQuery("#p_upr_menu_id").val() != "0"){
    			jQuery("#tree").dynatree("getTree").activateKey(jQuery("#upprRoleid").val());
    		}
    	},
    	/*dnd: {
    		preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
    		onDragStart: function(node) {
      			return true;
    		},
    		onDragEnter: function(node, sourceNode) {
      		if(node.parent !== sourceNode.parent)
       			return false;
   				return ["before", "after"];
    		},
    		onDrop: function(node, sourceNode, hitMode, ui, draggable) {
      			sourceNode.move(node, hitMode);  
      			//alert(node.data.title);
      			fn_role_reOrder(node, sourceNode,hitMode);
    		}
 
    	}*/
  	});  
}

function fn_role_reOrder(node, sourceNode,hitMode){
	var uprRoleId = sourceNode.parent.data.key;
	var roleArr  = new Array();
	var child = node.parent.getChildren();
	
	if(child){
		for(var i=0; i< child.length;i++){
			roleArr.push(child[i].data.key);
		}
	}else{
		roleArr.push(sourceNode.data.key);
	}	
	
	jQuery.ajax({
		url : orderUrl,
		cache:false,
		dataType : 'json',
		data : {
			p_roleArr : new String(roleArr),
			roleid : sourceNode.data.key,
			uprRoleid : uprRoleId,	
			hitMode : hitMode,
			roleClsfCd : jQuery("#roleClsfCd").val(),
			mkey:'${mkey}'
		},
		success:function(data){
			
		}
		
	});
		
	
}

function fn_getRoleInfo(roleId){
	//console.log("fn_getRoleInfo : " + roleId);
	jQuery.ajax({
		url : detailUrl,
		cache : false,
		dataType: 'json',
		data : {
			roleid : roleId,
			//mkey:'${mkey}'
		},
		success : function(data){
			//console.log(data);
			fn_setRegistForm(data);
		
		}
	});
}

function fn_setRegistForm(data){
	if(data != undefined){
		jQuery("input[name='roleid']").val(data.ROLEID);
		jQuery("#viewRoleId").val(data.ROLEID);
		jQuery("#roleNm").html("역할 : "+data.NM);		// 역할
		jQuery("#viewRoleNm").val(data.NM);
		jQuery("#viewRoleExpln").val(data.DC);
		jQuery("input:radio[name=kndCd]:input[value='"+data.KND_CD+"']").prop("checked", true);
		jQuery("input:radio[name=trgtInstCd]:input[value='"+data.TRGT_INST_CD+"']").prop("checked", true);
		jQuery("input:radio[name=trgtRgnCd]:input[value='"+data.TRGT_RGN_CD+"']").prop("checked", true);
		if(data.USE_YN == "Y"){
		    toggleSwitch("#useYn", true);
		}else{
            toggleSwitch("#useYn", false);
        }
		
		jQuery("#editBtn").show();
		jQuery("#writeBtn").hide();
		
	}else{
		jQuery("#viewRoleId").val('');
		jQuery("#roleNm").html("");
		jQuery("#viewRoleNm").val('');
		jQuery("#viewRoleExpln").val('');
		jQuery("input[name='kndCd']:radio:input[value='A']").prop("checked", true);
		jQuery("input[name='trgtInstCd']:radio:input[value='A']").prop("checked", true);
		jQuery("input[name='trgtRgnCd']:radio:input[value='A']").prop("checked", true);
		toggleSwitch("#useYn", true);
		
		jQuery("#editBtn").hide();
		jQuery("#writeBtn").show();
	}
}

function fn_roleRegist(){
	var f = document.defineRoleform;
	if(!(jQuery("#defineRoleform").valid())) return;
    
    if(displayWorkProgress()){
    	jQuery.ajax({
    		url : saveUrl,
    		type: "POST",
    		cache : false,
    		dataType: 'json',
    		data : jQuery("#defineRoleform").serialize(),
    		success : function(data){
    			closeWorkProgress();
    			alert(data.msg);
    			loadTabContent('#defineRoleContent', '/mng/roleauth/defineRole.html?seCd='+_ROLE_LEVEL);
    		}
    	});
    }
}

//탭 이동
function goTab(url){
	var f= document.defineRoleform;
	
	f.action = url;
	f.submit();
}