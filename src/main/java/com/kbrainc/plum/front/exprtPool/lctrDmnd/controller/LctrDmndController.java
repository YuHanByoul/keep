package com.kbrainc.plum.front.exprtPool.lctrDmnd.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.LctrDmndVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.service.LctrDmndService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.apache.ibatis.type.Alias;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 환경교육 전문가 풀 > 섭외 요청 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.controller
 * - LctrDmndController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndController
 * @Description : 환경교육 전문가 풀 > 섭외 요청 컨트롤러 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.lctrDmndController")
@Controller("front.lctrDmndController")
@RequestMapping("/front/exprtPool")
public class LctrDmndController {
    private static final String VIEW_PATH = "/front/exprtPool";

    @Resource(name="front.lctrDmndService")
    private LctrDmndService lctrDmndService;

    @Autowired
    private FileServiceImpl fileService;

    @Value("${crypto.key}")
    private String encryptKey;
    /**
     * 섭외요청 목록 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndList
     * @Description : 섭외요청 목록 화면
     */
    @GetMapping("/lctrDmndList.html")
    public String lctrDmndList(ExprtVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/lctrDmndList";
    }

    /**
     * 섭외요청 상세 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndDetail
     * @Description : 섭외요청 상세 화면
     */
    @GetMapping("/lctrDmndDetail.html")
    public String lctrDmndDetail(ExprtVo searchVo, Model model) throws Exception {
        ExprtVo exprt = lctrDmndService.selectExprt(searchVo);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String decStr = encryptor.decrypt(exprt.getGndr());
        exprt.setGndr(decStr);

        model.addAttribute("searchVo",searchVo);
        model.addAttribute("exprt", exprt);
        return VIEW_PATH + "/lctrDmndDetail";
    }

    /**
     * 섭외 요청 등록 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndForm
     * @Description : 섭외 요청 등록 화면
     */
    @GetMapping("/lctrDmndForm.html")
    public String lctrDmndForm(ExprtVo searchVo, Model model) throws Exception {

        Map<String, Object> fileConfiguration = fileService.getConfigurationByFilegrpName("lctrDmnd_file");
        String uploadFileExtsn = ((HashMap<String, String>) fileConfiguration.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnKey -> "." + fileExtsnKey.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("fileConfiguration", fileConfiguration);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);

        return VIEW_PATH + "/lctrDmndForm";
    }

    /**
     * 섭외 요청 완료 화면
     *
     * @return string
     * @throws Exception
     * @Title : lctrDmndSuccess
     * @Description : 섭외 요청 완료 화면
     */
    @GetMapping("/lctrDmndSuccess.html")
    public String lctrDmndSuccess() throws Exception {
        return VIEW_PATH + "/lctrDmndSuccess";
    }

    /**
     * 전문가 목록 조회
     *
     * @param searchVO
     * @return map
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 전문가 목록 조회
     */
    @GetMapping("/selectExprtList.do")
    @ResponseBody
    public Map<String,Object> selectExprtList(ExprtVo searchVo, @UserInfo UserVo user) throws Exception{

        Map<String,Object> response = new HashMap<>();

        searchVo.setUser(user);

        List<ExprtVo> list = lctrDmndService.selectExprtList(searchVo);

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
     * 전문가 섭외 요청 등록
     *
     * @param lctrDmndVo
     * @return map
     * @throws Exception
     * @Title : insertLctrDmnd
     * @Description : 전문가 섭외 요청 등록
     */
    @PostMapping("/insertLctrDmnd.do")
    @ResponseBody
    public Map<String,Object> insertLctrDmnd(@Valid LctrDmndVo lctrDmndVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String,Object> response = new HashMap<>();
        response.put("result", Constant.REST_API_RESULT_FAIL);

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        lctrDmndVo.setUser(user);
 /*       int retVal = lctrDmndService.insertLctrDmnd(lctrDmndVo);

        if(retVal > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
        }*/

        return response;
    }


}
