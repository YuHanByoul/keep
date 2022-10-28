package com.kbrainc.plum.rte.model;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Ref;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kbrainc.plum.rte.util.StringUtil;

/**
 * 
 * ParentMapper 클래스에서 DataSet리턴시 사용되는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - DataSet.java
 * </pre> 
 *
 * @ClassName : DataSet
 * @Description : ParentMapper 클래스에서 DataSet리턴시 사용되는 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class DataSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSet.class);
    private ArrayList dsLst = null;
    private HashMap hmap = null;
    private int lstPos = -1;
    private int size = 0;
    private Object obj = null;

    /**
     * Desc : Constructor of DataSet.java class
     */
    public DataSet() {
    }

    /**
     * Desc : Constructor of DataSet.java class
     * 
     * @param list 데이터목록
     */
    public DataSet(ArrayList list) {
        dsLst = new ArrayList();
        dsLst.addAll(list);
        size = dsLst.size();
    }

    /**
     * 데이터셋의 정보를 구성한다.
     * 
     * @Title : setDataSet
     * @Description : 데이터셋의 정보를 구성한다.
     * @param llist 데이터목록
     */
    public void setDataSet(ArrayList llist) {
        dsLst = llist;
        size = dsLst.size();
    }

    /**
     * @Title : getDataSet
     * @Description : 테이터 리스트를 돌려준다.
     * @return List 데이터목록
     */
    public List getDataSet() {
        return dsLst;
    }

    /**
     * @Title : getRow
     * @Description : 전체Row의 수를 리턴.
     * @return int 전체 로우의 수
     */
    public int getRow() {
        return dsLst.size();
    }

    /**
     * @Title : getMapSize
     * @Description : 각 로우의 필드 갯수를 가지고 온다
     * @return int 현재 위치가 가르키는 로우의 필드 갯수
     */
    public int getMapSize() {
        hmap = (HashMap) dsLst.get(lstPos);
        return hmap.size();
    }

    /**
     * @Title : getPos
     * @Description : 현재위치를 리턴.
     * @return int 현재 위치
     */
    public int getPos() {
        return lstPos;
    }

    /**
     * @Title : setPos
     * @Description : 헤더에 포인터를 위치.
     * @return void
     */
    public void setPos() {
        lstPos = -1;
    }

    /**
     * @Title : next
     * @Description : 다음 레코드로 이동.
     * @return boolean 레코드 이동 성공 여부
     */
    public boolean next() {
        if ((lstPos + 1) < size) {
            lstPos = lstPos + 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title : previous
     * @Description : 이전레코드로 이동.
     * @return boolean 레코드 이동 성공 여부
     */
    public boolean previous() {
        if ((lstPos + 1) <= size && lstPos > 0) {
            lstPos = lstPos - 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title : isFirst
     * @Description : 첫번째 레코드인지 확인
     * @return boolean 첫번째 레코드 여부
     */
    public boolean isFirst() {
        if (lstPos == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title : isLast
     * @Description : 마지막 레코드인지 확인
     * @return boolean 마지막 레코드 여부
     */
    public boolean isLast() {
        if ((lstPos + 1) == size) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title : absolute
     * @Description : 지정된 위치로 포인터를 이동.
     * @param row 위치값
     * @return boolean 포인터 이동 성공 여부
     */
    public boolean absolute(int row) {
        if (row < size && row > 0) {
            lstPos = row - 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title : first
     * @Description : 첫번째 포인터로 이동
     * @return void
     */
    public void first() {
        lstPos = 0;
    }

    /**
     * @Title : last
     * @Description : 마지막 포인터로 이동
     * @return void
     */
    public void last() {
        lstPos = size - 1;
    }

    /**
     * @Title : close
     * @Description : 레코드셋의 자원 해제
     * @return void
     */
    public void close() {
        lstPos = -1;
        size = 0;
        dsLst = null;
    }

    /**
     * @Title : getArray
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 배열객체를 반환한다.
     * @param colName 컬럼명
     * @throws Exception
     * @return Array 배열객체
     */
    public Array getArray(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (Array) hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getAsciiStream
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 스트림객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return InputStream 스트림객체
     */
    public InputStream getAsciiStream(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (InputStream) hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getBigDecimal
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 BigDecimal객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return BigDecimal BigDecimal객체
     */
    public BigDecimal getBigDecimal(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return new BigDecimal(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getBinaryStream
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 InputStream객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return InputStream InputStream객체
     */
    public InputStream getBinaryStream(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (InputStream) hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getBlob
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 Blob객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Blob Blob객체
     */
    public Blob getBlob(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (Blob) hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getBoolean
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 boolean타입으로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return boolean true 또는 false
     */
    public boolean getBoolean(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Boolean.valueOf(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getByte
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 byte타입으로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return byte byte타입값
     */
    public byte getByte(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Byte.parseByte(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return Byte.parseByte("");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getCharacterStream
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 Reader객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Reader Reader객체
     */
    public Reader getCharacterStream(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (Reader) (hmap.get(colName.toUpperCase(new Locale(colName)).trim()));
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getClob
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 Clob객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Clob Clob객체
     */
    public Clob getClob(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (Clob) hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getDate
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 Date객체로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Date Date객체
     */
    public java.sql.Date getDate(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return java.sql.Date.valueOf(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getDouble
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 double타입으로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return double double타입값
     */
    public double getDouble(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Double.parseDouble(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return Double.parseDouble("0");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getFloat
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 float타입으로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return float float타입값
     */
    public float getFloat(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Float.parseFloat(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return Float.parseFloat("0");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getInt
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 int타입으로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return int int타입값
     */
    public int getInt(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Integer.parseInt(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return Integer.parseInt("0");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getLong
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 long타입으로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return long long타입값
     */
    public long getLong(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Long.parseLong(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return Long.parseLong("0");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getObject
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 Object객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Object Object객체
     */
    public Object getObject(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getCurMap
     * @Description : 현재 위치의 맵을 돌려준다.
     * @throws Exception
     * @return HashMap 레코드정보
     */
    public HashMap getCurMap() throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return hmap;
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getCurBox
     * @Description : 현재 위치의 맵을 돌려준다.
     * @throws Exception
     * @return Map 레코드정보
     */
    public Map getCurBox() throws Exception {
        Map map = null;
        try {
            map = new HashMap((HashMap) dsLst.get(lstPos));
            return map;
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getRef
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 Ref객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Ref Ref객체
     */
    public Ref getRef(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (Ref) hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getShort
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 문자열을 short타입으로 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return short short타입값
     */
    public short getShort(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Short.parseShort(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return Short.parseShort("0");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getString
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 String객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return String String객체
     */
    public String getString(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return StringUtil.nvl(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return "";
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getTime
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 Time객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Time Time객체
     */
    public java.sql.Time getTime(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return java.sql.Time.valueOf(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getTimestamp
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 Timestamp객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return Timestamp Timestamp객체
     */
    public Timestamp getTimestamp(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return Timestamp.valueOf(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getUnicodeStream
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 InputStream객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return InputStream InputStream객체
     */
    public InputStream getUnicodeStream(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return (InputStream) hmap.get(colName.toUpperCase(new Locale(colName)).trim());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : getURL
     * @Description : 현재포인터 위치의 로우에서 컬럼명에 해당하는 URL객체를 반환한다.
     * @param colName 컴럼명
     * @throws Exception
     * @return URL URL객체
     */
    public URL getURL(String colName) throws Exception {
        try {
            hmap = (HashMap) dsLst.get(lstPos);
            return new URL(hmap.get(colName.toUpperCase(new Locale(colName)).trim()).toString());
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    /**
     * @Title : setObject
     * @Description : 객체를 set한다.
     * @param obj
     * @return void
     */
    public void setObject(Object obj) {
        this.obj = obj;
    }

    /**
     * @Title : getObject
     * @Description : 객체를 get한다.
     * @return Object Object객체
     */
    public Object getObject() {
        return this.obj;
    }

    /**
     * @Title : getFirstObject
     * @Description : 첫번째 객체를 반환한다.
     * @return Map 첫번째 레코드정보
     */
    public Map getFirstObject() {
        Map map = null;

        if (size > 0) {
            if (lstPos == -1) {
                lstPos++;
                map = new HashMap((HashMap) dsLst.get(lstPos));
            } else {
                map = new HashMap((HashMap) dsLst.get(0));
            }
        }

        return map;
    }

    /**
     * @Title : getFirstMap
     * @Description : 첫번째 맵객체를 반환한다.
     * @return HashMap 첫번째 레코드정보
     */
    public HashMap getFirstMap() {
        if ((lstPos + 1) < size) {
            lstPos = lstPos + 1;
        }
        HashMap map = null;

        map = (HashMap) dsLst.get(lstPos);
        return map;
    }
}