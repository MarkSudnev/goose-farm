package person

import com.google.inject.AbstractModule
import com.google.inject.Provides

class PersonModule: AbstractModule() {

  @Provides
  @MapBasedRepository
  fun providePersonRepository() = PersonRepository()
}