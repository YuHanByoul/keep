package com.kbrainc.plum.mng.inqry.model;

import java.util.List;

import com.kbrainc.plum.rte.model.UserVo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.cmm.file.model.FileVo;

/**
 * 
 * 1:1문의 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.inqry.model
 * - InqryDao.java
 * </pre> 
 *
 * @ClassName : InqryDao
 * @Description : 1:1문의 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface InqryDao {

    /**
     * @Title : selectInqryList
     * @Description : 1:1문의 목록을 가져온다.
     * @param inqryVO
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
     * @Title : selectAttachFileList
     * @Description :첨부파일 리스트 호출
     * @param FileVo
     * @throws Exception
     * @return FileVo
     */
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;

    public int deleteInqryInfo(@Param("deleteInqryIds") String[] deleteInqryIds, @Param("userVo") UserVo userVo) throws Exception;

    public int updateInqrySttsCd(InqryAnswrVo inqryAnswrVo) throws Exception;

    public List<TelInqryVo> selectTelInqryList(TelInqryVo telInqryVo) throws Exception;
    public TelInqryVo selectTelInqryInfo(TelInqryVo telInqryVo) throws Exception;
    public int insertTelInqry(TelInqryVo telInqryVo) throws Exception;
    public int updateTelInqry(TelInqryVo telInqryVo) throws Exception;
    public List<PopupMemberVo> selectMemberList(TelInqryVo telInqryVo) throws Exception;
    public List<ManagerVo> selectInqryManagerList(InqryVo inqryVo) throws Exception;
    public List<ManagerVo> selectTelInqryManagerList(TelInqryVo telInqryVo)throws Exception;
    public int insertInqryManager(InqryAnswrVo inqryAnswrVo) throws Exception;
    public int insertTelInqryManager(TelInqryVo telInqryVo) throws Exception;
    public int deleteInqryManager(InqryAnswrVo inqryAnswrVo) throws Exception;
    public int deleteTelInqryManager(TelInqryVo telInqryVo) throws Exception;



}