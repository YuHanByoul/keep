package com.kbrainc.plum.rte.mvc.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 컨트롤러의 인자로 사용할 사이트정보 파라미터맵
 *
 * <pre>
 * com.kbrainc.plum.rte.mvc.bind.annotation
 * - SiteInfo.java
 * </pre> 
 *
 * @ClassName : SiteInfo
 * @Description : 컨트롤러의 인자로 사용할 사이트정보 파라미터맵
 * @author : KBRAINC
 * @date : 2023. 1. 11.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SiteInfo {

}