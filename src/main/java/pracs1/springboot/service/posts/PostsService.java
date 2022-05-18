package pracs1.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.search.pagination.Pagination;
import pracs1.springboot.search.search.PostsSearch;
import pracs1.springboot.search.search.dto.SearchPostResultDto;
import pracs1.springboot.web.dto.PostsListResponseDto;
import pracs1.springboot.web.dto.PostsResponseDto;
import pracs1.springboot.web.dto.PostsSaveRequestDto;
import pracs1.springboot.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음 id=" + id));

        postsRepository.delete(posts);

    }
    /*
    인덱스 최대 글 제한
     */
    public List<PostsListResponseDto> indexPaginationCall() {
        Pagination indexPagination = Pagination.indexPaginationCreate(5);
        return postsRepository.findAllDesc().stream()
                .limit(indexPagination.getPageSize())
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    /*
    검색 기능 서비스
     */
    // 서비스 stream 사용보다 repository 메서드 쿼리 수정이 성능에 있어서 더 좋지만
    // 프로젝트가 소규모이고 스트림 실습을 위해 아래와 같은 코드 사용
    @Transactional
    public List<PostsListResponseDto> findListpaging(int startindex, int pagesize) {
        return postsRepository.findAllDesc().stream()
                .skip(startindex)
                .limit(pagesize)
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }

    public SearchPostResultDto findSearchListByType(int page, String type, String keyword) {
        PostsSearch postsSearch = new PostsSearch(postsRepository);
        SearchPostResultDto dto = postsSearch.findSearchPostsList(page, type, keyword);
        return dto;
    }

//    @Transactional
//    public List<PostsListResponseDto> findAllByTitleSearch(String keyword) {
//        return postsRepository.findByTitleContaining(keyword).stream()
//                .map(PostsListResponseDto::new)
//                .collect(Collectors.toList());
//    }

//    @Transactional
//    public Pagination findPagination(String keyword, int page) {
//        int totalListCnt = postsRepository.findByTitleContaining(keyword).size();
//        PostsSearchVo dto = new PostsSearchVo(totalListCnt, page);
//        return dto.SearchCalcul();
//    }
//
//    @Transactional
//    public List<PostsListResponseDto> findByPagination(Pagination pagination, String keyword) {
//        int startindex = pagination.getStartIndex();
//        int pageSize = pagination.getPageSize();
//        return postsRepository.findByTitleContaining(keyword).stream()
//                .skip(startindex)
//                .limit(pageSize)
//                .map(PostsListResponseDto::new)
//                .collect(Collectors.toList());
//    }


}
