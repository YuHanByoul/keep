/**
 * 
 */
package com.kbrainc.plum.front.mypage.infntAplyHist.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistDao;
import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistVo;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육관 교육신청 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.infntAplyHist.service
* - InfntAplyHistServiceImpl.java
* </pre>
*
* @ClassName : InfntAplyHistServiceImpl
* @Description : 유아환경교육관 교육신청 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.infntAplyHistServiceImpl")
@Alias("front.infntAplyHistServiceImpl")
public class InfntAplyHistServiceImpl extends PlumAbstractServiceImpl implements InfntAplyHistService {

    @Resource(name = "front.infntAplyHistDao")
    private InfntAplyHistDao infntAplyHistDao;
    
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
    @Override
    public List<InfntAplyHistVo> selectInfntAplyHistList(InfntAplyHistVo infntAplyHistVo) throws Exception{
        return infntAplyHistDao.selectInfntAplyHistList(infntAplyHistVo);
    }        
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
    @Override
    public InfntAplyHistVo selectInfntAplyHistInfo(InfntAplyHistVo infntAplyHistVo) throws Exception{
        return infntAplyHistDao.selectInfntAplyHistInfo(infntAplyHistVo);
    }

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
    @Override
    public InfntAplyHistVo selectInfntAplyHistDetail(InfntAplyHistVo infntAplyHistVo) throws Exception{
        return infntAplyHistDao.selectInfntAplyHistDetail(infntAplyHistVo);
    }    
    
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
    @Override
    @Transactional    
    public int updateSttsInfntAply(InfntAplyHistVo infntAplyHistVo) throws Exception{
        int retVal = 0;
        if("C".equals(infntAplyHistVo.getUpdCd())) {
            infntAplyHistVo.setUpdCd("180104");
        }else if("R".equals(infntAplyHistVo.getUpdCd())) {
            infntAplyHistVo.setUpdCd("180105");            
        }
        retVal += infntAplyHistDao.updateSttsInfntAply(infntAplyHistVo);
        return retVal;
    }
    
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
    @Override
    public SrvyVo selectInfntSrvyInfo(SrvyVo srvyVo) throws Exception{
        return infntAplyHistDao.selectInfntSrvyInfo(srvyVo);        
    }
    
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
    @Override
    public List<SrvyVo> selectInfntSrvySendList(SrvyVo srvyVo) throws Exception{
        return infntAplyHistDao.selectInfntSrvySendList(srvyVo);
    }    
    
    
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
    @Override
    @Transactional    
    public int updateInfntAply(InfntAplyHistVo infntAplyHistVo) throws Exception{
        int retVal = 0;
        retVal += infntAplyHistDao.updateInfntAply(infntAplyHistVo);
        
        infntAplyHistDao.deleteTrgtCd(infntAplyHistVo);
        if(infntAplyHistVo.getTrgtCds()!=null & infntAplyHistVo.getTrgtCds().length > 0) {
            retVal += infntAplyHistDao.insertTrgtCd(infntAplyHistVo);
        }
        
        return retVal;
    }
    
    @Override
    public List<InfntAplyHistVo> selectEduTrgtCd(InfntAplyHistVo infntAplyHistVo) throws Exception {
        return infntAplyHistDao.selectEduTrgtCd(infntAplyHistVo);
    }    
}
