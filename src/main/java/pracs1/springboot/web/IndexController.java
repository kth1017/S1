package pracs1.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pracs1.springboot.config.auth.LoginUser;
import pracs1.springboot.config.auth.dto.SessionUser;
import pracs1.springboot.domain.posts.Posts;
import pracs1.springboot.domain.posts.pagination.Pagination;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.dto.PostsListResponseDto;
import pracs1.springboot.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class IndexController { // view용 controller

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        Pagination pagination = new Pagination(5, 1);
        pagination.setPageSize(5);
        List<PostsListResponseDto> posts = postsService.findAllDesc().stream()
                .limit(pagination.getPageSize())
                .collect(Collectors.toList());

        model.addAttribute("posts", posts);

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
    public String postsList(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        /*
        페이징 처리
         */

        // 총 게시물 수
        int totalListCnt = postsService.findAllDesc().size();
        // 생성인자로  총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalListCnt, page);
        // DB select start index
        int startIndex = pagination.getStartIndex();
        // 페이지 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();

        List<Posts> postsList = postsService.findListpaging(startIndex, pageSize);
        model.addAttribute("postsList", postsList);
        model.addAttribute("pagination", pagination);

        return "posts-list";
    }
}
