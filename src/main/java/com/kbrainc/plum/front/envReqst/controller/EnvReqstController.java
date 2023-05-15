package com.kbrainc.plum.front.envReqst.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.front.envReqst.model.AplyRsvtdeVo;
import com.kbrainc.plum.front.envReqst.model.EnvReqstVo;
import com.kbrainc.plum.front.envReqst.service.EnvReqstService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.mng.spce.service.SpceService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 환경교육시설 예약 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.envReqst.controller
 * - EnvReqstController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvReqstController
 * @Description : 환경교육시설 예약 Controller
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Controller
public class EnvReqstController {
    @Autowired
    private EnvReqstService envReqstService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SpceService spceService;

    @Autowired
    private CommonService commonService;

    /**
     * 환경교육시설 예약 목록 화면
     * Title : envReqstList
     * Description : 환경교육시설 예약 목록 화면
     *
     * @param envReqstVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/envReqst/envReqstList.html")
    public String envReqstList(EnvReqstVo envReqstVo, Model model) throws Exception {
        model.addAttribute("params", envReqstVo);
        model.addAttribute("sidoList", commonService.selectCtprvnList());
        return "front/envReqst/envReqstList";
    }

    @RequestMapping("/front/envReqst/selectEnvReqstList.do")
    @ResponseBody
    public Map<String, Object> selectEnvReqstList(EnvReqstVo envReqstVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        List<EnvReqstVo> list = envReqstService.selectEnvReqstList(envReqstVo);
        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping("/front/envReqst/selectResveEnvRvwList.do")
    @ResponseBody
    public Map<String, Object> selectResveEnvRvwList(EnvReqstVo envReqstVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        List<EnvReqstVo> list = envReqstService.selectResveEnvRvwList(envReqstVo);
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
     * 환경교육시설 예약 상세화면으로 이동
     *
     * @Title : resveEnvView
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param envReqstVo 환경교육시설 예약 객체
     * @param model model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/front/envReqst/resveEnvView.html")
    public String resveEnvView(EnvReqstVo envReqstVo, Model model, @UserInfo UserVo user, SpceVo spceVo) throws Exception {
        
        model.addAttribute("params", envReqstVo);
        envReqstVo.setUser(user);
        EnvReqstVo resultVo = envReqstService.selectResveEnvInfo(envReqstVo);
        
        model.addAttribute("envReqst", resultVo);
        FileVo fileVo = new FileVo();
        fileVo.setUser(user);
        // 대표 이미지
        if (resultVo.getRprsImgFileid() != null && !resultVo.getRprsImgFileid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getRprsImgFileid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("rprsImgFileMap",fileList );
        } else {
            model.addAttribute("rprsImgFileMap", null);
        }

        // 상세 이미지
        if (resultVo.getDtlImgFilegrpid() != null && !resultVo.getDtlImgFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getDtlImgFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("dtlImgFileMap",fileList );
            model.addAttribute("dtlCurrentFileCnt", fileList.size());
        } else {
            model.addAttribute("dtlImgFileMap", null);
            model.addAttribute("dtlCurrentFileCnt", 0);
        }

        // 안내자료
        if (resultVo.getGdncFileid() != null && !resultVo.getGdncFileid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getGdncFileid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("gdncFileMap",fileList );
            model.addAttribute("gdncCurrentFileCnt", fileList.size());
        } else {
            model.addAttribute("gdncFileMap", null);
            model.addAttribute("gdncCurrentFileCnt", 0);
        }
        
        // 공간 보유개수 조회
        spceVo.setFcltid(envReqstVo.getFcltid());
        spceVo.setUser(user);
        model.addAttribute("spceList", envReqstService.selectSpceListByFcltid(spceVo));

        return "front/envReqst/resveEnvView";
    }

    /**
     * 환경교육시설 예약 화면
     *
     * @Title : updateEnvReqst
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param envReqstVo 환경교육시설 예약 객체
     * @param model model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/front/envReqst/updateEnvReqst.html")
    public String updateEnvReqst(EnvReqstVo envReqstVo, Model model, @UserInfo UserVo user, SpceVo spceVo) throws Exception {

        model.addAttribute("params", envReqstVo);
        
        JSONParser parser = new JSONParser();
        //Object obj = parser.parse(envReqstVo.getRsvtdeid().toString());
        // 공간 보유개수 조회
        envReqstVo.setSpceid(envReqstVo.getSpceid());
        //envReqstVo.setRsvtdeid(obj);

        model.addAttribute("user", user);
        model.addAttribute("param", spceVo);
        model.addAttribute("spceInfo", envReqstService.selectSpceInfo(envReqstVo));

        List<Map<String, Object>> result = null;
        result = envReqstService.selectFclRsvtdeList(envReqstVo);

        int amt = 0;
        if( result.size() > 0 ){
            for( int i=0; i<result.size(); i++ ){
                if( result.get(i).get("RSVT_PSBLTY_YN").equals("Y") ){
                    amt += ((BigDecimal) result.get(i).get("AMT")).intValue();
                }
            }
        }

        model.addAttribute("amt", amt);
        model.addAttribute("alldayYn", result.get(0).get("ALLDAY_YN"));

        return "front/envReqst/updateEnvReqst";
    }

    /**
     * 예약 등록
     *
     * @Title : resveEnvUpdate
     * @Description : 예약 등록
     * @param envReqstVo 환경교육시설 예약 객체
     * @param bindingResult 예약 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/envReqst/resveEnvUpdate.do")
    @ResponseBody
    public Map<String, Object> resveEnvUpdate(@Valid EnvReqstVo envReqstVo, BindingResult bindingResult, @UserInfo UserVo user, InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        envReqstVo.setUser(user);
        int retVal = 0;
        
        List<AplyRsvtdeVo> checkList = envReqstService.selectReservedRsvtdeList(envReqstVo);
        
        
        if(checkList!= null && checkList.size() > 0 ) {
            
            boolean isThereImpossibleResve =  false;
            boolean isThereReservedResve =  false;
            for(AplyRsvtdeVo vo :checkList) {
                if(vo.getRsvtPsbltyYn().equals("N")){
                    isThereImpossibleResve = true;
                }
            }
            String failMsg=(isThereImpossibleResve)? "운영중지 된 일정이 존재 합니다. 일정을 다시 확인 후 신청 하여주시기 바랍니다."
                    :"이미 예약이 신청(승인) 된 일정이 존재합니다.일정을 다시 확인 후 신청 하여주시기 바랍니다. ";
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", failMsg);
            return resultMap;
        }
        
        retVal = envReqstService.insertResveEnvFclSpceAply(envReqstVo);
        
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
     * 환경교육시설 예약 완료 화면
     * Title : envReqstComplete
     * Description : 환경교육시설 예약 완료 화면
     *
     * @param envReqstVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/envReqst/envReqstComplete.html")
    public String envReqstComplete(EnvReqstVo envReqstVo, Model model) throws Exception {
        return "front/envReqst/envReqstComplete";
    }

    /**
     * 예약가능일자 리스트 호출
     *
     * @Title       : selectSpceRsvtdeList
     * @Description : 예약가능일자 리스트 호출
     * @param envReqstVo EnvReqstVo envReqstVo 객체
     * @return List<SpceRsvtdeVo> 기관정보 목록
     * @throws Exception 예외
     */
    @ResponseBody
    @RequestMapping(value = "/front/envReqst/selectSpceRsvtdeList.do")
    public Map<String, Object> selectSpceRsvtdeList(EnvReqstVo envReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnvReqstVo> result = null;
        envReqstVo.setUser(user);
        result = envReqstService.selectSpceRsvtdeList(envReqstVo);
        resultMap.put("rsvtdelist", result);
        return resultMap;
    }
}
