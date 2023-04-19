package com.kbrainc.plum.mng.qlityChk.service;

import java.util.List;

import com.kbrainc.plum.mng.qlityChk.model.QlityChkArtclVo;
import com.kbrainc.plum.mng.qlityChk.model.QlityChkVo;
import com.kbrainc.plum.mng.qlityChk.model.QlityChklstVo;

public interface QlityChkService {

    List<QlityChklstVo> selectQlityChkList() throws Exception;

    List<QlityChkArtclVo> selectQlityChkArtclList(String cntntsid) throws Exception;

    int insertQlityChkList(QlityChkVo qlityChkVo, QlityChkArtclVo qlityChkArtclVo) throws Exception;

}
