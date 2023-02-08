package com.kbrainc.plum.front.book.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.book.model.BookVo;
import com.kbrainc.plum.front.book.service.BookService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import com.kbrainc.plum.front.book.model.BookVo;

/**
* 우수환경도서 관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.book.controller
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
@Controller("front.bookController")
@Alias("front.bookController")
public class BookController {

    @Resource(name = "front.bookServiceImpl")
    private BookService bookService;
    
    /**
    * 우수환경도서 관리 목록화면 이동
    *
    * @Title : bookListForm
    * @Description : 우수환경도서 관리 목록화면 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/book/bookListForm.html")
    public String bookListForm() throws Exception {

        return "front/book/bookList";
    }
    
    @RequestMapping(value = "/front/book/bookDetailForm.html")
    public String bookDetailForm(Model model, BookVo bookVo) throws Exception {
        bookService.updateBookHits(bookVo); 
        
        BookVo result = null;
        result =  bookService.selectBookInfo(bookVo);
        model.addAttribute("book", result);
        
        List<BookVo> file = bookService.selectBookFileList(bookVo);
        model.addAttribute("file", file);
        
        return "front/book/bookDetail";
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
    @RequestMapping(value = "/front/book/selectBookList.do")
    @ResponseBody
    public Map<String, Object> selectBookList(BookVo bookVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<BookVo> result = null;
        
        result =  bookService.selectBookList(bookVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 16));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
