package com.kbrainc.plum.mng.dsgnPrgrm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmDao;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

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

		//지정 이력 insert
		ret += dsgnPrgrmDao.insertDsgnHstry(dsgnPrgrmVo);

		//지정 프로그램 update
		asgsysSrngVo.setPrgrmid(dsgnPrgrmVo.getPrgrmid());
		//지정상태코드 - 지정승인
		if("132101".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111111");  //상태코드 지정승인
		}
		//지정상태코드 - 지정탈락
		else if("132102".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111112");  //상태코드 지정탈락
		}

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

	/**
	* 지정내역 변경
	*
	* @Title : updateDsgnHstry
	* @Description : 지정내역 변경
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		int ret=0;

		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();

		//지정 이력 update
		ret += dsgnPrgrmDao.updateDsgnHstry(dsgnPrgrmVo);


		//지정 프로그램 update
		asgsysSrngVo.setPrgrmid(dsgnPrgrmVo.getPrgrmid());

		//지정상태코드 - 지정승인
		if("132101".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111111");  //상태코드 지정승인
		}
		//지정상태코드 - 지정탈락
		else if("132102".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111112");  //상태코드 지정탈락
		}

		ret += asgSrngDao.updatePrgrSttsCd(asgsysSrngVo);
		return ret;
	}

	/**
	* 변경신청(탭) 목록 조회
	*
	* @Title : selectChgAply
	* @Description : 변경신청(탭) 목록 조회
	* @param DsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	@Override
	public List<DsgnPrgrmVo> selectChgAplyList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectChgAplyList(dsgnPrgrmVo);

	}

	/**
	* 변경신청(탭) 등록
	*
	* @Title : insertChgAply
	* @Description : 변경신청(탭) 등록
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int insertChgAply(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao
				.insertChgAply(dsgnPrgrmVo);
	}

	/**
	* 변경신청(탭) 수정
	*
	* @Title : updateChgAply
	* @Description : 변경신청(탭) 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int updateChgAply(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.updateChgAply(dsgnPrgrmVo);
	}

	/**
	* 변경신청상세 조회
	*
	* @Title : selectChgAplyDtl
	* @Description : 변경신청상세 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return Object
	*/
	@Override
	public DsgnPrgrmVo selectChgAplyDtl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		 return dsgnPrgrmDao.selectChgAplyDtl(dsgnPrgrmVo);
	}

	/**
	* 변경신청상태 수정
	*
	* @Title : updateChgAplyStts
	* @Description : 변경신청상태 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateChgAplyStts(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.updateChgAplyStts(dsgnPrgrmVo);
	}

	/**
	* 운영결과 상세 조회
	*
	* @Title : selectOperRsltDtl
	* @Description : 운영결과 상세 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception;
	*/
	@Override
	public DsgnPrgrmVo selectOperRsltDtl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectOperRsltDtl(dsgnPrgrmVo);
	}

	/**
	 * 운영결과 목록 조회
	 *
	 * @Title : selectOperRsltList
	 * @Description : 운영결과 목록 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public List<DsgnPrgrmVo> selectOperRsltList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectOperRsltList(dsgnPrgrmVo);
	}
}
