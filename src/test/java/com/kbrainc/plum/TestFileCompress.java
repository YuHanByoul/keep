package com.kbrainc.plum;

import java.io.IOException;

import org.junit.Test;

import com.kbrainc.plum.rte.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFileCompress {

	@Test
	public void testFileCompress() {
		// 디렉토리 압축
		FileUtil.compressZipFile("/Users/comnic/Desktop/compress_test", "/Users/comnic/Desktop/testFolder.zip");
		
		// 파일 압축
		FileUtil.compressZipFile("/Users/comnic/Desktop/compress_test/tree-nature-animal-cute-wildlife-zoo-655662-pxhere.com.jpg", "/Users/comnic/Desktop/testFile.zip");
	}
	
	@Test
	public void testFileDecompress() {
		
		// 디렉토리 압축 해제
		try {
			FileUtil.decompressZipFile("/Users/comnic/Desktop/testFolder.zip", "/Users/comnic/Desktop");
		} catch (IOException e) {
			log.error("testFileDecompress.IOException.30L");
		}
		
		// 파일 압축 해제
		try {
			FileUtil.decompressZipFile("/Users/comnic/Desktop/testFile.zip", "/Users/comnic/Desktop");
		} catch (IOException e) {
		    log.error("testFileDecompress.IOException.37L");
		}
	}
	
}
