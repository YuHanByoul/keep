package com.kbrainc.plum.mng.book.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.book.model.BookVo;
import com.kbrainc.plum.mng.book.service.BookService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 우수환경도서 관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.book.controller
* - BookController.java
* </pre>
*
* @ClassName : BookController
* @Description : 컨텐츠 관리 컨트롤러 클래스
* @author : JD
* @date : 2023. 1. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 우수환경도서 관리 목록화면 이동
    *
    * @Title : bookListForm
    * @Description : 우수환경도서 관리 목록화면 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/book/bookListForm.html")
    public String bookListForm() throws Exception {

        return "/mng/book/bookList";
    }
    
    /**
    * 우수환경도서 관리 등록화면 이동
    *
    * @Title : bookInsertForm
    * @Description : 우수환경도서 관리 등록화면 이동
    * @param model 객체
    * @param user 사용자세션정보
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/book/bookInsertForm.html")
    public String bookInsertForm(Model model, @UserInfo UserVo user) throws Exception {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        model.addAttribute("regDate", formatter.format(date));
        
        model.addAttribute("userid", user.getUserid());
        model.addAttribute("useridNm", user.getAcnt()+"("+user.getNm()+")");
        
        return "/mng/book/bookInsertForm";
    }
    
    /**
    * 우수환경도서 관리 수정화면 이동
    *
    * @Title : bookUpdateForm
    * @Description : 우수환경도서 관리 수정화면 이동
    * @param model 객체
    * @param bookVo 객체
    * @param user 사용자세션정보
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/book/bookUpdateForm.html")
    public String bookUpdateForm(Model model, BookVo bookVo, @UserInfo UserVo user) throws Exception {
        BookVo result = null;
        result =  bookService.selectBookInfo(bookVo);
        
        FileVo fileVo = new FileVo();
        fileVo.setUser(user);
        
        if (result.getAtchFilegrpid() != null && !result.getAtchFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(result.getAtchFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileMap",fileList );
            model.addAttribute("currentFileCnt", fileList.size());
        } else {
            model.addAttribute("fileMap", null);
            model.addAttribute("currentFileCnt", 0);
        }
        
        model.addAttribute("book", result);
        
        if(bookVo.getRprsImgFileid() != 0 && result.getFileIdntfcKey() != null) {
            StringBuffer fileBtn = new StringBuffer();
            fileBtn.append("<div class ='label label-inverse text-white' id='" + bookVo.getRprsImgFileid() + "'>");
            fileBtn.append("<a href=javascript:downloadFileByFileid('" + bookVo.getRprsImgFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>" + result.getOrginlFileNm() + "&nbsp;&nbsp;</a>");
            fileBtn.append("<a href=javascript:fn_deleteFileList('" + bookVo.getRprsImgFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>X</a></div>");
            model.addAttribute("fileBtn", fileBtn);
        }
        
        
        return "/mng/book/bookUpdate";
    }
    
    /**
    * 우수환경도서 관리 목록 조회
    *
    * @Title : selectBookList
    * @Description : 우수환경도서 관리 목록 조회
    * @param bookVo 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/book/selectBookList.do")
    @ResponseBody
    public Map<String, Object> selectBookList(BookVo bookVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<BookVo> result = null;
        
        result =  bookService.selectBookList(bookVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 우수환경도서 관리 등록
    *
    * @Title : insertBook
    * @Description : 우수환경도서 관리 등록
    * @param bookVo 객체
    * @param bindingResult 유효성검증 결과
    * @param user 사용자세션 정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/book/insertBook.do")
    @ResponseBody
    public Map<String, Object> insertBook(@Valid BookVo bookVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        bookVo.setUser(user);
        
        int retVal = 0;
                
        retVal = bookService.insertBook(bookVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
         return resultMap;
    }
    
    /**
    * 우수환경도서 관리 수정
    *
    * @Title : updateBook
    * @Description : 우수환경도서 관리 수정
    * @param bookVo 객체
    * @param bindingResult 유효성검증 결과
    * @param user 사용자세션 정보
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/book/updateBook.do")
    @ResponseBody
    public Map<String, Object> updateBook(@Valid BookVo bookVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        bookVo.setUser(user);

        int retVal = 0;
                
        retVal = bookService.updateBook(bookVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
    * 우수환경도서 게시글 삭제
    *
    * @Title : deleteBook
    * @Description : 우수환경도서 게시글 삭제
    * @param request 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/book/deleteBook.do")
    @ResponseBody
    public Map<String, Object> deleteBook(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        String[] bookids = request.getParameterValues("bookids");
        
        retVal = bookService.deleteBook(bookids);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }
}
