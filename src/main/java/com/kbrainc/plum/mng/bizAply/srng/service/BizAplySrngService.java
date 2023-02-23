/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.srng.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.srng.model.BizAplySrngVo;

/**
* [심사관리 Service 인터페이스]. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.srng.service
* - BizAplySrngService.java
* </pre> 
*
* @ClassName : BizAplySrngService
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 16.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface BizAplySrngService {

    /**
    * [심사관리 목록 엑셀다운로드]. 
    *
    * @Title : selectSrngListExcelDownload
    * @Description : TODO
    * @param reqMngVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void selectSrngListExcelDownload(ReqMngVo reqMngVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
    * [심사관리대상자 목록 엑셀다운르도]. 
    *
    * @Title : selectSrngUserListExcelDownload
    * @Description : TODO
    * @param reqUserVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void selectSrngUserListExcelDownload(ReqUserVo reqUserVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
    * [심사결과 조회]. 
    *
    * @Title : detailBizAplySrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @return
    * @throws Exception
    * @return BizAplySrngVo
     */
    BizAplySrngVo detailBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception;
    
    /**
    * [심사결과 등록]. 
    *
    * @Title : insertBizAplySrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception;
    
    /**
    * [심사결과 수정]. 
    *
    * @Title : updateBizAplySrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception;
    
    /**
    * [심사위원 목록 조회]. 
    *
    * @Title : selectCnsltngExprtList
    * @Description : TODO
    * @return
    * @throws Exception
    * @return List<BizAplySrngVo>
     */
    List<BizAplySrngVo> selectCnsltngExprtList() throws Exception;
}
