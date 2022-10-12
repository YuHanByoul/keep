package com.kbrainc.plum.mng.scheduling.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* 스케줄링Vo 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.model
* - SchedulingVo.java
* </pre>
*
* @ClassName   : SchedulingVo 
* @Description : 스케줄링Vo 클래스. 
* @author      : ZENIEL
* @date        : 2021. 3. 17.
* @Version     :  
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class SchedulingVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 트리거아이디 */
    private Integer triggerid;
    
    /** 트리거_이름 */
    @NotEmpty(message = "트리거명을 입력해 주십시오.")
    @Size(max = 60, message = "트리거명은 60자를 넘을 수 없습니다.")
    private String nm;
    
    /** Job 이름 */
    private String jobName;

    /** 설명 */
    @Size(max = 160, message = "설명은 160자를 넘을 수 없습니다.")
    private String dc;

    /** 크론표현식 */
    @NotEmpty(message = "크론표현식을 입력해 주십시오.")
    @Size(max = 60, message = "크론표현식은 60자를 넘을 수 없습니다.")
    private String cronExpression;

    /** 사용여부 */
    @NotEmpty(message = "사용여부를 입력해 주십시오.")
    @Pattern(regexp = "Y|N")
    private String useYn;
    
    /** 활성화여부 */
    private String activeYn;
}