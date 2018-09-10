package app.com.notesappwithandroidarchcomponents.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNotesAscByTitle(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Notes): Long
}
