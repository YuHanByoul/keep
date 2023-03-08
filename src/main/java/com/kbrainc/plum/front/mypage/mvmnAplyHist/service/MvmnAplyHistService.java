/**
 * 
 */
package com.kbrainc.plum.front.mypage.mvmnAplyHist.service;

import java.util.List;

import com.kbrainc.plum.front.mypage.mvmnAplyHist.model.MvmnAplyHistVo;

/**
* 푸름이 이동환경교실 교육신청 서비스 클래스
*
* <pre>
* com.kbrainc.plum.front.mvmnAplyHist.service
* - MvmnAplyHistService.java
* </pre>
*
* @ClassName : MvmnAplyHistService
* @Description : 푸름이 이동환경교실 교육신청 서비스 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface MvmnAplyHistService {

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
     * @Description : 푸름이 이동환경교실 신청이력 조회
     * @param mvmnAplyHistVo
     * @return
     * @throws Exception
     * @return MvmnAplyHistVo
     */
    public MvmnAplyHistVo selectMvmnAplyHistDetail(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;   
    
    
    /**
    * 푸름이 이동환경교실 신청취소
    **
    * @Title : updateCancelMvmnAply
    * @Description : 푸름이 이동환경교실 신청취소
    * @param mvmnAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateCancelMvmnAply(MvmnAplyHistVo mvmnAplyHistVo) throws Exception;
}
