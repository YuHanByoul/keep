package com.kbrainc.plum.mng.cntnts.model;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;

/**
* 컨텐츠 관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.model
* - CntntsDao.java
* </pre>
*
* @ClassName : CntntsDao
* @Description : 컨텐츠 관리 Dao 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface CntntsDao {
    
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception;
    
    public int insertCntnts(CntntsVo cntntsVo) throws Exception;
    
    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception; 
    
    public int updateCntnts(CntntsVo cntntsVo) throws Exception;
    
    public int deleteCntnts(String[] cntntsids) throws Exception;
}
