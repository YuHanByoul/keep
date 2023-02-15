package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("front.ExprtCrtfctVo")
public class ExprtCrtfctVo {
    /** 자격증 아이디 */
    private Integer exprtCrtfctId;
    /** 자격증 이름 */
    private String crtfctNm;
    /** 취득 기관 */
    private String acqsInst;
    /** 취득 번호 */
    private String acqsNo;
    /** 취득 등급 */
    private String acqsGrd;
    /** 취득 일자 */
    private String acqsDe;
    /** 자격증 파일아이디 */
    private Integer crtfctFileid;
    /** 순서 */
    private Integer ordr;
    /**자격증 파일정보 */
    private FileVo crtfctFile;
}