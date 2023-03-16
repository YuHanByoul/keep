package com.kbrainc.plum.front.inqry.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 1:1문의 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.model
 * - InqryDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryDao
 * @Description : 1:1문의 Dao 클래스
 * @date : 2023. 02. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.inqryDao")
public interface InqryDao {
    /**
     * 1:1문의 목록 조회
     *
     * @param inqryVo
     * @return list
     * @throws Exception
     * @Title : selectInqryList
     * @Description : 1:1문의 목록 조회
     */
    public List<InqryVo> selectInqryList(InqryVo inqryVo) throws Exception;

    /**
     * 마이페이지 > 1:1문의 목록 조회
     *
     * @param inqryVo
     * @return list
     * @throws Exception
     * @Title : selectMypageInqryList
     * @Description : 마이페이지 > 1:1문의 목록 조회
     */
    public List<InqryVo> selectMypageInqryList(InqryVo inqryVo) throws Exception;

    /**
     * 1:1문의 상세 조회
     *
     * @param inqryVo
     * @return inqry vo
     * @throws Exception
     * @Title : selectInqry
     * @Description : 1:1문의 상세 조회
     */
    public InqryVo selectInqry(InqryVo inqryVo) throws Exception;

    /**
     * 1:1문의 답변 조회
     *
     * @param inqryVo
     * @return inqry ans vo
     * @throws Exception
     * @Title : selectInqryAns
     * @Description : 1:1문의 답변 조회
     */
    public InqryAnsVo selectInqryAns(InqryVo inqryVo) throws Exception;

    /**
     * 1:1문의 생성
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : insertInqry
     * @Description : 1:1문의 생성
     */
    public int insertInqry(InqryVo inqryVo) throws Exception;

    /**
     * 1:1문의 수정
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : updateInqry
     * @Description : 1:1문의 수정
     */
    public int updateInqry(InqryVo inqryVo) throws Exception;

    /**
     * 1:1문의 삭제
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : deleteInqry
     * @Description : 1:1문의 삭제
     */
    public int deleteInqry(InqryVo inqryVo) throws Exception;

    /**
     * 1:1문의 취소
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : cancelInqry
     * @Description : 1:1문의 취소
     */
    public int cancelInqry(InqryVo inqryVo) throws Exception;
}
