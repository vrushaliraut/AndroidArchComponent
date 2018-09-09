package app.com.notesappwithandroidarchcomponents.di.component

import android.app.Application
import app.com.notesappwithandroidarchcomponents.NotesApplication
import app.com.notesappwithandroidarchcomponents.di.module.ActivityModule
import app.com.notesappwithandroidarchcomponents.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [(AppModule::class), (ActivityModule::class), (AndroidSupportInjectionModule::class)])
interface  Appcomponent{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : Appcomponent
    }

    fun Inject(app : NotesApplication)
}