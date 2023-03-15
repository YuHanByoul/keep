package com.kbrainc.plum.front.instInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.instInfo.model.InstInfoVo;
import com.kbrainc.plum.front.instInfo.model.InstPicVo;
import com.kbrainc.plum.front.instInfo.service.InstInfoService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 기관정보 Controller 클래스
*
* <pre>
* com.kbrainc.plum.front.instInfo.controller
* - InstInfoController.java
* </pre>
*
* @ClassName : InstInfoController
* @Description : TODO
* @author : JD
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.instInfoController")
@Alias("front.instInfoController")
public class InstInfoController {
    
    @Resource(name = "front.instInfoServiceImpl")
    private InstInfoService instInfoService;
    
    /**
    * 기업정보 페이지 이동
    *
    * @Title : instInfoForm
    * @Description : 기업정보 페이지 이동
    * @param instInfoVo
    * @param userVo
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/instInfo/instInfoForm.html")
    public String instInfoForm(InstInfoVo instInfoVo, @UserInfo UserVo userVo, Model model) throws Exception {
        model.addAttribute("userVo", userVo);
        
        instInfoVo.setUser(userVo);
        
        InstInfoVo instInfo = null;
        instInfo = instInfoService.selectInstInfo(instInfoVo);
        model.addAttribute("instInfo", instInfo);
        
        return "front/instInfo/instInfoForm";
    }
    
    /**
    * 기업정보 수정
    *
    * @Title : updateInstInfo
    * @Description : 기업정보 수정
    * @param instInfoVo
    * @param userVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/instInfo/updateInstInfo.do")
    @ResponseBody
    public Map<String, Object> updateInstInfo(InstInfoVo instInfoVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if(!(userVo.getInstpicRoleCd().equals("109101"))) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "기관관리자만 수정이 가능합니다.");
            return resultMap;
        }
        
        instInfoVo.setUser(userVo);
        
        int retVal = 0;
        retVal = instInfoService.updateInstInfo(instInfoVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "기관정보 수정이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "기관정보 수정에 실패했습니다.");
        }
        return resultMap;
    }
    
    /**
    * 기업 담당자 설정 페이지 이동
    *
    * @Title : instPicList
    * @Description : 기업 담당자 설정 페이지 이동
    * @param userVo
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/instInfo/instPicList.html")
    public String instPicList(@UserInfo UserVo userVo, Model model) throws Exception {
        model.addAttribute("userVo", userVo);
        return "front/instInfo/instPicList";
    }
    
    /**
    * 기관 담당자 목록 조회
    *
    * @Title : selectInstPictList
    * @Description : 기관 담당자 목록 조회
    * @param instPicVo
    * @param userVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/front/instInfo/selectInstPictList.do")
    @ResponseBody
    public Map<String, Object> selectInstPictList(InstPicVo instPicVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InstPicVo> result = null;
        instPicVo.setInstid(userVo.getInstid());
        result =  instInfoService.selectInstPictList(instPicVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 기관 담당자 등록 > 회원 검색 페이지 이동
    *
    * @Title : instPicSearch
    * @Description : 기관 담당자 등록 > 회원 검색 페이지 이동
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/instInfo/instPicSearch.html")
    public String instPicSearch() throws Exception {
        return "front/instInfo/instPicSearch";
    }
    
    /**
    * 검색 회원 목록
    *
    * @Title : selectPicSearchList
    * @Description : 검색 회원 목록
    * @param instPicVo
    * @param userVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/front/instInfo/selectPicSearchList.do")
    @ResponseBody
    public Map<String, Object> selectPicSearchList(InstPicVo instPicVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InstPicVo> result = null;
        instPicVo.setInstid(userVo.getInstid());
        result =  instInfoService.selectPicSearchList(instPicVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 기관 담당자 등록 기능
    *
    * @Title : updateRegInstPic
    * @Description : 기관 담당자 등록 기능
    * @param instPicVo
    * @param userVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/instInfo/updateRegInstPic.do")
    @ResponseBody
    public Map<String, Object> updateRegInstPic(InstPicVo instPicVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if(!(userVo.getInstpicRoleCd().equals("109101"))) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "기관관리자만 등록이 가능합니다.");
            return resultMap;
        }
        
        InstPicVo instPicInfo = null; 
        instPicInfo = instInfoService.selectInstPicInfo(instPicVo);
        
        int retVal = 0;
        if(instPicInfo.getInstpicRoleCd() != null) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "이미 기관담당자로 등록된 회원입니다.");
        }else {
            instPicVo.setUser(userVo);
            instPicVo.setInstid(userVo.getInstid());
            
            retVal = instInfoService.updateRegInstPic(instPicVo);
        }
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "기관담당자 등록에 실패했습니다.");
        }
        return resultMap;
    }
    
    /**
    * 기관 담당자 상세정보 페이지
    *
    * @Title : instPicDetail
    * @Description : 기관 담당자 상세정보 페이지
    * @param instPicVo
    * @param userVo
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/instInfo/instPicDetail.html")
    public String instPicDetail(InstPicVo instPicVo, @UserInfo UserVo userVo, Model model) throws Exception {
        model.addAttribute("userVo", userVo);
        
        instPicVo.setUser(userVo);
        
        InstPicVo instPicInfo = null; 
        instPicInfo = instInfoService.selectInstPicInfo(instPicVo);
        model.addAttribute("instPicInfo", instPicInfo);
        
        return "front/instInfo/instPicDetail";
    }
    
    /**
    * 기관 담당자 해지 기능
    *
    * @Title : updateCancelInstPic
    * @Description : 기관 담당자 해지 기능
    * @param instPicVo
    * @param userVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/instInfo/updateCancelInstPic.do")
    @ResponseBody
    public Map<String, Object> updateCancelInstPic(InstPicVo instPicVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if(!(userVo.getInstpicRoleCd().equals("109101"))) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "기관관리자만 해지가 가능합니다.");
            return resultMap;
        }
        
        if(!(instPicVo.getInstid().equals(userVo.getInstid()))) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "본인과 같은 기관 담당자만 해지가 가능합니다.");
            return resultMap;
        }
        
        InstPicVo instPicInfo = null; 
        instPicInfo = instInfoService.selectInstPicInfo(instPicVo);
        
        int retVal = 0;
        if(instPicInfo.getInstpicRoleCd() == null) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "이미 기관담당자가 해지된 회원입니다.");
        }else {
            instPicVo.setUser(userVo);
            instPicVo.setInstid(userVo.getInstid());
            
            retVal = instInfoService.updateCancelInstPic(instPicVo);   
        }
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "해지가 완료 되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "기관담당자 해지에 실패했습니다.");
        }
         return resultMap;
    }
}
