package com.kbrainc.plum.mng.clclnMng.clclnDsctn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;
import com.kbrainc.plum.mng.clclnMng.clclnDsctn.model.ClclnDsctnVo;
import com.kbrainc.plum.rte.model.UserVo;



/**
* 체험환경교육 지원사업 -> 정산관리 -> 정산내역관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.clclnMng.clclnDsctn.service
* - ClclnDsctnService.java
* </pre>
**
@ClassName : ClclnDsctnService
* @Description : 체험환경교육 지원사업 -> 정산관리 -> 정산내역관리 서비스 인터페이스
* @author : 이한명
* @date : 2023. 2. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ClclnDsctnService {
    
    /**
    * 정산내역관리 목록 조회
    *
    * @Title : selectClclnDsctnList
    * @Description : 정산내역관리 게시글 목록 조회
    * @param clclnDsctn 정산내역관리 객체
    * @throws Exception 예외
    * @return List<ClclnDsctnVo>
    */
    public List<ClclnDsctnVo> selectClclnDsctnList(ClclnDsctnVo clclnDsctn) throws Exception;

    /**
    * 정산내역관리 상세목록 조회
    **
    * @Title : selectClclnDsctnDetailList
    * @Description : 정산내역관리 상세목록 조회
    * @param clclnDsctnVo
    * @throws Exception 예외
    * @return List<ClclnDsctnVo>
    */
    public List<ClclnDsctnVo> selectClclnDsctnDetailList(ClclnDsctnVo clclnDsctnVo) throws Exception;

    /**
     * 정산내역관리 상세정보 조회
     **
     * @Title : selectClclnDsctnDetailInfo
     * @Description : 정산내역관리 상세정보 조회
     * @param clclnDsctnVo
     * @throws Exception 예외
     * @return ClclnDsctnVo
     */
    public ClclnDsctnVo selectClclnDsctnDetailInfo(ClclnDsctnVo clclnDsctnVo) throws Exception;

    /**
     * 정산내역관리 상세정보 집행내역 개요서 리스트 조회
     **
     * @Title : selectClclnDsctnDetailOutlList
     * @Description : 정산내역관리 상세정보 집행내역 개요서 리스트 조회
     * @param clclnDsctnVo
     * @throws Exception 예외
     * @return ClclnDsctnVo
     */
    public List<ClclnDsctnVo> selectClclnDsctnDetailOutlList(ClclnDsctnVo clclnDsctnVo) throws Exception;

    /**
     * 정산내역관리 상세정보 집행상세내역 개요서 리스트 조회
     **
     * @Title : selectClclnDsctnDetailOutlDtlList
     * @Description : 정산내역관리 상세정보 집행상세내역 개요서 리스트 조회
     * @param clclnDsctnVo
     * @throws Exception 예외
     * @return ClclnDsctnVo
     */
    public List<ClclnDsctnVo> selectClclnDsctnDetailOutlDtlList(ClclnDsctnVo clclnDsctnVo) throws Exception;
    
    /**
    * 정산내역관리 상세목록 제출상태 수정
    **
    @Title : updateClclnDsctnComplete
    * @Description : 정산내역관리 상세목록 제출상태 수정
    * @param completeAplyIds
    * @param userVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateClclnDsctnComplete(String[] completeAplyIds, UserVo userVo) throws Exception;
    
    
    public void clclnDsctnDetailExcelDownList(ClclnDsctnVo clclnDsctnVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    public void clclnDsctnOutlListExcelDownList(ClclnDsctnVo clclnDsctnVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    public void clclnDsctnOutlDtlListExcelDownList(ClclnDsctnVo clclnDsctnVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
    * 보완요청 목록 조회. 
    *
    * @Title : selectExcclcSplmntList
    * @Description : TODO
    * @param excclcSplmntVo
    * @return
    * @throws Exception
    * @return List<SupplementVo>
     */
    public List<SupplementVo> selectExcclcSplmntList(SupplementVo excclcSplmntVo) throws Exception;
    
    /**
    * 보완요청. 
    *
    * @Title : insertExcclcSplmnt
    * @Description : TODO
    * @param excclcSplmntVo
    * @return
    * @throws Exception
    * @return int
     */
    public int insertExcclcSplmnt(SupplementVo excclcSplmntVo) throws Exception;
    
    /**
     * 보완요청 수정. 
     *
     * @Title : updateExcclcSplmnt
     * @Description : TODO
     * @param excclcSplmntVo
     * @return
     * @throws Exception
     * @return int
      */
    public int updateExcclcSplmnt(SupplementVo excclcSplmntVo) throws Exception;
    
}
