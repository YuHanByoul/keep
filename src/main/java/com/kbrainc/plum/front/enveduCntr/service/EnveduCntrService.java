package com.kbrainc.plum.front.enveduCntr.service;

import com.kbrainc.plum.front.enveduCntr.model.EnveduCntrVO;

import java.util.List;

/**
 * 지역 환경교육센처 Service
 *
 * <pre>
 * com.kbrainc.plum.front.enveduCntr.service
 * - EnveduCntrService.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnveduCntrService
 * @Description : 지역 환경교육센처 Service
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface EnveduCntrService {

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectEnveduCntrList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param enveduCntrVO
     * @return list
     */
    List<EnveduCntrVO> selectEnveduCntrList(EnveduCntrVO enveduCntrVO);
    
    /**
     * 민간 환경교육단체 목록 조회
     * Title : selectPrivateEnvEduCntrList
     * Description : 민간 환경교육단체 목록 조회
     *
     * @param 
     * @return list
     */
    List<EnveduCntrVO> selectPrvtEnvEduCntrList();
}
