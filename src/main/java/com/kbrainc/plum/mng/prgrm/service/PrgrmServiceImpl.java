package com.kbrainc.plum.mng.prgrm.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.menu.model.MenuVo;
import com.kbrainc.plum.mng.menu.service.MenuService;
import com.kbrainc.plum.mng.prgrm.model.PrgrmDao;
import com.kbrainc.plum.mng.prgrm.model.PrgrmVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 
* 프로그램 관리 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.mng.prgrm.service
* - PrgrmServiceImpl.java
* </pre> 
*
* @ClassName : PrgrmServiceImpl
* @Description : 프로그램 관리 서비스 구현 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Service("mng.prgrm.prgrmService")
public class PrgrmServiceImpl extends PlumAbstractServiceImpl implements PrgrmService {

    @Resource(name = "mng.menu.menuService")
    private MenuService menuService;

    @Autowired
    private PrgrmDao prgrmDao;

    /**
    * @Title : selectPrgrmTreeList
    * @Description : 프로그램 트리
    * @return List<TreeItem> 프로그램 트리 목록
    * @throws Exception 예외
    */
    @Override
    public List<TreeItem> selectPrgrmTreeList() throws Exception {
        return prgrmDao.selectPrgrmTreeList();
    }

    /**
    * @Title : selectPrgrmView
    * @Description : 프로그램 상세
    * @param prgrmid 프로그램ID
    * @return PrgrmVo 프로그램 상세 정보
    * @throws Exception 예외
    */
    public PrgrmVo selectPrgrmView(Integer prgrmid) throws Exception {
        return prgrmDao.selectPrgrmView(prgrmid);
    }

    /**
    * @Title : insertPrgrm
    * @Description : 프로그램 등록
    * @param prgrm 프로그램VO 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int insertPrgrm(PrgrmVo prgrm) throws Exception {
        int retVal = 0;
        retVal = prgrmDao.insertPrgrm(prgrm);
        return retVal;
    }

    /**
    * @Title : updatePrgrm
    * @Description : 프로그램 수정
    * @param prgrm 프로그램VO 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int updatePrgrm(PrgrmVo prgrm) throws Exception {
        int retVal = 0;
        retVal = prgrmDao.updatePrgrm(prgrm);
        return retVal;
    }

    /**
    * @Title : updatePrgrmTreeReorder
    * @Description : 프로그램의 순서
    * @param prgrm 프로그램VO 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int updatePrgrmTreeReorder(PrgrmVo prgrm) throws Exception {
        int retVal = 0;
        if (!"over".equals(prgrm.getHitMode())) {
            Integer prgrmid = prgrm.getPrgrmid();
            Integer tprgrmid = prgrm.getTprgrmid();
            PrgrmVo prgrmInfo = prgrmDao.selectPrgrmView(prgrmid); // 이동될 위치의 prgrm_ord 정보를 얻기
            Integer pord = prgrmInfo.getOrd();
            Integer puprid = prgrmInfo.getUppr_prgrmid();

            prgrmInfo = prgrmDao.selectPrgrmView(tprgrmid);// 이동하는 메뉴의 menu 정보를 얻기
            Integer tord = prgrmInfo.getOrd();
            Integer tuprid = prgrmInfo.getUppr_prgrmid();

            if (puprid.intValue() == tuprid.intValue()) { // 부모가같을때
                if (pord.intValue() < tord.intValue()) { // intValue 디버깅 확인 필요
                    prgrm.setUpperYn("Y");
                    if ("after".equals(prgrm.getHitMode())) {
                        prgrm.setOrd(pord + 1);
                    } else {
                        prgrm.setOrd(pord);
                    }
                } else {
                    prgrm.setUpperYn("N");
                    if ("after".equals(prgrm.getHitMode())) {
                        prgrm.setOrd(pord);
                    } else {
                        prgrm.setOrd(pord - 1);
                    }
                }
                // 트리의 order 변경 (+1, -1)
                prgrmDao.updatePrgrmReOrder(prgrm);
            } else { // 부모가 다를때
                if ("after".equals(prgrm.getHitMode())) {
                    prgrm.setOrd(pord + 1);

                } else {
                    prgrm.setOrd(pord);
                }
                prgrmDao.updatePrgrmReOrderPrnDiff(prgrm);
                prgrmDao.updatePrgrmReOrderPrnDiffOrgin(prgrm);
            }

        }
        // 트리 menu 부모 코드 및 순서변경
        // 트리순서 조정
        prgrmDao.updatePrarmTreeInfo(prgrm);
        retVal = 1;
        return retVal;
    }

    /**
    * @Title : deletePrgrm
    * @Description : 프로그램 삭제
    * @param prgrm 프로그램VO 클래스
    * @return int delete로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int deletePrgrm(PrgrmVo prgrm) throws Exception {
        int retVal = 0;
        Integer prgrmid = prgrm.getPrgrmid();

        // 자식리스트 불러오기
        List<PrgrmVo> prgrmList = prgrmDao.selectPrgrmChildTreeList(prgrm);
        for (int i = 0; i < prgrmList.size(); i++) {
            // 프로그램에 연결된 메뉴리스트를 삭제
            prgrm.setPrgrmid(((PrgrmVo) prgrmList.get(i)).getPrgrmid());
            List menulist = prgrmDao.selectPrgrmConnctMenuList(prgrm);
            if (menulist != null && menulist.size() > 0) {
                MenuVo menu = new MenuVo();
                for (int j = 0; j < menulist.size(); j++) {
                    menu.setMenuid((Integer) ((Map) menulist.get(j)).get("MENUID"));
                    menuService.deleteMenuInfo(menu);
                }
            }
        }

        // menu_ord조정
        prgrm.setHitMode("after");
        prgrm.setUpperYn("N");
        prgrm.setTprgrmid(prgrmid);
        prgrm.setPrgrmid(null);
        prgrmDao.updatePrgrmReOrder(prgrm);
        prgrm.setPrgrmid(prgrmid);
        prgrmDao.deletePrgrm(prgrm);// 메뉴삭제

        retVal = 1;
        return retVal;
    }
}