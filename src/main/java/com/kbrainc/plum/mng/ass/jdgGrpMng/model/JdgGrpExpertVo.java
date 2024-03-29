package com.kbrainc.plum.mng.ass.jdgGrpMng.model;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 심사위원 그룹 전문가 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.ass.jdgGrpMng.model
 * - JdgGrpExpertVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : JdgGrpExpertVo
 * @Description : 심사위원 그룹 전문가 Vo 클래스
 * @date : 2022. 12. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class JdgGrpExpertVo extends ParentRequestVo {
    /**
     * 로그인 사용자 정보
     */
    private UserVo user;

    /**
     * 아이디
     */
    private String acnt;

    /**
     * 이름
     */
    private String nm;

    /**
     * 이메일
     */
    private String eml;

    /**
     * 휴대폰 번호
     */
    private String moblphon;

    /**
     * 자격증 이름
     */
    private String crtfctNm;

    /**
     * 전문분야 코드
     */
    private String sbjctCd;

    /**
     * 활동가능지역 코드
     */
    private String rgnCd;

    /**
     * 전문분야 코드 이름
     */
    private String sbjctCdNm;

    /**
     * 활동가능지역 코드 이름
     */
    private String rgnCdNm;

    /**
     * 등록일
     */
    private Date regDt;

    /**
     * 수정일
     */
    private Date mdfcnDt;

    /**
     * 검색용 아이디/이름
     */
    private String acntOrNm;

    /**
     * 일련번호
     */
    private Integer userid;

    /**
     * 소속된 심사위원 그룹 일련번호
     */
    private Integer grpId;

    /**
     * 심사위원 그룹에 추가되는 전문가 일련번호
     */
    private Integer[] insertIds;

    public void setSbjctCd(String sbjctCd) {
        this.sbjctCd = sbjctCd;

        if(CommonUtil.isEmpty(this.sbjctCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sbjctCd);
                this.sbjctCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }

    public void setRgnCd(String rgnCd) {
        this.rgnCd = rgnCd;

        if(CommonUtil.isEmpty(this.rgnCdNm)) {
            try {
                CommonService commonService = (CommonService) CommonUtil.getBean("commonServiceImpl", CommonUtil.getCurrentRequest());
                List<Map<String, Object>> ctprvnList = commonService.selectCtprvnList();

                for (Map<String, Object> ctprvnInfo : ctprvnList) {
                    if (ctprvnInfo.get("CTPRVN_CD").equals(this.rgnCd)) {
                        this.rgnCdNm = (String) ctprvnInfo.get("CTPRVN_NM");
                        break;
                    }
                }
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
}
