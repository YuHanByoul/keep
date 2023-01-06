package com.kbrainc.plum.mng.expertPoolMng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * 전문가 로그Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertLogVoj.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertLogVo
 * @Description : 전문가 로그Vo 클래스
 * @date : 2023. 01. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ExpertLogVo extends ParentRequestVo {
    /**
     * 로그인 사용자 정보
     */
    private UserVo user;
    /**
     * 로그 아이디
     */
    private Integer logid;
    /**
     * 사용자 아이디
     */
    private Integer userid;
    /**
     * 처리 구분 코드
     */
    private String prcsSeCd;
    /**
     * 사유 내용
     */
    private String rsnCn;
    /**
     * 등록일
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDt;
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
     * 처리 구분 코드 이름
     */
    private String prcsSeCdNm;

    public void setPrcsSeCd(String prcsSeCd) {
        this.prcsSeCd = prcsSeCd;

        if (CommonUtil.isEmpty(this.prcsSeCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.prcsSeCd);
                this.prcsSeCdNm = code.getCdNm();
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
