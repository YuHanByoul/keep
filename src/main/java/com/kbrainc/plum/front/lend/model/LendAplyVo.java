package com.kbrainc.plum.front.lend.model;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
 *
 * 알림 VO
 *  
 * <pre>
 * com.kbrainc.plum.fornt.lend.model
 * - LendAplyVo.java
 * </pre> 
 *
 * @ClassName : LendAplyVo
 * @Description : LendAplyVo
 * @author : KBRAINC
 * @date : 2023. 03. 06.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.lendAplyVo")
public class LendAplyVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    
    /** 대여 신청 아이디 */
    private Integer aplyid;
    
    /** 신청자아이디 */
    private Integer aplcntid;
    
    /** 기관아이디 */
    private Integer instid;
    
    /** 상태_코드 */
    @Size(max = 20, message = "상태코드는 20자를 초과할 수 없습니다.")
    private String sttsCd;
    
    /** 상태_코드명 */
    private String sttsCdNm;
    
    /** 배송_상태_코드 */
    @Size(max = 20, message = "상태코드는 20자를 초과할 수 없습니다.")
    private String dlvySttsCd;
    
    /** 신청자_이름 */
    @Size(max = 60, message = "신청자 이름은 60자를 초과할 수 없습니다.")
    private String aplcntNm;
    
    /** 기관_이름 */
    @Size(max = 100, message = "기관 이름은 100자를 초과할 수 없습니다.")
    private String instNm;
    
    /** 전화번호 */
    @Size(max = 40, message = "전화번호는 40자를 초과할 수 없습니다.")
    private String telno;
    
    /** 이메일 */
    @Size(max = 200, message = "이메일은 200자를 초과할 수 없습니다.")
    private String eml;
    
    /** 교육 횟수*/
    private Integer eduCnt;
    
    /** 교육 인원수*/
    private Integer eduNope;

    /** 단체 이름  */
    @Size(max = 100, message = "단체이름은 100자를 초과할 수 없습니다.")
    private String grp_nm;
    
    /** 시도_코드*/
    @Size(max = 20, message = "시도_코드는 20자를 초과할 수 없습니다.")
    private String ctprvnCd;
    
    /** 수령인*/
    @Size(max = 100, message = " 수령인은 100자를 초과할 수 없습니다.")
    private String recptr;
    
    /** 수령인*/
    @Size(max = 40, message = "수령인 전화번호는 40자를 초과할 수 없습니다.")
    private String recptrTelno;
    
    /** 수령인 이메일*/
    @Size(max = 200, message = "수령인 이메일은 200자를 초과할 수 없습니다.")
    private String recptrEml;
    
    /** 수령인 이메일*/
    @Size(max = 200, message = "배송 우편번호는 10자를 초과할 수 없습니다.")
    private String dlvyZip;
    
    /** 배송 주소*/
    @Size(max = 200, message = "배송 주소은 200자를 초과할 수 없습니다.")
    private String dlvyAddr;
    
    /** 배송 주소상세*/
    @Size(max = 400, message = "수령인 이메일은 400자를 초과할 수 없습니다.")
    private String dlvyAddrDtl;
    
    /** 배송 송장번호 출고 */
    @Size(max = 100, message = "출고 송장번호는 100자를 초과할 수 없습니다.")
     private String invcnoDlivy;
    
    /** 배송 송장번호 입고 */
    @Size(max = 100, message = "입고 송장번호는 100자를 초과할 수 없습니다.")
    private String invcnoWrhousng;
    
    /** 후기 점수 */
    private Integer rvwScr;
    
    /** 후기 내용 */
    @Size(max = 500, message = "후기 내용은 500자를 초과할 수 없습니다.")
    private String rvwCn;
    
    /** 후기 파일 그룹 아이디 */
    private Integer rvwFilegrpid;
    
    /** 후기 등록 일자 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date rvwRegDt;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    public void setSttsCd(String sttsCd) throws Exception{
        this.sttsCd = sttsCd;
        if(CommonUtil.isEmpty(this.sttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sttsCd);
                this.sttsCdNm = code.getCdNm();
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