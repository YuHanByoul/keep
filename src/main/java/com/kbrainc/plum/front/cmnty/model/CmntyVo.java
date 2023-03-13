package com.kbrainc.plum.front.cmnty.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 커뮤니티 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyVo
 * @Description : 커뮤니티 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.CmntyVo")
public class CmntyVo extends ParentRequestVo {
    private UserVo user;

    /** 커뮤니티아이디 */
    private Integer cmntyid;

    /** 커뮤니티_이름 */
    private String cmntyNm;

    /** 커뮤니티_소개 */
    private String cmntyIntrcn;

    /** 개설자아이디 */
    private String esterid;

    /** 개설자이름 */
    private String esterNm;

    /** 가입승인방식_코드 */
    private String joinaprvmthdCd;

    /** 공개_여부 */
    private String rlsYn;

    /** 검색_노출_여부 */
    private String srchExpsrYn;

    /** 운영_여부 */
    private String operYn;

    /** 커뮤니티_로고_파일아이디 */
    private Integer cmntyLogoFileid;

    /** 회원수 */
    private Integer mbrCnt;

    /** 가입상태_코드 */
    private String mbrSttsCd;

    /** 가입자_권한코드 */
    private String authrtCd;

    /** 가입상태_코드명 */
    private String mbrSttsCdNm;

    /** 로고_파일_키 */
    private String fileIdntfcKey;

    /** 수정_일시 */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;

    /** 수정자아이디 */
    private String mdfrid;

    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;

    /** 등록자아이디 */
    private String rgtrid;

    /** 카테고리 템플릿 아이디 배열 */
    private Integer[] tmplatIds;

    /** 카테고리 템플릿 사용 Y 아이디 배열 */
    private Integer[] tmplatYIds;

    /** 커뮤니티 개설 구분 */
    private String esterYn;

    /** 커뮤니티 초대 구분 */
    private String inviteYn;

    /** 초대할 사용자/삭제,반려할 아이디 */
    private String inviteUserid;

    public void setMbrSttsCd(String mbrSttsCd) throws Exception{
        this.mbrSttsCd = mbrSttsCd;
        if(CommonUtil.isEmpty(this.mbrSttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.mbrSttsCd);
                this.mbrSttsCdNm = code.getCdNm();
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
