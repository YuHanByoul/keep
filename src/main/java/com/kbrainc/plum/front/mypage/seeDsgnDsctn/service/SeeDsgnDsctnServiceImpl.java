package com.kbrainc.plum.front.mypage.seeDsgnDsctn.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.mypage.seeDsgnDsctn.model.SeeDsgnDsctnDao;
import com.kbrainc.plum.front.mypage.seeDsgnDsctn.model.SeeDsgnDsctnVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 사회환경교육기관 지정 관리 > 지정관리 serviceimpl
*
* <pre>
* com.kbrainc.plum.front.seeDsgnDsctn.service
* - SeeDsgnDsctnServiceImpl.java
* </pre>
*
* @ClassName : SeeDsgnDsctnServiceImpl
* @Description : 사회환경교육기관 지정 관리 > 지정관리 serviceimple
* @author : LHM
* @date : 2023. 4. 25.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.seeDsgnDsctnServiceImpl")
@Alias("front.seeDsgnDsctnServiceImpl")
public class SeeDsgnDsctnServiceImpl extends PlumAbstractServiceImpl implements SeeDsgnDsctnService {

    @Resource(name = "front.seeDsgnDsctnDao")
    SeeDsgnDsctnDao seeDsgnDsctnDao;

    /**
	* 지정내역 목록 조회
	*
	* @Title : selectSeeDsgnDsctnList
	* @Description : 지정내역 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> selectSeeDsgnDsctnList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
	    return seeDsgnDsctnDao.selectSeeDsgnDsctnList(seeDsgnDsctnVo);
	}

	/**
	* 변경내역 목록 조회
	*
	* @Title : selectChgSeeDsgnDsctnList
	* @Description : 변경내역 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> selectChgSeeDsgnDsctnList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectChgSeeDsgnDsctnList(seeDsgnDsctnVo);
	}

	/**
	* 운영결과 목록 조회
	*
	* @Title : selectOperRsltList
	* @Description : 운영결과 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> selectOperRsltList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectOperRsltList(seeDsgnDsctnVo);
	}

	/**
	* 운영결과상세 조회
	*
	* @Title : selectOperRslt
	* @Description : 운영결과상세 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	@Override
	public SeeDsgnDsctnVo selectOperRslt(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectOperRslt(seeDsgnDsctnVo);
	}

	/**
	* 운영결과 등록
	*
	* @Title : insertOperRslt
	* @Description : 운영결과 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertOperRslt(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		int ret=0;
		if(seeDsgnDsctnVo.getRsltCyclid() != null && seeDsgnDsctnVo.getRsltCyclid() !=0 ) {
			ret+=seeDsgnDsctnDao.updateOperRslt(seeDsgnDsctnVo);
		}else {
			ret+=seeDsgnDsctnDao.insertOperRslt(seeDsgnDsctnVo);
		}
		return ret;

		//seeDsgnDsctnVo.get
	}

	/**
	* 운영결과실적 목록 조회
	*
	* @Title : selectOperRsltPrfmncList
	* @Description : 운영결과실적 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> selectOperRsltPrfmncList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.selectOperRsltPrfmncList(seeDsgnDsctnVo);
	}

	/**
	* 이행확인 목록 조회
	*
	* @Title : selectImplmntIdntyList
	* @Description : 이행확인 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectImplmntIdntyList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.selectImplmntIdntyList(seeDsgnDsctnVo);
	}

	/**
	* 이의신청 목록 조회
	*
	* @Title : selectObjcAplyList
	* @Description : 이의신청 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> selectObjcAplyList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectObjcAplyList(seeDsgnDsctnVo);
	}

	/**
	* 지정신청 목록 조회
	*
	* @Title : dsgnAplyList
	* @Description : 지정신청 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> dsgnAplyList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.dsgnAplyList(seeDsgnDsctnVo);
	}

	/**
	* 이의신청 등록
	*
	* @Title : insertObjcAplyForm
	* @Description : 이의신청 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertObjcAplyForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{

		int ret =0 ;
		if(seeDsgnDsctnVo.getAplyid()!=null && seeDsgnDsctnVo.getAplyid()!=0) {
			ret+=seeDsgnDsctnDao.updateObjcAplyForm(seeDsgnDsctnVo);
		}else {
			ret+=seeDsgnDsctnDao.insertObjcAplyForm(seeDsgnDsctnVo);
		}

		return ret;
	}

	/**
	* 이의신청 삭제
	*
	* @Title : deleteObjcAplyForm
	* @Description : 이의신청 삭제
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int deleteObjcAplyForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.deleteObjcAplyForm(seeDsgnDsctnVo);
	}

	/**
	* 이의신청 조회
	*
	* @Title : selectObjcAply
	* @Description : 이의신청 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	@Override
	public SeeDsgnDsctnVo selectObjcAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectObjcAply(seeDsgnDsctnVo);
	}

	/**
	* 보안요청 조회
	*
	* @Title : selectSplmntDmnd
	* @Description : 보안요청 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	@Override
	public SeeDsgnDsctnVo selectSplmntDmnd(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectSplmntDmnd(seeDsgnDsctnVo);
	}

	/**
	* 보안계획 조회
	*
	* @Title : selectSplmntPlan
	* @Description : 보안계획 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	@Override
	public SeeDsgnDsctnVo selectSplmntPlan(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectSplmntPlan(seeDsgnDsctnVo);
	}

	/**
	* 보안계획 등록
	*
	* @Title : insertImprvPlanForm
	* @Description : 보안계획 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertImprvPlanForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		int ret=0;
		if(seeDsgnDsctnVo.getPlanid() != null && seeDsgnDsctnVo.getPlanid() != 0) {
			ret += seeDsgnDsctnDao.updateImprvPlanForm(seeDsgnDsctnVo);
		}else {
			ret += seeDsgnDsctnDao.insertImprvPlanForm(seeDsgnDsctnVo);
		}
		return ret;
	}

	/**
	* 변경신청상세 조회
	*
	* @Title : selectChgSeeDsgnAply
	* @Description : 변경신청상세 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	@Override
	public SeeDsgnDsctnVo selectChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.selectChgSeeDsgnAply(seeDsgnDsctnVo);
	}

	/**
	* 파일 목록 조회
	*
	* @Title : selectFileList
	* @Description : 파일 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> selectFileList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.selectFileList(seeDsgnDsctnVo);
	}

	/**
	* 변경신청 수정
	*
	* @Title : updateChgSeeDsgnAply
	* @Description : 변경신청 수정
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int updateChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.updateChgSeeDsgnAply(seeDsgnDsctnVo);
	}

	/**
	 * 변경신청 등록
	 *
	 * @Title : insertChgSeeDsgnAply
	 * @Description : 변경신청 등록
	 * @param seeDsgnDsctnVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	@Override
	public int insertChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.insertChgSeeDsgnAply(seeDsgnDsctnVo);
	}

	/**
	* 변경신청 삭제
	*
	* @Title : deleteChgSeeDsgnAply
	* @Description : 변경신청 삭제
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return boolean
	*/
	@Override
	public boolean deleteChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.deleteChgSeeDsgnAply(seeDsgnDsctnVo);
	}

	/**
	* 컨설팅관리 목록 조회
	*
	* @Title : selectCnsltngMngList
	* @Description : 컨설팅관리 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	@Override
	public List<SeeDsgnDsctnVo> selectCnsltngMngList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectCnsltngMngList(seeDsgnDsctnVo);
	}

	/**
	* (컨설팅)신청정보 조회
	*
	* @Title : selectAplyInfo
	* @Description : (컨설팅)신청정보 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	@Override
	public SeeDsgnDsctnVo selectAplyInfo(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.selectAplyInfo(seeDsgnDsctnVo);
	}

	/**
	* 컨설팅 신청 등록
	*
	* @Title : insertCnsltngAply
	* @Description : 컨설팅 신청 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int insertCnsltngAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.insertCnsltngAply(seeDsgnDsctnVo);
	}

	/**
	 * 컨설팅 신청 수정
	 *
	 * @Title : updateCnsltngAply
	 * @Description : 컨설팅 신청 수정
	 * @param seeDsgnDsctnVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	@Override
	public int updateCnsltngAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		SeeDsgnDsctnVo aplyInfo = null;
		aplyInfo = seeDsgnDsctnDao.selectAplyInfo(seeDsgnDsctnVo);
		aplyInfo.setCnsltngKndCd (seeDsgnDsctnVo.getCnsltngKndCd ());
		aplyInfo.setHopeDe1      (seeDsgnDsctnVo.getHopeDe1      ());
		aplyInfo.setHopeDe1AmPmCd(seeDsgnDsctnVo.getHopeDe1AmPmCd());
		aplyInfo.setHopeDe2      (seeDsgnDsctnVo.getHopeDe2      ());
		aplyInfo.setHopeDe2AmPmCd(seeDsgnDsctnVo.getHopeDe2AmPmCd());
		aplyInfo.setPrgrm        (seeDsgnDsctnVo.getPrgrm        ());
		aplyInfo.setLdr          (seeDsgnDsctnVo.getLdr          ());
		aplyInfo.setEtc          (seeDsgnDsctnVo.getEtc          ());
		aplyInfo.setFilegrpid    (seeDsgnDsctnVo.getFilegrpid    ());
		aplyInfo.setSttsCd       (seeDsgnDsctnVo.getSttsCd       ());
		aplyInfo.setUser(seeDsgnDsctnVo.getUser());
		return seeDsgnDsctnDao.updateCnsltngAply(aplyInfo);
	}

	/**
	* 컨설팅신청 삭제
	*
	* @Title : deleteCnsltngAply
	* @Description : 컨설팅신청 삭제
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int deleteCnsltngAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception {
		return seeDsgnDsctnDao.deleteCnsltngAply(seeDsgnDsctnVo);
	}

	/**
	* (컨설팅)설문 응답 목록 조회
	*
	* @Title : selectSrvyAnsList
	* @Description : (컨설팅)설문 응답 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<QitemVo>
	*/
	@Override
	public List<QitemVo> selectSrvyAnsList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception{
		return seeDsgnDsctnDao.selectSrvyAnsList(seeDsgnDsctnVo);
	}


}
