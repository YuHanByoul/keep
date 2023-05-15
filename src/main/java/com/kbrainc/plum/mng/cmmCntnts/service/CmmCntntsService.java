package com.kbrainc.plum.mng.cmmCntnts.service;

import java.util.List;

import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;



/**
* 콘텐츠 품질관리 체크리스트 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.cmmCntnts.service
* - CmmCntntsService.java
* </pre>
**
@ClassName : CmmCntntsService
* @Description : 콘텐츠 품질관리 체크리스트 서비스 인터페이스
* @author : 이한명
* @date : 2023. 4. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CmmCntntsService {
    
    /**
    * 콘텐츠 품질관리 체크리스트 체크ID 조회
    *
    * @Title : selectCmmCntntsQlityChkId
    * @Description : 콘텐츠 품질관리 체크리스트 체크 조회
    * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception 예외
    * @return int
    */
    public Integer selectCmmCntntsQlityChkId(CmmCntntsVo cmmCntnts) throws Exception;
    
    /**
    * 콘텐츠 품질관리 체크리스트 목록 조회
    *
    * @Title : selectCmmCntntsList
    * @Description : 콘텐츠 품질관리 체크리스트 목록 조회
    * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception 예외
    * @return List<CmmCntntsVo>
    */
    public List<CmmCntntsVo> selectCmmCntntsQlityChklstList(CmmCntntsVo cmmCntnts) throws Exception;
    
    /**
    * 콘텐츠 품질관리 체크리스트 등록
    *
    * @Title : insertCmmCntnts
    * @Description : 콘텐츠 품질관리 체크리스트 등록
    * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertCmmCntnts(CmmCntntsVo cmmCntnts) throws Exception;
}
