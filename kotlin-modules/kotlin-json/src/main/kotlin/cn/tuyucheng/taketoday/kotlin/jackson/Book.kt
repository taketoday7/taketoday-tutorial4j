package cn.tuyucheng.taketoday.kotlin.jackson

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Book(var title: String, @JsonProperty("author") var authorName: String) {
   var genres: List<String>? = emptyList()
}