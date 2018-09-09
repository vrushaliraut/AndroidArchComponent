package app.com.notesappwithandroidarchcomponents.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.com.notesappwithandroidarchcomponents.ViewModelFactory
import app.com.notesappwithandroidarchcomponents.di.scope.ViewModelKey
import app.com.notesappwithandroidarchcomponents.feature.NoteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindMainViewModel(noteViewModel: NoteViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
