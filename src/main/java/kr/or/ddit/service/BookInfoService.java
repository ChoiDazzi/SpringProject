package kr.or.ddit.service;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookInfoVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface BookInfoService {
    //메서드 시그니처
    //도서 정보 등록 (첨부파일 함께 등록)
    public int addBookPost(BookInfoVO bookInfoVO);

    //도서 코드 자동 생성
    public String getBookId();

    public List<BookInfoVO> listBook(Map<String, Object> map);

    public int getBookInfoTotal(Map<String,Object> map);

    public BookInfoVO detailBook(String bookId);

    public int updateBookPost(BookInfoVO bookInfoVO);

}
