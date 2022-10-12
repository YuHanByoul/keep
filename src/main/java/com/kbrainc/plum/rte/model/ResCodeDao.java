package com.kbrainc.plum.rte.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 코드정보를 메모리에 적재하기 위한 DAO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - ResCodeDao.java
 * </pre> 
 *
 * @ClassName : ResCodeDao
 * @Description : 코드정보를 메모리에 적재하기 위한 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ResCodeDao {

    /**
     * @Title : selectAllCodeInfoList
     * @Description : 전체 코드 목록을 돌려준다
     * @return List CodeInfoVo  전체 코드 목록
     */
    public List<CodeInfoVo> selectAllCodeInfoList();
    
    /**
     * @Title : selectCodeList
     * @Description : 그룹코드아이디에 해당하는 코드 목록을 반환한다.
     * @param codeInfoVo CodeInfoVo객체
     * @return List<CodeInfoVo> 그룹코드아이디에 해당하는 코드 목록
     */
     public List<CodeInfoVo> selectCodeList(CodeInfoVo codeInfoVo) throws Exception;

    /**
     * @Title : selectCodeInfo
     * @Description : 코드아이디에 해당하는 코드정보를 반환한다.
     * @param cdid 코드그룹아이디|코드아이디
     * @throws Exception
     * @return CodeInfoVo 코드정보
     */
    public CodeInfoVo selectCodeInfo(String cdid) throws Exception;
    
    /**
    * @Title : selectCdgrpidAndUpprcdList
    * @Description : 그룹코드아이디에 해당하는 모든 하위 코드 목록를 반환한다.
    * @param cdgrpid 코드그룹아이디
    * @throws Exception
    * @return List<CodeInfoVo> 그룹코드아이디에 해당하는 모든 하위 코드 목록
    */
    public List<CodeInfoVo> selectCdgrpidAndUpprcdList(String cdgrpid) throws Exception;
}