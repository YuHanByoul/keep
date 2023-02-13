package com.kbrainc.plum.mng.tchaid.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.cmpnt.model.CmpntVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidCmpntCmpstnVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidDao;
import com.kbrainc.plum.mng.tchaid.model.TchaidEduSbjctVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidEduTrgtVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidWrhousngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 교구관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaid.service
 * - TchaidServiceImpl.java
 * </pre> 
 *
 * @ClassName : TchaidServiceImpl
 * @Description : 교구관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 02
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class TchaidServiceImpl extends PlumAbstractServiceImpl implements TchaidService { 

    @Autowired
    private TchaidDao tchaidDao;
    
    /**
     * 교구 목록 호출 
     *
     * @Title       : selectTchaidList 
     * @Description : 교구 목록 호출
     * @param TchaidVo 객체
     * @return List<TchaidVo>  목록
     * @throws Exception 예외
     */
    public List<TchaidVo> selectTchaidList(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.selectTchaidList(tchaidVo);
    }
    /**
     * 교구 등록 
     *
     * @Title       : insertTchaid 
     * @Description : 교구 등록 
     * @param TchaidVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int insertTchaid(TchaidVo tchaidVo) throws Exception{
        int resInt = 0;
        resInt = tchaidDao.insertTchaid(tchaidVo);
        resInt += tchaidDao.insertTchaidEduTrgt(tchaidVo);
        resInt += tchaidDao.insertTchaidEduSbjct(tchaidVo);
        resInt += tchaidDao.insertTchaidCmpntCmpstn(tchaidVo);
        return resInt;
    }
    /**
     * 교구 대상 코드 맵핑 등록 
     *
     * @Title       : insertTchaidEduTrgt 
     * @Description : 교구 대상 코드 맵핑 등록 
     * @param TchaidVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertTchaidEduTrgt(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.insertTchaidCmpntCmpstn(tchaidVo);
    }
    /**
     * 교구 주제 코드 맵핑 등록 
     *
     * @Title       : insertTchaidEduSbjct 
     * @Description : 교구 주제 코드 맵핑 등록 
     * @param TchaidVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertTchaidEduSbjct(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.insertTchaidCmpntCmpstn(tchaidVo);
    }
    /**
     * 교구 수정 
     *
     * @Title       : updateTchaid 
     * @Description : 교구 주제 코드 맵핑 등록 
     * @param TchaidVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int updateTchaid(TchaidVo tchaidVo) throws Exception{
        
        int resInt = 0;
        //기존 교육 주제 및 대상 삭제 
        resInt += tchaidDao.deleteTchaidEduSbjct(tchaidVo);
        resInt += tchaidDao.deleteTchaidEduTrgt(tchaidVo);
        
        resInt += tchaidDao.updateTchaid(tchaidVo);
        resInt += tchaidDao.insertTchaidEduTrgt(tchaidVo);
        resInt += tchaidDao.insertTchaidEduSbjct(tchaidVo);
        return resInt;
    }
    /**
     * 교구 주제 코드 삭제 
     *
     * @Title       : deleteTchaidEduSbjct 
     * @Description : 교구 주제 코드 삭제 
     * @param TchaidVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteTchaidEduSbjct(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.insertTchaidCmpntCmpstn(tchaidVo);
    }
    /**
     * 교구 대상코드 삭제
     *
     * @Title       : deleteTchaidEduTrgt 
     * @Description : 교구 대상코드 삭제
     * @param TchaidVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteTchaidEduTrgt(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.insertTchaidCmpntCmpstn(tchaidVo);
    }
    /**
     * 교구 상세 내역 호출 
     *
     * @Title       : selectTchaid 
     * @Description : 교구 상세 내역 호출
     * @param TchaidVo tchaidVo 객체
     * @return TchaidVo 목록
     * @throws Exception 예외
     */
    public TchaidVo selectTchaid(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.selectTchaid(tchaidVo);
    };
    /**
     * 교구 교육 주제 코드 리스트 호출 
     *
     * @Title       : selectTchaidEduSbjctList 
     * @Description : 교구 교육 주제 코드 리스트 호출 
     * @param TchaidVo 객체
     * @return List<TchaidEduSbjctVo>  목록
     * @throws Exception 예외
     */
    public List<TchaidEduSbjctVo> selectTchaidEduSbjctList(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.selectTchaidEduSbjctList(tchaidVo);
    };
    /**
     * 교구 교육 대상 코드 리스트 호출 
     *
     * @Title       : selectTchaidEduTrgtList 
     * @Description : 교구 교육 대상 코드 리스트 호출 
     * @param TchaidVo 객체
     * @return List<TchaidEduTrgtVo>  목록
     * @throws Exception 예외
     */
    public List<TchaidEduTrgtVo> selectTchaidEduTrgtList(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.selectTchaidEduTrgtList(tchaidVo);
    };
    /**
     * 교구 입고 등록
     *
     * @Title       : insertTchaidWrhousng 
     * @Description : 교구 입고 등록
     * @param TchaidVo 객체
     * @return 
     * @throws Exception 예외
     */
    @Transactional
    public int insertTchaidWrhousng(TchaidWrhousngVo tchaidWrhousngVo) throws Exception{
        
        int resInt = 0;
        resInt += tchaidDao.insertTchaidWrhousng(tchaidWrhousngVo);
        
        TchaidVo paramVo = new TchaidVo();
        paramVo.setUser(tchaidWrhousngVo.getUser());
        paramVo.setTchaidid(tchaidWrhousngVo.getTchaidid());
        paramVo.setPlusQnty(tchaidWrhousngVo.getQnty());
        resInt += tchaidDao.updateTchaid(paramVo);
        
        return resInt;
        
    };
    /**
     * 교구 입고 목록 호출
     *
     * @Title       : selectTchaidWrhousngList 
     * @Description : 교구 입고 목록 호출 
     * @param TchaidVo 객체
     * @return List<TchaidWrhousngVo>  목록
     * @throws Exception 예외
     */
    public List<TchaidWrhousngVo> selectTchaidWrhousngList(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.selectTchaidWrhousngList(tchaidVo);
    };
    /**
     * 교구 구성품 등록
     *
     * @Title       : insertTchaidCmpntCmpstn 
     * @Description : 교구 구성품 등록
     * @param TchaidVo 객체
     * @return int 
     * @throws Exception 예외
     */
    public int insertTchaidCmpntCmpstn(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.insertTchaidCmpntCmpstn(tchaidVo);
    };
    /**
     * 교구 관련 코드 호출 
     *
     * @Title       : selectTchaidCdList 
     * @Description : 교구 관련 코드 호출 
     * @param Map<String,String> 객체
     * @return List<Map<String,String>> 목록
     * @throws Exception 예외
     */
    public List<Map<String,String>> selectTchaidCdList(Map<String,String> map) throws Exception{
        return tchaidDao.selectTchaidCdList(map);
    };
    /**
     * 교구 모든 구성품 목록 호출  
     *
     * @Title       : selectAllCmpntList 
     * @Description : 교구 모든 구성품 목록 호출 
     * @return List<CmpntVo> 목록
     * @throws Exception 예외
     */
    public List<CmpntVo> selectAllCmpntList(Map<String,String> map) throws Exception{
        return tchaidDao.selectAllCmpntList(map);
    };
    /**
     * 교구 구성품 목록 호출  
     *
     * @Title       : selectTchaidCmpntList 
     * @Description : 교구 구성품 목록 호출  
     * @return List<TchaidCmpntCmpstnVo> 목록
     * @throws Exception 예외
     */
    public List<TchaidCmpntCmpstnVo> selectTchaidCmpntList(Map<String,String> map) throws Exception{
        return tchaidDao.selectTchaidCmpntList(map);
    };
    /**
     * 꾸러미 교구 갯수(여부)호출 
     *
     * @Title       : selectTchaidCntForPackage 
     * @Description : 꾸러미 교구 갯수(여부)호출 
     * @param TchaidVo 객체
     * @return int 
     * @throws Exception 예외
     */
    public int selectTchaidCntForPackage(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.selectTchaidCntForPackage(tchaidVo);
    };
    /**
     * 교구 삭제 
     *
     * @Title       : deleteTchaid 
     * @Description : 교구 삭제 
     * @param TchaidVo 객체
     * @return int 
     * @throws Exception 예외
     */
    @Transactional
    public int deleteTchaid(TchaidVo tchaidVo) throws Exception{
        
        int resInt = 0;
        resInt += tchaidDao.updateCmpntQntyBack(tchaidVo);
        resInt += tchaidDao.deleteTchaidEduSbjct(tchaidVo);
        resInt += tchaidDao.deleteTchaidEduTrgt(tchaidVo);
        resInt += tchaidDao.deleteTchaidCmpntCmpstn(tchaidVo);
        resInt += tchaidDao.deleteTchaid(tchaidVo);
        return resInt;
    };
    /**
     * 교구 구성품 구성 삭제 
     *
     * @Title       : deleteTchaid 
     * @Description : 교구 구성품 구성 삭제 
     * @param TchaidVo 객체
     * @return int 
     * @throws Exception 예외
     */
    public int deleteTchaidCmpntCmpstn(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.deleteTchaidCmpntCmpstn(tchaidVo);
    };
    /**
     * 교구 사용여부 변경 
     *
     * @Title       : updateTchaidUseYn 
     * @Description : 교구 사용여부 변경 
     * @param TchaidVo 객체
     * @return int 
     * @throws Exception 예외
     */
    public int updateTchaidUseYn(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.updateTchaid(tchaidVo);
    };
    /**
     * 교구 구성 상세 리스트 호출   
     *
     * @Title       : selectTchaidCmpntCmpstnDetailList 
     * @Description : 교구 구성 상세 리스트 호출 
     * @return List<TchaidCmpntCmpstnVo> 목록
     * @throws Exception 예외
     */
    public List<TchaidCmpntCmpstnVo> selectTchaidCmpntCmpstnDetailList(TchaidVo tchaidVo) throws Exception{
        return tchaidDao.selectTchaidCmpntCmpstnDetailList(tchaidVo);
    };
    
    /**
     * 교구 생성( 구성품으로 ) 
     *
     * @Title       : createTchaidFromCmpnt 
     * @Description : 교구 생성( 구성품으로 ) 
     * @param TchaidVo 객체
     * @return int 
     * @throws Exception 예외
     */
    @Transactional
    public int createTchaidFromCmpnt(TchaidVo tchaidVo) throws Exception{
        int resInt = 0;
        resInt += tchaidDao.updateCmpntQntyForTchaidPlus(tchaidVo);
        resInt += tchaidDao.updateTchaid(tchaidVo);
        return resInt;
    }
}
