package com.kbrainc.plum.mng.clclnMng.clclnDsctn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;
import com.kbrainc.plum.mng.clclnMng.clclnDsctn.model.ClclnDsctnVo;
import com.kbrainc.plum.mng.clclnMng.clclnDsctn.service.ClclnDsctnService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

/**
* 체험환경교육 지원사업 -> 정산관리 -> 정산내역관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.clclnDsctn.controller
* - ClclnDsctnController.java
* </pre>
**
@ClassName : ClclnDsctnController
* @Description : TODO
* @author : 이한명
* @date : 2023. 2. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
@Slf4j
public class ClclnDsctnController {
    
    @Autowired
    private ClclnDsctnService clclnDsctnService;
    
    @Autowired
    private FileService fileService;
    
    @Value("${file.upload-dir}")
    private String uploadZipPath;
    
    /**
    * 정산내역관리 리스트화면으로 이동
    *
    * @Title : clclnDsctnListForm
    * @Description : 정산내역관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/clclnDsctnListForm.html")
    public String clclnDsctnListForm(Model model, HttpServletRequest request) throws Exception {

        return "mng/clclnMng/clclnDsctn/clclnDsctnList";
    }
    
    /**
    * 정산내역관리 목록 조회
    *
    * @Title : selectClclnDsctnList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param clclnDsctnVo 교육신청관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/selectClclnDsctnList.do")
    @ResponseBody
    public Map<String, Object> selectClclnDsctnList(ClclnDsctnVo clclnDsctnVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ClclnDsctnVo> result = null;
        result =  clclnDsctnService.selectClclnDsctnList(clclnDsctnVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 정산내역관리 상세화면으로 이동
    **
    * @Title : clclnDsctnDetailForm
    * @Description : 정산내역관리 상세화면으로 이동
    * @param clclnDsctnVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/clclnDsctnDetailForm.html")
    public String clclnDsctnDetailForm(ClclnDsctnVo clclnDsctnVo, Model model) throws Exception {
        model.addAttribute("clclnDsctnVo", clclnDsctnVo);
        
        return "mng/clclnMng/clclnDsctn/clclnDsctnDetailForm";
    }

    /**
     * 정산내역관리 상세화면으로 이동
     **
     * @Title : clclnDsctnDetailForm
     * @Description : 정산내역관리 상세화면으로 이동
     * @param clclnDsctnVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/clclnDsctnDetailInfoForm.html")
    public String clclnDsctnDetailInfoForm(ClclnDsctnVo clclnDsctnVo, Model model) throws Exception {

        ClclnDsctnVo result = new ClclnDsctnVo();
        ClclnDsctnVo calResult = new ClclnDsctnVo();
        List<FileVo> bnkbFileRsltList = null;
        
        List<ClclnDsctnVo> resultList = null;
        List<ClclnDsctnVo> resultDtlList = null;
        
        result = clclnDsctnService.selectClclnDsctnDetailInfo(clclnDsctnVo);
        resultList = clclnDsctnService.selectClclnDsctnDetailOutlList(clclnDsctnVo);
        resultDtlList = clclnDsctnService.selectClclnDsctnDetailOutlDtlList(clclnDsctnVo);
        
        //calResult = clclnDsctnService.selectClclnDsctnOutlCalculateInfo(clclnDsctnVo);

        
        double bgtAmtSum = 0;
        double expndAmtSum = 0;
        double implCntSum = 0;
        double implBlncAmtSum = 0;
        double implRtSum = 0;
        double dsctnSum = 0;
        
        if(resultList != null && resultList.size() > 0){
            for(int i = 0; i < resultList.size(); i++) {
                bgtAmtSum += resultList.get(i).getBgtAmt();
                expndAmtSum += resultList.get(i).getExpndAmt();
                implCntSum += resultList.get(i).getImplCnt();
                implBlncAmtSum += resultList.get(i).getImplBlncAmt();
            }
            implRtSum = Math.round((expndAmtSum / bgtAmtSum) * 100); 
            
        }
        
        calResult.setBgtAmtSum(bgtAmtSum);
        calResult.setExpndAmtSum(expndAmtSum);
        calResult.setImplCntSum(implCntSum);
        calResult.setImplBlncAmtSum(implBlncAmtSum);
        calResult.setImplRtSum(implRtSum);
        
        if(resultDtlList != null && resultDtlList.size() > 0){
            for(int i = 0; i < resultDtlList.size(); i++) {
                if("210101".equals(resultDtlList.get(i).getSeCd())) {
                    dsctnSum = dsctnSum + resultDtlList.get(i).getAmt();
                }else {
                    dsctnSum = dsctnSum - resultDtlList.get(i).getAmt();
                }
            }
        }
        if(result.getAtchFileIdntfcKey() != null) {
            StringBuffer atchFileBtn = new StringBuffer();
            atchFileBtn.append("<div class ='label label-inverse text-white' id='");
            atchFileBtn.append(result.getAtchFileid());
            atchFileBtn.append("'>");
            
            atchFileBtn.append("<a href=javascript:downloadFileByFileid('");
            atchFileBtn.append(result.getAtchFileid());
            atchFileBtn.append("','");
            atchFileBtn.append(result.getAtchFileIdntfcKey());
            atchFileBtn.append("') class='text-white'>");
            atchFileBtn.append(result.getAtchOrginlFileNm());
            atchFileBtn.append("&nbsp;&nbsp;</a>");
            
            atchFileBtn.append("<a href=javascript:fn_deleteFileList('");
            atchFileBtn.append(result.getAtchFileid());
            atchFileBtn.append("','");
            atchFileBtn.append(result.getAtchFileIdntfcKey());
            atchFileBtn.append("') class='text-white'>X</a></div>");
            
            model.addAttribute("atchFileBtn", atchFileBtn.toString());
        }
        if (!StringUtil.nvl(result.getBnkbFileid()).equals("") && !StringUtil.nvl(result.getBnkbFileid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(result.getBnkbFileid());
            
            bnkbFileRsltList = fileService.getFileList(fileVo);
            
            if(bnkbFileRsltList.get(0).getFileIdntfcKey() != null) {
                StringBuffer bnkbFileBtn = new StringBuffer();
                
                bnkbFileBtn.append("<div class ='label label-inverse text-white' id='");
                bnkbFileBtn.append(bnkbFileRsltList.get(0).getFileid());
                bnkbFileBtn.append("'>");
                
                bnkbFileBtn.append("<a href=javascript:downloadFileByFileid('");
                bnkbFileBtn.append(bnkbFileRsltList.get(0).getFileid());
                bnkbFileBtn.append("','");
                bnkbFileBtn.append(bnkbFileRsltList.get(0).getFileIdntfcKey());
                bnkbFileBtn.append("') class='text-white'>");
                bnkbFileBtn.append(bnkbFileRsltList.get(0).getOrginlFileNm());
                bnkbFileBtn.append("&nbsp;&nbsp;</a>");
                
                bnkbFileBtn.append("<a href=javascript:fn_deleteFileList('");
                bnkbFileBtn.append(bnkbFileRsltList.get(0).getFileid());
                bnkbFileBtn.append("','");
                bnkbFileBtn.append(bnkbFileRsltList.get(0).getFileIdntfcKey());
                bnkbFileBtn.append("') class='text-white'>X</a></div>");
                
                model.addAttribute("bnkbFileBtn", bnkbFileBtn.toString());
            }
            model.addAttribute("bankFile", fileService.getFileList(fileVo).get(0));
        } else {
            model.addAttribute("bankFile", null);
        }        

        model.addAttribute("clclnDsctn", result);
        model.addAttribute("calculateInfo", calResult);
        model.addAttribute("outlList", resultList);
        model.addAttribute("outlDtlList", resultDtlList);
        model.addAttribute("dsctnSum", dsctnSum);
        
        return "mng/clclnMng/clclnDsctn/clclnDsctnDetailInfoForm";
    }
    
    /**
    * 정산내역관리 상세목록 조회
    **
    * @Title : selectClclnDsctnDetailList
    * @Description : 정산내역관리 상세목록 조회
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/selectClclnDsctnDetailList.do")
    @ResponseBody
    public Map<String, Object> selectClclnDsctnDetailList(ClclnDsctnVo clclnDsctnVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ClclnDsctnVo> result = null;
        result =  clclnDsctnService.selectClclnDsctnDetailList(clclnDsctnVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 정산내역관리 상세목록 제출상태 수정
    **
    @Title : updateClclnDsctnComplete
    * @Description : 정산내역관리 상세목록 제출상태 수정
    * @param completeAplyIds
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/updateClclnDsctnComplete.do")
    @ResponseBody
    public Map<String, Object> updateClclnDsctnComplete(@RequestParam("completeAplyIds") String[] completeAplyIds, @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = clclnDsctnService.updateClclnDsctnComplete(completeAplyIds, user);

        if (retVal > 0) {
            reseultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            reseultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            reseultMap.put("result", Constant.REST_API_RESULT_FAIL);
            reseultMap.put("msg", "수정에 실패했습니다.");
        }

        return reseultMap;
    }
    
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/clclnDsctnDetailExcelDownList.do")
    public void clclnDsctnDetailExcelDownList(HttpServletRequest request, HttpServletResponse response, ClclnDsctnVo clclnDsctnVo) throws Exception {
        clclnDsctnService.clclnDsctnDetailExcelDownList(clclnDsctnVo, response, request);
    }
    
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/clclnDsctnOutlListExcelDownList.do")
    public void clclnDsctnOutlListExcelDownList(HttpServletRequest request, HttpServletResponse response, ClclnDsctnVo clclnDsctnVo) throws Exception {
        clclnDsctnService.clclnDsctnOutlListExcelDownList(clclnDsctnVo, response, request);
    }
    
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/clclnDsctnOutlDtlListExcelDownList.do")
    public void clclnDsctnOutlDtlListExcelDownList(HttpServletRequest request, HttpServletResponse response, ClclnDsctnVo clclnDsctnVo) throws Exception {
        clclnDsctnService.clclnDsctnOutlDtlListExcelDownList(clclnDsctnVo, response, request);
    }
    
    /**
    * 보완요청 팝업. 
    *
    * @Title : clclnExcclcSplmntPopup
    * @Description : TODO
    * @param excclcSplmntVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/clclnMng/clclnDsctn/clclnExcclcSplmntPopup.html")
    public String clclnExcclcSplmntPopup(SupplementVo excclcSplmntVo, Model model) throws Exception {
        SupplementVo detail = new SupplementVo();

        if (excclcSplmntVo != null) {
            if (!"201101".equals(excclcSplmntVo.getAnsSttsCd())) {
                List<SupplementVo> result = clclnDsctnService.selectExcclcSplmntList(excclcSplmntVo);                    
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
            } else {
                detail.setSplmntid(excclcSplmntVo.getSplmntid());
                detail.setAplyid(excclcSplmntVo.getAplyid());
                detail.setAnsSttsCd(excclcSplmntVo.getAnsSttsCd());
            }
        }
        model.addAttribute("excclcSplmntVo", detail);
        model.addAttribute("ansSttsCd", excclcSplmntVo.getAnsSttsCd());
        
        return "mng/clclnMng/clclnDsctn/clclnExcclcSplmntPopup";
    }
    
    /**
    * 보완요청 목록 조회. 
    *
    * @Title : selectExcclcSplmntList
    * @Description : TODO
    * @param excclcSplmntVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/clclnMng/clclnDsctn/selectExcclcSplmntList.do")
    @ResponseBody
    public Map<String, Object> selectSExcclcSplmnt(SupplementVo excclcSplmntVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SupplementVo> result = clclnDsctnService.selectExcclcSplmntList(excclcSplmntVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;        
    }  
    /**
    * 보완요청 등록. 
    *
    * @Title : insertExcclcSplmnt
    * @Description : TODO
    * @param excclcSplmntVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/clclnMng/clclnDsctn/insertExcclcSplmnt.do")
    @ResponseBody
    public Map<String, Object> insertExcclcSplmnt(@Valid SupplementVo excclcSplmntVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        excclcSplmntVo.setUser(user);
        
        int retVal = clclnDsctnService.insertExcclcSplmnt(excclcSplmntVo);
        
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
    * 보완요청 수정. 
    *
    * @Title : updateExcclcSplmnt
    * @Description : TODO
    * @param excclcSplmntVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/clclnMng/clclnDsctn/updateExcclcSplmnt.do")
    @ResponseBody
    public Map<String, Object> updateExcclcSplmnt(@Valid SupplementVo excclcSplmntVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        excclcSplmntVo.setUser(user);
        
        int retVal = clclnDsctnService.updateExcclcSplmnt(excclcSplmntVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }  
    
    /**
    * 증빙자료 압축파일 다운로드. 
    *
    * @Title : fileCompressZIP
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/clclnMng/clclnDsctn/fileCompressZIP.do")
    @ResponseBody    
    public void fileCompressZIP(ClclnDsctnVo clclnDsctnVo, HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        List<ClclnDsctnVo> resultDtlList = null;
        resultDtlList = clclnDsctnService.selectClclnDsctnDetailOutlDtlList(clclnDsctnVo);
        
        ZipOutputStream zout = null;
        String zipName = "증빙자료 다운로드.zip";     //ZIP 압축 파일명
        String tempPath = "";
        InputStream in = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ServletOutputStream so = null;
        BufferedOutputStream bos = null;

        if (resultDtlList.size() > 0) {
              try{
                 tempPath = "/temp/";       //ZIP 압축 파일 저장경로
                 //ZIP파일 압축 START
                 zout = new ZipOutputStream(new FileOutputStream(new File(uploadZipPath+"/"+zipName)));
                 byte[] buffer = new byte[1024];
                 
                 for ( int k=0; k<resultDtlList.size(); k++){
                     FileVo fileVo = new FileVo();
                     fileVo.setFileid(resultDtlList.get(k).getAtchFileid());
                     fileVo.setFileIdntfcKey(resultDtlList.get(k).getAtchFileIdntfcKey());
                     String fileName ="";
                     fileVo = fileService.selectFile(fileVo);   
                     fileName = fileVo.getSaveFileNm();
                     String filePath = fileVo.getFilePath();
                     //filePath = filePath.replaceAll("/", "");
                     
                     ClassPathResource resource = new ClassPathResource(filePath + "/" + fileName);
//                   File file = new File(lmsdataPath+filePath+"/"+sFileName);
                     File file = resource.getFile();
                     
                    in = new FileInputStream(file);      //압축 대상 파일
                    zout.putNextEntry(new ZipEntry(fileName));  //압축파일에 저장될 파일명
                    int len;
                    while(true){
                       len = in.read(buffer);
                       if (len <= 0) {
                           break;
                       }
                       zout.write(buffer, 0, len);          //읽은 파일을 ZipOutputStream에 Write
                    }
                    zout.closeEntry();
                    in.close();
                 }
                 zout.close();
                 //ZIP파일 압축 END
                 
                 //파일다운로드 START
                 response.setContentType("application/zip");
                 response.addHeader("Content-Disposition", "attachment;filename=" + zipName);
                 
                 fis = new FileInputStream(tempPath + zipName);
                 bis = new BufferedInputStream(fis);
                 so = response.getOutputStream();
                 bos = new BufferedOutputStream(so);
                 
                 int n = 0;
                 while(true){
                    n = bis.read(buffer);
                    if (n <= 0) {
                        break;
                    }
                    bos.write(buffer, 0, n);
                    bos.flush();
                 }
                                  
                 //파일다운로드 END
              }catch(IOException e){
                 log.error("fileCompressZIP.IOException");
              }finally{
                  if (in != null) {
                      in.close();
                  }
                  if (zout != null){
                    zout.close();
                  }
                  if(bos != null) bos.close();
                  if(bis != null) bis.close();
                  if(so != null) so.close();
                  if(fis != null) fis.close();
              }
        }
    }    
}
