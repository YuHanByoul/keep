package com.kbrainc.plum.front.eduInst.model;

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

}
