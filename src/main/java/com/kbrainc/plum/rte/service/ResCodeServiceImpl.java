package com.kbrainc.plum.rte.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ResCodeDao;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
* 
* 코드정보를 메모리에 적재 / 조회하는 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.rte.service
* - ResCodeServiceImpl.java
* </pre> 
*
* @ClassName : ResCodeServiceImpl
* @Description : 코드정보를 메모리에 적재 / 조회하는 서비스 구현 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAINC. All Rights Reserved
*/
@Service
public class ResCodeServiceImpl extends PlumAbstractServiceImpl implements ResCodeService {

    /** 코드목록정보 */
    Map<String, List<CodeInfoVo>> codeListMap;

    /** 코드정보 */
    Map<String, CodeInfoVo> codeMap;

    @Autowired
    private ResCodeDao resCodeDao;

    @Autowired
    CacheManager cacheManager;
    
    /**
    * 코드정보를 리로드 한다.
    * 
    * @Title : reloadCodeInfo
    * @Description : 코드정보를 리로드 한다.
    * @return boolean 호출성공
    * @throws Exception 예외
    */
    @Override
    public boolean reloadCodeInfo() throws Exception {
        synchronized (this) {
        	
	        Ehcache codeMap = (Ehcache) cacheManager.getCache("codeMap");
	        Ehcache codeListMap = (Ehcache) cacheManager.getCache("codeListMap");
	        codeMap.removeAll(true);
	        codeListMap.removeAll(true);

	        List<CodeInfoVo> allCodeInfoList = resCodeDao.selectAllCodeInfoList();
	        List<CodeInfoVo> CodeInfoList = null;
	        Element element = null;

	        for (CodeInfoVo codeInfo : allCodeInfoList) {
	            codeMap.put(new Element("CODE|" + codeInfo.getCd(), codeInfo), true);
	            element = codeListMap.get(codeInfo.getCdgrpid() + "|" + codeInfo.getUppr_cd());
	            
	            if (element == null) {
	                CodeInfoList = new ArrayList<CodeInfoVo>();
	                CodeInfoList.add(codeInfo);
	                codeListMap.put(new Element(codeInfo.getCdgrpid() + "|" + codeInfo.getUppr_cd(), CodeInfoList), true);
	            } else {
	                CodeInfoList = (List<CodeInfoVo>) element.getObjectValue();    
	                CodeInfoList.add(codeInfo);
	                codeListMap.put(new Element(codeInfo.getCdgrpid() + "|" + codeInfo.getUppr_cd(), CodeInfoList), true);
	            }
	        }
        }
        return true;
    }

    /**
    * 그룹코드아이디에 해당하는 1depth 코드 목록을 반환한다.
    * 
    * @Title : getCodeList
    * @Description : 그룹코드아이디에 해당하는 1depth 코드 목록을 반환한다.
    * @param cdgrpid 코드그룹아이디
    * @return List<CodeInfoVo> 코드그룹아이디에 해당하는 1depth 코드 목록
    * @throws Exception 예외
    */
    @Override
    @Cacheable(value = "codeListMap", key = "#cdgrpid.concat('|0')")
    public List<CodeInfoVo> getCodeList(String cdgrpid) throws Exception {
        return getCodeList(cdgrpid, "0");
    }

    /**
    * 그룹코드아이디에 해당하는 코드 목록을 반환한다.
    * 
    * @Title : getCodeList
    * @Description : 그룹코드아이디에 해당하는 코드 목록을 반환한다.
    * @param cdgrpid 그룹코드아이디
    * @param upprCd  상위코드
    * @return List<CodeInfoVo> 그룹코드아이디에 해당하는 코드 목록
    * @throws Exception 예외
    */
    @Override
    @Cacheable(value = "codeListMap", key = "#cdgrpid.concat('|').concat(#upprCd)")
    public List<CodeInfoVo> getCodeList(String cdgrpid, String upprCd) throws Exception {
    	CodeInfoVo CodeInfoVo = new CodeInfoVo();
        CodeInfoVo.setCdgrpid(cdgrpid);
        CodeInfoVo.setUppr_cd(upprCd);
        return resCodeDao.selectCodeList(CodeInfoVo);
    }

    /**
    * 코드에 해당하는 코드정보를 반환한다.
    * 
    * @Title : getCodeInfo
    * @Description : 코드에 해당하는 코드정보를 반환한다.
    * @param cd 코드그룹아이디|코드아이디
    * @return CodeInfoVo 코드정보
    * @throws Exception 예외
    */
    @Override
    @Cacheable(value = "codeMap", key = "#cd")
    public CodeInfoVo getCodeInfo(String cd) throws Exception {
    	return resCodeDao.selectCodeInfo(cd);
    }
    
    /**
    * 그룹코드아이디에 해당하는 모든 하위 코드 목록를 반환한다.
    * 
    * @Title : selectCdgrpidAndUpprcdList
    * @Description : 그룹코드아이디에 해당하는 모든 하위 코드 목록를 반환한다.
    * @param cdgrpid 코드그룹아이디
    * @return List<CodeInfoVo> 그룹코드아이디에 해당하는 모든 하위 코드 목록
    * @throws Exception 예이
    */
    public List<CodeInfoVo> selectCdgrpidAndUpprcdList(String cdgrpid) throws Exception {
        return resCodeDao.selectCdgrpidAndUpprcdList(cdgrpid);
    }
    
    /**
    * 코드아이디에 해당하는 코드이름을 반환한다.
    * 
    * @Title : getCodeName
    * @Description : 코드아이디에 해당하는 코드이름을 반환한다.
    * @param cd 코드그룹아이디|코드아이디
    * @return String 코드이름
    * @throws Exception 예외
    */
    @Override
    public String getCodeName(String cd) throws Exception {
        CodeInfoVo codeInfo = codeMap.get(cd);
        if (codeInfo == null) {
            return "";
        }
        return codeInfo.getCd_nm();
    }
    
    /**
    * 코드그룹이하 모든 코드를 캐시에서 삭제한다.
    *
    * @Title       : removeCacheForCdgrp 
    * @Description : 코드그룹이하 모든 코드를 캐시에서 삭제한다(코드그룹캐시포함).
    * @param cdgrpid 코드그룹아이디
    * @param upprCd 상위코드아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    public void removeCacheForCdgrp(String cdgrpid, String upprCd) throws Exception {
        Ehcache codeListMap = (Ehcache)cacheManager.getCache("codeListMap").getNativeCache();
        Element element = codeListMap.get(cdgrpid + "|" + upprCd);
        
        if (element != null) {
            Ehcache codeMap = (Ehcache)cacheManager.getCache("codeMap").getNativeCache();
            List<CodeInfoVo> codeList = (List<CodeInfoVo>) element.getObjectValue();   

            for (CodeInfoVo codeInfo : codeList) {
                codeMap.remove("CODE|" + codeInfo.getCd());
            }
            codeListMap.remove(cdgrpid + "|" + upprCd);
        }
    }
}