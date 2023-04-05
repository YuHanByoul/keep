package com.kbrainc.plum.front.dsgnMng.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.dsgnMng.model.DsgnMngDao;
import com.kbrainc.plum.front.dsgnMng.model.DsgnMngVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 사용자.지정관리 serviceimple
*
* <pre>
* com.kbrainc.plum.front.dsgnMng.service
* - DsgnMngServiceImpl.java
* </pre>
*
* @ClassName : DsgnMngServiceImpl
* @Description : 사용자.지정관리 serviceimple
* @author : kbrain
* @date : 2023. 3. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.dsgnMngServiceImpl")
@Alias("front.dsgnMngServiceImpl")
public class DsgnMngServiceImpl extends PlumAbstractServiceImpl implements DsgnMngService {

    @Resource(name = "front.dsgnMngDao")
    DsgnMngDao dsgnMngDao;

    /**
	* 지정내역 목록 조회
	*
	* @Title : selectDsgnHstryList
	* @Description : 지정내역 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> selectDsgnHstryList(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectDsgnHstryList(dsgnMngVo);
	}

	/**
	* 변경내역 목록 조회
	*
	* @Title : selectChgDsctnList
	* @Description : 변경내역 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> selectChgDsctnList(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectChgDsctnList(dsgnMngVo);
	}

	/**
	* 운영결과 목록 조회
	*
	* @Title : selectOperRsltList
	* @Description : 운영결과 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> selectOperRsltList(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectOperRsltList(dsgnMngVo);
	}

	/**
	* 운영결과상세 조회
	*
	* @Title : selectOperRslt
	* @Description : 운영결과상세 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	@Override
	public DsgnMngVo selectOperRslt(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectOperRslt(dsgnMngVo);
	}

	/**
	* 운영결과 등록
	*
	* @Title : insertOperRslt
	* @Description : 운영결과 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertOperRslt(DsgnMngVo dsgnMngVo) throws Exception {
		int ret=0;
		if(dsgnMngVo.getRsltCyclid() != null && dsgnMngVo.getRsltCyclid() !=0 ) {
			ret+=dsgnMngDao.updateOperRslt(dsgnMngVo);
		}else {
			ret+=dsgnMngDao.insertOperRslt(dsgnMngVo);
		}
		return ret;

		//dsgnMngVo.get
	}

	/**
	* 운영결과실적 목록 조회
	*
	* @Title : selectOperRsltPrfmncList
	* @Description : 운영결과실적 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> selectOperRsltPrfmncList(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.selectOperRsltPrfmncList(dsgnMngVo);
	}

	/**
	* 이행확인 목록 조회
	*
	* @Title : selectImplmntIdntyList
	* @Description : 이행확인 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectImplmntIdntyList(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.selectImplmntIdntyList(dsgnMngVo);
	}

	/**
	* 이의신청 목록 조회
	*
	* @Title : selectObjcAplyList
	* @Description : 이의신청 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> selectObjcAplyList(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectObjcAplyList(dsgnMngVo);
	}

	/**
	* 지정프로그램 목록 조회
	*
	* @Title : dsgnPrgrmList
	* @Description : 지정프로그램 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> dsgnPrgrmList(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.dsgnPrgrmList(dsgnMngVo);
	}

	/**
	* 이의신청 등록
	*
	* @Title : insertObjcAplyForm
	* @Description : 이의신청 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertObjcAplyForm(DsgnMngVo dsgnMngVo) throws Exception{

		int ret =0 ;
		if(dsgnMngVo.getAplyid()!=null && dsgnMngVo.getAplyid()!=0) {
			ret+=dsgnMngDao.updateObjcAplyForm(dsgnMngVo);
		}else {
			ret+=dsgnMngDao.insertObjcAplyForm(dsgnMngVo);
		}

		return ret;
	}

	/**
	* 이의신청 삭제
	*
	* @Title : deleteObjcAplyForm
	* @Description : 이의신청 삭제
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int deleteObjcAplyForm(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.deleteObjcAplyForm(dsgnMngVo);
	}

	/**
	* 이의신청 조회
	*
	* @Title : selectObjcAply
	* @Description : 이의신청 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	@Override
	public DsgnMngVo selectObjcAply(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectObjcAply(dsgnMngVo);
	}

	/**
	* 보안요청 조회
	*
	* @Title : selectSplmntDmnd
	* @Description : 보안요청 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	@Override
	public DsgnMngVo selectSplmntDmnd(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectSplmntDmnd(dsgnMngVo);
	}

	/**
	* 보안계획 조회
	*
	* @Title : selectSplmntPlan
	* @Description : 보안계획 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	@Override
	public DsgnMngVo selectSplmntPlan(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectSplmntPlan(dsgnMngVo);
	}

	/**
	* 보안계획 등록
	*
	* @Title : insertImprvPlanForm
	* @Description : 보안계획 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertImprvPlanForm(DsgnMngVo dsgnMngVo) throws Exception{
		int ret=0;
		if(dsgnMngVo.getPlanid() != null && dsgnMngVo.getPlanid() != 0) {
			ret += dsgnMngDao.updateImprvPlanForm(dsgnMngVo);
		}else {
			ret += dsgnMngDao.insertImprvPlanForm(dsgnMngVo);
		}
		return ret;
	}

	/**
	* 변경신청상세 조회
	*
	* @Title : selectChgAply
	* @Description : 변경신청상세 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	@Override
	public DsgnMngVo selectChgAply(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.selectChgAply(dsgnMngVo);
	}

	/**
	* 파일 목록 조회
	*
	* @Title : selectFileList
	* @Description : 파일 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> selectFileList(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.selectFileList(dsgnMngVo);
	}

	/**
	* 변경신청 수정
	*
	* @Title : updateChgAply
	* @Description : 변경신청 수정
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int updateChgAply(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.updateChgAply(dsgnMngVo);
	}

	/**
	 * 변경신청 등록
	 *
	 * @Title : insertChgAply
	 * @Description : 변경신청 등록
	 * @param dsgnMngVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	@Override
	public int insertChgAply(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.insertChgAply(dsgnMngVo);
	}

	/**
	* 변경신청 삭제
	*
	* @Title : deleteChgAply
	* @Description : 변경신청 삭제
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return boolean
	*/
	@Override
	public boolean deleteChgAply(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.deleteChgAply(dsgnMngVo);
	}

