//@file:JvmName("CustomName")
package cn.tuyucheng.taketoday.run

class RunClass {
//companion object {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        println("Running main function")
//    }
//}

   fun printInsideClass() {
      println("Running inside the RunClass")
   }
}

fun main(args: Array<String>) {
   println("Running the main function")
   RunClass().printInsideClass()
}
