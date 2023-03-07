/**
 * 
 */
package com.kbrainc.plum.front.expEnv.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.bizAply.model.ReqUserVo;
import com.kbrainc.plum.front.bizAply.model.SupplementVo;

/**
* 마이페이지 > 체험환경교육 프로그램 지원 관리 > 공모신청 내역 서비스 DAO. 
*
* <pre>
* com.kbrainc.plum.front.expEnv.model
* - MyReqMngDao.java
* </pre> 
*
* @ClassName : MyReqMngDao
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MyReqMngDao {

    /**
    * 공모신청내역 목록 조회. 
    *
    * @Title : selectMyAplyList
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return List<ReqUserVo>
     */
    List<ReqUserVo> selectMyAplyList(ReqUserVo reqUserVo) throws Exception;
    
    /**
     * 보완요청 조회. 
     *
     * @Title : detailSplmnt
     * @Description : TODO
     * @param reqUserVo
     * @return
     * @throws Exception
     * @return SupplementVo
     */
    SupplementVo detailSplmnt(SupplementVo supplementVo) throws Exception;

    /**
    * 보완요청 답변 저장. 
    *
    * @Title : updateSplmnt
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateSplmnt(SupplementVo supplementVo) throws Exception;
    
}
