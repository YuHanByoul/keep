package com.kbrainc.plum.mng.example.excel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kbrainc.plum.mng.example.excel.service.ExcelService;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.util.ExcelUtil;

/**
 * 
 * ExcelController.
 *
 * <pre>
 * com.kbrainc.plum.mng.example.excel.controller - ExcelController.java
 * </pre>
 *
 * @ClassName : ExcelController
 * @Description : excel 업로드/정합성 검사/다운로드 예시용 컨트롤러 실제 적용시 프로그램 및 메뉴등록등 절차 이후에 알맞게 커스터마이징 해야 합니다.
 * @author : KBRAINC
 * @date : 2022. 9. 21.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class ExcelController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ExcelService excelService;
	
	/**
	 * 회원정보 엑셀 다운로드 예시 
	 */
	@RequestMapping(value = "/example/mng/memberExcelRegisterPop.html")
	public String memberExcelRegister(HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws Exception {
        return "mng/member/memberExcelRegisterPop";
	}
	
	/**
	 * 회원정보 엑셀 다운로드 예시 
	 */
	@RequestMapping(value = "/example/mng/memberExcelDownList.do")
	public void crsExcelDownList(HttpServletRequest request, HttpServletResponse response, MemberVo memberVo) throws Exception {
		excelService.memberExcelDownList(memberVo, response, request);
	} 
	
	/**
	 * 회원정보 엑셀 데이터 정합성 체크
	 */
	@RequestMapping("/example/mng/memberExcelDataCheck.do")
    @ResponseBody
	public Map<String,Object> crsSeqExcelWriteCheck (MultipartHttpServletRequest multiRequest,HttpServletResponse response,ModelMap model)throws Exception{
		
		Map<String,Object> map = new HashMap();
		
		MultipartFile file = multiRequest.getFile("p_excelFile");
		ArrayList excelList = null;
		//엑셀내용을 리스트로
		if(file.getOriginalFilename().indexOf(".xlsx") >-1){
			excelList = ExcelUtil.getExcelPoiArrayList(file.getInputStream());
		}else if( file.getOriginalFilename().indexOf(".xls") >-1){
			excelList = ExcelUtil.getExcelJxlArrayList(file.getInputStream());
		}
		
		Map<String,Object> result = excelService.memberExcelDatalValidationCheck(excelList);//엑셀 체크
		
		map.put("excelList" ,(ArrayList)result.get("checkList"));
		map.put("successYn" ,(String)result.get("successYn"));
		
		return map;
	}
	
	/**
	 * 회원정보 엑셀 데이터 정합성 체크
	 */
	@RequestMapping("/example/mng/memberExcelWrite.do")
	@ResponseBody
	public Map<String,Object> memberExcelWrite (MultipartHttpServletRequest multiRequest,HttpServletResponse response,ModelMap model)throws Exception{
		
		Map<String,Object> map = new HashMap();
		
		MultipartFile file = multiRequest.getFile("p_excelFile");
		ArrayList excelList = null;
		//엑셀내용을 리스트로
		if(file.getOriginalFilename().indexOf(".xlsx") >-1){
			excelList = ExcelUtil.getExcelPoiArrayList(file.getInputStream());
		}else if( file.getOriginalFilename().indexOf(".xls") >-1){
			excelList = ExcelUtil.getExcelJxlArrayList(file.getInputStream());
		}
		
		int retVal = excelService.memberWriteExcel(excelList);//엑셀 체크
		
		String msg = "";
		
        if (retVal > 0) {
        	map.put("result", Constant.REST_API_RESULT_SUCCESS);
        	map.put("msg", "등록에 성공하였습니다.");
        } else {
        	map.put("result", Constant.REST_API_RESULT_FAIL);
        	map.put("msg", "등록에 실패했습니다.");
        }
		return map;
	}
	


}
