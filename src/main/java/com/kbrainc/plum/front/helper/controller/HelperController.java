package com.kbrainc.plum.front.helper.controller;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.type.Alias;
import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.kbrainc.plum.cmm.esylgn.model.EsylgnVo;
import com.kbrainc.plum.cmm.esylgn.service.EsylgnService;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnSuccessVo;
import com.kbrainc.plum.cmm.idntyVrfctn.service.IdntyVrfctnService;
import com.kbrainc.plum.front.member.model.MemberAcntPswdFindVo;
import com.kbrainc.plum.front.member.model.MemberAcntPswdVo;
import com.kbrainc.plum.front.member.model.MemberAgreVo;
import com.kbrainc.plum.front.member.model.MemberAuthVo;
import com.kbrainc.plum.front.member.model.MemberInstSearchVo;
import com.kbrainc.plum.front.member.model.MemberInstVo;
import com.kbrainc.plum.front.member.model.MemberParamVo;
import com.kbrainc.plum.front.member.model.MemberTypeVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.member.service.MemberService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 플랫폼 도우미 메뉴 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.front.helper.controller
* - HelperController.java
* </pre> 
*
* @ClassName : HelperController
* @Description : 플랫폼 도우미 메뉴 컨트롤러 클래스
* @author : KBRAINC
* @date : 2023. 2. 27.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Controller
public class HelperController {
     
    /**
    * 이용약관 화면.
    *
    * @Title       : termsOfUseForm 
    * @Description : 이용약관 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/helper/termsOfUse.html")
    public String termsOfUseForm() throws Exception {
        return "front/helper/termsOfUse.html";
    }
    
    /**
    * 저작권정책.
    *
    * @Title       : copyrightPolicyForm 
    * @Description : 저작권정책 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/helper/copyrightPolicy.html")
    public String copyrightPolicyForm() throws Exception {
        return "front/helper/copyrightPolicy.html";
    }
}