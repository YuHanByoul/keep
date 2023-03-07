package com.kbrainc.plum.front.enveduFlct.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 내 주변 환경교육시설 Dao
 *
 * <pre>
 * com.kbrainc.plum.front.enveduFlct.model
 * - EnveduFlctDao.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : EnveduFlctDao
 * @Description : 내 주변 환경교육시설 Dao
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface EnveduFlctDao {
    /**
     * 내 주변 환경교육 시설 목록을 반환한다
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 목록을 반환한다
     * @return 내 주변 환경교육 시설 목록
     * @throws Exception 예외
     */
    public List<Map<String, Object>> nearbyEnveduFlct(Map map) throws Exception;
    
    /**
     * 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     * @return int
     * @throws Exception 예외
     */
    public int nearbyEnveduFlctCount(Map map) throws Exception;
}
