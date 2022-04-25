package pracs1.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.pagination.Pagination;
import pracs1.springboot.search.object.PostsSearchVo;
import pracs1.springboot.search.object.SearchResultDto;
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

    @Transactional
    public List<PostsListResponseDto> findListpaging(int startindex, int pagesize) {
        return postsRepository.findAllDesc().stream()
                .skip(startindex)
                .limit(pagesize)
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public List<PostsListResponseDto> findAllByTitleSearch(String keyword) {
        return postsRepository.findByTitleContaining(keyword).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

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

    public SearchResultDto findSearchListByType(int page, String type, String keyword) {
        PostsSearchVo postsSearchVo = new PostsSearchVo(postsRepository);
        SearchResultDto dto = postsSearchVo.findSearchPostsList(page, type, keyword);
        return dto;
    }

    public List<PostsListResponseDto> indexPaginationCall() {
        Pagination indexPagination = Pagination.indexPaginationCreate(5);
        return postsRepository.findAllDesc().stream()
                .limit(indexPagination.getPageSize())
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
