package com.kbrainc.plum.front.mypage.mypageEnvReqst.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.mypage.mypageEnvReqst.model.MypageEnvReqstVo;
import com.kbrainc.plum.front.mypage.mypageEnvReqst.service.MypageEnvReqstService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeVo;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.mng.resveReqst.service.ResveReqstService;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.mng.spce.service.SpceService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 환경교육시설 예약 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.mypageEnvReqst.controller
 * - MypageEnvReqstController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MypageEnvReqstController
 * @Description : 환경교육시설 예약 Controller
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Controller
public class MypageEnvReqstController {
    @Autowired
    private MypageEnvReqstService mypageEnvReqstService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SpceService spceService;

    @Autowired
    private ResveReqstService resveReqstService;

    /**
     * 환경교육시설 예약 목록 화면
     * Title : mypageEnvReqstList
     * Description : 환경교육시설 예약 목록 화면
     *
     * @param mypageEnvReqstVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/mypage/mypageEnvReqst/mypageEnvReqstList.html")
    public String mypageEnvReqstList(MypageEnvReqstVo mypageEnvReqstVo, Model model) throws Exception {
        Map<String,List<MypageEnvReqstVo>> listMap = new HashMap<>();
        List<MypageEnvReqstVo> list = mypageEnvReqstService.selectMypageEnvReqstList(mypageEnvReqstVo);
        model.addAttribute("list",listMap);
        return "front/mypage/mypageEnvReqst/mypageEnvReqstList";
    }

    @RequestMapping("/front/mypage/mypageEnvReqst/selectMypageEnvReqstList.do")
    @ResponseBody
    public Map<String, Object> selectMypageEnvReqstList(MypageEnvReqstVo mypageEnvReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();

        mypageEnvReqstVo.setUser(user);
        List<MypageEnvReqstVo> list = mypageEnvReqstService.selectMypageEnvReqstList(mypageEnvReqstVo);
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
     * @Title : mypageResveEnvView
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @param model model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/front/mypage/mypageEnvReqst/mypageResveEnvView.html")
    public String mypageResveEnvView(MypageEnvReqstVo mypageEnvReqstVo, Model model, @UserInfo UserVo user, SpceVo spceVo) throws Exception {

        model.addAttribute("param", mypageEnvReqstVo);

        mypageEnvReqstVo.setUser(user);
        MypageEnvReqstVo resultVo = mypageEnvReqstService.selectResveEnvInfo(mypageEnvReqstVo);

        model.addAttribute("mypageEnvReqst", resultVo);


        FileVo fileVo = new FileVo();
        fileVo.setUser(user);

        // 대표 이미지
        if (resultVo.getRprsImgFileid() != null && !resultVo.getRprsImgFileid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getRprsImgFileid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("rprsImgFileMap",fileList );
            model.addAttribute("rprsCurrentFileCnt", fileList.size());
        } else {
            model.addAttribute("rprsImgFileMap", null);
            model.addAttribute("rprsCurrentFileCnt", 0);
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

        return "front/mypage/mypageEnvReqst/mypageResveEnvView";
    }

    /**
     * 사유 확인 팝업
     * Title : rsnPopup
     * Description : 사유 확인 팝업
     *
     * @param mypageEnvReqstVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/mypage/mypageEnvReqst/rsnPopup.html")
    public String rsnPopup(MypageEnvReqstVo mypageEnvReqstVo, Model model) throws Exception {
        MypageEnvReqstVo data = mypageEnvReqstService.selectRsnInfo(mypageEnvReqstVo);
        model.addAttribute("mypageEnvReqst",data);
        return "front/mypage/mypageEnvReqst/rsnPopup";
    }

    /**
     * 입금정보 팝업
     * Title : dpstInfoPopup
     * Description : 입금정보 팝업
     *
     * @param mypageEnvReqstVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/mypage/mypageEnvReqst/dpstInfoPopup.html")
    public String dpstInfoPopup(MypageEnvReqstVo mypageEnvReqstVo, Model model) throws Exception {
        MypageEnvReqstVo data = mypageEnvReqstService.selectDpstInfo(mypageEnvReqstVo);
        model.addAttribute("mypageEnvReqst",data);
        return "front/mypage/mypageEnvReqst/dpstInfoPopup";
    }

    /**
     * 예약취소사유 등록 팝업
     * Title : insertRsnPopup
     * Description : 예약취소사유 등록 팝업
     *
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/mypage/mypageEnvReqst/insertRsnPopup.html")
    public String insertRsnPopup(MypageEnvReqstVo mypageEnvReqstVo, Model model) throws Exception {
        model.addAttribute("aplyid",mypageEnvReqstVo.getAplyid());
        return "front/mypage/mypageEnvReqst/insertRsnPopup";
    }

    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateFcltMng
     * @Description : 예약 신청 취소 처리
     * @param mypageEnvReqstVo 입금 전 객체
     * @param bindingResult 입금 전 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/mypage/mypageEnvReqst/insertRsn.do")
    @ResponseBody
    public Map<String, Object> insertRsn(@Valid MypageEnvReqstVo mypageEnvReqstVo, BindingResult bindingResult, @UserInfo UserVo user, ResveReqstVo resveReqstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        mypageEnvReqstVo.setUser(user);

        int retVal = 0;

        retVal = mypageEnvReqstService.insertRsn(mypageEnvReqstVo);

        // 상태변경 데이터 세팅
        resveReqstVo.setAplyid(mypageEnvReqstVo.getAplyid());
        resveReqstVo.setUser(user);
        // 상태변경이력 추가
        resveReqstService.insertHstry(resveReqstVo);

        // 상태변경이력 추가
//        mypageEnvReqstService.insertHstry(mypageEnvReqstVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "예약신청 취소에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "예약신청 취소에 실패했습니다.");
        }

        return resultMap;
    }
}
