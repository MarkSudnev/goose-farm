package note

import com.google.inject.Inject
import java.util.UUID

class NoteRepository @Inject constructor() {

  private val notes = HashMap<UUID, Note>()

  fun add(note: Note) {
    if (!notes.containsKey(note.id)) {
      notes[note.id] = note
    }
  }

  fun getAll() = notes.values

  fun find(id: UUID) = notes[id]

  fun find(title: String) = notes.values.find { it.title.contains(title, true) }

  fun findByAuthor(authorId: UUID) = notes.values.find { it.author.id == authorId }

  fun remove(id: UUID) {
    notes.remove(id)
  }
}
