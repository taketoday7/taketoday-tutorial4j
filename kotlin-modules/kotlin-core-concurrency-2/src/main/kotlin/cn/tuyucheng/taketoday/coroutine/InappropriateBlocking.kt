package cn.tuyucheng.taketoday.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun sleepThread() {
   withContext(Dispatchers.IO) {
      Thread.sleep(100L)
   }
}
