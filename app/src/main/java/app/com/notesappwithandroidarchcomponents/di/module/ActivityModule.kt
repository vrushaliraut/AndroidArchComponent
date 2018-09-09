package app.com.notesappwithandroidarchcomponents.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeNotesListActivity()

    @ContributesAndroidInjector
    internal abstract fun contributeNotesDetailsActivity()
}