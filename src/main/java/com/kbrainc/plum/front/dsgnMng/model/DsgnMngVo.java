package com.kbrainc.plum.front.dsgnMng.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* 사용자.지정관리 vo
*
* <pre>
* com.kbrainc.plum.front.dsgnMng.model
* - DsgnMngVo.java
* </pre>
*
* @ClassName : DsgnMngVo
* @Description : 사용자.지정관리 vo
* @author : kbrain
* @date : 2023. 3. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.DsgnMngVo")
public class DsgnMngVo extends ParentRequestVo {

    private UserVo user;

    /** 팝업 구분 */
    private String popSe;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 지정 번호 */
    private String dsgnNo;

    /** 프로그램 이름 */
    private String prgrmNm;

    /** 상태 코드 */
    private String sttsCd;

    /** 상태 코드 명*/
    private String sttsCdNm;

    /** 지정 획득 일자 */
    private String dsgnObtainDe;

    /** 지정 종료 일자 */
    private String dsgnEndDe;

    /** 지정 기간 */
    private String dsgnPrd;

    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date mdfcnDt;

    /** 신청아이디 */
    private Integer aplyid;

    /** 신청 상태 코드 */
    private String aplySttsCd;

    /** 신청자아이디 */
    private String aplcntid;

    /** 보완 요청 내용 */
    private String splmntDmndCn;

    /** 상태 수정 일시 */
    private Date sttsMdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;

    /** 차수아이디 */
    private Integer cyclid;

    /** 운영등록 */
    private String operReg;

    /** 제출 시작 일자 */
    private String sbmsnBgngDe;

    /** 제출 종료 일자 */
    private String sbmsnEndDe;

    /** 제출 기간 */
    private String sbmsnPrd;

    /** 심사아이디 */
    private Integer srngid;

    /** 이력아이디 */
    private Integer hstryid;

    /** 방문 일자 */
    private String vstDe;

    /** 요청아이디 */
    private Integer dmndid;

    /** 계획아이디 */
    private Integer planid;

    /** 제목 */
    private String ttl;

    /** 내용 */
    private String cn;

    /** 답변 */
    private String ans;

    /** 담당자 아이디 */
    private String picid;

    /** 답변 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date ansDt;

    public void setSttsCd(String sttsCd) throws Exception{
        this.sttsCd = sttsCd;

        //이미 코드이름이 있다면, 무시.
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
