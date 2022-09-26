package com.kbrainc.plum.rte.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * ParentVO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - ParentVo.java
 * </pre> 
 *
 * @ClassName : ParentVo
 * @Description : ParentVO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class ParentVo {

    private static final long serialVersionUID = 4945484751569409009L;

    /**
     * @Title : equals
     * @Description : 필드가 변경되어도 따로 구현체를 수정하지 않아도 된다.
     * @param obj 비교할대상객체
     * @return boolean 필드값동일여부
     */
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * @Title : hashCode
     * @Description : equals가 같다면 hashCode도 같은 값을 가지도록 재정의
     * @return int 해쉬코드
     */
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * @Title : toString
     * @Description : VO정보 프린트용(디버그정보로 활용)
     * @return String VO정보출력문자열
     */
    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        } catch (Exception e) {
            return "";
        }
    }
}