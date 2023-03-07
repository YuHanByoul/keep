package com.kbrainc.plum.cmm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kbrainc.plum.cmm.model.CommonDao;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;

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
    
    /**
    * 현재 사용자의 접근가능한 사이트목록을 반환한다.
    *
    * @Title : selectAlowedSiteList
    * @Description : 현재 사용자의 접근가능한 사이트목록을 반환한다.
    * @param sysSeCd 시스템구분코드
    * @return List<Map<String,Object>> 사이트목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedSiteList(String sysSeCd) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        UserVo userInfo = (UserVo)session.getAttribute("user");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user", userInfo);
        param.put("sysSeCd", sysSeCd);
        return commonDao.selectAlowedSiteList(param);
    }
    
    /**
    * 로그인 성공 후처리.
    *
    * @Title : insertLoginSuccess
    * @Description : 로그인 성공 후처리를 한다.
    * @param request 요청객체
    * @param userid 사용자아이디
    * @return int insert로우수
    * @throws Exception 예외
    */
    @Transactional
    public int insertLoginSuccess(HttpServletRequest request, String userid) throws Exception {
        int retVal = 0;
        Map<String, String> param = new HashMap<String, String>();
        
        param.put("userid", userid);
        Device device = new LiteDeviceResolver().resolveDevice(request);
        request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE,device);
        Device currentDevice  = DeviceUtils.getCurrentDevice(request);
        if(currentDevice .isMobile()){
            param.put("deviceCd", "PHONE");
        }else if(currentDevice .isTablet()){
            param.put("deviceCd", "PAD");
        }else{
            param.put("deviceCd", "PC");
        }
        param.put("ipAddr", CommonUtil.getClientIp(request));
        
        retVal = commonDao.insertLoginDescription(param);
        retVal += commonDao.updateUserLgnInfo(userid);
        
        return retVal;
    }
    
    /**
    * 로그인 실패 후처리.
    *
    * @Title : insertLoginFailDescription
    * @Description : 로그인 실패 후처리를 한다.
    * @param request 요청객체
    * @param userid 사용자아이디
    * @return int insert로우수
    * @throws Exception 예외
    */
    @Transactional
    public int insertLoginFail(HttpServletRequest request, String userid) throws Exception {
        int retVal = 0;
        Map<String, String> param = new HashMap<String, String>();
        
        param.put("userid", userid);
        Device device = new LiteDeviceResolver().resolveDevice(request);
        request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE,device);
        Device currentDevice  = DeviceUtils.getCurrentDevice(request);
        if(currentDevice .isMobile()){
            param.put("deviceCd", "PHONE");
        }else if(currentDevice .isTablet()){
            param.put("deviceCd", "PAD");
        }else{
            param.put("deviceCd", "PC");
        }
        param.put("ipAddr", CommonUtil.getClientIp(request));
        
        commonDao.insertLoginFailDescription(param);
        commonDao.updateLgnFailCntPlusOne(userid);
        commonDao.updateAccountLock(userid);
        
        return retVal;
    }
    
    /**
    * 전체 지역목록을 반환한다.
    *
    * @Title : selectAllRgnList
    * @Description : 전체 지역목록을 반환한다.
    * @return List<Map<String,Object>> 지역목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAllRgnList() throws Exception {
        return commonDao.selectAllRgnList();
    }
    
    /**
    * 시도 지역목록을 반환한다.
    *
    * @Title : selectCtprvnList
    * @Description : 시도 지역목록을 반환한다.
    * @return List<Map<String,Object>> 지역목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectCtprvnList() throws Exception{
        return commonDao.selectCtprvnList();
    }

    /**
     * 내 주변 환경교육 시설 목록을 반환한다
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 목록을 반환한다
     * @return 내 주변 환경교육 시설 목록
     * @throws Exception 예외
     */
    public List<Map<String, Object>> nearbyEnveduFlct(Map map) throws Exception {
        return commonDao.nearbyEnveduFlct(map);
    }
    
    /**
     * 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     * @return int
     * @throws Exception 예외
     */
    public int nearbyEnveduFlctCount(Map map) throws Exception{
        return commonDao.nearbyEnveduFlctCount(map);
    };
}
