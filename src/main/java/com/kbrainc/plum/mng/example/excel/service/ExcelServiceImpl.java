package com.kbrainc.plum.mng.example.excel.service;

import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.file.service.FileStorageService;
import com.kbrainc.plum.cmm.service.SmsService;
import com.kbrainc.plum.mng.member.model.MemberDao;
import com.kbrainc.plum.mng.member.model.MemberDtlVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.FormChecker;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;
import com.kbrainc.plum.rte.util.mail.service.MailService;

/**
 * 
 * 사용자관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.service
 * - ExcelServiceImpl.java
 * </pre> 
 *
 * @ClassName : MemberServiceImpl
 * @Description : 사용자관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Service
public class ExcelServiceImpl extends PlumAbstractServiceImpl implements ExcelService {

    @Autowired
    private MemberDao memberDao;

    @Autowired @Qualifier("MailService")
    private MailService mailService;
    
    //@Autowired
    //private FormChecker FormChecker;
    
    @Autowired
    private ResCodeService resCodeService;
    
    @Autowired
    private FileDao fileDao;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private SmsService smsService;
    
    //@Value("${front.server.host}")
    private String frontServerHost;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    
	/********************************************************************
	 * 회원 엑셀 다운로드 예시
	 * @param param
	 * @return
	 * @throws Exception
	 ********************************************************************/
    @Override
	public void memberExcelDownList(MemberVo memberVo ,	HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<MemberVo> list = null;
		String realName = "";
		MemberVo modelVo = null;	

		realName = "memberExcelList.xls";
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
				"사용자 번호"
				,"사용자 아이디"
				,"성명"
				,"생년월일"
				,"이메일"
				,"휴대폰 번호"
				,"주소"
				,"소개"
				,"사용여부"
				,"가입일"
				
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

		list = memberDao.selectMemberExcelList(memberVo);
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd  hh:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		
		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);
				
				//타이틀이 1개 row에 write 되어있음 따라서 i+1 
			    row = sheet.createRow((i+1));
			    cellnum = 0;
			    
			    /*사용자 번호*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getUserid(), ""));
			    cell.setCellStyle(style);
			    /*아이디*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getAcnt(), ""));
			    cell.setCellStyle(style);	
			    /*성명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getNm(), ""));
			    cell.setCellStyle(styleL);
			    /*생년월일*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getBrthdy(), ""));
			    cell.setCellStyle(styleL);
				/*이메일*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getEmail(), ""));
				cell.setCellStyle(styleL);
				/*휴대폰 번호*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getMobno(),""));
			    cell.setCellStyle(styleL);
			    /*주소*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getAddr(),"")+" "+StringUtil.nvl(modelVo.getAddr() ,""));
			    cell.setCellStyle(styleL);
			    /*소개*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getIntrcn(),""));
			    cell.setCellStyle(styleL); 
			    /*사용여부*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getDel_yn(),""));
			    cell.setCellStyle(styleL); 
			    /*등록일자*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl( dateFormat.format(modelVo.getReg_dt()), ""));
			    cell.setCellStyle(styleR);	
			    
			}

			for(int i=0;i<titleList.size();i++){
				sheet.autoSizeColumn((short)i);
				sheet.setColumnWidth(i, sheet.getColumnWidth(i)+512);
			}
		}	
		
		ExcelUtils.excelInfoSet(response,realName);

		//엑셀 파일을 만듬
		OutputStream fileOutput = response.getOutputStream();
 
		workbook.write(fileOutput);
		fileOutput.flush();
		fileOutput.close();		
	}

	@Override
	public Map<String, Object> memberExcelDatalValidationCheck(ArrayList list) throws Exception{
		
		Map<String,Object> param = new HashMap<>();
		
		boolean checkVal = true;
		ArrayList checkList = new ArrayList();
		if(list != null){			
			ArrayList usrList = null;
			String checkStr = "";
			
			for(int i=1; i<list.size(); i++ ){ //해더 제외
				usrList = (ArrayList)list.get(i);
				checkStr = memberDataCheckList(usrList);
				usrList.add(checkStr);
				
				checkList.add(usrList);	
				
				if(!checkStr.equals("OK")){
					checkVal = false;
				}
			}
		}
		if (checkVal){
			param.put("successYn", "Y");
		}else{
			param.put("successYn", "N");
		}
		param.put("checkList", checkList);
		
		return param;
	}
    
	
	/**
	  * 회원 엑셀 일괄등록 정합성 체크
	  * @param param
	  * @return
	  * @throws Exception
	  */
	public String memberDataCheckList(ArrayList data) throws Exception{
		
		
	    String checkStr = "";
        String checkStrSum = "";
       
        Map<String,Object> param = new HashMap<>();
       
        HashMap map = new HashMap();
       
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMdd" ) ;       
       
        if(data != null && data.size() > 0 ) {
        	
        	int index = 0;
		    checkStrSum = "";
		   
		    /**
			 * 
			 * @param dispName : 객체명
			 * @param nullYn : 필수 여부(Y,N)
			 * @param len : 최대 길이 
			 * @param dataType : S (String, default), N (number), E (email), J (jumin), P(phone), T(tel)
			 * @return
			 */
			//public static String Validate(String data, String dispName, String nullYn, int len, String dataType) {
		    
		    
		    //아이디 정합서 ㅇ체크 및 중복여부 체크 
		    MemberVo paramVo = new MemberVo();
		    checkStr = FormChecker.Validate((String)data.get(0), "아이디", "N", 100 , "S");
		    
			paramVo.setAcnt((String)data.get(0));
			String isDUplicated = memberDao.checkIdYn(paramVo);
			if(isDUplicated.equals("Y")) {
				checkStr = "이미 사용하고 있는아이디입니다.";
			}
		   	if(!checkStr.equals("")){
		   		if (index > 0) {
		   			checkStrSum += "<br/>";
		   		}
			   	index++;
			   	checkStrSum += index+". "+checkStr;
		    }
			
			//이름
			checkStr = FormChecker.Validate((String)data.get(1), "이름", "N", 100 , "S");
			if(!checkStr.equals("")){
				if (index > 0) {
					checkStrSum += "<br/>";
				}
	   			index++;
	   			checkStrSum += index+". "+checkStr;
			}

			//비밀번호
			checkStr =  FormChecker.Validate((String)data.get(2), "비밀번호", "N", 100 , "S");
			if(!checkStr.equals("")){
				if (index > 0) {
					checkStrSum += "<br/>";
				}
	   			index++;
	   			checkStrSum += index+". "+checkStr;
	   		}
			
			//이메일
			checkStr =  FormChecker.Validate((String)data.get(3), "이메일", "N", 200 , "S");
			if(!checkStr.equals("")){
				if (index > 0) {
					checkStrSum += "<br/>";
				}
				index++;
				checkStrSum += index+". "+checkStr;
			}
			
			//핸드폰
			checkStr =  FormChecker.Validate((String)data.get(4), "핸드폰", "N", 100 , "P");
			if(!checkStr.equals("")){
				if (index > 0) {
					checkStrSum += "<br/>";
				}
				index++;
				checkStrSum += index+". "+checkStr;
			}
			
			//이용약관 동의여부
			checkStr =  FormChecker.Validate((String)data.get(5), "이용약관 동의여부", "N", 1 , "YN");
			if(!checkStr.equals("")){
				if (index > 0) {
					checkStrSum += "<br/>";
				}
				index++;
				checkStrSum += index+". "+checkStr;
			}
			
			//개인정보 수집 동의여부
			checkStr =  FormChecker.Validate((String)data.get(6), "개인정보 수집 동의여부", "N", 1 , "YN");
			if(!checkStr.equals("")){
				if (index > 0) {
					checkStrSum += "<br/>";
				}
				index++;
				checkStrSum += index+". "+checkStr;
			}
			
			//소개
			checkStr =  FormChecker.Validate((String)data.get(7), "소개", "N", 500 , "S");
			if(!checkStr.equals("")){
				if (index > 0) {
					checkStrSum += "<br/>";
				}
				index++;
				checkStrSum += index+". "+checkStr;
			}

			if("".equals(checkStrSum)){ checkStrSum = "OK";}
   		
        }
		return checkStrSum;
	}
    
    
	public int memberWriteExcel(ArrayList list) throws Exception{
		int retVal=0;
		if(list != null){
			ArrayList memberList = null;
			
			for(int i=1; i<list.size(); i++ ){ //해더 제외
				memberList = (ArrayList)list.get(i);
				insertMemberByExcelWrite(memberList);
			}
		}	
		retVal = 1;
		return retVal;
	}
    
	
	public int insertMemberByExcelWrite(ArrayList data) throws Exception {
		
		int retVal = 0;
		
		MemberVo memVo = new MemberVo();
		MemberDtlVo memDtlVo = new MemberDtlVo();
		//아이디
		memVo.setAcnt((String)data.get(0));
		//이름
		memVo.setNm((String)data.get(1));
		//비밀번호
        String password = (String)data.get(2);
        String hashPassword = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(password.getBytes("UTF-8")));
		memVo.setPwd(hashPassword);
		//이메일
		memVo.setEmail((String)data.get(3));
		//핸드폰
		memVo.setMobno((String)data.get(4));
		//이용약관동의여부
		memVo.setTos_agre_yn((String)data.get(5));
		//개징정보 수집 동의여부
		memVo.setPrvcy_agre_yn((String)data.get(6));
		//계정잠김여부
		memVo.setAcnt_lock_yn("N");
		//삭데여부
		memVo.setDel_yn("N");
		//사용자 구분 코드
		memVo.setUser_se_cd("P");
		
        retVal += memberDao.insertMember(memVo);
        // selectKey userid 받아옴
        memDtlVo.setUserid(memVo.getUserid());
        //소개
        memDtlVo.setIntrcn((String)data.get(7));
        retVal += memberDao.insertMemberDtl(memDtlVo);

		return retVal;
	}

}