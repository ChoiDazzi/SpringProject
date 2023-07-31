package kr.or.ddit.util;

import java.util.List;

//페이징 관련 정보 + 게시글 데이터
public class ArticlePage<T> {
    private int total;          //전체 글 수
    private int currentPage;    //현재페이지 번호
    private int totalPages;     //전체페이지 수
    private int startPage;      //시작페이지 번호
    private int endPage;        //종료페이지 번호
    private List<T> content;    //데이터
    //생성자(Constructor): 페이징 정보를 생성
    public ArticlePage(int total, int currentPage, int size, List<T> content) {
        //size: 한 화면에 보여질 목록의 행 수
        this.total = total;
        this.currentPage = currentPage;
        this.content = content;

        if (total == 0) { //전체 글 수가 0이라면?
            this.totalPages = 0;
            this.startPage = 0;
            this.endPage = 0;
        } else {
            this.totalPages = this.total / size;

            if (total % size > 0) {
                this.totalPages++;
            }
            //시작페이지 = 현재페이지 / 페이지크기 * 페이지크기 + 1
            startPage = currentPage / 5 * 5 + 1;

            if (currentPage % 5 == 0) {
                startPage -= 5;
            }

            //종료페이지번호 = 시작페이지번호 + (페이지크기 - 1)
            endPage = startPage + (5-1);

            //종료페이지번호 > 전체페이지 수보다 클 때
            if (endPage < totalPages) {
                endPage = totalPages;
            }
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean hasNoArticles() {
        return total == 0;
    }

    public boolean hasArticles() {
        return total > 0;
    }
}
