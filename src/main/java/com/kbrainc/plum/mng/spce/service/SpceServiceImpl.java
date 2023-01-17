package com.kbrainc.plum.mng.spce.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.spce.model.SpceDao;
import com.kbrainc.plum.mng.spce.model.SpceRsvtdeVo;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 사용자관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.service
 * - MemberServiceImpl.java
 * </pre> 
 *
 * @ClassName : MemberServiceImpl
 * @Description : 사용자관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 1. 10
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class SpceServiceImpl extends PlumAbstractServiceImpl implements SpceService {

    @Autowired
    private SpceDao spceDao;
    
    /**
     * 공간정보 저장
     *
     * @Title       : insertSpce 
     * @Description : 공간정보 저장
     * @param SpceVo SpceVo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int insertSpce(SpceVo spceVo) throws Exception{
        return spceDao.insertSpce(spceVo);
    }
    /**
     * 공간예약일자 등록
     *
     * @Title       : insertSpceRsvtde 
     * @Description : 공간예약일자 등록
     * @param SpceRsvtdeVo SpceRsvtdeVo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int insertSpceRsvtde(SpceRsvtdeVo sceRsvtdeVo) throws Exception{
        return spceDao.insertSpceRsvtde(sceRsvtdeVo);
    }
    /**
    * 공간 목록 리스트 호출 
    *
    * @Title       : selectSpceList 
    * @Description : 공간 목록 리스트 호출 
    * @param param SpceVo SpceVo 객체
    * @return List<SpceVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<SpceVo> selectSpceList(SpceVo spceVo) throws Exception{
        return spceDao.selectSpceList(spceVo);
    }
    /**
     * 시설 리스트 호출 
     *
     * @Title       : selectSpceList 
     * @Description : 시설 리스트 호출 
     * @param param SpceVo SpceVo 객체
     * @return List<Map<String,Object>> 기관정보 목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectFcltList(SpceVo spceVo) throws Exception{
        return spceDao.selectFcltList(spceVo);
    }
    /**
    * 공간 상세정보 조회 
    *
    * @Title       : selectSpceInfo 
    * @Description : 공간 상세정보 조회 
    * @param param SpceVo SpceVo 객체
    * @return List<SpceVo> 기관정보 목록
    * @throws Exception 예외
    */
    public SpceVo selectSpceInfo(SpceVo spceVo) throws Exception{
        return spceDao.selectSpceInfo(spceVo);
    }
    /**
     * 공간 정보 수정 
     *
     * @Title       : updateSpce 
     * @Description : 공간 정보 수정 
     * @param SpceVo spceVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int updateSpce(SpceVo spceVo) throws Exception{
        return spceDao.updateSpce(spceVo);
    }
    /**
     * 공간 정보 삭제 
     *
     * @Title       : deleteSpce 
     * @Description : 공간 정보 삭제 
     * @param SpceVo spceVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    @Transactional
    public int deleteSpce(SpceVo spceVo) throws Exception{
        int retVal = 0;
        retVal += spceDao.deleteSpceRsvtde(spceVo);
        retVal += spceDao.deleteSpce(spceVo);
        return retVal ;
    }
    /**
     * 공간 예약일자 정보 삭제 
     *
     * @Title       : deleteSpceRsvtde 
     * @Description : 공간 예약일자 정보 삭제 
     * @param SpceVo spceVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int deleteSpceRsvtde(SpceVo spceVo) throws Exception{
        return spceDao.deleteSpceRsvtde(spceVo);
    }
    /**
     * 공간 예약 내역 존재 여부 확인  
     *
     * @Title       : isThereSpceRsvt 
     * @Description : 공간 예약일자 정보 삭제 
     * @param SpceVo spceVo객체
     * @return String [Y,N]
     * @throws Exception 예외
     */
    public String isThereSpceRsvt(SpceVo spceVo) throws Exception{
        return spceDao.isThereSpceRsvt(spceVo);
    }
    /**
     * 예약일자 리스트 호출  
     *
     * @Title       : selectSpceRsvtList 
     * @Description : 예약일자 리스트 호출  
     * @param param SpceVo SpceVo 객체
     * @return List<SpceRsvtdeVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<SpceRsvtdeVo> selectSpceRsvtdeList(SpceRsvtdeVo spceRsvtdeVo) throws Exception{
        return spceDao.selectSpceRsvtdeList(spceRsvtdeVo);
    }
}