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

    List<PrgrmMngVo> selectPrgrmMngList(PrgrmMngVo prgrmMngVo) throws Exception;
    
    int insertPrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception;
    
    int updatePrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception;
}
