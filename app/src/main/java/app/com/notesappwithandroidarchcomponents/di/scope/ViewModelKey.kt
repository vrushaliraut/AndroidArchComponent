package app.com.notesappwithandroidarchcomponents.di.scope

import android.arch.lifecycle.ViewModel
import app.com.notesappwithandroidarchcomponents.feature.NoteViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<NoteViewModel>)