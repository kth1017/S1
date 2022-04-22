package pracs1.springboot.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.PostsRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private PostsRepository postsRepository;

    @Test
    public void 메인페이지_로딩() {

        //when
        String body = this.restTemplate.getForObject("/", String.class); // 왜 this를 씀?

        //then
        assertThat(body).contains("fadet의 프로젝트 페이지입니다");
    }

    @Test
    public void 글목록_로딩() {

        //when
        String body = this.restTemplate.getForObject("/posts-list", String.class); // 왜 this를 씀?

        //then
        assertThat(body).contains("글목록");
    }

    @Test
    public void 인덱스글리스트_출력(){

        //given
        Posts entity = new Posts("테스트1", "테스트 중", "테스터");
        postsRepository.save(entity);

        //when
        String body = this.restTemplate.getForObject("/", String.class); // 왜 this를 씀?

        //then
        assertThat(body).contains("테스트1");
    }

    @Test
    public void 게시글목록글리스트_출력(){

        //given
        Posts entity = new Posts("테스트2", "테스트 중", "테스터");
        postsRepository.save(entity);

        //when
        String body = this.restTemplate.getForObject("/posts-list", String.class); // 왜 this를 씀?

        //then
        assertThat(body).contains("테스트2");
    }




}