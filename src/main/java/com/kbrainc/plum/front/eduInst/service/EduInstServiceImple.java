package com.kbrainc.plum.front.eduInst.service;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.eduInst.model.EduExprtVo;
import com.kbrainc.plum.front.eduInst.model.EduInstDao;
import com.kbrainc.plum.front.eduInst.model.EduInstVo;
import com.kbrainc.plum.front.eduInst.model.SchdlVo;
import com.kbrainc.plum.front.eduInst.model.SeePrgrmVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ExpndArtclVo;
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

	/**
	* 운영계획 조회
	*
	* @Title : selectOperPlan
	* @Description : 운영계획 조회
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return EduInstVo
	*/
	@Override
	public EduInstVo selectOperPlan(EduInstVo eduInstVo) throws Exception {
		return eduInstDao.selectOperPlan(eduInstVo);
	}

	/**
	* 운영계획 등록
	*
	* @Title : insertOperPlan
	* @Description : 운영계획 등록
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertOperPlan(EduInstVo eduInstVo) throws Exception{
		int ret=0;
		ret+=eduInstDao.insertOperPlan(eduInstVo);
		return ret;
	}

	/**
	* 운영계획 수정
	*
	* @Title : updateOperPlan
	* @Description : 운영계획 수정
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int updateOperPlan(EduInstVo eduInstVo) throws Exception{
		int ret=0;
		ret+=eduInstDao.updateOperPlan(eduInstVo);
		return ret;
	}

	/**
	* 추진일정 목록 조회
	*
	* @Title : selectSchdlList
	* @Description : 추진일정 목록 조회
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return List<SchdlVo>
	*/
	@Override
	public List<SchdlVo> selectSchdlList(EduInstVo eduInstVo) throws Exception{
		return eduInstDao.selectSchdlList(eduInstVo);
	}

	/**
	* 추진일정 등록
	*
	* @Title : insertPropSchdl
	* @Description : 추진일정 등록
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertPropSchdl(EduInstVo eduInstVo) throws Exception {
		int ret=0;
		List<SchdlVo> schdlList = null;

		eduInstDao.deletePropSchdl(eduInstVo);

		schdlList = eduInstVo.getSchdlList();
		if(schdlList != null && schdlList.size()> 0) {
			for(SchdlVo vo : schdlList) {
				vo.setUser(eduInstVo.getUser());
				vo.setAplyid(eduInstVo.getAplyid());

				ret+=eduInstDao.insertPropSchdl(vo);
			}
		}

		return ret;
	}

	/**
	* 교육전문인력 목록 조회
	*
	* @Title : selectEduExprtList
	* @Description : 교육전문인력 목록 조회
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return List<EduExprtVo>
	*/
	public List<EduExprtVo> selectEduExprtList(EduInstVo eduInstVo) throws Exception{
		return eduInstDao.selectEduExprtList(eduInstVo);
	}

	/**
	* 교육전문인력 등록
	*
	* @Title : insertEduExprt
	* @Description : 교육전문인력 등록
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertEduExprt(EduInstVo eduInstVo) throws Exception{
		int ret=0;

		List<EduExprtVo> exprtList = null;

		eduInstDao.deleteEduExprt(eduInstVo);

		exprtList = eduInstVo.getEduExprtList();
		if(exprtList != null && exprtList.size()> 0) {
			for(EduExprtVo vo : exprtList) {
				vo.setUser(eduInstVo.getUser());
				vo.setAplyid(eduInstVo.getAplyid());

				ret+=eduInstDao.insertEduExprt(vo);
			}
		}

		return ret;
	}

	/**
	* 교육프로그램 목록 조회
	*
	* @Title : selectSeePrgrmList
	* @Description : 교육프로그램 목록 조회
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return List<SeePrgrmVo>
	*/
	@Override
	public List<SeePrgrmVo> selectSeePrgrmList(EduInstVo eduInstVo) throws Exception{
		return eduInstDao.selectSeePrgrmList(eduInstVo);
	}

	/**
	* 지정 프로그램 목록 조회
	*
	* @Title : selectDsgnPrgrmList
	* @Description : 지정 프로그램 목록 조회
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return List<SeePrgrmVo>
	*/
	@Override
	public List<SeePrgrmVo> selectDsgnPrgrmList(EduInstVo eduInstVo) throws Exception{
		return eduInstDao.selectDsgnPrgrmList(eduInstVo);
	}

}
