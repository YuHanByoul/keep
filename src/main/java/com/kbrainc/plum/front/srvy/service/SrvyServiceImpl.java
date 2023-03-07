package com.kbrainc.plum.front.srvy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.type.Alias;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.srvy.model.SrvyDao;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnAnsVo;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnVo;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.mng.qestnr.model.QitemExVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
 * 
 * 설문조사 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.front.srvy.service
 * - SrvyServiceImpl.java
 * </pre> 
 *
 * @ClassName : SrvyServiceImpl
 * @Description : 설문조사 서비스 구현 
 * @author : KBRAINC
 * @date : 2023. 2. 28.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.SrvyServiceImpl")
@Alias("front.SrvyServiceImpl")
public class SrvyServiceImpl extends PlumAbstractServiceImpl implements SrvyService {
    
    @Resource(name = "front.SrvyDao")
    private SrvyDao srvyDao;
    
    
    /**
    * 설문 목록 조회
    *
    * @Title : selectSrvyList
    * @Description : 설문 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return List<srvyVo> 설문 목록
    * @throws Exception 예외
    */
    @Override
    public List<SrvyVo> selectSrvyList(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectSrvyList(srvyVo);
    }
    
    /**
    * 설문 정보 조회
    *
    * @Title : selectSrvyInfo
    * @Description : 설문 정보 조회
    * @param srvyVo SrvyVo 객체
    * @return SrvyVo SrvyVo 객체
    * @throws Exception 예외
    */
    @Override
    public SrvyVo selectSrvyInfo(SrvyVo srvyVo) throws Exception {
        return srvyDao.selectSrvyInfo(srvyVo);
    }
    
    /**
    * 설문 문항, 보기 조회
    *
    * @Title : selectQitemList
    * @Description : 설문 문항, 보기 조회
    * @param srvyVo SrvyVo 객체
    * @return List<QitemVo> 설문 문항, 보기 목록
    * @throws Exception 예외
    */
    @Override
    public List<QitemVo> selectQitemList(SrvyVo srvyVo) throws Exception {
        List<QitemVo> qitemList = srvyDao.selectQitemList(srvyVo);
        QitemVo qitemInfo;
        List<QitemExVo> exList;
        if(qitemList != null && qitemList.size() >0) {
            for(int i = 0; i < qitemList.size(); i++) {
                qitemInfo = qitemList.get(i);
                if(qitemInfo.getExCnt() > 0) {
                    exList = srvyDao.selectExList(qitemInfo);
                    qitemInfo.setExampleList(exList);
                }
            }
        }
        
        return qitemList;
    }
    
    /**
    * 설문 제출
    *
    * @Title : insertSrvySbmsn 
    * @Description : 설문 제출
    * @param srvySbmsnVo SrvySbmsnVo 객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int insertSrvySbmsn(HttpServletRequest request, SrvySbmsnVo srvySbmsnVo, List<SrvySbmsnAnsVo> srvySbmsnAnsList) throws Exception {
        int retVal = 0;
        // 아이피 확인
        srvySbmsnVo.setUserIp(CommonUtil.getClientIp(request));
        // 브라우저 확인
        String agent = request.getHeader("User-Agent");
        String browser = "";
        if(agent.indexOf("Trident") > -1) { // IE
            browser = "ie";
        } else if(agent.indexOf("Edge") > -1) { // Edge
            browser = "edge";
        } else if(agent.indexOf("Whale") > -1) { // Naver Whale
            browser = "whale";
        } else if(agent.indexOf("Opera") > -1 || agent.indexOf("OPR") > -1) { // Opera
            browser = "opera";
        } else if(agent.indexOf("Firefox") > -1) { // Firefox
            browser = "firefox";
        } else if(agent.indexOf("Safari") > -1 && agent.indexOf("Chrome") == -1 ) { // Safari
            browser = "safari";     
        } else if(agent.indexOf("Chrome") > -1) { // Chrome  
            browser = "chrome";
        }
        srvySbmsnVo.setBrwsrNm(browser);
        // 디바이스 확인
        Device device = new LiteDeviceResolver().resolveDevice(request);
        request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE,device);
        Device currentDevice  = DeviceUtils.getCurrentDevice(request);
        String deviceCd;
        if(currentDevice .isMobile()) {
            deviceCd = "MOBILE";
        } else if(currentDevice .isTablet()) {
            deviceCd = "TABLET";
        } else {
            deviceCd = "PC";
        }
        srvySbmsnVo.setDeviceKndCd(deviceCd);
        // 1. 설문 제출
        retVal += srvyDao.insertSrvySbmsn(srvySbmsnVo);
        
        //List<SrvySbmsnAnsVo> ansList = srvySbmsnVo.getAnsList();
        if(srvySbmsnAnsList != null && srvySbmsnAnsList.size() > 0) {
            SrvySbmsnAnsVo ans = new SrvySbmsnAnsVo();
            for(int i = 0; i < srvySbmsnAnsList.size(); i++) {
                ans = srvySbmsnAnsList.get(i);
                ans.setSbmsnid(srvySbmsnVo.getSbmsnid());
                ans.setUser(srvySbmsnVo.getUser());
                // 2. 답변 등록
                retVal += srvyDao.insertSrvySbmsnAns(ans);
            }
        }
        
        return retVal;
     }
    
    /**
    * 설문 답변 조회
    *
    * @Title : selectAnsList
    * @Description : 설문 답변 조회
    * @param srvyVo SrvyVo 객체
    * @return List<QitemVo> 설문 답변 목록
    * @throws Exception 예외
    */
    @Override
    public List<QitemVo> selectAnsList(SrvyVo srvyVo) throws Exception {
        List<QitemVo> qitemList = srvyDao.selectAnsList(srvyVo);
        QitemVo qitemInfo;
        List<QitemExVo> exList;
        if(qitemList != null && qitemList.size() >0) {
            for(int i = 0; i < qitemList.size(); i++) {
                qitemInfo = qitemList.get(i);
                if(qitemInfo.getExCnt() > 0) {
                    exList = srvyDao.selectExList(qitemInfo);
                    qitemInfo.setExampleList(exList);
                }
            }
        }
        
        return qitemList;
    }
    
}