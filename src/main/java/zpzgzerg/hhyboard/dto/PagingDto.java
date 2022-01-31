package zpzgzerg.hhyboard.dto;

import lombok.Getter;

@Getter
public class PagingDto {

    /* 게시물 총 카운트 수*/
    long totalCount;
    /* 한페이지에 나오는 시작 row 수 */
    long startRow;
    /* 한페이지에 나오는 끝 row 수 */
    long endRow;
    /* 현재 페이지 번호 */
    int currentPage;
    /* 페이징 네비게이션에서의 시작 페이지 번호 */
    int startPage;
    /* 페이징 네비게이션에서의 끝 페이지 번호 */
    int endPage;
    /* 페이징 네비게이션에서의 이전 버튼 페이지 번호 */
    int prevPage;
    /* 페이징 네비게이션에서의 다음 페이지 번호 */
    int nextPage;
    /* 페이징 네비게이션에서의 이전 버튼 보임 유무 */
    boolean isPrev;
    /* 페이징 네비게이션에서의 다음 버튼 보임 유무 */
    boolean isNext;

    public PagingDto(long totalCount, long startRow, long endRow, int currentPage, int startPage, int endPage, int prevPage, int nextPage, boolean isPrev, boolean isNext) {
        this.totalCount = totalCount;
        this.startRow = startRow;
        this.endRow = endRow;
        this.currentPage = currentPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.prevPage = prevPage;
        this.nextPage = nextPage;
        this.isPrev = isPrev;
        this.isNext = isNext;
    }
}
