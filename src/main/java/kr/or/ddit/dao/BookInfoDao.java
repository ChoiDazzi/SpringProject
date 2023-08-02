package kr.or.ddit.dao;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookInfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//스프링이 [데이터를 관리하는 클래스] 라고 인지하여 자바빈으로 등록하여 관리
@Repository
public class BookInfoDao {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public int addBookPost(BookInfoVO bookInfoVO) {
        return sqlSessionTemplate.insert("bookInfo.addBookPost", bookInfoVO);
    }

    public int addAttach(AttachVO attachVO) {
        return sqlSessionTemplate.insert("bookInfo.addAttach",attachVO);
    }

    public String getBookId(){
        return sqlSessionTemplate.selectOne("bookInfo.getBookId");
    }

    public List<BookInfoVO> listBook(Map<String, Object> map){
        return sqlSessionTemplate.selectList("bookInfo.listBook", map);
    }
    public int getBookInfoTotal(Map<String,Object> map) {
        return sqlSessionTemplate.selectOne("bookInfo.getBookInfoTotal",map);
    }

    public BookInfoVO detailBook(String bookId) {
        return sqlSessionTemplate.selectOne("bookInfo.detailBook", bookId);
    }
    public int updateBookPost(BookInfoVO bookInfoVO){
        return sqlSessionTemplate.update("bookInfo.updateBookPost", bookInfoVO);
    }
}
