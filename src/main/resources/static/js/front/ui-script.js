
$(function (){
	resizeWindow.init();
	gnb.init();
	navigation.init();
	formStyle.init();
	dropDown.init();
	toggleActiveClass.init();
	layerPopup.init();
	tabContent.init();
	accordionContent.init();
	// uploadFile();
	headerSearch.init();
	asideScrollTop();
	$('body').removeClass('preload');
});

let $WINDOW_MODE = '';
const DESKTOP = 'DESKTOP';
const TABLET = 'TABLET';
const MOBILE = 'MOBILE';
const $html =  $('html');
const ANIMATION_TIME = 300;
const preventScrollOn = function () {
	$html.css('overflow','hidden');
};
const preventScrollOff = function () {
	$html.css('overflow','');
};

var resizeWindow = {
	init : function () {
		
		resizeWindow.onResize();
		resizeWindow.sizeCheck();

		resizeWindow.toggleLayerResize();
		
	},
	reset : function () {
		// $html.removeAttr('style','');
		// $('.header .dep02').removeAttr('style','');
		// $('.header .gnb').removeAttr('style','');
	},
	sizeCheck : function () {
		$windowWidth = $(window).outerWidth();
		var DESKTOP_SIZE = $windowWidth >= 1280;
		var TABLET_SIZE = $windowWidth < 1280 && $windowWidth >= 1024;
		var MOBILE_SIZE = $windowWidth < 1024;
		
		if (DESKTOP_SIZE) {
			$html.addClass(DESKTOP);
			$html.removeClass(TABLET);
			$html.removeClass(MOBILE);
			$WINDOW_MODE = DESKTOP;
		} else if ((TABLET_SIZE )) {
			$html.addClass(TABLET);
			$html.removeClass(DESKTOP);
			$html.removeClass(MOBILE);
			$WINDOW_MODE = TABLET;
		} else if ((MOBILE_SIZE)) {
			$html.addClass(MOBILE);
			$html.removeClass(DESKTOP);
			$html.removeClass(TABLET);
			$WINDOW_MODE = MOBILE;
		}
		console.log("🚀", $WINDOW_MODE)
		
		

	},
	onResize : function () {
		$(window).on('resize', function () {
			$windowWidth = $(window).outerWidth();
			resizeWindow.sizeCheck();
		})

	},
	toggleLayerResize : function () {
		$(window).on('resize', function () {
			const $toggleLayer = $('.toggle-layer');
			if ($toggleLayer.length) {
				const $toggleLayerInner = $toggleLayer.find('.toggle-layer-inner');
				const $layerLeft = $toggleLayer.offset().left; //layer position
				const $layerWidth = $toggleLayer.outerWidth() / 2; //layer width (center)
				const $layerInnerWIdth = $toggleLayerInner.outerWidth() / 2; //layerpopup (center)
				$windowWidth = $(window).outerWidth();
				const overflowRightPosition = Math.ceil($windowWidth - ($layerLeft + $layerWidth +  $layerInnerWIdth)); //layer position + layer width + layerpopup 
				if (overflowRightPosition < 0) {
					$toggleLayerInner.css('margin-left', overflowRightPosition)
				} else {
					$toggleLayerInner.css('margin-left','');
				}
			}
		})
	},
}

