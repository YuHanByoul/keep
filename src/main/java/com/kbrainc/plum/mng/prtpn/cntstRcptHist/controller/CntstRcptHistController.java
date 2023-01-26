package com.kbrainc.plum.mng.prtpn.cntstRcptHist.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.prtpn.cntst.model.CntstVO;
import com.kbrainc.plum.mng.prtpn.cntst.service.CntstService;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstAplySchlVO;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstAplyVO;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.service.CntstRcptHistService;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.excel.ExcelDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 공모전 접수 내역 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.controller
 * - CntstRcptHistController.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstRcptHistController
 * @Description : 공모전 접수 내역 컨트롤러
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Controller
public class CntstRcptHistController {

    @Autowired
    CntstService cntstService;

    @Autowired
    CntstRcptHistService cntstRcptHistService;

    @Autowired
    ResCodeService resCodeService;

    @Autowired
    private FileService fileService;

    /**
     * 공모전 접수내역
     * Description : 공모전 접수내역
     *
     * @param model
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistListForm.html")
    public String cntstRcptHistListForm(Model model) throws Exception {
        Map<String, String> cntstClsfCdMap = resCodeService.getCodeMap("165");
        model.addAttribute("cntstClsfCdMap", cntstClsfCdMap);
        return "mng/prtpn/cntstRcptHist/cntstRcptHistList";
    }

    /**
     * 공모전 접수내역 목록 조회
     * Title : selectCntstRcptHistList
     * Description : 공모전 접수내역 목록 조회
     *
     * @param cntstAplyVO
     * @return map
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/selectCntstRcptHistList.do")
    @ResponseBody
    public Map<String, Object> selectCntstRcptHistList(CntstAplyVO cntstAplyVO) {
        Map<String, Object> resultMap = new HashMap<>();
        List<CntstAplyVO> result = null;
        result =  cntstRcptHistService.selectCntstAplyList(cntstAplyVO);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }

    /**
     * 공모전 접수내역 목록 엑셀 다운
     * Title : cntstRcptHistListExcelDown
     * Description : 공모전 접수내역 목록 엑셀 다운
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistListExcelDown.do")
    @ResponseBody
    public void cntstRcptHistListExcelDown(CntstAplyVO cntstAplyVO, HttpServletResponse response) throws Exception {
        Map<String, String> cntstClsfCdMap = resCodeService.getCodeMap("165");
        List<CntstAplyVO> result = cntstRcptHistService.selectCntstAplyList(cntstAplyVO);
        ExcelDownloadUtil<CntstAplyVO> excelDownUtil = new ExcelDownloadUtil<>(new String[] {"번호", "분류", "공모전제목", "접수번호", "접수자명", "접수기관명", "접수일자"}, result,
            (data, mapper, idx) -> {
                mapper
                        .putData(0, data.getRowNumber(), ExcelDownloadUtil.CELL_ALIGN.CENTER)
                        .putData(1, cntstClsfCdMap.get(data.getCntstClsfCd()))
                        .putData(2, data.getCntstTtl(), ExcelDownloadUtil.CELL_ALIGN.LEFT)
                        .putData(3, data.getAplyno())
                        .putData(4, data.getUserNm())
                        .putData(5, data.getInstNm())
                        .putData(6, data.getRegDt());
                return true;
            }
        );
        excelDownUtil.excelDownload(response, "공모전 접수내역 목록");
    }

    /**
     * 공모전 접수내역 조회
     * Title : cntstRcptHistView
     * Description : 공모전 접수내역 조회
     *
     * @param aplyid
     * @param model
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistView.html")
    public String cntstRcptHistView(Integer aplyid, ModelMap model) throws Exception {

        Map<String, String> cntstClsfCdMap = resCodeService.getCodeMap("165");
        model.addAttribute("cntstClsfCdMap", cntstClsfCdMap);
        Map<String, String> cntstFldCdMap = resCodeService.getCodeMap("166");
        model.addAttribute("cntstFldCdMap", cntstFldCdMap);
        CntstAplyVO cntstAplyVO = cntstRcptHistService.selectCntstAplyInfo(aplyid);
        CntstVO cntstVO = cntstService.selectCntstInfo(cntstAplyVO.getCntstid());
        List<String> cntstFldCdList = cntstService.selectCntstFldCdList(cntstAplyVO.getCntstid());
        model.addAttribute("cntstAplyVO", cntstAplyVO);
        model.addAttribute("cntstInfo", cntstVO);
        model.addAttribute("cntstFldCdList", cntstFldCdList);
        if (cntstVO.getClsfCd().equals("165105")) {
            List<CntstAplySchlVO> cntstAplySchlList = cntstRcptHistService.selectCntstAplySchlList(aplyid);
            model.addAttribute("cntstAplySchlList", cntstAplySchlList);
        }
        FileVo fileVo = new FileVo();
        if (cntstAplyVO.getPrdctFilegrpid() != null && !cntstAplyVO.getPrdctFilegrpid().equals(0)) {
            fileVo.setFilegrpid(cntstAplyVO.getPrdctFilegrpid());
            ArrayList<FileVo> prdctFileList = fileService.getFileList(fileVo);
            model.addAttribute("prdctFileList", prdctFileList);
        }
        return "mng/prtpn/cntstRcptHist/cntstRcptHistDetail";
    }
}
