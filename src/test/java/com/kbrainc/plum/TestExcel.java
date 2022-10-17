package com.kbrainc.plum;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kbrainc.plum.rte.util.ExcelUtil;

public class TestExcel {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testReadExcelFile() throws Exception {
		FileInputStream fis;
		fis = new FileInputStream("/Users/comnic/Desktop/test.xlsx");
		ArrayList dataList = ExcelUtil.getExcelPoiArrayList(fis);
		
		for (int i = 0; i < dataList.size(); i++) {
			logger.info(dataList.get(i).toString());
		}
	}

	@Test
	public void testWriteExcelFile() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("Test1");
		list.add("Test2");
		list.add("Test3");
		list.add("Test4");
		list.add("Test5");
		
		String targetPath = "/Users/comnic/Desktop/testSample.xls";
		ExcelUtil.writeExcel(list, targetPath, "xls");
		
		String targetPath2 = "/Users/comnic/Desktop/testSample.xlsx";
		ExcelUtil.writeExcel(list, targetPath2, "xlsx");

	}
}
