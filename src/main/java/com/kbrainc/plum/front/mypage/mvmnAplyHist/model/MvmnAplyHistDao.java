/**
 * 
 */
package com.kbrainc.plum.front.mypage.mvmnAplyHist.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.srvy.model.SrvyVo;

/**
* 푸름이 이동환경교실 교육신청 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.mvmnAplyHist.model
* - MvmnAplyHistDao.java
* </pre>
*
* @ClassName : MvmnAplyHistDao
* @Description : 푸름이 이동환경교실 교육신청 Dao 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.mvmnAplyHistDao")
public interface MvmnAplyHistDao {
    
    /**
     * 푸름이 이동환경교실 신청이력 조회
     *
     * @Title : selectMvmnAplyHistList
     * @Description : 푸름이 이동환경교실 신청이력 조회
     * @param mvmnAplyHistVo
     * @return
     * @throws Exception
     * @return List<MvmnAplyHistVo>
     */
    public List<MvmnAplyHistVo> selectMvmnAplyHistList(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;        

    /**
     * 푸름이 이동환경교실 신청이력 정보 조회
     *
     * @Title : selectMvmnAplyHistInfo
     * @Description : 푸름이 이동환경교실 신청이력 정보 조회
     * @param mvmnAplyHistVo
     * @return
     * @throws Exception
     * @return MvmnAplyHistVo
     */
    public MvmnAplyHistVo selectMvmnAplyHistInfo(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;        

    /**
     * 푸름이 이동환경교실 신청이력 상세 조회
     *
     * @Title : selectMvmnAplyHistDetail
     * @Description : 푸름이 이동환경교실 신청이력 상세 조회
     * @param mvmnAplyHistVo
     * @return
     * @throws Exception
     * @return MvmnAplyHistVo
     */
    public MvmnAplyHistVo selectMvmnAplyHistDetail(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;
    
    /**
    * 푸름이 이동환경교실 상태수정
    **
    * @Title : updateSttsMvmnAply
    * @Description : 푸름이 이동환경교실 상태수정
    * @param mvmnAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateSttsMvmnAply(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;
    
    /**
     * 푸름이 이동환경교실 설문 정보 조회
     **
     * @Title : selectMvmnSrvyInfo
     * @Description : 푸름이 이동환경교실 설문 정보 조회
     * @param mvmnAplyHistVo
     * @return
     * @throws Exception
     * @return SrvyVo
     */
    public SrvyVo selectMvmnSrvyInfo(SrvyVo srvyVo) throws Exception;
    
    /**
     * 푸름이 이동환경교실 설문 공유 리스트 조회
     *
     * @Title : selectMvmnSrvySendList
     * @Description : 푸름이 이동환경교실 설문 공유 리스트 조회
     * @param srvyVo
     * @return
     * @throws Exception
     * @return List<SrvyVo>
     */
    public List<SrvyVo> selectMvmnSrvySendList(SrvyVo srvyVo) throws Exception;   
    
    /**
    * 푸름이 이동환경교실 참여이력 수정
    **
    * @Title : updateMvmnAply
    * @Description : 푸름이 이동환경교실 참여이력 수정
    * @param mvmnAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateMvmnAply(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;   
    
    /**
    * 교육대상 등록
    **
    @Title : insertTrgtCd
    * @Description : 교육대상 등록
    * @param mvmnAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertTrgtCd(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;
    
    /**
    * 교육대상 삭제
    **
    @Title : deleteTrgtCd
    * @Description : 교육대상 삭제
    * @param mvmnAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteTrgtCd(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;    
}
