package zpzgzerg.hhyboard.service.comm;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import zpzgzerg.hhyboard.dto.PagingDto;

@Component
@ConfigurationProperties(prefix = "paging")
@Setter
public class Paging {

    private int blockPerPage;  // 한번에 보여줄 수 있는 페이지번호 수

    public PagingDto process(int currPage, int pageSize, int totalPage, long totalCount) {

        // 현재의 페이지 블록 번호
        int currentPageBlock = (int) Math.floor(currPage / blockPerPage);

        // 이전 버튼 가능 여부
        boolean isPrev = true;
        // 다음 버튼 가능 여부
        boolean isNext = true;

        // 시작페이지 구하기
        int startPage = currentPageBlock * blockPerPage;
        // 이전버튼 페이지 구하기
        int prevPage = startPage - 1;

        // 끝페이지 구하기
        int endPage = ((currentPageBlock + 1) * blockPerPage) - 1;
        if (endPage > totalPage) {
            endPage = (totalPage - 1) < 0 ? 0 : (totalPage - 1);
        }
        // 다음 버튼 페이지 구하기
        int nextPage = endPage + 1;

        // 이전 버튼 여부 체크
        if (prevPage < 0) isPrev = false;

        // 다음 버튼 여부 체크
        if (nextPage > totalPage - 1) isNext = false;

        // 시작 로우 - 끝 로우 구하기
        long startRow = (currPage * pageSize) + 1;
        long endRow = ((startRow + pageSize) -1) > totalCount ? (int) totalCount : (startRow + pageSize) -1;

        // DTO 생성 및 모델객체에 add
        return new PagingDto(totalCount, startRow, endRow, currPage, startPage, endPage, prevPage, nextPage, isPrev, isNext);
    }
}
