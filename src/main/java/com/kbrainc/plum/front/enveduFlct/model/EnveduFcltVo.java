package com.kbrainc.plum.front.enveduFlct.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.EnveduFcltVo")
public class EnveduFcltVo extends ParentRequestVo {
    
    private UserVo user;
    
    /* 환경교육시설 현황 */
    /** 시설 아이디 */
    private String fcltid;
    /** 대표 이미지 */
    private Integer rprsImgFileid;
    /** 시설명 */
    private String fcltNm;
    /** 기관명 */
    private String instNm;
    /** 주소 */
    private String addr;
    /** 상세주소 */
    private String addrDtl;
    /** 예약일자아이디 */
    private String rsvtdeid;
    
    private FileVo fileInfo;

}
