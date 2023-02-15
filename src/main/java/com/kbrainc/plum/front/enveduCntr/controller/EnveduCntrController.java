package com.kbrainc.plum.front.enveduCntr.controller;

import com.kbrainc.plum.front.enveduCntr.model.EnveduCntrVO;
import com.kbrainc.plum.front.enveduCntr.service.EnveduCntrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 지역 환경교육센터 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.enveduCntr.controller
 * - EnveduCntrController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnveduCntrController
 * @Description : 지역 환경교육센터 Controller
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Controller
public class EnveduCntrController {
    @Autowired
    private EnveduCntrService enveduCntrService;

    /**
     * 국가 환경교육센터 목록 화면
     * Title : crEnveduCntrList
     * Description : 국가 환경교육센터 목록 화면
     *
     * @param enveduCntrVO
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/enveduCntr/crEnveduCntrList.html")
    public String crEnveduCntrList(EnveduCntrVO enveduCntrVO, Model model) throws Exception {
        Map<String,List<EnveduCntrVO>> listMap = new HashMap<>();
        enveduCntrVO.setSeCd("138101");
        List<EnveduCntrVO> list = enveduCntrService.selectEnveduCntrList(enveduCntrVO);
        for(EnveduCntrVO item : list ){
            List<EnveduCntrVO> cntrList = listMap.get(item.getRgnCd());
            if(cntrList == null){
                cntrList = new LinkedList<>();
                listMap.put(item.getRgnCd(), cntrList);
            }
            cntrList.add(item);
        }
        model.addAttribute("list",listMap);
        model.addAttribute("enveduCntrTitle","국가 환경교육센터");
        return "front/enveduCntr/enveduCntrList";
    }

    /**
     * 광역 환경교육센터 목록 화면
     * Title : waEnveduCntrList
     * Description : 광역 환경교육센터 목록 화면
     *
     * @param enveduCntrVO
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/enveduCntr/waEnveduCntrList.html")
    public String waEnveduCntrList(EnveduCntrVO enveduCntrVO, Model model) throws Exception {
        Map<String,List<EnveduCntrVO>> listMap = new HashMap<>();
        enveduCntrVO.setSeCd("138102");
        List<EnveduCntrVO> list = enveduCntrService.selectEnveduCntrList(enveduCntrVO);
        for(EnveduCntrVO item : list ){
//            if(listMap.containsKey(item.getRgnCd())){
//                List<EnveduCntrVO> cntrList = new LinkedList<>();
//                cntrList.add(item);
//                listMap.get(item.getRgnCd()).add(item);
//            }else{
//                List<EnveduCntrVO> cntrList = new LinkedList<>();
//                cntrList.add(item);
//                listMap.put(item.getRgnCd(), cntrList);
//            }
            List<EnveduCntrVO> cntrList = listMap.get(item.getRgnCd());
            if(cntrList == null){
                cntrList = new LinkedList<>();
                listMap.put(item.getRgnCd(), cntrList);
            }
            cntrList.add(item);
        }
        model.addAttribute("list",listMap);
        model.addAttribute("enveduCntrTitle","광역 환경교육센터");
        return "front/enveduCntr/enveduCntrList";
    }

    /**
     * 기초 환경교육센터 목록 화면
     * Title : basicEnveduCntrList
     * Description : 기초 환경교육센터 목록 화면
     *
     * @param enveduCntrVO
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/enveduCntr/basicEnveduCntrList.html")
    public String basicEnveduCntrList(EnveduCntrVO enveduCntrVO, Model model) throws Exception {
        Map<String,List<EnveduCntrVO>> listMap = new HashMap<>();
        enveduCntrVO.setSeCd("138103");
        List<EnveduCntrVO> list = enveduCntrService.selectEnveduCntrList(enveduCntrVO);
        for(EnveduCntrVO item : list ){
            List<EnveduCntrVO> cntrList = listMap.get(item.getRgnCd());
            if(cntrList == null){
                cntrList = new LinkedList<>();
                listMap.put(item.getRgnCd(), cntrList);
            }
            cntrList.add(item);
        }
        model.addAttribute("list",listMap);
        model.addAttribute("enveduCntrTitle","기초 환경교육센터");
        return "front/enveduCntr/enveduCntrList";
    }
}
