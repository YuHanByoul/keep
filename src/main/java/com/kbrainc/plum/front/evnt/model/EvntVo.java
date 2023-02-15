package com.kbrainc.plum.front.evnt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.cntst.model.CntstVO;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import java.sql.Date;
import java.util.List;

/**
 * 참여신청관리 > 이벤트Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.evt.model
 * - EvntVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EvntVo
 * @Description : 참여신청관리 > 이벤트Vo 클래스
 * @date : 2023. 01. 26.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.EvntVo")
public class EvntVo extends ParentRequestVo {
    /**
     * 로그인 사용자 정보
     */
    private UserVo user;

    /**
     * 이벤트 아이디 
     */
    private Integer evntid;

    /**
     * 제목
     */
    @NotEmpty
    @Size(max = 200, message = "제목은 200자를 넘을 수 없습니다.")
    private String ttl;

    /**
     * 내용
     */
    @NotEmpty
    private String cn;

    /**
     * 접수시작일
     */
    @NotEmpty
    private String bgngDe;

    /**
     * 접수종료일
     */
    @NotEmpty
    private String endDe;

    /**
     * 당첨자발표일
     */
    @NotEmpty
    private String prsntnDe;

    /**
     * 신청링크
     */
    private String aplyUrl;

    /**
     * 조회수
     */
    private Integer hits;

    /**
     * 신규게시물
     */
    private String newEvnt;

    /**
     * 상태
     */
    private String stts;

    /**
     * 삭제여부
     */
    private String delYn;

    /**
     * 수정일
     */
    private Date mdfcnDt;

    /**
     * 등록일
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    /**
     * 수정자 아이디
     */
    private Integer mdfrid;

    /**
     * 등록자 아이디
     */
    private Integer rgtrid;

    /**
     * 등록자 이름
     */
    private String nm;

    /**
     * 등록자 아이디
     */
    private String acnt;

    /**
     * 등록기관 이름
     */
    private String instNm;

    /**
     * 파일VO 리스트
     */
    private List<FileVo> fileVoList;
    
    private String nextEvntid;
    private String nextEvntTtl;
    private String beforeEvntid;
    private String beforeEvntTtl;
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    private String ext;

}
