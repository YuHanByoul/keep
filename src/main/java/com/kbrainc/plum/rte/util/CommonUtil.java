package com.kbrainc.plum.rte.util;

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;
import com.kbrainc.plum.rte.crypto.CryptoAES256;
import com.kbrainc.plum.rte.idgnr.UUIdGnr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * 공통Util 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.util
 * - CommonUtil.java
 * </pre> 
 *
 * @ClassName : CommonUtil
 * @Description : 공통Util 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class CommonUtil {

    private static Configuration applicationConfig = ConfigurationFactory.getInstance().getApplicationConfig();

    private static String[] wasIpport = applicationConfig.getStringArray("admin.was.ipport");

    /**
     * @Title : getBean
     * @Description : 특정 bean을 가져오는 함수
     * @param beanName bean객체 이름
     * @param request  요청객체
     * @return Object bean객체
     */
    public static Object getBean(String beanName, HttpServletRequest request) {
        WebApplicationContext ctx = WebApplicationContextUtils
                .getWebApplicationContext(request.getSession().getServletContext());
        return ctx.getBean(beanName);
    }

    /**
    * 특정 bean을 가져오는 함수.
    *
    * @Title       : getBean 
    * @Description : 특정 bean을 가져오는 함수
    * @param beanName bean객체 이름
    * @return Object bean객체
    */
    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
    
    /**
     * crypto bean을 가져오는 함수.
     *
     * @Title       : CryptoBean 
     * @Description : crypto bean을 가져오는 함수
     * @param beanName bean객체 이름
     * @return Object bean객체
     */
     public static CryptoAES256 getCryptoBean() {
         ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
         return (CryptoAES256)applicationContext.getBean("cryptoAES256");
     }

    /**
     * @Title : getSessionValue
     * @Description : 세션에 저장된 변수를 가지고 온다.
     * @param key     키값
     * @param request 요청객체
     * @return String 지정한 key에 해당하는 세션속성 값
     */
    public static String getSessionValue(String key, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(key);
    }

    /**
     * @Title : setSessionValue
     * @Description : 세션에 변수를 저장해 준다.
     * @param key     키값
     * @param value   속성값
     * @param request 요청객체
     * @return void
     */
    public static void setSessionValue(String key, String value, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * @Title : allWasUrlCaller
     * @Description : global.properties에 등록된 모든 was에 대하여 동일 Url을 호출한다.
     * @param callUrl 호출할url
     * @throws Exception
     * @return String 미실행 was ip목록
     */
    public static String allWasUrlCaller(String callUrl) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        URL url = null;
        URLConnection urlcnn = null;
        
        String[] checkHosts = CommonUtil.wasIpport;
        int checkHostsLength = checkHosts.length;
        String resultCheckHost = "";

        int idx = 0;

        try {

            for (int i = 0; i < checkHosts.length; i++, idx = i) {
                if (checkHosts[i].startsWith("https://")) {
                    BufferedReader br = null;
                    StringBuilder sb = new StringBuilder();
                    url = new URL(checkHosts[i] + callUrl);
                    
                    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                            checkClientTrusted
                        }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                            checkServerTrusted
                        }
                    }};
    
                    SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, trustAllCerts, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                    HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
                    InputStream is = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        line = br.readLine();
                    }
                } else {
                    url = new URL(checkHosts[i] + callUrl);
                    urlcnn = url.openConnection();
                    urlcnn.setConnectTimeout(1000);
                    urlcnn.getContent();
                }
            }
        } catch (Exception e) {
            for (int i = idx; i < checkHostsLength; i++) {
                resultCheckHost += checkHosts[i];
                if (i < checkHostsLength - 1) {
                    resultCheckHost += ",";
                }
            }
        }

        return resultCheckHost;
    }

    /**
     * @Title : getCurrentRequest
     * @Description : 현재 요청에 대한 요청객체를 가져온다.
     * @return HttpServletRequest 요청객체
     */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes sreq = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest req = sreq.getRequest();
        return req;
    }

    /**
     * @Title : getClientIp
     * @Description : 실제 클라이언트 IP 가져오기.
     * @param request 요청객체
     * @return String 클라이언트의ip
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        ip = StringUtil.nvl(ip).trim();
        if (ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(","));
        }

        return ip;
    }

    /**
     * 객체의 null 및 공백에 대해 체크한다. 주로 mybatis에서 사용할 예정.
     */
    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return obj == null || "".equals(obj.toString().trim());
        } else if (obj instanceof List) {
            return obj == null || ((List) obj).isEmpty();
        } else if (obj instanceof Map) {
            return obj == null || ((Map) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return obj == null || Array.getLength(obj) == 0;
        } else {
            return obj == null;
        }
    }

    /**
     * 객체의 null 및 공백이 아닌지 체크한다. 주로 mybatis에서 사용할 예정.
     */
    public static boolean isNotEmpty(Object s) {
        return !isEmpty(s);
    }
    
    /**
    * UUID를 생성을 위한 bean을 가져오는 함수.
    *
    * @Title       : getUUIdGnrBean 
    * @Description : UUID를 생성을 위한 bean을 가져오는 함수.
    * @return UUIdGnr UUIdGnr객체
    */
    public static UUIdGnr getUUIdGnrBean() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return (UUIdGnr)applicationContext.getBean("uuidGnr");
    }
    
    /**
     * 브라우저명을 리턴. 
     *
     * @Title : getBrowser
     * @Description : 요청헤더를 통해 브라우저 식별하여 alias리턴
     * @param request 요청객체
     * @return String 브라우저명
     */
     public static String getBrowser(HttpServletRequest request) { 
         String header = request.getHeader("User-Agent"); 
     
         if (header.indexOf("MSIE") > -1) { 
             return "MSIE"; 
         } else if (header.indexOf("Chrome") > -1) { 
             return "Chrome"; 
         } else if (header.indexOf("Opera") > -1) { 
             return "Opera"; 
         } else if (header.indexOf("Trident/7.0") > -1) { //IE 11 이상 
             //IE 버전 별 체크 >> Trident/6.0(IE 10) , Trident/5.0(IE 9) , Trident/4.0(IE 8) 
             return "MSIE"; 
         } 
         
         return "Firefox"; 
     }
     
     /**
     * 브라우저별 파일명 인코딩. 
     *
     * @Title : getDisposition
     * @Description : 브라우저별 파일명 인코딩.
     * @param filename 파일명
     * @param browser 브라우저명
     * @return String 인코딩된 파일명 
     * @throws Exception 예외
     */
     public static String getDisposition(String filename, String browser) throws Exception { 
         String encodedFilename = null; 
         if (browser.equals("MSIE")) { 
             encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20"); 
         } else if (browser.equals("Firefox")) { 
             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\""; 
         } else if (browser.equals("Opera")) { 
             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\""; 
         } else if (browser.equals("Chrome")) { 
             StringBuffer sb = new StringBuffer(); 
             for (int i = 0; i < filename.length(); i++) { 
                 char c = filename.charAt(i); 
                 if (c > '~') { 
                     sb.append(URLEncoder.encode("" + c, "UTF-8")); 
                 } else { 
                     sb.append(c); 
                 } 
             } 
             encodedFilename = sb.toString(); 
         } else { 
             throw new RuntimeException("Not supported browser"); 
         } 
         return encodedFilename; 
     }
}