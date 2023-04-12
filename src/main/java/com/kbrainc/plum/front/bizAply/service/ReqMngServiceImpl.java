/**
 * 
 */
package com.kbrainc.plum.front.bizAply.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.bizAply.model.CapabilityResultVo;
import com.kbrainc.plum.front.bizAply.model.ReqMngDao;
import com.kbrainc.plum.front.bizAply.model.ReqMngVo;
import com.kbrainc.plum.front.bizAply.model.ReqUserVo;
import com.kbrainc.plum.front.bizAply.model.BudgetVo;
import com.kbrainc.plum.front.bizAply.model.OperRndVo;
import com.kbrainc.plum.front.bizAply.model.OperVo;
import com.kbrainc.plum.front.bizAply.model.PrgrmEvlVo;
import com.kbrainc.plum.front.bizAply.model.PrgrmInfoOutlineVo;
import com.kbrainc.plum.front.bizAply.model.ProcPlanVo;
import com.kbrainc.plum.front.bizAply.model.ProgramInfoVo;
import com.kbrainc.plum.front.bizAply.model.SafetyMngVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderAcbgVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderCarrVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderJobVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderLicVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderVo;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.front.bizAply.model.CapabilityVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

import kr.go.onepass.client.org.apache.commons.lang.StringUtils;

