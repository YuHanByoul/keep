package com.kbrainc.plum.front.pop.service;

import java.util.List;

import com.kbrainc.plum.front.pop.model.PopUpNtcVo;
import com.kbrainc.plum.rte.model.UserVo;

/**
 * 
 * 팝업 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.service
 * - PopService.java
 * </pre> 
 *
 * @ClassName : PopService
 * @Description : 팝업 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface PopService {

	/**
	 * @Title : insertPopUpNtc
	 * @Description :TB_PopUpNtc 테이블 인서트
	 * @param PopUpNtcVo 타입의 인자
	 * @throws Exception
	 * @return int
	 */
	public int insertPopUpNtc(PopUpNtcVo paramVO) throws Exception;
	  
	/**
	 * @Title : updatePopUpNtc
	 * @Description :TB_PopUpNtc 테이블 update
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
	 * @Description :TB_PopUpNtc 테이블 row delete
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
	public List<PopUpNtcVo> getDataForCommnonPopup(PopUpNtcVo paramVO, UserVo user) throws Exception;
	
	
	
}