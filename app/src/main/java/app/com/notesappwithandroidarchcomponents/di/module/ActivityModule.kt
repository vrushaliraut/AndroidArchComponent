package app.com.notesappwithandroidarchcomponents.di.module

import app.com.notesappwithandroidarchcomponents.NotesDetailActivity
import app.com.notesappwithandroidarchcomponents.feature.detail.NotesListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeNotesListActivity(): NotesListActivity

    @ContributesAndroidInjector
    internal abstract fun contributeNotesDetailsActivity(): NotesDetailActivity
}
