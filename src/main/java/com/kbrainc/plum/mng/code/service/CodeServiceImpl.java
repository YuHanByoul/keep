package com.kbrainc.plum.mng.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.code.model.CodeDao;
import com.kbrainc.plum.mng.code.model.CodeGrpVo;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 코드 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.code.service
 * - CodeServiceImpl.java
 * </pre> 
 *
 * @ClassName : CodeServiceImpl
 * @Description : 코드 관리 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("mng.code.codeService")
public class CodeServiceImpl extends PlumAbstractServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;

    /**
     * 코드그룹 리스트 가져옴.
     * 
     * @Title : selectCodeGrpList
     * @Description : 코드그룹 리스트 가져옴
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return List 코드그룹 목록
     */
    @Override
    public List<CodeGrpVo> selectCodeGrpList(CodeGrpVo codeGrpVO) throws Exception {
        return codeDao.selectCodeGrpList(codeGrpVO);
    }

    /**
     * 코드 리스트 가져옴.
     * 
     * @Title : selectCodeList
     * @Description : 코드 리스트 가져옴.
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return List<CodeVo> 코드 목록
     */
    @Override
    public List<CodeVo> selectCodeList(CodeVo codeVo) throws Exception {
        List<CodeVo> list = null;
        list = codeDao.selectCodeList(codeVo);
        
        return list;
    }

    /**
     * 코드그룹 정보 가져옴.
     * 
     * @Title : selectCodeGrpInfo
     * @Description : 코드그룹 정보 가져옴
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return CodeGrpVO 코드그룹 정보
     */
    @Override
    public CodeGrpVo selectCodeGrpInfo(CodeGrpVo codeGrpVO) throws Exception {
        return codeDao.selectCodeGrpInfo(codeGrpVO);
    }

    /**
     * 코드그룹 정보 등록.
     * 
     * @Title : insertCodeGrpInfo
     * @Description : 코드그룹 정보 등록
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return int 저장로우수
     */
    @Override
    @Transactional
    public int insertCodeGrpInfo(CodeGrpVo codeGrpVO) throws Exception {
        return codeDao.insertCodeGrpInfo(codeGrpVO);
    }

    /**
     * 코드그룹 정보 수정.
     * 
     * @Title : updateCodeGrpInfo
     * @Description : 코드그룹 정보 수정
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception
     * @return int 수정로우수
     */
    @Override
    @Transactional
    public int updateCodeGrpInfo(CodeGrpVo codeGrpVO) throws Exception {
        return codeDao.updateCodeGrpInfo(codeGrpVO);
    }

    /**
     * 코드 정보 가져옴.
     * 
     * @Title : selectCodeInfo
     * @Description : 코드 정보 가져옴
     * @param codeVo 코드VO 클래스
     * @throws Exception
     * @return CodeVo 코드 정보
     */
    @Override
    public CodeVo selectCodeInfo(CodeVo codeVo) throws Exception {
        return codeDao.selectCodeInfo(codeVo.getCd());
    }

    /**
     * 코드 트리.
     * 
     * @Title : selectCodeTreeList
     * @Description : 코드 트리
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return List<TreeItem> 코드 트리 목록
     */
    public List<TreeItem> selectCodeTreeList(CodeGrpVo codeGrpVO) throws Exception {
        return codeDao.selectCodeTreeList(codeGrpVO);
    }

    /**
     * 코드의 순서.
     * 
     * @Title : updateCodeTreeReorder
     * @Description : 코드의 순서
     * @param codeVo 코드VO 클래스
     * @throws Exception
     * @return int 처리성공값
     */
    @Override
    @Transactional
    public int updateCodeTreeReorder(CodeVo codeVo) throws Exception {
        int retVal = 0;
        if (!"over".equals(codeVo.getHitMode())) {
            String cd = codeVo.getCd();
            String tcd = codeVo.getTcd();
            CodeVo codeInfo = codeDao.selectCodeInfo(cd); // 이동될 위치의 ord 정보를 얻기
            Integer pord = codeInfo.getOrd();
            String pucd = codeInfo.getUpprCd();

            codeInfo = codeDao.selectCodeInfo(tcd);// 이동하는 코드의 code 정보를 얻기
            Integer tord = codeInfo.getOrd();
            String tucd = codeInfo.getUpprCd();

            if (pucd.equals(tucd)) { // 부모가같을때
                if (pord.intValue() < tord.intValue()) { // intValue 디버깅 확인 필요
                    codeVo.setUpperYn("Y");
                    if ("after".equals(codeVo.getHitMode())) {
                        codeVo.setOrd(pord + 1);
                    } else {
                        codeVo.setOrd(pord);
                    }
                } else {
                    codeVo.setUpperYn("N");
                    if ("after".equals(codeVo.getHitMode())) {
                        codeVo.setOrd(pord);
                    } else {
                        codeVo.setOrd(pord - 1);
                    }
                }
                // 트리의 order 변경 (+1, -1)
                codeDao.updateCodeReOrder(codeVo);
            } else { // 부모가 다를때
                if ("after".equals(codeVo.getHitMode())) {
                    codeVo.setOrd(pord + 1);

                } else {
                    codeVo.setOrd(pord);
                }
                codeDao.updateCodeReOrderPrnDiff(codeVo);
                codeDao.updateCodeReOrderPrnDiffOrgin(codeVo);
            }

        }
        // 트리 코드 부모 코드 및 순서변경
        // 트리순서 조정
        codeDao.updateCodeTreeInfo(codeVo);
        retVal = 1;
        return retVal;
    }

    /**
     * 코드 정보 등록.
     * 
     * @Title : insertCodeInfo
     * @Description : 코드 정보 등록
     * @param codeVo 코드VO 클래스
     * @throws Exception
     * @return int 저장로우수
     */
    @Override
    @Transactional
    public int insertCodeInfo(CodeVo codeVo) throws Exception {
        return codeDao.insertCodeInfo(codeVo);
    }

    /**
     * 코드 정보 수정.
     * 
     * @Title : updateCodeInfo
     * @Description : 코드 정보 수정
     * @param codeVo 코드VO 클래스
     * @throws Exception
     * @return int 수정로우수
     */
    @Override
    @Transactional
    public int updateCodeInfo(CodeVo codeVo) throws Exception {
        return codeDao.updateCodeInfo(codeVo);
    }       
    
}
