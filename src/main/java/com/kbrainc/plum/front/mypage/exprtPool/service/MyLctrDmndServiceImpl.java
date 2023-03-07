package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyLctrDmndDao;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyLctrDmndVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 마이페이지 > 전문가 요청 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyLctrDmndServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyLctrDmndServiceImpl
 * @Description : 마이페이지 > 전문가 요청 관리 서비스 구현 클래스
 * @date : 2023. 03. 06.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class MyLctrDmndServiceImpl extends PlumAbstractServiceImpl implements MyLctrDmndService {

    @Autowired
    private MyLctrDmndDao myLctrDmndDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 강의 요청서 목록 조회
     *
     * @param myLctrDmndVo
     * @return list
     * @throws Exception
     * @Title : selectLctrDmndList
     * @Description : 강의 요청서 목록 조회
     */
    @Override
    public List<MyLctrDmndVo> selectLctrDmndList(MyLctrDmndVo myLctrDmndVo) throws Exception {
        return myLctrDmndDao.selectLctrDmndList(myLctrDmndVo);
    }

    /**
     * 강의 요청서 상세 조회
     *
     * @param myLctrDmndVo
     * @return my lctr dmnd vo
     * @throws Exception
     * @Title : selectLctrDmnd
     * @Description : 강의 요청서 상세 조회
     */
    @Override
    public MyLctrDmndVo selectLctrDmnd(MyLctrDmndVo myLctrDmndVo) throws Exception {
        MyLctrDmndVo lctrDmnd = myLctrDmndDao.selectLctrDmnd(myLctrDmndVo);

        if (lctrDmnd != null && !lctrDmnd.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(lctrDmnd.getFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            lctrDmnd.setFileList(fileList);
        }

        return lctrDmnd;
    }

    /**
     * 강의 요청서 상태 변경
     *
     * @param myLctrDmndVo
     * @return int
     * @throws Exception
     * @Title : updateStatus
     * @Description : 강의 요청서 상태 변경
     */
    @Override
    @Transactional
    public int updateStatus(MyLctrDmndVo myLctrDmndVo) throws Exception {
        return myLctrDmndDao.updateStatus(myLctrDmndVo);
    }
}
