package com.kbrainc.plum.cmm.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.rte.util.CommonUtil;
import com.google.gson.Gson;

/**
 * <pre>
 * com.kbrainc.plum.cmm.service - MailService.java
 * </pre>
 *
 * @ClassName : PushService
 * @Description : 푸쉬발송 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 1.0
 * @Company : CopyrightⒸ KBRAINC. All Rights Reserved
 *
 */
@Service
public class PushServiceImpl implements PushService {

    @Value("${fcm.server.key}")
    private String fcmServerKey;
    
    /** 푸쉬 다중발송 최대인원수 */
    private static final int maxLength = 1000;
    
	private final Gson gson = new Gson();

    /**
    * 단일 푸쉬 발송(비동기).
    *
    * @Title       : send 
    * @Description : 단일 푸쉬 발송(비동기).
    * @param param 푸쉬발송관련 파라미터(title, body, token 필수)
    * @return void 리턴값없음
    * @throws Exception 예외
    */
	@Override
	@Async
	public void send(Map<String, Object> param) throws Exception {
    	URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + fcmServerKey);

        conn.setDoOutput(true);
    	
    	String token = param.get("token").toString();
    	if (CommonUtil.isEmpty(token))
    		return;

    	Map<String, Object> notification = new HashMap<String, Object>();
    	notification.put("title", param.get("title"));
    	notification.put("body", param.get("body"));
    	notification.put("sound", "default");
    	
    	Map<String, Object> message = new HashMap<String, Object>();
    	message.put("to", token);
    	message.put("notification", notification);
    	
    	if (null != param.get("data")) { // 전달할 데이터 파라미터 셋팅
        	message.put("data", param.get("data"));
    	}
        
        OutputStream os = conn.getOutputStream();
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(gson.toJson(message).toString().getBytes("UTF-8"));
        os.flush();
        os.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer response = new StringBuffer();

        String inputLine = in.readLine();
        while (inputLine != null) {
            response.append(inputLine);
            inputLine = in.readLine();
        }
        in.close();
	}

	/**
    * 다중 푸쉬 발송(비동기).
    *
    * @Title       : send 
    * @Description : 다중 푸쉬 발송(비동기).
    * @param param 푸쉬발송관련 파라미터(title, body)
    * @param registrationIds 수신대상자 pushToken 배열
    * @return void 리턴값없음
    * @throws Exception 예외
    */
	@Override
	@Async
	public void send(Map<String, Object> param, String[] registrationIds) throws Exception {
		if (registrationIds.length > maxLength) {
			send(param, Arrays.copyOfRange(registrationIds, maxLength, registrationIds.length));
			registrationIds = Arrays.copyOfRange(registrationIds, 0, maxLength);
		}
		
    	URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + fcmServerKey);
        conn.setDoOutput(true);

    	Map<String, Object> notification = new HashMap<String, Object>();
    	notification.put("title", param.get("title"));
    	notification.put("body", param.get("body"));
    	notification.put("sound", "default");
    	
    	Map<String, Object> message = new HashMap<String, Object>();
    	message.put("registration_ids", registrationIds);
    	message.put("notification", notification);
    	
    	if (null != param.get("data")) { // 전달할 데이터 파라미터 셋팅
        	message.put("data", param.get("data"));
    	}
    	
        OutputStream os = conn.getOutputStream();
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(gson.toJson(message).toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuffer response = new StringBuffer();

        String inputLine = in.readLine();
        while (inputLine != null) {
            response.append(inputLine);
            inputLine = in.readLine();
        }
        in.close();
	}
}
