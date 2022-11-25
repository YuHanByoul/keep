"use strict";
$(document).ready(function() {
	// Single swithces
	var elemsingle = document.querySelector('.js-single-small');
	var switchery = null;
	if(elemsingle != undefined){
        switchery = new Switchery(elemsingle, { color: '#4680ff', jackColor: '#fff' });
    }
});
