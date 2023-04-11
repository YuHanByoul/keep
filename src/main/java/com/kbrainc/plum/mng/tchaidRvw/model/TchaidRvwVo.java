package com.kbrainc.plum.mng.tchaidRvw.model;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 교구후기 관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.mmnws.model
* - MmnwsVo.java
* </pre>
*
* @ClassName : MmnwsVo
* @Description : 교구후기 관리 VO 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/

@Data
public class TchaidRvwVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    private String tchaidid;
    private String type;
    private String typeCd;
    private Integer aplyid;
    private String aplcntid;
    private String aplcntNm;
    private String instNm;
    private String rcritNo;
    private String rcritNm;
    private String productNm;
    private Integer rvwScr;
    private String rvwCn;
    private Integer rvwFilegrpid;
    private Date rvwRegDt;
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    private String searchRcritNm;
    private String searchCd;
    private String searchScr;
    
    /** 삭제용 */
    private String[] orderids;
    private String[] lendAplyids;
    
}
