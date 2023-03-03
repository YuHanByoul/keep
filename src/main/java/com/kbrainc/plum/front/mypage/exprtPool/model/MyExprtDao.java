package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.kbrainc.plum.front.exprtPool.register.model.CareerVo;
import com.kbrainc.plum.front.exprtPool.register.model.CrtfctVo;
import com.kbrainc.plum.front.exprtPool.register.model.HdofVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 마이페이지 > 환경교육 전문가 >  풀 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - MyExprtDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyExprtDao
 * @Description : 마이페이지 > 환경교육 전문가 풀 인터페이스
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface MyExprtDao {
    /**
     * 전문가 정보 조회
     *
     * @param exprtVo
     * @return my exprt vo
     * @Title : selectMyExprt
     * @Description : 전문가 정보 조회
     */
    public MyExprtVo selectMyExprt(CommonExprtVo exprtVo) throws Exception;

    /**
     * 수정 화면에서 사용되는 전문가 정보 조회
     *
     * @param myExprtMdfcnVo
     * @return my exprt mdfcn vo
     * @throws Exception
     * @Title : selectMyExprtMdfcn
     * @Description : 수정 화면에서 사용되는 전문가 정보 조회
     */
    public MyExprtMdfcnVo selectMyExprtMdfcn(CommonExprtVo myExprtMdfcnVo) throws Exception;

    /**
     * 재직 정보 목록 조회
     *
     * @param exprtVo
     * @return list
     * @Title : selectExpertHdofList
     * @Description : 재직 정보 목록 조회
     */
    List<MyHdofVo> selectExpertHdofList(CommonExprtVo exprtVo);

    /**
     * 경력 정보 목록 조회
     *
     * @param exprtVo
     * @return list
     * @Title : selectExpertCareerList
     * @Description : 경력 정보 목록 조회
     */
    List<MyCareerVo> selectExpertCareerList(CommonExprtVo exprtVo);

    /**
     * 자격 정보 목록 조회
     *
     * @param exprtVo
     * @return list
     * @Title : selectExpertCrtfctList
     * @Description : 자격 정보 목록 조회
     */
    List<MyCrtfctVo> selectExpertCrtfctList(CommonExprtVo exprtVo);

    /**
     * 공개 범위 및 서비스 수신 여부 변경
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : updateRlsAndRcptn
     * @Description : 공개 범위 및 서비스 수신 여부 변경
     */
    public int updateRlsAndRcptn(MyExprtVo exprtVo) throws Exception;
}
