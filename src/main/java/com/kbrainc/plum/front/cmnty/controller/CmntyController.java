package com.kbrainc.plum.front.cmnty.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.cmnty.model.*;
import com.kbrainc.plum.front.cmnty.service.CmntyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 환경동아리 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.controller
 * - CmntyController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyController
 * @Description : 환경동아리 Controller
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller("front.CmntyController")
@Alias("front.CmntyController")
public class CmntyController {
    @Resource(name = "front.CmntyService")
    private CmntyService cmntyService;
    @Autowired
    private ResCodeService resCodeService;
    @Autowired
    private FileServiceImpl fileService;

    /**
     * 환경동아리 목록
     * Title : cmntyList
     * Description : 환경동아리 목록
     *
     * @param cmntyVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/cmnty/cmntyList.html")
    public String cmntyList(@UserInfo UserVo userVo, CmntyVo cmntyVo, Model model) throws Exception{
        cmntyVo.setUser(userVo);
        List<CmntyVo> cmntyList = cmntyService.selectCmntyList(cmntyVo);
        model.addAttribute("totalCount", cmntyList.size() > 0 ? cmntyList.get(0).getTotalCount() : 0);
        model.addAttribute("list",cmntyList);
        model.addAttribute("paramVo",cmntyVo);
        model.addAttribute("esterid", cmntyVo.getEsterid());
        return "front/cmnty/cmntyList";
    }

    /**
     * 환경동아리 가입
     * Title : insertCmntyMbr
     * Description : 환경동아리 가입
     *
     * @param cmntyVo
     * @param user
     * @return map
     */
    @RequestMapping(value="/front/cmnty/insertCmntyMbr.do")
    @ResponseBody
    public Map<String,Object> insertCmntyMbr(CmntyVo cmntyVo, @UserInfo UserVo user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;
        CmntyVo cmntyInfo = cmntyService.selectCmntyInfo(cmntyVo);

        cmntyVo.setUser(user);
        cmntyVo.setEsterid(cmntyInfo.getEsterid());
        cmntyVo.setJoinaprvmthdCd(cmntyInfo.getJoinaprvmthdCd());
        if(cmntyInfo != null){
            result = cmntyService.insertCmntyMbr(cmntyVo);
        }

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "가입신청 되었습니다.\n관리자의 승인 후 가입 처리됩니다.");
            if("115101".equals(cmntyInfo.getJoinaprvmthdCd())){
                resultMap.put("msg", "가입되었습니다.");
                resultMap.put("url","/front/cmnty/cmntyPstList.html");
            }
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "가입신청 실패하였습니다");
        }
        return resultMap;
    }

    /**
     * 환경동아리 가입신청취소/삭제/반려
     * Title : deleteCmntyMbr
     * Description : 환경동아리 가입신청취소/삭제/반려
     *
     * @param cmntyVo
     * @param user
     * @return map
     */
    @RequestMapping(value="/front/cmnty/deleteCmntyMbr.do")
    @ResponseBody
    public Map<String,Object> deleteCmntyMbr(CmntyVo cmntyVo, @UserInfo UserVo user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        cmntyVo.setUser(user);
        result = cmntyService.deleteCmntyMbr(cmntyVo);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "가입신청이 취소되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "가입신청 취소에 실패하였습니다");
        }
        return resultMap;
    }

    /**
     * 환경동아리 탈퇴/권한부여/권한철회/다시초대/승인/복원
     * Title : updateCmntyMbr
     * Description : 환경동아리 탈퇴/권한부여/권한철회/다시초대/승인/복원
     *
     * @param cmntyVo
     * @param user
     * @return map
     */
    @RequestMapping(value="/front/cmnty/updateCmntyMbr.do")
    @ResponseBody
    public Map<String,Object> updateCmntyMbr(CmntyVo cmntyVo, @UserInfo UserVo user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        cmntyVo.setUser(user);
        result = cmntyService.updateCmntyMbr(cmntyVo);

        if(result){
            resultMap.put("result", true);
        } else {
            resultMap.put("result", false);
        }
        return resultMap;
    }

    /**
     * 환경동아리 개설 화면
     * Title : cmntyForm
     * Description : 환경동아리 개설 화면
     *
     * @param cmntyVo
     * @param model
     * @return string
     */
    @RequestMapping(value="/front/cmnty/cmntyForm.html")
    public String cmntyForm(CmntyVo cmntyVo, Model model, @UserInfo UserVo user) throws Exception {
        cmntyVo.setUser(user);
        model.addAttribute("cmntyVo",cmntyVo);
        model.addAttribute("joinaprvmthdCdList", resCodeService.getCodeList("115"));
        model.addAttribute("tmplatList",cmntyService.selectCmntyTmplatList(null));
        return "front/cmnty/cmntyForm";
    }

    /**
     * 환경동아리 개설
     * Title : insertCmnty
     * Description : 환경동아리 개설
     *
     * @param cmntyVo
     * @param user
     * @return map
     */
    @RequestMapping(value="/front/cmnty/insertCmnty.do")
    @ResponseBody
    public Map<String,Object> insertCmnty(CmntyVo cmntyVo, @UserInfo UserVo user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        cmntyVo.setUser(user);
        cmntyVo.setEsterYn("Y");
        result = cmntyService.insertCmnty(cmntyVo);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "개설이 완료되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "개설에 실패하였습니다.");
        }
        return resultMap;
    }

    /**
     * 환경동아리 상세 목록
     * Title : cmntyPstList
     * Description : 환경동아리 상세 목록
     *
     * @param paramVo
     * @param model
     * @param user
     * @return string
     */
    @RequestMapping(value="/front/cmnty/cmntyPstList.html")
    public String cmntyPstList(CmntyBbsVo paramVo, Model model, @UserInfo UserVo user){
        paramVo.setUser(user);
        //환경동아리 정보 & 회원 상태 값
        CmntyVo cmntyVo = new CmntyVo();
        cmntyVo.setUser(user);
        cmntyVo.setCmntyid(paramVo.getCmntyid());
        CmntyVo cmntyInfo = cmntyService.selectCmntyInfo(cmntyVo);
        //환경동아리 카테고리 순서
        List<CmntyCtgryVo> cmntyCtgryList = cmntyService.selectCmntyCtgryList(paramVo);
        //환경동아리 게시판 탭 이동 구분
        if("N".equals(paramVo.getSearchYn() == null ? "" : paramVo.getSearchYn())){
            paramVo.setSearchKeyword(null);
            paramVo.setSearchType(null);
            paramVo.setRowPerPage(10);
            paramVo.setPageNumber(1);
        }
        //환경동아리 게시판 정보
        if(paramVo.getBbsid() == null){
            paramVo.setBbsid(cmntyCtgryList.get(0).getBbsid());
        }
        CmntyBbsVo bbsInfo = cmntyService.selectBbsInfo(paramVo);
        //환경동아리 게시판 목록 조회(공통)
        paramVo.setHotUseCrtrHits(bbsInfo.getHotUseCrtrHits());
        paramVo.setNewIndictDaycnt(bbsInfo.getNewIndictDaycnt());
        paramVo.setCmntyBbsTmplatid(bbsInfo.getCmntyBbsTmplatid());
        paramVo.setHotUseYn(bbsInfo.getHotUseYn());
        paramVo.setNewUseYn(bbsInfo.getNewUseYn());
        if ("1".equals(bbsInfo.getClsfCd() != null ? bbsInfo.getClsfCd() : "0")) {
            paramVo.setOrderField("GRP DESC,SORTORDR");
        } else {
            paramVo.setOrderField("FIXORDER ASC,GRP DESC,SORTORDR");
        }
        List<CmntyPstVo> cmntyBbsList = cmntyService.selectCmntyBbsList(paramVo);
        model.addAttribute("cmntyCtgryList", cmntyCtgryList);
        model.addAttribute("list", cmntyBbsList);
        model.addAttribute("cmntyInfo", cmntyInfo);
        model.addAttribute("paramVo", paramVo);
        model.addAttribute("totalCount",cmntyBbsList.size() > 0 ? cmntyBbsList.get(0).getTotalCount() : 0);
        return "front/cmnty/cmntyPstList";
    }

    /**
     * 환경동아리 설정 화면
     * Title : cmntySetForm
     * Description : 환경동아리 설정 화면
     *
     * @param paramVo
     * @param model
     * @param user
     * @return string
     * @throws Exception
     */
    @RequestMapping(value="/front/cmnty/cmntySetForm.html")
    public String cmntySetForm(CmntyVo paramVo, Model model, @UserInfo UserVo user) throws Exception {
        paramVo.setUser(user);
        CmntyVo cmntyInfo = cmntyService.selectCmntyInfo(paramVo);
        model.addAttribute("cmntyInfo",cmntyInfo);
        model.addAttribute("paramVo",paramVo);
        model.addAttribute("joinaprvmthdCdList", resCodeService.getCodeList("115"));
        model.addAttribute("tmplatList",cmntyService.selectCmntyTmplatList(paramVo.getCmntyid()));
        return "front/cmnty/cmntySetForm";
    }

    /**
     * 환경동아리 폐쇄
     * Title : deleteCmnty
     * Description : 환경동아리 폐쇄
     *
     * @param cmntyVo
     * @param user
     * @return map
     */
    @RequestMapping(value="/front/cmnty/deleteCmnty.do")
    @ResponseBody
    public Map<String,Object> deleteCmnty(CmntyVo cmntyVo, @UserInfo UserVo user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        cmntyVo.setUser(user);
        cmntyVo.setOperYn("N");
        CmntyMbrVo cmntyMbrVo = new CmntyMbrVo();
        cmntyMbrVo.setCmntyid(cmntyVo.getCmntyid());
        cmntyMbrVo.setAuthrtCd("117101");
        Integer listSize = cmntyService.selectCmntyMbrList(cmntyMbrVo).size();
        if(listSize < 1){
            result = cmntyService.updateCmnty(cmntyVo);
        }

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "커뮤니티가 폐쇄되었습니다.");
        } else if(listSize > 0){
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "회원이 존재할 경우 커뮤니티를 폐쇄할 수 없습니다.");
        }else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "커뮤니티 폐쇄에 실패하였습니다.");
        }
        return resultMap;
    }

    /**
     * 환경동아리 수정
     * Title : updateCmnty
     * Description : 환경동아리 수정
     *
     * @param cmntyVo
     * @param user
     * @return map
     */
    @RequestMapping(value="/front/cmnty/updateCmnty.do")
    @ResponseBody
    public Map<String,Object> updateCmnty(CmntyVo cmntyVo, @UserInfo UserVo user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        cmntyVo.setUser(user);
        cmntyVo.setOperYn("Y");
        result = cmntyService.saveCmnty(cmntyVo);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하셨습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하셨습니다.");
        }
        return resultMap;
    }

    /**
     * 환경동아리 회원관리 목록
     * Title : cmntyMbrList
     * Description : 환경동아리 회원관리 목록
     *
     * @param paramVo
     * @param model
     * @param user
     * @return string
     */
    @RequestMapping(value = "/front/cmnty/cmntyMbrList.html")
    public String cmntyMbrList(CmntyMbrVo paramVo, Model model, @UserInfo UserVo user){
        CmntyVo cmntyVo = new CmntyVo();
        cmntyVo.setUser(user);
        cmntyVo.setCmntyid(paramVo.getCmntyid());
        CmntyVo cmntyInfo = cmntyService.selectCmntyInfo(cmntyVo);
        paramVo.setOrderField("ORDR ASC, MDFCN_DT");
        List<CmntyMbrVo> list = cmntyService.selectCmntyMbrList(paramVo);
        model.addAttribute("list",list);
        model.addAttribute("cmntyInfo",cmntyInfo);
        model.addAttribute("totalCount",list.size() > 0 ? list.get(0).getTotalCount() : 0);
        model.addAttribute("paramVo",paramVo);
        model.addAttribute("userid",user.getUserid());
        return "front/cmnty/cmntyMbrList";
    }

    /**
     * 환경동아리 회원검색
     * Title : selectMbr
     * Description : 환경동아리 회원검색
     *
     * @param userNm
     * @return map
     */
    @RequestMapping(value="/front/cmnty/selectMbr.do")
    @ResponseBody
    public Map<String,Object> selectMbr(String userNm, String cmntyid){
        Map<String, Object> result = new HashMap<>();
        List<UserVo> userInfoList = cmntyService.selectMbr(userNm, cmntyid);

        if (userInfoList.size() > 0) {
            result.put("datalist", userInfoList);
        }else {
            result.put("datalist",null);
        }

        return result;
    }

    /**
     * 환경동아리 회원 초대
     * Title : joinCmntyMbr
     * Description : 환경동아리 회원 초대
     *
     * @param cmntyVo
     * @param user
     * @return map
     */
    @RequestMapping(value="/front/cmnty/joinCmntyMbr.do")
    @ResponseBody
    public Map<String,Object> joinCmntyMbr(CmntyVo cmntyVo, @UserInfo UserVo user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        CmntyVo cmntyInfo = cmntyService.selectCmntyInfo(cmntyVo);
        cmntyVo.setUser(user);
        cmntyVo.setEsterid(cmntyInfo.getEsterid());
        cmntyVo.setJoinaprvmthdCd(cmntyInfo.getJoinaprvmthdCd());
        cmntyVo.setInviteYn("Y"); //초대 시 필수 값
        if(cmntyInfo != null){
            result = cmntyService.insertCmntyMbr(cmntyVo);
        }

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return resultMap;
    }

    /**
     * 환경동아리 가입대기회원 목록
     * Title : cmntyMbrWaitList
     * Description : 환경동아리 가입대기회원 목록
     *
     * @param paramVo
     * @param model
     * @param user
     * @return string
     */
    @RequestMapping(value = "/front/cmnty/cmntyMbrWaitList.html")
    public String cmntyMbrWaitList(CmntyMbrVo paramVo, Model model, @UserInfo UserVo user){
        CmntyVo cmntyVo = new CmntyVo();
        cmntyVo.setUser(user);
        cmntyVo.setCmntyid(paramVo.getCmntyid());
        CmntyVo cmntyInfo = cmntyService.selectCmntyInfo(cmntyVo);
        paramVo.setOrderField("ORDR ASC, MDFCN_DT");
        paramVo.setNotAprvYn("Y"); // 미승인 회원만 검색한다.
        List<CmntyMbrVo> list = cmntyService.selectCmntyMbrList(paramVo);
        model.addAttribute("list",list);
        model.addAttribute("cmntyInfo",cmntyInfo);
        model.addAttribute("totalCount",list.size() > 0 ? list.get(0).getTotalCount() : 0);
        model.addAttribute("paramVo",paramVo);
        model.addAttribute("userid",user.getUserid());
        return "front/cmnty/cmntyMbrWaitList";
    }

    /**
     * 환경동아리 게시글 보기
     * Title : cmntyPstView
     * Description : 환경동아리 게시글 보기
     *
     * @param cmntyid
     * @param paramVo
     * @param model
     * @param user
     * @return string
     */
    @RequestMapping(value = "/front/cmnty/cmntyPstView.html")
    public String cmntyPstView(Integer cmntyid, CmntyPstVo paramVo, Model model, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        paramVo.setUser(user);
        model.addAttribute("paramVo", paramVo);

        //조회수 증가
        cmntyService.updatePstHitsCount(paramVo);
        //게시판 정보 조회
        CmntyBbsVo cmntyBbsVo = new CmntyBbsVo();
        cmntyBbsVo.setBbsid(paramVo.getBbsid());
        CmntyBbsVo bbsInfo = cmntyService.selectBbsInfo(cmntyBbsVo);
        //게시글 정보 조회
        if ("1".equals(bbsInfo.getClsfCd() != null ? bbsInfo.getClsfCd() : "0")) {
            paramVo.setOrderField("GRP DESC,SORTORDR");
        } else {
            paramVo.setOrderField("FIXORDER ASC,GRP DESC,SORTORDR");
        }
        resultMap = cmntyService.selectPstInfo(paramVo);

//        paramVo = (PstVo) resultMap.get("paramMap");
//        Map result = cmntyService.selectPst(paramVo);
//		  paramVo.setPrntsPstid(paramVo.getPstid());
//        model.addAttribute("replyList", bbsService.selectReplyPstList(paramVo));

        model.addAttribute("bbsInfo", bbsInfo);
        model.addAttribute("cmntyPstInfo", resultMap.get("paramMap"));
        model.addAttribute("fileMap", resultMap.get("fileMap"));
        model.addAttribute("currentFileCnt", resultMap.get("currentFileCnt"));
        model.addAttribute("cmntyid",cmntyid);
        model.addAttribute("paramVo",paramVo);
        return "front/cmnty/cmntyPstView";
    }

    /**
     * 환경동아리 게시글 등록 화면
     * Title : cmntyPstInsertForm
     * Description : 환경동아리 게시글 등록 화면
     *
     * @param cmntyid
     * @param paramVo
     * @param model
     * @param user
     * @return string
     */
    @RequestMapping(value = "/front/cmnty/cmntyPstInsertForm.html")
    public String cmntyPstInsertForm(Integer cmntyid, CmntyPstVo paramVo, Model model, @UserInfo UserVo user) {
        //게시판 정보 조회
        CmntyBbsVo cmntyBbsVo = new CmntyBbsVo();
        cmntyBbsVo.setBbsid(paramVo.getBbsid());
        CmntyBbsVo bbsInfo = cmntyService.selectBbsInfo(cmntyBbsVo);

        Map<String, Object> fileConfiguration = fileService.getConfigurationByFilegrpName("cmntyPst_file");
        String uploadFileExtsn = ((HashMap<String, String>) fileConfiguration.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnKey -> "." + fileExtsnKey.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        model.addAttribute("bbsInfo",bbsInfo);
        model.addAttribute("paramVo",paramVo);
        model.addAttribute("cmntyid",cmntyid);
        model.addAttribute("formType","INSERT");
        model.addAttribute("cmntyPstInfo", new CmntyPstVo());
        return "front/cmnty/cmntyPstForm";
    }

    /**
     * 환경동아리 게시글 등록 처리
     * Title : insertCmntyPst
     * Description : 환경동아리 게시글 등록 처리
     *
     * @param paramVo
     * @param user
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/front/cmnty/insertCmntyPst.do")
    @ResponseBody
    public Map<String, Object> insertCmntyPst(@Valid CmntyPstVo paramVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        paramVo.setUser(user);
        result = cmntyService.insertCmntyPst(paramVo);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록이 완료되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다.");
        }
        return resultMap;
    }

}
