
function fn_chooseCommonPopup(list){
	if(list.length > 0){
		for( i= 0; i < list.length ; i++){
			if($.cookie("commonPop_cookie_"+list[i].popupntcid) != "Y") {  
				if(list[i].popup_type_cd =="P")fn_openCommonPopup(list[i]); //팝업
				else if(list[i].popup_type_cd =="L")fn_openCommonModal(list[i]); //레이어 모달
				else fn_openCommonLayerPopup(list[i]); // 딥 모달  ---> function name 주의 
			}
	  }
	}
}

function fn_openCommonPopup(item){
  var popupOption  ="scrollbars=yes,resizable=no,menubar=no , location=no" ;
      popupOption +=",left="+item.left_lc;
      popupOption +=",width="+item.width_size; 
      popupOption +=",height="+item.vrtcl_size; 
      popupOption +=",top="+item.top_lc;
  var getPopUrl= "/mng/pop/getPopup.html?popupntcid="+item.popupntcid;
  eval("objWin" + item.popupntcid + " = window.open(getPopUrl, 'objWin" + item.popupntcid+"', popupOption);");    
}
function getDataForCommnonPopup( siteid, menuid ){
	
		let data = {"siteid": siteid,"menuid": menuid};
		
		try{
			if(popMain){
				data = {"siteid": siteid,"expsrLcCd": "M"};
			}
		}catch(e){
			
		}
		
		
		$.ajax({
            type: "POST",
            url: "/mng/pop/getDataForCommnonPopup.do",
            data: data,
            dataType: "json"
          }).done(function(response){
        	  fn_chooseCommonPopup(response.popupList);
        });
}

function fn_neverShow(id,isChecked){
  (isChecked)? $.cookie("commonPop_cookie_"+id, "Y", 365) :$.cookie("commonPop_cookie_"+id, "N", 365);
}

function getLayerStr(item){
	
	var layerStr = '    <div id="layer'+item.popupntcid+'" class="pop-layer" style="overflow:auto;  width:'+item.width_size+'px; height : '+item.vrtcl_size+'px; ">';
	    layerStr += '        <div class="panel panel-default">';
	    layerStr += '	      <div class="panel-heading">';
	    layerStr += '		    <h4>'+item.title+'</h4>';
	    layerStr += '		     <ul class="links">';
	    layerStr += '		    	<li>';
	    layerStr += '		    	  <a class="glyphicon glyphicon-remove modal-close " aria-hidden="true"></a>';
	    layerStr += '		    	</li>';
	    layerStr += '		     </ul>';
	    layerStr += '		   </div>';
	    layerStr += '           <div class="pop-conts row col-xs-12">';
	    layerStr += '                     '+item.cntnts+'';
	    layerStr += '           </div>';
	    layerStr += '            <div  class="col-xs-12 pop-footer" style="border-top :1px solid #cae1ef ; margin:0px; bottom:0;  position:absolute; " >';
	    layerStr += '		       <h6><label class=""   ><input type="checkbox"  id="checkCookie'+item.popupntcid+'" onclick=fn_setPopupCookie('+item.popupntcid+') /> 다시 보지 않기</label></h6>';
	    layerStr += '            </div>';
	    layerStr += '        </div>';
	    layerStr += '    </div>';
	    return layerStr;
}

function getDeepLayerStr(item){
	var str = getLayerStr(item);
	var layerStr  = "";
	    layerStr += '<div class="dim-layer dim-layer-'+item.popupntcid+'" id="dim-layer-'+item.popupntcid+'">';
	    layerStr += '   <div class="dimBg"></div>';
	    layerStr += str;  
	    layerStr += '</div>';
	    return layerStr;
}

function fn_openCommonLayerPopup(item){
	$("#layerPopupSapn").append(getDeepLayerStr(item));
	layer_popup("#layer"+item.popupntcid,item);
}


function fn_openCommonModal(item){
	$("#layerPopupSapn").append(getLayerStr(item));
	layer_popup("#layer"+item.popupntcid,item);
}

function layer_popup(el,item){
	
	console.log(el)
	console.log(item)

    var $el = $(el);        //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

    //isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();
    isDim ? $("#dim-layer-"+item.popupntcid).fadeIn() : $el.fadeIn();

    var $elWidth = ~~($el.outerWidth()),
        $elHeight = ~~($el.outerHeight()),
        docWidth = $(document).width(),
        docHeight = $(document).height();

    //if(item.left_lc != 0 || item.top_lc != 0 ){
	//    $el.css({top: item.top_lc,left: item.left_lc})
    //}else if ($elHeight < docHeight || $elWidth < docWidth) {  // 화면의 중앙에 레이어를 띄운다.
    //    $el.css({ marginTop: -$elHeight/2, marginLeft: -$elWidth/2})
    //} else {
    //    $el.css({top: 0, left: 0});
    //}

    $el.css({top: item.top_lc,left: item.left_lc})

    $el.find('a.modal-close').click(function(){
        //isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        isDim ? $("#dim-layer-"+item.popupntcid).fadeOut() : $el.fadeOut(); 
        return false;
    });

    $('.layer .dimBg').click(function(){
        //$('.dim-layer').fadeOut();
        $("#dim-layer-"+item.popupntcid).fadeOut();
        return false;
    });
 }

  function fn_setPopupCookie(id){
   if($("#checkCookie"+id).is(":checked")){
      $.cookie("commonPop_cookie_"+id, "Y", 365);
   }else{
      $.cookie("commonPop_cookie_"+id, "N", 365);   
   }
    
}















