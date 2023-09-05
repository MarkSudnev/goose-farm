package person

import java.util.UUID

data class Person(
  val id: UUID,
  val name: String,
  val phone: String
)