package com.kbrainc.plum.cmm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kbrainc.plum.cmm.model.CommonDao;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.model.RoleInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 어플리케이션 전체의 공통 요청을 처리하는 서비스 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - CommonServiceImpl.java
 * </pre> 
 *
 * @ClassName : CommonServiceImpl
 * @Description : 어플리케이션 전체의 공통 요청을 처리하는 서비스 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class CommonServiceImpl extends PlumAbstractServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    /**
    * 사이트 리스트를 반환한다.
    *
    * @Title       : selectSiteList 
    * @Description : 사이트 리스트를 반환한다.
    * @param site SiteVo객체
    * @return List<SiteVo> 사이트정보 목록
    * @throws Exception 예외
    */
    public List<SiteVo> selectSiteList(SiteVo site) throws Exception {
        return commonDao.selectSiteList(site);
    }
    
    /**
    * 현재 사용자의 접근가능한 기관목록을 반환한다.
    *
    * @Title : selectAlowedInstList
    * @Description : 현재 사용자의 접근가능한 기관목록을 반환한다.
    * @return List<Map<String,Object>> 기관목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedInstList() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        UserVo userInfo = (UserVo)session.getAttribute("user");
        return commonDao.selectAlowedInstList(userInfo);
    }
}
