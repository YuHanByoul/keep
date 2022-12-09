package com.kbrainc.plum.mng.asgsysSrng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;


/**
* 지정제사업관리 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.service
* - AsgsysSrngServiceImpl.java
* </pre>
*
* @ClassName : AsgsysSrngServiceImpl
* @Description : 지정제사업관리 서비스 구현 클래스.
* @author : kbrain
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class AsgsysSrngServiceImpl extends PlumAbstractServiceImpl implements AsgsysSrngService{


	@Autowired
    private AsgsysSrngDao asgsysSrngDao;

    /**
    * 지정신청 목록 조회.
    *
    * @Title       : selectDsgnAplyList
    * @Description : 지정신청 목록 조회.
    * @param memberVo AsgsysAplyVo객체
    * @return List<AsgsysSrngVo> 심사신청정보 목록
    * @throws Exception 예외
    */
    @Override
	public List<AsgsysSrngVo> selectDsgnAplyList(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectDsgnAplyList(asgsysSrngVo);
	}


	/**
	* 지정신청 상세 조회.
	*
	* @Title : getSelectDsgnAplyInfo
	* @Description : 지정신청 상세 조회.
	* @param asgsysSrngVo
	* @return AsgsysSrngVo AsgsysSrngVo객체
    * @throws Exception 예외
	*/
    @Override
	public AsgsysSrngVo selectDsgnAplyDtlInfo(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectDsgnAplyDtlInfo(asgsysSrngVo);
	}

}
