/**
 * 
 */
package com.kbrainc.plum.front.cncl.service;

import java.util.List;

import com.kbrainc.plum.front.cncl.model.CancelVo;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 사업포기신청 서비스
*
* <pre>
* com.kbrainc.plum.front.cncl.service
* - CancelService.java
* </pre> 
*
* @ClassName : CancelService
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CancelService {

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
    * 사업포기신청 저장
    *
    * @Title : saveCancel
    * @Description : TODO
    * @param cancelVo
    * @return
    * @throws Exception
    * @return int
     */
    int saveCancel(CancelVo cancelVo) throws Exception;
}
