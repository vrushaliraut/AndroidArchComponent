package app.com.notesappwithandroidarchcomponents.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import app.com.notesappwithandroidarchcomponents.helpers.parcelableCreator

@Entity(tableName = "note")
data class Notes(

        var title: String,
        var note: String,
        var photoPath: String,
        var recordedAt: Long
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong()) {
        id = parcel.readLong()
    }

    constructor() : this("", "", "", -1)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(note)
        parcel.writeString(photoPath)
        parcel.writeLong(recordedAt)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::Notes)
    }
}
