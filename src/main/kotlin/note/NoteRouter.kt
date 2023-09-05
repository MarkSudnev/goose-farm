package note

import com.google.inject.Inject
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.format.Jackson
import org.http4k.routing.bind
import java.util.UUID

class NoteRouter @Inject constructor(noteService: NoteService) {

  private val createNoteRequestLens = Jackson.autoBody<CreateNoteDto>().toLens()
  private val viewNotesResponseLens = Jackson.autoBody<Collection<ViewNoteDto>>().toLens()

  val handlers = listOf(
    "/notes" bind POST to {
      val dto = createNoteRequestLens(it)
      noteService.createNote(dto.title, dto.content, dto.authorId)
      Response(CREATED)
    },
    "/notes" bind GET to {
      val notes = noteService.getNotes().map(ViewNoteDto::from)
      Response(OK).with(viewNotesResponseLens of notes)
    }
  )

  data class CreateNoteDto(
    val title: String,
    val content: String,
    val authorId: UUID
  )

  data class ViewNoteDto(
    val id: UUID,
    val title: String,
    val content: String,
    val author: AuthorDto
  ) {
    companion object {

      fun from(note: Note) =
        ViewNoteDto(
          note.id,
          note.title,
          note.content,
          AuthorDto(
            note.author.id,
            note.author.name
          )
        )
    }
  }

  data class AuthorDto(
    val id: UUID,
    val name: String
  )
}