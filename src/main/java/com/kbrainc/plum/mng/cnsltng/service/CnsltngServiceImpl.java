package com.kbrainc.plum.mng.cnsltng.service;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kbrainc.plum.cmm.service.AlimtalkNhnService;
import com.kbrainc.plum.cmm.service.MailNhnServiceImpl;
import com.kbrainc.plum.cmm.service.SmsNhnServiceImpl;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngDao;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngExprtGrpVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngExprtVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngResultVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.member.model.MemberDao;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

/**
 * 
 * 컨설팅 관리를 위한 서비스 구현.
 *
 * <pre>
 * com.kbrainc.plum.mng.site.service
 * - SiteServiceImpl.java
 * </pre> 
 *
 * @ClassName : CnsltngServiceImpl
 * @Description : 컨설팅 관리
 * @author : KBRAINC
 * @date : 2021. 3. 16.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class CnsltngServiceImpl extends PlumAbstractServiceImpl implements CnsltngService {
    
    @Autowired
    private CnsltngDao cnsltngDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private NtcnDao ntcnDao;
    
    @Autowired @Qualifier("MailNhnService")
    private MailService mailService;
    
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private SmsNhnServiceImpl smsNhnService;
    
    @Autowired
    private AlimtalkNhnService alimtalkService;
    
    /**
    * 컨설팅 리스트 호출 
    *
    * @Title       : selectConsultList 
    * @Description : 컨설팅 리스트 호출 
    * @param ConsultVo consultVo객체
    * @return List<ConsultVo>
    * @throws Exception 예외
    */
    public List<CnsltngVo> selectCnsltngList(CnsltngVo cnsltngVo) throws Exception{
        return cnsltngDao.selectCnsltngList(cnsltngVo);
    }
    
    /**
     * 컨설팅 상세정보 호출 
     *
     * @Title       : selectSiteApplyInfo 
     * @Description : 컨설팅 상세정보 호출
     * @param ConsultVo ConsultVo객체
     * @return ConsultVo
     * @throws Exception 예외
     */
    public CnsltngVo selectCnsltngtInfo(CnsltngVo cnsltngVo) throws Exception{
        return cnsltngDao.selectCnsltngtInfo(cnsltngVo);
    }
    /**
     * 컨설팅 신청 상태 수정 
     *
     * @Title       : updateConsultStatus 
     * @Description : 사이트 신청 상태 수정
     * @param ConsultVo ConsultVo객체
     * @return int
     * @throws Exception 예외
     */
    public int updateConsultStatus(CnsltngVo cnsltngVo) throws Exception{
        return cnsltngDao.updateConsultStatus(cnsltngVo);
    }
    /**
     * 컨설턴트 리스트 호출 
     *
     * @Title       : selectCnstntList 
     * @Description : 컨설턴트 리스트 호출 
     * @param CnsltngVo consultVo 객체
     * @return List<CnsltngVo>
     * @throws Exception 예외
     */
    public List<CnsltngVo> selectCnstntList(CnsltngVo cnsltngVo) throws Exception{
        return cnsltngDao.selectCnstntList(cnsltngVo);
    }
    /**
     * 전문가 리스트 호출 
     *
     * @Title       : selectCnsltngExprtList 
     * @Description : 전문가 리스트 호출 
     * @param CnsltngVo consultVo 객체
     * @return List<CnsltngExprtVo>
     * @throws Exception 예외
     */
    public List<CnsltngExprtVo> selectCnsltngExprtList(CnsltngExprtVo cnsltngExprtVo) throws Exception{
        return cnsltngDao.selectCnsltngExprtList(cnsltngExprtVo);
    }
    
    /**
     * 전문가 그룹 리스트 호출 
     *
     * @Title       : selectCnsltngExprtGrpList 
     * @Description : 전문가 리스트 호출 
     * @param PublicContestMngGrpVo CnsltngExprtGrpVo 객체
     * @return List<CnsltngExprtGrpVo>
     * @throws Exception 예외
     */
    public List<CnsltngExprtGrpVo> selectCnsltngExprtGrpList(CnsltngExprtGrpVo cnsltngExprtGrpVo) throws Exception{
        return cnsltngDao.selectCnsltngExprtGrpList(cnsltngExprtGrpVo);
    }
    /**
     * 컨설팅 담당자 일괄 삭제  
     *
     * @Title       : deleteCnsltnt 
     * @Description : 사이트 신청 상태 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteCnsltnt(CnsltngVo cnsltngVo) throws Exception{
        return cnsltngDao.deleteCnsltnt(cnsltngVo);
    }
    /**
     *  지역 시도 리스트 호출 
     *
     * @Title       : selectAddrCtprvnList 
     * @Description : 시도 리스트 호출 
     * @param Map<String,Object> 객체
     * @return List<Map<String,Object>
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectAddrCtprvnList(Map<String,Object> paramMap) throws Exception{
        return cnsltngDao.selectAddrCtprvnList(paramMap);
    }
    /**
     * 컨설팅 담당자 등록  
     *
     * @Title       : insertCnsltnt 
     * @Description : 사이트 신청 상태 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int insertCnsltnt(CnsltngVo cnsltngVo) throws Exception{
        
        List<String> compareList = new ArrayList();   
        List<String> paramList = new ArrayList();
        List<String> curExprt = new ArrayList();
        
        curExprt = cnsltngDao.selectCurrentExprtList(cnsltngVo);
        
        if(curExprt != null && curExprt.size() > 0 ) {
            //비교할 인원이 있음 
            if(cnsltngVo.getGrpYn()!= null && cnsltngVo.getGrpYn().equals("Y")) {
                //현재 인원과 비교 하여 중복 되지 않으면 insert
                compareList = cnsltngDao.selectExprtGrpUserIdList(cnsltngVo);
                compareList.removeAll(curExprt);
                paramList = compareList;
            }else{
                //현재 인원과 비교 하여 중복 되지 않으면 insert
                if(!curExprt.contains(String.valueOf(cnsltngVo.getCnstntid()))) {
                    paramList.add(String.valueOf(cnsltngVo.getCnstntid()));
                }
            }
        }else {
            if(cnsltngVo.getGrpYn()!= null && cnsltngVo.getGrpYn().equals("Y")) {
                compareList = cnsltngDao.selectExprtGrpUserIdList(cnsltngVo);
                paramList = compareList;
            }else{
                paramList.add(String.valueOf(cnsltngVo.getCnstntid()));
            }
        }
        
        if(paramList.size() > 0) {
            cnsltngVo.setCnstntids(paramList);
            cnsltngDao.insertCnsltnt(cnsltngVo);
        }
        
        cnsltngDao.selectCnstntList(cnsltngVo);
        
        
        return 1;
    }
    
    /**
     * 컨설팅 담당자 일괄 등록  
     *
     * @Title       : insertCnsltnt 
     * @Description : 사이트 신청 상태 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int insertCnsltntALL(CnsltngVo cnsltngVo) throws Exception{
        
        int resInt = 0;
        //중복제거
        List<String> list = cnsltngVo.getCnstntids();
        Set<String> set = new HashSet<String>(list);
        List<String> newList =new ArrayList<String>(set);
        cnsltngVo.setCnstntids(newList);
        resInt +=cnsltngDao.deleteCnsltntAll(cnsltngVo);
        resInt += cnsltngDao.insertCnsltnt(cnsltngVo);
        return resInt;
    }
    /**
     *  컨설팅 담당자 배정 메일 발송 
     *
     * @Title       : sendEmailForCnsltng 
     * @Description : 컨설팅 담당자 배정 메일 발송
     * @param MemberVo MemberVo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    @Async
    public int sendEmailForCnsltng(MemberVo memberVo,String prgrmNm) throws Exception{
            StringBuilder contents = new StringBuilder();
            contents.append("<tr><td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">");
            contents.append(memberVo.getNm());
            contents.append("님  " );
            contents.append(prgrmNm);
            contents.append("  의 지정단으로 배정되셨습니다." );
            contents.append("<br /><br />자세한 내용은 관리자 페이지에서 확인해주십시오.<br /><br />");
            contents.append("&nbsp;</td></tr><tr><td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td></tr>");
            Context context = new Context();
            context.setVariable("title", "[환경보전협회] "+prgrmNm+"의 지정단으로 배정되셨습니다.");
            context.setVariable("content", contents.toString());
            context.setVariable("portalUrl", CommonUtil.adminUrl);
            String mailContents = templateEngine.process("mail/mail_basic_template", context);
            String mailTitle = "[환경보전협회] "+prgrmNm+"의 지정단으로 배정되셨습니다.";
            MailVo mailVo = new MailVo(null, memberVo.getEml(), mailTitle, mailContents, Integer.valueOf(memberVo.getUser().getUserid()), "U", memberVo.getUserid());
            mailService.sendMail(mailVo); // 이메일 발송
        return 1;
    }
    
    /**
     * 컨설팅 설문리스트리스트 호출  
     *
     * @Title       : selectCnsltngSrvyList 
     * @Description : 컨설팅 설문리스트리스트 호출 
     * @param SrvyVo SrvyVo 객체
     * @return List<SrvyVo>
     * @throws Exception 예외
     */
    public List<SrvyVo> selectCnsltngSrvyList(SrvyVo srvyVo) throws Exception{
        return cnsltngDao.selectCnsltngSrvyList(srvyVo);   
    }
    /**
     * 컨설팅 상태 정보 수정  
     *
     * @Title       : updateCnsltnt 
     * @Description : 컨설팅 상태 정보 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    public int updateCnsltng(CnsltngVo cnsltngVo) throws Exception{
        return cnsltngDao.updateCnsltng(cnsltngVo);   
    }
    /**
     * 컨설팅 상태 정보 수정(알림등 발송 추가 )  
     *
     * @Title       : updateCnsltngInfo 
     * @Description : 컨설팅 상태 정보 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int updateCnsltngInfo(CnsltngVo cnsltngVo) throws Exception{
        int resInt = 0;
        //중복제거
        List<String> list = cnsltngVo.getCnstntids();
        Set<String> set = new HashSet<String>(list);
        List<String> newList =new ArrayList<String>(set);
        cnsltngVo.setCnstntids(newList);
        resInt +=cnsltngDao.deleteCnsltntAll(cnsltngVo);
        resInt += cnsltngDao.insertCnsltnt(cnsltngVo);
        resInt +=cnsltngDao.updateCnsltng(cnsltngVo);
        
        //알림 발송
        CnsltngVo resVo = cnsltngDao.selectCnsltngtInfo(cnsltngVo);
        
        for(String id : newList) {
            //알림 발송
            NtcnVo ntcnVo = new NtcnVo();
            ntcnVo.setUserid(Integer.parseInt(id));
            ntcnVo.setTtl("지정단으로 배정 완료");
            ntcnVo.setCn(
                    resVo.getPrgrmNm() +"의 지정단으로 배정 되셨습니다.\r\n"
                    + "자세한 내용은 관리자 사이트에서 확인해주세요.");
            ntcnVo.setInqYn("N");
            ntcnVo.setKndCd("156102");
            ntcnDao.insertNtcn(ntcnVo);
            //메일 발송
            MemberVo member = new MemberVo();
            member.setUser(cnsltngVo.getUser());
            member.setUserid(Integer.parseInt(id));
            MemberVo parmaVo = memberDao.selectMemberInfo(member);
            parmaVo.setUser(cnsltngVo.getUser());
            if(StringUtil.isNotNull(parmaVo.getEml())){
                sendEmailForCnsltng(parmaVo,resVo.getPrgrmNm());
            }
            //SMS 발송 
            String phone = parmaVo.getMoblphon();
            if(!"".equals(StringUtil.nvl(phone))) {
                String[] phonlist = new String[]{phone};
                String msg = "[환경보전협회] '"+resVo.getPrgrmNm()+"'우수 프로그램 지정제사업 지정단으로 배정되셨습니다.";
                Map<String, Object> resMap = smsNhnService.sendSms(msg,phonlist,""); // sms 발송
                String result = (String)((Map<String, Object>)(resMap.get("header"))).get("resultMessage");
            }
            //알림톡 발송
            String recipientList =  "[{\"recipientNo\": \""+phone+"\",\"templateParameter\": {\"nm\":\""+parmaVo.getNm()+"\", \"prgrmNm\":\""+resVo.getPrgrmNm()+"\"} }]"; 
            alimtalkService.sendAlimtalk("keep-030","",recipientList);
        }
        
        return resInt;   
    }
    /**
     * 컨설턴트 결과 정보 조회 
     *
     * @Title       : selectCnstntList 
     * @Description : 컨설턴트 리스트 호출 
     * @param CnsltngResultVo CnsltngResultVo 객체
     * @return List<CnsltngResultVo>
     * @throws Exception 예외
     */
    public CnsltngResultVo selectCnsltngtResult(CnsltngResultVo cnsltngResultVo) throws Exception{
        return cnsltngDao.selectCnsltngtResult(cnsltngResultVo);   
    }
    
    /**
     * 컨설팅 결과 저장   
     *
     * @Title       : mergeCnsltngRslt 
     * @Description : 컨설팅 결과 저장
     * @param CnsltngResultVo cnsltngResultVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int mergeCnsltngRslt(CnsltngResultVo cnsltngResultVo) throws Exception{
        return cnsltngDao.mergeCnsltngRslt(cnsltngResultVo);   
    }
    
    /**
     * 컨설턴트 엑셀 리스트 호출 
     *
     * @Title       : selectCnsltngExcelList 
     * @Description : 컨설턴트 엑셀 리스트 호출 
     * @param CnsltngVo consultVo 객체
     * @return List<CnsltngVo>
     * @throws Exception 예외
     */
    public List<CnsltngVo> selectCnsltngExcelList(CnsltngVo consultVo) throws Exception{
        return cnsltngDao.selectCnsltngExcelList(consultVo);
    }
    /**
     * 컨설팅 엑셀 다운로드  
     *
     * @Title       : cnstlngExcelDownList 
     * @Description : 컨설팅 엑셀 다운로드
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    @Override
    public void cnstlngExcelDownList(CnsltngVo cnsltngVo , HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<CnsltngVo> list = null;
        String realName = "";
        CnsltngVo modelVo = null;    

        realName = "컨설팅 검색 결과.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        //Font 설정.
        HSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        //제목의 스타일 지정
        HSSFCellStyle titlestyle = workbook.createCellStyle();
        titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        titlestyle.setFont(font);
        
        //내용 스타일 지정
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);        
        HSSFCellStyle styleR = workbook.createCellStyle();
        styleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleR.setFont(font);
        
        HSSFCellStyle styleL = workbook.createCellStyle();
        styleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleL.setFont(font);       
        HSSFSheet sheet = null;
        
        sheet = workbook.createSheet("sheet1");

        String [] titleArr = {
                "No."
                ,"프로그램명"
                ,"기관명"
                ,"컨설팅 유형"
                ,"전문가명"
                ,"진행상태"
                ,"신청일"
                ,"방문기간"
        };      

        //Row 생성
        HSSFRow row = sheet.createRow(0);
        //Cell 생성   
        HSSFCell cell = null;

        ArrayList<String> titleList = new ArrayList<String>();
        for(int i=0;i<titleArr.length;i++){
                titleList.add(titleArr[i]);
        }

        int titleCnt = 0;
        for(String title : titleList){
            cell = row.createCell(titleCnt++);
            cell.setCellValue(title);
            cell.setCellStyle(titlestyle);
        }

        list = cnsltngDao.selectCnsltngExcelList(cnsltngVo);
        
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1 
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                /*번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRowNumber(), ""));
                cell.setCellStyle(style);   
                /*프로그램명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getPrgrm(), ""));
                cell.setCellStyle(style);   
                /*기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(styleL);
                /*컨설팅유형*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCnsltngKndCdNm(), ""));
                cell.setCellStyle(styleL);
                /*전문가명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCnstntNm(), ""));
                cell.setCellStyle(styleL);
                /*진행상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSttsCdNm(),""));
                cell.setCellStyle(styleL);
                /*신청일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl( dateFormat.format(modelVo.getRegDt()), ""));
                cell.setCellStyle(styleR);
                
                /*방문기간 */
                String visitTm ="";
                visitTm =(StringUtil.nvl(modelVo.getVstDe(),"")=="")? "-"
                        :modelVo.getVstDe()+" "+modelVo.getVstBgngDt()+" ~ "+modelVo.getVstEndDt();
                cell = row.createCell(cellnum++);
                cell.setCellValue(visitTm);
                cell.setCellStyle(styleL);
            }

            for(int i=0;i<titleList.size();i++){
                sheet.autoSizeColumn((short)i);
                //sheet.setColumnWidth(i, sheet.getColumnWidth(i)+512);
            }
        }   
        
        ExcelUtils.excelInfoSet(response,realName);

        //엑셀 파일을 만듬
        OutputStream fileOutput = response.getOutputStream();
 
        workbook.write(fileOutput);
        fileOutput.flush();
        fileOutput.close();     
    }
    /**
     * 컨설팅 설문 결과 호출  
     *
     * @Title       : selectCnsltngtSrvyResult 
     * @Description : 컨설팅 설문 결과 호출 
     * @param CnsltngVo consultVo 객체
     * @return List<Map<String,Object>>
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectCnsltngtSrvyResult(CnsltngVo consultVo) throws Exception{
        return cnsltngDao.selectCnsltngtSrvyResult(consultVo);   
    }
}