const gnb = {
	init : function (){
		$header = $('#header');
		$elGnb = $header.find('.gnb');
		$Dep01 = $elGnb.find('.dep01');
		$Dep01List = $Dep01.find('>li');
		$Dep02 = $elGnb.find('.dep02');
		$Dep03 = $elGnb.find('.dep03');

		$gnbOnFocusStart = $elGnb.find('.btn-gnb-start');
		$gnbOnFocusEnd = $elGnb.find('.btn-gnb-end');

		$btnActiveGnb = $('.sitemap .toggleTrigger');
		CLASS_CURRENT_PAGE = 'current-page';
		CLASS_DEP_ACTIVE = 'active';

		//Desktop
		gnb.onHover();
		gnb.onFocus();
		
		//Mobile
		gnb.onClickMobileGnb();
		gnb.onClickDep01();
		gnb.onClickDep02();

		//공통
		gnb.onLoadAria();
		gnb.headerReset();
		
		if ($header.length) {
			$headerHeight = $header.outerHeight();
			$headerOffsetTop = $header.offset().top;
			gnb.headerResize();
			gnb.onScroll();

		}

		if ($WINDOW_MODE === MOBILE) {

		};
	},
	open : function () {
		if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
			$Dep01.find('>.active').find($Dep02).stop().fadeIn(0);
		}
		if ($WINDOW_MODE === MOBILE) {
			gnb.onLoadDep02();
			$Dep01.each(function () {
				if (!$(this).find('>li').hasClass(CLASS_CURRENT_PAGE)) {
					if (!$(this).find('>li').hasClass(CLASS_DEP_ACTIVE)) {
						$Dep01.find('>li:first-child').addClass(CLASS_DEP_ACTIVE);
						$Dep01.find('>li:first-child').find('.dep02').show();
					}
				} else {
					$Dep01.find('>li.current-page').addClass(CLASS_DEP_ACTIVE);
					$Dep01.find('>li.current-page').find('.dep02').show();
				}
			})
			$('.center').removeClass('active');
			preventScrollOn();
		}
	},
	close : function (){
		if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
			$Dep01List.find($Dep02).stop().fadeOut(150, function () {
				$(this).attr('style','display:none')
			});
			preventScrollOff();
		}
		
		if ($WINDOW_MODE === MOBILE) {
			preventScrollOff();
			
			$Dep01.each(function () {
				const dep01Active = $(this).find('li.active');
				const dep02Active = $(this).find('.dep02>.dep02-inner>ul>li.active');
				dep01Active.removeClass(CLASS_DEP_ACTIVE);
				dep02Active.removeClass(CLASS_DEP_ACTIVE);
			})
		}
	},
	onHover : function (){
		$Dep01List.on('mouseenter', function (){
			if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
				$(this).siblings('li').removeClass(CLASS_DEP_ACTIVE);
				$(this).addClass(CLASS_DEP_ACTIVE);
				gnb.open();
			}
		})
		$Dep01List.on('mouseleave', function (){
			if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
				$(this).removeClass(CLASS_DEP_ACTIVE);
				gnb.close();
			}
		})
	},
	onFocus : function (){
		$Dep01List.on('focusin', function (){
			if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
				$(this).siblings('li').removeClass(CLASS_DEP_ACTIVE);
				$(this).addClass(CLASS_DEP_ACTIVE);
				$(this).siblings('li').find($Dep02).stop().fadeOut(ANIMATION_TIME, function () {
					$(this).attr('style','display:none')
				});
				$(this).find($Dep02).stop().fadeIn(ANIMATION_TIME);
			}
		})
		$Dep01List.on('focusout', function (){
			if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
				$(this).removeClass(CLASS_DEP_ACTIVE);
			}
		})
		$gnbOnFocusStart.on('focus', function (){
			gnb.close();
		})
		$gnbOnFocusEnd.on('focus', function (){
			gnb.close();
		})
	},
	onClickMobileGnb : function () {
		$btnActiveGnb.on('click', function (){
			const par = $(this).closest('.header-bottom-inner');
			if (par.hasClass(CLASS_TOGGLE_ACTIVE)) {
				gnb.close();
			} else {
				gnb.open();
			}
		})
	},
	onClickDep01 : function () {
		// target = _this;
		$(document).on('click', '.gnb .dep01 > li > a' , function (e) {
			if ($WINDOW_MODE === MOBILE) {
				const par = $(this).closest('li');
				e.preventDefault();
				if (!par.hasClass(CLASS_DEP_ACTIVE)) {
					par.siblings('li').removeClass(CLASS_DEP_ACTIVE);
					par.siblings('li').find('.dep02').hide();
					par.addClass(CLASS_DEP_ACTIVE);
					par.find('.dep02').show();
					$elGnb.animate({scrollTop: '0'}, 300);
					$Dep02.animate({scrollTop: '0'}, 300);
				}
			}
		})
			
	},
	onClickDep02 : function () {
		// target = _this;
		$(document).on('click', '.gnb .dep02-inner > ul > li > a' , function (e) {
			if ($WINDOW_MODE === MOBILE) {
				const par = $(this).closest('li');
				if (par.hasClass('hasDep03')) {
					e.preventDefault();
					if (!par.hasClass(CLASS_DEP_ACTIVE)) {
						par.siblings('li').removeClass(CLASS_DEP_ACTIVE);
						par.siblings('li').find('.dep03').slideUp(ANIMATION_TIME);
						par.addClass(CLASS_DEP_ACTIVE);
						par.find('.dep03').slideDown(ANIMATION_TIME);
					} else {
						par.removeClass(CLASS_DEP_ACTIVE);
						par.find('.dep03').slideUp(ANIMATION_TIME);
					}
				}
			}
		})
			
	},
	onLoadAria : function () {
		$Dep01.find('li').each(function () {
			if ($(this).hasClass(CLASS_CURRENT_PAGE)) { 
				$(this).find('>a').attr('aria-current','page');
			}
		})
	},
	onLoadDep02 : function () {
		$Dep02.find('.dep02-inner>ul>li').each(function () {
			if ($(this).find($Dep03).length) {
				$(this).addClass('hasDep03');
			}
			if ($(this).hasClass(CLASS_CURRENT_PAGE)) { 
				$(this).addClass(CLASS_DEP_ACTIVE);
				$(this).find($Dep03).stop().slideDown(0);
			} else {
				$(this).find($Dep03).stop().slideUp(0);
			}
		})
		
	},
	headerResize : function () {
		if ($WINDOW_MODE === DESKTOP) {
			if ($headerOffsetTop > 145) {
				$html.addClass('header-medium');
			} else {
				$html.removeClass('header-medium');
			}
		}

	},
	headerReset : function () {
		$(window).on('resize', function () {
			if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
				gnb.close();
				$Dep01List.removeClass(CLASS_DEP_ACTIVE);
				$Dep03.show();
			}
			if ($WINDOW_MODE === MOBILE) {
				$('.header-bottom-inner').removeClass(CLASS_DEP_ACTIVE);
				$Dep01List.find('.dep02').hide();
				$Dep01List.removeClass(CLASS_DEP_ACTIVE);
				$Dep01List.find('.dep02-inner > ul > li').removeClass(CLASS_DEP_ACTIVE);
				preventScrollOff();

			}
		})
	},
	onScroll : function () {
		if ($WINDOW_MODE === DESKTOP || $WINDOW_MODE === TABLET) {
			$(window).on('scroll', function () {
				$headerOffsetTop = $header.offset().top;
				gnb.headerResize();
			})
		}

	},

}


