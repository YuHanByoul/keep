package com.kbrainc.plum.front.instInfo.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.envEdu.model.PrgrmVo;

/**
* 기관정보 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.instInfo.model
* - InstInfoDao.java
* </pre>
*
* @ClassName : InstInfoDao
* @Description : TODO
* @author : JD
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.instInfoDao")
public interface InstInfoDao {

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
