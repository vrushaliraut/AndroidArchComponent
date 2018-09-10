package app.com.notesappwithandroidarchcomponents.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import app.com.notesappwithandroidarchcomponents.NotesDetailActivity
import app.com.notesappwithandroidarchcomponents.R
import app.com.notesappwithandroidarchcomponents.ViewModelFactory
import app.com.notesappwithandroidarchcomponents.data.Notes
import app.com.notesappwithandroidarchcomponents.feature.NoteViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list_notes.fab
import kotlinx.android.synthetic.main.activity_list_notes.rvNotes
import kotlinx.android.synthetic.main.activity_list_notes.toolbar
import javax.inject.Inject

class NotesListActivity :AppCompatActivity(), RVAdapterItemClickListener{

    private lateinit var notesList: ArrayList<Notes>
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { ViewModelProviders.of(this@NotesListActivity, viewModelFactory).get(NoteViewModel::class.java) }
    private lateinit var sort: String
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@NotesListActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_notes)
        notesList = ArrayList()
        adapter = NotesRVAdapter(notesList)
        adapter.setOnItemClickListener(this@NotesListActivity)
        initViews()
        sort = ""
        fetchData()
    }

    private fun fetchData() {
        viewModel.getNotes(sort = sort).observe(
                this,
                Observer { data ->
                    run {
                        notesList.clear()
                        notesList.addAll(data!!)
                        adapter.updateItems(notesList)
                    }
                }
        )
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Notes"
        }
        fab.setOnClickListener {
            startActivity(NotesDetailActivity.newIntent(this@NotesListActivity))
        }

        setUpRecyclerViewList()

    }

    private fun setUpRecyclerViewList() {
        rvNotes.layoutManager = LinearLayoutManager(this@NotesListActivity)
        rvNotes.addItemDecoration(DividerItemDecoration(this@NotesListActivity))
        rvNotes.adapter = adapter
    }

    override fun onClick(position: Int) {
        startActivity(NotesDetailActivity.newIntent(this@NotesListActivity, adapter.getItemAtPos(position)))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_notes_list, menu)
        val item = menu?.findItem(R.id.actionSearch)
   //     searchView.setMenuItem(item)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item?.itemId == R.id.actionSort) {
            showSortDialog()
            true
        } else
            super.onOptionsItemSelected(item)
    }

    private fun showSortDialog() {
        val sortDialogBuilder = AlertDialog.Builder(this@NotesListActivity)
        sortDialogBuilder.setTitle("Sort")
/*
        val arrayAdapter = ArrayAdapter<String>(this@NotesListActivity, android.R.layout.select_dialog_singlechoice)
        arrayAdapter.add("Title ascending")
        sortDialogBuilder.setNegativeButton("cancel", { dialog, _ -> dialog.dismiss() })

        sortDialogBuilder.setAdapter(arrayAdapter, { dialogInterface, which ->
            sort = when (which) {
                0 -> "Title ascending"
                else -> {
                    "Title ascending"
                }
            }
           //this seems wrong
            // fetchData(sort)
        })
        sortDialogBuilder.show()*/
    }


}
