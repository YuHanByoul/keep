/**
 * 
 */

	var menuTreeSearchListUrl = "/mng/menu/menuTreeSearchList.do";
	var selectPrgrmTreeListUrl = "/mng/prgrm/selectPrgrmTreeList.do";
	var getSiteListUrl = "/cmm/getSiteList.do";
	var getMenuInfoUrl = "/mng/menu/getMenuInfo.do";
	var saveMenuTreeListUrl = "/mng/menu/saveMenuTreeList.do";
	var menuTreeReorderUrl = "/mng/menu/menuTreeReorder.do";
	var menuRegistInfoUrl = "/mng/menu/menuRegistInfo.do";
	var menuDeleteInfoUrl = "/mng/menu/menuDeleteInfo.do";
	var menuHompageScreenInfoUrl = "/mng/menu/menuHompageScreenInfo.do";
	var selectBbsMasterListUrl = "/mng/bbs/selectBbsMasterList.do";
	var refMenuIdPopupUrl = "/mng/menu/refMenuIdPopup.html";
	var menuTree = null;
	var menuLayout = null;
	var prgmTree = null;
	var innerLayout = null;
	var bbsList = null;
	var isFlag = true;
	var isSave = true;
	
	// Contextmenu helper
	function bindContextMenu(span) {

	  $(span).contextMenu({menu: "menuContext"}, function(action, el, pos) {
	    var node = $.ui.dynatree.getNode(el);
	    switch(action) {
	    case "folderRegist":
	    	if($("#siteid").val() == ""){
				alert("사이트를 선택해 주십시오.");
				return;
			}
			$("#menuid").val(node.data.key);
			if(isFlag && isSave){
				fn_InitMenuForm('C');
			}else if(!isFlag ){
				alert('프로그램 Pool이 열린 상태에서는 메뉴구조를 변경할 수 없습니다.');
				return;
			}else if(!isSave ){
				alert('먼저 메뉴구조를 저장해야 합니다.');
				return;
			}
	    	break;
	    case "delete":
	    	
	    	if(!isFlag){
	    		alert('프로그램 Pool이 열린 상태에서는 메뉴구조를 변경할 수 없습니다.');
				return;
			}
			if(!isSave){
				alert('먼저 메뉴구조를 저장해야 합니다.');
				return;
			}
			$("#menuid").val(node.data.key);			
			$("#uppr_menuid").val(node.parent.data.key);
			if($("#menuid").val() == "" || $("#menuid").val() == "0"){
				alert("삭제할 메뉴를 선택해 주십시오.");
				return;
			}			
			if(!confirm("삭제 할경우 하위 메뉴까지 삭제가 진행됩니다. 삭제 하시겠습니까?")){
				return
			}
			fn_menuDelete();
			break;
	    case "reflash":
	    	if(menuTree != null){
				$("#tree").dynatree("destroy");
			}
	    	fn_menuTreeList();
			break;
	    case "allExpend":
	    	menuTree.dynatree("getRoot").visit(function(node){
	   	    	node.expand(true);
	   	    });	   	    
	    	break;
	    case "allClose":
	    	menuTree.dynatree("getRoot").visit(function(node){
	    		node.expand(false);
	   	    });	  
	    	break;
	    default:
	    }
	  });
	};
	$(document).ready(function(){
		fn_getSiteList();
		fn_menuLayout();
		fn_prgmTreeList();
		$("input[name='popup_yn']").each(function(key,val){
			$(this).bind('click',function(){
				if(key == 1){					
					$("input[name='popup_wd']").val('');
					$("input[name='popup_hg']").val('');
					$("input[name='popup_wd']").attr('disabled',true);
					$("input[name='popup_hg']").attr('disabled',true);
				}else{					
					$("input[name='popup_wd']").attr('disabled',false);
					$("input[name='popup_hg']").attr('disabled',false);
				}
			});
		});
		
		
		
		$("#doEdit").bind('click',function(){
			if($("#siteid").val() == ""){
				alert("사이트명을 선택하십시오.");
				return;
			}
			
			if($("#menuid").val() == "" && $("#mode").val() == "U"){
				alert("메뉴를 선택해야 합니다.");
				return;
			}
			
			if(isFlag && isSave){
				fn_menuEdit();
			}else if(!isFlag){
				alert('프로그램 Pool이 열린 상태에서는 메뉴구조를 변경할 수 없습니다.');
				return;
			}else if(!isSave){
				alert('먼저 메뉴구조를 저장해야 합니다.');
				return;
			}
		});	
		
		$("#doCancel").bind('click', function(){
			fn_InitMenuForm('C');
		});
		
		$("#menuSave").bind('click', function(){
			
			if($("#siteid").val() == ""){
				alert("사이트를 선택해 주십시오.");
				return;
			}	

			if(isFlag){
				menuLayout.open("west");
				$("#menuSave").html("메뉴구성 저장후 닫기");
				$("#menuCancel").show();
				$("#layout_menuInfo").hide();
				$("#layout_menuCompose").show();
				$("#tabhide").hide();
			}else{	
				fn_treeSave();
				isSave = true;
				menuLayout.close("west");
				$("#menuSave").html("메뉴구성");
				$("#menuCancel").hide();
				$("#layout_menuInfo").show();
				$("#tabhide").show();
			}		
		});
		
		$("#menuDelete").bind('click', function(){
			
			if(!isFlag){
				alert('프로그램 Pool이 열린 상태에서는 메뉴구조를 변경할 수 없습니다.');
				return;
			}
			
			if(!isSave){
				alert('먼저 메뉴구조를 저장해야 합니다.');
				return;
			}
			
			if($("#menuid").val() == ""){
				alert("삭제할 메뉴를 선택해 주십시오.");
				return;
			}
			
			if(!confirm("삭제 할경우 하위 메뉴까지 삭제가 진행됩니다. 삭제 하시겠습니까?")){
				return
			}
			
			fn_menuDelete();
		})
		
		// 메뉴구성 취소
		$("#menuCancel").bind("click", function(){
			isSave = true;			
			menuLayout.close( "west" );
			$("#menuSave").html("메뉴구성");
			$("#menuCancel").hide();
			$("#layout_menuInfo").show();
			$("#layout_menuCompose").hide();	
			$("#tabhide").show();
			if(menuTree != null){
				$("#tree").dynatree("destroy");
			}
			fn_menuTreeList();
		});		
	});
	
	function fn_menuLayout(){
		menuLayout=$('#menuLayout').layout({
				slidable: true
			,	closable: true
			,	resizable: true
			,east: {
				initClosed:	true
				,	togglerClass: "menuViewClose"
				,	size: $("#menuLayout").width()
				,   spacing_closed: 0
				,   spacing_open: 0
				,	minSize: '40%'
				,	maxSize: .99 // 50% of layout width
				, 	onopen : function(){
					resizeJqGridWidthNoSearch("bbsList", "bbsDiv", "100%"); //그리드 리사이즈
				}
			}
			,west: {
				initClosed:	true
				,	togglerClass: "menuView"
				,	size: $("#menuLayout").width()
				,	minSize: '40%'
				,	maxSize: .99 // 50% of layout width	
				,   spacing_closed: 0
				,   spacing_open: 0
				, 	onopen : function(){
					isFlag = false;
					isSave = false;
				}
				, 	onclose : function(){
					isFlag = true;
				}
				
			} 
			,stateManagement__enabled:	false	
			});
		
		$(".ui-layout-resizer-east").unbind("click");
		$(".ui-layout-resizer-east").unbind("dblclick");
		$(".ui-layout-resizer-east .menuViewClose").unbind("click");
	}
	
	function fn_menuTreeList(){
		menuTree = $("#tree").dynatree({
	    	autoFocus: true,
	    	minExpandLevel: 2,
	    	initAjax: {
				url: menuTreeSearchListUrl,
				dataType:'json',
				data: {	
					siteid: $("#siteid").val(),
					site_nm: $("#siteid option:selected").text(),
					srch_url: $("#srch_url").val()
	            }
			},
	    	onActivate: function(node) {
	    		if(isFlag && isSave){
	  	  			$("#menuid").val(node.data.key);
	  	  			fn_getMenuInfo();
	    		} 
	    		node.expand(true);
	    	},
	    	onClick: function(node, event) {
	    	},
	    	onCreate: function(node, span){
	    		$(span).attr('id','menu_'+node.data.key);
	    		bindContextMenu(span);
	    		if($("#menuid").val() != ""){
	    			$("#tree").dynatree("getTree").activateKey($("#menuid").val());
	    		}else if($("#uppr_menuid").val() != "0"){
	    			$("#tree").dynatree("getTree").activateKey($("#uppr_menuid").val());
	    		}
	    		
	    	},
	    	dnd: {
	    	      autoExpandMS: 1000,
	    	      preventVoidMoves: true,
	    	      onDragStart: function(node) {
	                	logMsg("tree.onDragStart(%o)", node);	                	   
	                  	return true;
	              },
	    	      onDragEnter: function(node, sourceNode) {
	    	        logMsg("tree.onDragEnter(%o, %o)", node, sourceNode);	    	        
	    	        return true;
	    	      },
	    	      onDragOver: function(node, sourceNode, hitMode) {
	    	       	logMsg("tree.onDragOver(%o, %o, %o)", node, sourceNode, hitMode);
	    	      },
	    	      onDrop: function(node, sourceNode, hitMode, ui, draggable) {
	    	        var isSelf = false;
	    	        isSelf = (node.tree==sourceNode.tree);
	    	      	       
	    	        logMsg("tree.onDrop(%o, %o)", node, sourceNode);
	    	      	if(!isSelf){ //다른 트리에서 이동시
		    	        var copynode;
		    	        if(sourceNode) {	    	        	
		    	          	copynode = sourceNode.toDict(true, function(dict){
		    	            dict.key = "_" + dict.key;
		    	          });
		    	        }else{
		    	          copynode = {title: "This node was dropped here (" + ui.helper + ")."};
		    	        }		    	        
		    	        if(hitMode == "over"){
		    	          // Append as child node
		    	          node.addChild(copynode);
		    	          // expand the drop target
		    	          node.expand(true);
		    	       	  // 디렉토리로 변경해주고 다시 그려준다.
	                      node.data.isFolder = true;
	                      node.tree.redraw();
		    	        }else if(hitMode == "before"){
		    	          node.parent.addChild(copynode, node);
		    	        }else if(hitMode == "after"){
		    	          if(node.parent.data.key == "_1"){
		    	        	  node.addChild(copynode, node.getNextSibling());  
		    	          }else{
		    	        	  node.parent.addChild(copynode, node.getNextSibling());  
		    	          };
		    	        }
	    	      	}else{ //같은 트리에서 순서 이동시 실시간 변경
	    	      		if(isFlag && isSave){
	    	      			sourceNode.move(node, hitMode);
	    	      			fn_menu_reOrder(node, sourceNode,hitMode);
	    	      			 if(hitMode == "over"){
	    	      				node.data.isFolder = true;
	  	                      	node.tree.redraw();
	    	      			 }
	    	      		}
	    	      	}
	    	      },
	    	      onDragLeave: function(node, sourceNode) {
	    	        logMsg("tree.onDragLeave(%o, %o)", node, sourceNode);
	    	      }
	    	    }
	  	});  
	}
	
	function fn_prgmTreeList(){
		
		prgmTree = $("#prgmTree").dynatree({
	    	autoFocus: true,
	    	cache: false,
	    	minExpandLevel: 2,
	    	initAjax: {
				url: selectPrgrmTreeListUrl,
				type: 'post',
				dataType:'json'
			},
	    	onActivate: function(node) {
	    	},
	    	onClick: function(node, event) {
	      	
	    	},
	    	onCreate: function(node, span) {
	    		$(span).attr('id','prgm_'+node.data.key);
	    		$(span).draggable({
        			helper:	function() {
        				var $helper = $(this).clone().appendTo('body').css('zIndex',5).show();
	        			$helper.data("dtSourceNode", node);
        				return $helper; 
        			}
        		,	cursor:	'move'
        		,	revert: function(dropped){
        		      if(typeof dropped === "boolean") {
        		        return !dropped;
        		      }
        		      
        		      var helper = $.ui.ddmanager && $.ui.ddmanager.current && $.ui.ddmanager.current.helper;
        		      var isRejected = helper && helper.hasClass("dynatree-drop-reject");
        		      return isRejected;
        		      },
        		    connectToDynatree: true,
        		    cursorAt: { top: -5, left:-5 }
        		});
	    	},
	    	dnd: {
	    		revert: false,
            	onDragStart: function(node) {
                	logMsg("tree.onDragStart(%o)", node);                	         	
                  	return true;
                },
                onDragStop: function(node) {
                	logMsg("tree.onDragStop(%o)", node);
                }
            }
	  	});  
	}
	
	function fn_getSiteList(){
		$.ajax({
			url : getSiteListUrl,
			type: 'POST',
			cache : false,
			dataType: 'json',
			success : function (data){
				selectbox_insertlist2("siteid", data, "site_nm", "siteid");
			},
			error : function (error){
				
			}
			
		});
	}
	
	function fn_getMenuInfo(){
		displayWorkProgress();
		$.ajax({
			url : getMenuInfoUrl,
			cache : false,
			dataType: 'json',
			data : {
				menuid :  $("#menuid").val()
			},
			success : function (data){
				$("#mode").val('U');
				fn_setMenuForm(data);
			},
			error : function (error){
			}
			
		});
		closeWorkProgress(); 
	}
	
	function fn_treeSave(){
		$("#layout_menuCompose").hide();
		displayWorkProgress();
		var saveTreeData = [];
		$("#tree").dynatree("getRoot").visit(function(node) {
			var order   = 1;
            var preNode = node;
            while ((preNode=preNode.getPrevSibling()) != null) {
                order++;
            }

			if((node.data.key).substr(0,1) =="_"){
				saveTreeData.push({
					key:node.data.key,
					parent_key:node.parent.data.key,
					title:node.data.title,
					order:order
				});
			}
		});				
		$.ajax({
			url : saveMenuTreeListUrl,
			cache : false,
			type : 'POST',
			dataType : 'json',
			data : {
				siteid:  $("#siteid").val(),
				saveData: saveTreeData,
				datalen: saveTreeData.length
			},
			success : function (data){
				alert(data.msg);
				if(menuTree != null){
					$("#tree").dynatree("destroy");
				}
				fn_menuTreeList();
				closeWorkProgress(); 
			},
			error : function(err){
			}
		})
	}
	
	function fn_menu_reOrder(node, sourceNode, hitMode){
		var uprMenuId = sourceNode.parent.data.key;
		var menuArr  = new Array();
		var child = node.parent.getChildren();
		if(hitMode == 'over'){
			uprMenuId = node.data.key;
			child = node.getChildren();
		}
		if(child){
			for(var i=0; i < child.length; i++){
				menuArr.push(child[i].data.key);
			}
		}else{
			menuArr.push(sourceNode.data.key);
		}

		$.ajax({
			url : menuTreeReorderUrl,
			cache:false,
			dataType : 'json',
			data : {
				menuArr : new String(menuArr),
				uppr_menuid : uprMenuId,	
				menuid : sourceNode.data.key,
				hitMode : hitMode,
				siteid :  $("#siteid").val()
			},
			success:function(data){
				
			}
		});	
		
	}
	function validate(f){
		//$(f).validate();
		return true;
		
	}
	function fn_menuEdit(){
		var f= document.form1;
		
		if(!validate2(f)){
			return;
		}
		
		if(getRadioValue(f,"popup_yn") == "Y"){
			if($("input[name='popup_wd']").val() == "" || $("input[name='popup_hg']").val() == ""){
				alert("팝업크기를 지정해야 합니다.");
				return;
			}
			if(!isInteger($("input[name='popup_wd']").val()) ){
				alert("팝업크기는 정수이어야 합니다.");
				return;
			}else{
                if($("input[name='popup_wd']").val() > 1024) {
                    alert("팝업의 가로크기는 1024이하여야 합니다.")
                    return;
                }
            }
            
			if(!isInteger($("input[name='popup_hg']").val()) ){
				alert("팝업크기는 정수이어야 합니다.");
				return;
			}else{
                if($("input[name='popup_hg']").val() > 768) {
                    alert("팝업의 세로크기는 768이하여야 합니다.")
                    return;
                }
            }
		}
		
		var msg = "";
		if($("#mode").val() != "U"){
			msg = "저장하시겠습니까?";
		}else{
			msg = "수정하시겠습니까?";
		}
		if(!confirm(msg)){
			return
		}
		
		displayWorkProgress();
		$.ajax({
			url : menuRegistInfoUrl,
			type: 'post',
			cache : false,
			dataType: 'json',
			data : $("#form1").serialize(),
			success : function(data){
				alert(data.msg);
				if(menuTree != null){
					$("#tree").dynatree("destroy");
				}
				fn_menuTreeList();
				closeWorkProgress(); 
			}				
		});
	}
	
	function fn_menuDelete(){
		displayWorkProgress();
		$.ajax({
			url : menuDeleteInfoUrl,
			type:'post',
			cache : false,
			dataType:'json',
			data : {
				siteid :  $("#siteid").val(),
				menuid :  $("#menuid").val(),
				uppr_menuid : $("#uppr_menuid").val()
			},
			success : function(data){
				alert(data.msg);
				if(menuTree != null){
					$("#tree").dynatree("destroy");
					fn_InitMenuForm('D');
				}
				fn_menuTreeList();
				closeWorkProgress(); 
			}
		})
	}
	
	function fn_setMenuForm(data){
		
		$("#uppr_menuid").val(data.uppr_menuid);
		if(data.menuid != undefined){
			if(data.prgrmid == undefined){
				data.prgrmid = "없음";
			}
			$("#view_menuid").html(data.menuid+"("+data.prgrmid+")");
		}else{
			$("#view_menuid").html("");
		}
		$("#menu_nm").val(data.nm);
		$("#menu_expln").val(data.dc);
		
		if(data.type_cd == 'D'){		//디렉토리
			$("#urlView").show();
			$("#new_menutype").hide();
			$("#menutype").show();
			$("#view_ref_menuid").show();
			$("#ref_menuid").html(data.ref_menuid);
			$("input[name='ref_menuid']").val(data.ref_menuid);
			$("#view_prgmurl").hide();
			$("#view_prgrm_type_cd").hide();
			$("input[name='popup_yn']").eq(1).trigger('click');
			$("input[name='popup_yn']").attr('disabled',true);
		}else {
			$("#view_ref_menuid").hide();
			$("#ref_menuid").html('');
			$("input[name='ref_menuid']").val('');
			$("#mode").val('U');			
			$("#urlView").show();
			$("#new_menutype").hide();
			$("#menutype").show();
			$("#view_prgmurl").show();
			$("#view_prgrm_type_cd").show();
			$("input[name='popup_yn']").attr('disabled',false);
			$("input[name='popup_yn']:radio:input[value='"+data.popup_yn+"']").prop("checked", true);
			$("input[name='popup_wd']").val(data.popup_wd);
			$("input[name='popup_hg']").val(data.popup_hg);
		}
		
		if(data.popup_yn == "N"){
			$("input[name='popup_wd']").attr('disabled',true);
			$("input[name='popup_hg']").attr('disabled',true);
		}else{
			$("input[name='popup_wd']").attr('disabled',false);
			$("input[name='popup_hg']").attr('disabled',false);
		}
		
		if(data.url != "" && data.url != ""){
			$("#url").val(data.url);
		}else{
			$("#url").val("");
		}
		
		$("input[name='https_use_yn']:radio:input[value='"+data.https_use_yn+"']").prop("checked", true);
		$("input[name='type_cd']").attr("disabled", true);
		$("input[name='type_cd']:radio:input[value='"+data.type_cd+"']").prop("checked", true).attr("disabled", false);
				
			
		$("input[name='popup_trgt_cd']:radio:input[value='"+data.popup_trgt_cd+"']").prop("checked", true);
		$("input[name='nm_expsr_trgt_cd']:radio:input[value='"+data.nm_expsr_trgt_cd+"']").prop("checked", true);
		$("input[name='login_yn']:radio:input[value='"+data.login_yn+"']").prop("checked", true);		
		$("input[name='hide_yn']:radio:input[value='"+data.hide_yn+"']").prop("checked", true);
		$("input[name='gst_yn']:radio:input[value='"+data.gst_yn+"']").prop("checked", true);
		var prgrm_type = ""
		if(data.ptype_cd == "01"){
			prgrm_type = "디렉토리";
			$("input[name='popup_yn']").eq(1).trigger('click');
			$("input[name='popup_yn']").attr('disabled',true);
		}else if(data.ptype_cd == "02"){
			prgrm_type = "메뉴";
		}else if(data.ptype_cd == "03"){
			prgrm_type = "메뉴상세";
			$("input[name='popup_yn']").eq(1).trigger('click');
            $("input[name='popup_yn']").attr('disabled',true);
		}else if(data.ptype_cd == "04"){
			prgrm_type = "기능";
			$("input[name='popup_yn']").eq(1).trigger('click');
			$("input[name='popup_yn']").attr('disabled',true);
		}else if(data.ptype_cd == "05"){
			prgrm_type = "팝업";
            $("input[name='popup_yn']").eq(1).trigger('click');
            $("input[name='popup_yn']").attr('disabled',true);
		}
		
		$("#prgrm_type_cd").html(prgrm_type);
		$("#prgmurl").html(data.purl);
	}
	
	function fn_InitMenuForm(gubun){
        // gubun C:디렉토리등록, D:삭제
		$("#mode").val(gubun);
		if(gubun =='C' && $("#menuid").val() != ""){
			$("#uppr_menuid").val($("#menuid").val());
		}else{
			if(gubun != 'D'){
				$("#uppr_menuid").val(0);
			}
		}
		$("#menuid").val('');		
		$("#view_menuid").html('');
		$("#menu_nm").val('');
		$("#menu_expln").val('');
		$("#url").val('');
		if(gubun =='W'){
			$("#urlView").show();
		}else{
			$("#urlView").hide();
		}
		
		$("#view_ref_menuid").hide();
		$("#ref_menuid").html('');
		$("inpput[name='ref_menuid']").val('');
		$("input[name='popup_yn']").attr('disabled',false);
		
		if(gubun =='C'){ //디렉토리등록
			$("#view_ref_menuid").show();
			$("input[name='type_cd']").attr('disabled',true);
			$("input[name='type_cd']:radio:input[value='D']").prop("checked", true).attr("disabled",false);			
			$("#new_menutype").hide();		
			$("#menutype").show();
		}else{ //초기화
			$("input[name='type_cd']").attr('disabled',false);
			$("input[name='type_cd']:radio:input[value='P']").prop("checked", true);
			$("#new_menutype").hide();
			$("#menutype").show();
		}		
		$("input[name='login_yn']:radio:input[value='Y']").prop("checked", true);
		$("input[name='hide_yn']:radio:input[value='N']").prop("checked", true);
		$("input[name='https_use_yn']:radio:input[value='Y']").prop("checked", true);
		$("input[name='popup_yn']:radio:input[value='Y']").prop("checked", true);
		
		$("input[name='popup_wd']").val('');
		$("input[name='popup_hg']").val('');
		if(gubun =='C'){
			$("input[name='popup_yn']").eq(1).trigger('click');
			$("input[name='popup_yn']").attr('disabled',true);			
		}else{
			$("input[name='popup_yn']").eq(0).trigger('click');			
		}
		
		$("input[name='popup_trgt_cd']:radio:input[value='P']").prop("checked", true);
		$("#view_prgmurl").hide();
		$("#view_prgrm_type_cd").hide();
	}
	
	function goSearch(){
		if( $("#layout_menuCompose").css("display") != "none" ){
			$("#menuCancel").click();
		}
		
		if($("#siteid").val() == ""){
			alert("사이트명을 선택하십시오.");
			return;
		}
		if(menuTree != null){
			$("#tree").dynatree("destroy");
		}
		$("#srch_url").val($.trim($("#srch_url").val()));
		fn_menuTreeList();
	}
	
	function fn_jsRefMenuPopup(){
		var f= document.form1;
		if($("#mode").val() != "U"){
			alert("참조메뉴는 메뉴를 등록후 지정할 수 있습니다.");
			return;
		}
		Center_Fixed_Popup2(refMenuIdPopupUrl+"?siteid="+f.siteid.value+"&menuid="+f.menuid.value, "refMenuIdPopup", 500, 700, "yes");	
	}
    
    function fn_jsRefMenuCancel() {
        $("#ref_menuid").html('');
        $("input[name='ref_menuid']").val('');
    }
	