/**
 * 
 */
package com.kbrainc.plum.front.report.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.report.model.ReportDao;
import com.kbrainc.plum.front.report.model.ReportOperVo;
import com.kbrainc.plum.front.report.model.ReportVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.front.report.service
* - ReportServiceImpl.java
* </pre> 
*
* @ClassName : ReportServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ReportServiceImpl extends PlumAbstractServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<ReportVo> selectReportList(ReportVo reportVo) throws Exception {
        // TODO Auto-generated method stub
        return reportDao.selectReportList(reportVo);
    }

    @Transactional
    @Override
    public int saveReport(ReportVo reportVo) throws Exception {
        // TODO Auto-generated method stub
        int retVal = 0;
        if (null != reportVo) {
            if (reportVo.getReportid() > 0) {
                retVal += reportDao.updateReport(reportVo);
            } else {
                retVal += reportDao.insertReport(reportVo);
            }
        }
        
        String data = reportVo.getJsonString();
        if (StringUtils.isNotBlank(data)) {
            JSONParser json = new JSONParser();
            JSONObject jsonobj = (JSONObject)json.parse(data);
            
            JSONArray dataArray = (JSONArray)jsonobj.get("data");
            if (CollectionUtils.isNotEmpty(dataArray)) {
                ReportOperVo reportOperVo = new ReportOperVo();
                reportOperVo.setUser(reportVo.getUser());
                reportOperVo.setReportid(reportVo.getReportid());
                
                for(int i = 0; i < dataArray.size(); i++){
                    JSONObject jsonObj = (JSONObject) dataArray.get(i);
                    String operid = jsonObj.get("operid") != null ? jsonObj.get("operid").toString().trim() : "";
                    String seCd = jsonObj.get("seCd") != null ? jsonObj.get("seCd").toString().trim() : "";
                    String bgngDe = jsonObj.get("bgngDe") != null ? jsonObj.get("bgngDe").toString().trim() : "";
                    String endDe = jsonObj.get("endDe") != null ? jsonObj.get("endDe").toString().trim() : "";
                    String rnd = jsonObj.get("rnd") != null ? jsonObj.get("rnd").toString().trim() : "";
                    String nope = jsonObj.get("nope") != null ? jsonObj.get("nope").toString().trim() : "";
                    String mnt = jsonObj.get("mnt") != null ? jsonObj.get("mnt").toString().trim() : "";
                    String rmrk = jsonObj.get("rmrk") != null ? jsonObj.get("rmrk").toString().trim() : "";
                    
                    reportOperVo.setOperid(Integer.valueOf(operid));
                    reportOperVo.setSeCd(seCd);
                    reportOperVo.setBgngDe(bgngDe);
                    reportOperVo.setEndDe(endDe);
                    reportOperVo.setRnd(Integer.valueOf(rnd));
                    reportOperVo.setNope(Integer.valueOf(nope));
                    reportOperVo.setMnt(Integer.valueOf(mnt));
                    reportOperVo.setRmrk(rmrk);
                    
                    if (reportOperVo.getOperid() > 0) {
                        reportDao.updateReportOper(reportOperVo);
                    } else {
                        reportDao.insertReportOper(reportOperVo);
                    }
                }
            }
        }
        
        return retVal;
    }

    @Override
    public List<ReportOperVo> selectReportOperList(ReportOperVo reportVo) throws Exception {
        // TODO Auto-generated method stub
        return reportDao.selectReportOperList(reportVo);
    }

    @Override
    public SupplementVo selectSplmntInfo(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return reportDao.selectSplmntInfo(supplementVo);
    }

    @Transactional
    @Override
    public int updateSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result += reportDao.updateSplmnt(supplementVo);
        result += reportDao.updateReportStts(supplementVo);
        return result;
    }

    @Override
    public ReportVo selectConsulting(ReportVo reportVo) throws Exception {
        // TODO Auto-generated method stub
        return reportDao.selectConsulting(reportVo);
    }
}
