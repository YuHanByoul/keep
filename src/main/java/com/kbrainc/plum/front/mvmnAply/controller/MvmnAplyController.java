/**
 * 
 */
package com.kbrainc.plum.front.mvmnAply.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.infntAply.model.InfntAplyVo;
import com.kbrainc.plum.front.mvmnAply.model.MvmnAplyVo;
import com.kbrainc.plum.front.mvmnAply.service.MvmnAplyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 푸름이 이동환경교실 교육신청 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.mvmnAply.controller
* - MvmnAplyController.java
* </pre>
*
* @ClassName : MvmnAplyController
* @Description : TODO
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.mvmnAplyController")
@Alias("front.mvmnAplyController")
public class MvmnAplyController {

    @Resource(name = "front.mvmnAplyServiceImpl")
    private MvmnAplyService mvmnAplyService;
    
    /**
    * 푸름이 이동환경교실 교육신청 게시글 목록 화면 이동
    *
    * @Title : mvmnAplyListForm
    * @Description : 푸름이 이동환경교실 교육신청 게시글 목록 화면 이동
    * @param mvmnAplyVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/mvmnAply/mvmnAplyList.html")
    public String mvmnAplyListForm(MvmnAplyVo mvmnAplyVo, Model model) throws Exception {
        model.addAttribute("sareaList", mvmnAplyService.selectEduSareaList(mvmnAplyVo));
        
        return "front/mvmnAply/mvmnAplyList";
    }
     
    /**
    * 푸름이 이동환경교실 교육신청 게시글 상세 화면 이동
    *
    * @Title : mvmnAplyDetailForm
    * @Description : 푸름이 이동환경교실 교육신청 게시글 상세 화면 이동
    * @param mvmnAplyVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/mvmnAply/mvmnAplyDetailForm.html")
    public String mvmnAplyDetailForm(MvmnAplyVo mvmnAplyVo, @UserInfo UserVo user, Model model) throws Exception {
        MvmnAplyVo mvmnAply = null;
        mvmnAply = mvmnAplyService.selectMvmnAplyInfo(mvmnAplyVo);
        model.addAttribute("mvmnAply", mvmnAply);

         /*
         특화교육인 경우 교육 프로그램 등록시 설정했던 운영권역의 목록을 가져온다.
         공통교육인 경우 교육 일정에 등록되어 있는 운영권역의 목록을 가져온다.
         */
        if("196102".equals(mvmnAplyVo.getEduSeCd())) {
            model.addAttribute("sareaSignguList", mvmnAplyService.selectMvmnAplySignguList(mvmnAplyVo));
        } else {
            model.addAttribute("sareaSignguList", mvmnAplyService.selectMvmnAplySignguListUsingSchdl(mvmnAplyVo));
        }

        mvmnAplyVo.setInstid(mvmnAply.getInstid());
        mvmnAplyVo.setPrgrmid(mvmnAply.getPrgrmid());
        
        List<MvmnAplyVo> eduPhotoFileList = null;
        List<MvmnAplyVo> mvmnAplyTmeList = null;
        List<MvmnAplyVo> mvmnAplyEduSareaList = null;
        
        eduPhotoFileList = mvmnAplyService.selectEduPhotoFileList(mvmnAplyVo);
        model.addAttribute("eduPhotoFileList", eduPhotoFileList);
        model.addAttribute("user", user);
        mvmnAplyTmeList =  mvmnAplyService.selectMvmnTmeList(mvmnAplyVo);
        //mvmnAplyList = mvmnAplyService.selectInstMvmnAplyList(mvmnAplyVo);
        if(mvmnAplyTmeList.size() <= 0) {
            model.addAttribute("mvmnAplyTmeList", "null");
        }else {
            model.addAttribute("mvmnAplyTmeList", mvmnAplyTmeList);
        }
        
        mvmnAplyEduSareaList =  mvmnAplyService.selectMvmnAplyEduSareaList(mvmnAplyVo);
        if(mvmnAplyEduSareaList.size() <= 0) {
            model.addAttribute("mvmnAplyEduSareaList", "null");
        }else {
            model.addAttribute("mvmnAplyEduSareaList", mvmnAplyEduSareaList);
        }
        
