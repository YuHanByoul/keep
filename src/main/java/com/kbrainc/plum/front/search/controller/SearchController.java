package com.kbrainc.plum.front.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.search.model.SearchVo;
import com.kbrainc.plum.front.search.service.SearchService;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

/**
 * 
 * 통합검색 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.front.search.controller - SearchController.java
 * </pre>
 *
 * @ClassName : SearchController
 * @Description : 통합검색 컨트롤러
 * @author : KBRAINC
 * @date : 2023. 3. 30.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class SearchController { 

    @Autowired
    SearchService searchService;
        
    /** 한글초성 */
    private final String [] INITIAL_STRING = {
            "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", 
            "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", 
            "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", 
            "ㅋ", "ㅌ", "ㅍ", "ㅎ" };
    
    /**
    * 한글문자열을 초성으로 변환해서 리턴한다.
    *
    * @Title : getInitialString
    * @Description : 한글문자열을 초성으로 변환해서 리턴한다.
    * @param stringContent 입력문자열
    * @return String 초성변환 문자열
    */
    public String getInitialString(String stringContent) {
        String initialString = "";
        Optional<String> stringContentOp = Optional.ofNullable(stringContent);
        if (stringContentOp.isPresent()) {//전달 문자열 존재
            for (int i = 0; i < stringContentOp.get().length(); i++) {
                String stringLocationInfo = String.valueOf(stringContentOp.get().charAt(i)); //현재 위치의 한글자 추출
                if (stringLocationInfo.matches(".*[가-힣]+.*")) {// 한글일 경우
                    if (stringLocationInfo.charAt(0) >= 0xAC00) { // 음절 시작코드(10진값, 문자) : 0xAC00 (44032 ,'가' )
                        int unicode = stringLocationInfo.charAt(0) - 0xAC00;
                        int index = ((unicode - (unicode % 28))/28)/21;
                        initialString += INITIAL_STRING[index]; 
                    }
                } else { //한글이 아닐경우
                    initialString += stringLocationInfo; //변환없이 그대로 저장
                }
            }
        }
        
        return initialString;
    }
    
    /**
    * 통합검색
    *
    * @Title : search
    * @Description : 통합검색
    * @param search SearchVo객체
    * @param user 사용자세션정보
    * @param model 모델객체
    * @return String
    */
    @GetMapping("/search.html")
    public String search(SearchVo search, @UserInfo UserVo user, Model model) throws Exception {

        if ("".equals(StringUtil.nvl(search.getGtype()))) {
            search.setGtype("all");
        }
        if ("".equals(StringUtil.nvl(search.getPage()))) {
            search.setPage("1");
        }
        if ("".equals(StringUtil.nvl(search.getChkSort()))) {
            search.setChkSort("asc");
        }
        
        SearchVo searchParam = search.clone();
        Map<String, Object> searchMap = null;
        Map<String, Object> countMap = null;
        Map<String, Integer> countInfo = new HashMap<String, Integer>();
        List<Map<String, String>> gtype10 = new ArrayList<Map<String, String>>();
        List<Map<String, String>> gtype20 = new ArrayList<Map<String, String>>();
        List<Map<String, String>> gtype30 = new ArrayList<Map<String, String>>();
        List<Map<String, String>> gtype40 = new ArrayList<Map<String, String>>();
        List<Map<String, String>> gtype50 = new ArrayList<Map<String, String>>();
        
        countInfo.put("all", 0);
        countInfo.put("cnt10", 0);
        countInfo.put("cnt20", 0);
        countInfo.put("cnt30", 0);
        countInfo.put("cnt40", 0);
        countInfo.put("cnt50", 0);
        
        //if (!"".equals(StringUtil.nvl(search.getKeyword()))) {
            if ("all".equals(search.getGtype())) {
                searchParam.setNget("3");
                searchMap = searchService.getSearchInfo(makeQueryString(searchParam));
                countMap = searchMap;
            } else {
                searchParam.setNget("10");
                searchMap = searchService.getSearchInfo(makeQueryString(searchParam));
                searchParam.setGtype("all");
                searchParam.setNget("1");
                countMap = searchService.getSearchInfo(makeQueryString(searchParam));
            }
            
            if (countMap.get("resultSet") != null) {
                List<Map<String, Object>> resultList = ((ArrayList<Map<String, Object>>) ((Map<String, Object>) countMap.get("resultSet")).get("result"));
                Integer gtype10Cnt = (Integer)resultList.get(0).get("totalSize");
                Integer gtype20Cnt = (Integer)resultList.get(1).get("totalSize");
                Integer gtype30Cnt = (Integer)resultList.get(2).get("totalSize");
                Integer gtype40Cnt = (Integer)resultList.get(3).get("totalSize");
                Integer gtype50Cnt = (Integer)resultList.get(4).get("totalSize");
                
                countInfo.put("all", gtype10Cnt + gtype20Cnt + gtype30Cnt + gtype40Cnt + gtype50Cnt);
                countInfo.put("cnt10", gtype10Cnt);
                countInfo.put("cnt20", gtype20Cnt);
                countInfo.put("cnt30", gtype30Cnt);
                countInfo.put("cnt40", gtype40Cnt);
                countInfo.put("cnt50", gtype50Cnt);
            
                if (searchMap.get("resultSet") != null) {
                    ParentRequestVo parentRequestVo = new ParentRequestVo();
                    
                    parentRequestVo.setPageNumber(Integer.valueOf(search.getPage()));
                    List<ParentRequestVo> pagingInfolist = new ArrayList<ParentRequestVo>();
                    pagingInfolist.add(parentRequestVo);
                    model.addAttribute("list", pagingInfolist); // 페이징 데이터
                    
                    Integer resultSize = (Integer) ((Map<String, Object>) searchMap.get("resultSet")).get("resultSize");
                    List<Map<String, Object>> result = (ArrayList<Map<String, Object>>) ((Map<String, Object>) searchMap.get("resultSet")).get("result");
                    
                    if (resultSize == 1) {
                        if ("10".equals(search.getGtype())) {
                            gtype10 = (ArrayList<Map<String, String>>) (result.get(0).get("resultDocuments"));
                            parentRequestVo.setTotalPage((int) Math.ceil(gtype10Cnt.floatValue()/10));
                        } else if ("20".equals(search.getGtype())) {
                            gtype20 = (ArrayList<Map<String, String>>) (result.get(0).get("resultDocuments"));
                            parentRequestVo.setTotalPage((int) Math.ceil(gtype20Cnt.floatValue()/10));
                        } else if ("30".equals(search.getGtype())) {
                            gtype30 = (ArrayList<Map<String, String>>) (result.get(0).get("resultDocuments"));
                            parentRequestVo.setTotalPage((int) Math.ceil(gtype30Cnt.floatValue()/10));
                        } else if ("40".equals(search.getGtype())) {
                            gtype40 = (ArrayList<Map<String, String>>) (result.get(0).get("resultDocuments"));
                            parentRequestVo.setTotalPage((int) Math.ceil(gtype40Cnt.floatValue()/10));
                        } else if ("50".equals(search.getGtype())) {
                            gtype50 = (ArrayList<Map<String, String>>) (result.get(0).get("resultDocuments"));
                            parentRequestVo.setTotalPage((int) Math.ceil(gtype50Cnt.floatValue()/10));
                        }
                    } else {
                        gtype10 = (ArrayList<Map<String, String>>) (result.get(0).get("resultDocuments"));
                        gtype20 = (ArrayList<Map<String, String>>) (result.get(1).get("resultDocuments"));
                        gtype30 = (ArrayList<Map<String, String>>) (result.get(2).get("resultDocuments"));
                        gtype40 = (ArrayList<Map<String, String>>) (result.get(3).get("resultDocuments"));
                        gtype50 = (ArrayList<Map<String, String>>) (result.get(4).get("resultDocuments"));
                    }
                }
            }
        //}
        
        model.addAttribute("gtype10", gtype10);
        model.addAttribute("gtype20", gtype20);
        model.addAttribute("gtype30", gtype30);
        model.addAttribute("gtype40", gtype40);
        model.addAttribute("gtype50", gtype50);
        model.addAttribute("countInfo", countInfo);
        model.addAttribute("search", search);
        return "front/search/search";
    }
    
    /**
    * 쿼리스트링 문자열 반환.
    *
    * @Title : makeQueryString
    * @Description : 쿼리스트링 문자열 반환
    * @param search SearchVo객체
    * @return String 통합검색용 쿼리스트링
    */
    public String makeQueryString(SearchVo search) {
        StringBuffer queryString = new StringBuffer();
        
        if (!"".equals(StringUtil.nvl(search.getGtype()))) {
            queryString.append(String.format("gtype=%s&", search.getGtype()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getKeyword()))) {
            queryString.append(String.format("keyword=%s&", search.getKeyword()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getKeywordNot()))) {
            queryString.append(String.format("keyword_not=%s&", search.getKeywordNot()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getSbjctCd()))) {
            queryString.append(String.format("sbjct_cd=%s&", search.getSbjctCd()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getTrgtCd()))) {
            queryString.append(String.format("trgt_cd=%s&", search.getTrgtCd()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getRgnCd()))) {
            queryString.append(String.format("rgn_cd=%s&", search.getRgnCd()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getTypeCd()))) {
            queryString.append(String.format("type_cd=%s&", search.getTypeCd()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getChkSort()))) {
            queryString.append(String.format("chkSort=%s&", search.getChkSort()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getDateFilter()))) {
            queryString.append(String.format("dateFilter=%s&", search.getDateFilter()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getPage()))) {
            queryString.append(String.format("page=%s&", search.getPage()));
        }
        
        if (!"".equals(StringUtil.nvl(search.getNget()))) {
            queryString.append(String.format("nget=%s&", search.getNget()));
        }
        
        return queryString.toString();
    }
    
    /**
    * 검색어 자동완성 목록 조회.
    *
    * @Title : autocomplete
    * @Description : 검색어 자동완성 목록 조회
    * @param keyword 검색어
    * @return List 자동완성 목록
    */
    @RequestMapping(value = "/search/autocomplete", method = RequestMethod.GET)
    @ResponseBody
    public List autocomplete(@RequestParam(name="keyword", required=true) String keyword) throws Exception {
        Map<String, Object> smartmakerInfo = searchService.getSmartmakerInfo(keyword);
        List<Map<String, String>> smartWordList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) smartmakerInfo.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        
        for (Map<String, String> smartWordMap : smartWordList) {
            smartWordMap.put("CHOSUNG", getInitialString(smartWordMap.get("KEYWORD")));
        }
        
        return smartWordList;
    }
    
    /**
    * 인기 검색어 조회.
    *
    * @Title : autocomplete
    * @Description : 인기 검색어 조회
    * @return List 인기검색어 일간/주간/월간 목록
    */
    @RequestMapping(value = "/search/trndKeyword", method = RequestMethod.GET)
    @ResponseBody
    public Map trndKeyword() throws Exception {
        Map<String, Object> trndMap = new HashMap<String, Object>(); 
        Map<String, Object> keywordDay = searchService.getTrndKeywordDay();
        Map<String, Object> keywordWeek = searchService.getTrndKeywordWeek();
        Map<String, Object> keywordMonth = searchService.getTrndKeywordMonth();
        
        List<Map<String, String>> keywordDayList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) keywordDay.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        List<Map<String, String>> keywordWeekList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) keywordWeek.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        List<Map<String, String>> keywordMonthList = (ArrayList<Map<String, String>>) (((ArrayList<Map<String, Object>>) ((Map<String, Object>) keywordMonth.get("resultSet")).get("result")).get(0).get("resultDocuments"));
        
        trndMap.put("DAY", keywordDayList);
        trndMap.put("WEEK", keywordWeekList);
        trndMap.put("MONTH", keywordMonthList);
        
        return trndMap;
    }
    
    /**
    * 입력문자열에서 html태그를 제거한다.
    *
    * @Title : RemoveHTMLTag
    * @Description : 입력문자열에서 html태그를 제거한다
    * @param changeStr 입력문자열
    * @return String 태그가 제외된 문자열
    */
    
    /**
    * 검색결과 데이터로 이동링크를 생성/반환.
    *
    * @Title : getLink
    * @Description : 검색결과 데이터로 이동링크를 생성/반환
    * @param data resultDocuments의 요소
    * @return String 이동링크
    */
    public static String getLink(Map<String, String> data){
        String gtype = data.get("GTYPE");
        String etype = data.get("ETYPE");
        
        if ("10".equals(gtype)) {
            if ("1".equals(etype)) { // 프로그램/콘텐츠 > 프로그램
                return String.format("/front/envEdu/prgrmDetailForm.html?prgrmid=%s", data.get("PSTID"));
            } else if ("2".equals(etype)) { // 프로그램/콘텐츠 > 콘텐츠
                return String.format("/front/cntnts/cntntsDetailForm.html?cntntsid=%s", data.get("PSTID"));
            } else if ("3".equals(etype)) { // 프로그램/콘텐츠 > 우수환경도서
                return String.format("/front/book/bookDetailForm.html?bookid=%s", data.get("PSTID"));
            } else if ("4".equals(etype)) { // 소개/안내 > 환경교육 정책자료실 > 사업운영자료
                return String.format("/front/intro/envEduPlcyDta/bsnsOperDtaDetail.html?dtaid=%s", data.get("PSTID"));
            } else if ("5".equals(etype)) { // 소개/안내 > 환경교육 정책자료실 > 전문자료
                return String.format("/front/intro/envEduPlcyDta/spcltyDtaDetail.html?dtaid=%s", data.get("PSTID"));
            } else if ("6".equals(etype)) { // 참여/신청 > 환경동아리
                return String.format("/front/cmnty/cmntyPstView.html?cmntyid=%s&bbsid=%s&pstid=%s", data.get("CMNTYID"), data.get("BBSID"), data.get("PSTID"));
            } else if ("7".equals(etype)) { // 참여/신청 > 공모전/이벤트 > 공모전
                return String.format("/front/cntst/cntstDetailForm.html?cntstid=%s", data.get("PSTID"));
            } else if ("8".equals(etype)) { // 참여/신청 > 공모전/이벤트 > 이벤트
                return String.format("/front/evnt/evntDetailForm.html?evntid=%s", data.get("PSTID"));
            } else if ("9".equals(etype)) { // 알림/문의 > 새소식 > 공지사항
                return String.format("/front/bbs/1/view.html?pstid=%s&tabType=%s", data.get("PSTID"), data.get("TABTYPE"));
            } else if ("10".equals(etype)) { // 알림/문의 > 새소식 > 채용정보
                return String.format("/front/bbs/12/view.html?pstid=%s", data.get("PSTID"));
            } else if ("11".equals(etype)) { // 알림/문의 > 언론보도
                return data.get("URL");
            } else if ("12".equals(etype)) { // 알림/문의 > 웹진 > 환경교육 NOW
                return String.format("/front/wbzn/now/enveduDetailForm.html?enveduid=%s", data.get("PSTID"));
            } else if ("13".equals(etype)) { // 알림/문의 > 웹진 > 탄소중립 환경교육
                return String.format("/front/wbzn/carbon/carbonEnveduDetailForm.html?enveduid=%s", data.get("PSTID"));
            } else if ("14".equals(etype)) { // 알림/문의 > 자주하는 질문
                return String.format("/front/faq/faqList.html?faqid=%s", data.get("PSTID"));
            } else if ("15".equals(etype)) { // 참여/신청 > 체험 환경교육 프로그램 지원
                return String.format("/front/bizAply/reqMngDetailForm.html?pcntstid=%s", data.get("PSTID"));
            }
        } else if ("20".equals(gtype)) {
            if ("1".equals(etype)) { // 프로그램/콘텐츠 > 교구 > 환경교육 교구 대여
                return String.format("/front/lend/lendDetail.html?rcritid=%s", data.get("PSTID"));
            } else if ("2".equals(etype)) { // 프로그램/콘텐츠 > 교구 > 환경교육 교구 공동구매
                return String.format("/front/jntpurchs/jntpurchsDetailForm.html?jntpurchsid=%s", data.get("PSTID"));
            } else if ("3".equals(etype)) { // 참여/신청 > 국가 환경교육 > 유아환경교육관 교육 신청
                return String.format("/front/infntAply/infntAplyDetailForm.html?prgrmid=%s", data.get("PSTID"));
            } else if ("4".equals(etype)) { // 참여/신청 > 국가 환경교육 > 푸름이 이동환경교실 교육 신청 
                return String.format("/front/mvmnAply/mvmnAplyDetailForm.html?prgrmid=%s", data.get("PSTID"));
            }
        } else if ("30".equals(gtype)) {
            if ("1".equals(etype)) { // 교육기관/시설 > 지역 환경교육센터 > 국가 환경교육센터
                if ("".equals(data.get("URL"))) {
                    return "/front/enveduCntr/crEnveduCntrList.html";
                } else {                    
                    return data.get("URL");
                }
            } else if ("2".equals(etype)) { // 교육기관/시설 > 지역 환경교육센터 > 광역 환경교육센터
                if ("".equals(data.get("URL"))) {
                    return "/front/enveduCntr/waEnveduCntrList.html";
                } else {                    
                    return data.get("URL");
                }
            } else if ("3".equals(etype)) { // 교육기관/시설 > 지역 환경교육센터 > 기초 환경교육센터
                if ("".equals(data.get("URL"))) {
                    return "/front/enveduCntr/basicEnveduCntrList.html";
                } else {                    
                    return data.get("URL");
                }
            } else if ("4".equals(etype)) { // 교육기관/시설 > 민간환경교육단체
                if ("".equals(data.get("URL"))) {
                    return "/front/enveduCntr/prvtEnveduCntrList.html";
                } else {                    
                    return data.get("URL");
                }
            } else if ("5".equals(etype)) { // 교육기관/시설 > 환경교육사 양성기관 > 양성기관 현황 
                if ("".equals(data.get("URL"))) {
                    return "/front/envtcherTrnngInst/envtcherTrnngInstSituList.html";
                } else {                    
                    return data.get("URL");
                }
            } else if ("6".equals(etype)) { // 참여/신청 > 환경교육시설 예약 
                return String.format("/front/envReqst/resveEnvView.html?fcltid=%s", data.get("PSTID"));
            }
        } else if ("40".equals(gtype)) {
            return String.format("javascript:downloadFileByFileidForSearch('%s', '%s')", data.get("PSTID"), data.get("FILE_IDNTFC_KEY"));
        } else if ("50".equals(gtype)) {
            return data.get("URL");
        } 
        
        return "#";
    }
    
    /**
    * 검색결과 데이터로 path를 반환.
    *
    * @Title : getPath
    * @Description : 검색결과 데이터로 path를 반환.
    * @param data resultDocuments의 요소
    * @return String path
    */
    public static String getPath(Map<String, String> data){
        String gtype = data.get("GTYPE");
        String etype = data.get("ETYPE");
        
        if ("10".equals(gtype)) {
            if ("1".equals(etype)) { // 프로그램/콘텐츠 > 프로그램
                return "프로그램·콘텐츠 > 프로그램";
            } else if ("2".equals(etype)) { // 프로그램/콘텐츠 > 콘텐츠
                return "프로그램·콘텐츠 > 콘텐츠";
            } else if ("3".equals(etype)) { // 프로그램/콘텐츠 > 우수환경도서
                return "프로그램·콘텐츠 > 우수환경도서";
            } else if ("4".equals(etype)) { // 소개/안내 > 환경교육 정책자료실 > 사업운영자료
                return "소개·안내 > 환경교육 정책자료실 > 사업운영자료";
            } else if ("5".equals(etype)) { // 소개/안내 > 환경교육 정책자료실 > 전문자료
                return "소개·안내 > 환경교육 정책자료실 > 전문자료";
            } else if ("6".equals(etype)) { // 참여/신청 > 환경 동아리
                return "참여·신청 > 환경 동아리";
            } else if ("7".equals(etype)) { // 참여/신청 > 공모전/이벤트 > 공모전
                return "참여·신청 > 공모전·이벤트 > 공모전";
            } else if ("8".equals(etype)) { // 참여/신청 > 공모전/이벤트 > 이벤트
                return "참여·신청 > 공모전·이벤트 > 이벤트";
            } else if ("9".equals(etype)) { // 알림/문의 > 새소식 > 공지사항
                return "알림·문의 > 새소식 > 공지사항";
            } else if ("10".equals(etype)) { // 알림/문의 > 새소식 > 채용정보
                return "알림·문의 > 새소식 > 채용정보";
            } else if ("11".equals(etype)) { // 알림/문의 > 언론보도
                return "알림·문의 > 언론보도";
            } else if ("12".equals(etype)) { // 알림/문의 > 웹진 > 환경교육 NOW
                return "알림·문의 > 웹진 > 환경교육 NOW";
            } else if ("13".equals(etype)) { // 알림/문의 > 웹진 > 탄소중립 환경교육
                return "알림·문의 > 웹진 > 탄소중립 환경교육";
            } else if ("14".equals(etype)) { // 알림/문의 > 자주하는 질문
                return "알림·문의 > 자주하는 질문";
            } else if ("15".equals(etype)) { // 참여/신청 > 체험 환경교육 프로그램 지원
                return "참여·신청 > 체험 환경교육 프로그램 지원 > 공모신청";
            }
        } else if ("20".equals(gtype)) {
            if ("1".equals(etype)) { // 프로그램/콘텐츠 > 교구 > 환경교육 교구 대여
                return "프로그램·콘텐츠 > 교구 > 환경교육 교구 대여";
            } else if ("2".equals(etype)) { // 프로그램/콘텐츠 > 교구 > 환경교육 교구 공동구매
                return "프로그램·콘텐츠 > 교구 > 환경교육 교구 공동구매";
            } else if ("3".equals(etype)) { // 참여/신청 > 국가 환경교육 > 유아환경교육관 교육 신청
                return "참여·신청 > 국가환경교육 > 유아환경교육관 교육신청";
            } else if ("4".equals(etype)) { // 참여/신청 > 국가 환경교육 > 푸름이 이동환경교실 교육 신청 
                return "참여·신청 > 국가환경교육 > 푸름이 이동환경교실 교육신청";
            }
        } else if ("30".equals(gtype)) {
            if ("1".equals(etype)) { // 교육기관/시설 > 지역 환경교육센터 > 국가 환경교육센터
                return "교육기관·시설 > 지역 환경교육센터 > 국가 환경교육센터";
            } else if ("2".equals(etype)) { // 교육기관/시설 > 지역 환경교육센터 > 광역 환경교육센터
                return "교육기관·시설 > 지역 환경교육센터 > 광역 환경교육센터";
            } else if ("3".equals(etype)) { // 교육기관/시설 > 지역 환경교육센터 > 기초 환경교육센터
                return "교육기관·시설 > 지역 환경교육센터 > 기초 환경교육센터";
            } else if ("4".equals(etype)) { // 교육기관/시설 > 민간환경교육단체
                return "교육기관·시설 > 민간환경교육단체";
            } else if ("5".equals(etype)) { // 교육기관/시설 > 환경교육사 양성기관 > 양성기관 현황 
                return "교육기관·시설 > 환경교육사 양성기관 > 양성기관 현황";
            } else if ("6".equals(etype)) { // 참여/신청 > 환경교육시설 예약 
                return "참여·신청 > 환경교육시설 예약";
            }
        } else if ("40".equals(gtype)) {
            if ("1".equals(etype)) { // 소개/안내 > 사업운영자료
                return "소개·안내 > 환경교육 정책자료실 > 사업운영자료";
            } else if ("2".equals(etype)) { // 소개/안내 > 전문자료
                return "소개·안내 > 환경교육 정책자료실 > 전문자료";
            } else if ("3".equals(etype)) { // 알림및문의 > 공지사항
                return "알림·문의 > 새 소식 > 공지사항";
            } else if ("4".equals(etype)) { // 알림및문의 > 채용정보
                return "알림·문의 > 새 소식 > 채용정보";
            } else if ("5".equals(etype)) { // 참여/신청 > 체험 환경교육 프로그램 지원 > 공모신청(공모)
                return "참여·신청 > 체험 환경교육 프로그램 지원 > 공모신청";
            } else if ("6".equals(etype)) { // 참여/신청 > 환경동아리 글
                return "참여·신청 > 환경 동아리";
            } else if ("7".equals(etype)) { // 참여/신청 > 공모전
                return "참여·신청 > 공모전·이벤트 > 공모전";
            } else if ("8".equals(etype)) { // 참여/신청 > 이벤트
                return "참여·신청 > 공모전·이벤트 > 이벤트";
            } else if ("9".equals(etype)) { // 프로그램/콘텐츠 > 콘텐츠
                return "프로그램·콘텐츠 > 콘텐츠";
            } else if ("10".equals(etype)) { // 프로그램/콘텐츠 > 환경교육 교구 대여 - 지도안
                return "프로그램·콘텐츠 > 교구 > 환경교육 교구 대여";
            } else if ("11".equals(etype)) { // 프로그램/콘텐츠 > 환경교육 교구 대여 - 교육사진
                return "프로그램·콘텐츠 > 교구 > 환경교육 교구 대여";
            } else if ("12".equals(etype)) { // 프로그램/콘텐츠 > 환경교육 교구 공동구매 - 지도안
                return "프로그램·콘텐츠 > 교구 > 환경교육 교구 공동구매";
            } else if ("13".equals(etype)) { // 프로그램/콘텐츠 > 환경교육 교구 공동구매 - 교육사진
                return "프로그램·콘텐츠 > 교구 > 환경교육 교구 공동구매";
            } else if ("14".equals(etype)) { // 프로그램/콘텐츠 ? 우수환경도서 - 첨부파일
                return "프로그램·콘텐츠 > 우수환경도서";
            } else if ("15".equals(etype)) { // 참여/신청 > 유아환경교육관 교육신청 - 교육소개자료
                return "참여·신청 > 국가환경교육 > 유아환경교육관 교육신청";
            } else if ("16".equals(etype)) { // 참여/신청 > 푸름이 이동환경교실 교육신청 - 교육소개자료
                return "참여·신청 > 국가환경교육 > 푸름이 이동환경교실 교육신청";
            } else if ("17".equals(etype)) { // 참여/신청 > 환경교육시설 예약 - 안내자료
                return "참여·신청 > 환경교육시설 예약";
            }
        }
        
        return "";
    }
}