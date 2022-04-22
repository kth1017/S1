package pracs1.springboot.service.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;
import pracs1.springboot.web.dto.PostsListResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PostsServiceTest {

    @Autowired PostsRepository postsRepository;
    @Autowired PostsService postsService;

    @Test
    public void findListpagingTest(){
        //given
        for(int i=1; i<=11; ++i){
            Posts entity = new Posts("1", "1", "1");
            postsRepository.save(entity);
        }
        //when
        List<PostsListResponseDto> pagingList = postsService.findListpaging(0, 10);

        //then
        assertThat(pagingList.size()).isEqualTo(10);
    }

}