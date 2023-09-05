package person

import java.util.UUID

class PersonRepository {

  private val persons = HashMap<UUID, Person>()

  init {
    val person = Person(
      UUID.randomUUID(),
      "Mark Sudnev",
      "337-040-520"
    )
    persons[person.id] = person
  }

  fun add(person: Person) {
    if (!persons.containsKey(person.id)) {
      persons[person.id] = person
    }
  }

  fun remove(id: UUID) {
    persons.remove(id)
  }

  fun getAll() = persons.values

  fun find(id: UUID) = persons[id]

  fun find(name: String) = persons.values.find { it.name.contains(name, true) }
}