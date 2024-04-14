package cn.tuyucheng.taketoday.subclassinnerclass;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubClassUnitTest {
   @Test
   public void givenSubclassWhenInstantiatedThenSubclassObjectIsPossible() {
      Notifier emailNotifier = new EmailNotifier();
      assertThat(emailNotifier).hasSameClassAs(new EmailNotifier());
      assertThat(emailNotifier).isExactlyInstanceOf(EmailNotifier.class);

      Notifier textMessageNotifier = new TextMessageNotifier();
      assertThat(textMessageNotifier).isInstanceOf(Notifier.class);
      assertThat(textMessageNotifier).isExactlyInstanceOf(TextMessageNotifier.class);
   }
}