const navigation = {
	init : function () {
		$navList = $('.navigation');
		$dep01 = $navList.find('.dep01 > li');
		$dep02 = $dep01.find('.dep02');
		DEP02_INNER = 'dep02-inner';
		navigation.onClick();
		navigation.afterLoad();
	},
	onClick : function () {
		$dep01.find('>a').on('click', function (e){
			const parent = $(this).closest('li');
			if (parent.hasClass(DEP02_INNER)) {
				e.preventDefault();
				if (parent.hasClass('active')) {
					parent.removeClass('active');
					parent.attr('aria-expanded', 'true');
					parent.find($dep02).stop().slideUp(ANIMATION_TIME);
				} else {
					parent.addClass('active');
					parent.attr('aria-expanded', 'false');
					parent.find($dep02).stop().slideDown(ANIMATION_TIME);
				}
			}
		});
	},
	afterLoad  : function () {
		$dep01.each(function (){
			if ($(this).find($dep02).length) {
				$(this).addClass(DEP02_INNER);
				if ($(this).hasClass('active')) {
					$(this).attr('aria-expanded', 'false');
					$(this).find($dep02).css('display','block');
				} else {
					$(this).attr('aria-expanded', 'true');
					$(this).find($dep02).css('display','none');
				}
			}	
		});
	}
}

const formStyle = {
	init : function (){
		// formStyle.typing();
		// formStyle.typed();
		// formStyle.selected();
		formStyle.textareaResize();
		formStyle.passwordTypeChange();
		formStyle.tableRowCheck();
		if ($WINDOW_MODE === DESKTOP) {
			formStyle.widthEnabled();
		} else {
			formStyle.widthDisabled();
		}
		formStyle.onResize();
		formStyle.onNumberClick();
		formStyle.onNumberKeydown();
		formStyle.readonly();
		// formStyle.searchFilterSuggest();
		suggestFlag = '';
	},
	// typing : function (){
	// 	$(document).on('focusin keypress keydown','.form-input input:not([type="checkbox"]), .form-input input:not([type="radio"]), .form-input textarea', function () {
	// 		if ($(this).val().length >= 0) {
	// 			$(this).addClass('typing');
	// 			$(this).removeClass('typed')
	// 		} else {
	// 			$(this).removeClass('typing')
	// 		}
	// 	});
	// },
	// typed : function (){
	// 	$(document).on('focusout','.form-input input:not([type="checkbox"]), .form-input input:not([type="radio"]), .form-input textarea', function () {
	// 		if ($(this).val().length > 0 || $(this).text().length > 0) {
	// 			$(this).addClass('typed');
	// 		} else {
	// 			$(this).removeClass('typing typed');
	// 		}
	// 	});
	// },
	// selected : function (){
	// 	$(document).on('change','.form-select select', function () {
	// 		var selectValue = $(this).val();
	// 		if (selectValue.length > 0 ) {
	// 			$(this).addClass('selected');
	// 		}
			
	// 	});
	// },
	// afterLoad : function () {
	// 	$('.form-input input:not([type="checkbox"]), .form-input input:not([type="radio"]), .form-input textarea').each(function (){
	// 		if ($(this).val().length > 0 || $(this).text().length > 0) {
	// 			$(this).addClass('typed');
	// 		} else {
	// 			$(this).removeClass('typing typed');
	// 		}
	// 	});
	// 	$('.form-select select').each(function () {
	// 		var selectValue = $(this).val();
	// 		console.log(selectValue)
	// 		if (!selectValue == "") {
	// 			if (selectValue.length > 0 ) {
	// 				$(this).addClass('selected');
	// 			}
	// 		}
	// 	});

	// },
	textareaResize : function () {
		$.each($('textarea'), function() {
			if (!$(this).is('[readonly]')) {
				const offset = this.offsetHeight - this.clientHeight;
				const resizeTextarea = function(el) {
					$(el).css('height', 'auto').css('height', el.scrollHeight + offset);
					$(el).addClass('areaResize')
				};
				$(document).on('keyup input', 'textarea', function () {
					resizeTextarea(this);
				});
			}
		});
	},
	passwordTypeChange : function () {
		$('.form-input-password .toggleTrigger').on('click', function () {
			const parent = $(this).closest('.toggleParent');
			const $input = $(this).closest('.form-input-password').find('input');
			if (parent.hasClass('active')) {
				$input.attr('type','password');
			} else {
				$input.attr('type','text');
			}
		})
	},
	tableRowCheck : function () {
		$(document).on('click', '.table-col .tr-check input[type=checkbox],.table-col .tr-check input[type=radio]' , function () {
			const tr = $(this).closest('.tr-check');
			if ($(this).is(':checked')) {
				tr.addClass('tr-checked');
			} else {
				tr.removeClass('tr-checked');
			}
		})
	},
	widthEnabled : function () {
		$('.form-input input,.form-input select, .form-input textarea, .form-input .label, .form-input-file').each(function () {
			if ($(this).data('width')) {
				$(this).css({
					'width': $(this).data('width'),
					'flex': '0 0' + $(this).data('width') + 'px'
				});
			}
		})
	},
	widthDisabled : function () {
		$('.form-input input, .form-input select, .form-input textarea, .form-input .label, .form-input-file').each(function () {
			if ($(this).data('width')) {
				$(this).removeAttr('style');
			}
		})
	},
	onResize : function () {
		$(window).on('resize', function () {
			if ($WINDOW_MODE === DESKTOP) {
				formStyle.widthEnabled();
			} else {
				formStyle.widthDisabled();
			}
		});

	},
	onNumberClick :function () {
		$(document).on('click', '.form-input-number button' , function () {
			const par = $(this).closest('.form-input-number');
			const numInput = par.find('input[type="number"]');
			const valueMin = parseInt(numInput[0].min);
			const valueMax = parseInt(numInput[0].max);

			//value up
			if ($(this).hasClass('num-up')) {
				if (numInput.val() === '') {
					numInput.val(0)
				}
				if (valueMax > numInput.val() || numInput[0].max === '') {
				numInput[0].value++;
				}
			}

			//value down
			if ($(this).hasClass('num-down')) {
				if (numInput.val() === '') {
					numInput.val(0)
				}
				if (numInput.val() > valueMin || numInput[0].min === '') {
					numInput[0].value--;
				}
			}
			
		});
	},
	onNumberKeydown : function () {
		$(document).on('keyup', '.form-input-number input[type="number"]' , function () {
			const numInput = $(this);
			const valueMin = parseInt(numInput[0].min);
			const valueMax = parseInt(numInput[0].max);
			if (numInput.val() > valueMax) {
				numInput.val(valueMax)
			}
			if (numInput.val() < valueMin) {
				numInput.val(valueMin)
			}
		});
	},
	readonly : function () {
		$('input[type="checkbox"], input[type="radio"]').each(function () {
			if ($(this)[0].readOnly === true) {
				$(this).attr('aria-readonly',true)
			}
		});
	},
	searchFilterSuggest : function () {
		$(document).on('focusin', '.search-suggest-position', function () {
			$('.search-filter-suggest').addClass('suggest-active');
		})
		$(document).on('focusout', '.search-suggest-position', function () {
			$('.search-filter-suggest').removeClass('suggest-active')
		});
	}
} 

