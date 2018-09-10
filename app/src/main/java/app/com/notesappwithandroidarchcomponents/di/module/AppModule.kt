package app.com.notesappwithandroidarchcomponents.di.module

import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import app.com.notesappwithandroidarchcomponents.NotesApplication
import app.com.notesappwithandroidarchcomponents.ViewModelFactory
import app.com.notesappwithandroidarchcomponents.data.NoteDao
import app.com.notesappwithandroidarchcomponents.data.NotesDatabase
import app.com.notesappwithandroidarchcomponents.data.NotesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    fun provideContext(application: NotesApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDB(context: Context): NotesDatabase = Room.databaseBuilder(context, NotesDatabase::class.java, "tech-notes")
            .build()

    @Provides
    @Singleton
    fun provideNoteDao(database: NotesDatabase): NoteDao = database.noteDao()

    @Singleton
    @Provides
    fun provideNotesRepository(db: NotesDatabase): NotesRepository {
        return NotesRepository(db = db)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory {
        return factory
    }
}
