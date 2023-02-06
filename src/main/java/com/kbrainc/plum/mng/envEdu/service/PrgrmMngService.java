/**
 * 
 */
package com.kbrainc.plum.mng.envEdu.service;

import java.util.List;

import com.kbrainc.plum.mng.envEdu.model.PrgrmMngVo;

/**
* 우수환경교육 프로그램 관리 서비스 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.envEdu.service
* - PrgrmMngService.java
* </pre> 
*
* @ClassName : PrgrmMngService
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface PrgrmMngService {

    /**
    * 우수환경교육 프로그램 관리 목록 조회. 
    *
    * @Title : selectPrgrmMngList
    * @Description : TODO
    * @param prgrmMngVo
    * @return
    * @throws Exception
    * @return List<PrgrmMngVo>
     */
    List<PrgrmMngVo> selectPrgrmMngList(PrgrmMngVo prgrmMngVo) throws Exception;
    
    /**
    * 우수환경교육 프로그램 관리 등록. 
    *
    * @Title : insertPrgrmMng
    * @Description : TODO
    * @param prgrmMngVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertPrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception;
    
    /**
    * 우수환경교육 프로그램 관리 수정. 
    *
    * @Title : updatePrgrmMng
    * @Description : TODO
    * @param prgrmMngVo
    * @return
    * @throws Exception
    * @return int
     */
    int updatePrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception;
    
    /**
    * 우수환경교육 프로그램 관리 조회수 증가. 
    *
    * @Title : updateHits
    * @Description : TODO
    * @param prgrmMngVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateHits(PrgrmMngVo prgrmMngVo) throws Exception;
    
    /**
    * 우수환경교육 프로그램 관리 삭제. 
    *
    * @Title : deletePrgrmMng
    * @Description : TODO
    * @param prgrmids
    * @return
    * @throws Exception
    * @return int
     */
    int deletePrgrmMng(String[] prgrmids) throws Exception;
}