const dropDown = {
	init : function (){
		CLASS_DROPDOWN_ACTIVE = 'active';
		let par = '';
		dropDown.onClick();
		dropDown.select();
		// dropDown.onKeybind();
	},
	open : function () {
		par.addClass(CLASS_DROPDOWN_ACTIVE);
		par.find('>.target').slideDown(0);
		$(this).attr('aria-hidden','false');
	},
	close : function (){
		par.removeClass(CLASS_DROPDOWN_ACTIVE);
		par.find('>.target').slideUp(0);
		$(this).attr('aria-hidden','true');
	},
	select :function () {
		$(document).on('click','.dropDown .target button', function (){
			par = $(this).closest('.dropDown');
			if (par.hasClass('active')) {
				const value = $(this).html();
				par.find('.trigger').html('');
				par.find('.trigger').append(value);
				dropDown.close();
			}
		});
	},
	onClick : function () {
		$(document).on('click', '.dropDown .trigger', function (e){
			par = $(this).closest('.dropDown');
			if (par.find('.target').length){
				if (!par.hasClass('active')){
					$('.dropDown.active').each(function () {
						$(this).removeClass('active');
						$(this).find('>.target').slideUp(0);
						$(this).find('.trigger').attr('aria-hidden','true');
					})
					dropDown.open();
				} else {
					dropDown.close();
				}
			}
		});
	}
}

