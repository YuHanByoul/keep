package com.kbrainc.plum.mng.prtpn.mvmnPrgrm.service;

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
import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmDao;
import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
* 푸름이이동환경교실 -> 교육프로그램관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnPrgrm.service
* - MvmnPrgrmServiceImpl.java
* </pre>
**
@ClassName : MvmnPrgrmServiceImpl
* @Description : 푸름이이동환경교실 -> 교육프로그램관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MvmnPrgrmServiceImpl extends PlumAbstractServiceImpl implements MvmnPrgrmService{
    
    @Autowired
    private MvmnPrgrmDao mvmnPrgrmDao;
    
    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileGrpDao fileGrpDao;        
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    @Override
    public List<MvmnPrgrmVo> selectMvmnPrgrmList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception {
        return mvmnPrgrmDao.selectMvmnPrgrmList(mvmnPrgrmVo);
    }

    @Override
    @Transactional
    public int insertMvmnPrgrmCopy(String[] copyPrgrmIds, UserVo userVo) throws Exception {
        int retVal = 0;
        MvmnPrgrmVo mvmnPrgrmVo = new MvmnPrgrmVo();
        mvmnPrgrmVo.setUser(userVo);

        for(String copyPrgrmId : copyPrgrmIds) {
          mvmnPrgrmVo.setCopyPrgrmId(copyPrgrmId);
          MvmnPrgrmVo mvmnCopyPrgrmFileVo = mvmnPrgrmDao.selectMvmnCopyPrgrmFileInfo(mvmnPrgrmVo);
          
          FileGrpVo eduIntrcnFileGrpVo = new FileGrpVo();
          if(mvmnCopyPrgrmFileVo.getEduIntrcnFilegrpid() != null) {
              eduIntrcnFileGrpVo.setFilegrpid(Integer.parseInt(mvmnCopyPrgrmFileVo.getEduIntrcnFilegrpid()));
              FileVo eduIntrcnFileInfo = fileDao.selectFileInfo(eduIntrcnFileGrpVo);
              copyFile(userVo, eduIntrcnFileInfo, "eduIntrcn_file");
    
              mvmnPrgrmVo.setEduIntrcnFileid(eduIntrcnFileInfo.getFilegrpid());
          }
          
          FileGrpVo eduPhotoFileGrpVo = new FileGrpVo();
          if(mvmnCopyPrgrmFileVo.getEduPhotoFilegrpid() != null) {          
              eduPhotoFileGrpVo.setFilegrpid(Integer.parseInt(mvmnCopyPrgrmFileVo.getEduPhotoFilegrpid()));
              FileVo eduPhotoFileInfo = fileDao.selectFileInfo(eduPhotoFileGrpVo);
              copyFile(userVo, eduPhotoFileInfo, "eduPhotoFile_file");
              
              mvmnPrgrmVo.setEduPhotoFileid(eduPhotoFileInfo.getFilegrpid());
          }
          
          retVal += mvmnPrgrmDao.insertMvmnPrgrmCopy(mvmnPrgrmVo); // 이동_프로그램 복사
          retVal += mvmnPrgrmDao.insertMvmnPrgrmClsfMapngCopy(mvmnPrgrmVo); // 이동 프로그램 분류 복사
          retVal += mvmnPrgrmDao.insertMvmnPrgrmTrgtMapngCopy(mvmnPrgrmVo); //이동 프로그램 대상 복사
          retVal += mvmnPrgrmDao.insertMvmnPrgrmTmeCopy(mvmnPrgrmVo); // 이동 프로그램 회차 복사
        }
        
        
        return retVal;
    }

    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectMvmnPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return MvmnPrgrmVo
    */
    @Override
    public MvmnPrgrmVo selectMvmnPrgrmInfo(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        return mvmnPrgrmDao.selectMvmnPrgrmInfo(mvmnPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateMvmnPrgrm(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += mvmnPrgrmDao.updateMvmnPrgrm(mvmnPrgrmVo);
        
        mvmnPrgrmDao.deleteTrgtCd(mvmnPrgrmVo);
        mvmnPrgrmDao.deleteClsfCd(mvmnPrgrmVo);
        
        if(mvmnPrgrmVo.getTrgtCds()!=null & mvmnPrgrmVo.getTrgtCds().length > 0) {
            retVal += mvmnPrgrmDao.insertTrgtCd(mvmnPrgrmVo);
        }
        if(mvmnPrgrmVo.getClsfCds()!=null & mvmnPrgrmVo.getClsfCds().length > 0) {
            retVal += mvmnPrgrmDao.insertClsfCd(mvmnPrgrmVo);
        }

        MvmnPrgrmVo delMvmnPrgrmVo =  new MvmnPrgrmVo();

        if(mvmnPrgrmVo.getMvmnPrgrmVoList() != null) {
            retVal += mvmnPrgrmDao.insertMvmnPrgrmTme(mvmnPrgrmVo); // 새로 추가된 회차 INSERT
            retVal += mvmnPrgrmDao.updateMvmnPrgrmTme(mvmnPrgrmVo); // 기존 회차 UPDATE
            for (int i=0; i<mvmnPrgrmVo.getMvmnPrgrmVoList().size();i++){
                if("Y".equals(mvmnPrgrmVo.getMvmnPrgrmVoList().get(i).getDelFlag())) {
                    // 교육일정에 매핑된 내역 존재 시 삭제 불가
                    delMvmnPrgrmVo.setTmeId(mvmnPrgrmVo.getMvmnPrgrmVoList().get(i).getTmeId());
                    int count = mvmnPrgrmDao.selectEduMvmnPrgrmTmeSchdl(delMvmnPrgrmVo.getTmeId());
                    if( count == 0 )
                        retVal += mvmnPrgrmDao.deleteMvmnPrgrmTme(delMvmnPrgrmVo);
//                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmAplyEduTrgt(delMvmnPrgrmVo); // 이동 프로그램 신청 교육 대상
//                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmAply(delMvmnPrgrmVo); // 이동 프로그램 신청
//                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmTmeSchdl(delMvmnPrgrmVo); //이동 프로그램 회차 일정
//                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmTme(delMvmnPrgrmVo); // 이동 프로그램 회차
                }
            }
        }

        return retVal;        
    }


    /**
     * 교육프로그램관리 게시글 등록
     *
     * @Title : insertMvmnPrgrm
     * @Description : 교육프로그램관리 게시글 등록
     * @param mvmnPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예회
     * @return int
     */
    @Override
    @Transactional
    public int insertMvmnPrgrm(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += mvmnPrgrmDao.insertMvmnPrgrm(mvmnPrgrmVo);

        if(mvmnPrgrmVo.getTrgtCds()!=null & mvmnPrgrmVo.getTrgtCds().length > 0) {
            retVal += mvmnPrgrmDao.insertTrgtCd(mvmnPrgrmVo);
        }
        if(mvmnPrgrmVo.getClsfCds()!=null & mvmnPrgrmVo.getClsfCds().length > 0) {
            retVal += mvmnPrgrmDao.insertClsfCd(mvmnPrgrmVo);
        }

        retVal += mvmnPrgrmDao.insertMvmnPrgrmTme(mvmnPrgrmVo);

        return retVal;
    }

    /**
    * 교육프로그램관리 회차 등록, 사용X. 테스트 후 삭제 필요
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        int retVal = 0;
        MvmnPrgrmVo delMvmnPrgrmVo =  new MvmnPrgrmVo();
        
        if(mvmnPrgrmVo.getMvmnPrgrmVoList() != null) {
            retVal += mvmnPrgrmDao.insertMvmnPrgrmTme(mvmnPrgrmVo); // 새로 추가된 회차 INSERT
            retVal += mvmnPrgrmDao.updateMvmnPrgrmTme(mvmnPrgrmVo); // 기존 회차 UPDATE
            for (int i=0; i<mvmnPrgrmVo.getMvmnPrgrmVoList().size();i++){
                if("Y".equals(mvmnPrgrmVo.getMvmnPrgrmVoList().get(i).getDelFlag())) {
                    // 삭제 체크 된 경우
                    // 교육일정에 매핑된 내역 존재 시 삭제 불가
                    delMvmnPrgrmVo.setTmeId(mvmnPrgrmVo.getMvmnPrgrmVoList().get(i).getTmeId());
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmAplyEduTrgt(delMvmnPrgrmVo);
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmAply(delMvmnPrgrmVo);
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmTmeSchdl(delMvmnPrgrmVo);
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmTme(delMvmnPrgrmVo);
                }
            }
            
            //retVal += mvmnPrgrmDao.deleteMvmnPrgrmTme(mvmnPrgrmVo);
        }
        return retVal;
    }
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        return mvmnPrgrmDao.updateMvmnPrgrmTme(mvmnPrgrmVo);
    }    
    
    /**
    * 교육프로그램관리 회차 조회
    *
    * @Title : selectMvmnPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    @Override
    public List<MvmnPrgrmVo> selectMvmnPrgrmTmeList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception {
        return mvmnPrgrmDao.selectMvmnPrgrmTmeList(mvmnPrgrmVo);
    }    

    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    *
    * @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    @Override
    public List<MvmnPrgrmVo> selectPrgrmSettingList(String operFomCd) throws Exception {
        return mvmnPrgrmDao.selectPrgrmSettingList(operFomCd);
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
     * 푸름이이동환경교실(신청자) 설문지 목록 조회
     *
     * @Title : selectAplcntDgstfnSrvyList
     * @Description : 푸름이이동환경교실(신청자) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    @Override
    public List<SrvyVo> selectAplcntDgstfnSrvyList() throws Exception {
        return mvmnPrgrmDao.selectAplcntDgstfnSrvyList();
    }

    /**
     * 푸름이이동환경교실(학생) 설문지 목록 조회
     *
     * @Title : selectStdntDgstfnSrvyList
     * @Description : 푸름이이동환경교실(학생) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    @Override
    public List<SrvyVo> selectStdntDgstfnSrvyList() throws Exception {
        return mvmnPrgrmDao.selectStdntDgstfnSrvyList();
    }        
}
