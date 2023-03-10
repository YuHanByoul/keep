package com.kbrainc.plum.front.dsgnMng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.dsgnMng.model.DsgnMngVo;
import com.kbrainc.plum.front.dsgnMng.service.DsgnMngService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

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
public class DsgnMngController {

	@Resource(name = "front.dsgnMngServiceImpl")
    private DsgnMngService dsgnMngService;

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

    //이의신청 화면 이동	/front/dsgnMng/objcAplyForm.html
	//이의신청 목록 조회  	/front/dsgnMng/selectObjcAplyList.do

	//변경승인 팝업       	/front/dsgnMng/chgAprvPopup.html

	//이의신청 삭제       	/front/dsgnMng/deleteObjcAplyForm.do
	//이의신청 등록       	/front/dsgnMng/insertObjcAplyForm.do


	//보안개선계획서 등록	/front/dsgnMng/insertScrtyImprvForm.do
	//보안개선계획서 팝업	/front/dsgnMng/scrtyImprvForm.html
	//운영결과 상세       	/front/dsgnMng/operRsltDetailForm.html
	//운영결과 등록       	/front/dsgnMng/insertOperRsltForm.do
	//변경신청 삭제       	/front/dsgnMng/deleteChgAplyDetailForm.do
	//변경신청 등록       	/front/dsgnMng/insertChgAplyForm.do
	//변경신청 상세       	/front/dsgnMng/chgAplyDetailForm.html

	//







}
