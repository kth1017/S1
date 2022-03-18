package pracs1.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index"; // starter 의존성으로 주소, 확장자 자동 추가
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
