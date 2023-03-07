package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyRelationDao;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyRelationVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.ReviewVo;

import java.util.List;
import java.util.Map;

/**
 * 마이페이지 > 섭외 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyRelationService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyRelationService
 * @Description : 마이페이지 > 섭외 관리 서비스 인터페이스
 * @date : 2023. 03. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface MyRelationService {
    public List<MyRelationVo> selectMyRelationList(MyRelationVo myRelationVo) throws Exception;

    public Map<String,Object> selectMyRelation(MyRelationVo myRelationVo) throws Exception;

    int cancelRelation(MyRelationVo myRelationVo) throws Exception;

    int insertReview(ReviewVo reviewVo) throws Exception;

    int deleteReview(ReviewVo reviewVo) throws Exception;

    ReviewVo selectAddrByDmndid(Integer dmndid) throws Exception;

    ReviewVo selectReviewByDmndid(Integer dmndid) throws Exception;
}
