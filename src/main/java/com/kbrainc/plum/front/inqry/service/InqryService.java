package com.kbrainc.plum.front.inqry.service;

import com.kbrainc.plum.front.inqry.model.InqryAnsVo;
import com.kbrainc.plum.front.inqry.model.InqryVo;

import java.util.List;

/**
 * 1:1문의 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.service
 * - InqryService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryService
 * @Description : 1:1문의 서비스 인터페이스
 * @date : 2023. 02. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

public interface InqryService {
    public List<InqryVo> selectInqryList(InqryVo inqryVo) throws Exception;

    public InqryVo selectInqry(InqryVo inqryVo) throws Exception;

    public InqryAnsVo selectInqryAns(InqryVo inqryVo) throws Exception;

    public int insertInqry(InqryVo inqryVo) throws Exception;

    public int updateInqry(InqryVo inqryVo) throws Exception;

    public int deleteInqry(InqryVo inqryVo) throws Exception;

    public int cancelInqry(InqryVo inqryVo) throws Exception;

}