const toggleActiveClass = {
	init : function () {
		CLASS_TOGGLE_ACTIVE = 'active';
		toggleActiveClass.onClick();
		let par = '';
		let togglePar = '';
		
	},
	open : function () {
		par.addClass(CLASS_TOGGLE_ACTIVE);
		$(this).find('.on').attr('aria-hidden','true');
		$(this).find('.off').attr('aria-hidden','false');
	},
	close : function () {
		par.removeClass(CLASS_TOGGLE_ACTIVE);
		$(this).find('.on').attr('aria-hidden','false');
		$(this).find('.off').attr('aria-hidden','true');
	},
	onClick : function () {
		$(document).on('click', '.toggleTrigger', function (){
			par = $(this).closest('.toggleParent');
			togglePar = $(this).closest('.toggle-layer');
			if (par.hasClass(CLASS_TOGGLE_ACTIVE)){
				toggleActiveClass.close();
				if (togglePar.length) {
					par.find('.toggleTrigger:not(.toggle-layer-close)').focus();
				}
			} else {
				if (par.hasClass('toggle-layer')) {
					toggleActiveClass.onClickToggleLayer();
				}
				if (par.siblings('.toggleParent').length) {
					par.siblings('.toggleParent').each(function () {
						$(this).removeClass('active');
					})
				}
				if (par.hasClass('pigeon')) {
					$('body').find('.pigeon.active').removeClass('active');
				}
				toggleActiveClass.open();
			}
		})
	},

	onClickToggleLayer : function () {
		const $toggleLayer = $('.toggle-layer');
		const $toggleLayerInner = $toggleLayer.find('.toggle-layer-inner');
		const $layerLeft = $toggleLayer.offset().left; //layer position
		const $layerWidth = $toggleLayer.outerWidth() / 2; //layer width (center)
		const $layerInnerWIdth = $toggleLayerInner.outerWidth() / 2; //layerpopup (center)
		$windowWidth = $(window).outerWidth();
		const overflowRightPosition = Math.ceil($windowWidth - ($layerLeft + $layerWidth +  $layerInnerWIdth)); //layer position + layer width + layerpopup 
		if (overflowRightPosition < 0) {
			$toggleLayerInner.css('margin-left', overflowRightPosition)
		} else {
			$toggleLayerInner.css('margin-left','');
		}
	},
	
}

const layerPopup = {
	init : function () {
		ZINDEX = 2000;
		CLASS_LAYER_ACTIVE = 'active';
		layerPosition = '';
		
		layerPopup.onClickTrigger();
		layerPopup.onClickClose();
		layerPopup.onClickDimmed();
		layerPopup.resize();
		
	},
	open : function ({target, w, h, l, t, callback}) {
		const targetWrap = $('[data-layer-id="' + target + '"]');
		ZINDEX++;
		
		
		//layer
		if (targetWrap.hasClass('layer-alert')) {
			targetWrap.css({
				width: w,
				height : h,
				left : l,
				top : t,
				display : 'block',
				padding: 0
			})

			if (l === undefined) {
				const targetWidth = targetWrap.outerWidth();
				const targetHeight = targetWrap.outerHeight();
				const targetLeft = (targetWidth / 2) * -1;
				const targetTop = (targetHeight / 2) * -1;
				targetWrap.css({
					marginLeft : targetLeft,
					marginTop : targetTop,
					left : '50%',
					top : '50%'
				})
			}
			if (w === undefined) {
				targetWrap.css({
					width:'auto'
				})
			}
			if (h === undefined) {
				targetWrap.css({
					height:'auto'
				})
			}
			layerPosition = targetWrap[0].getAttribute('style');
			targetWrap.attr('data-style',layerPosition);
			if ($WINDOW_MODE === TABLET || $WINDOW_MODE === MOBILE) {
				targetWrap.removeAttr('style');
			}
		}
		targetWrap.addClass(CLASS_LAYER_ACTIVE);
		
		targetWrap.attr('tabindex',0);
		setTimeout(function () {
			targetWrap.focus();

			//callback
			if (callback !== undefined) {
				callback();
			}
		},ANIMATION_TIME)

		$('body .layer-popup').each(function (){
			targetWrap.css('z-index',ZINDEX);
		});

		
	},
	close : function ({target, callback}) {
		const targetWrap = $('[data-layer-id="' + target + '"]');
		const trigger = $('[data-layer-href="' + target + '"]');
		
		targetWrap.removeClass(CLASS_LAYER_ACTIVE);
		targetWrap.removeAttr('tabindex style');
		targetWrap.css('z-index','');
		trigger.focus();
		
		if (callback !== undefined) {
			callback();
		}
		
	},
	onClickTrigger : function () {
		$(document).on('click', '[data-layer-href]' , function (e){

			// a link
			if ($(this).is('a')){
				e.preventDefault();
			}

			const target = $(this).attr('data-layer-href');
			if ($('[data-layer-id="' + target + '"]').hasClass('active')) {
					layerPopup.close({target});
			} else {
				//checkbox flag
				if ($(this).is('.inp')) {
					if ($(this).find('input').is(':checked')){
						layerPopup.open({target});
					}
				} else {
					layerPopup.open({target});
				}
			}
		})
	},
	onClickClose : function () {
		$(document).on('click', '[data-layer-close]' , function (){
			const target = $(this).closest('.layer-popup').attr('data-layer-id');
			layerPopup.close({target});
			console.log(target)
	
		})
	},
	onClickDimmed : function () {
		$(document).on('click', '.layer-dimmed:not(.prevent-close)' , function (){
			const target = $(this).closest('.layer-popup').attr('data-layer-id');
			layerPopup.close({target});
	
		})
	},
	resize : function () {
		$(window).on('resize', function () {
			$('body .layer-popup').each(function (){
				if ($(this).hasClass('active')) {
					const layerStyle = $(this).attr('data-style');
					if ($WINDOW_MODE === TABLET || $WINDOW_MODE === MOBILE) {
						$(this).removeAttr('style');
					} else {
						$(this).attr('style',layerStyle);
					}
				}
			});
		})
		
	}
}

