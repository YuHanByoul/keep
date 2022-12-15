package com.kbrainc.plum.mng.employ.service;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.employ.model.EmployDao;
import com.kbrainc.plum.mng.employ.model.EmployVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployServiceImpl implements EmployService{

    private final EmployDao employDao;

    @Override
    public List<EmployVo> selectEmployList(EmployVo employVo) throws Exception {
        return employDao.selectEmployList(employVo);
    }

    @Override
    public EmployVo selectEmployInfo(EmployVo employVo) throws Exception {
        return employDao.selectEmployInfo(employVo);
    }

    @Override
    public int insertEmploy(EmployVo employVo) throws Exception {
        return employDao.insertEmploy(employVo);
    }

    @Override
    public int updateEmploy(EmployVo employVo) throws Exception {
        return employDao.updateEmploy(employVo);
    }

    @Override
    public int deleteEmploy(String[] deleteEmployIds, UserVo userVo) throws Exception {
        return employDao.deleteEmploy(deleteEmployIds, userVo);
    }

    @Override
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception {
        return employDao.selectAttachFileList(fileVo);
    }
}
