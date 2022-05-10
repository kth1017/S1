package pracs1.springboot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pracs1.springboot.PostsValidationDto;
import pracs1.springboot.config.auth.LoginUser;
import pracs1.springboot.config.auth.dto.SessionUser;
import pracs1.springboot.posts.search.dto.SearchResultDto;
import pracs1.springboot.service.link.LinkService;
import pracs1.springboot.service.posts.PostsService;
import pracs1.springboot.web.LinkDto.LinkListResponseDto;
import pracs1.springboot.web.LinkDto.LinkResponseDto;
import pracs1.springboot.web.dto.PostsListResponseDto;
import pracs1.springboot.web.dto.PostsResponseDto;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LinkController { // view용 controller

    private final LinkService linkService;

    @GetMapping("/link")
    public String LinkList(Model model, @LoginUser SessionUser user,
                                  @RequestParam(defaultValue = "1") int page, String keyword, String type) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        List<LinkListResponseDto> responseDto = linkService.findAllDesc();

        model.addAttribute("link", responseDto);
        return "link";
    }

    @GetMapping("/link/save")
    public String linkSave(Model model) {
        model.addAttribute("link", new PostsValidationDto());
        return "link-save";
    }

    @PostMapping("/link/save")
    public String linkSaveForm() {
        return "link-save";
    }

    @GetMapping("link/update/{id}")
    public String linkUpdate(@PathVariable Long id, Model model) {
        LinkResponseDto dto = linkService.findById(id);
        model.addAttribute("link", dto);
        return "link-update";
    }
}