const tabContent = {
	init : function () {
		$tabList = $('.tab-list li');
		$trigger = $tabList.find('button') //toggle;
		tabContent.onClick();
		tabContent.afterLoadHash();
		tabContent.afterLoadTab();
	},
	onClick : function () {
		$trigger.on('click', function (){
			const $parent = $(this).closest('li');
			// 선택 탭 활성화
			$parent.addClass('active');
			$(this).attr({
				'aria-selected': 'true'
			})
			// 기존 탭 비활성화
			$parent.siblings().removeClass('active');
			$parent.siblings().find('button').attr({
				'aria-selected': 'false'
			});
			// 선택된 연관된 탭 패널 활성화
			$('#' + $(this).attr('aria-controls')).addClass('active');
			// 기존 탭 패널 비활성화
			$('#' + $(this).attr('aria-controls')).siblings('.tabpanel').removeClass('active');
			if ($(this).is('button')) {
				history.pushState({}, "", "#" + $(this).attr('id'))
			}
		})
	},
	afterLoadTab  : function () {
		$trigger = $tabList.find('button, a')
		$trigger.each(function (){
			const $parent = $(this).closest('li');
			const $dropDown = $(this).closest('.dropDown');
			if ($parent.hasClass('active')) {
				$(this).attr({
					'aria-selected': 'true'
				})
				$('#' + $(this).attr('aria-controls')).addClass('active');

				//mobile trigger text 
				const $tabParent = $(this).closest('.tab-content');
				const $mobileTrigger = $tabParent.find('.tab-mobile-trigger')
				$mobileTrigger.html('');
				$mobileTrigger.append($(this).html());
			} else {
				$(this).attr({
					'aria-selected': 'false'
				});
				$('#' + $(this).attr('aria-controls')).removeClass('active');
			}
		});
	},
	afterLoadHash : function () {
		const hashTag = window.location.hash;
		if (hashTag.length) {
			$trigger.each(function () {
				const $triggerId = `#${$(this).attr('id')}`
				if ($triggerId === hashTag) {
					const $parent = $(this).closest('li');
					// 선택 탭 활성화
					$parent.addClass('active');
					$parent.siblings().removeClass('active');
				}
			})
		}
	}
}

const accordionContent = {
	init : function () {
		$accordionList = $('.accordion-content .acc-title');
		$trigger = $accordionList.find('button');
		accordionContent.onClick();
		accordionContent.afterLoad();
	},
	onClick : function () {
		$trigger.on('click', function (){
			const parent = $(this).closest('.acc-title')
			if (parent.hasClass('active')) {
				parent.removeClass('active');
				$(this).attr('aria-expanded', 'true');
				$('#' + $(this).attr('aria-controls')).removeClass('active')
			} else {
				parent.addClass('active');
				$(this).attr('aria-expanded', 'false');
				$('#' + $(this).attr('aria-controls')).addClass('active')
			}
		})
	},
	afterLoad  : function () {
		$trigger.each(function (){
			const parent = $(this).closest('.acc-title')
			if (parent.hasClass('active')) {
				$(this).attr('aria-expanded', 'false');
				$('#' + $(this).attr('aria-controls')).addClass('active')
			} else {
				$(this).attr('aria-expanded', 'true');
				$('#' + $(this).attr('aria-controls')).removeClass('active')
			}
		});
	}
}

