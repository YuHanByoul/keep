package com.kbrainc.plum.sample.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kbrainc.plum.rte.util.ExcelUtil;

/**
 * 
 * 엑셀 파일 읽기/쓰기 샘플.
 *
 * <pre>
 * com.kbrainc.plum.sample.excel
 * - SampleExcel.java
 * </pre> 
 *
 * @ClassName : SampleExcel
 * @Description : 엑셀 파일 읽기/쓰기 샘플.
 * @author : comnic
 * @date : 2021. 2. 25.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public class SampleExcel {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * 엑셀파일 읽기 샘플. 
	 *
	 * @Title : testReadExcelFile
	 * @Description : 엑셀파일 읽기 샘플
	 * @throws Exception
	 * @return void
	 */
	public void sampleReadExcelFile() throws Exception {
		FileInputStream fis;
		fis = new FileInputStream("/Users/comnic/Desktop/test.xlsx");
		ArrayList dataList = ExcelUtil.getExcelPoiArrayList(fis);
		
		for (int i = 0; i < dataList.size(); i++) {
			logger.info(dataList.get(i).toString());
		}
	}

	/**
	 * 
	 * 엑셀파일 쓰기 샘플.
	 *
	 * @Title : testWriteExcelFile
	 * @Description : 엑셀파일 쓰기 샘플.
	 * @throws Exception
	 * @return void
	 */
	public void sampleWriteExcelFile() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("Test1");
		list.add("Test2");
		list.add("Test3");
		list.add("Test4");
		list.add("Test5");
		
		// xls파일로 쓰기
		String targetPath = "/Users/comnic/Desktop/testSample.xls";
		ExcelUtil.writeExcel(list, targetPath, "xls");
		
		// xlsx파일로 쓰기
		String targetPath2 = "/Users/comnic/Desktop/testSample.xlsx";
		ExcelUtil.writeExcel(list, targetPath2, "xlsx");

	}

}
