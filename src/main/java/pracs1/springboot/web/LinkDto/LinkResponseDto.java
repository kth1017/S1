package pracs1.springboot.web.LinkDto;

import lombok.Getter;
import pracs1.springboot.domain.link.Link;
import pracs1.springboot.domain.posts.Posts;

@Getter
public class LinkResponseDto {

    private Long id;
    private String title;

    private String stackCategory;
    private String description;
    private String blogLink;
    private String githubLink;

    public LinkResponseDto(Link entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.stackCategory = entity.getStackCategory();
        this.description = entity.getDescription();
        this.blogLink = entity.getBlogLink();
        this.githubLink = entity.getGithubLink();
    }
}