const uploadFile = function (){
	var dt = new DataTransfer();

	$(".form-input-file input").on('change', function(e){
		var fileWrap = $(this).closest('.form-input-file');
		// 2022.12.06 정동헌 : maxcount 기능 추가
		var maxcount = $(this).attr('maxcount');

		dt = new DataTransfer();
		for(var i = 0; i < this.files.length; i++){
			// 2022.12.06 정동헌 : maxcount 기능 추가
			if (maxcount != undefined && maxcount <= fileWrap.find('ul').children().length) {
				alert('최대 파일 첨부 개수('+ maxcount +')를 초과했습니다.')
				break;
			}
			
			if (fileWrap.hasClass('form-input-thumb')) { // thumbnail
				const file = e.target.files[0];
				const url = URL.createObjectURL(file);
				let fileImage = document.querySelector('.form-input-thumb img')
				fileImage.src = url;
				
			} else { //기본
				//확장자 체크
				const accept = this.accept;
				let ext =  this.files[i].name.split('.').pop().toLowerCase();
				if (accept.includes(ext) === true) {
					let fileBloc = $('<li/>', {class: 'file-block', data_Ext: ext}),
						fileName = $('<span/>', {class: 'name', text: this.files.item(i).name});
					fileBloc.append(fileName).append('<button type="button" class="file-delete" title="삭제"><span class="icon icon-circle-close"></span></button>');
	
					fileWrap.find('ul').append(fileBloc);
				} else {
					alert('첨부 가능한 파일이 아닙니다.')
				}


			}

			dt.items.add(this.files[i]);
	

		};
		// for (let file of this.files) {
		// 	dt.items.add(file);
		// }
		this.files = dt.files;
	});

	$(document).on('click', 'button.file-delete', function (e) {
		var fileWrap = $(this).closest('.form-input-file');
		let name = $(this).siblings('.name').text();

		/**
		 * DataTransfer(dt) 를 유지시키는 것이 아닌 필요할때마다 초기화 후 input.files 로 재생성하게끔 수정
		 * why : 화면 내 file input 이 여러개 존재하는 경우 DataTransfer(dt) 가 중복으로 사용되어, 파일 중복 현상 발생
		 */
		let fileInput = $(this).closest('.form-input-file').find('input');
		dt = new DataTransfer();
		for(let i = 0; i < fileInput[0].files.length; i++){
			dt.items.add(fileInput[0].files[i]);
		}

		for(let i = 0; i < dt.items.length; i++){
			if(name === dt.items[i].getAsFile().name){
				dt.items.remove(i);
				continue;
			}
		}
		fileWrap.find('input')[0].files = dt.files;

		/**
		 * 각 화면별로 파일 삭제 로직이 다르므로,
		 * 기 업로드된 파일(삭제 버튼 태그 내 data-id="~~~" 속성으로 판단)은 element.remove() 호출 X
		 * why : element 날리면 각 화면에서 구현한 이벤트 호출되지 않음
		 */
		let fileId = $(this).data('id');
		if (fileId == undefined || fileId == null) {
			$(this).parent().remove();
		}
	});
}

const headerSearch = {
	init : function () {
		$elHeaderSearch = $('.header-search');
		headerSearch.onTyping();
		headerSearch.onFocusin();
		headerSearch.onFocusout();
		headerSearch.onMouseenter();
		headerSearch.onMouseleave();
		headerSearch.onkeywordSelect();
		headerSearch.onSugBtnClick();
		SUGGEST = '';
		CLASS_SUGGEST_ACTIVE = 'suggest-active';
		CLASS_TYPING = 'typing';
		CLASS_TYPED = 'typed';
		CLASS_FOCUSING = 'focusing';
	},
	Suggest : function () {
		if (SUGGEST === true) { 
			if (!$elHeaderSearch.hasClass(CLASS_SUGGEST_ACTIVE))  {
				$elHeaderSearch.addClass(CLASS_SUGGEST_ACTIVE);
			}
		} else {
			if ($elHeaderSearch.hasClass(CLASS_SUGGEST_ACTIVE))  {
				$elHeaderSearch.removeClass(CLASS_SUGGEST_ACTIVE);
			}
		}
	},
	onTyping : function (){
		$(document).on('keyup', '.header-search input[type=search]', function () {
			if ($(this).val().length > 0) {
				$elHeaderSearch.addClass(CLASS_TYPING);
				$elHeaderSearch.removeClass(CLASS_TYPED);
				// SUGGEST = false;
			} else {
				$elHeaderSearch.removeClass(CLASS_TYPING);
				// SUGGEST = true;
				// headerSearch.Suggest();
			}
		});
	},
	onFocusin : function () {
		$(document).on('focusin', '.header-search input[type=search]', function () {
			$elHeaderSearch.addClass(CLASS_FOCUSING)
		})
	},
	onFocusout : function () {
		$(document).on('focusout', '.header-search input[type=search]', function () {
			$elHeaderSearch.removeClass(CLASS_FOCUSING)
		});
	},
	onMouseenter : function () {
		$(document).on('mouseenter', '.header-search input[type=search]', function () {
			$elHeaderSearch.addClass(CLASS_FOCUSING)
		});
	},
	onMouseleave : function () {
		$(document).on('mouseleave', '.header-search', function () {
			SUGGEST = false;
			headerSearch.Suggest();
			$elHeaderSearch.removeClass(CLASS_FOCUSING)
		});
	},
	onkeywordSelect : function () {
		const searchInput = $('.header-search input[type=search]');
		$(document).on('click', '.search-suggest-list .list .keyword', function (e) {
			e.preventDefault();
			searchInput.val(e.target.innerText);
			
		});
		$(document).on('keydown', '.search-suggest-list .list .keyword', function (e) {
			if (e.which == 13) {
				searchInput.val(e.target.innerText).focus();
			}
		})
	},
	onSugBtnClick : function () {
		$(document).on('click', '.header-search .btn-suggest', function (e) {
			if ($elHeaderSearch.hasClass(CLASS_SUGGEST_ACTIVE)) {
				$elHeaderSearch.removeClass(CLASS_SUGGEST_ACTIVE)
			} else {
				$elHeaderSearch.addClass(CLASS_SUGGEST_ACTIVE)
			}
		})
	}
	
}
const asideScrollTop = function () {
	$('.aside .scroll-top a').on('click', function () {
		$('html, body').animate({scrollTop: '0'}, 300);
	})
}

