package com.kbrainc.plum.front.delvry.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;

/**
 * 
 * 교부관리 DAO
 *
 * <pre>
 * com.kbrainc.plum.front.delvry.model
 * - DelvryDao.java
 * </pre> 
 *
 * @ClassName : DelvryDao
 * @Description : 교부관리 DAO 
 * @author : KCS
 * @date : 2023. 03. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.delvryDao")
public interface DelvryDao {
    
    /**
    * 교부 신청 목록 조회
    *
    * @Title : selectDelvryAplyList
    * @Description : 교부 신청 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<DelvryAplyVo> 교부 신청 목록
    * @throws Exception 예외
    */
    public List<DelvryAplyVo> selectDelvryAplyList(DelvryAplyVo delvryAplyVo) throws Exception;
    
    /**
     * 교부신청 등록. 
     *
     * @Title : insertDelvryAply
     * @Description : TODO
     * @param delvryAplyVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertDelvryAply(DelvryAplyVo delvryAplyVo) throws Exception;

    /**
    * 교부 신청 정보 수정
    *
    * @Title : updateDelvryAply
    * @Description : 교부 신청 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryAply(DelvryAplyVo delvryAplyVo) throws Exception;
    
    /**
    * 교부 신청 산출내역 목록 조회
    *
    * @Title : selectDelvryAplyComputList
    * @Description : 교부 신청 산출내역 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<DelvryAplyComputVo> 교부 신청 산출내역 목록
    * @throws Exception 예외
    */
    public List<DelvryAplyComputVo> selectDelvryAplyComputList(DelvryAplyVo delvryAplyVo) throws Exception;
      
    /**
    * 교부 신청 산출내역 등록
    *
    * @Title : insertDelvryAplyComput
    * @Description : 교부 신청 산출내역 등록
    * @param delvryAplyComptVo DelvryAplyComputVo 객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    public int insertDelvryAplyComput(DelvryAplyComputVo delvryAplyComptVo) throws Exception;
       
    /**
    * 교부 신청 산출내역 수정
    *
    * @Title : updateDelvryAplyComput
    * @Description : 교부 신청 산출내역 업데이트
    * @param delvryAplyComptVo DelvryAplyComputVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryAplyComput(DelvryAplyComputVo delvryAplyComptVo) throws Exception;
    
    /**
    * 교부 신청 보완요청 정보 조회
    *
    * @Title : selectDelvryAplySplmntInfo
    * @Description : 교부 신청 보완요청 정보 조회
    * @param supplementVo
    * @return DelvryAplySplmntVo 교부 신청 보완요청 정보
    * @throws Exception 예외
    */
    public SupplementVo selectDelvryAplySplmntInfo(SupplementVo supplementVo) throws Exception;
    
    /**
    * 교부 신청 보완요청 업데이트
    *
    * @Title : updateDelvryAplyComput
    * @Description : 교부 신청 보완요청 업데이트
    * @param supplementVo
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryAplySplmnt(SupplementVo supplementVo) throws Exception;
    
    /**
    * 교부신청상태 수정. 
    *
    * @Title : updateDelvryAplyStts
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    public int updateDelvryAplyStts(SupplementVo supplementVo) throws Exception;
    
}