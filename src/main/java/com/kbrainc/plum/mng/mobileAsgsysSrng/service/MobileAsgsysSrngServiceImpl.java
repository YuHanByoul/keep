package com.kbrainc.plum.mng.mobileAsgsysSrng.service;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ChklstAnsVo;
import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngDao;
import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.mobileAsgsysSrng.service
* - MobileAsgsysSrngServiceImpl.java
* </pre>
*
* @ClassName : MobileAsgsysSrngServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MobileAsgsysSrngServiceImpl extends PlumAbstractServiceImpl implements MobileAsgsysSrngService{
    
    @Autowired
    private MobileAsgsysSrngDao mobileAsgsysSrngDao;
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectAsgsysSrngList
    * @Description : 시설 목록 조회
    * @param mobileAsgsysSrngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<MobileAsgsysSrngVo>
    */
    @Override
    public List<MobileAsgsysSrngVo> selectAsgsysSrngList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception{
        return mobileAsgsysSrngDao.selectAsgsysSrngList(mobileAsgsysSrngVo);
    }

    /**
     * 지원단심사 상세 조회
     *
     * @Title : selectAsgsysSrngInfo
     * @Description : 지원단심사 상세 조회
     * @param mobileAsgsysSrngVo
     * @return MobileAsgsysSrngVo
     * @throws Exception
     */
    @Override
    public MobileAsgsysSrngVo selectAsgsysSrngInfo(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {
        return mobileAsgsysSrngDao.selectAsgsysSrngInfo(mobileAsgsysSrngVo);
    }

    /**
     * 지원단심사 체크리스트 조회
     *
     * @Title : selectCheckList
     * @Description : 지원단심사 체크리스트 조회
     * @param mobileAsgsysSrngVo
     * @return List<MobileAsgsysSrngVo>
     * @throws Exception
     */
    @Override
    public List<MobileAsgsysSrngVo> selectCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {
        return mobileAsgsysSrngDao.selectCheckList(mobileAsgsysSrngVo);
    }

    /**
     * 지원단심사 등록
     *
     * @Title : insertSprtgrpSrng
     * @Description : 지원단심사 등록
     * @param mobileAsgsysSrngVo
     * @return int
     * @throws Exception
     */
    @Override
    @Transactional
    public int insertSprtgrpSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {

        int ret=0;
        String preSeCd="";
        int ordrAns=1;
        ChklstAnsVo ordrAnsVo = new ChklstAnsVo();

        //지원단심사 수정
        ret += mobileAsgsysSrngDao.updateSprtgrpOpnn(mobileAsgsysSrngVo);

        if (mobileAsgsysSrngVo.getSbmsnid() != null && !mobileAsgsysSrngVo.getSbmsnid().equals(0)) {
            //체크리스트 제출 수정
            ret = mobileAsgsysSrngDao.updateChklstSbmsn(mobileAsgsysSrngVo);
        }else {
            //체크리스트 제출 등록
            ret = mobileAsgsysSrngDao.insertChklstSbmsn(mobileAsgsysSrngVo);
        }

        MobileAsgsysSrngVo sbmsnInfo = mobileAsgsysSrngDao.selectChkListSbmsn(mobileAsgsysSrngVo);

        //체크리스트 답변 수정
        List<ChklstAnsVo> lst = mobileAsgsysSrngVo.getAnsLst();

        //체크리스트 답변 순서 삭제
        ordrAnsVo.setSbmsnid(sbmsnInfo.getSbmsnid());
        mobileAsgsysSrngDao.deleteChklstSeOrdrAnsList(ordrAnsVo);

        if(lst.size() > 0 ) {

            for(ChklstAnsVo vo : lst) {

                vo.setUser(mobileAsgsysSrngVo.getUser());
                vo.setSbmsnid(sbmsnInfo.getSbmsnid());

                if(1 == mobileAsgsysSrngDao.selectKeyCntChklstAns(vo)) {
                    ret += mobileAsgsysSrngDao.updateChklstAns(vo);
                }else if(0 == mobileAsgsysSrngDao.selectKeyCntChklstAns(vo)) {
                    ret += mobileAsgsysSrngDao.insertChklstAns(vo);
                }
                //체크리스트 답변 순서 등록
                if(!vo.getSeCd().equals(preSeCd)) {
                    ordrAnsVo.setSbmsnid(sbmsnInfo.getSbmsnid());
                    ordrAnsVo.setSeCd(vo.getSeCd());
                    ordrAnsVo.setOrdr(ordrAns);
                    ordrAnsVo.setUser(vo.getUser());
                    ret+=mobileAsgsysSrngDao.insertChklstSeOrdrAnsList(ordrAnsVo);
                    ordrAns++;
                    preSeCd=vo.getSeCd();
                }
            }
        }

        return ret;
    }

    /**
     * 지원단심사 수정
     *
     * @Title : updateSprtgrpSrng
     * @Description : 지원단심사 등록
     * @param mobileAsgsysSrngVo
     * @return int
     * @throws Exception
     */
    @Override
    @Transactional
    public int updateSprtgrpSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {

        int ret=0;

        //지원단심사 수정
        ret += mobileAsgsysSrngDao.updateSprtgrpOpnn(mobileAsgsysSrngVo);

        //체크리스트 제출 저장
        ret += mobileAsgsysSrngDao.updateChklstSbmsn(mobileAsgsysSrngVo);

        //체크리스트 답변 수정
        List<ChklstAnsVo> lst = mobileAsgsysSrngVo.getAnsLst();
        if(lst.size() > 0 ) {
            //체크리스트 답변 순서 삭제
            //ret += mobileAsgsysSrngDao.deleteChklstSeOrdrAnsList(lst.get(0)); todo

            for(ChklstAnsVo vo : lst) {

                vo.setUser(mobileAsgsysSrngVo.getUser());

                if(1 == mobileAsgsysSrngDao.selectKeyCntChklstAns(vo)) {
                    ret += mobileAsgsysSrngDao.updateChklstAns(vo);
                }else if(0 == mobileAsgsysSrngDao.selectKeyCntChklstAns(vo)) {
                    ret += mobileAsgsysSrngDao.insertChklstAns(vo);
                }

                //체크리스트 답변 순서 등록 todo
                //ret += mobileAsgsysSrngDao.insertChklstSeOrdrAnsList(vo);

            }

        }

        return ret;
    }
}
