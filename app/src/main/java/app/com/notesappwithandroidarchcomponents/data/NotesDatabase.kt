package app.com.notesappwithandroidarchcomponents.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(
        entities = [(Notes::class)],
        version = 1,
        exportSchema = true
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
