package pracs1.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pracs1.springboot.domain.posts.Posts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @NotEmpty(message = "제목을 입력해주세요")
    @Size(max = 10, message = "제목은 10자 이하로 입력해주세요")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;
    @NotEmpty(message = "작성자를 입력해주세요")
    @Size(max = 10, message = "작성자는 10자 이하로 입력해주세요")
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
