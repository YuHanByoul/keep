package com.kbrainc.plum.cmm.file.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileGrpDao;
import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.exception.CustomRuntimeException;
import com.kbrainc.plum.rte.file.FileStorageProperties;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@ConfigurationProperties(prefix = "file")
@Slf4j
public class FileServiceImpl extends PlumAbstractServiceImpl implements FileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    private FileGrpDao fileGrpDao;
    @Autowired
    private FileDao fileDao;
    @Autowired
    FileStorageProperties fileStorageProperties;

    private Map<String, Map<String, Object>> filegrpName;

    public void setFilegrpName(Map<String, Map<String, Object>> filegrpName) {
        this.filegrpName = filegrpName;
    }
    	
    @Override
    public FileVo uploadFile(MultipartFile file, FileGrpVo fileGrpVo, UserVo userVo, boolean isMulti) {
    	
        // 파일저장
        FileVo fileVo = fileStorageService.storeFile(file, fileGrpVo);
        fileGrpVo.setReguserid(Integer.parseInt(userVo.getUserid()));
        fileVo.setUser(userVo);
        //DB처리
        try {
                         /*if(!isMulti && fileGrpVo.getFilegrpid() > 0) { // 단일파일 업로드 수정일때
                             // 기존 파일그룹의 파일정보를 불러와 물리파일 및 DB에서 삭제처리한다.
                             FileVo oldFileInfo = selectFileInfo(fileGrpVo);
                             fileStorageService.deleteFile(oldFileInfo);
                             deleteFileVo(oldFileInfo);
                         }*/
			 fileVo = saveFile(fileVo, fileGrpVo); 
		} catch (Exception e) {
			log.error("uploadFile.Exception.63L");
		}
        //리턴값 생성
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFileByFileid.do")
                .queryParam("fileid", fileVo.getFileid())
                .queryParam("file_idntfc_key", fileVo.getFileIdntfcKey())
                .toUriString();
        
        fileVo.setFilePath(fileDownloadUri);
        
        return fileVo; 
	}
	
	/**
	 * fileGrpId가 0일 경우 생성한다.
	 */
	@Override
	public FileVo saveFile(FileVo fileVo, FileGrpVo fileGrpVo) throws Exception {
		

		if (fileGrpVo.getFilegrpid() <= 0) {

			fileGrpDao.newFileGrp(fileGrpVo);
			
			if(fileGrpVo.getFilegrpid() <= 0) {
				throw new CustomRuntimeException("File Group ID를 생성하지 못했습니다.");
			}
			
			logger.info("New FileGrp ID : {}", fileGrpVo.getFilegrpid());
		}
		
		String fileNm = fileVo.getOrginlFileNm();
		String fileExt = fileNm.substring(fileNm.lastIndexOf("."));
		
		fileVo.setFilegrpid(fileGrpVo.getFilegrpid());
		fileVo.setFileExtsn(fileExt);
		fileVo.setReguserid(fileGrpVo.getReguserid());

		fileVo.setFileIdntfcKey(CommonUtil.getUUIdGnrBean().getNextBigDecimalId().toString());
		
		/*
		 * FileVo FileVo = new FileVo( fileGrpVo.getFilegrpid(),
		 * fileStorageProperties.getUploadDir(), save_file_nm, file_nm, file_ext,
		 * fileSize, 1, fileGrpVo.getReguserid() );
		 */
		fileDao.addFile(fileVo);
		return fileVo;
	}

	@Override
	public boolean saveFiles(MultipartFile file, FileGrpVo fileGrpVo) {
		return false;
	}
	
	public FileVo selectFile(FileVo fileVo) throws Exception{
		return fileDao.selectFile(fileVo);
	}	
	
	public boolean deleteFileVo(FileVo fileVo) throws Exception{
		return fileDao.deleteFile(fileVo);
	}
	
	public FileVo selectFileInfo(FileGrpVo fileGrpVo) throws Exception{
	        return fileDao.selectFileInfo(fileGrpVo);
	}
	
	public List<FileVo> selectOldFileList(int fileid) throws Exception{
	    return fileDao.selectOldFileList(fileid);
	}
	
	@Override
	@Transactional
	public void deleteOldFiles(int fileid) throws Exception {
	    List<FileVo> oldFileList = selectOldFileList(fileid);
	    for(FileVo oldFileInfo : oldFileList) {
	        fileStorageService.deleteFile(oldFileInfo);
	        deleteFileVo(oldFileInfo);
	    }
	}
	
	/**
    * 다운로드 가능 여부를 반환한다.
    *
    * @Title       : downloadFileCheck 
    * @Description : 다운로드 가능 여부를 반환한다.
    * @param fileVo FileVo객체
    * @param user 사용자세션정보
    * @return boolean 다운로드가능여부
    * @throws Exception 예외
    */
    @Override
    public boolean downloadFileCheck(FileVo fileVo, UserVo user) throws Exception {
        if (this.filegrpName.containsKey(fileVo.getFilegrpNm())) {
            LinkedHashMap downloadChecker = ((LinkedHashMap)this.filegrpName.get(fileVo.getFilegrpNm()).get("downloadChecker"));
            
            if ("bbs".equals(fileVo.getFilegrpNm())) {
                if ("N".equals(fileVo.getNloginPermYn()) && user.getUserid() == null) {
                    return false;
                }
            } else if (downloadChecker != null) {
                if (downloadChecker.containsValue("login")) { // 로그인체크
                    if(user.getUserid() == null) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        return false;
    }
}
