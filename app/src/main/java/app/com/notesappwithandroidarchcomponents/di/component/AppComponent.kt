package app.com.notesappwithandroidarchcomponents.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import app.com.notesappwithandroidarchcomponents.NotesApplication
import app.com.notesappwithandroidarchcomponents.di.module.ActivityModule
import app.com.notesappwithandroidarchcomponents.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (ActivityModule::class), (AndroidSupportInjectionModule::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicatlion(application: NotesApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: NotesApplication)
}
