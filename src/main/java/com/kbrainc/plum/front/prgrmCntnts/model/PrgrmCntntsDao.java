package com.kbrainc.plum.front.prgrmCntnts.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("front.prgrmCntntsDao")
public interface PrgrmCntntsDao {

    List<PrgrmCntntsVo> selectPrgrmCntntsList(PrgrmCntntsVo prgrmCntntsVo);

}
