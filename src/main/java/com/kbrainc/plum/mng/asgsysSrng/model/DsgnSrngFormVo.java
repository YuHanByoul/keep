package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/***
* 심사양식VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - DsgnSrngFormVO.java
* </pre>
*
* @ClassName : DsgnSrngFormVO
* @Description : 심사양식VO 클래스
* @author : kbrain
* @date : 2022. 12. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class DsgnSrngFormVo {

	/** 로그인 사용자 정보 */
    private UserVo user;

    /** 지정기준코드 */
    private String dsgncrtrCd;

    /** 지정기준코드 명*/
    private String dsgncrtrCdNm;

    /** 확인사항 */
    private String idntyMttr;

    /** 지정기준 순서 */
    private Integer dsgncrtrOrdr;

    /** 순서 */
    private Integer ordr;

    /** 항목ID */
    private Integer qitemid;

    /** 체크리스트 구분코드 */
    private String chklstSeCd;

    /** 배점 */
    private Integer altm;

    /** 점수 */
    private Integer scr;

    /** 총점(배점) */
    private Integer allaltm;

    /** 총점(점수) */
    private Integer allscr;

    /** 양식 ID */
    private Integer formid;

    /** 제출 ID */
    private Integer sbmsnid;

    /** 등록자아이디*/;
	private Integer rgtrid;

    /** 프로그램아이디*/;
	private Integer prgrmid;

    /** 등록_일시*/
	private Date regDt;

	/** 수정일시 */
	private Date mdfcnDt;

	/** 수정자ID */
	private Integer mdfrid;

	/** RNAK */
	private Integer rnk;

	/** COLSPAN */
	private Integer rowspan;

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

    /** 지정기준코드 명 */
	public void setDsgncrtrCd(String dsgncrtrCd) throws Exception{

		this.dsgncrtrCd = dsgncrtrCd;

		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.dsgncrtrCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.dsgncrtrCd);
				this.dsgncrtrCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

}
