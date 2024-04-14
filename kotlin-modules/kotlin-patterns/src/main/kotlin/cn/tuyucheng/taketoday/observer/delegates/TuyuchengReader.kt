package cn.tuyucheng.taketoday.observer.delegates

fun main() {
   val newsletter = TuyuchengNewsletter()
   newsletter.newestArticleObservers.add { newestArticleUrl ->
      println("New Tuyucheng article: ${newestArticleUrl}")
   }
}