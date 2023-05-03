package com.kbrainc.plum.front.mypage.seeDsgnDsctn.controller;

import java.util.Collections;
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
import com.kbrainc.plum.front.mypage.seeDsgnDsctn.model.SeeDsgnDsctnVo;
import com.kbrainc.plum.front.mypage.seeDsgnDsctn.service.SeeDsgnDsctnService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

/**
* 사회환경교육기관 지정 관리 > 지정관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.front.mypage.seeDsgnDsctn.controller
* - SeeDsgnDsctnController.java
* </pre>
*
* @ClassName : SeeDsgnDsctnController
* @Description : 사회환경교육기관 지정 관리 > 지정관리 컨트롤러
* @author : LHM
* @date : 2023. 4. 25.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.seeDsgnDsctnController")
@Alias("front.seeDsgnDsctnController")
@Slf4j
public class SeeDsgnDsctnController {

	@Resource(name = "front.seeDsgnDsctnServiceImpl")
    private SeeDsgnDsctnService seeDsgnDsctnService;

    /**
    * 지정관리 메뉴 이동
    *
    * @Title : seeDsgnDsctnDetailForm
    * @Description : 지정관리 메뉴 이동
    * @param seeDsgnDsctnVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/seeDsgnDsctnDetailForm.html")
    public String seeDsgnDsctnDetailForm(SeeDsgnDsctnVo seeDsgnDsctnVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
        return "front/mypage/seeDsgnDsctn/seeDsgnDsctnDetail";
    }

    /**
     * 지정내역 목록 조회
     *
     * @Title : selectDsgnDsctnList
     * @Description : 지정내역 목록 조회
     * @param seeDsgnDsctnVo
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/front/mypage/seeDsgnDsctn/selectSeeDsgnDsctnList.do")
     @ResponseBody
     public Map<String, Object> selectSeeDsgnDsctnList(SeeDsgnDsctnVo seeDsgnDsctnVo, @UserInfo UserVo user) throws Exception {
     	Map<String, Object> response = new HashMap<>();

     	seeDsgnDsctnVo.setUser(user);

     	List<SeeDsgnDsctnVo> list = seeDsgnDsctnService.selectSeeDsgnDsctnList(seeDsgnDsctnVo);
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
     * @Title : chgSeeDsgnDsctnForm
     * @Description : 변경내역 화면 이동
     * @param seeDsgnDsctnVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
     @RequestMapping(value = "/front/mypage/seeDsgnDsctn/chgSeeDsgnDsctnForm.html")
     public String chgSeeDsgnDsctnForm(SeeDsgnDsctnVo seeDsgnDsctnVo, Model model, @UserInfo UserVo user) throws Exception {
    	 model.addAttribute("aplcntid", user.getUserid());
    	 seeDsgnDsctnVo.setAplcntid(user.getUserid());

    	 model.addAttribute("dsgnPrgrmList", seeDsgnDsctnService.dsgnPrgrmList(seeDsgnDsctnVo));
         return "front/mypage/seeDsgnDsctn/chgSeeDsgnDsctnForm";
     }

    /**
    * 변경내역 목록 조회
    *
    * @Title : selectChgSeeDsgnDsctnList
    * @Description : 변경내역 목록 조회
    * @param seeDsgnDsctnVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/selectChgSeeDsgnDsctnList.do")
     @ResponseBody
     public Map<String, Object> selectChgSeeDsgnDsctnList(SeeDsgnDsctnVo seeDsgnDsctnVo, @UserInfo UserVo user) throws Exception {
    	 Map<String, Object> response = new HashMap<>();

    	 seeDsgnDsctnVo.setUser(user);

    	 List<SeeDsgnDsctnVo> list = seeDsgnDsctnService.selectChgSeeDsgnDsctnList(seeDsgnDsctnVo);
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
    * @Title : chgSeeDsgnAplyDetailForm
    * @Description : 변경내역 상세 화면 이동
    * @param seeDsgnDsctnVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/chgSeeDsgnAplyDetailForm.html")
    public String chgSeeDsgnAplyDetailForm(SeeDsgnDsctnVo seeDsgnDsctnVo, Model model, @UserInfo UserVo user) throws Exception {
    	SeeDsgnDsctnVo chgSeeDsgnAplyInfo=null;
    	chgSeeDsgnAplyInfo = seeDsgnDsctnService.selectChgSeeDsgnAply(seeDsgnDsctnVo);
    	seeDsgnDsctnVo.setAplcntid(user.getUserid());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("dsgnPrgrmList", seeDsgnDsctnService.dsgnPrgrmList(seeDsgnDsctnVo));
    	model.addAttribute("chgSeeDsgnAplyInfo" ,chgSeeDsgnAplyInfo);

        if(chgSeeDsgnAplyInfo.getFilegrpid() != null && chgSeeDsgnAplyInfo.getFilegrpid() != 0) {
            List<SeeDsgnDsctnVo> fileList = seeDsgnDsctnService.selectFileList(chgSeeDsgnAplyInfo);
            model.addAttribute("fileList", fileList);
        }else {
        	model.addAttribute("fileList", Collections.emptyList());
        }
        return "front/mypage/seeDsgnDsctn/chgSeeDsgnAplyDetail";
    }

    /**
    * 변경신청수정 화면이동
    *
    * @Title : chgSeeDsgnAplyUpdateForm
    * @Description : 변경신청수정 화면이동
    * @param seeDsgnDsctnVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/chgSeeDsgnAplyUpdateForm.html")
    public String chgSeeDsgnAplyUpdateForm(SeeDsgnDsctnVo seeDsgnDsctnVo, Model model, @UserInfo UserVo user) throws Exception {
    	SeeDsgnDsctnVo chgSeeDsgnAplyInfo=null;
    	chgSeeDsgnAplyInfo = seeDsgnDsctnService.selectChgSeeDsgnAply(seeDsgnDsctnVo);
    	seeDsgnDsctnVo.setAplcntid(user.getUserid());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("dsgnPrgrmList", seeDsgnDsctnService.dsgnPrgrmList(seeDsgnDsctnVo));
    	model.addAttribute("chgSeeDsgnAplyInfo" ,chgSeeDsgnAplyInfo);

        if(chgSeeDsgnAplyInfo.getFilegrpid() != null && chgSeeDsgnAplyInfo.getFilegrpid() != 0) {
            List<SeeDsgnDsctnVo> fileList = seeDsgnDsctnService.selectFileList(chgSeeDsgnAplyInfo);
            model.addAttribute("fileList", fileList);
        }else {
        	model.addAttribute("fileList", Collections.emptyList());
        }
    	return "front/mypage/seeDsgnDsctn/chgSeeDsgnAplyUpdate";
    }


    /**
    * 변경신청 수정
    *
    * @Title : updateChgSeeDsgnAply
    * @Description : 변경신청 수정
    * @param seeDsgnDsctnVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/updateChgSeeDsgnAply.do")
    @ResponseBody
    public Map<String, Object> updateChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo, Model model ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        seeDsgnDsctnVo.setUser(user);
        seeDsgnDsctnVo.setAplcntid(user.getUserid());

        int retVal = 0;

        retVal = seeDsgnDsctnService.updateChgSeeDsgnAply(seeDsgnDsctnVo);

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
    * @Title : chgSeeDsgnAplyInsertForm
    * @Description : 변경신청등록 화면 이동
    * @param seeDsgnDsctnVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/chgSeeDsgnAplyInsertForm.html")
    public String chgSeeDsgnAplyInsertForm(SeeDsgnDsctnVo seeDsgnDsctnVo, Model model, @UserInfo UserVo user) throws Exception {
    	seeDsgnDsctnVo.setAplcntid(user.getUserid());
    	model.addAttribute("aplcntid", user.getUserid());
    	model.addAttribute("dsgnPrgrmList", seeDsgnDsctnService.dsgnPrgrmList(seeDsgnDsctnVo));

    	return "front/mypage/seeDsgnDsctn/chgAplyInsert";
    }

    /**
    * 변경신청 등록
    *
    * @Title : insertChgSeeDsgnAply
    * @Description : 변경신청 등록
    * @param seeDsgnDsctnVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/insertChgSeeDsgnAply.do")
    @ResponseBody
    public Map<String, Object> insertChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        seeDsgnDsctnVo.setUser(user);
        seeDsgnDsctnVo.setAplcntid(user.getUserid());

        int retVal = 0;

        retVal = seeDsgnDsctnService.insertChgSeeDsgnAply(seeDsgnDsctnVo);

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
    * @Title : chgSeeDsgnAprvPopup
    * @Description : 변경승인 팝업
    * @param seeDsgnDsctnVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/mypage/seeDsgnDsctn/chgSeeDsgnAprvPopup.html")
    public String chgSeeDsgnAprvPopup(SeeDsgnDsctnVo seeDsgnDsctnVo, Model model, @UserInfo UserVo user) throws Exception {
        seeDsgnDsctnVo.setAplcntid(user.getUserid());
        model.addAttribute("aplcntid", user.getUserid());
        model.addAttribute("sttsNm", seeDsgnDsctnVo.getSttsNm());
        model.addAttribute("dmndCn", seeDsgnDsctnVo.getDmndCn());

        return "front/mypage/seeDsgnDsctn/chgSeeDsgnAprvPopup";
    }

    /**
    * 변경내역 삭제
    *
    * @Title : deleteChgSeeDsgnAply
    * @Description : 변경내역 삭제
    * @param seeDsgnDsctnVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/front/mypage/seeDsgnDsctn/deleteChgSeeDsgnAply.do")
    @ResponseBody
    public Map<String,Object> deleteChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo, @UserInfo UserVo user) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        boolean result = false;

        seeDsgnDsctnVo.setUser(user);
        result = seeDsgnDsctnService.deleteChgSeeDsgnAply(seeDsgnDsctnVo);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "변경신청 삭제되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "변경신청 삭제에 실패하였습니다");
        }
        return resultMap;
    }



}
