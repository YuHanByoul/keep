package com.kbrainc.plum.mng.qlityChk.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QlityChkDao {

    List<QlityChklstVo> selectQlityChkList() throws Exception;

    List<QlityChkArtclVo> selectQlityChkArtclList(String cntntsid) throws Exception;

    int insertQlityChkList(QlityChkVo qlityChkVo) throws Exception;

    int insertQlityChkArtclList(QlityChkArtclVo qlityChkArtclVo) throws Exception;

}
