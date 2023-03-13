package com.kbrainc.plum.front.cmnty.service;

import com.kbrainc.plum.front.cmnty.model.*;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 환경동아리 ServiceImpl
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.service
 * - CmntyServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyServiceImpl
 * @Description : 환경동아리 ServiceImpl
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service("front.CmntyService")
@Alias("front.CmntyService")
public class CmntyServiceImpl extends PlumAbstractServiceImpl implements CmntyService {
    @Resource(name = "front.CmntyDao")
    private CmntyDao cmntyDao;

    /**
     * 환경동아리 목록
     * Title : selectCmntyList
     * Description : 환경동아리 목록
     *
     * @param cmntyVo
     * @return list
     */
    @Override
    public List<CmntyVo> selectCmntyList(CmntyVo cmntyVo) {
        return cmntyDao.selectCmntyList(cmntyVo);
    }

    /**
     * 환경동아리 커뮤니티 정보
     * Title : selectCmntyInfo
     * Description : 환경동아리 커뮤니티 정보
     *
     * @param cmntyVo
     * @return cmnty vo
     */
    @Override
    public CmntyVo selectCmntyInfo(CmntyVo cmntyVo) {
        return cmntyDao.selectCmntyInfo(cmntyVo);
    }

    /**
     * 환경동아리 가입/초대
     * Title : insertCmntyMbr
     * Description : 환경동아리 가입/초대
     *
     * @param cmntyVo
     * @return boolean
     */
    @Override
    public boolean insertCmntyMbr(CmntyVo cmntyVo) {
        return cmntyDao.insertCmntyMbr(cmntyVo);
    }

    /**
     * 환경동아리 가입신청취소/삭제/반려
     * Title : deleteCmntyMbr
     * Description : 환경동아리 가입신청취소/삭제/반려
     *
     * @param cmntyVo
     * @return boolean
     */
    @Override
    public boolean deleteCmntyMbr(CmntyVo cmntyVo) {
        return cmntyDao.deleteCmntyMbr(cmntyVo);
    }

    /**
     * 환경동아리 템플릿 조회
     * Title : selectCmntyTmplatList
     * Description : 환경동아리 템플릿 조회
     *
     * @param cmntyid
     * @return cmnty bbs tmplat vo
     */
    @Override
    public List<CmntyBbsTmplatVo> selectCmntyTmplatList(Integer cmntyid) {
        return cmntyDao.selectCmntyTmplatList(cmntyid);
    }

    /**
     * 환경동아리 개설
     * Title : insertCmnty
     * Description : 환경동아리 개설
     *
     * @param cmntyVo
     * @return boolean
     */
    @Override
    @Transactional
    public boolean insertCmnty(CmntyVo cmntyVo) {
        boolean result = false;
        if(cmntyDao.insertCmnty(cmntyVo) && cmntyDao.insertCmntyMbr(cmntyVo)) result = true;
        Integer index = 0;
        for(Integer tmplatId : cmntyVo.getTmplatIds()){
            if(cmntyDao.insertCmntyCtgry(cmntyVo, tmplatId, index) && cmntyDao.insertCmntyBbs(cmntyVo, tmplatId)){
                result = true;
            }else{
                result = false;
            }
            index++;
        }
        return result;
    }

    /**
     * 환경동아리 카테고리 순서 조회
     * Title : selectCmntyCtgryList
     * Description : 환경동아리 카테고리 순서 조회
     *
     * @param cmntyBbsVo
     * @return list
     */
    @Override
    public List<CmntyCtgryVo> selectCmntyCtgryList(CmntyBbsVo cmntyBbsVo) {
        return cmntyDao.selectCmntyCtgryList(cmntyBbsVo);
    }

    /**
     * 환경동아리 게시글 목록 조회
     * Title : selectCmntyBbsList
     * Description : 환경동아리 게시글 목록 조회
     *
     * @param bbsInfo
     * @return list
     */
    @Override
    public List<CmntyPstVo> selectCmntyBbsList(CmntyBbsVo bbsInfo) {
        return cmntyDao.selectCmntyBbsList(bbsInfo);
    }

