package com.kbrainc.plum.mng.prtpn.infntPrgrm.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileGrpDao;
import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmDao;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
* 유아환경교육 -> 교육프로그램관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.service
* - InfntPrgrmServiceImpl.java
* </pre>
**
@ClassName : InfntPrgrmServiceImpl
* @Description : 유아환경교육 -> 교육프로그램관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class InfntPrgrmServiceImpl extends PlumAbstractServiceImpl implements InfntPrgrmService{
    
    @Autowired
    private InfntPrgrmDao infntPrgrmDao;
    
    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileGrpDao fileGrpDao;    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    @Override
    public List<InfntPrgrmVo> selectInfntPrgrmList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        return infntPrgrmDao.selectInfntPrgrmList(infntPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 게시글 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 게시글 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertInfntPrgrm(InfntPrgrmVo infntPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += infntPrgrmDao.insertInfntPrgrm(infntPrgrmVo);
        
        if(infntPrgrmVo.getTrgtCds()!=null & infntPrgrmVo.getTrgtCds().length > 0) {
            retVal += infntPrgrmDao.insertTrgtCd(infntPrgrmVo);
        }
        if(infntPrgrmVo.getClsfCds()!=null & infntPrgrmVo.getClsfCds().length > 0) {
            retVal += infntPrgrmDao.insertClsfCd(infntPrgrmVo);
        }

        retVal += infntPrgrmDao.insertInfntPrgrmTme(infntPrgrmVo);

        return retVal;
    }

    @Override
    @Transactional
    public int insertInfntPrgrmCopy(String[] copyPrgrmIds, UserVo userVo) throws Exception {
        int retVal = 0;
        InfntPrgrmVo infntPrgrmVo = new InfntPrgrmVo();
//        infntPrgrmVo.setCopyPrgrmIds(copyPrgrmIds);
        infntPrgrmVo.setUser(userVo);
        for(String copyPrgrmId : copyPrgrmIds) {
          infntPrgrmVo.setCopyPrgrmId(copyPrgrmId);
          InfntPrgrmVo infntCopyPrgrmFileVo = infntPrgrmDao.selectInfntCopyPrgrmFileInfo(infntPrgrmVo);
          
          FileGrpVo eduIntrcnFileGrpVo = new FileGrpVo();
          if(infntCopyPrgrmFileVo.getEduIntrcnFilegrpid() != null) {
              eduIntrcnFileGrpVo.setFilegrpid(Integer.parseInt(infntCopyPrgrmFileVo.getEduIntrcnFilegrpid()));
              FileVo eduIntrcnFileInfo = fileDao.selectFileInfo(eduIntrcnFileGrpVo);
              copyFile(userVo, eduIntrcnFileInfo, "eduIntrcn_file");
    
              infntPrgrmVo.setEduIntrcnFileid(eduIntrcnFileInfo.getFilegrpid());
          }
          FileGrpVo eduPhotoFileGrpVo = new FileGrpVo();
          if(infntCopyPrgrmFileVo.getEduPhotoFilegrpid() != null) {          
              eduPhotoFileGrpVo.setFilegrpid(Integer.parseInt(infntCopyPrgrmFileVo.getEduPhotoFilegrpid()));
              FileVo eduPhotoFileInfo = fileDao.selectFileInfo(eduPhotoFileGrpVo);
              copyFile(userVo, eduPhotoFileInfo, "eduPhotoFile_file");
              
              infntPrgrmVo.setEduPhotoFileid(eduPhotoFileInfo.getFilegrpid());
          }
          retVal += infntPrgrmDao.insertInfntPrgrmCopy(infntPrgrmVo);
          retVal += infntPrgrmDao.insertInfntPrgrmClsfMapngCopy(infntPrgrmVo);
          retVal += infntPrgrmDao.insertInfntPrgrmTrgtMapngCopy(infntPrgrmVo);
          retVal += infntPrgrmDao.insertInfntPrgrmTmeCopy(infntPrgrmVo);
        }
        
        
        return retVal;
    }

    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectInfntPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return InfntPrgrmVo
    */
    @Override
    public InfntPrgrmVo selectInfntPrgrmInfo(InfntPrgrmVo infntPrgrmVo) throws Exception{
        return infntPrgrmDao.selectInfntPrgrmInfo(infntPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateInfntPrgrm(InfntPrgrmVo infntPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += infntPrgrmDao.updateInfntPrgrm(infntPrgrmVo);
        
        infntPrgrmDao.deleteTrgtCd(infntPrgrmVo);
        infntPrgrmDao.deleteClsfCd(infntPrgrmVo);
        
        if(infntPrgrmVo.getTrgtCds()!=null & infntPrgrmVo.getTrgtCds().length > 0) {
            retVal += infntPrgrmDao.insertTrgtCd(infntPrgrmVo);
        }
        if(infntPrgrmVo.getClsfCds()!=null & infntPrgrmVo.getClsfCds().length > 0) {
            retVal += infntPrgrmDao.insertClsfCd(infntPrgrmVo);
        }

        InfntPrgrmVo delInfntPrgrmVo =  new InfntPrgrmVo();

        if(infntPrgrmVo.getInfntPrgrmVoList() != null) {
            retVal += infntPrgrmDao.insertInfntPrgrmTme(infntPrgrmVo);
            retVal += infntPrgrmDao.updateInfntPrgrmTme(infntPrgrmVo);
            for (int i=0; i<infntPrgrmVo.getInfntPrgrmVoList().size();i++){
                if("Y".equals(infntPrgrmVo.getInfntPrgrmVoList().get(i).getDelFlag())) {
                    delInfntPrgrmVo.setTmeId(infntPrgrmVo.getInfntPrgrmVoList().get(i).getTmeId());

                    int count = infntPrgrmDao.selectEduInfntPrgrmTmeSchdl(delInfntPrgrmVo.getTmeId());
                    if( count == 0 )
                        retVal += infntPrgrmDao.deleteInfntPrgrmTme(delInfntPrgrmVo);
            /*        retVal += infntPrgrmDao.deleteInfntPrgrmAplyEduTrgt(delInfntPrgrmVo);
                    retVal += infntPrgrmDao.deleteInfntPrgrmAply(delInfntPrgrmVo);
                    retVal += infntPrgrmDao.deleteInfntPrgrmTmeSchdl(delInfntPrgrmVo);
                    retVal += infntPrgrmDao.deleteInfntPrgrmTme(delInfntPrgrmVo);*/
                }
            }
        }

        return retVal;        
    }
    
    /**
    * 교육프로그램관리 회차 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertInfntPrgrmTme(InfntPrgrmVo infntPrgrmVo) throws Exception{
        int retVal = 0;
        InfntPrgrmVo delInfntPrgrmVo =  new InfntPrgrmVo();
        
        if(infntPrgrmVo.getInfntPrgrmVoList() != null) {
            retVal += infntPrgrmDao.insertInfntPrgrmTme(infntPrgrmVo);            
            retVal += infntPrgrmDao.updateInfntPrgrmTme(infntPrgrmVo);
            for (int i=0; i<infntPrgrmVo.getInfntPrgrmVoList().size();i++){
                if("Y".equals(infntPrgrmVo.getInfntPrgrmVoList().get(i).getDelFlag())) {
                    delInfntPrgrmVo.setTmeId(infntPrgrmVo.getInfntPrgrmVoList().get(i).getTmeId());
                    retVal += infntPrgrmDao.deleteInfntPrgrmAplyEduTrgt(delInfntPrgrmVo);
                    retVal += infntPrgrmDao.deleteInfntPrgrmAply(delInfntPrgrmVo);
                    retVal += infntPrgrmDao.deleteInfntPrgrmTmeSchdl(delInfntPrgrmVo);
                    retVal += infntPrgrmDao.deleteInfntPrgrmTme(delInfntPrgrmVo);
                }
            }
            
            //retVal += infntPrgrmDao.deleteInfntPrgrmTme(infntPrgrmVo);
        }
        return retVal;
    }
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateInfntPrgrmTme(InfntPrgrmVo infntPrgrmVo) throws Exception{
        return infntPrgrmDao.updateInfntPrgrmTme(infntPrgrmVo);
    }    
    
    /**
    * 교육프로그램관리 회차 조회
    *
    * @Title : selectInfntPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    @Override
    public List<InfntPrgrmVo> selectInfntPrgrmTmeList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        return infntPrgrmDao.selectInfntPrgrmTmeList(infntPrgrmVo);
    }    

    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    *
    * @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    @Override
    public List<InfntPrgrmVo> selectPrgrmSettingList(String rcptMthdCd) throws Exception {
        return infntPrgrmDao.selectPrgrmSettingList(rcptMthdCd);
    }    
    
    private void copyFile(UserVo userVo, FileVo fileVo, String filegrpNm) throws Exception {
        /* 파일 그룹 생성 */
        FileGrpVo fileGrpVo = new FileGrpVo();
        fileGrpVo.setFilegrpNm(filegrpNm);
        Integer rgtrid = userVo.getUserid() != null ? Integer.parseInt(userVo.getUserid()) : null;
        fileGrpVo.setRgtrid(rgtrid);
        fileGrpDao.newFileGrp(fileGrpVo);

        /* 파일 생성 */
        String fileNm = fileVo.getOrginlFileNm();
        String saveFileNm = fileVo.getSaveFileNm();
        UUID uuid = UUID.randomUUID();
        String copyFileNm = uuid + "_" + fileNm;
        String fileExt = fileNm.substring(fileNm.lastIndexOf("."));

        fileVo.setFilegrpid(fileGrpVo.getFilegrpid());
        fileVo.setFileExtsn(fileExt);
        fileVo.setRgtrid(fileGrpVo.getRgtrid());
        fileVo.setFileIdntfcKey(CommonUtil.getUUIdGnrBean().getNextBigDecimalId().toString());
        fileVo.setSaveFileNm(copyFileNm);

        fileDao.addFile(fileVo);

        File source = new File(fileVo.getFilePath() + "/" + saveFileNm);
        File target = new File(fileVo.getFilePath() + "/" + copyFileNm);
        Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }     
    
    /**
     * 유아환경교육(신청자) 설문지 목록 조회
     *
     * @Title : selectAplcntDgstfnSrvyList
     * @Description : 유아환경교육(신청자) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    @Override
    public List<SrvyVo> selectAplcntDgstfnSrvyList() throws Exception {
        return infntPrgrmDao.selectAplcntDgstfnSrvyList();
    }

    /**
     * 유아환경교육(학생) 설문지 목록 조회
     *
     * @Title : selectStdntDgstfnSrvyList
     * @Description : 유아환경교육(학생) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    @Override
    public List<SrvyVo> selectStdntDgstfnSrvyList() throws Exception {
        return infntPrgrmDao.selectStdntDgstfnSrvyList();
    }    
}
