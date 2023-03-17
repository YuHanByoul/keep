package com.kbrainc.plum.front.inqry.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.inqry.model.InqryAnsVo;
import com.kbrainc.plum.front.inqry.model.InqryDao;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 1:1문의 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.service
 * - InqryServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryServiceImpl
 * @Description : 1:1문의 서비스 구현 클래스
 * @date : 2023. 02. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.inqryService")
@Alias("front.inqryService")
public class InqryServiceImpl extends PlumAbstractServiceImpl implements InqryService {

    @Resource(name = "front.inqryDao")
    private InqryDao inqryDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 1:1문의 목록 조회
     *
     * @param inqryVo
     * @return list
     * @throws Exception
     * @Title : selectInqryList
     * @Description : 1:1문의 목록 조회
     */
    @Override
    public List<InqryVo> selectInqryList(InqryVo inqryVo) throws Exception {
        return inqryDao.selectInqryList(inqryVo);
    }

    /**
     * 마이페이지 > 1:1문의 목록 조회
     *
     * @param inqryVo
     * @return list
     * @throws Exception
     * @Title : selectMypageInqryList
     * @Description : 마이페이지 > 1:1문의 목록 조회
     */
    @Override
    public List<InqryVo> selectMypageInqryList(InqryVo inqryVo) throws Exception {
        return inqryDao.selectMypageInqryList(inqryVo);
    }

    /**
     * 1:1문의 상세 조회
     *
     * @param inqryVo
     * @return inqry vo
     * @throws Exception
     * @Title : selectInqry
     * @Description : 1:1문의 상세 조회
     */
    @Override
    public InqryVo selectInqry(InqryVo inqryVo) throws Exception {
        InqryVo inqry = inqryDao.selectInqry(inqryVo);

        if(inqry != null && !inqry.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(inqry.getFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            inqry.setFileList(fileList);
        }

        return inqry;

    }

    /**
     * 1:1문의 답변 조회
     *
     * @param inqryVo
     * @return inqry ans vo
     * @throws Exception
     * @Title : selectInqryAns
     * @Description : 1:1문의 답변 조회
     */
    @Override
    public InqryAnsVo selectInqryAns(InqryVo inqryVo) throws Exception {
        InqryAnsVo inqryAns = inqryDao.selectInqryAns(inqryVo);
        if(inqryAns != null && inqryAns.getFilegrpid() != null && !inqryAns.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(inqryAns.getFilegrpid()));
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            inqryAns.setFileList(fileList);
        }
        return inqryAns;
    }

    /**
     * 1:1문의 생성
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : insertInqry
     * @Description : 1:1문의 생성
     */
    @Override
    public int insertInqry(InqryVo inqryVo) throws Exception {
        int retVal = 0;
        retVal += inqryDao.insertInqry(inqryVo);
        return retVal;
    }

    /**
     * 1:1문의 수정
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : updateInqry
     * @Description : 1:1문의 수정
     */
    @Override
    public int updateInqry(InqryVo inqryVo) throws Exception {
        int retVal = 0;
        retVal += inqryDao.updateInqry(inqryVo);
        return retVal;
    }

    /**
     * 1:1문의 삭제
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : deleteInqry
     * @Description : 1:1문의 삭제
     */
    @Override
    public int deleteInqry(InqryVo inqryVo) throws Exception {
        int retVal = 0;
        retVal += inqryDao.deleteInqry(inqryVo);
        return retVal;
    }

    /**
     * 1:1문의 취소
     *
     * @param inqryVo
     * @return int
     * @throws Exception
     * @Title : cancelInqry
     * @Description : 1:1문의 취소
     */
    @Override
    public int cancelInqry(InqryVo inqryVo) throws Exception {
        int retVal = 0;
        retVal += inqryDao.cancelInqry(inqryVo);
        return retVal;
    }
}
