package com.kbrainc.plum.mng.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.book.model.BookDao;
import com.kbrainc.plum.mng.book.model.BookSbjctVo;
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
    @Transactional
    public int insertBook(BookVo bookVo) throws Exception {
        int resInt = 0;
        resInt +=bookDao.insertBook(bookVo);
        resInt +=bookDao.insertBookSbjct(bookVo);
        return resInt;
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
    @Transactional
    public int updateBook(BookVo bookVo) throws Exception {
        int resInt = 0;
        resInt +=bookDao.deleteBookSbjct(bookVo);
        resInt +=bookDao.insertBookSbjct(bookVo);
        resInt +=bookDao.updateBook(bookVo);
        return resInt;
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
    @Transactional
    public int deleteBook(BookVo bookVo) throws Exception {
        int resInt = 0;
        //resInt += bookDao.deleteBookSbjct(bookVo);
        resInt += bookDao.deleteBook(bookVo);
        return resInt;
    }
    
    /**
    * 우수환경도서 교육주제 목록 조회
    *
    * @Title : selectBookList
    * @Description : 우수환경도서 교육주제 목록 조회
    * @param bookVo 객체
    * @throws Exception 예외
    * @return List<BookSbjctVo>
    */
    public List<BookSbjctVo> selectBookSbjctList(BookVo bookVo) throws Exception{
        return bookDao.selectBookSbjctList(bookVo);
    }
    /**
     * 우수환경도서 교육주제 삭제
     *
     * @Title : deleteBook
     * @Description : 우수환경도서 교육주제 삭제
     * @param  BookVo
     * @throws Exception 예외
     * @return int
     */
    public int deleteBookSbjct(BookVo bookVo) throws Exception{
        return bookDao.deleteBookSbjct(bookVo);
    }
    /**
     * 우수환경도서 교육 주제 등록
     *
     * @Title : deleteBook
     * @Description : 우수환경도서 교육 주제 등록
     * @param BookVo 
     * @throws Exception 예외
     * @return int
     */
    public int insertBookSbjct(BookVo bookVo) throws Exception{
        return bookDao.insertBookSbjct(bookVo);
    }
}
