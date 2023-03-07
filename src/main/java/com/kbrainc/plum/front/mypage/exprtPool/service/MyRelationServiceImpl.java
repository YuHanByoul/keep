package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.LctrDmndDao;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.service.LctrDmndService;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyRelationDao;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyRelationVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.ReviewVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 > 전문가 섭외 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyRelationServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyRelationServiceImpl
 * @Description : 마이페이지 > 전문가 섭외 관리 서비스 구현 클래스
 * @date : 2023. 03. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class MyRelationServiceImpl extends PlumAbstractServiceImpl implements MyRelationService {

    @Autowired
    private MyRelationDao myRelationDao;

    @Autowired
    private FileDao fileDao;

    @Resource(name = "front.lctrDmndService")
    private LctrDmndService lctrDmndService;

    @Override
    public List<MyRelationVo> selectMyRelationList(MyRelationVo myRelationVo) throws Exception {
        return myRelationDao.selectMyRelationList(myRelationVo);
    }

    @Override
    public Map<String,Object> selectMyRelation(MyRelationVo myRelationVo) throws Exception {
        Map<String,Object> result = new HashMap<>();

        MyRelationVo myRelation = myRelationDao.selectMyRelation(myRelationVo);

        if(myRelation != null && !myRelation.getFilegrpid().equals("0")) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(myRelation.getFilegrpid());
                ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            myRelation.setFileList(fileList);
        }

        ExprtVo exprtVo = new ExprtVo();
        exprtVo.setUserid(myRelation.getExprtid());
        ExprtVo exprt = lctrDmndService.selectExprt(exprtVo);

        result.put("myRelation",myRelation);
        result.put("exprt", exprt);
        return result;
    }

    @Override
    public int cancelRelation(MyRelationVo myRelationVo) throws Exception {
        int retVal = 0;
        retVal += myRelationDao.cancelRelation(myRelationVo);
        return retVal;
    }

    @Override
    public int insertReview(ReviewVo reviewVo) throws Exception {
        int retVal = 0;
        retVal += myRelationDao.insertReview(reviewVo);
        return retVal;
    }

    @Override
    public int deleteReview(ReviewVo reviewVo) throws Exception {
        int retVal = 0;
        retVal += myRelationDao.deleteReview(reviewVo);
        return retVal;
    }

    @Override
    public ReviewVo selectAddrByDmndid(Integer dmndid) throws Exception {
        return myRelationDao.selectAddrByDmndid(dmndid);
    }

    @Override
    public ReviewVo selectReviewByDmndid(Integer dmndid) throws Exception {
        return myRelationDao.selectReviewByDmndid(dmndid);
    }
}
