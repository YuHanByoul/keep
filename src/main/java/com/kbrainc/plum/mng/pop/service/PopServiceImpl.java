package com.kbrainc.plum.mng.pop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.mng.pop.model.PopDao;
import com.kbrainc.plum.mng.pop.model.PopUpNtcVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * PopServiceImpl
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.service
 * - PopServiceImpl.java
 * </pre> 
 *
 * @ClassName : PopServiceImpl
 * @Description : PopServiceImpl
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class PopServiceImpl extends PlumAbstractServiceImpl implements PopService {

	@Autowired
	PopDao popDao;

	@Autowired
	private FileDao fileDao;

	/**
	 * @Title : insertPopUpNtc
	 * @Description :TB_PopUpNtc 테이블 인서트
	 * @param PopUpNtcVo 타입의 인자
	 * @throws Exception
	 * @return int
	 */
	public int insertPopUpNtc(PopUpNtcVo paramVO) throws Exception {
		return popDao.insertPopUpNtc(paramVO);
	}

	/**
	 * @Title : updatePopUpNtc
	 * @Description :TB_PopUpNtc 테이블 update
	 * @param PopUpNtcVo 타입의 인자
	 * @throws Exception
	 * @return int
	 */
	public int updatePopUpNtc(PopUpNtcVo paramVO) throws Exception {
		return popDao.updatePopUpNtc(paramVO);
	}

	/**
	 * @Title : selectPopUpNtcList
	 * @Description :selectPopUpNtcList
	 * 
	 * @param PopUpNtcVo 타입의 인자
	 * @throws Exception
	 * @return List
	 */
	public List<PopUpNtcVo> selectPopUpNtcList(PopUpNtcVo paramVO) throws Exception {
		return popDao.selectPopUpNtcList(paramVO);
	}

	/**
	 * @Title : selectPopUpNtc
	 * @Description :selectPopUpNtc 단일항 셀렉트
	 * @param PopUpNtcVo 타입의 인자
	 * @throws Exception
	 * @return List
	 */
	public PopUpNtcVo selectPopUpNtc(PopUpNtcVo paramVO) throws Exception {
		return popDao.selectPopUpNtc(paramVO);
	}

	/**
	 * @Title : deletePopUpNtc
	 * @Description :TB_PopUpNtc 테이블 row delete
	 * @param PopUpNtcVo 타입의 인자
	 * @throws Exception
	 * @return int
	 */
	public int deletePopUpNtc(PopUpNtcVo paramVO) throws Exception {
		return popDao.deletePopUpNtc(paramVO);
	}

	/**
	 * @Title : getDataForCommnonPopup
	 * @Description :getDataForCommnonPopup
	 * @param PopUpNtcVo 타입의 인자
	 * @throws Exception
	 * @return List
	 */
	public List<PopUpNtcVo> getDataForCommnonPopup(PopUpNtcVo paramVO, @UserInfo UserVo user) throws Exception {

		List<PopUpNtcVo> list = new ArrayList();
		list = popDao.getDataForCommnonPopup(paramVO);
		List<PopUpNtcVo> returnList = new ArrayList();

		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {

				boolean logYnMapping = true;
				boolean roleMapping = false;
				boolean checkRoleMapping = false;

				if (list.get(i).getLogin_need_yn().equals("Y")) {
					if (user != null && user.getUserid().equals(null) || user.getUserid().equals("")) {
						logYnMapping = false;
					}
				}

				 if(list.get(i).getNtc_trgt_roleid() != null || list.get(i).getNtc_trgt_cd().equals(108102) ) {
					checkRoleMapping = true;
					if (user != null && user.getAuthorities().size() > 0) {
						for (int x = 0; x < user.getAuthorities().size(); x++) {
							if ((list.get(i).getNtc_trgt_roleid())
									.equals(Integer.parseInt(user.getAuthorities().get(x).get("roleid")))) {
								roleMapping = true;
							}
						}

					}
				}
				if((checkRoleMapping == true && roleMapping == true && logYnMapping ==true) || (checkRoleMapping == false && logYnMapping ==true )) {
				   returnList.add(list.get(i));
				}
			}
		}

		return returnList;
	};
}
