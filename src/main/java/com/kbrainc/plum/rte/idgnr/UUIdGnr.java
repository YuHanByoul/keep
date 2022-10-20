package com.kbrainc.plum.rte.idgnr;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

import com.kbrainc.plum.rte.exception.CustomRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UUIdGnr {

	private static final String ERROR_STRING = "address in the configuration should be a valid IP or MAC Address";

    /**
     * Address Id
     */
    private String mAddressId;

    /**
     * MAC Address
     */
    private long hostId;

    /**
     * BigDecimal 타입을 아이디 제공
     * @return BigDecimal 타입 ID
     * @throws FdlException 아이디 생성에 실패한 경우
     */
    public BigDecimal getNextBigDecimalId() throws Exception {
        String newId = getNextStringId().replaceAll("-", "");
        // CHECKSTYLE:OFF
        BigInteger bi = new BigInteger(newId, 16);
        // CHECKSTYLE:ON
        BigDecimal bd = new BigDecimal(bi);
        return bd;
    }

    /**
     * String 타입을 아이디 제공
     * @return String 타입 ID
     * @throws FdlException 아이디 생성에 실패한 경우
     */
    public String getNextStringId() throws Exception {
        return getUUId();
    }

    /**
     * Config 정보에 지정된 Address 세팅
     * @param address Config 에 지정된 address 정보
     * @throws FdlException IP 정보가 이상한 경우
     */
    public void setAddress(String address) throws Exception {
    	if ("".equals(address)) {
    		return;
    	}
        byte[] addressBytes = new byte[6];
        
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        if (null == address) {
            log.warn("IDGeneration Service : Using a random number as the base for id's."
            		+ "This is not the best method for many purposes, but may be adequate in some circumstances."
            		+ " Consider using an IP or ethernet (MAC) address if available. ");
            for (int i = 0; i < 6; i++) {
            	//2017-02-03 장동한 시큐어코딩(ES)-시큐어 코딩 적절하지 않은 난수값 사용[CWE-330]
            	addressBytes[i] = (byte) (random.nextDouble() * 255 + 0);
            }
        } else { 
            if (address.indexOf(".") > 0) {
                // we should have an IP
                StringTokenizer stok = new StringTokenizer(address, ".");
                if (stok.countTokens() != 4) {
                    throw new CustomRuntimeException(ERROR_STRING);
                }
                addressBytes[0] = (byte) 255;
                addressBytes[1] = (byte) 255;
                int i = 2;
				while (stok.hasMoreTokens()) {
					Integer intValue = Integer.valueOf(stok.nextToken(),16);
					addressBytes[i++] = intValue.byteValue();
				}
				
            } else if (address.indexOf(":") > 0) {
                // we should have a MAC
                StringTokenizer stok = new StringTokenizer(address, ":");
                if (stok.countTokens() != 6) {
                    throw new CustomRuntimeException(ERROR_STRING);
                }
                int i = 0;
				while (stok.hasMoreTokens()) {
					
					Integer intValue = Integer.valueOf(stok.nextToken(),16);
					addressBytes[i++] = intValue.byteValue();
				}
            } else {
                throw new CustomRuntimeException(ERROR_STRING);
            }
        }
        // CHECKSTYLE:ON

        mAddressId = TimeBasedUUIDGenerator.getMacAddressAsString(addressBytes);
        hostId = TimeBasedUUIDGenerator.getMacAddressAsLong(addressBytes);
        log.debug("Address Id : " + mAddressId);
    }

    /**
     * UUID 얻기
     * @return String unique id
     */
    private String getUUId() {
    	if (mAddressId == null) {
    		return UUID.randomUUID().toString();
    	} else {
    		return TimeBasedUUIDGenerator.generateId(hostId).toString();
    	}
    }
}

/**
 * Will generate time-based UUID (version 1 UUID).
 * Requires JDK 1.6+
 * 
 * @author Oleg Zhurakousky
 */
@Slf4j
final class TimeBasedUUIDGenerator {
	public static final Object LOCK = new Object();
	private static long lastTime;
	private static long clockSequence = 0;
	private static final long HOST_IDENTIFIER = getHostId();
	
	private TimeBasedUUIDGenerator() {
	}

	/**
	 * Will generate unique time based UUID where the next UUID is 
	 * always greater then the previous.
	 */
	public static final UUID generateId() {
		return generateIdFromTimestamp(System.currentTimeMillis(), 0L);
	}
	
	public static final UUID generateId(long hostId) {
		return generateIdFromTimestamp(System.currentTimeMillis(), hostId);
	}

	public static final UUID generateIdFromTimestamp(long currentTimeMillis, long hostId) {
		long time;
		synchronized (LOCK) {
			if (currentTimeMillis > lastTime) {
				lastTime = currentTimeMillis;
				clockSequence = 0;
			} else {
				++clockSequence;
			}
		}

		time = currentTimeMillis;
		// low Time
		time = currentTimeMillis << 32;
		// mid Time
		time |= ((currentTimeMillis & 0xFFFF00000000L) >> 16);
		// hi Time
		time |= 0x1000 | ((currentTimeMillis >> 48) & 0x0FFF);

		long clockSequenceHi = clockSequence;
		clockSequenceHi <<= 48;
		long lsb = (hostId != 0L ? clockSequenceHi | hostId : clockSequenceHi | HOST_IDENTIFIER);

		return new UUID(time, lsb);
	}

	private static final long getHostId() {
		long macAddressAsLong = 0;
		try {
			InetAddress address = InetAddress.getLocalHost();
			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			if (ni != null) {
				byte[] mac = ni.getHardwareAddress();
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						macAddressAsLong <<= 8;
						macAddressAsLong ^= (long) mac[i] & 0xFF;
					}
				}
			}
		//2017.02.15 장동한 시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
		} catch(IllegalArgumentException | UnknownHostException | SocketException e) {
			log.error("[IllegalArgumentException] getHostId Exception : "+ e.getMessage());
		}

		log.debug("MAC Address (from Network Interface) : " + getMacAddressAsString(getMacAddress(macAddressAsLong)));

		return macAddressAsLong;
	}

	public static byte[] getMacAddress(long address) {
		byte[] addressInBytes = new byte[] {
			(byte)((address >> 40) & 0xff),
			(byte)((address >> 32) & 0xff),
			(byte)((address >> 24) & 0xff),
			(byte)((address >> 16) & 0xff),
			(byte)((address >> 8 ) & 0xff),
			(byte)((address >> 0) & 0xff)
		};
		return addressInBytes;
	}

	public static String getMacAddressAsString(byte[] address) {
		StringBuilder builder = new StringBuilder();
		for (byte b : address) {
			if (builder.length() > 0) {
				builder.append(":");
			}
			builder.append(String.format("%02X", b & 0xFF));
		}
		return builder.toString();
	}
	
	public static long getMacAddressAsLong(byte[] address) {
		long mac = 0;
		// CHECKSTYLE:OFF
		for (int i = 0; i < 6; i++) {
			long t = (address[i] & 0xffL) << ((5 - i) * 8);
			mac |= t;
		}

		return mac;
	}
	
}