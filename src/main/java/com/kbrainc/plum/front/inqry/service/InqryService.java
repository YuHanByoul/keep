package com.kbrainc.plum.front.inqry.service;

import com.kbrainc.plum.front.inqry.model.InqryVo;

/**
 * 
 * 1:1문의 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.service
 * - InqryService.java
 * </pre> 
 *
 * @ClassName : InqryService
 * @Description : 1:1문의 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public interface InqryService {
    /**
     * @Title : insertInqry
     * @Description : 1:1문의 등록
     * @param inqryAnswrVO 1:1문의VO 클래스
     * @throws Exception
     * @return int 등록 로우수
     */
    public int insertInqry(InqryVo inqryVO) throws Exception;
}