package com.kbrainc.plum.front.data.controller;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * BBS Controller
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.controller
 * - BbsController.java
 * </pre> 
 *
 * @ClassName : BbsController
 * @Description : BBS Controller
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Controller("front.dataController")
@Alias("front.dataController")
public class DataController {


    @RequestMapping(value = "/front/profData/korean/1.html")
    public String study_data1_kr() throws Exception {
        return "front/data/study_data1_kr";
    }
    
    
    @RequestMapping(value = "/front/profData/korean/2.html")
    public String study_data2_kr() throws Exception {
    	return "front/data/study_data1_kr";
    }
    
    @RequestMapping(value = "/front/profData/elngish/1.html")
    public String study_data1_en() throws Exception {
    	return "front/data/study_data1_en";
    }
    @RequestMapping(value = "/front/profData/elngish/2.html")
    public String study_data2_en() throws Exception {
    	return "front/data/study_data1_en";
    }
    
    @RequestMapping(value = "/front/profData/math/1.html")
    public String study_data1_math() throws Exception {
    	return "front/data/study_data1_math";
    }
    @RequestMapping(value = "/front/profData/math/2.html")
    public String study_data2_math() throws Exception {
    	return "front/data/study_data1_math";
    }
    
    
    //국어 시험지 학습자료 
    @RequestMapping(value = "/front/beforeData/korean/paperData.html")
    public String study_prev_data1_kr() throws Exception {
    	return "front/data/study_prev_data1_kr";
    }
    
    @RequestMapping(value = "/front/beforeData/korean/paperData2.html")
    public String study_prev_data1_kr2() throws Exception {
    	return "front/data/study_prev_data1_kr_2";
    }
    
    @RequestMapping(value = "/front/beforeData/korean/paperData3.html")
    public String study_prev_data1_kr3() throws Exception {
    	return "front/data/study_prev_data1_kr_3";
    }
    
    @RequestMapping(value = "/front/beforeData/korean/data.html")
    public String study_prev_data2_kr() throws Exception {
    	return "front/data/study_prev_data2_kr";
    }
    
    //영어 시험지 학습 자료 
    @RequestMapping(value = "/front/beforeData/english/paperData.html")
    public String study_prev_data1_en() throws Exception {
    	return "front/data/study_prev_data1_en";
    }
    
    @RequestMapping(value = "/front/beforeData/english/paperData2.html")
    public String study_prev_data1_en2() throws Exception {
    	return "front/data/study_prev_data1_en_2";
    }
    
    @RequestMapping(value = "/front/beforeData/english/paperData3.html")
    public String study_prev_data1_en3() throws Exception {
    	return "front/data/study_prev_data1_en_3";
    }
    
    
    @RequestMapping(value = "/front/beforeData/english/data.html")
    public String study_prev_data2_en() throws Exception {
    	return "front/data/study_prev_data2_en";
    }
    
    
    //수학 시험지 학습 자료
    @RequestMapping(value = "/front/beforeData/math/paperData.html")
    public String study_prev_data1_math() throws Exception {
    	return "front/data/study_prev_data1_math";
    }
    @RequestMapping(value = "/front/beforeData/math/paperData2.html")
    public String study_prev_data1_math2() throws Exception {
    	return "front/data/study_prev_data1_math_2";
    }
    @RequestMapping(value = "/front/beforeData/math/paperData3.html")
    public String study_prev_data1_math3() throws Exception {
    	return "front/data/study_prev_data1_math_3";
    }
    
    @RequestMapping(value = "/front/beforeData/math/data.html")
    public String study_prev_data2_math() throws Exception {
    	return "front/data/study_prev_data2_math";
    }
    
    
}
