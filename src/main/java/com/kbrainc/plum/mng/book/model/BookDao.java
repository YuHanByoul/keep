package com.kbrainc.plum.mng.book.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
* 우수환경도서 관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.book.model
* - BookDao.java
* </pre>
*
* @ClassName : BookDao
* @Description : 우수환경도서 관리 Dao 클래스
* @author : JD
* @date : 2023. 1. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface BookDao {
    
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
    public int deleteBook(BookVo bookVo) throws Exception;
    /**
    * 우수환경도서 교육주제 목록 조회
    *
    * @Title : selectBookList
    * @Description : 우수환경도서 교육주제 목록 조회
    * @param bookVo 객체
    * @throws Exception 예외
    * @return List<BookSbjctVo>
    */
    public List<BookSbjctVo> selectBookSbjctList(BookVo bookVo) throws Exception;
    /**
     * 우수환경도서 교육주제 삭제
     *
     * @Title : deleteBook
     * @Description : 우수환경도서 교육주제 삭제
     * @param  BookVo
     * @throws Exception 예외
     * @return int
     */
    public int deleteBookSbjct(BookVo bookVo) throws Exception;
    /**
     * 우수환경도서 교육 주제 등록
     *
     * @Title : deleteBook
     * @Description : 우수환경도서 교육 주제 등록
     * @param BookVo 
     * @throws Exception 예외
     * @return int
     */
    public int insertBookSbjct(BookVo bookVo) throws Exception;
}
