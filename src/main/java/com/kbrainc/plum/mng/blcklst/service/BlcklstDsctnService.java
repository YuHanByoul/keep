package com.kbrainc.plum.mng.blcklst.service;

import java.util.List;

import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;

/**
* 블랙리스트 내역 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.blcklst.service
* - BlcklstDsctnService.java
* </pre>
*
* @ClassName : BlcklstDsctnService
* @Description : 블랙리스트 내역 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 21.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface BlcklstDsctnService {
    
    /**
    * 블랙리슽 내역 게시글 목록 조회
    *
    * @Title : selectBlcklstDsctnList
    * @Description : 구독자 게시글 목록 조회
    * @param BannerVo 블랙리스트 내역 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<BlcklstDsctnVo> selectBlcklstDsctnList(BlcklstDsctnVo blcklstDsctnVo) throws Exception;
}