/**
* 공모신청 서비스 클래스 
*
* <pre>
* com.kbrainc.plum.front.bizAply.service
* - PublicContestAplyServiceImpl.java
* </pre> 
*
* @ClassName : PublicContestAplyServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 23.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.reqMngService")
@Alias("front.reqMngService")
public class ReqMngServiceImpl extends PlumAbstractServiceImpl implements ReqMngService {

    @Resource(name = "front.reqMngDao")
    private ReqMngDao reqMngDao;
    
    @Override
    public List<ReqMngVo> selectReqMngList(ReqMngVo reqMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectReqMngList(reqMngVo);
    }

    @Override
    public int updateHits(ReqMngVo reqMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.updateHits(reqMngVo);
    }

    @Override
    public ReqUserVo getAplyCnt(ReqMngVo reqMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.getAplyCnt(reqMngVo);
    }
    
    @Override
    public ReqUserVo userBaseInfoDetail(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.userBaseInfoDetail(reqUserVo);
    }
    
    @Override
    public ReqUserVo detailReqUser(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailReqUser(reqUserVo);
    }
    
    @Override
    public int insertBaseInfo(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        if ("U".equals(reqUserVo.getMode())) {
            result = reqMngDao.updateBaseInfo(reqUserVo);
        } else {
            result = reqMngDao.insertBaseInfo(reqUserVo); 
        }
        return result;
    }


    @Override
    public CapabilityVo detailBizAble(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailBizAble(capabilityVo);
    }

    @Override
    public List<CapabilityVo> selectInstPrgrmList(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectInstPrgrmList(capabilityVo);
    }

    @Override
    public List<CapabilityResultVo> selectInstOperRsltList(CapabilityResultVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectInstOperRsltList(capabilityVo);
    }
    
    @Transactional
    @Override
    public int insertBizAble(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        // 기관역량
        result += reqMngDao.insertBizAble(capabilityVo);
        
        // 전년도 지원사업 프로그램 주제
        if (CollectionUtils.isNotEmpty(capabilityVo.getPrgrmSbjctCds())) {
            result += reqMngDao.deleteBizAbleBefore(capabilityVo);
            result += reqMngDao.insertBizAbleBefore(capabilityVo);            
        }
        
        // 환경교육 운영성과
        if (null != capabilityVo.getSeCd() && capabilityVo.getSeCd().length > 0) {
            result += reqMngDao.deleteBizAbleOperRslt(capabilityVo);
            for (int i = 0; i < capabilityVo.getSeCd().length; i++) {
                CapabilityResultVo param = new CapabilityResultVo();
                param.setUser(capabilityVo.getUser());
                param.setAplyid(capabilityVo.getAplyid());                
                param.setSeCd(capabilityVo.getSeCd()[i]);
                param.setBsnsNm(capabilityVo.getBsnsNm()[i]);
                param.setBsnsPrd(StringUtils.defaultIfEmpty(capabilityVo.getBsnsPrd()[i], null));
                param.setEduNope(StringUtils.defaultIfEmpty(capabilityVo.getEduNope()[i], null));
                param.setBsnsBgt(StringUtils.defaultIfEmpty(capabilityVo.getBsnsBgt()[i], null));
                param.setOperCn(capabilityVo.getOperCn()[i]);
                param.setOrdr(capabilityVo.getOrdr()[i]);
                
                result += reqMngDao.insertBizAbleOperRslt(param);
            }
        }
        
        return result;
    }

    @Override
    public ProcPlanVo detailPlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailPlan(procPlanVo);
    }

    @Override
    public int insertPlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.insertPlan(procPlanVo);
    }

    @Override
    public int updatePlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.updatePlan(procPlanVo);
    }

    @Override
    public ProgramInfoVo detailPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailPrgrmInfo(programInfoVo);
    }

    @Transactional
    @Override
    public int insertPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result = reqMngDao.insertPrgrmInfo(programInfoVo);
        
        result += reqMngDao.deletePrgrmOutline(programInfoVo);
        if (null != programInfoVo.getPrgrmNm() && programInfoVo.getPrgrmNm().length > 0) {
            for (int i = 0; i < programInfoVo.getPrgrmNm().length; i++) {
                PrgrmInfoOutlineVo param = new PrgrmInfoOutlineVo();
                param.setUser(programInfoVo.getUser());
                param.setAplyid(programInfoVo.getAplyid());
                param.setOrdr(i+1);
                param.setPrgrmNm(programInfoVo.getPrgrmNm()[i]);
                param.setEduRnd(programInfoVo.getEduRnd()[i]);
                param.setEduNope(programInfoVo.getEduNope()[i]);
                
                result += reqMngDao.insertPrgrmOutline(param);
            }
        }
        
        return result;
    }

    @Transactional
    @Override
    public int updatePrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result = reqMngDao.updatePrgrmInfo(programInfoVo);
        
        result += reqMngDao.deletePrgrmOutline(programInfoVo);
        if (null != programInfoVo.getPrgrmNm() && programInfoVo.getPrgrmNm().length > 0) {
            for (int i = 0; i < programInfoVo.getPrgrmNm().length; i++) {
                PrgrmInfoOutlineVo param = new PrgrmInfoOutlineVo();
                param.setUser(programInfoVo.getUser());
                param.setAplyid(programInfoVo.getAplyid());
                param.setOrdr(i+1);
                param.setPrgrmNm(programInfoVo.getPrgrmNm()[i]);
                param.setEduRnd(programInfoVo.getEduRnd()[i]);
                param.setEduNope(programInfoVo.getEduNope()[i]);
                
                result += reqMngDao.insertPrgrmOutline(param);
            }
        }
        
        return result;
    }

    @Override
    public List<PrgrmInfoOutlineVo> selectPrgrmList(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectPrgrmList(programInfoVo);
    }

    @Override
    public SmrLeaderVo detailSmrLeader(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailSmrLeader(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderJobVo> selectLeaderPlanList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderPlanList(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderAcbgVo> selectLeaderAbilList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderAbilList(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderLicVo> selectLeaderLicList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderLicList(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderCarrVo> selectLeaderCarrList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderCarrList(smrLeaderVo);
    }

    @Transactional
    @Override
    public int insertSmrLeader(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        result += reqMngDao.updateSmrLeaderMgt(smrLeaderVo);
        result += reqMngDao.insertSmrLeaderInfo(smrLeaderVo);
        
        result += reqMngDao.deleteLeaderPlan(smrLeaderVo);   
        if (null != smrLeaderVo.getSe() && smrLeaderVo.getSe().length > 0) {
            for (int i = 0; i < smrLeaderVo.getSe().length; i++) {
                SmrLeaderJobVo param = new SmrLeaderJobVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setSe(smrLeaderVo.getSe()[i]);
                param.setNm(smrLeaderVo.getLdrnm()[i]);
                param.setTaskCn(smrLeaderVo.getTaskCn()[i]);
                
                result += reqMngDao.insertLeaderPlan(param);
            }
        }
        
        result += reqMngDao.deleteLeaderAbil(smrLeaderVo);   
        if (null != smrLeaderVo.getSchlNm() && smrLeaderVo.getSchlNm().length > 0) {
            for (int i = 0; i < smrLeaderVo.getSchlNm().length; i++) {
                SmrLeaderAcbgVo param = new SmrLeaderAcbgVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setDgr(smrLeaderVo.getDgr()[i]);
                String de[] = smrLeaderVo.getEduDe()[i].split("~");
                if (de.length == 2) {
                    param.setBgngDe(de[0].trim());
                    param.setEndDe(de[1].trim());
                }
                param.setMjr(smrLeaderVo.getMjr()[i]);
                param.setSchlNm(smrLeaderVo.getSchlNm()[i]);
                
                result += reqMngDao.insertLeaderAbil(param);
            }
        }
        
        result += reqMngDao.deleteLeaderLic(smrLeaderVo);   
        if (null != smrLeaderVo.getQlfcNm() && smrLeaderVo.getQlfcNm().length > 0) {
            for (int i = 0; i < smrLeaderVo.getQlfcNm().length; i++) {
                SmrLeaderLicVo param = new SmrLeaderLicVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setAcqsDe(smrLeaderVo.getAcqsDe()[i]);
                param.setGrd(smrLeaderVo.getGrd()[i]);
                param.setQlfcNm(smrLeaderVo.getQlfcNm()[i]);
                param.setWrkplc(smrLeaderVo.getWrkplc()[i]);
                
                result += reqMngDao.insertLeaderLic(param);
            }
        }
        
        result += reqMngDao.deleteLeaderCarr(smrLeaderVo);   
        if (null != smrLeaderVo.getPrd() && smrLeaderVo.getPrd().length > 0) {
            for (int i = 0; i < smrLeaderVo.getPrd().length; i++) {
                SmrLeaderCarrVo param = new SmrLeaderCarrVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setActvtNm(smrLeaderVo.getActvtNm()[i]);
                param.setActvtType(smrLeaderVo.getActvtType()[i]);
                param.setPrd(smrLeaderVo.getPrd()[i]);
                
                result += reqMngDao.insertLeaderCarr(param);
            }
        }
        
        return result;
    }

    @Override
    public SafetyMngVo detailSafetyMng(SafetyMngVo safetyMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailSafetyMng(safetyMngVo);
    }
    
    @Override
    public PrgrmEvlVo detailPrgrmEvl(SafetyMngVo safetyMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailPrgrmEvl(safetyMngVo);
    }

    @Transactional
    @Override
    public int insertSafeMng(SafetyMngVo safetyMngVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result = reqMngDao.insertSafetyMng(safetyMngVo);
        result += reqMngDao.insertPrgrmEvl(safetyMngVo);
        
        ReqUserVo reqUserVo = new ReqUserVo();
        reqUserVo.setAplyid(safetyMngVo.getAplyid());
        reqUserVo.setAplySttsCd("192102");
        result += reqMngDao.updateStatus(reqUserVo);
        
        return result;
    }

    @Override
    public List<BudgetVo> selectBudgetList(BudgetVo budgetVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectBudgetList(budgetVo);
    }

    @Transactional
    @Override
    public int insertBudget(BudgetVo budgetVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        if (budgetVo != null && budgetVo.getCodeArr() != null) {
            for (int i = 0; i < budgetVo.getCodeArr().length; i++) {
                budgetVo.setAmt(budgetVo.getAmtArr()[i]);
                budgetVo.setExpitmCd(budgetVo.getCodeArr()[i]);
                budgetVo.setDsctn(budgetVo.getDsctnArr()[i]);
                
                if (budgetVo.getBgtidArr()[i] != null) {
                    budgetVo.setBgtid(budgetVo.getBgtidArr()[i]);
                    result += reqMngDao.updateBudget(budgetVo);
                } else {
                    result += reqMngDao.insertBudget(budgetVo);
                }
            }
        }
        
        ReqUserVo reqUserVo = new ReqUserVo();
        reqUserVo.setAplyid(budgetVo.getAplyid());
        reqUserVo.setAplySttsCd("192102");
        result += reqMngDao.updateStatus(reqUserVo);
    
        return result;
    }


    @Override
    public OperVo detailOper(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailOper(operVo);
    }


    @Override
    public List<OperVo> selectOperSubjectList(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectOperSubjectList(operVo);
    }


    @Override
    public List<OperRndVo> selectOperSubjectRndList(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectOperSubjectRndList(operVo);
    }

    @Transactional
    @Override
    public int updateOper(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        // 운영개요
        result += reqMngDao.updateOper(operVo);
        
        if (CollectionUtils.isNotEmpty(operVo.getEduSbjctCds())) {
            // 교육 주제
            result += reqMngDao.deleteOperSubject(operVo);
            result += reqMngDao.insertOperSubject(operVo);            
        }
        
        if (null != operVo.getRnd() && operVo.getRnd().length > 0) {
            result += reqMngDao.deleteOperSubjectRnd(operVo);
            for (int i = 0; i < operVo.getRnd().length; i++) {
                if (operVo.getEduSbjctCdArr()[i] != null) {
                    OperRndVo param = new OperRndVo();
                    param.setUser(operVo.getUser());
                    param.setAplyid(operVo.getAplyid());                
                    param.setEduSbjctCd(StringUtils.defaultIfEmpty(operVo.getEduSbjctCdArr()[i], ""));
                    param.setRnd(operVo.getRnd()[i]);
                    param.setSbjctSeCd(operVo.getSbjctSeCdArr()[i]);
                    
                    // 운영 차시
                    result += reqMngDao.insertOperSubjectRnd(param);                    
                }
            }
        }
        
        return result;
    }

    @Override
    public List<CodeVo> selectCodeList(CodeVo code) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectCodeList(code);
    }
}
