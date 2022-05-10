package pracs1.springboot.web.LinkDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pracs1.springboot.domain.link.Link;

@Getter
@NoArgsConstructor
public class LinkListResponseDto {
    private Long id;
    private String title;

    private String stackCategory;
    private String description;
    private String postNum;
    private String githubRepo;

    public LinkListResponseDto(Link entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.stackCategory = entity.getStackCategory();
        this.description = entity.getDescription();
        this.postNum = entity.getPostNum();
        this.githubRepo = entity.getGithubRepo();
    }
}
