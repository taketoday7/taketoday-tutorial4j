package cn.tuyucheng.taketoday.exceptionhandling

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication(scanBasePackages = arrayOf("cn.tuyucheng.taketoday.exceptionhandling"), exclude = arrayOf(SecurityAutoConfiguration::class))
class ExceptionHandlingSrpingApplication

fun main(args: Array<String>) {
   SpringApplication.run(ExceptionHandlingSrpingApplication::class.java, *args)
}
