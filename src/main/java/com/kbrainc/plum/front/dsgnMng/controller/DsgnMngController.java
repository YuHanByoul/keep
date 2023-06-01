package com.kbrainc.plum.front.dsgnMng.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.dsgnMng.model.DsgnMngVo;
import com.kbrainc.plum.front.dsgnMng.service.DsgnMngService;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.dsgnPrgrm.service.DsgnPrgrmService;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.mng.qestnr.service.QestnrService;
import com.kbrainc.plum.mng.srvy.service.SrvyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

/**
* 사용자.지정관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.front.dsgnMng.controller
* - DsgnMngController.java
* </pre>
*
* @ClassName : DsgnMngController
* @Description : 사용자.지정관리 컨트롤러
* @author : kbrain
* @date : 2023. 3. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.dsgnMngController")
@Alias("front.dsgnMngController")
@Slf4j
public class DsgnMngController {

	@Resource(name = "front.dsgnMngServiceImpl")
    private DsgnMngService dsgnMngService;

	@Autowired
    private FileService fileService;

	@Autowired
	private DsgnPrgrmService dsgnPrgrmService;

	@Autowired
	private QestnrService qestnrService;
	@Autowired
	private SrvyService srvyService;

    /**
    * 지정관리 메뉴 이동
    *
    * @Title : dsgnMngDetailForm
    * @Description : 지정관리 메뉴 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/dsgnMngDetailForm.html")
    public String dsgnMngDetailForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
        return "front/dsgnMng/dsgnMngDetail";
    }

    /**
     * 지정내역 목록 조회
     *
     * @Title : selectDsgnDsctnList
     * @Description : 지정내역 목록 조회
     * @param dsgnMngVo
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/front/dsgnMng/selectDsgnDsctnList.do")
     @ResponseBody
     public Map<String, Object> selectDsgnSttusList(DsgnMngVo dsgnMngVo, @UserInfo UserVo user) throws Exception {
     	Map<String, Object> response = new HashMap<>();

     	dsgnMngVo.setUser(user);

     	List<DsgnMngVo> list = dsgnMngService.selectDsgnHstryList(dsgnMngVo);
     	if (list.size() > 0) {
     		response.put("totalCount", (list.get(0).getTotalCount()));
     		response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
     	} else {
     		response.put("totalCount", 0);
     	}
     	response.put("list", list);
     	return response;
     }

     /**
     * 변경내역 화면 이동
     *
     * @Title : chgDsctnForm
     * @Description : 변경내역 화면 이동
     * @param dsgnMngVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
     @RequestMapping(value = "/front/dsgnMng/chgDsctnForm.html")
     public String chgDsctnForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	 model.addAttribute("aplcntid", user.getUserid());
    	 dsgnMngVo.setAplcntid(user.getUserid());

    	 model.addAttribute("dsgnPrgrmList", dsgnMngService.dsgnPrgrmList(dsgnMngVo));
         return "front/dsgnMng/chgDsctnForm";
     }

    /**
    * 변경내역 목록 조회
    *
    * @Title : selectChgDsctnList
    * @Description : 변경내역 목록 조회
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/selectChgDsctnList.do")
     @ResponseBody
     public Map<String, Object> selectChgDsctnList(DsgnMngVo dsgnMngVo, @UserInfo UserVo user) throws Exception {
    	 Map<String, Object> response = new HashMap<>();

    	 dsgnMngVo.setUser(user);

    	 List<DsgnMngVo> list = dsgnMngService.selectChgDsctnList(dsgnMngVo);
    	 if (list.size() > 0) {
    		 response.put("totalCount", (list.get(0).getTotalCount()));
    		 response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
    	 } else {
    		 response.put("totalCount", 0);
    	 }
    	 response.put("list", list);
    	 return response;
     }

    /**
    * 변경내역 상세 화면 이동
    *
    * @Title : chgAplyDetailForm
    * @Description : 변경내역 상세 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/chgAplyDetailForm.html")
    public String chgAplyDetailForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	DsgnMngVo chgAplyInfo=null;
    	chgAplyInfo = dsgnMngService.selectChgAply(dsgnMngVo);
    	dsgnMngVo.setAplcntid(user.getUserid());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("dsgnPrgrmList", dsgnMngService.dsgnPrgrmList(dsgnMngVo));
    	model.addAttribute("chgAplyInfo" ,chgAplyInfo);

        if(chgAplyInfo.getFilegrpid() != null && chgAplyInfo.getFilegrpid() != 0) {
            List<DsgnMngVo> fileList = dsgnMngService.selectFileList(chgAplyInfo);
            model.addAttribute("fileList", fileList);
        }else {
        	model.addAttribute("fileList", Collections.emptyList());
        }
        return "front/dsgnMng/chgAplyDetail";
    }

    /**
    * 변경신청수정 화면이동
    *
    * @Title : chgAplyUpdateForm
    * @Description : 변경신청수정 화면이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/chgAplyUpdateForm.html")
    public String chgAplyUpdateForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	DsgnMngVo chgAplyInfo=null;
    	chgAplyInfo = dsgnMngService.selectChgAply(dsgnMngVo);
    	dsgnMngVo.setAplcntid(user.getUserid());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("dsgnPrgrmList", dsgnMngService.dsgnPrgrmList(dsgnMngVo));
    	model.addAttribute("chgAplyInfo" ,chgAplyInfo);

        if(chgAplyInfo.getFilegrpid() != null && chgAplyInfo.getFilegrpid() != 0) {
            List<DsgnMngVo> fileList = dsgnMngService.selectFileList(chgAplyInfo);
            model.addAttribute("fileList", fileList);
        }else {
        	model.addAttribute("fileList", Collections.emptyList());
        }
    	return "front/dsgnMng/chgAplyUpdate";
    }


    /**
    * 변경신청 수정
    *
    * @Title : updateChgAply
    * @Description : 변경신청 수정
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/updateChgAply.do")
    @ResponseBody
    public Map<String, Object> updateChgAply(DsgnMngVo dsgnMngVo, Model model ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        dsgnMngVo.setUser(user);
        dsgnMngVo.setAplcntid(user.getUserid());

        int retVal = 0;

        retVal = dsgnMngService.updateChgAply(dsgnMngVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

        return resultMap;
    }

    /**
    * 변경신청등록 화면 이동
    *
    * @Title : chgAplyInsertForm
    * @Description : 변경신청등록 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/chgAplyInsertForm.html")
    public String chgAplyInsertForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	dsgnMngVo.setAplcntid(user.getUserid());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("dsgnPrgrmList", dsgnMngService.dsgnPrgrmList(dsgnMngVo));

    	return "front/dsgnMng/chgAplyInsert";
    }

    /**
    * 변경신청 등록
    *
    * @Title : insertChgAply
    * @Description : 변경신청 등록
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/insertChgAply.do")
    @ResponseBody
    public Map<String, Object> insertChgAply(DsgnMngVo dsgnMngVo ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        dsgnMngVo.setUser(user);
        dsgnMngVo.setAplcntid(user.getUserid());

        int retVal = 0;

        retVal = dsgnMngService.insertChgAply(dsgnMngVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }


    /**
    * 변경승인 팝업
    *
    * @Title : chgAplyPopup
    * @Description : 변경승인 팝업
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/chgAprvPopup.html")
    public String chgAplyPopup(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	dsgnMngVo.setAplcntid(user.getUserid());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("sttsCdNm", dsgnMngVo.getSttsCdNm());
    	model.addAttribute("splmntDmndCn", dsgnMngVo.getSplmntDmndCn());

    	return "front/dsgnMng/chgAprvPopup";
    }

    @RequestMapping(value="/front/dsgnMng/deleteChgAply.do")
    @ResponseBody
    public Map<String,Object> deleteChgAply(DsgnMngVo dsgnMngVo, @UserInfo UserVo user) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        dsgnMngVo.setUser(user);
        result = dsgnMngService.deleteChgAply(dsgnMngVo);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "변경신청 삭제되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "변경신청 삭제에 실패하였습니다");
        }
        return resultMap;
    }


    /**
     * 운영결과 화면 이동
     *
     * @Title : operRsltForm
     * @Description : 운영결과 화면 이동
     * @param dsgnMngVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/front/dsgnMng/operRsltForm.html")
    public String operRsltForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
    	return "front/dsgnMng/operRsltForm";
    }

    /**
    * 운영결과등록 화면 이동
    *
    * @Title : operRsltInsertForm
    * @Description : 운영결과등록 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "front/dsgnMng/operRsltInsertForm.html")
    public String operRsltInsertForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {

    	DsgnMngVo operRsltInfo = null;

    	operRsltInfo = dsgnMngService.selectOperRslt(dsgnMngVo);

    	if(operRsltInfo.getFilegrpid() != null && operRsltInfo.getFilegrpid() != 0) {
            List<DsgnMngVo> fileList = dsgnMngService.selectFileList(operRsltInfo);
            model.addAttribute("fileList", fileList);
        }else {
        	model.addAttribute("fileList", Collections.emptyList());
        }

    	if(operRsltInfo.getRsltCyclid() != null && operRsltInfo.getRsltCyclid() != 0 ) {
    		model.addAttribute("prfmncList", dsgnMngService.selectOperRsltPrfmncList(operRsltInfo));
    	}

    	model.addAttribute("mode", dsgnMngVo.getMode());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("operRsltInfo", operRsltInfo);
    	return "front/dsgnMng/operRsltInsert";
    }

    /**
    * 운영결과 등록
    *
    * @Title : insertOperRslt
    * @Description : 운영결과 등록
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/insertOperRslt.do")
    @ResponseBody
    public Map<String, Object> insertOperRslt(DsgnPrgrmVo dsgnPrgrmVo ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        dsgnPrgrmVo.setUser(user);

        int retVal = 0;

        retVal = dsgnPrgrmService.updateOperRslt(dsgnPrgrmVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "제출에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "제출에 실패했습니다.");
        }
        return resultMap;
    }

    /**
    * 운영결과수정 화면 이동
    *
    * @Title : operRsltUpdateForm
    * @Description : 운영결과수정 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/operRsltUpdateForm.html")
    public String operRsltUpdateForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("operRsltInfo", dsgnMngService.selectOperRslt(dsgnMngVo));
    	return "front/dsgnMng/operRsltUpdate";
    }

    /**
    * 운영결과상세 화면 이동
    *
    * @Title : operRsltDetail
    * @Description : 운영결과상세 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/operRsltDetailForm.html")
    public String operRsltDetail(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("operRsltInfo", dsgnMngService.selectOperRslt(dsgnMngVo));
    	return "front/dsgnMng/operRsltDetail";
    }

    /**
     * 운영결과 목록 조회
     *
     * @Title : selectOperRsltList
     * @Description : 운영결과 목록 조회
     * @param dsgnMngVo
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/dsgnMng/selectOperRsltList.do")
    @ResponseBody
    public Map<String, Object> selectOperRsltList(DsgnMngVo dsgnMngVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> response = new HashMap<>();

    	dsgnMngVo.setUser(user);

    	List<DsgnMngVo> list = dsgnMngService.selectOperRsltList(dsgnMngVo);
    	if (list.size() > 0) {
    		response.put("totalCount", (list.get(0).getTotalCount()));
    		response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
    	} else {
    		response.put("totalCount", 0);
    	}
    	response.put("list", list);
    	return response;
    }

    /**
     * 이행확인 화면 이동
     *
     * @Title : operRsltForm
     * @Description : 이행확인 화면 이동
     * @param dsgnMngVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/front/dsgnMng/implmntIdntyForm.html")
    public String implmntIdntyForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
    	return "front/dsgnMng/implmntIdntyForm";
    }

    /**
     * 이행확인 목록 조회
     *
     * @Title : selectOperRsltList
     * @Description : 이행확인 목록 조회
     * @param dsgnMngVo
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/dsgnMng/selectImplmntIdntyList.do")
    @ResponseBody
    public Map<String, Object> selectImplmntIdntyList(DsgnMngVo dsgnMngVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> response = new HashMap<>();

    	dsgnMngVo.setUser(user);

    	List<DsgnMngVo> list = dsgnMngService.selectImplmntIdntyList(dsgnMngVo);
    	if (list.size() > 0) {
    		response.put("totalCount", (list.get(0).getTotalCount()));
    		response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
    	} else {
    		response.put("totalCount", 0);
    	}
    	response.put("list", list);
    	return response;
    }

    /**
     * 보완개선요청 팝업
     *
     * @Title : scrtyImprvPopup
     * @Description : 보완개선요청 팝업
     * @param dsgnMngVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/front/dsgnMng/scrtyImprvPopup.html")
    public String scrtyImprvPopup(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {

//    	if(dsgnMngVo.getAplyid() != null && dsgnMngVo.getAplyid() != 0) {
//    		model.addAttribute("popInfo", dsgnMngService.selectObjcAply(dsgnMngVo));
//    	}else {
//    		model.addAttribute("popInfo", dsgnMngVo);
//    	}
    	model.addAttribute("popSe", dsgnMngVo.getPopSe());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("splmntDmnd", dsgnMngService.selectSplmntDmnd(dsgnMngVo));

    	return "front/dsgnMng/scrtyImprvPopup";
    }

    /**
     * 보완개선계획 팝업
     *
     * @Title : imprvPlanPopup
     * @Description : 보완개선계획 팝업
     * @param dsgnMngVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/front/dsgnMng/imprvPlanPopup.html")
    public String imprvPlanPopup(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {

    	model.addAttribute("popSe", dsgnMngVo.getPopSe());
    	model.addAttribute("aplcntid", user.getUserid());
    	//model.addAttribute("splmntPlan", dsgnMngService.selectSplmntPlan(dsgnMngVo));

    	DsgnMngVo splmntPlan = null;
    	splmntPlan = dsgnMngService.selectSplmntDmnd(dsgnMngVo);
    	model.addAttribute("splmntPlan", splmntPlan );

    	FileVo fileVo = new FileVo();

        if(splmntPlan.getFilegrpid() != null && splmntPlan.getFilegrpid() != 0) {
            fileVo.setFilegrpid(splmntPlan.getFilegrpid());
            ArrayList<FileVo> fileList = fileService.getFileList(fileVo);
            model.addAttribute("fileList", fileList);
        }else {
        	model.addAttribute("fileList", Collections.emptyList());
        }
    	return "front/dsgnMng/imprvPlanPopup";
    }

    /**
    * 개선계획등록
    *
    * @Title : insertImprvPlanForm
    * @Description : 개선계획등록
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/insertImprvPlanForm.do")
    @ResponseBody
    public Map<String, Object> insertImprvPlanForm(DsgnMngVo dsgnMngVo ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        dsgnMngVo.setUser(user);
        dsgnMngVo.setAplcntid(user.getUserid());

        int retVal = 0;

        retVal = dsgnMngService.insertImprvPlanForm(dsgnMngVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;
    }

    /**
     * 이의신청 화면 이동
     *
     * @Title : operRsltForm
     * @Description : 이의신청 화면 이동
     * @param dsgnMngVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/front/dsgnMng/objcAplyForm.html")
    public String objcAplyForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
    	return "front/dsgnMng/objcAplyForm";
    }

    /**
     * 이의신청 목록 조회
     *
     * @Title : selectObjcAplyList
     * @Description : 이의신청 목록 조회
     * @param dsgnMngVo
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/dsgnMng/selectObjcAplyList.do")
    @ResponseBody
    public Map<String, Object> selectObjcAplyList(DsgnMngVo dsgnMngVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> response = new HashMap<>();

    	dsgnMngVo.setUser(user);

    	List<DsgnMngVo> list = dsgnMngService.selectObjcAplyList(dsgnMngVo);
    	if (list.size() > 0) {
    		response.put("totalCount", (list.get(0).getTotalCount()));
    		response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
    	} else {
    		response.put("totalCount", 0);
    	}
    	response.put("list", list);
    	return response;
    }

    /**
    * 이의신청 팝업
    *
    * @Title : objcAplyPopup
    * @Description : 이의신청 팝업
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/objcAplyPopup.html")
    public String objcAplyPopup(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {

    	if(dsgnMngVo.getAplyid() != null && dsgnMngVo.getAplyid() != 0) {
    		model.addAttribute("popInfo", dsgnMngService.selectObjcAply(dsgnMngVo));
    	}else {
    		model.addAttribute("popInfo", dsgnMngVo);
    	}
    	model.addAttribute("popSe", dsgnMngVo.getPopSe());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("dsgnPrgrmList", dsgnMngService.dsgnPrgrmList(dsgnMngVo));

    	return "front/dsgnMng/objcAplyPopup";
    }

    /**
    * 이의신청 등록
    *
    * @Title : insertObjcAplyForm
    * @Description : 이의신청 등록
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/insertObjcAplyForm.do")
    @ResponseBody
    public Map<String, Object> insertObjcAplyForm(DsgnMngVo dsgnMngVo ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        dsgnMngVo.setUser(user);
        dsgnMngVo.setAplcntid(user.getUserid());

        int retVal = 0;

        retVal = dsgnMngService.insertObjcAplyForm(dsgnMngVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

    /**
     * 이의신청 삭제
     *
     * @Title : deleteObjcAplyForm
     * @Description : 이의신청 삭제
     * @param dsgnMngVo
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/dsgnMng/deleteObjcAplyForm.do")
    @ResponseBody
    public Map<String, Object> deleteObjcAplyForm(DsgnMngVo dsgnMngVo ,@UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	dsgnMngVo.setUser(user);
    	dsgnMngVo.setAplcntid(user.getUserid());

    	int retVal = 0;

    	retVal = dsgnMngService.deleteObjcAplyForm(dsgnMngVo);

    	if (retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		resultMap.put("msg", "삭제에 성공하였습니다.");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "삭제에 실패했습니다.");
    	}

    	return resultMap;
    }


    /**
    * 컨설팅관리 목록 화면 이동
    *
    * @Title : cnsltngMngList
    * @Description : 컨설팅관리 목록 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/cnsltngMngList.html")
    public String cnsltngMngList(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	dsgnMngVo.setUser(user);
    	model.addAttribute("aplcntid", user.getUserid());
    	return "front/dsgnMng/cnsltngMngList";
    }

    /**
    * 컨설팅관리 목록 조회
    *
    * @Title : selectCnsltngMngList
    * @Description : 컨설팅관리 목록 조회
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/selectCnsltngMngList.do")
    @ResponseBody
    public Map<String, Object> selectCnsltngMngList(DsgnMngVo dsgnMngVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> response = new HashMap<>();

    	dsgnMngVo.setUser(user);

    	List<DsgnMngVo> list = dsgnMngService.selectCnsltngMngList(dsgnMngVo);
    	if (list.size() > 0) {
    		response.put("totalCount", (list.get(0).getTotalCount()));
    		response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
    	} else {
    		response.put("totalCount", 0);
    	}
    	response.put("list", list);
    	return response;
    }

    /**
    * (컨설팅)신청정보 화면 이동
    *
    * @Title : aplyInfoForm
    * @Description : (컨설팅)신청정보 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/aplyInfoForm.html")
    public String aplyInfoForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	dsgnMngVo.setUser(user);
    	DsgnMngVo aplyInfo = null;
    	QitemVo qitemVo = new QitemVo();
    	List<QitemVo> qitemList = null;

    	aplyInfo = dsgnMngService.selectAplyInfo(dsgnMngVo);

    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("aplyInfo", aplyInfo);

    	if(aplyInfo.getQestnrid() != null && aplyInfo.getQestnrid()!=0) {
    		qitemVo.setQestnrid(aplyInfo.getQestnrid());
    		//컨설팅 문항 목록 조회
    		model.addAttribute("qitemList", qestnrService.selectQitemWithExList(qitemVo));
    		model.addAttribute("ansList",   dsgnMngService.selectSrvyAnsList(aplyInfo));
    		//컨설팅 답변 목록 조회
    	}

    	if(aplyInfo.getFilegrpid() != null && aplyInfo.getFilegrpid() != 0) {
            List<DsgnMngVo> fileList = dsgnMngService.selectFileList(aplyInfo);
            model.addAttribute("fileList", fileList);
        }else {
        	model.addAttribute("fileList", Collections.emptyList());
        }

    	return "front/dsgnMng/aplyInfo";
    }

    /**
    * 컨설팅 신청 등록
    *
    * @Title : insertCnsltngAply
    * @Description : 컨설팅 신청 등록
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/insertCnsltngAply.do")
    @ResponseBody
    public Map<String, Object> insertCnsltngAply(DsgnMngVo dsgnMngVo ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        dsgnMngVo.setUser(user);
        dsgnMngVo.setAplcntid(user.getUserid());

        int retVal = 0;

        retVal = dsgnMngService.insertCnsltngAply(dsgnMngVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

    /**
    * 컨설팅신청 삭제
    *
    * @Title : deleteCnsltngAply
    * @Description : 컨설팅 신청 삭제
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/deleteCnsltngAply.do")
    @ResponseBody
    public Map<String, Object> deleteCnsltngAply(DsgnMngVo dsgnMngVo ,@UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	dsgnMngVo.setUser(user);
    	dsgnMngVo.setAplcntid(user.getUserid());

    	int retVal = 0;

    	retVal = dsgnMngService.deleteCnsltngAply(dsgnMngVo);

    	if (retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		resultMap.put("msg", "삭제에 성공하였습니다.");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "삭제에 실패했습니다.");
    	}

    	return resultMap;
    }

    /**
    * 컨설팅신청 수정
    *
    * @Title : updateCnsltngAply
    * @Description : 컨설팅신청 수정
    * @param dsgnMngVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnMng/updateCnsltngAply.do")
    @ResponseBody
    public Map<String, Object> updateCnsltngAply(DsgnMngVo dsgnMngVo ,@UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	dsgnMngVo.setUser(user);
    	dsgnMngVo.setAplcntid(user.getUserid());

    	int retVal = 0;

    	retVal = dsgnMngService.updateCnsltngAply(dsgnMngVo);

    	if (retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		resultMap.put("msg", "수정에 성공하였습니다.");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "수정에 실패했습니다.");
    	}

    	return resultMap;
    }

    /**
    * 컨설팅평가 화면 이동
    *
    * @Title : cnsltngEvlForm
    * @Description : 컨설팅평가 화면 이동
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnMng/cnsltngEvlForm.html")
    public String cnsltngEvlForm(DsgnMngVo dsgnMngVo, Model model, @UserInfo UserVo user) throws Exception {
    	dsgnMngVo.setUser(user);
    	model.addAttribute("aplcntid", user.getUserid());
    	return "front/dsgnMng/cnsltngEvl";
    }

}
