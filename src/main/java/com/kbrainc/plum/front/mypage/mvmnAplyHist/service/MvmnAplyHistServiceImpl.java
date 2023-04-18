/**
 * 
 */
package com.kbrainc.plum.front.mypage.mvmnAplyHist.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.mypage.mvmnAplyHist.model.MvmnAplyHistDao;
import com.kbrainc.plum.front.mypage.mvmnAplyHist.model.MvmnAplyHistVo;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 푸름이 이동환경교실 교육신청 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.mvmnAplyHist.service
* - MvmnAplyHistServiceImpl.java
* </pre>
*
* @ClassName : MvmnAplyHistServiceImpl
* @Description : 푸름이 이동환경교실 교육신청 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.mvmnAplyHistServiceImpl")
@Alias("front.mvmnAplyHistServiceImpl")
public class MvmnAplyHistServiceImpl extends PlumAbstractServiceImpl implements MvmnAplyHistService {

    @Resource(name = "front.mvmnAplyHistDao")
    private MvmnAplyHistDao mvmnAplyHistDao;
    
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
    @Override
    public List<MvmnAplyHistVo> selectMvmnAplyHistList(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        return mvmnAplyHistDao.selectMvmnAplyHistList(mvmnAplyHistVo);
    }        
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
    @Override
    public MvmnAplyHistVo selectMvmnAplyHistInfo(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        return mvmnAplyHistDao.selectMvmnAplyHistInfo(mvmnAplyHistVo);
    }

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
    @Override
    public MvmnAplyHistVo selectMvmnAplyHistDetail(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        return mvmnAplyHistDao.selectMvmnAplyHistDetail(mvmnAplyHistVo);
    }    
    
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
    @Override
    @Transactional    
    public int updateSttsMvmnAply(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        int retVal = 0;
        if("C".equals(mvmnAplyHistVo.getUpdCd())) {
            mvmnAplyHistVo.setUpdCd("180104");
        }else if("R".equals(mvmnAplyHistVo.getUpdCd())) {
            mvmnAplyHistVo.setUpdCd("180105");            
        }
        retVal += mvmnAplyHistDao.updateSttsMvmnAply(mvmnAplyHistVo);
        return retVal;
    }
    
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
    @Override
    public SrvyVo selectMvmnSrvyInfo(SrvyVo srvyVo) throws Exception{
        return mvmnAplyHistDao.selectMvmnSrvyInfo(srvyVo);        
    }
    
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
    @Override
    public List<SrvyVo> selectMvmnSrvySendList(SrvyVo srvyVo) throws Exception{
        return mvmnAplyHistDao.selectMvmnSrvySendList(srvyVo);
    }  
    
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
    @Override
    @Transactional    
    public int updateMvmnAply(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        int retVal = 0;
        retVal += mvmnAplyHistDao.updateMvmnAply(mvmnAplyHistVo);
        
        mvmnAplyHistDao.deleteTrgtCd(mvmnAplyHistVo);
        if(mvmnAplyHistVo.getTrgtCds()!=null & mvmnAplyHistVo.getTrgtCds().length > 0) {
            retVal += mvmnAplyHistDao.insertTrgtCd(mvmnAplyHistVo);
        }
        
        return retVal;
    }        
}
