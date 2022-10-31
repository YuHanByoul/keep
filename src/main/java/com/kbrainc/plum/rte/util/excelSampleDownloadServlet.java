
package com.kbrainc.plum.rte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;

/**
 * 파일 다운로드
 * 
 */
@WebServlet(name = "excelSampleDownloadServlet", urlPatterns = "/excelSampleDownload")
@Slf4j
public class excelSampleDownloadServlet extends HttpServlet {
	
	//public LocaleAccessor localeAccessor = LocaleAccessor.getInstance();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rFileName = StringUtil.nvl(request.getParameter("rFileName"));
		String sFileName = StringUtil.nvl(request.getParameter("sFileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String fileGubun = StringUtil.nvl(request.getParameter("FILEGU"));
		String lmsdataPath = FileUtil.UPLOAD_PATH;

		if (rFileName.indexOf("../") > -1 || sFileName.indexOf("../") > -1 || filePath.indexOf("../") > -1 || rFileName.contains("\r\n")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			out.println("<html><body>");
			out.println("<script language='javascript'>");

			// 보안에 위반되는 경로를 포함하고 있어 다운로드 진행을 중지하였습니다.
			out.println("alert('보안에 위반되는 경로를 포함하고 있어 다운로드 진행을 중지하였습니다.');");
			out.println("</script>");
			out.println("</body></html>");
			out.close();
				
		} else {
			try {
				if (fileGubun.equals("MAIL")) {
	            	rFileName = new String(rFileName.getBytes("UTF-8"), "ISO-8859-1");
			    }
				filePath = filePath.replaceAll("/", "");
				ClassPathResource resource = new ClassPathResource(filePath + "/" + sFileName);
//				File file = new File(lmsdataPath+filePath+"/"+sFileName);
				File file = resource.getFile();
				FileInputStream in = null;
				
				try {
					in = new FileInputStream(file);
				} catch ( IOException e ) {
					log.error("doGet.IOException.74L");
				} catch ( Exception e ) {
                    log.error("doGet.Exception.74L");
                }
				
				//response.setCharacterEncoding("UTF-8");
				response.setContentType( "application/x-msdownload" );
				//response.setHeader( "Content-Disposition", "attachment; filename=\""+ new String(rFileName.getBytes("UTF-8"),"ISO-8859-1") + "\"" );
				setDisposition(rFileName, request, response);
				//response.setHeader( "Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(rFileName, "UTF-8") + "\"" );
	//			response.setHeader( "Content-Disposition", "attachment; filename=\""+ rFileName + "\"" );
				response.setHeader( "Content-Transfer-Coding", "binary" );
	
				ServletOutputStream binaryOut = response.getOutputStream();
				byte b[] = new byte[1024];
	
				try {
					int nRead = in.read(b);
					while( nRead > -1){
						binaryOut.write( b, 0, nRead );
						nRead = in.read(b);
					}
					
					binaryOut.flush();
				} catch (IOException e) {
					log.error("doGet.IOException.97L");
				} catch (Exception e) {
                    log.error("doGet.Exception.97L");
                } finally {
					if (in != null) {
						in.close();
					}
					if (binaryOut != null) {
						binaryOut.close();
					}
				}
	
			} catch(IOException e) {
				log.error("doGet.IOException.108L");
			} catch(Exception e) {
                log.error("doGet.Exception.108L");
            }
		}
	}
	
	@SuppressFBWarnings(value = "HTTP_RESPONSE_SPLITTING", justification = "외부입력값(rFileName)에서 개행문자(\\r\\n)를 제거하였음")
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);
		
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) {		// IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			sb.append("\"");
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			sb.append("\"");
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}
		
		// 보안약점 HTTP_RESPONSE_SPLITTING 패턴 대응처리
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename.replaceAll("[\\r\\n]", ""));

		if ("Opera".equals(browser)){
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }
	
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {	// IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

}




