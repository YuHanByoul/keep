
/**
 * ajax이용중 error발생시 처리
 */
jQuery(function(){
  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
	
 jQuery.ajaxSetup({
  beforeSend: function(xhr) {
	 xhr.setRequestHeader("AJAX", true);
	 xhr.setRequestHeader(header, token);
  },
  error:function(xhr,e){
	  closeWorkProgress();

	  if(xhr.status==404){
	      alert('해당 요청URL을 찾을 수 없습니다.');
	  }else if(xhr.status==401){
		  alert('세션이 유효하지 않습니다.\n페이지 새로고침을 해주십시오.');
		  //location.href='/';
	  }else if(xhr.status==403){
		  alert('접근권한이 없습니다.');
          //location.href='/';
	  }else if(xhr.status==500){
		  alert('내부 서버 오류입니다.');
	  }else if(e === 'parsererror'){
		  if(xhr.responseText == 'DUPLOGOUT'){
			  alert('중복로그인되어 로그아웃 되었습니다.\n로그인 페이지로 이동합니다.');
			  location.href='/login.html';
              xhr.responseText = 'DUPLOGOUT_COMPLETED';
		  }else{
			  alert('데이터를 파싱하는데 실패하였습니다.');
		  }
	  }else if(e === 'timeout'){
		  alert('요청시간이 초과되었습니다.');
	  }else {
		  alert('알수없는 오류가 발생하였습니다.');
	  }
  },
  complete:function(xhr,status){
    if(xhr.responseText == 'DUPLOGOUT'){
      alert('중복로그인되어 로그아웃 되었습니다.\n로그인 페이지로 이동합니다.');
      location.href='/login.html';
    }
  }
 });
});


jQuery(function(){
    $("#this_href_naver_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "http://share.naver.com/web/shareView.nhn?url=" + o(t) + "&title=" + o(a);
        if (!confirm("현재 페이지를 네이버에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
    
    $("#this_href_kakaostory_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "https://story.kakao.com/s/share?url=" + o(t) + "&text=" + o(a);
        if (!confirm("현재 페이지를 카카오스토리에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
    
    $("#this_href_twitter_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "https://twitter.com/intent/tweet?url=" + o(t) + "&text=" + o(a);
        if (!confirm("현재 페이지를 트위터에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
    
    $("#this_href_facebook_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "https://www.facebook.com/sharer/sharer.php?u=" + o(t) + "&t=" + o(a);
        if (!confirm("현재 페이지를 페이스북에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
});

// url 복사하는 함수
function copyUrl(e) {
  if (!document.queryCommandSupported("copy")) {
    return alert("복사 기능이 지원되지 않는 브라우저입니다.");
  }

  jQuery("#currentPageUrl").select();
  document.execCommand('copy');
  e.target.focus();

  alert("주소가 복사되었습니다.\n원하는 곳에 붙여넣기 해주세요.");
}

function o(e) {
    if (null == e || "" == e)
        return "";
    if (e instanceof Array) {
        for (var t = ""; 0 < e.length; ++s)
            "" != t && (t += "&"),
            t += o(e[0]);
        return t
    }
    return e = (e = (e = (e = (e = (e = encodeURIComponent(e)).replace(/\!/g, "%21")).replace(/\*/g, "%2A")).replace(/\'/g, "%27")).replace(/\(/g, "%28")).replace(/\)/g, "%29")
}

