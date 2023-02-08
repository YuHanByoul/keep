package com.kbrainc.plum.front.book.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 우수환경도서 관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.book.model
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
@Mapper("front.bookDao")
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
    
    public int updateBookHits(BookVo bookVo) throws Exception;
    
    public List<BookVo> selectBookFileList(BookVo bookVo) throws Exception;
}
