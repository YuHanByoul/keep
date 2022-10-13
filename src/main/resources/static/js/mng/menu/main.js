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
			$("#upprMenuid").val(node.parent.data.key);
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
		$("input[name='popupYn']").each(function(key,val){
			$(this).bind('click',function(){
				if(key == 1){					
					$("input[name='popupWd']").val('');
					$("input[name='popupHg']").val('');
					$("input[name='popupWd']").attr('disabled',true);
					$("input[name='popupHg']").attr('disabled',true);
				}else{					
					$("input[name='popupWd']").attr('disabled',false);
					$("input[name='popupHg']").attr('disabled',false);
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
				$("#layoutMenuInfo").hide();
				$("#layoutMenuCompose").show();
				$("#tabhide").hide();
			}else{	
				fn_treeSave();
				isSave = true;
				menuLayout.close("west");
				$("#menuSave").html("메뉴구성");
				$("#menuCancel").hide();
				$("#layoutMenuInfo").show();
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
			$("#layoutMenuInfo").show();
			$("#layoutMenuCompose").hide();	
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
					siteNm: $("#siteid option:selected").text(),
					srchUrl: $("#srchUrl").val()
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
	    		}else if($("#upprMenuid").val() != "0"){
	    			$("#tree").dynatree("getTree").activateKey($("#upprMenuid").val());
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
				selectbox_insertlist2("siteid", data, "siteNm", "siteid");
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
		$("#layoutMenuCompose").hide();
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
				upprMenuid : uprMenuId,	
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
		
		if(getRadioValue(f,"popupYn") == "Y"){
			if($("input[name='popupWd']").val() == "" || $("input[name='popupHg']").val() == ""){
				alert("팝업크기를 지정해야 합니다.");
				return;
			}
			if(!isInteger($("input[name='popupWd']").val()) ){
				alert("팝업크기는 정수이어야 합니다.");
				return;
			}else{
                if($("input[name='popupWd']").val() > 1024) {
                    alert("팝업의 가로크기는 1024이하여야 합니다.")
                    return;
                }
            }
            
			if(!isInteger($("input[name='popupHg']").val()) ){
				alert("팝업크기는 정수이어야 합니다.");
				return;
			}else{
                if($("input[name='popupHg']").val() > 768) {
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
				upprMenuid : $("#upprMenuid").val()
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
		
		$("#upprMenuid").val(data.upprMenuid);
		if(data.menuid != undefined){
			if(data.prgrmid == undefined){
				data.prgrmid = "없음";
			}
			$("#viewMenuid").html(data.menuid+"("+data.prgrmid+")");
		}else{
			$("#viewMenuid").html("");
		}
		$("#menuNm").val(data.nm);
		$("#menuExpln").val(data.dc);
		
		if(data.typeCd == 'D'){		//디렉토리
			$("#urlView").show();
			$("#newMenutype").hide();
			$("#menutype").show();
			$("#viewRefMenuid").show();
			$("#refMenuid").html(data.refMenuid);
			$("input[name='refMenuid']").val(data.refMenuid);
			$("#viewPrgmurl").hide();
			$("#viewPrgrmTypeCd").hide();
			$("input[name='popupYn']").eq(1).trigger('click');
			$("input[name='popupYn']").attr('disabled',true);
		}else {
			$("#viewRefMenuid").hide();
			$("#refMenuid").html('');
			$("input[name='refMenuid']").val('');
			$("#mode").val('U');			
			$("#urlView").show();
			$("#newMenutype").hide();
			$("#menutype").show();
			$("#viewPrgmurl").show();
			$("#viewPrgrmTypeCd").show();
			$("input[name='popupYn']").attr('disabled',false);
			$("input[name='popupYn']:radio:input[value='"+data.popupYn+"']").prop("checked", true);
			$("input[name='popupWd']").val(data.popupWd);
			$("input[name='popupHg']").val(data.popupHg);
		}
		
		if(data.popupYn == "N"){
			$("input[name='popupWd']").attr('disabled',true);
			$("input[name='popupHg']").attr('disabled',true);
		}else{
			$("input[name='popupWd']").attr('disabled',false);
			$("input[name='popupHg']").attr('disabled',false);
		}
		
		if(data.url != "" && data.url != ""){
			$("#url").val(data.url);
		}else{
			$("#url").val("");
		}
		
		$("input[name='httpsUseYn']:radio:input[value='"+data.httpsUseYn+"']").prop("checked", true);
		$("input[name='typeCd']").attr("disabled", true);
		$("input[name='typeCd']:radio:input[value='"+data.typeCd+"']").prop("checked", true).attr("disabled", false);
				
			
		$("input[name='popupTrgtCd']:radio:input[value='"+data.popupTrgtCd+"']").prop("checked", true);
		$("input[name='nmExpsrTrgtCd']:radio:input[value='"+data.nmExpsrTrgtCd+"']").prop("checked", true);
		$("input[name='loginYn']:radio:input[value='"+data.loginYn+"']").prop("checked", true);		
		$("input[name='hideYn']:radio:input[value='"+data.hideYn+"']").prop("checked", true);
		$("input[name='gstYn']:radio:input[value='"+data.gstYn+"']").prop("checked", true);
		var prgrm_type = ""
		if(data.ptypeCd == "01"){
			prgrm_type = "디렉토리";
			$("input[name='popupYn']").eq(1).trigger('click');
			$("input[name='popupYn']").attr('disabled',true);
		}else if(data.ptypeCd == "02"){
			prgrm_type = "메뉴";
		}else if(data.ptypeCd == "03"){
			prgrm_type = "메뉴상세";
			$("input[name='popupYn']").eq(1).trigger('click');
            $("input[name='popupYn']").attr('disabled',true);
		}else if(data.ptypeCd == "04"){
			prgrm_type = "기능";
			$("input[name='popupYn']").eq(1).trigger('click');
			$("input[name='popupYn']").attr('disabled',true);
		}else if(data.ptypeCd == "05"){
			prgrm_type = "팝업";
            $("input[name='popupYn']").eq(1).trigger('click');
            $("input[name='popupYn']").attr('disabled',true);
		}
		
		$("#prgrmTypeCd").html(prgrm_type);
		$("#prgmurl").html(data.purl);
	}
	
	function fn_InitMenuForm(gubun){
        // gubun C:디렉토리등록, D:삭제
		$("#mode").val(gubun);
		if(gubun =='C' && $("#menuid").val() != ""){
			$("#upprMenuid").val($("#menuid").val());
		}else{
			if(gubun != 'D'){
				$("#upprMenuid").val(0);
			}
		}
		$("#menuid").val('');		
		$("#viewMenuid").html('');
		$("#menuNm").val('');
		$("#menuExpln").val('');
		$("#url").val('');
		if(gubun =='W'){
			$("#urlView").show();
		}else{
			$("#urlView").hide();
		}
		
		$("#viewRefMenuid").hide();
		$("#refMenuid").html('');
		$("inpput[name='refMenuid']").val('');
		$("input[name='popupYn']").attr('disabled',false);
		
		if(gubun =='C'){ //디렉토리등록
			$("#viewRefMenuid").show();
			$("input[name='typeCd']").attr('disabled',true);
			$("input[name='typeCd']:radio:input[value='D']").prop("checked", true).attr("disabled",false);			
			$("#newMenutype").hide();		
			$("#menutype").show();
		}else{ //초기화
			$("input[name='typeCd']").attr('disabled',false);
			$("input[name='typeCd']:radio:input[value='P']").prop("checked", true);
			$("#newMenutype").hide();
			$("#menutype").show();
		}		
		$("input[name='loginYn']:radio:input[value='Y']").prop("checked", true);
		$("input[name='hideYn']:radio:input[value='N']").prop("checked", true);
		$("input[name='httpsUseYn']:radio:input[value='Y']").prop("checked", true);
		$("input[name='popupYn']:radio:input[value='Y']").prop("checked", true);
		
		$("input[name='popupWd']").val('');
		$("input[name='popupHg']").val('');
		if(gubun =='C'){
			$("input[name='popupYn']").eq(1).trigger('click');
			$("input[name='popupYn']").attr('disabled',true);			
		}else{
			$("input[name='popupYn']").eq(0).trigger('click');			
		}
		
		$("input[name='popupTrgtCd']:radio:input[value='P']").prop("checked", true);
		$("#viewPrgmurl").hide();
		$("#viewPrgrmTypeCd").hide();
	}
	
	function goSearch(){
		if( $("#layoutMenuCompose").css("display") != "none" ){
			$("#menuCancel").click();
		}
		
		if($("#siteid").val() == ""){
			alert("사이트명을 선택하십시오.");
			return;
		}
		if(menuTree != null){
			$("#tree").dynatree("destroy");
		}
		$("#srchUrl").val($.trim($("#srchUrl").val()));
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
        $("#refMenuid").html('');
        $("input[name='refMenuid']").val('');
    }
	