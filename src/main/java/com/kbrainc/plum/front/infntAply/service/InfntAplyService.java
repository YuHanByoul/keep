/**
 * 
 */
package com.kbrainc.plum.front.infntAply.service;

import java.util.List;

import com.kbrainc.plum.front.infntAply.model.InfntAplyVo;

/**
* 유아환경교육관 교육신청 서비스 클래스
*
* <pre>
* com.kbrainc.plum.front.infntAply.service
* - InfntAplyService.java
* </pre>
*
* @ClassName : InfntAplyService
* @Description : 유아환경교육관 교육신청 서비스 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface InfntAplyService {

    /**
    * 유아환경교육관 교육신청 게시글 목록 조회
    *
    * @Title : selectInfntAplyList
    * @Description : 유아환경교육관 교육신청 게시글 목록 조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyList(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 유아환경교육관 교육신청 게시글 상세 조회
    *
    * @Title : selectInfntAplyInfo
    * @Description : 유아환경교육관 교육신청 게시글 상세 조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return InfntAplyVo
    */
    public InfntAplyVo selectInfntAplyInfo(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 유아환경교육관 교육신청 회차 목록 조회
    *
    * @Title : selectInfntAplyTmeList
    * @Description : 유아환경교육관 교육신청 회차 목록 조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyTmeList(InfntAplyVo infntAplyVo) throws Exception;

    /**
     * 유아환경교육관 동일 교육관 교육 프로그램 목록 조회
     *
     * @Title : selectInfntAplyEduClssRmList
     * @Description : 유아환경교육관 동일 교육관 교육 프로그램 목록 조회
     * @param infntAplyVo
     * @return
     * @throws Exception
     * @return List<InfntAplyVo>
     */
    public List<InfntAplyVo> selectInfntAplyEduClssRmList(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 유아환경교육관 교육신청 교육사진 파일 목록 조회
    *
    * @Title : selectEduPhotoFileList
    * @Description : 유아환경교육관 교육신청 교육사진 파일 목록 조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectEduPhotoFileList(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 유아환경교육관 교육신청 날짜조회
    *
    * @Title : selectInfntAplyDeList
    * @Description : 유아환경교육관 교육신청 날짜조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return List<InfntAplyAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyDeList(InfntAplyVo infntAplyVo) throws Exception;    
    
    /**
    * 유아환경교육관 교육신청 등록정보 상세조회
    *
    * @Title : selectInfntAplyRegInfo
    * @Description : 유아환경교육관 교육신청 등록정보 상세조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return InfntAplyVo
    */
    public InfntAplyVo selectInfntAplyRegInfo(InfntAplyVo infntAplyVo) throws Exception;

    /**
     * 유아환경교육관 교육신청 등록
     *
     * @Title : insertInfntAply
     * @Description : 유아환경교육관 교육신청 등록
     * @param infntAplyVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertInfntAply(InfntAplyVo infntAplyVo) throws Exception;    

    /**
     * 유아환경교육관 신청이력 조회
     *
     * @Title : selectInfntAplyHistList
     * @Description : 유아환경교육관 신청이력 조회
     * @param infntAplyVo
     * @return
     * @throws Exception
     * @return List<InfntAplyVo>
     */
    public List<InfntAplyVo> selectInfntAplyHistList(InfntAplyVo infntAplyVo) throws Exception;    
}
