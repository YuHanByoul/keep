package com.kbrainc.plum.front.enveduFlct.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.enveduFlct.model.EnveduFcltVo;
import com.kbrainc.plum.front.enveduFlct.model.EnveduFlctDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;


/**
 * 내 주변 환경교육시설 Service
 *
 * <pre>
 * com.kbrainc.plum.front.enveduFlct.service
 * - EnveduFlctServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : EnveduFlctServiceImpl
 * @Description : 내 주변 환경교육시설 Service
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service
public class EnveduFlctServiceImpl extends PlumAbstractServiceImpl implements EnveduFlctService{
    
    @Autowired
    private EnveduFlctDao enveduFlctDao;
    
    @Autowired
    private FileDao fileDao;
    /**
     * 내 주변 환경교육 시설 목록을 반환한다
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 목록을 반환한다
     * @return 내 주변 환경교육 시설 목록
     * @throws Exception 예외
     */
    public List<Map<String, Object>> nearbyEnveduFlct(Map map) throws Exception {
        return enveduFlctDao.nearbyEnveduFlct(map);
    }
    
    /**
     * 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     *
     * @Title       : nearbyEnveduFlct
     * @Description : 내 주변 환경교육 시설 리스트 갯수를 반환한다.
     * @return int
     * @throws Exception 예외
     */
    public int nearbyEnveduFlctCount(Map map) throws Exception{
        return enveduFlctDao.nearbyEnveduFlctCount(map);
    }
    
    /**
    * 환경교육시설현황 목록 조회
    *
    * @Title : selectEnveduFcltList
    * @Description : 환경교육시설현황 목록 조회
    * @throws Exception
    * @return List<EnveduFcltVo>
    */
    @Override
    public List<EnveduFcltVo> selectEnveduFcltList() throws Exception {
        List<EnveduFcltVo> enveduFclt = enveduFlctDao.selectEnveduFcltList();
        
        for(int i = 0; i < enveduFclt.size(); i++) {
            if(enveduFclt != null && Objects.nonNull(enveduFclt.get(i).getRprsImgFileid())) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(enveduFclt.get(i).getRprsImgFileid().toString()));
                FileVo fileInfo= fileDao.getFileInfo(fileVo);
                enveduFclt.get(i).setFileInfo(fileInfo);
            }            
        }
        
        return enveduFclt;
    };

}
