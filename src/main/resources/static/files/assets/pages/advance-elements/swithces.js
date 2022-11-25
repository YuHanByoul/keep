"use strict";
$(document).ready(function() {
    // Single swithces small (22/11/16 JM sm버전을 위해 추가함
    var elemsingleSmall = document.querySelector('.js-single-small');
    var switcherySmall = null;
    if(elemsingleSmall != undefined){
        switcherySmall = new Switchery(elemsingleSmall, { color: '#4680ff', jackColor: '#fff', size : 'small'});
    }
    
    
});
