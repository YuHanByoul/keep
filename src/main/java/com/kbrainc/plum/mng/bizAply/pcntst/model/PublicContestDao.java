/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

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
    * 담당자 목록 조회. 
    *
    * @Title : selectMngList
    * @Description : TODO
    * @param publicContestMngGrpVo
    * @return
    * @throws Exception
    * @return List<PublicContestMngGrpVo>
     */
    List<PublicContestMngGrpVo> selectMngList(PublicContestMngGrpVo publicContestMngGrpVo) throws Exception;
    
    /**
    * 심사표 목록 조회. 
    *
    * @Title : selectEvalSheetList
    * @Description : TODO
    * @param PublicContestVo
    * @return
    * @throws Exception
    * @return List<EgovMap>
     */
    List<EgovMap> selectEvalSheetList(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 교부 정보 조회. 
    *
    * @Title : selectDelevery
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return List<PublicContestVo>
     */
    List<PublicContestVo> selectDelevery(PublicContestVo publicContestVo) throws Exception;
    
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
    * 공모관리 담당자 등록. 
    *
    * @Title : insertMng
    * @Description : TODO
    * @param publicContestMngGrpVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertMng(PublicContestMngGrpVo publicContestMngGrpVo) throws Exception;
    
    /**
    * 공모관리 담당자 삭제. 
    *
    * @Title : deleteMng
    * @Description : TODO
    * @param publicContestMngGrpVo
    * @return
    * @throws Exception
    * @return int
     */
    int deleteMng(PublicContestMngGrpVo publicContestMngGrpVo) throws Exception;
    
    /**
    * 교부 정보 등록. 
    *
    * @Title : insertDelevery
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertDelevery(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 교부 정보 삭제. 
    *
    * @Title : deleteDelevery
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return int
     */
    int deleteDelevery(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 교부 정보 수정. 
    *
    * @Title : updateDelevery
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateDelevery(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 심사배분율 조회. 
    *
    * @Title : selectDelevery
    * @Description : TODO
    * @param gradeVo
    * @return
    * @throws Exception
    * @return List<GradeVo>
     */
    List<GradeVo> selectGradeList(GradeVo gradeVo) throws Exception;
    
    /**
    * 심사배분율 등록. 
    *
    * @Title : insertGrade
    * @Description : TODO
    * @param gradeVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertGrade(GradeVo gradeVo) throws Exception;
    
    /**
    * 심사배분율 삭제. 
    *
    * @Title : deleteGrade
    * @Description : TODO
    * @param gradeVo
    * @return
    * @throws Exception
    * @return int
     */
    int deleteGrade(GradeVo gradeVo) throws Exception;
    
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
    * 공모관리 전문가 그룹 리스트 조회. 
    *
    * @Title : selectMngGrpList
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return List<PublicContestMngGrpVo>
     */
    List<PublicContestMngGrpVo> selectMngGrpList(PublicContestMngGrpVo publicContestVo) throws Exception;
    
    /**
     * 
    * 전문가 조회. 
    *
    * @Title : selectCnsltngExprtList
    * @Description : TODO
    * @param cnsltngExprtVo
    * @return
    * @throws Exception
    * @return List<PublicContestMngGrpVo>
     */
    List<PublicContestMngGrpVo> selectCnsltngExprtList(PublicContestMngGrpVo publicContestVo) throws Exception;
}
