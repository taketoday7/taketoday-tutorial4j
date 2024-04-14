@file:JvmName("APIServer")


import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level

data class Author(val name: String, val website: String)
data class ToDo(var id: Int, val name: String, val description: String, val completed: Boolean)

fun main(args: Array<String>) {

   val toDoList = ArrayList<ToDo>();
   val jsonResponse = """{
            "id": 1,
            "task": "Pay waterbill",
            "description": "Pay water bill today",
        }"""


   embeddedServer(Netty, 8080) {
      install(DefaultHeaders) {
         header("X-Developer", "Tuyucheng")
      }
      install(CallLogging) {
         level = Level.DEBUG
         filter { call -> call.request.path().startsWith("/todo") }
         filter { call -> call.request.path().startsWith("/author") }
      }
      install(ContentNegotiation) {
         gson {
            setPrettyPrinting()
         }
      }
      routing() {
         route("/todo") {
            post {
               var toDo = call.receive<ToDo>();
               toDo.id = toDoList.size;
               toDoList.add(toDo);
               call.respond("Added")

            }
            delete("/{id}") {
               call.respond(toDoList.removeAt(call.parameters["id"]!!.toInt()));
            }
            get("/{id}") {

               call.respond(toDoList[call.parameters["id"]!!.toInt()]);
            }
            get {
               call.respond(toDoList);
            }
         }
         get("/author") {
            call.respond(Author("Tuyucheng", "tuyucheng.com"));

         }


      }
   }.start(wait = true)
}