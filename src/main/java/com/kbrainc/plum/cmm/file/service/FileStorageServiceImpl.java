package com.kbrainc.plum.cmm.file.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.exception.CustomRuntimeException;
import com.kbrainc.plum.rte.exception.FileStorageException;
import com.kbrainc.plum.rte.exception.MyFileNotFoundException;
import com.kbrainc.plum.rte.file.FileStorageProperties;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConfigurationProperties(prefix = "file")
public class FileStorageServiceImpl extends PlumAbstractServiceImpl implements FileStorageService,Cloneable{
    private final Path fileStorageLocation;

    private FileStorageProperties fileStorageProperties;
    
    private Map<String, Map<String, Object>> filegrpName;

    public void setFilegrpName(Map<String, Map<String, Object>> filegrpName) {
        this.filegrpName = filegrpName;
    } 
    
    /**
     * .
     * Desc : Constructor of FileStorageServiceImpl.java class
     * @param fileStorageProperties :
     */
    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        
        try {
            this.fileStorageProperties = (FileStorageProperties)fileStorageProperties.clone();
        }catch(CloneNotSupportedException e) {
            log.error("FileStorageServiceImpl.CloneNotSupportedException.64L");   
        }
        
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (NotDirectoryException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",ex);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",ex);
        }
    }

    public FileVo storeFile(MultipartFile file, FileGrpVo fileGrpVo) {
    	
        String fileName = "";
        
        try {
            if (file == null) {
                throw new CustomRuntimeException("file not found Error");
            }
            
            FileVo fileVo =new FileVo();
     
            // Normalize file name
             fileName = StringUtils.cleanPath(file.getOriginalFilename());
    
    
            //IE 브라우저에서 cleanPath 로 파일명을 못가져옴 
            if(fileName.contains("/")) {
              String splitFileName[] =	fileName.split("/");	
              if(splitFileName.length > 0) {
            	  fileName =splitFileName[splitFileName.length-1];
              }
            }
            
            fileVo.setFileSize(file.getSize());
            
            fileVo.setOrginlFileNm(fileName);
        
        
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            UUID uuid = UUID.randomUUID();
            fileName = uuid.toString() + "_" + fileName;
            fileVo.setSaveFileNm(fileName);
            
            // Copy file to the target location (Replacing existing file with the same name)
            if (this.filegrpName.containsKey(fileGrpVo.getFilegrpNm())) {
                String orginlUploadPath = fileStorageProperties.getUploadDir() + (String)this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("uploadPath") + 
                       (("bbs".equals(fileGrpVo.getFilegrpNm()) || ("cmt_bbs".equals(fileGrpVo.getFilegrpNm()))) ? "/" + fileGrpVo.getBbsid() : "");
                Path uploadPath = Paths.get(orginlUploadPath).toAbsolutePath().normalize();
                Files.createDirectories(uploadPath);
                Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                if (this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("isThumbnail") != null) {
                    makeThumbnail(orginlUploadPath, fileName, fileName.substring(fileName.lastIndexOf(".") + 1), (Integer)this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("thumbnailWidth"), (Integer)this.filegrpName.get(fileGrpVo.getFilegrpNm()).get("thumbnailHeight"));
                }
                fileVo.setFilePath(orginlUploadPath);
            } else {
                Files.copy(file.getInputStream(), this.fileStorageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                fileVo.setFilePath(fileStorageProperties.getUploadDir());
            }
            
            return fileVo;
            
        } catch (FileStorageException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(FileVo fileVo) throws Exception {
	try {
	    Path filePath = Paths.get(fileVo.getFilePath()).toAbsolutePath().normalize().resolve(fileVo.getSaveFileNm());
	    /*Path filePath = null;
	    if (this.filegrpName.containsKey(fileVo.getFilegrp_nm()) && this.filegrpName.get(fileVo.getFilegrp_nm()).get("isThumbnail") != null) {
	        filePath = Paths.get(fileVo.getFile_path()).toAbsolutePath().normalize().resolve("THUMB_" + fileVo.getSave_file_nm());
	    } else {
	        filePath = Paths.get(fileVo.getFile_path()).toAbsolutePath().normalize().resolve(fileVo.getSave_file_nm());
	    }*/
	    Resource resource = new UrlResource(filePath.toUri());
	
	    if (resource.exists()) {
		return resource;
	    } else {
		throw new MyFileNotFoundException("File not found " + fileVo.getSaveFileNm());
	    }
	} catch (MalformedURLException ex) {
	    throw new MyFileNotFoundException("File not found " + fileVo.getSaveFileNm(), ex);
	}
    }
    
    public boolean deleteFile(FileVo fileVo) {
        Path filePath = Paths.get(fileVo.getFilePath()).toAbsolutePath().normalize().resolve(fileVo.getSaveFileNm());
        Path thumbnailFilePath = Paths.get(fileVo.getFilePath()).toAbsolutePath().normalize().resolve("THUMB_" + fileVo.getSaveFileNm());
	File delFile = new File(filePath.toString());
	File thumbnailDelFile = new File(thumbnailFilePath.toString()); 
	
	if(!delFile.exists()) {
	    return false;
	}
	if(thumbnailDelFile.exists()) {
	    thumbnailDelFile.delete();
        }
	return delFile.delete();
    }
    
    public String imgToStringByBase64(String filePath) throws Exception {        
        File f = new File(filePath);
        FileInputStream fis = null;
        String changeString = null;
        if (f.exists()) {
            
            fis = new FileInputStream(f);
            try {
                byte[] byteArray = new byte[(int)f.length()];
                fis.read(byteArray);
                //when using package 'import org.docx4j.org.apache.xml.security.utils.Base64;'
                //changeString = "data:image/"+filePath.substring(filePath.lastIndexOf(".")) +";base64, "+ Base64.encode(byteArray);
                //java.util.base64
                changeString = "data:image/" + filePath.substring(filePath.lastIndexOf(".")) + ";base64, " 
                        + new String(Base64.getEncoder().encodeToString(byteArray));
            }catch(IOException e) {
                log.error("imgToStringByBase64.IOException.124L");   
            }finally {
                fis.close();
            }
            
        } else {
            throw new MyFileNotFoundException("file not found");
        }
        return changeString;
    }
    
    private void makeThumbnail(String filePath, String fileName, String fileExt, int width, int height) throws Exception { 
        // 저장된 원본파일로부터 BufferedImage 객체를 생성합니다. 
        BufferedImage srcImg = ImageIO.read(new File(filePath + '/' + fileName)); 
        // 썸네일의 너비와 높이 입니다. 
        int dw = width, dh = height; 
        
        // 원본 이미지의 너비와 높이 입니다. 
        int ow = srcImg.getWidth(); 
        int oh = srcImg.getHeight();
                
        // 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다. 
        int nw = ow; int nh = (ow * dh) / dw; 
        
        // 계산된 높이가 원본보다 높다면 crop이 안되므로 
        // 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다. 
        if (nh > oh) {
            nw = (oh * dw) / dh; nh = oh; 
        } 
        
        BufferedImage destImg = null; 
                
        if (dw == dh) {
            destImg = Scalr.resize(srcImg, dw, (oh * dw) / ow);
            // 변환한 height가 dh 보다 작으면 크롭하지말자
            if (destImg.getHeight() > dh ) {
                destImg = Scalr.crop(destImg, 0, (destImg.getHeight() - dh) / 2, destImg.getWidth(), dh);
            }
        } else {
            // 계산된 크기로 원본이미지를 가운데에서 crop 합니다. 
            destImg = Scalr.crop(srcImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh); 
        
            // crop된 이미지로 썸네일을 생성합니다. 
            destImg = Scalr.resize(destImg, dw, dh);
        }
        
        // 썸네일을 저장합니다. 이미지 이름 앞에 "THUMB_" 를 붙여 표시했습니다. 
        String thumbName = filePath + "/THUMB_" + fileName; 
        File thumbFile = new File(thumbName);
        
        ImageIO.write(destImg, fileExt.toUpperCase(new Locale(fileExt)), thumbFile);
    }
}
