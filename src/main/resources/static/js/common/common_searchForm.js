
(function($){
    var originalVal = $.fn.val;
    $.fn.val = function(){
        var result =originalVal.apply(this,arguments);
        if(arguments.length>0){
        	if($(this).attr('selectSearchCondition') != undefined){ // 기관검색 select
        	    if($(this).val() == ''){
                    $('#selectInst'+$(this).attr('id')).val(' ').trigger('change');
                }else{
                    $('#selectInst'+$(this).attr('id')).val($(this).val()).trigger('change');
                }
            }
        }
        return result;
    };
})(jQuery);