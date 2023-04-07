package com.kbrainc.plum.mng.book.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* 우수환경도서 관리 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.book.model
* - BookVo.java
* </pre>
*
* @ClassName : BookVo
* @Description : 우수환경도서 관리 Vo 클래스
* @author : JD
* @date : 2023. 1. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class BookVo  extends ParentRequestVo{
    
    private UserVo user;
    
    
    /** 도서아이디 */
    private int bookid;
    /** 교육_주제_코드(중분류) */
    private String eduSbjctCd;
    /** 교육_주제_코드(대분류) */
    private String mainEduSbjctCd;
    /** 교육_대상_코드 */
    @NotEmpty(message = "교육대상 선택해주십시오.")
    private String eduTrgtCd;
    /** 작가 */
    private String writr;
    /** 작가_그림 */
    private String writrPictr;
    /** 출판사 */
    private String plscmpn;
    /** 금액 */
    private Integer amt;
    /** 도서명(제목) */
    @NotEmpty(message = "도서명을 입력해주십시오.")
    private String ttl;
    /** 내용 */
    @NotEmpty(message = "내용을 입력해주십시오.")
    private String cn;
    /** 내용 */
    private Integer hits;
    /** 대표_이미지_파일아이디 */
    private Integer rprsImgFileid;
    /** 첨부_파일그룹아이디 */
    private Integer atchFilegrpid;
    /** 저작권 코드 */
    private String cpyrhtCd;
    /** 삭제여부 */
    private String delYn;
    /** 수정_일시 */
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    private String rgtridNm;
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 필터 */
    private String searchManinEduSbjctCd;
    private String searchMdleEduSbjctCd;
    private String searchEduTrgtCd;
    private String searchPlscmpn;
    private String startDt;
    private String endDt;
    
    /** 교육_주제_코드명(다수) */
    private String eduSbjctCdNm;
    
    /** 교육_주제_코드(다수) 등록용 */
    private String[] eduSbjctCds;
    
    /** 다수 삭제용 */
    private String[] bookids;
    
    /** 교육_대상_코드명 */
    private String eduTrgtCdNm;
    
    public void setEduTrgtCd(String eduTrgtCd) throws Exception{
        this.eduTrgtCd = eduTrgtCd;
        if(CommonUtil.isEmpty(this.eduTrgtCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.eduTrgtCd);
                this.eduTrgtCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
             }catch(Exception e) {
                //e.printStackTrace();
                return ;
             }
        }
    }
    
    //public void setEduSbjctCd(String eduSbjctCd) throws Exception{
    //    this.eduSbjctCd = eduSbjctCd;
    //    List<String> eduCds = new ArrayList();
    //    String[] eduSbjctCdArr = eduSbjctCd.split(",");
    //    if(eduSbjctCdArr.length > 0) {
    //        for(String eduCd:eduSbjctCdArr) {
    //            try {
    //                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
    //                CodeInfoVo code = resCodeService.getCodeInfo(eduCd);
    //                eduCds.add(code.getCdNm());
    //            }catch(NoClassDefFoundError e) {
    //                return ;
    //            }catch(Exception e) {
    //                return ;
    //            }
    //        }
    //        
    //        this.eduSbjctCdNm = String.join(",", eduCds);
    //    }
    //}
    
}
