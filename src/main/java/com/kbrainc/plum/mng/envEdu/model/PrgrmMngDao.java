/**
 * 
 */
package com.kbrainc.plum.mng.envEdu.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 우수환경교육 프로그램 관리 DAO 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.envEdu.model
* - PrgrmMngDao.java
* </pre> 
*
* @ClassName : PrgrmMngDao
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface PrgrmMngDao {

    List<PrgrmMngVo> selectPrgrmMngList(PrgrmMngVo prgrmMngVo) throws Exception;
    
    int insertPrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception;
    
    int updatePrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception;
}
