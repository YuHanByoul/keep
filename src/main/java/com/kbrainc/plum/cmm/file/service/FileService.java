package com.kbrainc.plum.cmm.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.UserVo;

public interface FileService {
    public FileVo uploadFile(MultipartFile file, FileGrpVo fileGrpVo, UserVo user, boolean isMulti);

    public FileVo saveFile(FileVo fileVo, FileGrpVo fileGrpVo) throws Exception;

    public boolean saveFiles(MultipartFile file, FileGrpVo fileGrpVo) throws Exception;
	
	public FileVo selectFile(FileVo fileVo) throws Exception;
	public boolean deleteFileVo(FileVo fileVo) throws Exception;
	
    public List<FileVo> selectOldFileList(int fileid) throws Exception;

    public void deleteOldFiles(int fileid) throws Exception;
}
