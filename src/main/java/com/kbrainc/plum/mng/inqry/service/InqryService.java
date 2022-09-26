package com.kbrainc.plum.mng.inqry.service;

import java.util.List;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.inqry.model.InqryAnswrVo;
import com.kbrainc.plum.mng.inqry.model.InqryVo;

/**
 * 
 * 1:1문의 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.inqry.service
 * - InqryService.java
 * </pre> 
 *
 * @ClassName : InqryService
 * @Description : 1:1문의 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface InqryService {

    /**
     * @Title : selectInqryList
     * @Description : 1:1문의 리스트 가져옴
     * @param inqryVO 1:1문의VO 클래스
     * @throws Exception
     * @return List<InqryVo> 1:1문의 목록
     */
    public List<InqryVo> selectInqryList(InqryVo inqryVO) throws Exception;

    /**
     * @Title : selectInqryInfo
     * @Description : 1:1문의 정보를 가져온다.
     * @param inqryVO 1:1문의VO 클래스
     * @throws Exception
     * @return InqryVo 1:1문의 정보
     */
    public InqryVo selectInqryInfo(InqryVo inqryVO) throws Exception;

    /**
     * @Title : selectInqryAnswrInfo
     * @Description : 1:1문의답변 정보를 가져온다.
     * @param inqryVO 1:1문의답변VO 클래스
     * @throws Exception
     * @return InqryAnswrVo 1:1문의답변 정보
     */
    public InqryAnswrVo selectInqryAnswrInfo(InqryVo inqryVO) throws Exception;

    /**
     * @Title : insertInqryAnswr
     * @Description : 1:1문의답변 등록
     * @param inqryAnswrVO 1:1문의답변VO 클래스
     * @throws Exception
     * @return int 등록 로우수
     */
    public int insertInqryAnswr(InqryAnswrVo inqryAnswrVO) throws Exception;

    /**
     * @Title : updateInqryAnswr
     * @Description : 1:1문의답변 수정
     * @param inqryAnswrVO 1:1문의답변VO 클래스
     * @throws Exception
     * @return int 수정 로우수
     */
    public int updateInqryAnswr(InqryAnswrVo inqryAnswrVO) throws Exception;

    /**
     * @Title : deleteInqryAnswr
     * @Description : 1:1문의답변 삭제
     * @param inqryAnswrVO 1:1문의답변VO 클래스
     * @throws Exception
     * @return int 삭제 로우수
     */
    public int deleteInqryAnswr(InqryAnswrVo inqryAnswrVO) throws Exception;
    
    /**
     * @Title : selectAttachFileList
     * @Description :첨부파일 리스트 호출
     * @param FileVo
     * @throws Exception
     * @return FileVo
     */
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;

}