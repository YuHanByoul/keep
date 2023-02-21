package com.kbrainc.plum.mng.pack.model;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.pack.model
* - PackageEduSbjctVo.java
* </pre>
*
* @ClassName   : PackageEduSbjctVo 
* @Description : 꾸러미 교육 주제 맵핑 
* @author      : KBRAINC
* @date        : 2023.02.06
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class PackageEduSbjctVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 교구 아이이디 */
    private int tchaidid;
    
    /** 교구 교육 주제 코드 */
    @Size(max = 20, message = "교구 주제 코드는 100자를 넘을 수 없습니다.")
    private String eduSbjctCd;
    
    /** 교구 교육 주제 코드명 */
    private String eduSbjctCdNm;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
    public void setEduSbjctCd(String eduSbjctCd) throws Exception{
        this.eduSbjctCd = eduSbjctCd;
        
        //이미 코드이름이 있다면, 무시.
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