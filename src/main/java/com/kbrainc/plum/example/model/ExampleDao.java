package com.kbrainc.plum.example.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 예제 DAO 클래스.
*
* <pre>
* com.kbrainc.plum.example.model
* - ExampleDao.java
* </pre>
*
* @ClassName   : ExampleDao 
* @Description : 예제 DAO클래스 
* @author      : KBRAINC
* @date        : 2022. 9. 29.
* @Version     : 
* @Company     : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ExampleDao {
    
    /**
    * 프로그램 목록을 가지고 온다.
    *
    * @Title       : selectPrgrmList 
    * @Description : 프로그램 목록을 가지고 온다
    * @param prgrmVo PrgrmVo객체
    * @return List<PrgrmVo> 프로그램 목록
    * @throws Exception 예외
    */
    public List<PrgrmVo> selectPrgrmList(PrgrmVo prgrmVo) throws Exception;
}
