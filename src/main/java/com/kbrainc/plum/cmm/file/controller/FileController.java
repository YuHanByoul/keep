package com.kbrainc.plum.cmm.file.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @Value("${file.upload-dir}")
 	private String uploadImagesPath;
    
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
    
    @RequestMapping(value = "/ckE/upload.do", method = RequestMethod.POST)
	public void uploadimg(HttpServletRequest request,HttpServletResponse response, MultipartFile upload) throws Exception {
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
 
        //클라이언트에 이벤트 추가 (자바스크립트 실행)
        PrintWriter printWriter=response.getWriter(); //자바스크립트 쓰기위한 도구
        try {
        	
        	//파일 중복 저장 방지 
            String uuid = UUID.randomUUID().toString();
            
            //파일 이름 가져오기
            String fileName = uuid+upload.getOriginalFilename();
            
            String[] ext = (fileName.trim()).split("\\.");
                        
            String AllowdExt = "jpg,png,tif,jepg,bmp,rle,raw,bpg,svg";
            
            if(!AllowdExt.contains(ext[1].toLowerCase())) {
            	printWriter.println("<script>alert('이미지 파일만 등록 가능합니다.');</script>");
            	return;
            }
            
            //파일을 바이트 배열로 변환
            byte[] bytes=upload.getBytes();
     
            //이미지를 업로드할 디렉토리를 정해준다
            String uploadPath=uploadImagesPath;
     
            OutputStream out = new FileOutputStream(new File(uploadPath+"/ckEimg/"+fileName));
            
            //upload directory write
            out.write(bytes);
            
            String callback ="1";
            
            String fileUrl= request.getContextPath()+"/ckEimg/"+fileName;
            	
            printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction("+callback+",'"+fileUrl+"','이미지가 업로드되었습니다.')"+"</script>");
            //ckEditor 4.8 버전 이상부터 json 형태로 return 해야 오류 발생 없으나 커스텀 alert를 띄울 수 없음   
            //printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            
            out.flush();
            
        }catch(Exception e) {
        	printWriter.println("<script>alert('이미지 업로드에 실패했습니다.');</script>");
        }finally {
        	printWriter.flush();
        }
	}
    
    
}