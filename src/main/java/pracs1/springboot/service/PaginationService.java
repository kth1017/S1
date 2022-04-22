package pracs1.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.pagination.Pagination;
import pracs1.springboot.web.dto.PostsListResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PaginationService {
    private final PostsRepository postsRepository;

    public List<PostsListResponseDto> indexPaginationCall() {
        Pagination indexPagination = Pagination.indexPaginationCreate(5);
        return postsRepository.findAllDesc().stream()
                .limit(indexPagination.getPageSize())
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
