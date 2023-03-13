package com.kbrainc.plum.front.cntstAplyHist.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import org.apache.ibatis.type.Alias;

import java.sql.Date;
import java.util.List;

/**
* 공모전 참여 이력 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.cntstAplyHist.model
* - CntstAplyHistVo.java
* </pre>
*
* @ClassName : CntstAplyHistVo
* @Description : 공모전 참여 이력 Vo 클래스
* @author : JD
* @date : 2023. 2. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.CntstAplyHistVo")
public class CntstAplyHistVo extends ParentRequestVo {

    private UserVo user;

    /* List */
    /** 신청아이디 */
    private Integer aplyid;
    /** 분류명 */
    private String clsfCdNm;
    /** 제목(공모전 명)*/
    private String ttl;
    /** 등록기관 명*/
    private String instNm;
    /** 신청일 */
    private Date aplyDt;
    /** 공모전 등록일 */
    private Date regDt;
    
    /** 검색 영역 */
    private String searchTtl;
    
    /* Detail */
    /** 신청_시작_일자*/
    private String aplyBgngDt;
    /** 신청_종료_일자*/
    private String aplyEndDt;
    /** 발표_일자*/
    private String prsntnDt;
    /** 신청_구분_코드 */
    private String aplySeCd;
    /** 공모_분야_코드 */
    private String pcntstFldCd;
    /** 신청_경로_코드 */
    private String aplyPathCd;
    /** 신청_구분_코드명 */
    private String aplySeCdNm;
    /** 공모_분야_코드명 */
    private String pcntstFldCdNm;
    /** 신청_경로_코드명 */
    private String aplyPathCdNm;
    /** 대표자_이름 */
    private String rprsvNm;
    /** 생년월일 */
    private String brdt;
    /** 대표자_휴대폰번호 */
    private String rprsvMoblphon;
    /** 이메일 */
    private String eml;
    /** 주소 */
    private String addr;
    /** 주소_상세 */
    private String addrDtl;
    /** 작품_제목 */
    private String prdctTtl;
    /** 작품_설명 */
    private String prdctExpln;
    /** 작품_파일그룹아이디 */
    private Integer prdctFilegrpid;
    /** 안내*/
    private String gdnc;
    /** 약관*/
    private String trms;
    
    /* Update */
    /** 공모 분야*/
    private String fldCd;
    private String fldMapngNm;
    
    /* 환경방학 일기장 프로젝트 */
    /* Detail */
    private Integer aplySchlid;
    /** 학교_이름 */
    private String schlNm;
    /** 교사_이름 */
    private String tcherNm;
    /** 교사_성별 */
    private String tcherGndr;
    /** 전화번호 */
    private String telno;
    /** 학생_남자 */
    private String stdntMale;
    /** 학생 여자 */
    private String stdntFemale;
    
    /* cmm */
    /** 첨부파일 관련 */
    private List<FileVo> fileList;
    
}
