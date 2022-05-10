package pracs1.springboot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pracs1.springboot.service.link.LinkService;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.LinkDto.LinkResponseDto;
import pracs1.springboot.web.LinkDto.LinkSaveRequestDto;
import pracs1.springboot.web.LinkDto.LinkUpdateRequestDto;
import pracs1.springboot.web.dto.PostsResponseDto;
import pracs1.springboot.web.dto.PostsSaveRequestDto;
import pracs1.springboot.web.dto.PostsUpdateRequestDto;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LinkApiController { // api용 컨트롤러

    private final PostsService postsService;
    private final LinkService linkService;

    @PostMapping("/api/link")
    public Long save(@RequestBody @Valid LinkSaveRequestDto requestDto) {
        return linkService.save(requestDto);
    }

    @PutMapping("/api/link/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid LinkUpdateRequestDto requestDto) {
        return linkService.update(id, requestDto);
    }

    @GetMapping("/api/link/{id}")
    public LinkResponseDto findById (@PathVariable Long id) {
        return linkService.findById(id);
    }

    @DeleteMapping("/api/link/{id}")
    public Long delete(@PathVariable Long id) {
        linkService.delete(id);
        return id;
    }

}
