/**
 * 
 */
package com.kbrainc.plum.front.mypage.mvmnAplyHist.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.mypage.mvmnAplyHist.model.MvmnAplyHistDao;
import com.kbrainc.plum.front.mypage.mvmnAplyHist.model.MvmnAplyHistVo;
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
    private MvmnAplyHistDao MvmnAplyHistDao;
    
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
    public List<MvmnAplyHistVo> selectMvmnAplyHistList(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        return MvmnAplyHistDao.selectMvmnAplyHistList(mvmnAplyHistVo);
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
    public MvmnAplyHistVo selectMvmnAplyHistInfo(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        return MvmnAplyHistDao.selectMvmnAplyHistInfo(mvmnAplyHistVo);
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
    public MvmnAplyHistVo selectMvmnAplyHistDetail(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        return MvmnAplyHistDao.selectMvmnAplyHistDetail(mvmnAplyHistVo);
    }    
    
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
    public int updateCancelMvmnAply(MvmnAplyHistVo mvmnAplyHistVo) throws Exception{
        int retVal = 0;
        retVal += MvmnAplyHistDao.updateCancelMvmnAply(mvmnAplyHistVo);
        return retVal;
    }
}
