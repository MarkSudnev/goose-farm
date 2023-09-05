package person

import com.google.inject.Inject
import java.util.UUID

class PersonService @Inject constructor(
  @MapBasedRepository private val repository: PersonRepository
) {

  fun createPerson(name: String, phone: String) {
    repository.add(Person(UUID.randomUUID(), name, phone))
  }

  fun getPersons() = repository.getAll()

  fun findPerson(name: String) = repository.find(name)

  fun findPerson(id: UUID) = repository.find(id)

  fun find(name: String) = repository.getAll().filter { it.name.contains(name, true) }

}