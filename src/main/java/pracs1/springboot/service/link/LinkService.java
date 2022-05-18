package pracs1.springboot.service.link;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.link.Link;
import pracs1.springboot.domain.link.LinkRepository;
import pracs1.springboot.search.pagination.Pagination;
import pracs1.springboot.search.search.LinkSearch;
import pracs1.springboot.search.search.dto.SearchLinkResultDto;
import pracs1.springboot.web.LinkDto.LinkListResponseDto;
import pracs1.springboot.web.LinkDto.LinkResponseDto;
import pracs1.springboot.web.LinkDto.LinkSaveRequestDto;
import pracs1.springboot.web.LinkDto.LinkUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LinkService {
    private final LinkRepository linkRepository;

    @Transactional
    public Long save(LinkSaveRequestDto requestDto) {
        return linkRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, LinkUpdateRequestDto requestDto) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 링크가 없습니다. id=" + id));

        link.update(requestDto.getTitle(), requestDto.getStackCategory(), requestDto.getDescription(),
                requestDto.getPostNum(), requestDto.getGithubRepo(), requestDto.getImportance());

        return id;
    }

    public LinkResponseDto findById(Long id) {
        Link entity = linkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 링크가 없습니다. id=" + id));
        return new LinkResponseDto(entity);
    }

    public List<LinkListResponseDto> findAllDesc() {
        return linkRepository.findAllDesc().stream()
                .map(LinkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("링크 없음 id=" + id));

        linkRepository.delete(link);

    }

    // 인덱스 최대 글 제한
    public List<LinkListResponseDto> indexLinkPaginationCallFilter4() {
        Pagination indexPagination = Pagination.indexPaginationCreate(5);
        return linkRepository.findAllDesc().stream()
                .filter(i -> i.getImportance() == 4)
                .limit(indexPagination.getPageSize())
                .map(LinkListResponseDto::new)
                .collect(Collectors.toList());
    }

    /*
    검색 기능 서비스
     */

    @Transactional
    public List<LinkListResponseDto> findListpaging(int startindex, int pagesize) {
        return linkRepository.findAllDesc().stream()
                .skip(startindex)
                .limit(pagesize)
                .map(LinkListResponseDto::new)
                .collect(Collectors.toList());

    }

    public SearchLinkResultDto findSearchLinkByType(int page, String type, String keyword) {
        LinkSearch linkSearch = new LinkSearch(linkRepository);
        SearchLinkResultDto dto = linkSearch.findSearchPostsList(page, type, keyword);
        return dto;
    }
}
