/**
 * 
 */
package com.kbrainc.plum.front.mypage.infntAplyHist.model;

import java.util.List;

import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.srvy.model.SrvyVo;

/**
* 유아환경교육관 교육신청 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.infntAplyHist.model
* - InfntAplyHistDao.java
* </pre>
*
* @ClassName : InfntAplyHistDao
* @Description : 유아환경교육관 교육신청 Dao 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.infntAplyHistDao")
public interface InfntAplyHistDao {
    
    /**
     * 유아환경교육관 신청이력 조회
     *
     * @Title : selectInfntAplyHistList
     * @Description : 유아환경교육관 신청이력 조회
     * @param infntAplyHistVo
     * @return
     * @throws Exception
     * @return List<InfntAplyHistVo>
     */
    public List<InfntAplyHistVo> selectInfntAplyHistList(InfntAplyHistVo infntAplyHistVo) throws Exception;        

    /**
     * 유아환경교육관 신청이력 정보 조회
     *
     * @Title : selectInfntAplyHistInfo
     * @Description : 유아환경교육관 신청이력 정보 조회
     * @param infntAplyHistVo
     * @return
     * @throws Exception
     * @return InfntAplyHistVo
     */
    public InfntAplyHistVo selectInfntAplyHistInfo(InfntAplyHistVo infntAplyHistVo) throws Exception;        

    /**
     * 유아환경교육관 신청이력 상세 조회
     *
     * @Title : selectInfntAplyHistDetail
     * @Description : 유아환경교육관 신청이력 상세 조회
     * @param infntAplyHistVo
     * @return
     * @throws Exception
     * @return InfntAplyHistVo
     */
    public InfntAplyHistVo selectInfntAplyHistDetail(InfntAplyHistVo infntAplyHistVo) throws Exception;
    
    /**
    * 유아환경교육관 상태수정
    **
    * @Title : updateSttsInfntAply
    * @Description : 유아환경교육관 상태수정
    * @param infntAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateSttsInfntAply(InfntAplyHistVo infntAplyHistVo) throws Exception;

    /**
     * 유아환경교육관 설문 정보 조회
     **
     * @Title : selectInfntSrvyInfo
     * @Description : 유아환경교육관 설문 정보 조회
     * @param infntAplyHistVo
     * @return
     * @throws Exception
     * @return SrvyVo
     */
    public SrvyVo selectInfntSrvyInfo(SrvyVo srvyVo) throws Exception;
    
    /**
     * 유아환경교육관 설문 공유 리스트 조회
     *
     * @Title : selectInfntSrvySendList
     * @Description : 유아환경교육관 설문 공유 리스트 조회
     * @param srvyVo
     * @return
     * @throws Exception
     * @return List<SrvyVo>
     */
    public List<SrvyVo> selectInfntSrvySendList(SrvyVo srvyVo) throws Exception;  
    
    /**
    * 유아환경교육관 참여이력 수정
    **
    * @Title : updateInfntAply
    * @Description : 유아환경교육관 참여이력 수정
    * @param infntAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateInfntAply(InfntAplyHistVo infntAplyHistVo) throws Exception;   
    
    /**
    * 교육대상 등록
    **
    @Title : insertTrgtCd
    * @Description : 교육대상 등록
    * @param infntAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertTrgtCd(InfntAplyHistVo infntAplyHistVo) throws Exception;
    
    /**
    * 교육대상 삭제
    **
    @Title : deleteTrgtCd
    * @Description : 교육대상 삭제
    * @param infntAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteTrgtCd(InfntAplyHistVo infntAplyHistVo) throws Exception;

    public String selectEduTrgtCd(InfntAplyHistVo infntAplyHistVo) throws Exception;

    public SrvyVo selectSrvyInfo(SrvyVo srvyVo) throws Exception;
}
