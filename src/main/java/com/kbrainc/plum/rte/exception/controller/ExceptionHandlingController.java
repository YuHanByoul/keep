package com.kbrainc.plum.rte.exception.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.exception.FileStorageException;
import com.kbrainc.plum.rte.exception.FiledownloadCheckerException;
import com.kbrainc.plum.rte.exception.MyFileNotFoundException;
import com.kbrainc.plum.rte.exception.ParameterNotFoundException;
import com.kbrainc.plum.rte.exception.RestfulException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 처리하고자 하는 Exception을 등록하고 처리한다. 
 *
 * <pre>
 * com.kbrainc.plum.rte.exception.controller
 * - ExceptionHandlingController.java
 * </pre> 
 *
 * @ClassName : ExceptionHandlingController
 * @Description : 처리하고자 하는 Exception을 등록하고 처리한다. 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@ControllerAdvice
public class ExceptionHandlingController {

    protected Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    /**
     * 기본적인 exception을 처리한다.
     *
     * @Title       : getCommonException 
     * @Description : Exception이 발생하면 logger를 찍고 500페이지를 출력하도록 했다.
     * @param e : 
     * @return String
     */
    @ExceptionHandler({ Exception.class })
    public String getCommonException(Exception e) {
        logger.error("getCommonException.Exception.53L");
        e.printStackTrace();
        return "error/error_500";
    }
    
    /**
     * 필수 파라미터 누락에 대한 Exception.
     * 
     * @param e :
     * @return
     */
    /*
    @ExceptionHandler({ ParameterNotFoundException.class })
    public String parameterNotFoundError(ParameterNotFoundException e) {
        String msg = e.getMessage();
        logger.error("parameterNotFoundError : {}", msg);

        return "error/exception-001";
    }*/
    
    /**
     * RESTFul Exception 처리.
     *
     * @Title       : restfulException 
     * @Description : TODO
     * @param e :
     * @return Map String,Object
     */
    /*
    @ExceptionHandler({ RestfulException.class })
    @ResponseBody
    public Map<String, Object> restfullException(RestfulException e) {
        logger.error("RestfullException");
        
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("result", Constant.REST_API_RESULT_FAIL);
        
        res.put("msg", (e.getMessage() != null) ?  e.getMessage() : "Occur RestfulException.");
        
        return res;
    }*/

    /**
    * FileStorage Exception 처리.
    *
    * @Title       : FileStorageException 
    * @Description : TODO
    * @param e :
    * @return Map String,Object
    */
    @ExceptionHandler({ FileStorageException.class })
    @ResponseBody
    public Map<String, Object> restfullException(FileStorageException e) {
        logger.error("FileStorageException");
        
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("result", Constant.REST_API_RESULT_FAIL);
        
        res.put("msg", (e.getMessage() != null) ?  e.getMessage() : "Occur RestfulException.");
        
        return res;
    }
    
    @ExceptionHandler({ MyFileNotFoundException.class })
    @ResponseBody
    public String myFileNotFoundException(MyFileNotFoundException e) {
        return "404";
    }
    
    @ExceptionHandler({ FiledownloadCheckerException.class })
    @ResponseBody
    public String filedownloadCheckerException(FiledownloadCheckerException e) {
        return "403";
    }
}
