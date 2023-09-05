package note

import person.Person
import java.time.LocalDateTime
import java.util.UUID

data class Note(
  val id: UUID,
  val title: String,
  val content: String,
  val author: Person,
  val timestamp: LocalDateTime
)
