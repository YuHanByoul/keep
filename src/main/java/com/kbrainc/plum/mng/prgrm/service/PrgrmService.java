package com.kbrainc.plum.mng.prgrm.service;

import java.util.List;

import com.kbrainc.plum.mng.prgrm.model.PrgrmVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;

/**
* 
* 프로그램 관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.prgrm.service
* - PrgrmService.java
* </pre> 
*
* @ClassName : PrgrmService
* @Description : 프로그램 관리 서비스 인터페이스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAINC. All Rights Reserved
*/
public interface PrgrmService {

    /**
    * @Title : selectPrgrmTreeList
    * @Description : 프로그램 트리
    * @return List<TreeItem> 프로그램 트리 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectPrgrmTreeList() throws Exception;

    /**
    * @Title : selectPrgrmView
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
    * @return int 프로그램ID
    * @throws Exception 예외
    */
    public int insertPrgrm(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : updatePrgrm
    * @Description : 프로그램 수정
    * @param prgrm 프로그램VO 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    public int updatePrgrm(PrgrmVo param) throws Exception;

    /**
    * @Title : updatePrgrmTreeReorder
    * @Description : 프로그램의 순서
    * @param prgrm 프로그램VO 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    public int updatePrgrmTreeReorder(PrgrmVo prgrm) throws Exception;

    /**
    * @Title : deletePrgrm
    * @Description : 프로그램 삭제
    * @param prgrm 프로그램VO 클래스
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deletePrgrm(PrgrmVo prgrm) throws Exception;
}