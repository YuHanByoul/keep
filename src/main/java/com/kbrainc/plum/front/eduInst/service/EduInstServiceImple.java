package com.kbrainc.plum.front.eduInst.service;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.eduInst.model.EduInstDao;
import com.kbrainc.plum.front.eduInst.model.EduInstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 사회환경교육기관 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.eduInst.service
* - EduInstServiceImple.java
* </pre>
*
* @ClassName : EduInstServiceImple
* @Description : 사회환경교육기관 서비스 구현 클래스
* @author : kbrain
* @date : 2023. 4. 17.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.eduInstServiceImpl")
@Alias("front.eduInstServiceImpl")
public class EduInstServiceImple extends PlumAbstractServiceImpl implements EduInstService {

	@Resource(name = "front.eduInstDao")
    private EduInstDao eduInstDao;

	/**
	* 신청정보 조회
	*
	* @Title : selectAplyInfoForm
	* @Description : 신청정보 조회
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return EduInstVo
	*/
	@Override
	public EduInstVo selectAplyInfoForm(EduInstVo eduInstVo) throws Exception {
		return eduInstDao.selectAplyInfoForm(eduInstVo);
	}

	/**
	* 환경교육기관상세 조회
	*
	* @Title : selectEnvEduInst
	* @Description : 환경교육기관상세 조회
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return EduInstVo
	*/
	public EduInstVo selectEnvEduInst(EduInstVo eduInstVo) throws Exception{
		return eduInstDao.selectEnvEduInst(eduInstVo);
	}

	/**
	* 신청정보 등록
	*
	* @Title : insertAplyInfo
	* @Description : 신청정보 등록
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int insertAplyInfo(EduInstVo eduInstVo) throws Exception {
		int ret=0;
		ret+=eduInstDao.insertEnvEduInst(eduInstVo);
		return ret;
	}

	/**
	* 신청정보 수정
	*
	* @Title : updateAplyInfo
	* @Description : 신청정보 수정
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int updateAplyInfo(EduInstVo eduInstVo) throws Exception {
		int ret=0;
		ret+=eduInstDao.updateEnvEduInst(eduInstVo);
		return ret;
	}

}
