package note

import com.google.inject.Inject
import person.PersonService
import java.time.LocalDateTime
import java.util.UUID

class NoteService @Inject constructor(
  private val repository: NoteRepository,
  private val personService: PersonService
) {

  fun createNote(title: String, content: String, authorId: UUID) {
    personService.findPerson(authorId)?.let {
      val note = Note(UUID.randomUUID(), title, content, it, LocalDateTime.now())
      repository.add(note)
    }
  }

  fun getNotes() = repository.getAll()
}