package com.kbrainc.plum.mng.chklst.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
 * 
 * 체크리스트Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.model
 * - ChklstVo.java
 * </pre> 
 *
 * @ClassName : ChklstVo
 * @Description : 체크리스트Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 01. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ChklstVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 체크리스트 아이디 */
    private int chklstid;
    
    /** 체크리스트 명 */
    @NotEmpty(message = "체크리스트 명을 입력해주세요.")
    @Size(max = 40, message = "체크리스트 명은 40자 이하여야 합니다.")
    private String chklstNm;
    
    /** 체크리스트 설명 */
    @Size(max = 60, message = "체크리스트 설명은 60자 이하여야 합니다.")
    private String chklstExpln;
    
    /** 운형 형태 코드*/
    @NotEmpty(message = "운형 형태를 선택해주세요.")
    private String operFrmCd;
    
    /** 운형 형태 코드명*/
    private String operFrmCdNm;
    
    /** 사용 여부 */
    private String useYn;
    
    /** 문항수 */
    private String qitemCnt;
    
    /** 검색 운영형태 구분코드 */
    private String searchOperFrmCd;
    
    /** 사용중인 체크리스트 여부 */
    private String isUseChklst;
    
    public void setOperFrmCd(String operFrmCd) {
        this.operFrmCd = operFrmCd;
        if(CommonUtil.isEmpty(this.operFrmCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.operFrmCd);
                this.operFrmCdNm = code.getCdNm();
            } catch(NoClassDefFoundError e) {
                return;
             } catch(Exception e) {
                return;
             }
        }
    }
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
    /** 수정일자 정보 */
    public Date getMdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public void setMdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    /** 등록일자 정보 */
    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }
    
    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }
    
    /** 로그인 사용자 정보 */
    public UserVo getUser() {
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }
    
    public void setUser(UserVo user) {
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    
}