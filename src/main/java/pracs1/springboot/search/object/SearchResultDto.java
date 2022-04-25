package pracs1.springboot.search.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pracs1.springboot.pagination.Pagination;
import pracs1.springboot.web.dto.PostsListResponseDto;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchResultDto {
    private final List<PostsListResponseDto> SearchPostsListPaging;
    private final Pagination pagination;
    private final String type;
    private final String keyword;

}
