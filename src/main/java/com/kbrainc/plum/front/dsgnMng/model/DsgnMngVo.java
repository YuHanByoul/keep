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

    /** mode */
    private String mode;

    /** 버튼 구분 */
    private String btnSe;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 지정 번호 */
    private String dsgnNo;

    /** 프로그램 이름 */
    private String prgrmNm;

    /** 기관 이름 */
    private String instNm;

    /** 기관 이름 */
    private String telno;

    /** 상태 코드 */
    private String sttsCd;

    /** 지정 상태 코드 */
    private String dsgnSttsCdNm;

    /** 상태 코드 명*/
    private String sttsCdNm;

    /** 지정 획득 일자 */
    private String dsgnObtainDe;

    /** 지정 종료 일자 */
    private String dsgnEndDe;

    /** 지정 기간 */
    private String dsgnPrd;

    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date sttsMdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
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

    /** 방문 기간 */
    private String vstPrd;

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

    /** 담당자 명 */
    private String picNm;

    /** 요청 내용 */
    private String dmndCn;

    /** 요청 서류 */
    private String dmndDcmnt;

    /** 요청 완료 일자 */
    private String dmndCmptnDe;

    /** 총괄 의견 */
    private String smrizeOpnn;

    /** 동일 여부 */
    private String samenssYn;

    /** 동일 내용 */
    private String samenssCn;

    /** 프로그램 변경 여부 */
    private String prgrmChgYn;

    /** 프로그램 변경 내용 */
    private String prgrmChgCn;

    /** 예산 변경 여부 */
    private String bgtChgYn;

    /** 예산 변경 내용 */
    private String bgtChgCn;

    /** 지도자 변경 여부 */
    private String ldrChgYn;

    /** 지도자 변경 내용 */
    private String ldrChgCn;

    /** 교육 환경 변경 여부 */
    private String eduEnvChgYn;

    /** 교육 환경 변경 내용 */
    private String eduEnvChgCn;

    /** 변경 사유 */
    private String chgRsn;

    /** 파일아이디 */
    private Integer fileid;

    /** 파일 식별 키 */
    private String fileIdntfcKey;

    /** 원본 파일 이름 */
    private String orginlFileNm;

    /** ext */
    private String ext;

    /** 결과차수아이디 */
    private Integer rsltCyclid;

    /** 기관 유형 코드 */
    private String instTypeCd;

    /** 기관 유형 코드 명*/
    private String instTypeCdNm;

    /** 기관 유형 상위 코드 명*/
    private String instTypeUppCdNm;

    /** 책임개발자 이름 */
    private String snrstfdvlprNm;

    /** 책임개발자 이메일 */
    private String snrstfdvlprEml;

    /** 책임개발자 전화번호 */
    private String snrstfdvlprTelno;

    /** 책임개발자 휴대폰번호 */
    private String snrstfdvlprMoblphon;

    /** 담당자 이메일 */
    private String picEml;

    /** 담당자 전화번호 */
    private String picTelno;

    /** 담당자 휴대폰번호 */
    private String picMoblphon;

    /** 프로그램 유형 코드 */
    private String prgrmTypeCd;

    /** 프로그램 유형 코드 */
    private String addrAll;

    /** 숙박 여부 */
    private String styYn;

    /** 보험 가입 여부 */
    private String insrncJoinYn;

    /** 보험 사유 */
    private String insrncRsn;

    /** 변경 여부 */
    private String chgYn;

    /** 유아 */
    private String infnt;

    /** 초등학생 */
    private String schboy;

    /** 중학생 */
    private String msklsd;

    /** 고등학생 */
    private String hgschst;

    /** 성인 */
    private String adult;

    /** 전체 연령 */
    private String wholAge;

    /** 교육 인원수 */
    private Integer eduNope;

    /** 활동 장소 */
    private String actvtPlc;

    /** 참가비 */
    private Integer etrfee;

    /** 교육 횟수 */
    private Integer eduCnt;

    /** 교육 시간 */
    private Integer eduHr;

    /** 교육 분 */
    private Integer eduMnt;

    /** 안전사고 유무 */
    private String sftyacdntEnnc;

    /** 1월 */
    private String jan;

    /** 2월 */
    private String feb;

    /** 3월 */
    private String mar;

    /** 4월 */
    private String apr;

    /** 5월 */
    private String may;

    /** 6월 */
    private String june;

    /** 7월 */
    private String july;

    /** 8월 */
    private String aug;

    /** 9월 */
    private String sept;

    /** 10월 */
    private String oct;

    /** 11월 */
    private String nov;

    /** 12월 */
    private String dcm;

    /** 전체 월 */
    private String wholMm;

    /** 발급 여부 */
    private String issuYn;

    /** 발급 건수 */
    private Integer issuNocs;

    /** 발급 합계 */
    private Integer issuSum;

    /** 프로그램 우수성 */
    private String prgrmDstnctn;

    /** 운영 관리 */
    private String operMng;

    /** 평가 */
    private String evl;

    /** 자격 배치 */
    private String qlfcPostng;

    /** 안전 관리 */
    private String sftyMng;

    /** 파일그룹아이디 */
    private Integer filegrpid;

    /** 다음 연도 프로그램 */
    private String nextYrPrgrm;

    /** 다음 연도 지도자 */
    private String nextYrLdr;

    /** 다음 연도 교육 환경 */
    private String nextYrEduEnv;

    /** 실적아이디 */
    private Integer prfmncid;

    /** 실시 일자 */
    private String oprtnDe;

    /** 대상 */
    private String trgt;

    /** 시간 */
    private Integer hr;

    /** 인원수 */
    private Integer nope;

    /** 횟수 */
    private Integer cnt;

    /** 전체 시간 */
    private Integer wholHr;

    /** 전체 인원수 */
    private Integer wholNope;

    /** 지도자 배치 비율 */
    private Integer ldrPostngRt;

    /** 숙박(박) */
    private Integer styNight;

    /** 숙박(박) */
    private Integer styDaytm;

    /** 컨설팅  진행 여부 */
    private String cnsltngPrgrsYn;

    /** 컨설팅아이디 */
    private Integer cnsltngid;

    /** 사용자아이디 */
    private Integer userid;

    /** 신청 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date aplyDt;

    /** 컨설팅 종류 코드 */
    private String cnsltngKndCd;

    /** 컨설턴트아이디 */
    private Integer cnstntid;

    /** 컨설턴트아이디 목록*/
    private String cnstntidLst;

    /** 컨설턴트 이름 */
    private String cnstntNm;

    /** 컨설턴트 이름 목록*/
    private String cnstntNmLst;

    /** 평가상태 */
    private String evlStts;

    /** 수정자 아이디 */
    private Integer mdfrId;

    /** 등록자 아이디 */
    private Integer rgtrId;

    /** 컨설턴트명 */
    private String nm;

    /** 코드 명 */
    private String cdNm;

    /** 방문일시 */
    private String vstDt;

    /** 희망 일자1 */
    private String hopeDe1;

    /** 희망 일자1 오전 오후 코드 */
    private String hopeDe1AmPmCd;

    /** 희망 일자2 */
    private String hopeDe2;

    /** 희망 일자2 오전 오후 코드 */
    private String hopeDe2AmPmCd;

    /** 프로그램 */
    private String prgrm;

    /** 지도자 */
    private String ldr;

    /** 기타 */
    private String etc;

    /** 기관아이디 */
    private Integer instid;

    /** 사용자 이름 */
    private String userNm;

    /** 기관 전화번호 */
    private String instTelno;

    /** 우편번호 */
    private String zip;

    /** 주소 */
    private String addr;

    /** 주소 상세 */
    private String addrDtl;

    /** 설문아이디 */
    private Integer srvyid;

    /** 계정 */
    private String acnt;

    /** 제출id */
    private Integer sbmsnid;

    /** site */
    private String siteid;

    /** 항목id */
    private String qitemid;

    /** 답변 서술형 */
    private String ansDscrp;

    /** 척도 */
    private String scale;

    /** 점수 */
    private String scr;

    /** 기관담당자 이름 */
    private String instPicNm;

    private String addrZip;

    private Integer qestnrid;

    /** 답변 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
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
