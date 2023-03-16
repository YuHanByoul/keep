package com.kbrainc.plum.rte.util;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.util.Base64Utils;

/**
 * 
 * 문자열유틸 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.util
 * - StringUtil.java
 * </pre> 
 *
 * @ClassName : StringUtil
 * @Description : 문자열유틸 클래스
 * @author : KBRAINC
 * @date : 2021. 3. 10.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class StringUtil {

    private static SecureRandom random = new SecureRandom();
	/**
	 * 
	 * substring
	 *
	 * @Title : substring
	 * @Description : String.substring(start, end) 대체, NullPointException 방지
	 * @param src 원본문자열
	 * @param start 시작 index
	 * @param end 끝 index
	 * @return
	 * @return String 컷팅된 문자열
	 */
    public static String substring(String src, int start, int end) {
        int len = end;
        if (src == null || "".equals(src) || start > src.length() || start > len || start < 0) {
            return "";
        }
        if (len > src.length()) {
            len = src.length();
        }
        return src.substring(start, len);
    }


    /**
     * 
     * isNull
     *
     * @Title : isNull
     * @Description : 파라미터 스트링이 null or "" 이면 true, 아니면 false
     * @param param 검사문자열
     * @return boolean
     */
    public static boolean isNull(String param) {
        if (param == null || "".equals(param)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * isNotNull
     *
     * @Title : isNotNull
     * @Description : 파라미터 스트링이 null 이 아니고, "" 이 아니면 true, 아니면 false
     * @param param 검사문자열
     * @return boolean
     */
    public static boolean isNotNull(String param) {
        if (param != null && !"".equals(param)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * asc2ksc
     *
     * @Title : asc2ksc
     * @Description : ASCII을 한글캐릭터셋으로 변환
     * @param str 원본문자열
     * @return String 변환문자열
     */
    public static String asc2ksc(String str) {
        String result = "";
        try {
            // System.out.println(new String(str.getBytes("euc-kr"),"8859_1"));
            // System.out.println(new String(str.getBytes("KSC5601"),"euc-kr"));
            // System.out.println(new String(str.getBytes("8859_1"),"euc-kr"));
            // System.out.println(new String(str.getBytes("utf-8"),"euc-kr"));
            // result = new String(str.getBytes("utf-8"),"euc-kr");
            // esult = new String(str.getBytes("UTF-8"),"KSC5601");
            result = new String(str.getBytes("8859_1"), "euc-kr");
        } catch (UnsupportedEncodingException e) {
            result = str;
        }
        return result;
    }

    /**
     * 
     * ReplaceAll
     *
     * @Title : ReplaceAll
     * @Description : 스트링 치환 함수, 주어진 문자열(buffer)에서 특정문자열('src')를 찾아 특정문자열('dst')로 치환
     * @param buffer 원본문자열
     * @param src 특정문자열
     * @param dst 대체문자열
     * @return String 변환문자열
     */
    public static String ReplaceAll(String buffer, String src, String dst) {
        if (buffer == null) {
            return null;
        }
        if (buffer.indexOf(src) < 0) {
            return buffer;
        }

        int bufLen = buffer.length();
        int srcLen = src.length();
        StringBuffer result = new StringBuffer();

        int i = 0;
        int j = 0;
        for (; i < bufLen;) {
            j = buffer.indexOf(src, j);
            if (j >= 0) {
                result.append(buffer.substring(i, j));
                result.append(dst);

                j += srcLen;
                i = j;
            } else {
                break;
            }
        }
        result.append(buffer.substring(i));
        return result.toString();
    }

    /**
     * 
     * null문자열을 ""로 대체.
     *
     * @Title : nvl
     * @Description : null인 경우 ""을 return
     * @param value 검사문자열
     * @return String 결과문자열
     */
    public static String nvl(String value) {
        return nvl(value, "");
    }

    /**
     * 
     * null문자열을 원하는 Default값으로 대체.
     *
     * @Title : nvl
     * @Description : value가 null인 경우 defalult값을 return
     * @param value 검사문자열
     * @param defaultValue 디폴트문자열
     * @return String 결과문자열
     */
    public static String nvl(String value, String defaultValue) {
        if (value == null || value.equals("")) {
            return defaultValue;
        } else {
            return value;
        }
    }

    /**
     * 
     * null문자열을 원하는 int형 Default값으로 대체.
     * 
     * @Title : nvl
     * @Description : 숫자형의 value가 null인 경우 defalult값을 return
     * @param value        검사문자열
     * @param defaultValue 디폴트숫자
     * @return int 결과숫자
     */
    public static int nvl(String value, int defaultValue) {
        if (value == null || value.equals("")) {
            return defaultValue;
        } else {
            return Integer.parseInt(value);
        }
    }

    /**
     * 
     *  Object가 null인 경우 ""을 return
     * 
     * @Title : nvl
     * @Description : null인 경우 ""을 return
     * @param value 검사오브젝트
     * @return String 결과문자열
     */
    public static String nvl(Object value) {
        return nvl(value, "");
    }

    /**
     * 
     * Object형 value가 null인 경우 defalult값을 return
     * 
     * @Title : nvl
     * @Description : value가 null인 경우 defalult값을 return
     * @param value        검사오브젝트
     * @param defaultValue 디폴트문자열
     * @return String 결과문자열
     */
    public static String nvl(Object value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            if (value.toString().equals("")) {
                return defaultValue;
            } else {
                return value.toString();
            }
        }
    }

    /**
     * 
     * Object형 value가 null인 경우 defalult값을 return
     * 
     * @Title : nvl
     * @Description : value가 null인 경우 defalult값을 return
     * @param value        검사오브젝트
     * @param defaultValue 디폴트숫자
     * @return int 결과숫자
     */
    public static int nvl(Object value, int defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            if (value.toString().equals("")) {
                return defaultValue;
            } else {
                return Integer.parseInt(value.toString());
            }
        }
    }

    /**
     * 
     * String이 Number인지를 체크 한다.
     * 
     * @Title : isNumber
     * @Description : Number 인지를 체크 한다.
     * @param paramName 검사문자열
     * @return boolean 숫자여부
     */
    public static boolean isNumber(String paramName) {
        String str = paramName;
        str = nvl(str);
        try {
            Long.parseLong(str);
        } catch (ClassCastException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 데이타를 구분자로 나누어 배열로 리턴
     * 
     * @Title : split
     * @Description : 데이타를 구분자로 나누어 배열로 리턴
     * @param str      입력문자열
     * @param sepeStr 구분자
     * @return String[] 구분자로 나누어진 문자열배열
     */
    public static String[] split(String str, String sepeStr) {
        int index = 0;
        String[] result = new String[search(str, sepeStr) + 1];
        String strCheck = str;
        while (strCheck.length() != 0) {
            int begin = strCheck.indexOf(sepeStr);
            if (begin == -1) {
                result[index] = strCheck;
                break;
            } else {
                int end = begin + sepeStr.length();
                if (true) {
                    result[index++] = strCheck.substring(0, begin);
                }
                strCheck = strCheck.substring(end);
                if (strCheck.length() == 0 && true) {
                    result[index] = strCheck;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 
     * 문자열에서 특정 문자열의 위치를 돌려준다.
     * 
     * @Title : search
     * @Description : 문자열에서 특정 문자열의 위치를 돌려준다.
     * @param strTarget 입력문자열
     * @param strSearch 찾을문자열
     * @return int 위치값
     */
    public static int search(String strTarget, String strSearch) {
        int result = 0;
        String strCheck = strTarget;
        for (int i = 0; i < strTarget.length();) {
            int loc = strCheck.indexOf(strSearch);
            if (loc == -1) {
                break;
            } else {
                result++;
                i = loc + strSearch.length();
                strCheck = strCheck.substring(i);
            }
        }
        return result;
    }

    /**
     * 
     * 문자열 실제크기를 리턴한다.
     * 
     * @Title : realLength
     * @Description : 문자열 실제크기를 리턴한다.
     * @param s 입력문자열
     * @return int 문자열의 길이
     */
    public static int realLength(String s) {
        return s.getBytes().length;
    }

    /**
     * 
     *  파일 확장자를 리턴한다.
     * 
     * @Title : getExt
     * @Description : 파일 확장자를 리턴한다.
     * @param szTemp 입력문자열
     * @return String 파일확장자
     */
    public static String getExt(String szTemp) {
        if (szTemp == null) {
            return "";
        }

        String fname = "";
        if (szTemp.indexOf(".") != -1) {
            fname = szTemp.substring(szTemp.lastIndexOf(".") + 1);
            return fname;
        } else {
            return "";
        }
    }

    /**
     * 
     * HTML태그 및 SQL인젝션 방지.
     * 
     * @Title : transTagSqlInjection
     * @Description : HTML태그 및 SQL인젝션 방지.
     * @param ret 입력문자열
     * @return String 변환문자열
     */
    public static String transTagSqlInjection(String ret) {
        if (ret == null) {
            return null;
        }

        String str = ret;

        if (str.indexOf(";") != -1) {
            str = str.replaceAll(";", "&#59;");
        }

        if (str.indexOf(">") != -1) {
            str = str.replaceAll(">", "&#62;");
        }

        if (str.indexOf("<") != -1) {
            str = str.replaceAll("<", "&#60;");
        }

        if (str.indexOf("\"") != -1) {
            str = str.replaceAll("\"", "&#34;");
        }

        if (str.indexOf("'") != -1) {
            str = str.replaceAll("'", "&#39;");
        }

        if (str.indexOf("--") != -1) {
            str = str.replaceAll("--", "&#45;&#45;");
        }

        if (str.indexOf("/*") != -1) {
            str = str.replaceAll("/*", "&#47;&#42;");
        }

        if (str.indexOf("*/") != -1) {
            str = str.replaceAll("*/", "&#42;&#47;");
        }

        return str;
    }

    /**
     * 
     * HTML태그 및 SQL인젝션 방지 복구.
     * 
     * @Title : transTagSqlInjectionRecovery
     * @Description : HTML태그 및 SQL인젝션 방지 복구.
     * @param ret 입력문자열
     * @return String 복구문자열
     */
    public static String transTagSqlInjectionRecovery(String ret) {
        if (ret == null) {
            return null;
        }

        String str = ret;

        if (str.indexOf("&#59;") != -1) {
            str = str.replaceAll("&#59;", ";");
        }

        if (str.indexOf("&#62;") != -1) {
            str = str.replaceAll("&#62;", ">");
        }

        if (str.indexOf("&#60;") != -1) {
            str = str.replaceAll("&#60;", "<");
        }

        if (str.indexOf("&#34;") != -1) {
            str = str.replaceAll("&#34;", "\"");
        }

        if (str.indexOf("&#39;") != -1) {
            str = str.replaceAll("&#39;", "'");
        }

        if (str.indexOf("&#45;&#45;") != -1) {
            str = str.replaceAll("&#45;&#45;", "--");
        }

        if (str.indexOf("&#47;&#42;") != -1) {
            str = str.replaceAll("&#47;&#42;", "/*");
        }

        if (str.indexOf("&#42;&#47;") != -1) {
            str = str.replaceAll("&#42;&#47;", "*/");
        }

        return str;
    }

    /**
     * 
     * 문자열의 왼쪽에 대체문자를 해당 index의 갯수만큼 채워 리턴한다.
     * 
     * @Title : fillLeft
     * @Description : 문자열의 왼쪽에 대체문자를 해당 index의 갯수만큼 채워 리턴한다.
     * @param str    변경할 문자열
     * @param index  반복횟수
     * @param addStr 반복될문자열
     * @return String 결과문자열
     */
    public static String fillLeft(String str, int index, String addStr) {
        String retStr = str;
        int gap = 0;
        if ((retStr != null) && (addStr != null) && (retStr.length() <= index)) {
            gap = index - retStr.length();

            for (int i = 0; i < gap; i++) {
                retStr = addStr + retStr;
            }
            return retStr;
        } else {
            return "";
        }
    }

    /**
     * 
     * HashMap을 URL 스트링으로 변환한다.
     * 
     * @Title : mapToURLString
     * @Description : HashMap을 URL 스트링으로 변환한다.
     * @param map 입력Map
     * @return String 키밸류형식의 url문자열
     */
    public static String mapToURLString(Map map) {
        if (map == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        Set keys = map.keySet();
        Iterator iter = keys.iterator();
        String key = null;
        String value = null;
        String[] values = null;
        Object obj = null;
        while (iter.hasNext()) {
            key = (String) iter.next();
            if (!isNumber(key)) {
                obj = map.get(key);
                if (obj instanceof String) {
                    value = (String) map.get(key);
                    if (buf.length() == 0) {
                        buf.append(key).append("=").append(value);
                    } else {
                        buf.append("&").append(key).append("=").append(value);
                    }
                } else if (obj instanceof String[]) {
                    values = (String[]) map.get(key);
                    for (String str : values) {
                        if (buf.length() == 0) {
                            buf.append(key).append("=").append(str);
                        } else {
                            buf.append("&").append(key).append("=").append(str);
                        }
                    }
                }
            }
        }
        return buf.toString();
    }

    /**
     * 
     * 문자열 앞뒤 공백을 없앤다.
     * 
     * @Title : trim
     * @Description : 문자열 앞뒤 공백을 없앤다.
     * @param str 입력문자열
     * @return String 결과문자열
     */
    public static String trim(String str) {
        return trim(str, null);
    }

    /**
     * 
     * 문자열 앞뒤공백을 없앰, 만약 null이면 default를 돌려준다.
     * 
     * @Title : trim
     * @Description : 문자열 앞뒤공백을 없앰, 만약 null이면 default를 돌려준다.
     * @param str 입력문자열
     * @param def 디폴트문자열
     * @return String 결과문자열
     */
    public static String trim(String str, String def) {
        return (null == str ? def : str.trim());
    }

    /**
     * 
     * lPad에 의해 실행되는 메소드
     * 
     * @Title : strPad
     * @Description : lPad에 의해 실행되는 메소드
     * @param str    입력문자열
     * @param size   사이즈
     * @param padStr 채울 문자열
     * @param where  true : left, false : right
     * @return String 결과문자열
     */
    private static String strPad(String str, int size, String padStr, boolean where) {
        if (str == null) {
            return "";
        }
        if (str.length() >= size) {
            return str;
        }
        String res = null;
        StringBuffer sb = new StringBuffer();
        String tmpStr = null;
        int tmpSize = size - str.length();

        for (int i = 0; i < size; i = i + padStr.length()) {
            sb.append(padStr);
        }
        tmpStr = sb.toString().substring(0, tmpSize);

        if (where) {
            res = tmpStr.concat(str);
        } else {
            res = str.concat(tmpStr);
        }
        return res;
    }

    /**
     * 
     * 문자열의 왼쪽에 해당사이즈 만큼 문자를 채운다.
     * 
     * @Title : lPad
     * @Description : 문자열의 왼쪽에 해당사이즈 만큼 문자를 채운다.
     * @param str     입력문자열
     * @param size    사이즈
     * @param padChar 채울 문자열
     * @return String 결과문자열
     */
    public static String lPad(String str, int size, char padChar) {
        return lPad(str, size, String.valueOf(padChar));
    }

    /**
     * 
     * 문자열의 왼쪽에 해당사이즈 만큼 문자열을 채운다.
     * 
     * @Title : lPad
     * @Description : 문자열의 왼쪽에 해당사이즈 만큼 문자열을 채운다.
     * @param str    입력문자열
     * @param size   사이즈
     * @param padStr 채울 문자열
     * @return String 결과문자열
     */
    public static String lPad(String str, int size, String padStr) {
        return strPad(str, size, padStr, true);
    }
    
    /**
     * 
     * 임시 비밀번호 생성.
     *
     * @Title : temporaryPassword
     * @Description : 임시 비밀번호 생성.
     * @param size 크기
     * @return String 생성된 비밀번호
     */
    public static String temporaryPassword(int size) {
        StringBuffer buffer = new StringBuffer();
        String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9,!,@,#,$,%,^,*,(,)".split(",");

        for (int i = 0; i < size; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }

        return buffer.toString();
    }
    
    
    /**
    * SqlInjection 해당여부 확인.
    *
    * @Title : isSqlInjectionSafe
    * @Description : 입력문자열이 SqlInjection에 안전한지 확인한다.
    * @param dataString 입력문자열
    * @return boolean sqlInjection 안전 여부
    */
    public static boolean isSqlInjectionSafe(String dataString){
        String sqlTypes =
                "TABLE, TABLESPACE, PROCEDURE, FUNCTION, TRIGGER, KEY, VIEW, MATERIALIZED VIEW, LIBRARY" +
                "DATABASE LINK, DBLINK, INDEX, CONSTRAINT, TRIGGER, USER, SCHEMA, DATABASE, PLUGGABLE DATABASE, BUCKET, " +
                "CLUSTER, COMMENT, SYNONYM, TYPE, JAVA, SESSION, ROLE, PACKAGE, PACKAGE BODY, OPERATOR" +
                "SEQUENCE, RESTORE POINT, PFILE, CLASS, CURSOR, OBJECT, RULE, USER, DATASET, DATASTORE, " +
                "COLUMN, FIELD, OPERATOR";
        
        String[] sqlRegexps = {
                "(?i)(.*)(\\b)+SELECT(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+INSERT(\\b)+\\s.*(\\b)+INTO(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+UPDATE(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+DELETE(\\b)+\\s.*(\\b)+FROM(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+UPSERT(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+SAVEPOINT(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+CALL(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+ROLLBACK(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+KILL(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+DROP(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+CREATE(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+ALTER(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+TRUNCATE(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+LOCK(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+UNLOCK(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+RELEASE(\\b)+(\\s)*(" + sqlTypes.replaceAll(",", "|") + ")(\\b)+\\s.*(.*)",
                "(?i)(.*)(\\b)+DESC(\\b)+(\\w)*\\s.*(.*)",
                "(?i)(.*)(\\b)+DESCRIBE(\\b)+(\\w)*\\s.*(.*)",
                "(.*)(/\\*|\\*/|;){1,}(.*)",
                "(.*)(-){2,}(.*)"
        };
        
        if(dataString == null || dataString.length() == 0){
            return true;
        }

        List<Pattern> patterns = new ArrayList<Pattern>();
        for(String sqlExpression : sqlRegexps){
            patterns.add(Pattern.compile(sqlExpression, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
        }
        
        for(Pattern pattern : patterns){
            if(pattern.matcher(dataString).matches()){
                return false;
            }
        }
        return true;
    }
    
    /**
    * 쿼리트링으로 넘어오는 base64Encoding 파라미터값을 decoding한다.
    *
    * @Title : base64DecodeFromUrlSafeString
    * @Description : 쿼리트링으로 넘어오는 base64Encoding 파라미터값을 decoding한다
    * @param encodedStr base64인코딩 문자열
    * @return String base64디코딩 문자열
    */
    public static String base64DecodeFromUrlSafeString(String encodedStr) {
        return new String(Base64Utils.decodeFromUrlSafeString(encodedStr));
    }
    
    /**
    * queyrString에서 pwd파라미터를 제거한 url을 반환.
    *
    * @Title : removePwdParameterURL
    * @Description : queyrString에서 pwd파라미터를 제거한 url을 반환
    * @param url 요청url
    * @param queryString 쿼리스트링
    * @return String pwd파라미터가 제거된 쿼리스트링 포함 URL
    */
    public static String removePwdParameterURL(String url, String queryString) {
        if (queryString == null) {
            return url;
        }
        
        if ("pwd".equals(queryString) || "&pwd".equals(queryString) || "pwd&".equals(queryString) || "&pwd&".equals(queryString)) {
            return url;
        }
        
        if (queryString.startsWith("pwd&")) {
            return String.format("%s?%s", url, queryString.substring(4, queryString.length()));
        }
        
        if (queryString.startsWith("&pwd&")) {
            return String.format("%s?%s", url, queryString.substring(5, queryString.length()));
        }
        
        if (queryString.endsWith("&pwd")) {
            return String.format("%s?%s", url, queryString.substring(0, queryString.length() - 4));
        }
        
        if (queryString.endsWith("&pwd&")) {
            return String.format("%s?%s", url, queryString.substring(0, queryString.length() - 5));
        }
        
        return String.format("%s?%s", url, queryString.replaceFirst("&pwd&", "&"));
    }
    
    /**
    * 이름을 마스킹 처리한후 돌려준다.
    *
    * @Title : maskingName
    * @Description : 이름을 마스킹 처리한후 돌려준다.
    * @param name 이름
    * @return String 마스킹 이름
    */
    public static String maskingName(String name) {
        if (name == null) {
            return null;
        }
        
        if ("휴면회원".equals(name)) {
            return name;
        }
        
        String retName = name;
        switch (name.length()) {
            case 2:
                retName = String.format("%s*", name.substring(0, 1));
                break;
            case 3:
                retName = String.format("%s*%s", name.substring(0, 1), name.substring(2, 3));
                break;
            case 4:
                retName = String.format("%s**%s", name.substring(0, 1), name.substring(3, 4));
                break;
        }   
        
        if (name.length() > 4) {
            retName = String.format("%s**%s", name.substring(0, 1), name.substring(3, name.length()));
        }
        
        return retName;
    }
    
    /**
    * 계정을 마스킹 처리한후 돌려준다.
    *
    * @Title : maskingAccount
    * @Description : 계정을 마스킹 처리한후 돌려준다.
    * @param account 계정
    * @return String 마스킹 계정
    */
    public static String maskingAccount(String account) {
        
        String retAccount = account;
        if (account == null) {
            retAccount = UUID.randomUUID().toString().substring(0, 10);
        }
        
        if (retAccount.length() > 3) {
            return String.format("%s***", retAccount.substring(0, retAccount.length() - 3));
        }
        
        return retAccount;
    }
    
    /**
    * 휴대폰번호를 마스킹 처리한후 돌려준다.
    *
    * @Title : maskingMobilePhone
    * @Description : 휴대폰번호를 마스킹 처리한후 돌려준다.
    * @param account 휴대폰번호
    * @return String 마스킹 휴대폰번호
    */
    public static String maskingMobilePhone(String mobilePhone) {
        if (mobilePhone == null) {
            return null;
        }
        
        if (mobilePhone.length() == 11) {
            return String.format("%s****%s", mobilePhone.substring(0, 3), mobilePhone.substring(7, 11));
        } else if(mobilePhone.length() == 13 && mobilePhone.substring(3, 4).equals("-") && mobilePhone.substring(8, 9).equals("-")) {
            return String.format("%s-****-%s", mobilePhone.substring(0, 3), mobilePhone.substring(9, 13));
        }
        
        return mobilePhone;
    }
}