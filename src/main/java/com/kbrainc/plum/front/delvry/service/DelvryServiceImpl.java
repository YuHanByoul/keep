package com.kbrainc.plum.front.delvry.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.delvry.model.DelvryAplyComputVo;
import com.kbrainc.plum.front.delvry.model.DelvryAplySplmntVo;
import com.kbrainc.plum.front.delvry.model.DelvryAplyVo;
import com.kbrainc.plum.front.delvry.model.DelvryDao;
import com.kbrainc.plum.front.delvry.model.PcntstVo;
import com.kbrainc.plum.mng.score.model.QuestionVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 교부관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.front.delvry.service
 * - DelvryServiceImpl.java
 * </pre> 
 *
 * @ClassName : DelvryServiceImpl
 * @Description : 교부관리 서비스 구현 
 * @author : KCS
 * @date : 2023. 03. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.delvryService")
@Alias("front.delvryService")
public class DelvryServiceImpl extends PlumAbstractServiceImpl implements DelvryService {
    
    @Resource(name = "front.delvryDao")
    private DelvryDao delvryDao;
    
    /**
    * 공모 목록 조회
    *
    * @Title : selectPcntstList
    * @Description : 공모 목록 조회
    * @param pcntstVo PcntstVo 객체
    * @return List<PcntstVo> 공모 목록
    * @throws Exception 예외
    */
    @Override
    public List<PcntstVo> selectPcntstList(PcntstVo pcntstVo) throws Exception {
        return delvryDao.selectPcntstList(pcntstVo);
    }
    
    /**
    * 공모 정보 조회
    *
    * @Title : selectPcntstInfo
    * @Description : 공모 정보 조회
    * @param pcntstVo PcntstVo 객체
    * @return PcntstVo 공모 정보
    * @throws Exception 예외
    */
    @Override
    public PcntstVo selectPcntstInfo(PcntstVo pcntstVo) throws Exception {
        return delvryDao.selectPcntstInfo(pcntstVo);
    }
    
    /**
    * 교부 신청 목록 조회
    *
    * @Title : selectDelvryAplyList
    * @Description : 교부 신청 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<DelvryAplyVo> 교부 신청 목록
    * @throws Exception 예외
    */
    @Override
    public List<DelvryAplyVo> selectDelvryAplyList(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.selectDelvryAplyList(delvryAplyVo);
    }
    
    /**
    * 교부 상태 업데이트
    *
    * @Title : updateDelvryStts
    * @Description : 교부 상태 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    @Override
    public int updateDelvryStts(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.updateDelvryStts(delvryAplyVo);
    }
    
    /**
    * 교부 신청 정보 조회
    *
    * @Title : selectDelvryAplyInfo
    * @Description : 교부 신청 정보 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return DelvryAplyVo 교부 신청 정보
    * @throws Exception 예외
    */
    @Override
    public DelvryAplyVo selectDelvryAplyInfo(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.selectDelvryAplyInfo(delvryAplyVo);
    }
    
    /**
    * 교부 신청 산출내역 목록 조회
    *
    * @Title : selectDelvryAplyComputList
    * @Description : 교부 신청 산출내역 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<DelvryAplyComputVo> 교부 신청 산출내역 목록
    * @throws Exception 예외
    */
    @Override
    public List<DelvryAplyComputVo> selectDelvryAplyComputList(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.selectDelvryAplyComputList(delvryAplyVo);
    }
    