    /**
     * 환경동아리 커뮤니티 게시판 정보
     * Title : selectBbsInfo
     * Description : 환경동아리 커뮤니티 게시판 정보
     *
     * @param cmntyBbsVo
     * @return cmnty bbs vo
     */
    @Override
    public CmntyBbsVo selectBbsInfo(CmntyBbsVo cmntyBbsVo) {
        return cmntyDao.selectBbsInfo(cmntyBbsVo);
    }

    /**
     * 환경동아리 회원 목록
     * Title : selectCmntyMbrList
     * Description : 환경동아리 회원 목록
     *
     * @param cmntyMbrVo
     * @return cmnty mbr vo
     */
    @Override
    public List<CmntyMbrVo> selectCmntyMbrList(CmntyMbrVo cmntyMbrVo) {
        return cmntyDao.selectCmntyMbrList(cmntyMbrVo);
    }

    /**
     * 환경동아리 탈퇴/권한부여/권한철회/다시초대/승인/복원
     * Title : updateCmntyMbr
     * Description : 환경동아리 탈퇴/권한부여/권한철회/다시초대/승인/복원
     *
     * @param cmntyVo
     * @return boolean
     */
    @Override
    public boolean updateCmntyMbr(CmntyVo cmntyVo) {
        return cmntyDao.updateCmntyMbr(cmntyVo);
    }

    /**
     * 환경동아리 수정
     * Title : updateCmnty
     * Description : 환경동아리 수정
     *
     * @param cmntyVo
     * @return boolean
     */
    @Override
    @Transactional
    public boolean updateCmnty(CmntyVo cmntyVo) {
        return cmntyDao.updateCmnty(cmntyVo);
    }

    /**
     * 환경동아리 수정 처리
     * Title : saveCmnty
     * Description : 환경동아리 수정 처리
     *
     * @param cmntyVo
     * @return boolean
     */
    @Override
    public boolean saveCmnty(CmntyVo cmntyVo) {
        boolean result = false;
        CmntyBbsVo cmntyBbsVo = new CmntyBbsVo();
        cmntyBbsVo.setCmntyid(cmntyVo.getCmntyid());
        List<Integer> tmplatYList = new ArrayList<>(Arrays.asList(cmntyVo.getTmplatYIds()));
        result = cmntyDao.updateCmnty(cmntyVo);
        //카테고리 수정시 작업 전에 해당템플릿 게시판에 있는지 체크 후
        //있으면 템플릿 업데이트, 없으면 템플릿 등록 후 순서도 같이 등록
        Integer index = 0;
        for(Integer tmplatId : cmntyVo.getTmplatIds()){
            cmntyBbsVo.setCmntyBbsTmplatid(tmplatId);
            boolean isBbsInfo = selectBbsInfo(cmntyBbsVo) != null;
            if(tmplatYList.contains(tmplatId)){ // Y인애들 insert & update
                if(!isBbsInfo){
                    if(cmntyDao.insertCmntyCtgry(cmntyVo, tmplatId, index) && cmntyDao.insertCmntyBbs(cmntyVo, tmplatId)) result = true;
                    else result = false;
                }else{
                    result = cmntyDao.updateCmntyBbs(cmntyVo, tmplatId, "Y");
                }
            }else{ // N인애들 update
                if(isBbsInfo) result = cmntyDao.updateCmntyBbs(cmntyVo, tmplatId, "N");
            }
            index++;
        }
        return result;
    }

    /**
     * 환경동아리 회원검색
     * Title : selectMbr
     * Description : 환경동아리 회원검색
     *
     * @param userNm
     * @param cmntyid
     * @return list
     */
    @Override
    public List<UserVo> selectMbr(String userNm, String cmntyid) {
        return cmntyDao.selectMbr(userNm, cmntyid);
    }
}
