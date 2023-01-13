package com.kbrainc.plum.mng.spce.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
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
* com.kbrainc.plum.mng.spce.model
* - SpceRsvtdeVo.java
* </pre>
*
* @ClassName   : SpceRsvtdeVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.01.09
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class SpceRsvtdeVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 예약일자 아이이디 */
    private int rsvtdeid;
      
    /** 공간 아이디 */
    private Integer spceid;
    
    /** 이용구분 코드  */
    @Size(max = 20, message = "이용구분 코드는 20자를 넘을 수 없습니다.")
    private String utztnSeCd;
    
    /** 일자   */
    @Size(max = 10, message = "일자는 10자를 넘을 수 없습니다.")
    private String de;
    
    /** 시작일시   */
    private String bgngDt;
    
    /** 종료일시   */
    private String endDt;
    
    /** 종일 여부 */
    @Pattern(regexp="[YN]")
    private String alldayYn;
    
    /** 금액 */
    private String amt;
    
    /** 예약가능여부 */
    @Pattern(regexp="[YN]")
    private String rsvtPsbltyYn;
    
    /** 공간 번호 */
    @Size(max = 60, message = "공간 번호은 60자를 넘을 수 없습니다.")
    private String spceNo;
    
    /** 최대 인원수 */
    private Integer maxNope;
    
    /** 공간 번호 */
    @Size(max = 20, message = "크기는 20자를 넘을 수 없습니다.")
    private String size;
    
    /** 체크인 시간 */
    private String chkinHr;
    
    /** 체크 아웃 시간 */
    private String chcktHr;
    
}