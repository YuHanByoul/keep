package com.kbrainc.plum.mng.chklst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.chklst.model.ChklstDao;
import com.kbrainc.plum.mng.chklst.model.ChklstQitemMapngVo;
import com.kbrainc.plum.mng.chklst.model.ChklstQitemVo;
import com.kbrainc.plum.mng.chklst.model.ChklstVo;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.qestnr.model.QitemExVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 체크리스트관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.service
 * - ChklstServiceImpl.java
 * </pre> 
 *
 * @ClassName : ChklstServiceImpl
 * @Description : 체크리스트관리 서비스 구현 
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class ChklstServiceImpl extends PlumAbstractServiceImpl implements ChklstService {
    
    @Autowired
    private ChklstDao chklstDao;
    
    /**
     * 체크리스트 문항 등록
     *
     * @Title : insertChklstQitem 
     * @Description : 체크리스트 문항 등록
     * @param chklstQitemVo ChklstQitemVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
     @Override
     public int insertChklstQitem(ChklstQitemVo chklstQitemVo) throws Exception {
         int retVal = 0;
         retVal += chklstDao.insertChklstQitem(chklstQitemVo);
         
         return retVal;
     }
     
     /**
      * 체크리스트 문항 구분코드 목록 조회
      *
      * @Title : selectChklstQitemCdList
      * @Description : 체크리스트 문항 구분코드 목록 조회
      * @param codeVo CodeVo 객체
      * @return List<ChklstQitemVo> 체크리스트 문항 목록
      * @throws Exception 예외
      */
     @Override
     public List<CodeVo> selectChklstQitemCdList(CodeVo codeVo) throws Exception {
         return chklstDao.selectChklstQitemCdList(codeVo);
     }
     
    /**
     * 체크리스트 문항 목록 조회
     *
     * @Title : selectChklstQitemList
     * @Description : 체크리스트 문항 목록 조회
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return List<ChklstQitemVo> 체크리스트 문항 목록
     * @throws Exception 예외
     */
    @Override
    public List<ChklstQitemVo> selectChklstQitemList(ChklstQitemVo chklstQitemVo) throws Exception {
        return chklstDao.selectChklstQitemList(chklstQitemVo);
    }
    
    /**
     * 체크리스트 문항 정보 조회
     *
     * @Title : selectChklstQitemInfo
     * @Description : 체크리스트 문항 정보 조회
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return ChklstQitemVo ChklstQitemVo 객체
     * @throws Exception 예외
     */
    @Override
    public ChklstQitemVo selectChklstQitemInfo(ChklstQitemVo chklstQitemVo) throws Exception {
        return chklstDao.selectChklstQitemInfo(chklstQitemVo);
    }
      
    /**
     * 체크리스트 문항 업데이트
     *
     * @Title : updateChklstQitem
     * @Description : 체크리스트 문항 업데이트
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    public int updateChklstQitem(ChklstQitemVo chklstQitemVo) throws Exception {
        int retVal = 0;
        retVal = chklstDao.updateChklstQitem(chklstQitemVo);        
             
        return retVal;
    }
    
    /**
     * 체크리스트 등록
     *
     * @Title : insertChklst 
     * @Description : 체크리스트 등록
     * @param chklstVo ChklstVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
     @Override
     public int insertChklst(ChklstVo chklstVo) throws Exception {
         int retVal = 0;
         retVal += chklstDao.insertChklst(chklstVo);
         
         return retVal;
     }
     
     /**
      * 체크리스트 목록 조회
      *
      * @Title : selectChklstList
      * @Description : 체크리스트 목록 조회
      * @param chklstVo ChklstVo 객체
      * @return List<ChklstVo> 체크리스트 목록
      * @throws Exception 예외
      */
     @Override
     public List<ChklstVo> selectChklstList(ChklstVo chklstVo) throws Exception {
         return chklstDao.selectChklstList(chklstVo);
     }
     
     /**
      * 체크리스트 정보 조회
      *
      * @Title : selectChklstQitemInfo
      * @Description : 체크리스트 정보 조회
      * @param chklstVo ChklstVo 객체
      * @return ChklstVo ChklstVo 객체
      * @throws Exception 예외
      */
     @Override
     public ChklstVo selectChklstInfo(ChklstVo chklstVo) throws Exception {
         return chklstDao.selectChklstInfo(chklstVo);
     }
     
     /**
      * 사용중인 체크리스트 여부 확인
      *
      * @Title : isUseChklst
      * @Description : 사용중인 체크리스트 여부 확인
      * @param chklstVo ChklstVo 객체
      * @return ChklstVo ChklstVo 객체
      * @throws Exception 예외
      */
     @Override
     public ChklstVo isUseChklst(ChklstVo chklstVo) throws Exception {
         return chklstDao.isUseChklst(chklstVo);
     }
     
     /**
      * 체크리스트 업데이트
      *
      * @Title : updateChklst
      * @Description : 체크리스트 업데이트
      * @param chklstVo ChklstVo 객체
      * @return int update 로우수
      * @throws Exception 예외
      */
     @Override
     public int updateChklst(ChklstVo chklstVo) throws Exception {
         int retVal = 0;
         retVal = chklstDao.updateChklst(chklstVo);        
              
         return retVal;
     }
     
     /**
      * 체크리스트 문항구성 목록 조회
      *
      * @Title : selectChklstQitemMapngList
      * @Description : 체크리스트 문항구성 목록 조회
      * @param chklstQitemMapngVo ChklstQitemMapngVo 객체
      * @return List<ChklstQitemMapngVo> 체크리스트 문항구성 목록
      * @throws Exception 예외
      */
     @Override
     public List<ChklstQitemMapngVo> selectChklstQitemMapngList(ChklstQitemMapngVo chklstQitemMapngVo) throws Exception {
         return chklstDao.selectChklstQitemMapngList(chklstQitemMapngVo);
     }
     
     /**
      * 체크리스트 문항구성 업데이트
      *
      * @Title : updateChklstQitemMapng
      * @Description : 체크리스트 문항구성 업데이트
      * @param chklstQitemMapngVo ChklstQitemMapngVo 객체
      * @return int update 로우수
      * @throws Exception 예외
      */
     @Override
     @Transactional
     public int updateChklstQitemMapng(ChklstQitemMapngVo chklstQitemMapngVo) throws Exception {
         int retVal = 0;
         
         List<ChklstQitemVo> qitemList = chklstQitemMapngVo.getQitemList();
         if(qitemList != null && qitemList.size() > 0) {
             ChklstQitemVo qitemVo = null;
             for(int i = 0 ; i < qitemList.size() ; i++) {
                 qitemVo = qitemList.get(i);
                 qitemVo.setUser(chklstQitemMapngVo.getUser());
                 if("Y".equals(qitemVo.getNewYn())) { // 추가된 항목 insert
                     retVal += chklstDao.insertChklstQitemMapng(qitemVo);
                 } else { // 업데이트
                     retVal += chklstDao.updateChklstQitemMapng(qitemVo);
                 }
             }
         }
         
         return retVal;
     }
    
}