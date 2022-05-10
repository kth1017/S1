package pracs1.springboot.web.LinkDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pracs1.springboot.domain.link.Link;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class LinkUpdateRequestDto {
    private String blogLink;
    private String githubLink;

    @NotEmpty(message = "제목을 입력해주세요")
    @Size(max = 10, message = "제목은 10자 이하로 입력해주세요")
    private String title;
    @NotEmpty(message = "스택 종류를 입력해주세요")
    @Size(max = 10, message = "다음 항목은 10자 이하로 입력해주세요")
    private String stackCategory;
    @NotEmpty(message = "설명을 입력해주세요")
    private String description;

    public LinkUpdateRequestDto(String blogLink, String githubLink, String title, String stackCategory, String description) {
        this.blogLink = blogLink;
        this.githubLink = githubLink;
        this.title = title;
        this.stackCategory = stackCategory;
        this.description = description;
    }
}