/*** layerpopup focus out prevent ***/
$(document).ready(function() {

	const focusableElementsString = "a[href], area[href], input:not([disabled]), select:not([disabled]), textarea:not([disabled]), button:not([disabled]), iframe, object, embed, *[tabindex], *[contenteditable], video";

	// dropdown open key event
	$(document).on('keydown', '.header-search.active .suggest-wrap, .trapTab.active, .toggle-layer-inner, .layer-popup, .dropDown.active', function (event, target) {
		trapTabKey($(this), event);
	})


	//focusout prevent event
	function trapTabKey(obj, evt, target) {
		// if tab or shift-tab pressed
		if (evt.which == 9) {
			// get list of all children elements in given object
			const o = obj.find('*');
			// get list of focusable items
			let focusableItems;
			focusableItems = o.filter(focusableElementsString).filter(':visible')
			// get currently focused item
			let focusedItem;
			focusedItem = $(':focus');
			// get the number of focusable items
			var numberOfFocusableItems;
			numberOfFocusableItems = focusableItems.length
			// get the index of the currently focused item
			var focusedItemIndex;
			focusedItemIndex = focusableItems.index(focusedItem);

			if (evt.shiftKey) {
				//back tab
				// if focused on first item and user preses back-tab, go to the last focusable item
				if (focusedItemIndex == 0) {
					focusableItems.get(numberOfFocusableItems - 1).focus();
					evt.preventDefault();
				}
			} else {
				//forward tab
				// if focused on the last item and user preses tab, go to the first focusable item
				if (focusedItemIndex == numberOfFocusableItems - 1) {
					focusableItems.get(0).focus();
					evt.preventDefault();
				}
			}
		}
		if (evt.which == 27) {
			//layer-popup
			if (obj.hasClass('layer-popup')) {
				const target = obj.attr('data-layer-id');
				layerPopup.close({target});
			}
			//toggle layer
			if (obj.hasClass('toggle-layer-inner')) {
				obj.find('.toggle-layer-close').trigger('click').focus();
			}
			//toggle default
			if (obj.hasClass('toggleParent trapTab')) {
				obj.find('.toggleTrigger').trigger('click').focus();
			}
			//search suggest
			if (obj.hasClass('suggest-wrap')) {
				obj.find('.toggleTrigger').trigger('click').focus();
			}
			//dropDown
			if (obj.hasClass('dropDown')) {
				obj.find('.trigger').trigger('click').focus();
			}
		}
	}
	
});

//URL hash tag layerpopup open 추후 삭제 예정
$(document).ready(function() {
	let thisURL = window.location;
	let thisPath = thisURL.pathname;
	let thisHash = thisURL.hash;
	if (thisHash.indexOf('layer') === 1)  {
		thisHash = thisHash.slice(1);
		layerPopup.open({target:thisHash});
	}
	if (thisPath.indexOf('component') === 1)  {
		thisPath = thisPath.replace('/component_', '');
		thisPath = thisPath.replace('.html', '');
		document.title = thisPath;
	}
	if (thisPath.indexOf('UI-C') === 1)  {
		const pageTitle = document.querySelector('.visual-inner h2');
		if (pageTitle !== null) {
			const pageTitleText = pageTitle.innerText;
			document.title = pageTitleText;
		}
	}
	$('body').each(function () {
		var current_path = window.location.pathname;
		var localhost = window.location.href;
		if (localhost.indexOf('http://127.0.0.1:5500') === 0) { //vscode liveServer port
			var vsCode = "vscode://file///C:/KEEP_PORTAL_HTML"; //source file 경로
			var vsCodeHref = '<a class="vscodepath" href=' + '"' + vsCode + current_path + '"></a>';
			$('body').after(vsCodeHref);

			var serverUrl = "http://192.168.0.6:9008"; //source file 경로
			var serverHref = '<a class="serverUrl" href=' + '"' + serverUrl + current_path + '"></a>';
			$('body').after(serverHref);
		}
		if (localhost.indexOf('http://192.168.0.6:9008') === 0) { //vscode liveServer port
			var serverUrl = "http://127.0.0.1:5500"; //source file 경로
			var serverHref = '<a class="serverUrl" href=' + '"' + serverUrl + current_path + '"></a>';
			$('body').after(serverHref);
		}

	})
	
});

const popupCenter = ({url, title, w, h, l, t}) => {

	//open
	// Fixes dual-screen position                             Most browsers      Firefox
	const dualScreenLeft = window.screenLeft !==  undefined ? window.screenLeft : window.screenX;
	const dualScreenTop = window.screenTop !==  undefined   ? window.screenTop  : window.screenY;

	const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
	const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

	const systemZoom = width / window.screen.availWidth;
	let top = '';
	let left = '';
	if (l === undefined) {
		top = (height - h) / 2 / systemZoom + dualScreenTop;
		left = (width - w) / 2 / systemZoom + dualScreenLeft;
	} else {
		top = t + dualScreenTop;
		left = l + dualScreenLeft;
	};
	const newWindow = window.open(url, title, 
		`scrollbars=yes, width=${w}, height=${h}, left=${left}, top=${top}`
	);
	if (window.focus) newWindow.focus();
}