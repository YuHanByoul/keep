package com.kbrainc.plum.front.instInfo.service;

import java.util.List;

import com.kbrainc.plum.front.envEdu.model.PrgrmVo;
import com.kbrainc.plum.front.instInfo.model.InstInfoVo;
import com.kbrainc.plum.front.instInfo.model.InstPicVo;

/**
* 기관정보 Service 클래스
*
* <pre>
* com.kbrainc.plum.front.instInfo.service
* - InstInfoService.java
* </pre>
*
* @ClassName : InstInfoService
* @Description : TODO
* @author : JD
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface InstInfoService {

    /**
    * 기관정보 조회
    *
    * @Title : selectInstInfo
    * @Description : 기관정보 조회
    * @param instInfoVo
    * @throws Exception
    * @return InstInfoVo
    */
    public InstInfoVo selectInstInfo(InstInfoVo instInfoVo) throws Exception;
    
    /**
    * 기관정보 수정
    *
    * @Title : updateInstInfo
    * @Description : 기관정보 수정
    * @param instInfoVo
    * @throws Exception
    * @return int
    */
    public int updateInstInfo(InstInfoVo instInfoVo) throws Exception;

    /**
    * 기관 담당자 목록 조회
    *
    * @Title : selectInstPictList
    * @Description : 기관 담당자 목록 조회
    * @param instPicVo
    * @throws Exception
    * @return List<InstPicVo>
    */
    public List<InstPicVo> selectInstPictList(InstPicVo instPicVo) throws Exception;

    /**
    * 기관 담당자 정보 조회
    *
    * @Title : selectInstPicInfo
    * @Description : 기관 담당자 정보 조회
    * @param instPicVo
    * @throws Exception
    * @return InstPicVo
    */
    public InstPicVo selectInstPicInfo(InstPicVo instPicVo) throws Exception;

    /**
    * 기관 담당자 등록 > 회원 목록 조회
    *
    * @Title : selectPicSearchList
    * @Description : 기관 담당자 등록 > 회원 목록 조회
    * @param instPicVo
    * @throws Exception
    * @return List<InstPicVo>
    */
    public List<InstPicVo> selectPicSearchList(InstPicVo instPicVo) throws Exception;

    /**
    * 기관 담당자 등록
    *
    * @Title : updateRegInstPic
    * @Description : 기관 담당자 등록
    * @param instPicVo
    * @throws Exception
    * @return int
    */
    public int updateRegInstPic(InstPicVo instPicVo) throws Exception;

    /**
    * 기관 담당자 해지
    *
    * @Title : updateCancelInstPic
    * @Description : 기관 담당자 해지
    * @param instPicVo
    * @throws Exception
    * @return int
    */
    public int updateCancelInstPic(InstPicVo instPicVo) throws Exception;
    
}
