/**
 * 
 */
package com.kbrainc.plum.front.mvmnAply.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.mvmnAply.model.MvmnAplyDao;
import com.kbrainc.plum.front.mvmnAply.model.MvmnAplyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 푸름이 이동환경교실 교육신청 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.mvmnAply.service
* - MvmnAplyServiceImpl.java
* </pre>
*
* @ClassName : MvmnAplyServiceImpl
* @Description : 푸름이 이동환경교실 교육신청 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.mvmnAplyServiceImpl")
@Alias("front.mvmnAplyServiceImpl")
public class MvmnAplyServiceImpl extends PlumAbstractServiceImpl implements MvmnAplyService {

    @Resource(name = "front.mvmnAplyDao")
    private MvmnAplyDao mvmnAplyDao;

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
    @Override
    public List<MvmnAplyVo> selectMvmnAplyList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyList(mvmnAplyVo);
    }
    
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
    @Override
    public MvmnAplyVo selectMvmnAplyInfo(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyInfo(mvmnAplyVo);
    }
    
    /**
    * 푸름이 이동환경교실 교육신청 회차 목록 조회
    *
    * @Title : selectInstMvmnAplyList
    * @Description : 푸름이 이동환경교실 교육신청 회차 목록 조회
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return List<MvmnAplyVo>
    */
    @Override
    public List<MvmnAplyVo> selectMvmnAplyTmeList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyTmeList(mvmnAplyVo);
    }

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
    @Override
    public List<MvmnAplyVo> selectMvmnAplyEduSareaList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyEduSareaList(mvmnAplyVo);
    }
    
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
    @Override
    public List<MvmnAplyVo> selectEduPhotoFileList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectEduPhotoFileList(mvmnAplyVo);
    }
    
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
    @Override
    public List<MvmnAplyVo> selectMvmnAplyDeList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyDeList(mvmnAplyVo);
    } 

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
    @Override
    public MvmnAplyVo selectMvmnAplyRegInfo(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyRegInfo(mvmnAplyVo);
    }
    
    /**
    * 푸름이 이동환경교실 교육신청 등록
    *
    * @Title : insertMvmnAply
    * @Description : 푸름이 이동환경교실 교육신청 등록
    * @param mvmnAplyVo
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception{
        int retVal = 0;
        retVal += mvmnAplyDao.insertMvmnAply(mvmnAplyVo);
        retVal += mvmnAplyDao.insertMvmnAplySchdl(mvmnAplyVo);
        
        if(mvmnAplyVo.getTrgtCds()!=null & mvmnAplyVo.getTrgtCds().length > 0) {
            retVal += mvmnAplyDao.insertTrgtCd(mvmnAplyVo);
        }
        return retVal;
    }    
    
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
    @Override
    public List<MvmnAplyVo> selectEduSareaList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectEduSareaList(mvmnAplyVo);
    }
    
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
    public List<MvmnAplyVo> selectMvmnAplySignguList(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.selectMvmnAplySignguList(mvmnAplyVo);        
    }
    
}
