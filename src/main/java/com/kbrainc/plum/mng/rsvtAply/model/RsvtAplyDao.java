package com.kbrainc.plum.mng.rsvtAply.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.spce.model.SpceVo;

/**
 * 
 * 예약신청 관리 DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.rsvtAply.model
 * - RsvtAplyDao.java
 * </pre>        
 *
 * @ClassName : RsvtAplyDao
 * @Description : 예약신청 관리 DAO 클래스. 
 * @author : KBRAINC
 * @date : 2023. 01. 10.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface RsvtAplyDao {
    
    /**
     * 공간 리스트 호출 
     *
     * @Title       : selectFclRsvtdeList 
     * @Description : 공간 리스트 호출
     * @param Map<String,Object> 객체
     * @return List<Map<String,Object>> 기관정보 목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectSpcetList(SpceVo spceVo) throws Exception;
    /**
     * 예약신청 일자  리스트 호출 
     *
     * @Title       : selectRsvtApplyList 
     * @Description : 예약신청 일자  리스트 호출
     * @param Map<String,Object> 객체
     * @return List<FclAplyRsvtdeVo>
     * @throws Exception 예외
     */
    public List<FclAplyRsvtdeVo> selectRsvtAplyList(FclAplyRsvtdeVo fclAplyRsvtdeVo) throws Exception;
}