	/**
	* 컨설팅관리 목록 조회
	*
	* @Title : selectCnsltngMngList
	* @Description : 컨설팅관리 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	@Override
	public List<DsgnMngVo> selectCnsltngMngList(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectCnsltngMngList(dsgnMngVo);
	}

	/**
	* (컨설팅)신청정보 조회
	*
	* @Title : selectAplyInfo
	* @Description : (컨설팅)신청정보 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	@Override
	public DsgnMngVo selectAplyInfo(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectAplyInfo(dsgnMngVo);
	}

	/**
	* 컨설팅 신청 등록
	*
	* @Title : insertCnsltngAply
	* @Description : 컨설팅 신청 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int insertCnsltngAply(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.insertCnsltngAply(dsgnMngVo);
	}

	/**
	 * 컨설팅 신청 수정
	 *
	 * @Title : updateCnsltngAply
	 * @Description : 컨설팅 신청 수정
	 * @param dsgnMngVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	@Override
	public int updateCnsltngAply(DsgnMngVo dsgnMngVo) throws Exception {
		DsgnMngVo aplyInfo = null;
		aplyInfo = dsgnMngDao.selectAplyInfo(dsgnMngVo);
		aplyInfo.setCnsltngKndCd (dsgnMngVo.getCnsltngKndCd ());
		aplyInfo.setHopeDe1      (dsgnMngVo.getHopeDe1      ());
		aplyInfo.setHopeDe1AmPmCd(dsgnMngVo.getHopeDe1AmPmCd());
		aplyInfo.setHopeDe2      (dsgnMngVo.getHopeDe2      ());
		aplyInfo.setHopeDe2AmPmCd(dsgnMngVo.getHopeDe2AmPmCd());
		aplyInfo.setPrgrm        (dsgnMngVo.getPrgrm        ());
		aplyInfo.setLdr          (dsgnMngVo.getLdr          ());
		aplyInfo.setEtc          (dsgnMngVo.getEtc          ());
		aplyInfo.setFilegrpid    (dsgnMngVo.getFilegrpid    ());
		aplyInfo.setSttsCd       (dsgnMngVo.getSttsCd       ());
		aplyInfo.setUser(dsgnMngVo.getUser());
		return dsgnMngDao.updateCnsltngAply(aplyInfo);
	}

	/**
	* 컨설팅신청 삭제
	*
	* @Title : deleteCnsltngAply
	* @Description : 컨설팅신청 삭제
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int deleteCnsltngAply(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.deleteCnsltngAply(dsgnMngVo);
	}

	/**
	* (컨설팅)설문 응답 목록 조회
	*
	* @Title : selectSrvyAnsList
	* @Description : (컨설팅)설문 응답 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<QitemVo>
	*/
	@Override
	public List<QitemVo> selectSrvyAnsList(DsgnMngVo dsgnMngVo) throws Exception{
		return dsgnMngDao.selectSrvyAnsList(dsgnMngVo);
	}


}
