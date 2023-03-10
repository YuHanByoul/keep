package com.kbrainc.plum.front.enveduFlct.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.envEdu.model.PrgrmVo;
import com.kbrainc.plum.front.enveduCntr.model.EnveduCntrVO;
import com.kbrainc.plum.front.enveduFlct.model.EnveduFcltVo;
import com.kbrainc.plum.front.enveduFlct.service.EnveduFlctService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;


/**
 * 내 주변 환경교육시설 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.enveduFlct.controller
 * - EnveduFlctController.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : EnveduFlctController
 * @Description : 내 주변 환경교육시설 Controller
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller
public class EnveduFlctController {
    
    @Autowired
    private EnveduFlctService enveduFlctService;
    
    /**
     * 내 주변 환경교육 시설 페이지 이동
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 페이지 이동
     * @return String 이동화면경로
     * @throws Exception 예외
     */
     @RequestMapping(value = "/front/nearbyEnveduFlct.html")
     public String nearbyEnveduFlct(HttpServletRequest request, Model model) throws Exception {
         
         Map searchMap = new HashMap<>();
         int pagePerCnt = 10;
         int selectedPage = 1;
         int totalPage = 0;
         
         searchMap.put("pagePerCnt", pagePerCnt);
         searchMap.put("selectedPage", selectedPage);
         
         List<Map<String, Object>> list = enveduFlctService.nearbyEnveduFlct(searchMap);
         int totalCnt = enveduFlctService.nearbyEnveduFlctCount(searchMap);

         
         model.addAttribute("list", list);
         if(list.size() > 0) {
             totalPage = totalCnt / pagePerCnt + 1;
         }
         
         searchMap.put("totalPage", totalPage);
         searchMap.put("totalCnt", totalCnt);
         
         model.addAttribute("pagenationInfo", searchMap);
         
         return "front/nearbyEnveduFlct";
     }
     
     /**
      * 내 주변 환경교육 시설 검색 리스트 반환
      *
      * @Title       : searchEnveduFlct
      * @Description : 내 주변 환경교육 시설 검색 리스트 반환
      * @return List<Map<String, Object>>
      * @throws Exception 예외
      */
      @RequestMapping(value = "/front/map/searchNearbyEnveduFlct.do", method = RequestMethod.POST, produces = "application/json")
      @ResponseBody
      public  Map<String, Object> searchEnveduFlct(@RequestBody HashMap<String, Object> param, HttpServletRequest request) throws Exception {
          Map<String, Object> resultMap = new HashMap<>();
          Map searchMap = new HashMap<>();
          
          int pagePerCnt = 10;
          int selectedPage = (int) param.get("selectedPage");
          int totalPage = 0;
          
          if(param.get("selectedPage") != null && !"".equals(param.get("selectedPage"))) {
              selectedPage = (int) param.get("selectedPage");
          }
          
          if(param.get("keyword") != null && !"".equals(param.get("keyword"))) {
              searchMap.put("keyword", param.get("keyword"));
          }
          
          if(param.get("type") != null && !"".equals(param.get("type"))) {
              searchMap.put("searchType", param.get("type"));
          }
          
          if(param.get("category") != null && !"".equals(param.get("category"))) {
              searchMap.put("category", param.get("category"));
          }
          
          searchMap.put("pagePerCnt", 10);
          searchMap.put("selectedPage", selectedPage);
          
          List<Map<String, Object>> list = enveduFlctService.nearbyEnveduFlct(searchMap);
          int totalCnt = enveduFlctService.nearbyEnveduFlctCount(searchMap);
          
          resultMap.put("list", list);
          if(list.size() > 0) {
              totalPage = totalCnt / pagePerCnt + 1;
          }

          searchMap.put("totalPage", totalPage);
          searchMap.put("totalCnt", totalCnt);
          
          resultMap.put("pagenationInfo", searchMap);
          
          return resultMap;
      }
      
      @RequestMapping(value = "/front/enveduFlct/enveduFcltListForm.html")
      public String enveduFcltListForm() throws Exception {
          return "front/enveduFclt/enveduFcltList";
      }
      
      @RequestMapping(value="/front/enveduFlct/selectEnveduFcltList.do")
      @ResponseBody
      public Map<String, Object> selectEnveduFcltList() throws Exception {
          Map<String, Object> resultMap = new HashMap<>();
          List<EnveduFcltVo> result = null;
          
          result =  enveduFlctService.selectEnveduFcltList();
          
          if (result.size() > 0) {
              resultMap.put("totalCount", (result.get(0).getTotalCount()));
          } else {
              resultMap.put("totalCount", 0);
          }
          resultMap.put("list", result);
          
          return resultMap;
      }

}
