/**
 * 
 */
package com.kbrainc.plum.front.mypage.infntAplyHist.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistDao;
import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistVo;
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
    private InfntAplyHistDao InfntAplyHistDao;
    
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
    public List<InfntAplyHistVo> selectInfntAplyHistList(InfntAplyHistVo infntAplyHistVo) throws Exception{
        return InfntAplyHistDao.selectInfntAplyHistList(infntAplyHistVo);
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
    public InfntAplyHistVo selectInfntAplyHistInfo(InfntAplyHistVo infntAplyHistVo) throws Exception{
        return InfntAplyHistDao.selectInfntAplyHistInfo(infntAplyHistVo);
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
    public InfntAplyHistVo selectInfntAplyHistDetail(InfntAplyHistVo infntAplyHistVo) throws Exception{
        return InfntAplyHistDao.selectInfntAplyHistDetail(infntAplyHistVo);
    }    
    
    /**
    * 유아환경교육관 신청취소
    **
    * @Title : updateCancelInfntAply
    * @Description : 유아환경교육관 신청취소
    * @param infntAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateCancelInfntAply(InfntAplyHistVo infntAplyHistVo) throws Exception{
        int retVal = 0;
        retVal += InfntAplyHistDao.updateCancelInfntAply(infntAplyHistVo);
        return retVal;
    }
}