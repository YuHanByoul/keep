package com.kbrainc.plum.front.exprtPool.register.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.exprtPool.register.model.DefaultMemberInfoVo;
import com.kbrainc.plum.front.exprtPool.register.model.ExprtRegisterVo;
import com.kbrainc.plum.front.exprtPool.register.service.ExprtRegisterService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.apache.ibatis.type.Alias;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 전문가 등재신청 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.controller
 * - ExprtRegisterController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtRegisterController
 * @Description : 전문가 등재신청 클래스
 * @date : 2023. 02. 19.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.exprtRegisterController")
@Controller("front.exprtRegisterController")
@RequestMapping("/front/exprtPool")
public class ExprtRegisterController {
    private final static String VIEW_PATH = "/front/exprtPool";

    @Autowired
    private ExprtRegisterService exprtRegisterService;

    @Value("${crypto.key}")
    private String encryptKey;

    StandardPBEStringEncryptor encryptor;

    @Autowired
    private FileServiceImpl fileService;

    @GetMapping("/registerStep1.html")
    public String registerStep1(@UserInfo UserVo user, Model model) throws Exception {
        model.addAttribute("user", user);
        if (user != null) {
            String exprtStts = exprtRegisterService.selectExprtStts(user);
            model.addAttribute("exprtStts", exprtStts);
        }

        return VIEW_PATH + "/registerStep1";
    }

    @GetMapping("/registerStep2.html")
    public String registerStep2(HttpServletResponse response, ExprtRegisterVo exprtRegisterVo, @UserInfo UserVo user, Model model) throws Exception {
        if (user != null) {
            String exprtStts = exprtRegisterService.selectExprtStts(user);
            if (exprtStts != null) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                if (exprtStts.equals("134102")) {
                    writer.print("<script>alert('전문가 심사가 진행중입니다.');location.href='/front/exprtPool/registerStep1.html';</script>");
                } else if (exprtStts.equals("134101")) {
                    writer.print("<script>alert('작성중인 데이터가 존재합니다.');location.href='/front/exprtPool/registerStep1.html';</script>");
                } else {
                    writer.print("<script>alert('이미 전문가로 등록되어 있습니다.');location.href='/front/exprtPool/registerStep1.html';</script>");
                }
                return null;
            }
        }

        model.addAttribute("exprtRegisterVo", exprtRegisterVo);

        return VIEW_PATH + "/registerStep2";
    }


    @RequestMapping("/registerStep3.html")
    public String registerStep3(HttpServletResponse response, @ModelAttribute ExprtRegisterVo exprtRegisterVo, @UserInfo UserVo user, Model model) throws Exception {
        if (user != null) {
            String exprtStts = exprtRegisterService.selectExprtStts(user);
            if (exprtStts != null) {
                if (exprtStts.equals("134101")) {
                    exprtRegisterVo.setTosAgreYn("Y");
                    exprtRegisterVo.setPrvcClctAgreYn("Y");
                    exprtRegisterVo.setPrvcThptyPvsnAgreYn("Y");
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    if (exprtStts.equals("134102")) {
                        writer.print("<script>alert('전문가 심사가 진행중입니다.');location.href='/front/exprtPool/registerStep1.html';</script>");
                    } else {
                        writer.print("<script>alert('이미 전문가로 등록되어 있습니다.');location.href='/front/exprtPool/registerStep1.html';</script>");
                    }
                    return null;
                }
            } else {
                // 신규 등록
                if (!inValidateRequiredAgre(exprtRegisterVo.getTosAgreYn(), exprtRegisterVo.getPrvcClctAgreYn(), exprtRegisterVo.getPrvcThptyPvsnAgreYn())) {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.print("<script>alert('필수항목을 동의해야 회원가입이 가능합니다.');location.href='/front/exprtPool/registerStep2.html';</script>");
                    return null;
                }
            }
        }

        exprtRegisterVo.setUser(user);

        DefaultMemberInfoVo defaultMemberInfo = exprtRegisterService.selectDefaultMemberInfo(exprtRegisterVo);

        encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String decStr = encryptor.decrypt(defaultMemberInfo.getGndr());
        defaultMemberInfo.setGndr(decStr);

        Map<String, Object> fileConfiguration = fileService.getConfigurationByFilegrpName("expert_file");

        String uploadFileExtsn = ((HashMap<String, String>) fileConfiguration.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnName -> "." + fileExtsnName.getValue())
                .collect(Collectors.joining(", "));

        /*임시 저장한 이력 확인*/
        ExprtRegisterVo tempExprtRegister = exprtRegisterService.selectExprtRegister(exprtRegisterVo);
        if (tempExprtRegister == null) {
            tempExprtRegister = new ExprtRegisterVo();
        }

        model.addAttribute("uploadFileExtsn", uploadFileExtsn);
        model.addAttribute("tempExprtRegister", tempExprtRegister);
        model.addAttribute("defaultMemberInfo", defaultMemberInfo);

        return VIEW_PATH + "/registerStep3";
    }

    @GetMapping("/registerStep4.html")
    public String registerStep4() {
        return VIEW_PATH + "/registerStep4";
    }

    @PostMapping("/insertExprt.do")
    @ResponseBody
    public Map<String, Object> insertExprt(@Valid @RequestBody ExprtRegisterVo exprtRegisterVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        exprtRegisterVo.setUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }

        if (exprtRegisterService.insertExprt(exprtRegisterVo) > 0) {
            if(exprtRegisterVo.getTempSaveYn().equals("Y")){
                result.put("msg", "저장이 완료되었습니다.");
                result.put("redirectUrl", "/front/exprtPool/registerStep1.html");
            } else {
                result.put("msg", "신청이 완료되었습니다.");
                result.put("redirectUrl", "/front/exprtPool/registerStep4.html");
            }
            result.put("success", true);
        } else {
            result.put("msg", "신청이 실패하였습니다.");
        }

        return result;
    }

    private boolean inValidateRequiredAgre(String... agreYn) {
        for (String agre : agreYn) {
            if (agre == null || agre.equals("N")) return false;
        }

        return true;
    }

}
