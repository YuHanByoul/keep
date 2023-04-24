package com.kbrainc.plum.front.eduInst.service;

import java.util.List;

import javax.validation.Valid;

import com.kbrainc.plum.front.eduInst.model.EduExprtVo;
import com.kbrainc.plum.front.eduInst.model.EduInstVo;
import com.kbrainc.plum.front.eduInst.model.SchdlVo;
import com.kbrainc.plum.front.eduInst.model.SeePrgrmVo;

/**
* 사회환경교육기관 Service
*
* <pre>
* com.kbrainc.plum.front.eduInst.service
* - EduInstService.java
* </pre>
*
* @ClassName : EduInstService
* @Description : 사회환경교육기관 Service
* @author : kbrain
* @date : 2023. 4. 17.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EduInstService {

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
	* 신청정보 등록
	*
	* @Title : insertAplyInfo
	* @Description : SEE_환경_교육_기관 등록
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertAplyInfo(EduInstVo eduInstVo) throws Exception;

	/**
	* 신청정보 수정
	*
	* @Title : updateAplyInfo
	* @Description : SEE_환경_교육_기관 수정
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateAplyInfo(EduInstVo eduInstVo) throws Exception;

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
	* @param eduInstVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertPropSchdl(EduInstVo eduInstVo) throws Exception;

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
	public int insertEduExprt(@Valid EduInstVo eduInstVo) throws Exception;

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
	public List<SeePrgrmVo> selectSeePrgrmList(EduInstVo eduInstVo) throws Exception;

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
	public List<SeePrgrmVo> selectDsgnPrgrmList(EduInstVo eduInstVo) throws Exception;

}
