package com.kbrainc.plum.rte.util.mail.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import org.springframework.stereotype.Component;

/**
 * 
 * 매일인증 관련 클래스  PasswordAuthentication import
 *
 * <pre>
 * com.kbrainc.plum.rte.util.mail.service
 * - MailAuthentication.java
 * </pre> 
 *
 * @ClassName : MailAuthentication
 * @Description : 매일인증 관련 클래스  PasswordAuthentication import
 * @author : KBRAINC
 * @date : 2021. 3. 4.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Component
public class MailAuthentication extends Authenticator {

    PasswordAuthentication pa;
    String id ;
    String pw ;
    
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
    
    public void setPropeties(String id,String pw){
    	this.pa = new PasswordAuthentication(id, pw);
    }
}


