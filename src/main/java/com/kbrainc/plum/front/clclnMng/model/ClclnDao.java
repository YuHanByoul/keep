package com.kbrainc.plum.front.clclnMng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;

/**
* 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산관리 DAO 인터페이스 
*
* <pre>
* com.kbrainc.plum.front.clclnMng.model
* - ClclnDao.java
* </pre> 
*
* @ClassName : ClclnDao
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ClclnDao {
    
    /**
    * 정산관리 목록 조회. 
    *
    * @Title : selectClclnList
    * @Description : TODO
    * @param clclnVo
    * @return
    * @throws Exception
    * @return List<ClclnVo>
     */
    List<ClclnVo> selectClclnList(ClclnVo clclnVo) throws Exception;
    
    /**
    * 정산내역관리 탭 집행 목록 조회 
    *
    * @Title : selectClclnDsctnList
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return List<ClclnDsctnVo>
     */
    List<ClclnDsctnVo> selectClclnDsctnList(ClclnVo clclnDsctnVo) throws Exception;
    
    /**
    * 정산내역관리 탭 집행내역개요서 조회. 
    *
    * @Title : selectClclnDsctnOutlList
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return List<ClclnDsctnVo>
     */
    List<ClclnDsctnVo> selectClclnDsctnOutlList(ClclnVo clclnDsctnVo) throws Exception;
    
    /**
    * 잔액/미자관리 탭 조회. 
    *
    * @Title : selectBlncIntList
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return List<ClclnDsctnVo>
     */
    List<ClclnDsctnVo> selectBlncIntList(ClclnVo clclnDsctnVo) throws Exception;

    /**
    * 집행내역 등록. 
    *
    * @Title : insertClclnDsctn
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertClclnDsctn(ClclnDsctnVo clclnDsctnVo) throws Exception;
    
    /**
    * 집행내역 수정. 
    *
    * @Title : updateClclnDsctn
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateClclnDsctn(ClclnDsctnVo clclnDsctnVo) throws Exception;
    
    /**
    * 집행내역 삭제. 
    *
    * @Title : deleteClclnDsctn
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return int
     */
    int deleteClclnDsctn(ClclnDsctnVo clclnDsctnVo) throws Exception;
    
    /**
    * 보완요청 상세조회 
    *
    * @Title : selectSplmntInfo
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return SupplementVo
     */
    SupplementVo selectSplmntInfo(SupplementVo supplementVo) throws Exception;
    
    /**
    * 보완요청 답변 수정. 
    *
    * @Title : updateSplmnt
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateSplmnt(SupplementVo supplementVo) throws Exception;

    /**
    * 정산보고관리 제출 / 보완완료 상태 업데이트. 
    *
    * @Title : updateStts
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateStts(SupplementVo supplementVo) throws Exception;
}
