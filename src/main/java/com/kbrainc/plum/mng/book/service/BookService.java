package com.kbrainc.plum.mng.book.service;

import java.util.List;

import javax.validation.Valid;

import com.kbrainc.plum.mng.book.model.BookVo;
import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;

/**
* 우수환경도서 관리 서비스 클래스
*
* <pre>
* com.kbrainc.plum.mng.book.service
* - BookService.java
* </pre>
*
* @ClassName : BookService
* @Description :  우수환경도서 관리 서비스 클래스
* @author : JD
* @date : 2023. 1. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface BookService {
    
    /**
    * 우수환경도서 관리 목록 조회
    *
    * @Title : selectBookList
    * @Description : 우수환경도서 관리 목록 조회
    * @param bookVo 객체
    * @throws Exception 예외
    * @return List<BookVo>
    */
    public List<BookVo> selectBookList(BookVo bookVo) throws Exception;
    
    /**
    * 우수환경도서 관리 게시글 상세정보
    *
    * @Title : selectBookInfo
    * @Description : 우수환경도서 관리 게시글 상세정보
    * @param bookVo 객체
    * @throws Exception 예외
    * @return BookVo
    */
    public BookVo selectBookInfo(BookVo bookVo) throws Exception; 
    
    /**
     * 우수환경도서 관리 게시글 등록
     *
     * @Title : insertBook
     * @Description : 우수환경도서 관리 게시글 등록
     * @param bookVo 객체
     * @throws Exception 예외
     * @return int
     */
    public int insertBook(BookVo bookVo) throws Exception;
    
    /**
    * 우수환경도서 관리 게시글 수정
    *
    * @Title : updateBook
    * @Description : 우수환경도서 관리 게시글 수정
    * @param bookVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateBook(BookVo bookVo) throws Exception;
    
    /**
    * 우수환경도서 관리 게시글 삭제
    *
    * @Title : deleteBook
    * @Description : 우수환경도서 관리 게시글 삭제
    * @param bookids 게시글 아이디
    * @throws Exception 예외
    * @return int
    */
    public int deleteBook(String[] bookids) throws Exception;

}
