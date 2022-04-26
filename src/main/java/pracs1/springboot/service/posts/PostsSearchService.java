package pracs1.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.posts.PostsRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostsSearchService {
    private final PostsRepository postsRepository;



}
