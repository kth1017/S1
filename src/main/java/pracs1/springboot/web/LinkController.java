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
import pracs1.springboot.search.search.PostsDto.SearchLinkResultDto;
import pracs1.springboot.search.search.PostsDto.SearchPostResultDto;
import pracs1.springboot.service.link.LinkService;
import pracs1.springboot.web.LinkDto.LinkListResponseDto;
import pracs1.springboot.web.LinkDto.LinkResponseDto;

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
        SearchLinkResultDto searchLinkResult = linkService.findSearchLinkByType(page, type, keyword);

        model.addAttribute("link", searchLinkResult.getSearchLinkListPaging());
        model.addAttribute("pagination", searchLinkResult.getPagination());

        model.addAttribute("type", searchLinkResult.getType());
        model.addAttribute("keyword", searchLinkResult.getKeyword());

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


