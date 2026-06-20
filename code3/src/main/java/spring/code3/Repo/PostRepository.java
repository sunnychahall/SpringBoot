package spring.code3.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.code3.Entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
