package com.kbrainc.plum.front.eduInst.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.EqpVo")
public class EqpVo extends ParentRequestVo {
	/** 로그인사용자정보 */
    private UserVo user;

    private Integer eqpid;        /** 설비아이디 */
    private Integer aplyid;       /** 신청아이디 */
    private String  nm;           /** 이름 */
    private Integer qnty;         /** 수량 */
    private String  prcuseMthd;   /** 활용_방법 */
    private String  rmrk;         /** 비고 */
    private String  mdfcnDt;      /** 수정_일시 */
    private String  mdfrid;       /** 수정자아이디 */
    private String  regDt;        /** 등록_일시 */
    private String  rgtrid;       /** 등록자아이디 */

}
