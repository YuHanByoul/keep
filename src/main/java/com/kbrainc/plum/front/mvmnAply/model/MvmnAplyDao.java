/**
 * 
 */
package com.kbrainc.plum.front.mvmnAply.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 푸름이 이동환경교실 교육신청 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.mvmnAply.model
* - MvmnAplyDao.java
* </pre>
*
* @ClassName : MvmnAplyDao
* @Description : 푸름이 이동환경교실 교육신청 Dao 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.mvmnAplyDao")
public interface MvmnAplyDao {

    /**
    * 푸름이 이동환경교실 교육신청 게시글 목록 조회
    *
    * @Title : selectMvmnAplyList
    * @Description : 푸름이 이동환경교실 교육신청 게시글 목록 조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyList(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 푸름이 이동환경교실 교육신청 게시글 상세 조회
    *
    * @Title : selectMvmnAplyInfo
    * @Description : 푸름이 이동환경교실 교육신청 게시글 상세 조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return MvmnAplyVo
    */
    public MvmnAplyVo selectMvmnAplyInfo(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 푸름이 이동환경교실 교육신청 회차 목록 조회
    *
    * @Title : selectMvmnAplyTmeList
    * @Description : 푸름이 이동환경교실 교육신청 회차 목록 조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyTmeList(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 푸름이 이동환경교실 교육신청 회차 목록 조회(지속교육)
    *
    * @Title : selectMvmnTmeList
    * @Description : 푸름이 이동환경교실 교육신청 회차 목록 조회(지속교육)
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnTmeList(MvmnAplyVo mvmnAplyVo) throws Exception;

    /**
     * 푸름이 이동환경교실 동일 운영권역 교육 프로그램 목록 조회
     *
     * @Title : selectMvmnAplyEduSareaList
     * @Description : 푸름이 이동환경교실 동일 운영권역 교육 프로그램 목록 조회
     * @param mvmnAplyVo
     * @return
     * @throws Exception
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectMvmnAplyEduSareaList(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 푸름이 이동환경교실 교육신청 교육사진 파일 목록 조회
    *
    * @Title : selectEduPhotoFileList
    * @Description : 푸름이 이동환경교실 교육신청 교육사진 파일 목록 조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectEduPhotoFileList(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 푸름이 이동환경교실 교육신청 날짜조회
    *
    * @Title : selectMvmnAplyDeList
    * @Description : 푸름이 이동환경교실 교육신청 날짜조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyDeList(MvmnAplyVo mvmnAplyVo) throws Exception;   
    
    /**
    * 푸름이 이동환경교실 교육신청 등록정보 상세조회
    *
    * @Title : selectMvmnAplyRegInfo
    * @Description : 푸름이 이동환경교실 교육신청 등록정보 상세조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return MvmnAplyVo
    */
    public MvmnAplyVo selectMvmnAplyRegInfo(MvmnAplyVo mvmnAplyVo) throws Exception;    

    /**
     * 푸름이 이동환경교실 교육신청 등록
     *
     * @Title : insertMvmnAply
     * @Description : 푸름이 이동환경교실 교육신청 등록
     * @param mvmnAplyVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception;    

    /**
     * 푸름이 이동환경교실 교육신청 회차일정 아이디 등록 
     *
     * @Title : insertMvmnAplySchdl
     * @Description : 푸름이 이동환경교실 교육신청 회차일정 아이디 등록 
     * @param mvmnAplyVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertMvmnAplySchdl(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
     * 푸름이 이동환경교실 교육신청 대상등록
     *
     * @Title : insertTrgtCd
     * @Description : 푸름이 이동환경교실 교육신청 대상등록
     * @param mvmnAplyVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertTrgtCd(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 푸름이 이동환경교실 운영권역 목록 조회
    *
    * @Title : selectEduSareaList
    * @Description : 푸름이 이동환경교실 운영권역 목록 조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectEduSareaList(MvmnAplyVo mvmnAplyVo) throws Exception;    

    /**
     * 푸름이 이동환경교실 신청 지역선택 시군구 목록 조회
     *
     * @Title : selectMvmnAplySignguList
     * @Description : 푸름이 이동환경교실 신청 지역선택 시군구 목록 조회
     * @param mvmnAplyVo
     * @return
     * @throws Exception
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectMvmnAplySignguList(MvmnAplyVo mvmnAplyVo) throws Exception;

    public List<MvmnAplyVo> selectMvmnAplySignguListUsingSchdl(MvmnAplyVo mvmnAplyVo) throws Exception;
}
