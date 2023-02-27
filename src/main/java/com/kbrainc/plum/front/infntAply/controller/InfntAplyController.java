/**
 * 
 */
package com.kbrainc.plum.front.infntAply.controller;

import java.io.PrintWriter;
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
import com.kbrainc.plum.front.infntAply.service.InfntAplyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 유아환경교육관 교육신청 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.infntAply.controller
* - InfntAplyController.java
* </pre>
*
* @ClassName : InfntAplyController
* @Description : TODO
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.infntAplyController")
@Alias("front.infntAplyController")
public class InfntAplyController {

    @Resource(name = "front.infntAplyServiceImpl")
    private InfntAplyService InfntAplyService;
    
    /**
    * 유아환경교육관 교육신청 게시글 목록 화면 이동
    *
    * @Title : infntAplyListForm
    * @Description : 유아환경교육관 교육신청 게시글 목록 화면 이동
    * @param infntAplyVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/infntAply/infntAplyList.html")
    public String infntAplyListForm() throws Exception {
        return "front/infntAply/infntAplyList";
    }
     
    /**
    * 유아환경교육관 교육신청 게시글 상세 화면 이동
    *
    * @Title : infntAplyDetailForm
    * @Description : 유아환경교육관 교육신청 게시글 상세 화면 이동
    * @param infntAplyVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/infntAply/infntAplyDetailForm.html")
    public String infntAplyDetailForm(InfntAplyVo infntAplyVo, Model model) throws Exception {
        InfntAplyVo infntAply = null;
        infntAply = InfntAplyService.selectInfntAplyInfo(infntAplyVo);
        model.addAttribute("infntAply", infntAply);
        
        infntAplyVo.setInstid(infntAply.getInstid());
        infntAplyVo.setPrgrmid(infntAply.getPrgrmid());
        
        List<InfntAplyVo> eduPhotoFileList = null;
        List<InfntAplyVo> infntAplyTmeList = null;
        List<InfntAplyVo> infntAplyEduClssRmList = null;
        
        eduPhotoFileList = InfntAplyService.selectEduPhotoFileList(infntAplyVo);
        model.addAttribute("eduPhotoFileList", eduPhotoFileList);
        
        infntAplyTmeList =  InfntAplyService.selectInfntAplyTmeList(infntAplyVo);
        //infntAplyList = InfntAplyService.selectInstInfntAplyList(infntAplyVo);
        if(infntAplyTmeList.size() <= 0) {
            model.addAttribute("infntAplyTmeList", "null");
        }else {
            model.addAttribute("infntAplyTmeList", infntAplyTmeList);
        }

        infntAplyEduClssRmList =  InfntAplyService.selectInfntAplyEduClssRmList(infntAplyVo);
        if(infntAplyEduClssRmList.size() <= 0) {
            model.addAttribute("infntAplyEduClssRmList", "null");
        }else {
            model.addAttribute("infntAplyEduClssRmList", infntAplyEduClssRmList);
        }
        
        return "front/infntAply/infntAplyDetail";
    }
      
    /**
    * 유아환경교육관 교육신청 게시글 목록 조회
    *
    * @Title : selectInfntAplyList
    * @Description : 유아환경교육관 교육신청 게시글 목록 조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/front/infntAply/selectInfntAplyList.do")
    @ResponseBody
    public Map<String, Object> selectInfntAplyList(InfntAplyVo infntAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InfntAplyVo> result = null;
        
        result =  InfntAplyService.selectInfntAplyList(infntAplyVo);
        
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
     * 유아환경교육관 교육신청 날짜조회
     *
     * @Title : selectInfntAplyDeList
     * @Description : 유아환경교육관 교육신청 날짜조회
     * @param infntAplyVo
     * @return
     * @throws Exception
     * @return List<InfntAplyVo>
     */
    @RequestMapping(value="/front/infntAply/selectInfntAplyDeList.do")
    @ResponseBody
    public List<InfntAplyVo> selectInfntAplyDeList(InfntAplyVo infntAplyVo) throws Exception {
        List<InfntAplyVo> result = null;
        result =  InfntAplyService.selectInfntAplyDeList(infntAplyVo);
        return result;
    }
    
    /**
    * 유아환경교육관 교육신청 등록 화면 STEP1 이동
    *
    * @Title : infntAplyRegStep1
    * @Description : 유아환경교육관 교육신청 등록 화면 STEP1 이동
    * @param infntAplyVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/infntAply/infntAplyRegStep1.html")
    public String infntAplyRegStep1(InfntAplyVo infntAplyVo, Model model) throws Exception {
        model.addAttribute("infntAplyParamVo", infntAplyVo);
        return "front/infntAply/infntAplyRegStep1";
    }    

    /**
     * 유아환경교육관 교육신청 등록 화면 STEP2 이동
     *
     * @Title : infntAplyRegStep2
     * @Description : 유아환경교육관 교육신청 등록 화면 STEP2 이동
     * @param infntAplyVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value="/front/infntAply/infntAplyRegStep2.html")
    public String infntAplyRegStep2(HttpServletResponse response, InfntAplyVo infntAplyVo, BindingResult bindingResult, @UserInfo UserVo user, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            fieldError.getDefaultMessage();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + fieldError.getDefaultMessage() + "');location.href='/front/infntAply/infntAplyRegStep1.html';</script>");
            return null;
        }        
        model.addAttribute("infntAplyParamVo", infntAplyVo);
        
        InfntAplyVo infntAplyRegVo = null;
        infntAplyVo.setUser(user);        
        infntAplyRegVo = InfntAplyService.selectInfntAplyRegInfo(infntAplyVo);
        model.addAttribute("infntAplyRegVo", infntAplyRegVo);
        
        return "front/infntAply/infntAplyRegStep2";
    }    

    /**
     * 유아환경교육관 교육신청 등록 화면 STEP3 이동
     *
     * @Title : infntAplyRegStep3
     * @Description : 유아환경교육관 교육신청 등록 화면 STEP3 이동
     * @param infntAplyVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value="/front/infntAply/infntAplyRegStep3.html")
    public String infntAplyRegStep3(HttpServletResponse response, InfntAplyVo infntAplyVo, BindingResult bindingResult, Model model) throws Exception {
        model.addAttribute("data", infntAplyVo);
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            fieldError.getDefaultMessage();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + fieldError.getDefaultMessage() + "');location.href='/front/infntAply/infntAplyRegStep1.html';</script>");
            return null;
        }        
        return "front/infntAply/infntAplyRegStep3";
    }  
    
    @RequestMapping(value = "/front/infntAply/insertInfntAply.do")
    @ResponseBody
    public Map<String, Object> insertInfntAply(InfntAplyVo infntAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        retVal = InfntAplyService.insertInfntAply(infntAplyVo);
        
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
