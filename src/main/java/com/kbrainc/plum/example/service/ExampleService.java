package com.kbrainc.plum.example.service;

import java.util.List;

import com.kbrainc.plum.example.model.PrgrmVo;

/**
* 예제 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.example.service
* - ExampleService.java
* </pre>
*
* @ClassName   : ExampleService 
* @Description : 예제 서비스 인터페이스 
* @author      : KBRAINC
* @date        : 2022. 9. 29.
* @Version     : 
* @Company     : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ExampleService {

    /**
    * 프로그램 목록을 가지고 온다.
    *
    * @Title       : selectPrgrmList 
    * @Description : 프로그램 목록을 가지고 온다.
    * @param prgrmVo PrgrmVo객체
    * @return List<PrgrmVo> 프로그램 목록
    * @throws Exception 예외
    */
    public List<PrgrmVo> selectPrgrmList(PrgrmVo prgrmVo) throws Exception;
}