    /**
    * 교부 신청 업데이트
    *
    * @Title : saveDelvryAply
    * @Description : 교부 신청 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int saveDelvryAply(DelvryAplyVo delvryAplyVo) throws Exception {
        int retVal = 0;
        if (null != delvryAplyVo) {
            if (delvryAplyVo.getDelvryAplyid() > 0) {
                retVal += delvryDao.updateDelvryAply(delvryAplyVo);
            } else {
                retVal += delvryDao.insertDelvryAply(delvryAplyVo);
            }
        }
        
        String data = delvryAplyVo.getJsonString();
        if (StringUtils.isNotBlank(data)) {
            JSONParser json = new JSONParser();
            JSONObject jsonobj = (JSONObject)json.parse(data);
            
            JSONArray dataArray = (JSONArray)jsonobj.get("data");
            if (CollectionUtils.isNotEmpty(dataArray)) {
                DelvryAplyComputVo delvryAplyComputVo = new DelvryAplyComputVo();
                delvryAplyComputVo.setUser(delvryAplyVo.getUser());
                delvryAplyComputVo.setDelvryAplyid(delvryAplyVo.getDelvryAplyid());
                
                for(int i = 0; i < dataArray.size(); i++){
                    JSONObject jsonObj = (JSONObject) dataArray.get(i);
                    String computid = jsonObj.get("computid") != null ? jsonObj.get("computid").toString().trim() : "";
                    String expitmCd = jsonObj.get("expitmCd") != null ? jsonObj.get("expitmCd").toString().trim() : "";
                    String amt = jsonObj.get("amt") != null ? jsonObj.get("amt").toString().trim() : "";
                    String cn = jsonObj.get("cn") != null ? jsonObj.get("cn").toString().trim() : "";
                    String ordr = jsonObj.get("ordr") != null ? jsonObj.get("ordr").toString().trim() : "";
                    
                    delvryAplyComputVo.setComputid(Integer.valueOf(computid));
                    delvryAplyComputVo.setExpitmCd(expitmCd);
                    delvryAplyComputVo.setAmt(Integer.valueOf(amt));
                    delvryAplyComputVo.setCn(cn);
                    delvryAplyComputVo.setOrdr(Integer.valueOf(ordr));
                    
                    if (delvryAplyComputVo.getComputid() > 0) {
                        delvryDao.updateDelvryAplyComput(delvryAplyComputVo);
                    } else {
                        delvryDao.insertDelvryAplyComput(delvryAplyComputVo);
                    }
                }
            }
        }
        
        return retVal;
    }
    
    /**
    * 교부 신청 보완요청 목록 조회
    *
    * @Title : selectDelvryAplySplmntList
    * @Description : 교부 신청 보완요청 목록 조회
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return List<DelvryAplySplmntVo> 교부 신청 보완요청 목록
    * @throws Exception 예외
    */
    @Override
    public List<DelvryAplySplmntVo> selectDelvryAplySplmntList(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        return delvryDao.selectDelvryAplySplmntList(delvryAplySplmntVo);
    }
    
    /**
    * 교부 신청 보완요청 정보 조회
    *
    * @Title : selectDelvryAplySplmntInfo
    * @Description : 교부 신청 보완요청 정보 조회
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return DelvryAplySplmntVo 교부 신청 보완요청 정보
    * @throws Exception 예외
    */
    @Override
    public DelvryAplySplmntVo selectDelvryAplySplmntInfo(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        return delvryDao.selectDelvryAplySplmntInfo(delvryAplySplmntVo);
    }
    
    /**
    * 교부 신청 보완요청 등록
    *
    * @Title : insertDelvrySplmntAply
    * @Description : 교부 신청 보완요청 등록
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    @Override
    public int insertDelvryAplySplmnt(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        int retVal = 0;
        retVal = delvryDao.insertDelvryAplySplmnt(delvryAplySplmntVo);
        
        return retVal;
    }
    
    /**
    * 교부 신청 보완요청 업데이트
    *
    * @Title : updateDelvrySplmntAply
    * @Description : 교부 신청 보완요청 업데이트
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    @Override
    public int updateDelvryAplySplmnt(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        int retVal = 0;
        retVal = delvryDao.updateDelvryAplySplmnt(delvryAplySplmntVo);
        
        return retVal;
    }
    
    /**
    * 교부 신청 파일 목록 조회
    *
    * @Title : selectDelvryAplyFileList
    * @Description : 교부 신청 보완요청 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<DelvryAplyVo> 교부 신청 보완요청 목록
    * @throws Exception 예외
    */
    @Override
    public List<FileVo> selectDelvryAplyFileList(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.selectDelvryAplyFileList(delvryAplyVo);
    }
    
}