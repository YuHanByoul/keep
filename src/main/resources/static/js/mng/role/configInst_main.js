
var treeUrl = "/mng/roleauth/roleMgntTreeList.do";
var detailUrl = "/mng/roleauth/selectRoleInstInfo.do";
var saveUrl = "/mng/roleauth/saveInstRole.do";

jQuery(document).ready(function(){
	
	makeTree();
	
	// 저장
	jQuery("#save").bind("click",function(){
		fn_save();
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
        		//$('#menuBody').css("height", $('#roleBody').outerHeight() + "px");
            }
		},
    	onActivate: function(node) {
    		if(node.data.key !="0" && node.data.key != undefined){    		
                jQuery("#save").show();
	      		jQuery("#roleid").val(node.data.key);
                
                if(node.data.ext1 == "A"){
                    jQuery("#instids").html("");
                    $('.searchable').multiSelect('refresh');
                    jQuery(".ms-selection input").attr("disabled",true);
                    jQuery(".ms-selectable input").attr("disabled",true);
                    jQuery("#save").addClass("disabled");
                    jQuery(".ms-selection ul").append('<li class="ms-elem-selection disabled"><span>전체기관</span></li>');
                }else if(node.data.ext1 == "S"){
                    jQuery("#instids").html("");
                    $('.searchable').multiSelect('refresh');
                    jQuery(".ms-selection input").attr("disabled",true);
                    jQuery(".ms-selectable input").attr("disabled",true);
                    jQuery("#save").addClass("disabled");
                    jQuery(".ms-selection ul").append('<li class="ms-elem-selection disabled"><span>소속기관</span></li>');
                }else if(node.data.ext1 == "C"){
                    jQuery("#instids").html("");
                    // 배정기관과 전체기관 정보를 불러옴
                    jQuery.ajax({
                        url : detailUrl,
                        cache : false,
                        dataType: 'json',
                        async: false,
                        type : 'POST',
                        data : {
                          roleid : node.data.key  
                        },
                        success : function(data){
                            var findIndex;
                            var instNm;
                            for(var i = 0; i < data.allInstList.length; i++){
                                findIndex = data.roleInstList.findIndex(item => item.INSTID == data.allInstList[i].INSTID);
                                instNm = data.allInstList[i].INST_NM;
                                if (data.allInstList[i].INST_CD != null) {
                                    instNm = instNm + "(" + data.allInstList[i].INST_CD + ")";
                                }
                                jQuery("#instids").append("<option value='" + data.allInstList[i].INSTID + "' " + (findIndex > -1 ? "selected":"") + ">" + instNm + "</option>");
                            }
                        }
                    });
                    
                    $('.searchable').multiSelect('refresh');
                    jQuery("#save").removeClass("disabled");
                }
	   			
	   			jQuery("#layoutMenuCompose #txtDesc").hide();
	   			jQuery("#layoutMenuCompose #ms-instids").show();
    		}else{
                jQuery("#save").hide();
        		
	   			jQuery("#layoutMenuCompose #txtDesc").show();
	   			jQuery("#layoutMenuCompose #ms-instids").hide();
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

//저장
function fn_save(){
	
	if(jQuery("#roleid").val() == "" || jQuery("#roleid").val() == "0"){
		alert("선택된 역할이 없습니다.");	// 선택된 역할이 없습니다.
		return;
	}
		
	if(displayWorkProgress()){
    	jQuery.ajax({
    		url : saveUrl,
    		cache : false,
    		dataType: 'json',
    		type : 'POST',
    		data : $('#configInstForm').serialize(),
    		success : function(data){
    			closeWorkProgress();
    			alert(data.msg);
    		}
    	});
    }
}

$('.searchable').multiSelect({
    selectableHeader: "<input type='text' class='form-control' autocomplete='off' placeholder='검색어 입력'>",
    selectionHeader: "<input type='text' class='form-control' autocomplete='off' placeholder='검색어 입력'>",
    afterInit: function(ms) {
        var that = this,
            $selectableSearch = that.$selectableUl.prev(),
            $selectionSearch = that.$selectionUl.prev(),
            selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
            selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

        that.$selectionContainer.children().first().after("<div class=\"custom-header\">배정기관</div>");
        that.$selectableContainer.children().first().after("<div class=\"custom-header\">전체기관</div>");
        jQuery("#ms-instids").hide();
        
        that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
            .on('keydown', function(e) {
                if (e.which === 40) {
                    that.$selectableUl.focus();
                    return false;
                }
            });

        that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
            .on('keydown', function(e) {
                if (e.which == 40) {
                    that.$selectionUl.focus();
                    return false;
                }
            });
    },
    afterSelect: function() {
        this.qs1.cache();
        this.qs2.cache();
    },
    afterDeselect: function() {
        this.qs1.cache();
        this.qs2.cache();
    }
});
