package com.kbrainc.plum.rte.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;

/**
 * 
 * FileUtil 클래스
 *
 * <pre>
 * com.kbrainc.plum.rte.util
 * - FileUtil.java
 * </pre> 
 *
 * @ClassName : FileUtil
 * @Description : FileUtil 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    /** global.properties의 Configuration객체 */
    /*private static Configuration applicationConfig = ConfigurationFactory.getInstance().getApplicationConfig();*/
    /** 파일업로드경로 */
    public static final String UPLOAD_PATH = "upload";// globalConfig.getString("Global.Upload.Path");
    /** upload컨텐스트 */
    public static final String CONTEXT_UPLOAD_PATH = "upload";// globalConfig.getString("Global.Context.Upload.Path");
    /** html to pdf path */
    public static final String HTML_TO_PDF_PATH = "pdf";// globalConfig.getString("Global.HtmltoPdf.Path");

    /**
     * Desc : Constructor of FileUtil.java class
     */
    public FileUtil() {
    }

    /**
     * @Title : delFile
     * @Description : 파일을 삭제한다.
     * @param path 파일경로
     * @return void
     */
    public static void delFile(String path) {
        File cFile = new File(path);
        if (cFile.exists()) {
            cFile.delete();
        }
    }

    /**
     * @Title : setDirectory
     * @Description : 업로드 디렉토리 세팅
     * @param directory 업로드디렉토리경로
     * @return boolean 디렉토리생성여부
     */
    public static boolean setDirectory(String directory) {
        File wantedDirectory = new File(directory);
        if (wantedDirectory.isDirectory()) {
            return true;
        }
        return wantedDirectory.mkdirs();
    }

    /**
     * @Title : delDirectory
     * @Description : 디렉토리 삭제
     * @param path 디렉토리경로
     * @return void
     */
    public static void delDirectory(String path) {
        String filePath = path;
        File dirFile = new File(filePath);
        String[] fileList = dirFile.list();

        String lastStr = "/";
        if (filePath.lastIndexOf("\\") > -1) {
            lastStr = "\\";
        }
        if (!filePath.substring(filePath.length() - 1).equals(lastStr)) {
            filePath += lastStr;
        }

        if (dirFile.exists()) {
            for (int i = 0; i < fileList.length; i++) {
                File subFile = new File(filePath + fileList[i]);

                if (subFile.isDirectory()) {
                    delDirectory(filePath + fileList[i]);
                } else {
                    subFile.delete();
                }
            }
            dirFile.delete();
        }
    }

    /**
     * @Title : getFileExtention
     * @Description : 파일의 확장명 검색
     * @param fname 파일명
     * @return String 확장자문자열
     */
    public static String getFileExtention(String fname) {
        if (!fname.equals("")) {
            int lstIn = fname.lastIndexOf('.');
            String ext = fname.substring(lstIn + 1);
            return ext.toLowerCase(new Locale(ext));
        } else {
            return "";
        }
    }

    /**
     * @Title : getFileExistRename
     * @Description : 중복파일 이름 여부 체크 존재 시 시퀀스를 붙인 파일이름 반환 ex) a.txt -> a-1.txt
     * @param filePath 파일경로 ex) "c:/test/"
     * @param fileName 파일명 ex) "a.txt"
     * @return String 파일명
     */
    public static String getFileExistRename(String filePath, String fileName) {
        String rtn = "";
        String fileType = "";
        String name = "";

        try {
            int iFileTypeIndex = fileName.lastIndexOf('.');
            // 뒤에서 부터 .을 찾아 파일의 확장자를 얻는다.
            fileType = fileName.substring(iFileTypeIndex + 1);
            if (iFileTypeIndex > 0) {
                name = fileName.substring(0, iFileTypeIndex);
            } else {
                name = fileName;
            }

            // 파일명 찾기
            File cFile = new File(filePath + fileName);
            String imsi1 = "", imsi2 = "";
            int bracketIdx = 0;
            int imsiNum = 1;
            int check = 0;
            if (!cFile.exists()) {
                rtn = fileName;
            } else {
                bracketIdx = name.lastIndexOf('-');
                if (bracketIdx < 1) {
                    name = name + "-1";
                } else {
                    imsi1 = name.substring(bracketIdx + 1);
                    imsi2 = name.substring(0, bracketIdx);
                    try {
                        imsiNum = Integer.parseInt(imsi1);
                        check = 1;
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (check > 0) {
                        name = imsi2 + "-" + (imsiNum + 1);
                    }
                }

                rtn = getFileExistRename(filePath, name + "." + fileType);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return rtn;
    }

    /**
     * @Title : copyDirectoryFile
     * @Description : 지정된 위치에 있는 디렉토리와 파일들을 대상 위치로 모두 복사한다.
     * @param source 소스
     * @param target 타켓
     * @return boolean 성공여부
     */
    public static boolean copyDirectoryFile(String source, String target) {
        boolean result = true;
        int bufSize = 4096;
        byte buf[] = new byte[bufSize];

        String delimeter = "/";
        if (source.lastIndexOf("\\") > -1) {
            delimeter = "\\";
        }

        setDirectory(target);

        File sourceFile = new File(source);
        if (sourceFile.isDirectory()) {
            File sourceFileList[] = sourceFile.listFiles();
            for (int i = 0; i < sourceFileList.length; i++) {
                File sFile = sourceFileList[i];
                String newSource = sFile.getPath();
                String newTarget = target + delimeter + sFile.getName();

                if (sFile.isDirectory()) { // 디렉토리
                    copyDirectoryFile(newSource, newTarget);
                } else if (sFile.isFile()) { // 파일
                    InputStream in = null;
                    OutputStream out = null;

                    try {
                        in = new FileInputStream(sFile);
                        out = new FileOutputStream(newTarget, true);
                        int read = in.read(buf,0,bufSize);
                        while (read > 0) {
                            out.write(buf, 0, read);
                            read = in.read(buf,0,bufSize);
                        }

                        in.close();
                        out.close();
                    } catch (IOException e) {
                        result = false;
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                result = false;
                            }
                        }
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                result = false;
                            }
                        }
                    }
                }
            }
        } else if (sourceFile.isFile()) {
            InputStream in = null;
            OutputStream out = null;

            try {
                String newTarget = target + delimeter + sourceFile.getName();
                in = new FileInputStream(sourceFile);
                out = new FileOutputStream(newTarget, true);
                int read = in.read(buf,0,bufSize);
                while (read > 0) {
                    out.write(buf, 0, read);
                    read = in.read(buf,0,bufSize);
                }

                in.close();
                out.close();
            } catch (IOException e) {
                result = false;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * @Title : copyDirectoryDateFile
     * @Description : 파일수정일자가 오늘이후인것만 복사한다.
     * @param source 소스
     * @param target 타멧
     * @return boolean 성공여부
     */
    public static boolean copyDirectoryDateFile(String source, String target) {
        boolean result = true;
        int bufSize = 4096;
        byte buf[] = new byte[bufSize];

        Date td = new Date();
        Date fd = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

        String fmtd = formatter.format(td); // 오늘날짜값 yyyy.MM.dd
        String fmfd;

        String delimeter = "/";
        if (source.lastIndexOf("\\") > -1) {
            delimeter = "\\";
        }

        setDirectory(target);

        File sourceFile = new File(source);
        if (sourceFile.isDirectory()) {
            File sourceFileList[] = sourceFile.listFiles();
            for (int i = 0; i < sourceFileList.length; i++) {
                File sFile = sourceFileList[i];
                String newSource = sFile.getPath();
                String newTarget = target + delimeter + sFile.getName();

                if (sFile.isDirectory()) { // 디렉토리
                    copyDirectoryDateFile(newSource, newTarget);
                } else if (sFile.isFile()) { // 파일
                    InputStream in = null;
                    OutputStream out = null;

                    fd.setTime(sFile.lastModified());// 파일수정날짜
                    fmfd = formatter.format(fd);

                    if (fmfd.compareTo(fmtd) < 0) {
                        continue;// 파일수정날짜와 오늘날짜 비교
                    }

                    try {
                        in = new FileInputStream(sFile);
                        out = new FileOutputStream(newTarget, true);

                        int read = in.read(buf,0,bufSize);
                        while (read > 0) {
                            out.write(buf, 0, read);
                            read = in.read(buf,0,bufSize);
                        }

                        in.close();
                        out.close();
                    } catch (IOException e) {
                        result = false;
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                result = false;
                            }
                        }
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                result = false;
                            }
                        }
                    }
                }
            }
        } else if (sourceFile.isFile()) {
            InputStream in = null;
            OutputStream out = null;

            try {
                fd.setTime(sourceFile.lastModified()); // 파일수정날짜
                fmfd = formatter.format(fd);

                if (fmfd.compareTo(fmtd) < 0) {
                    String newTarget = target + delimeter + sourceFile.getName();
                    in = new FileInputStream(sourceFile);
                    out = new FileOutputStream(newTarget, true);

                    int read = in.read(buf,0,bufSize);
                    while (read > 0) {
                        out.write(buf, 0, read);
                        read = in.read(buf,0,bufSize);
                    }

                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                result = false;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * @Title : compressZipFile
     * @Description : 파일 압축하기(target을 입력하지 않을경우 source 경로에 생성한다.)
     * @param source 소스
     * @param target 타겟
     * @return boolean 성공여부
     */
    public static boolean compressZipFile(String source, String target) {
        String targetPath = target;
        boolean result = true;

        File inFile = new File(source);

        int bufSize = 1024;
        byte[] buf = new byte[bufSize];

        ZipArchiveOutputStream outputStream = null;

        int lastIdx = source.lastIndexOf("/");
        if (lastIdx == -1) {
            lastIdx = source.lastIndexOf("\\");
        }

        if (targetPath == null || targetPath.equals("")) {
            targetPath = source.substring(0, lastIdx + 1);
            String targetName = inFile.getName();

            int extIdx = targetName.lastIndexOf(".");
            if (extIdx > -1) {
                targetName = targetName.substring(0, extIdx);
            }
            targetPath = targetPath + targetName + ".zip";
        }

        try {
            outputStream = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(targetPath)));
            compressZipFileOutput(outputStream, inFile, source);

            outputStream.closeArchiveEntry();
            outputStream.close();
        } catch (Exception e) {
            result = false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * @Title : compressZipFileOutput
     * @Description : 파일 압축하기 - zip파일에 쓰기
     * @param outputStream 출력스트림
     * @param file         파일객체
     * @param source       소스
     * @return void
     */
    private static void compressZipFileOutput(ZipArchiveOutputStream outputStream, File file, String source) {
        int bufSize = 1024;
        byte[] buf = new byte[bufSize];

        FileInputStream fInputStream = null;
        BufferedInputStream bInputStream = null;

        try {
            // 파일인 경우
            if (file.isFile()) {
                String entryPath = file.getPath().substring(source.length());
                if (entryPath.equals("")) {
                    entryPath = file.getName();
                }
                ZipArchiveEntry entry = new ZipArchiveEntry(entryPath);
                outputStream.putArchiveEntry(entry);

                fInputStream = new FileInputStream(file.getPath());
                bInputStream = new BufferedInputStream(fInputStream, bufSize);

                outputStream.setLevel(8);

                int len = bInputStream.read(buf, 0, bufSize);
                while (len > 0) {
                    outputStream.write(buf, 0, len);
                    len = bInputStream.read(buf, 0, bufSize);
                }

            } else if (file.isDirectory()) { // 디렉토리인 경우
                File fileList[] = file.listFiles(); // 파일목록

                for (int i = 0; i < fileList.length; i++) {
                    compressZipFileOutput(outputStream, fileList[i], source);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (bInputStream != null) {
                    bInputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (fInputStream != null) {
                    fInputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
    
    /**
     * 
     * zip파일의 압축을 해제한다. 
     *
     * @Title : decompressZipFile
     * @Description : zip파일의 압축을 해제한다.
     * @param fileZip
     * @param destDirPath
     * @throws IOException
     * @return void
     */
	public static void decompressZipFile(String fileZip, String destDirPath) throws IOException {
		File destDir = new File(destDirPath);
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
		ZipEntry zipEntry = zis.getNextEntry();
		while (zipEntry != null) {
		     File newFile = newFile(destDir, zipEntry);
		     if (zipEntry.isDirectory()) {
		         if (!newFile.isDirectory() && !newFile.mkdirs()) {
		             throw new IOException("Failed to create directory " + newFile);
		         }
		     } else {
		         // fix for Windows-created archives
		         File parent = newFile.getParentFile();
		         if (!parent.isDirectory() && !parent.mkdirs()) {
		             throw new IOException("Failed to create directory " + parent);
		         }
		         
		         // write file content
		         FileOutputStream fos = new FileOutputStream(newFile);
		         int len = zis.read(buffer);
		         while (len > 0) {
		             fos.write(buffer, 0, len);
                     len = zis.read(buffer);
		         }
		         fos.close();
		     }
		 zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}

	/**
	 * 
	 * 파일생성. 
	 *
	 * @Title : newFile
	 * @Description : 파일생성.
	 * @param destinationDir
	 * @param zipEntry
	 * @throws IOException
	 * @return File
	 */
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
	    File destFile = new File(destinationDir, zipEntry.getName());

	    String destDirPath = destinationDir.getCanonicalPath();
	    String destFilePath = destFile.getCanonicalPath();

	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
	        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
	    }

	    return destFile;
	}
	
    /**
     * @Title : getSkinImgFileName
     * @Description : 이미지명을 반환해준다.
     * @param fname 파일명
     * @param skin  스킨명
     * @return String
     */
    public static String getSkinImgFileName(String fname, String skin) {
        if (!fname.equals("")) {
            int lstIn = fname.lastIndexOf('.');
            String fileNm = fname;
            String ext = "";
            if (lstIn > -1) {
                fileNm = fname.substring(0, lstIn);
                ext = fname.substring(lstIn + 1);
            }
            return fileNm + "@" + skin;// + "." + ext;
        } else {
            return "";
        }
    }

    public static String readableFileSize(long size) {
        if (size <= 0)
            return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}