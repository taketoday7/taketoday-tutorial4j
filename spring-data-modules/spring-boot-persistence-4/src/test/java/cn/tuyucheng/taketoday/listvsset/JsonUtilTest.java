package cn.tuyucheng.taketoday.listvsset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cn.tuyucheng.taketoday.listvsset.eager.list.fulldomain.Application;
import cn.tuyucheng.taketoday.listvsset.eager.list.fulldomain.Comment;
import cn.tuyucheng.taketoday.listvsset.eager.list.fulldomain.Group;
import cn.tuyucheng.taketoday.listvsset.eager.list.fulldomain.Post;
import cn.tuyucheng.taketoday.listvsset.eager.list.fulldomain.Profile;
import cn.tuyucheng.taketoday.listvsset.eager.list.fulldomain.User;
import cn.tuyucheng.taketoday.listvsset.util.JsonUtils;
import cn.tuyucheng.taketoday.listvsset.util.TestConfig;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Application.class, TestConfig.class})
class JsonUtilTest {

   @Autowired
   private JsonUtils jsonUtils;

   @Test
   void givenFileWhenConvertingToUsersThenConversionIsCorrect() {
      List<User> users = jsonUtils.getUsers(User.class);
      assertThat(users).isNotEmpty();
      boolean wentThroughEverything = false;
      for (User user : users) {
         assertThat(user.getGroups()).isNotNull();
         Profile profile = user.getProfile();
         if (profile != null) {
            assertThat(user.getId()).isEqualTo(profile.getUser().getId());
         }
         for (Post post : user.getPosts()) {
            assertThat(user.getId()).isEqualTo(post.getAuthor().getId());
            if (post.getComments() != null) {
               wentThroughEverything = true;
               for (Comment comment : post.getComments()) {
                  assertThat(post.getId()).isEqualTo(comment.getPost().getId());
               }
            }
         }
      }
      assertTrue(wentThroughEverything);
   }

   @Test
   void givenFileWhenConvertingToGroupsThenConversionIsCorrect() {
      List<Group> groups = jsonUtils.getGroups(Group.class);
      assertThat(groups).isNotEmpty();
   }
}