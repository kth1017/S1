package pracs1.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    @NotEmpty(message = "제목을 입력해주세요")
    @Size(max = 10, message = "제목은 10자 이하여야합니다")
    private String title;
    @NotEmpty
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
