package cn.tuyucheng.taketoday.listvsset.eager.set.moderatedomain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}