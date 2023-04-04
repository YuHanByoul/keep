package com.kbrainc.plum.cmm.kogl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kbrainc.plum.cmm.kogl.model.LicenseTypeVo;

/**
* 공공누리 저작권 요소 정보 제공.
*
* <pre>
* com.kbrainc.plum.cmm.kogl
* - KoglLicense.java
* </pre>
*
* @ClassName : KoglLicense
* @Description : 공공누리 저작권 요소 정보 제공
* @author : user
* @date : 2023. 4. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Component
public class KoglLicense {

        /** 라이센스 정보 map */
        private Map<String, LicenseTypeVo> info = new HashMap<String, LicenseTypeVo>();
    
        /**
        * Desc : license정보 셋팅
        */
        KoglLicense() {
            //info.put("241101", new LicenseTypeVo("241101", "적용안함 (저작권법 제24조 1항 1호~4호의 경우)", null, null, null));
            info.put("241102", new LicenseTypeVo("241102", "공공누리 1유형 : 출처표시", "본 저작물은 \"공공누리\" 제1유형:출처표시 조건에 따라 이용 할 수 있습니다.", "/images/common/kogl/type1.png", "https://www.kogl.or.kr/info/licenseType1.do"));
            info.put("241103", new LicenseTypeVo("241103", "공공누리 2유형 : 출처표시, 상업적이용금지", "본 저작물은 \"공공누리\" 제2유형:출처표시+상업적 이용금지 조건에 따라 이용 할 수 있습니다.", "/images/common/kogl/type2.png", "https://www.kogl.or.kr/info/licenseType2.do"));
            info.put("241104", new LicenseTypeVo("241104", "공공누리 3유형 : 출처표시, 변경금지", "본 저작물은 \"공공누리\" 제3유형:출처표시+변경금지 조건에 따라 이용 할 수 있습니다.", "/images/common/kogl/type3.png", "https://www.kogl.or.kr/info/licenseType3.do"));
            info.put("241105", new LicenseTypeVo("241105", "공공누리 4유형 : 출처표시, 상업적이용금지, 변경금지", "본 저작물은 \"공공누리\" 제4유형:출처표시+상업적 이용금지+변경금지 조건에 따라 이용 할 수 있습니다.", "/images/common/kogl/type4.png", "https://www.kogl.or.kr/info/licenseType4.do"));
        }
    
        /**
        * 공공누리 타입에 따른 라이센스 요소 정보Vo 반환.
        *
        * @Title : getInfo
        * @Description : 공공누리 타입에 따른 라이센스 요소 정보Vo 반환
        * @param typeCd 타입코드
        * @return LicenseTypeVo 라이센스 타입 정보
        */
        public LicenseTypeVo getInfo(String typeCd) {
            return info.get(typeCd);
        }
}
