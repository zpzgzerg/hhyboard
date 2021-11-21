package zpzgzerg.hhyboard.dto;

import lombok.Getter;

@Getter
public class PagingDto {

    long totalCount;
    int currentPage;
    int startPage;
    int endPage;
    int prevPage;
    int nextPage;
    boolean isPrev;
    boolean isNext;

    public PagingDto(long totalCount, int currentPage, int startPage, int endPage, int prevPage, int nextPage, boolean isPrev, boolean isNext) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.prevPage = prevPage;
        this.nextPage = nextPage;
        this.isPrev = isPrev;
        this.isNext = isNext;
    }
}
