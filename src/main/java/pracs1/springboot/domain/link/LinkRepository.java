package pracs1.springboot.domain.link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pracs1.springboot.domain.posts.Posts;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query("SELECT l FROM Link l ORDER BY l.id DESC")
    List<Link> findAllDesc();

    List<Link> findByTitleContaining(String keyword);
    List<Link> findByStackCategoryContaining(String keyword);
    List<Link> findByDescriptionContaining(String keyword);



}
