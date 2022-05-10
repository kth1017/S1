package pracs1.springboot.domain.link;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String stackCategory;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String blogLink;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String githubLink;

    public Link(String title, String stackCategory, String description, String blogLink, String githubLink) {
        this.title = title;
        this.stackCategory = stackCategory;
        this.description = description;
        this.blogLink = blogLink;
        this.githubLink = githubLink;
    }

    public void update(String title, String stackCategory, String description, String blogLink, String githubLink) {
        this.title = title;
        this.stackCategory = stackCategory;
        this.description = description;
        this.blogLink = blogLink;
        this.githubLink = githubLink;
    }
}
