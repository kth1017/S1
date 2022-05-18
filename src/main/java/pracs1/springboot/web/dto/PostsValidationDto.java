package pracs1.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class PostsValidationDto {

    @NotNull
    private String title;
    private String author;
    private String content;

    public PostsValidationDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
