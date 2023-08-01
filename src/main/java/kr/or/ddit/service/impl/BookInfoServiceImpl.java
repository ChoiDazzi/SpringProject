package kr.or.ddit.service.impl;

import kr.or.ddit.dao.BookInfoDao;
import kr.or.ddit.service.BookInfoService;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BookInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    BookInfoDao bookInfoDao;

    public int addBookPost(BookInfoVO bookInfoVO) {
        //1) 도서정보 등록
        int result =  bookInfoDao.addBookPost(bookInfoVO);

        //2) 첨부파일 등록. #{bookId}, #{filename}
        AttachVO attachVO = new AttachVO();
        attachVO.setBookId(bookInfoVO.getBookId()); //참조무결성

        String uploadFolder = "/Users/ChoiSeoYeon/Downloads/springProj (1)/src/main/webapp/resources/images";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = formatter.format(date);
        File uploadPath = new File(uploadFolder,str.replace("-", File.separator));
        log.info("uploadPath ={}", uploadPath);

        if (uploadPath.exists()==false) {
            uploadPath.mkdirs(); //해당 폴더 없으면 생성
        }

        MultipartFile multipartFile = bookInfoVO.getBookImage();

        log.info("filename={}", multipartFile.getOriginalFilename());
        log.info("filesize={}", multipartFile.getSize());
        log.info("MIME type={}", multipartFile.getContentType());

        //원래 파일명
        String uploadFileName = multipartFile.getOriginalFilename();
        UUID uuid = UUID.randomUUID();  //랜덤값, 같은 날 같은 이미지 중복 방지
        uploadFileName = uuid.toString() + "_" + uploadFileName;

        //File 객체 복사 설계 (복사할 대상 경로, 파일명)
        File saveFile = new File(uploadPath, uploadFileName);
        //실행
        try {
            multipartFile.transferTo(saveFile);
            //str: ..2023-07-28
            //window : "/" + str.replace("-", File.separator).replace("\\","/") + uploadFileName
            attachVO.setFilename("/" + str.replace("-", File.separator) + uploadFileName);

            result += bookInfoDao.addAttach(attachVO); //자식
            log.info("final result={}", result);

            return result;

        } catch (IOException e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public String getBookId() {
        return bookInfoDao.getBookId();
    }

    @Override
    public List<BookInfoVO> listBook(Map<String, Object> map) {
        return bookInfoDao.listBook(map);
    }

    @Override
    public int getBookInfoTotal(Map<String,Object> map) {
        return bookInfoDao.getBookInfoTotal(map);
    }

    @Override
    public BookInfoVO detailBook(String bookId) {
        return bookInfoDao.detailBook(bookId);
    }

}