        return "front/mvmnAply/mvmnAplyDetail";
    }
    
    /**
     * 푸름이 이동환경교실 교육신청 회차 목록 조회
     *
     * @Title : selectMvmnAplyTmeList
     * @Description : 푸름이 이동환경교실 교육신청 회차 목록 조회
     * @param mvmnAplyVo
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
     @RequestMapping(value="/front/mvmnAply/selectMvmnAplyTmeList.do")
     @ResponseBody
     public Map<String, Object> selectMvmnAplyTmeList(MvmnAplyVo mvmnAplyVo) throws Exception {
         Map<String, Object> resultMap = new HashMap<>();
         List<MvmnAplyVo> result =  mvmnAplyService.selectMvmnAplyTmeList(mvmnAplyVo);
//         if (result.size() > 0) {
//             resultMap.put("totalCount", (result.get(0).getTotalCount()));
//             resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
//         } else {
//             resultMap.put("totalCount", 0);
//         }
         resultMap.put("list", result);
         
         return resultMap;
     }
      
    /**
    * 푸름이 이동환경교실 교육신청 게시글 목록 조회
    *
    * @Title : selectMvmnAplyList
    * @Description : 푸름이 이동환경교실 교육신청 게시글 목록 조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/front/mvmnAply/selectMvmnAplyList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplyList(MvmnAplyVo mvmnAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnAplyVo> result = null;
        
        result =  mvmnAplyService.selectMvmnAplyList(mvmnAplyVo);
        
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
     * 푸름이 이동환경교실 교육신청 날짜조회
     *
     * @Title : selectMvmnAplyDeList
     * @Description : 푸름이 이동환경교실 교육신청 날짜조회
     * @param mvmnAplyVo
     * @return
     * @throws Exception
     * @return List<MvmnAplyVo>
     */
    @RequestMapping(value="/front/mvmnAply/selectMvmnAplyDeList.do")
    @ResponseBody
    public List<MvmnAplyVo> selectMvmnAplyDeList(MvmnAplyVo mvmnAplyVo) throws Exception {
        List<MvmnAplyVo> result = null;
        result =  mvmnAplyService.selectMvmnAplyDeList(mvmnAplyVo);
        return result;
    }
    
    /**
    * 푸름이 이동환경교실 교육신청 등록 화면 STEP1 이동
    *
    * @Title : mvmnAplyRegStep1
    * @Description : 푸름이 이동환경교실 교육신청 등록 화면 STEP1 이동
    * @param mvmnAplyVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/mvmnAply/mvmnAplyRegStep1.html")
    public String mvmnAplyRegStep1(MvmnAplyVo mvmnAplyVo, Model model) throws Exception {
        model.addAttribute("mvmnAplyParamVo", mvmnAplyVo);
        return "front/mvmnAply/mvmnAplyRegStep1";
    }    

    /**
     * 푸름이 이동환경교실 교육신청 등록 화면 STEP2 이동
     *
     * @Title : mvmnAplyRegStep2
     * @Description : 푸름이 이동환경교실 교육신청 등록 화면 STEP2 이동
     * @param mvmnAplyVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value="/front/mvmnAply/mvmnAplyRegStep2.html")
    public String mvmnAplyRegStep2(HttpServletResponse response, MvmnAplyVo mvmnAplyVo, BindingResult bindingResult, @UserInfo UserVo user, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            fieldError.getDefaultMessage();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + fieldError.getDefaultMessage() + "');location.href='/front/mvmnAply/mvmnAplyRegStep1.html';</script>");
            return null;
        }        
        model.addAttribute("mvmnAplyParamVo", mvmnAplyVo);
        
        MvmnAplyVo mvmnAplyRegVo = null;
        mvmnAplyVo.setUser(user);        
        mvmnAplyRegVo = mvmnAplyService.selectMvmnAplyRegInfo(mvmnAplyVo);
        model.addAttribute("mvmnAplyRegVo", mvmnAplyRegVo);
        
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        if(!"".equals(mvmnAplyRegVo.getTrgtCd())){
            String[] splitTrgtCdStr = mvmnAplyRegVo.getTrgtCd().split(",");
            String[] splitTrgtNmStr = mvmnAplyRegVo.getTrgtNm().split(",");
            
            for(int i=0; i<splitTrgtCdStr.length; i++){
                Map<String, String> map = new HashMap<String, String>();
                map.put("TRGTCD", splitTrgtCdStr[i]);
                map.put("TRGTNM", splitTrgtNmStr[i]);
                listMap.add(map);
            }
        }
        model.addAttribute("trgtList", listMap);
        
        return "front/mvmnAply/mvmnAplyRegStep2";
    }    

    /**
     * 푸름이 이동환경교실 교육신청 등록 화면 STEP3 이동
     *
     * @Title : mvmnAplyRegStep3
     * @Description : 푸름이 이동환경교실 교육신청 등록 화면 STEP3 이동
     * @param mvmnAplyVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value="/front/mvmnAply/mvmnAplyRegStep3.html")
    public String mvmnAplyRegStep3(HttpServletResponse response, MvmnAplyVo mvmnAplyVo, BindingResult bindingResult, Model model) throws Exception {
        model.addAttribute("data", mvmnAplyVo);
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            fieldError.getDefaultMessage();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + fieldError.getDefaultMessage() + "');location.href='/front/mvmnAply/mvmnAplyRegStep1.html';</script>");
            return null;
        }        
        return "front/mvmnAply/mvmnAplyRegStep3";
    }  
    
    @RequestMapping(value = "/front/mvmnAply/insertMvmnAply.do")
    @ResponseBody
    public Map<String, Object> insertMvmnAply(MvmnAplyVo mvmnAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        mvmnAplyVo.setUser(user);
        int retVal = 0;
        retVal = mvmnAplyService.insertMvmnAply(mvmnAplyVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }        
        return resultMap;    
    }
}
