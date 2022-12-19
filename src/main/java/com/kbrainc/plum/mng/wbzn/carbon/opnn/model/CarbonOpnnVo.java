package com.kbrainc.plum.mng.wbzn.carbon.opnn.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 탄소중립환경교육 -> 독자소리 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.opnn.model
* - OpnnVo.java
* </pre>
*
* @ClassName : CarbonOpnnVo
* @Description : 탄소중립환경교육 -> 독자소리 VO 클래스
* @author : JD
* @date : 2022. 12. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CarbonOpnnVo extends ParentRequestVo {
    
    /** 프로그램안내아이디 */
    private int opnnid;
    /** 분류 코드 */
    private String clsfCd;
    /** 제목 */
    private String ttl;
    /** 일시 */
    private String dt;
    /** 볼륨 */
    private String volum;
    /** 내용 */
    private String cn;
    /** 작성자 이름 */
    private String wrtrNm;
    /** 등록 일시*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
}
