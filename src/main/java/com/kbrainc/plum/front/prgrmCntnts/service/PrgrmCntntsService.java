package com.kbrainc.plum.front.prgrmCntnts.service;

import java.util.List;

import com.kbrainc.plum.front.prgrmCntnts.model.PrgrmCntntsVo;

public interface PrgrmCntntsService {

    List<PrgrmCntntsVo> selectPrgrmCntntsList(PrgrmCntntsVo prgrmCntntsVo);

}
