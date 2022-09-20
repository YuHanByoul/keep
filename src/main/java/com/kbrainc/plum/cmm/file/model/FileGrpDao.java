package com.kbrainc.plum.cmm.file.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface FileGrpDao {
    public int newFileGrp(FileGrpVo fileGrpVo);

    public FileGrpVo getFileGrpInfo(FileGrpVo fileGrpVo);

    public void deleteFileGrp(FileGrpVo fileGrpVo);

}
