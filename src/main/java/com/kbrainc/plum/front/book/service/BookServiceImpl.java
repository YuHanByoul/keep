package com.kbrainc.plum.front.book.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.book.model.BookDao;
import com.kbrainc.plum.front.book.model.BookVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 컨텐츠 관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.book.service
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
@Service("front.bookServiceImpl")
@Alias("front.bookServiceImpl")
public class BookServiceImpl extends PlumAbstractServiceImpl implements BookService{

    @Resource(name = "front.bookDao")
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
    @Override
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
    @Override
    public BookVo selectBookInfo(BookVo bookVo) throws Exception {
        return bookDao.selectBookInfo(bookVo);
    }
    
    @Override
    @Transactional
    public int updateBookHits(BookVo bookVo) throws Exception {
        return bookDao.updateBookHits(bookVo);
    };
    
    @Override
    public List<BookVo> selectBookFileList(BookVo bookVo) throws Exception {
        return bookDao.selectBookFileList(bookVo);
    };
}
