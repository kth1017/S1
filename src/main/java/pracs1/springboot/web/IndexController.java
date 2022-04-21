package pracs1.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pracs1.springboot.config.auth.LoginUser;
import pracs1.springboot.config.auth.dto.SessionUser;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController { // view용 controller

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 로그인 성공시 객체 생성 > 코드 리팩토링

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; // starter 의존성으로 주소, 확장자 자동 추가
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    @GetMapping("/posts-list")
    public String postsList(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "posts-list";
    }
}
