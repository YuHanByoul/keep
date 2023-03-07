package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.kbrainc.plum.front.mypage.exprtPool.service.MyRelationService;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 마이페이지 > 섭외관리 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - MyRelationDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyRelationDao
 * @Description : 마이페이지 > 섭외관리 Dao 인터페이스
 * @date : 2023. 03. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface MyRelationDao {
    public List<MyRelationVo> selectMyRelationList(MyRelationVo myRelationVo) throws Exception;

    public MyRelationVo selectMyRelation(MyRelationVo myRelationVo) throws Exception;

    int cancelRelation(MyRelationVo myRelationVo) throws Exception;

    int insertReview(ReviewVo reviewVo) throws Exception;

    int deleteReview(ReviewVo reviewVo) throws Exception;

    ReviewVo selectAddrByDmndid(Integer dmndid) throws Exception;

    ReviewVo selectReviewByDmndid(Integer dmndid) throws Exception;
}
