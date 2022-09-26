package com.kbrainc.plum.rte.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * DateTime유틸 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.util
 * - DateTimeUtil.java
 * </pre> 
 *
 * @ClassName : DateTimeUtil
 * @Description : DateTime유틸 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class DateTimeUtil {

    /** 날짜구분자 */
    private static final String DATE_GUBUN = ".";
    /** 시간구분자 */
    private static final String TIME_GUBUN = ":";
    /** DateTimeUtil객체의 인스턴스 */
    private static DateTimeUtil dateTimeUtilIns = null;

    /**
     * @Title : getInstance
     * @Description : DateTimeUtil객체의 인스턴스를 가져온다.
     * @return DateTimeUtil DateTimeUtil객체의 인스턴스
     */
    public static DateTimeUtil getInstance() {
        if (dateTimeUtilIns == null) {
            dateTimeUtilIns = new DateTimeUtil();
        }
        return dateTimeUtilIns;
    }

    /**
     * @Title : getTimeText
     * @Description : 현재 시간을 돌려준다.
     * @param type   시간문자열 포맷선택값
     * @param szTime 시간문자열
     * @return String 현재시간문자열
     */
    public static String getTimeText(int type, String szTime) {
        if (szTime != null && szTime.length() != 6) {
            return "";
        }

        if (szTime != null && szTime.length() == 6) {
            String hour = StringUtil.substring(szTime, 0, 2);
            String minute = StringUtil.substring(szTime, 2, 4);
            String second = StringUtil.substring(szTime, 4, 6);

            switch (type) {
            case 1:
                return hour + TIME_GUBUN + minute + TIME_GUBUN + second;
            case 2:
                return hour + TIME_GUBUN + minute;
            case 3:
                return hour;
            default:
            }
        }

        return "";
    }

    /**
     * @Title : getTime
     * @Description : 현재 시간 문자열(HHMMSS)을 반환한다.
     * @return String 현재시간 문자열(HHMMSS)
     */
    public static String getTime() {
        String hour, min, sec;

        Calendar cal = Calendar.getInstance(Locale.getDefault());

        StringBuffer buf = new StringBuffer();

        hour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        min = Integer.toString(cal.get(Calendar.MINUTE));
        if (min.length() == 1) {
            min = "0" + min;
        }

        sec = Integer.toString(cal.get(Calendar.SECOND));
        if (sec.length() == 1) {
            sec = "0" + sec;
        }

        buf.append(hour);
        buf.append(min);
        buf.append(sec);

        return buf.toString();
    }

    /**
     * @Title : getHour
     * @Description : 현재 시간(HH)을 돌려준다.
     * @return String 현재 시간(HH)
     */
    public static String getHour() {
        String hour;

        Calendar cal = Calendar.getInstance(Locale.getDefault());

        hour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        return hour;
    }

    /**
     * @Title : getDateText
     * @Description : 날짜 문자열을 지정된 타입으로 포맷팅해서 돌려준다. TYPE 1 : YYYY.MM.DD TYPE 2 :
     *              YY.MM.DD TYPE 3 : MM.DD TYPE 4 : YYYY.MM TYPE 5 : YYYY
     * @param type   타입
     * @param szdate 날짜문자열
     * @return String 지정된 타입으로 포맷팅된 날짜 문자열
     */
    public static String getDateText(int type, String szdate) {
        return getDateText(type, szdate, DATE_GUBUN);
    }

    /**
     * @Title : getDateText
     * @Description : 날짜 문자열을 지정된 타입으로 포맷팅해서 돌려준다.
     * @param type      타입
     * @param szdate    날짜문자열
     * @param delimeter 구분자
     * @return String 지정된 타입으로 포맷팅된 날짜 문자열
     */
    public static String getDateText(int type, String szdate, String delimeter) {
        String reDate = "";

        if (szdate != null && szdate.length() != 8) {
            return "";
        }

        if (szdate != null && szdate.length() == 8) {
            String year = szdate.substring(0, 4);
            String month = szdate.substring(4, 6);
            String day = szdate.substring(6, 8);

            switch (type) {
            case 1:
                return year + delimeter + month + delimeter + day;
            case 2:
                return year.substring(2, 4) + delimeter + month + delimeter + day;
            case 3:
                return month + delimeter + day;
            case 4:
                return year + delimeter + month;
            case 5:
                return year;
            default:
            }
        }

        return "";
    }

    /**
     * @Title : getDateType
     * @Description : 특정형태의 날자 타입을 돌려준다. TYPE 0 : YYYY.MM.DD HH:MI:SS TYPE 1 :
     *              YYYY.MM.DD TYPE 2 : YY.MM.DD TYPE 3 : MM.DD TYPE 4 : YYYY.MM
     *              TYPE 5 : YYYY TYPE 6 : MM.DD HH:MI TYPE 7 : HH:MI
     * @param type 타입
     * @param date 날짜문자열
     * @return String 지정된 타입으로 포맷팅된 날짜 문자열
     */
    public static String getDateType(int type, String date) {
        return getDateType(type, date, DATE_GUBUN);
    }

    /**
     * @Title : getDateType
     * @Description : 특정형태의 날자 타입을 돌려준다.
     * @param type      타입
     * @param date      날짜문자열
     * @param delimeter 구분자
     * @return String 지정된 타입으로 포맷팅된 날짜 문자열
     */
    public static String getDateType(int type, String date, String delimeter) {
        String str = date;
        if (str == null) {
            return "";
        }

        if (str.length() == 12) {
            str += "01";
        } else if (str.length() == 10) {
            str += "0101";
        } else if (str.length() == 8) {
            str += "010101";
        } else if (str.length() == 6) {
            str += "01010101";
        } else if (str.length() == 4) {
            str += "0101010101";
        }

        switch (type) {
        case 0:
            return getDateText(1, StringUtil.substring(str, 0, 8), delimeter) + " "
                    + getTimeText(1, StringUtil.substring(str, 8, 14));
        case 1:
            return getDateText(1, StringUtil.substring(str, 0, 8), delimeter);
        case 2:
            return getDateText(2, StringUtil.substring(str, 0, 8), delimeter);
        case 3:
            return getDateText(3, StringUtil.substring(str, 0, 8), delimeter);
        case 4:
            return getDateText(4, StringUtil.substring(str, 0, 8), delimeter);
        case 5:
            return getDateText(5, StringUtil.substring(str, 0, 8), delimeter);
        case 6:
            return getDateText(3, StringUtil.substring(str, 0, 8), delimeter) + " "
                    + getTimeText(2, StringUtil.substring(str, 8, 14));
        case 7:
            return getTimeText(2, StringUtil.substring(str, 8, 14));
        default:
        }

        return "";
    }

    /**
     * @Title : getYear
     * @Description : 현재 년도를 돌려준다.
     * @return String 현재 년도
     */
    public static String getYear() {
        String ym = getDate();
        return ym.substring(0, 4);
    }

    /**
     * @Title : getMonth
     * @Description : 현재 달을 돌려준다.
     * @return String 현재 달
     */
    public static String getMonth() {
        String ym = getDate();
        return ym.substring(4, 6);
    }

    /**
     * @Title : getDay
     * @Description : 현재 일자를 가져온다.
     * @return String
     */
    public static String getDay() {
        String ym = getDate();
        return ym.substring(6, 8);
    }

    /**
     * @Title : getYearMonth
     * @Description : 현재 년월을 돌려준다 - YYYYMM
     * @param @return
     * @return String 현재 년월
     */
    public static String getYearMonth() {
        String month;

        Calendar cal = Calendar.getInstance(Locale.getDefault());

        StringBuffer buf = new StringBuffer();

        buf.append(Integer.toString(cal.get(Calendar.YEAR)));
        month = Integer.toString(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1)
            month = "0" + month;

        buf.append(month);

        return buf.toString();
    }

    /**
     * @Title : getDate
     * @Description : 현재날짜를 돌려준다.
     * @return String 현재날짜
     */
    public static String getDate() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        StringBuffer buf = new StringBuffer();
        buf.append(Integer.toString(cal.get(1)));
        String month = Integer.toString(cal.get(2) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = Integer.toString(cal.get(5));
        if (day.length() == 1) {
            day = "0" + day;
        }
        buf.append(month);
        buf.append(day);
        return buf.toString();
    }

    /**
     * @Title : getDateTime
     * @Description : 현재날짜와 시간을 돌려준다.
     * @return String 현재날짜와 시간
     */
    public static String getDateTime() {
        return getDate() + getTime();
    }

    /**
     * @Title : getDateTime
     * @Description : 시스템 일시를 돌려준다.
     * @param type      타입
     * @param delimeter 구분자
     * @return String 현재날짜와 시간
     */
    public static String getDateTime(int type, String delimeter) {
        return getDateType(type, getDate() + getTime(), delimeter);
    }

    /**
     * @Title : getAddDate
     * @Description : 현재날짜에 일수를 더하여 날짜를 구한다.
     * @param amount 일수
     * @return String 현재날짜에 일수를 더한 날짜
     */
    public static String getAddDate(int amount) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.add(Calendar.DATE, amount);
        StringBuffer buf = new StringBuffer();
        buf.append(Integer.toString(cal.get(1)));
        String month = Integer.toString(cal.get(2) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = Integer.toString(cal.get(5));
        if (day.length() == 1) {
            day = "0" + day;
        }
        buf.append(month);
        buf.append(day);
        return buf.toString();
    }

    /**
     * @Title : getTimeMillisDate
     * @Description : TimeMillis값을 Date형식으로 바꾸어 준다.
     * @param time time값
     * @return String 현재날짜 문자열
     */
    public static String getTimeMillisDate(long time) {
        return getTimeMillisDate(time, DATE_GUBUN);
    }

    public static String getTimeMillisDate(long time, String delimeter) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);

        return getDate(cal, delimeter);
    }

    /**
     * @Title : getDate
     * @Description : 해당 날짜의 년월일을 돌려준다. - YYYYMMDD
     * @param cal       해당날짜의 Calendar 객체
     * @param delimeter 구분자
     * @return String 해당날짜의 년월일
     */
    public static String getDate(Calendar cal, String delimeter) {
        String month, day;

        StringBuffer buf = new StringBuffer();

        buf.append(Integer.toString(cal.get(Calendar.YEAR)));
        if (!delimeter.equals("")) {
            buf.append(delimeter);
        }
        month = Integer.toString(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        day = Integer.toString(cal.get(Calendar.DATE));
        if (day.length() == 1) {
            day = "0" + day;
        }

        buf.append(month);
        if (!delimeter.equals("")) {
            buf.append(delimeter);
        }
        buf.append(day);

        return buf.toString();
    }

    /**
     * @Title : getDateToString
     * @Description : 현재 날짜를 가져온다.
     * @param date 날짜객체
     * @param gap  증가분(minute)
     * @return String 현재날짜와 증가분을 합한 날짜
     */
    public static String getDateToString(Date date, int gap) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        cal.add(12, gap);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        return df.format(cal.getTime());
    }

    /**
     * @Title : getDayGap
     * @Description : 날짜 갭을 반환해준다.
     * @param date   날짜객체
     * @param daygap 날짜증가일수
     * @param mingap 증가분(minute)
     * @return String 날짜
     */
    public static String getDayGap(Date date, int daygap, int mingap) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        cal.add(5, daygap);
        cal.add(12, mingap);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        return df.format(cal.getTime());
    }

    /**
     * @Title : getDiffDate
     * @Description : 지정한 날짜에서 지정 시간 이전/이후 날짜(분)를 반환해준다.
     * @param date 날짜객체
     * @param type 타입
     * @param gap  갭
     * @return String 날짜
     */
    public static String getDiffDate(Date date, int type, int gap) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        cal.add(type, gap);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        return df.format(cal.getTime());
    }

    /**
     * @Title : getFirstDay
     * @Description : 해당 월의 첫날을 반환해준다.
     * @return String 해당 월의 첫날
     */
    public static String getFirstDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String buf = df.format(cal.getTime());
        return buf;
    }

    /**
     * @Title : getLastDay
     * @Description : 해당 월의 마지막날을 반환해준다.
     * @return String 해당 월의 마지막날
     */
    public static String getLastDay() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String buf = df.format(cal.getTime());
        return buf;
    }

    /**
     * @Title : getSelectLastDay
     * @Description : 입력 년월의 마지막날을 반환해준다.
     * @param year  년
     * @param month 월
     * @return String 입력 년월의 마지막날
     */
    public static String getSelectLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String buf = df.format(cal.getTime());
        return buf;
    }

    /**
     * @Title : getSelectFirstDay
     * @Description : 입력 년월의 처음날을 반환해준다.
     * @param year  년
     * @param month 월
     * @return String 입력 년월의 처음날
     */
    public static String getSelectFirstDay(int year, int month) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String buf = df.format(cal.getTime());
        return buf;
    }
}