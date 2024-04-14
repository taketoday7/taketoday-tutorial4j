package cn.tuyucheng.taketoday.logging

import mu.KotlinLogging
import mu.withLoggingContext

private val logger = KotlinLogging.logger {}

fun main() {
   withLoggingContext("user" to "Tuyucheng") {
      logger.info { "Log with MDC" }
   }
}