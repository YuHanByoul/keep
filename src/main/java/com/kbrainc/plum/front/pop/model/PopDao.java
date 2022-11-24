package com.kbrainc.plum.front.pop.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 팝업공지관리 DAO 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.model
 * - PopDao.java
 * </pre> 
 *
 * @ClassName : PopDao
 * @Description : 팝업공지관리 DAO 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper("front.popDao")
public interface PopDao {

    /**
     * @Title : insertPopUpNtc
     * @Description :TB_CMM_PopUpNtc 테이블 인서트
     * @param PopUpNtcVo 타입의 인자
     * @throws Exception
     * @return int
     */
    public int insertPopUpNtc(PopUpNtcVo paramVO) throws Exception;

    /**
     * @Title : updatePopUpNtc
     * @Description :TB_CMM_PopUpNtc 테이블 update
     * @param PopUpNtcVo 타입의 인자
     * @throws Exception
     * @return int
     */
    public int updatePopUpNtc(PopUpNtcVo paramVO) throws Exception;

    /**
     * @Title : selectPopUpNtcList
     * @Description :selectPopUpNtcList
     * @param PopUpNtcVo 타입의 인자
     * @throws Exception
     * @return List
     */
    public List<PopUpNtcVo> selectPopUpNtcList(PopUpNtcVo paramVO) throws Exception;

    /**
     * @Title : selectPopUpNtc
     * @Description :selectPopUpNtc 단일항 셀렉트
     * @param PopUpNtcVo 타입의 인자
     * @throws Exception
     * @return List
     */
    public PopUpNtcVo selectPopUpNtc(PopUpNtcVo paramVO) throws Exception;

    /**
     * @Title : deletePopUpNtc
     * @Description :TB_CMM_PopUpNtc 테이블 row delete
     * @param PopUpNtcVo 타입의 인자
     * @throws Exception
     * @return int
     */
    public int deletePopUpNtc(PopUpNtcVo paramVO) throws Exception;
    
     /**
     * @Title : getDataForCommnonPopup
     * @Description :getDataForCommnonPopup
     * @param PopUpNtcVo 타입의 인자
     * @throws Exception
     * @return List
     */
    public List<PopUpNtcVo> getDataForCommnonPopup(PopUpNtcVo paramVO) throws Exception;
    
    
    

}