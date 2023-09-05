package person

import com.google.inject.Inject
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.format.Jackson
import org.http4k.routing.bind
import java.util.UUID

class PersonRouter @Inject constructor(private val personService: PersonService) {

  private val createPersonRequestLens = Jackson.autoBody<CreatePersonDto>().toLens()
  private val viewPersonsResponseLens = Jackson.autoBody<Collection<ViewPersonDto>>().toLens()

  val handlers = listOf(
    "/persons" bind POST to {
      val dto = createPersonRequestLens(it)
      personService.createPerson(dto.name, dto.phone)
      Response(Status.CREATED)
    },
    "/persons" bind GET to {
      val persons = personService.getPersons().map(ViewPersonDto::from)
      Response(Status.OK).with(viewPersonsResponseLens of persons)
    }
  )

  data class CreatePersonDto(
    val name: String,
    val phone: String
  )

  data class ViewPersonDto(
    val id: UUID,
    val name: String,
    val phone: String
  ) {
    companion object {
      fun from(person: Person) = ViewPersonDto(
        person.id, person.name, person.phone
      )
    }
  }
}