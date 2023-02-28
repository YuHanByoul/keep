package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("front.ExprtCareerVo")
public class ExprtCareerVo {
    /** 경력 아이디 */
    private Integer exprtCareerId;
    /** 분야 이름 */
    private String fldNm;
    /** 활동 시작 일자 */
    private String actvtBgngDe;
    /** 활동 종료 일자 */
    private String actvtEndDe;
    /** 활동 기간 */
    private String actvtDe;
    /** 활동 여부 */
    private String actvtYn;
    /** 활동 시간 */
    private Integer actvtHr;
    /** 확인 기관 이름 */
    private String idntyInstNm;
    /** 활동 내용 */
    private String actvtCn;
    /** 확인 일자 */
    private String idntyDe;
    /** 확인서 파일아이디 */
    private Integer crtfFileid;
    /** 정관 파일아이디 */
    private Integer artclassFileid;
    /** 순서 */
    private Integer ordr;

    private FileVo crtfFile;

    private FileVo artClassFile;
}