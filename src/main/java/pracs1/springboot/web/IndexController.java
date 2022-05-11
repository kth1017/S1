package pracs1.springboot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pracs1.springboot.config.auth.LoginUser;
import pracs1.springboot.config.auth.dto.SessionUser;
import pracs1.springboot.service.link.LinkService;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.LinkDto.LinkListResponseDto;
import pracs1.springboot.web.dto.PostsListResponseDto;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController { // view용 controller

    private final PostsService postsService;
    private final LinkService linkService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        List<PostsListResponseDto> indexPostsList = postsService.indexPaginationCall();
        List<LinkListResponseDto> indexLinkList = linkService.indexLinkPaginationCallFilter4();
        model.addAttribute("posts", indexPostsList);
        model.addAttribute("link", indexLinkList);
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 로그인 성공시 객체 생성 > 코드 리팩토링

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; // starter 의존성으로 주소, 확장자 자동 추가
    }
}