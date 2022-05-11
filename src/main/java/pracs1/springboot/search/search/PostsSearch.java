package pracs1.springboot.search.search;

import lombok.*;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.search.pagination.Pagination;
import pracs1.springboot.search.search.PostsDto.SearchPostResultDto;
import pracs1.springboot.web.dto.PostsListResponseDto;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class PostsSearch {

    private final PostsRepository postsRepository;

    private int page;
    private int totalListCnt;

    private String type;
    private String keyword;

    private List<Posts> searchPostsList;

    public SearchPostResultDto findSearchPostsList(int page, String type, String keyword) {
        initParam(page, type, keyword);
        listSetByType();
        return ResultDtoCreate();
    }
    /*
    페이징 메서드
     */
    public void initParam(int page, String type, String keyword){
        this.page = page;
        this.type = type;
        this.keyword = keyword;
    }

    public void listSetByType() {
        switch (this.type) {
            case "title":
                this.searchPostsList = postsRepository.findByTitleContaining(keyword);
                break;
            case "content":
                this.searchPostsList = postsRepository.findByContentContaining(keyword);
                break;
            case "author":
                this.searchPostsList = postsRepository.findByAuthorContaining(keyword);
                break;
        }
    }

    public SearchPostResultDto ResultDtoCreate(){
        totalListCnt = this.searchPostsList.size();
        Pagination pagination = new Pagination(totalListCnt, this.page);
        int startindex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<PostsListResponseDto> searchPostsListPaging = searchPostsList.stream()
                .sorted(Comparator.comparing(Posts::getId).reversed())
                .skip(startindex)
                .limit(pageSize)
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

        SearchPostResultDto searchResultDto = new SearchPostResultDto(searchPostsListPaging, pagination, type, keyword);

        return searchResultDto;
    }

    // 분할 전
//    public SearchResultDto findSearchPostsList(int page, String type, String keyword) {
//        this.page = page;
//        this.type = type;
//        this.keyword = keyword;
//
//        switch (type) {
//            case "title":
//                this.searchPostsList = postsRepository.findByTitleContaining(keyword);
//                break;
//            case "content":
//                this.searchPostsList = postsRepository.findByContentContaining(keyword);
//                break;
//            case "author":
//                this.searchPostsList = postsRepository.findByAuthorContaining(keyword);
//                break;
//        }
//
//        totalListCnt = searchPostsList.size();
//        Pagination pagination = new Pagination(totalListCnt, page);
//        int startindex = pagination.getStartIndex();
//        int pageSize = pagination.getPageSize();
//
//        List<PostsListResponseDto> searchPostsListPaging = searchPostsList.stream()
//                .sorted(Comparator.comparing(Posts::getId).reversed())
//                .skip(startindex)
//                .limit(pageSize)
//                .map(PostsListResponseDto::new)
//                .collect(Collectors.toList());
//
//        SearchResultDto searchResultDto = new SearchResultDto(searchPostsListPaging, pagination, type, keyword);
//
//        return searchResultDto;
//    }
}
