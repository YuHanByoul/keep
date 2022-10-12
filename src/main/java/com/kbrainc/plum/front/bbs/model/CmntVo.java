package com.kbrainc.plum.front.bbs.model;/** **/

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * Comment VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.model
 * - CmntVo.java
 * </pre> 
 *
 * @ClassName : CmntVo
 * @Description : Comment VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.CmntVo")
public class CmntVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    /** **/

    private int cmntid;
    /** **/
    private Integer pstid; 
    /** **/
    private String cntnts;
    /** **/
    private Integer prntsCmntid;
    /** **/
    private Integer cmntGrp;
    /** **/
    private Integer dpth;
    /** **/
    private Integer ord;
    /** **/
    private String opnYn;
    /** **/
    private String updtDt;
    /** 수정_일시 **/
    private int updtuserid;
    /** 수정자_아이디 **/
    private String regDt;
    /** 등록_일시 **/
    private int reguserid; /** 등록_아이디 **/

    private String delYn;
    
    private String acnt;
    private String nm;
    
    private String paddingStr;
    private String dpthStr;
    
    public int totalPage;
    public int pageNumber;
    
}