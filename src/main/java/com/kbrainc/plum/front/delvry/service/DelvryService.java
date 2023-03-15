package com.kbrainc.plum.front.delvry.service;

import java.util.List;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.delvry.model.DelvryAplyComputVo;
import com.kbrainc.plum.front.delvry.model.DelvryAplyVo;

/**
 * 
 * 교부관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.delvry.service
 * - DelvryService.java
 * </pre> 
 *
 * @ClassName : DelvryService
 * @Description : 교부관리 서비스 인터페이스 
 * @author : KCS
 * @date : 2023. 03. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface DelvryService {
    
    /**
    * 교부 신청 목록 조회
    *
    * @Title : selectDelvryAplyList 
    * @Description : 교부 신청 목록 조회
    * @param delvryAplyVo DelvryAplyVo객체
    * @return List<DelvryAplyVo> 교부 신청 목록
    * @throws Exception 예외
    */
    public List<DelvryAplyVo> selectDelvryAplyList(DelvryAplyVo delvryAplyVo) throws Exception;
    
    /**
    * 교부 신청 산출내역 목록 조회
    *
    * @Title : selectDelvryAplyComputList 
    * @Description : 교부 신청 산출내역 목록 조회
    * @param delvryAplyVo DelvryAplyVo객체
    * @return List<DelvryAplyComputVo> 교부 신청 산출내역 목록
    * @throws Exception 예외
    */
    public List<DelvryAplyComputVo> selectDelvryAplyComputList(DelvryAplyVo delvryAplyVo) throws Exception;
      
    /**
    * 교부 신청 업데이트
    *
    * @Title : saveDelvryAply 
    * @Description : 교부 신청 업데이트
    * @param delvryAplyVo DelvryAplyVo객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int saveDelvryAply(DelvryAplyVo delvryAplyVo) throws Exception;
       
    /**
    * 교부 신청 보완요청 정보 조회
    *
    * @Title : selectDelvryAplySplmntInfo 
    * @Description : 교부 신청 보완요청 정보 조회
    * @param supplementVo
    * @return supplementVo 교부 신청 보완요청 정보
    * @throws Exception 예외
    */
    public SupplementVo selectDelvryAplySplmntInfo(SupplementVo supplementVo) throws Exception;
    
    /**
    * 교부 신청 보완요청 업데이트
    *
    * @Title : updateDelvryAplySplmnt 
    * @Description : 교부 신청 보완요청 업데이트
    * @param supplementVo
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryAplySplmnt(SupplementVo supplementVo) throws Exception;
    
}