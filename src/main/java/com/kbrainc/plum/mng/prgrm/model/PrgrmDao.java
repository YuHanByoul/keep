package com.kbrainc.plum.mng.prgrm.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.lib.tree.TreeItem;

/**
* 
* 프로그램 관리 DAO 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.prgrm.model
* - PrgrmDao.java
* </pre> 
*
* @ClassName : PrgrmDao
* @Description :프로그램 관리 DAO 인터페이스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAINC. All Rights Reserved
*/
@Mapper
public interface PrgrmDao {

    /**
    * 프로그램 트리.
    *
    * @Title       : selectPrgrmTreeList 
    * @Description : 프로그램 트리
    * @return List<TreeItem> 프로그램 트리 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectPrgrmTreeList() throws Exception;

    /**
    * 프로그램 상세.
    *
    * @Title       : selectPrgrmView 
    * @Description : 프로그램 상세
    * @param prgrmid 프로그램ID
    * @return PrgrmVo 프로그램 상세 정보
    * @throws Exception 예외
    */
    public PrgrmVo selectPrgrmView(Integer prgrmid) throws Exception;

    /**
    * @Title : insertPrgrm
    * @Description : 프로그램 등록
    * @param prgrm 프로그램VO 클래스
    * @return Integer 프로그램ID
    * @throws Exception 예외
    */
    public Integer insertPrgrm(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : updatePrgrm
    * @Description : 프로그램 수정
    * @param prgrm 프로그램VO 클래스
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updatePrgrm(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : updatePrgrmTreeOrder
    * @Description : 프로그램 순서조정
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void updatePrgrmTreeOrder() throws Exception;

    /**
    * @Title : updatePrgrmReOrder
    * @Description : 프로그램 순서재조정 자리세팅
    * @param prgrm 프로그램VO 클래스
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void updatePrgrmReOrder(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : updatePrgrmReOrderPrnDiff
    * @Description : 프로그램 순서재조정 자리세팅
    * @param prgrm 프로그램VO 클래스
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void updatePrgrmReOrderPrnDiff(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : updatePrgrmReOrderPrnDiffOrgin
    * @Description : 프로그램 순서재조정 자리세팅
    * @param prgrm 프로그램VO 클래스
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void updatePrgrmReOrderPrnDiffOrgin(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : updatePrgrmOrderInfo
    * @Description : 프로그램순서재조정
    * @param param 프로그램VO 클래스
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void updatePrgrmOrderInfo(Map param) throws Exception;

    /**
    * @Title : updatePrarmTreeInfo
    * @Description : 메뉴 트리저장시 Prgrm 정보 수정
    * @param prgrm 프로그램VO 클래스
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updatePrarmTreeInfo(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : selectPrgrmChildTreeList
    * @Description : 하위 프로그램 리스트
    * @param prgrm 프로그램VO 클래스
    * @return List 하위 프로그램 목록
    * @throws Exception 예외
    */
    public List<PrgrmVo> selectPrgrmChildTreeList(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : deletePrgrm
    * @Description : 프로그램 삭제
    * @param prgrm 프로그램VO 클래스
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deletePrgrm(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : selectPrgrmConnctMenuList
    * @Description : 프로그램에 연결된 리스트
    * @param prgrm 프로그램VO 클래스
    * @return List 프로그램 목록
    * @throws Exception 예외
    */
    public List selectPrgrmConnctMenuList(PrgrmVo prgrm) throws Exception;
}