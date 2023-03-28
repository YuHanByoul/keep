package com.kbrainc.plum.mng.map.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kbrainc.plum.mng.map.model.MapDao;
import com.kbrainc.plum.mng.map.model.MapVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 지도기반데이터 관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.map.service
* - MapServiceImple.java
* </pre>
*
* @ClassName : MapServiceImple
* @Description : 지도기반데이터 관리 서비스 구현 클래스
* @author : kbrain
* @date : 2023. 3. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MapServiceImple extends PlumAbstractServiceImpl implements MapService {

	@Autowired
    private MapDao mapDao;

	@Value("${kakao.restapi.key}")
    private String kakaoRestapiKey;

	@Value("${kakao.search.address.api.url}")
    private String kakaoSearchAddressApiUrl;

	/**
	* 지도기반데이터 목록 조회
	*
	* @Title : selectMapList
	* @Description : 지도기반데이터 목록 조회
	* @param mapVo
	* @return
	* @throws Exception
	* @return List<MapVo>
	*/
	@Override
	public List<MapVo> selectMapList(MapVo mapVo) throws Exception{
		return mapDao.selectMapList(mapVo);
	}

	/**
	* 지도데이터 등록
	*
	* @Title : insertMap
	* @Description : 지도데이터 등록
	* @param mapVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int insertMap(MapVo mapVo) throws Exception {
		return mapDao.insertMap(mapVo);
	}

	/**
	* 시도목록 조회
	*
	* @Title : selectCtprvnList
	* @Description : 시도목록 조회
	* @param mapVo
	* @return
	* @throws Exception
	* @return List<MapVo>
	*/
	@Override
	public List<MapVo> selectCtprvnList() throws Exception {
		return mapDao.selectCtprvnList();
	}

	/**
	* 지도기반데이터 조회
	*
	* @Title : selectMap
	* @Description : 지도기반데이터 조회
	* @param mapVo
	* @return
	* @throws Exception
	* @return MapVo
	*/
	@Override
	public MapVo selectMap(MapVo mapVo) throws Exception {
		return mapDao.selectMap(mapVo);
	}

	/**
	* 지도기반데이터 수정
	*
	* @Title : updateMap
	* @Description : 지도기반데이터 수정
	* @param mapVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	public int updateMap(MapVo mapVo) throws Exception {
		return mapDao.updateMap(mapVo);
	}

	/**
	* 지도기반데이터 엑셀데이터 체크
	*
	* @Title : mapExcelDatalValidationCheck
	* @Description : 지도기반데이터 엑셀데이터 체크
	* @param excelList
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@Override
	public Map<String, Object> mapExcelDatalValidationCheck(ArrayList list) {
		Map<String, Object> result = new HashMap<>();
        boolean isValid = true;
        ArrayList checkList = new ArrayList();
        String status = "정상";

        String ctprvnNm="";
        String hmpg="";
        String telno="";
        String addr="";
        String chkAddr="";
        String addrDtl="";
        String ctprvnCd="";

        if(list != null) {
            ArrayList data = null;
            for(int i = 1; i < list.size(); i++) { // 헤더를 제외하기 위해 1부터 시작
                data = (ArrayList) list.get(i);
                data.add(0);
                data.add("");
                ctprvnNm = (String) data.get(0);
                hmpg     = (String) data.get(1);
                telno    = (String) data.get(2);
                addr     = (String) data.get(3);
                addrDtl  = (String) data.get(4);

                JSONObject addrJson=null;

                //환경자원명
                if(ctprvnNm == null || ctprvnNm.equals("")) {
                    status = "환경자원명이 입력되지 않았습니다.";
                //홈페이지
                }else if(hmpg == null || hmpg.equals("")) {
                    status = "홈페이지 항목이 입력되지 않았습니다.";
                //전화번호
                }else if(telno == null || telno.equals("")) {
                	status = "홈페이지 항목이 입력되지 않았습니다.";
            	//주소
                }else if(addr == null || addr.equals("")) {
                	status = "주소 항목이 입력되지 않았습니다.";
                //주소-apicheck
                }else if(addr != null && !addr.equals("")) {
                	addrJson = getAddrDocWithaddress(addr);

                	if(addrJson!=null) {
                		chkAddr  = addrJson.get("addr_name").toString();
                		if(addr.equals(chkAddr)) {
                			ctprvnCd = addrJson.get("h_code").toString().substring(0, 2);
                			status ="정상";
                		}else {
                			status ="잘못된 지역(시도)코드 입니다.";
                		}
                	}else {
                		status ="잘못된 지역(시도)코드 입니다.";
                	}

            	//주소상세
                }else if(addrDtl == null || addrDtl.equals("")) {
                	status = "주소 상세 항목이 입력되지 않았습니다.";
                }else {
                	status ="정상";
                }
                data.add(ctprvnCd);
                data.add(status);
                checkList.add(data);
            }
        }
        result.put("checkList", checkList);

        return result;

	};

	/**
	    * 주소로 주소 doc정보를 가져온다 (카카오 주소API)
	    *
	    * @Title : getSignguCdWithaddress
	    * @Description : 주소로 주소 doc정보를 가져온다 (카카오 주소API)
	    * @param addr 주소
	    * @return JSONObject
	    */
	    public JSONObject getAddrDocWithaddress(String addr) {
	        try {
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
	            headers.add("Authorization", String.format("KakaoAK %s", kakaoRestapiKey));
	            String url = String.format("%s?analyze_type=exact&page=1&size=1&query=%s", kakaoSearchAddressApiUrl, addr);

	            @SuppressWarnings("rawtypes")
	            ResponseEntity response = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity(headers), String.class);

	            JSONParser jsonParser = new JSONParser();
	            try {
	                JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
	                JSONArray array = (JSONArray) jsonObject.get("documents");
	                if(array.size() != 0){
	                    JSONObject objectDoc = (JSONObject) ((JSONObject) array.get(0)).get("address");
//	                    return objectDoc.get("h_code").toString().substring(0, 2);
	                    return objectDoc;
	                }
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	        }
	        catch(RestClientException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
}
