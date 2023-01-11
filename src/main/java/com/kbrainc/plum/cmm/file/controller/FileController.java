package com.kbrainc.plum.cmm.file.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.encoder.Encode;
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
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.exception.FileStorageException;
import com.kbrainc.plum.rte.exception.FiledownloadCheckerException;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

@RestController
@ConfigurationProperties(prefix = "file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
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

        if (this.filegrpName.containsKey(fileGrpVo.getFilegrpNm())) {
            Integer uploadFileSize = (Integer) this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("uploadFileSize");
            if (uploadFileSize != null) {
                // uploadFileSize byte로 변환 
                long fileSizeByte = uploadFileSize * 1024 * 1024; // MB -> Byte로 변환 
                if (fileSizeByte < file.getSize()) {
                    throw new FileStorageException("파일사이즈는 " + uploadFileSize + "MB 이하여야합니다."); 
                }
                
            }
            
            LinkedHashMap uploadFileExtsn = ((LinkedHashMap)this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("uploadFileExtsn"));
            if (uploadFileExtsn != null) {
                String fileNm = file.getOriginalFilename();
                String fileExt = "";
                if (fileNm != null) {
                    fileExt = fileNm.substring(fileNm.lastIndexOf(".") + 1);
                }
                
                if (!uploadFileExtsn.containsValue(fileExt.toLowerCase(new Locale(fileExt)))) {
                    //throw new FileStorageException("파일확장자는 " + uploadFileExtsn.values() + "만 가능합니다.");
                    throw new FileStorageException("허용되지않는 파일형식입니다.");
                }
            }
            
            LinkedHashMap imageSizeMap = ((LinkedHashMap)this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("imageSize"));
            if (imageSizeMap != null) {
                BufferedImage image = ImageIO.read(file.getInputStream());
                Integer width = image.getWidth();
                Integer height = image.getHeight();
                StringBuffer sb = new StringBuffer().append(width).append("X").append(height);
                if (!imageSizeMap.containsValue(sb.toString())) {
                    throw new FileStorageException("허용되지않는 이미지사이즈입니다.");
                }
            }
            
            return fileService.uploadFile(file, fileGrpVo, user, isMulti);
        }
        return new FileVo();
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
        
        if (this.filegrpName.containsKey(fileGrpVo.getFilegrpNm())) {
            FileVo fileVo = new FileVo();
            List<FileVo> fileList= new ArrayList<FileVo>();
            long fileTotalSize = 0;
            
            if (fileGrpVo.getFilegrpid() != 0) {
                fileVo.setFilegrpid(fileGrpVo.getFilegrpid());
                fileList= fileService.getFileList(fileVo);
                // 파일 총 사이즈 합산
                for (FileVo file : fileList) {
                    fileTotalSize += file.getFileSize();
                }
            }

            for (MultipartFile file : files) {
                fileTotalSize += file.getSize();
            }
            
            Integer uploadFileCnt = (Integer) this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("uploadFileCnt");
            if (uploadFileCnt != null) {
                if (uploadFileCnt < files.length + fileList.size()) {
                    throw new FileStorageException("파일갯수는 " + uploadFileCnt + "개 까지만 가능합니다."); 
                }
            }
            
            Integer uploadFileTotalSize = (Integer) this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("uploadFileTotalSize");
            if (uploadFileTotalSize != null) {
                // uploadFileTotalSize byte로 변환 
                long fileSizeByte = uploadFileTotalSize * 1024 * 1024; // MB -> Byte로 변환 
                if (fileSizeByte < fileTotalSize) {
                    throw new FileStorageException("총파일사이즈는 " + uploadFileTotalSize + "MB 이하여야합니다."); 
                }
                
            }
            
            List<FileVo> uploadFileList = new ArrayList<FileVo>();
            
            for (MultipartFile file : files) {
                try {
                    uploadFileList.add(uploadFile(file, fileGrpVo, user, true));
                } catch (Exception e) {
                    for (FileVo uploadFile : uploadFileList) {
                        fileVo = fileService.selectFile(uploadFile);   
                        fileStorageService.deleteFile(fileVo);
                        fileService.deleteFileVo(fileVo);
                    }
                    String errorMsg = e.getMessage(); // 수정하지마시오.
                    throw new FileStorageException(errorMsg); // 수정하지마시오.
                }
            }
            
            return uploadFileList;
        } else {
            return new ArrayList<FileVo>();
        }
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
    @GetMapping(value = {"/downloadFileByFileid.do", "/downloadLogo.do"})
    public ResponseEntity<Resource> downloadFile(@RequestParam(name="fileid",required=true) int fileid, @RequestParam(name="file_idntfc_key",required=true) String fileIdntfcKey, HttpServletRequest request, @UserInfo UserVo user) throws Exception {        
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
        
        if ("bbs".equals(fileVo.getFilegrpNm()) && fileVo.getBbsid() == 0) { // 게시판전용 파일다운로드
            throw new FiledownloadCheckerException("You do not have access to the file. " + fileVo.getSaveFileNm());
        }
        
        if ("/downloadLogo.do".equals(request.getRequestURI()) && !"site_logo".equals(fileVo.getFilegrpNm())) {
            throw new FiledownloadCheckerException("You do not have access to the file. " + fileVo.getSaveFileNm());
        }
        
        if (!fileService.downloadFileCheck(fileVo, user)) {
            throw new FiledownloadCheckerException("You do not have access to the file. " + fileVo.getSaveFileNm());
        }

        Resource resource = fileStorageService.loadFileAsResource(fileVo);

        if (!"/downloadLogo.do".equals(request.getRequestURI())) {
            // 파일다운로드 횟수 udpate
            fileService.updateFileDwnldCntPlusOne(fileVo);
        }
        
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileVo.getOrginlFileNm().getBytes("EUC-KR"),"ISO-8859-1") + "\"")
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
    public @ResponseBody Map<String,Object> deleteFile(@RequestParam(required=true) String fileid, @RequestParam(name="file_idntfc_key",required=true) String fileIdntfcKey, HttpServletRequest request) throws UnsupportedEncodingException {
    	FileVo fileVo =new FileVo();
    	Map<String, Object> resultMap = new HashMap<>();
    	fileVo.setFileid(Integer.valueOf(fileid));
    	fileVo.setFileIdntfcKey(fileIdntfcKey);
    	try {
    		
    		fileVo = fileService.selectFile(fileVo);
	    	String fileName = fileVo.getSaveFileNm();
	    	fileStorageService.deleteFile(fileVo);
	    	fileService.deleteFileVo(fileVo);
	    	resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    	}catch(SQLException e) {
    		logger.info("Could not SQLException file ");
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
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
                        
            LinkedHashMap uploadFileExtsn = ((LinkedHashMap)this.filegrpName.get("ckEditor").get("uploadFileExtsn"));
            if (uploadFileExtsn != null) {
                String fileNm = upload.getOriginalFilename();
                String fileExt = "";
                if (fileNm != null) {
                    fileExt = fileNm.substring(fileNm.lastIndexOf(".") + 1);
                }
                
                if (!uploadFileExtsn.containsValue(fileExt.toLowerCase(new Locale(fileExt)))) {
                    printWriter.println("{\"uploaded\" : 0, \"error\":{\"message\":\"허용되지않는 파일형식입니다.\"}}");
                    return;
                }
            }
            
            Integer uploadFileSize = (Integer) this.filegrpName.get("ckEditor").get("uploadFileSize");
            long fileSizeByte = uploadFileSize * 1024 * 1024; // MB -> Byte로 변환
            if (fileSizeByte < upload.getSize()) {
                printWriter.println("{\"uploaded\" : 0, \"error\":{\"message\":\"파일사이즈는 "+uploadFileSize+" MB 이하여야 합니다.\"}}");
                return;
            }
            
            //파일을 바이트 배열로 변환
            byte[] bytes=upload.getBytes();
     
            //이미지를 업로드할 디렉토리를 정해준다
            String uploadPath=uploadImagesPath;
     
            String ckImgPath = (String) this.filegrpName.get("ckEditor").get("uploadPath");
            
            
            OutputStream out = new FileOutputStream(new File(uploadPath+ckImgPath+"/"+fileName));
            
            //upload directory write
            out.write(bytes);
            
            String fileUrl= request.getContextPath()+ckImgPath+"/"+fileName;
            	
            //printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction("+callback+",'"+fileUrl+"','이미지가 업로드되었습니다.')"+"</script>");
            //ckEditor 4.8 버전 이상부터 json 형태로 return 해야 오류 발생 없으나 커스텀 alert를 띄울 수 없음   
            printWriter.println("{\"filename\" : \""+Encode.forHtml(fileUrl)+"\", \"uploaded\" : 1, \"url\":\""+Encode.forHtml(fileUrl)+"\"}");
            
            out.flush();
            
        }catch(IOException e) {
            printWriter.println("{\"uploaded\" : 0, \"error\":{\"message\":\"이미지 업로드에 실패 하였습니다.\"}}");
        }catch(Exception e) {
            printWriter.println("{\"uploaded\" : 0, \"error\":{\"message\":\"이미지 업로드에 실패 하였습니다.\"}}");
        }finally {
        	printWriter.flush();
        }
	}
}