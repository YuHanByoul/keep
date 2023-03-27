/**
 * 
 */
package com.kbrainc.plum.front.cnsltng.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 컨설팅 대상내역 VO 클래스
*
* <pre>
* com.kbrainc.plum.front.cnsltng.model
* - ConsultingVo.java
* </pre> 
*
* @ClassName : ConsultingVo
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ConsultingVo extends ParentRequestVo {

    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 검색 조건 */
    private String searchFldCd;
    private String searchPcntstNm;
    private String searchPrgrmNm;
    private String searchSttsCd;
    
    /** 사업분야 */
    private String fldNm;
    
    /** 공모명 */
    private String pcntstNm;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /** 컨설팅 아이디 */
    private Integer cnsltngid;
    
    /** 컨설팅 상태 */
    private String cnsltngStts;
    
    /** 방문일시 */
    private String vstDt;
    
    /** 컨설팅 결과 */
    private String rslt;
    
    /** 이전 내용 */
    private String cnBfr;
    
    /** 이후 내용 */
    private String cnAftr;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private Integer mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자 아이디 */
    private Integer rgtrid;
}
