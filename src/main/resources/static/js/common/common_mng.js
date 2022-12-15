
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
		  alert('인증에 실패하였습니다.\n로그인 페이지로 이동합니다.');
		  location.href='/';
	  }else if(xhr.status==403){
		  alert('접근권한이 없습니다.');
          //location.href='/';
	  }else if(xhr.status==500){
		  alert('내부 서버 오류입니다.');
	  }else if(e === 'parsererror'){
		  if(xhr.responseText == 'DUPLOGOUT'){
			  alert('중복로그인되어 로그아웃 되었습니다.\n로그인 페이지로 이동합니다.');
			  location.href='/';
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
      location.href='/';
    }
  }
 });
});

