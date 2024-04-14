package cn.tuyucheng.taketoday.spring.data.persistence.deletionCascading;

import cn.tuyucheng.taketoday.spring.data.persistence.unidirectionalcascadingdelete.Article;
import cn.tuyucheng.taketoday.spring.data.persistence.unidirectionalcascadingdelete.ArticleRepository;
import cn.tuyucheng.taketoday.spring.data.persistence.unidirectionalcascadingdelete.ArticleService;
import cn.tuyucheng.taketoday.spring.data.persistence.unidirectionalcascadingdelete.CascadingDeleteApplication;
import cn.tuyucheng.taketoday.spring.data.persistence.unidirectionalcascadingdelete.Comment;
import cn.tuyucheng.taketoday.spring.data.persistence.unidirectionalcascadingdelete.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CascadingDeleteApplication.class)
@Transactional
class ArticleRepositoryIntegrationTest {

   @Autowired
   private ArticleRepository articleRepository;

   @Autowired
   private CommentRepository commentRepository;

   @Autowired
   private ArticleService articleService;

   @Test
   @Transactional
   void givenAnArticleAndItsComments_whenDeleteArticle_thenCommentsDeletedAutomatically() {
      Set<Comment> comments = new HashSet<>();
      Article article = new Article();
      article.setName("introduction to Spring");

      Comment comment1 = new Comment();
      comment1.setDescription("Explain types of Autowired");
      comment1.setDate(Date.from(Instant.now()));

      Comment comment2 = new Comment();
      comment2.setDescription("Good article");
      comment2.setDate(Date.from(Instant.now()
            .minus(10, ChronoUnit.MINUTES)));

      comments.add(comment1);
      comments.add(comment2);

      article.setComments(comments);
      articleRepository.save(article);

      List<Article> articles = articleRepository.findAll();
      assertThat(articles.size()).isEqualTo(1);
      Article retrivedArticle = articles.get(0);

      List<Comment> fetchedComments = commentRepository.findAll();
      assertThat(fetchedComments.size()).isEqualTo(2);

      articleService.deleteArticle(retrivedArticle);
      assertThat(articleRepository.findAll()).isEmpty();

      assertThat(commentRepository.findAll()).isEmpty();
   }
}