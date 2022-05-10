package pracs1.springboot.web.LinkDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pracs1.springboot.domain.link.Link;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LinkSaveRequestDto {
    private String postNum;
    private String githubRepo;

    @NotEmpty(message = "제목을 입력해주세요")
    @Size(max = 10, message = "제목은 10자 이하로 입력해주세요")
    private String title;
    @NotEmpty(message = "스택 종류를 입력해주세요")
    @Size(max = 10, message = "다음 항목은 10자 이하로 입력해주세요")
    private String stackCategory;
    @NotEmpty(message = "설명을 입력해주세요")
    private String description;

    public LinkSaveRequestDto(String postNum, String githubRepo, String title, String stackCategory, String description) {
        this.postNum = postNum;
        this.githubRepo = githubRepo;
        this.title = title;
        this.stackCategory = stackCategory;
        this.description = description;
    }

    public Link toEntity() {
        return new Link(title, stackCategory, description, postNum, githubRepo);
    }
}
