package com.kbrainc.plum.mng.pack.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.tchaid.model.TchaidCmpntCmpstnVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.pack.model
* - PackageindvdVo.java
* </pre>
*
* @ClassName   : PackageindvdVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.02.13
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class PackageindvdVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 꾸러미 개체  아이이디 */
    private int packageindvdid;
    
    /** 꾸러미   아이이디 */
    private Integer packageid;
    
    /** 꾸러미 개체 명   */
    @Size(max = 200, message = "꾸러미명은 200자를 넘을 수 없습니다.")
    private String packageindvdNm;
    
    /** 꾸러미 개체  번호   */
    @Size(max = 20, message = "꾸러미 번호는 20자를 넘을 수 없습니다.")
    private String indvdno;
    
    /** 상태 코드   */
    @Size(max = 20, message = "상태 코드는 20자를 넘을 수 없습니다.")
    private String sttsCd;
    
    /** 상태 코드명 */
    private String sttsCdNm;
    
    /** 제품 상태 코드 */
    @Size(max = 20, message = "제품상태코드는 20자를 넘을 수 없습니다.")
    private String prductSttsCd;
    
    /** 꾸러미 유형 코드명 */
    private String prductSttsCdNm;
    
    /** 사용여부 */
    private String useYn;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
    /** 등록자 명 */
    private String regNm;
    
    /** 개체 교구 구성품 리스트  */
    private List<PackageindvdTchaidCmpstnVo> packageindvdTchaidCmpstnVoList;
    
    /** 재고 추가분 */
    private Integer plusQnty;
    
    /** 꾸러미 개체 이상  아이이디 */
    private Integer abnrmlid;
    
    private String mode;
    
    
    public void setSttsCd(String sttsCd) throws Exception{
        this.sttsCd = sttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.sttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sttsCd);
                this.sttsCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
             }catch(Exception e) {
                //e.printStackTrace();
                return ;
             }
        }
    }
    
    public void setPrductSttsCd(String prductSttsCd) throws Exception{
        this.prductSttsCd = prductSttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.prductSttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.prductSttsCd);
                this.prductSttsCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
    
}