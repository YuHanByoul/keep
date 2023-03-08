package com.kbrainc.plum.front.intro.envEduPlcyDta.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.intro.envEduPlcyDta.model.BsnsOperDtaVo;
import com.kbrainc.plum.front.intro.envEduPlcyDta.model.SpcltyDtaVo;
import com.kbrainc.plum.front.intro.envEduPlcyDta.service.EnvEduPlcyDtaService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 환경교육 정책자료실 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyDta.controller
 * - EnvEduPlcyDtaController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyDtaController
 * @Description : 환경교육 정책자료실 컨트롤러
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/intro/envEduPlcyDta")
public class EnvEduPlcyDtaController {

    private static final String VIEW_PATH = "/front/intro/envEduPlcyDta";

    @Resource(name = "front.envEduPlcyDtaService")
    private EnvEduPlcyDtaService envEduPlcyDtaService;

    /**
     * 사업운영 자료 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : bsnsOperDtaList
     * @Description : 사업운영 자료 목록 화면
     */
    @GetMapping("/bsnsOperDtaList.html")
    public String bsnsOperDtaList(BsnsOperDtaVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/bsnsOperDtaList";
    }

    /**
     * 사업운영자료 상세 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : bsnsOperDtaDetail
     * @Description : 사업운영자료 상세 화면
     */
    @GetMapping("/bsnsOperDtaDetail.html")
    public String bsnsOperDtaDetail(BsnsOperDtaVo searchVo, Model model) throws Exception {
        BsnsOperDtaVo bsnsOperDta = envEduPlcyDtaService.selectBsnsOperDta(searchVo);
        List<FileVo> pdfFileList = bsnsOperDta.getPdfFileList();
        List<String> pdfFilePaths = getPdfFilePaths(pdfFileList);

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("bsnsOperDta", bsnsOperDta);
        model.addAttribute("pdfFilePaths", pdfFilePaths);
        return VIEW_PATH + "/bsnsOperDtaDetail";
    }

    /**
     * 전문자료 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : spcltyDtaList
     * @Description : 전문자료 목록 화면
     */
    @GetMapping("/spcltyDtaList.html")
    public String spcltyDtaList(SpcltyDtaVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/spcltyDtaList";
    }

    /**
     * 전문자료 상세 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : spcltyDtaDetail
     * @Description : 전문자료 상세 화면
     */
    @GetMapping("/spcltyDtaDetail.html")
    public String spcltyDtaDetail(SpcltyDtaVo searchVo, Model model) throws Exception {
        SpcltyDtaVo spcltyDta = envEduPlcyDtaService.selectSpcltyDta(searchVo);
        List<FileVo> pdfFileList = spcltyDta.getPdfFileList();
        List<String> pdfFilePaths = getPdfFilePaths(pdfFileList);

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("spcltyDta", spcltyDta);
        model.addAttribute("pdfFilePaths", pdfFilePaths);
        return VIEW_PATH + "/spcltyDtaDetail";
    }

    /**
     * 사업운영자료 목록 조회
     *
     * @param bsnsOperDtaVo
     * @return map
     * @throws Exception
     * @Title : selectBsnsOperDtaList
     * @Description : 사업운영자료 목록 조회
     */
    @GetMapping("/selectBsnsOperDtaList.do")
    @ResponseBody
    public Map<String, Object> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        List<BsnsOperDtaVo> list = envEduPlcyDtaService.selectBsnsOperDtaList(bsnsOperDtaVo);

        if (list.size() > 0) {
            response.put("totalCount", list.get(0).getTotalCount());
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    /**
     * 전문자료 목록 조회
     *
     * @param spcltyDtaVo
     * @return map
     * @throws Exception
     * @Title : selectSpcltyDtaList
     * @Description : 전문자료 목록 조회
     */
    @GetMapping("/selectSpcltyDtaList.do")
    @ResponseBody
    public Map<String, Object> selectSpcltyDtaList(SpcltyDtaVo spcltyDtaVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        List<SpcltyDtaVo> list = envEduPlcyDtaService.selectSpcltyDtaList(spcltyDtaVo);

        if (list.size() > 0) {
            response.put("totalCount", list.get(0).getTotalCount());
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    /**
     * PDF 파일 경로를 얻어온다.
     * 파일명에 대괄호 포함시 문자열 변경
     *
     * @param pdfFileList
     * @return list
     * @Title : getPdfFilePaths
     * @Description : PDF 파일 경로를 얻어온다.
     */
    private List<String> getPdfFilePaths(List<FileVo> pdfFileList) {
        List<String> pdfFilePaths = new ArrayList<>();
        for (FileVo fileVo : pdfFileList) {
            String filePath = "/pdf_view_file/";
            String saveFileNm = fileVo.getSaveFileNm();
            saveFileNm = saveFileNm.replaceAll("\\[", "%5B");
            saveFileNm = saveFileNm.replaceAll("\\]", "%5D");
            pdfFilePaths.add(filePath + saveFileNm);
        }
        return pdfFilePaths;
    }

}
