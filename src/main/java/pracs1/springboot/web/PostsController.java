package pracs1.springboot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pracs1.springboot.web.dto.PostsValidationDto;
import pracs1.springboot.config.auth.LoginUser;
import pracs1.springboot.config.auth.dto.SessionUser;
import pracs1.springboot.search.search.dto.SearchPostResultDto;
import pracs1.springboot.service.link.LinkService;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.dto.PostsResponseDto;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostsController { // view용 controller

    private final PostsService postsService;
    private final LinkService linkService;

    @GetMapping("/posts/save")
    public String postsSave(Model model) {
        model.addAttribute("posts", new PostsValidationDto());
        return "posts-save";
    }

    @PostMapping("/posts/save")
    public String postsSaveForm() {
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

//    @GetMapping("/posts-list")
//    public String postsList(Model model, @LoginUser SessionUser user, @RequestParam(defaultValue = "1") int page) {
//
//        if (user != null) {
//            model.addAttribute("userName", user.getName());
//        }
//
//        /*
//        페이징 처리
//         */
//
//        int totalListCnt = postsService.findAllDesc().size();
//        Pagination pagination = new Pagination(totalListCnt, page);
//        int startIndex = pagination.getStartIndex();
//        int pageSize = pagination.getPageSize();
//
//        List<PostsListResponseDto> postsList = postsService.findListpaging(startIndex, pageSize);
//        model.addAttribute("postsList", postsList);
//        model.addAttribute("pagination", pagination);
//
//        return "search-list";
//    }

    @GetMapping("/search")
    public String postsSearchList(Model model, @LoginUser SessionUser user,
                                  @RequestParam(defaultValue = "1") int page, String keyword, String type) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        SearchPostResultDto searchPostsResult = postsService.findSearchListByType(page, type, keyword);

        model.addAttribute("postsList", searchPostsResult.getSearchPostsListPaging());
        model.addAttribute("pagination", searchPostsResult.getPagination());

        model.addAttribute("type", searchPostsResult.getType());
        model.addAttribute("keyword", searchPostsResult.getKeyword());

        return "posts-searchList";
    }
}
