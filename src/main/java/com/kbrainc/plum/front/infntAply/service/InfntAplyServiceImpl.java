/**
 * 
 */
package com.kbrainc.plum.front.infntAply.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.infntAply.model.InfntAplyDao;
import com.kbrainc.plum.front.infntAply.model.InfntAplyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육관 교육신청 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.infntAply.service
* - InfntAplyServiceImpl.java
* </pre>
*
* @ClassName : InfntAplyServiceImpl
* @Description : 유아환경교육관 교육신청 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.infntAplyServiceImpl")
@Alias("front.infntAplyServiceImpl")
public class InfntAplyServiceImpl extends PlumAbstractServiceImpl implements InfntAplyService {

    @Resource(name = "front.infntAplyDao")
    private InfntAplyDao infntAplyDao;

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
    @Override
    public List<InfntAplyVo> selectInfntAplyList(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyList(infntAplyVo);
    }
    
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
    @Override
    public InfntAplyVo selectInfntAplyInfo(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyInfo(infntAplyVo);
    }
    
    /**
    * 유아환경교육관 교육신청 회차 목록 조회
    *
    * @Title : selectInstInfntAplyList
    * @Description : 유아환경교육관 교육신청 회차 목록 조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return List<InfntAplyVo>
    */
    @Override
    public List<InfntAplyVo> selectInfntAplyTmeList(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyTmeList(infntAplyVo);
    }

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
    @Override
    public List<InfntAplyVo> selectInfntAplyEduClssRmList(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyEduClssRmList(infntAplyVo);
    }
    
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
    @Override
    public List<InfntAplyVo> selectEduPhotoFileList(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectEduPhotoFileList(infntAplyVo);
    }
    
    /**
    * 유아환경교육관 교육신청 날짜조회
    *
    * @Title : selectInfntAplyDeList
    * @Description : 유아환경교육관 교육신청 날짜조회
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return List<InfntAplyVo>
    */
    @Override
    public List<InfntAplyVo> selectInfntAplyDeList(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyDeList(infntAplyVo);
    } 

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
    @Override
    public InfntAplyVo selectInfntAplyRegInfo(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyRegInfo(infntAplyVo);
    }
    
    /**
    * 유아환경교육관 교육신청 등록
    *
    * @Title : insertInfntAply
    * @Description : 유아환경교육관 교육신청 등록
    * @param infntAplyVo
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertInfntAply(InfntAplyVo infntAplyVo) throws Exception{
        int retVal = 0;
        retVal += infntAplyDao.insertInfntAply(infntAplyVo);
        
        if(infntAplyVo.getTrgtCds()!=null & infntAplyVo.getTrgtCds().length > 0) {
            retVal += infntAplyDao.insertTrgtCd(infntAplyVo);
        }
        return retVal;
    }    
    
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
    @Override
    public List<InfntAplyVo> selectInfntAplyHistList(InfntAplyVo infntAplyVo) throws Exception{
        return infntAplyDao.selectInfntAplyHistList(infntAplyVo);
    }        
}
