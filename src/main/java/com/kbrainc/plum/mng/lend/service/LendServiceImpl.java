package com.kbrainc.plum.mng.lend.service;

import com.kbrainc.plum.mng.lend.model.*;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.kbrainc.plum.mng.lend.model.LendAplyDlivyVo;
import com.kbrainc.plum.mng.lend.model.LendAplyVo;
import com.kbrainc.plum.mng.lend.model.LendDao;
import com.kbrainc.plum.mng.lend.model.LendPackageindvdChckVo;
import com.kbrainc.plum.mng.lend.model.LendRndPackageindvdVo;
import com.kbrainc.plum.mng.lend.model.LendRndVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.pack.model.PackageDao;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdAbnrmlVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 교구관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.service
 * - TchaidServiceImpl.java
 * </pre> 
 *
 * @ClassName : LendServiceImpl
 * @Description : 교구 대여 관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 20
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class LendServiceImpl extends PlumAbstractServiceImpl implements LendService { 

    @Autowired
    private LendDao lendDao;
    
    @Autowired
    private PackageDao packageDao;

    /**
     * 교구 대여 모집 목록 호출 
     *
     * @Title       : selectLendList 
     * @Description : 교구 대여 모집 목록 호출 
     * @param LendVo 객체
     * @return List<LendVo>  목록
     * @throws Exception 예외
     */
    public List<LendVo> selectLendList(LendVo lendVo) throws Exception{
        return lendDao.selectLendList(lendVo);
    }
    
    /**
     * 교구 대여 모집 등록 
     *
     * @Title       : insertLend 
     * @Description : 교구 대여 모집 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int insertLend(LendVo lendVo) throws Exception{
        
        int resInt = 0;
        resInt +=lendDao.insertLend(lendVo);
        
        List<LendRndVo> list = lendVo.getLendRndList();
        for(LendRndVo vo :list ) {
            vo.setUser(lendVo.getUser());
            vo.setRcritid(lendVo.getRcritid());
            resInt +=lendDao.insertLendRnd(vo);
            //resInt +=lendDao.insertRndPackageindvd(vo); 
        }
        return resInt;
    }
    
    /**
     * 교구 대여 차시 등록 
     *
     * @Title       : insertLendRnd 
     * @Description : 교구 대여 차시 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertLendRnd(LendRndVo lendRndVo) throws Exception{
        return lendDao.insertLendRnd(lendRndVo);
    }
    /**
     * 교구 대여 차시 꾸러미 등록 
     *
     * @Title       : insertRndPackageindvd 
     * @Description : 교구 대여 차시 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertRndPackageindvd(LendRndVo lendRndVo) throws Exception{
        return lendDao.insertRndPackageindvd(lendRndVo);
    }
    /**
     * 교구 대여 수정 
     *
     * @Title       : udateLend 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int udateLend(LendVo lendVo) throws Exception{
        
        int resInt = 0;
        resInt +=lendDao.udateLend(lendVo);
        LendRndVo lendRndVo = new LendRndVo();
        
        List<Integer> delIds = new ArrayList();
        delIds.add(lendVo.getRcritid());
        lendRndVo.setDeleteIds(delIds);
        //resInt +=lendDao.deleteLendRndPackageindvd(lendRndVo);
        resInt +=lendDao.deleteLendRnd(lendRndVo);
        
        List<LendRndVo> list = lendVo.getLendRndList();
        for(LendRndVo vo :list ) {
            vo.setUser(lendVo.getUser());
            vo.setRcritid(lendVo.getRcritid());
            resInt +=lendDao.insertLendRnd(vo);
            //resInt +=lendDao.insertRndPackageindvd(vo); 
        }
        return resInt;
    }
    /**
     * 교구 대여 모집 삭제
     *
     * @Title       : deleteLend 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int deleteLend(LendVo lendVo) throws Exception{
        
        int resInt = 0;
        
        LendRndVo lendRndVo = new LendRndVo();
        lendRndVo.setDeleteIds(lendVo.getDeleteIds());
        
        resInt +=lendDao.deleteLendRndPackageindvd(lendRndVo);
        resInt +=lendDao.deleteLendRnd(lendRndVo);
        resInt +=lendDao.deleteLend(lendVo);
        
        return resInt;
    }
    /**
     * 교구 대여 차시 삭제
     *
     * @Title       : deleteLendRnd 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteLendRnd(LendRndVo lendRndVo) throws Exception{
        return lendDao.deleteLendRnd(lendRndVo);
    }
    /**
     * 교구 대여 모집 상세 호출 
     *
     * @Title       : selectLend 
     * @Description : 교구 대여 모집 상세 호출 
     * @param LendVo 객체
     * @return List<LendVo>  목록
     * @throws Exception 예외
     */
    public LendVo selectLend(LendVo lendVo) throws Exception{
        return lendDao.selectLend(lendVo);
    }
    /**
     * 교구 대여 차시 목록 호출 
     *
     * @Title       : selectLend 
     * @Description : 교구 대여 모집 상세 호출 
     * @param LendVo 객체
     * @return List<LendRndVo>  목록
     * @throws Exception 예외
     */
    public List<LendRndVo> selectLendRndList(LendVo lendVo) throws Exception{
        return lendDao.selectLendRndList(lendVo);
    }
    /**
     * 교구 대여 차시 꾸러마 목록 호출 
     *
     * @Title       : selectLendRndPackageindvdList 
     * @Description : 교구 대여 차시 꾸러마 목록 호출 
     * @param LendVo 객체
     * @return List<LendRndPackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<LendRndPackageindvdVo> selectLendRndPackageindvdList(LendRndVo lendRndVo) throws Exception{
        return lendDao.selectLendRndPackageindvdList(lendRndVo);
    }
    /**
     * 검색용 꾸러미 개체 호출 
     *
     * @Title       : selectPackageindvdList 
     * @Description : 검색용 꾸러미 개체 호출 
     * @param LendVo 객체
     * @return List<PackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdVo> selectPackageindvdList(PackageindvdVo packageindvdVo) throws Exception{
        return lendDao.selectPackageindvdList(packageindvdVo);
    }
    /**
     * 꾸러미 개체 대여 신청여부 확인  
     *
     * @Title       : selectLendApplyYn 
     * @Description : 꾸러미 개체 대여 신청여부 확인  
     * @param LendVo 객체
     * @return String [Y/N]
     * @throws Exception 예외
     */
    public String selectLendApplyYn(LendVo lendVo) throws Exception{
        return lendDao.selectLendApplyYn(lendVo);
    }
    /**
     * 검색용 꾸러미 목록 호출 
     *
     * @Title       : selectPackageList 
     * @Description : 검색용 꾸러미 목록 호출 
     * @param LendVo 객체
     * @return List<PackageVo>  목록
     * @throws Exception 예외
     */
    public List<PackageVo> selectPackageList(PackageVo packageVo) throws Exception{
        return lendDao.selectPackageList(packageVo);
    }
    /**
     * 꾸러미 대여 중복 체크 
     *
     * @Title       : checkPackageDuplicationYn 
     * @Description : 꾸러미 대여 중복 체크 
     * @param LendVo 객체
     * @return String  목록
     * @throws Exception 예외
     */
    public String checkPackageDuplicationYn(LendVo lendVo) throws Exception{
        return lendDao.checkPackageDuplicationYn(lendVo);
    }
    
    /****** 꾸러미 대여 신청 관리**********/
    /**
     * 대여 신청 목록 호출 
     *
     * @Title       : selectLendAplyList 
     * @Description :대여 신청 목록 호출 
     * @param LendVo 객체
     * @return List<LendAplyVo>  목록
     * @throws Exception 예외
     */
    public List<LendAplyVo> selectLendAplyList(LendAplyVo LendAplyVo) throws Exception{
        return lendDao.selectLendAplyList(LendAplyVo);
    }
    /**
     * 대여 모집 목록 호출 
     *
     * @Title       : selectLendRcritList 
     * @Description :대여 모집 목록 호출 
     * @param LendVo 객체
     * @return List<LendAplyVo>  목록
     * @throws Exception 예외
     */
    public List<LendVo> selectLendRcritList(LendVo lendVo) throws Exception{
        return lendDao.selectLendRcritList(lendVo);
    }
    /**
     * 대여 신청 승인 전 재고 및 수량 체크  
     *
     * @Title       : checkRndStockOver 
     * @Description :대여 모집 목록 호출 
     * @param LendVo 객체
     * @return Map<String,String>
     * @throws Exception 예외
     */
    public Map<String,String> checkRndStockOver(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.checkRndStockOver(lendAplyVo);
    }
    /**
     * 대여 신청 일괄 상태 변경
     *
     * @Title       : updateLendAplyStts 
     * @Description : 대여 신청 일괄 상태 변경 
     * @param LendAplyVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updateLendAplyStts(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.updateLendAplyStts(lendAplyVo);
    }
    /**
     * 대여 신청 상세 호출
     *
     * @Title       : selectLendAplyInfo 
     * @Description : 대여 신청 상세 호출 
     * @param LendAplyVo 객체
     * @return int
     * @throws Exception 예외
     */
    public LendAplyVo selectLendAplyInfo(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectLendAplyInfo(lendAplyVo);
    }
    /**
     * 대여 출고 목록 호출
     *
     * @Title       : selectLendAplyDlvyList 
     * @Description : 대여 출고 목록 호출 
     * @param LendAplyVo 객체
     * @return List<LendAplyDlivyVo> 객체
     * @throws Exception 예외
     */
    public List<LendAplyDlivyVo> selectLendAplyDlvyList(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectLendAplyDlvyList(lendAplyVo);
    }
    /**
     * 대여 신청 수정
     *
     * @Title       : updateLendAply 
     * @Description : 대여 신청 수정 
     * @param LendAplyVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int updateLendAply(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.updateLendAply(lendAplyVo);
    }
    /**
     * 대여 모집 차시 목록 호출 (검색용) 
     *
     * @Title       : selectLendRcritRndList 
     * @Description :대여 모집 차시 목록 호출 (검색용) 
     * @param LendVo 객체
     * @return List<LendAplyVo>  목록
     * @throws Exception 예외
     */
    public List<LendVo> selectLendRcritRndList(LendVo lendVo) throws Exception{
        return lendDao.selectLendRcritRndList(lendVo);
    }
    /**
     * 출고 꾸러미 개체 목록 호출
     *
     * @Title       : searchPackageindvdList 
     * @Description :출고 꾸러미 개체 목록 호출 
     * @param LendVo 객체
     * @return List<PackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdVo> searchPackageindvdList(PackageindvdVo packageindvdVo) throws Exception{
        return lendDao.searchPackageindvdList(packageindvdVo);
    }
    /**
     * 꾸러미 개체 입출고 상태 수정
     *
     * @Title       : updatePackageindvdStts 
     * @Description :꾸러미 개체 입출고 상태 수정 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int updatePackageindvdStts(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.updatePackageindvdStts(lendAplyVo);
    }
    /**
     * 대여 신청 출고 등록
     *
     * @Title       : insertLendAplyDlivy 
     * @Description :대여 신청 출고 등록 
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    @Transactional
    public int insertLendAplyDlivy(LendAplyVo lendAplyVo) throws Exception{
        int resInt = 0 ;
        //신청 상태 변경 
        resInt += lendDao.updateLendAply(lendAplyVo);
        //신청 꾸러미 출고 등록 
        resInt += lendDao.insertLendAplyDlivy(lendAplyVo);
        //꾸러미 개체 입출고 상태 변경
        resInt += lendDao.updatePackageindvdStts(lendAplyVo);
        return resInt;
    }

    /**
     * 배송 및 츨고 관리  출고정보 수정
     * Title : updateLendAplyDlivy
     * Description : 배송 및 츨고 관리  출고정보 수정
     *
     * @param lendAplyVo
     * @return int
     */
    @Override
    @Transactional
    public int updateLendAplyDlivy(LendAplyVo lendAplyVo) throws Exception {
        int resInt = 0;
        //해당 신청아이디에 속한 꾸러미 출고 전체 삭제
        resInt += lendDao.deleteLendAplyDlivy(lendAplyVo);
        //신청 꾸러미 출고 등록
        resInt += lendDao.insertLendAplyDlivy(lendAplyVo);

        String[] insertPackageindvd = lendAplyVo.getPackageindvdids();
        String[] deletePackageindvd = lendAplyVo.getDeletePackageindvdids();
        //꾸러미 입고 상태 변경
        lendAplyVo.setPackageindvdids(deletePackageindvd);
        lendAplyVo.setPackSttsCd("216101");
        resInt += lendDao.updatePackageindvdStts(lendAplyVo);
        //꾸러미 출고 상태 변경
        lendAplyVo.setPackageindvdids(insertPackageindvd);
        lendAplyVo.setPackSttsCd("216102");
        resInt += lendDao.updatePackageindvdStts(lendAplyVo);

        return resInt;
    }

    /**************입고 관리 **************/
    /**
     * 대여 신청 출고 상태 (입고)수정
     *
     * @Title       : updateLendAplyDlivy
     * @Description :대여 신청 출고 상태 (입고)수정
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    @Transactional
    public int updateLendAplyDlivy(LendAplyVo lendAplyVo) throws Exception{
        int resInt = 0 ;
        resInt +=lendDao.updateLendAplyDlivy(lendAplyVo);
        //꾸러미 개체 입출고 상태 변경
        resInt += lendDao.updatePackageindvdStts(lendAplyVo);
        return resInt;
    }
    /**
     * 꾸러미 개체 점검 등록
     *
     * @Title       : insertLendPackageindvdChck
     * @Description :꾸러미 개체 점검 등록
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int insertLendPackageindvdChck(LendPackageindvdChckVo lendPackageindvdChckVo) throws Exception{
        return lendDao.insertLendPackageindvdChck(lendPackageindvdChckVo);
    }
    /**
     * 꾸러미 개체 점검 답변 등록
     *
     * @Title       : insertLendPackageindvdChckAns
     * @Description :꾸러미 개체 점검 답변 등록
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public int insertLendPackageindvdChckAns(LendPackageindvdChckVo lendPackageindvdChckVo) throws Exception{
        return lendDao.insertLendPackageindvdChckAns(lendPackageindvdChckVo);
    }
    /**
     * 입고 확인 꾸러미 개체 목록 호출
     *
     * @Title       : selectLendPackageindvdList
     * @Description :입고 확인 꾸러미 개체 목록 호출
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public List<LendAplyVo> selectLendPackageindvdList(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectLendPackageindvdList(lendAplyVo);
    }
    /**
     * 교구 점검 양식 문항/보기 리스트 호출
     *
     * @Title       : selectFormExidList
     * @Description :교구 점검 양식 문항/보기 리스트 호출
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectFormExidList(Map<String,Object> parmaMap) throws Exception{
        return lendDao.selectFormExidList(parmaMap);
    }

    /**
     * 입고 처리 (점검 등록 및 이상처리 등)
     *
     * @Title       : selectFormExidList
     * @Description :교구 점검 양식 문항/보기 리스트 호출
     * @param LendAplyVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    @Transactional
    public Map<String,Object> wrhounsngProcess(LendPackageindvdChckVo lendPackageindvdChckVo) throws Exception{

        Map<String,Object> result = new HashMap();

        Integer resInt = 0 ;
        Integer packageindvdid = lendPackageindvdChckVo.getPackageindvdid();
        Integer aplyid = lendPackageindvdChckVo.getAplyid();
        Integer rndid = lendPackageindvdChckVo.getRndid();

        UserVo user= lendPackageindvdChckVo.getUser();
        PackageindvdAbnrmlVo packageindvdAbnrmlVolVo = new PackageindvdAbnrmlVo();
        PackageindvdVo packageindvdVo = new PackageindvdVo();
        packageindvdVo.setUser(user);
        packageindvdVo.setPackageindvdid(packageindvdid);
        packageindvdVo.setPackageindvdTchaidCmpstnVoList(lendPackageindvdChckVo.getPackageindvdTchaidCmpstnVoList());


        //'C' // (위생 점검 O, 이상등록 O, 이상->정상처리 교구개수등록 O, 입고 처리 O)
        //'S' // (위생 점검 O, 이상등록 O, 이상->정상처리 상태변경 O,    입고 처리 O)

        if(lendPackageindvdChckVo.getModifyStts().equals("S")
                || lendPackageindvdChckVo.getModifyStts().equals("C")
                || lendPackageindvdChckVo.getModifyStts().equals("A")) {

            if(lendPackageindvdChckVo.getModifyStts().equals("C")) {
                //교구 재고 확인
                if (packageDao.selectInvtryOverYn(packageindvdVo).equals("Y")) {
                    result.put("result", Constant.REST_API_RESULT_FAIL);
                    result.put("msg", "교구 재고를 확인하여주십시오.");
                    return result;
                }
                //교구 재고 일괄 감량
                resInt += packageDao.updateTchaidQntyForPackageindvdNormalize(packageindvdVo);
                //망실 내역 저장
                resInt += packageDao.insertPackageindvdTchaidCmpstnDsctnByList(packageindvdVo);
                //기존 교구 수량보다 많을시 구성 업데이트
                resInt += packageDao.updateTchaidCmpstnForPackageindvdNormalize(packageindvdVo);
            }

            //이상 테이블 인서트(정상처리로 인서트)
            packageindvdAbnrmlVolVo.setUser(user);
            packageindvdAbnrmlVolVo.setAplyid(aplyid);
            packageindvdAbnrmlVolVo.setPackageindvdid(packageindvdid);
            packageindvdAbnrmlVolVo.setRndid(rndid);
            packageindvdAbnrmlVolVo.setCn(lendPackageindvdChckVo.getCn());

            if(lendPackageindvdChckVo.getModifyStts().equals("S") || lendPackageindvdChckVo.getModifyStts().equals("C")) {
                packageindvdAbnrmlVolVo.setNrmltYn("Y");
                packageindvdAbnrmlVolVo.setNrmltPrcsYn("Y");
            }
            packageindvdAbnrmlVolVo.setNrmltPrcsPicid(Integer.parseInt(user.getUserid()));
            resInt += packageDao.insertPackageindvdAbnrml(packageindvdAbnrmlVolVo);
        }

        //위생점검
        if(!lendPackageindvdChckVo.getModifyStts().equals("B")) {

            //점검 및 답변 등록
            lendPackageindvdChckVo.setAbnrmlid(packageindvdAbnrmlVolVo.getAbnrmlid());
            resInt += lendDao.insertLendPackageindvdChck(lendPackageindvdChckVo);
            //개체 점검 교구 등록
            resInt += lendDao.insertPackageindvdChckTchaid(lendPackageindvdChckVo);
            //답변 등록
            resInt += lendDao.insertLendPackageindvdChckAns(lendPackageindvdChckVo);
        }


        //입고 처리
        resInt += lendDao.updateLendAplyWrhousng(lendPackageindvdChckVo);
        //꾸러미 개체 상태 입출고상태 및 정상/이상 처리 업데이트
        String prductSttsCd = (lendPackageindvdChckVo.getModifyStts().equals("A"))? "217102":"217101";
        packageindvdVo.setSttsCd("216101");//입고
        packageindvdVo.setPrductSttsCd(prductSttsCd);//정상/이상상태
        resInt += packageDao.updatePackageindvd(packageindvdVo);

        result.put("resInt", resInt);
        result.put("result", Constant.REST_API_RESULT_SUCCESS);
        result.put("msg", "입고 처리 되었습니다.");
        return result;
    }

    /**
     * 대여차시 별 이미 승인 된 꾸러미 개체 여부 확인
     *
     * @Title       : isThereRndPackageindvdYn
     * @Description : 대여차시 별 이미 승인 된 꾸러미 개체 여부 확인
     * @param LendPackageindvdChckVo 객체
     * @return int  목록
     * @throws Exception 예외
     */
    public String isThereRndPackageindvdYn(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.isThereRndPackageindvdYn(lendAplyVo);
    }


}






