<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                <div class="row">
                    <div class="col-sm-12 col-md-6">
                        <div class="dataTables_length" id="dataTable_length">
                            <label>Show <select name="dataTable_length"
                                                aria-controls="dataTable"
                                                class="custom-select custom-select-sm form-control form-control-sm"><option
                                    value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option></select> entries
                            </label>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-6">
                        <div id="dataTable_filter" class="dataTables_filter">
                            <label>Search:<input type="search"
                                                 class="form-control form-control-sm" placeholder=""
                                                 aria-controls="dataTable"></label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-bordered dataTable" id="dataTable"
                               width="100%" cellspacing="0" role="grid"
                               aria-describedby="dataTable_info" style="width: 100%;">
                            <thead>
                            <tr role="row">
                                <th class="sorting sorting_asc" tabindex="0"
                                    aria-controls="dataTable" rowspan="1" colspan="1"
                                    aria-sort="ascending"
                                    aria-label="순번: activate to so번rt column descending"
                                    style="width: 10%;">순번</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1"
                                    aria-label="분류: activate to sort column ascending"
                                    style="width: 10%;">분류</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1"
                                    aria-label="도서명: activate to sort column ascending"
                                    style="width: 15%;">도서명</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1"
                                    aria-label="요약: activate to sort column ascending"
                                    style="width: 20%;">요약</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1"
                                    aria-label="저자: activate to sort column ascending"
                                    style="width: 15%;">저자</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1"
                                    aria-label="출판사: activate to sort column ascending"
                                    style="width: 15%;">출판사</th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1"
                                    aria-label="발매일: activate to sort column ascending"
                                    style="width: 15%;">발매일</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!--
                                mav.addObject("data, data)
                                data: List<BookInfoVO>

                                stat.count: 1부터 시작
                                stat.index: 0부터 시작
                            -->
                            <c:forEach var="bookInfoVO" items="#{data.content}" varStatus="stat">
                                <tr class="odd">
                                    <td class="sorting_1">${bookInfoVO.rnum}</td>
                                    <td>${bookInfoVO.category}</td>
                                    <td>${bookInfoVO.name}</td>
                                    <td>${bookInfoVO.description}</td>
                                    <td>${bookInfoVO.author}</td>
                                    <td>${bookInfoVO.publisher}</td>
                                    <td>${bookInfoVO.releaseDate}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-5">
                        <div class="dataTables_info" id="dataTable_info" role="status"
                             aria-live="polite">Showing 1 to 10 of 57 entries</div>
                    </div>
                    <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_simple_numbers"
                             id="dataTable_paginate">
                            <ul class="pagination">
                                <li class="paginate_button page-item previous disabled"
                                    id="dataTable_previous"><a href="#"
                                                               aria-controls="dataTable" data-dt-idx="0" tabindex="0"
                                                               class="page-link">Previous</a></li>
                                <c:forEach var="pNo" begin="${data.startPage}" end="${data.endPage}">
                                <li class='paginate_button page-item <c:if test = "${param.currentPage == pNo}"> active </c:if>' >
                                        <a href="/bookInfo/listBook?currentPage=${pNo}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">
                                            ${pNo}
                                        </a>
                                    </li>
                                </c:forEach>
                                <li class="paginate_button page-item next" id="dataTable_next"><a
                                        href="#" aria-controls="dataTable" data-dt-idx="7" tabindex="0"
                                        class="page-link">Next</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>