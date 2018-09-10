package app.com.notesappwithandroidarchcomponents

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import app.com.notesappwithandroidarchcomponents.data.Notes
import app.com.notesappwithandroidarchcomponents.feature.NoteViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_notes_detail.*
import javax.inject.Inject


class NotesDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { ViewModelProviders.of(this@NotesDetailActivity, viewModelFactory).get(NoteViewModel::class.java) }


    private lateinit var notes: Notes
    lateinit var progressDialog: Dialog

    companion object {
        fun newIntent(context: Context, notes: Notes? = null): Intent {
            return Intent(context, NotesDetailActivity::class.java).apply {
                notes?.let {
                    putExtra("notes", notes)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@NotesDetailActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_detail)
        initData()
        initViews()
        setData()
        saveData()
    }

    private fun saveData() {

        btnSave.setOnClickListener {
            validateAndSaveNote()
        }
    }

    private fun validateAndSaveNote() {
        if (etNoteTitle.text.isEmpty()) {
            etNoteTitle.error = "This field can't be empty"
            return
        }
        if (etNoteContent.text.isEmpty()) {
            etNoteContent.error = "This field can't be empty"
            return
        }
        notes.apply {
            title = etNoteTitle.text.toString()
            note = etNoteContent.text.toString()
        }

        viewModel.addNote(notes).observe(
                this,
                Observer {
                    progressDialog.dismiss()
                    finish()
                }
        )
    }

    private fun setData() {
        etNoteTitle.setText(notes.title)
        etNoteContent.setText(notes.note)
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            title = if (notes.title.isBlank())
                getString(R.string.add_note)
            else
                getString(R.string.edit_note)
            setDisplayHomeAsUpEnabled(true)
        }

        setProgressBar()
    }

    private fun setProgressBar() {
        progressDialog = Dialog(this@NotesDetailActivity, R.style.ProgressDialog)
        progressDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(layoutInflater.inflate(R.layout.layout_progress_fullscreen, null))
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setCancelable(false)
    }

    private fun initData() {
        notes = if (intent.hasExtra("notes")) {
            intent.extras.getParcelable("notes")
        } else {
            Notes()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
