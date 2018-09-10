package app.com.notesappwithandroidarchcomponents.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NotesRepository(var db: NotesDatabase) {

    fun getAllNotes(sort: String): LiveData<List<Notes>> {
        return when (sort) {
            "Title ascending" -> db.noteDao().getNotesAscByTitle()
            else -> db.noteDao().getNotesAscByTitle()
        }
    }

    fun addNote(notes: Notes): LiveData<Notes> {
        val mutableLiveData = MutableLiveData<Notes>()
        Single.fromCallable {
            db.noteDao().insert(notes)
        }.subScribeOn(Schedulers.io())
                .ObserverOn(AndroidSchedulers.mainThread())
                .subscribe({ id ->
                    Timber.tag("DB").d("inserted record with $id")
                    notes.id = id
                    mutableLiveData.value = notes
                }, { e ->
                    e.printStackTrace()
                })
        return mutableLiveData
    }
}
