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
    // 상중하 1~3 메인 4
    @Column(length = 2, nullable = false)
    private Long importance;

    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 20, nullable = false)
    private String stackCategory;
    @Column(length = 100, nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String postNum;
    @Column(columnDefinition = "TEXT")
    private String githubRepo;



    public Link(String title, String stackCategory, String description, String postNum, String githubRepo, Long importance) {
        this.title = title;
        this.stackCategory = stackCategory;
        this.description = description;
        this.postNum = postNum;
        this.githubRepo = githubRepo;
        this.importance = importance;
    }

    public void update(String title, String stackCategory, String description, String postNum, String githubRepo, Long importance) {
        this.title = title;
        this.stackCategory = stackCategory;
        this.description = description;
        this.postNum = postNum;
        this.githubRepo = githubRepo;
        this.importance = importance;

    }
}
