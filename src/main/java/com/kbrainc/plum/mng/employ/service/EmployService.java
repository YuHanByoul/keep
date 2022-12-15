package com.kbrainc.plum.mng.employ.service;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.employ.model.EmployVo;
import com.kbrainc.plum.rte.model.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployService {

    public List<EmployVo> selectEmployList(EmployVo employVo) throws Exception;

    public EmployVo selectEmployInfo(EmployVo employVo) throws Exception;

    public int insertEmploy(EmployVo employVo) throws Exception;

    public int updateEmploy(EmployVo employVo) throws Exception;

    public int deleteEmploy(String[] deleteEmployIds, UserVo userVo) throws Exception;

    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;
}
