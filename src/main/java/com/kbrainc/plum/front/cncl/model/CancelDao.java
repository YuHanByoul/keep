/**
 * 
 */
package com.kbrainc.plum.front.cncl.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 사업포기신청 DAO
*
* <pre>
* com.kbrainc.plum.front.cncl.model
* - CancelDao.java
* </pre> 
*
* @ClassName : CancelDao
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface CancelDao {

    /**
    * 사업포기신청 목록 조회. 
    *
    * @Title : selectCancelList
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return List<CancelVo>
     */
    List<CancelVo> selectCancelList(CancelVo cancelVo) throws Exception;
    
    /**
    * 사업분야 목록 조회. 
    *
    * @Title : selectFldList
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return List<CancelVo>
     */
    List<CancelVo> selectFldList(CancelVo cancelVo) throws Exception;
    
    /**
    * 공모신청 기본정보 조회 
    *
    * @Title : selectBaseInfo
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return List<CancelVo>
     */
    List<CancelVo> selectBaseInfo(CancelVo cancelVo) throws Exception;
    
    /**
    * 사업포기신청 
    *
    * @Title : insertCancel
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertCancel(CancelVo cancelVo) throws Exception;
    
    /**
    * 사업포기신청 수정 
    *
    * @Title : updateCancel
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateCancel(CancelVo cancelVo) throws Exception;
    
    /**
    * 공모신청서 상태값 변경 
    *
    * @Title : updateAplyCancel
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateAplyCancel(CancelVo cancelVo) throws Exception;
    
    /**
    * 송금은행정보 수정. 
    *
    * @Title : updateDelvryInfo
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateDelvryInfo(CancelVo cancelVo) throws Exception;
}
