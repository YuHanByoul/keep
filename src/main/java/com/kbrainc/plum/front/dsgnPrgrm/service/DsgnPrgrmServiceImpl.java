package com.kbrainc.plum.front.dsgnPrgrm.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmDao;
import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 *
 * 지정프로그램 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.dsgnPrgrm.service - DsgnPrgrmServiceImpl.java
 * </pre>
 *
 * @ClassName : DsgnPrgrmServiceImpl
 * @Description : 지정프로그램 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("front.dsgnPrgrmServiceImpl")
@Alias("front.dsgnPrgrmServiceImpl")
public class DsgnPrgrmServiceImpl extends PlumAbstractServiceImpl implements DsgnPrgrmService {

	@Resource(name = "front.dsgnPrgrmDao")
	private DsgnPrgrmDao dsgnPrgrmDao;

	@Resource(name = "asgsysSrngDao")
	private AsgsysSrngDao asgsysSrngDao;

	/**
	 * 지정현황 목록 조회
	 *
	 * @Title : selectDsgnSttusList
	 * @Description : 지정현황 목록 조회
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return List<DsgnPrgrmVo>
	 */
	@Override
	public List<DsgnPrgrmVo> selectDsgnSttusList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnSttusList(dsgnPrgrmVo);
	}

	/**
	* 지정현황 상세 화면이동
	*
	* @Title : selectDsgnSttus
	* @Description : 지정현황 상세 화면이동
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	@Override
	public DsgnPrgrmVo selectDsgnSttus(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnSttus(dsgnPrgrmVo);
	}

	/**
	* 교육사진파일목록 조회
	*
	* @Title : selectEduPhotoFileList
	* @Description : 교육사진파일목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectEduPhotoFileList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectEduPhotoFileList(dsgnPrgrmVo);
	}

	/**
	* 기관지정프로그램 목록 조회
	*
	* @Title : selectEduPhotoFileList
	* @Description : 기관지정프로그램 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	@Override
	public List<DsgnPrgrmVo> selectInstPrgrmList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectInstPrgrmList(dsgnPrgrmVo);
	}

	/**
	* 프로그램 운영일정 목록조회
	*
	* @Title : selectPrgrmSchdlList
	* @Description : 프로그램 운영일정 목록조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<PrgrmSchdlVo>
	*/
	@Override
	public List<DsgnPrgrmVo> selectPrgrmSchdlList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectPrgrmSchdlList(dsgnPrgrmVo);
	}

	/**
	* 대처계획 목록 조회
	*
	* @Title : selectPlanList
	* @Description : 대처계획 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	@Override
	public List<DsgnPrgrmVo> selectPlanList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectPlanList(dsgnPrgrmVo);
	}

	/**
	* 기관정보 조회
	*
	* @Title : selectInstInfo
	* @Description : 기관정보 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	@Override
	public DsgnPrgrmVo selectInstInfo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectInstInfo(dsgnPrgrmVo);
	}

	/**
	* 신청정보 조회
	*
	* @Title : selectAplyInfo
	* @Description : 신청정보 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	@Override
	public DsgnPrgrmVo selectAplyInfo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {

		if(dsgnPrgrmVo.getPrgrmid() == null || dsgnPrgrmVo.getPrgrmid() == 0 ) {
			Integer id= dsgnPrgrmDao.getPrgrmid(dsgnPrgrmVo);
			dsgnPrgrmVo.setPrgrmid(id);
		}
		return dsgnPrgrmDao.selectAplyInfo(dsgnPrgrmVo);
	}



	/**
	* 지정프로그램 등록
	*
	* @Title : insertPrgrmAssPrgrm
	* @Description : 지정프로그램 등록
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertPrgrmAssPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.insertPrgrmAssPrgrm(dsgnPrgrmVo);
	}

	/**
	 * 지정프로그램 수정
	 *
	 * @Title : updateAssPrgrm
	 * @Description : 지정프로그램 수정
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	@Override
	@Transactional
	public int updateAssPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.updateAssPrgrm(dsgnPrgrmVo);
	}

	/**
	* 프로그램우수성 등록
	*
	* @Title : insertPrgrmDstnctnForm
	* @Description : 프로그램우수성 등록
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertPrgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{

		int ret = 0;
		//지정프로그램
		ret += dsgnPrgrmDao.updateAssPrgrm(dsgnPrgrmVo);
		if(dsgnPrgrmVo.getDstnctnid() != null && dsgnPrgrmVo.getDstnctnid() > 0) {
			ret += dsgnPrgrmDao.updatePrgrmDstnctnForm(dsgnPrgrmVo);    //수정
		}else {
			ret += dsgnPrgrmDao.insertPrgrmDstnctnForm(dsgnPrgrmVo);    //등록
		}

		return ret;
	}

	/**
	* 프로그램 평가 조회
	*
	* @Title : selectPrgrmEvlForm
	* @Description : 프로그램 평가 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	@Override
	public DsgnPrgrmVo selectPrgrmEvlForm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectPrgrmEvlForm(dsgnPrgrmVo);
	}

	/**
	* 컨설팅 목록 조회
	*
	* @Title : selectCsltngList
	* @Description : 컨설팅 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectCsltngList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectCsltngList(dsgnPrgrmVo);
	}

	/**
	* 컨설팅 등록
	*
	* @Title : insertCsltng
	* @Description : 컨설팅 등록
	* @param cnsltngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertCsltng(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		int ret=0;
		int cnsltngid;
		if(dsgnPrgrmVo.getCnsltngid() > 0) {
			ret+=dsgnPrgrmDao.updateCsltng(dsgnPrgrmVo);
		}else {
			ret+=dsgnPrgrmDao.insertCsltng(dsgnPrgrmVo);

			cnsltngid = dsgnPrgrmDao.selectCnsltngid(dsgnPrgrmVo);
			dsgnPrgrmVo.setCnsltngid(cnsltngid);
			ret+=dsgnPrgrmDao.updateCnsltngid(dsgnPrgrmVo);
		}

		return ret;
	}

	/**
	* 신청내역 조회
	*
	* @Title : selectAplyDsctnList
	* @Description : 신청내역 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	@Override
	public List<DsgnPrgrmVo> selectAplyDsctnList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectAplyDsctnList(dsgnPrgrmVo);
	}

	/**
	* 체크리스트 정보 조회
	*
	* @Title : selectChkListInfo
	* @Description : 체크리스트 정보 조회
	* @param dsgnPrgrmVo
	* @throws Exception
	* @return dsgnPrgrmVo
	*/
	@Override
	public DsgnPrgrmVo selectChkListInfo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectChkListInfo(dsgnPrgrmVo);
	}

	/**
	* 문항 목록 조회
	*
	* @Title : selectQitemList
	* @Description : 문항 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	@Override
	public List<DsgnPrgrmVo> selectQitemList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectQitemList(dsgnPrgrmVo);
	}

	/**
	* 체크리스트 답변 목록 조회
	*
	* @Title : selectChkAnsList
	* @Description : 체크리스트 답변 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	@Override
	public List<DsgnPrgrmVo> selectChkAnsList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectChkAnsList(dsgnPrgrmVo);
	}

	/**
	* 보안요청 조회
	*
	* @Title : selectSplmntDmnd
	* @Description : 보안요청 조회
	* @param dsgnPrgrmVo
	* @throws Exception
	* @return void
	*/
	@Override
	public DsgnPrgrmVo selectSplmntDmnd(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectSplmntDmnd(dsgnPrgrmVo);
	}

}
