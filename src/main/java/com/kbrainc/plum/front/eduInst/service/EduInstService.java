package com.kbrainc.plum.front.eduInst.service;

import com.kbrainc.plum.front.eduInst.model.EduInstVo;

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


}
