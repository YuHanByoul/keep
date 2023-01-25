package com.kbrainc.plum.mng.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.book.model.BookDao;
import com.kbrainc.plum.mng.book.model.BookVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 컨텐츠 관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.book.service
* - BookServiceImpl.java
* </pre>
*
* @ClassName : BookServiceImpl
* @Description : 컨텐츠 관리 서비스 구현 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class BookServiceImpl extends PlumAbstractServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;
    
    /**
    * 우수환경도서 관리 목록 조회
    *
    * @Title : selectBookList
    * @Description : 우수환경도서 관리 목록 조회
    * @param bookVo 객체
    * @throws Exception 예외
    * @return List<BookVo>
    */
    public List<BookVo> selectBookList(BookVo bookVo) throws Exception {
        return bookDao.selectBookList(bookVo);
    };
    
    /**
    * 우수환경도서 관리 게시글 상세정보
    *
    * @Title : selectBookInfo
    * @Description : 우수환경도서 관리 게시글 상세정보
    * @param bookVo 객체
    * @throws Exception 예외
    * @return BookVo
    */
    public BookVo selectBookInfo(BookVo bookVo) throws Exception {
        return bookDao.selectBookInfo(bookVo);
    }
    
    /**
    * 우수환경도서 관리 게시글 등록
    *
    * @Title : insertBook
    * @Description : 우수환경도서 관리 게시글 등록
    * @param bookVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertBook(BookVo bookVo) throws Exception {
        return bookDao.insertBook(bookVo);
    }
    
    /**
    * 우수환경도서 관리 게시글 수정
    *
    * @Title : updateBook
    * @Description : 우수환경도서 관리 게시글 수정
    * @param bookVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateBook(BookVo bookVo) throws Exception {
        return bookDao.updateBook(bookVo);
    }
    
    /**
    * 우수환경도서 관리 게시글 삭제
    *
    * @Title : deleteBook
    * @Description : 우수환경도서 관리 게시글 삭제
    * @param bookids 게시글 아이디
    * @throws Exception 예외
    * @return int
    */
    public int deleteBook(String[] bookids) throws Exception {
        return bookDao.deleteBook(bookids);
    }
}
