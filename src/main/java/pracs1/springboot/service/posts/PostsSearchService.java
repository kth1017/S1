package pracs1.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.web.dto.PostsListResponseDto;
import pracs1.springboot.web.dto.PostsResponseDto;
import pracs1.springboot.web.dto.PostsSaveRequestDto;
import pracs1.springboot.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostsSearchService {
    private final PostsRepository postsRepository;

    public List<PostsListResponseDto> findSearchTitleDesc(String keyword) {
        return postsRepository.findByTitleContaining(keyword).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PostsListResponseDto> findTitleListpaging(int startindex, int pagesize, String keyword) {
        return postsRepository.findByTitleContaining(keyword).stream()
                .skip(startindex)
                .limit(pagesize)
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
