package com.kbrainc.plum.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.rte.exception.ParameterNotFoundException;
import com.kbrainc.plum.rte.exception.RestfulException;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;
import com.kbrainc.plum.sample.model.JqGridRequest;
import com.kbrainc.plum.sample.model.JqGridResponse;
import com.kbrainc.plum.sample.model.PageParameter;
import com.kbrainc.plum.sample.model.TestTableVO;
import com.kbrainc.plum.sample.service.ExService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ExController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExService exService;

    @Autowired
    private ResCodeService resCodeService;

    @Autowired @Qualifier("MailService")
    private MailService mailService;

    @GetMapping("/test/test")
    public String test(Model model) throws Exception {
    	
        return "/test/test";
    }

    @GetMapping("/test/subLayout.html")
    public String subLayout() throws Exception {

        return "/test/test_sub_layout";
    }

    @RequestMapping(value = "/test/test_json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String testJson() throws Exception {
        Map<String, String> testMap = new HashMap<>();

        testMap.put("Response", "OK");
        testMap.put("ResponseCode", "200");
        testMap.put("ResponseMsg", "");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(testMap);

        return jsonRes;
    }

    /**
     * Custom Annotation를 사용한 파라미터 사용 예시. session에 user정보가 있으면 userVO에 넣어 리턴한다.
     * 
     * @param model
     * @param user
     * @return
     * @throws Exception
     */
    @GetMapping("/test/userinfo.html")
    public String userInfo(Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("username", user.getNm());

        return "/test/user_info";
    }

    @GetMapping("/test/jqgrid.html")
    public String jqgrid(Model model) throws Exception {

        return "/test/jqgrid";
    }

    /**
     * 
     */
    @GetMapping("/test/jqdata.do")
    public @ResponseBody String jqgridTestData(JqGridRequest params, Model model) throws Exception {

        List<TestTableVO> list = exService.getJqData(new PageParameter(params));

        // 마지막 페이지 다음 페이지 로딩시 total을 알수 없어 이전 리스트의 토탈을 파라미터 받아 사용한다.
        int total = params.getTotal();
        if (list.size() > 0) {
            total = list.get(0).getTotalCount();
        }
        int records = total;

        JqGridResponse res = new JqGridResponse(params.getCallback(), records, params.getPage(), total, list);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(res);

        return jsonRes;
    }

    @GetMapping("/test/jsgrid.do")
    public String jsgrid(Model model) throws Exception {

        return "/test/jsgrid";
    }

    /**
     * 
     */
    @GetMapping("/test/jsdata.do")
    public @ResponseBody String jsgridTestData(Model model) throws Exception {

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> item = new HashMap<String, String>();

        item.put("selected", "true");
        item.put("seq", "1");
        item.put("create_date", "1111");
        item.put("create_name", "2222");
        item.put("title", "3333");
        item.put("hitnum", "4");

        list.add(item);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(list);

        return jsonRes;
    }

    /**
     * @Title : testCodeList
     * @Description : 코드그룹에 해당하는 코드목록을 반환한다.
     * @throws Exception
     * @return List<CodeInfoVo> 코드목록
     */
    @ResponseBody
    @RequestMapping(value = "/test/test_codeList")
    public List<CodeInfoVo> testCodeList() throws Exception {
        return resCodeService.getCodeList("G0001", "B0001");
        // 타임리프에서 ${@resCodeService.getCodeList('G0001', 'B0001')} 로 사용가능
    }

    /**
     * @Title : testCodeInfo
     * @Description : 코드아이디에 해당하는 코드정보를 반환한다.
     * @throws Exception
     * @return CodeInfoVo 코드정보
     */
    @ResponseBody
    @RequestMapping(value = "/test/test_codeInfo")
    public CodeInfoVo testCodeInfo() throws Exception {
        return resCodeService.getCodeInfo("G0001|" + "A0001");
        // 타임리프에서 ${@resCodeService.getCodeInfo('G0001|' + 'A0001')} 로 사용가능
    }

    /**
     * @Title : testCodeName
     * @Description : 코드아이디에 해당하는 코드이름을 반환한다.
     * @throws Exception
     * @return String 코드이름
     */
    @ResponseBody
    @RequestMapping(value = "/test/test_codeName")
    public String testCodeName() throws Exception {
        return resCodeService.getCodeName("G0001|" + "A0001");
        // 타임리프에서 ${@resCodeService.getCodeName('G0001|' + 'A0001')} 로 사용가능
    }

    @GetMapping("/test/exception.html")
    public String exception(String param1) throws ParameterNotFoundException {

        if (param1 == null || "".equals(param1)) {
            // throw new Exception("errorTest.do에서 에러발생");
            throw new ParameterNotFoundException("A parameter Not found.");
        }
        return "test";
    }

    @RequestMapping(value = "/test/fileupload.html")
    public String fileuploadForm() {

        return "/test/fileupload";
    }

    @GetMapping("/test/ckeditor.html")
    public String ckeditor(Model model) throws Exception {

        model.addAttribute("content", "Hello World!!!");

        return "/test/ckeditor4";
    }

    @GetMapping("/test/errTest")
    @ResponseBody
    public String errorTest() throws RestfulException {

        throw new RestfulException("REST 에러입니다.");
    }

    @GetMapping("/test/errTest2")
    @ResponseBody
    public String errorTest2() throws RestfulException {

        throw new RestfulException();
    }

    @GetMapping("/test/errTest3")
    @ResponseBody
    public void errorTest3() throws RestfulException {

        List<String> list = null;
        list.add("aaaa"); // nullPointerException 발생.
    }
    
    @ResponseBody
    @RequestMapping(value = "/test/mail")    
    public String testSendMail() throws Exception {
        MailVo mailVo = new MailVo();
        mailVo.setRcptnEmail("comnics@gmail.com");
        mailVo.setTitle("메일 발송 테스트");
        mailVo.setCntnts("메일 발송 테스트 샘플입니다.");
        Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
        
        List<MailRcptnVo> mailList  = new ArrayList<MailRcptnVo>();
        mailList.add(new MailRcptnVo("comnics@gmail.com", null));
        mailList.add(new MailRcptnVo("jskcj@emlook.com", null));
        mailVo = new MailVo("rhea.emlook@gmail.com", null, "다중 메일 테스트", "다중메일 테스트 입니다.", 0, "J", 0);
        Map<String, Object> resMap2 = mailService.sendMultiMail(mailList, mailVo);
        
    	
        return "OK";
    }
}
