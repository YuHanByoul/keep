/**
 * 
 */
package com.kbrainc.plum.rte.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.StringUtils;

/**
 * @author Administrator
 *
 */
public class FormChecker {
    
	public static boolean isNull(String m) {
	    if(m == null || m == "" || !StringUtils.hasText(m))return true;
	    else return false;
	}

	public static boolean isDate(String date) {
		boolean result =false ;
        if( date == null )  return  false ;
        else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMdd", Locale.getDefault() ) ;
                formatter.setLenient(false); 
                formatter.parse ( date ) ;
                result = true ;
            } catch(Exception e) {
            	 result = false ;
            }finally {
            	return result ;
            }
        }
         
	}

	public static boolean isBirth(String date) {
		boolean result = false;
		if (date == null) return false;
		else {
			String mat = "\\d{4}-\\d{2}-\\d{2}";
			boolean val = date.matches(mat);
			if (val) {
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
					formatter.setLenient(false);
					formatter.parse(date);
					result = true;
				} catch (Exception e) {
					result = false;
				} finally {
					return result;
				}
			}else{
				return result;
			}

		}
	}

	
	public static boolean isNumber(String m) {
		if(isNull(m))
			return false;

		String tm = m;
		tm = tm.trim();
		int n = tm.length();
		for(int i=0; i < n; i++) {
			char c = tm.charAt(i);
			if(!('0' <=  c && c <= '9'))
				return false;
		}
		return true;
	}
	
	public static boolean isSame(String m, String n) {
		if(isNull(m) || isNull(n))
			return false;
		String tm = m;
		String tn = n;
		tm = tn.trim();
		tn = tn.trim();
		if(tm.equals(tn))
			return true;
		
		return false;
	}
	
	public static boolean isID(String m) {
		if(isNull(m))
			return false;

		String tm = m;
		tm = tm.trim().toUpperCase(new Locale(tm));
		char c = tm.charAt(0);
		if(!('A' <= c && c <= 'Z'))
			return false;
		
		int n = tm.length();
		for(int i=1; i < n; i++) {
			c = tm.charAt(i);
			if(!(('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') ||
			     (c == '_')))
				return false;
		}
		return true;
	}
	
	public static boolean isCardNumber(String m) {
		if(isNull(m))
			return false;
		String tm = m;
		tm = tm.trim();
		if(tm.length() != 16)
			return false;
		if(isNumber(tm))
			return true;
			
		return false;
	}
	
	public static boolean isEmail(String m) {
		if(isNull(m))
			return false;

		int n = m.indexOf("@");
		if(n < 0)
			return false;
		n = m.indexOf(".");
		if(n < 0)
			return false;
			
		return true;
	}
	
	public static boolean isSSN(String jumin) {
		String s1 = null, s2 = null;
		if(jumin != null && jumin.length() >= 13){
			s1 = jumin.substring(0,6);
			s2 = jumin.substring(6,13);
		}else return false;
		
		if(!isNumber(s1))	
			return false;

		if(!isNumber(s2))
			return false;
		
		s1 = s1.trim();
		s2 = s2.trim();
		
		if(!(s1.length() == 6 && s2.length() == 7))
			return false;
			
		int hap = 0;
		for(int i=0; i < 6; i++) {
			hap +=  (s1.charAt(i) - '0') * (i+2) ;	
		}
		
		int n0 = s2.charAt(0) - '0';
		int n1 = s2.charAt(1) - '0';
		int n2 = s2.charAt(2) - '0';
		int n3 = s2.charAt(3) - '0';
		int n4 = s2.charAt(4) - '0';
		int n5 = s2.charAt(5) - '0';
		int n6 = s2.charAt(6) - '0';
		
		hap += n0*8+n1*9+n2*2+n3*3+n4*4+n5*5;
		hap %= 11;
		hap = 11 - hap;
		hap %= 10;
		if(hap != n6)
			return false;
		
		return true;
	}
	
	/**
	 * check juminNo
	 * @param jumin
	 * @return
	 */
	public static boolean isJumin( String jumin ) { 
		boolean isKorean = true; 
		int check = 0;
	 
		if( jumin == null || jumin.length() != 13 ) {
			return false; 
		}
		if( Character.getNumericValue( jumin.charAt( 6 ) ) > 4 && Character.getNumericValue( jumin.charAt( 6 ) ) < 9 ) { 
			isKorean = false; 
		}

		for( int i = 0 ; i < 12 ; i++ ) { 
			if( isKorean ) {
				check += ( ( i % 8 + 2 ) * Character.getNumericValue( jumin.charAt( i ) ) ); 
			}else {
				check += ( ( 9 - i % 8 ) * Character.getNumericValue( jumin.charAt( i ) ) ); 
			}
		}
		
		if( isKorean ) { 
			check = 11 - ( check % 11 ); 
			check %= 10; 
		}else { 
			int remainder = check % 11; 
			if ( remainder == 0 ) {
				check = 1; 
			}else if ( remainder==10 ) {
				check = 0; 
			}else {
				check = remainder;
			}
			int check2 = check + 2; 
			if ( check2 > 9 ) {
				check = check2 - 10; 
			}else {
				check = check2; 
			}
		}
		
		if( check == Character.getNumericValue( jumin.charAt( 12 ) ) ) {
			return true; 
		}else {
			return false;
		}
	} 

	public static boolean isStringDouble(String s){
		try{
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	public static boolean isPhone(String num){
		String tnum = num;
		tnum=tnum.replaceAll("-", "");
		//String mat = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
		String mat = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
		boolean val = tnum.matches(mat);

		return val;
	}

	public static boolean isTel(String num){
		String mat ="^\\d{2,3}-\\d{3,4}-\\d{4}$";
		boolean val = num.matches(mat);

		return val;
	}

	/**
	 * 
	 * @param dispName : 객체명
	 * @param nullYn : 필수 여부(Y,N)
	 * @param len : 최대 길이 
	 * @param dataType : S (String, default), N (number), E (email), J (jumin), P(phone), T(tel)
	 * @return
	 */
	public static String Validate(String data, String dispName, String nullYn, int len, String dataType) {
		if ("N".equals(nullYn)) {
			if (isNull(data)) {
				return dispName + "은(는) 필수 입력값 입니다.";
			}
		}
		String tmpData = data;
		tmpData = StringUtil.nvl(tmpData);

		if (!isNull(tmpData) && len > 0) {
			if (StringUtil.realLength(tmpData) > len) {
				return dispName + "의 길이는 " + len + " 보다 큽니다.";
			}
		}

		String tmpDataType = dataType;
		tmpDataType = StringUtil.nvl(tmpDataType,"S");
		
		if ("N".equals(nullYn) && "N".equals(tmpDataType)) {
			if (!NumberUtils.isNumber(tmpData)) {
				return dispName + "는 숫자값이어야 합니다.";
			}
		}
		
		if ("N".equals(nullYn) && "N2".equals(tmpDataType)) {
			if (!NumberUtils.isNumber(tmpData) && !tmpData.matches("[0-9]*")) {
				return dispName + "는 숫자만 허용 합니다.";
			}
		}
		
		if ("YN".equals(tmpDataType)) {
			if (!"".equals(tmpData) && !"Y".equals(tmpData) && !"N".equals(tmpData)) {
				return dispName + "는 Y 혹은 N 만 허용 합니다.";
			}
		}

		if ("E".equals(tmpDataType)) {
			if (!isEmail(tmpData)) {
				return dispName + "는 형식에 맞지 않습니다.";
			}
		}
		
		if ("J".equals(tmpDataType)) {
			if (!isJumin(tmpData)) {
				return dispName + "는 형식에 맞지 않습니다.";
			}
		}

		if ("Date".equals(tmpDataType)) {
			if (!isDate(tmpData)) {
				return dispName + "는 잘못된 날짜 입니다.";
			}
		}

		if ("Birth".equals(tmpDataType)) {
			if (!isBirth(tmpData)) {
				return dispName + "는 잘못된 날짜 입니다.";
			}
		}

		if ("P".equals(tmpDataType)) { //휴대전화 (01*-****-***)
			if (!isPhone(tmpData)) {
				return dispName + "는 잘못된 전화번호 형식입니다.";
			}
		}

		if (!isNull(tmpData) && "T".equals(tmpDataType)) { //일반전화(0*-****-****)
			if (!isTel(tmpData)) {
				return dispName + "는 잘못된 전화번호 형식입니다.";
			}
		}

		if (!isNull(tmpData)) {
			if("N".equals(tmpDataType)) {
				if(!NumberUtils.isNumber(tmpData)) {
					return dispName+"는 숫자값이어야 합니다.";
				}
			} else if ("N2".equals(tmpDataType)) {
				if(!NumberUtils.isNumber(tmpData) && !tmpData.matches("[0-9]*")) {
					return dispName+"는 숫자만 허용 합니다.";
				}
			} else if("D".equals(tmpDataType)) {
				if(!isStringDouble(tmpData)) {
					return  dispName+"는 실수만 허용 합니다.";
				}
			}
		}
		
		if (tmpDataType.equals("KNOWSUBJTYPE")) {
			if (!"".equals(tmpData) && !"N".equals(tmpData) && !"J".equals(tmpData)) {
				return dispName + "은 J(참여학습)혹은 N(필요학습)만 허용합니다.";
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @param column : 컬럼명
	 * @return
	 */
	public static String ValidateData(String data, String dispName, List ds, String column){
		boolean vali = false;

		try {
			if(ds!=null && ds.size()>0){
				for(int x=0; x<ds.size(); x++){
					HashMap map = (HashMap)ds.get(x);
					String str = (String)map.get(column);
					
					if(data.equals(str)) vali = true;
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(vali){
			return "";
		}else{
			return dispName+"에 등록되지않은 값입니다.";
		}
		
	}
}
