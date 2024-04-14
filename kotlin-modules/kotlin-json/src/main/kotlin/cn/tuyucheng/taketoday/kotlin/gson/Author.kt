package cn.tuyucheng.taketoday.kotlin.gson

import com.google.gson.annotations.SerializedName

data class Author(
      var name: String,
      var type: String? = null,
      @SerializedName("author_articles")
      var articles: List<cn.tuyucheng.taketoday.kotlin.gson.Article>? = null,
) {
}