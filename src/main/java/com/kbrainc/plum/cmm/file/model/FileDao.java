package com.kbrainc.plum.cmm.file.model;

import java.util.ArrayList;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface FileDao {
    public boolean addFile(FileVo fileVo) throws Exception;

    public ArrayList<FileVo> getFileList(FileVo fileVo) throws Exception;

    public FileVo getFileInfo(FileVo fileVo) throws Exception;

    public boolean deleteFile(FileVo fileVo) throws Exception;

    public boolean updateFile(FileVo fileVo) throws Exception;
    
    public FileVo selectFile(FileVo fileVo) throws Exception;
    
    public FileVo selectFileInfo(FileGrpVo fileGrpVo) throws Exception;
    
    public List<FileVo> selectOldFileList(int fileid) throws Exception;
}
