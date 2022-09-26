package com.kbrainc.plum.mng.example.excel.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.member.model.MemberVo;

/**
 * 
 * 사용자관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.service
 * 
 * - MemberService.java
 * </pre> 
 *
 * @ClassName : MemberService
 * @Description : 사용자관리 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface ExcelService {
	
	
	public void memberExcelDownList(MemberVo memberVo,	HttpServletResponse response, HttpServletRequest request) throws Exception;

	public Map<String, Object> memberExcelDatalValidationCheck(ArrayList list) throws Exception;

	public int memberWriteExcel(ArrayList list) throws Exception;

}