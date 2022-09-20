package com.kbrainc.plum.cmm.scheduling.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
* 스케줄링트리거Vo 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.model
* - SchedulingTriggerVo.java
* </pre>
*
* @ClassName   : SchedulingTriggerVo 
* @Description : 스케줄링트리거Vo 클래스. 
* @author      : ZENIEL
* @date        : 2021. 2. 23.
* @Version     :  
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class SchedulingTriggerVo implements Serializable {

    private static final long serialVersionUID = 4052462080524472486L;

    /** 아이디 */
    private Integer triggerid;
    
    /** 트리거_이름 */
    private String nm;

    /** 설명 */
    private String dc;

    /** 크론표현식 */
    private String cron_expression;

    /** 사용여부 */
    private String use_yn;
}