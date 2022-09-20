package com.kbrainc.plum.cmm.file.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.file.service.FileStorageService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.exception.FileStorageException;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

@RestController
@ConfigurationProperties(prefix = "file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Map<String, Map<String, Object>> filegrpName;
    
    @Autowired
    private FileService fileService;
    @Autowired
    FileStorageService fileStorageService;
    
    public void setFilegrpName(Map<String, Map<String, Object>> filegrpName) {
        this.filegrpName = filegrpName;
    }
    
    /**
     * uploadFile.
     *
     * @Title       : uploadFile 
     * @Description : TODO
     * @param file :
     * @param fileGrpVo :
     * @param user :
     * @return FileVO
     */
    @PostMapping("/uploadFile.do")
    public FileVo uploadFile(@RequestParam("file") MultipartFile file, FileGrpVo fileGrpVo, @UserInfo UserVo user, boolean isMulti) throws Exception {

        if (this.filegrpName.containsKey(fileGrpVo.getFilegrp_nm())) {
            Integer uploadFileSize = (Integer) this.filegrpName.get(fileGrpVo.getFilegrp_nm()).get("uploadFileSize");
            if (uploadFileSize != null) {
                // uploadFileSize byte로 변환 
                long fileSizeByte = uploadFileSize * 1024 * 1024; // MB -> Byte로 변환 
                if (fileSizeByte < file.getSize()) {
                    throw new FileStorageException("파일사이즈는 " + uploadFileSize + "MB 이하여야합니다."); 
                }
                
            }
            
            LinkedHashMap uploadFileExtsn = ((LinkedHashMap)this.filegrpName.get(fileGrpVo.getFilegrp_nm()).get("uploadFileExtsn"));
            if (uploadFileExtsn != null) {
                String fileNm = file.getOriginalFilename();
                String fileExt = fileNm.substring(fileNm.lastIndexOf(".") + 1);
                
                if (!uploadFileExtsn.containsValue(fileExt.toLowerCase())) {
                    //throw new FileStorageException("파일확장자는 " + uploadFileExtsn.values() + "만 가능합니다.");
                    throw new FileStorageException("허용되지않는 파일형식입니다.");
                }
            }
            
        }
        
        return fileService.uploadFile(file, fileGrpVo, user, isMulti);
    }

    /**
     * uploadMultipleFiles.
     *
     * @Title       : uploadMultipleFiles 
     * @Description : TODO
     * @param files :
     * @param fileGrpVo :
     * @param user :
     * @return List FileVO 
     */
    @PostMapping("/uploadMultipleFiles.do")
    public List<FileVo> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, FileGrpVo fileGrpVo, @UserInfo UserVo user) throws Exception {
        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return uploadFile(file, fileGrpVo, user, true);
                    } catch (Exception e) {
                        throw new FileStorageException(e.getMessage());
                    }
                })
                .collect(Collectors.toList());
    }
    
    /**
    * downloadFile.
    *
    * @Title       : downloadFile 
    * @Description : 파일아이디 받아서 다운로드 
    * @param fileid :
    * @param request :
    * @return ResponseEntity Resource
     * @throws Exception 
    */
    @GetMapping("/downloadFileByFileid.do")
    public ResponseEntity<Resource> downloadFile(@RequestParam(name="fileid",required=true) int fileid, @RequestParam(name="file_idntfc_key",required=true) String fileIdntfcKey, HttpServletRequest request) throws Exception {
        FileVo fileVo =new FileVo();
    	fileVo.setFileid(fileid);
    	fileVo.setFile_idntfc_key(fileIdntfcKey);
    	String fileName ="";
        String contentType = null;
        try {
    		
    		fileVo=fileService.selectFile(fileVo);
	    	fileName = fileVo.getSave_file_nm();
	    	
    	}catch(Exception e) {
    		logger.info("Could not fileSql Exception ");
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

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileVo.getOrginl_file_nm().getBytes("EUC-KR"),"ISO-8859-1") + "\"")
                .body(resource);
    }
 
     /**
     * deleteFile.do
     *
     * @Title       : deleteFile 
     * @Description : 파일아이디 받아서 파일 삭제(DB, file storage 동시 삭제 ) 
     * @param fileid :
     * @param request :
     * @return Msg : success or fail
     * @throws UnsupportedEncodingException :
     */    
    @GetMapping("/deleteFile.do")
    public @ResponseBody Map<String,Object> deleteFile(@RequestParam(required=true) int fileid, @RequestParam(name="file_idntfc_key",required=true) String fileIdntfcKey, HttpServletRequest request) throws UnsupportedEncodingException {
    	FileVo fileVo =new FileVo();
    	Map<String, Object> resultMap = new HashMap<>();
    	fileVo.setFileid(fileid);
    	fileVo.setFile_idntfc_key(fileIdntfcKey);
    	try {
    		
    		fileVo = fileService.selectFile(fileVo);
	    	String fileName = fileVo.getSave_file_nm();
	    	fileStorageService.deleteFile(fileVo);
	    	fileService.deleteFileVo(fileVo);
	    	resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    	}catch(Exception e) {
    		logger.info("Could not delete file ");
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    	}
    	return resultMap;
    }
    
    
}