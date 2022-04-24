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
import pracs1.springboot.pagination.Pagination;
import pracs1.springboot.service.PaginationService;
import pracs1.springboot.service.posts.PostsSearchService;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.dto.PostsListResponseDto;
import pracs1.springboot.web.dto.PostsResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController { // view용 controller

    private final PostsService postsService;
    private final PaginationService paginationService;
    private final PostsSearchService postsSearchService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        List<PostsListResponseDto> indexPostsDtoList = paginationService.indexPaginationCall();
        model.addAttribute("posts", indexPostsDtoList);
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

        int totalListCnt = postsService.findAllDesc().size();
        Pagination pagination = new Pagination(totalListCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<PostsListResponseDto> postsList = postsService.findListpaging(startIndex, pageSize);
        model.addAttribute("postsList", postsList);
        model.addAttribute("pagination", pagination);

        return "posts-list";
    }

    @GetMapping("/search")
    public String postsSearchList(Model model, @LoginUser SessionUser user,
                                  @RequestParam(defaultValue = "1") int page, String keyword, String type) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        int totalListCnt = postsSearchService.findSearchTitleDesc(keyword).size();
        Pagination pagination = new Pagination(totalListCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<PostsListResponseDto> postsList = postsSearchService.findTitleListpaging(startIndex, pageSize, keyword);
        model.addAttribute("pagination", pagination);
        model.addAttribute("postsList", postsList);

        return "posts-searchList";
    }
}
