package pracs1.springboot.web.LinkDto;

import lombok.Getter;
import pracs1.springboot.domain.link.Link;

@Getter
public class LinkResponseDto {

    private Long id;
    private String title;

    private String stackCategory;
    private String description;
    private String postNum;
    private String githubRepo;

    public LinkResponseDto(Link entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.stackCategory = entity.getStackCategory();
        this.description = entity.getDescription();
        this.postNum = entity.getPostNum();
        this.githubRepo = entity.getGithubRepo();
    }
}