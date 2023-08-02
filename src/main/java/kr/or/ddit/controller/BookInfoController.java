package kr.or.ddit.controller;

import kr.or.ddit.service.BookInfoService;
import kr.or.ddit.util.ArticlePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.vo.BookInfoVO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class BookInfoController {
	@Autowired
	BookInfoService bookInfoService;

	//요청URI : /bookInfo/addBook
	@RequestMapping(value = "/bookInfo/addBook", method = RequestMethod.GET)
	public ModelAndView addBook(ModelAndView mav) {
		//forwarding
		//tiles-config.xml에서 <definition name="			*/  *"
		//										bookInfo/addBook
		//						/WEB-INF/views/{1}		/{2}	.jsp
		mav.setViewName("bookInfo/addBook");
		
		return mav;
	}
	
	/**
	 요청URL : /bookInfo/addBookPost
	요청파라미터 : {bookId=ISBN1234,name=...}
	요청방식 : post 
	 */
	@ResponseBody
	@RequestMapping(value = "/bookInfo/addBookPost", method = RequestMethod.POST)
	public int addBookPost(ModelAndView mav,
			@ModelAttribute BookInfoVO bookInfoVO) {
		log.info("bookInfoVO : " + bookInfoVO);

		int result = bookInfoService.addBookPost(bookInfoVO);
		log.info("addBookPost => result={}", result);
		//forwarding
		//mav.setViewName("bookInfo/detailBook");
		
		return result;
	}

	/**
	 * [도서코드 자동생성]
	 */
	@ResponseBody
	@RequestMapping(value = "/bookInfo/getBookId" , method = RequestMethod.POST)
	public String getBookId() {
		String bookId = bookInfoService.getBookId();

		log.info("bookId={}", bookId);

		return bookId;
	}

	//VO: @ModelAttribute
	//map, String: @RequestParam
	//도서목록
	@RequestMapping(value = "/bookInfo/listBook", method = RequestMethod.GET)
	public ModelAndView listBook(ModelAndView mav,
								 @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
								 @RequestParam (value = "size", required = false, defaultValue = "10") int size,
								 @RequestParam (value = "keyword", required = false, defaultValue = "") String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("size", size);
		map.put("keyword", keyword);

		log.info("listBook => map={}", map);
		List<BookInfoVO> data = bookInfoService.listBook(map);

		log.info("data={}", data);

		int total = this.bookInfoService.getBookInfoTotal(map);
		log.info("total={}", total);

		//페이징 처리한 data
		mav.addObject("data", new ArticlePage<BookInfoVO>(total, currentPage, size, data));

		mav.setViewName("bookInfo/listBook");
		return mav;
	}

	@RequestMapping(value = "/bookInfo/detailBook", method = RequestMethod.GET)
	public ModelAndView detailBook(@RequestParam String bookId, ModelAndView mav){
		log.info("bookId={}", bookId);

		BookInfoVO bookInfoVO = bookInfoService.detailBook(bookId);

		log.info("bookInfoVO={}", bookInfoVO);
		mav.addObject("data", bookInfoVO);
		mav.setViewName("bookInfo/detailBook");
		return mav;
	}

	@RequestMapping(value = "/bookInfo/updateBookPost", method = RequestMethod.POST)
	public ModelAndView updateBookPost(ModelAndView mav, BookInfoVO bookInfoVO) {
		log.info("bookInfoVO={}", bookInfoVO);

		int result = bookInfoService.updateBookPost(bookInfoVO);
		log.info("updateResult={}", result);

		mav.setViewName("redirect:/bookInfo/detailBook?bookId="+bookInfoVO.getBookId());

		return mav;
	}

}
