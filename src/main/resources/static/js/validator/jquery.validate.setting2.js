/**
 * 밸리데이터 디폴 값 변경 
 * @since 19.04.11
 * 
 * **/
//밸리데이터 옵션  alert 형식으로 변경 
$.validator.setDefaults({
                onkeyup:false, 
                onclick:false,
                onfocusout:false,
                errorElement: "input",
                highlight :true,
                showErrors:function(errorMap, errorList){ //alert
                    $('.form-msg').text('');
                    if(this.currentElements.length > 0) {
                        $('.feedback.invalid', this.currentForm).text('').removeClass("invalid");
                    }
                    if(this.numberOfInvalids() && errorList.length != 0) {
                        //option1 : alert 표시
                        //alert(errorList[0].message);
                                            
                        errorList[0].element.focus();
                        //option2  : 입력 값 지우고 에러메세지 placeholder 입력
                        //*$(errorList[0].element).val(null); 
                        //$(errorList[0].element).attr("placeholder",errorList[0].message);
                        
                        //option3 : input border 라인 red 강조 수정
                        //$(errorList[0].element).css("border","2px solid #ff0000");//#B0371D : 진한빨강 ,  
                        //setTimeout(function() { $(errorList[0].element).css("border","")}, 1000);
                        
                        //$(errorList[0].element.nextElementSibling).text('123')
                        var nextSibling = null;
                        $(errorList).each(function(){
                            if($(this.element).siblings(".form-msg")){ // 예제용
                               $(this.element).siblings(".form-msg").text(this.message);
                            }
                            
                            if($(this.element).siblings(".invalid-feedback").length == 1){ // 로그인 전용
                               $(this.element).siblings(".invalid-feedback").text(this.message);
                               return false;
                            }
                            
                            if($(this.element).closest("td").find(".feedback").length == 1) { // 포털용
                                $(this.element).closest("td").find(".feedback").text(this.message).removeClass("valid").addClass("invalid");
                            }else if($(this.element).siblings(".feedback").length == 1){
                                $(this.element).siblings(".feedback").text(this.message).removeClass("valid").addClass("invalid");
                            }

/*                            nextSibling = this.element.nextElementSibling;
                            if($(nextSibling).is(".form-msg")){
                                $(nextSibling).text(this.message);
                            }*/

                        });
                    }
                }
});

//default message 값 변경 English -> korean
$.extend( $.validator.messages, 
          { 
            //jquery.validate.js 디폴트 메소드 및 메세지 
             required: "필수 입력 항목입니다."
            , remote: "항목을 수정해 주십시오."
            , email: "유효하지 않은 E-Mail주소입니다."
            , url: "유효하지 않은 URL입니다."
            , date: "올바른 날짜를 입력하십시오."
            , dateISO: "올바른 날짜(ISO)를 입력하십시오."
            , number: "숫자만 입력가능합니다."
            , digits: "숫자만 입력 가능합니다."
            , creditcard: "신용카드 번호가 바르지 않습니다."
            , equalTo: "같은 값을 다시 입력하십시오."
            , extension: "올바른 확장자가 아닙니다."
            , maxlength: $.validator.format( "{0}자를 넘을 수 없습니다. " ) //maxlength:[8]
            , minlength: $.validator.format( "{0}자 이상 입력하십시오." )
            , rangelength: $.validator.format( "문자 길이가 {0} 에서 {1} 사이의 값을 입력하십시오." ) //rangelength: [2, 10]
            , range: $.validator.format( "{0} 에서 {1} 사이의 값을 입력하십시오." ) //range: [2, 10]
            , max: $.validator.format( "{0} 이하의 값을 입력하십시오." )
            , min: $.validator.format( "{0} 이상의 값을 입력하십시오." )
            /***************** custom Method*******************
             * jquey.validate.add.js  추가 메소드     
             *************************************************/ 
            , alphanumeric: "알파벳과 숫자만 입력 가능합니다."  
            , passwordPolicy1 : "영문,숫자만 입력 가능합니다."
            , passwordPolicy2 : "영문,숫자,특수문자 가 하나 이상 입력되어야 합니다."
            , isBlank :"공백이 있습니다."
            , isHangulOn : "한글만 입력가능합니다."
            , isHangulOff : "한글은 입력 할 수 없습니다."
            , isSybolOff : "특수기호는 사용 할 수 없습니다."
            , isRightId : "올바르지 않은 주민번호입니다."
            , isRightBizNo : "올바르지 않는 사업자 번호입니다."
            , emailDomain : "올바른 이메일을 입력해주십시오."
            , hangulOrAlpha : "한글과 알파벳만 사용가능합니다."
            , ckRequired: '내용을 입력해 주십시오.'
            }

);



