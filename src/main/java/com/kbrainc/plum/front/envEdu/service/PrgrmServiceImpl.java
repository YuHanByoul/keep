/**
 * 
 */
package com.kbrainc.plum.front.envEdu.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.envEdu.model.PrgrmDao;
import com.kbrainc.plum.front.envEdu.model.PrgrmVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 프로그램 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.envEdu.service
* - PrgrmServiceImpl.java
* </pre>
*
* @ClassName : PrgrmServiceImpl
* @Description : 프로그램 서비스 구현 클래스
* @author : JD
* @date : 2023. 2. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.prgrmServiceImpl")
@Alias("front.prgrmServiceImpl")
public class PrgrmServiceImpl extends PlumAbstractServiceImpl implements PrgrmService {

    @Resource(name = "front.prgrmDao")
    private PrgrmDao prgrmDao;

    /**
    * 프로그램 게시글 목록 조회
    *
    * @Title : selectPrgrmList
    * @Description : 프로그램 게시글 목록 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return List<PrgrmVo>
    */
    @Override
    public List<PrgrmVo> selectPrgrmList(PrgrmVo prgrmVo) throws Exception {
        return prgrmDao.selectPrgrmList(prgrmVo);
    }
    
    /**
    * 프로그램 게시글 상세 조회
    *
    * @Title : selectPrgrmInfo
    * @Description : 프로그램 게시글 상세 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return PrgrmVo
    */
    @Override
    public PrgrmVo selectPrgrmInfo(PrgrmVo prgrmVo) throws Exception {
        return prgrmDao.selectPrgrmInfo(prgrmVo);
    }
    
    /**
    * 프로그램 해당기업 프로그램 목록 조회
    *
    * @Title : selectInstPrgrmList
    * @Description : 프로그램 해당기업 프로그램 목록 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return List<PrgrmVo>
    */
    @Override
    public List<PrgrmVo> selectInstPrgrmList(PrgrmVo prgrmVo) throws Exception {
        return prgrmDao.selectInstPrgrmList(prgrmVo);
    }

    /**
    * 프로그램 교육사진 파일 목록 조회
    *
    * @Title : selectEduPhotoFileList
    * @Description : 프로그램 교육사진 파일 목록 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return List<PrgrmVo>
    */
    @Override
    public List<PrgrmVo> selectEduPhotoFileList(PrgrmVo prgrmVo) throws Exception {
        return prgrmDao.selectEduPhotoFileList(prgrmVo);
    }
}
