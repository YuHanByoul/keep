package com.kbrainc.plum.mng.drmncy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.drmncy.model.DrmncyVo;
import com.kbrainc.plum.mng.drmncy.service.DrmncyServiceImpl;

/**
 * 
 * 휴면계정관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.drmncy.controller
 * - DrmncyController.java
 * </pre> 
 *
 * @ClassName : DrmncyController
 * @Description : 휴면계정관리 컨트롤러 클래스.
 * @author : 이한명
 * @date : 2023. 3. 21.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class DrmncyController {

    @Autowired
    private DrmncyServiceImpl drmncyService;
    
    /**
    * 휴면계정관리 리스트화면 이동.
    *
    * @Title       : drmncyForm 
    * @Description : 휴면계정관리 리스트화면 이동.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/drmncy/drmncyForm.html")
    public String drmncyForm() throws Exception {
        return "mng/drmncy/drmncy";
    }
    
    /**
    * 휴면회원목록 조회.
    *
    * @Title       : selectDrmncyList 
    * @Description : 휴면회원목록 조회.
    * @param drmncyVo DrmncyVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/drmncy/selectDrmncyList.do")
    @ResponseBody
    public Map<String, Object> selectDrmncyList(DrmncyVo drmncyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<DrmncyVo> result = null;
        
        result = drmncyService.selectDrmncyList(drmncyVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}