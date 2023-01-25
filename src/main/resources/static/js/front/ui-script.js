
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
	uploadFile();
	headerSearch.init();
	asideScrollTop();
});

let $WINDOW_MODE = '';
const DESKTOP = 'DESKTOP';
const TABLET = 'TABLET';
const MOBILE = 'MOBILE';
const $html =  $('html');

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
		var DESKTOP_SIZE = $windowWidth > 1280;
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
	},
	onResize : function () {
		$(window).on('resize', function () {
			$windowWidth = $(window).outerWidth();
			resizeWindow.sizeCheck();
			console.log($WINDOW_MODE)
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

		// $btnActiveGnb = $('.mobile-gnb button');
		// $btnInActiveGnb = $('.mobile-menu .gnb-mobile-close');
		ANIMATION_TIME = 300;
		// DESKTOP_GNB_ACTIVECLASS = 'gnb-active'
		MOBILE_GNB_ACTIVECLASS = 'mobile-gnb-active'
		gnb.onHover();
		gnb.onClick();
		gnb.onFocus();
		gnb.onActiveDep01();
		gnb.onLoadDep01();
		gnb.onActiveDep02();
		gnb.onLoadDep02();

		
		if ($header.length) {
			$headerHeight = $header.outerHeight();
			$headerOffsetTop = $header.offset().top;
			gnb.onResize();
			gnb.onScroll();
		}

		if ($WINDOW_MODE === MOBILE) {
			// $elGnb.css('display','none');
		};
	},
	open : function () {
		if ($WINDOW_MODE === DESKTOP || TABLET) {
			$Dep01.find('>.active').find($Dep02).stop().fadeIn(0);
		}
		if ($WINDOW_MODE === MOBILE) {
			$elGnb.stop().show(0, function () {
				// $header.addClass(DESKTOP_GNB_ACTIVECLASS);
				$html.addClass(MOBILE_GNB_ACTIVECLASS);
				// $btnInActiveGnb.focus();
			});
		}
	},
	close : function (){
		// $header.removeClass(DESKTOP_GNB_ACTIVECLASS);
		if ($WINDOW_MODE === DESKTOP || TABLET) {
			$Dep01List.find($Dep02).stop().fadeOut(150, function () {
				$(this).attr('style','display:none')
			});
		}
		if ($WINDOW_MODE === MOBILE) {
			setTimeout(function () {
				$elGnb.css('display','none');
				$html.removeClass(MOBILE_GNB_ACTIVECLASS);
				$btnActiveGnb.focus();
			},ANIMATION_TIME);
		}
	},
	onHover : function (){
		$Dep01List.on('mouseenter', function (){
			if ($WINDOW_MODE === DESKTOP || TABLET) {
				$(this).siblings('li').removeClass('active');
				$(this).addClass('active');
				gnb.open();
			}
		})
		$Dep01List.on('mouseleave', function (){
			if ($WINDOW_MODE === DESKTOP || TABLET) {
				$(this).removeClass('active');
				gnb.close();
			}
		})
	},
	onFocus : function (){
		$Dep01List.on('focusin', function (){
			if ($WINDOW_MODE === DESKTOP || TABLET) {
				$(this).siblings('li').removeClass('active');
				$(this).addClass('active');
				$(this).siblings('li').find($Dep02).stop().fadeOut(ANIMATION_TIME, function () {
					$(this).attr('style','display:none')
				});
				$(this).find($Dep02).stop().fadeIn(ANIMATION_TIME);
			}
		})
		$Dep01List.on('focusout', function (){
			if ($WINDOW_MODE === DESKTOP || TABLET) {
				$(this).removeClass('active');
			}
		})
		$gnbOnFocusStart.on('focus', function (){
			gnb.close();
		})
		$gnbOnFocusEnd.on('focus', function (){
			gnb.close();
		})
	},
	onClick : function () {
		// $btnActiveGnb.on('click', function (){
		// 	if (!$header.hasClass('.gnb-active')) { 
		// 		gnb.open();
		// 	}
		// })
		// $btnInActiveGnb.on('click', function () {
		// 	gnb.close();
		// })
	},
	onActiveDep01 : function () {
		// $Dep01.find('>li>a').on('click', function (e) {
		// 	var par = $(this).closest('li');
		// 	if ($WINDOW_MODE === MOBILE && !par.hasClass('dep01Link')) {
		// 		e.preventDefault();
		// 		if (!par.hasClass('open')) {
		// 			par.addClass('open');
		// 			par.siblings('li').removeClass('open');
		// 		}
		// 	}
		// })
	},
	onLoadDep01 : function () {
		// $Dep01.find('>li').each(function () {
		// 	if ($(this).hasClass('active')) { 
		// 		$(this).addClass('open');
		// 	}
		// })
	},
	onActiveDep02 : function () {
		// $Dep02.find('>li>a').on('click', function (e) {
		// 	if ($WINDOW_MODE === MOBILE) {
		// 		var par = $(this).closest('li');
		// 		if (par.find($Dep03).length) {
		// 			e.preventDefault();
		// 			if (!par.hasClass('open')) {
		// 				par.addClass('open');
		// 				par.find($Dep03).stop().slideDown(ANIMATION_TIME);
		// 				par.siblings('li').removeClass('open');
		// 				par.siblings('li').find($Dep03).stop().slideUp(ANIMATION_TIME, function () {
		// 					$(this).removeAttr('style','')
		// 				});
		// 			} else {
		// 				par.removeClass('open');
		// 				par.find($Dep03).stop().slideUp(ANIMATION_TIME, function () {
		// 					$(this).removeAttr('style','')
		// 				});
	
		// 			}
		// 		}
		// 	}
		// })
	},
	onLoadDep02 : function () {
		// $Dep02.find('>li').each(function () {
		// 	if ($(this).find($Dep03).length) {
		// 		$(this).addClass('hasDep03')
		// 	}
		// 	if ($(this).hasClass('active')) { 
		// 		$(this).addClass('open');
		// 		$(this).find($Dep03).stop().slideDown(ANIMATION_TIME);
		// 	}
		// })
	},
	onResize : function () {
		if ($WINDOW_MODE === DESKTOP) {
			if ($headerOffsetTop > $headerHeight) {
				$html.addClass('header-medium');
			} else {
				$html.removeClass('header-medium');
			}
		}
	},
	onScroll : function () {
		if ($WINDOW_MODE === DESKTOP || TABLET) {
			$(window).on('scroll', function () {
				$headerOffsetTop = $header.offset().top;
				gnb.onResize();
			})
		}

	}
}


const navigation = {
	init : function () {
		$navList = $('.navigation');
		$dep01 = $navList.find('.dep01 > li');
		$dep02 = $dep01.find('.dep02');
		DEP02_INNER = 'dep02-inner';
		ANIMATION_TIME = 300;
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
			const offset = this.offsetHeight - this.clientHeight;
			const resizeTextarea = function(el) {
				$(el).css('height', 'auto').css('height', el.scrollHeight + offset);
				$(el).addClass('areaResize')
			};
			$(this).on('keyup input', function() {
				resizeTextarea(this);
			});
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
		$(document).on('click', '.table-col .tr-check input[type=checkbox]' , function () {
			const tr = $(this).closest('.tr-check');
			if ($(this).is(':checked')) {
				tr.addClass('tr-checked');
			} else {
				tr.removeClass('tr-checked');
			}
		})
	},
	widthEnabled : function () {
		$('.form-input input,.form-input select,.form-input textarea').each(function () {
			if ($(this).data('width')) {
				$(this).css('width',$(this).data('width'));
			}
		})
	},
	widthDisabled : function () {
		$('.form-input input,.form-input select,.form-input textarea').each(function () {
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

	}

} 

const dropDown = {
	init : function (){
		$elDropdown = $('.dropDown');
		$elTrigger = $elDropdown.find('.trigger');
		$elTarget = $elDropdown.find('.target');
		dropDown.open();
		dropDown.close();
		dropDown.select();
		dropDown.onKeybind();
	},
	open : function () {
		$(document).on('click', '.dropDown >.trigger', function (e){
			const par = $(this).closest($elDropdown);
			if (par.find('>.target').length){
				// e.stopPropagation();
				if (!par.hasClass('active')){
					$('.dropDown.active').each(function () {
						$(this).removeClass('active');
						$(this).find('>.target').slideUp(0);
					})
					par.addClass('active');
					par.find('>.target').slideDown(0);
				} else {
					par.removeClass('active')
					par.find('>.target').slideUp(0);
				}
			}
		});
	},
	close : function (){
		$('.dropDown.active').each(function () {
			$(this).removeClass('active');
			$(this).find('>.target').slideUp(0);
		})
	},
	select :function () {
		$elTarget.find('a').on('click', function (){
			const par = $(this).closest($elDropdown);
			const value = $(this).html();
			par.find($elTrigger).html(value);
			par.removeClass('active')
			par.find('>.target').slideUp(0);
		});
	},
	onKeybind : function () {
		$(document).on('keydown', '.dropDown.active', function (event) {
			if (event.keyCode === 27) {
				dropDown.close();
			}
		})
	}
}

const toggleActiveClass = {
	init : function () {
		CLASS_ACTIVE = 'active';
		toggleActiveClass.onClick();
		let par = '';
		let togglePar = '';
		
	},
	open : function () {
		par.addClass(CLASS_ACTIVE);
		$(this).find('.on').attr('aria-hidden','true');
		$(this).find('.off').attr('aria-hidden','false');
	},
	close : function () {
		par.removeClass(CLASS_ACTIVE);
		$(this).find('.on').attr('aria-hidden','false');
		$(this).find('.off').attr('aria-hidden','true');
	},
	onClick : function () {
		$(document).on('click', '.toggleTrigger', function (){
			par = $(this).closest('.toggleParent');
			togglePar = $(this).closest('.toggle-layer');
			if (par.hasClass(CLASS_ACTIVE)){
				toggleActiveClass.close();
				if (togglePar.length) {
					par.find('.toggleTrigger:not(.toggle-layer-close)').focus();
				}
			} else {
				toggleActiveClass.open();
				if (par.hasClass('toggle-layer')) {
					toggleActiveClass.onClickToggleLayer();
				}
				if (par.siblings('.toggleParent').length) {
					par.siblings('.toggleParent').each(function () {
						$(this).removeClass('active');
					})
				}
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
		ACTIVECLASS = 'active';
		ANIMATION_TIME = 300;
		layerPopup.onClickTrigger();
		layerPopup.onClickClose();
		layerPopup.onClickDimmed();
		
	},
	open : function (target) {
		ZINDEX++;
		$('[data-layer-id="' + target + '"]').addClass(ACTIVECLASS);
		
		$('[data-layer-id="' + target + '"]').attr('tabindex',0);
		setTimeout(function () {
			$('[data-layer-id="' + target + '"]').focus();
		},ANIMATION_TIME)

		$('body .layer-popup').each(function (){
			$('[data-layer-id="' + target + '"]').css('z-index',ZINDEX);
		});
	},
	close : function (target) {
		$('[data-layer-id="' + target + '"]').removeClass(ACTIVECLASS);
		$('[data-layer-id="' + target + '"]').removeAttr('tabindex');
		$('[data-layer-id="' + target + '"]').css('z-index','');
		$('[data-layer-href="' + target + '"]').focus();
	},
	onClickTrigger : function () {
		$(document).on('click', '[data-layer-href]' , function (e){
			if ($(this).is('a')){
				e.preventDefault();
			}
			const target = $(this).attr('data-layer-href');
			if ($('[data-layer-id="' + target + '"]').hasClass('active')) {
				layerPopup.close(target);
			} else {
				layerPopup.open(target);
			}
			
			
		})
	},
	onClickClose : function () {
		$(document).on('click', '[data-layer-close]' , function (){
			const target = $(this).closest('.layer-popup').attr('data-layer-id');
			layerPopup.close(target);
	
		})
	},
	onClickDimmed : function () {
		$(document).on('click', '.layer-dimmed' , function (){
			const target = $(this).closest('.layer-popup').attr('data-layer-id');
			layerPopup.close(target);
	
		})
	}
}

const tabContent = {
	init : function () {
		$tabList = $('.tab-list li');
		$trigger = $tabList.find('button') //toggle;
		tabContent.onClick();
		tabContent.afterLoad();
	},
	onClick : function () {
		$trigger.on('click', function (){
			const $parent = $(this).closest('li');
			// 선택 탭 활성화
			$parent.addClass('active');
			$(this).attr({
				// 'tabindex': '0',
				'aria-selected': 'true'
			}).focus();
			// 기존 탭 비활성화
			$parent.siblings().removeClass('active');
			$parent.siblings().find('button').attr({
				// 'tabindex': '-1',
				'aria-selected': 'false'
			});
			// 선택된 연관된 탭 패널 활성화
			// $('#' + $(this).attr('aria-controls')).attr('tabindex', '0').addClass('active')
			$('#' + $(this).attr('aria-controls')).addClass('active')
			// 기존 탭 패널 비활성화
			// $('#' + $(this).attr('aria-controls')).siblings('.tabpanel').attr('tabindex', '-1').removeClass('active');
			$('#' + $(this).attr('aria-controls')).siblings('.tabpanel').removeClass('active');
		})
	},
	afterLoad  : function () {
		$trigger = $tabList.find('button, a')
		$trigger.each(function (){
			const $parent = $(this).closest('li');
			if ($parent.hasClass('active')) {
				$(this).attr({
					// 'tabindex': '0',
					'aria-selected': 'true'
				})
				// $('#' + $(this).attr('aria-controls')).attr('tabindex', '0').addClass('active');
				$('#' + $(this).attr('aria-controls')).addClass('active');
			} else {
				$(this).attr({
					// 'tabindex': '-1',
					'aria-selected': 'false'
				});
				// $('#' + $(this).attr('aria-controls')).attr('tabindex', '-1').removeClass('active');
				$('#' + $(this).attr('aria-controls')).removeClass('active');
			}
		});
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
			
			// 2022.12.07 정동헌 : 동일한 기준으로 for문 2번 돌던거 하나로 병합

			
			if (fileWrap.hasClass('form-input-thumb')) { // thumbnail
				const file = e.target.files[0];
				const url = URL.createObjectURL(file);
				let fileImage = document.querySelector('.form-input-thumb img')
				fileImage.src = url;
				
			} else { //기본
				//확장자 체크
				var ext =  this.files[i].name.split('.').pop().toLowerCase();
				// console.log(ext);

				let fileBloc = $('<li/>', {class: 'file-block', data_Ext: ext}),
					fileName = $('<span/>', {class: 'name', text: this.files.item(i).name});
				fileBloc.append(fileName).append('<button type="button" class="file-delete" title="삭제"><span class="icon icon-circle-close"></span></button>');

				fileWrap.find('ul').append(fileBloc);

			}

			dt.items.add(this.files[i]);
	

		};
		// for (let file of this.files) {
		// 	dt.items.add(file);
		// }
		this.files = dt.files;

		fileWrap.find('button.file-delete').on('click', function(e){
			let name = $(this).siblings('.name').text();

			/**
			 * 2022.12.26 정동헌
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
			 * 2022.12.08 정동헌
			 * 각 화면별로 파일 삭제 로직이 다르므로,
			 * 기 업로드된 파일(삭제 버튼 태그 내 data-id="~~~" 속성으로 판단)은 element.remove() 호출 X
			 * why : element 날리면 각 화면에서 구현한 이벤트 호출되지 않음
			 */
			let fileId = $(this).data('id');
			if (fileId == undefined || fileId == null) {
				$(this).parent().remove();
			}
		});
	});
}

const headerSearch = {
	init : function () {
		$el = $('.header-search');
		headerSearch.onTyping();
		headerSearch.onFocusin();
		headerSearch.onFocusout();
		headerSearch.onMouseenter();
		headerSearch.onMouseleave();
		headerSearch.onkeywordSelect();
		SUGGEST = '';
		CLASS_ACTIVE = 'active';
		CLASS_TYPING = 'typing';
		CLASS_TYPED = 'typed';
		CLASS_FOCUSING = 'focusing';
	},
	Suggest : function () {
		if (SUGGEST === true) { 
			if (!$el.hasClass(CLASS_ACTIVE))  {
				$el.addClass(CLASS_ACTIVE);
			}
		} else {
			if ($el.hasClass(CLASS_ACTIVE))  {
				$el.removeClass(CLASS_ACTIVE);
			}
		}
	},
	onTyping : function (){
		$(document).on('keyup', '.header-search input[type=search]', function () {
			if ($(this).val().length > 0) {
				$el.addClass(CLASS_TYPING);
				$el.removeClass(CLASS_TYPED);
				// SUGGEST = false;
			} else {
				$el.removeClass(CLASS_TYPING);
				// SUGGEST = true;
				// headerSearch.Suggest();
			}
		});
	},
	onFocusin : function () {
		$(document).on('focusin', '.header-search input[type=search]', function () {
			$el.addClass(CLASS_FOCUSING)
		})
	},
	onFocusout : function () {
		$(document).on('focusout', '.header-search input[type=search]', function () {
			$el.removeClass(CLASS_FOCUSING)
		});
	},
	onMouseenter : function () {
		$(document).on('mouseenter', '.header-search input[type=search]', function () {
			$el.addClass(CLASS_FOCUSING)
		});
	},
	onMouseleave : function () {
		$(document).on('mouseleave', '.header-search:not(.typing) input[type=search], .header-search:not(.typing) .search-suggest', function () {
			SUGGEST = false;
			headerSearch.Suggest();
			$el.removeClass(CLASS_FOCUSING)
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
	$(document).on('keydown', '.header-search.active .suggest-wrap, .trapTab.active, .toggle-layer-inner, .layer-popup', function (event, target) {
		trapTabKey($(this), event);
	})


	//focusout prevent event
	function trapTabKey(obj, evt) {
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
				const layerName = obj.attr('data-layer-id');
				layerPopup.close(layerName);
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
		}
	}
});