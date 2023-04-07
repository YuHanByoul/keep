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
* 우수환경도서 교육 주제 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.book.model
* - BookSbjctVo.java
* </pre>
*
* @ClassName : BookSbjctVo
* @Description : 우수환경도서 교육 주제 Vo 클래스
* @author : JD
* @date : 2023. 04. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class BookSbjctVo  extends ParentRequestVo{
    
    private UserVo user;
    
    /** 도서아이디 */
    private int bookid;
    
    /** 교육_주제_코드(중분류) */
    private String eduSbjctCd;
    
    /** 등록_일시 */
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    /** 교육_주제_코드명() */
    private String eduSbjctCdNm;
    
    public void setEduSbjctCd(String eduSbjctCd) throws Exception{
        this.eduSbjctCd = eduSbjctCd;
        if(CommonUtil.isEmpty(this.eduSbjctCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.eduSbjctCd);
                this.eduSbjctCdNm = code.getCdNm();
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
