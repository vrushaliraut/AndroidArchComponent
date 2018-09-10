package app.com.notesappwithandroidarchcomponents.feature

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import app.com.notesappwithandroidarchcomponents.data.Notes
import app.com.notesappwithandroidarchcomponents.data.NotesRepository
import javax.inject.Inject

class NoteViewModel @Inject constructor(var repo : NotesRepository) : ViewModel(){

    fun getNotes(sort: String): LiveData<List<Notes>>{
        return repo.getAllNotes(sort = sort)
    }

    fun addNote(notes: Notes): LiveData<Notes> {
        return repo.addNote(notes = notes)
    }
}
