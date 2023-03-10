package com.kbrainc.plum.front.delvry.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.cmm.file.model.FileVo;

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
    * 공모 목록 조회
    *
    * @Title : selectPcntstList
    * @Description : 공모 목록 조회
    * @param pcntstVo PcntstVo 객체
    * @return List<PcntstVo> 공모 목록
    * @throws Exception 예외
    */
    public List<PcntstVo> selectPcntstList(PcntstVo pcntstVo) throws Exception;
    
    /**
    * 공모 정보 조회
    *
    * @Title : selectPcntstInfo
    * @Description : 공모 정보 조회
    * @param pcntstVo PcntstVo 객체
    * @return PcntstVo 공모 정보
    * @throws Exception 예외
    */
    public PcntstVo selectPcntstInfo(PcntstVo pcntstVo) throws Exception;
    
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
    * 교부 상태 업데이트
    *
    * @Title : updateDelvryStts
    * @Description : 교부 상태 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryStts(DelvryAplyVo delvryAplyVo) throws Exception;
    
    /**
    * 교부 신청 정보 조회
    *
    * @Title : selectDelvryAplyInfo
    * @Description : 교부 신청 정보 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return DelvryAplyVo 교부 신청 정보
    * @throws Exception 예외
    */
    public DelvryAplyVo selectDelvryAplyInfo(DelvryAplyVo delvryAplyVo) throws Exception;
     
    /**
    * 교부 신청 업데이트
    *
    * @Title : updateDelvryAply
    * @Description : 교부 신청 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryAply(DelvryAplyVo delvryAplyVo) throws Exception;
    
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
    * 교부 신청 산출내역 업데이트
    *
    * @Title : updateDelvryAplyComput
    * @Description : 교부 신청 산출내역 업데이트
    * @param delvryAplyComptVo DelvryAplyComputVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryAplyComput(DelvryAplyComputVo delvryAplyComptVo) throws Exception;
    
    /**
    * 교부 신청 산출내역 삭제
    *
    * @Title : deleteDelvryAplyComput
    * @Description : 교부 신청 산출내역 삭제
    * @param delvryAplyComptVo DelvryAplyComputVo 객체
    * @return int delete 로우수
    * @throws Exception 예외
    */
    public int deleteDelvryAplyComput(DelvryAplyComputVo delvryAplyComptVo) throws Exception;
        
    /**
    * 교부 신청 보완요청 목록 조회
    *
    * @Title : selectDelvryAplySplmntList
    * @Description : 교부 신청 보완요청 목록 조회
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return List<DelvryAplySplmntVo> 교부 신청 보완요청 목록
    * @throws Exception 예외
    */
    public List<DelvryAplySplmntVo> selectDelvryAplySplmntList(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception;
    
    /**
    * 교부 신청 보완요청 정보 조회
    *
    * @Title : selectDelvryAplySplmntInfo
    * @Description : 교부 신청 보완요청 정보 조회
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return DelvryAplySplmntVo 교부 신청 보완요청 정보
    * @throws Exception 예외
    */
    public DelvryAplySplmntVo selectDelvryAplySplmntInfo(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception;
    
    /**
    * 교부 신청 보완요청 등록
    *
    * @Title : insertDelvryAplyComput
    * @Description : 교부 신청 보완요청 등록
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    public int insertDelvryAplySplmnt(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception;
       
    /**
    * 교부 신청 보완요청 업데이트
    *
    * @Title : updateDelvryAplyComput
    * @Description : 교부 신청 보완요청 업데이트
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    public int updateDelvryAplySplmnt(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception;
    
    /**
    * 교부 신청 파일 목록 조회
    *
    * @Title : selectDelvryAplyFileList
    * @Description : 교부 신청 파일 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<FileVo> 교부 신청 파일 목록
    * @throws Exception 예외
    */
    public List<FileVo> selectDelvryAplyFileList(DelvryAplyVo delvryAplyVo) throws Exception;
    
}