package com.kbrainc.plum.mng.dsgnPrgrm.service;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmDao;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmObjcVo;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * 코드 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.dsgnPrgrm.service
 * - DsgnPrgrmServiceImpl.java
 * </pre>
 *
 * @ClassName : DsgnPrgrmServiceImpl
 * @Description : 코드 관리 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class DsgnPrgrmServiceImpl extends PlumAbstractServiceImpl implements DsgnPrgrmService {

    @Autowired
    private DsgnPrgrmDao dsgnPrgrmDao;

    @Autowired
    private AsgsysSrngDao asgSrngDao;

	/**
	* 지정프로그램 목록 조회
	*
	* @Title : selectDsgnPrgrmList
	* @Description : 지정프로그램 목록 조회
	* @param dsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	@Override
	public List<DsgnPrgrmVo> selectDsgnPrgrmList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnPrgrmList(dsgnPrgrmVo);
	}

	/**
	* 지정내역 목록 조회
	*
	* @Title : selectDsgnDsctnList
	* @Description : 지정내역 목록 조회
	* @param dsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	@Override
	public List<DsgnPrgrmVo> selectDsgnDsctnList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnDsctnList(dsgnPrgrmVo);
	}

	/**
	 * 이의신청 목록 조회
	 *
	 * @param dsgnPrgrmVo
	 * @return list
	 * @throws Exception
	 * @Title : selectObjcList
	 * @Description : 이의신청 목록 조회
	 */
	@Override
	public List<DsgnPrgrmObjcVo> selectObjcList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectObjcList(dsgnPrgrmVo);
	}

	/**
	 * 이의신청 정보 조회
	 *
	 * @param dsgnPrgrmObjcVo
	 * @return DsgnPrgrmObjcVo
	 * @throws Exception
	 * @Title : selectObjcInfo
	 * @Description : 이의신청 정보 조회
	 */
	@Override
	public DsgnPrgrmObjcVo selectObjcInfo(DsgnPrgrmObjcVo dsgnPrgrmObjcVo) throws Exception {
		return dsgnPrgrmDao.selectObjcInfo(dsgnPrgrmObjcVo);
	}

	/**
	 * 이의신청 답변 등록
	 *
	 * @param dsgnPrgrmObjcVo
	 * @return int
	 * @throws Exception
	 * @Title : insertObjcAns
	 * @Description : 이의신청 답변 등록
	 */
	@Override
	public int insertObjcAns(DsgnPrgrmObjcVo dsgnPrgrmObjcVo) throws Exception {
		return dsgnPrgrmDao.insertObjcAns(dsgnPrgrmObjcVo);
	}
	/**
	* 지정내역 저장
	*
	* @Title : insertDsgnHstry
	* @Description : 지정내역 저장
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int insertDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {

		int ret=0;

		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();

		// 지정내역 저장 상태코드 :지정탈락 132102
		dsgnPrgrmVo.setSttsCd("132102");

		ret += dsgnPrgrmDao.insertDsgnHstry(dsgnPrgrmVo);

		asgsysSrngVo.setSttsCd("111112");
		// 지정 프로그램 상태 수정 상태코드 :지정탈락 111112
		asgsysSrngVo.setPrgrmid(dsgnPrgrmVo.getPrgrmid());
		ret += asgSrngDao.updatePrgrSttsCd(asgsysSrngVo);

		return ret;
	}

	/**
	* 지정프로그램 상세 조회
	*
	* @Title : selectDsgnAplyDtlInfo
	* @Description : 지정프로그램 상세 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception
	*/
	@Override
	public DsgnPrgrmVo selectDsgnPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnPrgrm(dsgnPrgrmVo);
	}

}
