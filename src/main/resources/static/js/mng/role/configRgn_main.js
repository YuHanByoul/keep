
var treeUrl = "/mng/roleauth/roleMgntTreeList.do";
var detailUrl = "/mng/roleauth/selectRoleRgnInfo.do";
var saveUrl = "/mng/roleauth/saveRgnRole.do";

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
                
                if(node.data.ext2 == "A"){
                    jQuery("#rgncds").html("");
                    $('.searchable').multiSelect('refresh');
                    jQuery(".ms-selection input").attr("disabled",true);
                    jQuery(".ms-selectable input").attr("disabled",true);
                    jQuery("#save").addClass("disabled");
                    jQuery(".ms-selection ul").append('<li class="ms-elem-selection disabled"><span>사용안함</span></li>');
                }else if(node.data.ext2 == "S"){
                    jQuery("#rgncds").html("");
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
                            var option = "";
                            var ctprvnCd;
                            for(var i = 0; i < data.allRgnList.length; i++){
                                findIndex = data.roleRgnList.findIndex(item => item.RGN_CD == data.allRgnList[i].RGN_CD);
                                option += "<option value='" + data.allRgnList[i].RGN_CD + "' " + (findIndex > -1 ? "selected":"") + ">" + data.allRgnList[i].CD_NM + "</option>";
                            }
                            jQuery("#rgncds").append(option);
                        }
                    });
                    
                    $('.searchable').multiSelect('refresh');
                    jQuery("#save").removeClass("disabled");
                }
	   			
	   			jQuery("#layoutMenuCompose #txtDesc").hide();
	   			jQuery("#layoutMenuCompose #ms-rgncds").show();
    		}else{
                jQuery("#save").hide();
        		
	   			jQuery("#layoutMenuCompose #txtDesc").show();
	   			jQuery("#layoutMenuCompose #ms-rgncds").hide();
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
    		data : $('#configRgnForm').serialize(),
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

        that.$selectionContainer.children().first().after("<div class=\"custom-header\">배정지역</div>");
        that.$selectableContainer.children().first().after("<div class=\"custom-header\">전체지역</div>");
        jQuery("#ms-rgncds").hide();
        
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
