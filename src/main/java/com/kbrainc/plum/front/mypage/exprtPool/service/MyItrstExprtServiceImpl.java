package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 마이페이지 > 전문가 풀 관리 > 관심 전문가 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyItrstExprtServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyItrstExprtServiceImpl
 * @Description : 마이페이지 > 전문가 풀 관리 > 관심 전문가 서비스 구현 클래스
 * @date : 2023. 03. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class MyItrstExprtServiceImpl extends PlumAbstractServiceImpl implements MyItrstExprtServcice {

    @Autowired
    private MyItrstExprtDao myItrstExprtDao;

    @Value("${crypto.key}")
    private String encryptKey;

    StandardPBEStringEncryptor encryptor;

    @PostConstruct
    public void init() throws Exception {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
    }

    /**
     * 관심 전문가 목록 조회
     *
     * @param user
     * @return list
     * @throws Exception
     * @Title : selectItrstExprtList
     * @Description : 관심 전문가 목록 조회
     */
    @Override
    public List<ExprtVo> selectItrstExprtList(ExprtVo searchVo) throws Exception {
        ExprtVo exprtVo = new ExprtVo();
        exprtVo.setPageNumber(searchVo.getPageNumber());
        exprtVo.setUser(searchVo.getUser());

        List<ExprtVo> exprts = myItrstExprtDao.selectItrstExprtList(exprtVo);
        for (ExprtVo exprt : exprts) {
            String decStr = encryptor.decrypt(exprt.getGndr());
            exprt.setGndr(decStr);
        }

        return exprts;
    }
}
