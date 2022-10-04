package com.kbrainc.plum.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.example.model.ExampleDao;
import com.kbrainc.plum.example.model.PrgrmVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 예제 서비스 클래스.
*
* <pre>
* com.kbrainc.plum.example.service
* - ExampleServiceImpl.java
* </pre>
*
* @ClassName   : ExampleServiceImpl 
* @Description : 예제 서비스 클래스 
* @author      : KBRAINC
* @date        : 2022. 9. 29.
* @Version     : 
* @Company     : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ExampleServiceImpl extends PlumAbstractServiceImpl implements ExampleService {

    @Autowired
    private ExampleDao exampleDao;

    /**
    * 프로그램 목록을 가지고 온다.
    *
    * @Title       : selectPrgrmList 
    * @Description : 프로그램 목록을 가지고 온다.
    * @param prgrmVo PrgrmVo객체
    * @return List<PrgrmVo> 프로그램 목록
    * @throws Exception 예외
    */
    @Override
    public List<PrgrmVo> selectPrgrmList(PrgrmVo prgrmVo) throws Exception {
    	return exampleDao.selectPrgrmList(prgrmVo);
    }
}
