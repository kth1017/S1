package pracs1.springboot.search.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pracs1.springboot.domain.link.Link;
import pracs1.springboot.domain.link.LinkRepository;
import pracs1.springboot.search.pagination.Pagination;
import pracs1.springboot.search.search.dto.SearchLinkResultDto;
import pracs1.springboot.web.LinkDto.LinkListResponseDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class LinkSearch {

    private final LinkRepository linkRepository;

    private int page;
    private int totalListCnt;

    private String type;
    private String keyword;

    private List<Link> searchLinkList;

    public SearchLinkResultDto findSearchPostsList(int page, String type, String keyword) {
        initParam(page, type, keyword);
        listSetByType();
        return ResultLinkDtoCreate();
    }

    /*
    페이징 메서드
     */
    public void initParam(int page, String type, String keyword) {
        this.page = page;
        this.type = type;
        this.keyword = keyword;
    }

    public void listSetByType() {
        switch (this.type) {
            case "title":
                this.searchLinkList = linkRepository.findByTitleContaining(keyword);
                break;
            case "stackCategory":
                this.searchLinkList = linkRepository.findByStackCategoryContaining(keyword);
                break;
            case "description":
                this.searchLinkList = linkRepository.findByDescriptionContaining(keyword);
                break;
        }
    }

    public SearchLinkResultDto ResultLinkDtoCreate() {
        totalListCnt = this.searchLinkList.size();
        Pagination pagination = new Pagination(totalListCnt, this.page);
        int startindex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<LinkListResponseDto> searchLinkListPaging = searchLinkList.stream()
                .sorted(Comparator.comparing(Link::getId).reversed())
                .skip(startindex)
                .limit(pageSize)
                .map(LinkListResponseDto::new)
                .collect(Collectors.toList());

        SearchLinkResultDto searchLinkResultDto = new SearchLinkResultDto(searchLinkListPaging, pagination, type, keyword);

        return searchLinkResultDto;
    }
}
