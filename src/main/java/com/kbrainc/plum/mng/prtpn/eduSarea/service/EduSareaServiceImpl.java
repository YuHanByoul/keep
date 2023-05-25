package com.kbrainc.plum.mng.prtpn.eduSarea.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.prtpn.eduSarea.model.EduSareaVo;
import com.kbrainc.plum.mng.prtpn.eduSarea.model.EduSareaDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육 -> 교육관관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduSarea.service
* - EduSareaServiceImpl.java
* </pre>
**
@ClassName : EduSareaServiceImpl
* @Description : 유아환경교육 -> 교육관관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class EduSareaServiceImpl extends PlumAbstractServiceImpl implements EduSareaService{
    
    @Autowired
    private EduSareaDao eduSareaDao;
    
    /**
    * 환경교육관리 게시글 목록 조회
    *
    * @Title : selectEduSareaList
    * @Description : 환경교육관리 게시글 목록 조회
    * @param eduSareaVo 환경교육관리 객체
    * @throws Exception 예외
    * @return List<EduSareaVo>
    */
    @Override
    public List<EduSareaVo> selectEduSareaList(EduSareaVo eduSareaVo) throws Exception {
        return eduSareaDao.selectEduSareaList(eduSareaVo);
    }
    
    /**
    * 환경교육관리 게시글 등록
    *
    * @Title : insertEduSarea
    * @Description : 환경교육관리 게시글 등록
    * @param eduSareaVo 환경교육관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertEduSarea(EduSareaVo eduSareaVo) throws Exception{
        int retVal = 0;
        retVal += eduSareaDao.insertEduSarea(eduSareaVo);
        if(!eduSareaVo.getCtprvnCds().isEmpty()) {
            retVal += eduSareaDao.insertCtprvnCd(eduSareaVo);
        }

        return retVal;
    }
    
    /**
    * 환경교육관리 게시글 상세조회
    *
    * @Title : selectEduSareaInfo
    * @Description : 환경교육관리 게시글 상세조회
    * @param eduSareaVo 환경교육관리 객체
    * @throws Exception 예외
    * @return EduSareaVo
    */
    @Override
    public EduSareaVo selectEduSareaInfo(EduSareaVo eduSareaVo) throws Exception{
        return eduSareaDao.selectEduSareaInfo(eduSareaVo);
    }
    
    /**
    * 환경교육관리 게시글 수정
    *
    * @Title : updateEduSarea
    * @Description : 환경교육관리 게시글 수정
    * @param eduSareaVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateEduSarea(EduSareaVo eduSareaVo) throws Exception{
        int retVal = 0;

        retVal += eduSareaDao.updateEduSarea(eduSareaVo);

        retVal += eduSareaDao.deleteCtprvnCd(eduSareaVo);

        if(!eduSareaVo.getCtprvnCds().isEmpty()) {
            retVal += eduSareaDao.insertCtprvnCd(eduSareaVo);
        }

        return retVal;

        /*
        // 세부지역 정보 select
        EduSareaVo signguInfo = eduSareaDao.selectEduSareaSignguById(eduSareaVo);

        if(signguInfo == null) {
            // 세부지역 설정이 되어 있지 않은 경우
            retVal += eduSareaDao.deleteCtprvnCdAll(eduSareaVo);

            if(!eduSareaVo.getCtprvnCds().isEmpty()) {
                retVal += eduSareaDao.insertCtprvnCd(eduSareaVo);
            }
        } else {
            Boolean isNotChanged;
            isNotChanged = Arrays.stream(eduSareaVo.getCtprvnCd().split(",")).anyMatch(cd -> cd.equals(signguInfo.getCtprvnCd()));

            if (isNotChanged) {

                // 세부 지역 설정이 되어 있는 지역이 삭제 되지 않고 남아 있는 경우
                eduSareaVo.getCtprvnCds().remove(signguInfo.getCtprvnCd());

                if(!eduSareaVo.getCtprvnCds().isEmpty()) {
                    retVal += eduSareaDao.deleteCtprvnCd(eduSareaVo);
                    retVal += eduSareaDao.insertCtprvnCd(eduSareaVo);
                }
            } else {
                // 세부 지역 설정이 되어 있는 지역이 삭제된 경우
                retVal += eduSareaDao.deleteEduSareaSignguAll(eduSareaVo);
                retVal += eduSareaDao.deleteCtprvnCdAll(eduSareaVo);

                if(!eduSareaVo.getCtprvnCds().isEmpty()) {
                    retVal += eduSareaDao.insertCtprvnCd(eduSareaVo);
                }
            }
        }

        return retVal;*/


    }

    /**
     * 운영권역관리 세부지역설정 수정
     *
     * @Title : updateEduSareaSignguSetting
     * @Description : 운영권역관리 세부지역설정 수정
     * @param eduSareaVo 환경교육관리 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional    
    public int updateEduSareaSignguSetting(EduSareaVo eduSareaVo) throws Exception{
        int retVal = 0;
        
        eduSareaDao.deleteEduSareaSignguSetting(eduSareaVo);
        retVal += eduSareaDao.updateEduSareaSignguSetting(eduSareaVo);
        
        return retVal;
    }
    
    /**
    * 교육관_교육유형 조회.
    *
    * @Title : updateEduSarea
    * @Description : 교육관_교육유형 조회.
    * @param eduSareaVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public EduSareaVo selectClssrmEduTypeCd(String clssrmId) throws Exception{
        return eduSareaDao.selectClssrmEduTypeCd(clssrmId);        
    }
    
    /**
    * 시군구코드 목록을 조회.
    **
    * @Title : selectSignguCodeList
    * @Description : 시군구코드 목록을 조회
    * @param codeVo
    * @return
    * @throws Exception
    * @return List<EduSareaVo>
    */
    @Override
    public List<EduSareaVo> selectSignguCodeList(EduSareaVo eduSareaVo) throws Exception{
        return eduSareaDao.selectSignguCodeList(eduSareaVo);        
    }
    
    /**
     * 지역설정 목록을 조회.
     **
    @Title : selectCtprvnCdList
     * @Description : 지역설정 목록을 조회
     * @param sareaid
     * @return
     * @throws Exception
     * @return List<EduSareaVo>
     */
    @Override
    public List<EduSareaVo> selectCtprvnCdList(int sareaid) throws Exception{
        return eduSareaDao.selectCtprvnCdList(sareaid);
    }
    
    /**
    * 지역코드 목록을 공통 테이블에서 조회.
    **
    @Title : selectAddrCtprvnList
    * @Description : 지역코드 목록을 공통 테이블에서 조회.
    * @param codeVo
    * @return
    * @throws Exception
    * @return List<EduSareaVo>
    */
    @Override
    public List<EduSareaVo> selectAddrCtprvnList() throws Exception{
        return eduSareaDao.selectAddrCtprvnList();
    }
    
    /**
     * 시군구 목록을 공통 테이블에서 조회.
     **
    @Title : selectAddrSignguList
     * @Description : 시군구 목록을 공통 테이블에서 조회.
     * @param 
     * @return
     * @throws Exception
     * @return List<EduSareaVo>
     */
    @Override
    public List<EduSareaVo> selectAddrSignguList(EduSareaVo eduSareaVo) throws Exception{
        return eduSareaDao.selectAddrSignguList(eduSareaVo);        
    }

    /**
     * 권역 > 시도 테이블에 등록된 개수 조회
     *
     * @param eduSareaVo
     * @return int
     * @throws Exception
     * @Title : countAddrSignguList
     * @Description : TODO [메소드 설명]
     */
    @Override
    public int countAddrSignguList(EduSareaVo eduSareaVo) throws Exception {
        return eduSareaDao.countAddrSignguList(eduSareaVo);
    }

    /**
    * 등록된 지역코드 목록 조회
    *
    * @Title : selectDplctCtprvnCdList
    * @Description : 등록된 지역코드 목록 조회
    * @throws Exception 예외
    * @return List<String>
    */
    @Override
    public List<String> selectDplctCtprvnCdList(EduSareaVo eduSareaVo) throws Exception{
        return eduSareaDao.selectDplctCtprvnCdList(eduSareaVo);
    }    
}
