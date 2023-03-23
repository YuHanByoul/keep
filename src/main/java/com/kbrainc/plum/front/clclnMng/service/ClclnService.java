package com.kbrainc.plum.front.clclnMng.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.front.clclnMng.model.ClclnDsctnVo;
import com.kbrainc.plum.front.clclnMng.model.ClclnVo;



/**
* 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산관리 서비스 
*
* <pre>
* com.kbrainc.plum.front.clclnMng.service
* - ClclnService.java
* </pre> 
*
* @ClassName : ClclnService
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface ClclnService {
    
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
    * 정산내역관리 탭 집행 목록 조회. 
    *
    * @Title : selectClclnDsctnList
    * @Description : TODO
    * @param clclnDsctn
    * @return
    * @throws Exception
    * @return List<ClclnDsctnVo>
     */
    List<ClclnDsctnVo> selectClclnDsctnList(ClclnVo clclnDsctn) throws Exception;

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
    * 집행내역 저장. 
    *
    * @Title : saveClclnDsctn
    * @Description : TODO
    * @param clclnDsctnVo
    * @return
    * @throws Exception
    * @return int
     */
    int saveClclnDsctn(ClclnDsctnVo clclnDsctnVo) throws Exception;
    
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
    * 집행내역개요서 엑셀 다운로드 
    *
    * @Title : clclnDsctnOutlExcelDownload
    * @Description : TODO
    * @param clclnVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void clclnDsctnOutlExcelDownload(ClclnVo clclnVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
}
