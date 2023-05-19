package com.kbrainc.plum.mng.mobileAsgsysSrng.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

/***
 * 지정제심사VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.mobileAsgsysSrng.model
 * - MobileAsgsysSrngVo.java
 * </pre>
 *
 * @ClassName : MobileAsgsysSrngVo
 * @Description : 지정제심사VO 클래스
 * @author : kbrain
 * @date : 2022. 12. 6.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class MobileAsgsysSrngVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 프로그램아이디 */
    private Integer prgrmid;
    /** 체크리스트아이디 */
    private Integer chklstid;
    /** 지원단아이디 */
    private Integer sprtgrpid;
    /** 제출아이디 */
    private Integer sbmsnid;
    /** 신청자 제출아이디*/
    private Integer aplcntSbmsnid;
    /** 프로그램명 */
    private String prgrmNm;
    /** 심사_상태 */
    private String srgnStts;
    /** 기관명 */
    private String instNm;
    /** 방문일시 or 현장점검 지정일 */
    @NotEmpty(message = "방문일시를 입력해주십시오.")
    private String vstDt;
    /** 배정일(등록일) */
    private String regDt;
    /** 프로그램 교육사진 */
    private String eduPhotoFilegrpid;
    /** 숙박여부 */
    private String styYn;
    /** 운영형태 */
    private String operFrm;
    /** 안전관리 사전인증여부*/
    private String bfrCertYn;
    /** 파일그룹아이디*/
    private String filegrpid;
    /** 사전인증 파일그룹아이디*/
    private String bfrCertFilegrpid;
    
    /** 파일정보 */
    private ArrayList<FileVo> eduPhotoFileInfo;
    /** 파일정보 */
    private ArrayList<FileVo> fileInfo;
    /** 파일정보 */
    private ArrayList<FileVo> bfrCertFilegrpInfo;
    
    /* 체크리스트 */
    /** 상위 구분 코드명 */
    private String lv1key;
    /** 상위 구분 코드 */
    private String lv2key;
    /** 구분 코드명 */
    private String cn;
    /** 문항 id*/
    private Integer qitemid;
    /** 문항 수*/
    private Integer cnt;
    /** 구분 코드 */
    private Integer treeOrd;
    /** 문항 내용 */
    private Integer ordr;
    /** 배점 */
    private Integer altm;
    /** 답변 점수 */
    private Integer scr;
    
    private Integer aplcntScr;
    
    
    /** 검색영역 */
    private String searchYear;
    private String searchSrngStts;
    
    //등록
    /** TB_ASS_CHKLST_ANS(ASS_체크리스트_답변) */
    private List<MobileAsgsysSrngVo> chkLst;            //체크리스트
    
    /** TB_ASS_CHKLST_SBMSN(ASS_체크리스트_제출) */
    private String sbmsnSeCd;                           //제출 구분 코드
    private String sbmsnSttsCd;                         //제출 상태 코드
    private String userIp;                              //사용자 아이피
    private String totScr;                              //사용자 아이피
    private String chklstRsltCn;                        //체크리스트 결과 내용
    
    /** TB_ASS_CHKLST_SE_ORDR_ANS(ASS_체크리스트_구분_순서_답변) */
    private List<MobileAsgsysSrngVo> chklstSeOrdrAns;   //체크리스트
    private String seCd;                                //구분코드
    
    /** TB_ASS_SPRTGRP_SRNG(ASS_지원단_심사) */
    private String srngOpnn;                            //심사 의견
    private String srngSttsCd;                          //심사 상태 코드
}
