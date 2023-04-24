package com.kbrainc.plum.front.eduInst.model;

import java.util.List;

import javax.validation.Valid;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 사회환경교육기관 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.eduInst.model
* - EduInstDao.java
* </pre>
*
* @ClassName : EduInstDao
* @Description : 사회환경교육기관 Dao 클래스
* @author : kbrain
* @date : 2023. 4. 17.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.eduInstDao")
public interface EduInstDao {

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
	public EduInstVo selectAplyInfoForm(EduInstVo eduInstVo) throws Exception;

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
	public EduInstVo selectEnvEduInst(EduInstVo eduInstVo) throws Exception;

	/**
	* SEE_환경_교육_기관 등록
	*
	* @Title : insertEnvEduInst
	* @Description : SEE_환경_교육_기관 등록
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertEnvEduInst(EduInstVo eduInstVo) throws Exception;

	/**
	* SEE_환경_교육_기관 수정
	*
	* @Title : updateEnvEduInst
	* @Description : SEE_환경_교육_기관 수정
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateEnvEduInst(EduInstVo eduInstVo) throws Exception;

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
	public EduInstVo selectOperPlan(EduInstVo eduInstVo) throws Exception;

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
	public int insertOperPlan(EduInstVo eduInstVo) throws Exception;

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
	public int updateOperPlan(EduInstVo eduInstVo) throws Exception;

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
	public List<SchdlVo> selectSchdlList(EduInstVo eduInstVo) throws Exception;

	/**
	* 추진일정 등록
	*
	* @Title : insertPropSchdl
	* @Description : 추진일정 등록
	* @param vo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertPropSchdl(SchdlVo schdlVo) throws Exception;

	/**
	* 추진일정 목록 삭제
	*
	* @Title : deletePropSchdl
	* @Description : 추진일정 목록 삭제
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int deletePropSchdl(EduInstVo eduInstVo) throws Exception;

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
	public List<EduExprtVo> selectEduExprtList(EduInstVo eduInstVo) throws Exception;

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
	public int insertEduExprt(EduExprtVo eduInstVo) throws Exception;

	/**
	* 교육전문인력 목록 삭제
	*
	* @Title : deleteEduExprtList
	* @Description : 교육전문인력 목록 삭제
	* @param eduInstVo
	* @throws Exception
	* @return void
	*/
	public void deleteEduExprt(EduInstVo eduInstVo) throws Exception;


}
