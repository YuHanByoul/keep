// comm
var gnbBox = $(".gnb .gnb-box");
var btnGnb = $(".gnb .btn-gnb");
var btnGnbSub = $(".gnb .btn-gnb-sub");
var containerElm = $("#container");

// gnb
btnGnb.on("focus focusin mouseover", function() {
  gnbBox.removeClass("is-active");
  $(this).closest(".gnb-box").addClass("is-active");
});
containerElm.on("focusin mouseover", function() {
  gnbBox.removeClass("is-active");
});

// 폰트사이즈
function fontSize() {
  var btnFontUp = $("#header .btn-up");
  var btnFontDown = $("#header .btn-down");

  btnFontUp.on("click", function() {
    // $("html, body").css("font-size", "+=1");
    $("body").css("zoom", "+=10%");
  });

  btnFontDown.on("click", function() {
    // $("html, body").css("font-size", "-=1");
    $("body").css("zoom", "-=10%");
  });
} fontSize();

// gnb mobile
function gnbMobile() {
  var btnMenuMobile = $(".header .btn-menu-mobile");
  var headerMenuMobile = $(".header .header-menu-mobile");
  var btnGnbClse = $(".header .btn-gnb-clse");
  var gnbMobile = $(".gnb-mobile");
  var btnMobileGnb = $(".gnb-mobile .btn-gnb");
  var btnMobileGnbSub = $(".gnb-mobile .btn-gnb-sub");

  // 모바일 gnb 열기
  btnMenuMobile.on("click", function() {
    headerMenuMobile.hide();
    gnbMobile.slideDown(200);
    btnGnbClse.show();
  });

  // 모바일 gnb 닫기
  btnGnbClse.on("click", function() {
    gnbMobile.slideUp(200);
    headerMenuMobile.show();
    btnGnbClse.hide();
  });

  // 모바일 gnb 서브메뉴 열기
  btnMobileGnb.on("click", function() {  
    $(this).closest(".gnb-box").find(".gnb-sub").slideToggle(200);
  });

  // 모바일 gnb 서브메뉴2 열기
  btnMobileGnbSub.on("click", function() {  
    $(this).closest("li").find(".gnb-sub-sub").slideToggle(200);
  });

} gnbMobile();

// lnb
function lnb() {
  var btnLnbSub = $(".js-lnb .btn-lnb-sub");

  $(".lnb .lnb-sub .is-on .lnb-sub-sub").show();

  btnLnbSub.on("click", function() {
    $(this).closest("li").toggleClass("is-on").find(".lnb-sub-sub").slideToggle(200);
  });
} lnb();

// 자료제공 아코디언
function studyDataSlide() {
  btnStudyDataSlide = $(".section-study .learning-data .btn-slide");

  btnStudyDataSlide.on("click", function() {
    $(this).toggleClass("btn-slide-down btn-slide-up");
    $(this).closest("dd").find(".conts").toggleClass("is-on");
  });
} studyDataSlide();

// resize
var lastWindowWidth = $(window).width();
window.addEventListener("resize", function() {
  var windowWidth = $(window).width();

  if (windowWidth > 1024) {
    $(".gnb-mobile").hide();
    $(".header .btn-gnb-clse").hide();
  } else {
    $(".header .header-menu-mobile").removeAttr("style");
  }
});

// scroll
window.addEventListener("scroll", function() {

});