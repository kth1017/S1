package pracs1.springboot.seach.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pracs1.springboot.pagination.Pagination;

@Getter
@Setter
@NoArgsConstructor
public class PostsSearchRequestDto {
    int page;
    int totalListCnt;
    int startIndex;
    int pageSize;

    public PostsSearchRequestDto(int totalListCnt, int page) {
        this.totalListCnt = totalListCnt;
        this.page = page;
    }

    public Pagination SearchCalcul() {

        Pagination pagination = new Pagination(totalListCnt, page);

        return pagination;
    }
}
