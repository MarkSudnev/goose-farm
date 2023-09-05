import com.google.inject.Guice
import note.NoteRouter
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer
import person.PersonModule
import person.PersonRouter

fun main(args: Array<String>) {

  val injector = Guice.createInjector(PersonModule())
  val personRouter = injector.getInstance(PersonRouter::class.java)
  val noteRouter = injector.getInstance(NoteRouter::class.java)

  val handlers = (
      personRouter.handlers + noteRouter.handlers
      ).toTypedArray()

  val routes = routes(*handlers)

  routes.asServer(Undertow(9000)).start()
}

