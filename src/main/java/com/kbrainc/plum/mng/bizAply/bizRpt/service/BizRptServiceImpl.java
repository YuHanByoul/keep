package com.kbrainc.plum.mng.bizAply.bizRpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.bizAply.bizRpt.model.BizRptDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 사업보고관리 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.service
* - bizRptServiceImpl.java
* </pre>
*
* @ClassName : bizRptServiceImpl
* @Description : 사업보고관리 서비스 구현 클래스.
* @author : kbrain
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class BizRptServiceImpl extends PlumAbstractServiceImpl implements BizRptService{

	@Autowired
    private BizRptDao bizRptDao;
}
