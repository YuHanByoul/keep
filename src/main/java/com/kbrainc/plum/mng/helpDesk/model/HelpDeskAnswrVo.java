package com.kbrainc.plum.mng.helpDesk.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;


/**
 * 탄소중립헬프데스크 답변Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.helpDesk.model
 * - HelpDeskAnswrVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpDeskAnswrVo
 * @Description : 탄소중립헬프데스크 답변Vo 클래스
 * @date : 2022. 12. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class HelpDeskAnswrVo extends ParentRequestVo {
    /** 로그인사용자 정보 */
    private UserVo user;
    /** 문의 일련번호 */
    private Integer inqryid;
    /** 답변 일련번호 */
    private Integer ansid;
    /** 답변 제목 */
    @Size(max = 50, message = "답변 제목은 50자를 넘을 수 없습니다.")
    private String ttl;
    /** 답변 내용 */
    @Size(max = 2000, message = "답변 내용은 2000자를 넘을 수 없습니다.")
    private String cn;
    /** 파일그룹 아이디 */
    private Integer filegrpid;
    /** 답변자 일련번호 */
    private Integer prcrid;
    /** 답변자 이름 */
    private Integer prcrNm;
    /** 답변공개 여부 */
    private String rlsYn;
    /** 답변일자 */
    private String ansDe;
    /** 지정 담당자 일련번호 */
    private String[] helpDeskManager;
    /** 문의 상태코드 */
    private String sttsCd;
    /** 파일Vo 목록 */
    private List<FileVo> fileList;
}
