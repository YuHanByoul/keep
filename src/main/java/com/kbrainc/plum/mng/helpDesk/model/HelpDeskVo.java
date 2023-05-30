package com.kbrainc.plum.mng.helpDesk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * 탄소중립헬프데스크 문의글Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.helpDesk.model
 * - HelpDeskVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpDeskVo
 * @Description : 탄소중립헬프데스크 문의글Vo 클래스
 * @date : 2022. 12. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class HelpDeskVo extends ParentRequestVo {
    /** 분류코드 그룹아이디 */
    private String clsfCdgrpid = "112";
    /** 상태코드 그룹아이디 */
    private String sttsCdgrpid = "113";
    /** 로그인 사용자 정보 */
    private UserVo user;
    /** 사용자 아이디 */
    private Integer userid;
    /** 휴대 번호 */
    private String moblphon;
    /** 문의 일련번호 */
    private Integer inqryid;
    /** 분류코드 */
    private String clsfCd;
    /** 분류명 */
    private String clsfNm;
    /** 상태코드 */
    private String sttsCd;
    /** 상태코드명 */
    private String sttsCdNm;
    /** 제목 */
    private String ttl;
    /** 내용 */
    private String cn;
    /** 파일그룹아이디 */
    private Integer filegrpid;
    /** 기관명 */
    private String instNm;
    /** 작성자명 */
    private String nm;
    /** 작성자아이디 */
    private String acnt;
    /** 작성일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    /** 처리자명 */
    private String prcrNm;
    /** 본인 처리 여부*/
    private String assignYn;

    public void setClsfCd(String clsfCd) {
        this.clsfCd = clsfCd;
        //이미 코드이름이 있다면, 무시.
        if (CommonUtil.isEmpty(this.clsfNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.clsfCd);
                this.clsfNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }

    public void setSttsCd(String sttsCd) {
        this.sttsCd = sttsCd;
        //이미 코드이름이 있다면, 무시.
        if (CommonUtil.isEmpty(this.sttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sttsCd);
                this.sttsCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }
}
