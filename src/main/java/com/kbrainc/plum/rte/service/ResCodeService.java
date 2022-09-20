package com.kbrainc.plum.rte.service;

import java.util.List;

import com.kbrainc.plum.rte.model.CodeInfoVo;

/**
* 
* 코드정보를 메모리에 적재 / 조회하는 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.rte.service
* - ResCodeService.java
* </pre> 
*
* @ClassName : ResCodeService
* @Description : 코드정보를 메모리에 적재 / 조회하는 서비스 인터페이스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAINC. All Rights Reserved
*/
public interface ResCodeService {

    /**
    * 코드정보를 리로드 한다.
    * 
    * @Title : reloadCodeInfo
    * @Description : 코드정보를 리로드 한다.
    * @return boolean 호출성공
    * @throws Exception 예외
    */
    public boolean reloadCodeInfo() throws Exception;

    /**
    * 그룹코드아이디에 해당하는 1depth 코드 목록을 반환한다.
    * 
    * @Title : getCodeList
    * @Description : 그룹코드아이디에 해당하는 1depth 코드 목록을 반환한다.
    * @param grpcdid 그룹코드아이디
    * @return List<CodeInfoVo> 그룹코드아이디에 해당하는 1depth 코드 목록
    * @throws Exception 예외
    */
    public List<CodeInfoVo> getCodeList(String grpcdid) throws Exception;

    /**
    * 그룹코드아이디에 해당하는 코드 목록을 반환한다.
    * 
    * @Title : getCodeList
    * @Description : 그룹코드아이디에 해당하는 코드 목록을 반환한다.
    * @param grpcdid  그룹코드아이디
    * @param upprCdid 상위코드아이디
    * @return List<CodeInfoVo> 그룹코드아이디에 해당하는 코드 목록
    * @throws Exception 예외
    */
    public List<CodeInfoVo> getCodeList(String grpcdid, String upprCdid) throws Exception;

    /**
    * 코드아이디에 해당하는 코드정보를 반환한다.
    * 
    * @Title : getCodeInfo
    * @Description : 코드아이디에 해당하는 코드정보를 반환한다.
    * @param cdid 코드그룹아이디|코드아이디
    * @return CodeInfoVo 코드정보
    * @throws Exception 예외
    */
    public CodeInfoVo getCodeInfo(String cdid) throws Exception;

    /**
    * 그룹코드아이디에 해당하는 모든 하위 코드 목록를 반환한다.
    * 
    * @Title : selectCdgrpidAndUpprcdList
    * @Description : 그룹코드아이디에 해당하는 모든 하위 코드 목록를 반환한다.
    * @param cdgrpid 코드그룹아이디
    * @return List<CodeInfoVo> 그룹코드아이디에 해당하는 모든 하위 코드 목록
    * @throws Exception 예외
    */
    public List<CodeInfoVo> selectCdgrpidAndUpprcdList(String cdgrpid) throws Exception;
   
    /**
    * 코드아이디에 해당하는 코드이름을 반환한다.
    * 
    * @Title : getCodeName
    * @Description : 코드아이디에 해당하는 코드이름을 반환한다.
    * @param cdid 코드그룹아이디|코드아이디
    * @throws Exception 예외
    * @return String 코드이름
    */
    public String getCodeName(String cdid) throws Exception;
    
    
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
    public void removeCacheForCdgrp(String cdgrpid, String upprCd) throws Exception;
}