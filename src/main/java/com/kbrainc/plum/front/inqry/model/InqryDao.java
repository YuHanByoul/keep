package com.kbrainc.plum.front.inqry.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 1:1문의 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.model
 * - InqryDao.java
 * </pre> 
 *
 * @ClassName : InqryDao
 * @Description : 1:1문의 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 11. 18.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper("front.inqryDao")
public interface InqryDao {

    /**
     * @Title : insertInqry
     * @Description : 1:1문의 등록
     * @param inqryAnswrVO 1:1문의VO 클래스
     * @throws Exception
     * @return int 등록 로우수
     */
    public int insertInqry(InqryVo inqryVO) throws Exception;


}