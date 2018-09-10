package app.com.notesappwithandroidarchcomponents.feature.detail

import android.view.View

interface RVAdapterItemClickListener{
    fun onClick(position:Int)
    fun onClick(position:Int, view: View) = Any()
}
