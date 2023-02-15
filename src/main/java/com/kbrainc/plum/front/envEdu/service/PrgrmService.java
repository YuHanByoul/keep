/**
 * 
 */
package com.kbrainc.plum.front.envEdu.service;

import java.util.List;

import com.kbrainc.plum.front.envEdu.model.PrgrmVo;

/**
* 프로그램 서비스 클래스
*
* <pre>
* com.kbrainc.plum.front.envEdu.service
* - PrgrmService.java
* </pre>
*
* @ClassName : PrgrmService
* @Description : 프로그램 서비스 클래스
* @author : JD
* @date : 2023. 2. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface PrgrmService {

    /**
    * 프로그램 게시글 목록 조회
    *
    * @Title : selectPrgrmList
    * @Description : 프로그램 게시글 목록 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return List<PrgrmVo>
    */
    public List<PrgrmVo> selectPrgrmList(PrgrmVo prgrmVo) throws Exception;
    
    /**
    * 프로그램 게시글 상세 조회
    *
    * @Title : selectPrgrmInfo
    * @Description : 프로그램 게시글 상세 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return PrgrmVo
    */
    public PrgrmVo selectPrgrmInfo(PrgrmVo prgrmVo) throws Exception;
    
    /**
    * 프로그램 해당기업 프로그램 목록 조회
    *
    * @Title : selectInstPrgrmList
    * @Description : 프로그램 해당기업 프로그램 목록 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return List<PrgrmVo>
    */
    public List<PrgrmVo> selectInstPrgrmList(PrgrmVo prgrmVo) throws Exception;
    
    /**
    * 프로그램 교육사진 파일 목록 조회
    *
    * @Title : selectEduPhotoFileList
    * @Description : 프로그램 교육사진 파일 목록 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return List<PrgrmVo>
    */
    public List<PrgrmVo> selectEduPhotoFileList(PrgrmVo prgrmVo) throws Exception;
}
