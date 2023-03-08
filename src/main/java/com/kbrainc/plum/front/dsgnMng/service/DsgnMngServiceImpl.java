package com.kbrainc.plum.front.dsgnMng.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.dsgnMng.model.DsgnMngDao;
import com.kbrainc.plum.front.dsgnMng.model.DsgnMngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
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
	public List<DsgnMngVo> selectOperRsltList(DsgnMngVo dsgnMngVo) throws Exception {
		return dsgnMngDao.selectOperRsltList(dsgnMngVo);
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
	@Transactional
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

}
