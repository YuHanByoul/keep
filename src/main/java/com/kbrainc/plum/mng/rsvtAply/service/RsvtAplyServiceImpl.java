package com.kbrainc.plum.mng.rsvtAply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.rsvtAply.model.FclAplyRsvtdeVo;
import com.kbrainc.plum.mng.rsvtAply.model.RsvtAplyDao;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 예약신청 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.rsvtAply.service
 * - RsvtAplyServiceImpl.java
 * </pre> 
 *
 * @ClassName : MemberServiceImpl
 * @Description : 예약신청 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 1. 27
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class RsvtAplyServiceImpl extends PlumAbstractServiceImpl implements RsvtAplyService { 

    @Autowired
    private RsvtAplyDao rsvtApplyDao;
    
    
    /**
     * 공간 리스트 호출 
     *
     * @Title       : selectFclRsvtdeList 
     * @Description : 공간 리스트 호출
     * @param Map<String,Object> 객체
     * @return List<Map<String,Object>> 기관정보 목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectSpcetList(SpceVo spceVo) throws Exception{
         return rsvtApplyDao.selectSpcetList(spceVo);
    }
    
    /**
     * 예약신청 일자  리스트 호출 
     *
     * @Title       : selectRsvtApplyList 
     * @Description : 예약신청 일자  리스트 호출
     * @param Map<String,Object> 객체
     * @return List<FclAplyRsvtdeVo>
     * @throws Exception 예외
     */
    public List<FclAplyRsvtdeVo> selectRsvtAplyList(FclAplyRsvtdeVo fclAplyRsvtdeVo) throws Exception{
        return rsvtApplyDao.selectRsvtAplyList(fclAplyRsvtdeVo);
   }
    
}