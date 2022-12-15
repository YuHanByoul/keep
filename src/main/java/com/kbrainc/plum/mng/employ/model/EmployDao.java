package com.kbrainc.plum.mng.employ.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.UserVo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface EmployDao {
    public List<EmployVo> selectEmployList(EmployVo employVo) throws Exception;

    public EmployVo selectEmployInfo(EmployVo employVo) throws Exception;

    public int updateEmploy(EmployVo employVo) throws Exception;

    public int insertEmploy(EmployVo employVo) throws Exception;

    public int deleteEmploy(@Param("deleteEmployIds") String[] deleteEmployIds, @Param("userVo") UserVo userVo) throws Exception;

    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;
}
