package pracs1.springboot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import pracs1.springboot.service.posts.PostsService;

import pracs1.springboot.web.dto.PostsResponseDto;
import pracs1.springboot.web.dto.PostsSaveRequestDto;
import pracs1.springboot.web.dto.PostsUpdateRequestDto;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostsApiController { // api용 컨트롤러

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody @Valid PostsSaveRequestDto requestDto) {
        log.info(requestDto.toString());
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
