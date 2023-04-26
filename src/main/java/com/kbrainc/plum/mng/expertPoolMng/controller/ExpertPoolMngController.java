package com.kbrainc.plum.mng.expertPoolMng.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.file.service.FileStorageService;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertLogVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertReviewHistoryVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertVo;
import com.kbrainc.plum.mng.expertPoolMng.service.ExpertPoolMngService;
import com.kbrainc.plum.rte.exception.FiledownloadCheckerException;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.penta.scpdb.ScpDbAgent;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 전문가 풀 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.controller
 * - ExpertPoolMngController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertPoolMngController
 * @Description : 전문가 풀 관리 컨트롤러 클래스
 * @date : 2022. 12. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class ExpertPoolMngController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpertPoolMngService expertPoolMngService;

    @Autowired
    private FileService fileService;
    @Autowired
    FileStorageService fileStorageService;

    /**
     * 전문가 목록 화면
     *
     * @param exprtTypeCd
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertList
     * @Description : 전문가 목록 화면
     */
    @RequestMapping("/mng/expertPoolMng/{exprtTypeCd}/expertList.html")
    public String expertList(@PathVariable String exprtTypeCd,Model model) throws Exception {
        model.addAttribute("exprtTypeCd", exprtTypeCd);
        return "mng/expertPoolMng/expertList";
    }

    /**
     * 전문가 목록 조회
     *
     * @param expertVo
     * @return map
     * @throws Exception
     * @Title : selectExpertList
     * @Description : 전문가 목록 조회
     */
    @RequestMapping("/mng/expertPoolMng/selectExpertList.do")
    @ResponseBody
    public Map<String, Object> selectExpertList(ExpertVo expertVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<ExpertVo> list = expertPoolMngService.selectExpertList(expertVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 전문가 상세 탭 이동
     *
     * @param expertVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertDetail
     * @Description : 전문가 상세 탭 화면 이동
     */
    @RequestMapping("/mng/expertPoolMng/expertDetail.html")
    public String expertDetail(ExpertVo expertVo, Model model) throws Exception {
        model.addAttribute("expertInfo", expertVo);
        return "mng/expertPoolMng/expertDetail";
    }

    /**
     * 전문가 신청정보 탭 상세 화면
     *
     * @param expertVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertApplyInfoForm
     * @Description : 전문가 신청정보 상세 화면
     */
    @RequestMapping("/mng/expertPoolMng/expertApplyInfoForm.html")
    public String expertApplyInfoForm(ExpertVo expertVo, Model model) throws Exception {
        ExpertVo expertApplyInfo = expertPoolMngService.selectExpertApplyInfo(expertVo);
        model.addAttribute("expertApplyInfo" , expertApplyInfo);
        return "mng/expertPoolMng/expertApplyInfoForm";
    }


    /**
     * 회원정보 탭 상세화면
     *
     * @param expertVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertInfoForm
     * @Description : 회원정보 탭 상세화면
     */
    @RequestMapping("/mng/expertPoolMng/expertInfoForm.html")
    public String expertInfoForm(ExpertVo expertVo, Model model) throws Exception {

        ExpertVo expertInfo = expertPoolMngService.selectExpertInfo(expertVo);

        ScpDbAgent agt = new ScpDbAgent();
        String decStr = "";
        if (System.getenv("PC_KIND") != null) {
            decStr = agt.ScpDecStr(CommonUtil.damoScpIniFilePath, "KEY1", expertInfo.getGndr());
        } else {
            decStr = "M"; // 암호화 모듈을 사용할수 없는 MAC인경우 무조건 남자로 설정.
        }

        expertInfo.setGndr(decStr);
        model.addAttribute("expertInfo", expertInfo);
        return "mng/expertPoolMng/expertInfoForm";
    }

    /**
     * 후기이력 탭 상세화면
     *
     * @param expertReviewHistoryVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertReviewHistoryForm
     * @Description : 후기이력 탭 상세화면
     */
    @RequestMapping("/mng/expertPoolMng/expertReviewHistoryForm.html")
    public String expertReviewHistoryForm(ExpertReviewHistoryVo expertReviewHistoryVo, Model model) throws Exception {
        model.addAttribute("scrAvg", expertPoolMngService.getExpertReviewScrAvg(expertReviewHistoryVo));
        return "mng/expertPoolMng/expertReviewHistoryForm";
    }

    /**
     * 상태변경이력 탭 상세화면
     *
     * @return string
     * @throws Exception
     * @Title : expertStatusChangeHistoryForm
     * @Description : 상태변경이력 탭 상세화면
     */
    @RequestMapping("/mng/expertPoolMng/expertStatusChangeHistoryForm.html")
    public String expertStatusChangeHistoryForm() throws Exception {
        return "mng/expertPoolMng/expertStatusChangeHistoryForm";
    }

    /**
     * 전문가 상태 변경
     *
     * @param expertVo
     * @return boolean
     * @throws Exception
     * @Title : updateExpertStatus
     * @Description : 전문가 상태 변경
     */
    @RequestMapping("/mng/expertPoolMng/updateExpertStatus.do")
    @ResponseBody
    public boolean updateExpertStatus(ExpertVo expertVo, ExpertLogVo expertLogVo, @UserInfo UserVo user) throws Exception {
        expertLogVo.setUser(user);

        return expertPoolMngService.updateExpertStatus(expertVo, expertLogVo) > 0;
    }

    /**
     * 전문가 후기 이력 조회
     *
     * @param expertReviewHistoryVo
     * @return map
     * @throws Exception
     * @Title : selectReviewHistoryList
     * @Description : 전문가 후기 이력 조회
     */
    @RequestMapping("/mng/expertPoolMng/selectExpertReviewHistoryList.do")
    @ResponseBody
    public Map<String,Object> selectExpertReviewHistoryList(ExpertReviewHistoryVo expertReviewHistoryVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<ExpertReviewHistoryVo> list = expertPoolMngService.selectExpertReviewHistoryList(expertReviewHistoryVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 전문가 상태변경 이력 조회
     *
     * @param expertLogVo
     * @return map
     * @throws Exception
     * @Title : selectExpertList
     * @Description : 전문가 상태변경 이력 조회
     */
    @RequestMapping("/mng/expertPoolMng/selectExpertLogList.do")
    @ResponseBody
    public Map<String,Object> selectExpertLogList(ExpertLogVo expertLogVo) throws Exception {

        Map<String, Object> result = new HashMap<>();

        List<ExpertLogVo> list = expertPoolMngService.selectExpertLogList(expertLogVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 전문가 파일 다운로드 및 로그 생성
     *
     * @param fileid
     * @param fileIdntfcKey
     * @param expertLogVo
     * @param request
     * @param user
     * @return response entity
     * @throws Exception
     * @Title : downloadFile
     * @Description : 전문가 파일 다운로드 및 로그 생성
     */
    @PostMapping("/mng/expertPoolMng/downloadFileByFileid.do")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestParam(name="fileid",required=true) int fileid, @RequestParam(name="file_idntfc_key",required=true) String fileIdntfcKey
                                                 , ExpertLogVo expertLogVo,HttpServletRequest request, @UserInfo UserVo user) throws Exception {
        FileVo fileVo = new FileVo();
        fileVo.setFileid(fileid);
        fileVo.setFileIdntfcKey(fileIdntfcKey);
        String fileName ="";
        String contentType = null;
        try {
            fileVo=fileService.selectFile(fileVo);
            fileName = fileVo.getSaveFileNm();
        }catch(SQLException e) {
            logger.info("Could not fileSql SQLException ");
        }catch(Exception e) {
            logger.info("Could not fileSql Exception ");
        }

        if (!fileService.downloadFileCheck(fileVo, user)) {
            throw new FiledownloadCheckerException("You do not have access to the file. " + fileVo.getSaveFileNm());
        }

        Resource resource = fileStorageService.loadFileAsResource(fileVo);

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        expertLogVo.setUser(user);
        expertPoolMngService.insertExpertLog(expertLogVo);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileVo.getOrginlFileNm().getBytes("EUC-KR"),"ISO-8859-1") + "\"")
                .header("Set-Cookie","fileDownload=true;path=/")
                .body(resource);
    }
}
