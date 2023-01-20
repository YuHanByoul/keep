/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 공모관리 DAO 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.sprtBizPcntst.model
* - PublicContestDao.java
* </pre> 
*
* @ClassName : PublicContestDao
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface PublicContestDao {

    /**
    * 공모관리 리스트 조회. 
    *
    * @Title : selectContestList
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return List<PublicContestVo>
     */
    List<PublicContestVo> selectContestList(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 공모관리 상세조회. 
    *
    * @Title : detailContest
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return PublicContestVo
     */
    PublicContestVo detailContest(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 공모관리 등록. 
    *
    * @Title : insertContest
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertContest(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 공모관리 수정. 
    *
    * @Title : updateContest
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateContest(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 공모관리 목록 엑셀 다운로드. 
    *
    * @Title : publicContestListExcelDownload
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return void
     */
    List<PublicContestVo> publicContestListExcelDownload(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 공모관리 삭제. 
    *
    * @Title : deleteContest
    * @Description : TODO
    * @param deleteContestIds
    * @return
    * @throws Exception
    * @return int
     */
    int deleteContest(@Param("deleteContestIds") Integer[] deleteContestIds) throws Exception;
}
