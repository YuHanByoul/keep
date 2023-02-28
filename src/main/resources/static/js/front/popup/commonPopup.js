var modals = [];

function fn_chooseCommonPopup(list){
	if(list.length > 0){
		for( i= 0; i < list.length ; i++){
			if($.cookie("commonPop_cookie_checkCookie"+list[i].popupntcid) != "Y") {  
				if(list[i].popupTypeCd =="P") fn_openCommonPopup(list[i]); //팝업
				else if(list[i].popupTypeCd =="L") fn_openLayer(list[i]); //레이어
				else fn_openModal(list[i]); // 모달 
			}
	    }
	    
	    if(modals.length > 0) {
            layerPopup.open(modals[0]);
            modals.shift();    
        }
	}
}

function fn_openCommonPopup(item){
    popupCenter({url: "/front/pop/getPopup.html?popupntcid="+item.popupntcid, title: 'popup' + item.popupntcid, w: item.widthSize, h: item.vrticlSize, l: item.leftLc, t: item.topLc});
}
function getDataForCommnonPopup( siteid, menuid ){
    
		let data = { "siteid": siteid,"menuid": menuid};
        try{
            if(isMainPage){ data = {"siteid": siteid,"expsrLcCd": "M"}; }
        }catch(e){
            console.log("not main Page");
        }
        
		$.ajax({
            type: "POST",
            url: "/front/pop/getDataForCommnonPopup.do",
            data: data,
            dataType: "json"
          }).done(function(response){
        	  fn_chooseCommonPopup(response.popupList);
        });
}

function getLayerStr(item){
    var layerStr = '<div class="layer-popup layer-alert layer-medium" data-layer-id="layer'+item.popupntcid+'">';
    layerStr += '<div class="layer-dimmed" title="레이어팝업 닫기"></div>';
    layerStr += '<div class="layer-wrap pb0">';
    layerStr += '    <div class="layer-header">';
    layerStr += '        <strong class="tit">'+item.title+'</strong>';
    layerStr += '        <button type="button" class="btn-layer-close" title="레이어팝업 닫기" data-layer-close></button>';
    layerStr += '    </div>';
    layerStr += '    <div class="layer-content">';
    layerStr += item.cntnts;
    layerStr += '    </div>';
    layerStr += '    <div class="layer-footer">';
    layerStr += '        <div class="layer-cookie j-between a-center">';
    layerStr += '            <div class="form-input d-flex">';
    layerStr += '                <label class="inp vt"><input type="checkbox" id="checkCookie'+item.popupntcid+'"><b class="fc-grayc">오늘 그만 보기</b></label>';
    layerStr += '            </div>';
    layerStr += '            <div>';
    layerStr += '                <button type="button" class="btn-small btn-popup-close" data-layer-close onclick="fn_setPopupCookie(\'checkCookie'+item.popupntcid+'\')">닫기</button>';
    layerStr += '            </div>';
    layerStr += '        </div>';
    layerStr += '    </div>';
    layerStr += '</div>';
    layerStr += '</div>';
    return layerStr;
}

function getModalStr(item){
    var layerStr = '<div class="layer-popup" data-layer-id="layer'+item.popupntcid+'">';
    layerStr += '    <div class="layer-dimmed prevent-close"></div>';
    layerStr += '    <div class="layer-wrap">';
    layerStr += '        <div class="layer-header">';
    layerStr += '            <strong class="tit">'+item.title+'</strong>';
    layerStr += '            <button type="button" class="btn-layer-close" title="레이어팝업 닫기" onclick="layerPopup.close({target:\'layer'+item.popupntcid+'\', callback: function(){ if(modals[0] != undefined) {layerPopup.open(modals[0]);modals.shift();}}})"></button>';
    layerStr += '        </div>';
    layerStr += '        <div class="layer-content">';
    layerStr += item.cntnts;
    layerStr += '        </div>';
    layerStr += '        <div class="layer-footer">';
    layerStr += '            <div class="layer-cookie mt20"><button type="button" class="no-display" onclick="fn_setPopupCookie2(\''+item.popupntcid+'\')">오늘 그만 보기</button></div>';
    layerStr += '        </div>';
    layerStr += '    </div>';
    layerStr += '</div>';
    return layerStr;
}

function fn_openLayer(item){
    $("#layerPopupSapn").append(getLayerStr(item));
    layerPopup.open({target: "layer"+item.popupntcid, w: item.widthSize, l: item.leftLc, t: item.topLc});
}

function fn_openModal(item){
    $("#layerPopupSapn").append(getModalStr(item));
    modals.push({target: "layer"+item.popupntcid, w: item.widthSize, l: 0, t: 0});
}

function fn_setPopupCookie(id){
    if($("#"+id).is(":checked")){
        $.cookie("commonPop_cookie_"+id, "Y", {expires:1, path: '/'});
    }
}

function fn_setPopupCookie2(id){  
    $.cookie("commonPop_cookie_checkCookie"+id, "Y", {expires:1, path: '/'});
    layerPopup.close({target:'layer'+id, callback: function(){ if(modals[0] != undefined) {layerPopup.open(modals[0]);modals.shift();}}});
}
