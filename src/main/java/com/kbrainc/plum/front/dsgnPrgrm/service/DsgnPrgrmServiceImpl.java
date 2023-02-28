package com.kbrainc.plum.front.dsgnPrgrm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmDao;
import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.asgsysSrng.model.PrgrmSchdlVo;
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
	 * @Title : updatePrgrmAssPrgrm
	 * @Description : 지정프로그램 수정
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	@Override
	@Transactional
	public int updatePrgrmAssPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.updatePrgrmAssPrgrm(dsgnPrgrmVo);
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
	public int insertPrgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		int ret = 0;
		//지정프로그램 수정
		ret =+ dsgnPrgrmDao.updatePrgrmAssPrgrm(dsgnPrgrmVo);
		if(dsgnPrgrmVo.getDstnctnid() != null && dsgnPrgrmVo.getDstnctnid() > 0) {
			ret += dsgnPrgrmDao.updatePrgrmDstnctnForm(dsgnPrgrmVo);    //수정
		}else {
			ret += dsgnPrgrmDao.insertPrgrmDstnctnForm(dsgnPrgrmVo);    //등록
		}
		return ret;
	}


}
