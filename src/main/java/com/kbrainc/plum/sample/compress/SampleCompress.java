package com.kbrainc.plum.sample.compress;

import java.io.IOException;

import com.kbrainc.plum.rte.util.FileUtil;

/**
 * 
 * [클래스 요약].
 *
 * <pre>
 * com.kbrainc.plum.sample.compress
 * - SampleCompress.java
 * </pre> 
 *
 * @ClassName : SampleCompress
 * @Description : TODO
 * @author : comnic
 * @date : 2021. 2. 22.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class SampleCompress {

	/**
	 * 
	 * 압축 샘플. 
	 *
	 * @Title : compressFileSample
	 * @Description : 파일 또는 디렉토리를 압축한다.
	 * @throws IOException
	 * @return void
	 */
	public void compressFileSample() throws IOException {
		
		// 디렉토리 압축
		FileUtil.compressZipFile(
				"/Users/comnic/Desktop/compress_test", 
				"/Users/comnic/Desktop/testFolder.zip"
		);
		
		// 파일 압축
		FileUtil.compressZipFile(
				"/Users/comnic/Desktop/compress_test/tree-nature-animal-cute-wildlife-zoo-655662-pxhere.com.jpg", 
				"/Users/comnic/Desktop/testFile.zip"
		);
			
	}
	
	/**
	 * 
	 * 압축 해제 샘픔. 
	 *
	 * @Title : decompressFileSample
	 * @Description : 압축 해제 샘픔
	 * @throws IOException
	 * @return void
	 */
	public void decompressFileSample() throws IOException {
		
		// 디렉토리 압축 해제
		FileUtil.decompressZipFile(
				"/Users/comnic/Desktop/testFolder.zip", 
				"/Users/comnic/Desktop"
		);
		
		// 파일 압축 해제
		FileUtil.decompressZipFile(
				"/Users/comnic/Desktop/testFile.zip", 
				"/Users/comnic/Desktop"
		);
			
	}

}
