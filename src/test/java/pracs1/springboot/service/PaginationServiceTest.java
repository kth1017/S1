package pracs1.springboot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.dto.PostsListResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PaginationServiceTest {

    @Autowired PostsService postsService;
    @Autowired PostsRepository postsRepository;

    @Test
    public void indexPaginationCallTest(){
        //given
        Posts entity = new Posts("1", "1", "1");
        postsRepository.save(entity);
        for(int i=1; i<=10; ++i){
            Posts entity2 = new Posts("2", "2", "2");
            postsRepository.save(entity2);
        }

        //when
        List<PostsListResponseDto> indexList = postsService.indexPaginationCall();

        //then
        assertThat(indexList.size()).isEqualTo(5);
    }
}