package com.kbrainc.plum.front.eduInst.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.EduExprtVo")
public class EduExprtVo extends ParentRequestVo {
	/** 로그인사용자정보 */
    private UserVo user;

    private Integer aplyid;           /** 신청아이디 */
    private Integer exprtid;          /** 전문가아이디 */
    private String seCd;              /** 구분 코드 */
    private String nm;                /** 이름 */
    private String brdt;              /** 생년월일 */
    private String chrgtask;          /** 담당업무 */
    private String crtfct;            /** 자격증 */
    private String hdofBgngDe;        /** 재직 시작 일자 */
    private String hdofEndDe;         /** 재직 종료 일자 */
    private String career;            /** 경력 */
    private Integer atchFilegrpid;    /** 첨부 파일그룹아이디 */
    private Date mdfcnDt;             /** 수정 일시 */
    private Integer mdfrid;           /**  수정자아이디 */
    private Date regDt;               /**  등록 일시 */

	private List<FileVo> fileList;


}
