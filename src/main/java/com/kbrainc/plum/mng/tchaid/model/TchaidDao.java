package com.kbrainc.plum.mng.tchaid.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.cmpnt.model.CmpntVo;
import com.kbrainc.plum.mng.cmpnt.model.CmpntWrhousngVo;

/**
 * 
 * 교구관리 관리 DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaid.model
 * - TchaidDao.java
 * </pre>        
 *
 * @ClassName : TchaidDao
 * @Description : 교구관리 관리 DAO 클래스. 
 * @author : KBRAINC
 * @date : 2023. 02. 02
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface TchaidDao {
    
    /**
     * 구성품 목록 호출 
     *
     * @Title       : selectCmpntList 
     * @Description : 구성품 목록 호출
     * @param CmpntVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    public List<CmpntVo> selectCmpntList(CmpntVo cmpntVo) throws Exception;
    /**
     * 구성품 입고 내역 호출 
     *
     * @Title       : selectCmpntWrhousngList 
     * @Description : 구성품 목록 호출
     * @param CmpntWrhousngVo 객체
     * @return List<CmpntWrhousngVo> 목록
     * @throws Exception 예외
     */
    public List<CmpntWrhousngVo> selectCmpntWrhousngList(CmpntWrhousngVo cmpntWrhousngVo) throws Exception;
    
    /**
     * 구성품 등록 
     *
     * @Title       : insertCmpnt 
     * @Description : 구성품 등록 
     * @param CmpntVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    public int insertCmpnt(CmpntVo cmpntVo) throws Exception;
    
    /**
     * 구성품 입고 처리 등록 
     *
     * @Title       : insertCmpntWrhousng 
     * @Description : 구성품 입고 처리 등록
     * @param CmpntWrhousngVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    public int insertCmpntWrhousng(CmpntWrhousngVo cmpntVo) throws Exception;
    
    /**
     * 구성품 수정 
     *
     * @Title       : updateCmpnt 
     * @Description : 구성품 수정
     * @param CmpntVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    public int updateCmpnt(CmpntVo cmpntVo) throws Exception;
    
    
    

}